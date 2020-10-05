package org.wirabumi.hris.employee.master.Actionbutton;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.HrisContractUpdate;
import org.wirabumi.hris.employee.master.HrisDataHandler;
import org.wirabumi.hris.employee.master.data.EmployeePosition;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Department;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;
import org.wirabumi.hris.employee.master.data.hris_employee_candidate;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;

public class PublishContract extends DalBaseProcess {
  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    try {
      final String batchId = (String) bundle.getParams().get("Hris_Employee_Candidate_ID");
      int processedData = 0;
      hris_employee_candidate BatchObject = OBDal.getInstance().get(hris_employee_candidate.class,
          batchId);

      OBCriteria<hris_ec_lines> Candidate = OBDal.getInstance().createCriteria(hris_ec_lines.class);
      Candidate.add(Restrictions.eq(hris_ec_lines.PROPERTY_HRISEMPLOYEECANDIDATE, BatchObject));
      Candidate.add(Restrictions.isNotNull(hris_ec_lines.PROPERTY_BUSINESSPARTNER));
      Candidate.add(Restrictions.isNotNull(hris_ec_lines.PROPERTY_CONTRACTNO));
      Candidate.add(Restrictions.isNotNull(hris_ec_lines.PROPERTY_EMPLOYEMENTTYPE));

      List<hris_ec_lines> CandidateList = Candidate.list();

      if (CandidateList.size() <= 0) {
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            CandidateList.size() + " @HRIS_DataMatch@");
        final OBError msg = new OBError();
        msg.setType("Info");
        msg.setTitle("Processed Data");
        msg.setMessage(message);
        bundle.setResult(msg);
      } else {

        for (hris_ec_lines linesCandidate : CandidateList) {
          // memastikan SK tidak terbit 2x, indikasinya: nomor SK dan employment type sama dengan apa yang sudah ada di hris_c_bp_empinfo
             //membuat object employee info
        	 OBCriteria<HRIS_C_Bp_Empinfo> employeeInfo = OBDal.getInstance().createCriteria(HRIS_C_Bp_Empinfo.class);
        	 employeeInfo.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_BUSINESSPARTNER, linesCandidate.getBusinessPartner()));
        	 employeeInfo.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_EMPLOYEMENTTYPE, linesCandidate.getEmployementtype()).ignoreCase());
        	 employeeInfo.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_CONTRACTNO, linesCandidate.getContractno()).ignoreCase());
        	 employeeInfo.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_VALIDFROMDATE, linesCandidate.getTMT()));
        	       
        	 List<HRIS_C_Bp_Empinfo> employeeInfoList = employeeInfo.list();
        	 if (employeeInfoList.size()>0){
        		 continue;
        	 }else{
        		 BusinessPartner businessPartner = linesCandidate.getBusinessPartner();
                 String contractNo = linesCandidate.getContractno();
                 Date validfrom = linesCandidate.getTMT();
                 EmployeePosition position = linesCandidate.getPosition();
                 String employeegrade = linesCandidate.getGrade();
                 String echelon = linesCandidate.getEchelon();
                 CostCenter costCenter = linesCandidate.getCostcenter();
                 hris_jobtitle jobtitle = linesCandidate.getJobTitle();
                 
                 String contractType = linesCandidate.getContractype();
                 String employementType = linesCandidate.getEmployementtype();

                 OBQuery<HRIS_C_Bp_Department> Department = OBDal.getInstance().createQuery(
                         HRIS_C_Bp_Department.class, "active=true and default=true");
                 Department.setMaxResult(1);
                 if (Department.list().size()==0) {
               	  throw new Exception("@hris_no_departnment@");
                 }
                 
                 HRIS_C_Bp_Empinfo employeContract = OBProvider.getInstance().get(
                         HRIS_C_Bp_Empinfo.class);
                 employeContract.setHrisContracttype(contractType);

                 HrisDataHandler.setCotractDetail(employeContract, businessPartner, false,
                     employementType, contractNo, validfrom, null, null, echelon, employeegrade,
                     position, jobtitle, null, null, null, null, costCenter);
                 HrisContractUpdate.setCurrentPosition(businessPartner.getId());

                  // check apakah tipe kepegawaian valid atau tidak sesuai preference
            		/* Window window = OBDal.getInstance().get(Window.class, "318A38AFC4A3420FAA6317F852BB5474");
                     OBCriteria<Preference> preference = OBDal.getInstance().createCriteria(Preference.class);
                     preference.add(Restrictions.eq(Preference.PROPERTY_WINDOW, window));
                     preference.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, employementType));
                     List<Preference> resultPreference = preference.list();
                     if (resultPreference.size() > 0) {
                         // ambil property dari preference berdasarkan status pegawai
                         Preference thePreference = resultPreference.get(0);
                         String Property = thePreference.getSearchKey();
                         linesCandidate.set(Property, employeContract);
                     }
                     processedData++;
                     OBDal.getInstance().save(linesCandidate);*/
        	 }
        	 

//            HRIS_C_Bp_Empinfo foreignKeySK = (HRIS_C_Bp_Empinfo) linesCandidate.get(Property);

//            if (foreignKeySK == null) {
              // jika column foreign key null maka update dan simpan contract
              // seting Contract number
              
              // bulog

              //  String kategoriPegawai = linesCandidate.getBLGEmployeeCategory();

              //membuat object department
                // set not current pos
//              HrisContractUpdate.setNotCurrentpos(businessPartner);
             //employeContract.setBlgEmployeecategory(kategoriPegawai);

        }
        OBDal.getInstance().commitAndClose();
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            processedData + " @Hris_DataCreated@");
        final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage(message);
        bundle.setResult(msg);
      }
    } catch (Exception e) {
    OBDal.getInstance().rollbackAndClose();
      String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
          " @Hris_DataFailed@");
      final OBError msg = new OBError();
      msg.setType("Error");
      msg.setTitle("Failed");
      msg.setMessage(message);
      bundle.setResult(msg);
    }
  }
}
