package org.wirabumi.hris.employee.master.event;

import java.util.List;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.model.ad.ui.Tab;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;

public class RetirementActionHandler extends DocumentRoutingHandlerAction {

@Override
public void doRouting(String adWindowId, String adTabId, String doc_status_to,
		VariablesSecureApp vars, List<String> recordId) {
	// TODO Auto-generated method stub
	
}

@Override
public String getCoDocumentNo(String recordID, Tab tab) {
	// TODO Auto-generated method stub
	return null;
}

}
