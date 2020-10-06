//package org.wirabumi.hris.employee.master.Actionbutton;

package org.wirabumi.hris.payroll.ad_process;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.EmployeeSalaryCategory;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Department;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.employee.master.data.hris_site;
import org.wirabumi.hris.payroll.Pyr_Bp_Deduction;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_bp_earning;
import org.wirabumi.hris.payroll.pyr_deduction;
import org.wirabumi.hris.payroll.pyr_earning;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;
import org.wirabumi.hris.timeandattendance.utility.AttendancePerformanceBean;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

public class CalculateSalaryFormula extends DalBaseProcess implements SalaryFormula{
  Logger log4j = Logger.getLogger(this.getClass());
  protected ConnectionProvider connectionProvider = new DalConnectionProvider();
  protected VariablesSecureApp vars = RequestContext.get().getVariablesSecureApp();
  
  //konstanta nama internal variable
  private final String issalesrep="issalesrep";
  private final String istaxexempt="istaxexempt";
  private final String isdirectlabour="isdirectlabour";
  private final String sitecode="sitecode";
  private final String dateofbirth="dateofbirth";
  private final String religion="religion";
  private final String maritalstatus="maritalstatus";
  private final String joindate="joindate";
  private final String retirementdate="retirementdate";
  private final String departementcode="departementcode";
  private final String employmenttype="employmenttype";
  private final String employeegrade="employeegrade";
  private final String employeeposition="employeeposition";
  private final String jobtitlecode="jobtitlecode";
  private final String isstaff="isstaff";
  private final String isremoteemployee="isremoteemployee";
  private final String sex="sex";
  private final String costcentercode="costcentercode";
  private final String nationalitycode="nationalitycode";
  private final String echelon="echelon";
  
  private final String formula="FORMULA";
  private final String constant="CONST";
  private final String java="JAVA";
  
  private final String clientID=OBContext.getOBContext().getCurrentClient().getId();
  
  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
	  
      
      List<pyr_sp_employee> speL;
      
      String spID;
      String speID=(String) bundle.getParams().get("PYR_Sp_Employee_ID");
      if (speID!=null && !speID.isEmpty()){
    	  pyr_sp_employee spe = OBDal.getInstance().get(pyr_sp_employee.class, speID);
		  speL= new ArrayList<pyr_sp_employee>();
		  speL.add(spe);
      }
      else{
    	  spID = (String) bundle.getParams().get("PYR_Salarypayment_ID");
    	  pyr_salarypayment sp = OBDal.getInstance().get(pyr_salarypayment.class, spID);
    	  speL=sp.getPyrSpEmployeeList();
      }
      
      final OBError msg = doCalculateSalaryFormula(speL);
      
      bundle.setResult(msg);
      
  }
  
  public OBError doCalculateSalaryFormula(List<pyr_sp_employee> speL) throws NoConnectionAvailableException, SQLException, ScriptException {
      //loop per employee in salary payment (or individual salary formula calculation)
      for (pyr_sp_employee spe : speL){
    	  BusinessPartner bp = spe.getBusinessPartner();
    	  pyr_salarypayment sp = spe.getSalaryPayment();
    	  Date startdate = sp.getStartingDate();
    	  Date enddate = sp.getEndingDate();
    	  //get salary category
    	  EmployeeSalaryCategory esc=null;
    	  OBCriteria<EmployeeSalaryCategory> escC = OBDal.getInstance().createCriteria(EmployeeSalaryCategory.class);
    	  escC.add(Restrictions.eq(EmployeeSalaryCategory.PROPERTY_BUSINESSPARTNER, bp));
    	  escC.add(Restrictions.le(EmployeeSalaryCategory.PROPERTY_STARTINGDATE, enddate));
    	  escC.addOrderBy(EmployeeSalaryCategory.PROPERTY_STARTINGDATE, false);
    	  escC.setMaxResults(1);
    	  List<EmployeeSalaryCategory> escL = escC.list();
    	  if (escL.size()>0)
    		  esc=escL.get(0);
    	  
    	  /*
           * 1. evaluate internal variable --> masukkan he scriptenginee
           * 2. loop pendapatan --> evaluasi formula --> masukkan hasilnya ke scriptenginee
           * 3. loop potongan --> evaluasi formula --> masukkan hasilnya ke scriptenginee
           * 
           * catatan: jika scriptnya null, atau empty string, maka set amount = 0
           * */
          
          ScriptEngineManager mgr = new ScriptEngineManager();
          ScriptEngine se = mgr.getEngineByName("JavaScript");
          
          //1. internal variable
          doGetInternalVariable(se, bp, startdate, enddate);
          
          //2. loop pendapatan
          doEvaluateEarning(spe, se, esc);
          
          //3. loop potongan
          doEvaluateDeduction(spe, se, esc);
    	  
      }
      
      final OBError msg = new OBError();
      msg.setType("Success");
      msg.setTitle("Created Succes");
      msg.setMessage("salary calculated successfully");
      return msg;
	
}

//implementasi tipe process adhoc pada deduction
  private void doEvaluateDeduction(pyr_sp_employee spe, ScriptEngine se,
		EmployeeSalaryCategory esc) throws ScriptException {
	  String whereclause = "as sped where sped.employeeSalaryPayment.id = :speID order by sped.pYRDeduction.sequenceNumber asc";
      OBQuery<pyr_spe_deduction> spedQ = OBDal.getInstance().createQuery(pyr_spe_deduction.class, whereclause);
      spedQ.setNamedParameter("speID", spe.getId());
      
      for(pyr_spe_deduction sped : spedQ.list()){
    	  pyr_deduction deduction = sped.getPYRDeduction();
    	  String key = deduction.getSearchKey();
    	  String type = deduction.getType();
    	  String formula = deduction.getFormula();
    	  BigDecimal amount = deduction.getAmount();
    	  if (amount==null)
    		  amount=BigDecimal.ZERO;
    	  
    	  //lihat ada perkecualian atau tidak
    	  OBCriteria<Pyr_Bp_Deduction> bpdC = OBDal.getInstance().createCriteria(Pyr_Bp_Deduction.class);
		  bpdC.add(Restrictions.eq(Pyr_Bp_Deduction.PROPERTY_SALARYCATEGORY, esc));
		  bpdC.add(Restrictions.eq(Pyr_Bp_Deduction.PROPERTY_PYRDEDUCTION, deduction));
		  List<Pyr_Bp_Deduction> bpdL = bpdC.list();
		  if (bpdL.size()>0){
			//ada perkecualian
			  Pyr_Bp_Deduction bpd = bpdL.get(0);
			  String deductionType = bpd.getType();
			  if (deductionType.equalsIgnoreCase(this.formula)){
				  formula = bpd.getFormula();
				  if (formula!=null){
					  type=this.formula;
					//evaluasi formula
					  se.eval(formula);
					  Bindings bindings = se.getBindings(ScriptContext.ENGINE_SCOPE);
					  String result = bindings.get("result").toString(); 
					  amount = new BigDecimal(result);
				  }
				  
			  } else if (deductionType.equalsIgnoreCase(this.java)){
				  Class<?> formulaGaji;
				  SalaryFormula salaryFormula = null;
				  try {
					  formulaGaji = Class.forName(bpd.getClassname());
					  salaryFormula = (SalaryFormula) formulaGaji.newInstance();
				  } catch (ClassNotFoundException e) {
					  e.printStackTrace();
				  } catch (InstantiationException e){
					  e.printStackTrace();
				  } catch (IllegalAccessException e){
					  e.printStackTrace();
				  }
				  double amount_f = salaryFormula.hitungFormulaGaji(null, null, null, sped);
				  amount = new BigDecimal(amount_f);
				  
			  } else if (deductionType.equalsIgnoreCase(this.constant)){
				  type=this.constant;
    			  amount=bpd.getAmount();
    			  if (amount==null)
    				  amount=BigDecimal.ZERO;
			  }
			   
			  	  
		  } else {
			  //tidak ada perkecualian, ikut master earning
			  if (type.equalsIgnoreCase(this.formula)){
				//formula
				  if (formula==null || formula.isEmpty()){
					  //empty formula, set spee to zero
					  amount = BigDecimal.ZERO;
					  
				  } else {
					  //evaluasi formula
					  se.eval(formula);
					  Bindings bindings = se.getBindings(ScriptContext.ENGINE_SCOPE);
					  String result = bindings.get("result").toString();
					  amount = new BigDecimal(result);
					  
				  }
				  
			  } else if (type.equalsIgnoreCase(this.java)){
				  Class<?> formulaGaji;
				  SalaryFormula salaryFormula = null;
				  try {
					  formulaGaji = Class.forName(deduction.getClassname());
					  salaryFormula = (SalaryFormula) formulaGaji.newInstance();
				  } catch (ClassNotFoundException e) {
					  e.printStackTrace();
				  } catch (InstantiationException e){
					  e.printStackTrace();
				  } catch (IllegalAccessException e){
					  e.printStackTrace();
				  }
				  double amount_f = salaryFormula.hitungFormulaGaji(null, null, null, sped);
				  amount = new BigDecimal(amount_f);
				  
			  } else if (type.equalsIgnoreCase(this.constant)){
				  //do nothing since amount have been retrieved at the begining
				  
			  }
			  
		  }
		  
		  amount = amount.setScale(2, RoundingMode.HALF_UP);
		  sped.setAmount(amount);
		  OBDal.getInstance().save(sped);
		  se.put(key, amount);
		  
      }
	  
  }
  
  //implementasi tipe process adhoc pada earning
  private void doEvaluateEarning(pyr_sp_employee spe, ScriptEngine se, EmployeeSalaryCategory esc) throws ScriptException {
	  
      String whereclause = "as spee where spee.employeeSalaryPayment.id = :speID order by spee.earning.sequenceNumber asc";
      OBQuery<pyr_spe_earning> speeQ = OBDal.getInstance().createQuery(pyr_spe_earning.class, whereclause);
      speeQ.setNamedParameter("speID", spe.getId());
      
      for (pyr_spe_earning spee : speeQ.list()){
    	  pyr_earning earning = spee.getEarning();
    	  String key = earning.getSearchKey();
    	  String type = earning.getType();
    	  String formula = earning.getFormula(); 
    	  BigDecimal amount= earning.getAmount();
    	  if (amount==null)
    		  amount=BigDecimal.ZERO;
    	  
    	  //lihat ada perkecualian atau tidak
    	  
    	  OBCriteria<pyr_bp_earning> bpeC = OBDal.getInstance().createCriteria(pyr_bp_earning.class);
		  bpeC.add(Restrictions.eq(pyr_bp_earning.PROPERTY_SALARYCATEGORY, esc));
		  bpeC.add(Restrictions.eq(pyr_bp_earning.PROPERTY_EARNING, earning));
		  List<pyr_bp_earning> bpeL = bpeC.list();
		  if (bpeL.size()>0){
			  //ada perkecualian
			  pyr_bp_earning bpe = bpeL.get(0);
			  type=bpe.getType();
			  if (type.equalsIgnoreCase("FORMULA")){
				  formula = bpe.getFormula();
				//evaluasi formula
				  se.eval(formula);
				  Bindings bindings = se.getBindings(ScriptContext.ENGINE_SCOPE);
				  String result = bindings.get("result").toString();
				  amount = new BigDecimal(result);				  
			  } else if (type.equalsIgnoreCase("JAVA")){
				  Class<?> formulaGaji;
				  SalaryFormula salaryFormula = null;
				  try {
					  formulaGaji = Class.forName(bpe.getClassname());
					  salaryFormula = (SalaryFormula) formulaGaji.newInstance();
				  } catch (ClassNotFoundException e) {
					  e.printStackTrace();
				  } catch (InstantiationException e){
					  e.printStackTrace();
				  } catch (IllegalAccessException e){
					  e.printStackTrace();
				  }
				  double amount_f = salaryFormula.hitungFormulaGaji(null, null, spee, null);
				  amount = new BigDecimal(amount_f);
			  } else {
    			  amount=bpe.getAmount();
    			  if (amount==null)
    				  amount = BigDecimal.ZERO;
			  }
			  	  
		  } else {
			  //tidak ada perkecualian, ikut master earning
			  if (type.equalsIgnoreCase(this.constant)){
				  //konstan, do nothing
				  
			  } else {
				  //formula
				  if (type.equalsIgnoreCase(this.formula)){
					  if (formula==null || formula.isEmpty()){
						  //empty formula, set spee to zero
						  amount = BigDecimal.ZERO;
						  
					  } else {
						  //evaluasi formula
						  se.eval(formula);
						  Bindings bindings = se.getBindings(ScriptContext.ENGINE_SCOPE);
						  String result = bindings.get("result").toString();
						  amount = new BigDecimal(result);
						  
					  }
					  
				  } else if (type.equalsIgnoreCase(this.constant)){
					  //do nothing, amount have been retrieved at the begining
					  
				  } else if (type.equalsIgnoreCase(this.java)){
					  Class<?> formulaGaji;
					  SalaryFormula salaryFormula = null;
					  try {
						  formulaGaji = Class.forName(earning.getClassname());
						  salaryFormula = (SalaryFormula) formulaGaji.newInstance();
					  } catch (ClassNotFoundException e) {
						  e.printStackTrace();
					  } catch (InstantiationException e){
						  e.printStackTrace();
					  } catch (IllegalAccessException e){
						  e.printStackTrace();
					  }
					  double amount_f = salaryFormula.hitungFormulaGaji(null, null, spee, null);
					  amount = new BigDecimal(amount_f);
				  }
				  
			  }
		  }
		  
		  amount = amount.setScale(2, RoundingMode.HALF_UP);
		  spee.setAmount(amount);
		  OBDal.getInstance().save(spee);
		  se.put(key, amount);
		  
      }
  }
  
  private void doGetInternalVariable(ScriptEngine se, BusinessPartner bp, Date startdate, Date enddate) 
		  throws NoConnectionAvailableException, SQLException {
	  long nulldate = new Date(0).getTime();
	  
	  AttendancePerformanceBean abp = AttendanceUtility.getAttendancePerformance(startdate, enddate, bp);
	  
	  int harikerja = abp.getHarikerja();
	  se.put("harikerja", harikerja);
	  
	  int nightShift = abp.getNightShift();
	  se.put("nightshift", nightShift);
	  
	  int terlambat = abp.getTerlambat();
	  se.put("terlambat", terlambat);
	  
	  int durasiterlambat = abp.getDurasiterlambat();
	  se.put("durasiterlambat", durasiterlambat);
	  
	  int pulangcepat = abp.getPulangcepat();
	  se.put("pulangcepat", pulangcepat);
	  
	  int durasipulangcepat = abp.getDurasipulangcepat();
	  se.put("durasipulangcepat", durasipulangcepat);
	  
	  int cuti = abp.getCuti();
	  se.put("leave", cuti);

	  int tidakmasuk = abp.getTidakmasuk();
	  se.put("tidakmasuk", tidakmasuk);
	  
	  int unpaidLeaveDuration = abp.getUnpaidLeaveDuration();
	  se.put("leaveduration", unpaidLeaveDuration);
	  
	  int unpaidLeave = abp.getUnpaidLeave();
	  se.put("unpaidleave", unpaidLeave);

	  //employee related internal variable
	  boolean issalesrep=bp.isSalesRepresentative();
	  if (issalesrep)
		  se.put(this.issalesrep, "Y");
	  else
		  se.put(this.issalesrep, "N");
	  
	  boolean istaxexempt=bp.isTaxExempt();
	  if (istaxexempt)
		  se.put(this.istaxexempt, "Y");
	  else
		  se.put(this.istaxexempt, "N");
	  
	  boolean isworker=bp.isOperator();
	  if (isworker)
		  se.put(this.isdirectlabour, "Y");
	  else
		  se.put(this.isdirectlabour, "N");
	  
	  hris_site site = bp.getHrisSite();
	  if (site!=null){
		  String sitecode=site.getSearchKey();
		  se.put(this.sitecode, sitecode);
	  } else
		  se.put(this.sitecode, "");
	  
	  Date dateofbirth = bp.getHrisBirthday();
	  if (dateofbirth!=null)
		  se.put(this.dateofbirth, dateofbirth.getTime());
	  else
		  se.put(this.dateofbirth, nulldate);
	  
	  String religion = bp.getHrisReligion();
	  if (religion!=null)
		  se.put(this.religion, religion);
	  else
		  se.put(this.religion, "");
	  
	  String maritalstatus = bp.getHRISMaritalStatus();
	  if (maritalstatus!=null)
		  se.put(this.maritalstatus, maritalstatus);
	  else
		  se.put(this.maritalstatus, "");
	  
	  Date joindate = bp.getHrisJoindate();
	  if (joindate!=null)
		  se.put(this.joindate, joindate.getTime());
	  else
		  se.put(this.joindate, nulldate);
	  
	  Date retirementdate = bp.getHrisRetirementdate();
	  if (retirementdate!=null)
		  se.put(this.retirementdate, retirementdate.getTime());
	  else
		  se.put(this.retirementdate, nulldate);
	  
	  HRIS_C_Bp_Department department=bp.getHrisCBpDepartment();
	  if (department!=null){
		  String departementcode=department.getSearchKey();
		  se.put(this.departementcode, departementcode);
	  } else
		  se.put(this.departementcode, "");
	  
	  String employmenttype = bp.getHRISEmployementType();
	  if (employmenttype!=null)
		  se.put(this.employmenttype, employmenttype);
	  else
		  se.put(this.employmenttype, "");
	  
	  String employeegrade = bp.getHRISLevel();
	  if (employeegrade!=null)
		  se.put(this.employeegrade, employeegrade);
	  else
		  se.put(this.employeegrade, "");
	  
	  String employeeposition = bp.getHRISPosition();
	  if (employeeposition!=null)
		  se.put(this.employeeposition, employeeposition);
	  else
		  se.put(this.employeeposition, "");
	  
	  hris_jobtitle jobtitle = bp.getHrisJobtitle();
	  if (jobtitle!=null){
		  String jobtitlecode=jobtitle.getSearchKey();
		  se.put(this.jobtitlecode, jobtitlecode);
	  } else
		  se.put(this.jobtitlecode, "");
	  
	  boolean isstaff = bp.isHRISStaff();
	  if (isstaff)
		  se.put(this.isstaff, "Y");
	  else
		  se.put(this.isstaff, "N");
	  
	  boolean isremoteemployee = bp.isHrisIssite();
	  if (isremoteemployee)
		  se.put(this.isremoteemployee, "Y");
	  else
		  se.put(this.isremoteemployee, "N");
	  
	  String sex = bp.getHrisSex();
	  se.put(this.sex, sex);
	  
	  
	  Costcenter costcenter = bp.getHrisCCostcenter();
	  if (costcenter!=null){
		  String costcentercode=costcenter.getSearchKey();
		  se.put(this.costcentercode, costcentercode);
	  } else
		  se.put(this.costcentercode, "");
	  
	  Country country = bp.getHrisNationality();
	  if (country!=null){
		  String nationalitycode=country.getISOCountryCode();
		  se.put(this.nationalitycode, nationalitycode);
	  } else
		  se.put(this.nationalitycode, "");
	  
	  String echelon = bp.getHrisEchelon();
	  if (echelon!=null)
		  se.put(this.echelon, echelon);
	  else
		  se.put(this.echelon, "");
	  	  
	  //diasumsikan earningparamcode deductionparamcode dan salaryvariablecode adalah unik 
	  
	  //earning related internal variable, diasumsikan earning_param tidak mengandung tabel kebenaran. contoh table kebenaran adalah pemetaan masa kerja terhadap nilai masa kerja. table kebenaran didefinisikan di earning formula
	  String sqlQuery="select	ep.value as earningparamcode, ep.param_amount as amount"
	  		+ " from pyr_earning_param ep"
	  		+ " where ep.ad_client_id=?"
	  		+ " and (case when ep.echelon is not null then ep.echelon=? else 1=1 end)"
	  		+ " and (case when ep.position is not null then ep.position=? else 1=1 end)"
	  		+ " and (case when ep.level is not null then ep.level=? else 1=1 end)"
	  		+ " and ep.validfrom<=?"
	  		+ " and (case when ep.validto is not null then validto>=? else 1=1 end)";
	  PreparedStatement ps;
      ConnectionProvider conn = new DalConnectionProvider();
      Connection connection = conn.getConnection();
      ps = connection.prepareStatement(sqlQuery);
      ps.setString(1, clientID);
      ps.setString(2, echelon);
      ps.setString(3, employeeposition);
      ps.setString(4, employeegrade);
      java.sql.Date startdateSQL = new java.sql.Date(startdate.getTime());
      ps.setDate(5, startdateSQL);
      java.sql.Date enddateSQL = new java.sql.Date(enddate.getTime());
      ps.setDate(6, enddateSQL);
      ResultSet result=ps.executeQuery();
      while (result.next()){
    	  String earningparamcode = result.getString("earningparamcode");
    	  BigDecimal amount = result.getBigDecimal("amount");
    	  se.put(earningparamcode, amount.doubleValue());
      }
      ps.close();
      result.close();
      
    //deduction related internal variable, diasumsikan deduction_param tidak mengandung tabel kebenaran. contoh table kebenaran adalah pemetaan masa kerja terhadap nilai masa kerja. table kebenaran didefinisikan di deduction formula
      sqlQuery="select	ep.value as deductionparamcode, ep.param_amount as amount"
  	  		+ " from pyr_deduction_param ep"
  	  		+ " where ep.ad_client_id=?"
  	  		+ " and (case when ep.echelon is not null then ep.echelon=? else 1=1 end)"
  	  		+ " and (case when ep.position is not null then ep.position=? else 1=1 end)"
  	  		+ " and (case when ep.level is not null then ep.level=? else 1=1 end)"
  	  		+ " and ep.validfrom<=?"
  	  		+ " and (case when ep.validto is not null then validto>=? else 1=1 end)";
  	  ps=null;
  	  result=null;
  	  conn = new DalConnectionProvider();
  	  connection = conn.getConnection();
  	  ps = connection.prepareStatement(sqlQuery);
  	  ps.setString(1, clientID);
  	  ps.setString(2, echelon);
  	  ps.setString(3, employeeposition);
  	  ps.setString(4, employeegrade);
  	  ps.setDate(5, startdateSQL);
  	  ps.setDate(6, enddateSQL);
      result=ps.executeQuery();
      while (result.next()){
      	  String deductionparamcode = result.getString("deductionparamcode");
      	  BigDecimal amount = result.getBigDecimal("amount");
      	  se.put(deductionparamcode, amount.doubleValue());
      }
      ps.close();
      result.close();
      
      //internal variable from salary variable entries
      sqlQuery="select	ep.value as salaryvariablecode, ep.amount as amount"
      		+ " from pyr_sal_variable ep"
      		+ " where ep.ad_client_id=?"
      		+ " and (case when ep.c_bpartner_id is not null then ep.c_bpartner_id=? else 1=1 end)"
      		+ " and ep.validfrom<=?"
      		+ " and (case when ep.validto is not null then validto>=? else 1=1 end)";
      ps=null;
  	  result=null;
  	  conn = new DalConnectionProvider();
  	  connection = conn.getConnection();
  	  ps = connection.prepareStatement(sqlQuery);
      ps.setString(1, clientID);
      ps.setString(2, bp.getId());
      ps.setDate(3, startdateSQL);
      ps.setDate(4, enddateSQL);
      result=ps.executeQuery();
      while (result.next()){
      	  String salaryvariablecode = result.getString("salaryvariablecode");
      	  BigDecimal amount = result.getBigDecimal("amount");
      	  se.put(salaryvariablecode, amount.doubleValue());
      }
      ps.close();
      result.close();
      
      //internal variable from employee education
      sqlQuery="select a.level as tingkatpendidikan, b.name as jurusanpendidikan"
      		+ " from hris_c_bp_education a"
      		+ " inner join hris_dicipline b on b.hris_dicipline_id=a.dicipline"
      		+ " where a.c_bpartner_id=?"
      		+ " and a.validfrom<=?"
      		+ " and a.validto>=?";
      ps=null;
      result=null;
      conn = new DalConnectionProvider();
      connection = conn.getConnection();
      ps = connection.prepareStatement(sqlQuery);
      ps.setString(1, bp.getId());
      ps.setDate(2, startdateSQL);
      ps.setDate(3, enddateSQL);
      result=ps.executeQuery();
      while (result.next()){
    	  String tingkatpendidikan = result.getString("tingkatpendidikan");
    	  se.put("tingkatpendidikan", tingkatpendidikan);
    	  String jurusanpendidikan = result.getString("jurusanpendidikan");
    	  se.put("jurusanpendidikan", jurusanpendidikan);
      }
      ps.close();
      result.close();
  }

  @Override
  public double hitungFormulaGaji(pyr_salarypayment salaryPayment, pyr_sp_employee sp_employee, pyr_spe_earning spee,
		  pyr_spe_deduction sped) {
	System.out.println("Hitung Salary Formula Mulai.");
	  List<pyr_sp_employee> speList;
	  if (sp_employee!=null){
		  speList = new ArrayList<pyr_sp_employee>();
		  speList.add(sp_employee);
	  } else
		  speList = salaryPayment.getPyrSpEmployeeList();

	  try {
		  doCalculateSalaryFormula(speList);
	  } catch (NoConnectionAvailableException e) {
		  e.printStackTrace();
		  throw new OBException(e.getMessage());
	  } catch (SQLException e) {
		  e.printStackTrace();
		  throw new OBException(e.getMessage());
	  } catch (ScriptException e) {
		  e.printStackTrace();
		  throw new OBException(e.getMessage());
	  }

	  return 0;
  }
}
