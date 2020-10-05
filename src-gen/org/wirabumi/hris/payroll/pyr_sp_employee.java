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
package org.wirabumi.hris.payroll;

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
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.employee.master.data.hris_site;
import org.wirabumi.hris.pph.pph_pph21;
/**
 * Entity class for entity pyr_sp_employee (stored in table pyr_sp_employee).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class pyr_sp_employee extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "pyr_sp_employee";
    public static final String ENTITY_NAME = "pyr_sp_employee";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SALARYPAYMENT = "salaryPayment";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_JOBTITLE = "jobTitle";
    public static final String PROPERTY_EMPLOYEGRADE = "employeGrade";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_SITE = "site";
    public static final String PROPERTY_CALCULATESALARYFORMULA = "calculatesalaryformula";
    public static final String PROPERTY__COMPUTEDCOLUMNS = "_computedColumns";
    public static final String PROPERTY_PPHPPH21LIST = "pphPph21List";
    public static final String PROPERTY_PYRSPEDEDUCTIONLIST = "pyrSpeDeductionList";
    public static final String PROPERTY_PYRSPEEARNINGLIST = "pyrSpeEarningList";


    // Computed columns properties, these properties cannot be directly accessed, they need
    // to be read through _commputedColumns proxy. They cannot be directly used in HQL, OBQuery
    // nor OBCriteria. 
    public static final String COMPUTED_COLUMN_SUMMARY = "summary";

    public pyr_sp_employee() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CALCULATESALARYFORMULA, false);
        setDefaultValue(PROPERTY_PPHPPH21LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSPEDEDUCTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSPEEARNINGLIST, new ArrayList<Object>());
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
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

    public pyr_salarypayment getSalaryPayment() {
        return (pyr_salarypayment) get(PROPERTY_SALARYPAYMENT);
    }

    public void setSalaryPayment(pyr_salarypayment salaryPayment) {
        set(PROPERTY_SALARYPAYMENT, salaryPayment);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public String getPosition() {
        return (String) get(PROPERTY_POSITION);
    }

    public void setPosition(String position) {
        set(PROPERTY_POSITION, position);
    }

    public hris_jobtitle getJobTitle() {
        return (hris_jobtitle) get(PROPERTY_JOBTITLE);
    }

    public void setJobTitle(hris_jobtitle jobTitle) {
        set(PROPERTY_JOBTITLE, jobTitle);
    }

    public String getEmployeGrade() {
        return (String) get(PROPERTY_EMPLOYEGRADE);
    }

    public void setEmployeGrade(String employeGrade) {
        set(PROPERTY_EMPLOYEGRADE, employeGrade);
    }

    public Costcenter getCostCenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(Costcenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public hris_site getSite() {
        return (hris_site) get(PROPERTY_SITE);
    }

    public void setSite(hris_site site) {
        set(PROPERTY_SITE, site);
    }

    public Boolean isCalculatesalaryformula() {
        return (Boolean) get(PROPERTY_CALCULATESALARYFORMULA);
    }

    public void setCalculatesalaryformula(Boolean calculatesalaryformula) {
        set(PROPERTY_CALCULATESALARYFORMULA, calculatesalaryformula);
    }

    public BigDecimal getSummary() {
        return (BigDecimal) get(COMPUTED_COLUMN_SUMMARY);
    }

    public void setSummary(BigDecimal summary) {
        set(COMPUTED_COLUMN_SUMMARY, summary);
    }

    public pyr_sp_employee_ComputedColumns get_computedColumns() {
        return (pyr_sp_employee_ComputedColumns) get(PROPERTY__COMPUTEDCOLUMNS);
    }

    public void set_computedColumns(pyr_sp_employee_ComputedColumns _computedColumns) {
        set(PROPERTY__COMPUTEDCOLUMNS, _computedColumns);
    }

    @SuppressWarnings("unchecked")
    public List<pph_pph21> getPphPph21List() {
      return (List<pph_pph21>) get(PROPERTY_PPHPPH21LIST);
    }

    public void setPphPph21List(List<pph_pph21> pphPph21List) {
        set(PROPERTY_PPHPPH21LIST, pphPph21List);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_spe_deduction> getPyrSpeDeductionList() {
      return (List<pyr_spe_deduction>) get(PROPERTY_PYRSPEDEDUCTIONLIST);
    }

    public void setPyrSpeDeductionList(List<pyr_spe_deduction> pyrSpeDeductionList) {
        set(PROPERTY_PYRSPEDEDUCTIONLIST, pyrSpeDeductionList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_spe_earning> getPyrSpeEarningList() {
      return (List<pyr_spe_earning>) get(PROPERTY_PYRSPEEARNINGLIST);
    }

    public void setPyrSpeEarningList(List<pyr_spe_earning> pyrSpeEarningList) {
        set(PROPERTY_PYRSPEEARNINGLIST, pyrSpeEarningList);
    }


    @Override
    public Object get(String propName) {
      if (COMPUTED_COLUMN_SUMMARY.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getSummary();
      }
    
      return super.get(propName);
    }
}
