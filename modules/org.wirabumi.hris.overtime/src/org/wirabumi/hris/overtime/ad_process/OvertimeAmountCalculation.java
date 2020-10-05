package org.wirabumi.hris.overtime.ad_process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.gen.oez.utility.DateIntervalUtility;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.overtime.utility.OvertimeLinetAmount;
import org.wirabumi.hris.overtime.utility.Utility;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

@Deprecated
public class OvertimeAmountCalculation extends DalBaseProcess {
  Logger log4OVertimeCalculation = Logger.getLogger(this.getClass());

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    try {
      SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
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
      overtime.setMaxResult(100);
      List<ot_overtime> listOvertime = overtime.list();
      int counter = 0;
      for (ot_overtime ovt : listOvertime) {
        counter++;
        System.out.println(counter + " - " + ovt.getId() + " - " + ovt.getStartingDate());
        if (AttendanceUtility.isShiftPresent(ovt.getStartingDate(), ovt.getBusinessPartner()
            .getId())) {
          ovt.getOtOvertimeDetailList().removeAll(ovt.getOtOvertimeDetailList());
          boolean a = true;
          long amount = 0;
          Date ovtRequestIn = formatDate.parse(DateIntervalUtility
              .getDate(ovt.getStartingDate(), "dd-MM-yyyy").concat("  ")
              .concat(DateIntervalUtility.getTime(ovt.getCheckin())));
          Date ovtRequestOut = formatDate.parse(DateIntervalUtility
              .getDate(ovt.getEndingDate(), "dd-MM-yyyy").concat("  ")
              .concat(DateIntervalUtility.getTime(ovt.getCheckout())));

          Date validIn = Utility.validStartOvertime(ovt.getBusinessPartner(), ovtRequestIn);
          Date validOut = Utility.validFinishOvertime(ovt.getBusinessPartner(), ovtRequestOut);
          // System.out
          // .println("===========" + validIn + "=============" + validOut + "=================");
          double interval = DateIntervalUtility.getDay(validIn, validOut);
          String employeePosition=null;
          if (ovt.getPosition()!=null)
          	employeePosition=ovt.getPosition().getSearchKey();
          if (interval > 0) {
            amount = OvertimeLinetAmount.getOvertimeAmount(ovt, employeePosition, validIn,
                validOut);
          }
          if (ovt.getDocumentStatus().equals("CO")) {
            ovt.setCalculated(true);
          }
          ovt.setAmount(new BigDecimal(amount));
          int InterVl = new Double(interval * 24 * 60 * 60 * 1000).intValue();
          ovt.setDuration(new Timestamp(DateIntervalUtility.resetDateAdjust(InterVl).getTime()));
          ovt.setValidrequeststart(validIn);
          ovt.setValidrequstfinish(validOut);
          double hourInterval = DateIntervalUtility.getHour(validIn, validOut);
          ovt.setIntduration(new BigDecimal(hourInterval));
          OBDal.getInstance().save(ovt);
        }
      }
      OBDal.getInstance().commitAndClose();
      log4OVertimeCalculation.error(" Calculating Overtime Finish" + System.currentTimeMillis());
    } catch (Exception e) {
      log4OVertimeCalculation.error(e);
      OBDal.getInstance().rollbackAndClose();
      e.printStackTrace();
    }
  }
}
