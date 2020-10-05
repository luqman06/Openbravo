package org.wirabumi.meikarta.report;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.reporting.DocumentType;
import org.openbravo.erpCommon.utility.reporting.printing.PrintController;
import org.openbravo.model.common.order.Order;
import org.wirabumi.printservice.print.PrintService;

public class PurchaseOrderPrint extends PrintController {

	private static final long serialVersionUID = 1L;
	
	public void init (ServletConfig config) {
		super.init(config);
		boolHist = false;
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) 
			throws IOException,ServletException {
		VariablesSecureApp vars = new VariablesSecureApp(request);

	    DocumentType documentType = DocumentType.SALESORDER;
	    // The prefix PRINTORDERS is a fixed name based on the KEY of the
	    // AD_PROCESS
	    String sessionValuePrefix = "PRINTORDERS";
	    String strDocumentId = null;
	    
	    if (vars.commandIn("DEFAULT")) {
	    	strDocumentId = vars.getSessionValue("Meikart_Print_PO.inpcOrderId_R");
			if (strDocumentId.equals(""))
				strDocumentId = vars.getStringParameter("Meikart_Print_PO");
			if (strDocumentId.equals(""))
				strDocumentId = vars.getSessionValue("Meikart_Print_PO.inpcOrderId");
			if (log4j.isDebugEnabled()) 
				log4j.debug("strcOrderId: "+ strDocumentId);
			
			if (strDocumentId==null || strDocumentId.isEmpty())
				throw new OBException("can not find order id");
	    }

	    
	    strDocumentId = strDocumentId.replaceAll("\\(|\\)|'", "");
	    Order po = OBDal.getInstance().get(Order.class, strDocumentId);
	    if (po==null)
	    	throw new OBException("invalid order id: "+strDocumentId);
	    	
	    String clientID = po.getClient().getId();
	    if (po.isSalesTransaction() || !clientID.equals("27B4805BA95A499DA9A8FE97B2709079")) //sales order atau bukan meikarta
	    	post(request, response, vars, documentType, sessionValuePrefix, strDocumentId);
	    
	    //purchase order dan client meikarta, maka redirect print
		printPagePDFwithJRRender(response, vars, po);
		
	}
	
	private void printPagePDFwithJRRender(HttpServletResponse response, VariablesSecureApp vars, Order po) {
		PrintService pops = new PurchaseOrderPrintService(po.getId(), vars);
		
		String strReportName = pops.getReportOutputFileName();
		
		response.setHeader("Content-disposition", "inline; filename="+strReportName);
		response.setContentType("application/pdf");

		//create ob object
		try {
			renderJR(vars, response, strReportName, "pdf", pops.getReportParameter(), pops.getDataSourceByArray(), null);
		} catch (ServletException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
		
	}
	
	public String getServletInfo() {
		return "Servlet that print purchase order of Meikarta";
	} // End of getServletInfo() method

}
