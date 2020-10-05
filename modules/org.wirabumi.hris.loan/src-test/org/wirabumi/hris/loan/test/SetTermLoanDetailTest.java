package org.wirabumi.hris.loan.test;

import org.openbravo.dal.service.OBDal;
import org.openbravo.test.base.OBBaseTest;
import org.wirabumi.hris.loan.ad_process.SetTermLoanDetail;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_salarypayment;

public class SetTermLoanDetailTest extends OBBaseTest{

	public void setTermLoan() throws Exception {
		
		pyr_salarypayment salaryPayment = OBDal.getInstance().get(pyr_salarypayment.class, 
				"551AAC10E50F4989AC43BAE2D75DE3E7");
		SetTermLoanDetail loanDetail = new SetTermLoanDetail();
		loanDetail.hitungFormulaGaji(salaryPayment, null, null, null);
		
	}
}
