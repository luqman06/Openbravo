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
import org.wirabumi.hris.payroll.pyr_deduction_param;
import org.wirabumi.hris.payroll.pyr_earning_param;
import org.wirabumi.hris.payroll.pyr_sp_employee;
/**
 * Entity class for entity hris_site (stored in table hris_site).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_site extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_site";
    public static final String ENTITY_NAME = "hris_site";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_PARENTSITE = "parentSite";
    public static final String PROPERTY_BUSINESSPARTNEREMHRISSITELIST = "businessPartnerEMHrisSiteList";
    public static final String PROPERTY_HRISCBPEMPINFOSITELIST = "hRISCBpEmpinfoSiteList";
    public static final String PROPERTY_HRISETLINEOLDHRISSITEIDLIST = "hrisEtLineOLDHrisSiteIDList";
    public static final String PROPERTY_HRISETLINENEWHRISSITEIDLIST = "hrisEtLineNEWHrisSiteIDList";
    public static final String PROPERTY_HRISSITEPARENTSITEIDLIST = "hrisSiteParentSiteIDList";
    public static final String PROPERTY_PYRDEDUCTIONPARAMEMHRISSITELIST = "pyrDeductionParamEMHrisSiteList";
    public static final String PROPERTY_PYREARNINGPARAMEMHRISSITELIST = "pyrEarningParamEmHrisSiteList";
    public static final String PROPERTY_PYRSPEMPLOYEELIST = "pyrSpEmployeeList";

    public hris_site() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNEREMHRISSITELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEMPINFOSITELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINEOLDHRISSITEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINENEWHRISSITEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISSITEPARENTSITEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRDEDUCTIONPARAMEMHRISSITELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYREARNINGPARAMEMHRISSITELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSPEMPLOYEELIST, new ArrayList<Object>());
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

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public hris_site getParentSite() {
        return (hris_site) get(PROPERTY_PARENTSITE);
    }

    public void setParentSite(hris_site parentSite) {
        set(PROPERTY_PARENTSITE, parentSite);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerEMHrisSiteList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNEREMHRISSITELIST);
    }

    public void setBusinessPartnerEMHrisSiteList(List<BusinessPartner> businessPartnerEMHrisSiteList) {
        set(PROPERTY_BUSINESSPARTNEREMHRISSITELIST, businessPartnerEMHrisSiteList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Empinfo> getHRISCBpEmpinfoSiteList() {
      return (List<HRIS_C_Bp_Empinfo>) get(PROPERTY_HRISCBPEMPINFOSITELIST);
    }

    public void setHRISCBpEmpinfoSiteList(List<HRIS_C_Bp_Empinfo> hRISCBpEmpinfoSiteList) {
        set(PROPERTY_HRISCBPEMPINFOSITELIST, hRISCBpEmpinfoSiteList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineOLDHrisSiteIDList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINEOLDHRISSITEIDLIST);
    }

    public void setHrisEtLineOLDHrisSiteIDList(List<EmployeeTransferLine> hrisEtLineOLDHrisSiteIDList) {
        set(PROPERTY_HRISETLINEOLDHRISSITEIDLIST, hrisEtLineOLDHrisSiteIDList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineNEWHrisSiteIDList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINENEWHRISSITEIDLIST);
    }

    public void setHrisEtLineNEWHrisSiteIDList(List<EmployeeTransferLine> hrisEtLineNEWHrisSiteIDList) {
        set(PROPERTY_HRISETLINENEWHRISSITEIDLIST, hrisEtLineNEWHrisSiteIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_site> getHrisSiteParentSiteIDList() {
      return (List<hris_site>) get(PROPERTY_HRISSITEPARENTSITEIDLIST);
    }

    public void setHrisSiteParentSiteIDList(List<hris_site> hrisSiteParentSiteIDList) {
        set(PROPERTY_HRISSITEPARENTSITEIDLIST, hrisSiteParentSiteIDList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_deduction_param> getPyrDeductionParamEMHrisSiteList() {
      return (List<pyr_deduction_param>) get(PROPERTY_PYRDEDUCTIONPARAMEMHRISSITELIST);
    }

    public void setPyrDeductionParamEMHrisSiteList(List<pyr_deduction_param> pyrDeductionParamEMHrisSiteList) {
        set(PROPERTY_PYRDEDUCTIONPARAMEMHRISSITELIST, pyrDeductionParamEMHrisSiteList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_earning_param> getPyrEarningParamEmHrisSiteList() {
      return (List<pyr_earning_param>) get(PROPERTY_PYREARNINGPARAMEMHRISSITELIST);
    }

    public void setPyrEarningParamEmHrisSiteList(List<pyr_earning_param> pyrEarningParamEmHrisSiteList) {
        set(PROPERTY_PYREARNINGPARAMEMHRISSITELIST, pyrEarningParamEmHrisSiteList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_sp_employee> getPyrSpEmployeeList() {
      return (List<pyr_sp_employee>) get(PROPERTY_PYRSPEMPLOYEELIST);
    }

    public void setPyrSpEmployeeList(List<pyr_sp_employee> pyrSpEmployeeList) {
        set(PROPERTY_PYRSPEMPLOYEELIST, pyrSpEmployeeList);
    }

}
