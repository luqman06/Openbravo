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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity oez_daily_ln_prod_shrimp (stored in table oez_daily_ln_prod_shrimp).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_daily_ln_prod_shrimp extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_daily_ln_prod_shrimp";
    public static final String ENTITY_NAME = "oez_daily_ln_prod_shrimp";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BPARTNER = "bpartner";
    public static final String PROPERTY_SUPPLIERCODE = "suppliercode";
    public static final String PROPERTY_NONOTA = "nonota";
    public static final String PROPERTY_RECEIPTDATE = "receiptdate";
    public static final String PROPERTY_QTYRECEIPT = "qtyreceipt";
    public static final String PROPERTY_QTYTIMBUN = "qtytimbun";
    public static final String PROPERTY_TOTALRM = "totalrm";
    public static final String PROPERTY_RMPROCESS = "rmprocess";
    public static final String PROPERTY_SUMSTTPSOAK = "sumsttpsoak";
    public static final String PROPERTY_TIMBUNHL = "timbunhl";
    public static final String PROPERTY_TIMBUNPD = "timbunpd";
    public static final String PROPERTY_TIMBUNHO = "timbunho";
    public static final String PROPERTY_FGPD = "fgpd";
    public static final String PROPERTY_FGSTTP = "fgsttp";
    public static final String PROPERTY_FGPTO = "fgpto";
    public static final String PROPERTY_FGHLBROOK = "fghlbrook";
    public static final String PROPERTY_FGHO = "fgho";
    public static final String PROPERTY_QTYBROKEN = "qtybroken";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_OEZDAILYHDPRODSHRIMP = "oEZDailyHdProdShrimp";

    public oez_daily_ln_prod_shrimp() {
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
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

    public BusinessPartner getBpartner() {
        return (BusinessPartner) get(PROPERTY_BPARTNER);
    }

    public void setBpartner(BusinessPartner bpartner) {
        set(PROPERTY_BPARTNER, bpartner);
    }

    public String getSuppliercode() {
        return (String) get(PROPERTY_SUPPLIERCODE);
    }

    public void setSuppliercode(String suppliercode) {
        set(PROPERTY_SUPPLIERCODE, suppliercode);
    }

    public String getNonota() {
        return (String) get(PROPERTY_NONOTA);
    }

    public void setNonota(String nonota) {
        set(PROPERTY_NONOTA, nonota);
    }

    public Date getReceiptdate() {
        return (Date) get(PROPERTY_RECEIPTDATE);
    }

    public void setReceiptdate(Date receiptdate) {
        set(PROPERTY_RECEIPTDATE, receiptdate);
    }

    public BigDecimal getQtyreceipt() {
        return (BigDecimal) get(PROPERTY_QTYRECEIPT);
    }

    public void setQtyreceipt(BigDecimal qtyreceipt) {
        set(PROPERTY_QTYRECEIPT, qtyreceipt);
    }

    public BigDecimal getQtytimbun() {
        return (BigDecimal) get(PROPERTY_QTYTIMBUN);
    }

    public void setQtytimbun(BigDecimal qtytimbun) {
        set(PROPERTY_QTYTIMBUN, qtytimbun);
    }

    public BigDecimal getTotalrm() {
        return (BigDecimal) get(PROPERTY_TOTALRM);
    }

    public void setTotalrm(BigDecimal totalrm) {
        set(PROPERTY_TOTALRM, totalrm);
    }

    public BigDecimal getRmprocess() {
        return (BigDecimal) get(PROPERTY_RMPROCESS);
    }

    public void setRmprocess(BigDecimal rmprocess) {
        set(PROPERTY_RMPROCESS, rmprocess);
    }

    public BigDecimal getSumsttpsoak() {
        return (BigDecimal) get(PROPERTY_SUMSTTPSOAK);
    }

    public void setSumsttpsoak(BigDecimal sumsttpsoak) {
        set(PROPERTY_SUMSTTPSOAK, sumsttpsoak);
    }

    public BigDecimal getTimbunhl() {
        return (BigDecimal) get(PROPERTY_TIMBUNHL);
    }

    public void setTimbunhl(BigDecimal timbunhl) {
        set(PROPERTY_TIMBUNHL, timbunhl);
    }

    public BigDecimal getTimbunpd() {
        return (BigDecimal) get(PROPERTY_TIMBUNPD);
    }

    public void setTimbunpd(BigDecimal timbunpd) {
        set(PROPERTY_TIMBUNPD, timbunpd);
    }

    public BigDecimal getTimbunho() {
        return (BigDecimal) get(PROPERTY_TIMBUNHO);
    }

    public void setTimbunho(BigDecimal timbunho) {
        set(PROPERTY_TIMBUNHO, timbunho);
    }

    public BigDecimal getFgpd() {
        return (BigDecimal) get(PROPERTY_FGPD);
    }

    public void setFgpd(BigDecimal fgpd) {
        set(PROPERTY_FGPD, fgpd);
    }

    public BigDecimal getFgsttp() {
        return (BigDecimal) get(PROPERTY_FGSTTP);
    }

    public void setFgsttp(BigDecimal fgsttp) {
        set(PROPERTY_FGSTTP, fgsttp);
    }

    public BigDecimal getFgpto() {
        return (BigDecimal) get(PROPERTY_FGPTO);
    }

    public void setFgpto(BigDecimal fgpto) {
        set(PROPERTY_FGPTO, fgpto);
    }

    public BigDecimal getFghlbrook() {
        return (BigDecimal) get(PROPERTY_FGHLBROOK);
    }

    public void setFghlbrook(BigDecimal fghlbrook) {
        set(PROPERTY_FGHLBROOK, fghlbrook);
    }

    public BigDecimal getFgho() {
        return (BigDecimal) get(PROPERTY_FGHO);
    }

    public void setFgho(BigDecimal fgho) {
        set(PROPERTY_FGHO, fgho);
    }

    public BigDecimal getQtybroken() {
        return (BigDecimal) get(PROPERTY_QTYBROKEN);
    }

    public void setQtybroken(BigDecimal qtybroken) {
        set(PROPERTY_QTYBROKEN, qtybroken);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public oez_daily_hd_prod_shrimp getOEZDailyHdProdShrimp() {
        return (oez_daily_hd_prod_shrimp) get(PROPERTY_OEZDAILYHDPRODSHRIMP);
    }

    public void setOEZDailyHdProdShrimp(oez_daily_hd_prod_shrimp oEZDailyHdProdShrimp) {
        set(PROPERTY_OEZDAILYHDPRODSHRIMP, oEZDailyHdProdShrimp);
    }

}
