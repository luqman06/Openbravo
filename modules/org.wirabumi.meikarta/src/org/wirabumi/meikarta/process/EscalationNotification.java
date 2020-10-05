package org.wirabumi.meikarta.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.access.Role;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.access.UserRoles;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.gen.oez.oez_documentrouting;
import org.wirabumi.meikarta.utility.EmailUtility;

import com.google.common.collect.HashBasedTable;

public class EscalationNotification extends DalBaseProcess {
	private ProcessLogger logger;

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		logger = bundle.getLogger();
		Connection connection = new DalConnectionProvider().getConnection();
		String clientID = OBContext.getOBContext().getCurrentClient().getId();
		
		String escalationThreshold = Utility.getPreference(bundle.getContext().toVars(), 
				"EscalationThreshold", null); //prioritas 2
		if (escalationThreshold==null || escalationThreshold.isEmpty())
			escalationThreshold="6"; //1 minggu
		int jarakeskalasi = getJarakEskalasi(bundle);
		
		//build table yang memetakan tabID dan docStatus, ke list of role
		//tidak termasuk reactive, close, dan void
		final HashBasedTable<String, String, List<Role>> documentrouting = getDocumentRouting();
		
		
		notifyPurchaseRequestEscalation(connection, clientID, documentrouting, jarakeskalasi);
		notifyPurchaseOrderEscalation(connection, clientID, documentrouting, jarakeskalasi);
		notifyPurchaseInvoiceEscalation(connection, clientID, documentrouting, jarakeskalasi);
	}

	private int getJarakEskalasi(ProcessBundle bundle) {
		String escalationThreshold = Utility.getPreference(bundle.getContext().toVars(), 
				"EscalationThreshold", null); //prioritas 2
		if (escalationThreshold==null || escalationThreshold.isEmpty())
			escalationThreshold="6"; //1 minggu
		return Integer.parseInt(escalationThreshold);
	}

	private HashBasedTable<String, String, List<Role>> getDocumentRouting() {
		// output memetakan tabID, documentstatus, dan role yang memiliki otorisasi
		HashBasedTable<String, String, List<Role>> output = HashBasedTable.create();
		OBCriteria<oez_documentrouting> docroutingC = OBDal.getInstance().createCriteria(oez_documentrouting.class);
		for (oez_documentrouting routing : docroutingC.list()){
			Role role = routing.getRole();
			String documentAction = routing.getDocumentAction();
			if (documentAction.equals("CL") || documentAction.equals("VO") || documentAction.equals("RE"))
				continue; //untuk action closing, void, atau reactive, tidak perlu di notifikasi
			if (output.contains(routing.getTab().getId(), routing.getDocumentStatus())){
				List<Role> roles = output.get(routing.getTab().getId(), routing.getDocumentStatus());
				if (!roles.contains(role))
					roles.add(role);
			} else {
				List<Role> roles = new ArrayList<>();
				roles.add(role);
				output.put(routing.getTab().getId(), routing.getDocumentStatus(), roles);
			}
		}
		return output;
	}

	private void notifyPurchaseRequestEscalation(Connection connection, String clientID,
			HashBasedTable<String, String, List<Role>> documentrouting,
			int jarakeskalasi) {
		/*
		 * cari PR yang audit trailnya memiliki usia lebih dari 1 minggu, dan belum processed.
		 * kirim email notifikasi, bedarsarkan:
		 * 1. cari document routing yang mengarah ke header PR dengan docstatus from, sama dengan status PR
		 * 2. dari role nya, cari usernya
		 * 3. kirim email ke user yang ditemukan
		 */
		
		
		HashMap<String, Date> requisitiondata = getRequisitionLatestApproval(connection, clientID);
		final Calendar cal = Calendar.getInstance();
		final DateTime enddate = new DateTime(cal.getTimeInMillis());
		for (String id : requisitiondata.keySet()){
			Requisition requisition = OBDal.getInstance().get(Requisition.class, id);
			Date latest_approval = requisitiondata.get(id);
			DateTime startdate = new DateTime(latest_approval.getTime());
			int d = Days.daysBetween(startdate, enddate).getDays();
			if (d!=jarakeskalasi)
				continue;
			//sudah 1 minggu belum di approve, kirim notifikasi
			//cari dulu dikirim ke siapa
			if (!documentrouting.contains("800249", requisition.getDocumentStatus()))
				continue;
			List<Role> roles = documentrouting.get("800249", requisition.getDocumentStatus());
			for (Role role : roles){
				for (UserRoles userrole : role.getADUserRolesList()){
					User user = userrole.getUserContact();
					String recipientaddress = user.getEmail();
					
					//send email notification
					EmailUtility.sendEscalationEmail("Requisition", requisition.getDocumentNo(), recipientaddress);
					logger.log("send email for requsition "+requisition.getDocumentNo()+" to "+user.getName()+System.lineSeparator());
				}
			}
		}
		
	}

	private HashMap<String, Date> getRequisitionLatestApproval(Connection connection, String clientID) {
		HashMap<String, Date> output = new HashMap<>();
		String sql = "select a.m_requisition_id, max(b.event_time::date) as latest_approval"
				+ " from m_requisition a"
				+ " inner join ad_audit_trail b on b.record_id=a.m_requisition_id"
				+ " where a.ad_client_id=?"
				+ " and b.ad_table_id='800212'" //table m_requisition
				+ " and b.ad_column_id='1004400026'" //column m_requisition.docstatus
				+ " and b.new_char=a.docstatus"
				+ " and a.processed='N'" //yang sudah processed tidak perlu di notifikasi
				+ " group by a.m_requisition_id";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String id = rs.getString("m_requisition_id");
				Date latest_approval = rs.getDate("latest_approval");
				output.put(id, latest_approval);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
		
		return output;
	}
	
	private void notifyPurchaseOrderEscalation(Connection connection, String clientID,
			HashBasedTable<String, String, List<Role>> documentrouting,
			int jarakeskalasi) {
		/*
		 * cari PO yang audit trailnya memiliki usia lebih dari 1 minggu, dan belum processed.
		 * kirim email notifikasi, bedarsarkan:
		 * 1. cari document routing yang mengarah ke header PO dengan docstatus from, sama dengan status PO
		 * 2. dari role nya, cari usernya
		 * 3. kirim email ke user yang ditemukan
		 */
		
		
		HashMap<String, Date> poData = getPOLatestApproval(connection, clientID);
		final Calendar cal = Calendar.getInstance();
		final DateTime enddate = new DateTime(cal.getTimeInMillis());
		for (String id : poData.keySet()){
			Order order = OBDal.getInstance().get(Order.class, id);
			Date latest_approval = poData.get(id);
			DateTime startdate = new DateTime(latest_approval.getTime());
			int d = Days.daysBetween(startdate, enddate).getDays();
			if (d!=jarakeskalasi)
				continue;
			//sudah 1 minggu belum di approve, kirim notifikasi
			//cari dulu dikirim ke siapa
			if (!documentrouting.contains("294", order.getDocumentStatus()))
				continue;
			List<Role> roles = documentrouting.get("294", order.getDocumentStatus());
			for (Role role : roles){
				for (UserRoles userrole : role.getADUserRolesList()){
					User user = userrole.getUserContact();
					String recipientaddress = user.getEmail();
					
					//send email notification
					EmailUtility.sendEscalationEmail("Purchase Order", order.getDocumentNo(), recipientaddress);
					logger.log("send email for purchase order "+order.getDocumentNo()+" to "+user.getName()+System.lineSeparator());
				}
			}
		}
		
	}

	private HashMap<String, Date> getPOLatestApproval(Connection connection, String clientID) {
		HashMap<String, Date> output = new HashMap<>();
		String sql = "select a.c_order_id, max(b.event_time::date) as latest_approval"
				+ " from c_order a"
				+ " inner join ad_audit_trail b on b.record_id=a.c_order_id"
				+ " where a.ad_client_id=?"
				+ " and b.ad_table_id='259'" //table c_order
				+ " and b.ad_column_id='2170'" //column c_order.docstatus
				+ " and b.new_char=a.docstatus"
				+ " and a.processed='N'" //yang sudah processed tidak perlu di notifikasi
				+ " group by a.c_order_id";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String id = rs.getString("c_order_id");
				Date latest_approval = rs.getDate("latest_approval");
				output.put(id, latest_approval);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
		
		return output;
	}
	
	private void notifyPurchaseInvoiceEscalation(Connection connection, String clientID,
			HashBasedTable<String, String, List<Role>> documentrouting,
			int jarakeskalasi) {
		/*
		 * cari PO yang audit trailnya memiliki usia lebih dari 1 minggu, dan belum processed.
		 * kirim email notifikasi, bedarsarkan:
		 * 1. cari document routing yang mengarah ke header PO dengan docstatus from, sama dengan status PO
		 * 2. dari role nya, cari usernya
		 * 3. kirim email ke user yang ditemukan
		 */
		
		
		HashMap<String, Date> piData = getPILatestApproval(connection, clientID);
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, jarakeskalasi); //TODO test dikurangi seminggu, seharusnya baris ini tidak perlu
		final DateTime enddate = new DateTime(cal.getTimeInMillis());
		for (String id : piData.keySet()){
			Invoice invoice = OBDal.getInstance().get(Invoice.class, id);
			Date latest_approval = piData.get(id);
			DateTime startdate = new DateTime(latest_approval.getTime());
			int d = Days.daysBetween(startdate, enddate).getDays();
			if (d!=jarakeskalasi)
				continue;
			//sudah 1 minggu belum di approve, kirim notifikasi
			//cari dulu dikirim ke siapa
			if (!documentrouting.contains("290", invoice.getDocumentStatus()))
				continue;
			List<Role> roles = documentrouting.get("290", invoice.getDocumentStatus());
			for (Role role : roles){
				for (UserRoles userrole : role.getADUserRolesList()){
					User user = userrole.getUserContact();
					String recipientaddress = user.getEmail();
					
					//send email notification
					EmailUtility.sendEscalationEmail("Purchase Invoice", invoice.getDocumentNo(), recipientaddress);
					logger.log("send email for purchase invoice "+invoice.getDocumentNo()+" to "+user.getName()+System.lineSeparator());
				}
			}
		}
		
	}

	private HashMap<String, Date> getPILatestApproval(Connection connection, String clientID) {
		HashMap<String, Date> output = new HashMap<>();
		String sql = "select a.c_invoice_id, max(b.event_time::date) as latest_approval"
				+ " from c_invoice a"
				+ " inner join ad_audit_trail b on b.record_id=a.c_invoice_id"
				+ " where a.ad_client_id=?"
				+ " and b.ad_table_id='318'" //table c_invoice
				+ " and b.ad_column_id='3494'" //column c_invoice.docstatus
				+ " and b.new_char=a.docstatus"
				+ " and a.processed='N'" //yang sudah processed tidak perlu di notifikasi
				+ " group by a.c_invoice_id";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String id = rs.getString("c_invoice_id");
				Date latest_approval = rs.getDate("latest_approval");
				output.put(id, latest_approval);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
		
		return output;
	}

}
