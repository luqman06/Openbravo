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
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.wirabumi.hris.employee.master.data.hris_benefits;
import org.wirabumi.hris.employee.master.data.hris_rappel_salary;
import org.wirabumi.hris.employee.master.data.hris_reimbursment;
import org.wirabumi.hris.overtime.ot_emergency_call;
import org.wirabumi.hris.overtime.ot_overtime;
/**
 * Entity class for entity pyr_salarypayment (stored in table pyr_salarypayment).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class pyr_salarypayment extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "pyr_salarypayment";
    public static final String ENTITY_NAME = "pyr_salarypayment";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_EFFECTIVEDATE = "effectiveDate";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_MONTHLY = "monthly";
    public static final String PROPERTY_BONUS = "bonus";
    public static final String PROPERTY_PERFORMANCEINSENTIVE = "performanceInsentive";
    public static final String PROPERTY_OTHERINSENTIVE = "otherInsentive";
    public static final String PROPERTY_PERFORMANCEDISINSENTIVE = "performanceDisinsentive";
    public static final String PROPERTY_OTHERDISINSENTIVE = "otherDisinsentive";
    public static final String PROPERTY_GENERATESALARYPAYMENT = "generatesalarypayment";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_PAYROLLMASTER = "payrollmaster";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_PAYMENTGROUP = "paymentGroup";
    public static final String PROPERTY_BRPDOINCOMETAX = "bRPDoincometax";
    public static final String PROPERTY_CALCULATESALARYFORMULA = "calculatesalaryformula";
    public static final String PROPERTY_PPHPAJAKRAMPUNG = "pPHPajakRampung";
    public static final String PROPERTY_PPHPREVSALARYPAYMENT = "pphPrevsalarypayment";
    public static final String PROPERTY_HRISBENEFITSLIST = "hrisBenefitsList";
    public static final String PROPERTY_HRISRAPPELSALARYLIST = "hrisRappelSalaryList";
    public static final String PROPERTY_HRISREIMBURSMENTLIST = "hrisReimbursmentList";
    public static final String PROPERTY_OTEMERGENCYCALLLIST = "otEmergencyCallList";
    public static final String PROPERTY_OTOVERTIMELIST = "otOvertimeList";
    public static final String PROPERTY_PYRINCIDENTALDEDUCTIONLIST = "pyrIncidentalDeductionList";
    public static final String PROPERTY_PYRINCIDENTALEARNINGLIST = "pyrIncidentalEarningList";
    public static final String PROPERTY_PYRSALARYPAYMENTEMPPHPREVSALARYPAYMENTIDLIST = "pyrSalarypaymentEMPphPrevsalarypaymentIDList";
    public static final String PROPERTY_PYRSPEMPLOYEELIST = "pyrSpEmployeeList";

    public pyr_salarypayment() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MONTHLY, true);
        setDefaultValue(PROPERTY_BONUS, false);
        setDefaultValue(PROPERTY_PERFORMANCEINSENTIVE, false);
        setDefaultValue(PROPERTY_OTHERINSENTIVE, false);
        setDefaultValue(PROPERTY_PERFORMANCEDISINSENTIVE, false);
        setDefaultValue(PROPERTY_OTHERDISINSENTIVE, false);
        setDefaultValue(PROPERTY_GENERATESALARYPAYMENT, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_DOCUMENTACTION, "CO");
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_BRPDOINCOMETAX, false);
        setDefaultValue(PROPERTY_CALCULATESALARYFORMULA, false);
        setDefaultValue(PROPERTY_PPHPAJAKRAMPUNG, false);
        setDefaultValue(PROPERTY_HRISBENEFITSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRAPPELSALARYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISREIMBURSMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OTEMERGENCYCALLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OTOVERTIMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRINCIDENTALDEDUCTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRINCIDENTALEARNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSALARYPAYMENTEMPPHPREVSALARYPAYMENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSPEMPLOYEELIST, new ArrayList<Object>());
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

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
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

    public Date getEffectiveDate() {
        return (Date) get(PROPERTY_EFFECTIVEDATE);
    }

    public void setEffectiveDate(Date effectiveDate) {
        set(PROPERTY_EFFECTIVEDATE, effectiveDate);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public Boolean isMonthly() {
        return (Boolean) get(PROPERTY_MONTHLY);
    }

    public void setMonthly(Boolean monthly) {
        set(PROPERTY_MONTHLY, monthly);
    }

    public Boolean isBonus() {
        return (Boolean) get(PROPERTY_BONUS);
    }

    public void setBonus(Boolean bonus) {
        set(PROPERTY_BONUS, bonus);
    }

    public Boolean isPerformanceInsentive() {
        return (Boolean) get(PROPERTY_PERFORMANCEINSENTIVE);
    }

    public void setPerformanceInsentive(Boolean performanceInsentive) {
        set(PROPERTY_PERFORMANCEINSENTIVE, performanceInsentive);
    }

    public Boolean isOtherInsentive() {
        return (Boolean) get(PROPERTY_OTHERINSENTIVE);
    }

    public void setOtherInsentive(Boolean otherInsentive) {
        set(PROPERTY_OTHERINSENTIVE, otherInsentive);
    }

    public Boolean isPerformanceDisinsentive() {
        return (Boolean) get(PROPERTY_PERFORMANCEDISINSENTIVE);
    }

    public void setPerformanceDisinsentive(Boolean performanceDisinsentive) {
        set(PROPERTY_PERFORMANCEDISINSENTIVE, performanceDisinsentive);
    }

    public Boolean isOtherDisinsentive() {
        return (Boolean) get(PROPERTY_OTHERDISINSENTIVE);
    }

    public void setOtherDisinsentive(Boolean otherDisinsentive) {
        set(PROPERTY_OTHERDISINSENTIVE, otherDisinsentive);
    }

    public Boolean isGeneratesalarypayment() {
        return (Boolean) get(PROPERTY_GENERATESALARYPAYMENT);
    }

    public void setGeneratesalarypayment(Boolean generatesalarypayment) {
        set(PROPERTY_GENERATESALARYPAYMENT, generatesalarypayment);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BusinessPartner getPayrollmaster() {
        return (BusinessPartner) get(PROPERTY_PAYROLLMASTER);
    }

    public void setPayrollmaster(BusinessPartner payrollmaster) {
        set(PROPERTY_PAYROLLMASTER, payrollmaster);
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

    public String getPaymentGroup() {
        return (String) get(PROPERTY_PAYMENTGROUP);
    }

    public void setPaymentGroup(String paymentGroup) {
        set(PROPERTY_PAYMENTGROUP, paymentGroup);
    }

    public Boolean isBRPDoincometax() {
        return (Boolean) get(PROPERTY_BRPDOINCOMETAX);
    }

    public void setBRPDoincometax(Boolean bRPDoincometax) {
        set(PROPERTY_BRPDOINCOMETAX, bRPDoincometax);
    }

    public Boolean isCalculatesalaryformula() {
        return (Boolean) get(PROPERTY_CALCULATESALARYFORMULA);
    }

    public void setCalculatesalaryformula(Boolean calculatesalaryformula) {
        set(PROPERTY_CALCULATESALARYFORMULA, calculatesalaryformula);
    }

    public Boolean isPPHPajakRampung() {
        return (Boolean) get(PROPERTY_PPHPAJAKRAMPUNG);
    }

    public void setPPHPajakRampung(Boolean pPHPajakRampung) {
        set(PROPERTY_PPHPAJAKRAMPUNG, pPHPajakRampung);
    }

    public pyr_salarypayment getPphPrevsalarypayment() {
        return (pyr_salarypayment) get(PROPERTY_PPHPREVSALARYPAYMENT);
    }

    public void setPphPrevsalarypayment(pyr_salarypayment pphPrevsalarypayment) {
        set(PROPERTY_PPHPREVSALARYPAYMENT, pphPrevsalarypayment);
    }

    @SuppressWarnings("unchecked")
    public List<hris_benefits> getHrisBenefitsList() {
      return (List<hris_benefits>) get(PROPERTY_HRISBENEFITSLIST);
    }

    public void setHrisBenefitsList(List<hris_benefits> hrisBenefitsList) {
        set(PROPERTY_HRISBENEFITSLIST, hrisBenefitsList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_rappel_salary> getHrisRappelSalaryList() {
      return (List<hris_rappel_salary>) get(PROPERTY_HRISRAPPELSALARYLIST);
    }

    public void setHrisRappelSalaryList(List<hris_rappel_salary> hrisRappelSalaryList) {
        set(PROPERTY_HRISRAPPELSALARYLIST, hrisRappelSalaryList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_reimbursment> getHrisReimbursmentList() {
      return (List<hris_reimbursment>) get(PROPERTY_HRISREIMBURSMENTLIST);
    }

    public void setHrisReimbursmentList(List<hris_reimbursment> hrisReimbursmentList) {
        set(PROPERTY_HRISREIMBURSMENTLIST, hrisReimbursmentList);
    }

    @SuppressWarnings("unchecked")
    public List<ot_emergency_call> getOtEmergencyCallList() {
      return (List<ot_emergency_call>) get(PROPERTY_OTEMERGENCYCALLLIST);
    }

    public void setOtEmergencyCallList(List<ot_emergency_call> otEmergencyCallList) {
        set(PROPERTY_OTEMERGENCYCALLLIST, otEmergencyCallList);
    }

    @SuppressWarnings("unchecked")
    public List<ot_overtime> getOtOvertimeList() {
      return (List<ot_overtime>) get(PROPERTY_OTOVERTIMELIST);
    }

    public void setOtOvertimeList(List<ot_overtime> otOvertimeList) {
        set(PROPERTY_OTOVERTIMELIST, otOvertimeList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_incidental_deduction> getPyrIncidentalDeductionList() {
      return (List<pyr_incidental_deduction>) get(PROPERTY_PYRINCIDENTALDEDUCTIONLIST);
    }

    public void setPyrIncidentalDeductionList(List<pyr_incidental_deduction> pyrIncidentalDeductionList) {
        set(PROPERTY_PYRINCIDENTALDEDUCTIONLIST, pyrIncidentalDeductionList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_incidental_earning> getPyrIncidentalEarningList() {
      return (List<pyr_incidental_earning>) get(PROPERTY_PYRINCIDENTALEARNINGLIST);
    }

    public void setPyrIncidentalEarningList(List<pyr_incidental_earning> pyrIncidentalEarningList) {
        set(PROPERTY_PYRINCIDENTALEARNINGLIST, pyrIncidentalEarningList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_salarypayment> getPyrSalarypaymentEMPphPrevsalarypaymentIDList() {
      return (List<pyr_salarypayment>) get(PROPERTY_PYRSALARYPAYMENTEMPPHPREVSALARYPAYMENTIDLIST);
    }

    public void setPyrSalarypaymentEMPphPrevsalarypaymentIDList(List<pyr_salarypayment> pyrSalarypaymentEMPphPrevsalarypaymentIDList) {
        set(PROPERTY_PYRSALARYPAYMENTEMPPHPREVSALARYPAYMENTIDLIST, pyrSalarypaymentEMPphPrevsalarypaymentIDList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_sp_employee> getPyrSpEmployeeList() {
      return (List<pyr_sp_employee>) get(PROPERTY_PYRSPEMPLOYEELIST);
    }

    public void setPyrSpEmployeeList(List<pyr_sp_employee> pyrSpEmployeeList) {
        set(PROPERTY_PYRSPEMPLOYEELIST, pyrSpEmployeeList);
    }

}
