package org.wirabumi.gen.oez.callout;

import javax.servlet.ServletException;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

public class ExisitingAttributeSetInstanceCallout extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		String lastchanged = info.getLastFieldChanged();
		if (!lastchanged.equals("inpemOezAttributesetinstanceId"))
			return;
		
		String exisitingattributeset = info.getStringParameter("inpemOezAttributesetinstanceId");
		info.addResult("inpmAttributesetinstanceId", exisitingattributeset);

	}

}
