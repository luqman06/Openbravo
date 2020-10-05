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
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.gl.GLBatch;
import org.openbravo.model.financialmgmt.gl.GLCategory;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.project.Project;
import org.openbravo.model.sales.SalesRegion;
/**
 * Entity class for entity DataImportGLJournal (stored in table I_GLJournal).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class GLJournal extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_GLJournal";
    public static final String ENTITY_NAME = "DataImportGLJournal";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_NEWCLIENTIDENTIFIER = "newClientIdentifier";
    public static final String PROPERTY_DOCUMENTORG = "documentOrg";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_JOURNALBATCH = "journalBatch";
    public static final String PROPERTY_BATCHDOCUMENTNO = "batchDocumentNo";
    public static final String PROPERTY_BATCHDESCRIPTION = "batchDescription";
    public static final String PROPERTY_JOURNALENTRY = "journalEntry";
    public static final String PROPERTY_JOURNALDOCUMENTNO = "journalDocumentNo";
    public static final String PROPERTY_POSTINGTYPE = "postingType";
    public static final String PROPERTY_ACCOUNTINGSCHEMA = "accountingSchema";
    public static final String PROPERTY_ACCOUNTSCHEMANAME = "accountSchemaName";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTTYPENAME = "documentTypeName";
    public static final String PROPERTY_GLCATEGORY = "gLCategory";
    public static final String PROPERTY_CATEGORYNAME = "categoryName";
    public static final String PROPERTY_PERIOD = "period";
    public static final String PROPERTY_BUDGET = "budget";
    public static final String PROPERTY_JOURNALLINE = "journalLine";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_FOREIGNCURRENCYDEBIT = "foreignCurrencyDebit";
    public static final String PROPERTY_DEBIT = "debit";
    public static final String PROPERTY_FOREIGNCURRENCYCREDIT = "foreignCurrencyCredit";
    public static final String PROPERTY_CREDIT = "credit";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_ISOCODE = "iSOCode";
    public static final String PROPERTY_CURRENCYRATETYPE = "currencyRateType";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_ACCOUNTINGCOMBINATION = "accountingCombination";
    public static final String PROPERTY_ORGKEY = "orgKey";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_ACCOUNTKEY = "accountKey";
    public static final String PROPERTY_TRXORGANIZATION = "trxOrganization";
    public static final String PROPERTY_TRXORGKEY = "trxOrgKey";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_PRODUCTSEARCHKEY = "productSearchKey";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_SKU = "sKU";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_BUSINESSPARTNERSEARCHKEY = "businessPartnerSearchKey";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_PROJECTKEY = "projectKey";
    public static final String PROPERTY_LOCATIONTOADDRESS = "locationToAddress";
    public static final String PROPERTY_LOCATIONFROMADDRESS = "locationFromAddress";
    public static final String PROPERTY_SALESREGION = "salesRegion";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";

    public GLJournal() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
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

    public String getNewClientIdentifier() {
        return (String) get(PROPERTY_NEWCLIENTIDENTIFIER);
    }

    public void setNewClientIdentifier(String newClientIdentifier) {
        set(PROPERTY_NEWCLIENTIDENTIFIER, newClientIdentifier);
    }

    public Organization getDocumentOrg() {
        return (Organization) get(PROPERTY_DOCUMENTORG);
    }

    public void setDocumentOrg(Organization documentOrg) {
        set(PROPERTY_DOCUMENTORG, documentOrg);
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

    public Boolean isImportProcessComplete() {
        return (Boolean) get(PROPERTY_IMPORTPROCESSCOMPLETE);
    }

    public void setImportProcessComplete(Boolean importProcessComplete) {
        set(PROPERTY_IMPORTPROCESSCOMPLETE, importProcessComplete);
    }

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
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

    public GLBatch getJournalBatch() {
        return (GLBatch) get(PROPERTY_JOURNALBATCH);
    }

    public void setJournalBatch(GLBatch journalBatch) {
        set(PROPERTY_JOURNALBATCH, journalBatch);
    }

    public String getBatchDocumentNo() {
        return (String) get(PROPERTY_BATCHDOCUMENTNO);
    }

    public void setBatchDocumentNo(String batchDocumentNo) {
        set(PROPERTY_BATCHDOCUMENTNO, batchDocumentNo);
    }

    public String getBatchDescription() {
        return (String) get(PROPERTY_BATCHDESCRIPTION);
    }

    public void setBatchDescription(String batchDescription) {
        set(PROPERTY_BATCHDESCRIPTION, batchDescription);
    }

    public org.openbravo.model.financialmgmt.gl.GLJournal getJournalEntry() {
        return (org.openbravo.model.financialmgmt.gl.GLJournal) get(PROPERTY_JOURNALENTRY);
    }

    public void setJournalEntry(org.openbravo.model.financialmgmt.gl.GLJournal journalEntry) {
        set(PROPERTY_JOURNALENTRY, journalEntry);
    }

    public String getJournalDocumentNo() {
        return (String) get(PROPERTY_JOURNALDOCUMENTNO);
    }

    public void setJournalDocumentNo(String journalDocumentNo) {
        set(PROPERTY_JOURNALDOCUMENTNO, journalDocumentNo);
    }

    public String getPostingType() {
        return (String) get(PROPERTY_POSTINGTYPE);
    }

    public void setPostingType(String postingType) {
        set(PROPERTY_POSTINGTYPE, postingType);
    }

    public AcctSchema getAccountingSchema() {
        return (AcctSchema) get(PROPERTY_ACCOUNTINGSCHEMA);
    }

    public void setAccountingSchema(AcctSchema accountingSchema) {
        set(PROPERTY_ACCOUNTINGSCHEMA, accountingSchema);
    }

    public String getAccountSchemaName() {
        return (String) get(PROPERTY_ACCOUNTSCHEMANAME);
    }

    public void setAccountSchemaName(String accountSchemaName) {
        set(PROPERTY_ACCOUNTSCHEMANAME, accountSchemaName);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentTypeName() {
        return (String) get(PROPERTY_DOCUMENTTYPENAME);
    }

    public void setDocumentTypeName(String documentTypeName) {
        set(PROPERTY_DOCUMENTTYPENAME, documentTypeName);
    }

    public GLCategory getGLCategory() {
        return (GLCategory) get(PROPERTY_GLCATEGORY);
    }

    public void setGLCategory(GLCategory gLCategory) {
        set(PROPERTY_GLCATEGORY, gLCategory);
    }

    public String getCategoryName() {
        return (String) get(PROPERTY_CATEGORYNAME);
    }

    public void setCategoryName(String categoryName) {
        set(PROPERTY_CATEGORYNAME, categoryName);
    }

    public Period getPeriod() {
        return (Period) get(PROPERTY_PERIOD);
    }

    public void setPeriod(Period period) {
        set(PROPERTY_PERIOD, period);
    }

    public String getBudget() {
        return (String) get(PROPERTY_BUDGET);
    }

    public void setBudget(String budget) {
        set(PROPERTY_BUDGET, budget);
    }

    public GLJournalLine getJournalLine() {
        return (GLJournalLine) get(PROPERTY_JOURNALLINE);
    }

    public void setJournalLine(GLJournalLine journalLine) {
        set(PROPERTY_JOURNALLINE, journalLine);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BigDecimal getForeignCurrencyDebit() {
        return (BigDecimal) get(PROPERTY_FOREIGNCURRENCYDEBIT);
    }

    public void setForeignCurrencyDebit(BigDecimal foreignCurrencyDebit) {
        set(PROPERTY_FOREIGNCURRENCYDEBIT, foreignCurrencyDebit);
    }

    public BigDecimal getDebit() {
        return (BigDecimal) get(PROPERTY_DEBIT);
    }

    public void setDebit(BigDecimal debit) {
        set(PROPERTY_DEBIT, debit);
    }

    public BigDecimal getForeignCurrencyCredit() {
        return (BigDecimal) get(PROPERTY_FOREIGNCURRENCYCREDIT);
    }

    public void setForeignCurrencyCredit(BigDecimal foreignCurrencyCredit) {
        set(PROPERTY_FOREIGNCURRENCYCREDIT, foreignCurrencyCredit);
    }

    public BigDecimal getCredit() {
        return (BigDecimal) get(PROPERTY_CREDIT);
    }

    public void setCredit(BigDecimal credit) {
        set(PROPERTY_CREDIT, credit);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getISOCode() {
        return (String) get(PROPERTY_ISOCODE);
    }

    public void setISOCode(String iSOCode) {
        set(PROPERTY_ISOCODE, iSOCode);
    }

    public String getCurrencyRateType() {
        return (String) get(PROPERTY_CURRENCYRATETYPE);
    }

    public void setCurrencyRateType(String currencyRateType) {
        set(PROPERTY_CURRENCYRATETYPE, currencyRateType);
    }

    public BigDecimal getRate() {
        return (BigDecimal) get(PROPERTY_RATE);
    }

    public void setRate(BigDecimal rate) {
        set(PROPERTY_RATE, rate);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public AccountingCombination getAccountingCombination() {
        return (AccountingCombination) get(PROPERTY_ACCOUNTINGCOMBINATION);
    }

    public void setAccountingCombination(AccountingCombination accountingCombination) {
        set(PROPERTY_ACCOUNTINGCOMBINATION, accountingCombination);
    }

    public String getOrgKey() {
        return (String) get(PROPERTY_ORGKEY);
    }

    public void setOrgKey(String orgKey) {
        set(PROPERTY_ORGKEY, orgKey);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public ElementValue getAccount() {
        return (ElementValue) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(ElementValue account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public String getAccountKey() {
        return (String) get(PROPERTY_ACCOUNTKEY);
    }

    public void setAccountKey(String accountKey) {
        set(PROPERTY_ACCOUNTKEY, accountKey);
    }

    public Organization getTrxOrganization() {
        return (Organization) get(PROPERTY_TRXORGANIZATION);
    }

    public void setTrxOrganization(Organization trxOrganization) {
        set(PROPERTY_TRXORGANIZATION, trxOrganization);
    }

    public String getTrxOrgKey() {
        return (String) get(PROPERTY_TRXORGKEY);
    }

    public void setTrxOrgKey(String trxOrgKey) {
        set(PROPERTY_TRXORGKEY, trxOrgKey);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getProductSearchKey() {
        return (String) get(PROPERTY_PRODUCTSEARCHKEY);
    }

    public void setProductSearchKey(String productSearchKey) {
        set(PROPERTY_PRODUCTSEARCHKEY, productSearchKey);
    }

    public String getUPCEAN() {
        return (String) get(PROPERTY_UPCEAN);
    }

    public void setUPCEAN(String uPCEAN) {
        set(PROPERTY_UPCEAN, uPCEAN);
    }

    public String getSKU() {
        return (String) get(PROPERTY_SKU);
    }

    public void setSKU(String sKU) {
        set(PROPERTY_SKU, sKU);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getBusinessPartnerSearchKey() {
        return (String) get(PROPERTY_BUSINESSPARTNERSEARCHKEY);
    }

    public void setBusinessPartnerSearchKey(String businessPartnerSearchKey) {
        set(PROPERTY_BUSINESSPARTNERSEARCHKEY, businessPartnerSearchKey);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public String getProjectKey() {
        return (String) get(PROPERTY_PROJECTKEY);
    }

    public void setProjectKey(String projectKey) {
        set(PROPERTY_PROJECTKEY, projectKey);
    }

    public Location getLocationToAddress() {
        return (Location) get(PROPERTY_LOCATIONTOADDRESS);
    }

    public void setLocationToAddress(Location locationToAddress) {
        set(PROPERTY_LOCATIONTOADDRESS, locationToAddress);
    }

    public Location getLocationFromAddress() {
        return (Location) get(PROPERTY_LOCATIONFROMADDRESS);
    }

    public void setLocationFromAddress(Location locationFromAddress) {
        set(PROPERTY_LOCATIONFROMADDRESS, locationFromAddress);
    }

    public SalesRegion getSalesRegion() {
        return (SalesRegion) get(PROPERTY_SALESREGION);
    }

    public void setSalesRegion(SalesRegion salesRegion) {
        set(PROPERTY_SALESREGION, salesRegion);
    }

    public ABCActivity getActivity() {
        return (ABCActivity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(ABCActivity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public Campaign getSalesCampaign() {
        return (Campaign) get(PROPERTY_SALESCAMPAIGN);
    }

    public void setSalesCampaign(Campaign salesCampaign) {
        set(PROPERTY_SALESCAMPAIGN, salesCampaign);
    }

    public String getStDimension() {
        return (String) get(PROPERTY_STDIMENSION);
    }

    public void setStDimension(String stDimension) {
        set(PROPERTY_STDIMENSION, stDimension);
    }

    public String getNdDimension() {
        return (String) get(PROPERTY_NDDIMENSION);
    }

    public void setNdDimension(String ndDimension) {
        set(PROPERTY_NDDIMENSION, ndDimension);
    }

}
