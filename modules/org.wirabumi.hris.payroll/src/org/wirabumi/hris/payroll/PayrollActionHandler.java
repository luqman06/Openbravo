package org.wirabumi.hris.payroll;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.EmployeeSalaryCategory;
import org.openbravo.model.common.hcm.SalaryCategory;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.service.db.DbUtility;
import org.wirabumi.hris.employee.master.data.HRISDeductionTemplate;
import org.wirabumi.hris.employee.master.data.HRISEarningTemplate;

public class PayrollActionHandler extends BaseActionHandler {
  private Logger log4j = Logger.getLogger(this.getClass());
  ConnectionProvider connectionProvider = new DalConnectionProvider();
  VariablesSecureApp vars = RequestContext.get().getVariablesSecureApp();

  @Override
  protected JSONObject execute(Map<String, Object> parameters, String content) {
    JSONObject response = new JSONObject();
    try {
      final JSONObject jsonData = new JSONObject(content);
      final String action = jsonData.getString("action");
      final String adTabId = jsonData.getString("adTabId");
      final String windowId = jsonData.getString("windowId");
      final JSONArray RecordList = jsonData.getJSONArray("recordIdList");

      int createdData = 0;

      if (action.equals("OpenPopupParamater")) {
        response.put("posibleSalaryCategory", getSalaryCategory());
      } else {
        SalaryCategory salaryCategory = OBDal.getInstance().get(SalaryCategory.class, action);
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");// (vars.getSessionValue("#AD_JavaDateFormat"));
        Date startDate = null;
        final String startingDate = jsonData.getString("startingDate");
        try {
          startDate = formatDate.parse(startingDate);
        } catch (Exception e) {
          OBDal.getInstance().rollbackAndClose();
        }
        for (String busPartner : parseJSON(RecordList)) {
          OBContext.setAdminMode();
          BusinessPartner businessPartner = OBDal.getInstance().get(BusinessPartner.class,
              busPartner);
          EmployeeSalaryCategory generateSalary = createBusinessPartnerSalaryCategory(
              businessPartner, salaryCategory, startDate);
          // loop employee earning
          employeyeeEarning(salaryCategory, generateSalary, businessPartner);
          // loop employee deduction
          employeyeeDeduction(salaryCategory, generateSalary, businessPartner);
          createdData++;
        }
        OBDal.getInstance().commitAndClose();
        String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
            createdData + " @pyr_salarytemplatecreated@ ");
        JSONObject Message = new JSONObject();
        Message.put("severity", "success");
        Message.put("text", message);
        OBContext.restorePreviousMode();
        response.put("message", Message);
      }
    } catch (Exception e) {
      OBDal.getInstance().rollbackAndClose();
      log4j.error("Generate Salary Category Failed : " + e.getMessage(), e);
      Throwable ex = DbUtility.getUnderlyingSQLException(e);
      String message = OBMessageUtils.translateError(ex.getMessage()).getMessage();
      try {
        JSONObject errorMessage = new JSONObject();
        errorMessage.put("severity", "error");
        errorMessage.put("text", message);
        OBContext.setAdminMode();
        response.put("message", errorMessage);
      } catch (JSONException ignore) {
      }
    }
    return response;
  }

  protected List<String> parseJSON(JSONArray idJSON) throws JSONException {
    List<String> ids = new ArrayList<String>();
    for (int i = 0; i < idJSON.length(); i++) {
      ids.add(idJSON.getString(i));
    }
    return ids;
  }

  protected JSONObject getSalaryCategory() throws JSONException {
    JSONObject respons = new JSONObject();
    JSONObject salaryTemplateAvailable = new JSONObject();
    try {
      SalaryCategoryList[] salaryCategoryAvailable = SalaryCategoryList.SalaryCategory();
      for (SalaryCategoryList salaryListAvailable : salaryCategoryAvailable) {
        String salaryCategoryID = salaryListAvailable.getData("ID");
        String salaryCategoryName = salaryListAvailable.getData("NAME");
        salaryTemplateAvailable.put(salaryCategoryID, salaryCategoryName);
      }
    } catch (Exception e) {
      log4j.error("Error Getting Data From Salary Category \n" + e);
    }
    respons.put("salaryCategoryAvailable", salaryTemplateAvailable);
    return respons;
  }

  protected EmployeeSalaryCategory createBusinessPartnerSalaryCategory(
      BusinessPartner businessPartner, SalaryCategory salaryCategory, Date startingDate) {

    EmployeeSalaryCategory employeeSalaryCategory = OBProvider.getInstance().get(
        EmployeeSalaryCategory.class);
    employeeSalaryCategory.setBusinessPartner(businessPartner);
    employeeSalaryCategory.setSalaryCategory(salaryCategory);
    employeeSalaryCategory.setStartingDate(startingDate);
    OBDal.getInstance().save(employeeSalaryCategory);
    return employeeSalaryCategory;
  }

  protected void employeyeeEarning(SalaryCategory salaryCategory,
      EmployeeSalaryCategory employeeSalaryCategory, BusinessPartner businessPartner) {
    OBCriteria<HRISEarningTemplate> hrisEarningTemplate = OBDal.getInstance().createCriteria(
        HRISEarningTemplate.class);
    hrisEarningTemplate.add(Restrictions.eq(HRISEarningTemplate.PROPERTY_SALARYCATEGORY,
        salaryCategory));
    List<HRISEarningTemplate> EarningTemplate = hrisEarningTemplate.list();

    for (HRISEarningTemplate earningTemplate : EarningTemplate) {
      BigDecimal amount =earningTemplate.getAmount();
      pyr_earning deduction = earningTemplate.getPYREarning();

      pyr_bp_earning PyrEarning = OBProvider.getInstance().get(pyr_bp_earning.class);
      PyrEarning.setEarning(deduction);
      PyrEarning.setSalaryCategory(employeeSalaryCategory);
      PyrEarning.setAmount(amount);

      OBDal.getInstance().save(PyrEarning);
    }
  }

  protected void employeyeeDeduction(SalaryCategory salaryCategory,
      EmployeeSalaryCategory employeeSalaryCategory, BusinessPartner businessPartner) {
    OBCriteria<HRISDeductionTemplate> hrisDeductionTemplate = OBDal.getInstance().createCriteria(
        HRISDeductionTemplate.class);
    hrisDeductionTemplate.add(Restrictions.eq(HRISDeductionTemplate.PROPERTY_SALARYCATEGORY,
        salaryCategory));
    List<HRISDeductionTemplate> DeductionTemplate = hrisDeductionTemplate.list();

    for (HRISDeductionTemplate deductionTemplate : DeductionTemplate) {
      BigDecimal amount = deductionTemplate.getAmount();
      pyr_deduction deduction = deductionTemplate.getPYRDeduction();

      Pyr_Bp_Deduction PyrDeduction = OBProvider.getInstance().get(Pyr_Bp_Deduction.class);
      PyrDeduction.setPYRDeduction(deduction);
      PyrDeduction.setSalaryCategory(employeeSalaryCategory);
      PyrDeduction.setAmount(amount);

      OBDal.getInstance().save(PyrDeduction);
    }
  }
}
