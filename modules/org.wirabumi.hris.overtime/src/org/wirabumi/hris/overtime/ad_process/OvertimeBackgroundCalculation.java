package org.wirabumi.hris.overtime.ad_process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
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

public class OvertimeBackgroundCalculation extends DalBaseProcess {
  private static Logger log4OVertimeCalculation = Logger
      .getLogger(OvertimeBackgroundCalculation.class);

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    try {
      SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      ConnectionProvider conectionProvider = bundle.getConnection();
      VariablesSecureApp vars = new VariablesSecureApp(RequestContext.get().getRequest());
      OBPropertiesProvider properti = OBPropertiesProvider.getInstance();
      Properties prop = properti.getOpenbravoProperties();
      String sqlFormat = prop.getProperty("dateFormat.sql");
      String javaDateTimeFormat = prop.getProperty("dateTimeFormat.java");
      String jsFormat = prop.getProperty("dateFormat.js");
      String javaFormat = prop.getProperty("dateFormat.java");

      vars.setSessionValue("#AD_JavaDateFormat", javaFormat);
      vars.setSessionValue("#AD_JavaDateTimeFormat", javaDateTimeFormat);
      vars.setSessionValue("#AD_JsDateFormat", jsFormat);
      vars.setSessionValue("#AD_SqlDateFormat", sqlFormat);
      vars = new VariablesSecureApp(RequestContext.get().getRequest(), true);
      // loop overtime not calculated and before ending salary
      String WhereClause = "where calculate=? and paid=? and documentStatus=?";
      List<Object> params = new ArrayList<Object>();
      params.add(false);
      params.add(false);
      params.add("CO");
      OBQuery<ot_overtime> overtime = OBDal.getInstance().createQuery(ot_overtime.class,
          WhereClause);
      overtime.setFilterOnReadableClients(true);
      overtime.setFilterOnActive(true);
      overtime.setParameters(params);
      List<ot_overtime> listOvertime = overtime.list();
      for (ot_overtime ovt : listOvertime) {
        if (AttendanceUtility.isShiftPresent(ovt.getStartingDate(), ovt.getBusinessPartner()
            .getId())) {
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
                ovt.setValidationnote(message);
                OBDal.getInstance().save(ovt);
                continue;
              } else if (matchedAttendance.size() > 1) {
                String message = org.openbravo.erpCommon.utility.Utility
                    .parseTranslation(conectionProvider, vars, vars.getLanguage(),
                        " More Than One Attendence Matced");
                ovt.setValidationnote(message);
                OBDal.getInstance().save(ovt);
                continue;
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

            // .println("===========" + validIn + "=============" + validOut +
            // "==================");
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
            double hourInterval = DateIntervalUtility.getHour(validIn, validOut);
            ovt.setIntduration(new BigDecimal(hourInterval));
            if (ovt.getDocumentStatus().equals("CO")) {
              ovt.setCalculated(true);
              ovt.setValidationnote(null);
            }
            OBDal.getInstance().save(ovt);
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
          String message = org.openbravo.erpCommon.utility.Utility
              .parseTranslation(conectionProvider, vars, vars.getLanguage(),
                  " Shift Pada Karyawan Tidak terdefinisi");
          ovt.setValidationnote(message);
          OBDal.getInstance().save(ovt);
        }
      }

      OBDal.getInstance().commitAndClose();
      log4OVertimeCalculation.error("Calculating Overtime Finish" + System.currentTimeMillis());
    } catch (Exception e) {
      log4OVertimeCalculation.error(e);
      OBDal.getInstance().rollbackAndClose();
      e.printStackTrace();
    }
  }

}
