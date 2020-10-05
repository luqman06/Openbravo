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
 * Entity class for entity HRIS_C_Bp_Department (stored in table HRIS_C_Bp_Department).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class HRIS_C_Bp_Department extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "HRIS_C_Bp_Department";
    public static final String ENTITY_NAME = "HRIS_C_Bp_Department";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_DEPARTMENTTYPE = "departmentType";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_BUSINESSPARTNEREMHRISCBPDEPARTMENTIDLIST = "businessPartnerEMHrisCBpDepartmentIDList";
    public static final String PROPERTY_HRISCBPEMPINFOLIST = "hRISCBpEmpinfoList";
    public static final String PROPERTY_HRISETLINEOLDDEPARTMENTIDLIST = "hrisEtLineOLDDepartmentIDList";
    public static final String PROPERTY_HRISETLINENEWDEPARTMENTIDLIST = "hrisEtLineNEWDepartmentIDList";

    public HRIS_C_Bp_Department() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNEREMHRISCBPDEPARTMENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEMPINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINEOLDDEPARTMENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINENEWDEPARTMENTIDLIST, new ArrayList<Object>());
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

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getDepartmentType() {
        return (String) get(PROPERTY_DEPARTMENTTYPE);
    }

    public void setDepartmentType(String departmentType) {
        set(PROPERTY_DEPARTMENTTYPE, departmentType);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerEMHrisCBpDepartmentIDList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNEREMHRISCBPDEPARTMENTIDLIST);
    }

    public void setBusinessPartnerEMHrisCBpDepartmentIDList(List<BusinessPartner> businessPartnerEMHrisCBpDepartmentIDList) {
        set(PROPERTY_BUSINESSPARTNEREMHRISCBPDEPARTMENTIDLIST, businessPartnerEMHrisCBpDepartmentIDList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Empinfo> getHRISCBpEmpinfoList() {
      return (List<HRIS_C_Bp_Empinfo>) get(PROPERTY_HRISCBPEMPINFOLIST);
    }

    public void setHRISCBpEmpinfoList(List<HRIS_C_Bp_Empinfo> hRISCBpEmpinfoList) {
        set(PROPERTY_HRISCBPEMPINFOLIST, hRISCBpEmpinfoList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineOLDDepartmentIDList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINEOLDDEPARTMENTIDLIST);
    }

    public void setHrisEtLineOLDDepartmentIDList(List<EmployeeTransferLine> hrisEtLineOLDDepartmentIDList) {
        set(PROPERTY_HRISETLINEOLDDEPARTMENTIDLIST, hrisEtLineOLDDepartmentIDList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineNEWDepartmentIDList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINENEWDEPARTMENTIDLIST);
    }

    public void setHrisEtLineNEWDepartmentIDList(List<EmployeeTransferLine> hrisEtLineNEWDepartmentIDList) {
        set(PROPERTY_HRISETLINENEWDEPARTMENTIDLIST, hrisEtLineNEWDepartmentIDList);
    }

}
