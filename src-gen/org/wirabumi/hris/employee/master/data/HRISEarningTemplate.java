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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.hcm.SalaryCategory;
import org.wirabumi.hris.payroll.pyr_earning;
/**
 * Entity class for entity HRIS_Earning_Template (stored in table HRIS_Earning_Template).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class HRISEarningTemplate extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "HRIS_Earning_Template";
    public static final String ENTITY_NAME = "HRIS_Earning_Template";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_PYREARNING = "pYREarning";
    public static final String PROPERTY_SALARYCATEGORY = "salaryCategory";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_SALARYFORMULA = "salaryFormula";
    public static final String PROPERTY_JAVACLASSNAME = "javaClassname";

    public HRISEarningTemplate() {
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
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

    public pyr_earning getPYREarning() {
        return (pyr_earning) get(PROPERTY_PYREARNING);
    }

    public void setPYREarning(pyr_earning pYREarning) {
        set(PROPERTY_PYREARNING, pYREarning);
    }

    public SalaryCategory getSalaryCategory() {
        return (SalaryCategory) get(PROPERTY_SALARYCATEGORY);
    }

    public void setSalaryCategory(SalaryCategory salaryCategory) {
        set(PROPERTY_SALARYCATEGORY, salaryCategory);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
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

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public String getSalaryFormula() {
        return (String) get(PROPERTY_SALARYFORMULA);
    }

    public void setSalaryFormula(String salaryFormula) {
        set(PROPERTY_SALARYFORMULA, salaryFormula);
    }

    public String getJavaClassname() {
        return (String) get(PROPERTY_JAVACLASSNAME);
    }

    public void setJavaClassname(String javaClassname) {
        set(PROPERTY_JAVACLASSNAME, javaClassname);
    }

}
