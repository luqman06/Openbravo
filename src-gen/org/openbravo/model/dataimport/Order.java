/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2008-2014 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.dataimport;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.model.project.Project;
import org.openbravo.model.shipping.ShippingCompany;
/**
 * Entity class for entity DataImportOrder (stored in table I_Order).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Order extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_Order";
    public static final String ENTITY_NAME = "DataImportOrder";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_TRXORGANIZATION = "trxOrganization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_SALESREPRESENTATIVE = "salesRepresentative";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_SHIPPINGCOMPANY = "shippingCompany";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_BUSINESSPARTNERSEARCHKEY = "businessPartnerSearchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_INVOICEADDRESS = "invoiceAddress";
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_ADDRESSLINE1 = "addressLine1";
    public static final String PROPERTY_ADDRESSLINE2 = "addressLine2";
    public static final String PROPERTY_POSTALCODE = "postalCode";
    public static final String PROPERTY_CITYNAME = "cityName";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_REGIONNAME = "regionName";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_ISOCOUNTRYCODE = "iSOCountryCode";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_CONTACTNAME = "contactName";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_PHONE = "phone";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTTYPENAME = "documentTypeName";
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_PAYMENTTERMKEY = "paymentTermKey";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_PRODUCTSEARCHKEY = "productSearchKey";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_SKU = "sKU";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_TAXSEARCHKEY = "taxSearchKey";
    public static final String PROPERTY_TAXAMOUNT = "taxAmount";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_LINEDESCRIPTION = "lineDescription";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_ORDEREDQUANTITY = "orderedQuantity";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_UNITPRICE = "unitPrice";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_BILLTOUPC = "billToUPC";
    public static final String PROPERTY_BUSINESSPARTNERUPC = "businessPartnerUPC";
    public static final String PROPERTY_UPCADDRESS = "uPCAddress";
    public static final String PROPERTY_ORDERDATE = "orderDate";
    public static final String PROPERTY_SCHEDULEDDELIVERYDATE = "scheduledDeliveryDate";
    public static final String PROPERTY_ORDERREFERENCENO = "orderReferenceNo";
    public static final String PROPERTY_PAYMENTAMOUNT1 = "paymentamount1";
    public static final String PROPERTY_PAYMENTAMOUNT2 = "paymentamount2";
    public static final String PROPERTY_PAYMENTRULE1 = "paymentrule1";
    public static final String PROPERTY_PAYMENTRULE2 = "paymentrule2";
    public static final String PROPERTY_PERFORMPOST = "performPost";
    public static final String PROPERTY_VENDORUPC = "vendorUPC";
    public static final String PROPERTY_OEZCURRENCY = "oezCurrency";
    public static final String PROPERTY_OEZPRICELIST = "oezPricelist";
    public static final String PROPERTY_OEZWAREHOUSE = "oezWarehouse";
    public static final String PROPERTY_OEZSALESREP = "oezSalesrep";
    public static final String PROPERTY_OEZUOM = "oezUom";
    public static final String PROPERTY_OEZIMPORT = "oezImport";
    public static final String PROPERTY_ATTRIBUTESETIDENT = "attributesetident";
    public static final String PROPERTY_SYNCHRONIZED = "synchronized";

    public Order() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_SALESTRANSACTION, false);
        setDefaultValue(PROPERTY_PAYMENTAMOUNT1, new BigDecimal(0));
        setDefaultValue(PROPERTY_PAYMENTAMOUNT2, new BigDecimal(0));
        setDefaultValue(PROPERTY_PERFORMPOST, false);
        setDefaultValue(PROPERTY_OEZIMPORT, false);
        setDefaultValue(PROPERTY_SYNCHRONIZED, false);
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Organization getTrxOrganization() {
        return (Organization) get(PROPERTY_TRXORGANIZATION);
    }

    public void setTrxOrganization(Organization trxOrganization) {
        set(PROPERTY_TRXORGANIZATION, trxOrganization);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public Boolean isImportProcessComplete() {
        return (Boolean) get(PROPERTY_IMPORTPROCESSCOMPLETE);
    }

    public void setImportProcessComplete(Boolean importProcessComplete) {
        set(PROPERTY_IMPORTPROCESSCOMPLETE, importProcessComplete);
    }

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public User getSalesRepresentative() {
        return (User) get(PROPERTY_SALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(User salesRepresentative) {
        set(PROPERTY_SALESREPRESENTATIVE, salesRepresentative);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public ShippingCompany getShippingCompany() {
        return (ShippingCompany) get(PROPERTY_SHIPPINGCOMPANY);
    }

    public void setShippingCompany(ShippingCompany shippingCompany) {
        set(PROPERTY_SHIPPINGCOMPANY, shippingCompany);
    }

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getBusinessPartnerSearchKey() {
        return (String) get(PROPERTY_BUSINESSPARTNERSEARCHKEY);
    }

    public void setBusinessPartnerSearchKey(String businessPartnerSearchKey) {
        set(PROPERTY_BUSINESSPARTNERSEARCHKEY, businessPartnerSearchKey);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public Location getInvoiceAddress() {
        return (Location) get(PROPERTY_INVOICEADDRESS);
    }

    public void setInvoiceAddress(Location invoiceAddress) {
        set(PROPERTY_INVOICEADDRESS, invoiceAddress);
    }

    public org.openbravo.model.common.geography.Location getLocationAddress() {
        return (org.openbravo.model.common.geography.Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(org.openbravo.model.common.geography.Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public String getAddressLine1() {
        return (String) get(PROPERTY_ADDRESSLINE1);
    }

    public void setAddressLine1(String addressLine1) {
        set(PROPERTY_ADDRESSLINE1, addressLine1);
    }

    public String getAddressLine2() {
        return (String) get(PROPERTY_ADDRESSLINE2);
    }

    public void setAddressLine2(String addressLine2) {
        set(PROPERTY_ADDRESSLINE2, addressLine2);
    }

    public String getPostalCode() {
        return (String) get(PROPERTY_POSTALCODE);
    }

    public void setPostalCode(String postalCode) {
        set(PROPERTY_POSTALCODE, postalCode);
    }

    public String getCityName() {
        return (String) get(PROPERTY_CITYNAME);
    }

    public void setCityName(String cityName) {
        set(PROPERTY_CITYNAME, cityName);
    }

    public Region getRegion() {
        return (Region) get(PROPERTY_REGION);
    }

    public void setRegion(Region region) {
        set(PROPERTY_REGION, region);
    }

    public String getRegionName() {
        return (String) get(PROPERTY_REGIONNAME);
    }

    public void setRegionName(String regionName) {
        set(PROPERTY_REGIONNAME, regionName);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public String getISOCountryCode() {
        return (String) get(PROPERTY_ISOCOUNTRYCODE);
    }

    public void setISOCountryCode(String iSOCountryCode) {
        set(PROPERTY_ISOCOUNTRYCODE, iSOCountryCode);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public String getContactName() {
        return (String) get(PROPERTY_CONTACTNAME);
    }

    public void setContactName(String contactName) {
        set(PROPERTY_CONTACTNAME, contactName);
    }

    public String getEmail() {
        return (String) get(PROPERTY_EMAIL);
    }

    public void setEmail(String email) {
        set(PROPERTY_EMAIL, email);
    }

    public String getPhone() {
        return (String) get(PROPERTY_PHONE);
    }

    public void setPhone(String phone) {
        set(PROPERTY_PHONE, phone);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public ABCActivity getActivity() {
        return (ABCActivity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(ABCActivity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentTypeName() {
        return (String) get(PROPERTY_DOCUMENTTYPENAME);
    }

    public void setDocumentTypeName(String documentTypeName) {
        set(PROPERTY_DOCUMENTTYPENAME, documentTypeName);
    }

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public String getPaymentTermKey() {
        return (String) get(PROPERTY_PAYMENTTERMKEY);
    }

    public void setPaymentTermKey(String paymentTermKey) {
        set(PROPERTY_PAYMENTTERMKEY, paymentTermKey);
    }

    public org.openbravo.model.common.order.Order getSalesOrder() {
        return (org.openbravo.model.common.order.Order) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(org.openbravo.model.common.order.Order salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getProductSearchKey() {
        return (String) get(PROPERTY_PRODUCTSEARCHKEY);
    }

    public void setProductSearchKey(String productSearchKey) {
        set(PROPERTY_PRODUCTSEARCHKEY, productSearchKey);
    }

    public String getUPCEAN() {
        return (String) get(PROPERTY_UPCEAN);
    }

    public void setUPCEAN(String uPCEAN) {
        set(PROPERTY_UPCEAN, uPCEAN);
    }

    public String getSKU() {
        return (String) get(PROPERTY_SKU);
    }

    public void setSKU(String sKU) {
        set(PROPERTY_SKU, sKU);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public String getTaxSearchKey() {
        return (String) get(PROPERTY_TAXSEARCHKEY);
    }

    public void setTaxSearchKey(String taxSearchKey) {
        set(PROPERTY_TAXSEARCHKEY, taxSearchKey);
    }

    public BigDecimal getTaxAmount() {
        return (BigDecimal) get(PROPERTY_TAXAMOUNT);
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        set(PROPERTY_TAXAMOUNT, taxAmount);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public String getLineDescription() {
        return (String) get(PROPERTY_LINEDESCRIPTION);
    }

    public void setLineDescription(String lineDescription) {
        set(PROPERTY_LINEDESCRIPTION, lineDescription);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getOrderedQuantity() {
        return (BigDecimal) get(PROPERTY_ORDEREDQUANTITY);
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        set(PROPERTY_ORDEREDQUANTITY, orderedQuantity);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public BigDecimal getUnitPrice() {
        return (BigDecimal) get(PROPERTY_UNITPRICE);
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        set(PROPERTY_UNITPRICE, unitPrice);
    }

    public Campaign getSalesCampaign() {
        return (Campaign) get(PROPERTY_SALESCAMPAIGN);
    }

    public void setSalesCampaign(Campaign salesCampaign) {
        set(PROPERTY_SALESCAMPAIGN, salesCampaign);
    }

    public String getBillToUPC() {
        return (String) get(PROPERTY_BILLTOUPC);
    }

    public void setBillToUPC(String billToUPC) {
        set(PROPERTY_BILLTOUPC, billToUPC);
    }

    public String getBusinessPartnerUPC() {
        return (String) get(PROPERTY_BUSINESSPARTNERUPC);
    }

    public void setBusinessPartnerUPC(String businessPartnerUPC) {
        set(PROPERTY_BUSINESSPARTNERUPC, businessPartnerUPC);
    }

    public String getUPCAddress() {
        return (String) get(PROPERTY_UPCADDRESS);
    }

    public void setUPCAddress(String uPCAddress) {
        set(PROPERTY_UPCADDRESS, uPCAddress);
    }

    public Date getOrderDate() {
        return (Date) get(PROPERTY_ORDERDATE);
    }

    public void setOrderDate(Date orderDate) {
        set(PROPERTY_ORDERDATE, orderDate);
    }

    public Date getScheduledDeliveryDate() {
        return (Date) get(PROPERTY_SCHEDULEDDELIVERYDATE);
    }

    public void setScheduledDeliveryDate(Date scheduledDeliveryDate) {
        set(PROPERTY_SCHEDULEDDELIVERYDATE, scheduledDeliveryDate);
    }

    public String getOrderReferenceNo() {
        return (String) get(PROPERTY_ORDERREFERENCENO);
    }

    public void setOrderReferenceNo(String orderReferenceNo) {
        set(PROPERTY_ORDERREFERENCENO, orderReferenceNo);
    }

    public BigDecimal getPaymentamount1() {
        return (BigDecimal) get(PROPERTY_PAYMENTAMOUNT1);
    }

    public void setPaymentamount1(BigDecimal paymentamount1) {
        set(PROPERTY_PAYMENTAMOUNT1, paymentamount1);
    }

    public BigDecimal getPaymentamount2() {
        return (BigDecimal) get(PROPERTY_PAYMENTAMOUNT2);
    }

    public void setPaymentamount2(BigDecimal paymentamount2) {
        set(PROPERTY_PAYMENTAMOUNT2, paymentamount2);
    }

    public String getPaymentrule1() {
        return (String) get(PROPERTY_PAYMENTRULE1);
    }

    public void setPaymentrule1(String paymentrule1) {
        set(PROPERTY_PAYMENTRULE1, paymentrule1);
    }

    public String getPaymentrule2() {
        return (String) get(PROPERTY_PAYMENTRULE2);
    }

    public void setPaymentrule2(String paymentrule2) {
        set(PROPERTY_PAYMENTRULE2, paymentrule2);
    }

    public Boolean isPerformPost() {
        return (Boolean) get(PROPERTY_PERFORMPOST);
    }

    public void setPerformPost(Boolean performPost) {
        set(PROPERTY_PERFORMPOST, performPost);
    }

    public String getVendorUPC() {
        return (String) get(PROPERTY_VENDORUPC);
    }

    public void setVendorUPC(String vendorUPC) {
        set(PROPERTY_VENDORUPC, vendorUPC);
    }

    public String getOezCurrency() {
        return (String) get(PROPERTY_OEZCURRENCY);
    }

    public void setOezCurrency(String oezCurrency) {
        set(PROPERTY_OEZCURRENCY, oezCurrency);
    }

    public String getOezPricelist() {
        return (String) get(PROPERTY_OEZPRICELIST);
    }

    public void setOezPricelist(String oezPricelist) {
        set(PROPERTY_OEZPRICELIST, oezPricelist);
    }

    public String getOezWarehouse() {
        return (String) get(PROPERTY_OEZWAREHOUSE);
    }

    public void setOezWarehouse(String oezWarehouse) {
        set(PROPERTY_OEZWAREHOUSE, oezWarehouse);
    }

    public String getOezSalesrep() {
        return (String) get(PROPERTY_OEZSALESREP);
    }

    public void setOezSalesrep(String oezSalesrep) {
        set(PROPERTY_OEZSALESREP, oezSalesrep);
    }

    public String getOezUom() {
        return (String) get(PROPERTY_OEZUOM);
    }

    public void setOezUom(String oezUom) {
        set(PROPERTY_OEZUOM, oezUom);
    }

    public Boolean isOezImport() {
        return (Boolean) get(PROPERTY_OEZIMPORT);
    }

    public void setOezImport(Boolean oezImport) {
        set(PROPERTY_OEZIMPORT, oezImport);
    }

    public String getAttributesetident() {
        return (String) get(PROPERTY_ATTRIBUTESETIDENT);
    }

    public void setAttributesetident(String attributesetident) {
        set(PROPERTY_ATTRIBUTESETIDENT, attributesetident);
    }

    public Boolean isSynchronized() {
        return (Boolean) get(PROPERTY_SYNCHRONIZED);
    }

    public void setSynchronized(Boolean synchrnized) {
        set(PROPERTY_SYNCHRONIZED, synchrnized);
    }

}
