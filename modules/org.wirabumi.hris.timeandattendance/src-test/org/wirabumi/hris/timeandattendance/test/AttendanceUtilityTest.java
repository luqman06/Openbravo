package org.wirabumi.hris.timeandattendance.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openbravo.test.base.BaseTest;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

public class AttendanceUtilityTest extends BaseTest {
	
	public void testIsWorkDay(){
		String employeeId="D931E355865F4AEDA365BA4E168995DE";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date workDay=null;
		Boolean isWorkDay=null;
		try {
			workDay = dateFormat.parse("06-01-2014");
			isWorkDay=AttendanceUtility.isWorkDayBasedOnShiftOnly(workDay, employeeId);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(isWorkDay);
	}

}
