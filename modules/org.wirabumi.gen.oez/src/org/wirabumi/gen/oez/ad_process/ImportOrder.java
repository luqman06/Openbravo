package org.wirabumi.gen.oez.ad_process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.Restrictions;
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
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.dataimport.Order;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.model.pricing.pricelist.ProductPrice;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class ImportOrder extends DalBaseProcess {
  private OBError msg = new OBError();

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    int counter = 0;

    try {
      Organization org = OBDal.getInstance().get(Organization.class,
          bundle.getParams().get("adOrgId"));

      // select header by document no
      List<String> headList = new ArrayList<String>();
      String str = "select coalesce(o.documentNo,'') from DataImportOrder o "
          + "where o.importProcessComplete=false group by o.documentNo";
      Query q = OBDal.getInstance().getSession().createQuery(str);
      ScrollableResults rs = q.scroll(ScrollMode.FORWARD_ONLY);
      while (rs.next()) {
        if (!rs.getString(0).equals(""))
          headList.add(rs.getString(0));
      }
      rs.close();

      List<org.openbravo.model.common.order.Order> orderList = new ArrayList<org.openbravo.model.common.order.Order>();
      List<OrderLine> lineList = new ArrayList<OrderLine>();
      for (String id : headList) {
        OBCriteria<Order> ordList = OBDal.getInstance().createCriteria(Order.class);
        ordList.add(Restrictions.eq(Order.PROPERTY_DOCUMENTNO, id));
        org.openbravo.model.common.order.Order head = null;
        long lineNo = 0;
        for (Order ord : ordList.list()) {
          // cek if header exist
          head = cekHeader(orderList, ord);
          if (head == null) {
            head = setHeader(ord, org);
            orderList.add(head);
          }

          lineNo += 10;
          OrderLine line = setLines(head, ord, lineNo);
          lineList.add(line);

          ord.setImportProcessComplete(true);
          counter++;
        }
      }
      for (org.openbravo.model.common.order.Order ord : orderList) {
        OBDal.getInstance().save(ord);
        OBDal.getInstance().flush();
      }
      for (OrderLine line : lineList) {
        OBDal.getInstance().save(line);
        OBDal.getInstance().flush();
      }
      OBDal.getInstance().commitAndClose();
      msg.setType("success");
      msg.setTitle("Import Order");
      msg.setMessage(counter + " data has been imported successfull.");
      bundle.setResult(msg);
    } catch (Exception e) {
      e.printStackTrace();
      msg.setType("error");
      msg.setTitle("Import Order");
      msg.setMessage(e.getMessage());
      bundle.setResult(msg);
      OBDal.getInstance().rollbackAndClose();
    }
  }

  private org.openbravo.model.common.order.Order setHeader(Order ord, Organization org) {
    org.openbravo.model.common.order.Order hsl = null;

    try {
      // get bpartner
      OBCriteria<BusinessPartner> bpList = OBDal.getInstance()
          .createCriteria(BusinessPartner.class);
      bpList.add(Restrictions.eq(BusinessPartner.PROPERTY_SEARCHKEY,
          ord.getBusinessPartnerSearchKey()));
      BusinessPartner bp = null;
      if (bpList.list().size() > 0) {
        bp = bpList.list().get(0);
      }
      PriceList pList = bp.getPurchasePricelist();
      FIN_PaymentMethod payMethod = bp.getPOPaymentMethod();
      PaymentTerm payTerm = bp.getPOPaymentTerms();
      Currency cur = pList.getCurrency();

      // get partner location
      List<Object> param = new ArrayList<Object>();
      param.add(bp);
      param.add(ord.getISOCountryCode());
      param.add(ord.getRegionName());
      String str = "as bpl inner join bpl.locationAddress loc inner join loc.region r inner join r.country c ";
      str += "where bpl.businessPartner=? ";
      param.add(bp);
      if (ord.getISOCountryCode() != null) {
        str += "and c.iSOCountryCode=? ";
        param.add(ord.getISOCountryCode());
      }
      if (ord.getRegionName() != null) {
        str += "and r.name=?";
        param.add(ord.getRegionName());
      }
      OBQuery<Location> bpLoc = OBDal.getInstance().createQuery(Location.class, str, param);
      Location loc = null;
      if (bpLoc.list().size() > 0) {
        loc = bpLoc.list().get(0);
      }

      // get document type
      OBCriteria<DocumentType> docTypeList = OBDal.getInstance().createCriteria(DocumentType.class);
      docTypeList.add(Restrictions.eq(DocumentType.PROPERTY_NAME, ord.getDocumentTypeName()));
      DocumentType docTypeTrx = null;
      if (docTypeList.list().size() > 0) {
        docTypeTrx = docTypeList.list().get(0);
      }

      // get warehouse
      OBCriteria<Warehouse> whList = OBDal.getInstance().createCriteria(Warehouse.class);
      whList.add(Restrictions.eq(Warehouse.PROPERTY_SEARCHKEY, ord.getOezWarehouse()));
      Warehouse wh = null;
      if (whList.list().size() > 0) {
        wh = whList.list().get(0);
      }

      Date ordDate = ord.getOrderDate();
      Date schDate = ord.getScheduledDeliveryDate();

      // create header
      org.openbravo.model.common.order.Order header = OBProvider.getInstance().get(
          org.openbravo.model.common.order.Order.class);
      header.setOrganization(org);
      header.setBusinessPartner(bp);
      DocumentType docType = OBDal.getInstance().get(DocumentType.class, "0");
      header.setDocumentType(docType);
      header.setDocumentNo(ord.getDocumentNo());
      header.setGrandTotalAmount(new BigDecimal(0));
      header.setPriceList(pList);
      header.setPaymentMethod(payMethod);
      header.setPaymentTerms(payTerm);
      header.setOrderDate(ordDate);
      header.setScheduledDeliveryDate(schDate);
      header.setTransactionDocument(docTypeTrx);
      header.setPartnerAddress(loc);
      header.setInvoiceAddress(loc);
      header.setWarehouse(wh);
      header.setDocumentStatus("DR");
      header.setCurrency(cur);
      header.setSummedLineAmount(new BigDecimal(0));
      header.setAccountingDate(ordDate);
      header.setDocumentAction("CO");
      header.setSalesTransaction(ord.isSalesTransaction());
      if (ord.getDescription() != null) {
        header.setDescription(ord.getDescription());
      }
      if (ord.getOezSalesrep() != null) {
        OBCriteria<User> salesRep = OBDal.getInstance().createCriteria(User.class);
        salesRep.add(Restrictions.eq(User.PROPERTY_NAME, ord.getOezSalesrep()));
        User sales = null;
        if (salesRep.list().size() > 0) {
          sales = salesRep.list().get(0);
        }
        header.setSalesRepresentative(sales);
      }
      OBDal.getInstance().save(header);
      hsl = header;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return hsl;
  }

  private OrderLine setLines(org.openbravo.model.common.order.Order header, Order ord, long lineNo) {
    OrderLine line = OBProvider.getInstance().get(OrderLine.class);

    // get Product
    OBCriteria<Product> productList = OBDal.getInstance().createCriteria(Product.class);
    productList.add(Restrictions.eq(Product.PROPERTY_SEARCHKEY, ord.getProductSearchKey()));
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
    ProductPrice price = null;
    if (priceList.list().size() > 0) {
      price = priceList.list().get(0);
    }

    line.setOrganization(header.getOrganization());
    line.setLineNo(lineNo);
    line.setProduct(produk);
    line.setUnitPrice(price.getListPrice());
    double netAmt = ord.getOrderedQuantity().doubleValue() * price.getListPrice().doubleValue();
    line.setLineNetAmount(new BigDecimal(netAmt));
    line.setOrderedQuantity(ord.getOrderedQuantity());
    line.setTax(tax);
    line.setTaxableAmount(new BigDecimal(0));
    line.setSalesOrder(header);
    line.setUOM(produk.getUOM());
    line.setOrderDate(ord.getOrderDate());
    line.setScheduledDeliveryDate(ord.getScheduledDeliveryDate());
    line.setWarehouse(header.getWarehouse());
    line.setCurrency(header.getCurrency());
    OBDal.getInstance().save(line);
    // set header amount
    double amt = header.getGrandTotalAmount().doubleValue();
    amt += netAmt;
    header.setSummedLineAmount(new BigDecimal(amt));
    OBDal.getInstance().save(header);
    return line;
  }

  private org.openbravo.model.common.order.Order cekHeader(
      List<org.openbravo.model.common.order.Order> data, Order ord) {
    org.openbravo.model.common.order.Order hsl = null;
    for (org.openbravo.model.common.order.Order head : data) {
      if (head.getDocumentNo().equals(ord.getDocumentNo())) {
        hsl = head;
        break;
      }
    }
    return hsl;
  }
}
