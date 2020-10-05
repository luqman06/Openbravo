package org.infinite.expensebudget.event;

import java.util.List;

import org.infinite.expensebudget.utility.ExpenseBudgetUtility;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;

public class RequisitionBudgetMatchingActionHandler extends DocumentRoutingHandlerAction {

	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List<String> recordId) {
		
		//get open budget
		List<BudgetLine> openbudgetList = getOpenBudgetList();
		
		for (String requisitionID : recordId){
			
			Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
			for (RequisitionLine requisitionLine : requisition.getProcurementRequisitionLineList()) {
				//cek apakah sudah pernah di matching kan
				if (requisitionLine.getUnrealizedusageList().size()>0)
					continue; //sudah pernah di matchingkan dan match, maka abaikan
				
				//get matched budget
				BudgetLine budgetLine = ExpenseBudgetUtility.getMatchedBudget(requisitionLine, openbudgetList);
				if (budgetLine==null)
					throw new OBException("gagal mendapatkan budget untuk requisition "+requisition.getDocumentNo()+
							" pada line "+requisitionLine.getLineNo());
				
				ExpenseBudgetUtility.applyUnrealizedBudgetUsage(budgetLine, requisitionLine);
				
			}
			
		}
		
	}
	
	private List<BudgetLine> getOpenBudgetList() {
		OBCriteria<BudgetLine> blC = OBDal.getInstance().createCriteria(BudgetLine.class);
		return blC.list();
	}

	@Override
	public String getCoDocumentNo(String recordID, Tab tab) {
		// TODO sementara null dulu
		return null;
	}
	

}
