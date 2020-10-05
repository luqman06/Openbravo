package org.wirabumi.hris.employee.master.ad_callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;

public class BenefitsData extends SimpleCallout {

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    // TODO Auto-generated method stub
    String bpartner_id = info.getStringParameter("inpcBpartnerId", null);
    final BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpartner_id);
    String nilai = bp.getSearchKey();
    info.addResult("inpvalue", nilai);
    if (bp.getHrisEchelon() != null) {
      String eselon = bp.getHrisEchelon();
      info.addResult("inpechelon", eselon);
    } else {
      info.addResult("inpechelon", "");
    }
    if (bp.getHRISLevel() != null) {
      String grade = bp.getHRISLevel();
      info.addResult("inplevel", grade);
    } else {
      info.addResult("inplevel", "");
    }
    if (bp.getHRISPosition() != null) {
      String posisi = bp.getHRISPosition();
      info.addResult("inpposition", posisi);
    } else {
      info.addResult("inpposition", "");
    }
    if (bp.getHrisJobtitle() != null) {
      String judul = bp.getHrisJobtitle().getId();
      info.addResult("inphrisJobtitleId", judul);
    } else {
      info.addResult("inphrisJobtitleId", "");
    }
    if (bp.getHrisCostcenter() != null) {
      String cost_center = bp.getHrisCostcenter().getId();
      info.addResult("inpmaCostcenterId", cost_center);
    } else {
      info.addResult("inpmaCostcenterId", "");
    }
  }
}
