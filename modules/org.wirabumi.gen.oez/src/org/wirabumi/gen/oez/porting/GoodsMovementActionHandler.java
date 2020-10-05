package org.wirabumi.gen.oez.porting;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.ad.utility.Tree;
import org.openbravo.model.ad.utility.TreeNode;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.materialmgmt.onhandquantity.ProductStockView;
import org.openbravo.model.materialmgmt.onhandquantity.StorageDetail;
import org.openbravo.model.materialmgmt.transaction.InternalMovement;
import org.openbravo.model.materialmgmt.transaction.InternalMovementLine;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;

public class GoodsMovementActionHandler extends DocumentRoutingHandlerAction {
	private final String reactiveStatus="RE";
	private final String inoutProcessID="122";
	private final String completeStatus="CO";
	private final String closedStatus="CL";
	private final String voidStatus="VO";
	private final String draftStatus="DR";

	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List<String> recordId) {
		if (doc_status_to.equalsIgnoreCase(reactiveStatus))
			throw new OBException("@ActionNotAllowedHere@"); //shipment inout tidak boleh di reactive

		for (String goodsMovementID : recordId){

			InternalMovement goodMovId = OBDal.getInstance().get(InternalMovement.class, goodsMovementID);
			String docstatus = goodMovId.getOezDocstatus();
			String docaction = goodMovId.getOezDocaction();
			if(doc_status_to.equalsIgnoreCase(completeStatus)) {
				for (InternalMovementLine goodMovLineId : goodMovId.getMaterialMgmtInternalMovementLineList())
				{
					Locator bin = goodMovLineId.getStorageBin();
					Product productId = goodMovLineId.getProduct(); // getProduct nya 
					BigDecimal quantityOnHand = BigDecimal.valueOf(0);

					for (StorageDetail sd : bin.getMaterialMgmtStorageDetailList()) {
						if (sd.getStorageBin().equals(bin) && sd.getProduct().equals(productId) && sd.getQuantityOnHand() != BigDecimal.ZERO) {
							quantityOnHand=sd.getQuantityOnHand();
						}
					}

					String productName = goodMovLineId.getProduct().getName();
					int qntyMovementLines = goodMovLineId.getMovementQuantity().intValue();
					String uom = goodMovLineId.getUOM().getName();
					String originalWarehouse = goodMovLineId.getStorageBin().getSearchKey();
					
					if(goodMovLineId.getMovementQuantity().compareTo(quantityOnHand) > 0 ) // Error if Movement Quantity greater than Quantity on Hand
					{				
						String string =  String.format("Insufficient stock, Quantity on Hand of Storage Bin %s for Product %s is just %.0f", originalWarehouse, productName, quantityOnHand);
						throw new OBException(string);
					}
					if(goodMovLineId.getMovementQuantity() == BigDecimal.valueOf(0)) // Error if Movement Quantity equals to 0
					{
						String string =  String.format("Product %s Movement Quantity should not be 0", productName);
						throw new OBException(string);
					}	
					if(goodMovLineId.getMovementQuantity().compareTo(BigDecimal.ZERO) < 0 ) // Error if Movement Quantity is less than 0
					{
						String string =  String.format("Product %s Movement Quantity should not be negative", productName);
						throw new OBException(string);
					}
				}
			}
			
			// cek apakah dari CL atau VO. jika ya maka exception 
			if(goodMovId.getOezDocstatus().equalsIgnoreCase(closedStatus) || 
					goodMovId.getOezDocstatus().equalsIgnoreCase(voidStatus)) 
				throw new OBException("@ActionNotAllowedHere@");

			// ubah oezdocstatus menjadi DR, baru di complete
			goodMovId.setOezDocstatus(draftStatus);
			goodMovId.setOezDocaction(doc_status_to);
			OBDal.getInstance().save(goodMovId);
			try {
				OBDal.getInstance().getConnection().commit();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new OBException(e.getMessage());
			}

			try {
				OBError oberror = doExecuteProcedureCall(goodsMovementID, inoutProcessID);
				if (oberror.getType().equals("Error"))
					throw new OBException(oberror.getMessage());					
			} catch (Exception e) {
				// terjadi exception kembalikan ke doc_status
				goodMovId.setOezDocstatus(docstatus);
				goodMovId.setOezDocaction(docaction);
				OBDal.getInstance().save(goodMovId);
				try {
					OBDal.getInstance().getConnection().commit();
				} catch (SQLException e2) {
					e.printStackTrace();
					throw new OBException(e.getMessage());
				}
				
				//throw chain exception
				e.printStackTrace();
				throw new OBException(e.getMessage());
			}
		}

	}

	@Override
	public String getCoDocumentNo(String recordID, Tab tab) {
		// TODO semengtara null dulu
		return null;
	}

}