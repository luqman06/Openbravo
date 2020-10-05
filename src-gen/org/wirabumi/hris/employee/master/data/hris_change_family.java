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
/**
 * Entity class for entity hris_change_family (stored in table hris_change_family).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_change_family extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_change_family";
    public static final String ENTITY_NAME = "hris_change_family";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_HRISCONTACT = "hrisContact";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_DATEMARRIED = "dateMarried";
    public static final String PROPERTY_NOAKTA = "noakta";
    public static final String PROPERTY_DATEAKTA = "dateAkta";
    public static final String PROPERTY_TAXMARITALSTATUSNEW = "taxmaritalstatusNew";
    public static final String PROPERTY_TAXMARITALSTATUSOLD = "taxmaritalstatusOld";
    public static final String PROPERTY_MARITALSTATUS = "maritalStatus";
    public static final String PROPERTY_STATUSDEAD = "statusdead";
    public static final String PROPERTY_EDUCATIONSTATUS = "educationStatus";
    public static final String PROPERTY_CREATEDATA = "createData";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";

    public hris_change_family() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATEDATA, false);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_DOCUMENTACTION, "AP");
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

    public hris_contact getHrisContact() {
        return (hris_contact) get(PROPERTY_HRISCONTACT);
    }

    public void setHrisContact(hris_contact hrisContact) {
        set(PROPERTY_HRISCONTACT, hrisContact);
    }

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Date getDateMarried() {
        return (Date) get(PROPERTY_DATEMARRIED);
    }

    public void setDateMarried(Date dateMarried) {
        set(PROPERTY_DATEMARRIED, dateMarried);
    }

    public String getNoakta() {
        return (String) get(PROPERTY_NOAKTA);
    }

    public void setNoakta(String noakta) {
        set(PROPERTY_NOAKTA, noakta);
    }

    public Date getDateAkta() {
        return (Date) get(PROPERTY_DATEAKTA);
    }

    public void setDateAkta(Date dateAkta) {
        set(PROPERTY_DATEAKTA, dateAkta);
    }

    public String getTaxmaritalstatusNew() {
        return (String) get(PROPERTY_TAXMARITALSTATUSNEW);
    }

    public void setTaxmaritalstatusNew(String taxmaritalstatusNew) {
        set(PROPERTY_TAXMARITALSTATUSNEW, taxmaritalstatusNew);
    }

    public String getTaxmaritalstatusOld() {
        return (String) get(PROPERTY_TAXMARITALSTATUSOLD);
    }

    public void setTaxmaritalstatusOld(String taxmaritalstatusOld) {
        set(PROPERTY_TAXMARITALSTATUSOLD, taxmaritalstatusOld);
    }

    public String getMaritalStatus() {
        return (String) get(PROPERTY_MARITALSTATUS);
    }

    public void setMaritalStatus(String maritalStatus) {
        set(PROPERTY_MARITALSTATUS, maritalStatus);
    }

    public String getStatusdead() {
        return (String) get(PROPERTY_STATUSDEAD);
    }

    public void setStatusdead(String statusdead) {
        set(PROPERTY_STATUSDEAD, statusdead);
    }

    public String getEducationStatus() {
        return (String) get(PROPERTY_EDUCATIONSTATUS);
    }

    public void setEducationStatus(String educationStatus) {
        set(PROPERTY_EDUCATIONSTATUS, educationStatus);
    }

    public Boolean isCreateData() {
        return (Boolean) get(PROPERTY_CREATEDATA);
    }

    public void setCreateData(Boolean createData) {
        set(PROPERTY_CREATEDATA, createData);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

}
