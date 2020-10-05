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
import org.wirabumi.hris.employee.master.data.hris_pengundurandiri;

public class CreateResignContract extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    try {
      int processedData = 0;

      OBCriteria<hris_pengundurandiri> getValidData = OBDal.getInstance().createCriteria(
          hris_pengundurandiri.class);
      getValidData.add(Restrictions.isNull(hris_pengundurandiri.PROPERTY_HRISCONTRACTFK));
      getValidData.add(Restrictions.eq(hris_pengundurandiri.PROPERTY_DOCUMENTSTATUS, "AP"));
      getValidData.add(Restrictions.isNotNull(hris_pengundurandiri.PROPERTY_CONTRACTNO));

      List<hris_pengundurandiri> listData = getValidData.list();

      if (listData.size() <= 0) {
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            listData.size() + " @HRIS_DataMatch@");
        final OBError msg = new OBError();
        msg.setType("Info");
        msg.setTitle("Processed Data");
        msg.setMessage(message);
        bundle.setResult(msg);
      } else {
        for (hris_pengundurandiri resignReq : listData) {
          String contractno = resignReq.getContractno();
          Date validfrom = resignReq.getValidFromDate();
          String employmenttype = resignReq.getEmployementtype();
          EmployeePosition position = resignReq.getPosition();
          String level = resignReq.getGolPerum();
          String echelon = resignReq.getEchelon();
          CostCenter costcenter = resignReq.getCostCenter();
          hris_jobtitle jobtitle = resignReq.getJobTitle();
          String contracttype = resignReq.getContractType();

          HRIS_C_Bp_Empinfo newContract = OBProvider.getInstance().get(HRIS_C_Bp_Empinfo.class);
          newContract.setHrisContracttype(contracttype);
          HrisDataHandler.setCotractDetail(newContract, resignReq.getBusinessPartner(), true,
              employmenttype, contractno, validfrom, null, null, echelon, level, position,
              jobtitle, null, null, null, null, costcenter);

          resignReq.setHrisContractFk(newContract);
          processedData++;
          OBDal.getInstance().save(resignReq);
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
