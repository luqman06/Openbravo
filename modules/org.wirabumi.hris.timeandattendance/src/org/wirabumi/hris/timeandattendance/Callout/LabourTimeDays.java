package org.wirabumi.hris.timeandattendance.Callout;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

//org.wirabumi.hris.timeandattendance.Callout.LabourTimeDays
public class LabourTimeDays extends SimpleCallout {

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    String changedColumn = info.getLastFieldChanged();
    String fmDate = info.vars.getJavaDateFormat();
    String fmDateTime = info.vars.getJavaDataTimeFormat();
    SimpleDateFormat dateFormat = new SimpleDateFormat(fmDate);
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat(fmDateTime);
    if ("inpcBpartnerId".equals(changedColumn) || "inpswitcheddate".equals(changedColumn)) {
      // set actual employee time
      String businessPartnerID = info.getStringParameter("inpcBpartnerId", null);
      String switchedDate = info.getStringParameter("inpswitcheddate", null);
      try {
        Date switchDate = !switchedDate.isEmpty() ? dateFormat.parse(switchedDate) : null;
        BusinessPartner bpartner = !businessPartnerID.isEmpty() ? OBDal.getInstance().get(
            BusinessPartner.class, businessPartnerID) : null;
        if (bpartner != null && switchDate != null) {
          Date startWorking = AttendanceUtility.workingStart(switchDate, bpartner);
          Date endWorking = AttendanceUtility.workingFinish(switchDate, bpartner);
          if (startWorking != null && endWorking != null) {
            String sw = dateTimeFormat.format(startWorking);
            String ew = dateTimeFormat.format(endWorking);
            info.addResult("inpcheckin", sw);
            info.addResult("inpcheckout", ew);
          }
        }
        String pgs = info.getStringParameter("inppgsId", null);
        if (!pgs.isEmpty()) {
          String shitchedMan = info.getStringParameter("inppgsId", null);
          BusinessPartner swMan = !shitchedMan.isEmpty() ? OBDal.getInstance().get(
              BusinessPartner.class, shitchedMan) : null;
          if (swMan != null && switchDate != null) {
            Date startWorking = AttendanceUtility.workingStart(switchDate, swMan);
            Date endWorking = AttendanceUtility.workingFinish(switchDate, swMan);
            if (startWorking != null && endWorking != null) {
              String sw2 = dateTimeFormat.format(startWorking);
              String ew2 = dateTimeFormat.format(endWorking);
              info.addResult("inpcheckin2", sw2);
              info.addResult("inpcheckout2", ew2);
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if ("inppgsId".equals(changedColumn)) {
      String shitchedMan = info.getStringParameter("inppgsId", null);
      String switchedDate = info.getStringParameter("inpswitcheddate", null);
      try {
        Date switchDate = !switchedDate.isEmpty() ? dateFormat.parse(switchedDate) : null;
        BusinessPartner swMan = !shitchedMan.isEmpty() ? OBDal.getInstance().get(
            BusinessPartner.class, shitchedMan) : null;
        if (swMan != null && switchDate != null) {
          Date startWorking = AttendanceUtility.workingStart(switchDate, swMan);
          Date endWorking = AttendanceUtility.workingFinish(switchDate, swMan);
          if (startWorking != null && endWorking != null) {
            String sw2 = dateTimeFormat.format(startWorking);
            String ew2 = dateTimeFormat.format(endWorking);
            info.addResult("inpcheckin2", sw2);
            info.addResult("inpcheckout2", ew2);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
