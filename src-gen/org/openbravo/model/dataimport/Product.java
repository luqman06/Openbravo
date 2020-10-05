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
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.ProductCategory;
import org.openbravo.model.common.uom.UOM;
/**
 * Entity class for entity DataImportProduct (stored in table I_Product).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Product extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_Product";
    public static final String ENTITY_NAME = "DataImportProduct";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_SKU = "sKU";
    public static final String PROPERTY_EDICODE = "eDICode";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_PRODUCTCATEGORYKEY = "productCategoryKey";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_PRODUCTTYPE = "productType";
    public static final String PROPERTY_CLASSIFICATION = "classification";
    public static final String PROPERTY_VOLUME = "volume";
    public static final String PROPERTY_WEIGHT = "weight";
    public static final String PROPERTY_SHELFWIDTH = "shelfWidth";
    public static final String PROPERTY_SHELFHEIGHT = "shelfHeight";
    public static final String PROPERTY_SHELFDEPTH = "shelfDepth";
    public static final String PROPERTY_UNITSPERPALLET = "unitsPerPallet";
    public static final String PROPERTY_DISCONTINUED = "discontinued";
    public static final String PROPERTY_DISCONTINUEDBY = "discontinuedBy";
    public static final String PROPERTY_IMAGEURL = "imageURL";
    public static final String PROPERTY_DESCRIPTIONURL = "descriptionURL";
    public static final String PROPERTY_BUSINESSPARTNERSEARCHKEY = "businessPartnerSearchKey";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ISOCODE = "iSOCode";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_LISTPRICE = "listPrice";
    public static final String PROPERTY_PURCHASEORDERPRICE = "purchaseOrderPrice";
    public static final String PROPERTY_ROYALTYAMOUNT = "royaltyAmount";
    public static final String PROPERTY_PRICEEFFECTIVEFROM = "priceEffectiveFrom";
    public static final String PROPERTY_VENDORPRODUCTNO = "vendorProductNo";
    public static final String PROPERTY_VENDORCATEGORY = "vendorCategory";
    public static final String PROPERTY_MANUFACTURER = "manufacturer";
    public static final String PROPERTY_MINIMUMORDERQTY = "minimumOrderQty";
    public static final String PROPERTY_QUANTITYPERPACKAGE = "quantityPerPackage";
    public static final String PROPERTY_FIXEDCOSTPERORDER = "fixedCostPerOrder";
    public static final String PROPERTY_PURCHASINGLEADTIME = "purchasingLeadTime";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";

    public Product() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PRODUCTTYPE, "I");
        setDefaultValue(PROPERTY_DISCONTINUED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
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

    public org.openbravo.model.common.plm.Product getProduct() {
        return (org.openbravo.model.common.plm.Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(org.openbravo.model.common.plm.Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public String getHelpComment() {
        return (String) get(PROPERTY_HELPCOMMENT);
    }

    public void setHelpComment(String helpComment) {
        set(PROPERTY_HELPCOMMENT, helpComment);
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

    public String getEDICode() {
        return (String) get(PROPERTY_EDICODE);
    }

    public void setEDICode(String eDICode) {
        set(PROPERTY_EDICODE, eDICode);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public String getProductCategoryKey() {
        return (String) get(PROPERTY_PRODUCTCATEGORYKEY);
    }

    public void setProductCategoryKey(String productCategoryKey) {
        set(PROPERTY_PRODUCTCATEGORYKEY, productCategoryKey);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public String getProductType() {
        return (String) get(PROPERTY_PRODUCTTYPE);
    }

    public void setProductType(String productType) {
        set(PROPERTY_PRODUCTTYPE, productType);
    }

    public String getClassification() {
        return (String) get(PROPERTY_CLASSIFICATION);
    }

    public void setClassification(String classification) {
        set(PROPERTY_CLASSIFICATION, classification);
    }

    public BigDecimal getVolume() {
        return (BigDecimal) get(PROPERTY_VOLUME);
    }

    public void setVolume(BigDecimal volume) {
        set(PROPERTY_VOLUME, volume);
    }

    public BigDecimal getWeight() {
        return (BigDecimal) get(PROPERTY_WEIGHT);
    }

    public void setWeight(BigDecimal weight) {
        set(PROPERTY_WEIGHT, weight);
    }

    public BigDecimal getShelfWidth() {
        return (BigDecimal) get(PROPERTY_SHELFWIDTH);
    }

    public void setShelfWidth(BigDecimal shelfWidth) {
        set(PROPERTY_SHELFWIDTH, shelfWidth);
    }

    public BigDecimal getShelfHeight() {
        return (BigDecimal) get(PROPERTY_SHELFHEIGHT);
    }

    public void setShelfHeight(BigDecimal shelfHeight) {
        set(PROPERTY_SHELFHEIGHT, shelfHeight);
    }

    public BigDecimal getShelfDepth() {
        return (BigDecimal) get(PROPERTY_SHELFDEPTH);
    }

    public void setShelfDepth(BigDecimal shelfDepth) {
        set(PROPERTY_SHELFDEPTH, shelfDepth);
    }

    public Long getUnitsPerPallet() {
        return (Long) get(PROPERTY_UNITSPERPALLET);
    }

    public void setUnitsPerPallet(Long unitsPerPallet) {
        set(PROPERTY_UNITSPERPALLET, unitsPerPallet);
    }

    public Boolean isDiscontinued() {
        return (Boolean) get(PROPERTY_DISCONTINUED);
    }

    public void setDiscontinued(Boolean discontinued) {
        set(PROPERTY_DISCONTINUED, discontinued);
    }

    public Date getDiscontinuedBy() {
        return (Date) get(PROPERTY_DISCONTINUEDBY);
    }

    public void setDiscontinuedBy(Date discontinuedBy) {
        set(PROPERTY_DISCONTINUEDBY, discontinuedBy);
    }

    public String getImageURL() {
        return (String) get(PROPERTY_IMAGEURL);
    }

    public void setImageURL(String imageURL) {
        set(PROPERTY_IMAGEURL, imageURL);
    }

    public String getDescriptionURL() {
        return (String) get(PROPERTY_DESCRIPTIONURL);
    }

    public void setDescriptionURL(String descriptionURL) {
        set(PROPERTY_DESCRIPTIONURL, descriptionURL);
    }

    public String getBusinessPartnerSearchKey() {
        return (String) get(PROPERTY_BUSINESSPARTNERSEARCHKEY);
    }

    public void setBusinessPartnerSearchKey(String businessPartnerSearchKey) {
        set(PROPERTY_BUSINESSPARTNERSEARCHKEY, businessPartnerSearchKey);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getISOCode() {
        return (String) get(PROPERTY_ISOCODE);
    }

    public void setISOCode(String iSOCode) {
        set(PROPERTY_ISOCODE, iSOCode);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BigDecimal getListPrice() {
        return (BigDecimal) get(PROPERTY_LISTPRICE);
    }

    public void setListPrice(BigDecimal listPrice) {
        set(PROPERTY_LISTPRICE, listPrice);
    }

    public BigDecimal getPurchaseOrderPrice() {
        return (BigDecimal) get(PROPERTY_PURCHASEORDERPRICE);
    }

    public void setPurchaseOrderPrice(BigDecimal purchaseOrderPrice) {
        set(PROPERTY_PURCHASEORDERPRICE, purchaseOrderPrice);
    }

    public BigDecimal getRoyaltyAmount() {
        return (BigDecimal) get(PROPERTY_ROYALTYAMOUNT);
    }

    public void setRoyaltyAmount(BigDecimal royaltyAmount) {
        set(PROPERTY_ROYALTYAMOUNT, royaltyAmount);
    }

    public Date getPriceEffectiveFrom() {
        return (Date) get(PROPERTY_PRICEEFFECTIVEFROM);
    }

    public void setPriceEffectiveFrom(Date priceEffectiveFrom) {
        set(PROPERTY_PRICEEFFECTIVEFROM, priceEffectiveFrom);
    }

    public String getVendorProductNo() {
        return (String) get(PROPERTY_VENDORPRODUCTNO);
    }

    public void setVendorProductNo(String vendorProductNo) {
        set(PROPERTY_VENDORPRODUCTNO, vendorProductNo);
    }

    public String getVendorCategory() {
        return (String) get(PROPERTY_VENDORCATEGORY);
    }

    public void setVendorCategory(String vendorCategory) {
        set(PROPERTY_VENDORCATEGORY, vendorCategory);
    }

    public String getManufacturer() {
        return (String) get(PROPERTY_MANUFACTURER);
    }

    public void setManufacturer(String manufacturer) {
        set(PROPERTY_MANUFACTURER, manufacturer);
    }

    public Long getMinimumOrderQty() {
        return (Long) get(PROPERTY_MINIMUMORDERQTY);
    }

    public void setMinimumOrderQty(Long minimumOrderQty) {
        set(PROPERTY_MINIMUMORDERQTY, minimumOrderQty);
    }

    public Long getQuantityPerPackage() {
        return (Long) get(PROPERTY_QUANTITYPERPACKAGE);
    }

    public void setQuantityPerPackage(Long quantityPerPackage) {
        set(PROPERTY_QUANTITYPERPACKAGE, quantityPerPackage);
    }

    public BigDecimal getFixedCostPerOrder() {
        return (BigDecimal) get(PROPERTY_FIXEDCOSTPERORDER);
    }

    public void setFixedCostPerOrder(BigDecimal fixedCostPerOrder) {
        set(PROPERTY_FIXEDCOSTPERORDER, fixedCostPerOrder);
    }

    public Long getPurchasingLeadTime() {
        return (Long) get(PROPERTY_PURCHASINGLEADTIME);
    }

    public void setPurchasingLeadTime(Long purchasingLeadTime) {
        set(PROPERTY_PURCHASINGLEADTIME, purchasingLeadTime);
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

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
    }

    public Boolean isImportProcessComplete() {
        return (Boolean) get(PROPERTY_IMPORTPROCESSCOMPLETE);
    }

    public void setImportProcessComplete(Boolean importProcessComplete) {
        set(PROPERTY_IMPORTPROCESSCOMPLETE, importProcessComplete);
    }

}
