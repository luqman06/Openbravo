package org.wirabumi.hris.overtime.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.data.UtilSql;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBDateUtils;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.wirabumi.gen.oez.utility.DateIntervalUtility;
import org.wirabumi.gen.oez.utility.DateTimeUtility;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.timeandattendance.TAAttendance;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

public class Utility {

  public static Date validStartOvertime(BusinessPartner employee, Date requestDate)
      throws ParseException {
	Boolean isShouldAttend=AttendanceUtility.IsEmployeeShouldAttend(employee.getId(), requestDate);
	if (!isShouldAttend)
		return requestDate;
	
    Date validTime = null;
    // Date CheckOutTimeStamp = null;
    Date CheckOutTimeStamp = AttendanceUtility.workingFinish(requestDate, employee);
    Date CheckInTimeStamp = AttendanceUtility.workingStart(requestDate, employee);

    if (AttendanceUtility.isWorkDayBasedOnShiftOnly(requestDate, employee.getId())) {
      // get valid start time
      if (requestDate.before(CheckOutTimeStamp) && requestDate.after(CheckInTimeStamp)) {// Request
        validTime = CheckOutTimeStamp;
      } else if (requestDate.before(CheckInTimeStamp)) {// sebelum jam kerja di validasi dengan sift
                                                        // hari sebelumnya
        Date checkoutShiftYesterday = AttendanceUtility.workingFinish(
            DateIntervalUtility.adjustDate(requestDate, -1), employee);
        if (checkoutShiftYesterday != null && requestDate.before(CheckInTimeStamp)
            && requestDate.after(checkoutShiftYesterday)) {
          validTime = requestDate;
        } else if (checkoutShiftYesterday != null && requestDate.before(CheckInTimeStamp)
            && requestDate.before(checkoutShiftYesterday)) {
          validTime = checkoutShiftYesterday;
        } else {
          validTime = requestDate;
        }
      } else if (requestDate.after(CheckOutTimeStamp)) {// setelah jam kerja maka yang valid adalah
        validTime = requestDate;
      } else if (requestDate.equals(CheckOutTimeStamp)) {
        validTime = requestDate;
      }
    } else {
      validTime = requestDate;
    }
    return validTime;
  }

  public static Date validStartOvertimeBasedAttendance(BusinessPartner employee, Date requestDate,
      TAAttendance attendance) throws ParseException {
    Date validTime = null;
    // Date CheckOutTimeStamp = null;
    Date CheckOutTimeStamp = attendance.getCheckout();
    Date CheckInTimeStamp = attendance.getCheckin();
    // get valid start time
    if (requestDate.before(CheckOutTimeStamp) && requestDate.after(CheckInTimeStamp)) {
      // Request Start Overtime alter by End Of Shift Time
      validTime = requestDate;
    } else if (requestDate.before(CheckInTimeStamp)) {// sebelum jam kerja di validasi dengan sift
                                                      // hari sebelumnya
      Date checkoutShiftYesterday = getLastCheckoutDateBefore(employee,
          DateIntervalUtility.adjustDate(requestDate, -1));
      if (checkoutShiftYesterday != null && requestDate.before(CheckInTimeStamp)
          && requestDate.after(checkoutShiftYesterday)) {
        validTime = requestDate;
      } else if (checkoutShiftYesterday != null && requestDate.before(CheckInTimeStamp)
          && requestDate.before(checkoutShiftYesterday)) {
        validTime = checkoutShiftYesterday;
      } else {
        validTime = requestDate;
      }
    } else if (requestDate.after(CheckOutTimeStamp)) {// setelah jam kerja maka yang valid adalah
      validTime = requestDate;
    } else if (requestDate.equals(CheckOutTimeStamp)) {
      validTime = requestDate;
    }

    return validTime;
  }

  public static Date validFinishOvertime(BusinessPartner employee, Date requestDate) {
	Boolean isShouldAttend=AttendanceUtility.IsEmployeeShouldAttend(employee.getId(), requestDate);
	if (!isShouldAttend)
		return requestDate;
		
    Date validTime = null;
    Date CheckOutTimeStamp = AttendanceUtility.workingFinish(requestDate, employee);
    Date CheckInTimeStamp = AttendanceUtility.workingStart(requestDate, employee);

    if (AttendanceUtility.isWorkDayBasedOnShiftOnly(requestDate, employee.getId())) {
      // get valid end time
      if (requestDate.before(CheckOutTimeStamp) && requestDate.after(CheckInTimeStamp)) {// Request
                                                                                         // Start
                                                                                         // Overtime
                                                                                         // alter by
                                                                                         // Start Of
                                                                                         // Shift
                                                                                         // Time
        validTime = CheckInTimeStamp;
      } else if (requestDate.before(CheckInTimeStamp)) {// Setelah jam kerja di validasi dengan sift
                                                        // hari Berikutnya
        Date checkInShiftTomorow = AttendanceUtility.workingFinish(
            DateIntervalUtility.adjustDate(requestDate, 1), employee);
        if (checkInShiftTomorow != null && requestDate.after(CheckOutTimeStamp)
            && requestDate.before(checkInShiftTomorow)) {
          validTime = requestDate;
        } else if (checkInShiftTomorow != null && requestDate.after(CheckOutTimeStamp)
            && requestDate.after(checkInShiftTomorow)) {
          validTime = checkInShiftTomorow;
        } else {
          validTime = requestDate;
        }
      } else if (requestDate.after(CheckOutTimeStamp)) {// setelah jam kerja maka yang valid adalah
                                                        // req time
        validTime = requestDate;
      } else {
        validTime = requestDate;
      }
    } else {
      validTime = requestDate;
    }
    return validTime;
  }

  public static Date validFinishOvertimeAttendance(BusinessPartner employee, Date requestDate,
      TAAttendance attendance) {
    Date validTime = null;
    Date CheckOutTimeStamp = attendance.getCheckout();
    Date CheckInTimeStamp = attendance.getCheckin();

    if (requestDate.before(CheckOutTimeStamp) && requestDate.after(CheckInTimeStamp)) {// Request
                                                                                       // Start
                                                                                       // Overtime
                                                                                       // alter by
                                                                                       // Start Of
                                                                                       // Shift
                                                                                       // Time
      validTime = requestDate;
    } else if (requestDate.before(CheckInTimeStamp)) {// Setelah jam kerja di validasi dengan sift
                                                      // hari Berikutnya
      Date checkInShiftTomorow = getFirsCheckInTomorow(employee,
          DateIntervalUtility.adjustDate(requestDate, 1));
      if (checkInShiftTomorow != null && requestDate.after(CheckOutTimeStamp)
          && requestDate.before(checkInShiftTomorow)) {
        validTime = requestDate;
      } else if (checkInShiftTomorow != null && requestDate.after(CheckOutTimeStamp)
          && requestDate.after(checkInShiftTomorow)) {
        validTime = checkInShiftTomorow;
      } else {
        validTime = requestDate;
      }
    } else if (requestDate.after(CheckOutTimeStamp)) {// setelah jam kerja maka yang valid adalah
                                                      // req time
      validTime = CheckOutTimeStamp;
    } else {
      validTime = requestDate;
    }
    return validTime;
  }

  public static List<TAAttendance> matchingAttendance(ConnectionProvider conectionProvider,
      String businessPartnerID, Date overtimeDate, VariablesSecureApp vars) {
    List<TAAttendance> mathedAttendance = new ArrayList<TAAttendance>();
    try {
      // String whereClause =
      // "as att where  att.businessPartner=? and att.checkin=? and att.checkout=? ";
      String Sql = " select ta_attendance_id from ta_attendance where c_bpartner_id=? "
          + "and isactive='Y' and checkin between date_trunc('Day',?::timestamp) and date_trunc('Day',?::timestamp + '1 Day'::interval )-'1 minute'::interval order by checkout desc";
      ResultSet result;
      PreparedStatement statement;
      int paramater = 0;
      String patern = vars.getJavaDataTimeFormat();
      VariablesSecureApp varsx = new VariablesSecureApp(RequestContext.get().getRequest());
      patern = patern == null ? varsx.getJavaDataTimeFormat() : patern;
      String ovtDate = OBDateUtils.formatDate(overtimeDate, patern);
      statement = conectionProvider.getPreparedStatement(Sql);
      paramater++;
      UtilSql.setValue(statement, paramater, 12, null, businessPartnerID);
      paramater++;
      UtilSql.setValue(statement, paramater, 12, null, ovtDate);
      paramater++;
      UtilSql.setValue(statement, paramater, 12, null, ovtDate);

      result = statement.executeQuery();
      while (result.next()) {
        TAAttendance Attendance = OBDal.getInstance().get(TAAttendance.class,
            result.getString("ta_attendance_id"));
        mathedAttendance.add(Attendance);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new OBException(e.getLocalizedMessage());
    }
    return mathedAttendance;
  }

  public static boolean isOvertimeOverlaps(BusinessPartner employee, ot_overtime ot,
      Date startOvertime, Date endOvertime, Date checkIn, Date checkOut) {
    boolean overlaps = false;
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    try {
      String whereClause = "as ot where ot.businessPartner=?  and ot.startingDate>=? and ot.endingDate<=? and ot!=?";
      OBQuery<ot_overtime> overtime = OBDal.getInstance().createQuery(ot_overtime.class,
          whereClause);
      List<Object> params = new ArrayList<Object>();
      params.add(employee);
      params.add(startOvertime);
      params.add(endOvertime);
      params.add(ot);
      overtime.setParameters(params);
      List<ot_overtime> overtimeList = overtime.list();
      for (ot_overtime ovt : overtimeList) {
        try {
          Date ovtIn = formatDate.parse(DateIntervalUtility
              .getDate(ovt.getStartingDate(), "dd-MM-yyyy").concat("  ")
              .concat(DateIntervalUtility.getTime(ovt.getCheckin())));
          Date ovtOut = formatDate.parse(DateIntervalUtility
              .getDate(ovt.getEndingDate(), "dd-MM-yyyy").concat(" ")
              .concat(DateIntervalUtility.getTime(ovt.getCheckout())));
          if (DateTimeUtility.isOverlap(checkIn, checkOut, ovtIn, ovtOut, false)) {
            return true;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return overlaps;
  }

  public static Date getLastCheckoutDateBefore(BusinessPartner partner, Date checkIn) {
    Date lastCheckOut = null;
    try {
      String query = "select max(checkout) from TA_Attendance where businessPartner=? and checkout < ?";
      Query syntax = OBDal.getInstance().getSession().createQuery(query);
      syntax.setParameter(0, partner);
      syntax.setParameter(1, checkIn);
      List<Date> result = syntax.list();
      if (result.size() > 0) {
        lastCheckOut = result.get(0);
      }
    } catch (Exception e) {
      throw new OBException(e.getLocalizedMessage());
    }
    return lastCheckOut;
  }

  public static Date getFirsCheckInTomorow(BusinessPartner partner, Date checkOut) {
    Date lastCheckOut = null;
    try {
      String query = "select min(checkin) from TA_Attendance where businessPartner=? and checkin < ?";
      Query syntax = OBDal.getInstance().getSession().createQuery(query);
      syntax.setParameter(0, partner);
      syntax.setParameter(1, checkOut);
      List<Date> result = syntax.list();
      if (result.size() > 0) {
        lastCheckOut = result.get(0);
      }
    } catch (Exception e) {
      throw new OBException(e.getLocalizedMessage());
    }
    return lastCheckOut;
  }
}
