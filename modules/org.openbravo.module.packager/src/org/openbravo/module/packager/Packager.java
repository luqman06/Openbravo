/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2011 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */

package org.openbravo.module.packager;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDao;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.utility.DataSet;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalConnectionProvider;

import com.openbravo.gps.translationsmanager.ExportToXmlFiles;

public class Packager implements org.openbravo.scheduling.Process {
  private final Logger log4j = Logger.getLogger(Packager.class);

  private static final String EXPORTDATABASE = "export.database";
  private static final String PACKAGEMODULE = "package.module";

  @Override
  public void execute(ProcessBundle bundle) throws Exception {
    final String moduleId = (String) bundle.getParams().get("AD_Module_ID");
    final boolean exportDatasets = "Y".equals((String) bundle.getParams().get("exportDatasets")) ? true
        : false;
    final boolean exportDatabase = "Y".equals((String) bundle.getParams().get("exportDatabase")) ? true
        : false;
    final boolean exportTranslation = "Y".equals((String) bundle.getParams().get(
        "exportTranslation")) ? true : false;
    final boolean packageModule = "Y".equals((String) bundle.getParams().get("packageModule")) ? true
        : false;
    final String lang = bundle.getContext().getLanguage();

    try {
      OBContext.setAdminMode(false);
      final Module module = OBDao.getOBObjectListFromString(Module.class, moduleId).get(0);

      if (exportDatasets && module.isHasReferenceData()) {
        log4j.debug("Exporting datasets...");
        for (final DataSet dataset : OBDao.getFilteredCriteria(DataSet.class,
            Restrictions.eq(DataSet.PROPERTY_MODULE, module)).list()) {
          PackagerUtility.exportDataset(dataset);
        }
      }

      if (exportDatabase) {
        log4j.debug("Exporting database...");
        PackagerUtility.executeAntTarget(EXPORTDATABASE, new HashMap<String, String>());
      }

      if (exportTranslation && module.isTranslationModule()) {
        log4j.debug("Exporting translation...");
        new ExportToXmlFiles().execute(bundle);
      }

      if (packageModule) {
        log4j.debug("Packaging module: " + module.getIdentifier());
        final Map<String, String> params = new HashMap<String, String>();
        params.put("module", module.getJavaPackage());
        PackagerUtility.executeAntTarget(PACKAGEMODULE, params);
      }
    } finally {
      OBContext.restorePreviousMode();
    }

    final OBError msg = new OBError();
    msg.setType("Success");
    msg.setTitle(Utility.messageBD(new DalConnectionProvider(), "Done", lang));
    msg.setMessage(Utility.messageBD(new DalConnectionProvider(), "Success", lang));
    bundle.setResult(msg);
  }
}
