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
package org.wirabumi.gen.oez;

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
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.assetmgmt.AssetGroup;
import org.openbravo.model.project.Project;
/**
 * Entity class for entity oez_i_asset (stored in table oez_i_asset).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ImportAsset extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_i_asset";
    public static final String ENTITY_NAME = "oez_i_asset";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HELPCOMMENT = "helpComment";
    public static final String PROPERTY_ASSETGROUPVALUE = "assetgroupvalue";
    public static final String PROPERTY_ASSETCATEGORY = "assetCategory";
    public static final String PROPERTY_PRODUCTSEARCHKEY = "productSearchKey";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_SERIALNO = "serialNo";
    public static final String PROPERTY_LOTNAME = "lotName";
    public static final String PROPERTY_VERSIONNO = "versionNo";
    public static final String PROPERTY_EXPIRATIONDATE = "expirationDate";
    public static final String PROPERTY_INSERVICEDATE = "inServiceDate";
    public static final String PROPERTY_OWNED = "owned";
    public static final String PROPERTY_ASSETDEPRECIATIONDATE = "assetDepreciationDate";
    public static final String PROPERTY_USABLELIFEYEARS = "usableLifeYears";
    public static final String PROPERTY_USABLELIFEMONTHS = "usableLifeMonths";
    public static final String PROPERTY_LIFEUSE = "lifeUse";
    public static final String PROPERTY_USEUNITS = "useUnits";
    public static final String PROPERTY_DISPOSED = "disposed";
    public static final String PROPERTY_ASSETDISPOSALDATE = "assetDisposalDate";
    public static final String PROPERTY_INPOSSESSION = "inPossession";
    public static final String PROPERTY_LOCATIONCOMMENT = "locationComment";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_DEPRECIATE = "depreciate";
    public static final String PROPERTY_FULLYDEPRECIATED = "fullyDepreciated";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_DEPRECIATIONENDDATE = "depreciationEndDate";
    public static final String PROPERTY_DEPRECIATIONSTARTDATE = "depreciationStartDate";
    public static final String PROPERTY_ANNUALDEPRECIATION = "annualDepreciation";
    public static final String PROPERTY_ASSETVALUE = "assetValue";
    public static final String PROPERTY_CURRENCYISOCODE = "currencyIsoCode";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_CANCELLATIONDATE = "cancellationDate";
    public static final String PROPERTY_PURCHASEDATE = "purchaseDate";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_RESIDUALASSETVALUE = "residualAssetValue";
    public static final String PROPERTY_ACCOUNTINGVALUEAMOUNT = "accountingValueAmount";
    public static final String PROPERTY_DEPRECIATIONTYPE = "depreciationType";
    public static final String PROPERTY_DEPRECIATIONAMT = "depreciationAmt";
    public static final String PROPERTY_AMORTIZE = "amortize";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PROFIT = "profit";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_CALCULATETYPE = "calculateType";
    public static final String PROPERTY_DEPRECIATEDPLAN = "depreciatedPlan";
    public static final String PROPERTY_PREVIOUSLYDEPRECIATEDAMT = "previouslyDepreciatedAmt";
    public static final String PROPERTY_DEPRECIATEDVALUE = "depreciatedValue";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_PROJECTVALUE = "projectvalue";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_STATIC = "static";
    public static final String PROPERTY_EVERYMONTHIS30DAYS = "everyMonthIs30Days";
    public static final String PROPERTY_PROCESSIMPORT = "processImport";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";

    public ImportAsset() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OWNED, true);
        setDefaultValue(PROPERTY_DISPOSED, false);
        setDefaultValue(PROPERTY_INPOSSESSION, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_DEPRECIATE, true);
        setDefaultValue(PROPERTY_FULLYDEPRECIATED, false);
        setDefaultValue(PROPERTY_PROCESSED, "N");
        setDefaultValue(PROPERTY_PREVIOUSLYDEPRECIATEDAMT, new BigDecimal(0));
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_STATIC, false);
        setDefaultValue(PROPERTY_EVERYMONTHIS30DAYS, true);
        setDefaultValue(PROPERTY_PROCESSIMPORT, false);
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getHelpComment() {
        return (String) get(PROPERTY_HELPCOMMENT);
    }

    public void setHelpComment(String helpComment) {
        set(PROPERTY_HELPCOMMENT, helpComment);
    }

    public String getAssetgroupvalue() {
        return (String) get(PROPERTY_ASSETGROUPVALUE);
    }

    public void setAssetgroupvalue(String assetgroupvalue) {
        set(PROPERTY_ASSETGROUPVALUE, assetgroupvalue);
    }

    public AssetGroup getAssetCategory() {
        return (AssetGroup) get(PROPERTY_ASSETCATEGORY);
    }

    public void setAssetCategory(AssetGroup assetCategory) {
        set(PROPERTY_ASSETCATEGORY, assetCategory);
    }

    public String getProductSearchKey() {
        return (String) get(PROPERTY_PRODUCTSEARCHKEY);
    }

    public void setProductSearchKey(String productSearchKey) {
        set(PROPERTY_PRODUCTSEARCHKEY, productSearchKey);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getSerialNo() {
        return (String) get(PROPERTY_SERIALNO);
    }

    public void setSerialNo(String serialNo) {
        set(PROPERTY_SERIALNO, serialNo);
    }

    public String getLotName() {
        return (String) get(PROPERTY_LOTNAME);
    }

    public void setLotName(String lotName) {
        set(PROPERTY_LOTNAME, lotName);
    }

    public String getVersionNo() {
        return (String) get(PROPERTY_VERSIONNO);
    }

    public void setVersionNo(String versionNo) {
        set(PROPERTY_VERSIONNO, versionNo);
    }

    public Date getExpirationDate() {
        return (Date) get(PROPERTY_EXPIRATIONDATE);
    }

    public void setExpirationDate(Date expirationDate) {
        set(PROPERTY_EXPIRATIONDATE, expirationDate);
    }

    public Date getInServiceDate() {
        return (Date) get(PROPERTY_INSERVICEDATE);
    }

    public void setInServiceDate(Date inServiceDate) {
        set(PROPERTY_INSERVICEDATE, inServiceDate);
    }

    public Boolean isOwned() {
        return (Boolean) get(PROPERTY_OWNED);
    }

    public void setOwned(Boolean owned) {
        set(PROPERTY_OWNED, owned);
    }

    public Date getAssetDepreciationDate() {
        return (Date) get(PROPERTY_ASSETDEPRECIATIONDATE);
    }

    public void setAssetDepreciationDate(Date assetDepreciationDate) {
        set(PROPERTY_ASSETDEPRECIATIONDATE, assetDepreciationDate);
    }

    public Long getUsableLifeYears() {
        return (Long) get(PROPERTY_USABLELIFEYEARS);
    }

    public void setUsableLifeYears(Long usableLifeYears) {
        set(PROPERTY_USABLELIFEYEARS, usableLifeYears);
    }

    public Long getUsableLifeMonths() {
        return (Long) get(PROPERTY_USABLELIFEMONTHS);
    }

    public void setUsableLifeMonths(Long usableLifeMonths) {
        set(PROPERTY_USABLELIFEMONTHS, usableLifeMonths);
    }

    public Long getLifeUse() {
        return (Long) get(PROPERTY_LIFEUSE);
    }

    public void setLifeUse(Long lifeUse) {
        set(PROPERTY_LIFEUSE, lifeUse);
    }

    public BigDecimal getUseUnits() {
        return (BigDecimal) get(PROPERTY_USEUNITS);
    }

    public void setUseUnits(BigDecimal useUnits) {
        set(PROPERTY_USEUNITS, useUnits);
    }

    public Boolean isDisposed() {
        return (Boolean) get(PROPERTY_DISPOSED);
    }

    public void setDisposed(Boolean disposed) {
        set(PROPERTY_DISPOSED, disposed);
    }

    public Date getAssetDisposalDate() {
        return (Date) get(PROPERTY_ASSETDISPOSALDATE);
    }

    public void setAssetDisposalDate(Date assetDisposalDate) {
        set(PROPERTY_ASSETDISPOSALDATE, assetDisposalDate);
    }

    public Boolean isInPossession() {
        return (Boolean) get(PROPERTY_INPOSSESSION);
    }

    public void setInPossession(Boolean inPossession) {
        set(PROPERTY_INPOSSESSION, inPossession);
    }

    public String getLocationComment() {
        return (String) get(PROPERTY_LOCATIONCOMMENT);
    }

    public void setLocationComment(String locationComment) {
        set(PROPERTY_LOCATIONCOMMENT, locationComment);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public org.openbravo.model.common.geography.Location getLocationAddress() {
        return (org.openbravo.model.common.geography.Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(org.openbravo.model.common.geography.Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isDepreciate() {
        return (Boolean) get(PROPERTY_DEPRECIATE);
    }

    public void setDepreciate(Boolean depreciate) {
        set(PROPERTY_DEPRECIATE, depreciate);
    }

    public Boolean isFullyDepreciated() {
        return (Boolean) get(PROPERTY_FULLYDEPRECIATED);
    }

    public void setFullyDepreciated(Boolean fullyDepreciated) {
        set(PROPERTY_FULLYDEPRECIATED, fullyDepreciated);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public Date getDepreciationEndDate() {
        return (Date) get(PROPERTY_DEPRECIATIONENDDATE);
    }

    public void setDepreciationEndDate(Date depreciationEndDate) {
        set(PROPERTY_DEPRECIATIONENDDATE, depreciationEndDate);
    }

    public Date getDepreciationStartDate() {
        return (Date) get(PROPERTY_DEPRECIATIONSTARTDATE);
    }

    public void setDepreciationStartDate(Date depreciationStartDate) {
        set(PROPERTY_DEPRECIATIONSTARTDATE, depreciationStartDate);
    }

    public BigDecimal getAnnualDepreciation() {
        return (BigDecimal) get(PROPERTY_ANNUALDEPRECIATION);
    }

    public void setAnnualDepreciation(BigDecimal annualDepreciation) {
        set(PROPERTY_ANNUALDEPRECIATION, annualDepreciation);
    }

    public BigDecimal getAssetValue() {
        return (BigDecimal) get(PROPERTY_ASSETVALUE);
    }

    public void setAssetValue(BigDecimal assetValue) {
        set(PROPERTY_ASSETVALUE, assetValue);
    }

    public String getCurrencyIsoCode() {
        return (String) get(PROPERTY_CURRENCYISOCODE);
    }

    public void setCurrencyIsoCode(String currencyIsoCode) {
        set(PROPERTY_CURRENCYISOCODE, currencyIsoCode);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Date getCancellationDate() {
        return (Date) get(PROPERTY_CANCELLATIONDATE);
    }

    public void setCancellationDate(Date cancellationDate) {
        set(PROPERTY_CANCELLATIONDATE, cancellationDate);
    }

    public Date getPurchaseDate() {
        return (Date) get(PROPERTY_PURCHASEDATE);
    }

    public void setPurchaseDate(Date purchaseDate) {
        set(PROPERTY_PURCHASEDATE, purchaseDate);
    }

    public AttributeSetInstance getAttributeSetValue() {
        return (AttributeSetInstance) get(PROPERTY_ATTRIBUTESETVALUE);
    }

    public void setAttributeSetValue(AttributeSetInstance attributeSetValue) {
        set(PROPERTY_ATTRIBUTESETVALUE, attributeSetValue);
    }

    public BigDecimal getResidualAssetValue() {
        return (BigDecimal) get(PROPERTY_RESIDUALASSETVALUE);
    }

    public void setResidualAssetValue(BigDecimal residualAssetValue) {
        set(PROPERTY_RESIDUALASSETVALUE, residualAssetValue);
    }

    public BigDecimal getAccountingValueAmount() {
        return (BigDecimal) get(PROPERTY_ACCOUNTINGVALUEAMOUNT);
    }

    public void setAccountingValueAmount(BigDecimal accountingValueAmount) {
        set(PROPERTY_ACCOUNTINGVALUEAMOUNT, accountingValueAmount);
    }

    public String getDepreciationType() {
        return (String) get(PROPERTY_DEPRECIATIONTYPE);
    }

    public void setDepreciationType(String depreciationType) {
        set(PROPERTY_DEPRECIATIONTYPE, depreciationType);
    }

    public BigDecimal getDepreciationAmt() {
        return (BigDecimal) get(PROPERTY_DEPRECIATIONAMT);
    }

    public void setDepreciationAmt(BigDecimal depreciationAmt) {
        set(PROPERTY_DEPRECIATIONAMT, depreciationAmt);
    }

    public String getAmortize() {
        return (String) get(PROPERTY_AMORTIZE);
    }

    public void setAmortize(String amortize) {
        set(PROPERTY_AMORTIZE, amortize);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getProcessed() {
        return (String) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(String processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public BigDecimal getProfit() {
        return (BigDecimal) get(PROPERTY_PROFIT);
    }

    public void setProfit(BigDecimal profit) {
        set(PROPERTY_PROFIT, profit);
    }

    public Long getQuantity() {
        return (Long) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(Long quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public String getCalculateType() {
        return (String) get(PROPERTY_CALCULATETYPE);
    }

    public void setCalculateType(String calculateType) {
        set(PROPERTY_CALCULATETYPE, calculateType);
    }

    public BigDecimal getDepreciatedPlan() {
        return (BigDecimal) get(PROPERTY_DEPRECIATEDPLAN);
    }

    public void setDepreciatedPlan(BigDecimal depreciatedPlan) {
        set(PROPERTY_DEPRECIATEDPLAN, depreciatedPlan);
    }

    public BigDecimal getPreviouslyDepreciatedAmt() {
        return (BigDecimal) get(PROPERTY_PREVIOUSLYDEPRECIATEDAMT);
    }

    public void setPreviouslyDepreciatedAmt(BigDecimal previouslyDepreciatedAmt) {
        set(PROPERTY_PREVIOUSLYDEPRECIATEDAMT, previouslyDepreciatedAmt);
    }

    public BigDecimal getDepreciatedValue() {
        return (BigDecimal) get(PROPERTY_DEPRECIATEDVALUE);
    }

    public void setDepreciatedValue(BigDecimal depreciatedValue) {
        set(PROPERTY_DEPRECIATEDVALUE, depreciatedValue);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public String getProjectvalue() {
        return (String) get(PROPERTY_PROJECTVALUE);
    }

    public void setProjectvalue(String projectvalue) {
        set(PROPERTY_PROJECTVALUE, projectvalue);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Boolean isStatic() {
        return (Boolean) get(PROPERTY_STATIC);
    }

    public void setStatic(Boolean statc) {
        set(PROPERTY_STATIC, statc);
    }

    public Boolean isEveryMonthIs30Days() {
        return (Boolean) get(PROPERTY_EVERYMONTHIS30DAYS);
    }

    public void setEveryMonthIs30Days(Boolean everyMonthIs30Days) {
        set(PROPERTY_EVERYMONTHIS30DAYS, everyMonthIs30Days);
    }

    public Boolean isProcessImport() {
        return (Boolean) get(PROPERTY_PROCESSIMPORT);
    }

    public void setProcessImport(Boolean processImport) {
        set(PROPERTY_PROCESSIMPORT, processImport);
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

}
