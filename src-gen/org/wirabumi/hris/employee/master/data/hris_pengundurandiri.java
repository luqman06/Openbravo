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
 * Entity class for entity hris_pengundurandiri (stored in table hris_pengundurandiri).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_pengundurandiri extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_pengundurandiri";
    public static final String ENTITY_NAME = "hris_pengundurandiri";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_VALID = "valid";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_JOBTITLE = "jobTitle";
    public static final String PROPERTY_JOINDATE = "joindate";
    public static final String PROPERTY_CONTRACTNO = "contractno";
    public static final String PROPERTY_DOCDATE = "docdate";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_YEAROFSERVICE = "yearofservice";
    public static final String PROPERTY_EMPLOYEMENTTYPE = "employementtype";
    public static final String PROPERTY_MONTHOFSERVICE = "monthofservice";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_GOLPERUM = "golPerum";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_DESCRIPTIONX = "descriptionx";
    public static final String PROPERTY_CONTRACTTYPE = "contractType";
    public static final String PROPERTY_POBLISHCONTRACT = "poblishcontract";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_TERBITKANNOSK = "terbitkanNoSK";
    public static final String PROPERTY_HRISCONTRACTFK = "hrisContractFk";
    public static final String PROPERTY_HASVIOLATION = "hasviolation";

    public hris_pengundurandiri() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_VALID, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_POBLISHCONTRACT, false);
        setDefaultValue(PROPERTY_TERBITKANNOSK, false);
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

    public Boolean isValid() {
        return (Boolean) get(PROPERTY_VALID);
    }

    public void setValid(Boolean valid) {
        set(PROPERTY_VALID, valid);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public CostCenter getCostCenter() {
        return (CostCenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(CostCenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public hris_jobtitle getJobTitle() {
        return (hris_jobtitle) get(PROPERTY_JOBTITLE);
    }

    public void setJobTitle(hris_jobtitle jobTitle) {
        set(PROPERTY_JOBTITLE, jobTitle);
    }

    public Date getJoindate() {
        return (Date) get(PROPERTY_JOINDATE);
    }

    public void setJoindate(Date joindate) {
        set(PROPERTY_JOINDATE, joindate);
    }

    public String getContractno() {
        return (String) get(PROPERTY_CONTRACTNO);
    }

    public void setContractno(String contractno) {
        set(PROPERTY_CONTRACTNO, contractno);
    }

    public Date getDocdate() {
        return (Date) get(PROPERTY_DOCDATE);
    }

    public void setDocdate(Date docdate) {
        set(PROPERTY_DOCDATE, docdate);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public Long getYearofservice() {
        return (Long) get(PROPERTY_YEAROFSERVICE);
    }

    public void setYearofservice(Long yearofservice) {
        set(PROPERTY_YEAROFSERVICE, yearofservice);
    }

    public String getEmployementtype() {
        return (String) get(PROPERTY_EMPLOYEMENTTYPE);
    }

    public void setEmployementtype(String employementtype) {
        set(PROPERTY_EMPLOYEMENTTYPE, employementtype);
    }

    public Long getMonthofservice() {
        return (Long) get(PROPERTY_MONTHOFSERVICE);
    }

    public void setMonthofservice(Long monthofservice) {
        set(PROPERTY_MONTHOFSERVICE, monthofservice);
    }

    public EmployeePosition getPosition() {
        return (EmployeePosition) get(PROPERTY_POSITION);
    }

    public void setPosition(EmployeePosition position) {
        set(PROPERTY_POSITION, position);
    }

    public String getGolPerum() {
        return (String) get(PROPERTY_GOLPERUM);
    }

    public void setGolPerum(String golPerum) {
        set(PROPERTY_GOLPERUM, golPerum);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
    }

    public String getDescriptionx() {
        return (String) get(PROPERTY_DESCRIPTIONX);
    }

    public void setDescriptionx(String descriptionx) {
        set(PROPERTY_DESCRIPTIONX, descriptionx);
    }

    public String getContractType() {
        return (String) get(PROPERTY_CONTRACTTYPE);
    }

    public void setContractType(String contractType) {
        set(PROPERTY_CONTRACTTYPE, contractType);
    }

    public Boolean isPoblishcontract() {
        return (Boolean) get(PROPERTY_POBLISHCONTRACT);
    }

    public void setPoblishcontract(Boolean poblishcontract) {
        set(PROPERTY_POBLISHCONTRACT, poblishcontract);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public Boolean isTerbitkanNoSK() {
        return (Boolean) get(PROPERTY_TERBITKANNOSK);
    }

    public void setTerbitkanNoSK(Boolean terbitkanNoSK) {
        set(PROPERTY_TERBITKANNOSK, terbitkanNoSK);
    }

    public HRIS_C_Bp_Empinfo getHrisContractFk() {
        return (HRIS_C_Bp_Empinfo) get(PROPERTY_HRISCONTRACTFK);
    }

    public void setHrisContractFk(HRIS_C_Bp_Empinfo hrisContractFk) {
        set(PROPERTY_HRISCONTRACTFK, hrisContractFk);
    }

    public Boolean isHasviolation() {
        return (Boolean) get(PROPERTY_HASVIOLATION);
    }

    public void setHasviolation(Boolean hasviolation) {
        set(PROPERTY_HASVIOLATION, hasviolation);
    }

}
