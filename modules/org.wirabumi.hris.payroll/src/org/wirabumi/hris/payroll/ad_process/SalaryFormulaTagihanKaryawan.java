package org.wirabumi.hris.payroll.ad_process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.wirabumi.hris.loan.ln_bill_register;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_deduction;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;


public class SalaryFormulaTagihanKaryawan implements SalaryFormula {

	@Override
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment, pyr_sp_employee sp_employee, pyr_spe_earning spee,
			pyr_spe_deduction sped) {
		
		pyr_salarypayment sp = null;
		if (sp_employee==null)
			sp=salaryPayment;
		else
			sp=sp_employee.getSalaryPayment();
		
		final String DEDUCTIONKEY = "TAGIHANKARYAWAN";
		OBCriteria<pyr_deduction> deductioncriteria = OBDal.getInstance().createCriteria(pyr_deduction.class);
		deductioncriteria.add(Restrictions.eq(pyr_deduction.PROPERTY_SEARCHKEY, DEDUCTIONKEY));
		List<pyr_deduction> deductionlist = deductioncriteria.list();
		if (deductionlist==null || deductionlist.size()==0)
			throw new OBException("failed to get deduction master data with key "+DEDUCTIONKEY);
		
		final pyr_deduction deduction = deductionlist.get(0);
		
		//build map untuk SPED (bp, deduction) --> SPED
		List<pyr_sp_employee> speList;
		if (sp_employee!=null){
			speList = new ArrayList<pyr_sp_employee>();
			speList.add(sp_employee);
		} else
			speList = salaryPayment.getPyrSpEmployeeList();
		
		HashMap<BusinessPartner, pyr_spe_deduction> spedMap = new HashMap<BusinessPartner, pyr_spe_deduction>();
		for (pyr_sp_employee spe : speList){
			List<pyr_spe_deduction> spedList = spe.getPyrSpeDeductionList();
			for(pyr_spe_deduction sped2 : spedList){
				pyr_deduction deduction2 = sped2.getPYRDeduction();
				if (deduction2.getId().equalsIgnoreCase(deduction.getId()))
					spedMap.put(spe.getBusinessPartner(), sped2);
			}
		}
		
		//build map untuk tagihan koperasi (bp) --> total amount
		OBCriteria<ln_bill_register> tagihancriteria = OBDal.getInstance().createCriteria(ln_bill_register.class);
		tagihancriteria.add(Restrictions.le(ln_bill_register.PROPERTY_ENDINGDATE, sp.getEndingDate())); //tanggal akhir maksimal sesuai tanggal gaji
		tagihancriteria.add(Restrictions.ne(ln_bill_register.PROPERTY_DOCUMENTSTATUS, "CL")); //documentstatus tidak closed
		List<ln_bill_register> tagihanlist = tagihancriteria.list();
		HashMap<BusinessPartner, BigDecimal> tagihanMap = new HashMap<BusinessPartner, BigDecimal>();
		for (ln_bill_register tagihan : tagihanlist){
			BusinessPartner employee = tagihan.getEmployee();
			BigDecimal amount = BigDecimal.ZERO;
			if (tagihanMap.containsKey(employee))
				amount = tagihanMap.get(employee);
			BigDecimal currentAmount = tagihan.getOutstandingAmount();
			if (currentAmount==null)
				currentAmount = BigDecimal.ZERO;
			amount = amount.add(currentAmount);
			//TODO coupling dengan osaka
//			for (InvoiceTagihanKaryawan itk : tagihan.getOskInvoicetagihankaryawanTagihankaryawanIdList()){
//				amount = amount.add(itk.getInvoice().getGrandTotalAmount());
//			}
			
			tagihanMap.put(employee, amount);
			
			tagihan.setDocumentStatus("CL");
			OBDal.getInstance().save(tagihan);
		}
		
		//update SPED
		for (BusinessPartner employee : spedMap.keySet()){
			if (!tagihanMap.containsKey(employee))
				continue;
			BigDecimal amount = tagihanMap.get(employee);
			pyr_spe_deduction sped2 = spedMap.get(employee);
			sped2.setAmount(amount);
			OBDal.getInstance().save(sped2);
		}
		
		return 0;
	}

}
