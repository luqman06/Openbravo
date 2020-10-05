package org.wirabumi.hris.employee.master.ad_callout;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.calendar.Year;
import org.wirabumi.hris.employee.master.data.KPIJobTitle;
import org.wirabumi.hris.employee.master.data.KPIVersionJobTitle;

public class KPItarget extends SimpleCallout {

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
	  Calendar calendar = Calendar.getInstance();
	  calendar.set(Calendar.HOUR, 0);
	  calendar.set(Calendar.MINUTE, 0);
	  calendar.set(Calendar.SECOND, 0);
	  calendar.set(Calendar.MILLISECOND, 0);
	
	  Date timeNow = calendar.getTime();
	  SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	  String now = formatDate.format(timeNow);
	  
	  
	  try {
	  Date nowTime = formatDate.parse(now);
      String KpiJtID = info.getStringParameter("inphrisJtKpiId", null);
      KPIJobTitle jobtitleKpi = OBDal.getInstance().get(KPIJobTitle.class, KpiJtID);
      BigDecimal target = jobtitleKpi.getTarget();
      KPIVersionJobTitle kpiVersionId = jobtitleKpi.getJobTitleKPIVersion();
      BigDecimal weightAchievment = new BigDecimal(0);
      Period periodID = findPeriod(kpiVersionId,nowTime);
      Year yearID = periodID.getYear();

      info.addResult("inptarget", target);
      info.addResult("inpcYearId", yearID.getId());
      info.addResult("inpcPeriodId", periodID.getId());
      info.addResult("inpweightedachievment", weightAchievment);
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  
	private Period findPeriod(KPIVersionJobTitle kpiVersionId,Date nowTime){
		Period periodID = null;
		String whereClause = " as period where period.startingDate<=? and period.endingDate>=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(nowTime);
		paramList.add(nowTime);
		OBQuery<Period> period = OBDal.getInstance()
				.createQuery(Period.class, whereClause, paramList);
		List<Period> periodList = period.list();
		if (periodList.size() > 0) {
			periodID = periodList.get(0);
		} else {
			throw new OBException("Period Doesn Not Exist ");
		}
		return periodID;
	}
}

