package org.wirabumi.hris.timeandattendance.Callout;

import java.sql.Timestamp;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.wirabumi.hris.timeandattendance.ManualScheduleGroup;

public class EmployeeManualScheduleGroupCallout extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		/*
		 * kalau manual schedule group sudah dipilih, maka field berikut akan di update sesuai manual schedule group yg dipilih:
		 * 1. check in
		 * 2. check out
		 * 3. off
		 */
		
		String msgID = info.getStringParameter("inptaManualschedulegroupId");
		if (msgID==null || msgID.isEmpty())
			return; //invalid manual schedule group ID
		
		ManualScheduleGroup msg = OBDal.getInstance().get(ManualScheduleGroup.class, msgID);
		if (msg==null)
			return; //invalid manual schedule group ID
		
		Timestamp checkin = msg.getCheckIn();
		Timestamp checkout = msg.getCheckOut();
		boolean isoff = msg.isOff();
		
		if (checkin!=null)
			info.addResult("inpcheckin", checkin);
		else
			info.addResult("inpcheckin", null);
		if (checkout!=null)
			info.addResult("inpcheckout", checkout);
		else
			info.addResult("inpcheckout", null);
		if (isoff)
			info.addResult("inpisoff", "Y");
		else
			info.addResult("inpisoff", "N");

	}

}
