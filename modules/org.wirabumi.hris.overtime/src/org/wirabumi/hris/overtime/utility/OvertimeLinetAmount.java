package org.wirabumi.hris.overtime.utility;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.wirabumi.gen.oez.utility.DateIntervalUtility;
import org.wirabumi.hris.overtime.ot_business_rate;
import org.wirabumi.hris.overtime.ot_nonbusiness_rate;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.overtime.ot_overtime_detail;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

public class OvertimeLinetAmount {
  
  public static long getOvertimeAmount(ot_overtime overtimeRequest, String Position, Date checkIn,
      Date checkOut) {
	if (overtimeRequest.isManual()){
		return calculateManualOvertimeAmount(overtimeRequest);
	}
    long amount = 0;
    try {
      if (DateUtils.isSameDay(checkIn, checkOut)) {
        double DayDiff = DateIntervalUtility.getDay(checkIn, checkOut);
        double Duration = (DayDiff * 24);

        if (AttendanceUtility.isHoliday(checkIn, overtimeRequest.getBusinessPartner())) {
          amount = holiDayRate(overtimeRequest, Position, checkIn, checkOut, Duration);
        } else {
          amount = workDayRate(overtimeRequest, Position, checkIn, checkOut, Duration);
        }
      } else {// lintas hari
        boolean checkInHolyDay = AttendanceUtility.isHoliday(checkIn,
            overtimeRequest.getBusinessPartner());
        boolean checkOutHolyDay = AttendanceUtility.isHoliday(checkOut,
            overtimeRequest.getBusinessPartner());
        if (checkInHolyDay == checkOutHolyDay) {
          double DayDiff = DateIntervalUtility.getDay(checkIn, checkOut);
          double Duration = (DayDiff * 24);
          if (checkInHolyDay == true && checkOutHolyDay == true) {
            amount = holiDayRate(overtimeRequest, Position, checkIn, checkOut, Duration);
          } else if (checkInHolyDay == false && checkOutHolyDay == false) {
            amount = workDayRate(overtimeRequest, Position, checkIn, checkOut, Duration);
          }
        } else {
          if (checkInHolyDay == true && checkOutHolyDay == false) {
            Date validCheckOutToday = DateIntervalUtility.clearTime(checkOut);
            double durHolyDay = DateIntervalUtility.getDay(checkIn, validCheckOutToday) * 24;
            amount = holiDayRate(overtimeRequest, Position, checkIn, validCheckOutToday, durHolyDay);
            double durWorkDay = DateIntervalUtility.getDay(validCheckOutToday, checkOut) * 24;
            amount += workDayRate(overtimeRequest, Position, validCheckOutToday, checkOut,
                durWorkDay);
          } else if (checkInHolyDay == false && checkOutHolyDay == true) {
            Date validCheckOutToday = DateIntervalUtility.clearTime(checkOut);
            double durWorkDay = DateIntervalUtility.getDay(checkIn, validCheckOutToday) * 24;
            amount = workDayRate(overtimeRequest, Position, checkIn, validCheckOutToday, durWorkDay);
            double durHolyDay = DateIntervalUtility.getDay(validCheckOutToday, checkOut) * 24;
            amount += holiDayRate(overtimeRequest, Position, validCheckOutToday, checkOut,
                durHolyDay);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return amount;
  }

  private static long calculateManualOvertimeAmount(ot_overtime overtimeRequest) {
	  
	  Date startDate = overtimeRequest.getStartingDate();
	  Date endingDate = overtimeRequest.getEndingDate();
	  long overtimeDayDiff = endingDate.getTime()-startDate.getTime();
	  
	  String position=null;
      if (overtimeRequest.getPosition()!=null)
    	  position=overtimeRequest.getPosition().getSearchKey();
	  SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	  
	  Date checkIn=null;
	  Date checkOut=null;
	  try {
		checkIn = formatDate.parse(DateIntervalUtility
		          .getDate(startDate, "dd-MM-yyyy").concat(" ")
		          .concat(DateIntervalUtility.getTime(overtimeRequest.getCheckin())));
		checkOut = formatDate.parse(DateIntervalUtility
	              .getDate(endingDate, "dd-MM-yyyy").concat(" ")
	              .concat(DateIntervalUtility.getTime(overtimeRequest.getCheckout())));
	  } catch (ParseException e) {
		e.printStackTrace();
		return 0;
	  }
	  
	  Calendar calIn = Calendar.getInstance();
	  calIn.setTime(checkIn);
	  
	  Calendar calOut = Calendar.getInstance();
	  calOut.setTime(checkOut);
	  
	  if (overtimeDayDiff>0)
		  return calculateManualOvertimeAmountGantiHari(overtimeRequest,
					position, checkIn, checkOut);
	  
	  //hitung durasi jam lembur
	  overtimeDayDiff = calOut.getTimeInMillis()-calIn.getTimeInMillis();
	  double hours  = ((overtimeDayDiff / (1000*60*60)));
	  long amountLembur = 0;
	  if (overtimeRequest.isCheckinonholiday())
		  amountLembur = holiDayRate(overtimeRequest, position, checkIn, checkOut, new Double(hours));
	  else
		  amountLembur = workDayRate(overtimeRequest, position, checkIn, checkOut, new Double(hours));
	  
	return amountLembur;
}

private static long calculateManualOvertimeAmountGantiHari(
		ot_overtime overtimeRequest, String position, Date checkIn,
		Date checkOut) {
	Calendar calIn = Calendar.getInstance();
	calIn.setTime(checkIn);
	Calendar calOut = Calendar.getInstance();
	calOut.setTime(checkOut);
	Calendar tengahHari = Calendar.getInstance();
	tengahHari.setTime(checkOut);
	tengahHari.set(Calendar.HOUR, 0);
	tengahHari.set(Calendar.MINUTE, 0);
	tengahHari.set(Calendar.SECOND, 0);
	tengahHari.set(Calendar.MILLISECOND, 0);
	long durasiAwal = tengahHari.getTimeInMillis()-calIn.getTimeInMillis();
	double jamAwal = ((durasiAwal / (1000*60*60)));
	long amountLemburAwal = 0;
	if (overtimeRequest.isCheckinonholiday())
		  amountLemburAwal = holiDayRate(overtimeRequest, position, checkIn, checkOut, new Double(jamAwal));
	else
		  amountLemburAwal = workDayRate(overtimeRequest, position, checkIn, checkOut, new Double(jamAwal));
	
	long durasiAkhir = calOut.getTimeInMillis()-tengahHari.getTimeInMillis();
	double jamAkhir = ((durasiAkhir / (1000*60*60)));
	long amountLemburAkhir = 0;
	if (overtimeRequest.isCheckoutonholiday())
		  amountLemburAkhir = holiDayRate(overtimeRequest, position, checkIn, checkOut, new Double(jamAkhir));
	else
		  amountLemburAkhir = workDayRate(overtimeRequest, position, checkIn, checkOut, new Double(jamAkhir));
	
	  
	return amountLemburAwal+amountLemburAkhir;
}

public static long holiDayRate(ot_overtime overtimeRequest, String Position, Date checkIn,
      Date checkOut, Double Duration) {
    long amount = 0;
    double tmpAmount = 0;
    Date tmpStart = null;
    Date tmpEnd = null;
    double tmpDuration = 0;
    OBContext.setAdminMode();
    StringBuilder whereClause = new StringBuilder();
    whereClause.append(" as bsnday where "
        + "bsnday.setOvertime.validFromDate<=? and bsnday.setOvertime.validToDate>=?");
    List<Object> params = new ArrayList<Object>();
    params.add(checkIn);
    params.add(checkOut);
    if (Position != null && !Position.equals("")) {
      whereClause.append("and bsnday.setOvertime.position=?");
      params.add(Position);
    }
    whereClause.append("order by bsnday.minlevel");
    OBQuery<ot_nonbusiness_rate> nonBusinessDay = OBDal.getInstance().createQuery(
        ot_nonbusiness_rate.class, whereClause.toString());
    nonBusinessDay.setParameters(params);
    List<ot_nonbusiness_rate> rateOvertime = nonBusinessDay.list();
    Long line = (long) 10;
    for (ot_nonbusiness_rate rate : rateOvertime) {
      if (Duration <= rate.getMaxlevel().doubleValue()) {
        tmpAmount = (rate.getSetOvertime().getTarif().doubleValue() * rate.getMultiplier()
            .doubleValue()) * (Duration - rate.getMinlevel().doubleValue());
        amount += tmpAmount;
        tmpStart = tmpStart == null ? checkIn : tmpEnd;
        tmpDuration = (Duration - rate.getMinlevel().doubleValue());
        tmpEnd = DateIntervalUtility.adjustSecond(tmpStart,
            DateIntervalUtility.getHourSecond(tmpDuration));
        creteOvertimeLines(overtimeRequest, false, tmpStart, tmpEnd, tmpDuration,
            rate.getMultiplier(), tmpAmount, line);
        // System.out.println("Holiday : yes Start Time :" + tmpStart + " End period :" + tmpEnd
        // + " Duration:" + tmpDuration + " Amount :" + amount + " pengali :"
        // + rate.getMultiplier().doubleValue());
        break;
      } else {
        tmpAmount = (rate.getSetOvertime().getTarif().doubleValue() * rate.getMultiplier()
            .doubleValue()) * (rate.getMaxlevel().doubleValue() - rate.getMinlevel().doubleValue());
        amount += tmpAmount;
        tmpStart = tmpStart == null ? checkIn : tmpEnd;
        tmpDuration = rate.getMaxlevel().longValue();
        tmpEnd = DateIntervalUtility.adjustSecond(tmpStart,
            DateIntervalUtility.getHourSecond(tmpDuration));
        creteOvertimeLines(overtimeRequest, false, tmpStart, tmpEnd, tmpDuration,
            rate.getMultiplier(), tmpAmount, line);
        // System.out.println("Holiday : yes Start Time :" + tmpStart + " End period :" + tmpEnd
        // + " Duration:" + tmpDuration + " Amount :" + amount + " pengali :"
        // + rate.getMultiplier().doubleValue());

      }
      line = line + 10;
    }
    OBContext.restorePreviousMode();
    return amount;
  }

  public static long workDayRate(ot_overtime overtimeRequest, String Position, Date checkIn,
      Date checkOut, Double Duration) {
    long amount = 0;
    double tmpAmount = 0;
    Date tmpStart = null;
    Date tmpEnd = null;
    double tmpDuration = 0;
    OBContext.setAdminMode();
    StringBuilder whereClause = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    whereClause.append(" as bsnday where "
        + "bsnday.setOvertime.validFromDate<=? and bsnday.setOvertime.validToDate>=?");
    params.add(checkIn);
    params.add(checkOut);
    if (Position != null && !Position.equals("")) {
      whereClause.append(" and bsnday.setOvertime.position=?");
      params.add(Position);
    }
    whereClause.append(" order by bsnday.minlevel");
    OBQuery<ot_business_rate> businessDay = OBDal.getInstance().createQuery(ot_business_rate.class,
        whereClause.toString());
    businessDay.setParameters(params);
    List<ot_business_rate> rateOvertime = businessDay.list();
    Long line = (long) 10;
    for (ot_business_rate rate : rateOvertime) {
      if (Duration <= rate.getMaxlevel().doubleValue()) {
        tmpAmount = (rate.getSetOvertime().getTarif().doubleValue() * rate.getMultiplier()
            .doubleValue()) * (Duration - rate.getMinlevel().doubleValue());
        amount += tmpAmount;
        tmpStart = tmpStart == null ? checkIn : tmpEnd;
        tmpDuration = (Duration - rate.getMinlevel().doubleValue());
        tmpEnd = DateIntervalUtility.adjustSecond(tmpStart,
            DateIntervalUtility.getHourSecond(tmpDuration));
        creteOvertimeLines(overtimeRequest, true, tmpStart, tmpEnd, tmpDuration,
            rate.getMultiplier(), tmpAmount, line);
        // System.out.println("Holiday : No Start Time :" + tmpStart + " End period :" + tmpEnd
        // + " Duration:" + tmpDuration + " Amount ::" + amount + " pengali :"
        // + rate.getMultiplier().doubleValue());
        break;
      } else {
        tmpAmount = (rate.getSetOvertime().getTarif().doubleValue() * rate.getMultiplier()
            .doubleValue()) * (rate.getMaxlevel().doubleValue() - rate.getMinlevel().doubleValue());
        amount += tmpAmount;
        tmpStart = tmpStart == null ? checkIn : tmpEnd;
        tmpDuration = rate.getMaxlevel().longValue();
        tmpEnd = DateIntervalUtility.adjustSecond(tmpStart,
            DateIntervalUtility.getHourSecond(tmpDuration));
        creteOvertimeLines(overtimeRequest, true, tmpStart, tmpEnd, tmpDuration,
            rate.getMultiplier(), tmpAmount, line);
        // System.out.println("Holiday : No Start Time :" + tmpStart + " End period :" + tmpEnd
        // + " Duration:" + tmpDuration + " Amount ::" + amount + " pengali :"
        // + rate.getMultiplier().doubleValue());
      }
      
      line = line + 10;
    }
    OBContext.restorePreviousMode();
    return amount;
  }

  /**
   * 
   * @param overtimeRequest
   * @param Position
   * @param checkIn
   * @param checkOut
   * @param Duration
   * @param overtimeRateAmount
   * @return
   */
  public static long workDayRate(ot_overtime overtimeRequest, String Position, Date checkIn,
      Date checkOut, Double Duration, double overtimeRateAmount) {
    long amount = 0;
    double tmpAmount = 0;
    Date tmpStart = null;
    Date tmpEnd = null;
    double tmpDuration = 0;
    OBContext.setAdminMode();
    StringBuilder whereClause = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    whereClause.append(" as bsnday where "
        + "bsnday.setOvertime.validFromDate<=? and bsnday.setOvertime.validToDate>=?");
    params.add(checkIn);
    params.add(checkOut);
    if (Position != null && !Position.equals("")) {
      whereClause.append(" and bsnday.setOvertime.position=?");
      params.add(Position);
    }
    whereClause.append(" order by bsnday.minlevel");
    OBQuery<ot_business_rate> businessDay = OBDal.getInstance().createQuery(ot_business_rate.class,
        whereClause.toString());
    businessDay.setParameters(params);
    List<ot_business_rate> rateOvertime = businessDay.list();
    Long line = (long) 10;
    for (ot_business_rate rate : rateOvertime) {
      if (Duration <= rate.getMaxlevel().doubleValue()) {
        tmpAmount = (overtimeRateAmount * rate.getMultiplier().doubleValue())
            * (Duration - rate.getMinlevel().doubleValue());
        amount += tmpAmount;
        tmpStart = tmpStart == null ? checkIn : tmpEnd;
        tmpDuration = (Duration - rate.getMinlevel().doubleValue());
        tmpEnd = DateIntervalUtility.adjustSecond(tmpStart,
            DateIntervalUtility.getHourSecond(tmpDuration));
        creteOvertimeLines(overtimeRequest, false, tmpStart, tmpEnd, tmpDuration,
            rate.getMultiplier(), tmpAmount, line);
        // System.out.println("Holiday : No Start Time :" + tmpStart + " End period :" + tmpEnd
        // + " Duration:" + tmpDuration + " Amount ::" + amount + " pengali :"
        // + rate.getMultiplier().doubleValue());
        break;
      } else {
        tmpAmount = (overtimeRateAmount * rate.getMultiplier().doubleValue())
            * (rate.getMaxlevel().doubleValue() - rate.getMinlevel().doubleValue());
        amount += tmpAmount;
        tmpStart = tmpStart == null ? checkIn : tmpEnd;
        tmpDuration = rate.getMaxlevel().longValue();
        tmpEnd = DateIntervalUtility.adjustSecond(tmpStart,
            DateIntervalUtility.getHourSecond(tmpDuration));
        creteOvertimeLines(overtimeRequest, false, tmpStart, tmpEnd, tmpDuration,
            rate.getMultiplier(), tmpAmount, line);
        // System.out.println("Holiday : No Start Time :" + tmpStart + " End period :" + tmpEnd
        // + " Duration:" + tmpDuration + " Amount ::" + amount + " pengali :"
        // + rate.getMultiplier().doubleValue());
      }
      
      line = line + 10;
    }
    OBContext.restorePreviousMode();
    return amount;
  }

  /**
   * 
   * @param overtimeRequest
   * @param Position
   * @param checkIn
   * @param checkOut
   * @param Duration
   * @param overtimeRateAmount
   * @return
   */
  public static long holiDayRate(ot_overtime overtimeRequest, String Position, Date checkIn,
      Date checkOut, Double Duration, Double overtimeRateAmount) {
    long amount = 0;
    double tmpAmount = 0;
    Date tmpStart = null;
    Date tmpEnd = null;
    double tmpDuration = 0;
    OBContext.setAdminMode();
    StringBuilder whereClause = new StringBuilder();
    whereClause.append(" as bsnday where "
        + "bsnday.setOvertime.validFromDate<=? and bsnday.setOvertime.validToDate>=?");
    List<Object> params = new ArrayList<Object>();
    params.add(checkIn);
    params.add(checkOut);
    if (Position != null && !Position.equals("")) {
      whereClause.append("and bsnday.setOvertime.position=?");
      params.add(Position);
    }
    whereClause.append("order by bsnday.minlevel");
    OBQuery<ot_nonbusiness_rate> nonBusinessDay = OBDal.getInstance().createQuery(
        ot_nonbusiness_rate.class, whereClause.toString());
    nonBusinessDay.setParameters(params);
    List<ot_nonbusiness_rate> rateOvertime = nonBusinessDay.list();
    Long line = (long) 10;
    for (ot_nonbusiness_rate rate : rateOvertime) {
      if (Duration <= rate.getMaxlevel().doubleValue()) {
        tmpAmount = (overtimeRateAmount * rate.getMultiplier().doubleValue())
            * (Duration - rate.getMinlevel().doubleValue());
        amount += tmpAmount;
        tmpStart = tmpStart == null ? checkIn : tmpEnd;
        tmpDuration = (Duration - rate.getMinlevel().doubleValue());
        tmpEnd = DateIntervalUtility.adjustSecond(tmpStart,
            DateIntervalUtility.getHourSecond(tmpDuration));
        creteOvertimeLines(overtimeRequest, true, tmpStart, tmpEnd, tmpDuration,
            rate.getMultiplier(), tmpAmount, line);
        // System.out.println("Holiday : yes Start Time :" + tmpStart + " End period :" + tmpEnd
        // + " Duration:" + tmpDuration + " Amount :" + amount + " pengali :"
        // + rate.getMultiplier().doubleValue());
        break;
      } else {
        tmpAmount = (overtimeRateAmount * rate.getMultiplier().doubleValue())
            * (rate.getMaxlevel().doubleValue() - rate.getMinlevel().doubleValue());
        amount += tmpAmount;
        tmpStart = tmpStart == null ? checkIn : tmpEnd;
        tmpDuration = rate.getMaxlevel().longValue();
        tmpEnd = DateIntervalUtility.adjustSecond(tmpStart,
            DateIntervalUtility.getHourSecond(tmpDuration));
        creteOvertimeLines(overtimeRequest, false, tmpStart, tmpEnd, tmpDuration,
            rate.getMultiplier(), tmpAmount, line);
        // System.out.println("Holiday : yes Start Time :" + tmpStart + " End period :" + tmpEnd
        // + " Duration:" + tmpDuration + " Amount :" + amount + " pengali :"
        // + rate.getMultiplier().doubleValue());

      }
      
      line = line + 10;
    }
    OBContext.restorePreviousMode();
    return amount;
  }

  /**
   * 
   * @param overtimeRequest
   * @param Position
   * @param checkIn
   * @param checkOut
   * @param overtimeRateAmount
   * @return
   */
  public static long getOvertimeAmount(ot_overtime overtimeRequest, String Position, Date checkIn,
      Date checkOut, Double overtimeRateAmount) {
    long amount = 0;
    try {
      if (DateUtils.isSameDay(checkIn, checkOut)) {
        double DayDiff = DateIntervalUtility.getDay(checkIn, checkOut);
        double Duration = (DayDiff * 24);

        if (AttendanceUtility.isHoliday(checkIn, overtimeRequest.getBusinessPartner())) {
          amount = holiDayRate(overtimeRequest, Position, checkIn, checkOut, Duration,
              overtimeRateAmount);
        } else {
          amount = workDayRate(overtimeRequest, Position, checkIn, checkOut, Duration,
              overtimeRateAmount);
        }
      } else {// lintas hari
        boolean checkInHolyDay = AttendanceUtility.isHoliday(checkIn,
            overtimeRequest.getBusinessPartner());
        boolean checkOutHolyDay = AttendanceUtility.isHoliday(checkOut,
            overtimeRequest.getBusinessPartner());
        if (checkInHolyDay == checkOutHolyDay) {
          double DayDiff = DateIntervalUtility.getDay(checkIn, checkOut);
          double Duration = (DayDiff * 24);
          if (checkInHolyDay == true && checkOutHolyDay == true) {
            amount = holiDayRate(overtimeRequest, Position, checkIn, checkOut, Duration,
                overtimeRateAmount);
          } else if (checkInHolyDay == false && checkOutHolyDay == false) {
            amount = workDayRate(overtimeRequest, Position, checkIn, checkOut, Duration,
                overtimeRateAmount);
          }
        } else {
          if (checkInHolyDay == true && checkOutHolyDay == false) {
            Date validCheckOutToday = DateIntervalUtility.clearTime(checkOut);
            double durHolyDay = DateIntervalUtility.getDay(checkIn, validCheckOutToday) * 24;
            amount = holiDayRate(overtimeRequest, Position, checkIn, validCheckOutToday,
                durHolyDay, overtimeRateAmount);
            double durWorkDay = DateIntervalUtility.getDay(validCheckOutToday, checkOut) * 24;
            amount += workDayRate(overtimeRequest, Position, validCheckOutToday, checkOut,
                durWorkDay, overtimeRateAmount);
          } else if (checkInHolyDay == false && checkOutHolyDay == true) {
            Date validCheckOutToday = DateIntervalUtility.clearTime(checkOut);
            double durWorkDay = DateIntervalUtility.getDay(checkIn, validCheckOutToday) * 24;
            amount = workDayRate(overtimeRequest, Position, checkIn, validCheckOutToday,
                durWorkDay, overtimeRateAmount);
            double durHolyDay = DateIntervalUtility.getDay(validCheckOutToday, checkOut) * 24;
            amount += holiDayRate(overtimeRequest, Position, validCheckOutToday, checkOut,
                durHolyDay, overtimeRateAmount);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return amount;
  }

  public static int creteOvertimeLines(ot_overtime overtime, boolean isHoliday, Date StartTime,
      Date endTime, double duration, BigDecimal multipleBy, double amont, Long line) {
    int result = 0;
    try {
      ot_overtime_detail ovtLines = OBProvider.getInstance().get(ot_overtime_detail.class);
      ovtLines.setOvertime(overtime);
      ovtLines.setBusinessday(!isHoliday);
      ovtLines.setStartingDate(StartTime);
      ovtLines.setEndingDate(endTime);
      ovtLines.setDuration(new BigDecimal(duration));
      ovtLines.setMultiplier(multipleBy);
      ovtLines.setAmount(new BigDecimal(amont));
      ovtLines.setLine(line);
      OBDal.getInstance().save(ovtLines);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
