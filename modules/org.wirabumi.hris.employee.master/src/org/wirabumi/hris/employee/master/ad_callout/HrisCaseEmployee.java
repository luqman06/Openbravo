package org.wirabumi.hris.employee.master.ad_callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;

public class HrisCaseEmployee extends SimpleCallout {

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    // TODO Auto-generated method stub
    String businessPartner = info.getStringParameter("inpcBpartnerId", null);
    final BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, businessPartner);
    String value = bp.getSearchKey();
    String level = bp.getHRISLevel();
    String echelon = bp.getHrisEchelon();
    String position = bp.getHRISPosition();
    String costCenter = bp.getHrisCostcenter().getId();
    String jobtitle = bp.getHrisJobtitle().getId();

    info.addResult("inpvalue", value);
    info.addResult("inplevel", level);
    info.addResult("inpechelon", echelon);
    info.addResult("inpposition", position);
    info.addResult("inpmaCostcenterId", costCenter);
    info.addResult("inphrisJobtitleId", jobtitle);

  }

}
