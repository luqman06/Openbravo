package org.wirabumi.hris.payroll.erpReports;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.wirabumi.gen.oez.Terbilang;
import org.wirabumi.hris.payroll.pyr_sp_employee;

import java.util.HashMap;

import net.sf.jasperreports.engine.*;

public class RptSlipGajiEmployeeReport extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;

  public void init(ServletConfig config) {
    super.init(config);
    boolHist = false;
  }
  

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);

    if (vars.commandIn("DEFAULT")) {
      String inppyrSalarypaymentId = vars.getSessionValue("Print_Salary_Slip.inppyrSpEmployeeId");
      printPagePDF(response, vars, inppyrSalarypaymentId);
    } else
      pageError(response);
  }

  
  private void printPagePDF(HttpServletResponse response, VariablesSecureApp vars,
      String inppyrSalarypaymentId) throws IOException, ServletException {
   if (log4j.isDebugEnabled()) log4j.debug("Output: RptSlipGajiEmployeeReportJR - pdf");
   String strReportName = "@basedesign@/org/wirabumi/hris/payroll/erpReports/Slip_Gaji_Employee.jrxml";
   response.setHeader("Content-disposition", "inline; filename=RptSlipGajiEmployeeReport.pdf");

   inppyrSalarypaymentId=inppyrSalarypaymentId.replace("(", "");
   inppyrSalarypaymentId=inppyrSalarypaymentId.replace(")", "");
   inppyrSalarypaymentId=inppyrSalarypaymentId.replace("\'", "");
   String terbilang = getTerbilang(inppyrSalarypaymentId);

   HashMap<String, Object> parameters = new HashMap<String, Object>();
   parameters.put("PYR_Sp_Employee_ID", inppyrSalarypaymentId);   
   parameters.put("terbilang", terbilang); 
   renderJR(vars, response, strReportName, "pdf", parameters, null, null );


   
  }
  
  private String getTerbilang(String inppyrSpEmployeeID) {
		
	  pyr_sp_employee spEmployee = OBDal.getInstance().get(pyr_sp_employee.class, inppyrSpEmployeeID);
	  Terbilang newTerbilang =  new Terbilang();
	  BigDecimal salaryAmount = spEmployee.getAmount();
	  if (salaryAmount==null)
		  salaryAmount = BigDecimal.ZERO;
	  String terbilang = newTerbilang.generateTerbilangWithCurrency(salaryAmount.doubleValue(), "IDR"); 
	  
	return terbilang;
}

  public String getServletInfo() {
    return "Servlet that presents the RptCInvoices seeker";
  } // End of getServletInfo() method
}
