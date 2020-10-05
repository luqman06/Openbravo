package org.infinite.expensebudget.event;

import java.util.List;

import org.infinite.expensebudget.utility.ExpenseBudgetUtility;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;

public class PurchaseOrderBudgetMatchingActionHandler extends DocumentRoutingHandlerAction {

	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List<String> recordId) {
		
		//get open budget
		List<BudgetLine> openbudgetList = getOpenBudgetList();
		
		for (String poID : recordId){
			
			Order po = OBDal.getInstance().get(Order.class, poID);
			for (OrderLine poLine : po.getOrderLineList()) {
				//cek apakah sudah pernah di matching kan
				if (poLine.getRealizedusageList().size()>0)
					continue; //sudah pernah di matchingkan dan match, maka abaikan
				
				//get matched budget
				BudgetLine budgetLine = ExpenseBudgetUtility.getMatchedBudget(poLine, openbudgetList);
				if (budgetLine==null)
					throw new OBException("gagal mendapatkan budget untuk purchase order "+po.getDocumentNo()+
							" pada line "+poLine.getLineNo());
				
				ExpenseBudgetUtility.applyRealizedBudgetUsage(budgetLine, poLine);
				
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
