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
package org.wirabumi.hris.leave;

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
/**
 * Entity class for entity lv_set_leave (stored in table lv_set_leave).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class lv_set_leave extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "lv_set_leave";
    public static final String ENTITY_NAME = "lv_set_leave";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_PLAFON = "plafon";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_VALIDTODATE = "validToDate";
    public static final String PROPERTY_PROPOSALNAME = "proposalName";
    public static final String PROPERTY_LVCBPLEAVELIST = "lvCBpLeaveList";
    public static final String PROPERTY_LVMASSLEAVELIST = "lvMassLeaveList";

    public lv_set_leave() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_LVCBPLEAVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LVMASSLEAVELIST, new ArrayList<Object>());
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

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public Long getPlafon() {
        return (Long) get(PROPERTY_PLAFON);
    }

    public void setPlafon(Long plafon) {
        set(PROPERTY_PLAFON, plafon);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public Date getValidToDate() {
        return (Date) get(PROPERTY_VALIDTODATE);
    }

    public void setValidToDate(Date validToDate) {
        set(PROPERTY_VALIDTODATE, validToDate);
    }

    public String getProposalName() {
        return (String) get(PROPERTY_PROPOSALNAME);
    }

    public void setProposalName(String proposalName) {
        set(PROPERTY_PROPOSALNAME, proposalName);
    }

    @SuppressWarnings("unchecked")
    public List<lv_c_bp_leave> getLvCBpLeaveList() {
      return (List<lv_c_bp_leave>) get(PROPERTY_LVCBPLEAVELIST);
    }

    public void setLvCBpLeaveList(List<lv_c_bp_leave> lvCBpLeaveList) {
        set(PROPERTY_LVCBPLEAVELIST, lvCBpLeaveList);
    }

    @SuppressWarnings("unchecked")
    public List<lv_mass_leave> getLvMassLeaveList() {
      return (List<lv_mass_leave>) get(PROPERTY_LVMASSLEAVELIST);
    }

    public void setLvMassLeaveList(List<lv_mass_leave> lvMassLeaveList) {
        set(PROPERTY_LVMASSLEAVELIST, lvMassLeaveList);
    }

}
