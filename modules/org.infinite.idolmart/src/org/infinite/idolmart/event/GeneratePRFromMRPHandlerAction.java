package org.infinite.idolmart.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.plm.ApprovedVendor;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.mrp.PurchasingRun;
import org.openbravo.model.mrp.PurchasingRunLine;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;

public class GeneratePRFromMRPHandlerAction extends DocumentRoutingHandlerAction {

  @Override
  public void doRouting(String adWindowId, String adTabId, String doc_status_to,
      VariablesSecureApp vars, List<String> recordId) {
	  for (String recordid : recordId) {
		  HashMap<Currency, PriceList> pricelists = getDefaultPriceList();
		  HashMap<Product, ApprovedVendor> currentVendors = getCurrentVendors();
		  HashMap<BusinessPartner, Requisition> purchaseRequests = new HashMap<>();
		  HashMap<Requisition, List<RequisitionLine>> prLineList = new HashMap<>();
		  PurchasingRun mrpHeader = OBDal.getInstance().get(PurchasingRun.class, recordid);
		  for (PurchasingRunLine mrpLine : mrpHeader.getMRPPurchasingRunLineList()) {
			  if (!currentVendors.containsKey(mrpLine.getProduct()))
				  continue; //product has no current vendor
			  Product product = mrpLine.getProduct();
			  ApprovedVendor approvedVendor = currentVendors.get(product);
			  BusinessPartner vendor = approvedVendor.getBusinessPartner();
			  Requisition prHeader = null;
			  if (!purchaseRequests.containsKey(vendor)) {
				  //create new PR header
				  prHeader = OBProvider.getInstance().get(Requisition.class);
				  prHeader.setBusinessPartner(vendor);
				  prHeader.setUserContact(OBContext.getOBContext().getUser());
				  OBDal.getInstance().save(prHeader);
				  purchaseRequests.put(vendor, prHeader);
				  prLineList.put(prHeader, new ArrayList<RequisitionLine>());
			  } else
				  prHeader=purchaseRequests.get(vendor);

			  //create PR line
			  RequisitionLine prLine = OBProvider.getInstance().get(RequisitionLine.class);
			  long lineno = (prLineList.get(prHeader).size()+1)*10;
			  prLine.setLineNo(lineno);
			  prLine.setRequisition(prHeader);
			  prLine.setProduct(product);
			  prLine.setNeedByDate(mrpHeader.getDocumentDate());
			  prLine.setQuantity(mrpLine.getQuantity());
			  prLine.setListPrice(approvedVendor.getListPrice());
			  if (pricelists.containsKey(approvedVendor.getCurrency())) { 
				  PriceList pricelist = pricelists.get(approvedVendor.getCurrency());
				  prLine.setPriceList(pricelist);
			  }
			  
			  OBDal.getInstance().save(prLine);
			  List<RequisitionLine> prLines = prLineList.get(prHeader);
			  prLines.add(prLine);
			  
			  mrpLine.setRequisitionLine(prLine);
			  OBDal.getInstance().save(mrpLine);
		  }

		  OBDal.getInstance().commitAndClose();
	  }
	  
  }

  @Override
  public Boolean updateDocumentStatus(Entity entity, List<String> RecordId, String document_status_to,
      String column) {
    return true; //do nothing
  }

  @Override
  public String getCoDocumentNo(String record, Tab tab) {
    return null; // do nothing
  }
  
  private HashMap<Currency, PriceList> getDefaultPriceList() {
	  HashMap<Currency, PriceList> output = new HashMap<>();
	  OBCriteria<PriceList> pricelistCriteria = OBDal.getInstance().createCriteria(PriceList.class);
	  pricelistCriteria.add(Restrictions.eq(PriceList.PROPERTY_SALESPRICELIST, false));
	  pricelistCriteria.addOrderBy(PriceList.PROPERTY_DEFAULT, false); //descending
	  for (PriceList pricelist : pricelistCriteria.list()) {
		  Currency currency = pricelist.getCurrency();
		  if (output.containsKey(currency))
			  continue;
		  output.put(currency, pricelist);
	  }
	  
	  return output;
  }

  private HashMap<Product, ApprovedVendor> getCurrentVendors() {
	  HashMap<Product, ApprovedVendor> output = new HashMap<>();
	  OBCriteria<ApprovedVendor> approvedVendorCriteria = OBDal.getInstance().createCriteria(ApprovedVendor.class);
	  approvedVendorCriteria.add(Restrictions.eq(ApprovedVendor.PROPERTY_CURRENTVENDOR, true));
	  for (ApprovedVendor approvedVendor : approvedVendorCriteria.list()) {
		  output.put(approvedVendor.getProduct(), approvedVendor);
	  }
	  return output;
  }

}
