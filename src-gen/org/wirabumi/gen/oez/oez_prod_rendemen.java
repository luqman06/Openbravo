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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity oez_prod_rendemen (stored in table oez_prod_rendemen).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_prod_rendemen extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_prod_rendemen";
    public static final String ENTITY_NAME = "oez_prod_rendemen";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DATEPRODUCTION = "dateproduction";
    public static final String PROPERTY_LOTNO = "lotno";
    public static final String PROPERTY_PRODUCTCODE = "productcode";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_CHECKSIZE = "checksize";
    public static final String PROPERTY_TONASEHO = "tonaseho";
    public static final String PROPERTY_TONASEHL = "tonasehl";
    public static final String PROPERTY_TONASEPD = "tonasepd";
    public static final String PROPERTY_TONASESOAKING = "tonasesoaking";
    public static final String PROPERTY_TONASEHLGREADER = "tonaseHlGreader";
    public static final String PROPERTY_TONASEHLKUPAS = "tonaseHlKupas";
    public static final String PROPERTY_TONASEPDBEFORESOAKING = "tonasePdBeforeSoaking";
    public static final String PROPERTY_TONASEPDKUPASNATURAL = "tonasePdKupasNatural";
    public static final String PROPERTY_TONASEPDICSOAKING = "tonasePdIcSoaking";
    public static final String PROPERTY_TONASEICPDNATURAL = "tonaseIcPdNatural";
    public static final String PROPERTY_TONASEPDMCSOAKING = "tonasePdMcSoaking";
    public static final String PROPERTY_TONASEPDMCNATURAL = "tonasePdMcNatural";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_TONASEHOGREADER = "tonaseHoGreader";

    public oez_prod_rendemen() {
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

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
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

    public Date getDateproduction() {
        return (Date) get(PROPERTY_DATEPRODUCTION);
    }

    public void setDateproduction(Date dateproduction) {
        set(PROPERTY_DATEPRODUCTION, dateproduction);
    }

    public String getLotno() {
        return (String) get(PROPERTY_LOTNO);
    }

    public void setLotno(String lotno) {
        set(PROPERTY_LOTNO, lotno);
    }

    public String getProductcode() {
        return (String) get(PROPERTY_PRODUCTCODE);
    }

    public void setProductcode(String productcode) {
        set(PROPERTY_PRODUCTCODE, productcode);
    }

    public String getProduct() {
        return (String) get(PROPERTY_PRODUCT);
    }

    public void setProduct(String product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getChecksize() {
        return (BigDecimal) get(PROPERTY_CHECKSIZE);
    }

    public void setChecksize(BigDecimal checksize) {
        set(PROPERTY_CHECKSIZE, checksize);
    }

    public BigDecimal getTonaseho() {
        return (BigDecimal) get(PROPERTY_TONASEHO);
    }

    public void setTonaseho(BigDecimal tonaseho) {
        set(PROPERTY_TONASEHO, tonaseho);
    }

    public BigDecimal getTonasehl() {
        return (BigDecimal) get(PROPERTY_TONASEHL);
    }

    public void setTonasehl(BigDecimal tonasehl) {
        set(PROPERTY_TONASEHL, tonasehl);
    }

    public BigDecimal getTonasepd() {
        return (BigDecimal) get(PROPERTY_TONASEPD);
    }

    public void setTonasepd(BigDecimal tonasepd) {
        set(PROPERTY_TONASEPD, tonasepd);
    }

    public BigDecimal getTonasesoaking() {
        return (BigDecimal) get(PROPERTY_TONASESOAKING);
    }

    public void setTonasesoaking(BigDecimal tonasesoaking) {
        set(PROPERTY_TONASESOAKING, tonasesoaking);
    }

    public BigDecimal getTonaseHlGreader() {
        return (BigDecimal) get(PROPERTY_TONASEHLGREADER);
    }

    public void setTonaseHlGreader(BigDecimal tonaseHlGreader) {
        set(PROPERTY_TONASEHLGREADER, tonaseHlGreader);
    }

    public BigDecimal getTonaseHlKupas() {
        return (BigDecimal) get(PROPERTY_TONASEHLKUPAS);
    }

    public void setTonaseHlKupas(BigDecimal tonaseHlKupas) {
        set(PROPERTY_TONASEHLKUPAS, tonaseHlKupas);
    }

    public BigDecimal getTonasePdBeforeSoaking() {
        return (BigDecimal) get(PROPERTY_TONASEPDBEFORESOAKING);
    }

    public void setTonasePdBeforeSoaking(BigDecimal tonasePdBeforeSoaking) {
        set(PROPERTY_TONASEPDBEFORESOAKING, tonasePdBeforeSoaking);
    }

    public BigDecimal getTonasePdKupasNatural() {
        return (BigDecimal) get(PROPERTY_TONASEPDKUPASNATURAL);
    }

    public void setTonasePdKupasNatural(BigDecimal tonasePdKupasNatural) {
        set(PROPERTY_TONASEPDKUPASNATURAL, tonasePdKupasNatural);
    }

    public BigDecimal getTonasePdIcSoaking() {
        return (BigDecimal) get(PROPERTY_TONASEPDICSOAKING);
    }

    public void setTonasePdIcSoaking(BigDecimal tonasePdIcSoaking) {
        set(PROPERTY_TONASEPDICSOAKING, tonasePdIcSoaking);
    }

    public BigDecimal getTonaseIcPdNatural() {
        return (BigDecimal) get(PROPERTY_TONASEICPDNATURAL);
    }

    public void setTonaseIcPdNatural(BigDecimal tonaseIcPdNatural) {
        set(PROPERTY_TONASEICPDNATURAL, tonaseIcPdNatural);
    }

    public BigDecimal getTonasePdMcSoaking() {
        return (BigDecimal) get(PROPERTY_TONASEPDMCSOAKING);
    }

    public void setTonasePdMcSoaking(BigDecimal tonasePdMcSoaking) {
        set(PROPERTY_TONASEPDMCSOAKING, tonasePdMcSoaking);
    }

    public BigDecimal getTonasePdMcNatural() {
        return (BigDecimal) get(PROPERTY_TONASEPDMCNATURAL);
    }

    public void setTonasePdMcNatural(BigDecimal tonasePdMcNatural) {
        set(PROPERTY_TONASEPDMCNATURAL, tonasePdMcNatural);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public BigDecimal getTonaseHoGreader() {
        return (BigDecimal) get(PROPERTY_TONASEHOGREADER);
    }

    public void setTonaseHoGreader(BigDecimal tonaseHoGreader) {
        set(PROPERTY_TONASEHOGREADER, tonaseHoGreader);
    }

}
