package org.wirabumi.hris.employee.master.ad_callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.wirabumi.hris.employee.master.data.hris_dicipline;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;

public class EducationPermit extends SimpleCallout {

  private static final long serialVersionUID = 1L;

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    String lastChangedColumn = info.getLastFieldChanged();
    if (lastChangedColumn.equalsIgnoreCase("inpcBpartnerId")) {
      String businessPartner = info.getStringParameter("inpcBpartnerId", null);
      BusinessPartner partner = OBDal.getInstance().get(BusinessPartner.class, businessPartner);

      String searchKey = partner.getSearchKey();
      if (searchKey != null) {
        info.addResult("inpvalue", searchKey);
      } else {
        info.addResult("inpvalue", "");
      }

      String position = partner.getHRISPosition();
      if (position != null) {
        info.addResult("inpposition", position);
      } else {
        info.addResult("inpposition", "");
      }

      CostCenter costCenter = partner.getHrisCostcenter();
      if (costCenter != null) {
        String strCostCenter = costCenter.getId();
        info.addResult("inpmaCostcenterId", strCostCenter);
      } else {
        info.addResult("inpmaCostcenterId", "");
      }
      hris_jobtitle jobtitle = partner.getHrisJobtitle();
      if (jobtitle != null) {
        String strJobtitle = jobtitle.getId();
        info.addResult("inphrisJobtitleId", strJobtitle);
      } else {
        info.addResult("inphrisJobtitleId", "");
      }
      String employeeGrade = partner.getHRISLevel();
      if (employeeGrade != null) {
        info.addResult("inpgrade", employeeGrade);
      } else {
        info.addResult("inpgrade", "");
      }

      String echelon = partner.getHrisEchelon();
      if (echelon != null) {
        info.addResult("inpechelon", echelon);
      } else {
        info.addResult("inpechelon", "");
      }
      String EmployementType = partner.getHRISEmployementType();
      if (EmployementType != null) {
        info.addResult("inpemploymenttype", EmployementType);
      } else {
        info.addResult("inpemploymenttype", "");
      }
    } else if (lastChangedColumn.equalsIgnoreCase("inphrisDiciplineId")) {
      String diciplineId = info.getStringParameter("inphrisDiciplineId", null);
      hris_dicipline dicipline = OBDal.getInstance().get(hris_dicipline.class, diciplineId);

      String institution = dicipline.getInstitutionname();
      String educationLevel = dicipline.getEducationLevel();
      String faculty = dicipline.getFaculty();
      if (institution != null) {
        info.addResult("inpinstitutionname", institution);
      } else {
        info.addResult("inpinstitutionname", "");
      }

      if (educationLevel != null) {
        info.addResult("inpeducationlevel", educationLevel);
      } else {
        info.addResult("inpeducationlevel", "");
      }

      if (faculty != null) {
        info.addResult("inpfaculty", faculty);
      } else {
        info.addResult("inpfaculty", "");
      }
    }
  }

}
