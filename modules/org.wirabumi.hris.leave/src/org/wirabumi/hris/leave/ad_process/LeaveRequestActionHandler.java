package org.wirabumi.hris.leave.ad_process;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openbravo.advpaymentmngt.process.FIN_AddPaymentFromJournal;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.financialmgmt.gl.GLJournal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;
import org.wirabumi.hris.leave.lv_c_bp_leave;
import org.wirabumi.hris.leave.lv_leave;

public class LeaveRequestActionHandler extends DocumentRoutingHandlerAction {
	
	private final String completeStatus="CO";
	private final String voidStatus="VO";

	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List<String> recordId) {
		for (String leaveRequestID : recordId){
			lv_leave leave = OBDal.getInstance().get(lv_leave.class, leaveRequestID);
			BigDecimal daysInThisLeave=leave.getDaysUsedInThisLeave();
			
			OBContext.setAdminMode();
			
			if (doc_status_to.equalsIgnoreCase(completeStatus)){
				//doComplete
				String employeeLeaveId=leave.getEmployeeleave().getId();
				lv_c_bp_leave employeeLeave = OBDal.getInstance().get(lv_c_bp_leave.class, employeeLeaveId);
				BigDecimal currentUsedLeave = employeeLeave.getUsedLeave();
				currentUsedLeave=currentUsedLeave.add(daysInThisLeave);
				employeeLeave.setUsedLeave(currentUsedLeave);
				OBDal.getInstance().save(employeeLeave);
				
			} else if (doc_status_to.equalsIgnoreCase(voidStatus)){
				//doVoid
				String employeeLeaveId=leave.getEmployeeleave().getId();
				lv_c_bp_leave employeeLeave = OBDal.getInstance().get(lv_c_bp_leave.class, employeeLeaveId);
				BigDecimal currentUsedLeave = employeeLeave.getUsedLeave();
				currentUsedLeave=currentUsedLeave.subtract(daysInThisLeave);
				employeeLeave.setUsedLeave(currentUsedLeave);
				OBDal.getInstance().save(employeeLeave);
				
			}
			
			OBDal.getInstance().commitAndClose();
			
			OBContext.restorePreviousMode();
			
		}
	}

	@Override
	public String getCoDocumentNo(String recordID, Tab tab) {
		// irrelevant, do nothing
		return null;
	}

}
