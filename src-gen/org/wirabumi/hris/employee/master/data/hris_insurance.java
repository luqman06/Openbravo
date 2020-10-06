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
/**
 * Entity class for entity hris_insurance (stored in table hris_insurance).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_insurance extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_insurance";
    public static final String ENTITY_NAME = "hris_insurance";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_INSURANCEPROVIDER = "insuranceProvider";
    public static final String PROPERTY_HRISINSURANCEEARNINGLIST = "hrisInsuranceEarningList";
    public static final String PROPERTY_HRISINSURANCEEMPLOYEELIST = "hrisInsuranceEmployeeList";
    public static final String PROPERTY_HRISINSURANCEPREMIUMLIST = "hrisInsurancePremiumList";

    public hris_insurance() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_HRISINSURANCEEARNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISINSURANCEEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISINSURANCEPREMIUMLIST, new ArrayList<Object>());
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

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public BusinessPartner getInsuranceProvider() {
        return (BusinessPartner) get(PROPERTY_INSURANCEPROVIDER);
    }

    public void setInsuranceProvider(BusinessPartner insuranceProvider) {
        set(PROPERTY_INSURANCEPROVIDER, insuranceProvider);
    }

    @SuppressWarnings("unchecked")
    public List<hris_insurance_earning> getHrisInsuranceEarningList() {
      return (List<hris_insurance_earning>) get(PROPERTY_HRISINSURANCEEARNINGLIST);
    }

    public void setHrisInsuranceEarningList(List<hris_insurance_earning> hrisInsuranceEarningList) {
        set(PROPERTY_HRISINSURANCEEARNINGLIST, hrisInsuranceEarningList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_insurance_employee> getHrisInsuranceEmployeeList() {
      return (List<hris_insurance_employee>) get(PROPERTY_HRISINSURANCEEMPLOYEELIST);
    }

    public void setHrisInsuranceEmployeeList(List<hris_insurance_employee> hrisInsuranceEmployeeList) {
        set(PROPERTY_HRISINSURANCEEMPLOYEELIST, hrisInsuranceEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_insurance_premium> getHrisInsurancePremiumList() {
      return (List<hris_insurance_premium>) get(PROPERTY_HRISINSURANCEPREMIUMLIST);
    }

    public void setHrisInsurancePremiumList(List<hris_insurance_premium> hrisInsurancePremiumList) {
        set(PROPERTY_HRISINSURANCEPREMIUMLIST, hrisInsurancePremiumList);
    }

}