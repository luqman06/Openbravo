package org.wirabumi.hris.overtime.ad_process;

import java.math.BigDecimal;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.gen.oez.utility.DateIntervalUtility;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.overtime.utility.OvertimeLinetAmount;
import org.wirabumi.hris.overtime.utility.Utility;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

public class CalculateOvertime extends DalBaseProcess {
  Logger log4Overtime = Logger.getLogger(this.getClass());
  
  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    try {
      
      String overtimeRequestID = (String) bundle.getParams().get("OT_Overtime_ID");
      ot_overtime ovt = OBDal.getInstance().get(ot_overtime.class, overtimeRequestID);
      final OBError msg = doCalculateOvertime(ovt, conectionProvider, vars);
      OBDal.getInstance().commitAndClose();
      bundle.setResult(msg);
    } catch (Exception e) {
      log4Overtime.error(e);
      String message = org.openbravo.erpCommon.utility.Utility.parseTranslation(conectionProvider,
          vars, vars.getLanguage(), " Overtime Tidak Valid ");
      final OBError msg = new OBError();
      msg.setType("Error");
      msg.setTitle("Failed");
      msg.setMessage(message);
      bundle.setResult(msg);
    }
  }
  
  public OBError doCalculateOvertime(ot_overtime ovt, ConnectionProvider conn, VariablesSecureApp vars) throws Exception{
	  SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	  final OBError msg = new OBError();
	  
	  if (AttendanceUtility.isShiftPresent(ovt.getStartingDate(), ovt.getBusinessPartner().getId()) || ovt.isManual()) {
		  Statement deleteStatement =	conn.getStatement();
  		  String strDeleteQuery = "delete from ot_overtime_detail where ot_overtime_id='"+ovt.getId()+"'";
  		  deleteStatement.executeUpdate(strDeleteQuery);
  		  OBDal.getInstance().flush();
  		  
          long amount = 0;
          Date ovtRequestIn = formatDate.parse(DateIntervalUtility
              .getDate(ovt.getStartingDate(), "dd-MM-yyyy").concat(" ")
              .concat(DateIntervalUtility.getTime(ovt.getCheckin())));
          Date ovtRequestOut = formatDate.parse(DateIntervalUtility
              .getDate(ovt.getEndingDate(), "dd-MM-yyyy").concat(" ")
              .concat(DateIntervalUtility.getTime(ovt.getCheckout())));
          
          Date validIn = Utility.validStartOvertime(ovt.getBusinessPartner(), ovtRequestIn);
          Date validOut = Utility.validFinishOvertime(ovt.getBusinessPartner(), ovtRequestOut);
          // System.out
          // .println("===========" + validIn + "=============" + validOut + "==================");
          double interval=0;
          if (validIn!=null && validOut!=null)
        	  interval = DateIntervalUtility.getDay(validIn, validOut);
          
          String employeePosition=null;
          if (ovt.getPosition()!=null)
          	employeePosition=ovt.getPosition().getSearchKey();
          if (interval > 0) {
            amount = OvertimeLinetAmount.getOvertimeAmount(ovt, employeePosition, validIn,
                validOut);
          }
          ovt.setAmount(new BigDecimal(amount));
          int InterVl = new Double(interval * 24 * 60 * 60 * 1000).intValue();
          ovt.setDuration(new Timestamp(DateIntervalUtility.resetDateAdjust(InterVl).getTime()));
          ovt.setValidrequeststart(validIn);
          ovt.setValidrequstfinish(validOut);
          ovt.setCalculated(true);
          OBDal.getInstance().save(ovt);
          
          String message = org.openbravo.erpCommon.utility.Utility.parseTranslation(
              conn, vars, vars.getLanguage(), " @Hris_DataCreated@");
          
          msg.setType("Success");
          msg.setTitle("Done");
          msg.setMessage(message);
       } else {
    	    String message = org.openbravo.erpCommon.utility.Utility.parseTranslation(
   	            conn, vars, vars.getLanguage(), " Bukan lembur manual dan Shift Pada Karyawan Tidak terdefinisi");
   	        msg.setType("Error");
   	        msg.setTitle("Failed");
   	        msg.setMessage(message);
       }
	  return msg;
  }

}
