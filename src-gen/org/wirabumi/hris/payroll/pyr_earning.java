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
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.wirabumi.hris.employee.master.data.HRISEarningTemplate;
import org.wirabumi.hris.employee.master.data.hris_benefits;
import org.wirabumi.hris.employee.master.data.hris_insurance_earning;
import org.wirabumi.hris.employee.master.data.hris_rappel_salary;
import org.wirabumi.hris.employee.master.data.hris_reimbursment;
/**
 * Entity class for entity pyr_earning (stored in table pyr_earning).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class pyr_earning extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "pyr_earning";
    public static final String ENTITY_NAME = "pyr_earning";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_PRINTTEXT = "printText";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_MONTHLY = "monthly";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_PAYMENTGROUP = "paymentGroup";
    public static final String PROPERTY_PPHTAXMODE = "pphTaxmode";
    public static final String PROPERTY_PPHISANNUALIZED = "pphIsannualized";
    public static final String PROPERTY_FORMULA = "formula";
    public static final String PROPERTY_CLASSNAME = "classname";
    public static final String PROPERTY_EARNINGGROUP = "earningGroup";
    public static final String PROPERTY_HRISEARNINGTEMPLATELIST = "hRISEarningTemplateList";
    public static final String PROPERTY_HRISBENEFITSLIST = "hrisBenefitsList";
    public static final String PROPERTY_HRISINSURANCEEARNINGLIST = "hrisInsuranceEarningList";
    public static final String PROPERTY_HRISRAPPELSALARYRAPPELEARNINGIDLIST = "hrisRappelSalaryRappelEarningIDList";
    public static final String PROPERTY_HRISRAPPELSALARYLIST = "hrisRappelSalaryList";
    public static final String PROPERTY_HRISREIMBURSMENTLIST = "hrisReimbursmentList";
    public static final String PROPERTY_PYRBPEARNINGLIST = "pyrBpEarningList";
    public static final String PROPERTY_PYRINCIDENTALEARNINGLIST = "pyrIncidentalEarningList";
    public static final String PROPERTY_PYRSPEEARNINGLIST = "pyrSpeEarningList";

    public pyr_earning() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TYPE, "CONST");
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_MONTHLY, true);
        setDefaultValue(PROPERTY_PPHISANNUALIZED, true);
        setDefaultValue(PROPERTY_HRISEARNINGTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISBENEFITSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISINSURANCEEARNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRAPPELSALARYRAPPELEARNINGIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRAPPELSALARYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISREIMBURSMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRBPEARNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRINCIDENTALEARNINGLIST, new ArrayList<Object>());
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

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public String getPrintText() {
        return (String) get(PROPERTY_PRINTTEXT);
    }

    public void setPrintText(String printText) {
        set(PROPERTY_PRINTTEXT, printText);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
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

    public GLItem getAccount() {
        return (GLItem) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(GLItem account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public Boolean isMonthly() {
        return (Boolean) get(PROPERTY_MONTHLY);
    }

    public void setMonthly(Boolean monthly) {
        set(PROPERTY_MONTHLY, monthly);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getPaymentGroup() {
        return (String) get(PROPERTY_PAYMENTGROUP);
    }

    public void setPaymentGroup(String paymentGroup) {
        set(PROPERTY_PAYMENTGROUP, paymentGroup);
    }

    public String getPphTaxmode() {
        return (String) get(PROPERTY_PPHTAXMODE);
    }

    public void setPphTaxmode(String pphTaxmode) {
        set(PROPERTY_PPHTAXMODE, pphTaxmode);
    }

    public Boolean isPphIsannualized() {
        return (Boolean) get(PROPERTY_PPHISANNUALIZED);
    }

    public void setPphIsannualized(Boolean pphIsannualized) {
        set(PROPERTY_PPHISANNUALIZED, pphIsannualized);
    }

    public String getFormula() {
        return (String) get(PROPERTY_FORMULA);
    }

    public void setFormula(String formula) {
        set(PROPERTY_FORMULA, formula);
    }

    public String getClassname() {
        return (String) get(PROPERTY_CLASSNAME);
    }

    public void setClassname(String classname) {
        set(PROPERTY_CLASSNAME, classname);
    }

    public EarnigGroup getEarningGroup() {
        return (EarnigGroup) get(PROPERTY_EARNINGGROUP);
    }

    public void setEarningGroup(EarnigGroup earningGroup) {
        set(PROPERTY_EARNINGGROUP, earningGroup);
    }

    @SuppressWarnings("unchecked")
    public List<HRISEarningTemplate> getHRISEarningTemplateList() {
      return (List<HRISEarningTemplate>) get(PROPERTY_HRISEARNINGTEMPLATELIST);
    }

    public void setHRISEarningTemplateList(List<HRISEarningTemplate> hRISEarningTemplateList) {
        set(PROPERTY_HRISEARNINGTEMPLATELIST, hRISEarningTemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_benefits> getHrisBenefitsList() {
      return (List<hris_benefits>) get(PROPERTY_HRISBENEFITSLIST);
    }

    public void setHrisBenefitsList(List<hris_benefits> hrisBenefitsList) {
        set(PROPERTY_HRISBENEFITSLIST, hrisBenefitsList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_insurance_earning> getHrisInsuranceEarningList() {
      return (List<hris_insurance_earning>) get(PROPERTY_HRISINSURANCEEARNINGLIST);
    }

    public void setHrisInsuranceEarningList(List<hris_insurance_earning> hrisInsuranceEarningList) {
        set(PROPERTY_HRISINSURANCEEARNINGLIST, hrisInsuranceEarningList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_rappel_salary> getHrisRappelSalaryRappelEarningIDList() {
      return (List<hris_rappel_salary>) get(PROPERTY_HRISRAPPELSALARYRAPPELEARNINGIDLIST);
    }

    public void setHrisRappelSalaryRappelEarningIDList(List<hris_rappel_salary> hrisRappelSalaryRappelEarningIDList) {
        set(PROPERTY_HRISRAPPELSALARYRAPPELEARNINGIDLIST, hrisRappelSalaryRappelEarningIDList);
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
    public List<pyr_bp_earning> getPyrBpEarningList() {
      return (List<pyr_bp_earning>) get(PROPERTY_PYRBPEARNINGLIST);
    }

    public void setPyrBpEarningList(List<pyr_bp_earning> pyrBpEarningList) {
        set(PROPERTY_PYRBPEARNINGLIST, pyrBpEarningList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_incidental_earning> getPyrIncidentalEarningList() {
      return (List<pyr_incidental_earning>) get(PROPERTY_PYRINCIDENTALEARNINGLIST);
    }

    public void setPyrIncidentalEarningList(List<pyr_incidental_earning> pyrIncidentalEarningList) {
        set(PROPERTY_PYRINCIDENTALEARNINGLIST, pyrIncidentalEarningList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_spe_earning> getPyrSpeEarningList() {
      return (List<pyr_spe_earning>) get(PROPERTY_PYRSPEEARNINGLIST);
    }

    public void setPyrSpeEarningList(List<pyr_spe_earning> pyrSpeEarningList) {
        set(PROPERTY_PYRSPEEARNINGLIST, pyrSpeEarningList);
    }

}
