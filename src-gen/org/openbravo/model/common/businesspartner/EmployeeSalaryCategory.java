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
package org.openbravo.model.common.businesspartner;

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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.hcm.SalaryCategory;
import org.wirabumi.hris.payroll.Pyr_Bp_Deduction;
import org.wirabumi.hris.payroll.pyr_bp_earning;
/**
 * Entity class for entity EmployeeSalaryCategory (stored in table C_BP_SALCATEGORY).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EmployeeSalaryCategory extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BP_SALCATEGORY";
    public static final String ENTITY_NAME = "EmployeeSalaryCategory";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_SALARYCATEGORY = "salaryCategory";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_PYRCOPYFROMTEMPLATE = "pyrCopyFromTemplate";
    public static final String PROPERTY_HRISVALIDTO = "hrisValidto";
    public static final String PROPERTY_PYRBPDEDUCTIONLIST = "pyrBpDeductionList";
    public static final String PROPERTY_PYRBPEARNINGLIST = "pyrBpEarningList";

    public EmployeeSalaryCategory() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_PYRCOPYFROMTEMPLATE, false);
        setDefaultValue(PROPERTY_PYRBPDEDUCTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRBPEARNINGLIST, new ArrayList<Object>());
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public SalaryCategory getSalaryCategory() {
        return (SalaryCategory) get(PROPERTY_SALARYCATEGORY);
    }

    public void setSalaryCategory(SalaryCategory salaryCategory) {
        set(PROPERTY_SALARYCATEGORY, salaryCategory);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Boolean isPyrCopyFromTemplate() {
        return (Boolean) get(PROPERTY_PYRCOPYFROMTEMPLATE);
    }

    public void setPyrCopyFromTemplate(Boolean pyrCopyFromTemplate) {
        set(PROPERTY_PYRCOPYFROMTEMPLATE, pyrCopyFromTemplate);
    }

    public Date getHrisValidto() {
        return (Date) get(PROPERTY_HRISVALIDTO);
    }

    public void setHrisValidto(Date hrisValidto) {
        set(PROPERTY_HRISVALIDTO, hrisValidto);
    }

    @SuppressWarnings("unchecked")
    public List<Pyr_Bp_Deduction> getPyrBpDeductionList() {
      return (List<Pyr_Bp_Deduction>) get(PROPERTY_PYRBPDEDUCTIONLIST);
    }

    public void setPyrBpDeductionList(List<Pyr_Bp_Deduction> pyrBpDeductionList) {
        set(PROPERTY_PYRBPDEDUCTIONLIST, pyrBpDeductionList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_bp_earning> getPyrBpEarningList() {
      return (List<pyr_bp_earning>) get(PROPERTY_PYRBPEARNINGLIST);
    }

    public void setPyrBpEarningList(List<pyr_bp_earning> pyrBpEarningList) {
        set(PROPERTY_PYRBPEARNINGLIST, pyrBpEarningList);
    }

}
