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
 * Entity class for entity oez_blank_storage_v (stored in table oez_blank_storage_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_blank_storage_v extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_blank_storage_v";
    public static final String ENTITY_NAME = "oez_blank_storage_v";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAMEWAREHOUSE = "namewarehouse";
    public static final String PROPERTY_STORAGENAME = "storagename";
    public static final String PROPERTY_X = "X";
    public static final String PROPERTY_Y = "Y";
    public static final String PROPERTY_Z = "Z";
    public static final String PROPERTY_QTYONHAND = "qtyonhand";
    public static final String PROPERTY_INVENTORYSTATUS = "inventorystatus";

    public oez_blank_storage_v() {
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

    public String getNamewarehouse() {
        return (String) get(PROPERTY_NAMEWAREHOUSE);
    }

    public void setNamewarehouse(String namewarehouse) {
        set(PROPERTY_NAMEWAREHOUSE, namewarehouse);
    }

    public String getStoragename() {
        return (String) get(PROPERTY_STORAGENAME);
    }

    public void setStoragename(String storagename) {
        set(PROPERTY_STORAGENAME, storagename);
    }

    public String getX() {
        return (String) get(PROPERTY_X);
    }

    public void setX(String X) {
        set(PROPERTY_X, X);
    }

    public String getY() {
        return (String) get(PROPERTY_Y);
    }

    public void setY(String Y) {
        set(PROPERTY_Y, Y);
    }

    public String getZ() {
        return (String) get(PROPERTY_Z);
    }

    public void setZ(String Z) {
        set(PROPERTY_Z, Z);
    }

    public Long getQtyonhand() {
        return (Long) get(PROPERTY_QTYONHAND);
    }

    public void setQtyonhand(Long qtyonhand) {
        set(PROPERTY_QTYONHAND, qtyonhand);
    }

    public String getInventorystatus() {
        return (String) get(PROPERTY_INVENTORYSTATUS);
    }

    public void setInventorystatus(String inventorystatus) {
        set(PROPERTY_INVENTORYSTATUS, inventorystatus);
    }

}
