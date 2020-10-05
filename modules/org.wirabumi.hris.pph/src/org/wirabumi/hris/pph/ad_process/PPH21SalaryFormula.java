package org.wirabumi.hris.pph.ad_process;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;


public class PPH21SalaryFormula implements SalaryFormula {

	@Override
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment,
			pyr_sp_employee sp_employee, pyr_spe_earning spee,
			pyr_spe_deduction sped) {
		
		if (salaryPayment==null)
			throw new OBException("@pph_salaryPaymentNULL@");
		
		OBDal.getInstance().refresh(salaryPayment);
		
		HttpServletRequest request = RequestContext.get().getRequest();
	    VariablesSecureApp vars = new VariablesSecureApp(request);
		ProcessBundle bundle = new ProcessBundle(null, vars);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("PYR_Salarypayment_ID", salaryPayment.getId());
		bundle.setParams(params);
		CalculatePPh21 calculatepph21 = new CalculatePPh21();
		try {
			calculatepph21.doExecute(bundle);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
   		return 0;
	}

}
