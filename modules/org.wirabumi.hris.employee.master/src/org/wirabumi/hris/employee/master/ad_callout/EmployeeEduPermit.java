package org.wirabumi.hris.employee.master.ad_callout;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_education;
import org.wirabumi.hris.employee.master.data.hris_dicipline;

public class EmployeeEduPermit extends SimpleCallout {

  private static final long serialVersionUID = 1L;

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    String lastColumnChange = info.getLastFieldChanged();
    if (lastColumnChange.equalsIgnoreCase("inpdiciplineletter")) {
      String educationPermit = info.getStringParameter("inpdiciplineletter", null);
      final hris_dicipline hd = OBDal.getInstance().get(hris_dicipline.class, educationPermit);

      String institusi = hd.getInstitutionname();
      info.addResult("inpinstitutionletter", institusi);

      String akreditasi = hd.getAccreditation();
      info.addResult("inphrisAccreditation", akreditasi);
    } else if (lastColumnChange.equalsIgnoreCase("inphrisDiciplineId")) {
      String educationPermit = info.getStringParameter("inphrisDiciplineId", null);
      final hris_dicipline hd = OBDal.getInstance().get(hris_dicipline.class, educationPermit);

      String institusi = hd.getInstitutionname();
      info.addResult("inpinstitutionname", institusi);
    } else if (lastColumnChange.equalsIgnoreCase("inpcBpartnerId")) {
      String businessPartner = info.getStringParameter("inpcBpartnerId", null);
      if (businessPartner == null) {
        return;
      }
      final BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, businessPartner);
      String value = bp.getSearchKey();
      if (value != null) {
        info.addResult("inpvalue", value);
      } else {
        info.addResult("inpvalue", "");
      }

      String position = bp.getHRISPosition();
      if (position != null) {
        info.addResult("inpposition", position);
      } else {
        info.addResult("inpposition", "");
      }
      String echelon = bp.getHrisEchelon();
      if (echelon != null) {
        info.addResult("inpechelon", echelon);
      } else {
        info.addResult("inpechelon", "");
      }
      String grade = bp.getHRISLevel();
      if (grade != null) {
        info.addResult("inpgrade", grade);
      } else {
        info.addResult("inpgrade", "");
      }
      String employementtype = bp.getHRISEmployementType();
      if (employementtype != null) {
        info.addResult("inpemployementtype", employementtype);
      } else {
        info.addResult("inpemployementtype", "");
      }
      String jobtitle = bp.getHrisJobtitle().getId();
      if (jobtitle != null) {
        info.addResult("inphrisJobtitleId", jobtitle);
      } else {
        info.addResult("inphrisJobtitleId", "");
      }
      if (bp != null) {
        String whereclause = "e " + " where e.businessPartner =?"
            + " order by validFromDate desc -1";

        OBQuery<HRIS_C_Bp_education> education = OBDal.getInstance().createQuery(
            HRIS_C_Bp_education.class, whereclause);
        ArrayList<Object> param = new ArrayList<Object>();
        param.add(bp);
        education.setParameters(param);
        List<HRIS_C_Bp_education> bpedu = education.list();
        for (HRIS_C_Bp_education lasedu : bpedu) {
          String institution = lasedu.getInstitutionName();
          String educationid = lasedu.getDicipline().getId();
          info.addResult("inphrisLastinstitutionName", institution);
          info.addResult("inphrisLasteducationId", educationid);
        }
      }
    }
  }
}
