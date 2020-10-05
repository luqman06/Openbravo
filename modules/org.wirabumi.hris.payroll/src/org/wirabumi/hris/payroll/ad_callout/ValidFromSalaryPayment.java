package org.wirabumi.hris.payroll.ad_callout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class ValidFromSalaryPayment extends SimpleCallout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// jika valid from diubah, maka valid to akan menjadi bulan+1 dan H-1, tanggal akuntansi dan tanggal efektif diubah ke akhir bulan depan
		
		VariablesSecureApp vars = info.vars;
		String strJavaDateFormat = vars.getJavaDateFormat();
		SimpleDateFormat df = new SimpleDateFormat(strJavaDateFormat);
		String strStartDate = info.getStringParameter("inpstartdate", null);
		Date startDate = null;
		try {
			startDate = df.parse(strStartDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		
		cal.add(Calendar.MONTH, 1); //+1 bulan untuk membentuk valid to
		cal.add(Calendar.DATE, -1); //-1 hari untuk membentuk valid from
		Date endDate = cal.getTime();
		String strEndDate = df.format(endDate);
		info.addResult("inpenddate", strEndDate);
		
		int intendofmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, intendofmonth);
		Date endofmonth = cal.getTime();
		String strEndofmonth = df.format(endofmonth);
		info.addResult("inpdateacct", strEndofmonth);
		info.addResult("inpvalutadate", strEndofmonth);
		
	}

}
