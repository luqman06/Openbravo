package org.wirabumi.hris.employee.master.Actionbutton;

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
import org.wirabumi.hris.employee.master.data.hris_r_line;
import org.wirabumi.hris.employee.master.data.hris_retirement;

public class CreateRetirementContract extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    try {
      final String batchId = (String) bundle.getParams().get("Hris_Retirement_ID");
      int processedData = 0;
      hris_retirement BatchObject = OBDal.getInstance().get(hris_retirement.class, batchId);

      String contractno = BatchObject.getContractno();
      Date validfrom = BatchObject.getDocdate();

      OBCriteria<hris_r_line> getValidData = OBDal.getInstance().createCriteria(hris_r_line.class);
      getValidData.add(Restrictions.isNull(hris_r_line.PROPERTY_CONTRACT));

      List<hris_r_line> listData = getValidData.list();

      if (listData.size() <= 0) {
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            listData.size() + " @HRIS_DataMatch@");
        final OBError msg = new OBError();
        msg.setType("Info");
        msg.setTitle("Processed Data");
        msg.setMessage(message);
        bundle.setResult(msg);
      } else {
        for (hris_r_line pansiunanPerum : listData) {
            String employmenttype = pansiunanPerum.getEmployementType();
            EmployeePosition position = pansiunanPerum.getPosition();
            String level = pansiunanPerum.getEmployeeGrade();
            String echelon = pansiunanPerum.getEchelon();
            CostCenter costcenter = pansiunanPerum.getCostCenter();
            hris_jobtitle jobtitle = pansiunanPerum.getJobtitle();
            String contracttype = pansiunanPerum.getContractType();

            HRIS_C_Bp_Empinfo newContract = OBProvider.getInstance().get(HRIS_C_Bp_Empinfo.class);
            newContract.setHrisContracttype(contracttype);
            HrisDataHandler.setCotractDetail(newContract, pansiunanPerum.getBusinessPartner(),
                true, employmenttype, contractno, validfrom, null, null, echelon, level, position,
                jobtitle, null, null, null, null, costcenter);

            pansiunanPerum.setContract(newContract);
            processedData++;
            OBDal.getInstance().save(pansiunanPerum);
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
