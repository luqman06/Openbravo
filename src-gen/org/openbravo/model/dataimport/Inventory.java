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
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.transaction.InventoryCount;
import org.openbravo.model.materialmgmt.transaction.InventoryCountLine;
/**
 * Entity class for entity DataImportInventory (stored in table I_Inventory).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Inventory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_Inventory";
    public static final String ENTITY_NAME = "DataImportInventory";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_LOTNAME = "lotName";
    public static final String PROPERTY_PHYSICALINVENTORYLINE = "physicalInventoryLine";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_BOOKQUANTITY = "bookQuantity";
    public static final String PROPERTY_QUANTITYCOUNT = "quantityCount";
    public static final String PROPERTY_SERIALNO = "serialNo";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_WAREHOUSEKEY = "warehouseKey";
    public static final String PROPERTY_ROWX = "rowX";
    public static final String PROPERTY_STACKY = "stackY";
    public static final String PROPERTY_LEVELZ = "levelZ";
    public static final String PROPERTY_LOCATORKEY = "locatorKey";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_PHYSINVENTORY = "physInventory";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_MOVEMENTDATE = "movementDate";

    public Inventory() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getLotName() {
        return (String) get(PROPERTY_LOTNAME);
    }

    public void setLotName(String lotName) {
        set(PROPERTY_LOTNAME, lotName);
    }

    public InventoryCountLine getPhysicalInventoryLine() {
        return (InventoryCountLine) get(PROPERTY_PHYSICALINVENTORYLINE);
    }

    public void setPhysicalInventoryLine(InventoryCountLine physicalInventoryLine) {
        set(PROPERTY_PHYSICALINVENTORYLINE, physicalInventoryLine);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public BigDecimal getBookQuantity() {
        return (BigDecimal) get(PROPERTY_BOOKQUANTITY);
    }

    public void setBookQuantity(BigDecimal bookQuantity) {
        set(PROPERTY_BOOKQUANTITY, bookQuantity);
    }

    public BigDecimal getQuantityCount() {
        return (BigDecimal) get(PROPERTY_QUANTITYCOUNT);
    }

    public void setQuantityCount(BigDecimal quantityCount) {
        set(PROPERTY_QUANTITYCOUNT, quantityCount);
    }

    public String getSerialNo() {
        return (String) get(PROPERTY_SERIALNO);
    }

    public void setSerialNo(String serialNo) {
        set(PROPERTY_SERIALNO, serialNo);
    }

    public String getUPCEAN() {
        return (String) get(PROPERTY_UPCEAN);
    }

    public void setUPCEAN(String uPCEAN) {
        set(PROPERTY_UPCEAN, uPCEAN);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getWarehouseKey() {
        return (String) get(PROPERTY_WAREHOUSEKEY);
    }

    public void setWarehouseKey(String warehouseKey) {
        set(PROPERTY_WAREHOUSEKEY, warehouseKey);
    }

    public String getRowX() {
        return (String) get(PROPERTY_ROWX);
    }

    public void setRowX(String rowX) {
        set(PROPERTY_ROWX, rowX);
    }

    public String getStackY() {
        return (String) get(PROPERTY_STACKY);
    }

    public void setStackY(String stackY) {
        set(PROPERTY_STACKY, stackY);
    }

    public String getLevelZ() {
        return (String) get(PROPERTY_LEVELZ);
    }

    public void setLevelZ(String levelZ) {
        set(PROPERTY_LEVELZ, levelZ);
    }

    public String getLocatorKey() {
        return (String) get(PROPERTY_LOCATORKEY);
    }

    public void setLocatorKey(String locatorKey) {
        set(PROPERTY_LOCATORKEY, locatorKey);
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

    public InventoryCount getPhysInventory() {
        return (InventoryCount) get(PROPERTY_PHYSINVENTORY);
    }

    public void setPhysInventory(InventoryCount physInventory) {
        set(PROPERTY_PHYSINVENTORY, physInventory);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public Date getMovementDate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementDate(Date movementDate) {
        set(PROPERTY_MOVEMENTDATE, movementDate);
    }

}
