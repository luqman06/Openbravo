package com.openbravo.gps.translationsmanager;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_forms.TranslationManager;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.module.Module;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 * This process creates a standard translation module for the current module.
 * 
 */
public class ImportFromXmlFiles extends DalBaseProcess {
  private static final Logger log4j = Logger.getLogger(TranslationManager.class);

  @Override
  public void execute(ProcessBundle bundle) throws Exception {

    final String StrSourceModuleId = (String) bundle.getParams().get("AD_Module_ID");

    Properties prop = OBPropertiesProvider.getInstance().getOpenbravoProperties();
    String obDir = prop.getProperty("source.path");

    OBContext.setAdminMode();
    try {

      Module TranslationModule = OBDal.getInstance().get(Module.class, StrSourceModuleId);

      log4j.info("Importing language " + TranslationModule.getLanguage().getLanguage()
          + " from module " + TranslationModule.getName());
      TranslationManager.importTrlDirectory(bundle.getConnection(), obDir + "/modules/"
          + TranslationModule.getJavaPackage() + "/referencedata/translation", TranslationModule
          .getLanguage().getLanguage(), "0", null);

      OBError msg = new OBError();
      msg.setType("Success");
      msg.setTitle("@Success@");
      msg.setMessage("@obgpstm_import_success@");
      bundle.setResult(msg);
    } finally {
      OBContext.restorePreviousMode();
    }
  }

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    // TODO Auto-generated method stub

  }
}
