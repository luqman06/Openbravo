package org.wirabumi.hris.timeandattendance.erpCommon.ad_process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.timeandattendance.ImportManualSchedule;
import org.wirabumi.hris.timeandattendance.ManualSchedule;
import org.wirabumi.hris.timeandattendance.ManualScheduleGroup;

public class ProcessImportEmployeeManualSchedule extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		/*
		 * 1. get distinct bp, load bp to hashmap (key --> bp object)
		 * 2. get min/max date on temp table, then load matched exisiting msg record to hashmap (bp --> (date --> msg object))
		 * 3. loop each record on prev step, get manual schedule group, and update the field
		 * 4. insert/update msg entity
		 */
		
		final ConnectionProvider conn = new DalConnectionProvider();
		final Connection connection = conn.getConnection();
		
		final Client client = OBContext.getOBContext().getCurrentClient();
		
		//get manual schedule group (name --> manual schedule group entity)
		HashMap<String, ManualScheduleGroup> msgMap = getManualScheduleGroup();
		
		//langkah 1, get distinct bp, load bp to hashmap (key --> bp object)
		HashMap<String, BusinessPartner> bpMap = getBPMap(connection, client.getId());
		List<BusinessPartner> bpList = convertBpMapToList(bpMap); 
		
		//langkah 2, get min/max date on temp table, then load matched exisiting msg record to hashmap (bp key --> (date --> msg object))
		HashMap<String, HashMap<Date, String>> existingMSG = getExistingManualSchedule(connection, client, bpList);
		
		//langkah 3
		OBCriteria<ImportManualSchedule> imsC = OBDal.getInstance().createCriteria(ImportManualSchedule.class);
		imsC.add(Restrictions.eq(ImportManualSchedule.PROPERTY_IMPORTPROCESSCOMPLETE, false));
		List<ImportManualSchedule> imsL = imsC.list();
		for (ImportManualSchedule ims : imsL){
			StringBuilder sb = new StringBuilder();
			//update bp key
			String bpkey = ims.getEmployeekey();
			if (bpkey==null || bpkey.isEmpty())
				sb.append("employee key is null or empty").append(System.lineSeparator());
			if (!bpMap.containsKey(bpkey))
				sb.append("can not find employee with key "+bpkey).append(System.lineSeparator());
			BusinessPartner employee = bpMap.get(bpkey);
			ims.setBusinessPartner(employee);
			
			//update 1 to 31
			for (int i=1; i<=31; i++){
				String propnameKey = "scheduleGroup"+i+"Key";
				String propnameObject = "scheduleGroup"+i;
				String msgkey = (String)ims.getValue(propnameKey);
				if (msgkey!=null && !msgkey.isEmpty()){
					if (msgMap.containsKey(msgkey))
						ims.setValue(propnameObject, msgMap.get(msgkey));
					else
						sb.append("can not find manual schedule group with name "+msgkey+" for day "+i).append(System.lineSeparator());
				}
			}
			
			//save evaluated record
			if (sb.length()>0){
				if (sb.length()>2000)
					ims.setImportErrorMessage(sb.substring(0, 2000).toString());
				else
					ims.setImportErrorMessage(sb.toString());
			}
			OBDal.getInstance().save(ims);
			
			if (sb.length()>0)
				continue; //invalid IMS record, then continue to next record;
			
			//valid IMS, then create employee manual schedule record
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			for (int i=1; i<=31; i++){
				String propnameObject = "scheduleGroup"+i;
				ManualScheduleGroup msg = (ManualScheduleGroup)ims.getValue(propnameObject);
				if (msg==null)
					continue; //not valid day
				
				//start creating EMS
				String strYear = ims.getFiscalYear();
				Long month = ims.getMonth()-1;
				cal.set(Calendar.YEAR, Integer.parseInt(strYear));
				cal.set(Calendar.MONTH, month.intValue());
				cal.set(Calendar.DAY_OF_MONTH, 0);
				cal.add(Calendar.DAY_OF_MONTH, i);
				Date docdate = cal.getTime();
				
				ManualSchedule ms = null;//OBProvider.getInstance().get(ManualSchedule.class);
				if (existingMSG.containsKey(employee.getSearchKey())){
					HashMap<Date, String> tempOutput = existingMSG.get(employee.getSearchKey());
					if (tempOutput.containsKey(docdate)){
						String msID = tempOutput.get(docdate);
						ms = OBDal.getInstance().get(ManualSchedule.class, msID);
					}
				}
				if (ms==null)
					ms = OBProvider.getInstance().get(ManualSchedule.class);
				
				ms.setEmployee(employee);
				ms.setDocumentDate(docdate);
				ms.setManualScheduleGroup(msg);
				ms.setCheckIn(msg.getCheckIn());
				ms.setCheckOut(msg.getCheckOut());
				ms.setOff(msg.isOff());
				OBDal.getInstance().save(ms);
				
			}
		}
		
		//build return message
		OBError message = new OBError();
		message.setType("Success");
		message.setTitle("Success");
		message.setMessage("process import executed successfully.");
		
		bundle.setResult(message);
	}

	/**
	 * get exisiting manual schedule group master data based on it's client, no other restriction.
	 * @return hashmap of manual schedule group name --> manual schedule group entity 
	 */
	private HashMap<String, ManualScheduleGroup> getManualScheduleGroup() {
		OBCriteria<ManualScheduleGroup> msgC = OBDal.getInstance().createCriteria(ManualScheduleGroup.class);
		HashMap<String, ManualScheduleGroup> output = new HashMap<>();
		for (ManualScheduleGroup msg : msgC.list()){
			String name = msg.getName();
			output.put(name, msg);
		}
		return output;
	}

	/**
	 * convert loaded business partner map into list container of string, consists of loaded business partner ob object
	 * @param bpMap hashmap of business partner name --> business partner entity
	 * @return array of business partner id based on bpMap
	 */
	private List<BusinessPartner> convertBpMapToList(HashMap<String, BusinessPartner> bpMap) {
		List<BusinessPartner> output = new ArrayList<>();
		for (BusinessPartner bp : bpMap.values()){
			output.add(bp);
		}
		return output;
	}

	/**
	 * load exisiting employee manual schedule record into hashmap.
	 * to improve performace, we avoid loading all existing employee manual schedule record,
	 * we will get min and max date on unimported employee manual schedule,
	 * we also get distinct of employee on unimported employee manual schedule,
	 * then use those 3 variable to get existing employee manual schedule, that may used for update purpose
	 * so, we avoid create duplicated employee manual schedule, same employee and same document date at the same time. 
	 * 
	 * @param connection sql connection used for prepared statement
	 * @param client client object to filter prepared statement
	 * @param bpIDs distinct employee id to filter prepared statement
	 * @return hashmap of exisiting employee manual schedule. employeeKey --> (document date --> exisitng EMS record)
	 * @throws SQLException 
	 */
	private HashMap<String, HashMap<Date, String>> getExistingManualSchedule(Connection connection,
			Client client, List<BusinessPartner> bpIDs) throws SQLException {
		HashMap<String, HashMap<Date, String>> output = new HashMap<>(); 
		
		String sql = "select min(year) as minyear, min(month) as minmonth, max(year) as maxyear, max(month) as maxmonth"
				+ " from ta_i_manualschedule"
				+ " where ad_client_id=?"
				+ " and i_isimported='N'";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, client.getId());
		ResultSet rs = ps.executeQuery();
		String minyear=null, maxyear=null;
		BigDecimal minmonth=null, maxmonth=null;
		while (rs.next()){
			minyear = rs.getString("minyear");
			maxyear = rs.getString("maxyear");
			minmonth = rs.getBigDecimal("minmonth");
			maxmonth = rs.getBigDecimal("maxmonth");
		}
		
		if (minyear==null || minyear.isEmpty() ||
				maxyear==null || maxyear.isEmpty() ||
				minmonth==null && maxmonth==null){
			//invalid record
			throw new OBException("invalid date range min/max month/year");
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		//build minimum date
		cal.set(Calendar.YEAR, Integer.parseInt(minyear));
		cal.set(Calendar.MONTH, minmonth.intValue()-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		java.sql.Date mindate = new java.sql.Date(cal.getTimeInMillis());
		
		//build maximum date
		cal.set(Calendar.YEAR, Integer.parseInt(maxyear));
		cal.set(Calendar.MONTH, maxmonth.intValue()-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		java.sql.Date maxdate = new java.sql.Date(cal.getTimeInMillis());
		
		//create array of bpIDs
		String bpIDinStr = Utility.getInStrList(bpIDs);
		
		sql = "select b.value as employeekey, a.documentdate, a.ta_manualschedule_id"
				+ " from ta_manualschedule a"
				+ " inner join c_bpartner b on b.c_bpartner_id=a.c_bpartner_id"
				+ " where a.ad_client_id=?"
				+ " and a.documentdate>=?"
				+ " and a.documentdate<=?"
				+ " and a.c_bpartner_id in ("+bpIDinStr+")";
		
		//clear preapred statement object and it's result set
		ps.clearBatch(); ps.clearParameters(); ps.clearWarnings();
		ps.close();
		rs.close();
		
		ps = connection.prepareStatement(sql);
		ps.setString(1, client.getId());
		ps.setDate(2, mindate);
		ps.setDate(3, maxdate);
		
		rs = ps.executeQuery();
		while (rs.next()){
			String employeeKey = rs.getString("employeekey");
			Date docdate = rs.getDate("documentdate");
			String emsID = rs.getString("ta_manualschedule_id");
			if (employeeKey==null || employeeKey.isEmpty() ||
					docdate==null || emsID==null || emsID.isEmpty())
				continue; //invalid record;
			
			//employeeKey --> (document date --> exisitng EMS ID)
			HashMap<Date, String> tempOutput = null;
			if (output.containsKey(employeeKey))
				tempOutput = output.get(employeeKey);
			else
				tempOutput = new HashMap<>();
			tempOutput.put(docdate, emsID);
			output.put(employeeKey, tempOutput);
		}
		
		return output;
	}

	/**
	 * load distinct employee based on unimported employee manual schedule
	 * @param connection sql connection for prepared statement
	 * @param clientID client ID to filter prepred statement
	 * @return hashmap employee key --> employee entity
	 * @throws SQLException 
	 */
	private HashMap<String, BusinessPartner> getBPMap(Connection connection, String clientID) throws SQLException {
		HashMap<String, BusinessPartner> output = new HashMap<>();
		String sql = "select distinct b.value as employeekey, b.c_bpartner_id"
				+ " from ta_i_manualschedule a"
				+ " inner join c_bpartner b on b.value=a.employeekey and b.ad_client_id=a.ad_client_id"
				+ " where a.ad_client_id=?"
				+ " and i_isimported='N'";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, clientID);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			String value = rs.getString("employeekey");
			String bpID = rs.getString("c_bpartner_id");
			BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
			output.put(value, bp);
		}
		
		return output;
	}

}
