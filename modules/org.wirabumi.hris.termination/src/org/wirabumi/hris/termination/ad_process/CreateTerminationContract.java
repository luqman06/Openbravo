package org.wirabumi.hris.termination.ad_process;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.HrisDataHandler;
import org.wirabumi.hris.employee.master.data.EmployeePosition;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.termination.tm_termination;

public class CreateTerminationContract extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    try {
      int processedData = 0;

      OBCriteria<tm_termination> getValidData = OBDal.getInstance().createCriteria(
          tm_termination.class);
      getValidData.add(Restrictions.isNull(tm_termination.PROPERTY_HRISCONTRACTFK));
      getValidData.add(Restrictions.eq(tm_termination.PROPERTY_DOCUMENTSTATUS, "CO"));
      getValidData.add(Restrictions.isNotNull(tm_termination.PROPERTY_CONTRACTNO));

      List<tm_termination> listData = getValidData.list();

      if (listData.size() <= 0) {
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            listData.size() + " @HRIS_DataMatch@");
        final OBError msg = new OBError();
        msg.setType("Info");
        msg.setTitle("Processed Data");
        msg.setMessage(message);
        bundle.setResult(msg);
      } else {
        for (tm_termination terminatedEmployee : listData) {
          String contractno = terminatedEmployee.getContractno();
          Date validfrom = terminatedEmployee.getValidFromDate();
          String employmenttype = terminatedEmployee.getEmployementtype();
          //String employeecategory = terminatedEmployee.getBLGEmployeeCategory();
          EmployeePosition position = terminatedEmployee.getPosition();
          String level = terminatedEmployee.getEmployeegrade();
          //String golPns = terminatedEmployee.getBlGGolPns();
          String echelon = terminatedEmployee.getEchelon();
          CostCenter costcenter = terminatedEmployee.getCostCenter();
          hris_jobtitle jobtitle = terminatedEmployee.getHrisJobtitle();
          String contracttype = terminatedEmployee.getContracttype();

          HRIS_C_Bp_Empinfo newContract = OBProvider.getInstance().get(HRIS_C_Bp_Empinfo.class);
         //newContract.setBlgEmployeecategory(employeecategory);
         // newContract.setBlgGolPns(golPns);
          newContract.setHrisContracttype(contracttype);
          HrisDataHandler.setCotractDetail(newContract, terminatedEmployee.getBusinessPartner(),
              true, employmenttype, contractno, validfrom, null, null, echelon, level, position,
              jobtitle, null, null, null, null, costcenter);

          terminatedEmployee.setHrisContractFk(newContract);
          processedData++;
          OBDal.getInstance().save(terminatedEmployee);
        }
        OBDal.getInstance().commitAndClose();
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            processedData + " @Hris_DataCreated@");
        final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage(message);
        bundle.setResult(msg);
      }
    } catch (Exception e) {
      String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
          " @Hris_DataFailed@");
      final OBError msg = new OBError();
      msg.setType("Error");
      msg.setTitle("Failed");
      msg.setMessage(message);
      bundle.setResult(msg);
    }

  }

}
