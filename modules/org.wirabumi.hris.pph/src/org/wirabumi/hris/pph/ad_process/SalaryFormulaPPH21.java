package org.wirabumi.hris.pph.ad_process;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.scheduling.ProcessBundle;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;

public class SalaryFormulaPPH21 implements SalaryFormula {

	@Override
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment,
			pyr_sp_employee sp_employee, pyr_spe_earning spee,
			pyr_spe_deduction sped) {
		String spID=null, speID=null;
		if (salaryPayment!=null)
			spID = salaryPayment.getId();
		if (sp_employee!=null)
			speID = sp_employee.getId();
		
		HttpServletRequest request = RequestContext.get().getRequest();
		VariablesSecureApp vars = new VariablesSecureApp(request);
		ProcessBundle bundle = new ProcessBundle(null, vars);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("PYR_Sp_Employee_ID", speID);
		params.put("PYR_Salarypayment_ID", spID);
		bundle.setParams(params);
		
		CalculatePPh21 csf = new CalculatePPh21();
		try {
			csf.doExecute(bundle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
