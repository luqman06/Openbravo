package org.wirabumi.hris.payroll.ad_process;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_incidental_earning;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;

import com.google.common.collect.HashBasedTable;

public class IncidentalEarning implements SalaryFormula {

	@Override
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment, pyr_sp_employee sp_employee, pyr_spe_earning spee,
			pyr_spe_deduction sped) {
		
		List<pyr_sp_employee> speL=null;
		if (salaryPayment==null && sp_employee==null)
			throw new OBException("pyr_bothSPandSPEareNULL");
		
		if (salaryPayment!=null)
			speL=salaryPayment.getPyrSpEmployeeList();
		else {
			speL = new ArrayList<pyr_sp_employee>();
			speL.add(sp_employee);
		}
			
		
		if (speL==null || speL.size()==0)
			throw new OBException("pyr_noSPEtobeProcessed");
		
		return hitungFormulaGaji(speL);
		
	}

	private double hitungFormulaGaji(List<pyr_sp_employee> speL) {
		//load earning catalog
		pyr_salarypayment sp = speL.get(0).getSalaryPayment();
		Date startdate = sp.getStartingDate();
		Date enddate = sp.getEndingDate();
		HashBasedTable<String, String, pyr_spe_earning> speMap = loadSPEMap(sp); //bp_key, earning_key --> pyr_spe_earning object
		
		OBCriteria<pyr_incidental_earning> ieC = OBDal.getInstance().createCriteria(pyr_incidental_earning.class);
		ieC.add(Restrictions.ge(pyr_incidental_earning.PROPERTY_DATE, startdate));
		ieC.add(Restrictions.le(pyr_incidental_earning.PROPERTY_DATE, enddate));
		for (pyr_incidental_earning ie : ieC.list()) {
			String employeekey = ie.getEmployee().getSearchKey();
			String earningkey = ie.getEarning().getSearchKey();
			
			if (!speMap.contains(employeekey, earningkey))
				continue;
			
			pyr_spe_earning spee = speMap.get(employeekey, earningkey);
			spee.setAmount(ie.getAmount());
			OBDal.getInstance().save(spee);
		}
		
		try {
			OBDal.getInstance().getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
		
		return 0;
	}

	private HashBasedTable<String, String, pyr_spe_earning> loadSPEMap(pyr_salarypayment sp) {
		HashBasedTable<String, String, pyr_spe_earning> output = HashBasedTable.create();
		for (pyr_sp_employee spe : sp.getPyrSpEmployeeList()) {
			for (pyr_spe_earning spee : spe.getPyrSpeEarningList()) {
				String employeeKey = spe.getBusinessPartner().getSearchKey();
				String earningKey = spee.getEarning().getSearchKey();
				output.put(employeeKey, earningKey, spee);
			}
		}
		return output;
	}

	
}

