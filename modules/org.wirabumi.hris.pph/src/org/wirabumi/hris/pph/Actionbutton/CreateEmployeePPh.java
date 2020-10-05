package org.wirabumi.hris.pph.Actionbutton;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BankAccount;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.geography.Location;
import org.wirabumi.hris.employee.master.HrisContractUpdate;
import org.wirabumi.hris.employee.master.HrisDataHandler;
import org.wirabumi.hris.employee.master.Actionbutton.CreateEmployee;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_education;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;
import org.wirabumi.hris.employee.master.data.hris_employee_candidate;

public class CreateEmployeePPh extends CreateEmployee {
  Logger log4crtEmplPPh = Logger.getLogger(this.getClass());

  protected OBError doCreateEmployee(ConnectionProvider conectionProvider, VariablesSecureApp vars,
      String batchId) {
    final OBError msg = new OBError();
    try {

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
          dataEmployee.put("pphTaxmaritalstatus", linesCandidate.getPphTaxmaritalstatus());
          BusinessPartner businessPartner = OBProvider.getInstance().get(BusinessPartner.class);
          HrisDataHandler.setHrisHashMapObject(businessPartner, dataEmployee);
          businessPartner.setHRISEmployeeWindow(true);
          OBDal.getInstance().save(businessPartner);

          getLocation(linesCandidate);
          Location location = OBProvider.getInstance().get(Location.class);
          HrisDataHandler.setHrisHashMapObject(location, dataLocation);
          OBContext.setAdminMode();
          OBDal.getInstance().save(location);
          OBContext.restorePreviousMode();

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
          contract.setCurrentpos(true);
          HrisDataHandler.setEmployeeContract(contract, dataContract);
          OBDal.getInstance().save(contract);

          boolean a = true;
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
      return msg;
    } catch (Exception e) {
      OBDal.getInstance().rollbackAndClose();
      log4crtEmplPPh.error(e);
      String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
          " @Hris_DataFailed@");
      msg.setType("Error");
      msg.setTitle("Failed");
      msg.setMessage(message);
    }
    return msg;
  }
}
