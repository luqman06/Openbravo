package org.wirabumi.hris.employee.master;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.wirabumi.hris.employee.master.data.EmployeePosition;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Department;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_education;
import org.wirabumi.hris.employee.master.data.hris_dicipline;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.employee.master.data.hris_site;

public class HrisDataHandler {
  static Logger log4j = Logger.getLogger(HrisDataHandler.class);

  public static void setHrisHashMapObject(BaseOBObject object, HashMap<String, Object> data) {
    for (Entry<String, Object> entry : data.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      object.set(key, value);
    }
  }

  public static void setEmployeeContract(HRIS_C_Bp_Empinfo object, HashMap<String, Object> data)
      throws Exception {
    for (Entry<String, Object> entry : data.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      if (key.equals("hrisCBpDepartment") && (value.equals("") || value.equals(null))) {
        OBQuery<HRIS_C_Bp_Department> Department = OBDal.getInstance().createQuery(
            HRIS_C_Bp_Department.class, "active=true and default=true");
        Department.setMaxResult(1);
        if (Department.list().size() == 0) {
          throw new Exception("@hris_no_departnment@");
        }
        value = Department.list().get(0);
      }
      object.set(key, value);
    }
  }

  public static BusinessPartner ceteateEsimployee(BusinessPartner businessPartner,
      String searchKey, String name, String birthPlace, Date birthDay, String sex, String religion,
      Country nationality, String maritalStatus, String taxStatus, String taxId, String bloodGroup,
      String email) {
    try {
      businessPartner.setSearchKey(searchKey);
      businessPartner.setName(name);
      businessPartner.setHrisBirthplace(birthPlace);
      businessPartner.setHrisBirthday(birthDay);
      businessPartner.setHrisSex(sex);
      businessPartner.setHrisReligion(religion);
      businessPartner.setHrisNationality(nationality);
      businessPartner.setHRISMaritalStatus(maritalStatus);
      // businessPartner.setPphTaxmaritalstatus(taxStatus);
      businessPartner.setTaxID(taxId);
      businessPartner.setHrisBloodgroup(bloodGroup);
      businessPartner.setHrisEmail(email);

      OBDal.getInstance().save(businessPartner);
    } catch (Exception e) {
      e.printStackTrace();
      log4j.error(e);
    }
    return businessPartner;
  }

  public static Location createLocation(Location location, String addresLine1, String addresLine2,
      String postalCode, String city, Country country, Region region) {
    try {
      location.setAddressLine1(addresLine1);
      location.setAddressLine2(addresLine2);
      location.setPostalCode(postalCode);
      location.setCityName(city);
      location.setCountry(country);
      location.setRegion(region);
      OBContext.setAdminMode();
      OBDal.getInstance().save(location);
      OBContext.restorePreviousMode();
    } catch (Exception e) {
      log4j.error(e);
      e.printStackTrace();
    }
    return location;
  }

  public static org.openbravo.model.common.businesspartner.Location setBusinesPartnerLocation(
      org.openbravo.model.common.businesspartner.Location businesPartnerLocation,
      BusinessPartner businessPartner, Location location, String phone, String alternatePhone,
      String name, String fax) {

    try {
      businesPartnerLocation.setBusinessPartner(businessPartner);
      businesPartnerLocation.setLocationAddress(location);
      businesPartnerLocation.setPhone(alternatePhone);
      businesPartnerLocation.setAlternativePhone(alternatePhone);
      businesPartnerLocation.setName(name);
      businesPartnerLocation.setFax(fax);
      OBDal.getInstance().save(businesPartnerLocation);
    } catch (Exception e) {
      log4j.error(e);
    }
    return businesPartnerLocation;
  }

  public static HRIS_C_Bp_education setEducation(HRIS_C_Bp_education education,
      BusinessPartner businessPartner, String grade, hris_dicipline dicipline, String institution,
      Date certificateDate, BigDecimal mark, String gradeNote, Boolean isGraduate) {

    try {
      education.setBusinessPartner(businessPartner);
      education.setLevel(grade);
      education.setDicipline(dicipline);
      education.setInstitutionName(institution);
      education.setValidFromDate(certificateDate);
      education.setGrade(mark);
      education.setGradeNote(gradeNote);
      education.setGraduate(isGraduate);
      OBDal.getInstance().save(education);
    } catch (Exception e) {
      log4j.error(e);
    }
    return education;
  }

  public static HRIS_C_Bp_Empinfo setCotractDetail(HRIS_C_Bp_Empinfo employementInfo,
      BusinessPartner bussinesPartner, Boolean currentPos, String employementType,
      String contractNo, Date validfrom, Date validto, HRIS_C_Bp_Department department,
      String echelon, String level, EmployeePosition position, hris_jobtitle jobtitle, Boolean isStaff,
      BusinessPartner payrollMaster, BusinessPartner reportTo, hris_site site, CostCenter costCenter) {
    try {
      if (department == null) {
        OBQuery<HRIS_C_Bp_Department> Department = OBDal.getInstance().createQuery(
            HRIS_C_Bp_Department.class, "active=true and default=true");
        Department.setMaxResult(1);
        if (Department.list().size() > 0) {
          department = Department.list().get(0);
        } else {
          throw new OBException("Department Mandatory Doesn't Exist");
        }
      }
      employementInfo.setHrisCBpDepartment(department);
      employementInfo.setBusinessPartner(bussinesPartner);
      employementInfo.setCurrentpos(currentPos);
      employementInfo.setEmployementtype(employementType);
      employementInfo.setContractno(contractNo);
      employementInfo.setValidFromDate(validfrom);
      employementInfo.setValidToDate(validto);
      employementInfo.setEchelon(echelon);
      employementInfo.setLevel(level);
      employementInfo.setPosition(position);
      employementInfo.setHrisJobtitle(jobtitle);
      employementInfo.setStaff(isStaff);
      employementInfo.setPYRPayrollMaster(payrollMaster);
      employementInfo.setReportTo(reportTo);
      employementInfo.setSite(site);
      employementInfo.setCostcenter(costCenter);
      OBDal.getInstance().save(employementInfo);
    } catch (Exception e) {
      log4j.error(e);
      throw new OBException(e.getLocalizedMessage());
    }
    return employementInfo;
  }

}
