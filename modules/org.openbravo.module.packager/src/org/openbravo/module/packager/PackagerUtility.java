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

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.utility.DataSet;
import org.openbravo.service.db.DataExportService;

public class PackagerUtility {

  private final static Logger log4j = Logger.getLogger(PackagerUtility.class);

  private static final String SOURCEPATH = OBPropertiesProvider.getInstance()
      .getOpenbravoProperties().get("source.path").toString();
  protected static final File buildXML = new File(SOURCEPATH, "build.xml");

  /**
   * Executes any OB ant task
   * 
   * @param target
   *          the ant task to execute
   * @param params
   *          optional parameters to pass to the ant task
   * @return OBError with the result of the execution
   * 
   * @throws OBException
   *           in case of any error, it returns an OBException
   */
  public static void executeAntTarget(final String target, final Map<String, String> params)
      throws OBException {
    log4j.debug("Executing ant task: " + target + "...");

    ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
    Project project = new Project();
    project.setUserProperty("ant.file", buildXML.getAbsolutePath());
    project.init();
    project.addReference("ant.projectHelper", projectHelper);
    projectHelper.parse(project, buildXML);

    try {
      for (final String param : params.keySet()) {
        log4j.debug("Parameter: " + param + "=" + params.get(param));
        project.setProperty(param, params.get(param));
      }
      project.executeTarget(target);
      log4j.debug("Ant task " + target + " has finished without errors.");
    } catch (final Exception e) {
      log4j.error("Error running " + target + " ant task");
      e.printStackTrace();
      throw new OBException(e.getMessage(), e);
    }

  }

  /**
   * Exports the dataset generating the correspondan XML file into the dataset's modules path
   * 
   * @param dataset
   * @return OBError with the result of the execution
   * 
   * @throws OBException
   *           in case of any error, it returns an OBException
   */
  public static void exportDataset(final DataSet dataset) throws OBException {
    log4j.debug("Exporting dataset " + dataset.getIdentifier() + "...");

    try {
      OBContext.setAdminMode(true);
      final Module module = dataset.getModule();

      final String xml = DataExportService.getInstance().exportDataSetToXML(dataset,
          module.getId(), new java.util.HashMap<String, Object>());
      final File folder = new File(SOURCEPATH + ("0".equals(module.getId()) ? "" : "/modules/")
          + module.getJavaPackage() + "/referencedata/standard");
      final File xmlFile = new File(folder, Utility.wikifiedName(dataset.getName()) + ".xml");
      if (!folder.exists())
        folder.mkdirs();

      FileOutputStream myOutputStream = new FileOutputStream(xmlFile);
      myOutputStream.write(xml.getBytes("UTF-8"));
      myOutputStream.close();

      MessageDigest cs = MessageDigest.getInstance("MD5");
      cs.update(xml.getBytes("UTF-8"));
      dataset.setChecksum(new BigInteger(1, cs.digest()).toString());
      OBDal.getInstance().save(dataset);
    } catch (final Exception e) {
      log4j.error("Error exporting dataset");
      e.printStackTrace();
      throw new OBException(e.getMessage(), e);
    } finally {
      OBContext.restorePreviousMode();
    }
  }

}
