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

import java.sql.Timestamp;
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
 * Entity class for entity TA_ShiftLine (stored in table TA_ShiftLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class TA_ShiftLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "TA_ShiftLine";
    public static final String ENTITY_NAME = "TA_ShiftLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_ISOFF = "isoff";
    public static final String PROPERTY_CHECKIN = "checkIn";
    public static final String PROPERTY_CHECKOUT = "checkOut";
    public static final String PROPERTY_SHIFT = "shift";
    public static final String PROPERTY_GRAYAREACHECKIN = "grayareaCheckin";
    public static final String PROPERTY_GRAYAREACHECKOUT = "grayareaCheckout";
    public static final String PROPERTY_GRAYAREABEFORE = "grayareaBefore";
    public static final String PROPERTY_GRAYAREAAFTER = "grayareaAfter";
    public static final String PROPERTY_TIMEMASTER = "timemaster";

    public TA_ShiftLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISOFF, false);
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

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Boolean isOff() {
        return (Boolean) get(PROPERTY_ISOFF);
    }

    public void setOff(Boolean isoff) {
        set(PROPERTY_ISOFF, isoff);
    }

    public Timestamp getCheckIn() {
        return (Timestamp) get(PROPERTY_CHECKIN);
    }

    public void setCheckIn(Timestamp checkIn) {
        set(PROPERTY_CHECKIN, checkIn);
    }

    public Timestamp getCheckOut() {
        return (Timestamp) get(PROPERTY_CHECKOUT);
    }

    public void setCheckOut(Timestamp checkOut) {
        set(PROPERTY_CHECKOUT, checkOut);
    }

    public TAShift getShift() {
        return (TAShift) get(PROPERTY_SHIFT);
    }

    public void setShift(TAShift shift) {
        set(PROPERTY_SHIFT, shift);
    }

    public Timestamp getGrayareaCheckin() {
        return (Timestamp) get(PROPERTY_GRAYAREACHECKIN);
    }

    public void setGrayareaCheckin(Timestamp grayareaCheckin) {
        set(PROPERTY_GRAYAREACHECKIN, grayareaCheckin);
    }

    public Timestamp getGrayareaCheckout() {
        return (Timestamp) get(PROPERTY_GRAYAREACHECKOUT);
    }

    public void setGrayareaCheckout(Timestamp grayareaCheckout) {
        set(PROPERTY_GRAYAREACHECKOUT, grayareaCheckout);
    }

    public Timestamp getGrayareaBefore() {
        return (Timestamp) get(PROPERTY_GRAYAREABEFORE);
    }

    public void setGrayareaBefore(Timestamp grayareaBefore) {
        set(PROPERTY_GRAYAREABEFORE, grayareaBefore);
    }

    public Timestamp getGrayareaAfter() {
        return (Timestamp) get(PROPERTY_GRAYAREAAFTER);
    }

    public void setGrayareaAfter(Timestamp grayareaAfter) {
        set(PROPERTY_GRAYAREAAFTER, grayareaAfter);
    }

    public LabourTimeMaster getTimemaster() {
        return (LabourTimeMaster) get(PROPERTY_TIMEMASTER);
    }

    public void setTimemaster(LabourTimeMaster timemaster) {
        set(PROPERTY_TIMEMASTER, timemaster);
    }

}
