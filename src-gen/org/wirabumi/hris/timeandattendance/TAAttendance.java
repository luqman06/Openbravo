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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.wirabumi.hris.overtime.ot_emergency_call;
import org.wirabumi.hris.overtime.ot_overtime;
/**
 * Entity class for entity TA_Attendance (stored in table TA_Attendance).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class TAAttendance extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "TA_Attendance";
    public static final String ENTITY_NAME = "TA_Attendance";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_SHIFT = "shift";
    public static final String PROPERTY_CHECKIN = "checkin";
    public static final String PROPERTY_CHECKOUT = "checkout";
    public static final String PROPERTY_CHECKINSTATUS = "checkinStatus";
    public static final String PROPERTY_CHECKOUTSTATUS = "checkoutStatus";
    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_LATE = "late";
    public static final String PROPERTY_EARLY = "early";
    public static final String PROPERTY_ATTANDANCEDATE = "attandanceDate";
    public static final String PROPERTY_OTEMERGENCYCALLLIST = "otEmergencyCallList";
    public static final String PROPERTY_OTOVERTIMELIST = "otOvertimeList";
    public static final String PROPERTY_TAIATTENDANCELIST = "taIAttendanceList";

    public TAAttendance() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OTEMERGENCYCALLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OTOVERTIMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIATTENDANCELIST, new ArrayList<Object>());
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

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public TAShift getShift() {
        return (TAShift) get(PROPERTY_SHIFT);
    }

    public void setShift(TAShift shift) {
        set(PROPERTY_SHIFT, shift);
    }

    public Date getCheckin() {
        return (Date) get(PROPERTY_CHECKIN);
    }

    public void setCheckin(Date checkin) {
        set(PROPERTY_CHECKIN, checkin);
    }

    public Date getCheckout() {
        return (Date) get(PROPERTY_CHECKOUT);
    }

    public void setCheckout(Date checkout) {
        set(PROPERTY_CHECKOUT, checkout);
    }

    public String getCheckinStatus() {
        return (String) get(PROPERTY_CHECKINSTATUS);
    }

    public void setCheckinStatus(String checkinStatus) {
        set(PROPERTY_CHECKINSTATUS, checkinStatus);
    }

    public String getCheckoutStatus() {
        return (String) get(PROPERTY_CHECKOUTSTATUS);
    }

    public void setCheckoutStatus(String checkoutStatus) {
        set(PROPERTY_CHECKOUTSTATUS, checkoutStatus);
    }

    public String getDuration() {
        return (String) get(PROPERTY_DURATION);
    }

    public void setDuration(String duration) {
        set(PROPERTY_DURATION, duration);
    }

    public Timestamp getLate() {
        return (Timestamp) get(PROPERTY_LATE);
    }

    public void setLate(Timestamp late) {
        set(PROPERTY_LATE, late);
    }

    public Timestamp getEarly() {
        return (Timestamp) get(PROPERTY_EARLY);
    }

    public void setEarly(Timestamp early) {
        set(PROPERTY_EARLY, early);
    }

    public Date getAttandanceDate() {
        return (Date) get(PROPERTY_ATTANDANCEDATE);
    }

    public void setAttandanceDate(Date attandanceDate) {
        set(PROPERTY_ATTANDANCEDATE, attandanceDate);
    }

    @SuppressWarnings("unchecked")
    public List<ot_emergency_call> getOtEmergencyCallList() {
      return (List<ot_emergency_call>) get(PROPERTY_OTEMERGENCYCALLLIST);
    }

    public void setOtEmergencyCallList(List<ot_emergency_call> otEmergencyCallList) {
        set(PROPERTY_OTEMERGENCYCALLLIST, otEmergencyCallList);
    }

    @SuppressWarnings("unchecked")
    public List<ot_overtime> getOtOvertimeList() {
      return (List<ot_overtime>) get(PROPERTY_OTOVERTIMELIST);
    }

    public void setOtOvertimeList(List<ot_overtime> otOvertimeList) {
        set(PROPERTY_OTOVERTIMELIST, otOvertimeList);
    }

    @SuppressWarnings("unchecked")
    public List<TA_ImportAttendance> getTaIAttendanceList() {
      return (List<TA_ImportAttendance>) get(PROPERTY_TAIATTENDANCELIST);
    }

    public void setTaIAttendanceList(List<TA_ImportAttendance> taIAttendanceList) {
        set(PROPERTY_TAIATTENDANCELIST, taIAttendanceList);
    }

}
