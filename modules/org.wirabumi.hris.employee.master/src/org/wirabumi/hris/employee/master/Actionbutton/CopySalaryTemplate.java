package org.wirabumi.hris.employee.master.Actionbutton;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.EmployeeSalaryCategory;
import org.openbravo.model.common.hcm.SalaryCategory;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.HRISDeductionTemplate;
import org.wirabumi.hris.employee.master.data.HRISEarningTemplate;
import org.wirabumi.hris.payroll.Pyr_Bp_Deduction;
import org.wirabumi.hris.payroll.pyr_bp_earning;
import org.wirabumi.hris.payroll.pyr_deduction;
import org.wirabumi.hris.payroll.pyr_earning;

public class CopySalaryTemplate extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    // TODO Auto-generated method stub
    final Date date = new Date();
    try {
      String C_BPartner_ID = (String) bundle.getParams().get("C_BPartner_ID");
      BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, C_BPartner_ID);

      String cSalaryCategoryId = (String) bundle.getParams().get("cSalaryCategoryId");
      SalaryCategory scID = OBDal.getInstance().get(SalaryCategory.class, cSalaryCategoryId);

      // OBCriteria<SalaryCategory> salcategory = OBDal.getInstance().createCriteria(
      // SalaryCategory.class);
      //
      // List<SalaryCategory> salarycategory = salcategory.list();

      // save bussines partner salary category------------
      EmployeeSalaryCategory employeeSalaryCategory = OBProvider.getInstance().get(
          EmployeeSalaryCategory.class);
      employeeSalaryCategory.setBusinessPartner(bp);
      employeeSalaryCategory.setSalaryCategory(scID);
      employeeSalaryCategory.setStartingDate(date);

      OBDal.getInstance().save(employeeSalaryCategory);

      // get earning by salary category
      OBCriteria<HRISEarningTemplate> hrisEarningTemplate = OBDal.getInstance().createCriteria(
          HRISEarningTemplate.class);
      hrisEarningTemplate.add(Restrictions.eq(HRISEarningTemplate.PROPERTY_SALARYCATEGORY, scID));
      List<HRISEarningTemplate> EarningTemplate = hrisEarningTemplate.list();

      for (HRISEarningTemplate HrisET : EarningTemplate) {
        BigDecimal amount = HrisET.getAmount();
        pyr_earning pyrEarningId = HrisET.getPYREarning();

        pyr_bp_earning PyrEarning = OBProvider.getInstance().get(pyr_bp_earning.class);
        PyrEarning.setEarning(pyrEarningId);
        PyrEarning.setSalaryCategory(employeeSalaryCategory);
        PyrEarning.setAmount(amount);

        OBDal.getInstance().save(PyrEarning);
      }

      // get duduction by salary category

      OBCriteria<HRISDeductionTemplate> hrisDeductionTemplate = OBDal.getInstance().createCriteria(
          HRISDeductionTemplate.class);
      hrisDeductionTemplate.add(Restrictions
          .eq(HRISDeductionTemplate.PROPERTY_SALARYCATEGORY, scID));
      List<HRISDeductionTemplate> DeductionTemplate = hrisDeductionTemplate.list();

      for (HRISDeductionTemplate HrisDT : DeductionTemplate) {
        BigDecimal amount = HrisDT.getAmount();
        pyr_deduction pyrDeductionId = HrisDT.getPYRDeduction();

        Pyr_Bp_Deduction PyrDeduction = OBProvider.getInstance().get(Pyr_Bp_Deduction.class);
        PyrDeduction.setPYRDeduction(pyrDeductionId);
        PyrDeduction.setSalaryCategory(employeeSalaryCategory);
        PyrDeduction.setAmount(amount);

        OBDal.getInstance().save(PyrDeduction);
      }

      OBDal.getInstance().commitAndClose();
    } catch (Exception e) {

    }
  }
}
