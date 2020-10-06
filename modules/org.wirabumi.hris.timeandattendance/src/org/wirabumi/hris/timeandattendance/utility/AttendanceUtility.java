package org.wirabumi.hris.timeandattendance.utility;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
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
import org.openbravo.model.ad.domain.Preference;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.financialmgmt.calendar.NonBusinessDay;
import org.wirabumi.gen.oez.utility.DateIntervalUtility;
import org.wirabumi.hris.businesstrip.bt_businesstrip;
import org.wirabumi.hris.leave.lv_category;
import org.wirabumi.hris.leave.lv_leave;
import org.wirabumi.hris.leave.lv_permit;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.timeandattendance.ManualSchedule;
import org.wirabumi.hris.timeandattendance.TAAttendance;
import org.wirabumi.hris.timeandattendance.TAShift;
import org.wirabumi.hris.timeandattendance.TA_ShiftLine;
import org.wirabumi.hris.timeandattendance.ta_c_bp_shift;
import org.wirabumi.hris.timeandattendance.ta_tukar_shift;

public class AttendanceUtility {

	private static int standardRest;
	private static int nightStartRestTime;
	private static int normalStartRestTime;
  
	private static int normalSundayRest;
	private static int normalMondayRest;
	private static int normalTuesdayRest;
	private static int normalWednesdayRest;
	private static int normalThursdayRest;
	private static int normalFridayRest;
	private static int normalSaturdayRest;

	private static int nightSundayRest;
	private static int nightMondayRest;
	private static int nightTuesdayRest;
	private static int nightWednesdayRest;
	private static int nightThursdayRest;
	private static int nightFridayRest;
	private static int nightSaturdayRest;

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
		  getRestPreference();
		  //get employee manual schedule
		  HashMap<Date, ManualSchedule> emsMap = getEmployeeManualSchedule(startDate, endDate, employee);
		  
		  //get latest shift rule
		  TAShift shift = getMatchedShiftline(startDate, employee);
	
		  if(shift == null)
			  throw new OBException("employee "+employee.getSearchKey()+"-"+employee.getName()
			  + " does not have either employee shift rule nor manual schedule"
			  + " on "+startDate);
		  
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
			  
			  Date documentDate=cal.getTime();
			  if (emsMap.containsKey(documentDate))
				  doAttendancePeformanceByManualSchedule(output, i, employee, documentDate, emsMap);
			  else if (isShiftPresent(documentDate, employee.getId()))
				  doAttendancePeformanceByShiftRule(output, i, shiftlineList, employee, cal);
			  else
				  throw new OBException("employee "+employee.getSearchKey()+"-"+employee.getName()
				  		  + " does not have either shift rule nor manual schedule"
						  + " on "+documentDate);
			  i++;
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
		  line = (long) (cal.get(Calendar.DAY_OF_WEEK) - 1);
		  TA_ShiftLine sl = shiftlineList.get(line.intValue());
		  if (!sl.isOff() && !isNonBusinessDay(cal.getTime())){
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
					  boolean isNightShift = attendance.getShift().isNightShift();
					  if (isNightShift) {
						  int nightShift = output.getNightShift()+1;
						  output.setNightShift(nightShift);
					  }
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
						  Calendar cal2 = Calendar.getInstance();
						  cal2.setTimeInMillis(attendance.getLate().getTime());
						  int hour = cal2.get(Calendar.HOUR_OF_DAY);
						  int minute = cal2.get(Calendar.MINUTE);
						  int lateDuration = output.getDurasiterlambat() + (hour * 60) + minute;
						  output.setDurasiterlambat(lateDuration);
					  }
	
					  //pulang cepat
					  String checkoutstatus = attendance.getCheckoutStatus();
					  if(checkoutstatus == null)
						  throw new OBException("employee "+employee.getSearchKey()+"-"+employee.getName()
						  + " does not have check out"
						  + " on "+attendance.getAttandanceDate());
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
					  
					  List<lv_permit> permitList = getHourlyUnpaidLeave(cal.getTime(), employee);
					  if (!permitList.isEmpty()) {
						  int leaveDuration = 0;
						  // there is a chance of adjustment if use by another client
						  for(lv_permit permit : permitList) {
							  int restTime = 0;
							  if (isNightShift) {
								  Calendar cal2 = Calendar.getInstance();
								  cal2.setTimeInMillis(attendance.getAttandanceDate().getTime());
								  int day = cal2.get(Calendar.DAY_OF_WEEK) - 1;
								  cal2.setTimeInMillis(permit.getStarttime().getTime());
								  int hour = cal2.get(Calendar.HOUR_OF_DAY);
								  int minute = cal2.get(Calendar.MINUTE);
								  int attCheckout = (hour * 60) + minute;
								  int startDay = cal2.get(Calendar.DAY_OF_MONTH);
								  cal2.setTimeInMillis(permit.getEndtime().getTime());
								  int endDay = cal2.get(Calendar.DAY_OF_MONTH);
								  switch(day) {
								  	case 0:
								  		restTime = nightSundayRest;
								  		break;
								  	case 1:
								  		restTime = nightMondayRest;
								  		break;
								  	case 2:
								  		restTime = nightTuesdayRest;
								  		break;
								  	case 3:
								  		restTime = nightWednesdayRest;
								  		break;
								  	case 4:
								  		restTime = nightThursdayRest;
								  		break;
								  	case 5:
								  		restTime = nightFridayRest;
								  		break;
								  	case 6:
								  		restTime = nightSaturdayRest;
								  		break;
								  }
								  if(restTime == 0) {
									  restTime = standardRest;
								  }
								  
								  if(startDay == endDay) {
									  attCheckout = attCheckout + 1440;
								  }

								  if(attCheckout >= nightStartRestTime && attCheckout <= nightStartRestTime + restTime) {
									  restTime = (nightStartRestTime + restTime) - attCheckout;
								  } else if (attCheckout > nightStartRestTime + restTime) {
									  restTime = 0;
								  }
							  } else {
								  Calendar cal2 = Calendar.getInstance();
								  cal2.setTimeInMillis(attendance.getAttandanceDate().getTime());
								  int day = cal2.get(Calendar.DAY_OF_WEEK) - 1;
								  cal2.setTimeInMillis(permit.getStarttime().getTime());
								  int hour = cal2.get(Calendar.HOUR_OF_DAY);
								  int minute = cal2.get(Calendar.MINUTE);
								  int attCheckout = (hour * 60) + minute;
								  switch(day) {
								  	case 0:
								  		restTime = normalSundayRest;
								  		break;
								  	case 1:
								  		restTime = normalMondayRest;
								  		break;
								  	case 2:
								  		restTime = normalTuesdayRest;
								  		break;
								  	case 3:
								  		restTime = normalWednesdayRest;
								  		break;
								  	case 4:
								  		restTime = normalThursdayRest;
								  		break;
								  	case 5:
								  		restTime = normalFridayRest;
								  		break;
								  	case 6:
								  		restTime = normalSaturdayRest;
								  		break;
								  }
								  if(restTime == 0) {
									  restTime = standardRest;
								  }
								  if(attCheckout >= normalStartRestTime && attCheckout <= normalStartRestTime + restTime) {
									  restTime = (normalStartRestTime + restTime) - attCheckout;
								  } else if (attCheckout > normalStartRestTime + restTime) {
									  restTime = 0;
								  }
							  }
							  long unpaidHourLeaveDuration = permit.getEndtime().getTime() - permit.getStarttime().getTime();
							  Calendar cal2 = Calendar.getInstance();
							  cal2.setTimeZone(TimeZone.getTimeZone("UTC"));
							  cal2.setTimeInMillis(unpaidHourLeaveDuration);
							  int hour = cal2.get(Calendar.HOUR_OF_DAY);
							  int minute = cal2.get(Calendar.MINUTE);
							  leaveDuration += (hour * 60) + minute - restTime;
						  }
						  leaveDuration += output.getUnpaidLeaveDuration();
						  output.setUnpaidLeaveDuration(leaveDuration);
						  int unpaidLeave = output.getUnpaidLeave()+1;
						  output.setUnpaidLeave(unpaidLeave);
					  }
				  }
			  }
		  }
	  }
	
	private static void getRestPreference() {
		LocalTime localTime;
		//load night start rest rest preference
		OBCriteria<Preference> preferenceNormalStartRestTime = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNormalStartRestTime.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "night start rest time"));
		preferenceNormalStartRestTime.setFetchSize(1);
		List<Preference> preferenceNormalStartRestTimeList = preferenceNormalStartRestTime.list();
		for (Preference preference : preferenceNormalStartRestTimeList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightStartRestTime =  (localTime.getHour() *60) + localTime.getMinute();
			if(nightStartRestTime == 0) {
				nightStartRestTime = 1440;
			}
		}
		
		//load normal start rest preference
		OBCriteria<Preference> preferenceNightStartRestTime = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNightStartRestTime.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "normal start rest time"));
		preferenceNightStartRestTime.setFetchSize(1);
		List<Preference> preferenceNightStartRestTimeList = preferenceNightStartRestTime.list();
		for (Preference preference : preferenceNightStartRestTimeList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			normalStartRestTime =  (localTime.getHour() *60) + localTime.getMinute();
		}
		//load standard rest preference
		OBCriteria<Preference> preferenceStandardRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceStandardRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "attendance rest rule"));
		preferenceStandardRest.setFetchSize(1);
		List<Preference> preferenceStandardRestList = preferenceStandardRest.list();
		for (Preference preference : preferenceStandardRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
    		standardRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		
		//load normal rest preference
		OBCriteria<Preference> preferenceNormalSundayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNormalSundayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "normal attendance rest 0"));
		preferenceNormalSundayRest.setFetchSize(1);
		List<Preference> preferenceNormalSundayRestList = preferenceNormalSundayRest.list();
		for (Preference preference : preferenceNormalSundayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightSundayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNormalMondayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNormalMondayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "normal attendance rest 1"));
		preferenceNormalMondayRest.setFetchSize(1);
		List<Preference> preferenceNormalMondayRestList = preferenceNormalMondayRest.list();
		for (Preference preference : preferenceNormalMondayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			normalMondayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNormalTuesdayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNormalTuesdayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "normal attendance rest 2"));
		preferenceNormalTuesdayRest.setFetchSize(1);
		List<Preference> preferenceNormalTuesdayRestList = preferenceNormalTuesdayRest.list();
		for (Preference preference : preferenceNormalTuesdayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			normalTuesdayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNormalWednesdayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNormalWednesdayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "normal attendance rest 3"));
		preferenceNormalWednesdayRest.setFetchSize(1);
		List<Preference> preferenceNormalWednesdayRestList = preferenceNormalWednesdayRest.list();
		for (Preference preference : preferenceNormalWednesdayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			normalWednesdayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNormalThursdayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNormalThursdayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "normal attendance rest 4"));
		preferenceNormalThursdayRest.setFetchSize(1);
		List<Preference> preferenceNormalThursdayRestList = preferenceNormalThursdayRest.list();
		for (Preference preference : preferenceNormalThursdayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			normalThursdayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNormalFridayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNormalFridayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "normal attendance rest 5"));
		preferenceNormalFridayRest.setFetchSize(1);
		List<Preference> preferenceNormalFridayRestList = preferenceNormalFridayRest.list();
		for (Preference preference : preferenceNormalFridayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			normalFridayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNormalSaturdayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNormalSaturdayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "normal attendance rest 6"));
		preferenceNormalSaturdayRest.setFetchSize(1);
		List<Preference> preferenceNormalSaturdayRestList = preferenceNormalSaturdayRest.list();
		for (Preference preference : preferenceNormalSaturdayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			normalSaturdayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		
		//load night rest preference
		OBCriteria<Preference> preferenceNightSundayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNightSundayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "night attendance rest 0"));
		preferenceNightSundayRest.setFetchSize(1);
		List<Preference> preferenceNightSundayRestList = preferenceNightSundayRest.list();
		for (Preference preference : preferenceNightSundayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightSundayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNightMondayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNightMondayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "night attendance rest 1"));
		preferenceNightMondayRest.setFetchSize(1);
		List<Preference> preferenceNightMondayRestList = preferenceNightMondayRest.list();
		for (Preference preference : preferenceNightMondayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightMondayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNightTuesdayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNightTuesdayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "night attendance rest 2"));
		preferenceNightTuesdayRest.setFetchSize(1);
		List<Preference> preferenceNightTuesdayRestList = preferenceNightTuesdayRest.list();
		for (Preference preference : preferenceNightTuesdayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightTuesdayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNightWednesdayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNightWednesdayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "night attendance rest 3"));
		preferenceNightWednesdayRest.setFetchSize(1);
		List<Preference> preferenceNightWednesdayRestList = preferenceNightWednesdayRest.list();
		for (Preference preference : preferenceNightWednesdayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightWednesdayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNightThursdayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNightThursdayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "night attendance rest 4"));
		preferenceNightThursdayRest.setFetchSize(1);
		List<Preference> preferenceNightThursdayRestList = preferenceNightThursdayRest.list();
		for (Preference preference : preferenceNightThursdayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightThursdayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNightFridayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNightFridayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "night attendance rest 5"));
		preferenceNightFridayRest.setFetchSize(1);
		List<Preference> preferenceNightFridayRestList = preferenceNightFridayRest.list();
		for (Preference preference : preferenceNightFridayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightFridayRest =  (localTime.getHour() *60) + localTime.getMinute();
		}
		OBCriteria<Preference> preferenceNightSaturdayRest = OBDal.getInstance().createCriteria(Preference.class);
		preferenceNightSaturdayRest.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "night attendance rest 6"));
		preferenceNightSaturdayRest.setFetchSize(1);
		List<Preference> preferenceNightSaturdayRestList = preferenceNightSaturdayRest.list();
		for (Preference preference : preferenceNightSaturdayRestList) {
			localTime = LocalTime.parse(preference.getSearchKey());
			nightSaturdayRest =  (localTime.getHour() *60) + localTime.getMinute();
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
		OBCriteria<lv_leave> leaveCriteria = OBDal.getInstance().createCriteria(lv_leave.class);
		leaveCriteria.add(Restrictions.eq(lv_leave.PROPERTY_EMPLOYEE, employee));
		leaveCriteria.add(Restrictions.eq(lv_leave.PROPERTY_DOCUMENTSTATUS, "CO"));
		leaveCriteria.add(Restrictions.le(lv_leave.PROPERTY_STARTINGDATE, time));
		leaveCriteria.add(Restrictions.ge(lv_leave.PROPERTY_ENDINGDATE, time));
		List<lv_leave> leaveList = leaveCriteria.list();
		if (leaveList!=null && leaveList.size()>0)
			return true;
		
		OBCriteria<lv_permit> permitCriteria = OBDal.getInstance().createCriteria(lv_permit.class);
		permitCriteria.add(Restrictions.eq(lv_permit.PROPERTY_BPARTNER, employee));
		permitCriteria.add(Restrictions.le(lv_permit.PROPERTY_STARTDATE, time));
		permitCriteria.add(Restrictions.ge(lv_permit.PROPERTY_ENDDATE, time));
		List<lv_permit> permitList = permitCriteria.list();
		for(lv_permit permit : permitList) {
			lv_category category = OBDal.getInstance().get(lv_category.class, permit.getCategory().getId());
			if(category.isPaid())
				return true;
		}
		
		return false;
	}
	  
	  private static List<lv_permit> getHourlyUnpaidLeave(Date time, BusinessPartner employee) {
		OBCriteria<lv_permit> permitCriteria = OBDal.getInstance().createCriteria(lv_permit.class);
		permitCriteria.add(Restrictions.eq(lv_permit.PROPERTY_BPARTNER, employee));
		permitCriteria.add(Restrictions.eq(lv_permit.PROPERTY_LEAVEDATE, time));
		permitCriteria.add(Restrictions.eq(lv_permit.PROPERTY_ISHOUR, true));
		List<lv_permit> permitList = new ArrayList<lv_permit>();
		for(lv_permit permit : permitCriteria.list()) {
			lv_category category = OBDal.getInstance().get(lv_category.class, permit.getCategory().getId());
			if(category.isPaid() == false)
				permitList.add(permit);
		}	
		return permitList;
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
