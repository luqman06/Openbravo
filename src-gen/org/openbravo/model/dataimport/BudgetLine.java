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
package org.openbravo.model.dataimport;

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
import org.openbravo.model.common.businesspartner.Category;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductCategory;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.Budget;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.project.Project;
import org.openbravo.model.sales.SalesRegion;
/**
 * Entity class for entity DataImportBudgetLine (stored in table I_Budgetline).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class BudgetLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_Budgetline";
    public static final String ENTITY_NAME = "DataImportBudgetLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUDGETLINE = "budgetLine";
    public static final String PROPERTY_ACCOUNTELEMENT = "accountElement";
    public static final String PROPERTY_ELEMENTVALUEIDENT = "elementvalueident";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_PERIODIDENTIFICATION = "periodIdentification";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_PRODUCTIDENTIFICATION = "productIdentification";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PARTNERIDENTIFICATION = "partnerIdentification";
    public static final String PROPERTY_TRXORGANIZATION = "trxOrganization";
    public static final String PROPERTY_ORGANIZATIONTRXIDENTIFICATION = "organizationTrxIdentification";
    public static final String PROPERTY_SALESREGION = "salesRegion";
    public static final String PROPERTY_SALESREGIONIDENTIFICATION = "salesRegionIdentification";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_PROJECTIDENTIFICATION = "projectIdentification";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_CAMPAIGNIDENTIFICATION = "campaignIdentification";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_ACTIVITYIDENTIFICATION = "activityIdentification";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORY = "businessPartnerCategory";
    public static final String PROPERTY_BPGROUPIDENTITIFICATION = "bPGroupIdentitification";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_PRODUCTCATEGORYID = "productCategoryID";
    public static final String PROPERTY_BUDGET = "budget";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_PRICE = "price";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_CURRENCYIDENTIFICATION = "currencyIdentification";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_ACCTSCHEMAIDENT = "acctschemaident";

    public BudgetLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
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

    public org.openbravo.model.financialmgmt.accounting.BudgetLine getBudgetLine() {
        return (org.openbravo.model.financialmgmt.accounting.BudgetLine) get(PROPERTY_BUDGETLINE);
    }

    public void setBudgetLine(org.openbravo.model.financialmgmt.accounting.BudgetLine budgetLine) {
        set(PROPERTY_BUDGETLINE, budgetLine);
    }

    public ElementValue getAccountElement() {
        return (ElementValue) get(PROPERTY_ACCOUNTELEMENT);
    }

    public void setAccountElement(ElementValue accountElement) {
        set(PROPERTY_ACCOUNTELEMENT, accountElement);
    }

    public String getElementvalueident() {
        return (String) get(PROPERTY_ELEMENTVALUEIDENT);
    }

    public void setElementvalueident(String elementvalueident) {
        set(PROPERTY_ELEMENTVALUEIDENT, elementvalueident);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public String getPeriodIdentification() {
        return (String) get(PROPERTY_PERIODIDENTIFICATION);
    }

    public void setPeriodIdentification(String periodIdentification) {
        set(PROPERTY_PERIODIDENTIFICATION, periodIdentification);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getProductIdentification() {
        return (String) get(PROPERTY_PRODUCTIDENTIFICATION);
    }

    public void setProductIdentification(String productIdentification) {
        set(PROPERTY_PRODUCTIDENTIFICATION, productIdentification);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getPartnerIdentification() {
        return (String) get(PROPERTY_PARTNERIDENTIFICATION);
    }

    public void setPartnerIdentification(String partnerIdentification) {
        set(PROPERTY_PARTNERIDENTIFICATION, partnerIdentification);
    }

    public Organization getTrxOrganization() {
        return (Organization) get(PROPERTY_TRXORGANIZATION);
    }

    public void setTrxOrganization(Organization trxOrganization) {
        set(PROPERTY_TRXORGANIZATION, trxOrganization);
    }

    public String getOrganizationTrxIdentification() {
        return (String) get(PROPERTY_ORGANIZATIONTRXIDENTIFICATION);
    }

    public void setOrganizationTrxIdentification(String organizationTrxIdentification) {
        set(PROPERTY_ORGANIZATIONTRXIDENTIFICATION, organizationTrxIdentification);
    }

    public SalesRegion getSalesRegion() {
        return (SalesRegion) get(PROPERTY_SALESREGION);
    }

    public void setSalesRegion(SalesRegion salesRegion) {
        set(PROPERTY_SALESREGION, salesRegion);
    }

    public String getSalesRegionIdentification() {
        return (String) get(PROPERTY_SALESREGIONIDENTIFICATION);
    }

    public void setSalesRegionIdentification(String salesRegionIdentification) {
        set(PROPERTY_SALESREGIONIDENTIFICATION, salesRegionIdentification);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public String getProjectIdentification() {
        return (String) get(PROPERTY_PROJECTIDENTIFICATION);
    }

    public void setProjectIdentification(String projectIdentification) {
        set(PROPERTY_PROJECTIDENTIFICATION, projectIdentification);
    }

    public Campaign getSalesCampaign() {
        return (Campaign) get(PROPERTY_SALESCAMPAIGN);
    }

    public void setSalesCampaign(Campaign salesCampaign) {
        set(PROPERTY_SALESCAMPAIGN, salesCampaign);
    }

    public String getCampaignIdentification() {
        return (String) get(PROPERTY_CAMPAIGNIDENTIFICATION);
    }

    public void setCampaignIdentification(String campaignIdentification) {
        set(PROPERTY_CAMPAIGNIDENTIFICATION, campaignIdentification);
    }

    public ABCActivity getActivity() {
        return (ABCActivity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(ABCActivity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public String getActivityIdentification() {
        return (String) get(PROPERTY_ACTIVITYIDENTIFICATION);
    }

    public void setActivityIdentification(String activityIdentification) {
        set(PROPERTY_ACTIVITYIDENTIFICATION, activityIdentification);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public Category getBusinessPartnerCategory() {
        return (Category) get(PROPERTY_BUSINESSPARTNERCATEGORY);
    }

    public void setBusinessPartnerCategory(Category businessPartnerCategory) {
        set(PROPERTY_BUSINESSPARTNERCATEGORY, businessPartnerCategory);
    }

    public String getBPGroupIdentitification() {
        return (String) get(PROPERTY_BPGROUPIDENTITIFICATION);
    }

    public void setBPGroupIdentitification(String bPGroupIdentitification) {
        set(PROPERTY_BPGROUPIDENTITIFICATION, bPGroupIdentitification);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public String getProductCategoryID() {
        return (String) get(PROPERTY_PRODUCTCATEGORYID);
    }

    public void setProductCategoryID(String productCategoryID) {
        set(PROPERTY_PRODUCTCATEGORYID, productCategoryID);
    }

    public Budget getBudget() {
        return (Budget) get(PROPERTY_BUDGET);
    }

    public void setBudget(Budget budget) {
        set(PROPERTY_BUDGET, budget);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public BigDecimal getPrice() {
        return (BigDecimal) get(PROPERTY_PRICE);
    }

    public void setPrice(BigDecimal price) {
        set(PROPERTY_PRICE, price);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
    }

    public Boolean isImportProcessComplete() {
        return (Boolean) get(PROPERTY_IMPORTPROCESSCOMPLETE);
    }

    public void setImportProcessComplete(Boolean importProcessComplete) {
        set(PROPERTY_IMPORTPROCESSCOMPLETE, importProcessComplete);
    }

    public String getCurrencyIdentification() {
        return (String) get(PROPERTY_CURRENCYIDENTIFICATION);
    }

    public void setCurrencyIdentification(String currencyIdentification) {
        set(PROPERTY_CURRENCYIDENTIFICATION, currencyIdentification);
    }

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
    }

    public String getAcctschemaident() {
        return (String) get(PROPERTY_ACCTSCHEMAIDENT);
    }

    public void setAcctschemaident(String acctschemaident) {
        set(PROPERTY_ACCTSCHEMAIDENT, acctschemaident);
    }

}
