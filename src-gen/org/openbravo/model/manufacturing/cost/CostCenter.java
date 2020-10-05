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
package org.openbravo.model.manufacturing.cost;

import java.math.BigDecimal;
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
import org.openbravo.model.manufacturing.floorshop.Machine;
import org.openbravo.model.manufacturing.transaction.Activity;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.hris_benefits;
import org.wirabumi.hris.employee.master.data.hris_case;
import org.wirabumi.hris.employee.master.data.hris_costcenterrule;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;
import org.wirabumi.hris.employee.master.data.hris_education_admission;
import org.wirabumi.hris.employee.master.data.hris_education_exam;
import org.wirabumi.hris.employee.master.data.hris_education_permit;
import org.wirabumi.hris.employee.master.data.hris_employee_transfer;
import org.wirabumi.hris.employee.master.data.hris_i_employee_candidate;
import org.wirabumi.hris.employee.master.data.hris_jobtitlerule;
import org.wirabumi.hris.employee.master.data.hris_pengundurandiri;
import org.wirabumi.hris.employee.master.data.hris_r_line;
import org.wirabumi.hris.employee.master.data.hris_reimbursment;
import org.wirabumi.hris.termination.tm_termination;
/**
 * Entity class for entity ManufacturingCostCenter (stored in table MA_CostCenter).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CostCenter extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_CostCenter";
    public static final String ENTITY_NAME = "ManufacturingCostCenter";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_COST = "cost";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_BYDEFAULT = "byDefault";
    public static final String PROPERTY_CALCULATED = "calculated";
    public static final String PROPERTY_COSTUOM = "costUOM";
    public static final String PROPERTY_BUSINESSPARTNEREMHRISCOSTCENTERIDLIST = "businessPartnerEmHrisCostcenterIdList";
    public static final String PROPERTY_HRISCBPEMPINFOLIST = "hRISCBpEmpinfoList";
    public static final String PROPERTY_MANUFACTURINGACTIVITYLIST = "manufacturingActivityList";
    public static final String PROPERTY_MANUFACTURINGCOSTCENTERVERSIONLIST = "manufacturingCostcenterVersionList";
    public static final String PROPERTY_MANUFACTURINGMACHINELIST = "manufacturingMachineList";
    public static final String PROPERTY_HRISBENEFITSLIST = "hrisBenefitsList";
    public static final String PROPERTY_HRISCASELIST = "hrisCaseList";
    public static final String PROPERTY_HRISCOSTCENTERRULELIST = "hrisCostcenterruleList";
    public static final String PROPERTY_HRISECLINESLIST = "hrisEcLinesList";
    public static final String PROPERTY_HRISEDUCATIONADMISSIONLIST = "hrisEducationAdmissionList";
    public static final String PROPERTY_HRISEDUCATIONEXAMLIST = "hrisEducationExamList";
    public static final String PROPERTY_HRISEDUCATIONPERMITLIST = "hrisEducationPermitList";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERFROMMACOSTCENTERIDLIST = "hrisEmployeeTransferFrommaCostcenterIDList";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERTOMACOSTCENTERIDLIST = "hrisEmployeeTransferTomaCostcenterIDList";
    public static final String PROPERTY_HRISIEMPLOYEECANDIDATELIST = "hrisIEmployeeCandidateList";
    public static final String PROPERTY_HRISJOBTITLERULELIST = "hrisJobtitleruleList";
    public static final String PROPERTY_HRISPENGUNDURANDIRILIST = "hrisPengundurandiriList";
    public static final String PROPERTY_HRISRLINELIST = "hrisRLineList";
    public static final String PROPERTY_HRISREIMBURSMENTLIST = "hrisReimbursmentList";
    public static final String PROPERTY_TERMINATIONLIST = "terminationList";

    public CostCenter() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BYDEFAULT, false);
        setDefaultValue(PROPERTY_CALCULATED, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNEREMHRISCOSTCENTERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEMPINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGACTIVITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGCOSTCENTERVERSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMACHINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISBENEFITSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCASELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCOSTCENTERRULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISECLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONADMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONEXAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONPERMITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERFROMMACOSTCENTERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERTOMACOSTCENTERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISIEMPLOYEECANDIDATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJOBTITLERULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISPENGUNDURANDIRILIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISREIMBURSMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TERMINATIONLIST, new ArrayList<Object>());
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

    public BigDecimal getCost() {
        return (BigDecimal) get(PROPERTY_COST);
    }

    public void setCost(BigDecimal cost) {
        set(PROPERTY_COST, cost);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public Boolean isByDefault() {
        return (Boolean) get(PROPERTY_BYDEFAULT);
    }

    public void setByDefault(Boolean byDefault) {
        set(PROPERTY_BYDEFAULT, byDefault);
    }

    public Boolean isCalculated() {
        return (Boolean) get(PROPERTY_CALCULATED);
    }

    public void setCalculated(Boolean calculated) {
        set(PROPERTY_CALCULATED, calculated);
    }

    public String getCostUOM() {
        return (String) get(PROPERTY_COSTUOM);
    }

    public void setCostUOM(String costUOM) {
        set(PROPERTY_COSTUOM, costUOM);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerEmHrisCostcenterIdList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNEREMHRISCOSTCENTERIDLIST);
    }

    public void setBusinessPartnerEmHrisCostcenterIdList(List<BusinessPartner> businessPartnerEmHrisCostcenterIdList) {
        set(PROPERTY_BUSINESSPARTNEREMHRISCOSTCENTERIDLIST, businessPartnerEmHrisCostcenterIdList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Empinfo> getHRISCBpEmpinfoList() {
      return (List<HRIS_C_Bp_Empinfo>) get(PROPERTY_HRISCBPEMPINFOLIST);
    }

    public void setHRISCBpEmpinfoList(List<HRIS_C_Bp_Empinfo> hRISCBpEmpinfoList) {
        set(PROPERTY_HRISCBPEMPINFOLIST, hRISCBpEmpinfoList);
    }

    @SuppressWarnings("unchecked")
    public List<Activity> getManufacturingActivityList() {
      return (List<Activity>) get(PROPERTY_MANUFACTURINGACTIVITYLIST);
    }

    public void setManufacturingActivityList(List<Activity> manufacturingActivityList) {
        set(PROPERTY_MANUFACTURINGACTIVITYLIST, manufacturingActivityList);
    }

    @SuppressWarnings("unchecked")
    public List<CostcenterVersion> getManufacturingCostcenterVersionList() {
      return (List<CostcenterVersion>) get(PROPERTY_MANUFACTURINGCOSTCENTERVERSIONLIST);
    }

    public void setManufacturingCostcenterVersionList(List<CostcenterVersion> manufacturingCostcenterVersionList) {
        set(PROPERTY_MANUFACTURINGCOSTCENTERVERSIONLIST, manufacturingCostcenterVersionList);
    }

    @SuppressWarnings("unchecked")
    public List<Machine> getManufacturingMachineList() {
      return (List<Machine>) get(PROPERTY_MANUFACTURINGMACHINELIST);
    }

    public void setManufacturingMachineList(List<Machine> manufacturingMachineList) {
        set(PROPERTY_MANUFACTURINGMACHINELIST, manufacturingMachineList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_benefits> getHrisBenefitsList() {
      return (List<hris_benefits>) get(PROPERTY_HRISBENEFITSLIST);
    }

    public void setHrisBenefitsList(List<hris_benefits> hrisBenefitsList) {
        set(PROPERTY_HRISBENEFITSLIST, hrisBenefitsList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_case> getHrisCaseList() {
      return (List<hris_case>) get(PROPERTY_HRISCASELIST);
    }

    public void setHrisCaseList(List<hris_case> hrisCaseList) {
        set(PROPERTY_HRISCASELIST, hrisCaseList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_costcenterrule> getHrisCostcenterruleList() {
      return (List<hris_costcenterrule>) get(PROPERTY_HRISCOSTCENTERRULELIST);
    }

    public void setHrisCostcenterruleList(List<hris_costcenterrule> hrisCostcenterruleList) {
        set(PROPERTY_HRISCOSTCENTERRULELIST, hrisCostcenterruleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ec_lines> getHrisEcLinesList() {
      return (List<hris_ec_lines>) get(PROPERTY_HRISECLINESLIST);
    }

    public void setHrisEcLinesList(List<hris_ec_lines> hrisEcLinesList) {
        set(PROPERTY_HRISECLINESLIST, hrisEcLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_admission> getHrisEducationAdmissionList() {
      return (List<hris_education_admission>) get(PROPERTY_HRISEDUCATIONADMISSIONLIST);
    }

    public void setHrisEducationAdmissionList(List<hris_education_admission> hrisEducationAdmissionList) {
        set(PROPERTY_HRISEDUCATIONADMISSIONLIST, hrisEducationAdmissionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_exam> getHrisEducationExamList() {
      return (List<hris_education_exam>) get(PROPERTY_HRISEDUCATIONEXAMLIST);
    }

    public void setHrisEducationExamList(List<hris_education_exam> hrisEducationExamList) {
        set(PROPERTY_HRISEDUCATIONEXAMLIST, hrisEducationExamList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_permit> getHrisEducationPermitList() {
      return (List<hris_education_permit>) get(PROPERTY_HRISEDUCATIONPERMITLIST);
    }

    public void setHrisEducationPermitList(List<hris_education_permit> hrisEducationPermitList) {
        set(PROPERTY_HRISEDUCATIONPERMITLIST, hrisEducationPermitList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferFrommaCostcenterIDList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERFROMMACOSTCENTERIDLIST);
    }

    public void setHrisEmployeeTransferFrommaCostcenterIDList(List<hris_employee_transfer> hrisEmployeeTransferFrommaCostcenterIDList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERFROMMACOSTCENTERIDLIST, hrisEmployeeTransferFrommaCostcenterIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferTomaCostcenterIDList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERTOMACOSTCENTERIDLIST);
    }

    public void setHrisEmployeeTransferTomaCostcenterIDList(List<hris_employee_transfer> hrisEmployeeTransferTomaCostcenterIDList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERTOMACOSTCENTERIDLIST, hrisEmployeeTransferTomaCostcenterIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_i_employee_candidate> getHrisIEmployeeCandidateList() {
      return (List<hris_i_employee_candidate>) get(PROPERTY_HRISIEMPLOYEECANDIDATELIST);
    }

    public void setHrisIEmployeeCandidateList(List<hris_i_employee_candidate> hrisIEmployeeCandidateList) {
        set(PROPERTY_HRISIEMPLOYEECANDIDATELIST, hrisIEmployeeCandidateList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_jobtitlerule> getHrisJobtitleruleList() {
      return (List<hris_jobtitlerule>) get(PROPERTY_HRISJOBTITLERULELIST);
    }

    public void setHrisJobtitleruleList(List<hris_jobtitlerule> hrisJobtitleruleList) {
        set(PROPERTY_HRISJOBTITLERULELIST, hrisJobtitleruleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_pengundurandiri> getHrisPengundurandiriList() {
      return (List<hris_pengundurandiri>) get(PROPERTY_HRISPENGUNDURANDIRILIST);
    }

    public void setHrisPengundurandiriList(List<hris_pengundurandiri> hrisPengundurandiriList) {
        set(PROPERTY_HRISPENGUNDURANDIRILIST, hrisPengundurandiriList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_r_line> getHrisRLineList() {
      return (List<hris_r_line>) get(PROPERTY_HRISRLINELIST);
    }

    public void setHrisRLineList(List<hris_r_line> hrisRLineList) {
        set(PROPERTY_HRISRLINELIST, hrisRLineList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_reimbursment> getHrisReimbursmentList() {
      return (List<hris_reimbursment>) get(PROPERTY_HRISREIMBURSMENTLIST);
    }

    public void setHrisReimbursmentList(List<hris_reimbursment> hrisReimbursmentList) {
        set(PROPERTY_HRISREIMBURSMENTLIST, hrisReimbursmentList);
    }

    @SuppressWarnings("unchecked")
    public List<tm_termination> getTerminationList() {
      return (List<tm_termination>) get(PROPERTY_TERMINATIONLIST);
    }

    public void setTerminationList(List<tm_termination> terminationList) {
        set(PROPERTY_TERMINATIONLIST, terminationList);
    }

}
