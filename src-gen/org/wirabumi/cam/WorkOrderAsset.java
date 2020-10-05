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
package org.wirabumi.cam;

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
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.manufacturing.maintenance.Task;
/**
 * Entity class for entity cam_workorderasset (stored in table cam_workorderasset).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class WorkOrderAsset extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "cam_workorderasset";
    public static final String ENTITY_NAME = "cam_workorderasset";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WORKORDER = "workOrder";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_ISASSETMOVEMENT = "isassetmovement";
    public static final String PROPERTY_ASSETLOCATION = "assetLocation";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_ISASSETOPNAME = "isAssetOpname";
    public static final String PROPERTY_MAINTOPERATION = "maintOperation";
    public static final String PROPERTY_ESTIMATEDCOST = "estimatedcost";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ISDISPOSED = "isDisposed";
    public static final String PROPERTY_OLDLOCATION = "oldLocation";
    public static final String PROPERTY_OLDCOSTCENTER = "oldCostCenter";

    public WorkOrderAsset() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISASSETMOVEMENT, false);
        setDefaultValue(PROPERTY_ISASSETOPNAME, false);
        setDefaultValue(PROPERTY_ISDISPOSED, false);
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

    public WorkOrder getWorkOrder() {
        return (WorkOrder) get(PROPERTY_WORKORDER);
    }

    public void setWorkOrder(WorkOrder workOrder) {
        set(PROPERTY_WORKORDER, workOrder);
    }

    public Asset getAsset() {
        return (Asset) get(PROPERTY_ASSET);
    }

    public void setAsset(Asset asset) {
        set(PROPERTY_ASSET, asset);
    }

    public Boolean isAssetmovement() {
        return (Boolean) get(PROPERTY_ISASSETMOVEMENT);
    }

    public void setAssetmovement(Boolean isassetmovement) {
        set(PROPERTY_ISASSETMOVEMENT, isassetmovement);
    }

    public Location getAssetLocation() {
        return (Location) get(PROPERTY_ASSETLOCATION);
    }

    public void setAssetLocation(Location assetLocation) {
        set(PROPERTY_ASSETLOCATION, assetLocation);
    }

    public Costcenter getCostCenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(Costcenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public Boolean isAssetOpname() {
        return (Boolean) get(PROPERTY_ISASSETOPNAME);
    }

    public void setAssetOpname(Boolean isAssetOpname) {
        set(PROPERTY_ISASSETOPNAME, isAssetOpname);
    }

    public Task getMaintOperation() {
        return (Task) get(PROPERTY_MAINTOPERATION);
    }

    public void setMaintOperation(Task maintOperation) {
        set(PROPERTY_MAINTOPERATION, maintOperation);
    }

    public BigDecimal getEstimatedcost() {
        return (BigDecimal) get(PROPERTY_ESTIMATEDCOST);
    }

    public void setEstimatedcost(BigDecimal estimatedcost) {
        set(PROPERTY_ESTIMATEDCOST, estimatedcost);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isDisposed() {
        return (Boolean) get(PROPERTY_ISDISPOSED);
    }

    public void setDisposed(Boolean isDisposed) {
        set(PROPERTY_ISDISPOSED, isDisposed);
    }

    public Location getOldLocation() {
        return (Location) get(PROPERTY_OLDLOCATION);
    }

    public void setOldLocation(Location oldLocation) {
        set(PROPERTY_OLDLOCATION, oldLocation);
    }

    public Costcenter getOldCostCenter() {
        return (Costcenter) get(PROPERTY_OLDCOSTCENTER);
    }

    public void setOldCostCenter(Costcenter oldCostCenter) {
        set(PROPERTY_OLDCOSTCENTER, oldCostCenter);
    }

}
