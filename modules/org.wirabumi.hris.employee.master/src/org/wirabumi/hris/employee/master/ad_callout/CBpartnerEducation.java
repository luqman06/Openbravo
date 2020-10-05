package org.wirabumi.hris.employee.master.ad_callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.wirabumi.hris.employee.master.data.hris_dicipline;

public class CBpartnerEducation extends SimpleCallout {

  private static final long serialVersionUID = 1L;

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    String education = info.getStringParameter("inpdicipline", null);
    final hris_dicipline hd = OBDal.getInstance().get(hris_dicipline.class, education);

    String level = hd.getEducationLevel();
    String institusi = hd.getInstitutionname();
    String akreditasi = hd.getAccreditation();

    info.addResult("inplevel", level);
    info.addResult("inpinstitutionName", institusi);
    info.addResult("inphrisAccreditation", akreditasi);
  }

}