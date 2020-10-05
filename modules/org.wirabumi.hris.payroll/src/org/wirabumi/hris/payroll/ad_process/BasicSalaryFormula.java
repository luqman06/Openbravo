package org.wirabumi.hris.payroll.ad_process;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.openbravo.base.exception.OBException;
import org.openbravo.exception.NoConnectionAvailableException;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;

public class BasicSalaryFormula implements SalaryFormula {

	@Override
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment,
			pyr_sp_employee sp_employee, pyr_spe_earning spee,
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
		
		CalculateSalaryFormula csf = new CalculateSalaryFormula();
		try {
			csf.doCalculateSalaryFormula(speL);
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
