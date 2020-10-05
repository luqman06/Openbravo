package org.wirabumi.gen.oez.ad_process;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.dataimport.Invoice;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class ProcessImportInvoice extends DalBaseProcess {
	private boolean salesTransaction=true;

  @Override
  protected void doExecute(ProcessBundle bundle) {
    OBCriteria<Invoice> importData = OBDal.getInstance().createCriteria(Invoice.class);
    importData.add(Restrictions.eq(Invoice.PROPERTY_IMPORTPROCESSCOMPLETE, false));
    validasi(importData);

    OBCriteria<Invoice> importData2 = OBDal.getInstance().createCriteria(Invoice.class);
    importData.add(Restrictions.eq(Invoice.PROPERTY_IMPORTPROCESSCOMPLETE, false));
    insertInvoice(importData2);

  }

  private void insertInvoice(OBCriteria<Invoice> importData2) {

    if (importData2.list().size() == 0)
      return;

    String documentNo = "";
    double totalGross = 0;
    org.openbravo.model.common.invoice.Invoice invoice = null;
    long lineNo = 10;
    for (Invoice dataInvoice2 : importData2.list()) {
      boolean a = dataInvoice2.isImportProcessComplete();
      if (a != true) {
        if (!documentNo.equals(dataInvoice2.getDocumentNo())) {
          invoice = OBProvider.getInstance().get(org.openbravo.model.common.invoice.Invoice.class);
          invoice.setDocumentNo(dataInvoice2.getDocumentNo());
          invoice.setDocumentType(dataInvoice2.getDocumentType());
          invoice.setTransactionDocument(dataInvoice2.getDocumentType());
          invoice.setInvoiceDate(dataInvoice2.getOezDateinvoiced());
          invoice.setBusinessPartner(dataInvoice2.getBusinessPartner());
          invoice.setPartnerAddress(dataInvoice2.getPartnerAddress());
          invoice.setPaymentTerms(dataInvoice2.getPaymentTerms());

          OBCriteria<FIN_PaymentMethod> paymentMethodCriteria = OBDal.getInstance().createCriteria(
              FIN_PaymentMethod.class);
          paymentMethodCriteria.add(Restrictions.eq(FIN_PaymentMethod.PROPERTY_NAME,
              "Wire Transfer"));
          FIN_PaymentMethod paymentMethode = paymentMethodCriteria.list().get(0);

          invoice.setPaymentMethod(paymentMethode);
          invoice.setPriceList(dataInvoice2.getPriceList());
          String startDateString = "01-04-2014";
          DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
          Date date;
          try {
            date = df.parse(startDateString);
            invoice.setAccountingDate(date);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          invoice.setCurrency(dataInvoice2.getCurrency());
          invoice.setDocumentStatus("DR");
          invoice.setSalesTransaction(salesTransaction);
          lineNo = 10;
          totalGross = 0;
          try {
            OBDal.getInstance().save(invoice);
            OBDal.getInstance().commitAndClose();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

        OBCriteria<Product> productCriteria = OBDal.getInstance().createCriteria(Product.class);
        productCriteria.add(Restrictions.eq(Product.PROPERTY_SEARCHKEY,
            dataInvoice2.getProductSearchKey()));

        Product product = productCriteria.list().get(0);
        UOM uom = product.getUOM();
        double qty = dataInvoice2.getOrderedQuantity().doubleValue();
        double price = dataInvoice2.getUnitPrice().doubleValue();
        double taxAmount = dataInvoice2.getTaxAmount().doubleValue();

        double netAmount = qty * price;
        double netTax = qty * taxAmount;
        double grossAmount = netAmount + netTax;
        totalGross += grossAmount;

        InvoiceLine invoiceLine = OBProvider.getInstance().get(InvoiceLine.class);
        invoiceLine.setLineNo(lineNo);
        invoiceLine.setInvoice(invoice);
        invoiceLine.setProduct(product);
        invoiceLine.setInvoicedQuantity(dataInvoice2.getOrderedQuantity());
        invoiceLine.setUOM(uom);
        invoiceLine.setUnitPrice(dataInvoice2.getUnitPrice());
        invoiceLine.setLineNetAmount(new BigDecimal(netAmount));
        invoiceLine.setTax(dataInvoice2.getTax());
        lineNo += 10;
        documentNo = dataInvoice2.getDocumentNo();

        // invoice.setGrandTotalAmount(new BigDecimal(totalGross));
        // invoice.setOutstandingAmount(new BigDecimal(totalGross));

        dataInvoice2.setInvoice(invoice);
        dataInvoice2.setInvoiceLine(invoiceLine);
        dataInvoice2.setImportProcessComplete(true);
        try {
          OBDal.getInstance().save(invoiceLine);
          OBDal.getInstance().save(dataInvoice2);
          OBDal.getInstance().commitAndClose();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void validasi(OBCriteria<Invoice> importData) {
    
    if (importData.list().size() == 0)
      return;

    for (Invoice dataInvoice : importData.list()) {

      String documentNo = dataInvoice.getDocumentNo();
      String documentTypeName = dataInvoice.getDocumentTypeName();
      String currencyIsoCode = dataInvoice.getOezCurrencyiso();
      String paymentTermKey = dataInvoice.getPaymentTermKey();
      String businesPartnerKey = dataInvoice.getBusinessPartnerSearchKey();
      String contactName = dataInvoice.getContactName();
      String productKey = dataInvoice.getProductSearchKey();
      String taxKey = dataInvoice.getTaxSearchKey();
      Date dateInvoice = dataInvoice.getOezDateinvoiced();

      DocumentType documentType = null;
      PaymentTerm paymentTerm = null;
      Currency currency = null;
      PriceList priceList = null;
      BusinessPartner businesPartner = null;
      String businesPartnerName = null;
      Location bpLocation = null;
      org.openbravo.model.common.geography.Location location = null;
      String address1 = null;
      String address2 = null;
      String postal = null;
      String regionName = null;
      Region regional = null;
      Country country = null;
      String isoCountycode = null;
      User user = null;
      String phone = null;
      String email = null;
      Product product = null;
      String upc = null;
      String uom = null;
      TaxRate tax = null;
      FIN_PaymentMethod paymentMethod = null;

      String errorMessage = "";

      if (documentNo == null) {
        errorMessage += "Document No";
      }

      if (dateInvoice == null) {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Date Invoiced";
      }

      OBCriteria<DocumentType> documentTypeCriteria = OBDal.getInstance().createCriteria(
          DocumentType.class);
      documentTypeCriteria.add(Restrictions.eq(DocumentType.PROPERTY_NAME, documentTypeName));

      if (documentTypeCriteria.list().size() != 0) {
        documentType = documentTypeCriteria.list().get(0);
        salesTransaction = documentType.isSalesTransaction();
      } else {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Document Type";
      }

      OBCriteria<PaymentTerm> paymentTermCriteria = OBDal.getInstance().createCriteria(
          PaymentTerm.class);
      paymentTermCriteria.add(Restrictions.eq(PaymentTerm.PROPERTY_SEARCHKEY, paymentTermKey));

      if (paymentTermCriteria.list().size() != 0) {
        paymentTerm = paymentTermCriteria.list().get(0);
      } else {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Payment Terms";
      }

      OBCriteria<FIN_PaymentMethod> paymentMethodCriteria = OBDal.getInstance().createCriteria(
          FIN_PaymentMethod.class);
      paymentMethodCriteria.add(Restrictions.eq(FIN_PaymentMethod.PROPERTY_NAME, "Wire Transfer"));

      if (paymentMethodCriteria.list().size() != 0) {
        paymentMethod = paymentMethodCriteria.list().get(0);
      } else {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Payment Method";
      }

      OBCriteria<Currency> currencyCriteria = OBDal.getInstance().createCriteria(Currency.class);
      currencyCriteria.add(Restrictions.eq(Currency.PROPERTY_ISOCODE, currencyIsoCode));

      if (currencyCriteria.list().size() != 0) {
        currency = currencyCriteria.list().get(0);
        priceList = currency.getPricingPriceListList().get(1);
      } else {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Currency";
      }

      OBCriteria<BusinessPartner> businessPartnerCriteria = OBDal.getInstance().createCriteria(
          BusinessPartner.class);
      businessPartnerCriteria
          .add(Restrictions.eq(BusinessPartner.PROPERTY_NAME, businesPartnerKey));

      if (businessPartnerCriteria.list().size() != 0) {
        businesPartner = businessPartnerCriteria.list().get(0);
        businesPartnerName = businesPartner.getName();
      } else {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Business Partner";
      }

      OBCriteria<Location> bpLocationCriteria = OBDal.getInstance().createCriteria(Location.class);
      bpLocationCriteria.add(Restrictions.eq(Location.PROPERTY_BUSINESSPARTNER, businesPartner));

      if (bpLocationCriteria.list().size() != 0) {
        bpLocation = bpLocationCriteria.list().get(0);
        location = bpLocation.getLocationAddress();
        address1 = location.getAddressLine1();
        address2 = location.getAddressLine2();
        postal = location.getPostalCode();
        regionName = location.getRegionName();
        regional = location.getRegion();
        country = location.getCountry();
        isoCountycode = country.getISOCountryCode();
      } else {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Partner Address";
      }

      OBCriteria<User> userCriteria = OBDal.getInstance().createCriteria(User.class);
      userCriteria.add(Restrictions.eq(User.PROPERTY_NAME, contactName));

      if (userCriteria.list().size() != 0) {
        user = userCriteria.list().get(0);
        phone = user.getPhone();
        email = user.getEmail();
      }

      OBCriteria<Product> productCriteria = OBDal.getInstance().createCriteria(Product.class);
      productCriteria.add(Restrictions.eq(Product.PROPERTY_SEARCHKEY, productKey));

      if (productCriteria.list().size() != 0) {
        product = productCriteria.list().get(0);
        upc = product.getUPCEAN();
        uom = product.getUOM().getName();
      } else {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Product";
      }

      OBCriteria<TaxRate> taxCriteria = OBDal.getInstance().createCriteria(TaxRate.class);
      taxCriteria.add(Restrictions.eq(TaxRate.PROPERTY_NAME, taxKey));
      if (taxCriteria.list().size() != 0) {
        tax = taxCriteria.list().get(0);
      } else {
        if (!errorMessage.equals("")) {
          errorMessage += ", ";
        }
        errorMessage += "Tax";
      }

      if (!errorMessage.equals("")) {
        errorMessage += " not valid ";
      }

      dataInvoice.setDocumentType(documentType);
      dataInvoice.setCurrency(currency);
      dataInvoice.setPriceList(priceList);
      dataInvoice.setPaymentTerms(paymentTerm);
      dataInvoice.setBusinessPartner(businesPartner);
      dataInvoice.setName(businesPartnerName);
      dataInvoice.setPartnerAddress(bpLocation);
      dataInvoice.setLocationAddress(location);
      dataInvoice.setRegion(regional);
      dataInvoice.setCountry(country);
      dataInvoice.setUserContact(user);
      dataInvoice.setProduct(product);
      dataInvoice.setUPCEAN(upc);
      dataInvoice.setTax(tax);
      // dataInvoice.(paymentMethod);
      dataInvoice.setImportErrorMessage(errorMessage);
      try {
        OBDal.getInstance().save(dataInvoice);
        OBDal.getInstance().commitAndClose();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
