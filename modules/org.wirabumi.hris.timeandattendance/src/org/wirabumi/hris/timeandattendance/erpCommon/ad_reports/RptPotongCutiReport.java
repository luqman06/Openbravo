package org.wirabumi.hris.timeandattendance.erpCommon.ad_reports;

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

public class RptPotongCutiReport extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strcotOvertimeID = vars.getSessionValue("ta_report_pot_cuti.inptaAkumulasiPotCutiId");
      printPagePDF(response, vars, strcotOvertimeID);
    } else
      pageError(response);
  }

  private void printPagePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strcotOvertimeID) throws IOException, ServletException {
   if (log4j.isDebugEnabled()) log4j.debug("Output: PotongCutiReport - pdf");
   JasperPrint jasperPrint;    
   String strReportName = "@basedesign@/org/wirabumi/hris/timeandattendance/erpCommon/ad_reports/ReportPotongCuti.jrxml";
   response.setHeader("Content-disposition", "inline; filename=PotongCutiReport.pdf");

   strcotOvertimeID=strcotOvertimeID.replace("(", "");
   strcotOvertimeID=strcotOvertimeID.replace(")", "");
   strcotOvertimeID=strcotOvertimeID.replace("\'", "");

   HashMap<String, Object> parameters = new HashMap<String, Object>();
   parameters.put("ta_akumulasi_pot_cuti_id", strcotOvertimeID);   
   renderJR(vars, response, strReportName, "pdf", parameters, null, null );

  }

  public String getServletInfo() {
    return "Servlet that presents the RptCInvoices seeker";
  } // End of getServletInfo() method
}
