package org.wirabumi.localization.id.community.ad_reports;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.invoice.Invoice;

import java.util.HashMap;

import net.sf.jasperreports.engine.*;

public class RptFakturPajak extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String strcInvoiceID = vars.getGlobalVariable("inpcInvoiceId",""); 
      printPagePDF(response, vars, strcInvoiceID);
    } else
      pageError(response);
  }

  private void printPagePDF(HttpServletResponse response, VariablesSecureApp vars,
      String documentID) throws IOException, ServletException {
   if (log4j.isDebugEnabled()) log4j.debug("Output: RptEmployeeCardJR - pdf");
   
   Invoice invoice = OBDal.getInstance().get(Invoice.class, documentID);
   Currency currency = invoice.getCurrency();
   String strReportName = null;
   if (currency.getISOCode().equalsIgnoreCase("IDR")){
	   //faktur pajak dalam rupiah
	   strReportName = "@basedesign@/org/wirabumi/localization/id/ad_reports/RptFakturPajak-usd.jrxml";
   } else {
	   strReportName = "@basedesign@/org/wirabumi/localization/id/ad_reports/RptFakturPajak-usd.jrxml";
   }
   
   response.setHeader("Content-disposition", "inline; filename=FakturPajak.pdf");

   documentID=documentID.replace("(", "");
   documentID=documentID.replace(")", "");
   documentID=documentID.replace("\'", "");

   HashMap<String, Object> parameters = new HashMap<String, Object>();
   parameters.put("DOCUMENT_ID", documentID);   
   renderJR(vars, response, strReportName, "pdf", parameters, null, null );

  }

  public String getServletInfo() {
    return "Servlet that presents the RptCInvoices seeker";
  } // End of getServletInfo() method
}
