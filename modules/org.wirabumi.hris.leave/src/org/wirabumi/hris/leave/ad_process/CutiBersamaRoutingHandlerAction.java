package org.wirabumi.hris.leave.ad_process;

import java.util.List;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.model.ad.ui.Tab;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;

public class CutiBersamaRoutingHandlerAction extends DocumentRoutingHandlerAction {
	
	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List<String> recordId) {
		//TODO implement cuti bersama business logic here
	}

	@Override
	public String getCoDocumentNo(String recordID, Tab tab) {
		// irrelevant, do nothing
		return null;
	}

}
