package org.wirabumi.hris.payroll.ad_process;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_javaclass_formula;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;

public class CalculateFormula extends DalBaseProcess {
	private final HashMap<Long, SalaryFormula> salaryFormulaMap = new HashMap<Long, SalaryFormula>();
	private SortedSet<Long> sortedSalaryFormulaSeqNo=null;

	protected void doExecute(ProcessBundle bundle) throws Exception {
		long start = Calendar.getInstance().getTimeInMillis();
		//load salary formula global library
		loadSalaryFormula();
		
		//load salary payment or employee salary payment
		String salaryPaymentID = (String) bundle.getParams().get("PYR_Salarypayment_ID");
		if (salaryPaymentID==null || salaryPaymentID.isEmpty()){
			String speID=(String) bundle.getParams().get("PYR_Sp_Employee_ID");
			if (speID==null || speID.isEmpty())
				throw new OBException("pyr_spIDorSpeIDisNull");
			pyr_sp_employee spe = OBDal.getInstance().get(pyr_sp_employee.class, speID);
			if (spe==null)
				throw new OBException("pyr_speIsNull");
			calculateSalary(null, spe, bundle);
		} else {
			//button clicked on salary payment header, then process batch NOT per employee
			pyr_salarypayment sp = OBDal.getInstance().get(pyr_salarypayment.class, salaryPaymentID);
			if (sp==null)
				throw new OBException("pyr_invalidsalarypaymentID");
			calculateSalary(sp, null, bundle);
			
		}
		
		long end = Calendar.getInstance().getTimeInMillis();
		long millis = end - start;
		long detik = (millis / 1000) % 60;
		long menit = (millis / (1000 * 60)) % 60;
		long jam = (millis / (1000 * 60 * 60)) % 24;
		String durasi = String.format("%02d:%02d:%02d:%d", jam, menit, detik, millis);
		final OBError msg = new OBError();
		msg.setType("Success");
	    msg.setTitle("Success");
	    msg.setMessage("Salary formula berhasil dijalankan dalam waktu "+durasi);
		bundle.setResult(msg);
			
	}

	private void loadSalaryFormula() {
		OBCriteria<pyr_javaclass_formula> formulaC = OBDal.getInstance().createCriteria(pyr_javaclass_formula.class);
		List<pyr_javaclass_formula> formulaL = formulaC.list();
		if (formulaL.size()>0){
			//global formula exists
			for(pyr_javaclass_formula formula : formulaC.list()) {
				Class<?> formulaGaji;
				SalaryFormula salaryFormula = null;
				try {
					formulaGaji = Class.forName(formula.getJavaClassName());
					salaryFormula = (SalaryFormula) formulaGaji.newInstance();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e){
					e.printStackTrace();
				} catch (IllegalAccessException e){
					e.printStackTrace();
				}
				
				//formula class validated, execute formula
				salaryFormulaMap.put(formula.getSequenceNumber(), salaryFormula);
				
			}
			
		} 
		
		//sort salary formula based on key
		sortedSalaryFormulaSeqNo = new TreeSet<Long>(salaryFormulaMap.keySet());
				
	}
	
	private void calculateSalary(pyr_salarypayment sp, pyr_sp_employee spe, ProcessBundle bundle){
		if (salaryFormulaMap.size()>0){
			//global salary formula exist, call formula class
			for (Long seqno : sortedSalaryFormulaSeqNo){
				SalaryFormula salaryformula = salaryFormulaMap.get(seqno);
				salaryformula.hitungFormulaGaji(sp, spe, null, null);
				try {
					OBDal.getInstance().getConnection().commit();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new OBException(e.getMessage());
				}
				
			}
			
		} else {
			//global salary formula does not exist, call CalculateSalaryFormula
			CalculateSalaryFormula calculateSalaryFormula = new CalculateSalaryFormula();
			try {
				calculateSalaryFormula.doExecute(bundle);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
	}

}
