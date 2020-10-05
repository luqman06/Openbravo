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
 * Entity class for entity hris_r_line (stored in table hris_r_line).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_r_line extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_r_line";
    public static final String ENTITY_NAME = "hris_r_line";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_BIRTHPLACE = "birthPlace";
    public static final String PROPERTY_BIRTHDAY = "birthday";
    public static final String PROPERTY_JOINDATE = "joindate";
    public static final String PROPERTY_YEARSOFSERVICE = "yearsOfService";
    public static final String PROPERTY_MONTHOFSERVICE = "monthofservice";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_EMPLOYEEGRADE = "employeeGrade";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_JOBTITLE = "jobtitle";
    public static final String PROPERTY_EMPLOYEMENTTYPE = "employementType";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_DELAY = "delay";
    public static final String PROPERTY_DATEDELAY = "dateDelay";
    public static final String PROPERTY_HRISRETIREMENT = "hrisRetirement";
    public static final String PROPERTY_CONTRACTTYPE = "contractType";
    public static final String PROPERTY_CONTRACT = "contract";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_HASVIOLATION = "hasviolation";

    public hris_r_line() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DELAY, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
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

    public String getBirthPlace() {
        return (String) get(PROPERTY_BIRTHPLACE);
    }

    public void setBirthPlace(String birthPlace) {
        set(PROPERTY_BIRTHPLACE, birthPlace);
    }

    public Date getBirthday() {
        return (Date) get(PROPERTY_BIRTHDAY);
    }

    public void setBirthday(Date birthday) {
        set(PROPERTY_BIRTHDAY, birthday);
    }

    public Date getJoindate() {
        return (Date) get(PROPERTY_JOINDATE);
    }

    public void setJoindate(Date joindate) {
        set(PROPERTY_JOINDATE, joindate);
    }

    public Long getYearsOfService() {
        return (Long) get(PROPERTY_YEARSOFSERVICE);
    }

    public void setYearsOfService(Long yearsOfService) {
        set(PROPERTY_YEARSOFSERVICE, yearsOfService);
    }

    public Long getMonthofservice() {
        return (Long) get(PROPERTY_MONTHOFSERVICE);
    }

    public void setMonthofservice(Long monthofservice) {
        set(PROPERTY_MONTHOFSERVICE, monthofservice);
    }

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
    }

    public String getEmployeeGrade() {
        return (String) get(PROPERTY_EMPLOYEEGRADE);
    }

    public void setEmployeeGrade(String employeeGrade) {
        set(PROPERTY_EMPLOYEEGRADE, employeeGrade);
    }

    public EmployeePosition getPosition() {
        return (EmployeePosition) get(PROPERTY_POSITION);
    }

    public void setPosition(EmployeePosition position) {
        set(PROPERTY_POSITION, position);
    }

    public CostCenter getCostCenter() {
        return (CostCenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(CostCenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public hris_jobtitle getJobtitle() {
        return (hris_jobtitle) get(PROPERTY_JOBTITLE);
    }

    public void setJobtitle(hris_jobtitle jobtitle) {
        set(PROPERTY_JOBTITLE, jobtitle);
    }

    public String getEmployementType() {
        return (String) get(PROPERTY_EMPLOYEMENTTYPE);
    }

    public void setEmployementType(String employementType) {
        set(PROPERTY_EMPLOYEMENTTYPE, employementType);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public Boolean isDelay() {
        return (Boolean) get(PROPERTY_DELAY);
    }

    public void setDelay(Boolean delay) {
        set(PROPERTY_DELAY, delay);
    }

    public Date getDateDelay() {
        return (Date) get(PROPERTY_DATEDELAY);
    }

    public void setDateDelay(Date dateDelay) {
        set(PROPERTY_DATEDELAY, dateDelay);
    }

    public hris_retirement getHrisRetirement() {
        return (hris_retirement) get(PROPERTY_HRISRETIREMENT);
    }

    public void setHrisRetirement(hris_retirement hrisRetirement) {
        set(PROPERTY_HRISRETIREMENT, hrisRetirement);
    }

    public String getContractType() {
        return (String) get(PROPERTY_CONTRACTTYPE);
    }

    public void setContractType(String contractType) {
        set(PROPERTY_CONTRACTTYPE, contractType);
    }

    public HRIS_C_Bp_Empinfo getContract() {
        return (HRIS_C_Bp_Empinfo) get(PROPERTY_CONTRACT);
    }

    public void setContract(HRIS_C_Bp_Empinfo contract) {
        set(PROPERTY_CONTRACT, contract);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Boolean isHasviolation() {
        return (Boolean) get(PROPERTY_HASVIOLATION);
    }

    public void setHasviolation(Boolean hasviolation) {
        set(PROPERTY_HASVIOLATION, hasviolation);
    }

}
