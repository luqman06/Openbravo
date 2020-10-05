package org.wirabumi.hris.loan.ad_callout;

import javax.servlet.ServletException;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class OtherLoanCallOut extends SimpleCallout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		String billtotal = info.getStringParameter("inpbillTotal", null);
		info.addResult("inppaid", billtotal);
		
	}

}
