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
 * Entity class for entity oez_prod_rendemen_v (stored in table oez_prod_rendemen_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_prod_rendemen_v extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_prod_rendemen_v";
    public static final String ENTITY_NAME = "oez_prod_rendemen_v";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DATEPRODUCTION = "dateproduction";
    public static final String PROPERTY_LOTNO = "lotno";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_VALUE = "value";
    public static final String PROPERTY_PRODUCTCODE = "productcode";
    public static final String PROPERTY_CHECKSIZE = "checksize";
    public static final String PROPERTY_TONASEHO = "tonaseho";
    public static final String PROPERTY_TONASEHL = "tonasehl";
    public static final String PROPERTY_TONASEPD = "tonasepd";
    public static final String PROPERTY_TONASESOAKING = "tonasesoaking";
    public static final String PROPERTY_RENDEMANHOHL = "rendemanhohl";
    public static final String PROPERTY_RENDEMENHLPD = "rendemenhlpd";
    public static final String PROPERTY_RENDEMENPDSOAKING = "rendemenpdsoaking";
    public static final String PROPERTY_RENDEMENGLOBAL = "rendemenglobal";

    public oez_prod_rendemen_v() {
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getValue() {
        return (String) get(PROPERTY_VALUE);
    }

    public void setValue(String value) {
        set(PROPERTY_VALUE, value);
    }

    public String getProductcode() {
        return (String) get(PROPERTY_PRODUCTCODE);
    }

    public void setProductcode(String productcode) {
        set(PROPERTY_PRODUCTCODE, productcode);
    }

    public Long getChecksize() {
        return (Long) get(PROPERTY_CHECKSIZE);
    }

    public void setChecksize(Long checksize) {
        set(PROPERTY_CHECKSIZE, checksize);
    }

    public Long getTonaseho() {
        return (Long) get(PROPERTY_TONASEHO);
    }

    public void setTonaseho(Long tonaseho) {
        set(PROPERTY_TONASEHO, tonaseho);
    }

    public Long getTonasehl() {
        return (Long) get(PROPERTY_TONASEHL);
    }

    public void setTonasehl(Long tonasehl) {
        set(PROPERTY_TONASEHL, tonasehl);
    }

    public Long getTonasepd() {
        return (Long) get(PROPERTY_TONASEPD);
    }

    public void setTonasepd(Long tonasepd) {
        set(PROPERTY_TONASEPD, tonasepd);
    }

    public Long getTonasesoaking() {
        return (Long) get(PROPERTY_TONASESOAKING);
    }

    public void setTonasesoaking(Long tonasesoaking) {
        set(PROPERTY_TONASESOAKING, tonasesoaking);
    }

    public Long getRendemanhohl() {
        return (Long) get(PROPERTY_RENDEMANHOHL);
    }

    public void setRendemanhohl(Long rendemanhohl) {
        set(PROPERTY_RENDEMANHOHL, rendemanhohl);
    }

    public Long getRendemenhlpd() {
        return (Long) get(PROPERTY_RENDEMENHLPD);
    }

    public void setRendemenhlpd(Long rendemenhlpd) {
        set(PROPERTY_RENDEMENHLPD, rendemenhlpd);
    }

    public Long getRendemenpdsoaking() {
        return (Long) get(PROPERTY_RENDEMENPDSOAKING);
    }

    public void setRendemenpdsoaking(Long rendemenpdsoaking) {
        set(PROPERTY_RENDEMENPDSOAKING, rendemenpdsoaking);
    }

    public Long getRendemenglobal() {
        return (Long) get(PROPERTY_RENDEMENGLOBAL);
    }

    public void setRendemenglobal(Long rendemenglobal) {
        set(PROPERTY_RENDEMENGLOBAL, rendemenglobal);
    }

}
