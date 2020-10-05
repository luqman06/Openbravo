package org.wirabumi.gen.oez.porting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;

public class RequisitionActionHandlerForWaitingRelease extends DocumentRoutingHandlerAction {
	
	@Override
	public void doRouting(String adWindowId, String adTabId, String doc_status_to, VariablesSecureApp vars,
			List<String> recordId) {
		// TODO Auto-generated method stub

		for (String requisitionID : recordId){
			if (!doc_status_to.toLowerCase().contains("oez_waitingrelease")) {
				throw new OBException("@ActionNotAllowedHere@");
			}			
			// validation investasi dan punya attachment 
			boolean isValidRequisition = validateRequistion(vars, requisitionID);
			if (!isValidRequisition)  
				throw new OBException("requisition is invalid, may be some requisition line has capital investment product but no attachment uploaded.");

			Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
			requisition.setDocumentAction(doc_status_to);
			OBDal.getInstance().save(requisition);
			OBDal.getInstance().flush();	
		}
	}

	private boolean validateRequistion(VariablesSecureApp vars, String requisitionID) {
		//jika memiliki lines yg inventory, dan memiliki attachment, maka dinyatakan valid
		boolean isvalid=true;

		Requisition r = OBDal.getInstance().get(Requisition.class, requisitionID);
		String investmentAssetGroupKey = Utility.getPreference(vars, "InvestmentAssetGroupKey", null);
		if (StringUtils.isEmpty(investmentAssetGroupKey))
			investmentAssetGroupKey="INVESTASI";
		for (RequisitionLine rl : r.getProcurementRequisitionLineList()) {
			boolean isInvestasi = rl.getProduct().getProductCategory().getSearchKey().equalsIgnoreCase(investmentAssetGroupKey);

			if (isInvestasi)
				isvalid = isRequisitionLineHasAttachment(rl);
			if (!isvalid)
				break;
		}

		return isvalid;
	}

	private boolean isRequisitionLineHasAttachment(RequisitionLine rl) {
		String sqlquery = "select c_file_id from c_file"
				+ " where ad_table_id= ?"
				+ " and ad_record_id= ?";
		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection;
		boolean hasAttachment = false;
		try {
			connection = conn.getConnection();
			PreparedStatement ps = connection.prepareStatement(sqlquery);
			ps.setString(1, "800214"); //m_requisitionline
			ps.setString(2, rl.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next())
				hasAttachment=true;

		} catch (NoConnectionAvailableException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OBException("Attachment document is required for procurement of this product");
		}
		return hasAttachment;
	}

	@Override
	public String getCoDocumentNo(String recordID, Tab tab) {
		// TODO sementara null dulu
		return null;
	}
}
