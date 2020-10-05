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
package org.wirabumi.hris.employee.master.data;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.Location;
/**
 * Entity class for entity hris_pengdata_kel (stored in table hris_pengdata_kel).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_pengdata_kel extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_pengdata_kel";
    public static final String ENTITY_NAME = "hris_pengdata_kel";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_BIRTHDAY = "birthday";
    public static final String PROPERTY_RELATION = "relation";
    public static final String PROPERTY_MARITALSTATUS = "maritalStatus";
    public static final String PROPERTY_PHONE = "phone";
    public static final String PROPERTY_ALTERNATIVEPHONE = "alternativePhone";
    public static final String PROPERTY_INSURED = "insured";
    public static final String PROPERTY_EMERGENCYCONTACT = "emergencyContact";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_CREATEDATA = "createdata";
    public static final String PROPERTY_HRISCONTACTFK = "hrisContactFk";

    public hris_pengdata_kel() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_INSURED, true);
        setDefaultValue(PROPERTY_EMERGENCYCONTACT, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_CREATEDATA, false);
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public Location getLocationAddress() {
        return (Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public Date getBirthday() {
        return (Date) get(PROPERTY_BIRTHDAY);
    }

    public void setBirthday(Date birthday) {
        set(PROPERTY_BIRTHDAY, birthday);
    }

    public String getRelation() {
        return (String) get(PROPERTY_RELATION);
    }

    public void setRelation(String relation) {
        set(PROPERTY_RELATION, relation);
    }

    public String getMaritalStatus() {
        return (String) get(PROPERTY_MARITALSTATUS);
    }

    public void setMaritalStatus(String maritalStatus) {
        set(PROPERTY_MARITALSTATUS, maritalStatus);
    }

    public String getPhone() {
        return (String) get(PROPERTY_PHONE);
    }

    public void setPhone(String phone) {
        set(PROPERTY_PHONE, phone);
    }

    public String getAlternativePhone() {
        return (String) get(PROPERTY_ALTERNATIVEPHONE);
    }

    public void setAlternativePhone(String alternativePhone) {
        set(PROPERTY_ALTERNATIVEPHONE, alternativePhone);
    }

    public Boolean isInsured() {
        return (Boolean) get(PROPERTY_INSURED);
    }

    public void setInsured(Boolean insured) {
        set(PROPERTY_INSURED, insured);
    }

    public Boolean isEmergencyContact() {
        return (Boolean) get(PROPERTY_EMERGENCYCONTACT);
    }

    public void setEmergencyContact(Boolean emergencyContact) {
        set(PROPERTY_EMERGENCYCONTACT, emergencyContact);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Boolean isCreatedata() {
        return (Boolean) get(PROPERTY_CREATEDATA);
    }

    public void setCreatedata(Boolean createdata) {
        set(PROPERTY_CREATEDATA, createdata);
    }

    public hris_contact getHrisContactFk() {
        return (hris_contact) get(PROPERTY_HRISCONTACTFK);
    }

    public void setHrisContactFk(hris_contact hrisContactFk) {
        set(PROPERTY_HRISCONTACTFK, hrisContactFk);
    }

}
