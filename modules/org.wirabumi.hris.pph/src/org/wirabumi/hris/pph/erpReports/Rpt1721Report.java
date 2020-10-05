package org.wirabumi.hris.pph.erpReports;

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

public class Rpt1721Report extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strpph1721ID = vars.getSessionValue("pph21_1721.inppph1721Id");
      printPagePDF(response, vars, strpph1721ID);
    } else
      pageError(response);
  }

  private void printPagePDF(HttpServletResponse response, VariablesSecureApp vars,
      String strpph1721ID) throws IOException, ServletException {
   if (log4j.isDebugEnabled()) log4j.debug("Output: 1721Report - pdf");
   JasperPrint jasperPrint;    
   String strReportName = "@basedesign@/org/wirabumi/hris/pph/erpReports/form_1721A1.jrxml";
   response.setHeader("Content-disposition", "inline; filename=1721Report.pdf");

   strpph1721ID=strpph1721ID.replace("(", "");
   strpph1721ID=strpph1721ID.replace(")", "");
   strpph1721ID=strpph1721ID.replace("\'", "");

   HashMap<String, Object> parameters = new HashMap<String, Object>();
   parameters.put("pph_1721_id", strpph1721ID);   
   renderJR(vars, response, strReportName, "pdf", parameters, null, null );

  }

  public String getServletInfo() {
    return "Servlet that presents the RptCInvoices seeker";
  } // End of getServletInfo() method
}
