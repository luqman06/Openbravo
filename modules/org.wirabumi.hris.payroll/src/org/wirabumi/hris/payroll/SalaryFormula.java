package org.wirabumi.hris.payroll;

public interface SalaryFormula {
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment, pyr_sp_employee sp_employee, pyr_spe_earning spee, pyr_spe_deduction sped);
	
}
