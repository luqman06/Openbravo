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
package org.wirabumi.gen.oez.importmasterdata;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.pricing.pricelist.PriceListVersion;
import org.openbravo.model.pricing.pricelist.ProductPrice;
/**
 * Entity class for entity oez_i_productprice (stored in table oez_i_productprice).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_i_productprice extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_i_productprice";
    public static final String ENTITY_NAME = "oez_i_productprice";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_PRICELISTVERSIONNAME = "pricelistversionname";
    public static final String PROPERTY_PRICELISTVERSION = "priceListVersion";
    public static final String PROPERTY_NETLISTPRICE = "netListPrice";
    public static final String PROPERTY_STANDARDPRICE = "standardPrice";
    public static final String PROPERTY_PRICELIMIT = "priceLimit";
    public static final String PROPERTY_PRODUCTPRICE = "productPrice";
    public static final String PROPERTY_PROCESSED = "processed";

    public oez_i_productprice() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getPricelistversionname() {
        return (String) get(PROPERTY_PRICELISTVERSIONNAME);
    }

    public void setPricelistversionname(String pricelistversionname) {
        set(PROPERTY_PRICELISTVERSIONNAME, pricelistversionname);
    }

    public PriceListVersion getPriceListVersion() {
        return (PriceListVersion) get(PROPERTY_PRICELISTVERSION);
    }

    public void setPriceListVersion(PriceListVersion priceListVersion) {
        set(PROPERTY_PRICELISTVERSION, priceListVersion);
    }

    public BigDecimal getNetListPrice() {
        return (BigDecimal) get(PROPERTY_NETLISTPRICE);
    }

    public void setNetListPrice(BigDecimal netListPrice) {
        set(PROPERTY_NETLISTPRICE, netListPrice);
    }

    public BigDecimal getStandardPrice() {
        return (BigDecimal) get(PROPERTY_STANDARDPRICE);
    }

    public void setStandardPrice(BigDecimal standardPrice) {
        set(PROPERTY_STANDARDPRICE, standardPrice);
    }

    public BigDecimal getPriceLimit() {
        return (BigDecimal) get(PROPERTY_PRICELIMIT);
    }

    public void setPriceLimit(BigDecimal priceLimit) {
        set(PROPERTY_PRICELIMIT, priceLimit);
    }

    public ProductPrice getProductPrice() {
        return (ProductPrice) get(PROPERTY_PRODUCTPRICE);
    }

    public void setProductPrice(ProductPrice productPrice) {
        set(PROPERTY_PRODUCTPRICE, productPrice);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

}
