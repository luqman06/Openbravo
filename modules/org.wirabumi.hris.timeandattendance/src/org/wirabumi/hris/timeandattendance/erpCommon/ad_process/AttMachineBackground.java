package org.wirabumi.hris.timeandattendance.erpCommon.ad_process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.model.ad.domain.Preference;
import org.openbravo.scheduling.KillableProcess;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.openbravo.service.db.DalBaseProcess;

/**
 * @author rahmadi
 * background process to get data from attendance machine server and insert it to table attendance machine
 */
public class AttMachineBackground extends DalBaseProcess implements KillableProcess{

	private ProcessLogger logger;
	OBError result = new OBError();
	private String message;
	private String recordTime;
	private String employeeId;
	// Add a variable 'killProcess' to control the kill implementation and set false by default
	private boolean killProcess = false;
	private String clientId;
    private long counter = 0;
    private String attendanceIn;
	private String attendanceOut;

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		logger = bundle.getLogger();
		OBCriteria<Preference> attendanceInPref = OBDal.getInstance().createCriteria(Preference.class);
		attendanceInPref.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "attendance machine in"));
		attendanceInPref.setFetchSize(1);
		List<Preference> attendanceInPrefList = attendanceInPref.list();
		for (Preference preference : attendanceInPrefList) {
			attendanceIn = preference.getSearchKey();
		}
		OBCriteria<Preference> attendanceOutPref = OBDal.getInstance().createCriteria(Preference.class);
		attendanceOutPref.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, "attendance machine out"));
		attendanceOutPref.setFetchSize(1);
		List<Preference> attendanceOutPrefList = attendanceOutPref.list();
		for (Preference preference : attendanceOutPrefList) {
			attendanceOut = preference.getSearchKey();
		}

		if(attendanceIn == null || attendanceOut == null) {
			message = "There is no preference with 'attendance machine in' or 'attendance machine out' attribute";
			logger.logln(message);
			result.setType("Error");
			result.setTitle(OBMessageUtils.messageBD("Error"));
			result.setMessage(message);
			bundle.setResult(result);
			throw new OBException(message);
		}

		try {
			getAndInsertMachineData(bundle, attendanceIn, "1");
			message = "There is " + counter + " of check in record inserted to Window Attendance Machine";
            logger.logln(message);
            
			getAndInsertMachineData(bundle, attendanceOut, "2");
			message = "There is " + counter + " of check out record inserted to Window Attendance Machine";
            logger.logln(message);
           
            insertAttendanceEntry(clientId);
            generateOvertimeRequest(clientId);
		} catch (Exception e) {
			message = "Error message = " + e.getMessage() + "/nCause = " + e.getCause();
			logger.logln(message);
			result.setType("Error");
			result.setTitle(OBMessageUtils.messageBD("Error"));
			result.setMessage(message);
			bundle.setResult(result);
			throw new OBException(message);
		}
	}
	
	private void getAndInsertMachineData(ProcessBundle bundle, String APIParameter, String inOrOut) {
		try {
			clientId = bundle.getContext().getClient();
			String webService = "http://localhost:1000/attendance/read" + APIParameter;
            URL url = new URL(webService);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output;
				counter = 0;
				while ((output = br.readLine()) != null) {
					JSONObject json = new JSONObject(output);
					JSONArray jsonResponse = json.getJSONArray("response");
					for (int n = 0; n < jsonResponse.length(); n++) {
						if (killProcess){
                            throw new OBException("Process killed");
                        }
                        JSONObject object = jsonResponse.getJSONObject(n);
						recordTime = object.getString("recordTime");
                        employeeId = object.getString("deviceUserId");
                        OBContext.setOBContext("0");
                        insertAttMachineData(clientId ,recordTime, employeeId, inOrOut);
                        counter = counter + 1;
					}
				}
			}
		} catch (Exception e) {
			throw new OBException(e);
		}
	}
	
	private void insertAttendanceEntry(String clientId) {
		String sql = " SELECT ta_import_attendance(?) ";
		Connection conn = OBDal.getInstance().getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, clientId);
			ResultSet rs = ps.executeQuery();
		}catch(Exception e){
			throw new OBException(e);
		}
	}
	
    private void generateOvertimeRequest(String clientId) {
		String sql = " SELECT ta_generate_ot_request(?) ";
		Connection conn = OBDal.getInstance().getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, clientId);
			ResultSet rs = ps.executeQuery();
		}catch(Exception e){
			throw new OBException(e);
		}
	}

	private void insertAttMachineData(String clientId, String recordTime, String employeeId, String machineId) {
		String sql = " SELECT ta_import_attendance_machine( ? , ? , ? , ? ) ";
		Connection conn = OBDal.getInstance().getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, clientId);
			ps.setString(2, recordTime);
			ps.setString(3, employeeId);
			ps.setString(4, machineId);
			ResultSet rs = ps.executeQuery();
		}catch(Exception e){
			throw new OBException(e);
		}
	}

    @Override
	public void kill(ProcessBundle bundle) throws Exception {
	    // When kill is called set variable 'stop' to true so the process will be interrupted in the
	    // next iteration: while (partnerScroller.next() && !stop)
	    this.killProcess = true;
	}
}

