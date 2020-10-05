package com.openbravo.gps.translationsmanager;

import org.hibernate.criterion.Expression;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.module.ModuleDependency;
import org.openbravo.model.ad.system.Language;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.utils.Replace;

/**
 * This process creates a standard translation module for the current module.
 * 
 */
public class CreateTranslationModule extends DalBaseProcess {

  @Override
  public void execute(ProcessBundle bundle) throws Exception {

    final String StrSourceModuleId = (String) bundle.getParams().get("AD_Module_ID");
    final String StrAdLanguage = (String) bundle.getParams().get("adLanguage");

    final Module SourceModule = OBDal.getInstance().get(Module.class, StrSourceModuleId);

    String ModuleName = SourceModule.getName() + " (" + StrAdLanguage + ")";
    String ModulePackage = SourceModule.getJavaPackage() + "." + StrAdLanguage;

    OBContext.setAdminMode();
    try {
      OBCriteria<Module> modCriteria = OBDal.getInstance().createCriteria(Module.class);
      modCriteria.add(Expression.ilike(Module.PROPERTY_NAME, ModuleName));
      if (modCriteria.count() != 0) {
        OBError msg = new OBError();
        msg.setType("Info");
        msg.setMessage(ModuleName + " @ModuleAlreadyExists@");
        bundle.setResult(msg);
        return;
      }
      Module module = OBProvider.getInstance().get(Module.class);
      module.setActive(true);
      module.setName(ModuleName);
      module.setJavaPackage(ModulePackage);
      module.setDescription(ModuleName + " is a translation module for " + SourceModule.getName());
      module.setInDevelopment(true);
      module.setVersion("1.0.0");
      module.setTranslationRequired(false);
      module.setTranslationModule(true);
      OBCriteria<Language> langCriteria = OBDal.getInstance().createCriteria(Language.class);
      langCriteria.add(Expression.eq(Language.PROPERTY_LANGUAGE, StrAdLanguage));
      module.setLanguage(langCriteria.list().get(0));
      OBDal.getInstance().save(module);

      ModuleDependency dep = OBProvider.getInstance().get(ModuleDependency.class);
      dep.setActive(true);
      dep.setModule(module);
      dep.setDependentModule(SourceModule);
      dep.setDependantModuleName(SourceModule.getName());
      dep.setFirstVersion(SourceModule.getVersion());
      dep.setIncluded(false);
      OBDal.getInstance().save(dep);
      OBDal.getInstance().commitAndClose();

      OBError msg = new OBError();
      msg.setType("Success");
      msg.setTitle("@Success@");
      String messagestring = Utility.messageBD(bundle.getConnection(),
          "obgpstm_create_module_success", bundle.getContext().getLanguage());
      messagestring = Replace.replace(messagestring, "&quot;", "\"");
      messagestring = messagestring.replace("@module_id@", module.getId());
      messagestring = messagestring.replace("@module_name@", ModuleName);
      msg.setMessage(messagestring);
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
