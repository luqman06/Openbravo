package org.wirabumi.hris.employee.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Department;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.employee.master.data.hris_site;

public class HrisContractUpdate {
  private static Logger log4j = Logger.getLogger(HrisContractUpdate.class);

  public static BusinessPartner setCotractDetail(BusinessPartner bussinesPartner,
      String employementType, String contractNo, Date validfrom, Date validto,
      HRIS_C_Bp_Department department, String echelon, String level, String position,
      hris_jobtitle jobtitle, Boolean isPayroll, BusinessPartner payrollMaster,
      BusinessPartner reportTo, hris_site site, CostCenter costCenter, String contractType,
      boolean isSalesRep) {
    try {
      bussinesPartner.setHRISEmployementType(employementType);
      bussinesPartner.setHRISContractNo(contractNo);
      bussinesPartner.setHrisValidfrom(validfrom);
      bussinesPartner.setHRISValidTo(validto);
      bussinesPartner.setHrisCBpDepartment(department);
      bussinesPartner.setHrisEchelon(echelon);
      bussinesPartner.setHRISLevel(level);
      bussinesPartner.setHRISPosition(position);
      bussinesPartner.setHrisJobtitle(jobtitle);
      bussinesPartner.setPyrIspayrollmaster(isPayroll);
      bussinesPartner.setPyrPayrollmaster(payrollMaster);
      bussinesPartner.setHrisReportTo(reportTo);
      bussinesPartner.setHrisSite(site);
      bussinesPartner.setHrisCostcenter(costCenter);
      bussinesPartner.setHrisContracttype(contractType);
      bussinesPartner.setSalesRepresentative(isSalesRep);
      OBDal.getInstance().save(bussinesPartner);
    } catch (Exception e) {
      log4j.error(e);
    }
    return bussinesPartner;
  }

  public static void setNotCurrentpos(BusinessPartner businessPartner) {

    OBCriteria<HRIS_C_Bp_Empinfo> curentContract = OBDal.getInstance().createCriteria(
        HRIS_C_Bp_Empinfo.class);
    curentContract
        .add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_BUSINESSPARTNER, businessPartner));
    curentContract.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_ISCURRENTPOS, true));
    List<HRIS_C_Bp_Empinfo> resultContract = curentContract.list();
    if (resultContract.size() > 0) {
      HRIS_C_Bp_Empinfo contracInfo = resultContract.get(0);
      contracInfo.setCurrentpos(false);
    }
  }

  public static void setNotCurrentpos(BusinessPartner businessPartner, Date validtoDate) {

    OBCriteria<HRIS_C_Bp_Empinfo> curentContract = OBDal.getInstance().createCriteria(
        HRIS_C_Bp_Empinfo.class);
    curentContract
        .add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_BUSINESSPARTNER, businessPartner));
    curentContract.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_ISCURRENTPOS, true));
    List<HRIS_C_Bp_Empinfo> resultContract = curentContract.list();
    if (resultContract.size() > 0) {
      HRIS_C_Bp_Empinfo contracInfo = resultContract.get(0);
      contracInfo.setValidToDate(validtoDate);
      contracInfo.setCurrentpos(false);
    }
  }

  public static void setCurrentPosition(String businessPartner) {
    String whereClause = "businessPartner.id='" + businessPartner
        + "'and validFromDate<=now() and active=true " + "order by validFromDate DESC " + "limit 1";
    OBQuery<HRIS_C_Bp_Empinfo> employeeInfo = OBDal.getInstance().createQuery(
        HRIS_C_Bp_Empinfo.class, whereClause);
    if (employeeInfo.list().size() > 0) {
      HRIS_C_Bp_Empinfo currentEmployeeInfo = employeeInfo.list().get(0);
      currentEmployeeInfo.setCurrentpos(true);
    }
  }

  public static void setCurrentPosition(String businessPartner, Date executionDate) {
    String whereClause = "businessPartner.id=? and validFromDate<= ? and active=true "
        + "order by validFromDate DESC " + "limit 1";
    List<Object> params = new ArrayList<Object>();
    params.add(businessPartner);
    params.add(executionDate);
    OBQuery<HRIS_C_Bp_Empinfo> employeeInfo = OBDal.getInstance().createQuery(
        HRIS_C_Bp_Empinfo.class, whereClause);
    employeeInfo.setParameters(params);
    if (employeeInfo.list().size() > 0) {
      HRIS_C_Bp_Empinfo currentEmployeeInfo = employeeInfo.list().get(0);
      currentEmployeeInfo.setCurrentpos(true);
    }
  }
}
