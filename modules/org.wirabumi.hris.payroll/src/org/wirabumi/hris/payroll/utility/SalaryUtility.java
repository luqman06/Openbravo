package org.wirabumi.hris.payroll.utility;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.payroll.pyr_earning;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;

public class SalaryUtility {

  public static void setAmountOfEarningPaymentAllEmployee(String pyr_salarypayment_id, String pyr_sp_employee_id,
      String PaymentComponent, BigDecimal amount) throws NoConnectionAvailableException, SQLException {
	String strQuery = "";
	if (pyr_sp_employee_id==null)
		strQuery="update pyr_spe_earning spee set amount=? "
				+ " where pyr_sp_employee_id in (select pyr_sp_employee_id from pyr_sp_employee where pyr_salarypayment_id=?)"
				+ " and pyr_earning_id in (select pyr_earning_id from pyr_earning e where e.value=?)";
	else
		strQuery="update pyr_spe_earning spee set amount=? "
		+ " where pyr_sp_employee_id =?"
		+ " and pyr_earning_id in (select pyr_earning_id from pyr_earning e where e.value=?)";
	PreparedStatement ps;
	ConnectionProvider conn = new DalConnectionProvider();
	Connection connection = conn.getConnection();
	ps = connection.prepareStatement(strQuery);
	ps.setBigDecimal(1, amount);
	if (pyr_sp_employee_id==null)
		ps.setString(2, pyr_salarypayment_id);
	else
		ps.setString(2, pyr_sp_employee_id);
	ps.setString(3, PaymentComponent);
	ps.executeUpdate();
	connection.commit();
	
  }
  
  public static double getPendapatan(pyr_sp_employee employeePayment, pyr_earning earning){
	  
	  OBCriteria<pyr_spe_earning> speeCriteria = OBDal.getInstance().createCriteria(pyr_spe_earning.class);
	  speeCriteria.add(Restrictions.eq(pyr_spe_earning.PROPERTY_EMPLOYEESALARYPAYMENT, employeePayment));
	  speeCriteria.add(Restrictions.eq(pyr_spe_earning.PROPERTY_EARNING, earning));
	  List<pyr_spe_earning> speeList = speeCriteria.list();
	  if (speeList.size()>0)
		  return speeList.get(0).getAmount().doubleValue();
	  else
		  return 0;
  }

  public static void setAmountOfDeductionPaymentAllEmployee(String pyr_salarypayment_id, String pyr_sp_employee_id,
      String PaymentComponent, BigDecimal amount) throws NoConnectionAvailableException, SQLException {
	  
	  String strQuery = "";
	  if (pyr_sp_employee_id==null)
		  strQuery="update pyr_spe_deduction sped set amount=? "
			+ " where pyr_sp_employee_id in (select pyr_sp_employee_id from pyr_sp_employee where pyr_salarypayment_id=?)"
			+ " and pyr_deduction_id in (select pyr_deduction_id from pyr_deduction d where d.value=?)";
	  else
		  strQuery="update pyr_spe_deduction sped set amount=? "
					+ " where pyr_sp_employee_id = ?"
					+ " and pyr_deduction_id in (select pyr_deduction_id from pyr_deduction d where d.value=?)";
	  PreparedStatement ps;
	  ConnectionProvider conn = new DalConnectionProvider();
	  Connection connection = conn.getConnection();
	  ps = connection.prepareStatement(strQuery);
	  ps.setBigDecimal(1, amount);
	  if (pyr_sp_employee_id==null)
		  ps.setString(2, pyr_salarypayment_id);
	  else
		  ps.setString(2, pyr_sp_employee_id);
	  ps.setString(3, PaymentComponent);
	  int result=ps.executeUpdate();
	  connection.commit();
  }

  public static List<pyr_spe_earning> earningTransactionList(String paymentStatus,
      String componentSearchKey, Date paymentDate) {
    List<pyr_spe_earning> earninglisList = new ArrayList<pyr_spe_earning>();
    try {
      String whereClause = "as spee where spee.employeeSalaryPayment.salaryPayment.documentStatus= ? "
          + "and spee.earning.searchKey=? "
          + "and ? between spee.employeeSalaryPayment.salaryPayment.startingDate "
          + "and spee.employeeSalaryPayment.salaryPayment.endingDate";
      List<Object> params = new ArrayList<Object>();
      params.add(paymentStatus);
      params.add(componentSearchKey);
      params.add(paymentDate);
      OBQuery<pyr_spe_earning> transactionList = OBDal.getInstance().createQuery(
          pyr_spe_earning.class, whereClause, params);
      earninglisList = transactionList.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return earninglisList;
  }

  public static List<pyr_spe_deduction> deductionTransactionList(String paymentStatus,
      String componentSearchKey, Date paymentDate) {
    List<pyr_spe_deduction> deductionTransList = new ArrayList<pyr_spe_deduction>();
    try {
      String whereClause = "as spee where sped.employeeSalaryPayment.salaryPayment.documentStatus= ? "
          + "and sped.pYRDeduction.searchKey=? "
          + "and ? between sped.employeeSalaryPayment.salaryPayment.startingDate "
          + "and sped.employeeSalaryPayment.salaryPayment.endingDate";
      List<Object> params = new ArrayList<Object>();
      params.add(paymentStatus);
      params.add(componentSearchKey);
      params.add(paymentDate);
      OBQuery<pyr_spe_deduction> transactionList = OBDal.getInstance().createQuery(
          pyr_spe_deduction.class, whereClause, params);
      deductionTransList = transactionList.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return deductionTransList;
  }

  public static HRIS_C_Bp_Empinfo getActualContract(BusinessPartner employeePaid,
      Date salaryEndingDate) {
    HRIS_C_Bp_Empinfo contract = null;
    try {
      String whereClause = "as contract where contract.businessPartner=? and contract.validFromDate <= ? "
          + "and (contract.validToDate>=? or contract.validToDate is null)  order by contract.validFromDate desc";
      List<Object> params = new ArrayList<Object>();
      params.add(employeePaid);
      params.add(salaryEndingDate);
      params.add(salaryEndingDate);
      OBQuery<HRIS_C_Bp_Empinfo> contractQuery = OBDal.getInstance().createQuery(
          HRIS_C_Bp_Empinfo.class, whereClause, params);
      if (contractQuery.list().size() > 0) {
        contract = contractQuery.list().get(0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contract;
  }
}
