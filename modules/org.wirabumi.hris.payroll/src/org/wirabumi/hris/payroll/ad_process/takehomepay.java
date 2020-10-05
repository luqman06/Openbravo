package org.wirabumi.hris.payroll.ad_process;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;

public class takehomepay extends DalBaseProcess {
  Logger log4j = Logger.getLogger(this.getClass());

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    // TODO Auto-generated method stub
    VariablesSecureApp vars = bundle.getContext().toVars();
    Date waktu;
    int updated = 0;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR, -12);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);

    String Date = (String) bundle.getParams().get("paymentdate");
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");// (vars.getSessionValue("#AD_JavaDateFormat"));
    waktu = (Date == null) ? null : format.parse(Date);
    waktu = (waktu == null) ? calendar.getTime() : waktu;

    try {
      final String whereClause = "as spe inner join spe.salaryPayment sp "
          + " where sp.startingDate <= ? and sp.endingDate >= ?";
      List<Object> params = new ArrayList<Object>();
      params.add(waktu);
      params.add(waktu);

      OBQuery<pyr_sp_employee> employeePayment = OBDal.getInstance().createQuery(
          pyr_sp_employee.class, whereClause);
      employeePayment.setParameters(params);
      List<pyr_sp_employee> employePayments = employeePayment.list();

      for (pyr_sp_employee employee : employePayments) {
        double earning = calculateEarning(employee);
        double deduction = calculateDeduction(employee);
        double takehomepay = earning - deduction;
        BigDecimal amount = new BigDecimal(takehomepay);
        employee.setAmount(amount);
        OBDal.getInstance().save(employee);
        updated++;
      }
      OBDal.getInstance().commitAndClose();
      final OBError msg = new OBError();
      msg.setType("Success");
      msg.setTitle("Done");
      msg.setMessage("Background Process Berjalan" + updated + "Di update");
      bundle.setResult(msg);
    } catch (Exception e) {
      OBDal.getInstance().rollbackAndClose();
      e.printStackTrace();
      final OBError msg = new OBError();
      msg.setType("Error");
      msg.setTitle("Done");
      msg.setMessage("Background Gagal Berjalan");
      bundle.setResult(msg);
    }
  }

  private double calculateEarning(pyr_sp_employee employee) {
    double amount = 0.0;
    try {
      OBQuery<pyr_spe_earning> earningQ = OBDal.getInstance().createQuery(pyr_spe_earning.class,
          "where employeeSalaryPayment=?");
      List<Object> params = new ArrayList<Object>();
      params.add(employee);
      earningQ.setParameters(params);
      List<pyr_spe_earning> earnings = earningQ.list();
      for (pyr_spe_earning speEarning : earnings) {
        amount += speEarning.getAmount().doubleValue();
      }
    } catch (Exception e) {
      log4j.error(e);
    }
    return amount;
  }

  private double calculateDeduction(pyr_sp_employee employee) {
    double amount = 0.0;
    try {
      OBQuery<pyr_spe_deduction> deductionQ = OBDal.getInstance().createQuery(
          pyr_spe_deduction.class, "where employeeSalaryPayment=?");
      List<Object> params = new ArrayList<Object>();
      params.add(employee);
      deductionQ.setParameters(params);
      List<pyr_spe_deduction> deductions = deductionQ.list();
      for (pyr_spe_deduction speDeduction : deductions) {
        amount += speDeduction.getAmount().doubleValue();
      }
    } catch (Exception e) {
      log4j.error(e);
    }
    return amount;
  }
}
