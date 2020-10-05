package org.wirabumi.hris.leave.erpReports;

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

public class RptLeaveReport extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String inpLvLeaveId = vars.getSessionValue("lv_leave.inpLvLeaveId");
      printPagePDF(response, vars, inpLvLeaveId);
    } else
      pageError(response);
  }

  private void printPagePDF(HttpServletResponse response, VariablesSecureApp vars,
      String inpLvLeaveId) throws IOException, ServletException {
   if (log4j.isDebugEnabled()) log4j.debug("Output: RptLeaveReportJR - pdf");
   JasperPrint jasperPrint;    
   String strReportName = "@basedesign@/org/wirabumi/hris/leave/erpReports/leave_report.jrxml";
   response.setHeader("Content-disposition", "inline; filename=RptLeaveReport.pdf");

   inpLvLeaveId=inpLvLeaveId.replace("(", "");
   inpLvLeaveId=inpLvLeaveId.replace(")", "");
   inpLvLeaveId=inpLvLeaveId.replace("\'", "");

   HashMap<String, Object> parameters = new HashMap<String, Object>();
   parameters.put("lv_leave_ID", inpLvLeaveId);   
   renderJR(vars, response, strReportName, "pdf", parameters, null, null );

  }

  public String getServletInfo() {
    return "Servlet that presents the RptCInvoices seeker";
  } // End of getServletInfo() method
}
