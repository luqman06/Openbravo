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
package org.wirabumi.gen.oez.importtrialbalance;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.transaction.InventoryCount;
import org.openbravo.model.materialmgmt.transaction.InventoryCountLine;
/**
 * Entity class for entity oez_i_inventory (stored in table oez_i_inventory).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_i_inventory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_i_inventory";
    public static final String ENTITY_NAME = "oez_i_inventory";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_STORAGEBINVALUE = "storagebinvalue";
    public static final String PROPERTY_PRODUCTSEARCHKEY = "productSearchKey";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_MOVEMENTDATE = "movementDate";
    public static final String PROPERTY_MOVEMENTQUANTITY = "movementQuantity";
    public static final String PROPERTY_PHYSINVENTORY = "physInventory";
    public static final String PROPERTY_PHYSICALINVENTORYLINE = "physicalInventoryLine";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_WAREHOUSEVALUE = "warehousevalue";
    public static final String PROPERTY_IMPORTINVENTORY = "importinventory";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ISIMPORTED = "isimported";

    public oez_i_inventory() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_IMPORTINVENTORY, false);
        setDefaultValue(PROPERTY_ISIMPORTED, false);
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

    public String getStoragebinvalue() {
        return (String) get(PROPERTY_STORAGEBINVALUE);
    }

    public void setStoragebinvalue(String storagebinvalue) {
        set(PROPERTY_STORAGEBINVALUE, storagebinvalue);
    }

    public String getProductSearchKey() {
        return (String) get(PROPERTY_PRODUCTSEARCHKEY);
    }

    public void setProductSearchKey(String productSearchKey) {
        set(PROPERTY_PRODUCTSEARCHKEY, productSearchKey);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public Date getMovementDate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementDate(Date movementDate) {
        set(PROPERTY_MOVEMENTDATE, movementDate);
    }

    public BigDecimal getMovementQuantity() {
        return (BigDecimal) get(PROPERTY_MOVEMENTQUANTITY);
    }

    public void setMovementQuantity(BigDecimal movementQuantity) {
        set(PROPERTY_MOVEMENTQUANTITY, movementQuantity);
    }

    public InventoryCount getPhysInventory() {
        return (InventoryCount) get(PROPERTY_PHYSINVENTORY);
    }

    public void setPhysInventory(InventoryCount physInventory) {
        set(PROPERTY_PHYSINVENTORY, physInventory);
    }

    public InventoryCountLine getPhysicalInventoryLine() {
        return (InventoryCountLine) get(PROPERTY_PHYSICALINVENTORYLINE);
    }

    public void setPhysicalInventoryLine(InventoryCountLine physicalInventoryLine) {
        set(PROPERTY_PHYSICALINVENTORYLINE, physicalInventoryLine);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getWarehousevalue() {
        return (String) get(PROPERTY_WAREHOUSEVALUE);
    }

    public void setWarehousevalue(String warehousevalue) {
        set(PROPERTY_WAREHOUSEVALUE, warehousevalue);
    }

    public Boolean isImportinventory() {
        return (Boolean) get(PROPERTY_IMPORTINVENTORY);
    }

    public void setImportinventory(Boolean importinventory) {
        set(PROPERTY_IMPORTINVENTORY, importinventory);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isImported() {
        return (Boolean) get(PROPERTY_ISIMPORTED);
    }

    public void setImported(Boolean isimported) {
        set(PROPERTY_ISIMPORTED, isimported);
    }

}
