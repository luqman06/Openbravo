package org.wirabumi.hris.loan.test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.test.base.OBBaseTest;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.hris_deductionterm;
import org.wirabumi.hris.payroll.pyr_deduction;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;

public class TestUtility extends OBBaseTest {
	  private static final Logger log = Logger.getLogger(TestUtility.class);

	public static BusinessPartner insertEmployee(String name, String employeeID, 
			String bloodGroup, String taxMaritalStatus, boolean isWindowEmployee, boolean isValid, boolean checkIfExist) {
		
		if (checkIfExist) {
		      OBCriteria<BusinessPartner> criteria = OBDal.getInstance().createCriteria(
		    		  BusinessPartner.class);
		      criteria.add(Restrictions.eq(BusinessPartner.PROPERTY_SEARCHKEY, employeeID));
		      criteria.add(Restrictions.eq(BusinessPartner.PROPERTY_EMPLOYEE, checkIfExist));
		      if (criteria.list() != null && criteria.list().size() > 0) {
		        return criteria.list().get(0);
		      }
		    }
		
		BusinessPartner bPartner = OBProvider.getInstance().get(BusinessPartner.class);
		bPartner.setName(name);
		bPartner.setSearchKey(employeeID);
		bPartner.setEmployee(isWindowEmployee);
		bPartner.setHrisBloodgroup(bloodGroup);
		bPartner.setPphTaxmaritalstatus(taxMaritalStatus);
		
		boolean exception = false;
	    try {
	      OBDal.getInstance().save(bPartner);
	      OBDal.getInstance().flush();
	      OBDal.getInstance().commitAndClose();
	    } catch (Exception e) {
	      log.error(e);
	      exception = true;
	      OBDal.getInstance().rollbackAndClose();
	    }

	    if (isValid)
	      assertFalse("Not inserted a valid Employee:" + name, exception);
	    else
	      assertTrue("Inserted a non-valid Employee:" + name, exception);

	    if (exception)
	      return null;
	    else		
	      return bPartner;
		
	}
	
	public static void insertTermLoan(BusinessPartner bPartner, int[] validFrom, int[] validTo, String name, 
			String deduction, long amount, long totalAmount, boolean paymentComplete) {
		Date validFromDate = getDate(validFrom);
		Date validToDate = getDate(validTo);
		
		if(paymentComplete) 
			assertFalse("This Term Loan Already Expired :" + name, false);

		
		pyr_deduction ded = OBDal.getInstance().get(pyr_deduction.class, deduction);
		
		hris_deductionterm dedTerm = OBProvider.getInstance().get(hris_deductionterm.class);
		dedTerm.setBpartner(bPartner);
		dedTerm.setValidfrom(validFromDate);
		dedTerm.setValidto(validToDate);
		dedTerm.setName(name);
		dedTerm.setPYRDeduction(ded);
		dedTerm.setAmount(amount);
		dedTerm.setTotalAmount(totalAmount);
		dedTerm.setPaidoff(paymentComplete);
		OBDal.getInstance().save(dedTerm);
		OBDal.getInstance().flush();
	    OBDal.getInstance().commitAndClose();
	}
	
	// Get date time
	private static Date getDate(int[] dateFormat) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.set(dateFormat[0], dateFormat[1], dateFormat[2]);
		Date date = cal.getTime(); 
		
		return date;
	}

	public static pyr_salarypayment insertSalaryPayment(String name, int[] startingDate, int[] endingDate, 
			int[] effectiveDate, int[] accountingDate, String payrollMaster, String currencyID, 
			String paymentGroup, boolean isValid, boolean checkIfExist) {
		if (checkIfExist) {
		      final OBCriteria<pyr_salarypayment> criteria = OBDal.getInstance().createCriteria(
		    		  pyr_salarypayment.class);
		      criteria.add(Restrictions.eq(pyr_salarypayment.PROPERTY_COMMERCIALNAME, name));
		      if (criteria.list() != null && criteria.list().size() > 0) {
		        return criteria.list().get(0);
		      }
		    }
		
		Date startDate = getDate(startingDate);
		Date endDate = getDate(endingDate);
		Date effDate = getDate(effectiveDate);
		Date accDate = getDate(accountingDate);
		
		BusinessPartner pyrMaster = OBDal.getInstance().get(BusinessPartner.class, payrollMaster);
		Currency currency = OBDal.getInstance().get(Currency.class, currencyID);
		
		OBCriteria<HRIS_C_Bp_Empinfo> criteria = OBDal.getInstance().createCriteria(HRIS_C_Bp_Empinfo.class);
		criteria.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_BUSINESSPARTNER, pyrMaster));
		criteria.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_ISCURRENTPOS, true));
		HRIS_C_Bp_Empinfo empInfo = criteria.list().get(0);
		
		if(empInfo.isPyrIspayrollmaster() == false) {
			assertFalse("This Employee is Not Payroll Master :" + pyrMaster.getName(), false);
			return null;
		} 
		
		pyr_salarypayment salaryPayment = OBProvider.getInstance().get(pyr_salarypayment.class);
		salaryPayment.setCommercialName(name);
		salaryPayment.setStartingDate(startDate);
		salaryPayment.setEndingDate(endDate);
		salaryPayment.setEffectiveDate(effDate);
		salaryPayment.setAccountingDate(accDate);
		salaryPayment.setPayrollmaster(pyrMaster);
		salaryPayment.setCurrency(currency);
		salaryPayment.setPaymentGroup(paymentGroup);
		
		boolean exception = false;
	    try {
	      OBDal.getInstance().save(salaryPayment);
	      OBDal.getInstance().flush();
	      OBDal.getInstance().commitAndClose();
	    } catch (Exception e) {
	      log.error(e);
	      exception = true;
	      OBDal.getInstance().rollbackAndClose();
	    }

	    if (isValid)
	      assertFalse("Not inserted a valid Salary Payment:" + name, exception);
	    else
	      assertTrue("Inserted a non-valid Salary Payment:" + name, exception);

	    if (exception)
	      return null;
	    else		
	      return salaryPayment;
		
	}
	
	public static pyr_sp_employee insertSpEmployee(BusinessPartner employee, pyr_salarypayment salaryPayment,
			BigDecimal amount, boolean isValid, boolean checkIfExist) {
		if (checkIfExist) {
		      final OBCriteria<pyr_sp_employee> criteria = OBDal.getInstance().createCriteria(
		    		  pyr_sp_employee.class);
		      criteria.add(Restrictions.eq(pyr_sp_employee.PROPERTY_BUSINESSPARTNER, employee));
		      if (criteria.list() != null && criteria.list().size() > 0) {
		        return criteria.list().get(0);
		      }
		    }
		
		pyr_sp_employee spEmployee = OBProvider.getInstance().get(pyr_sp_employee.class);
		spEmployee.setBusinessPartner(employee);
		spEmployee.setSalaryPayment(salaryPayment);
		spEmployee.setAmount(amount);
		
		boolean exception = false;
	    try {
	      OBDal.getInstance().save(spEmployee);
	      OBDal.getInstance().flush();
	      OBDal.getInstance().commitAndClose();
	    } catch (Exception e) {
	      log.error(e);
	      exception = true;
	      OBDal.getInstance().rollbackAndClose();
	    }

	    if (isValid)
	      assertFalse("Not inserted a valid Employee Salary Payment:" + employee.getName(), exception);
	    else
	      assertTrue("Inserted a non-valid Employee Salary Payment:" + employee.getName(), exception);

	    if (exception)
	      return null;
	    else		
	      return spEmployee;
		
	}
	
	public static pyr_spe_deduction insertSpeDeduction(pyr_sp_employee spEmployee, String deductionID, BigDecimal amount,
			boolean isValid, boolean checkIfExist) {
		if (checkIfExist) {
		      final OBCriteria<pyr_spe_deduction> criteria = OBDal.getInstance().createCriteria(
		    		  pyr_spe_deduction.class);
		      criteria.add(Restrictions.eq(pyr_spe_deduction.PROPERTY_EMPLOYEESALARYPAYMENT, spEmployee));
		      if (criteria.list() != null && criteria.list().size() > 0) {
		        return criteria.list().get(0);
		      }
		    }
		pyr_deduction deduction = OBDal.getInstance().get(pyr_deduction.class, deductionID);
		
		pyr_spe_deduction speDeduction = OBProvider.getInstance().get(pyr_spe_deduction.class);
		speDeduction.setEmployeeSalaryPayment(spEmployee);
		speDeduction.setAmount(amount);
		speDeduction.setPYRDeduction(deduction);
		
		boolean exception = false;
	    try {
	      OBDal.getInstance().save(speDeduction);
	      OBDal.getInstance().flush();
	      OBDal.getInstance().commitAndClose();
	    } catch (Exception e) {
	      log.error(e);
	      exception = true;
	      OBDal.getInstance().rollbackAndClose();
	    }

	    if (isValid)
	      assertFalse("Not inserted a valid SPE Deduction:" , exception);
	    else
	      assertTrue("Inserted a non-valid SPE Deduction:" , exception);

	    if (exception)
	      return null;
	    else		
	      return speDeduction;
		
	}
}



