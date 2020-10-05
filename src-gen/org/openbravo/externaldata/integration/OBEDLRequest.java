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
/**
 * Entity class for entity OBEDL_Request (stored in table OBEDL_Request).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OBEDLRequest extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBEDL_Request";
    public static final String ENTITY_NAME = "OBEDL_Request";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_EXECUTIONDATE = "executionDate";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_EDLPROCESS = "eDLProcess";
    public static final String PROPERTY_REQUESTDATA = "requestData";
    public static final String PROPERTY_RESPONSE = "response";
    public static final String PROPERTY_REQUESTDATE = "requestDate";
    public static final String PROPERTY_CANCEL = "cancel";
    public static final String PROPERTY_CONTEXTDATA = "contextData";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_EXECUTIONMODE = "executionMode";
    public static final String PROPERTY_OBEDLOUTPUTCONTENTLIST = "oBEDLOutputContentList";
    public static final String PROPERTY_OBEDLREQUESTLINELIST = "oBEDLRequestLineList";

    public OBEDLRequest() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_CANCEL, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_OBEDLOUTPUTCONTENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBEDLREQUESTLINELIST, new ArrayList<Object>());
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

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Date getExecutionDate() {
        return (Date) get(PROPERTY_EXECUTIONDATE);
    }

    public void setExecutionDate(Date executionDate) {
        set(PROPERTY_EXECUTIONDATE, executionDate);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public OBEDLProcess getEDLProcess() {
        return (OBEDLProcess) get(PROPERTY_EDLPROCESS);
    }

    public void setEDLProcess(OBEDLProcess eDLProcess) {
        set(PROPERTY_EDLPROCESS, eDLProcess);
    }

    public String getRequestData() {
        return (String) get(PROPERTY_REQUESTDATA);
    }

    public void setRequestData(String requestData) {
        set(PROPERTY_REQUESTDATA, requestData);
    }

    public String getResponse() {
        return (String) get(PROPERTY_RESPONSE);
    }

    public void setResponse(String response) {
        set(PROPERTY_RESPONSE, response);
    }

    public Date getRequestDate() {
        return (Date) get(PROPERTY_REQUESTDATE);
    }

    public void setRequestDate(Date requestDate) {
        set(PROPERTY_REQUESTDATE, requestDate);
    }

    public Boolean isCancel() {
        return (Boolean) get(PROPERTY_CANCEL);
    }

    public void setCancel(Boolean cancel) {
        set(PROPERTY_CANCEL, cancel);
    }

    public String getContextData() {
        return (String) get(PROPERTY_CONTEXTDATA);
    }

    public void setContextData(String contextData) {
        set(PROPERTY_CONTEXTDATA, contextData);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getExecutionMode() {
        return (String) get(PROPERTY_EXECUTIONMODE);
    }

    public void setExecutionMode(String executionMode) {
        set(PROPERTY_EXECUTIONMODE, executionMode);
    }

    @SuppressWarnings("unchecked")
    public List<OBEDLOutputContent> getOBEDLOutputContentList() {
      return (List<OBEDLOutputContent>) get(PROPERTY_OBEDLOUTPUTCONTENTLIST);
    }

    public void setOBEDLOutputContentList(List<OBEDLOutputContent> oBEDLOutputContentList) {
        set(PROPERTY_OBEDLOUTPUTCONTENTLIST, oBEDLOutputContentList);
    }

    @SuppressWarnings("unchecked")
    public List<OBEDLRequestLine> getOBEDLRequestLineList() {
      return (List<OBEDLRequestLine>) get(PROPERTY_OBEDLREQUESTLINELIST);
    }

    public void setOBEDLRequestLineList(List<OBEDLRequestLine> oBEDLRequestLineList) {
        set(PROPERTY_OBEDLREQUESTLINELIST, oBEDLRequestLineList);
    }

}
