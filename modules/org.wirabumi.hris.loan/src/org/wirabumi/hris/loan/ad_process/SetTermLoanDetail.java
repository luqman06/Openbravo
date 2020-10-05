package org.wirabumi.hris.loan.ad_process;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.wirabumi.hris.employee.master.data.hris_deductionterm;
import org.wirabumi.hris.employee.master.data.hris_deductionterm_detail;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;

public class SetTermLoanDetail implements SalaryFormula {

	private void setTermLoanDetail(pyr_sp_employee sp_employee,
			hris_deductionterm dedTermList, pyr_spe_deduction speDeduction) {
		hris_deductionterm_detail dedTermDetail = OBProvider.getInstance().get(hris_deductionterm_detail.class);
		dedTermDetail.setName(dedTermList.getName());
		dedTermDetail.setDate(sp_employee.getSalaryPayment().getEffectiveDate());
		dedTermDetail.setHrisDeductionterm(dedTermList);
		dedTermDetail.setPYRSpeDeduction(speDeduction);
		dedTermDetail.setAmount(dedTermList.getAmount());
		OBDal.getInstance().save(dedTermDetail);
	}

	@Override
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment,
			pyr_sp_employee sp_employee, pyr_spe_earning spee,
			pyr_spe_deduction sped) {

		if(salaryPayment == null  || salaryPayment.getId().isEmpty()) {
			OBCriteria<hris_deductionterm> criteria = OBDal.getInstance().createCriteria(hris_deductionterm.class);
			criteria.add(Restrictions.eq(hris_deductionterm.PROPERTY_BPARTNER, sp_employee.getBusinessPartner()));
			criteria.add(Restrictions.le(hris_deductionterm.PROPERTY_VALIDFROM, sp_employee.getSalaryPayment().getEndingDate()));
			criteria.add(Restrictions.ge(hris_deductionterm.PROPERTY_VALIDTO, sp_employee.getSalaryPayment().getStartingDate()));
			criteria.add(Restrictions.eq(hris_deductionterm.PROPERTY_ISPAIDOFF, false));
			
			for(hris_deductionterm dedTermList : criteria.list()) {
				OBCriteria<pyr_spe_deduction> speDCriteria = OBDal.getInstance().createCriteria(pyr_spe_deduction.class);
				speDCriteria.add(Restrictions.eq(pyr_spe_deduction.PROPERTY_EMPLOYEESALARYPAYMENT, sp_employee));
				speDCriteria.add(Restrictions.eq(pyr_spe_deduction.PROPERTY_PYRDEDUCTION, dedTermList.getPYRDeduction()));
				for(pyr_spe_deduction speDeduction : speDCriteria.list()) {
					BigDecimal amount = speDeduction.getAmount();
					amount = amount.add(new BigDecimal(dedTermList.getAmount()));
					speDeduction.setAmount(amount);
					OBDal.getInstance().save(speDeduction);
					setTermLoanDetail(sp_employee, dedTermList, speDeduction);
				}
			}
		} else {
			OBCriteria<pyr_sp_employee> criteria = OBDal.getInstance().createCriteria(pyr_sp_employee.class);
			criteria.add(Restrictions.eq(pyr_sp_employee.PROPERTY_SALARYPAYMENT, salaryPayment));
			
			for(pyr_sp_employee spEmployee : criteria.list()) {
				OBCriteria<hris_deductionterm> dedTermCriteria = OBDal.getInstance().createCriteria(hris_deductionterm.class);
				dedTermCriteria.add(Restrictions.eq(hris_deductionterm.PROPERTY_BPARTNER, spEmployee.getBusinessPartner()));
				dedTermCriteria.add(Restrictions.le(hris_deductionterm.PROPERTY_VALIDFROM, salaryPayment.getEndingDate()));
				dedTermCriteria.add(Restrictions.ge(hris_deductionterm.PROPERTY_VALIDTO, salaryPayment.getStartingDate()));
				dedTermCriteria.add(Restrictions.eq(hris_deductionterm.PROPERTY_ISPAIDOFF, false));
				
				for(hris_deductionterm dedTermList : dedTermCriteria.list()) {
					OBCriteria<pyr_spe_deduction> speDCriteria = OBDal.getInstance().createCriteria(pyr_spe_deduction.class);
					speDCriteria.add(Restrictions.eq(pyr_spe_deduction.PROPERTY_EMPLOYEESALARYPAYMENT, spEmployee));
					speDCriteria.add(Restrictions.eq(pyr_spe_deduction.PROPERTY_PYRDEDUCTION, dedTermList.getPYRDeduction()));
					for(pyr_spe_deduction speDeduction : speDCriteria.list()) {
						BigDecimal amount = speDeduction.getAmount();
						amount = amount.add(new BigDecimal(dedTermList.getAmount()));
						speDeduction.setAmount(amount);
						OBDal.getInstance().save(speDeduction);
						setTermLoanDetail(spEmployee, dedTermList, speDeduction);
					}
				}
			}
		}
		
		
		return 0;
		
	}

}
