package org.wirabumi.hris.overtime.ad_process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.payroll.SalaryFormula;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;
import org.wirabumi.hris.timeandattendance.TAShift;
import org.wirabumi.hris.timeandattendance.TA_ShiftLine;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;


public class OvertimeCalculation implements SalaryFormula{
	private final Table<pyr_sp_employee, String, pyr_spe_earning> speeKeyMap = HashBasedTable.create();
	private final HashMap<TAShift, List<TA_ShiftLine>> shiftruleMap = new HashMap<TAShift, List<TA_ShiftLine>>();
	private final HashMap<TAShift, Integer> shiftruleSize = new HashMap<TAShift, Integer>();
	private final HashMap<BusinessPartner, HashMap<Date, TAShift>> employeeShiftMap = new HashMap<BusinessPartner, HashMap<Date,TAShift>>();
	Logger log4j = Logger.getLogger(this.getClass());
	private final String clientID = OBContext.getOBContext().getCurrentClient().getId();
	private final String orgID = OBContext.getOBContext().getCurrentOrganization().getId();
	private final List<Date> holidayList = new ArrayList<Date>();
	private final HashMap<String, HashMap<Double, Double>> tarifLemburHariBiasa = new HashMap<String, HashMap<Double,Double>>();
	private final HashMap<String, HashMap<Double, Double>> tarifLemburHariLibur	 = new HashMap<String, HashMap<Double,Double>>();
	private final HashMap<BusinessPartner, Double> akumulasiLembur = new HashMap<BusinessPartner, Double>();
	private final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Override
	public double hitungFormulaGaji(pyr_salarypayment salaryPayment,
			pyr_sp_employee sp_employee, pyr_spe_earning spee,
			pyr_spe_deduction sped) {
		if (sped!=null)
			throw new OBException("invalid run level of salary payment formula for overtime. overtime should be run on earning.");
		if (spee!=null)
			throw new OBException("invalid run level of salary payment formula for overtime. overtime should be run on employee or batch level, not in employee earning level.");
		
		//dapatkan list of overtime dan employee yang terlibat
		BusinessPartner employee = null;
		if (sp_employee!=null){
			salaryPayment = sp_employee.getSalaryPayment();
			employee = sp_employee.getBusinessPartner();
		} 
		if (salaryPayment==null)
			throw new OBException("can not find valid salary payment");
		
		String paymentGroup=salaryPayment.getPaymentGroup();
		if (!paymentGroup.equalsIgnoreCase("GAJI"))
			return 0;
		
		java.sql.Date startdatesql = new java.sql.Date(salaryPayment.getStartingDate().getTime());
		java.sql.Date enddatesql = new java.sql.Date(salaryPayment.getEndingDate().getTime());
		String sqlQuery = "select ot_overtime_id, c_bpartner_id"
				+ " from ot_overtime"
				+ " where ad_client_id=?"
				+ " and docstatus='CO'"
				+ " and datefrom>=?"
				+ " and dateto<=?";
		if (employee!=null)
			sqlQuery += " and c_bpartner_id=?";
		
		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection;
		List<String> overtimeList = new ArrayList<String>();
		try {
			connection = conn.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, clientID);
			ps.setDate(2, startdatesql);
	        ps.setDate(3, enddatesql);
	        if (employee!=null)
	        	ps.setString(4, employee.getId());
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()){
	        	String overtimeID = rs.getString("ot_overtime_id");
	        	overtimeList.add(overtimeID);
	        }
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//load employee shift
		sqlQuery="select c_bpartner_id, ta_shift_id, validto"
				+ " from ta_c_bp_shift"
				+ " where ad_client_id=?"
				+ " and validto>=?";
		if (employee!=null)
			sqlQuery += " and c_bpartner_id=?";
		sqlQuery += " order by validfrom asc";
		
		try {
			connection = conn.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, clientID);
			ps.setDate(2, startdatesql);
	        if (employee!=null)
	        	ps.setString(3, employee.getId());
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()){
	        	String employeeID = rs.getString("c_bpartner_id");
	        	if (employeeID==null || employeeID.isEmpty())
	        		throw new OBException("employee ID is null or empty");
	        	BusinessPartner employee2 = OBDal.getInstance().get(BusinessPartner.class, employeeID);
	        	if (employee2==null)
	        		throw new OBException(employeeID+" is not valid employee ID");
	        	
	        	String shiftID = rs.getString("ta_shift_id");
	        	if (shiftID==null || shiftID.isEmpty())
	        		throw new OBException("shift ID is null or empty");
	        	TAShift shift = OBDal.getInstance().get(TAShift.class, shiftID);
	        	
	        	Date validto = rs.getDate("validto");
	        	if (validto==null)
	        		throw new OBException("valid to in shift is null");
	        	
	        	HashMap<Date, TAShift> shiftList = null;
	        	if (employeeShiftMap.containsKey(employee2)){
	        		shiftList = employeeShiftMap.get(employee2);
	        	} else {
	        		shiftList = new HashMap<Date, TAShift>();
	        	}
	        	shiftList.put(validto, shift);
	        	employeeShiftMap.put(employee2, shiftList);
	        	
	        }
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//load shift rule
		for (BusinessPartner employee3 : employeeShiftMap.keySet()){
			HashMap<Date, TAShift> shiftlist = employeeShiftMap.get(employee3);
			for (TAShift shift : shiftlist.values()){
				if (!shiftruleMap.containsKey(shift)){
					OBCriteria<TA_ShiftLine> shiftlineC = OBDal.getInstance().createCriteria(TA_ShiftLine.class);
					shiftlineC.add(Restrictions.eq(TA_ShiftLine.PROPERTY_SHIFT, shift));
					shiftlineC.addOrderBy(TA_ShiftLine.PROPERTY_LINENO, true);
					List<TA_ShiftLine> shiftlineList = shiftlineC.list();
					if (shiftlineList!=null && shiftlineList.size()>0)
						shiftruleMap.put(shift, shiftlineList);
				}
			}
		}
		
		//load shift rule size
		sqlQuery="select ta_shift_id, count(*) as size"
				+ " from ta_shiftline"
				+ " where ad_client_id=?"
				+ " and exists (select 1 from ta_c_bp_shift where ta_shift_id=ta_shiftline.ta_shift_id and validfrom>=?)"
				+ " group by ta_shift_id";
		try {
			connection = conn.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, clientID);
			ps.setDate(2, startdatesql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()){
	        	String shiftID = rs.getString("ta_shift_id");
	        	if (shiftID==null || shiftID.isEmpty())
	        		throw new OBException("shift ID is null or empty");
	        	TAShift shift = OBDal.getInstance().get(TAShift.class, shiftID);
	        	
	        	int size = rs.getBigDecimal("size").intValue();
	        	
	        	shiftruleSize.put(shift, size);
	        	
	        }
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//load public holiday
		sqlQuery="select distinct c.date1"
				+ " from ad_org a"
				+ " inner join c_calendar b on b.c_calendar_id=a.c_calendar_id"
				+ " inner join C_NonBusinessDay c on c.c_calendar_id=b.c_calendar_id"
				+ " where a.ad_org_id="
				+ " (select ad_org_getcalendarowner from ad_org_getcalendarowner(?))"
				+ " and c.date1>=?";
		try {
			connection = conn.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, orgID);
			ps.setDate(2, startdatesql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()){
	        	Date holidaydate = rs.getDate("date1");
	        	holidayList.add(holidaydate);
	        }
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//load tarif lembur hari biasa
		sqlQuery = "select a.position, b.maxlevel, b.multiplier*a.tarif as lemburperjam"
				+ " from ot_set_overtime a"
				+ " inner join ot_business_rate b on b.ot_set_overtime_id=a.ot_set_overtime_id"
				+ " where a.ad_client_id=?"
				+ " and a.validto>=?"
				+ " and a.position is not null"
				+ " and b.maxlevel is not null"
				+ " and b.multiplier is not null"
				+ " and a.tarif is not null"
				+ " order by a.position, b.maxlevel";
		try {
			connection = conn.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, clientID);
			ps.setDate(2, startdatesql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()){
	        	String jabatan = rs.getString("position");
	        	double maxlevel = rs.getBigDecimal("maxlevel").doubleValue();
	        	double lemburperjam = rs.getBigDecimal("lemburperjam").doubleValue();
	        	HashMap<Double, Double> tarifprogresiflembur = null;
	        	if (tarifLemburHariBiasa.containsKey(jabatan))
	        		tarifprogresiflembur=tarifLemburHariBiasa.get(jabatan);
	        	else
	        		tarifprogresiflembur = new HashMap<Double, Double>();
	        	tarifprogresiflembur.put(maxlevel, lemburperjam);
	        	tarifLemburHariBiasa.put(jabatan, tarifprogresiflembur);
	        }
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//load tarif lembur hari libur
		sqlQuery = "select a.position, b.maxlevel, b.multiplier*a.tarif as lemburperjam"
				+ " from ot_set_overtime a"
				+ " inner join ot_nonbusiness_rate b on b.ot_set_overtime_id=a.ot_set_overtime_id"
				+ " where a.ad_client_id=?"
				+ " and a.validto>=?"
				+ " and a.position is not null"
				+ " and b.maxlevel is not null"
				+ " and b.multiplier is not null"
				+ " and a.tarif is not null"
				+ " order by a.position, b.maxlevel";
		try {
			connection = conn.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, clientID);
			ps.setDate(2, startdatesql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()){
	        	String jabatan = rs.getString("position");
	        	double maxlevel = rs.getBigDecimal("maxlevel").doubleValue();
	        	double lemburperjam = rs.getBigDecimal("lemburperjam").doubleValue();
	        	HashMap<Double, Double> tarifprogresiflembur = null;
	        	if (tarifLemburHariLibur.containsKey(jabatan))
	        		tarifprogresiflembur=tarifLemburHariLibur.get(jabatan);
	        	else
	        		tarifprogresiflembur = new HashMap<Double, Double>();
	        	tarifprogresiflembur.put(maxlevel, lemburperjam);
	        	tarifLemburHariLibur.put(jabatan, tarifprogresiflembur);
	        }
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//do overtime calculation
		List<ot_overtime> result = doCalculateOvertime(overtimeList);
		
		//update sp on overtime, build accumulative overtime amount, put it into hashmap
		for (ot_overtime overtime : result){
			BusinessPartner employee4 = overtime.getBusinessPartner();
			double lembur = overtime.getAmount().doubleValue();
			if (akumulasiLembur.containsKey(employee4))
				lembur += akumulasiLembur.get(employee4);
			akumulasiLembur.put(employee4, lembur);
			overtime.setPYRSalarypayment(salaryPayment);
			OBDal.getInstance().save(overtime);
				
		}

		//load spee value/key map
		sqlQuery = "select a.pyr_sp_employee_id, b.pyr_spe_earning_id"
				+ " from pyr_sp_employee a"
				+ " inner join pyr_spe_earning b on b.pyr_sp_employee_id=a.pyr_sp_employee_id"
				+ " inner join pyr_earning c on c.pyr_earning_id=b.pyr_earning_id"
				+ " where a.pyr_salarypayment_id=?"
				+ " and c.value='TLB'"
				+ " and a.c_bpartner_id is not null";
		try {
			connection = conn.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlQuery);
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, clientID);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()){
	        	String speID = rs.getString("pyr_sp_employee_id");
	        	if (speID==null || speID.isEmpty())
	        		throw new OBException("spe id is null or empty");
	        	pyr_sp_employee spe2= OBDal.getInstance().get(pyr_sp_employee.class, speID);
	        	if (spe2==null)
	        		throw new OBException(speID+" is not valid spe id");
	        	String speeID = rs.getString("pyr_spe_earning_id");
	        	if (speeID==null || speeID.isEmpty())
	        		throw new OBException("spee id is null or empty");
	        	pyr_spe_earning spee2 = OBDal.getInstance().get(pyr_spe_earning.class, speeID);
	        	if (spee2==null)
	        		throw new OBException(speeID+" is not valid spee id");
	        	speeKeyMap.put(spe2, "TLB", spee2);
	        }
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//set overtime amount on SPEE
		for (pyr_sp_employee spe2 : speeKeyMap.rowKeySet()){
			BusinessPartner employee2 = spe2.getBusinessPartner();
			double lembur = 0.00;
			if (akumulasiLembur.containsKey(employee2))
				lembur = akumulasiLembur.get(employee2);
			pyr_spe_earning spee2 = speeKeyMap.get(spe2, "TLB");
			spee2.setAmount(new BigDecimal(lembur));
			OBDal.getInstance().save(spee2);
		}
			
		//commit transaction
		try {
			connection = conn.getConnection();
			connection.commit();
		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	private List<ot_overtime> doCalculateOvertime(List<String> overtimeList) {
		List<ot_overtime> output = new ArrayList<ot_overtime>();
		if (overtimeList==null)
			  return output;
		
		for (String overtimeID : overtimeList){
			double totalnominallembur=0.0;
			double totaljamlembur=0.0;
			StringBuilder validationnotebuilder = new StringBuilder();
			ot_overtime overtime = OBDal.getInstance().get(ot_overtime.class, overtimeID);
			if (overtime==null)
				throw new OBException("overtime ID "+overtimeID+" is not valid");
			
			//get check in date and time
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(overtime.getCheckin().getTime());
			int jam = cal.get(Calendar.HOUR_OF_DAY);
			int menit = cal.get(Calendar.MINUTE);
			cal.setTime(overtime.getStartingDate());
			cal.set(Calendar.HOUR_OF_DAY, jam);
			cal.set(Calendar.MINUTE, menit);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date checkin = cal.getTime();
			
			//get check out date and time
			cal.setTimeInMillis(overtime.getCheckout().getTime());
			jam = cal.get(Calendar.HOUR_OF_DAY);
			menit = cal.get(Calendar.MINUTE);
			cal.setTime(overtime.getEndingDate());
			cal.set(Calendar.HOUR_OF_DAY, jam);
			cal.set(Calendar.MINUTE, menit);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date checkout = cal.getTime();
			
			if (!checkout.after(checkin)){
				overtime.setAmount(BigDecimal.ZERO);
				overtime.setValidationnote("check in after check out, overtime is not valid.");
				overtime.setCalculated(true);
				output.add(overtime);
				continue;
			}
			
			cal.setTime(checkout);
			long checkoutlong = cal.getTimeInMillis();
			cal.setTime(checkin);
			long checkinlong = cal.getTimeInMillis();
			long duration = checkoutlong-checkinlong;
			int days = (int) (duration / (1000*60*60*24)) + 1;
			double amount = 0.00;
			long jumlahjam = 0;
			for (int i=1; i<=days; i++){
				Date harilembur = null;
				if (i==1 && i==days){
					duration = checkoutlong-checkinlong;
					cal.setTime(checkin);
					harilembur=cal.getTime();
				} else if (i==1 && days>1){
					cal.setTime(checkin);
					harilembur=cal.getTime();
					cal.add(Calendar.DAY_OF_MONTH, 1);
					cal.set(Calendar.HOUR, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					checkoutlong=cal.getTimeInMillis();
					duration = checkoutlong-checkinlong;
					
				} else if (i>1 && days>i){
					duration = 1000*60*60*24; //24 jam
					cal.setTime(checkin);
					cal.add(Calendar.DAY_OF_MONTH, i-1);
					harilembur=cal.getTime();
				} else if (i>1 && days==i){
					cal.setTime(checkout);
					checkoutlong = cal.getTimeInMillis();
					cal.set(Calendar.HOUR, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					checkinlong = cal.getTimeInMillis();
					duration = checkoutlong-checkinlong;
					harilembur=cal.getTime();
				} else
					throw new OBException("invalid state");
				jumlahjam = (duration / (1000 * 60 * 60)) % 24;
				long jumlahmenit = (duration / (1000 * 60)) % 60;
				double fraksijam = jumlahmenit/60.0;
				fraksijam = new BigDecimal(fraksijam).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//dibulatkan 2 angka dibelakang koma
				if (i==1)
					validationnotebuilder.append("overtime day "+i+" of "+days);
				else
					validationnotebuilder.append("\r\n"+"overtime day "+i+" of "+days);
				String employeePosition=null;
		          if (overtime.getPosition()!=null)
		          	employeePosition=overtime.getPosition().getSearchKey();
				amount = getNominalLembur(overtime.isManual(), employeePosition, overtime.getBusinessPartner(), 
						harilembur, (jumlahjam+fraksijam), validationnotebuilder);
				validationnotebuilder.append("\r\n"+"  total hour day "+i+": "+(jumlahjam+fraksijam)+", total overtime amount: "+amount);
				totalnominallembur += amount;
				totaljamlembur += jumlahjam+fraksijam;
				
				
			}
			validationnotebuilder.append("\r\n"+"total hour: "+totaljamlembur+", total overtime amount: "+totalnominallembur);
			overtime.setAmount(new BigDecimal(totalnominallembur));
			String validationnote = validationnotebuilder.toString();
			overtime.setValidationnote(validationnote);
			overtime.setCalculated(true);
			overtime.setIntduration(new BigDecimal(totaljamlembur));
			output.add(overtime);
		}
		
		return output;
	  
	}
	
	private double getNominalLembur(boolean manual, String position, BusinessPartner employee,
			Date harilembur, double jumlahjamlembur, StringBuilder validationnotebuilder) {
		
		double lembur = 0;
		HashMap<Double, Double> tarifprogresiflembur = null;
		boolean harilibur = false;
		if (holidayList.contains(harilembur))
			harilibur=true;
		else if (!manual){
			//menentukan hari libur dari absensi
			HashMap<Date, TAShift> shiftList = employeeShiftMap.get(employee);
			for (Date validtoshift : shiftList.keySet()){
				if (harilembur.before(validtoshift)){
					TAShift shift = shiftList.get(validtoshift);
					Date validfrom = shift.getValidFromDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(validfrom);
					long start = cal.getTimeInMillis();
					cal.setTime(harilembur);
					long end = cal.getTimeInMillis();
					long duration = end-start;
					int days = (int) (duration / (1000*60*60*24));
					if (!shiftruleSize.containsKey(shift)){
						validationnotebuilder.append("\r\n"+"  employee does not have valid shift as per date "+df.format(harilembur));
						return lembur;
					}
					int shiftSize = shiftruleSize.get(shift);
					int shiftlineseqno = days % shiftSize;
					if (!shiftruleMap.containsKey(shift)){
						validationnotebuilder.append("\r\n"+"  employee does not have valid shift as per date "+df.format(harilembur));
						return lembur;
					}
					List<TA_ShiftLine> shiftlineList = shiftruleMap.get(shift);
					if (shiftlineList.size()<(shiftlineseqno+1))
						throw new OBException("diperlukan posisi shift line ke "+shiftlineseqno+" tetapi ukuran shift line hanya "+shiftlineList.size());
					TA_ShiftLine shiftline = shiftlineList.get(shiftlineseqno);
					harilibur=shiftline.isOff();
				}
			}
		}
			
		if (harilibur){
			//proses hari libur
			if (tarifLemburHariLibur.containsKey(position)){
				tarifprogresiflembur = tarifLemburHariLibur.get(position);
				validationnotebuilder.append(", "+df.format(harilembur)+" using non business day fare.");
			}
				
		} else {
			//proses hari biasa
			if (tarifLemburHariBiasa.containsKey(position)){
				tarifprogresiflembur = tarifLemburHariBiasa.get(position);
				validationnotebuilder.append(", "+df.format(harilembur)+" using business day fare.");
			}
				
		}
		
		if (tarifprogresiflembur==null){
			validationnotebuilder.append("\r\n"+"  employee does not have valid position in overtime catalogue");
			return lembur;
		}
			
		
		Set<Double> upperboundset = new TreeSet<Double>(tarifprogresiflembur.keySet());
		double akumulasijamlembur = 0;
		for (Double upperbound : upperboundset){
			double tarifperjam = tarifprogresiflembur.get(upperbound);
			if (jumlahjamlembur<=upperbound){
				double lembur1 = ((jumlahjamlembur-akumulasijamlembur)*tarifperjam);
				lembur=lembur+lembur1;
				validationnotebuilder.append("\r\n  "+(jumlahjamlembur-akumulasijamlembur)+
						" hour(s), fare: "+tarifperjam+", overtime amount: "+lembur1+".");
				break;
			} else{
				double lembur1 = ((upperbound-akumulasijamlembur)*tarifperjam);
				lembur=lembur+lembur1;
				validationnotebuilder.append("\r\n  "+(upperbound-akumulasijamlembur)+
						" hour(s), fare: "+tarifperjam+", overtime amount: "+lembur1+".");
				akumulasijamlembur += upperbound;
			}
		}
		return lembur;
	}

}
