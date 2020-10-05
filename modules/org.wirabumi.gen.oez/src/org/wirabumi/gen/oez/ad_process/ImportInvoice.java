package org.wirabumi.gen.oez.ad_process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.dataimport.Invoice;
import org.openbravo.model.dataimport.Order;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.model.pricing.pricelist.ProductPrice;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class ImportInvoice extends DalBaseProcess {
  private OBError msg = new OBError();

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    int counter = 0;

    try {
      Organization org = OBDal.getInstance().get(Organization.class,
          bundle.getParams().get("adOrgId"));

      // select header by document no
      List<String> headList = new ArrayList<String>();
      String str = "select coalesce(i.documentNo,'') from DataImportInvoice i "
          + "where i.importProcessComplete=false group by i.documentNo";
      Query q = OBDal.getInstance().getSession().createQuery(str);
      ScrollableResults rs = q.scroll(ScrollMode.FORWARD_ONLY);
      while (rs.next()) {
        if (!rs.getString(0).equals(""))
          headList.add(rs.getString(0));
      }
      rs.close();

      List<org.openbravo.model.common.invoice.Invoice> invoiceList = new ArrayList<org.openbravo.model.common.invoice.Invoice>();
      List<InvoiceLine> lineList = new ArrayList<InvoiceLine>();
      for (String id : headList) {
        OBCriteria<Invoice> invList = OBDal.getInstance().createCriteria(Invoice.class);
        invList.add(Restrictions.eq(Order.PROPERTY_DOCUMENTNO, id));
        org.openbravo.model.common.invoice.Invoice head = null;
        long lineNo = 0;
        for (Invoice inv : invList.list()) {
          // cek if header exist
          head = cekHeader(invoiceList, inv);
          if (head == null) {
            head = setHeader(inv, org);
            invoiceList.add(head);
          }

          lineNo += 10;
          InvoiceLine line = setLines(head, inv, lineNo);
          lineList.add(line);

          inv.setImportProcessComplete(true);
          inv.setInvoiceLine(line);
          inv.setInvoice(head);
          counter++;
        }
      }
      for (org.openbravo.model.common.invoice.Invoice inv : invoiceList) {
        OBDal.getInstance().save(inv);
        OBDal.getInstance().flush();
      }
      for (InvoiceLine line : lineList) {
        OBDal.getInstance().save(line);
        OBDal.getInstance().flush();
      }

      OBDal.getInstance().commitAndClose();
      msg.setType("success");
      msg.setTitle("Import Invoice");
      msg.setMessage(counter + " data has been imported successfull.");
      bundle.setResult(msg);
    } catch (Exception e) {
      e.printStackTrace();
      msg.setType("error");
      msg.setTitle("Import Invoice");
      msg.setMessage(e.getMessage());
      bundle.setResult(msg);
      OBDal.getInstance().rollbackAndClose();
    }
  }

  private org.openbravo.model.common.invoice.Invoice setHeader(Invoice inv, Organization org) {
	  org.openbravo.model.common.invoice.Invoice header = OBProvider.getInstance().get(
	          org.openbravo.model.common.invoice.Invoice.class);

    try {
      //get business partner
      OBCriteria<BusinessPartner> bpList = OBDal.getInstance()
          .createCriteria(BusinessPartner.class);
      bpList.add(Restrictions.eq(BusinessPartner.PROPERTY_SEARCHKEY,
          inv.getBusinessPartnerSearchKey()));
      BusinessPartner bp = null;
      if (bpList.list().size() > 0) {
        bp = bpList.list().get(0);
      }
      
    //get currency
      String currencycode = inv.getOezCurrencyiso();
      OBQuery<Currency> currencyQuery = OBDal.getInstance().createQuery(Currency.class, "iSOCode=?");
      List<Object> params = new ArrayList<Object>();
      params.add(currencycode);
      currencyQuery.setParameters(params);
      List<Currency> currencyList = currencyQuery.list();
      Currency cur = null;
      if (currencyList.size()>0)
    	  cur = currencyList.get(0);
      else
    	  throw new OBException("@oez_cannotfindcurrency@ : "+currencycode);
      
      //get price list
      PriceList priceList = null;
      if (inv.isSalesTransaction())
    	  priceList = bp.getPriceList();
      else
    	  priceList = bp.getPurchasePricelist();
      
      if (priceList==null){
    	  //price list not defined in business partner definition, select 1 random purchase price list
    	  OBCriteria<PriceList> purchasePriceList = OBDal.getInstance().createCriteria(PriceList.class);
    	  purchasePriceList.add(Restrictions.eq(PriceList.PROPERTY_SALESPRICELIST, inv.isSalesTransaction()));
    	  purchasePriceList.add(Restrictions.eq(PriceList.PROPERTY_CURRENCY, cur));
    	  purchasePriceList.addOrderBy(PriceList.PROPERTY_DEFAULT, false);
    	  purchasePriceList.setFetchSize(1);
    	  List<PriceList> priceListList = purchasePriceList.list();
    	  if (priceListList.size()==0)
    		  throw new OBException("@oez_cannotfindpricelist@");
    	  priceList=priceListList.get(0);
      }
      
      //get payment method
      FIN_PaymentMethod payMethod = bp.getPOPaymentMethod();
      
      //get payment term
      PaymentTerm payTerm = bp.getPOPaymentTerms();
      if (payTerm==null){
    	  //payment term not defined in business partner definition yet, then select 1 random payment term
    	  OBCriteria<PaymentTerm> paytermCriteria = OBDal.getInstance().createCriteria(PaymentTerm.class);
    	  List<PaymentTerm> paytermList = paytermCriteria.list();
    	  if (paytermList.size()==0)
    		  throw new OBException("@oez_cannotfindpaymentterm@");
    	  payTerm= paytermList.get(0);
      }
      
      // get bill to address
      OBCriteria<Location> billtoAddressCriteria = OBDal.getInstance().createCriteria(Location.class);
      billtoAddressCriteria.add(Restrictions.eq(Location.PROPERTY_BUSINESSPARTNER, bp));
      billtoAddressCriteria.add(Restrictions.eq(Location.PROPERTY_INVOICETOADDRESS, true));
      List<Location> billtoaddressList = billtoAddressCriteria.list();
      if (billtoaddressList.size()==0)
    	  throw new OBException("@oez_cannotfindbillingaddress@");
      Location loc = billtoaddressList.get(0);
      
      // get document type
      OBCriteria<DocumentType> docTypeList = OBDal.getInstance().createCriteria(DocumentType.class);
      docTypeList.add(Restrictions.eq(DocumentType.PROPERTY_NAME, inv.getDocumentTypeName()));
      DocumentType docTypeTrx = null;
      if (docTypeList.list().size() > 0) {
        docTypeTrx = docTypeList.list().get(0);
      }

      Date invDate = inv.getOezDateinvoiced();

      // get user id
      OBCriteria<User> user = OBDal.getInstance().createCriteria(User.class);
      user.add(Restrictions.eq(User.PROPERTY_BUSINESSPARTNER, bp));
      if (user.list().size() > 0) {
        header.setUserContact(user.list().get(0));
      }
      
      //set invoice header
      header.setOrganization(org);
      header.setBusinessPartner(bp);
      DocumentType docType = OBDal.getInstance().get(DocumentType.class, "0");
      header.setDocumentType(docType);
      String documentno = inv.getDocumentNo();
      header.setDocumentNo(documentno);
      header.setGrandTotalAmount(new BigDecimal(0));
      header.setPriceList(priceList);
      header.setPaymentMethod(payMethod);
      header.setPaymentTerms(payTerm);
      header.setInvoiceDate(invDate);
      header.setTransactionDocument(docTypeTrx);
      header.setPartnerAddress(loc);
      header.setDocumentStatus("DR");
      header.setCurrency(cur);
      header.setSummedLineAmount(new BigDecimal(0));
      header.setAccountingDate(invDate);
      header.setDocumentAction("CO");
      header.setSalesTransaction(inv.isSalesTransaction());
      header.setDescription(inv.getDescription());
      
      if (inv.getOezSalesrep() != null) {
        OBCriteria<User> salesRep = OBDal.getInstance().createCriteria(User.class);
        salesRep.add(Restrictions.eq(User.PROPERTY_NAME, inv.getOezSalesrep()));
        User sales = null;
        if (salesRep.list().size() > 0) {
          sales = salesRep.list().get(0);
        }
        header.setSalesRepresentative(sales);
      }
      OBDal.getInstance().save(header);
      header.setDocumentNo(documentno);
      OBDal.getInstance().save(header);
      
    } catch (Exception e) {
      e.printStackTrace();
      throw new OBException(e.getLocalizedMessage());
    }

    return header;
  }

  private InvoiceLine setLines(org.openbravo.model.common.invoice.Invoice header, Invoice inv,
      long lineNo) {
    InvoiceLine line = OBProvider.getInstance().get(InvoiceLine.class);

    // get Product
    OBCriteria<Product> productList = OBDal.getInstance().createCriteria(Product.class);
    productList.add(Restrictions.eq(Product.PROPERTY_SEARCHKEY, inv.getProductSearchKey()));
    Product produk = null;
    if (productList.list().size() > 0) {
      produk = productList.list().get(0);
    }

    // get tax
    OBCriteria<TaxRate> taxList = OBDal.getInstance().createCriteria(TaxRate.class);
    taxList.add(Restrictions.eq(TaxRate.PROPERTY_TAXCATEGORY, produk.getTaxCategory()));
    TaxRate tax = null;
    if (taxList.list().size() > 0) {
      tax = taxList.list().get(0);
    }

    // get product price
    OBCriteria<ProductPrice> priceList = OBDal.getInstance().createCriteria(ProductPrice.class);
    priceList.add(Restrictions.eq(ProductPrice.PROPERTY_PRODUCT, produk));
    
    line.setOrganization(header.getOrganization());
    line.setLineNo(lineNo);
    line.setProduct(produk);
    BigDecimal unitprice = inv.getUnitPrice(); 
    if (unitprice==null)
    	unitprice = BigDecimal.ZERO;
    line.setUnitPrice(unitprice);
    double netAmt = inv.getOrderedQuantity().doubleValue() * unitprice.doubleValue();
    line.setLineNetAmount(new BigDecimal(netAmt));
    line.setInvoicedQuantity(inv.getOrderedQuantity());
    line.setTax(tax);
    line.setTaxableAmount(new BigDecimal(0));
    line.setInvoice(header);
    line.setUOM(produk.getUOM());
    OBDal.getInstance().save(line);
    // set header amount
    double amt = header.getGrandTotalAmount().doubleValue();
    amt += netAmt;
    header.setSummedLineAmount(new BigDecimal(amt));
    OBDal.getInstance().save(header);
    return line;
  }

  private org.openbravo.model.common.invoice.Invoice cekHeader(
      List<org.openbravo.model.common.invoice.Invoice> data, Invoice inv) {
    org.openbravo.model.common.invoice.Invoice hsl = null;
    for (org.openbravo.model.common.invoice.Invoice head : data) {
      if (head.getDocumentNo().equalsIgnoreCase(inv.getDocumentNo())) {
        hsl = head;
        break;
      }
    }
    return hsl;
  }
}
