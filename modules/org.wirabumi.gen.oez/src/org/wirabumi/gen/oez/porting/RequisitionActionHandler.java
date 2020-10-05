package org.wirabumi.gen.oez.porting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
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

public class RequisitionActionHandler extends DocumentRoutingHandlerAction {
	private final String reactiveStatus="RE";
	private final String completeStatus="CO";
	private final String draftStatus="DR";
	private final String closedStatus="CL";
	private final String voidStatus="VO";
	private final String processID="1004400003";
	private final String waitingRelease1="oez_waitingrelease1";
	private final String waitingRelease2="oez_waitingrelease2";
	private final String waitingRelease3="oez_waitingrelease3";
	private final String waitingRelease4="oez_waitingrelease4";
	private final String waitingRelease5="oez_waitingrelease5";
	
	//start - tambahan dari Ahmad 2018.08.31
	private final String pendingRelease1="oez_pendingrelease1";
	private final String pendingRelease2="oez_pendingrelease2";
	private final String pendingRelease3="oez_pendingrelease3";
	private final String pendingRelease4="oez_pendingrelease4";
	private final String pendingRelease5="oez_pendingrelease5";	
	//end - tambahan dari Ahmad 2018.08.31
			
	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List<String> recordId) {
		if (doc_status_to.equalsIgnoreCase(voidStatus))
			throw new OBException("@ActionNotAllowedHere@"); //requisition tidak boleh di void
		
		for (String requisitionID : recordId){
			
			boolean isValidRequisition = validateRequistion(vars, requisitionID);
			if (!isValidRequisition)
				throw new OBException("requisition is invalid, may be some requisition line has capital investment product but no attachment uploaded.");
			
			if (doc_status_to.equalsIgnoreCase(completeStatus)){
				Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
				//cek apakah dari CL atau VO, jika ya, maka exception
				if (requisition.getDocumentStatus().equalsIgnoreCase(closedStatus)||
						requisition.getDocumentStatus().equalsIgnoreCase(voidStatus))
					throw new OBException("@ActionNotAllowedHere@");
				
				//ubdah dulu docstatus menjadi DR, baru di complete
				requisition.setDocumentStatus(draftStatus);
				requisition.setDocumentAction(doc_status_to);
				OBDal.getInstance().save(requisition);
				OBDal.getInstance().flush();
				
			} else if (doc_status_to.equalsIgnoreCase(reactiveStatus)){
				//cek apakah doc status adalah CO, jika tidak maka exception
				Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
				if (!requisition.getDocumentStatus().equalsIgnoreCase(completeStatus))
					throw new OBException("@ActionNotAllowedHere@");
				requisition.setDocumentAction(doc_status_to);
				OBDal.getInstance().save(requisition);
				OBDal.getInstance().flush();
			
			//start - tambahan dari Ahmad 2018.08.31
			} else if (doc_status_to.equalsIgnoreCase(pendingRelease1)){
				//cek jika doc status bukan waitingRelease1, maka exception
				Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
				if (!requisition.getDocumentStatus().equalsIgnoreCase(waitingRelease1))
					throw new OBException("@ActionNotAllowedHere@");
				requisition.setDocumentAction(doc_status_to);
				OBDal.getInstance().save(requisition);
				OBDal.getInstance().flush();				
				
			} else if (doc_status_to.equalsIgnoreCase(pendingRelease2)){
				//cek jika doc status bukan waitingRelease1, maka exception
				Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
				if (!requisition.getDocumentStatus().equalsIgnoreCase(waitingRelease2))
					throw new OBException("@ActionNotAllowedHere@");
				requisition.setDocumentAction(doc_status_to);
				OBDal.getInstance().save(requisition);
				OBDal.getInstance().flush();				
			
			} else if (doc_status_to.equalsIgnoreCase(pendingRelease3)){
				//cek jika doc status bukan waitingRelease1, maka exception
				Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
				if (!requisition.getDocumentStatus().equalsIgnoreCase(waitingRelease3))
					throw new OBException("@ActionNotAllowedHere@");
				requisition.setDocumentAction(doc_status_to);
				OBDal.getInstance().save(requisition);
				OBDal.getInstance().flush();				
				
			} else if (doc_status_to.equalsIgnoreCase(pendingRelease4)){
				//cek jika doc status bukan waitingRelease1, maka exception
				Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
				if (!requisition.getDocumentStatus().equalsIgnoreCase(waitingRelease4))
					throw new OBException("@ActionNotAllowedHere@");
				requisition.setDocumentAction(doc_status_to);
				OBDal.getInstance().save(requisition);
				OBDal.getInstance().flush();				
				
			} else if (doc_status_to.equalsIgnoreCase(pendingRelease5)){
				//cek jika doc status bukan waitingRelease1, maka exception
				Requisition requisition = OBDal.getInstance().get(Requisition.class, requisitionID);
				if (!requisition.getDocumentStatus().equalsIgnoreCase(waitingRelease5))
					throw new OBException("@ActionNotAllowedHere@");
				requisition.setDocumentAction(doc_status_to);
				OBDal.getInstance().save(requisition);
				OBDal.getInstance().flush();				
			//end - tambahan dari Ahmad 2018.08.31			
			} 
			doExecuteProcedureCall(requisitionID, processID);
			
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
	
	@Override
	public Boolean updateDocumentStatus(Entity entity,  List<String> RecordId, String document_status_to,String column){
		if (document_status_to.equalsIgnoreCase(completeStatus)||
				document_status_to.equalsIgnoreCase(closedStatus))
			return true;
		else
			return super.updateDocumentStatus(entity, RecordId, document_status_to, column);
		
	}

}
