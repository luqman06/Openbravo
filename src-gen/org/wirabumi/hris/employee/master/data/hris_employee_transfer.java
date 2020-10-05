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
import org.openbravo.model.manufacturing.cost.CostCenter;
/**
 * Entity class for entity hris_employee_transfer (stored in table hris_employee_transfer).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_employee_transfer extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_employee_transfer";
    public static final String ENTITY_NAME = "hris_employee_transfer";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_FROMADORG = "fromadOrg";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_FROMLEVEL = "fromlevel";
    public static final String PROPERTY_JOINDATE = "joindate";
    public static final String PROPERTY_TENURE = "tenure";
    public static final String PROPERTY_FROMMACOSTCENTER = "frommaCostcenter";
    public static final String PROPERTY_FROMPOSITION = "fromposition";
    public static final String PROPERTY_FROMHRISJOBTITLE = "fromhrisJobtitle";
    public static final String PROPERTY_FROMECHELON = "fromechelon";
    public static final String PROPERTY_TOADORG = "toadOrg";
    public static final String PROPERTY_TOLEVEL = "tolevel";
    public static final String PROPERTY_TOMACOSTCENTER = "tomaCostcenter";
    public static final String PROPERTY_TOHRISJOBTITLE = "tohrisJobtitle";
    public static final String PROPERTY_TOECHELON = "toechelon";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_CONTRACTNO = "contractno";
    public static final String PROPERTY_TANGGALSK = "tanggalSK";
    public static final String PROPERTY_VALIDFROM = "validfrom";
    public static final String PROPERTY_CONTRACTTYPE = "contracttype";
    public static final String PROPERTY_TOPOSITIONED = "topositioned";
    public static final String PROPERTY_RELEASECONTRACT = "releaseContract";
    public static final String PROPERTY_EVALUASIPEGAWAI = "evaluasiPegawai";
    public static final String PROPERTY_HRISCONTRACTFK = "hrisContractFk";
    public static final String PROPERTY_EMPLOYMENTTYPE = "employmenttype";
    public static final String PROPERTY_HASVIOLATION = "hasviolation";

    public hris_employee_transfer() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_RELEASECONTRACT, false);
        setDefaultValue(PROPERTY_HASVIOLATION, false);
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

    public Organization getFromadOrg() {
        return (Organization) get(PROPERTY_FROMADORG);
    }

    public void setFromadOrg(Organization fromadOrg) {
        set(PROPERTY_FROMADORG, fromadOrg);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getFromlevel() {
        return (String) get(PROPERTY_FROMLEVEL);
    }

    public void setFromlevel(String fromlevel) {
        set(PROPERTY_FROMLEVEL, fromlevel);
    }

    public Date getJoindate() {
        return (Date) get(PROPERTY_JOINDATE);
    }

    public void setJoindate(Date joindate) {
        set(PROPERTY_JOINDATE, joindate);
    }

    public Long getTenure() {
        return (Long) get(PROPERTY_TENURE);
    }

    public void setTenure(Long tenure) {
        set(PROPERTY_TENURE, tenure);
    }

    public CostCenter getFrommaCostcenter() {
        return (CostCenter) get(PROPERTY_FROMMACOSTCENTER);
    }

    public void setFrommaCostcenter(CostCenter frommaCostcenter) {
        set(PROPERTY_FROMMACOSTCENTER, frommaCostcenter);
    }

    public EmployeePosition getFromposition() {
        return (EmployeePosition) get(PROPERTY_FROMPOSITION);
    }

    public void setFromposition(EmployeePosition fromposition) {
        set(PROPERTY_FROMPOSITION, fromposition);
    }

    public hris_jobtitle getFromhrisJobtitle() {
        return (hris_jobtitle) get(PROPERTY_FROMHRISJOBTITLE);
    }

    public void setFromhrisJobtitle(hris_jobtitle fromhrisJobtitle) {
        set(PROPERTY_FROMHRISJOBTITLE, fromhrisJobtitle);
    }

    public String getFromechelon() {
        return (String) get(PROPERTY_FROMECHELON);
    }

    public void setFromechelon(String fromechelon) {
        set(PROPERTY_FROMECHELON, fromechelon);
    }

    public Organization getToadOrg() {
        return (Organization) get(PROPERTY_TOADORG);
    }

    public void setToadOrg(Organization toadOrg) {
        set(PROPERTY_TOADORG, toadOrg);
    }

    public String getTolevel() {
        return (String) get(PROPERTY_TOLEVEL);
    }

    public void setTolevel(String tolevel) {
        set(PROPERTY_TOLEVEL, tolevel);
    }

    public CostCenter getTomaCostcenter() {
        return (CostCenter) get(PROPERTY_TOMACOSTCENTER);
    }

    public void setTomaCostcenter(CostCenter tomaCostcenter) {
        set(PROPERTY_TOMACOSTCENTER, tomaCostcenter);
    }

    public hris_jobtitle getTohrisJobtitle() {
        return (hris_jobtitle) get(PROPERTY_TOHRISJOBTITLE);
    }

    public void setTohrisJobtitle(hris_jobtitle tohrisJobtitle) {
        set(PROPERTY_TOHRISJOBTITLE, tohrisJobtitle);
    }

    public String getToechelon() {
        return (String) get(PROPERTY_TOECHELON);
    }

    public void setToechelon(String toechelon) {
        set(PROPERTY_TOECHELON, toechelon);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getContractno() {
        return (String) get(PROPERTY_CONTRACTNO);
    }

    public void setContractno(String contractno) {
        set(PROPERTY_CONTRACTNO, contractno);
    }

    public Date getTanggalSK() {
        return (Date) get(PROPERTY_TANGGALSK);
    }

    public void setTanggalSK(Date tanggalSK) {
        set(PROPERTY_TANGGALSK, tanggalSK);
    }

    public Date getValidfrom() {
        return (Date) get(PROPERTY_VALIDFROM);
    }

    public void setValidfrom(Date validfrom) {
        set(PROPERTY_VALIDFROM, validfrom);
    }

    public String getContracttype() {
        return (String) get(PROPERTY_CONTRACTTYPE);
    }

    public void setContracttype(String contracttype) {
        set(PROPERTY_CONTRACTTYPE, contracttype);
    }

    public EmployeePosition getTopositioned() {
        return (EmployeePosition) get(PROPERTY_TOPOSITIONED);
    }

    public void setTopositioned(EmployeePosition topositioned) {
        set(PROPERTY_TOPOSITIONED, topositioned);
    }

    public Boolean isReleaseContract() {
        return (Boolean) get(PROPERTY_RELEASECONTRACT);
    }

    public void setReleaseContract(Boolean releaseContract) {
        set(PROPERTY_RELEASECONTRACT, releaseContract);
    }

    public String getEvaluasiPegawai() {
        return (String) get(PROPERTY_EVALUASIPEGAWAI);
    }

    public void setEvaluasiPegawai(String evaluasiPegawai) {
        set(PROPERTY_EVALUASIPEGAWAI, evaluasiPegawai);
    }

    public HRIS_C_Bp_Empinfo getHrisContractFk() {
        return (HRIS_C_Bp_Empinfo) get(PROPERTY_HRISCONTRACTFK);
    }

    public void setHrisContractFk(HRIS_C_Bp_Empinfo hrisContractFk) {
        set(PROPERTY_HRISCONTRACTFK, hrisContractFk);
    }

    public String getEmploymenttype() {
        return (String) get(PROPERTY_EMPLOYMENTTYPE);
    }

    public void setEmploymenttype(String employmenttype) {
        set(PROPERTY_EMPLOYMENTTYPE, employmenttype);
    }

    public Boolean isHasviolation() {
        return (Boolean) get(PROPERTY_HASVIOLATION);
    }

    public void setHasviolation(Boolean hasviolation) {
        set(PROPERTY_HASVIOLATION, hasviolation);
    }

}
