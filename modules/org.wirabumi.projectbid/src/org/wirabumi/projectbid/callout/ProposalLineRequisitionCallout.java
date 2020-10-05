package org.wirabumi.projectbid.callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.procurement.RequisitionLine;

public class ProposalLineRequisitionCallout extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		String lastchanged=info.getLastFieldChanged();
		if (!lastchanged.equals("inpemPbidRequisitionlineId")) //last change ? requested product
			return;
		
		//change on requested product
		//then update, qty, unit price, description, product identifier, product name, product desc
		String requisitionlineid = info.getStringParameter("inpemPbidRequisitionlineId");
		RequisitionLine requisitionLine = OBDal.getInstance().get(RequisitionLine.class, requisitionlineid);
		Product product = requisitionLine.getProduct();
		info.addResult("inpprice", requisitionLine.getUnitPrice());
		info.addResult("inpmProductId", product.getId());
		info.addResult("inpqty", requisitionLine.getQuantity());
		info.addResult("inpdescription", requisitionLine.getDescription());
		info.addResult("inpproductValue", product.getSearchKey());
		info.addResult("inpproductName", product.getName());
		info.addResult("inpproductDescription", product.getDescription());
		
		
	}

}
