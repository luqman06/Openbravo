package org.wirabumi.hris.pph.Actionbutton;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.wirabumi.hris.employee.master.HrisDataHandler;
import org.wirabumi.hris.employee.master.Actionbutton.ImportEmployeeCandidates;
import org.wirabumi.hris.employee.master.data.hris_dicipline;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;
import org.wirabumi.hris.employee.master.data.hris_employee_candidate;
import org.wirabumi.hris.employee.master.data.hris_i_employee_candidate;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;

public class importEmpCandidatesPPh extends ImportEmployeeCandidates {
	protected OBError doImportEmployee(String strBatch) {
	    int processedData = 0;
	    try {
	      notifMessage = new StringBuilder();
	      
	      //create object employee candidate
	      hris_employee_candidate batchCandidate = OBDal.getInstance().get(
	          hris_employee_candidate.class, strBatch);

	      //create object import employee candidate yang belum diimport
	      OBCriteria<hris_i_employee_candidate> candidateList = OBDal.getInstance().createCriteria(
	          hris_i_employee_candidate.class);
	      candidateList.add(Restrictions.eq(hris_i_employee_candidate.PROPERTY_IMPORTED, false));
	      
	      //loop untuk memproses tiap record dalam employee candidate
	      List<hris_i_employee_candidate> candidates = candidateList.list();
	      for (hris_i_employee_candidate eCandidates : candidates) {
	        notifMessage.setLength(0);
	        isValidValue = true;

	        //mendapatkan value dari ref list dan ditranslate ke current language
	        String strSex = eCandidates.getStringSex();
	        isValidValue &= isValidList(eCandidates, language, "A0A8B37917904227BACCFDA90F0C7607",
	            strSex, "sex");
	        String strReligion = eCandidates.getStringReligion();
	        isValidValue &= isValidList(eCandidates, language, "F6855131D3F642018AA53153232227C0",
	            strReligion, "religion");
	        String strMaritalStatus = eCandidates.getStringMaritalstatus();
	        isValidValue &= isValidList(eCandidates, language, "A98FF6066A6A4D0AAA8DFB0BB387A500",
	            strMaritalStatus, "maritalStatus");
	        String strBloodGroup = eCandidates.getStringBlodtype();
	        isValidValue &= isValidList(eCandidates, language, "0F67106E65FC430A999873232DE42B29",
	            strBloodGroup, "bloodType");
	        String educationLevel = eCandidates.getStringEducationLevel();
	        isValidValue &= isValidList(eCandidates, language, "1199F86F2D324BA9952B7DAF9B353734",
	            educationLevel, "stringEducationLevel");
	        String position = eCandidates.getStringPosition();
	        isValidValue &= isValidList(eCandidates, language, "D470FB41D82446D282EA65AAE3840AA8",
	            position, "position");
	        String strGrade = eCandidates.getStringLevel();
	        isValidValue &= isValidList(eCandidates, language, "F24CFE85EB1940D5B6F39202DBAF6849",
	            strGrade, "grade");
	        String strEchelon = eCandidates.getStringEchelon();
	        isValidValue &= isValidList(eCandidates, language, "7FEDB3A24E1A426986F8E1D6DA68832C",
	            strEchelon, "echelon");
	        String strItitution = eCandidates.getInstitution();
	        isValidValue &= isValidList(eCandidates, language, "B2BF540AE8884A12AC5D86634E7E2AF2",
	            strItitution, "institution");
	        
	        //pph specific
	        String strTaxmaritalStatus = eCandidates.getStringTaxmaritalstatus();
	        isValidValue &= isValidList(eCandidates, language, "FA52B936A4F5480E904B9332FA3530B1",
	            strTaxmaritalStatus, "pPHTaxMaritalStatus");

	        //mendapatkan string untuk lookup FK
	        String strCountry = eCandidates.getStringCountry();
	        String strRegion = eCandidates.getStringRegion();
	        String strCostCenter = eCandidates.getStringCostcenter();
	        String strJobtitle = eCandidates.getStringJobTitle();
	        String strEducationDicipline = eCandidates.getStringDicipline();
	        String strNationality = eCandidates.getStringNationality();
	        
	        //membuat objek FK
	        Country nationality = getCoutry(strNationality);
	        hris_jobtitle jobtitle = getJobtitle(eCandidates.getPosition(), strJobtitle);
	        Country country = getCoutry(strCountry);
	        Region region = getRegion(country, strRegion);
	        CostCenter costcenter = getCostCenter(strCostCenter);
	        hris_dicipline educationDicipline = getMajor(strEducationDicipline);
	        if (isValidValue && nationality != null && jobtitle != null && country != null
	            && region != null && costcenter != null && educationDicipline != null) {
	          //update FK di import employee candidate
	          eCandidates.setNationality(nationality);
	          eCandidates.setEducationDicipline(educationDicipline);
	          eCandidates.setCountry(country);
	          eCandidates.setRegion(region);
	          eCandidates.setCostCenter(costcenter);
	          eCandidates.setJobTitle(jobtitle);

	          setEmployeeCandidateLineObject(eCandidates);
	          
	          //pph specific
	          data.put("pphTaxmaritalstatus", eCandidates.getPPHTaxMaritalStatus());
	          
	          //membuat object employee candidate line
	          hris_ec_lines employeeCandidate = OBProvider.getInstance().get(hris_ec_lines.class);
	          employeeCandidate.setHrisEmployeeCandidate(batchCandidate);
	          HrisDataHandler.setHrisHashMapObject(employeeCandidate, data);
	          OBDal.getInstance().save(employeeCandidate);
	          eCandidates.setImported(true);
	          eCandidates.setComments("");
	          OBDal.getInstance().save(eCandidates);
	          processedData++;
	        } else {
	          eCandidates.setComments(notifMessage.toString());
	          OBDal.getInstance().save(eCandidates);
	          continue;
	        }
	      }
	      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
	          " @HRIS_ImportSucces@ ");
	      final OBError msg = new OBError();
	      msg.setType("Success");
	      msg.setTitle("Import Succes");
	      msg.setMessage(processedData + " " + message);
	      // bundle.setResult(msg);
	      OBDal.getInstance().commitAndClose();
	      data.clear();
	      return msg;

	    } catch (Exception e) {
	      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
	          e.toString());
	      final OBError msg = new OBError();
	      msg.setType("Error");
	      msg.setTitle("Exception");
	      msg.setMessage(processedData + " " + message);
	      // bundle.setResult(msg);
	      log4j.error(e);
	      e.printStackTrace();
	      OBDal.getInstance().rollbackAndClose();
	      return msg;
	    }
	  }
}
