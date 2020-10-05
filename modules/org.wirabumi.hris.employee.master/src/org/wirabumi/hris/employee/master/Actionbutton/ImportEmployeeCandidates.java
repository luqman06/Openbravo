package org.wirabumi.hris.employee.master.Actionbutton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.employee.master.HrisDataHandler;
import org.wirabumi.hris.employee.master.data.hris_dicipline;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;
import org.wirabumi.hris.employee.master.data.hris_employee_candidate;
import org.wirabumi.hris.employee.master.data.hris_i_employee_candidate;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;

public class ImportEmployeeCandidates extends DalBaseProcess {
  protected static Logger log4j = Logger.getLogger(ImportEmployeeCandidates.class);
  protected HashMap<String, Object> data = new HashMap<String, Object>();
  protected StringBuilder notifMessage;
  protected VariablesSecureApp vars = RequestContext.get().getVariablesSecureApp();
  protected DateFormat formatDate = new SimpleDateFormat(vars.getSessionValue("#AD_JavaDateFormat"));
  protected ConnectionProvider connectionProvider = new DalConnectionProvider();
  protected String language = vars.getLanguage();
  protected boolean isValidValue;

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    String strBatch = (String) bundle.getParams().get("hrisEmployeeCandidateId");
    bundle.setResult(doImportEmployee(strBatch));
  }

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

  // geter void
  protected void setEmployeeCandidateLineObject(hris_i_employee_candidate object) {
    data.put("searchKey", object.get("searchKey"));
    data.put("commercialName", object.get("employeeName"));
    data.put("birthplace", object.get("birthPlace"));
    data.put("tanggalLahir", object.get("birthday"));
    data.put("sex", object.get("sex"));
    data.put("religion", object.get("religion"));
    data.put("nationality", object.get("nationality"));
    data.put("maritalstatus", object.get("maritalStatus"));
    data.put("taxtid", object.get("taxID"));
    data.put("bloodtype", object.get("bloodType"));
    data.put("email", object.get("email"));
    data.put("joindate", object.get("joindate"));
    data.put("description", object.get("description"));
    data.put("addressLine1", object.get("addressLine1"));
    data.put("addressLine2", object.get("addressLine2"));
    data.put("postalCode", object.get("postalCode"));
    data.put("cityName", object.get("cityName"));
    data.put("country", object.get("country"));
    data.put("region", object.get("region"));
    data.put("phone", object.get("phone"));
    data.put("alternativePhone", object.get("alternativePhone"));
    data.put("educationLevel", object.get("stringEducationLevel"));
    data.put("hrisDicipline", object.get("educationDicipline"));
    data.put("institutionName", object.get("instiutionName"));
    data.put("ijazah", object.get("certificateNumber"));
    data.put("dateIjazah", object.get("certificateDate"));
    data.put("nilai", object.get("nilai"));
    data.put("contractype", object.get("contractType"));
    data.put("position", object.get("position"));
    data.put("costcenter", object.get("costCenter"));
    data.put("jobTitle", object.get("jobTitle"));
    data.put("grade", object.get("grade"));
    data.put("echelon", object.get("echelon"));
    data.put("bankName", object.get("bankName"));
    data.put("genericAccountNo", object.get("genericAccountNo"));
    data.put("hrisIdcardNo", object.get("hrisIdcardNo"));
    data.put("hrisTitleName", object.get("hrisTitleName"));
  }

  protected boolean isValidList(BaseOBObject object, String language, String Reference,
      String Value, String Property) {
    boolean validlist = true;
    try {
      String validData = valueList(language, Reference, Value);
      if (!validData.equals("")) {
        object.set(Property, validData);
        validlist = true;
      } else {
        validlist = false;
        log4j.error(Value + "is" + validlist);
      }
    } catch (Exception e) {
      log4j.error(e);
    }
    return validlist;
  }

  private String valueList(String language, String referenceId, String key) {
    String value = "";
    String lowKey = key.toLowerCase();
    try {
      final String strQuery = "select refList.searchKey  from ADListTrl listTrl"
          + " left outer join listTrl.listReference refList"
          + " inner join refList.reference reference" + " inner join listTrl.language languageTrl "
          + " where reference.id=?"
          + " and ((lower(listTrl.name)=? and languageTrl.language=?) or lower(refList.name)=?)"
          + " and refList.active=true";

      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, referenceId);
      query.setParameter(1, lowKey);
      query.setParameter(2, language);
      query.setParameter(3, lowKey);
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      if (result.next()) {
        value = (String) result.get()[0];
      } else {
        notifMessage.append("- " + key + " Tidak Terdaftar" + "\n");
      }
    } catch (Exception e) {
      log4j.error(e);
    }
    return value;
  }

  protected Country getCoutry(String Name) throws Exception {
    Country instCountry = null;

    try {
      OBCriteria<Country> theCountry = OBDal.getInstance().createCriteria(Country.class);
      theCountry.add(Restrictions.eq(Country.PROPERTY_NAME, Name).ignoreCase());
      List<Country> countrey = theCountry.list();
      instCountry = countrey.get(0);
    } catch (Exception e) {
      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
          " @Hris_CountryNA@ ");
      notifMessage.append("- " + Name);
      notifMessage.append(message + "\n");
      // throw new Exception(Name + " " + message);
    }

    return instCountry;
  }

  protected Region getRegion(Country country, String strRegion) throws Exception {
    Region theRegion = null;
    try {
      OBCriteria<Region> myReg = OBDal.getInstance().createCriteria(Region.class);
      myReg.add(Restrictions.eq(Region.PROPERTY_COUNTRY, country));
      myReg.add(Restrictions.eq(Region.PROPERTY_NAME, strRegion).ignoreCase());
      List<Region> reg = myReg.list();
      theRegion = reg.get(0);
    } catch (Exception e) {
      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
          " @HRIS_RegionNA@ ");
      notifMessage.append("- " + strRegion);
      notifMessage.append(message + "\n");
      // throw new Exception(strRegion + " " + message + " " + country.getName());
    }
    return theRegion;
  }

  protected hris_jobtitle getJobtitle(String strPosition, String strJobtitle) throws Exception {
    hris_jobtitle theJobtitle = null;
    try {
      OBCriteria<hris_jobtitle> myJob = OBDal.getInstance().createCriteria(hris_jobtitle.class);
      myJob.add(Restrictions.eq(hris_jobtitle.PROPERTY_POSITION, strPosition).ignoreCase());
      myJob.add(Restrictions.eq(hris_jobtitle.PROPERTY_NAME, strJobtitle).ignoreCase());
      List<hris_jobtitle> Jobs = myJob.list();
      theJobtitle = Jobs.get(0);
    } catch (Exception e) {
      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
          " @Hris_JobNotFound@ ");
      notifMessage.append("- " + strJobtitle);
      notifMessage.append(message + "\n");
      // throw new Exception(strJobtitle + " " + message + " " + strPosition);
    }
    return theJobtitle;
  }

  protected CostCenter getCostCenter(String strCostCenter) throws Exception {
    CostCenter theCostCenter = null;
    try {
      OBCriteria<CostCenter> myCost = OBDal.getInstance().createCriteria(CostCenter.class);
      myCost.add(Restrictions.eq(CostCenter.PROPERTY_NAME, strCostCenter).ignoreCase());
      List<CostCenter> Cs = myCost.list();
      theCostCenter = Cs.get(0);
    } catch (Exception e) {
      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
          " @HRIS_CostCenterNotFound@ ");
      notifMessage.append("- " + strCostCenter);
      notifMessage.append(message + "\n");
      // throw new Exception(strCostCenter + " " + message);
    }
    return theCostCenter;
  }

  protected hris_dicipline getMajor(String strMajor) throws Exception {
    hris_dicipline theMajor = null;
    try {
      OBCriteria<hris_dicipline> myDisc = OBDal.getInstance().createCriteria(hris_dicipline.class);
      myDisc.add(Restrictions.eq(hris_dicipline.PROPERTY_NAME, strMajor).ignoreCase());
      List<hris_dicipline> Dic = myDisc.list();
      theMajor = Dic.get(0);
    } catch (Exception e) {
      String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
          " @HRIS_DiciplineNotFound@ ");
      notifMessage.append("- " + strMajor);
      notifMessage.append(message + "\n");
      // throw new Exception(strMajor + ": in Major Not Found " + e.getMessage());
    }
    return theMajor;
  }
}
