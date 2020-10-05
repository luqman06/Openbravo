package org.wirabumi.hris.employee.master.ad_process;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.HrisContractUpdate;

public class SetApplicableContract extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR, -12);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);

    Date processedDate;
    int updated = 0;

    String paramsDate = (String) bundle.getParams().get("executiondate");
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");// (vars.getSessionValue("#AD_JavaDateFormat"));
    processedDate = (paramsDate == null) ? null : format.parse(paramsDate);
    processedDate = (processedDate == null) ? calendar.getTime() : processedDate;
    try {
      String WhereClause = "as bp inner join bp.hRISCBpEmpinfoList contract where contract.validFromDate=?";
      List<Object> params = new ArrayList<Object>();
      params.add(processedDate);
      OBQuery<BusinessPartner> Qemployee = OBDal.getInstance().createQuery(BusinessPartner.class,
          WhereClause, params);
      List<BusinessPartner> employes = Qemployee.list();
      for (BusinessPartner employee : employes) {
        HrisContractUpdate.setNotCurrentpos(employee, processedDate);
        HrisContractUpdate.setCurrentPosition(employee.getId(), processedDate);
        updated++;
      }
      OBDal.getInstance().commitAndClose();
      final OBError msg = new OBError();
      msg.setType("Success");
      msg.setTitle("Done");
      msg.setMessage("Background Process Berjalan " + updated + " Di update");
      bundle.setResult(msg);

    } catch (Exception e) {
      OBDal.getInstance().rollbackAndClose();
      e.printStackTrace();
      final OBError msg = new OBError();
      msg.setType("Error");
      msg.setTitle("Done");
      msg.setMessage("Background Gagal Berjalan");
      bundle.setResult(msg);
    }
  }

}
