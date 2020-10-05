package org.wirabumi.hris.employee.master.ad_callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.wirabumi.hris.employee.master.data.hris_dicipline;

public class EmployeeEducation extends SimpleCallout {

	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		String employeeEducation = info.getStringParameter("inpdicipline", null);
		final hris_dicipline hd = OBDal.getInstance().get(hris_dicipline.class, employeeEducation);

		String institusi = hd.getInstitutionname();
		if (institusi!=null && !institusi.isEmpty())
			info.addResult("inpinstitutionName", institusi);

		String level = hd.getEducationLevel();
		if (level!=null && !level.isEmpty())
			info.addResult("inplevel", level);

		String akreditasi = hd.getAccreditation();
		if (akreditasi!=null && !akreditasi.isEmpty())
			info.addResult("inphrisAccreditation", akreditasi);

	}
  }