package org.wirabumi.hris.payroll.ad_process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.employee.master.data.hris_deductionterm;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_deduction;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;

import com.google.common.collect.HashBasedTable;


public class SalaryFormulaInstallment implements SalaryFormula {

	@Override
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment, pyr_sp_employee sp_employee, pyr_spe_earning spee,
			pyr_spe_deduction sped) {
		
		pyr_salarypayment sp = null;
		if (sp_employee==null)
			sp=salaryPayment;
		else
			sp=sp_employee.getSalaryPayment();
		
		/*
		 * langkah 1
		 * dari SPE, didapatkan SPED
		 * dari SPED, didapatkan distinct pyr_deduction
		 * masukkan SPED deduction kedalam map
		 * 
		 * langkah 2
		 * dapatkan sum installment yang masih berlaku, group by bp dan deduction
		 * update SPED
		 * 
		 */
		
		//langkah 1
		HashBasedTable<BusinessPartner, pyr_deduction, pyr_spe_deduction> spedMap = HashBasedTable.create();
		for (pyr_sp_employee spe : sp.getPyrSpEmployeeList()){
			BusinessPartner employee = spe.getBusinessPartner();
			for (pyr_spe_deduction sped2 : spe.getPyrSpeDeductionList()){
				pyr_deduction deduction = sped2.getPYRDeduction();
				if (!spedMap.contains(employee, deduction)){
					spedMap.put(employee, deduction, sped2);
				}
			}
		}
		
		//langkah 2
		OBCriteria<hris_deductionterm> installmentCrit = OBDal.getInstance().createCriteria(hris_deductionterm.class);
		Date enddingdate = sp.getEndingDate();
		installmentCrit.add(Restrictions.ge(hris_deductionterm.PROPERTY_VALIDTO, enddingdate));
		installmentCrit.add(Restrictions.ge(hris_deductionterm.PROPERTY_ISPAIDOFF, false));
		installmentCrit.addOrderBy(hris_deductionterm.PROPERTY_BPARTNER, true);
		installmentCrit.addOrderBy(hris_deductionterm.PROPERTY_PYRDEDUCTION, true);
		HashBasedTable<BusinessPartner, pyr_deduction, BigDecimal> installmentMap = HashBasedTable.create();
		
		for (hris_deductionterm installment : installmentCrit.list()){
			BusinessPartner employee2 = installment.getBpartner();
			pyr_deduction deduction2 = installment.getPYRDeduction();
			Long jumlah = installment.getAmount();
			BigDecimal amount = BigDecimal.ZERO;
			if (jumlah!=null)
				amount = new BigDecimal(jumlah);
			
			if (installmentMap.contains(employee2, deduction2))
				amount = amount.add(installmentMap.get(employee2, deduction2));
			
			installmentMap.put(employee2, deduction2, amount);
			
		}
		
		for (BusinessPartner employee : installmentMap.rowKeySet()){
			for (pyr_deduction deduction : installmentMap.columnKeySet()){
				if (!installmentMap.contains(employee, deduction))
					continue;
				if (!spedMap.contains(employee, deduction))
					continue;
				BigDecimal amount = installmentMap.get(employee, deduction);
				pyr_spe_deduction sped2 = spedMap.get(employee, deduction);
				sped2.setAmount(amount);
				OBDal.getInstance().save(sped2);
			}
		}
		
		//commit transaction
		ConnectionProvider conn = new DalConnectionProvider();
		try {
			Connection connection = conn.getConnection();
			connection.commit();
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
		return 0;
	}

}
