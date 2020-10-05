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
package org.wirabumi.hris.timeandattendance;

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
 * Entity class for entity TA_Shift (stored in table TA_Shift).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class TAShift extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "TA_Shift";
    public static final String ENTITY_NAME = "TA_Shift";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_VALIDTODATE = "validToDate";
    public static final String PROPERTY_TAATTENDANCELIST = "tAAttendanceList";
    public static final String PROPERTY_TASHIFTLINELIST = "tAShiftLineList";
    public static final String PROPERTY_TACBPSHIFTLIST = "taCBpShiftList";
    public static final String PROPERTY_TATUKARSHIFTLIST = "taTukarShiftList";

    public TAShift() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TAATTENDANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TASHIFTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TACBPSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TATUKARSHIFTLIST, new ArrayList<Object>());
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

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
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

    @SuppressWarnings("unchecked")
    public List<TAAttendance> getTAAttendanceList() {
      return (List<TAAttendance>) get(PROPERTY_TAATTENDANCELIST);
    }

    public void setTAAttendanceList(List<TAAttendance> tAAttendanceList) {
        set(PROPERTY_TAATTENDANCELIST, tAAttendanceList);
    }

    @SuppressWarnings("unchecked")
    public List<TA_ShiftLine> getTAShiftLineList() {
      return (List<TA_ShiftLine>) get(PROPERTY_TASHIFTLINELIST);
    }

    public void setTAShiftLineList(List<TA_ShiftLine> tAShiftLineList) {
        set(PROPERTY_TASHIFTLINELIST, tAShiftLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_c_bp_shift> getTaCBpShiftList() {
      return (List<ta_c_bp_shift>) get(PROPERTY_TACBPSHIFTLIST);
    }

    public void setTaCBpShiftList(List<ta_c_bp_shift> taCBpShiftList) {
        set(PROPERTY_TACBPSHIFTLIST, taCBpShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_tukar_shift> getTaTukarShiftList() {
      return (List<ta_tukar_shift>) get(PROPERTY_TATUKARSHIFTLIST);
    }

    public void setTaTukarShiftList(List<ta_tukar_shift> taTukarShiftList) {
        set(PROPERTY_TATUKARSHIFTLIST, taTukarShiftList);
    }

}
