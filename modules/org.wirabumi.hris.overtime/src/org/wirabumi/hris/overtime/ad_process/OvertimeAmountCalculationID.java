package org.wirabumi.hris.overtime.ad_process;

//org.wirabumi.hris.overtime.ad_process.OvertimeAmountCalculationID
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.wirabumi.hris.timeandattendance.TAAttendance;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

public class OvertimeAmountCalculationID extends DalBaseProcess {
  Logger log4Overtime = Logger.getLogger(this.getClass());

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    try {
      SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      String overtimeRequestID = (String) bundle.getParams().get("OT_Overtime_ID");
      ot_overtime ovt = OBDal.getInstance().get(ot_overtime.class, overtimeRequestID);
      if (AttendanceUtility.isShiftPresent(ovt.getStartingDate(), ovt.getBusinessPartner().getId())) {
        if (!ovt.isCalculated()) {
          ovt.getOtOvertimeDetailList().removeAll(ovt.getOtOvertimeDetailList());
          long amount = 0;
          Date ovtRequestIn = formatDate.parse(DateIntervalUtility
              .getDate(ovt.getStartingDate(), "dd-MM-yyyy").concat(" ")
              .concat(DateIntervalUtility.getTime(ovt.getCheckin())));
          Date ovtRequestOut = formatDate.parse(DateIntervalUtility
              .getDate(ovt.getEndingDate(), "dd-MM-yyyy").concat(" ")
              .concat(DateIntervalUtility.getTime(ovt.getCheckout())));
          TAAttendance usedattendance = ovt.getAttendance();
          if (usedattendance == null) {
            List<TAAttendance> matchedAttendance = Utility.matchingAttendance(
                bundle.getConnection(), ovt.getBusinessPartner().getId(), ovt.getStartingDate(),
                vars);
            if (matchedAttendance == null || matchedAttendance.size() == 0) {
              String message = org.openbravo.erpCommon.utility.Utility.parseTranslation(
                  conectionProvider, vars, vars.getLanguage(), " No One Attendence Matced");
              final OBError msg = new OBError();
              msg.setType("Info");
              msg.setTitle(" Please Manualy Chose Youre Attendance");
              msg.setMessage(message);
              bundle.setResult(msg);
              return;
            } else if (matchedAttendance.size() > 1) {
              String message = org.openbravo.erpCommon.utility.Utility.parseTranslation(
                  conectionProvider, vars, vars.getLanguage(), " More Than One Attendence Matced");
              final OBError msg = new OBError();
              msg.setType("Info");
              msg.setTitle("Please Manualy Chose Youre Attendance");
              msg.setMessage(message);
              bundle.setResult(msg);
              return;
            } else {
              usedattendance = matchedAttendance.get(0);
              ovt.setAttendance(usedattendance);
              OBDal.getInstance().save(ovt);
            }
          }
          // validate by shift
          Date validIn = Utility.validStartOvertime(ovt.getBusinessPartner(), ovtRequestIn);
          Date validOut = Utility.validFinishOvertime(ovt.getBusinessPartner(), ovtRequestOut);
          // validate By attendance
          validIn = Utility.validStartOvertimeBasedAttendance(ovt.getBusinessPartner(), validIn,
              usedattendance);

          validOut = Utility.validFinishOvertimeAttendance(ovt.getBusinessPartner(), validOut,
              usedattendance);

          // .println("===========" + validIn + "=============" + validOut + "==================");
          double interval = DateIntervalUtility.getDay(validIn, validOut);
          if (interval > 0) {

            double overtimeRateAmount = ovt.getBusinessPartner().getOtRateamount().doubleValue();
            amount = OvertimeLinetAmount.getOvertimeAmount(ovt, ovt.getBusinessPartner()
                .getHRISPosition(), validIn, validOut, overtimeRateAmount);
          }
          ovt.setAmount(new BigDecimal(amount));
          int InterVl = new Double(interval * 24 * 60 * 60 * 1000).intValue();
          ovt.setDuration(new Timestamp(DateIntervalUtility.resetDateAdjust(InterVl).getTime()));
          ovt.setValidrequeststart(validIn);
          ovt.setValidrequstfinish(validOut);
          if (ovt.getDocumentStatus().equals("CO")) {
            ovt.setCalculated(true);
          }
          OBDal.getInstance().save(ovt);
          OBDal.getInstance().commitAndClose();
          String message = org.openbravo.erpCommon.utility.Utility.parseTranslation(
              conectionProvider, vars, vars.getLanguage(), " @Hris_DataCreated@");
          final OBError msg = new OBError();
          msg.setType("Success");
          msg.setTitle("Done");
          msg.setMessage(message);
          bundle.setResult(msg);
        } else {
          String message = org.openbravo.erpCommon.utility.Utility.parseTranslation(
              conectionProvider, vars, vars.getLanguage(), " @OtherPostingProcessActive@");
          final OBError msg = new OBError();
          msg.setType("Error");
          msg.setTitle("Failed");
          msg.setMessage(message);
          bundle.setResult(msg);
        }
      } else {
        String message = org.openbravo.erpCommon.utility.Utility.parseTranslation(
            conectionProvider, vars, vars.getLanguage(), " Shift Pada Karyawan Tidak terdefinisi");
        final OBError msg = new OBError();
        msg.setType("Error");
        msg.setTitle("Failed");
        msg.setMessage(message);
        bundle.setResult(msg);
      }
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
}
