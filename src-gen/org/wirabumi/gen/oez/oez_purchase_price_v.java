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
package org.wirabumi.gen.oez;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity oez_purchase_price_v (stored in table oez_purchase_price_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_purchase_price_v extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_purchase_price_v";
    public static final String ENTITY_NAME = "oez_purchase_price_v";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentno";
    public static final String PROPERTY_CUSTOMER = "customer";
    public static final String PROPERTY_CUSTOMERCODE = "customerCode";
    public static final String PROPERTY_POREFERENCE = "poreference";
    public static final String PROPERTY_DATEORDERED = "dateordered";
    public static final String PROPERTY_DATEPROMISED = "datepromised";
    public static final String PROPERTY_PRODUCTNAME = "productName";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_OEZAUMPRICEACTUAL = "oezAumPriceactual";
    public static final String PROPERTY_PRICEACTUAL = "priceactual";
    public static final String PROPERTY_OEZAUMQTYORDERED = "oezAumQtyordered";
    public static final String PROPERTY_QTYORDERED = "qtyordered";
    public static final String PROPERTY_QTYDELIVERED = "qtydelivered";
    public static final String PROPERTY_QTYINVOICED = "qtyinvoiced";
    public static final String PROPERTY_ISOCODE = "iSOCode";
    public static final String PROPERTY_CURSYMBOL = "cursymbol";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_LINENETAMT = "linenetamt";
    public static final String PROPERTY_GRANDTOTAL = "grandtotal";

    public oez_purchase_price_v() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public String getDocumentno() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentno(String documentno) {
        set(PROPERTY_DOCUMENTNO, documentno);
    }

    public String getCustomer() {
        return (String) get(PROPERTY_CUSTOMER);
    }

    public void setCustomer(String customer) {
        set(PROPERTY_CUSTOMER, customer);
    }

    public String getCustomerCode() {
        return (String) get(PROPERTY_CUSTOMERCODE);
    }

    public void setCustomerCode(String customerCode) {
        set(PROPERTY_CUSTOMERCODE, customerCode);
    }

    public String getPoreference() {
        return (String) get(PROPERTY_POREFERENCE);
    }

    public void setPoreference(String poreference) {
        set(PROPERTY_POREFERENCE, poreference);
    }

    public Date getDateordered() {
        return (Date) get(PROPERTY_DATEORDERED);
    }

    public void setDateordered(Date dateordered) {
        set(PROPERTY_DATEORDERED, dateordered);
    }

    public Date getDatepromised() {
        return (Date) get(PROPERTY_DATEPROMISED);
    }

    public void setDatepromised(Date datepromised) {
        set(PROPERTY_DATEPROMISED, datepromised);
    }

    public String getProductName() {
        return (String) get(PROPERTY_PRODUCTNAME);
    }

    public void setProductName(String productName) {
        set(PROPERTY_PRODUCTNAME, productName);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getProductCategory() {
        return (String) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(String productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public Long getOezAumPriceactual() {
        return (Long) get(PROPERTY_OEZAUMPRICEACTUAL);
    }

    public void setOezAumPriceactual(Long oezAumPriceactual) {
        set(PROPERTY_OEZAUMPRICEACTUAL, oezAumPriceactual);
    }

    public Long getPriceactual() {
        return (Long) get(PROPERTY_PRICEACTUAL);
    }

    public void setPriceactual(Long priceactual) {
        set(PROPERTY_PRICEACTUAL, priceactual);
    }

    public Long getOezAumQtyordered() {
        return (Long) get(PROPERTY_OEZAUMQTYORDERED);
    }

    public void setOezAumQtyordered(Long oezAumQtyordered) {
        set(PROPERTY_OEZAUMQTYORDERED, oezAumQtyordered);
    }

    public Long getQtyordered() {
        return (Long) get(PROPERTY_QTYORDERED);
    }

    public void setQtyordered(Long qtyordered) {
        set(PROPERTY_QTYORDERED, qtyordered);
    }

    public Long getQtydelivered() {
        return (Long) get(PROPERTY_QTYDELIVERED);
    }

    public void setQtydelivered(Long qtydelivered) {
        set(PROPERTY_QTYDELIVERED, qtydelivered);
    }

    public Long getQtyinvoiced() {
        return (Long) get(PROPERTY_QTYINVOICED);
    }

    public void setQtyinvoiced(Long qtyinvoiced) {
        set(PROPERTY_QTYINVOICED, qtyinvoiced);
    }

    public String getISOCode() {
        return (String) get(PROPERTY_ISOCODE);
    }

    public void setISOCode(String iSOCode) {
        set(PROPERTY_ISOCODE, iSOCode);
    }

    public String getCursymbol() {
        return (String) get(PROPERTY_CURSYMBOL);
    }

    public void setCursymbol(String cursymbol) {
        set(PROPERTY_CURSYMBOL, cursymbol);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Long getLinenetamt() {
        return (Long) get(PROPERTY_LINENETAMT);
    }

    public void setLinenetamt(Long linenetamt) {
        set(PROPERTY_LINENETAMT, linenetamt);
    }

    public Long getGrandtotal() {
        return (Long) get(PROPERTY_GRANDTOTAL);
    }

    public void setGrandtotal(Long grandtotal) {
        set(PROPERTY_GRANDTOTAL, grandtotal);
    }

}
