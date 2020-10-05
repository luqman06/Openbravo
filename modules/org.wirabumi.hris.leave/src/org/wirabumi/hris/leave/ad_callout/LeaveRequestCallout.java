package org.wirabumi.hris.leave.ad_callout;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.wirabumi.hris.leave.lv_c_bp_leave;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

public class LeaveRequestCallout extends SimpleCallout {

	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(CalloutInfo info) {
		String employeeId=info.getStringParameter("inpcBpartnerId", null);
		String leaveCatalogueId=info.getStringParameter("inpemployeeleave", null);
		String strDateFrom=info.getStringParameter("inpdatefrom", null);
		String strEndDate=info.getStringParameter("inpdateto", null);
		String dateFormat=info.vars.getJavaDateFormat();
		Date dateFrom=null;
		Date dateTo=null;
		try {
			dateFrom = new SimpleDateFormat(dateFormat).parse(strDateFrom);
			dateTo = new SimpleDateFormat(dateFormat).parse(strEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//retrieve plafond and used leave
		lv_c_bp_leave employeeLeave = OBDal.getInstance().get(lv_c_bp_leave.class, leaveCatalogueId);
		BigDecimal usedLeave = employeeLeave.getUsedLeave();
		if (usedLeave != null) {
			info.addResult("inpusedleave", usedLeave.toString());
		}
		BigDecimal plafond = employeeLeave.getDuration();
		if (plafond != null) {
			info.addResult("inpduration", plafond.toString());
		}
		
		//calculate days used in this leave
		int dayUsedInLeave=0;
		while (!dateFrom.after(dateTo)){
			Boolean isWorkDay=null;
			try {
				isWorkDay=AttendanceUtility.isWorkDayBasedOnShiftOnly(dateFrom, employeeId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (isWorkDay==true) {
				dayUsedInLeave++;
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFrom);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			dateFrom=calendar.getTime();
		}
		
		info.addResult("inpdaysinthisleave", dayUsedInLeave);
		
	}

}
