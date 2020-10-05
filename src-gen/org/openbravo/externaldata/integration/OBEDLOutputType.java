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
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity OBEDL_Output_Type (stored in table OBEDL_Output_Type).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OBEDLOutputType extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBEDL_Output_Type";
    public static final String ENTITY_NAME = "OBEDL_Output_Type";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_REQUIRESPATH = "requiresPath";
    public static final String PROPERTY_REQUIRESUSER = "requiresUser";
    public static final String PROPERTY_REQUIRESPASSWORD = "requiresPassword";
    public static final String PROPERTY_REQUIRESFILENAME = "requiresFilename";
    public static final String PROPERTY_MODULE = "module";
    public static final String PROPERTY_REQUIRESWEBSERVICEMETHOD = "requiresWebServiceMethod";
    public static final String PROPERTY_OBEDLCONFIGOUTPUTLIST = "oBEDLConfigOutputList";

    public OBEDLOutputType() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_REQUIRESPATH, false);
        setDefaultValue(PROPERTY_REQUIRESUSER, false);
        setDefaultValue(PROPERTY_REQUIRESPASSWORD, false);
        setDefaultValue(PROPERTY_REQUIRESFILENAME, false);
        setDefaultValue(PROPERTY_REQUIRESWEBSERVICEMETHOD, false);
        setDefaultValue(PROPERTY_OBEDLCONFIGOUTPUTLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public Boolean isRequiresPath() {
        return (Boolean) get(PROPERTY_REQUIRESPATH);
    }

    public void setRequiresPath(Boolean requiresPath) {
        set(PROPERTY_REQUIRESPATH, requiresPath);
    }

    public Boolean isRequiresUser() {
        return (Boolean) get(PROPERTY_REQUIRESUSER);
    }

    public void setRequiresUser(Boolean requiresUser) {
        set(PROPERTY_REQUIRESUSER, requiresUser);
    }

    public Boolean isRequiresPassword() {
        return (Boolean) get(PROPERTY_REQUIRESPASSWORD);
    }

    public void setRequiresPassword(Boolean requiresPassword) {
        set(PROPERTY_REQUIRESPASSWORD, requiresPassword);
    }

    public Boolean isRequiresFilename() {
        return (Boolean) get(PROPERTY_REQUIRESFILENAME);
    }

    public void setRequiresFilename(Boolean requiresFilename) {
        set(PROPERTY_REQUIRESFILENAME, requiresFilename);
    }

    public Module getModule() {
        return (Module) get(PROPERTY_MODULE);
    }

    public void setModule(Module module) {
        set(PROPERTY_MODULE, module);
    }

    public Boolean isRequiresWebServiceMethod() {
        return (Boolean) get(PROPERTY_REQUIRESWEBSERVICEMETHOD);
    }

    public void setRequiresWebServiceMethod(Boolean requiresWebServiceMethod) {
        set(PROPERTY_REQUIRESWEBSERVICEMETHOD, requiresWebServiceMethod);
    }

    @SuppressWarnings("unchecked")
    public List<OBEDLConfigOutput> getOBEDLConfigOutputList() {
      return (List<OBEDLConfigOutput>) get(PROPERTY_OBEDLCONFIGOUTPUTLIST);
    }

    public void setOBEDLConfigOutputList(List<OBEDLConfigOutput> oBEDLConfigOutputList) {
        set(PROPERTY_OBEDLCONFIGOUTPUTLIST, oBEDLConfigOutputList);
    }

}
