package org.wirabumi.hris.employee.master.Actionbutton;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BankAccount;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.geography.Location;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.HrisContractUpdate;
import org.wirabumi.hris.employee.master.HrisDataHandler;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_education;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;
import org.wirabumi.hris.employee.master.data.hris_employee_candidate;

public class CreateEmployee extends DalBaseProcess {
  Logger log4createEmployee = Logger.getLogger(this.getClass());

  protected HashMap<String, Object> dataEmployee = new HashMap<String, Object>();
  protected HashMap<String, Object> dataLocation = new HashMap<String, Object>();
  protected HashMap<String, Object> dataBpLocation = new HashMap<String, Object>();
  protected HashMap<String, Object> dataEducation = new HashMap<String, Object>();
  protected HashMap<String, Object> dataContract = new HashMap<String, Object>();
  protected HashMap<String, Object> dataBank = new HashMap<String, Object>();

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    final String batchId = (String) bundle.getParams().get("Hris_Employee_Candidate_ID");
    bundle.setResult(doCreateEmployee(conectionProvider, vars, batchId));
  }

  protected OBError doCreateEmployee(ConnectionProvider conectionProvider, VariablesSecureApp vars,
      String batchId) {
    final OBError msg = new OBError();
    try {
    	 OBContext.setAdminMode();
      hris_employee_candidate BatchObject = OBDal.getInstance().get(hris_employee_candidate.class,
          batchId);

      OBCriteria<hris_ec_lines> Candidate = OBDal.getInstance().createCriteria(hris_ec_lines.class);
      Candidate.add(Restrictions.eq(hris_ec_lines.PROPERTY_HRISEMPLOYEECANDIDATE, BatchObject));
      Candidate.add(Restrictions.isNull(hris_ec_lines.PROPERTY_BUSINESSPARTNER));
      Candidate.add(Restrictions.isNotNull(hris_ec_lines.PROPERTY_CONTRACTNO));
      Candidate.add(Restrictions.isNotNull(hris_ec_lines.PROPERTY_EMPLOYEMENTTYPE));

      List<hris_ec_lines> CandidateList = Candidate.list();

      if (CandidateList.size() <= 0) {
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            CandidateList.size() + " @HRIS_DataMatch@");
        msg.setType("Info");
        msg.setTitle("Processed Data");
        msg.setMessage(message);
        return msg;
      } else {
        for (hris_ec_lines linesCandidate : CandidateList) {
          getEmployee(linesCandidate);
          BusinessPartner businessPartner = OBProvider.getInstance().get(BusinessPartner.class);
          HrisDataHandler.setHrisHashMapObject(businessPartner, dataEmployee);
          businessPartner.setHRISEmployeeWindow(true);
          OBDal.getInstance().save(businessPartner);

          getLocation(linesCandidate);
          Location location = OBProvider.getInstance().get(Location.class);
          HrisDataHandler.setHrisHashMapObject(location, dataLocation);
         
          OBDal.getInstance().save(location);

          getBusinessParnetLocation(linesCandidate, businessPartner, location);
          org.openbravo.model.common.businesspartner.Location bpLocation = OBProvider.getInstance()
              .get(org.openbravo.model.common.businesspartner.Location.class);
          HrisDataHandler.setHrisHashMapObject(bpLocation, dataBpLocation);
          OBDal.getInstance().save(bpLocation);

          getcEducation(linesCandidate, businessPartner);
          HRIS_C_Bp_education education = OBProvider.getInstance().get(HRIS_C_Bp_education.class);
          HrisDataHandler.setHrisHashMapObject(education, dataEducation);
          OBDal.getInstance().save(education);

          HrisContractUpdate.setNotCurrentpos(businessPartner);

          getContract(linesCandidate, businessPartner);
          HRIS_C_Bp_Empinfo contract = OBProvider.getInstance().get(HRIS_C_Bp_Empinfo.class);
          HrisDataHandler.setEmployeeContract(contract, dataContract);
          OBDal.getInstance().save(contract);
          HrisContractUpdate.setCurrentPosition(businessPartner.getId());

          getBank(linesCandidate, businessPartner);
          if (dataBank.size() > 0) {
            BankAccount bankAccount = OBProvider.getInstance().get(BankAccount.class);
            HrisDataHandler.setHrisHashMapObject(bankAccount, dataBank);
            OBDal.getInstance().save(bankAccount);
          }

          linesCandidate.setBusinessPartner(businessPartner);
          // linesCandidate.setBlgSkCapeg(employeContract);
          OBDal.getInstance().save(linesCandidate);
          // clearing data
          dataEmployee.clear();
          dataLocation.clear();
          dataBpLocation.clear();
          dataEducation.clear();
          dataBank.clear();
          dataContract.clear();
        }
      }
      OBDal.getInstance().commitAndClose();
      String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
          CandidateList.size() + " @Hris_DataCreated@");
      msg.setType("Success");
      msg.setTitle("Done");
      msg.setMessage(message);

      OBContext.restorePreviousMode();
      return msg;
    } catch (Exception e) {
      OBDal.getInstance().rollbackAndClose();
      log4createEmployee.error(e);
      String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
          " @Hris_DataFailed@");
      msg.setType("Error");
      msg.setTitle("Failed");
      msg.setMessage(message);
    }
    return msg;
  }

  protected void getEmployee(BaseOBObject object) {
    try {
      dataEmployee.put("searchKey", object.get("searchKey"));
      dataEmployee.put("name", object.get("commercialName"));
      dataEmployee.put("hrisBirthplace", object.get("birthplace"));
      dataEmployee.put("hrisBirthday", object.get("tanggalLahir"));
      dataEmployee.put("hrisSex", object.get("sex"));
      dataEmployee.put("hrisReligion", object.get("religion"));
      dataEmployee.put("hrisNationality", object.get("nationality"));
      dataEmployee.put("hRISMaritalStatus", object.get("maritalstatus"));
      dataEmployee.put("taxID", object.get("taxtid"));
      dataEmployee.put("hrisBloodgroup", object.get("bloodtype"));
      dataEmployee.put("hrisEmail", object.get("email"));
      dataEmployee.put("hrisJoindate", object.get("joindate"));
      dataEmployee.put("hrisDescription", object.get("description"));
      dataEmployee.put("hrisIdcardNo", object.get("hrisIdcardNo"));
      dataEmployee.put("hrisTitleName", object.get("hrisTitleName"));
    } catch (Exception e) {
      log4createEmployee.error(e);
    }
  }

  protected void getLocation(BaseOBObject object) {
    try {
      dataLocation.put("addressLine1", object.get("addressLine1"));
      dataLocation.put("addressLine2", object.get("addressLine2"));
      dataLocation.put("cityName", object.get("cityName"));
      dataLocation.put("postalCode", object.get("postalCode"));
      dataLocation.put("country", object.get("country"));
      dataLocation.put("region", object.get("region"));
    } catch (Exception e) {
      log4createEmployee.error(e);
    }

  }

  protected void getBusinessParnetLocation(BaseOBObject object, BusinessPartner businessPartner,
      Location location) {
    try {
      dataBpLocation.put("name",
          object.get("commercialName").toString().concat(object.get("addressLine1").toString()));
      dataBpLocation.put("businessPartner", businessPartner);
      dataBpLocation.put("locationAddress", location);
      dataBpLocation.put("phone", object.get("phone"));
      dataBpLocation.put("alternativePhone", object.get("alternativePhone"));
    } catch (Exception e) {
      log4createEmployee.error(e);
    }
  }

  protected void getcEducation(BaseOBObject object, BusinessPartner businessPartner) {
    try {
      dataEducation.put("businessPartner", businessPartner);
      dataEducation.put("level", object.get("educationLevel"));
      dataEducation.put("dicipline", object.get("hrisDicipline"));
      dataEducation.put("institutionName", object.get("institutionName"));
      dataEducation.put("certificateNumber", object.get("ijazah"));
      dataEducation.put("validFromDate", object.get("dateIjazah"));
      dataEducation.put("grade", object.get("nilai"));
    } catch (Exception e) {
      log4createEmployee.error(e);
    }
  }

  protected void getContract(BaseOBObject object, BusinessPartner businessPartner) {
    try {
      dataContract.put("businessPartner", businessPartner);
      dataContract.put("hrisCBpDepartment", "");// mandatory paramater
      dataContract.put("contractno", object.get("contractno"));
      dataContract.put("validFromDate", object.get("tMT"));
      dataContract.put("position", object.get("position"));
      dataContract.put("level", object.get("grade"));
      dataContract.put("echelon", object.get("echelon"));
      dataContract.put("costcenter", object.get("costcenter"));
      dataContract.put("hrisJobtitle", object.get("jobTitle"));
      dataContract.put("employementtype", object.get("employementtype"));
      dataContract.put("hrisContracttype", object.get("contractype"));
    } catch (Exception e) {
      log4createEmployee.error(e);
    }
  }

  protected void getBank(BaseOBObject object, BusinessPartner businessPartner) {
    try {

      Object a = object.get("bankName");
      Object b = object.get("genericAccountNo");
      Object c = object.get("name");
      if (!(object.get("bankName") == null) && !(object.get("genericAccountNo") == null)
          && !(object.get("name") == null)) {
        dataBank.put("bankName", object.get("bankName"));
        dataBank.put("accountNo", object.get("genericAccountNo"));
        dataBank.put("name", object.get("name"));
        dataBank.put("businessPartner", businessPartner);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
