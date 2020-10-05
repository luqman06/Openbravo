package org.wirabumi.hris.loan.erpReports;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.utility.Utility;

import java.util.HashMap;
import net.sf.jasperreports.engine.*;

public class RptLoanReport extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strclnLoanID = vars.getSessionValue("ln_loan.inplnLoanId");
      printPagePDF(response, vars, strclnLoanID);
    } else
      pageError(response);
  }

  private void printPagePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strclnLoanID) throws IOException, ServletException {
   if (log4j.isDebugEnabled()) log4j.debug("Output: LoanReport - pdf");
   JasperPrint jasperPrint;    
   String strReportName = "@basedesign@/org/wirabumi/hris/loan/erpReports/Loan_Report.jrxml";
   response.setHeader("Content-disposition", "inline; filename=LoanReport.pdf");

   strclnLoanID=strclnLoanID.replace("(", "");
   strclnLoanID=strclnLoanID.replace(")", "");
   strclnLoanID=strclnLoanID.replace("\'", "");

   HashMap<String, Object> parameters = new HashMap<String, Object>();
   parameters.put("ln_loan_id", strclnLoanID);   
   renderJR(vars, response, strReportName, "pdf", parameters, null, null );

  }

  public String getServletInfo() {
    return "Servlet that presents the RptCInvoices seeker";
  } // End of getServletInfo() method
}
