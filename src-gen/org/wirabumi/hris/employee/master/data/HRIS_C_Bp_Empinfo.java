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
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.wirabumi.hris.termination.tm_termination;
/**
 * Entity class for entity HRIS_C_Bp_Empinfo (stored in table HRIS_C_Bp_Empinfo).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class HRIS_C_Bp_Empinfo extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "HRIS_C_Bp_Empinfo";
    public static final String ENTITY_NAME = "HRIS_C_Bp_Empinfo";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_HRISCBPDEPARTMENT = "hrisCBpDepartment";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_EMPLOYEMENTTYPE = "employementtype";
    public static final String PROPERTY_LEVEL = "level";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_HRISJOBTITLE = "hrisJobtitle";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_VALIDTODATE = "validToDate";
    public static final String PROPERTY_STAFF = "staff";
    public static final String PROPERTY_ISCURRENTPOS = "iscurrentpos";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CONTRACTNO = "contractno";
    public static final String PROPERTY_REPORTTO = "reportTo";
    public static final String PROPERTY_ISSALESREPRESENTATIVE = "isSalesRepresentative";
    public static final String PROPERTY_OPERATOR = "operator";
    public static final String PROPERTY_ONSITEEMPLOYEE = "onSiteEmployee";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_COSTCENTER = "costcenter";
    public static final String PROPERTY_SITE = "site";
    public static final String PROPERTY_HRISCONTRACTTYPE = "hrisContracttype";
    public static final String PROPERTY_PYRPAYROLLMASTER = "pYRPayrollMaster";
    public static final String PROPERTY_PYRISPAYROLLMASTER = "pyrIspayrollmaster";
    public static final String PROPERTY_EMPLOYEECOSTCENTER = "employeeCostCenter";
    public static final String PROPERTY_CANCEL = "cancel";
    public static final String PROPERTY_ISPJS = "ispjs";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERHRISCONTRACTFKLIST = "hrisEmployeeTransferHrisContractFkList";
    public static final String PROPERTY_HRISETLINEOLDCONTRACTLIST = "hrisEtLineOldcontractList";
    public static final String PROPERTY_HRISETLINENEWCONTRACTLIST = "hrisEtLineNewcontractList";
    public static final String PROPERTY_HRISPENGUNDURANDIRIHRISCONTRACTFKLIST = "hrisPengundurandiriHrisContractFkList";
    public static final String PROPERTY_HRISRLINECONTRACTLIST = "hrisRLineContractList";
    public static final String PROPERTY_TMTERMINATIONHRISCONTRACTFKLIST = "tmTerminationHrisContractFkList";

    public HRIS_C_Bp_Empinfo() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_STAFF, false);
        setDefaultValue(PROPERTY_ISCURRENTPOS, false);
        setDefaultValue(PROPERTY_ISSALESREPRESENTATIVE, false);
        setDefaultValue(PROPERTY_OPERATOR, false);
        setDefaultValue(PROPERTY_ONSITEEMPLOYEE, false);
        setDefaultValue(PROPERTY_PYRISPAYROLLMASTER, false);
        setDefaultValue(PROPERTY_CANCEL, false);
        setDefaultValue(PROPERTY_ISPJS, false);
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERHRISCONTRACTFKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINEOLDCONTRACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINENEWCONTRACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISPENGUNDURANDIRIHRISCONTRACTFKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRLINECONTRACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TMTERMINATIONHRISCONTRACTFKLIST, new ArrayList<Object>());
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

    public HRIS_C_Bp_Department getHrisCBpDepartment() {
        return (HRIS_C_Bp_Department) get(PROPERTY_HRISCBPDEPARTMENT);
    }

    public void setHrisCBpDepartment(HRIS_C_Bp_Department hrisCBpDepartment) {
        set(PROPERTY_HRISCBPDEPARTMENT, hrisCBpDepartment);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public String getEmployementtype() {
        return (String) get(PROPERTY_EMPLOYEMENTTYPE);
    }

    public void setEmployementtype(String employementtype) {
        set(PROPERTY_EMPLOYEMENTTYPE, employementtype);
    }

    public String getLevel() {
        return (String) get(PROPERTY_LEVEL);
    }

    public void setLevel(String level) {
        set(PROPERTY_LEVEL, level);
    }

    public EmployeePosition getPosition() {
        return (EmployeePosition) get(PROPERTY_POSITION);
    }

    public void setPosition(EmployeePosition position) {
        set(PROPERTY_POSITION, position);
    }

    public hris_jobtitle getHrisJobtitle() {
        return (hris_jobtitle) get(PROPERTY_HRISJOBTITLE);
    }

    public void setHrisJobtitle(hris_jobtitle hrisJobtitle) {
        set(PROPERTY_HRISJOBTITLE, hrisJobtitle);
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

    public Boolean isStaff() {
        return (Boolean) get(PROPERTY_STAFF);
    }

    public void setStaff(Boolean staff) {
        set(PROPERTY_STAFF, staff);
    }

    public Boolean isCurrentpos() {
        return (Boolean) get(PROPERTY_ISCURRENTPOS);
    }

    public void setCurrentpos(Boolean iscurrentpos) {
        set(PROPERTY_ISCURRENTPOS, iscurrentpos);
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

    public String getContractno() {
        return (String) get(PROPERTY_CONTRACTNO);
    }

    public void setContractno(String contractno) {
        set(PROPERTY_CONTRACTNO, contractno);
    }

    public BusinessPartner getReportTo() {
        return (BusinessPartner) get(PROPERTY_REPORTTO);
    }

    public void setReportTo(BusinessPartner reportTo) {
        set(PROPERTY_REPORTTO, reportTo);
    }

    public Boolean isSalesRepresentative() {
        return (Boolean) get(PROPERTY_ISSALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(Boolean isSalesRepresentative) {
        set(PROPERTY_ISSALESREPRESENTATIVE, isSalesRepresentative);
    }

    public Boolean isOperator() {
        return (Boolean) get(PROPERTY_OPERATOR);
    }

    public void setOperator(Boolean operator) {
        set(PROPERTY_OPERATOR, operator);
    }

    public Boolean isOnSiteEmployee() {
        return (Boolean) get(PROPERTY_ONSITEEMPLOYEE);
    }

    public void setOnSiteEmployee(Boolean onSiteEmployee) {
        set(PROPERTY_ONSITEEMPLOYEE, onSiteEmployee);
    }

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
    }

    public CostCenter getCostcenter() {
        return (CostCenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostcenter(CostCenter costcenter) {
        set(PROPERTY_COSTCENTER, costcenter);
    }

    public hris_site getSite() {
        return (hris_site) get(PROPERTY_SITE);
    }

    public void setSite(hris_site site) {
        set(PROPERTY_SITE, site);
    }

    public String getHrisContracttype() {
        return (String) get(PROPERTY_HRISCONTRACTTYPE);
    }

    public void setHrisContracttype(String hrisContracttype) {
        set(PROPERTY_HRISCONTRACTTYPE, hrisContracttype);
    }

    public BusinessPartner getPYRPayrollMaster() {
        return (BusinessPartner) get(PROPERTY_PYRPAYROLLMASTER);
    }

    public void setPYRPayrollMaster(BusinessPartner pYRPayrollMaster) {
        set(PROPERTY_PYRPAYROLLMASTER, pYRPayrollMaster);
    }

    public Boolean isPyrIspayrollmaster() {
        return (Boolean) get(PROPERTY_PYRISPAYROLLMASTER);
    }

    public void setPyrIspayrollmaster(Boolean pyrIspayrollmaster) {
        set(PROPERTY_PYRISPAYROLLMASTER, pyrIspayrollmaster);
    }

    public Costcenter getEmployeeCostCenter() {
        return (Costcenter) get(PROPERTY_EMPLOYEECOSTCENTER);
    }

    public void setEmployeeCostCenter(Costcenter employeeCostCenter) {
        set(PROPERTY_EMPLOYEECOSTCENTER, employeeCostCenter);
    }

    public Boolean isCancel() {
        return (Boolean) get(PROPERTY_CANCEL);
    }

    public void setCancel(Boolean cancel) {
        set(PROPERTY_CANCEL, cancel);
    }

    public Boolean isPjs() {
        return (Boolean) get(PROPERTY_ISPJS);
    }

    public void setPjs(Boolean ispjs) {
        set(PROPERTY_ISPJS, ispjs);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferHrisContractFkList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERHRISCONTRACTFKLIST);
    }

    public void setHrisEmployeeTransferHrisContractFkList(List<hris_employee_transfer> hrisEmployeeTransferHrisContractFkList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERHRISCONTRACTFKLIST, hrisEmployeeTransferHrisContractFkList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineOldcontractList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINEOLDCONTRACTLIST);
    }

    public void setHrisEtLineOldcontractList(List<EmployeeTransferLine> hrisEtLineOldcontractList) {
        set(PROPERTY_HRISETLINEOLDCONTRACTLIST, hrisEtLineOldcontractList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineNewcontractList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINENEWCONTRACTLIST);
    }

    public void setHrisEtLineNewcontractList(List<EmployeeTransferLine> hrisEtLineNewcontractList) {
        set(PROPERTY_HRISETLINENEWCONTRACTLIST, hrisEtLineNewcontractList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_pengundurandiri> getHrisPengundurandiriHrisContractFkList() {
      return (List<hris_pengundurandiri>) get(PROPERTY_HRISPENGUNDURANDIRIHRISCONTRACTFKLIST);
    }

    public void setHrisPengundurandiriHrisContractFkList(List<hris_pengundurandiri> hrisPengundurandiriHrisContractFkList) {
        set(PROPERTY_HRISPENGUNDURANDIRIHRISCONTRACTFKLIST, hrisPengundurandiriHrisContractFkList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_r_line> getHrisRLineContractList() {
      return (List<hris_r_line>) get(PROPERTY_HRISRLINECONTRACTLIST);
    }

    public void setHrisRLineContractList(List<hris_r_line> hrisRLineContractList) {
        set(PROPERTY_HRISRLINECONTRACTLIST, hrisRLineContractList);
    }

    @SuppressWarnings("unchecked")
    public List<tm_termination> getTmTerminationHrisContractFkList() {
      return (List<tm_termination>) get(PROPERTY_TMTERMINATIONHRISCONTRACTFKLIST);
    }

    public void setTmTerminationHrisContractFkList(List<tm_termination> tmTerminationHrisContractFkList) {
        set(PROPERTY_TMTERMINATIONHRISCONTRACTFKLIST, tmTerminationHrisContractFkList);
    }

}
