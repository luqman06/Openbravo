package org.wirabumi.hris.timeandattendance.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.financialmgmt.calendar.NonBusinessDay;
import org.wirabumi.gen.oez.utility.DateIntervalUtility;
import org.wirabumi.hris.businesstrip.bt_businesstrip;
import org.wirabumi.hris.leave.lv_c_bp_leave;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.timeandattendance.ManualSchedule;
import org.wirabumi.hris.timeandattendance.TAAttendance;
import org.wirabumi.hris.timeandattendance.TAShift;
import org.wirabumi.hris.timeandattendance.TA_ShiftLine;
import org.wirabumi.hris.timeandattendance.ta_c_bp_shift;
import org.wirabumi.hris.timeandattendance.ta_tukar_shift;

public class AttendanceUtility {

  public static boolean IsEmployeeShouldAttend(String employee, Date date) {
    // cek libur
    boolean cekHariLibur = isNonBusinessDay(date);
    if (cekHariLibur == true) {
      return false;
    }

    // cek shift
    boolean isWorkDayBaseOnShift = isWorkDayBasedOnShiftOnly(date, employee);
    if (isWorkDayBaseOnShift == false) {
      // cek lembur
      boolean cekLembur = isRequestOvertime(employee, date);
      if (cekLembur == false) {
        return false;
      }
    } else {
      boolean isSwichedShift = isSwitchShift(date, employee);
      if (!isSwichedShift) {
        return false;
      }
    }

    // cek perjalanan dinas
    boolean cekPerjalananDinas = isBusinessTrip(employee, date);
    if (cekPerjalananDinas == true) {
      return false;
    }
    return true;
  }

  public static boolean isValidForOvertime(Date date, String employee) {
    boolean validOvertime = true;
    validOvertime = !isWorkDayBasedOnShiftOnly(date, employee);
    return validOvertime;
  }

  public static boolean isSwitchShift(Date date, String employee) {
    boolean isSwiched = false;
    String whereClause = " tt where tt.employee.id=? and date(tt.switcheddate)=date(?) ";
    List<Object> params = new ArrayList<Object>();
    params.add(employee);
    params.add(date);
    OBQuery<ta_tukar_shift> switchedShift = OBDal.getInstance().createQuery(ta_tukar_shift.class,
        whereClause, params);
    List<ta_tukar_shift> switchedShifts = switchedShift.list();
    if (switchedShifts.size() > 0) {
      isSwiched = true;
    }
    return isSwiched;
  }

  public static Date SwitchShiftIn(Date date, String employee) {
    Date swichedCheckIn = null;
    String whereClause = " tt where tt.employee.id=? and date(tt.switcheddate)=date(?)";
    List<Object> params = new ArrayList<Object>();
    params.add(employee);
    params.add(date);
    OBQuery<ta_tukar_shift> switchedShift = OBDal.getInstance().createQuery(ta_tukar_shift.class,
        whereClause, params);
    List<ta_tukar_shift> switchedShifts = switchedShift.list();
    if (switchedShifts.size() > 0) {
      swichedCheckIn = switchedShifts.get(0).getCheckin2();
    }
    return swichedCheckIn;
  }

  public static Date SwitchShiftOut(Date date, String employee) {
    Date swichedCheckOut = null;
    String whereClause = " tt where tt.employee.id=? and date(tt.switcheddate)=date(?)";
    List<Object> params = new ArrayList<Object>();
    params.add(employee);
    params.add(date);
    OBQuery<ta_tukar_shift> switchedShift = OBDal.getInstance().createQuery(ta_tukar_shift.class,
        whereClause, params);
    List<ta_tukar_shift> switchedShifts = switchedShift.list();
    if (switchedShifts.size() > 0) {
      swichedCheckOut = switchedShifts.get(0).getCheckout2();
    }
    return swichedCheckOut;
  }

  public static boolean isHoliday(Date date, BusinessPartner employee) {
    boolean holiday = true;
    OBContext.setAdminMode();
    holiday = isNonBusinessDay(date);
    holiday = holiday ? holiday : !isWorkDayBasedOnShiftOnly(date, employee.getId());
    OBContext.restorePreviousMode();
    return holiday;
  }

  public static boolean isNonBusinessDay(Date date) {
    OBContext.setAdminMode();
    OBCriteria<NonBusinessDay> nonBusinessDay = OBDal.getInstance().createCriteria(
        NonBusinessDay.class);
    nonBusinessDay.add(Restrictions.eq(NonBusinessDay.PROPERTY_NONBUSINESSDAYDATE, date));
    nonBusinessDay.setFilterOnActive(true);
    if (nonBusinessDay.list().size() > 0) {
      return true;
    }
    return false;
  }

  public static boolean isWorkDayBasedOnShiftOnly(Date date, String employee) {
    boolean harusMasukKarenaShift = false;
    try {
      OBContext.setAdminMode();
      final String strQuery = "select s.validFromDate, sh.shift from ta_c_bp_shift sh"
          + " inner join sh.businessPartner bp" + " inner join sh.shift s"
          + " where bp.id=?  and ? between sh.validFromDate and sh.validToDate";
      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, employee);
      query.setParameter(1, date);
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      while (result.next()) {
        Date startDate = (Date) result.get()[0];
        TAShift shift = (TAShift) result.get()[1];

        long hari = (date.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);

        OBCriteria<TA_ShiftLine> taShiftLine = OBDal.getInstance().createCriteria(
            TA_ShiftLine.class);
        taShiftLine.add(Restrictions.eq(TA_ShiftLine.PROPERTY_SHIFT, shift));
        List<TA_ShiftLine> shiftLineList = taShiftLine.list();
        int jumlahShiftLine = 0;
        if (shiftLineList != null) {
          jumlahShiftLine = shiftLineList.size();
          Long line = hari % jumlahShiftLine;

          TA_ShiftLine shiftLine = shiftLineList.get(line.intValue());
          harusMasukKarenaShift = !shiftLine.isOff();
          OBContext.restorePreviousMode();
        } else {
          throw new Exception("employee assigned to shift that has no lines");
        }
      }
    } catch (Exception e) {
    }

    if (harusMasukKarenaShift == true) {
      return true;
    }
    return false;
  }

  public static boolean isShiftPresent(Date date, String employee) {
    boolean present = false;
    try {
      final String strQuery = "select s.validFromDate, sh.shift from ta_c_bp_shift sh"
          + " inner join sh.businessPartner bp" + " inner join sh.shift s"
          + " where bp.id=?  and ? between sh.validFromDate and sh.validToDate";
      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, employee);
      query.setParameter(1, date);
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      while (result.next()) {
        TAShift shift = (TAShift) result.get()[1];

        OBContext.setAdminMode();
        OBCriteria<TA_ShiftLine> taShiftLine = OBDal.getInstance().createCriteria(
            TA_ShiftLine.class);
        taShiftLine.add(Restrictions.eq(TA_ShiftLine.PROPERTY_SHIFT, shift));
        List<TA_ShiftLine> shiftLineList = taShiftLine.list();
        if (shiftLineList != null) {
          present = true;
        } else {
          throw new Exception("employee assigned to shift that has no lines");
        }
      }
      OBContext.restorePreviousMode();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return present;
  }

  private static boolean isRequestOvertime(String employee, Date date) {
    boolean lembur = false;
    try {
      final String strQuery = "select ot.id from ot_overtime ot"
          + " inner join ot.businessPartner bp" + " where bp.id=?" + " and ot.documentStatus=?";
      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, employee);
      query.setParameter(1, "CO");
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      while (result.next()) {
        String overtimeId = (String) result.get()[0];

        OBCriteria<ot_overtime> overtime = OBDal.getInstance().createCriteria(ot_overtime.class);
        overtime.add(Restrictions.eq(ot_overtime.PROPERTY_ID, overtimeId));
        overtime.add(Restrictions.le(ot_overtime.PROPERTY_STARTINGDATE, date));
        overtime.add(Restrictions.ge(ot_overtime.PROPERTY_ENDINGDATE, date));
        overtime.setFilterOnActive(true);
        if (overtime.list().size() == 0) {
          lembur = false;
        }
        lembur = true;
      }

    } catch (Exception e) {

    }
    return lembur;
  }

  private static boolean isBusinessTrip(String employee, Date date) {
    boolean dinas = false;
    try {
      final String strQuery = "select bt.id from bt_businesstrip bt"
          + " inner join bt.businessPartner bp" + " where bp.id=?" + " and bt.documentStatus=?";
      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, employee);
      query.setParameter(1, "CO");
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      while (result.next()) {
        String businessTripId = (String) result.get()[0];

        OBCriteria<bt_businesstrip> businesstrip = OBDal.getInstance().createCriteria(
            bt_businesstrip.class);
        businesstrip.add(Restrictions.eq(bt_businesstrip.PROPERTY_ID, businessTripId));
        businesstrip.add(Restrictions.le(bt_businesstrip.PROPERTY_STARTINGDATE, date));
        businesstrip.add(Restrictions.ge(bt_businesstrip.PROPERTY_ENDINGDATE, date));
        if (businesstrip.list().size() > 0) {
          dinas = false;
        }
        dinas = true;
      }
    } catch (Exception e) {

    }
    return dinas;
  }
  
  public static TAShift applicableShift(Date attendanceDate, BusinessPartner employee) {
	  
	  TAShift shift = null;
	
	  try {
		  final String strQuery = "select sh.shift from ta_c_bp_shift sh"
				  + " inner join sh.businessPartner bp" 
				  + " inner join sh.shift s"
				  + " where bp=? and ? between sh.validFromDate and sh.validToDate";
		  final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
		  query.setParameter(0, employee);
		  query.setParameter(1, attendanceDate);
		  final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
		  while (result.next()) {
			  shift = (TAShift) result.get()[0];
		  }
	  } catch(Exception e) {
		 
	 }
	  
	return shift;
	  
}

  public static Date workingStart(Date requestDate, BusinessPartner employee) {
    Date started = null;
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    OBContext.setAdminMode();
    try {
      final String strQuery = "select s.validFromDate, sh.shift from ta_c_bp_shift sh"
          + " inner join sh.businessPartner bp" + " inner join sh.shift s"
          + " where bp=? and ? between sh.validFromDate and sh.validToDate";
      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, employee);
      query.setParameter(1, requestDate);
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      while (result.next()) {
        Date startDate = (Date) result.get()[0];
        TAShift shift = (TAShift) result.get()[1];

        long hari = (requestDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);

        OBCriteria<TA_ShiftLine> taShiftLine = OBDal.getInstance().createCriteria(
            TA_ShiftLine.class);
        taShiftLine.add(Restrictions.eq(TA_ShiftLine.PROPERTY_SHIFT, shift));
        List<TA_ShiftLine> shiftLineList = taShiftLine.list();
        int jumlahShiftLine = 0;
        if (shiftLineList != null) {
          jumlahShiftLine = shiftLineList.size();
          Long line = hari % jumlahShiftLine;

          TA_ShiftLine shiftLine = shiftLineList.get(line.intValue());
          Date checkInTime = shiftLine.getCheckIn();
          started = formatDate.parse(DateIntervalUtility.getDate(requestDate, "dd-MM-yyyy")
              .concat(" ").concat(DateIntervalUtility.getTime(checkInTime)));
        } else {
          throw new Exception("employee assigned to shift that has no lines");
        }
      }
    } catch (Exception e) {
    }
    OBContext.restorePreviousMode();
    return started;
  }

  public static Date workingFinish(Date requestDate, BusinessPartner employee) {
    Date finished = null;
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    OBContext.setAdminMode();
    try {
      final String strQuery = "select s.validFromDate, sh.shift from ta_c_bp_shift sh"
          + " inner join sh.businessPartner bp" + " inner join sh.shift s"
          + " where bp=? and ? between sh.validFromDate and sh.validToDate";
      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, employee);
      query.setParameter(1, requestDate);
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      while (result.next()) {
        Date startDate = (Date) result.get()[0];
        TAShift shift = (TAShift) result.get()[1];

        long hari = (requestDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);

        OBCriteria<TA_ShiftLine> taShiftLine = OBDal.getInstance().createCriteria(
            TA_ShiftLine.class);
        taShiftLine.add(Restrictions.eq(TA_ShiftLine.PROPERTY_SHIFT, shift));
        List<TA_ShiftLine> shiftLineList = taShiftLine.list();
        int jumlahShiftLine = 0;
        if (shiftLineList != null) {
          jumlahShiftLine = shiftLineList.size();
          Long line = hari % jumlahShiftLine;

          TA_ShiftLine shiftLine = shiftLineList.get(line.intValue());
          Date checkoutTime = shiftLine.getCheckOut();
          if (shiftLine.getCheckIn().after(shiftLine.getCheckOut())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(requestDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date date1 = calendar.getTime();
            finished = formatDate.parse(DateIntervalUtility.getDate(date1, "dd-MM-yyyy")
                .concat(" ").concat(DateIntervalUtility.getTime(checkoutTime)));
          } else {
            finished = formatDate.parse(DateIntervalUtility.getDate(requestDate, "dd-MM-yyyy")
                .concat(" ").concat(DateIntervalUtility.getTime(checkoutTime)));
          }
        } else {
          throw new Exception("employee assigned to shift that has no lines");
        }
      }
    } catch (Exception e) {
    }
    OBContext.restorePreviousMode();
    return finished;
  }

  public static Date workingStartSwitced(Date requestDate, BusinessPartner employee) {
    Date started = null;
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    OBContext.setAdminMode();
    try {
      final String strQuery = "select s.validFromDate, sh.shift from ta_c_bp_shift sh"
          + " inner join sh.businessPartner bp" + " inner join sh.shift s"
          + " where bp=? and ? between sh.validFromDate and sh.validToDate";
      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, employee);
      query.setParameter(1, requestDate);
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      while (result.next()) {
        Date startDate = (Date) result.get()[0];
        TAShift shift = (TAShift) result.get()[1];

        long hari = (requestDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);

        OBCriteria<TA_ShiftLine> taShiftLine = OBDal.getInstance().createCriteria(
            TA_ShiftLine.class);
        taShiftLine.add(Restrictions.eq(TA_ShiftLine.PROPERTY_SHIFT, shift));
        List<TA_ShiftLine> shiftLineList = taShiftLine.list();
        int jumlahShiftLine = 0;
        if (shiftLineList != null) {
          jumlahShiftLine = shiftLineList.size();
          Long line = hari % jumlahShiftLine;

          TA_ShiftLine shiftLine = shiftLineList.get(line.intValue());
          Date checkInTime = shiftLine.getCheckIn();
          if (isSwitchShift(requestDate, employee.getId())) {
            started = SwitchShiftIn(requestDate, employee.getId());
          } else {
            started = formatDate.parse(DateIntervalUtility.getDate(requestDate, "dd-MM-yyyy")
                .concat(" ").concat(DateIntervalUtility.getTime(checkInTime)));
          }
        } else {
          throw new Exception("employee assigned to shift that has no lines");
        }
      }
    } catch (Exception e) {
    }
    OBContext.restorePreviousMode();
    return started;
  }

  public static Date workingFinishSwitced(Date requestDate, BusinessPartner employee) {
    Date finished = null;
    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    OBContext.setAdminMode();
    try {
      final String strQuery = "select s.validFromDate, sh.shift from ta_c_bp_shift sh"
          + " inner join sh.businessPartner bp" + " inner join sh.shift s"
          + " where bp=? and ? between sh.validFromDate and sh.validToDate";
      final Query query = OBDal.getInstance().getSession().createQuery(strQuery);
      query.setParameter(0, employee);
      query.setParameter(1, requestDate);
      final ScrollableResults result = query.scroll(ScrollMode.FORWARD_ONLY);
      while (result.next()) {
        Date startDate = (Date) result.get()[0];
        TAShift shift = (TAShift) result.get()[1];

        long hari = (requestDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);

        OBCriteria<TA_ShiftLine> taShiftLine = OBDal.getInstance().createCriteria(
            TA_ShiftLine.class);
        taShiftLine.add(Restrictions.eq(TA_ShiftLine.PROPERTY_SHIFT, shift));
        List<TA_ShiftLine> shiftLineList = taShiftLine.list();
        int jumlahShiftLine = 0;
        if (shiftLineList != null) {
          jumlahShiftLine = shiftLineList.size();
          Long line = hari % jumlahShiftLine;

          TA_ShiftLine shiftLine = shiftLineList.get(line.intValue());
          Date checkoutTime = shiftLine.getCheckOut();
          if (shiftLine.getCheckIn().after(shiftLine.getCheckOut())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(requestDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date date1 = calendar.getTime();
            if (isSwitchShift(requestDate, employee.getId())) {
              finished = SwitchShiftOut(requestDate, employee.getId());
            } else {
              finished = formatDate.parse(DateIntervalUtility.getDate(date1, "dd-MM-yyyy")
                  .concat(" ").concat(DateIntervalUtility.getTime(checkoutTime)));
            }
          } else {
            if (isSwitchShift(requestDate, employee.getId())) {
              finished = SwitchShiftOut(requestDate, employee.getId());
            } else {
              finished = formatDate.parse(DateIntervalUtility.getDate(requestDate, "dd-MM-yyyy")
                  .concat(" ").concat(DateIntervalUtility.getTime(checkoutTime)));
            }
          }
        } else {
          throw new Exception("employee assigned to shift that has no lines");
        }
      }
    } catch (Exception e) {
    }
    OBContext.restorePreviousMode();
    return finished;
  }

  public static AttendancePerformanceBean getAttendancePerformance(Date startDate, Date endDate, BusinessPartner employee){
	  
	  AttendancePerformanceBean output = new AttendancePerformanceBean();
	  
	  //get employee manual schedule
	  HashMap<Date, ManualSchedule> emsMap = getEmployeeManualSchedule(startDate, endDate, employee);
	  
	  //get latest shift rule
	  TAShift shift = getMatchedShiftline(startDate, employee);
	  List<TA_ShiftLine> shiftlineList = shift.getTAShiftLineList();
	  
	  int shiftsize = shiftlineList.size();
	  Date startshift = shift.getValidFromDate();
	  
	  long selisihhari = getDateAge(TimeUnit.DAYS, startshift.getTime(), startDate.getTime());
	  Long i = selisihhari % shiftsize;
	  
	  //selisih hari dengan start shift
	  
	  //iterate from startDate to endDate
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(startDate);
	  while (true){
		  i++;
		  
		  Date documentDate=cal.getTime();
		  if (emsMap.containsKey(documentDate))
			  doAttendancePeformanceByManualSchedule(output, i, employee, documentDate, emsMap);
		  else if (isShiftPresent(documentDate, employee.getId()))
			  doAttendancePeformanceByShiftRule(output, i, shiftlineList, employee, cal);
		  else
			  throw new OBException("employee "+employee.getSearchKey()+"-"+employee.getName()
			  		  + " does not have either shift rule nor manual schedule"
					  + " on "+documentDate);

		  cal.add(Calendar.DAY_OF_MONTH, 1); //incremental 1 hari
		  if (cal.getTime().after(endDate))
			  break;
	  }
	  
	  return output;
  }

  private static void doAttendancePeformanceByManualSchedule(AttendancePerformanceBean output, Long i,
		BusinessPartner employee, Date documentDate, HashMap<Date, ManualSchedule> emsMap) {
	  
	  ManualSchedule ms = emsMap.get(documentDate);
	  
	  if (!ms.isOff()){
		  //hari kerja
		  int harikerja = output.getHarikerja()+1;
		  output.setHarikerja(harikerja);

		  //jam kerja
		  int selisihjam = getTimeAge(TimeUnit.HOURS,
				  ms.getCheckIn().getTime(), ms.getCheckOut().getTime()).intValue();
		  int jamkerja = output.getTotaljamkerja()+selisihjam;
		  output.setTotaljamkerja(jamkerja);

		  //cuti
		  boolean iscuti = getCuti(documentDate, employee);
		  if (iscuti){
			  int cuti = output.getCuti()+1;
			  output.setCuti(cuti);
		  }

		  //perjalanan dinas
		  boolean isperjalanandinas = getPerjalananDinas(documentDate, employee);
		  if (isperjalanandinas){
			  int perjalanandinas = output.getPerjalanandinas()+1;
			  output.setPerjalanandinas(perjalanandinas);
		  }

		  if (iscuti || isperjalanandinas){
			  int netjamkerja = output.getTotaljamkerja()-selisihjam;
			  output.setNettotaljamkerja(netjamkerja);
		  }

		  if (!iscuti && !isperjalanandinas && !ms.isOff()){
			  //wajib masuk kerja, maka lihat table absensi
			  TAAttendance attendance = getAttendance(employee, documentDate);
			  if (attendance==null){
				  //tidak masuk
				  int tidakmasuk = output.getTidakmasuk()+1;
				  output.setTidakmasuk(tidakmasuk);
			  } else{
				  //terlambat
				  String checkinstatus=attendance.getCheckinStatus();
				  if (checkinstatus.equalsIgnoreCase("FORGET")){
					  //lupa check in
					  int lupacheckin = output.getLupacheckin()+1;
					  output.setLupacheckin(lupacheckin);
				  } else if (checkinstatus.equalsIgnoreCase("LATE")){
					  //terlambat
					  int terlambat = output.getTerlambat()+1;
					  output.setTerlambat(terlambat);
					  Date checkin = attendance.getCheckin();
					  Long durasiterlambat = getTimeAge(TimeUnit.MINUTES, ms.getCheckIn().getTime(), checkin.getTime());
					  durasiterlambat=output.getDurasiterlambat()+durasiterlambat;
					  output.setDurasiterlambat(durasiterlambat.intValue());
				  }

				  //pulang cepat
				  String checkoutstatus = attendance.getCheckinStatus();
				  if (checkoutstatus.equalsIgnoreCase("FORGET")){
					  //lupa check out
					  int lupacheckout = output.getLupacheckout()+1;
					  output.setLupacheckout(lupacheckout);
				  } else if (checkoutstatus.equalsIgnoreCase("EARLY")){
					  //pulang cepat
					  int pulangcepat = output.getPulangcepat()+1;
					  output.setPulangcepat(pulangcepat);
					  Date checkout = attendance.getCheckout();
					  Long durasipulangcepat = getTimeAge(TimeUnit.MINUTES, checkout.getTime(), ms.getCheckOut().getTime());
					  durasipulangcepat=durasipulangcepat*-1;
					  durasipulangcepat=output.getDurasipulangcepat()+durasipulangcepat;
					  output.setDurasipulangcepat(durasipulangcepat.intValue());
				  }
			  }
		  }
	  }	
}

private static void doAttendancePeformanceByShiftRule(AttendancePerformanceBean output,  
		  long i, List<TA_ShiftLine> shiftlineList, BusinessPartner employee, Calendar cal) {
	  
	  int shiftsize = shiftlineList.size();
	  Long line;
	  if (i>=shiftsize)
		  line = i%shiftsize;
	  else line=i;
	  TA_ShiftLine sl = shiftlineList.get(line.intValue());
	  if (!sl.isOff()){
		  //hari kerja
		  int harikerja = output.getHarikerja()+1;
		  output.setHarikerja(harikerja);

		  //jam kerja
		  int selisihjam = getTimeAge(TimeUnit.HOURS, 
				  sl.getCheckIn().getTime(), sl.getCheckOut().getTime()).intValue();
		  int jamkerja = output.getTotaljamkerja()+selisihjam;
		  output.setTotaljamkerja(jamkerja);

		  //cuti
		  boolean iscuti = getCuti(cal.getTime(), employee);
		  if (iscuti){
			  int cuti = output.getCuti()+1;
			  output.setCuti(cuti);
		  }

		  //perjalanan dinas
		  boolean isperjalanandinas = getPerjalananDinas(cal.getTime(), employee);
		  if (isperjalanandinas){
			  int perjalanandinas = output.getPerjalanandinas()+1;
			  output.setPerjalanandinas(perjalanandinas);
		  }

		  if (iscuti || isperjalanandinas){
			  int netjamkerja = output.getTotaljamkerja()-selisihjam;
			  output.setNettotaljamkerja(netjamkerja);
		  }

		  if (!iscuti && !isperjalanandinas && !sl.isOff()){
			  //wajib masuk kerja, maka lihat table absensi
			  TAAttendance attendance = getAttendance(employee, cal.getTime());
			  if (attendance==null){
				  //tidak masuk
				  int tidakmasuk = output.getTidakmasuk()+1;
				  output.setTidakmasuk(tidakmasuk);
			  } else{
				  //terlambat
				  String checkinstatus=attendance.getCheckinStatus();
				  if (checkinstatus.equalsIgnoreCase("FORGET")){
					  //lupa check in
					  int lupacheckin = output.getLupacheckin()+1;
					  output.setLupacheckin(lupacheckin);
				  } else if (checkinstatus.equalsIgnoreCase("LATE")){
					  //terlambat
					  int terlambat = output.getTerlambat()+1;
					  output.setTerlambat(terlambat);
					  Date checkin = attendance.getCheckin();
					  Long durasiterlambat = getTimeAge(TimeUnit.MINUTES, sl.getCheckIn().getTime(), checkin.getTime());
					  durasiterlambat=output.getDurasiterlambat()+durasiterlambat;
					  output.setDurasiterlambat(durasiterlambat.intValue());
				  }

				  //pulang cepat
				  String checkoutstatus = attendance.getCheckinStatus();
				  if (checkoutstatus.equalsIgnoreCase("FORGET")){
					  //lupa check out
					  int lupacheckout = output.getLupacheckout()+1;
					  output.setLupacheckout(lupacheckout);
				  } else if (checkoutstatus.equalsIgnoreCase("EARLY")){
					  //pulang cepat
					  int pulangcepat = output.getPulangcepat()+1;
					  output.setPulangcepat(pulangcepat);
					  Date checkout = attendance.getCheckout();
					  Long durasipulangcepat = getTimeAge(TimeUnit.MINUTES, checkout.getTime(), sl.getCheckOut().getTime());
					  durasipulangcepat=durasipulangcepat*-1;
					  durasipulangcepat=output.getDurasipulangcepat()+durasipulangcepat;
					  output.setDurasipulangcepat(durasipulangcepat.intValue());
				  }
			  }

		  }

	  }

  }

private static HashMap<Date, ManualSchedule> getEmployeeManualSchedule(Date startDate, Date endDate,
		BusinessPartner employee) {
	OBCriteria<ManualSchedule> msCriteria = OBDal.getInstance().createCriteria(ManualSchedule.class);
	msCriteria.add(Restrictions.ge(ManualSchedule.PROPERTY_DOCUMENTDATE, startDate));
	msCriteria.add(Restrictions.le(ManualSchedule.PROPERTY_DOCUMENTDATE, endDate));
	msCriteria.add(Restrictions.eq(ManualSchedule.PROPERTY_EMPLOYEE, employee));
	msCriteria.addOrderBy(ManualSchedule.PROPERTY_DOCUMENTDATE, true);
	
	HashMap<Date, ManualSchedule> output = new HashMap<>();
	for (ManualSchedule ms : msCriteria.list()){
		output.put(ms.getDocumentDate(), ms);
	}
	return output;
}

private static TAAttendance getAttendance(BusinessPartner employee, Date time) {
	OBCriteria<TAAttendance> taaCriteria = OBDal.getInstance().createCriteria(TAAttendance.class);
	taaCriteria.add(Restrictions.eq(TAAttendance.PROPERTY_EMPLOYEE, employee));
	taaCriteria.add(Restrictions.eq(TAAttendance.PROPERTY_ATTANDANCEDATE, time));
	List<TAAttendance> taaList = taaCriteria.list();
	if (taaList!=null && taaList.size()>0)
		return taaList.get(0);
	
	return null;
}

  private static boolean getPerjalananDinas(Date time, BusinessPartner employee) {
	OBCriteria<bt_businesstrip> btCriteria = OBDal.getInstance().createCriteria(bt_businesstrip.class);
	btCriteria.add(Restrictions.eq(bt_businesstrip.PROPERTY_BUSINESSPARTNER, employee));
	btCriteria.add(Restrictions.eq(bt_businesstrip.PROPERTY_STARTINGDATE, time));
	List<bt_businesstrip> btList = btCriteria.list();
	if (btList!=null && btList.size()>0)
		return true;
	
	return false;
}

  private static boolean getCuti(Date time, BusinessPartner employee) {
	OBCriteria<lv_c_bp_leave> leaveCriteria = OBDal.getInstance().createCriteria(lv_c_bp_leave.class);
	leaveCriteria.add(Restrictions.eq(lv_c_bp_leave.PROPERTY_BUSINESSPARTNER, employee));
	leaveCriteria.add(Restrictions.le(lv_c_bp_leave.PROPERTY_VALIDFROMDATE, time));
	leaveCriteria.add(Restrictions.ge(lv_c_bp_leave.PROPERTY_VALIDTODATE, time));
	List<lv_c_bp_leave> leaveList = leaveCriteria.list();
	if (leaveList!=null && leaveList.size()>0)
		return true;
	
	return false;
}

  private static Long getTimeAge(TimeUnit timeunit, long time, long time2) {
	Calendar cal = Calendar.getInstance();
	cal.setTimeInMillis(time);
	cal.set(Calendar.DAY_OF_MONTH, 1);
	cal.set(Calendar.MONTH, 0);
	cal.set(Calendar.YEAR, 1970);
	long start = cal.getTimeInMillis();
	cal.setTimeInMillis(time2);
	cal.set(Calendar.DAY_OF_MONTH, 1);
	cal.set(Calendar.MONTH, 0);
	cal.set(Calendar.YEAR, 1970);
	long end = cal.getTimeInMillis();
	Long diff = end-start;
	Long selisih = timeunit.convert(diff, TimeUnit.MILLISECONDS);
		
	return selisih;
}

  private static Long getDateAge(TimeUnit timeunit, long time, long time2) {
	Long diff = time2-time;
	Long selisih = timeunit.convert(diff, TimeUnit.MILLISECONDS);
		
	return selisih;
}

  private static TAShift getMatchedShiftline(Date startDate, BusinessPartner employee) {
	OBCriteria<ta_c_bp_shift> employeeShiftCrit = OBDal.getInstance().createCriteria(ta_c_bp_shift.class);
	employeeShiftCrit.add(Restrictions.eq(ta_c_bp_shift.PROPERTY_BUSINESSPARTNER, employee));
	employeeShiftCrit.add(Restrictions.le(ta_c_bp_shift.PROPERTY_VALIDFROMDATE, startDate));
	employeeShiftCrit.addOrderBy(ta_c_bp_shift.PROPERTY_VALIDFROMDATE, false);
	List<ta_c_bp_shift> employeeShiftList = employeeShiftCrit.list();
	if (employeeShiftList!=null && employeeShiftList.size()>0)
		return employeeShiftList.get(0).getShift();
	
	return null;
}
}
