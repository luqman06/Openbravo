package org.wirabumi.projectbid.report;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.reporting.printing.PrintController;
import org.openbravo.model.project.Project;
import org.wirabumi.meikarta.report.ProjectBidPrintService;

public class ProjectBidPrint extends PrintController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init (ServletConfig config) {
		super.init(config);
		boolHist = false;
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response) 
			throws IOException,ServletException {
		VariablesSecureApp vars = new VariablesSecureApp(request);

	    String strDocumentId = null;
	    
	    if (vars.commandIn("DEFAULT")) {
	    	strDocumentId = vars.getSessionValue("PROJECTBID_PRINT.INPCPROJECTID");
			
			if (strDocumentId==null || strDocumentId.isEmpty())
				throw new OBException("can not find document id");
	    }
	    
	    strDocumentId = strDocumentId.replaceAll("\\(|\\)|'", "");
	    Project pr = OBDal.getInstance().get(Project.class, strDocumentId);
	    if (pr==null)
	    	throw new OBException("invalid PR id: "+strDocumentId);
	    
	    //purchase order dan client meikarta, maka redirect print
		printPagePDFwithJRRender(response, vars, pr);
		
	}

	private void printPagePDFwithJRRender(HttpServletResponse response, VariablesSecureApp vars, Project pr) {
		
		if (log4j.isDebugEnabled()) log4j.debug("Output: Project bid - pdf");
		ProjectBidPrintService pbps = new ProjectBidPrintService(pr.getId(), vars); //TODO coupling dengan modul meikarta
		String strReportTemplatePath = pbps.getReportTemplatePath();
		String strReportFileName = pbps.getReportOutputFileName();
		
		response.setHeader("Content-disposition", "inline; filename="+strReportFileName);
		response.setContentType("application/pdf");


		try {
			renderJR(vars, response, strReportTemplatePath, "pdf", pbps.getReportParameter(), pbps.getDataSourceByArray(), null );
		} catch (ServletException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
		
	}
	
	
	public String getServletInfo() {
		return "Servlet that print purchase request (project bid) of Meikarta";
	} // End of getServletInfo() method
	
	


}
