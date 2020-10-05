package org.wirabumi.hris.employee.master.ad_process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.employee.master.data.KPIJobTitle;
import org.wirabumi.hris.employee.master.data.KPIVersionJobTitle;
import org.wirabumi.hris.employee.master.data.KPIMeasurement;

public class GenerateKpiMeasurement extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    try {
      String kpiVersionID = (String) bundle.getParams().get("Hris_Jt_Kpi_Version_ID");
      KPIVersionJobTitle kpiVersion = OBDal.getInstance().get(KPIVersionJobTitle.class,
          kpiVersionID);
      hris_jobtitle jobtitle = kpiVersion.getJobTitle();
      Period periodMeasurement = OBDal.getInstance().get(Period.class, bundle.getParams().get("cPeriodId"));

      List<BusinessPartner> businessPartner = jobtitle.getBusinessPartnerEMHrisJobtitleIDList();
      BusinessPartner measuredEmployee = null;
      if (businessPartner.size() > 0) {
        measuredEmployee = businessPartner.get(0);
      }

      List<KPIJobTitle> kpiJobtitle = kpiVersion.getHrisJtKpiList();
      for (KPIJobTitle kpi : kpiJobtitle) {
        KPIMeasurement measurement = OBProvider.getInstance().get(KPIMeasurement.class);
        measurement.setJobTitleKPI(kpi);
        measurement.setEmployee(measuredEmployee);
        measurement.setTarget(kpi.getTarget());
        measurement.setPeriod(periodMeasurement);
        measurement.setYear(periodMeasurement.getYear());

        OBDal.getInstance().save(measurement);
      }
      OBDal.getInstance().commitAndClose();
      OBError message = new OBError();
      message.setTitle("Success");
      message.setType("Success");
      message.setMessage("Measurement Created Succesfully");
      bundle.setResult(message);
    } catch (Exception e) {
      e.printStackTrace();
      OBDal.getInstance().rollbackAndClose();
      OBError message = new OBError();
      message.setTitle("Error");
      message.setType("Error");
      message.setMessage("Message : " + e);
      bundle.setResult(message);
    }
  }

}
