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
package org.openbravo.model.manufacturing.maintenance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.wirabumi.cam.WorkOrder;
import org.wirabumi.cam.WorkOrderAsset;
/**
 * Entity class for entity ManufacturingMaintenanceTask (stored in table MA_Maint_Operation).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Task extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_Maint_Operation";
    public static final String ENTITY_NAME = "ManufacturingMaintenanceTask";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CAMPARENT = "camParent";
    public static final String PROPERTY_MANUFACTURINGMAINTENANCELIST = "manufacturingMaintenanceList";
    public static final String PROPERTY_MANUFACTURINGMAINTENANCESCHEDULELIST = "manufacturingMaintenanceScheduleList";
    public static final String PROPERTY_MANUFACTURINGMAINTENANCETASKEMCAMPARENTIDLIST = "manufacturingMaintenanceTaskEmCamParentIdList";
    public static final String PROPERTY_CAMWORKORDERLIST = "camWorkorderList";
    public static final String PROPERTY_CAMWORKORDERASSETLIST = "camWorkorderassetList";

    public Task() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MANUFACTURINGMAINTENANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMAINTENANCESCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMAINTENANCETASKEMCAMPARENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CAMWORKORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CAMWORKORDERASSETLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Task getCamParent() {
        return (Task) get(PROPERTY_CAMPARENT);
    }

    public void setCamParent(Task camParent) {
        set(PROPERTY_CAMPARENT, camParent);
    }

    @SuppressWarnings("unchecked")
    public List<Maintenance> getManufacturingMaintenanceList() {
      return (List<Maintenance>) get(PROPERTY_MANUFACTURINGMAINTENANCELIST);
    }

    public void setManufacturingMaintenanceList(List<Maintenance> manufacturingMaintenanceList) {
        set(PROPERTY_MANUFACTURINGMAINTENANCELIST, manufacturingMaintenanceList);
    }

    @SuppressWarnings("unchecked")
    public List<MaintenanceSchedule> getManufacturingMaintenanceScheduleList() {
      return (List<MaintenanceSchedule>) get(PROPERTY_MANUFACTURINGMAINTENANCESCHEDULELIST);
    }

    public void setManufacturingMaintenanceScheduleList(List<MaintenanceSchedule> manufacturingMaintenanceScheduleList) {
        set(PROPERTY_MANUFACTURINGMAINTENANCESCHEDULELIST, manufacturingMaintenanceScheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<Task> getManufacturingMaintenanceTaskEmCamParentIdList() {
      return (List<Task>) get(PROPERTY_MANUFACTURINGMAINTENANCETASKEMCAMPARENTIDLIST);
    }

    public void setManufacturingMaintenanceTaskEmCamParentIdList(List<Task> manufacturingMaintenanceTaskEmCamParentIdList) {
        set(PROPERTY_MANUFACTURINGMAINTENANCETASKEMCAMPARENTIDLIST, manufacturingMaintenanceTaskEmCamParentIdList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkOrder> getCamWorkorderList() {
      return (List<WorkOrder>) get(PROPERTY_CAMWORKORDERLIST);
    }

    public void setCamWorkorderList(List<WorkOrder> camWorkorderList) {
        set(PROPERTY_CAMWORKORDERLIST, camWorkorderList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkOrderAsset> getCamWorkorderassetList() {
      return (List<WorkOrderAsset>) get(PROPERTY_CAMWORKORDERASSETLIST);
    }

    public void setCamWorkorderassetList(List<WorkOrderAsset> camWorkorderassetList) {
        set(PROPERTY_CAMWORKORDERASSETLIST, camWorkorderassetList);
    }

}
