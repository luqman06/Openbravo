//package org.wirabumi.hris.employee.master.Actionbutton;

package org.wirabumi.hris.payroll.ad_process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.EmployeeSalaryCategory;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.employee.master.data.HRISDeductionTemplate;
import org.wirabumi.hris.employee.master.data.HRISEarningTemplate;
import org.wirabumi.hris.payroll.pyr_deduction;
import org.wirabumi.hris.payroll.pyr_earning;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;

public class GenerateSalaryPayment extends DalBaseProcess {
  Logger log4j = Logger.getLogger(this.getClass());
  protected ConnectionProvider connectionProvider = new DalConnectionProvider();
  protected VariablesSecureApp vars = RequestContext.get().getVariablesSecureApp();
  private final HashMap<BusinessPartner, List<HRISEarningTemplate>> earningList = new HashMap<BusinessPartner, List<HRISEarningTemplate>>();
  private final HashMap<BusinessPartner, List<HRISDeductionTemplate>> deductionList = new HashMap<BusinessPartner, List<HRISDeductionTemplate>>();

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
	long starttime = System.currentTimeMillis();
    int created = 0;
    
    //load parameters
    String employmentType = (String) bundle.getParams().get("employmenttype"); //empty employmentType is mean all employmentType
    String paymentGroup = (String) bundle.getParams().get("pyrPaymentGroup"); //empty paymentGroup is mean all paymentGroup
    String salaryPayment = (String) bundle.getParams().get("PYR_Salarypayment_ID");
    
    //load earning to hashmap
    loadEarning(paymentGroup);
    loadDeduction(paymentGroup);

    try {
      log4j.debug("start generating valid employee into salary payment || employee");
      final pyr_salarypayment salarypayment = OBDal.getInstance().get(pyr_salarypayment.class,
          salaryPayment);
      Date startDate = salarypayment.getStartingDate();
      BusinessPartner payrollMaster = (BusinessPartner) salarypayment.getPayrollmaster();
      final String clientExecuted = bundle.getContext().getClient();
      created = generateSPEmployee(salarypayment, startDate, payrollMaster, paymentGroup, employmentType, clientExecuted);
      log4j.debug("commiting generated employee salary payment to database");
      OBDal.getInstance().commitAndClose();
      log4j.debug("generated employee salary payment committed, now building process's return message");
      
      long duration= System.currentTimeMillis()-starttime;
      long second = (duration/1000)%60;
      long minute = (duration/(1000*60))%60;
      long hour = (duration/(1000*60*60))%24;
      String time = String.format("%02d:%02d:%02d", hour,minute,second);
      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
          " @Hris_DataCreated@ in "+time);
      final OBError msg = new OBError();
      
      msg.setType("Success");
      msg.setTitle("Created Succes");
      msg.setMessage(created + " " + message);
      bundle.setResult(msg);
      
    } catch (Exception e) {
      log4j.debug("exception caught, then rollback");
      OBDal.getInstance().rollbackAndClose();
      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
          e.toString());
      final OBError msg = new OBError();
      msg.setType("Error");
      msg.setTitle("Exception");
      msg.setMessage(message);
      bundle.setResult(msg);
      log4j.error(e);
      e.printStackTrace();
    }
  }

private void loadEarning(String paymentGroup) throws NoConnectionAvailableException, SQLException {
	String sqlQuery = "select A.c_bpartner_id, c.hris_earning_template_id from"
			+ " (select	a.c_bpartner_id,"
			+ "	(select c_bp_salcategory_id from c_bp_salcategory"
			+ "  where c_bpartner_id=a.c_bpartner_id"
			+ "	 order by datefrom desc limit 1) as c_bp_salcategory_id"
			+ " from c_bpartner a"
			+ " where a.ad_client_id=?"
			+ " and a.isactive='Y' )A"
			+ " inner join c_bp_salcategory b on b.c_bp_salcategory_id=A.c_bp_salcategory_id"
			+ " inner join hris_earning_template c on c.c_salary_category_id=b.c_salary_category_id"
			+ " inner join pyr_earning d on d.pyr_earning_id=c.pyr_earning_id"
			+ " where (d.pyr_payment_group=? or d.pyr_payment_group is null or ?='')";
	
	ConnectionProvider conn = new DalConnectionProvider();
	Connection connection = conn.getConnection();
	PreparedStatement ps = connection.prepareStatement(sqlQuery);
	ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
	ps.setString(2, paymentGroup);
	ps.setString(3, paymentGroup);
	ResultSet rs = ps.executeQuery();
	while (rs.next()){
		String bpID = rs.getString("c_bpartner_id");
		BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
		String earningTemplateID = rs.getString("hris_earning_template_id");
		HRISEarningTemplate et = OBDal.getInstance().get(HRISEarningTemplate.class, earningTemplateID);
		List<HRISEarningTemplate> earningTemplateList = null;
		if (earningList.containsKey(bp))
			earningTemplateList=earningList.get(bp);
		else
			earningTemplateList=new ArrayList<HRISEarningTemplate>();
		
		if (!earningTemplateList.contains(et))
			earningTemplateList.add(et);
		
		earningList.put(bp, earningTemplateList);
	}
}

private void loadDeduction(String paymentGroup) throws NoConnectionAvailableException, SQLException {
	String sqlQuery = "select A.c_bpartner_id, c.hris_deduction_template_id from"
			+ " (select	a.c_bpartner_id,"
			+ "	(select c_bp_salcategory_id from c_bp_salcategory"
			+ "  where c_bpartner_id=a.c_bpartner_id"
			+ "	 order by datefrom desc limit 1) as c_bp_salcategory_id"
			+ " from c_bpartner a"
			+ " where a.ad_client_id=?"
			+ " and a.isactive='Y' )A"
			+ " inner join c_bp_salcategory b on b.c_bp_salcategory_id=A.c_bp_salcategory_id"
			+ " inner join hris_deduction_template c on c.c_salary_category_id=b.c_salary_category_id"
			+ " inner join pyr_deduction d on d.pyr_deduction_id=c.pyr_deduction_id"
			+ " where (d.pyr_payment_group=? or d.pyr_payment_group is null or ?='')";
	
	ConnectionProvider conn = new DalConnectionProvider();
	Connection connection = conn.getConnection();
	PreparedStatement ps = connection.prepareStatement(sqlQuery);
	ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
	ps.setString(2, paymentGroup);
	ps.setString(3, paymentGroup);
	ResultSet rs = ps.executeQuery();
	while (rs.next()){
		String bpID = rs.getString("c_bpartner_id");
		BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
		String deductionTemplateID = rs.getString("hris_deduction_template_id");
		HRISDeductionTemplate dt = OBDal.getInstance().get(HRISDeductionTemplate.class, deductionTemplateID);
		List<HRISDeductionTemplate> deductionTemplateList = null;
		if (deductionList.containsKey(bp))
			deductionTemplateList=deductionList.get(bp);
		else
			deductionTemplateList=new ArrayList<HRISDeductionTemplate>();
		
		if (!deductionTemplateList.contains(dt))
			deductionTemplateList.add(dt);
		
		deductionList.put(bp, deductionTemplateList);
	}
}

private int generateSPEmployee(pyr_salarypayment salarypayment, Date startDate,
      BusinessPartner payrollMaster, String paymentgroup, String employmentType, String client) {
    int created = 0;
    Date endDate = salarypayment.getEndingDate();
    
    log4j.debug("build pensionDate");
    /*
     * TODO tanggal pensiun masih hardcoded.
     * saat ini tanggal pensiun adalah tanggal 1 bulan ini.
     * yang disebut bulan ini adalah bulan pada effective date pada salary payment yang bersangkutan.
     * contoh: effective date tanggal 31 maret, maka pensionDate adalah tanggal 1 maret
     * */
    Calendar cal = Calendar.getInstance();
    cal.setTime(salarypayment.getEffectiveDate());
    cal.set(Calendar.DATE, 1);
    cal.add(Calendar.DATE, 1);
    Date pensionDate = cal.getTime();

    try {
    	log4j.debug("build sql select statement to get valid employee");
      /* build sql select statement to get valid employee, here is the criteria:
       * 1. active employee
       * 2. BusinessPartner.isEmployee=true
       * 3. retirement date is blank (its mean not defined yet), or retirement date is greater than pension date, lihat komentar pada variable pensionDate
       * 4. pada tab employee || salary category, diambil 1 record terakhir dengan dateFrom yang lebih tua dari tanggal validFrom pada salary payment, jika tidak ada record di tab ini, akan dinyatakan invalid employee
       * 5. employee yang sudah terdaftar pada salary payment yang bersangkutan tidak akan dimasukkan lagi
       * 6. earning dan deduction didapatkan dari employee || salary category || earning/deduction
       * 7. earning dan deduction di filter payment group, atau payment group nya null, atau value dari parameter payment group nya empty*/
      final String strQuery = "select	bp.c_bpartner_id, bp.value, bp.name, bp.isactive,"
    		  +" 	(select c_bp_salcategory_id from c_bp_salcategory"
    		  +" 	 where c_bpartner_id=bp.c_bpartner_id"
    		  +" 	 and datefrom<=?::date"
    		  +" 	 order by datefrom desc limit 1) as c_bp_salcategory_id"
    		  +" from c_bpartner bp"
    		  +" where bp.ad_client_id=?"
    		  +" and not exists (select 1 from pyr_sp_employee"
    		  +" 		where pyr_salarypayment_id=?"
    		  +" 		and c_bpartner_id=bp.c_bpartner_id)"
    		  +" and isactive='Y'"
    		  +" and isemployee='Y'"
    		  +" and (em_hris_retirementdate>?::date or em_hris_retirementdate is null)"
    		  +" and EM_Pyr_Payrollmaster_ID=?"
    		  +" order by bp.value";
      
      PreparedStatement ps;
      ConnectionProvider conn = new DalConnectionProvider();
      Connection connection = conn.getConnection();
      ps = connection.prepareStatement(strQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      ps.setString(1, df.format(endDate));
      ps.setString(2, OBContext.getOBContext().getCurrentClient().getId());
      ps.setString(3, salarypayment.getId());
      ps.setString(4, df.format(pensionDate));
      ps.setString(5, payrollMaster.getId());
      ResultSet result=ps.executeQuery();
      
      if (result!=null){
    	  result.last();
    	  int size = result.getRow();
          log4j.debug("processing "+size+" record(s)");
          result.beforeFirst();
    	  while (result.next()){
    		    String bpID = result.getString("c_bpartner_id");
    		    BusinessPartner bPartner = OBDal.getInstance().get(BusinessPartner.class, bpID);
    	    	String bpsalID = result.getString("c_bp_salcategory_id");
    	    	EmployeeSalaryCategory salCategory = OBDal.getInstance().get(EmployeeSalaryCategory.class, bpsalID);
    	        pyr_sp_employee spEmployee = OBProvider.getInstance().get(pyr_sp_employee.class);
    	        spEmployee.setBusinessPartner(bPartner);
    	        spEmployee.setSalaryPayment(salarypayment);
    	        spEmployee.setOrganization(salarypayment.getOrganization());
    	        spEmployee.setActive(true);
    	        spEmployee.setPosition(bPartner.getHRISPosition());
    	        spEmployee.setJobTitle(bPartner.getHrisJobtitle());
    	        spEmployee.setEmployeGrade(bPartner.getHRISLevel());
    	        spEmployee.setCostCenter(bPartner.getHrisCCostcenter());
    	        spEmployee.setSite(bPartner.getHrisSite());
    	        OBDal.getInstance().save(spEmployee);
    			generateSPEEarning(spEmployee, paymentgroup, salCategory);
    	    	generateSPEDeduction(spEmployee, paymentgroup, salCategory);
    			created++;
    			log4j.debug("procesed record "+created+" of "+size+" : "+bPartner.getName());
    	        
    	  }
    	  return created;
      } else {
    	  return 0;
      }
      
    } catch (Exception e) {
      OBDal.getInstance().rollbackAndClose();
      log4j.error(e);
      e.printStackTrace();
      throw new OBException(e.getMessage());
    }

  }

  private void generateSPEEarning(pyr_sp_employee employeePaid, String paymentgroup,
      EmployeeSalaryCategory salCategory) {
    
	  for (HRISEarningTemplate spEarning : earningList.get(employeePaid.getBusinessPartner())) {
		  pyr_earning earningComponent = spEarning.getPYREarning();
	      BigDecimal amount = spEarning.getAmount();
	      pyr_spe_earning speEarning = OBProvider.getInstance().get(pyr_spe_earning.class);
	      speEarning.setEmployeeSalaryPayment(employeePaid);
	      speEarning.setEarning(earningComponent);
	      speEarning.setAmount(amount);
	      speEarning.setActive(true);
	      OBDal.getInstance().save(speEarning);
	  }
    
  }

  private void generateSPEDeduction(pyr_sp_employee employeePaid, String paymentgroup,
      EmployeeSalaryCategory salCategory) {
	  List<HRISDeductionTemplate> bpDeduction = deductionList.get(employeePaid.getBusinessPartner());
	  if (bpDeduction==null)
		  return;
      for (HRISDeductionTemplate spDeduction : bpDeduction) {
        pyr_deduction deductionComponent = spDeduction.getPYRDeduction();
        BigDecimal amount = spDeduction.getAmount();
        pyr_spe_deduction speDeduction = OBProvider.getInstance().get(pyr_spe_deduction.class);
        speDeduction.setEmployeeSalaryPayment(employeePaid);
        speDeduction.setPYRDeduction(deductionComponent);
        speDeduction.setAmount(amount);
        speDeduction.setActive(true);
        OBDal.getInstance().save(speDeduction);
      }
  }

}
