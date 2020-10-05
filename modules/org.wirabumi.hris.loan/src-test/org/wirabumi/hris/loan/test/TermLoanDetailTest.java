package org.wirabumi.hris.loan.test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jxl.write.DateTime;

import org.junit.Test;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.test.base.OBBaseTest;
import org.wirabumi.hris.employee.master.data.hris_deductionterm;
import org.wirabumi.hris.employee.master.data.hris_deductionterm_detail;
import org.wirabumi.hris.loan.ad_process.SetTermLoanDetail;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;

public class TermLoanDetailTest extends OBBaseTest {
	
	private static final String bloodGroup = "O";
	private static final String taxMaritalStatus = "TK";
	private static final String deduction = "1C0AE49B42BA409C9868956C41458722";
	private static final String paymentGroup = "GAJI";
	private static final String currID = "303";
	private static final String validPayrollMaster = "476EE490B3914C53817A6F77E0216B7B";
	
	@Test
	public void TermLoanDetailTestMethod() {
			
		// User = Openbravo
	    // Role = F&B International Group Admin
	    // Client = F&B International Group
	    // Org = F&B US, Inc.
		OBContext.setOBContext("100", "42D0EEB1C66F497A90DD526DC597E6F0",
		        "23C59575B9CF467C9620760EB255B389", "0");
		
		/*
		 * 
		 * Insert Term Loan Valid
		 */
		// Employee does exist get record from employee window
		BusinessPartner employeeTest1 = TestUtility.insertEmployee("Fiky", "000002", bloodGroup, 
				taxMaritalStatus, true, true, true);
		// set start date salary payment
		int[] startingDate1 = {2015, 12, 20};
		// set end date salary payment
		int[] endingDate1 = {2016, 1, 19};
		// set effective date salary payment
		int[] effectiveDate1 = {2016, 1, 31};
		// set accounting date salary payment
		int[] accountingDate1 = {2016, 1, 31};
		// Salary payment januari 2016 (salary payment does exist)
		pyr_salarypayment salaryPaymentTest1 = TestUtility.insertSalaryPayment("Test Gaji Januari 2016", startingDate1, 
				endingDate1, effectiveDate1, accountingDate1, /* valid payroll master */ 
				validPayrollMaster , currID, paymentGroup, true, true);
		// Insert Employee to sp Employee (sp employee does exist)
		pyr_sp_employee spEmployee1 = TestUtility.insertSpEmployee(employeeTest1, salaryPaymentTest1, BigDecimal.ZERO, true, true);
		// Insert Spe Deduction (spe deduction does exist)
		pyr_spe_deduction speDeduction = TestUtility.insertSpeDeduction(spEmployee1, deduction, 
				BigDecimal.ZERO, true, true);
		int[] validFrom1 = {2015, 12, 01};
		int[] validTo1 = {2016, 11, 01};
		// Insert term loan with valid date from and to
		TestUtility.insertTermLoan(employeeTest1, validFrom1, validTo1, "Term Loan A", 
				deduction, 100000, 1200000, false);
		// Set Object SetTermLoanDetail
		SetTermLoanDetail loanDet1 =new SetTermLoanDetail();
		double amount1 = loanDet1.hitungFormulaGaji(null, spEmployee1, null, null);
		OBDal.getInstance().flush();
		if(amount1 == 100000) {
			assertTrue(true);
			System.out.println("Valid Term Loan Amount : " + amount1);
		} else {
			assertFalse(false);
			System.out.println("Invalid Term Loan Amount : " + amount1);
		}
		
		/*
		 * 
		 * Insert Term Loan Invalid Date Range
		 */
		// Employee doesn't exist set record to employee window
		BusinessPartner employeeTest2 = TestUtility.insertEmployee("Juni", "000003", bloodGroup, taxMaritalStatus, 
				true, true, false);
		
		// Salary payment januari 2016 (salary payment does exist)
		pyr_salarypayment salaryPaymentTest2 = TestUtility.insertSalaryPayment("Test Gaji Januari 2016", null, 
				null, null, null, /* valid payroll master */ 
				validPayrollMaster , currID, paymentGroup, true, true);
		// Insert Employee to sp Employee (sp employee doesn't exist)
		pyr_sp_employee spEmployee2 = TestUtility.insertSpEmployee(employeeTest2, salaryPaymentTest2, BigDecimal.ZERO, true, false);
		// Insert Spe Deduction (spe deduction doesn't exist)
		pyr_spe_deduction speDeduction2 = TestUtility.insertSpeDeduction(spEmployee2, deduction, 
				BigDecimal.ZERO, true, false);
		int[] validFrom2 = {2015, 01, 01};
		int[] validTo2 = {2015, 06, 31};
		// Insert term loan with invalid date from and to
		TestUtility.insertTermLoan(employeeTest2, validFrom2, validTo2, "Term Loan B", 
				deduction, 500000, 3000000, true);
		SetTermLoanDetail loanDet2 = loanDet1;
		double amount2 = loanDet2.hitungFormulaGaji(null, spEmployee2, null, null);
		OBDal.getInstance().flush();
		if(amount2 == 0) {
			assertTrue(true);
			System.out.println("Invalid Term Loan Amount : " + amount2);
		} else {
			assertFalse(false);
			System.out.println("valid Term Loan Amount : " + amount2);
		}
		
		/*
		 * 
		 * Insert Term Loan Invalid payment complete
		 */
		// Employee doesn't exist set record to employee window
		BusinessPartner employeeTest3 = TestUtility.insertEmployee("Juni", "000003", bloodGroup, taxMaritalStatus, 
				true, true, true);
		
		// Salary payment januari 2016 (salary payment does exist)
		pyr_salarypayment salaryPaymentTest3 = TestUtility.insertSalaryPayment("Test Gaji Januari 2016", null, 
				null, null, null, /* valid payroll master */ 
				validPayrollMaster , currID, paymentGroup, true, true);
		// Insert Employee to sp Employee (sp employee doesn't exist)
		pyr_sp_employee spEmployee3 = TestUtility.insertSpEmployee(employeeTest3, salaryPaymentTest3, BigDecimal.ZERO, true, false);
		// Insert Spe Deduction (spe deduction doesn't exist)
		pyr_spe_deduction speDeduction3 = TestUtility.insertSpeDeduction(spEmployee3, deduction, 
				BigDecimal.ZERO, true, false);
		int[] validFrom3 = {2015, 12, 01};
		int[] validTo3 = {2016, 11, 01};
		// Insert term loan with invalid date from and to
		TestUtility.insertTermLoan(employeeTest3, validFrom3, validTo3, "Term Loan B", 
				deduction, 500000, 6000000, true);
		SetTermLoanDetail loanDet3 = loanDet1;
		double amount3 = loanDet3.hitungFormulaGaji(null, spEmployee3, null, null);
		OBDal.getInstance().flush();
		if(amount3 == 0) {
			assertTrue(true);
			System.out.println("Invalid Term Loan Amount : " + amount3);
		} else {
			assertFalse(false);
			System.out.println("valid Term Loan Amount : " + amount3);
		}
		
		
	
		// Delete All Term Loan and Term Loan Detail
		OBCriteria<hris_deductionterm_detail> criteria = OBDal.getInstance().createCriteria(hris_deductionterm_detail.class);
		for(hris_deductionterm_detail dedTermDetail : criteria.list()) {
			OBDal.getInstance().remove(dedTermDetail);
		}
		OBCriteria<hris_deductionterm> criteria1 = OBDal.getInstance().createCriteria(hris_deductionterm.class);
		for(hris_deductionterm dedTerm : criteria1.list()) {
			OBDal.getInstance().remove(dedTerm);
		}
		
	}
}
