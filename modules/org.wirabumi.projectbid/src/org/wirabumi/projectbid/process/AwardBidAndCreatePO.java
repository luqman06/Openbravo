package org.wirabumi.projectbid.process;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.procurement.RequisitionPOMatch;
import org.openbravo.model.project.ProjectProposal;
import org.openbravo.model.project.ProjectProposalLine;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class AwardBidAndCreatePO extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		/*
		 * award proposal of requisition, then convert it into PO.
		 */
		
		String proposalID = (String) bundle.getParams().get("C_Projectproposal_ID");
		ProjectProposal proposal = OBDal.getInstance().get(ProjectProposal.class, proposalID);
		
		//if awarded, than make sure PO still unprocessed
		if (proposal.isProjectBidWon()){
			Order po = proposal.getPbidOrder();
			if (po.isProcessed())
				throw new OBException("this proposal have been awarded and linked to completed PO No. "+po.getDocumentNo());
			else{
				//awarded but PO still unprocessed, then make it void
				po.setDocumentStatus("VO");
				po.setProcessed(true);
				OBDal.getInstance().save(po);
			}
		}
		
		Requisition requisition = proposal.getPbidRequisition();
		BusinessPartner bp = proposal.getBusinessPartner();
		Warehouse warehouse = requisition.getPbidWarehouse();
		if (warehouse==null)
			throw new OBException("requisition does not have any warehouse.");
		
		//PO Date
		String strDate = (String) bundle.getParams().get("dateordered");
		final SimpleDateFormat sdf = new SimpleDateFormat(bundle.getContext().getJavaDateFormat());
		Date orderDate = sdf.parse(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderDate);
		cal.add(Calendar.MONTH, 1);
		Date deliverydate = cal.getTime();
		
		//create PO header
		Order po = OBProvider.getInstance().get(Order.class);
		po.setSalesTransaction(false);
		DocumentType poDocType = getPODoctype();
		if (poDocType==null)
			throw new OBException("can not find document type for with document category purchase order.");
		DocumentType newDocType = getNewDoctype();
		po.setWarehouse(warehouse);
		Organization org = requisition.getOrganization();
		if (!org.getOrganizationType().isTransactionsAllowed())
			throw new OBException("organization type of "+org.getName()+" not allowed to do transaction.");
		po.setOrganization(org);
		po.setTransactionDocument(poDocType);
		po.setDocumentType(newDocType);
		po.setDocumentStatus("DR");
		po.setDocumentAction("CO");
		po.setBusinessPartner(bp);
		PriceList priceList = requisition.getPriceList();
		if (priceList==null)
			throw new OBException("project bid has no price list.");
		Currency currency = priceList.getCurrency();
		po.setCurrency(currency);
		po.setPriceList(priceList);
		if (bp.getBusinessPartnerLocationList().size()==0)
			throw new OBException("vendor/supplier "+bp.getName()+" has no address.");
		Location bpLocation = bp.getBusinessPartnerLocationList().get(0);
		po.setPartnerAddress(bpLocation);
		FIN_PaymentMethod paymentMethod = proposal.getPaymentMethod();
		if (paymentMethod==null)
			paymentMethod = bp.getPOPaymentMethod(); 
		if (paymentMethod==null)
			throw new OBException("this proposal and vendor/supplier "+bp.getName()+" has no payment method.");
		po.setPaymentMethod(paymentMethod);
		PaymentTerm paymentTerm = proposal.getPaymentTerms();
		if (paymentTerm==null)
			paymentTerm = bp.getPOPaymentTerms();
		if (paymentTerm==null)
			throw new OBException("this proposal and vendor/supplier "+bp.getName()+" has no payment term.");
		po.setPaymentTerms(paymentTerm);
		po.setOrderDate(orderDate);
		po.setAccountingDate(orderDate);
		po.setScheduledDeliveryDate(deliverydate);
		OBDal.getInstance().save(po);
		
		proposal.setPbidOrder(po);
		proposal.setProjectBidWon(true);
		OBDal.getInstance().save(proposal);
		
		//create PO lines
		List<OrderLine> orderLineList = new ArrayList<>();
		for (ProjectProposalLine line : proposal.getProjectProposalLineList()){
			
			if (line.isPbidExcludefromwin())
				continue; //exclude from winning bid, then skip
			
			org.wirabumi.projectbid.RequisitionLine rl = line.getPbidRequisitionline();
			if (rl==null)
				throw new OBException("proposal line not linked to requisition line");
			RequisitionLine requisitionLine = OBDal.getInstance().get(RequisitionLine.class, rl.getId());
			
			OrderLine orderLine = OBProvider.getInstance().get(OrderLine.class);
			orderLine.setSalesOrder(po);
			orderLine.setOrganization(org);
			orderLine.setLineNo(line.getLineNo());
			orderLine.setBusinessPartner(bp);
			orderLine.setPartnerAddress(bpLocation);
			orderLine.setOrderDate(orderDate);
			orderLine.setDateDelivered(orderDate);
			orderLine.setDescription(line.getDescription());
			orderLine.setProduct(line.getProduct());
			orderLine.setWarehouse(po.getWarehouse());
			orderLine.setDirectShipment(false);
			orderLine.setUOM(line.getProduct().getUOM());
			orderLine.setOrderedQuantity(line.getQuantity());
			orderLine.setListPrice(line.getPrice());
			orderLine.setStandardPrice(line.getPrice());
			orderLine.setUnitPrice(line.getPrice());
			orderLine.setPriceLimit(line.getPrice());
			BigDecimal lineNetAmt = line.getPrice().multiply(line.getQuantity());
			orderLine.setLineNetAmount(lineNetAmt);
			List<TaxRate> taxList = line.getProduct().getTaxCategory().getFinancialMgmtTaxRateList();
			if (taxList.size()==0)
				throw new OBException("product "+line.getProduct().getName()+" linked to tax category "+line.getProduct().getTaxCategory().getName()
						+" but it's doen not have any tax rate.");
			TaxRate tax = taxList.get(0);
			orderLine.setTax(tax);
			orderLine.setTaxableAmount(lineNetAmt);
			orderLine.setCurrency(po.getCurrency());
			
			OBDal.getInstance().save(orderLine);
			
			//link proposal line to PO
			line.setPbidOrderline(orderLine);
			OBDal.getInstance().save(line);
			
			//create matched requisition PO here
			RequisitionPOMatch matchedRequisition = OBProvider.getInstance().get(RequisitionPOMatch.class);
			matchedRequisition.setOrganization(po.getOrganization());
			matchedRequisition.setRequisitionLine(requisitionLine);
			matchedRequisition.setSalesOrderLine(orderLine);
			matchedRequisition.setQuantity(line.getQuantity());
			OBDal.getInstance().save(matchedRequisition);
			
			orderLineList.add(orderLine);
			
		}
		
		po.setOrderLineList(orderLineList);
		OBDal.getInstance().save(po);
		
		//make requisition closed, it's mean completed requisition have been converted into PO.
		requisition.setDocumentStatus("CL");
		OBDal.getInstance().save(requisition);
		
		//build return message
		OBError result = new OBError();
		result.setType("Success");
		result.setTitle("Success");
		result.setMessage("Proposal awarded successfully and PO have been created: "+po.getDocumentNo());
		
		bundle.setResult(result);
		
	}

	private DocumentType getNewDoctype() {
		DocumentType output = OBDal.getInstance().get(DocumentType.class, "0");
		return output;
	}

	private DocumentType getPODoctype() {
		OBCriteria<DocumentType> docTypeC = OBDal.getInstance().createCriteria(DocumentType.class);
		docTypeC.add(Restrictions.eq(DocumentType.PROPERTY_DOCUMENTCATEGORY, "POO")); //purchase order
		docTypeC.add(Restrictions.eq(DocumentType.PROPERTY_RETURN, false)); 
		for (DocumentType docType : docTypeC.list()){
			return docType;
		}
		
		return null;
	}

}

