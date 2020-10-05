package org.wirabumi.hris.employee.master.report;

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

public class RptEmployeeCard extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strcBpartnerID = vars.getSessionValue("HRIS.inpcBpartnerId");
      printPagePDF(response, vars, strcBpartnerID);
    } else
      pageError(response);
  }

  private void printPagePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strcBpartnerID) throws IOException, ServletException {
   if (log4j.isDebugEnabled()) log4j.debug("Output: RptEmployeeCardJR - pdf");
   JasperPrint jasperPrint;    
   String strReportName = "@basedesign@/org/wirabumi/hris/employee/master/report/EmployeeCard.jrxml";
   response.setHeader("Content-disposition", "inline; filename=RptEmployeeCard.pdf");

   strcBpartnerID=strcBpartnerID.replace("(", "");
   strcBpartnerID=strcBpartnerID.replace(")", "");
   strcBpartnerID=strcBpartnerID.replace("\'", "");

   HashMap<String, Object> parameters = new HashMap<String, Object>();
   parameters.put("C_BPartner_ID", strcBpartnerID);   
   renderJR(vars, response, strReportName, "pdf", parameters, null, null );

  }

  public String getServletInfo() {
    return "Servlet that presents the RptCInvoices seeker";
  } // End of getServletInfo() method
}
