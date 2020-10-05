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
import org.wirabumi.hris.payroll.pyr_deduction;
import org.wirabumi.hris.payroll.pyr_earning;
import org.wirabumi.hris.payroll.pyr_salarypayment;
/**
 * Entity class for entity hris_rappel_salary (stored in table hris_rappel_salary).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_rappel_salary extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_rappel_salary";
    public static final String ENTITY_NAME = "hris_rappel_salary";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_CREATEPAYMENT = "createpayment";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_VALIDTODATE = "validToDate";
    public static final String PROPERTY_EFFECTIVEDATE = "effectiveDate";
    public static final String PROPERTY_VALIDATED = "validated";
    public static final String PROPERTY_VALIDATIONMESSAGE = "validationMessage";
    public static final String PROPERTY_INCLUDEINPAYROLL = "includeInPayroll";
    public static final String PROPERTY_PYRSALARYPAYMENT = "pYRSalarypayment";
    public static final String PROPERTY_RAPPELEARNING = "rappelEarning";
    public static final String PROPERTY_PYREARNING = "pYREarning";
    public static final String PROPERTY_HRISINCLUDEDIN = "hrisIncludedin";
    public static final String PROPERTY_RAPPELDEDUCTION = "rappelDeduction";
    public static final String PROPERTY_PYRDEDUCTION = "pYRDeduction";
    public static final String PROPERTY_PAYMENTTYPE = "paymentType";

    public hris_rappel_salary() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_DOCUMENTACTION, true);
        setDefaultValue(PROPERTY_CREATEPAYMENT, true);
        setDefaultValue(PROPERTY_VALIDATED, false);
        setDefaultValue(PROPERTY_INCLUDEINPAYROLL, false);
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

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
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

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Boolean isDocumentAction() {
        return (Boolean) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(Boolean documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public Boolean isCreatepayment() {
        return (Boolean) get(PROPERTY_CREATEPAYMENT);
    }

    public void setCreatepayment(Boolean createpayment) {
        set(PROPERTY_CREATEPAYMENT, createpayment);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
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

    public Date getEffectiveDate() {
        return (Date) get(PROPERTY_EFFECTIVEDATE);
    }

    public void setEffectiveDate(Date effectiveDate) {
        set(PROPERTY_EFFECTIVEDATE, effectiveDate);
    }

    public Boolean isValidated() {
        return (Boolean) get(PROPERTY_VALIDATED);
    }

    public void setValidated(Boolean validated) {
        set(PROPERTY_VALIDATED, validated);
    }

    public String getValidationMessage() {
        return (String) get(PROPERTY_VALIDATIONMESSAGE);
    }

    public void setValidationMessage(String validationMessage) {
        set(PROPERTY_VALIDATIONMESSAGE, validationMessage);
    }

    public Boolean isIncludeInPayroll() {
        return (Boolean) get(PROPERTY_INCLUDEINPAYROLL);
    }

    public void setIncludeInPayroll(Boolean includeInPayroll) {
        set(PROPERTY_INCLUDEINPAYROLL, includeInPayroll);
    }

    public pyr_salarypayment getPYRSalarypayment() {
        return (pyr_salarypayment) get(PROPERTY_PYRSALARYPAYMENT);
    }

    public void setPYRSalarypayment(pyr_salarypayment pYRSalarypayment) {
        set(PROPERTY_PYRSALARYPAYMENT, pYRSalarypayment);
    }

    public pyr_earning getRappelEarning() {
        return (pyr_earning) get(PROPERTY_RAPPELEARNING);
    }

    public void setRappelEarning(pyr_earning rappelEarning) {
        set(PROPERTY_RAPPELEARNING, rappelEarning);
    }

    public pyr_earning getPYREarning() {
        return (pyr_earning) get(PROPERTY_PYREARNING);
    }

    public void setPYREarning(pyr_earning pYREarning) {
        set(PROPERTY_PYREARNING, pYREarning);
    }

    public String getHrisIncludedin() {
        return (String) get(PROPERTY_HRISINCLUDEDIN);
    }

    public void setHrisIncludedin(String hrisIncludedin) {
        set(PROPERTY_HRISINCLUDEDIN, hrisIncludedin);
    }

    public pyr_deduction getRappelDeduction() {
        return (pyr_deduction) get(PROPERTY_RAPPELDEDUCTION);
    }

    public void setRappelDeduction(pyr_deduction rappelDeduction) {
        set(PROPERTY_RAPPELDEDUCTION, rappelDeduction);
    }

    public pyr_deduction getPYRDeduction() {
        return (pyr_deduction) get(PROPERTY_PYRDEDUCTION);
    }

    public void setPYRDeduction(pyr_deduction pYRDeduction) {
        set(PROPERTY_PYRDEDUCTION, pYRDeduction);
    }

    public String getPaymentType() {
        return (String) get(PROPERTY_PAYMENTTYPE);
    }

    public void setPaymentType(String paymentType) {
        set(PROPERTY_PAYMENTTYPE, paymentType);
    }

}
