package org.wirabumi.hris.employee.master.Actionbutton;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
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
import org.wirabumi.hris.employee.master.data.hris_employee_transfer;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;

public class CreateTransferContract extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    try {
      int processedData = 0;
      OBContext.setAdminMode();

      OBCriteria<hris_employee_transfer> getValidData = OBDal.getInstance().createCriteria(
          hris_employee_transfer.class);
      getValidData.add(Restrictions.isNull(hris_employee_transfer.PROPERTY_HRISCONTRACTFK));
      getValidData.add(Restrictions.eq(hris_employee_transfer.PROPERTY_DOCUMENTSTATUS, "AP"));
      getValidData.add(Restrictions.isNotNull(hris_employee_transfer.PROPERTY_CONTRACTNO));

      List<hris_employee_transfer> listData = getValidData.list();

      if (listData.size() <= 0) {
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            listData.size() + " @HRIS_DataMatch@");
        final OBError msg = new OBError();
        msg.setType("Info");
        msg.setTitle("Processed Data");
        msg.setMessage(message);
        bundle.setResult(msg);
      } else {
        for (hris_employee_transfer transferEmployee : listData) {
          String contractno = transferEmployee.getContractno();
          Date validfrom = transferEmployee.getValidfrom();
          String employmenttype = transferEmployee.getEmploymenttype();
          EmployeePosition position = transferEmployee.getTopositioned();
          String level = transferEmployee.getFromlevel();
          String echelon = transferEmployee.getToechelon();
          CostCenter costcenter = transferEmployee.getTomaCostcenter();
          hris_jobtitle jobtitle = transferEmployee.getTohrisJobtitle();
          String contracttype = transferEmployee.getContracttype();

          HRIS_C_Bp_Empinfo newContract = OBProvider.getInstance().get(HRIS_C_Bp_Empinfo.class);
          newContract.setHrisContracttype(contracttype);
          HrisDataHandler.setCotractDetail(newContract, transferEmployee.getBusinessPartner(),
              true, employmenttype, contractno, validfrom, null, null, echelon, level, position,
              jobtitle, null, null, null, null, costcenter);

          transferEmployee.setHrisContractFk(newContract);
          processedData++;
          OBDal.getInstance().save(transferEmployee);
        }
        OBDal.getInstance().commitAndClose();
        OBContext.restorePreviousMode();
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
