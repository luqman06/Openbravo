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
 * Entity class for entity oez_sales_tracebility_v (stored in table oez_sales_tracebility_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_sales_tracebility_v extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_sales_tracebility_v";
    public static final String ENTITY_NAME = "oez_sales_tracebility_v";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentno";
    public static final String PROPERTY_DOCSTATUS = "docstatus";
    public static final String PROPERTY_DATEORDERED = "dateordered";
    public static final String PROPERTY_VALUE = "value";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_QTYORDERED = "qtyordered";
    public static final String PROPERTY_QTYDELIVERED = "qtydelivered";
    public static final String PROPERTY_OEZAUMPRICEACTUAL = "oezAumPriceactual";
    public static final String PROPERTY_PRICELIST = "pricelist";
    public static final String PROPERTY_PRICESTD = "pricestd";
    public static final String PROPERTY_PRICEACTUAL = "priceactual";
    public static final String PROPERTY_OEZMAKENETUNITPRICEFIXED = "oezMakenetunitpricefixed";
    public static final String PROPERTY_RECEIPTNO = "receiptNo";
    public static final String PROPERTY_MOVEMENTDATE = "movementdate";
    public static final String PROPERTY_INVOICENO = "invoiceNo";
    public static final String PROPERTY_MOVEMENTQTY = "movementqty";
    public static final String PROPERTY_DATEINVOICED = "dateinvoiced";

    public oez_sales_tracebility_v() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OEZMAKENETUNITPRICEFIXED, false);
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

    public String getDocstatus() {
        return (String) get(PROPERTY_DOCSTATUS);
    }

    public void setDocstatus(String docstatus) {
        set(PROPERTY_DOCSTATUS, docstatus);
    }

    public Date getDateordered() {
        return (Date) get(PROPERTY_DATEORDERED);
    }

    public void setDateordered(Date dateordered) {
        set(PROPERTY_DATEORDERED, dateordered);
    }

    public String getValue() {
        return (String) get(PROPERTY_VALUE);
    }

    public void setValue(String value) {
        set(PROPERTY_VALUE, value);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
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

    public Long getOezAumPriceactual() {
        return (Long) get(PROPERTY_OEZAUMPRICEACTUAL);
    }

    public void setOezAumPriceactual(Long oezAumPriceactual) {
        set(PROPERTY_OEZAUMPRICEACTUAL, oezAumPriceactual);
    }

    public Long getPricelist() {
        return (Long) get(PROPERTY_PRICELIST);
    }

    public void setPricelist(Long pricelist) {
        set(PROPERTY_PRICELIST, pricelist);
    }

    public Long getPricestd() {
        return (Long) get(PROPERTY_PRICESTD);
    }

    public void setPricestd(Long pricestd) {
        set(PROPERTY_PRICESTD, pricestd);
    }

    public Long getPriceactual() {
        return (Long) get(PROPERTY_PRICEACTUAL);
    }

    public void setPriceactual(Long priceactual) {
        set(PROPERTY_PRICEACTUAL, priceactual);
    }

    public Boolean isOezMakenetunitpricefixed() {
        return (Boolean) get(PROPERTY_OEZMAKENETUNITPRICEFIXED);
    }

    public void setOezMakenetunitpricefixed(Boolean oezMakenetunitpricefixed) {
        set(PROPERTY_OEZMAKENETUNITPRICEFIXED, oezMakenetunitpricefixed);
    }

    public String getReceiptNo() {
        return (String) get(PROPERTY_RECEIPTNO);
    }

    public void setReceiptNo(String receiptNo) {
        set(PROPERTY_RECEIPTNO, receiptNo);
    }

    public Date getMovementdate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementdate(Date movementdate) {
        set(PROPERTY_MOVEMENTDATE, movementdate);
    }

    public String getInvoiceNo() {
        return (String) get(PROPERTY_INVOICENO);
    }

    public void setInvoiceNo(String invoiceNo) {
        set(PROPERTY_INVOICENO, invoiceNo);
    }

    public Long getMovementqty() {
        return (Long) get(PROPERTY_MOVEMENTQTY);
    }

    public void setMovementqty(Long movementqty) {
        set(PROPERTY_MOVEMENTQTY, movementqty);
    }

    public Date getDateinvoiced() {
        return (Date) get(PROPERTY_DATEINVOICED);
    }

    public void setDateinvoiced(Date dateinvoiced) {
        set(PROPERTY_DATEINVOICED, dateinvoiced);
    }

}
