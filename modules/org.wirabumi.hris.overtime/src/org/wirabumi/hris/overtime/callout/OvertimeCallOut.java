package org.wirabumi.hris.overtime.callout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;

public class OvertimeCallOut extends SimpleCallout {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Logger log4OvertimeCallout = Logger.getLogger(this.getClass());

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    try {
      String fieldChange = info.getLastFieldChanged();
      SimpleDateFormat df = new SimpleDateFormat(info.vars.getJavaDateFormat());
      SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      String strCheckIn = info.getStringParameter("inpcheckin", null);
      String strCheckOut = info.getStringParameter("inpcheckout", null);
      String strDateFrom = info.getStringParameter("inpdatefrom", null);
      String strDateTo = info.getStringParameter("inpdateto", null);

      if (fieldChange.equals("inpcBpartnerId") || fieldChange.equals("inpdatefrom")) {
        String employee = info.getStringParameter("inpcBpartnerId", null);
        String validDate = info.getStringParameter("inpdatefrom", null);
        OBCriteria<HRIS_C_Bp_Empinfo> contract = OBDal.getInstance().createCriteria(
            HRIS_C_Bp_Empinfo.class);
        contract.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_BUSINESSPARTNER, OBDal
            .getInstance().get(BusinessPartner.class, employee)));
        contract
            .add(Restrictions.le(HRIS_C_Bp_Empinfo.PROPERTY_VALIDFROMDATE, df.parse(validDate)));
        contract.addOrderBy(HRIS_C_Bp_Empinfo.PROPERTY_VALIDFROMDATE, false);
        contract.setMaxResults(1);
        if (contract.list().size() > 0) {
          HRIS_C_Bp_Empinfo curContract = contract.list().get(0);
          info.addResult("inpposition", curContract.getPosition());
          info.addResult("inpechelon", curContract.getEchelon());
          info.addResult("inpemployeegrade", curContract.getLevel());
          if (curContract.getHrisJobtitle() != null) {
            info.addResult("inpjobtitle", curContract.getHrisJobtitle().getId());
          } else {
            info.addResult("inpjobtitle", "");
          }
        } else {
          info.addResult("inpposition", null);
          info.addResult("inpechelon", null);
          info.addResult("inpemployeegrade", null);
          info.addResult("inpjobtitle", null);
        }
        
        //jika date from diubah, maka date to dan dateacct akan berubah mengikuti date from
        info.addResult("inpdateto", strDateFrom);
        info.addResult("inpdateacct", strDateFrom);
        strDateTo=strDateFrom.toString();
      }
      
      if (strDateFrom!=null && !strDateFrom.isEmpty() &&
    		  strDateTo!=null && !strDateTo.isEmpty() &&
    		  strCheckIn!=null && !strCheckIn.isEmpty() &&
    		  strCheckOut!=null && !strCheckOut.isEmpty()){
    	  Date checkin = timeStamp.parse(strDateFrom.concat(" ").concat(strCheckIn));
          Date checkout = timeStamp.parse(strDateTo.concat(" ").concat(strCheckOut));
          Calendar cal = Calendar.getInstance();
          cal.setTime(checkin);
          long checkinLong = cal.getTimeInMillis();
          cal.setTime(checkout);
          long checkoutLong = cal.getTimeInMillis();
          long duration = checkoutLong-checkinLong;
          int minutes = (int) ((duration / (1000*60)) % 60);
          long hours   = TimeUnit.MILLISECONDS.toHours(duration);
          double interval = hours+(minutes/60.00);
          info.addResult("inpintduration", interval);
          
      }
      
    } catch (Exception e) {

    }
  }
}
