package org.wirabumi.hris.overtime.callout;

import javax.servlet.ServletException;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class ValidFromCallout extends SimpleCallout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// jika valid from diubah, maka valid to dan tanggal akuntansi akan mengikuti
		String strDateFrom = info.getStringParameter("inpdatefrom", null);
		
		info.addResult("inpdateacct", strDateFrom);
		info.addResult("inpdateto", strDateFrom);

	}

}
