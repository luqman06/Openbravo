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
package org.openbravo.externaldata.integration;

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
import org.openbravo.service.importprocess.ImportEntry;
import org.openbravo.service.importprocess.ImportEntryArchive;
/**
 * Entity class for entity OBEDL_Request_Line (stored in table OBEDL_Request_Line).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OBEDLRequestLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBEDL_Request_Line";
    public static final String ENTITY_NAME = "OBEDL_Request_Line";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EDLREQUEST = "eDLRequest";
    public static final String PROPERTY_IMPORTENTRY = "importEntry";
    public static final String PROPERTY_ARCHIVEIMPORTENTRY = "archiveImportEntry";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_LINEDATA = "linedata";
    public static final String PROPERTY_ERRORMSG = "errorMsg";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_OBEDLOUTPUTCONTENTLIST = "oBEDLOutputContentList";

    public OBEDLRequestLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_OBEDLOUTPUTCONTENTLIST, new ArrayList<Object>());
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

    public OBEDLRequest getEDLRequest() {
        return (OBEDLRequest) get(PROPERTY_EDLREQUEST);
    }

    public void setEDLRequest(OBEDLRequest eDLRequest) {
        set(PROPERTY_EDLREQUEST, eDLRequest);
    }

    public ImportEntry getImportEntry() {
        return (ImportEntry) get(PROPERTY_IMPORTENTRY);
    }

    public void setImportEntry(ImportEntry importEntry) {
        set(PROPERTY_IMPORTENTRY, importEntry);
    }

    public ImportEntryArchive getArchiveImportEntry() {
        return (ImportEntryArchive) get(PROPERTY_ARCHIVEIMPORTENTRY);
    }

    public void setArchiveImportEntry(ImportEntryArchive archiveImportEntry) {
        set(PROPERTY_ARCHIVEIMPORTENTRY, archiveImportEntry);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public String getLinedata() {
        return (String) get(PROPERTY_LINEDATA);
    }

    public void setLinedata(String linedata) {
        set(PROPERTY_LINEDATA, linedata);
    }

    public String getErrorMsg() {
        return (String) get(PROPERTY_ERRORMSG);
    }

    public void setErrorMsg(String errorMsg) {
        set(PROPERTY_ERRORMSG, errorMsg);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    @SuppressWarnings("unchecked")
    public List<OBEDLOutputContent> getOBEDLOutputContentList() {
      return (List<OBEDLOutputContent>) get(PROPERTY_OBEDLOUTPUTCONTENTLIST);
    }

    public void setOBEDLOutputContentList(List<OBEDLOutputContent> oBEDLOutputContentList) {
        set(PROPERTY_OBEDLOUTPUTCONTENTLIST, oBEDLOutputContentList);
    }

}
