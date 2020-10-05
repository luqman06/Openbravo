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
import org.openbravo.model.financialmgmt.accounting.Costcenter;
/**
 * Entity class for entity hris_et_line (stored in table hris_et_line).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EmployeeTransferLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_et_line";
    public static final String ENTITY_NAME = "hris_et_line";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_OLDEMPLOYMENTTYPE = "oLDEmploymenttype";
    public static final String PROPERTY_OLDDEPARTMENT = "oLDDepartment";
    public static final String PROPERTY_OLDECHELON = "oLDEchelon";
    public static final String PROPERTY_OLDEMPLOYEEGRADE = "oLDEmployeegrade";
    public static final String PROPERTY_OLDJOBTITLE = "oLDJobtitle";
    public static final String PROPERTY_OLDPAYROLLMASTER = "oLDPayrollmaster";
    public static final String PROPERTY_OLDHRISSITE = "oLDHrisSite";
    public static final String PROPERTY_OLDCOSTCENTER = "oLDCostcenter";
    public static final String PROPERTY_NEWEMPLOYMENTTYPE = "nEWEmploymenttype";
    public static final String PROPERTY_NEWDEPARTMENT = "nEWDepartment";
    public static final String PROPERTY_NEWECHELON = "nEWEchelon";
    public static final String PROPERTY_NEWEMPLOYEEGRADE = "nEWEmployeegrade";
    public static final String PROPERTY_NEWJOBTITLE = "nEWJobtitle";
    public static final String PROPERTY_NEWPAYROLLMASTER = "nEWPayrollmaster";
    public static final String PROPERTY_NEWHRISSITE = "nEWHrisSite";
    public static final String PROPERTY_NEWCOSTCENTER = "nEWCostcenter";
    public static final String PROPERTY_EMPLOYEETRANSFERTYPE = "employeetransfertype";
    public static final String PROPERTY_HRISEMPLOYEETRANSFER = "hrisEmployeetransfer";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_OLDCONTRACT = "oldcontract";
    public static final String PROPERTY_NEWCONTRACT = "newcontract";
    public static final String PROPERTY_OLDPOSITION = "oLDPosition";
    public static final String PROPERTY_NEWPOSITION = "nEWPosition";

    public EmployeeTransferLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public String getOLDEmploymenttype() {
        return (String) get(PROPERTY_OLDEMPLOYMENTTYPE);
    }

    public void setOLDEmploymenttype(String oLDEmploymenttype) {
        set(PROPERTY_OLDEMPLOYMENTTYPE, oLDEmploymenttype);
    }

    public HRIS_C_Bp_Department getOLDDepartment() {
        return (HRIS_C_Bp_Department) get(PROPERTY_OLDDEPARTMENT);
    }

    public void setOLDDepartment(HRIS_C_Bp_Department oLDDepartment) {
        set(PROPERTY_OLDDEPARTMENT, oLDDepartment);
    }

    public String getOLDEchelon() {
        return (String) get(PROPERTY_OLDECHELON);
    }

    public void setOLDEchelon(String oLDEchelon) {
        set(PROPERTY_OLDECHELON, oLDEchelon);
    }

    public String getOLDEmployeegrade() {
        return (String) get(PROPERTY_OLDEMPLOYEEGRADE);
    }

    public void setOLDEmployeegrade(String oLDEmployeegrade) {
        set(PROPERTY_OLDEMPLOYEEGRADE, oLDEmployeegrade);
    }

    public hris_jobtitle getOLDJobtitle() {
        return (hris_jobtitle) get(PROPERTY_OLDJOBTITLE);
    }

    public void setOLDJobtitle(hris_jobtitle oLDJobtitle) {
        set(PROPERTY_OLDJOBTITLE, oLDJobtitle);
    }

    public BusinessPartner getOLDPayrollmaster() {
        return (BusinessPartner) get(PROPERTY_OLDPAYROLLMASTER);
    }

    public void setOLDPayrollmaster(BusinessPartner oLDPayrollmaster) {
        set(PROPERTY_OLDPAYROLLMASTER, oLDPayrollmaster);
    }

    public hris_site getOLDHrisSite() {
        return (hris_site) get(PROPERTY_OLDHRISSITE);
    }

    public void setOLDHrisSite(hris_site oLDHrisSite) {
        set(PROPERTY_OLDHRISSITE, oLDHrisSite);
    }

    public Costcenter getOLDCostcenter() {
        return (Costcenter) get(PROPERTY_OLDCOSTCENTER);
    }

    public void setOLDCostcenter(Costcenter oLDCostcenter) {
        set(PROPERTY_OLDCOSTCENTER, oLDCostcenter);
    }

    public String getNEWEmploymenttype() {
        return (String) get(PROPERTY_NEWEMPLOYMENTTYPE);
    }

    public void setNEWEmploymenttype(String nEWEmploymenttype) {
        set(PROPERTY_NEWEMPLOYMENTTYPE, nEWEmploymenttype);
    }

    public HRIS_C_Bp_Department getNEWDepartment() {
        return (HRIS_C_Bp_Department) get(PROPERTY_NEWDEPARTMENT);
    }

    public void setNEWDepartment(HRIS_C_Bp_Department nEWDepartment) {
        set(PROPERTY_NEWDEPARTMENT, nEWDepartment);
    }

    public String getNEWEchelon() {
        return (String) get(PROPERTY_NEWECHELON);
    }

    public void setNEWEchelon(String nEWEchelon) {
        set(PROPERTY_NEWECHELON, nEWEchelon);
    }

    public String getNEWEmployeegrade() {
        return (String) get(PROPERTY_NEWEMPLOYEEGRADE);
    }

    public void setNEWEmployeegrade(String nEWEmployeegrade) {
        set(PROPERTY_NEWEMPLOYEEGRADE, nEWEmployeegrade);
    }

    public hris_jobtitle getNEWJobtitle() {
        return (hris_jobtitle) get(PROPERTY_NEWJOBTITLE);
    }

    public void setNEWJobtitle(hris_jobtitle nEWJobtitle) {
        set(PROPERTY_NEWJOBTITLE, nEWJobtitle);
    }

    public BusinessPartner getNEWPayrollmaster() {
        return (BusinessPartner) get(PROPERTY_NEWPAYROLLMASTER);
    }

    public void setNEWPayrollmaster(BusinessPartner nEWPayrollmaster) {
        set(PROPERTY_NEWPAYROLLMASTER, nEWPayrollmaster);
    }

    public hris_site getNEWHrisSite() {
        return (hris_site) get(PROPERTY_NEWHRISSITE);
    }

    public void setNEWHrisSite(hris_site nEWHrisSite) {
        set(PROPERTY_NEWHRISSITE, nEWHrisSite);
    }

    public Costcenter getNEWCostcenter() {
        return (Costcenter) get(PROPERTY_NEWCOSTCENTER);
    }

    public void setNEWCostcenter(Costcenter nEWCostcenter) {
        set(PROPERTY_NEWCOSTCENTER, nEWCostcenter);
    }

    public String getEmployeetransfertype() {
        return (String) get(PROPERTY_EMPLOYEETRANSFERTYPE);
    }

    public void setEmployeetransfertype(String employeetransfertype) {
        set(PROPERTY_EMPLOYEETRANSFERTYPE, employeetransfertype);
    }

    public EmployeeTransfer getHrisEmployeetransfer() {
        return (EmployeeTransfer) get(PROPERTY_HRISEMPLOYEETRANSFER);
    }

    public void setHrisEmployeetransfer(EmployeeTransfer hrisEmployeetransfer) {
        set(PROPERTY_HRISEMPLOYEETRANSFER, hrisEmployeetransfer);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public HRIS_C_Bp_Empinfo getOldcontract() {
        return (HRIS_C_Bp_Empinfo) get(PROPERTY_OLDCONTRACT);
    }

    public void setOldcontract(HRIS_C_Bp_Empinfo oldcontract) {
        set(PROPERTY_OLDCONTRACT, oldcontract);
    }

    public HRIS_C_Bp_Empinfo getNewcontract() {
        return (HRIS_C_Bp_Empinfo) get(PROPERTY_NEWCONTRACT);
    }

    public void setNewcontract(HRIS_C_Bp_Empinfo newcontract) {
        set(PROPERTY_NEWCONTRACT, newcontract);
    }

    public EmployeePosition getOLDPosition() {
        return (EmployeePosition) get(PROPERTY_OLDPOSITION);
    }

    public void setOLDPosition(EmployeePosition oLDPosition) {
        set(PROPERTY_OLDPOSITION, oLDPosition);
    }

    public EmployeePosition getNEWPosition() {
        return (EmployeePosition) get(PROPERTY_NEWPOSITION);
    }

    public void setNEWPosition(EmployeePosition nEWPosition) {
        set(PROPERTY_NEWPOSITION, nEWPosition);
    }

}
