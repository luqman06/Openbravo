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
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.wirabumi.hris.payroll.pyr_deduction;
import org.wirabumi.hris.payroll.pyr_earning;
import org.wirabumi.hris.payroll.pyr_salarypayment;
/**
 * Entity class for entity hris_benefits (stored in table hris_benefits).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_benefits extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_benefits";
    public static final String ENTITY_NAME = "hris_benefits";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BENEFITTYPE = "benefitType";
    public static final String PROPERTY_EMPLOYEEID = "employeeID";
    public static final String PROPERTY_EMPLOYEEGRADE = "employeeGrade";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_JOBTITLE = "jobTitle";
    public static final String PROPERTY_COSTCENTER = "costCenter";
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
    public static final String PROPERTY_SALARYPAYMENT = "salaryPayment";
    public static final String PROPERTY_EARNING = "earning";
    public static final String PROPERTY_INCLUDEIN = "includeIn";
    public static final String PROPERTY_DEDUCTION = "deduction";
    public static final String PROPERTY_INSUREDPAYEE = "insuredPayee";
    public static final String PROPERTY_PAYMENTTYPE = "paymentType";
    public static final String PROPERTY_COVEREDAMOUNT = "coveredAmount";

    public hris_benefits() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_DOCUMENTACTION, "AP");
        setDefaultValue(PROPERTY_CREATEPAYMENT, true);
        setDefaultValue(PROPERTY_VALIDATED, false);
        setDefaultValue(PROPERTY_INCLUDEINPAYROLL, false);
        setDefaultValue(PROPERTY_COVEREDAMOUNT, new BigDecimal(0));
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

    public String getBenefitType() {
        return (String) get(PROPERTY_BENEFITTYPE);
    }

    public void setBenefitType(String benefitType) {
        set(PROPERTY_BENEFITTYPE, benefitType);
    }

    public String getEmployeeID() {
        return (String) get(PROPERTY_EMPLOYEEID);
    }

    public void setEmployeeID(String employeeID) {
        set(PROPERTY_EMPLOYEEID, employeeID);
    }

    public String getEmployeeGrade() {
        return (String) get(PROPERTY_EMPLOYEEGRADE);
    }

    public void setEmployeeGrade(String employeeGrade) {
        set(PROPERTY_EMPLOYEEGRADE, employeeGrade);
    }

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
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

    public CostCenter getCostCenter() {
        return (CostCenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(CostCenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
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

    public pyr_salarypayment getSalaryPayment() {
        return (pyr_salarypayment) get(PROPERTY_SALARYPAYMENT);
    }

    public void setSalaryPayment(pyr_salarypayment salaryPayment) {
        set(PROPERTY_SALARYPAYMENT, salaryPayment);
    }

    public pyr_earning getEarning() {
        return (pyr_earning) get(PROPERTY_EARNING);
    }

    public void setEarning(pyr_earning earning) {
        set(PROPERTY_EARNING, earning);
    }

    public String getIncludeIn() {
        return (String) get(PROPERTY_INCLUDEIN);
    }

    public void setIncludeIn(String includeIn) {
        set(PROPERTY_INCLUDEIN, includeIn);
    }

    public pyr_deduction getDeduction() {
        return (pyr_deduction) get(PROPERTY_DEDUCTION);
    }

    public void setDeduction(pyr_deduction deduction) {
        set(PROPERTY_DEDUCTION, deduction);
    }

    public hris_insured_payee getInsuredPayee() {
        return (hris_insured_payee) get(PROPERTY_INSUREDPAYEE);
    }

    public void setInsuredPayee(hris_insured_payee insuredPayee) {
        set(PROPERTY_INSUREDPAYEE, insuredPayee);
    }

    public String getPaymentType() {
        return (String) get(PROPERTY_PAYMENTTYPE);
    }

    public void setPaymentType(String paymentType) {
        set(PROPERTY_PAYMENTTYPE, paymentType);
    }

    public BigDecimal getCoveredAmount() {
        return (BigDecimal) get(PROPERTY_COVEREDAMOUNT);
    }

    public void setCoveredAmount(BigDecimal coveredAmount) {
        set(PROPERTY_COVEREDAMOUNT, coveredAmount);
    }

}
