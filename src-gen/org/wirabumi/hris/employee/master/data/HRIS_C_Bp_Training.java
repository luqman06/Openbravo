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

import java.math.BigDecimal;
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
/**
 * Entity class for entity HRIS_C_Bp_Training (stored in table HRIS_C_Bp_Training).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class HRIS_C_Bp_Training extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "HRIS_C_Bp_Training";
    public static final String ENTITY_NAME = "HRIS_C_Bp_Training";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_VALIDFROM = "validFrom";
    public static final String PROPERTY_VALIDTO = "validTo";
    public static final String PROPERTY_GRADENOTE = "gradenote";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_REMARK = "remark";
    public static final String PROPERTY_TRAINING = "training";
    public static final String PROPERTY_GRADE = "grade";
    public static final String PROPERTY_HRISPRETEST = "hrisPretest";
    public static final String PROPERTY_HRISPOSTEST = "hrisPostest";
    public static final String PROPERTY_HRISSCORE = "hrisScore";

    public HRIS_C_Bp_Training() {
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

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public Date getValidFrom() {
        return (Date) get(PROPERTY_VALIDFROM);
    }

    public void setValidFrom(Date validFrom) {
        set(PROPERTY_VALIDFROM, validFrom);
    }

    public Date getValidTo() {
        return (Date) get(PROPERTY_VALIDTO);
    }

    public void setValidTo(Date validTo) {
        set(PROPERTY_VALIDTO, validTo);
    }

    public String getGradenote() {
        return (String) get(PROPERTY_GRADENOTE);
    }

    public void setGradenote(String gradenote) {
        set(PROPERTY_GRADENOTE, gradenote);
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

    public String getRemark() {
        return (String) get(PROPERTY_REMARK);
    }

    public void setRemark(String remark) {
        set(PROPERTY_REMARK, remark);
    }

    public hris_training getTraining() {
        return (hris_training) get(PROPERTY_TRAINING);
    }

    public void setTraining(hris_training training) {
        set(PROPERTY_TRAINING, training);
    }

    public BigDecimal getGrade() {
        return (BigDecimal) get(PROPERTY_GRADE);
    }

    public void setGrade(BigDecimal grade) {
        set(PROPERTY_GRADE, grade);
    }

    public Long getHrisPretest() {
        return (Long) get(PROPERTY_HRISPRETEST);
    }

    public void setHrisPretest(Long hrisPretest) {
        set(PROPERTY_HRISPRETEST, hrisPretest);
    }

    public Long getHrisPostest() {
        return (Long) get(PROPERTY_HRISPOSTEST);
    }

    public void setHrisPostest(Long hrisPostest) {
        set(PROPERTY_HRISPOSTEST, hrisPostest);
    }

    public Long getHrisScore() {
        return (Long) get(PROPERTY_HRISSCORE);
    }

    public void setHrisScore(Long hrisScore) {
        set(PROPERTY_HRISSCORE, hrisScore);
    }

}
