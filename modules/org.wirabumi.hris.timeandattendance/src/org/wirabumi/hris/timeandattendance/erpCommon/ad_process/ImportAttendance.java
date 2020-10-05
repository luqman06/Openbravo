package org.wirabumi.hris.timeandattendance.erpCommon.ad_process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.gen.oez.utility.DateIntervalUtility;
import org.wirabumi.hris.leave.lv_leave;
import org.wirabumi.hris.timeandattendance.TAAttendance;
import org.wirabumi.hris.timeandattendance.TAShift;
import org.wirabumi.hris.timeandattendance.TA_ImportAttendance;
import org.wirabumi.hris.timeandattendance.TA_ShiftLine;
import org.wirabumi.hris.timeandattendance.ta_c_bp_shift;
import org.wirabumi.hris.timeandattendance.utility.AttendanceUtility;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class ImportAttendance extends DalBaseProcess {

	private final String checkIn = "IN";
	private final String checkOut = "OUT";
	private int recSuccess = 0;
	
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		//DEFINISI TANGGAL SEKARANG UNTUK BATAS AKHIR
		Date currentDate = new Date();
		
		//INISIALISASI HASH MAP IMPORT ATTENDANCE 
		HashMap<String, List<TA_ImportAttendance>> importAttendanceMap = new HashMap<String, List<TA_ImportAttendance>>();
		//INISIALISASI HASH MAP EMPLOYEE ATTENDACE
		HashMap<BusinessPartner, Date> employeeAttendanceMap = new HashMap<BusinessPartner, Date>();
		//INISIALISASI HASH MAP EMPLOYEE SHIFT
		HashMap<BusinessPartner, Date> employeeShiftMap = new HashMap<BusinessPartner, Date>();
		//INISIALISASI TABLE SHIFT LINE
		Table<BusinessPartner, TAShift, List<TA_ShiftLine>> employeeShiftLineTable = HashBasedTable.create();
		//INISIALISASI HASH MAP EMPLOYEE LEAVE
		HashMap<BusinessPartner, List<lv_leave>> employeeLeaveMap = new HashMap<BusinessPartner, List<lv_leave>>();
		
		
		//AMBIL DATA ATTENDANCE
		String sqlQuery = "select max(checkin) as max_checkin, max(checkout) as max_checkout, c_bpartner_id "
				+ "from ta_attendance group by c_bpartner_id";
		PreparedStatement ps;
		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection2 = conn.getConnection();
		ps = connection2.prepareStatement(sqlQuery);
		ResultSet result = ps.executeQuery();
		while(result.next()) {
			String employeeID = result.getString("c_bpartner_id");
			BusinessPartner employee = OBDal.getInstance().get(BusinessPartner.class, employeeID);
			Date maxCheckIn = result.getDate("max_checkin");
			Date maxCheckOut = result.getDate("max_checkout");
			if(maxCheckIn != null && maxCheckOut != null) {
				if(maxCheckOut.after(maxCheckIn))
					employeeAttendanceMap.put(employee, maxCheckOut);
				else if(maxCheckIn.after(maxCheckOut))
					employeeAttendanceMap.put(employee, maxCheckIn);
			} 
			else if(maxCheckIn == null)
				employeeAttendanceMap.put(employee, maxCheckOut);
			else if(maxCheckOut == null)
				employeeAttendanceMap.put(employee, maxCheckIn);
		}
		
		//AMBIL DATA EMPLOYEE SHIFT AND SHIFT LINE
		OBCriteria<ta_c_bp_shift> shiftCriteria = OBDal.getInstance().createCriteria(ta_c_bp_shift.class);
		shiftCriteria.add(Restrictions.ge(ta_c_bp_shift.PROPERTY_VALIDTODATE, currentDate));
		if(shiftCriteria.list().size() > 0) {
			for(ta_c_bp_shift employeeShift : shiftCriteria.list()) {
				BusinessPartner employee = employeeShift.getBusinessPartner();
				Date validFrom = employeeShift.getValidFromDate();
				employeeShiftMap.put(employee, validFrom);
				
				TAShift shift = employeeShift.getShift();
				List<TA_ShiftLine> shiftLineList = shift.getTAShiftLineList();
				employeeShiftLineTable.put(employee, shift, shiftLineList);
			}
		}
		
		//AMBIL DATA IMPORT ATTENDANCE  
		OBCriteria<TA_ImportAttendance> criteriaImport = OBDal.getInstance().createCriteria(TA_ImportAttendance.class);
		criteriaImport.add(Restrictions.eq(TA_ImportAttendance.PROPERTY_PROCESSED, false));
		criteriaImport.add(Restrictions.eq(TA_ImportAttendance.PROPERTY_PROCESSIMPORTCOMPLETE, false));
		criteriaImport.add(Restrictions.isNull(TA_ImportAttendance.PROPERTY_ATTENDANCE));
		criteriaImport.addOrderBy(TA_ImportAttendance.PROPERTY_EMPLOYEEKEY, true);
		if(criteriaImport.list().size() > 0) {
			for(TA_ImportAttendance importAttendance : criteriaImport.list()) {
				List<TA_ImportAttendance> importAttendanceList = null;
				String employeeKey = importAttendance.getEmployeeKey();
				if(employeeKey != null) {
					if(importAttendanceMap.containsKey(employeeKey)) 
						importAttendanceList = importAttendanceMap.get(employeeKey);
					else 
						importAttendanceList = new ArrayList<TA_ImportAttendance>();
						
					importAttendanceList.add(importAttendance);
					importAttendanceMap.put(employeeKey, importAttendanceList);
				}			
			}
		}
		
		//AMBIL DATA CUTI
		OBCriteria<lv_leave> criteriaLeave = OBDal.getInstance().createCriteria(lv_leave.class);
		criteriaLeave.add(Restrictions.eq(lv_leave.PROPERTY_DOCUMENTSTATUS, "CO"));
		if(criteriaLeave.list().size() > 0) {
			for(lv_leave leave : criteriaLeave.list()) {
				List<lv_leave> leaveList = null;
				BusinessPartner employee = leave.getEmployee();
				if(employeeLeaveMap.containsKey(employee)) 
					leaveList = employeeLeaveMap.get(employee);
				else 
					leaveList = new ArrayList<lv_leave>();
				
				leaveList.add(leave);
				employeeLeaveMap.put(employee, leaveList);
			}
		}
		  
		//AMBIL DATA SEMUA KARYAWAN
		OBCriteria<BusinessPartner> criteriaEmployee = OBDal.getInstance().createCriteria(BusinessPartner.class);
		criteriaEmployee.add(Restrictions.eq(BusinessPartner.PROPERTY_SEARCHKEY, "EA"));
		if(criteriaEmployee.list().size() > 0) {
			matchingAttendance(currentDate, bundle, criteriaEmployee, employeeAttendanceMap, employeeShiftMap, importAttendanceMap, employeeShiftLineTable, employeeLeaveMap);
		}
	}

	private void matchingAttendance(
			Date currentDate, ProcessBundle bundle,
			OBCriteria<BusinessPartner> criteriaEmployee,
			HashMap<BusinessPartner, Date> employeeAttendanceMap,
			HashMap<BusinessPartner, Date> employeeShiftMap,
			HashMap<String, List<TA_ImportAttendance>> importAttendanceMap,
			Table<BusinessPartner, TAShift, List<TA_ShiftLine>> employeeShiftLineTable, HashMap<BusinessPartner,List<lv_leave>> employeeLeaveMap) {
		
		//EVALUASI SEMUA KARYAWAN
		for(BusinessPartner employee : criteriaEmployee.list()) {
			//INISIALISASI VARIABEL BOOLEAN 
			boolean isComeLate = false, isBackEarly = false, isCheckIn = false, isCheckOut = false;
			//INISIALISASI VARIABEL ATTENDANCE MAP
			HashMap<Date, HashMap<String, TA_ImportAttendance>> attendanceMap = new HashMap<Date, HashMap<String,TA_ImportAttendance>>();
			//INISIALISASI VARIABEL EMPLOYEE ATTENDANCE 2
			HashMap<BusinessPartner, HashMap<Date, HashMap<String, TA_ImportAttendance>>> employeeAttendanceMap2 = new HashMap<BusinessPartner, HashMap<Date, HashMap<String, TA_ImportAttendance>>>();
			//INISIALISASI VARIABEL CALENDAR
			Calendar startCal = Calendar.getInstance();
			//INISIALISASI VARIABEL UNTUK BATAS AWAL
			Date validFromDate = null;
			//INISIALISASI VARIABEL UNTUK DEFAULT TIMESTAMP LATE DAN EARLY
			Timestamp defaultTimeStamp = null;
			
			/*
			 * JIKA KARYAWAN SUDAH PERNAH ABSENSI 
			 * MAKA BATAS AWAL TANGGAL AMBIL TANGGAL TERAKHIR DARI ABSEN DI EMPLOYEE ATTENDANCE MAP
			 */
			if(employeeAttendanceMap.containsKey(employee)) {
				//BATAS AWAL AMBIL DARI TANGGAL TERAKHIR ABSENSI
				validFromDate = employeeAttendanceMap.get(employee);
			}
			/*
			 * JIKA BELUM BATAS AWAL TANGGAL 
			 * MAKA AMBIL DARI VALID FROM SHIFT YANG BERLAKU UNTUK KARYAWAN
			 */
			else {
				if(employeeShiftMap.containsKey(employee)) {
					//BATAS AWAL AMBIL DARI VALID FROM SHIFT
					validFromDate = employeeShiftMap.get(employee);
				}
				
				/*
				 * SET TANGGAL DEFAULT 
				 */
				startCal.set(Calendar.YEAR, 2000);
				startCal.set(Calendar.MONTH, 1);
				startCal.set(Calendar.DAY_OF_MONTH, 1);
				startCal.set(Calendar.HOUR_OF_DAY, 0);
				startCal.set(Calendar.MINUTE, 0);
				startCal.set(Calendar.SECOND, 0);
				Date defaultDate = startCal.getTime();
				//DEAFULT TIMESTAMP UNTUK DEFAULT FIELD LATE DAN EARLY
				defaultTimeStamp = new Timestamp(defaultDate.getTime());
				
				//SET TANGGAL VARIABEL CALENDAR DENGAN BATAS AWAL
				startCal.setTime(validFromDate);
				
				//LOOPING BATAS AWAL TANGGAL SAMPAI BATAS AKHIR
				while(startCal.getTime().before(currentDate)) {
					
					//INISIALISASI VARIABEL SHIFT LINE LIST
					List<TA_ShiftLine> shiftLineList = null;
					//INISIALISASI VARIABEL SHIFT LINE
					TA_ShiftLine shiftLine = null;
					
					//SET DEFAULT TIME
					startCal.set(Calendar.HOUR_OF_DAY, 0);
					startCal.set(Calendar.MINUTE, 0);
					startCal.set(Calendar.SECOND, 0);
					Date nextDate = startCal.getTime();
					
					//AMBIL SHIFT YANG BERLAKU DARI KARYAWAN
					//TAShift shift = AttendanceUtility.applicableShift(nextDate, employee); //TODO fixme
					
					/*
					 * FORMULA UNTUK MENGETAHUI JARAK HARI ANTARA BATAS AWAL TANGGAL DENGAN BATAS AKHIR TANGGAL
					 */
					long nextDay = nextDate.getTime();
					long validFromDay = validFromDate.getTime();
					long day = (nextDay - validFromDay) / (1000 * 60 * 60 * 24);
					
					//AMBIL SHIFT LINE LIST
					if(employeeShiftLineTable.containsRow(employee)) {
						Set<TAShift> columnSet = employeeShiftLineTable.columnKeySet();
						for(TAShift shf : columnSet) {
							if(employeeShiftLineTable.contains(employee, shf)) {
								shiftLineList = employeeShiftLineTable.get(employee, shf);
							}
						}	
					}
					
					/*
					 * JIKA ADA SHIFT LINE 
					 * MAKA AMBIL DATA SHIFT LINE BERDASAR LINE NO.
					 */
					if(shiftLineList != null) {
						/*
						 * FORMULA UNTUK MENGETAHUI LINE NO MANA YANG AKAN DIAMBIL DARI SHIFT LINE LIST
						 */
						int totalShiftLine = shiftLineList.size();
						Long line = day % totalShiftLine;
						//AMBIL SHIFT LINE BERDASAR LINE NO. 
						shiftLine = shiftLineList.get(line.intValue());
						
					} 
					//JIKA TIDAK ADA MAKA EXCEPTION KARENA SETIAP SHIFT HARUS MEMILIKI SHIFT LINE
					else 
						throw new OBException("employee assigned to shift that has no lines");
					
					//VARIABEL LIBUR
					boolean isOff = shiftLine.isOff();
					//VARIABLE HARI BESAR / TANGGAL MERAH
					boolean isNonBusinessDay = AttendanceUtility.isNonBusinessDay(nextDate);
					//VARIABEL CUTI DEFAULT FALSE
					boolean isLeave = false;
					
					/*
					 * JIKA KARYAWAN ADA RECORD DI EMPLOYEE LEAVE
					 * MAKA LAKUKAN PENGECEKAN SELANJUTNYA
					 */
					if(employeeLeaveMap.containsKey(employee)) {
						List<lv_leave> leaveList = employeeLeaveMap.get(employee);
						int leaveRec = 0;
						for(lv_leave leave : leaveList) {
							long startLeave = leave.getStartingDate().getTime();
							long endLeave = leave.getEndingDate().getTime();
							long matchDate = nextDate.getTime();
								
							if(matchDate >= startLeave && matchDate <= endLeave) {
								leaveRec++;
								break;
							}
						}
						
						/*
						 * JIKA ADA RECORD LEAVE
						 * MAKA VARIABLE LEAVE MENJADI TRUE YANG MENANDAKAN SEDANG CUTI
						 */
						if(leaveRec > 0)
							isLeave = true;
					}
					
					
					/*
					 * JIKA TIDAK LIBUR DAN TIDAK TANGGAL MERAH 
					 * MAKA LAKUKAN PENGECEKAN SELANJUTNYA
					 */
					if(!isOff && !isNonBusinessDay) {
						/*
						 * JIKA TIDAK CUTI 
						 * MAKA LAKUKAN PENGECEKAN SELANJUTNYA
						 */
						if(!isLeave) {

							//AMBIL JAM&TANGGAL CHECK IN DARI SHIFT LINE
							Date checkInShiftLine = shiftLine.getCheckIn();
							/*
							 * CONCAT NEXT DATE DENGAN TIMESTAMP CHECK IN
							 */
							Calendar calTime = Calendar.getInstance();
							calTime.setTime(checkInShiftLine);
							startCal.set(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
							startCal.set(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
							startCal.set(Calendar.SECOND, calTime.get(Calendar.SECOND));
							checkInShiftLine = startCal.getTime();
							
							//AMBIL JAM&TANGGAL CHECK OUT DARI SHIFT LINE
							Date checkOutShiftLine = shiftLine.getCheckOut();
							/*
							 * CONCAT NEXT DATE DENGAN TIMESTAMP CHECK IN
							 */
							calTime.setTime(checkOutShiftLine);
							startCal.set(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
							startCal.set(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
							startCal.set(Calendar.SECOND, calTime.get(Calendar.SECOND));
							checkOutShiftLine = startCal.getTime();
												
							
							//AMBIL SEARCH KEY KARYAWAN
							String employeeKey = employee.getSearchKey();
							
							/*
							 * JIKA ADA DATA IMPORT ATTENDANCE COCOK DENGAN SEARCH KEY KARYAWAN 
							 * MAKA LAKUKAN PENGECEKAN SELANJUTNYA
							 */
							if(importAttendanceMap.containsKey(employeeKey)) {
								
								//AMBIL IMPORT ATTENDANCE LIST PER KARYAWAN YANG ADA DI IMPORT ATTENDANCE MAP
								List<TA_ImportAttendance> importAttendanceList = importAttendanceMap.get(employeeKey);
								
								//EVALUASI SEMUA DATA DI IMPORT ATTENDANCE LIST
								for(TA_ImportAttendance importAttendance : importAttendanceList) {
									//INISIALISASI VARIABLE BOOLEAN
									
									
									//AMBIL JAM ABSENSI DARI IMPORT ATTENDANCE
									Date attendanceTime = importAttendance.getAttendanceTime();
									//AMBIL JAM&TANGGAL CHECK IN KARYAWAN SESUAI DENGAN JAM ABSENSI NYA
									Date workingStartDate = AttendanceUtility.workingStart(attendanceTime, employee);
									//AMBIL JAM&TANGGAL CHECK OUT KARYAWAN SESUAI DENGAN JAM ABSENSI NYA
									Date workingEndDate = AttendanceUtility.workingFinish(attendanceTime, employee);
									
									
									/*
									 * JIKA JAM&TANGGAL CHECK IN DAN OUT KARYAWAN COCOK DENGAN JAM&TANGGAL CHECK IN DAN OUT SHIFT LINE
									 * MAKA LAKUKAN PENGECEKAN SELANJUTNYA
									 */
									if(workingStartDate.equals(checkInShiftLine) && workingEndDate.equals(checkOutShiftLine)) {
										
										/*
										 * PENGECEKAN UNTUK MENENTUKAN : 
										 * 1. APAKAH KARYAWAN DATANG TEPAT WAKTU / TERLAMBAT DAN PULANG TEPAT WAKTU / PULANG CEPAT
										 * 2. PENGECEKANA UNTUK MENENTUKAN APAKAH JAM ABSENSI ITU UNTUK CHECK IN / CHECK OUT
										 */
										if(attendanceTime.getTime() <= workingStartDate.getTime()) 
											isCheckIn = true;
										else if(attendanceTime.getTime() >= workingEndDate.getTime()) 
											isCheckOut = true;
										else if(attendanceTime.after(workingStartDate) && attendanceTime.before(workingEndDate)) {
											long diffCheckIn = attendanceTime.getTime() - workingStartDate.getTime();
											long diffCheckOut = workingEndDate.getTime() - attendanceTime.getTime();
											if(diffCheckIn < diffCheckOut) {
												isCheckIn = true;
												isComeLate = true;
											} else if(diffCheckIn > diffCheckOut) {
												isCheckOut = true;
												isBackEarly = true;
											}
										}
										
										
										
										/*
										 * PENGECEKAN UNTUK MEMASUKKAN DATA SEBAGAI CEHECK IN ATAU CHECK OUT
										 * JIKA TERJADI LEBIH DARI 1 ABSENSI CHECK IN MAUPUN CHECK OUT
										 * MAKA YANG DIPAKAI ADALAH JAM YANG MENDEKATI JAM CHECK IN ATAU CHECK OUT
										 */
										if(isCheckIn == true && isComeLate == false) {
											if(attendanceMap.containsKey(workingStartDate)) {
												HashMap<String, TA_ImportAttendance> oldAttendanceTimeMap = attendanceMap.get(workingStartDate);
												if(oldAttendanceTimeMap.containsKey(checkIn)) {
													TA_ImportAttendance oldImportAttendance = oldAttendanceTimeMap.get(checkIn);
													Date oldDate = oldImportAttendance.getAttendanceTime();
													if(oldDate.before(attendanceTime)) {
														oldAttendanceTimeMap.put(checkIn, importAttendance);
														attendanceMap.put(workingStartDate, oldAttendanceTimeMap);
													}
												} else {
													oldAttendanceTimeMap.put(checkIn, importAttendance);
													attendanceMap.put(workingStartDate, oldAttendanceTimeMap);
												}
											} else {
												HashMap<String, TA_ImportAttendance> attendanceTimeMap = new HashMap<String, TA_ImportAttendance>();
												attendanceTimeMap.put(checkIn, importAttendance);
												attendanceMap.put(workingStartDate, attendanceTimeMap);
											}
										} else if(isCheckIn == true && isComeLate == true) {
											if(attendanceMap.containsKey(workingStartDate)) {
												HashMap<String, TA_ImportAttendance> oldAttendanceTimeMap = attendanceMap.get(workingStartDate);
												if(oldAttendanceTimeMap.containsKey(checkIn)) {
													TA_ImportAttendance oldImportAttendance = oldAttendanceTimeMap.get(checkIn);
													Date oldDate = oldImportAttendance.getAttendanceTime();
													if(oldDate.after(attendanceTime)) {
														oldAttendanceTimeMap.put(checkIn, importAttendance);
														attendanceMap.put(workingStartDate, oldAttendanceTimeMap);
													}
												} else {
													oldAttendanceTimeMap.put(checkIn, importAttendance);
													attendanceMap.put(workingStartDate, oldAttendanceTimeMap);
												}
											} else {
												HashMap<String, TA_ImportAttendance> attendanceTimeMap = new HashMap<String, TA_ImportAttendance>();
												attendanceTimeMap.put(checkIn, importAttendance);
												attendanceMap.put(workingStartDate, attendanceTimeMap);
											}
										} else if(isCheckOut == true && isBackEarly == false) {	
											if(attendanceMap.containsKey(workingStartDate)) {
												HashMap<String, TA_ImportAttendance> oldAttendanceTimeMap = attendanceMap.get(workingStartDate);
												if(oldAttendanceTimeMap.containsKey(checkOut)) {
													TA_ImportAttendance oldImportAttendance = oldAttendanceTimeMap.get(checkOut);
													Date oldDate = oldImportAttendance.getAttendanceTime();
													if(oldDate.after(attendanceTime)) {
														oldAttendanceTimeMap.put(checkOut, importAttendance);
													}
												} else {
													oldAttendanceTimeMap.put(checkOut, importAttendance);
													attendanceMap.put(workingStartDate, oldAttendanceTimeMap);
												}
											} else {
												HashMap<String, TA_ImportAttendance> attendanceTimeMap = new HashMap<String, TA_ImportAttendance>();
												attendanceTimeMap.put(checkOut, importAttendance);
												attendanceMap.put(workingStartDate, attendanceTimeMap);
											}	
										} else if(isCheckOut == true && isBackEarly == true) {
											if(attendanceMap.containsKey(workingStartDate)) {
												HashMap<String, TA_ImportAttendance> oldAttendanceTimeMap = attendanceMap.get(workingStartDate);
												if(oldAttendanceTimeMap.containsKey(checkOut)) {
													TA_ImportAttendance oldImportAttendance = oldAttendanceTimeMap.get(checkOut);
													Date oldDate = oldImportAttendance.getAttendanceTime();
													if(oldDate.after(attendanceTime)) {
														oldAttendanceTimeMap.put(checkOut, importAttendance);
													}
												} else {
													oldAttendanceTimeMap.put(checkOut, importAttendance);
													attendanceMap.put(workingStartDate, oldAttendanceTimeMap);
												}
											} else {
												HashMap<String, TA_ImportAttendance> attendanceTimeMap = new HashMap<String, TA_ImportAttendance>();
												attendanceTimeMap.put(checkOut, importAttendance);
												attendanceMap.put(workingStartDate, attendanceTimeMap);
											}
										}
										
										//SET PROCESSED TRUE UNTUK MENANDAI BAHWA RECORD IMPORT ATTENDANCE SUDAH DI PROCESS
										importAttendance.setProcessed(true);
										//SIMPAN OBJECK IMPORT ATTENDANCE
										OBDal.getInstance().save(importAttendance);
										
									} 
									/*
									 * JIKA TIDAK COCOK
									 * MAKA LANJUT EVALUASI IMPORT ATTENDANCE LIST MENCARI JAM&TANGGAL CHECK IN DAN OUT YANG COCOK
									 */
									else 
										continue;
								}
								
								/*
								 * JIKA ADA DATA ABSENSI DI TABLE IMPORT ATTENDANCE
								 * MAKA MASUKKAN DI HASH MAP EMPLOYEE ATTENDANCE 2
								 */
								if(attendanceMap != null) 
									employeeAttendanceMap2.put(employee, attendanceMap);
								/*
								 * JIKA TIDAK ADA MAKA MASUKKAN KE ATTENDANCE DENGAN KETERANGAN LUPA ABSENSI
								 */
								else {
									//SET TA_ATTENDANCE
									TAAttendance attendance = OBProvider.getInstance().get(TAAttendance.class);
									attendance.setEmployee(employee);
									//attendance.setShift(shift);//TODO fixme
									attendance.setAttandanceDate(nextDate);
									attendance.setCheckinStatus("FORGET");
									attendance.setCheckoutStatus("FORGET");
									attendance.setLate(defaultTimeStamp);
									attendance.setEarly(defaultTimeStamp);
									//SIMPAN KE TABLE TA_ATTENDANCE
									OBDal.getInstance().save(attendance);
									
									recSuccess++;
								}
							} 
						} 
						/*
						 * JIKA CUTI
						 * MAKA LANGSUNG MASUKKAN  KE TABLE TA_ATTENDANCE DENGAN MEMBERIKAN KETERANGAN CUTI
						 */
						else {
							//SET TA_ATTENDANCE
							TAAttendance attendance = OBProvider.getInstance().get(TAAttendance.class);
							attendance.setEmployee(employee);
							//attendance.setShift(shift);//TODO fixme
							attendance.setAttandanceDate(nextDate);
							attendance.setCheckinStatus("LEAVE");
							attendance.setCheckoutStatus("LEAVE");
							attendance.setLate(defaultTimeStamp);
							attendance.setEarly(defaultTimeStamp);
							//SIMPAN KE TABLE TA_ATTENDANCE
							OBDal.getInstance().save(attendance);
							
							recSuccess++;
						}
					}
					/*
					 * JIKA HARI LIBUR ATAU HARI BESAR 
					 * MAKA LANGSUNG MASUKKAN KE TABLE TA_ATTENDANCE DENGAN MEMBERIKAN KETERANGAN LIBUR 
					 */
					else {
						//SET TA_ATTENDANCE
						TAAttendance attendance = OBProvider.getInstance().get(TAAttendance.class);
						attendance.setEmployee(employee);
						//attendance.setShift(shift);//TODO fixme
						attendance.setAttandanceDate(nextDate);
						attendance.setCheckinStatus("OFF");
						attendance.setCheckoutStatus("OFF");
						attendance.setLate(defaultTimeStamp);
						attendance.setEarly(defaultTimeStamp);
						//SIMPAN KE TABLE TA_ATTENDANCE
						OBDal.getInstance().save(attendance);
						
						recSuccess++;
					}
					startCal.add(Calendar.DATE, 1);
				}
				
				//METHOD UNTUK MEMASUKKAN KE TABLE ATTENDANCE
				createAttendance(employeeAttendanceMap2);
				
				OBDal.getInstance().commitAndClose();
				final OBError msg = new OBError();
			      msg.setType("Success");		
			      msg.setTitle("Created Succes");
			      msg.setMessage(recSuccess + " " + "Record Created Success");
			      bundle.setResult(msg);				
			}
		}		
	}

	private void createAttendance(
			HashMap<BusinessPartner,HashMap<Date,HashMap<String,TA_ImportAttendance>>> employeeAttendanceMap) {
		
		//EVALUASI SEMUA DATA YG ADA DI EMPLOYEE ATTENDANCE MAP UNTUK DIMASUKKAN KE TABLE ATTENDANCE
		for(Map.Entry<BusinessPartner, HashMap<Date, HashMap<String, TA_ImportAttendance>>> entry : employeeAttendanceMap.entrySet()) {
			
			
			BusinessPartner employeeAttendance = entry.getKey();
			HashMap<Date, HashMap<String, TA_ImportAttendance>> attendanceMap = entry.getValue(); 
			Set<Date> keySet1 = attendanceMap.keySet();
			
			
			for(Date dateKey : keySet1) {
				
				Calendar cal = Calendar.getInstance();
				Date attTime = null;
				TAAttendance attendance = OBProvider.getInstance().get(TAAttendance.class);
				
				cal.set(Calendar.YEAR, 2000);
				cal.set(Calendar.MONTH, 1);
				cal.set(Calendar.DAY_OF_MONTH, 1);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				Date defaultDate = cal.getTime();
				Timestamp defaultTimeStamp = new Timestamp(defaultDate.getTime());
				
				//SET DEFAULT LATE
				attendance.setLate(defaultTimeStamp);
				//SET DEFAULT EARLY
				attendance.setEarly(defaultTimeStamp);
				
				//SET DEFAULT CHECK IN STATUS
				attendance.setCheckinStatus("OK");
				//SET DEFAULT CHECK OUT STATUS
				attendance.setCheckoutStatus("OK");
				HashMap<String, TA_ImportAttendance> attendanceTimeMap = attendanceMap.get(dateKey);
				Set<String> keySet2 = attendanceTimeMap.keySet();
				
				
				//EVALUASI UNTUK MENENTUKAN DATA YANG AKAN MASUK SEBAGAI CHECK IN DAN CHECK OUT
				for(String stringKey1 : keySet2) {
					TA_ImportAttendance importAttendance = attendanceTimeMap.get(stringKey1);
					attTime = importAttendance.getAttendanceTime();
					Date workingStartDate = AttendanceUtility.workingStart(attTime, employeeAttendance);
					Date workingEndDate = AttendanceUtility.workingFinish(attTime, employeeAttendance);
					
					if(stringKey1.equalsIgnoreCase("IN")) {
						attendance.setCheckin(attTime);
						if(attTime.after(workingStartDate)) {
							attendance.setCheckinStatus("LATE");
							Double diffHour = DateIntervalUtility.getHour(workingStartDate, attTime);
							Double diffMinute = DateIntervalUtility.getMinutes(workingStartDate, attTime);
							Double diffSecond = DateIntervalUtility.getSecond(workingStartDate, attTime);
							
							cal.setTime(attTime);
							cal.set(Calendar.HOUR_OF_DAY, diffHour.intValue());
							cal.set(Calendar.MINUTE, diffMinute.intValue());
							cal.set(Calendar.SECOND, diffSecond.intValue());
							Date lateDate = cal.getTime();
							Timestamp lateTimeStamp = new Timestamp(lateDate.getTime());
							attendance.setLate(lateTimeStamp);	
						}
					} else if(stringKey1.equalsIgnoreCase("OUT")) {
						attendance.setCheckout(attTime);
						if(attTime.before(workingEndDate)) {
							attendance.setCheckoutStatus("EARLY");
							Double diffHour = DateIntervalUtility.getHour(attTime, workingEndDate);
							Double diffMinute = DateIntervalUtility.getMinutes(attTime, workingEndDate);
							Double diffSecond = DateIntervalUtility.getSecond(attTime, workingEndDate);
							
							cal.setTime(attTime);
							cal.set(Calendar.HOUR_OF_DAY, diffHour.intValue());
							cal.set(Calendar.MINUTE, diffMinute.intValue());
							cal.set(Calendar.SECOND, diffSecond.intValue());
							Date earlyDate = cal.getTime();
							Timestamp earlyTimeStamp = new Timestamp(earlyDate.getTime());
							attendance.setEarly(earlyTimeStamp);
						}
					}
				}
				//SET EMPLOYEE
				attendance.setEmployee(employeeAttendance);
				//SET CHECK IN STATUS IF EMPLOYEE NO CHECK IN
				if(attendance.getCheckin() == null) {
					attendance.setCheckinStatus("FORGET");
					
					cal.setTime(attTime);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					Date lateDate = cal.getTime();
					Timestamp lateTimeStamp = new Timestamp(lateDate.getTime());
					attendance.setEarly(lateTimeStamp);
				}
					
				//SET CHECK OUT STATUS IF EMPLOYEE NO CHECK OUT
				if(attendance.getCheckout() == null) {
					attendance.setCheckoutStatus("FORGET");
					
					cal.setTime(attTime);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					Date earlyDate = cal.getTime();
					Timestamp earlyTimeStamp = new Timestamp(earlyDate.getTime());
					attendance.setEarly(earlyTimeStamp);
				}
				
				//GET APPLICABLE SHIFT
				//TAShift shift = AttendanceUtility.applicableShift(attTime, employeeAttendance);//TODO fixme
				//IF SHIFT NULL, RECORD NOT INSERT INTO TABLE
				//if(shift == null)//TODO fixme
					//continue;//TODO fixme
				//SET SHIFT
				//attendance.setShift(shift);//TODO fixme
				attendance.setAttandanceDate(attTime);
				OBDal.getInstance().save(attendance);
				recSuccess++;
				
				/*
				 * SET IMPORTED TRUE SEBAGAI TANDA BAHWA RECORD SUDAH TERIMPORT
				 * DAN SET ATTENDACE YANG SUDAH DIBUAT KE IMPORT ATTENDACE 
				 */
				for(String stringKey2 : keySet2) {
					TA_ImportAttendance importAttendance = attendanceTimeMap.get(stringKey2);
					importAttendance.setProcessImportComplete(true);
					importAttendance.setAttendance(attendance);
					OBDal.getInstance().save(importAttendance);
				}	
			}	
		}		
	}
}
