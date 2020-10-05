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
 * Entity class for entity ta_i_shift (stored in table ta_i_shift).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class TA_ImportShift extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "ta_i_shift";
    public static final String ENTITY_NAME = "ta_i_shift";
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
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_VALIDTODATE = "validToDate";
    public static final String PROPERTY_SHIFTMODEKEY = "shiftModeKey";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_IMPORTSHIFT = "importShift";
    public static final String PROPERTY_IMPORTED = "imported";
    public static final String PROPERTY_ISOFF = "isoff";
    public static final String PROPERTY_CHECKIN = "checkIn";
    public static final String PROPERTY_CHECKOUT = "checkOut";
    public static final String PROPERTY_GRAYAREACHECKIN = "grayAreaCheckIn";
    public static final String PROPERTY_GRAYAREACHECKOUT = "grayAreaCheckOut";
    public static final String PROPERTY_GRAYAREABEFORE = "grayAreaBefore";
    public static final String PROPERTY_GRAYAREAAFTER = "grayAreaAfter";
    public static final String PROPERTY_TIMEMASTERNAME = "timemasterName";

    public TA_ImportShift() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_IMPORTSHIFT, false);
        setDefaultValue(PROPERTY_IMPORTED, false);
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

    public String getShiftModeKey() {
        return (String) get(PROPERTY_SHIFTMODEKEY);
    }

    public void setShiftModeKey(String shiftModeKey) {
        set(PROPERTY_SHIFTMODEKEY, shiftModeKey);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isImportShift() {
        return (Boolean) get(PROPERTY_IMPORTSHIFT);
    }

    public void setImportShift(Boolean importShift) {
        set(PROPERTY_IMPORTSHIFT, importShift);
    }

    public Boolean isImported() {
        return (Boolean) get(PROPERTY_IMPORTED);
    }

    public void setImported(Boolean imported) {
        set(PROPERTY_IMPORTED, imported);
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

    public Timestamp getGrayAreaCheckIn() {
        return (Timestamp) get(PROPERTY_GRAYAREACHECKIN);
    }

    public void setGrayAreaCheckIn(Timestamp grayAreaCheckIn) {
        set(PROPERTY_GRAYAREACHECKIN, grayAreaCheckIn);
    }

    public Timestamp getGrayAreaCheckOut() {
        return (Timestamp) get(PROPERTY_GRAYAREACHECKOUT);
    }

    public void setGrayAreaCheckOut(Timestamp grayAreaCheckOut) {
        set(PROPERTY_GRAYAREACHECKOUT, grayAreaCheckOut);
    }

    public Timestamp getGrayAreaBefore() {
        return (Timestamp) get(PROPERTY_GRAYAREABEFORE);
    }

    public void setGrayAreaBefore(Timestamp grayAreaBefore) {
        set(PROPERTY_GRAYAREABEFORE, grayAreaBefore);
    }

    public Timestamp getGrayAreaAfter() {
        return (Timestamp) get(PROPERTY_GRAYAREAAFTER);
    }

    public void setGrayAreaAfter(Timestamp grayAreaAfter) {
        set(PROPERTY_GRAYAREAAFTER, grayAreaAfter);
    }

    public String getTimemasterName() {
        return (String) get(PROPERTY_TIMEMASTERNAME);
    }

    public void setTimemasterName(String timemasterName) {
        set(PROPERTY_TIMEMASTERNAME, timemasterName);
    }

}
