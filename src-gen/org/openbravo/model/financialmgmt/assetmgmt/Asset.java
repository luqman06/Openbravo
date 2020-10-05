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
package org.openbravo.model.financialmgmt.assetmgmt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
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
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.invoice.InvoiceLineAccountingDimension;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.order.OrderLineAccountingDimension;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.gl.GLJournal;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebt;
import org.openbravo.model.manufacturing.maintenance.MaintenanceSchedule;
import org.openbravo.model.materialmgmt.transaction.InOutLineAccountingDimension;
import org.openbravo.model.materialmgmt.transaction.InternalMovement;
import org.openbravo.model.materialmgmt.transaction.InventoryCount;
import org.openbravo.model.materialmgmt.transaction.ProductionTransaction;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.model.project.Project;
import org.openbravo.model.timeandexpense.Sheet;
import org.openbravo.model.timeandexpense.SheetLine;
import org.wirabumi.cam.AssetItem;
import org.wirabumi.cam.AssetPrintLine;
import org.wirabumi.cam.MeterGroup;
import org.wirabumi.cam.WorkOrder;
import org.wirabumi.cam.WorkOrderAsset;
/**
 * Entity class for entity FinancialMgmtAsset (stored in table A_Asset).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Asset extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "A_Asset";
    public static final String ENTITY_NAME = "FinancialMgmtAsset";
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
    public static final String PROPERTY_ASSETCATEGORY = "assetCategory";
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
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_CANCELLATIONDATE = "cancellationDate";
    public static final String PROPERTY_PURCHASEDATE = "purchaseDate";
    public static final String PROPERTY_ATTRIBUTESETVALUE = "attributeSetValue";
    public static final String PROPERTY_RESIDUALASSETVALUE = "residualAssetValue";
    public static final String PROPERTY_ACCTVALUEAMT = "acctvalueamt";
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
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_STATIC = "static";
    public static final String PROPERTY_EVERYMONTHIS30DAYS = "everyMonthIs30Days";
    public static final String PROPERTY_CAMLIFESPAN = "camLifespan";
    public static final String PROPERTY_PROCESSASSET = "processAsset";
    public static final String PROPERTY_CAMLIFETODATE = "camLifetodate";
    public static final String PROPERTY_CAMLOCATION = "camLocation";
    public static final String PROPERTY_CAMMETERGROUP = "camMetergroup";
    public static final String PROPERTY_CAMROTATINGITEM = "camRotatingitem";
    public static final String PROPERTY_CAMISASSETTEMPLATE = "camIsassettemplate";
    public static final String PROPERTY_CAMCUSTODIAN = "camCustodian";
    public static final String PROPERTY_CAMGENERICUSER = "camGenericuser";
    public static final String PROPERTY_CAMPRIMARYUSER = "camPrimaryuser";
    public static final String PROPERTY_CAMASSETTYPE = "camAssettype";
    public static final String PROPERTY_CAMVENDOR = "camVendor";
    public static final String PROPERTY_CAMMANUFACTURER = "camManufacturer";
    public static final String PROPERTY_CAMINSTALLDATE = "camInstalldate";
    public static final String PROPERTY_CAMPURCHASEPRICE = "camPurchaseprice";
    public static final String PROPERTY_CAMREPLACEMENTCOST = "camReplacementcost";
    public static final String PROPERTY_CAMPURCHASEORDER = "camPurchaseorder";
    public static final String PROPERTY_CAMTOTALCOST = "camTotalcost";
    public static final String PROPERTY_CAMYEARTODATECOST = "camYeartodatecost";
    public static final String PROPERTY_CAMBUDGETTED = "camBudgetted";
    public static final String PROPERTY_CAMISASSETUP = "camIsassetup";
    public static final String PROPERTY_CAMCOSTCENTER = "camCostcenter";
    public static final String PROPERTY_CAMPARENT = "camParent";
    public static final String PROPERTY_CAMTOTALDOWNTIME = "camTotaldowntime";
    public static final String PROPERTY_OEZDOCSTATUS = "oezDocstatus";
    public static final String PROPERTY_OEZPREPAYMENTASSET = "oEZPrepaymentAsset";
    public static final String PROPERTY__COMPUTEDCOLUMNS = "_computedColumns";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST = "amortizationLineAccountingDimensionList";
    public static final String PROPERTY_FINDOUBTFULDEBTLIST = "fINDoubtfulDebtList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTAMORTIZATIONLINELIST = "financialMgmtAmortizationLineList";
    public static final String PROPERTY_FINANCIALMGMTASSETEMCAMPARENTIDLIST = "financialMgmtAssetEmCamParentIdList";
    public static final String PROPERTY_FINANCIALMGMTASSETACCOUNTSLIST = "financialMgmtAssetAccountsList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST = "inOutLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST = "invoiceLineAccountingDimensionList";
    public static final String PROPERTY_MANUFACTURINGMAINTENANCESCHEDULEEMCAMASSETIDLIST = "manufacturingMaintenanceScheduleEmCamAssetIdList";
    public static final String PROPERTY_MATERIALMGMTINTERNALMOVEMENTLIST = "materialMgmtInternalMovementList";
    public static final String PROPERTY_MATERIALMGMTINVENTORYCOUNTLIST = "materialMgmtInventoryCountList";
    public static final String PROPERTY_MATERIALMGMTPRODUCTIONTRANSACTIONLIST = "materialMgmtProductionTransactionList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST = "materialMgmtShipmentInOutLineList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST = "orderLineAccountingDimensionList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLIST = "timeAndExpenseSheetList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINELIST = "timeAndExpenseSheetLineList";
    public static final String PROPERTY_CAMASSETPRINTLINELIST = "camAssetprintlineList";
    public static final String PROPERTY_CAMITEMLIST = "camItemList";
    public static final String PROPERTY_CAMWORKORDERLIST = "camWorkorderList";
    public static final String PROPERTY_CAMWORKORDERASSETLIST = "camWorkorderassetList";


    // Computed columns properties, these properties cannot be directly accessed, they need
    // to be read through _commputedColumns proxy. They cannot be directly used in HQL, OBQuery
    // nor OBCriteria. 
    public static final String COMPUTED_COLUMN_CAMBOOKVALUE = "camBookvalue";

    public Asset() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OWNED, false);
        setDefaultValue(PROPERTY_DISPOSED, false);
        setDefaultValue(PROPERTY_INPOSSESSION, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_DEPRECIATE, false);
        setDefaultValue(PROPERTY_FULLYDEPRECIATED, false);
        setDefaultValue(PROPERTY_PROCESSED, "N");
        setDefaultValue(PROPERTY_DEPRECIATEDPLAN, new BigDecimal(0));
        setDefaultValue(PROPERTY_PREVIOUSLYDEPRECIATEDAMT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DEPRECIATEDVALUE, new BigDecimal(0));
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_STATIC, false);
        setDefaultValue(PROPERTY_EVERYMONTHIS30DAYS, true);
        setDefaultValue(PROPERTY_PROCESSASSET, "N");
        setDefaultValue(PROPERTY_CAMISASSETTEMPLATE, false);
        setDefaultValue(PROPERTY_CAMISASSETUP, true);
        setDefaultValue(PROPERTY_OEZPREPAYMENTASSET, true);
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTAMORTIZATIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETEMCAMPARENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMAINTENANCESCHEDULEEMCAMASSETIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTINVENTORYCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTPRODUCTIONTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CAMASSETPRINTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CAMITEMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CAMWORKORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CAMWORKORDERASSETLIST, new ArrayList<Object>());
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

    public AssetGroup getAssetCategory() {
        return (AssetGroup) get(PROPERTY_ASSETCATEGORY);
    }

    public void setAssetCategory(AssetGroup assetCategory) {
        set(PROPERTY_ASSETCATEGORY, assetCategory);
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

    public Long getUseUnits() {
        return (Long) get(PROPERTY_USEUNITS);
    }

    public void setUseUnits(Long useUnits) {
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

    public BigDecimal getAcctvalueamt() {
        return (BigDecimal) get(PROPERTY_ACCTVALUEAMT);
    }

    public void setAcctvalueamt(BigDecimal acctvalueamt) {
        set(PROPERTY_ACCTVALUEAMT, acctvalueamt);
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

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
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

    public Long getCamLifespan() {
        return (Long) get(PROPERTY_CAMLIFESPAN);
    }

    public void setCamLifespan(Long camLifespan) {
        set(PROPERTY_CAMLIFESPAN, camLifespan);
    }

    public String getProcessAsset() {
        return (String) get(PROPERTY_PROCESSASSET);
    }

    public void setProcessAsset(String processAsset) {
        set(PROPERTY_PROCESSASSET, processAsset);
    }

    public String getCamLifetodate() {
        return (String) get(PROPERTY_CAMLIFETODATE);
    }

    public void setCamLifetodate(String camLifetodate) {
        set(PROPERTY_CAMLIFETODATE, camLifetodate);
    }

    public org.wirabumi.cam.Location getCamLocation() {
        return (org.wirabumi.cam.Location) get(PROPERTY_CAMLOCATION);
    }

    public void setCamLocation(org.wirabumi.cam.Location camLocation) {
        set(PROPERTY_CAMLOCATION, camLocation);
    }

    public MeterGroup getCamMetergroup() {
        return (MeterGroup) get(PROPERTY_CAMMETERGROUP);
    }

    public void setCamMetergroup(MeterGroup camMetergroup) {
        set(PROPERTY_CAMMETERGROUP, camMetergroup);
    }

    public Product getCamRotatingitem() {
        return (Product) get(PROPERTY_CAMROTATINGITEM);
    }

    public void setCamRotatingitem(Product camRotatingitem) {
        set(PROPERTY_CAMROTATINGITEM, camRotatingitem);
    }

    public Boolean isCamIsassettemplate() {
        return (Boolean) get(PROPERTY_CAMISASSETTEMPLATE);
    }

    public void setCamIsassettemplate(Boolean camIsassettemplate) {
        set(PROPERTY_CAMISASSETTEMPLATE, camIsassettemplate);
    }

    public BusinessPartner getCamCustodian() {
        return (BusinessPartner) get(PROPERTY_CAMCUSTODIAN);
    }

    public void setCamCustodian(BusinessPartner camCustodian) {
        set(PROPERTY_CAMCUSTODIAN, camCustodian);
    }

    public String getCamGenericuser() {
        return (String) get(PROPERTY_CAMGENERICUSER);
    }

    public void setCamGenericuser(String camGenericuser) {
        set(PROPERTY_CAMGENERICUSER, camGenericuser);
    }

    public String getCamPrimaryuser() {
        return (String) get(PROPERTY_CAMPRIMARYUSER);
    }

    public void setCamPrimaryuser(String camPrimaryuser) {
        set(PROPERTY_CAMPRIMARYUSER, camPrimaryuser);
    }

    public String getCamAssettype() {
        return (String) get(PROPERTY_CAMASSETTYPE);
    }

    public void setCamAssettype(String camAssettype) {
        set(PROPERTY_CAMASSETTYPE, camAssettype);
    }

    public BusinessPartner getCamVendor() {
        return (BusinessPartner) get(PROPERTY_CAMVENDOR);
    }

    public void setCamVendor(BusinessPartner camVendor) {
        set(PROPERTY_CAMVENDOR, camVendor);
    }

    public BusinessPartner getCamManufacturer() {
        return (BusinessPartner) get(PROPERTY_CAMMANUFACTURER);
    }

    public void setCamManufacturer(BusinessPartner camManufacturer) {
        set(PROPERTY_CAMMANUFACTURER, camManufacturer);
    }

    public Date getCamInstalldate() {
        return (Date) get(PROPERTY_CAMINSTALLDATE);
    }

    public void setCamInstalldate(Date camInstalldate) {
        set(PROPERTY_CAMINSTALLDATE, camInstalldate);
    }

    public BigDecimal getCamPurchaseprice() {
        return (BigDecimal) get(PROPERTY_CAMPURCHASEPRICE);
    }

    public void setCamPurchaseprice(BigDecimal camPurchaseprice) {
        set(PROPERTY_CAMPURCHASEPRICE, camPurchaseprice);
    }

    public Long getCamReplacementcost() {
        return (Long) get(PROPERTY_CAMREPLACEMENTCOST);
    }

    public void setCamReplacementcost(Long camReplacementcost) {
        set(PROPERTY_CAMREPLACEMENTCOST, camReplacementcost);
    }

    public BusinessPartner getCamPurchaseorder() {
        return (BusinessPartner) get(PROPERTY_CAMPURCHASEORDER);
    }

    public void setCamPurchaseorder(BusinessPartner camPurchaseorder) {
        set(PROPERTY_CAMPURCHASEORDER, camPurchaseorder);
    }

    public Long getCamTotalcost() {
        return (Long) get(PROPERTY_CAMTOTALCOST);
    }

    public void setCamTotalcost(Long camTotalcost) {
        set(PROPERTY_CAMTOTALCOST, camTotalcost);
    }

    public Long getCamYeartodatecost() {
        return (Long) get(PROPERTY_CAMYEARTODATECOST);
    }

    public void setCamYeartodatecost(Long camYeartodatecost) {
        set(PROPERTY_CAMYEARTODATECOST, camYeartodatecost);
    }

    public BigDecimal getCamBudgetted() {
        return (BigDecimal) get(PROPERTY_CAMBUDGETTED);
    }

    public void setCamBudgetted(BigDecimal camBudgetted) {
        set(PROPERTY_CAMBUDGETTED, camBudgetted);
    }

    public Boolean isCamIsassetup() {
        return (Boolean) get(PROPERTY_CAMISASSETUP);
    }

    public void setCamIsassetup(Boolean camIsassetup) {
        set(PROPERTY_CAMISASSETUP, camIsassetup);
    }

    public BigDecimal getCamBookvalue() {
        return (BigDecimal) get(COMPUTED_COLUMN_CAMBOOKVALUE);
    }

    public void setCamBookvalue(BigDecimal camBookvalue) {
        set(COMPUTED_COLUMN_CAMBOOKVALUE, camBookvalue);
    }

    public Costcenter getCamCostcenter() {
        return (Costcenter) get(PROPERTY_CAMCOSTCENTER);
    }

    public void setCamCostcenter(Costcenter camCostcenter) {
        set(PROPERTY_CAMCOSTCENTER, camCostcenter);
    }

    public Asset getCamParent() {
        return (Asset) get(PROPERTY_CAMPARENT);
    }

    public void setCamParent(Asset camParent) {
        set(PROPERTY_CAMPARENT, camParent);
    }

    public BigDecimal getCamTotaldowntime() {
        return (BigDecimal) get(PROPERTY_CAMTOTALDOWNTIME);
    }

    public void setCamTotaldowntime(BigDecimal camTotaldowntime) {
        set(PROPERTY_CAMTOTALDOWNTIME, camTotaldowntime);
    }

    public String getOezDocstatus() {
        return (String) get(PROPERTY_OEZDOCSTATUS);
    }

    public void setOezDocstatus(String oezDocstatus) {
        set(PROPERTY_OEZDOCSTATUS, oezDocstatus);
    }

    public Boolean isOEZPrepaymentAsset() {
        return (Boolean) get(PROPERTY_OEZPREPAYMENTASSET);
    }

    public void setOEZPrepaymentAsset(Boolean oEZPrepaymentAsset) {
        set(PROPERTY_OEZPREPAYMENTASSET, oEZPrepaymentAsset);
    }

    public Asset_ComputedColumns get_computedColumns() {
        return (Asset_ComputedColumns) get(PROPERTY__COMPUTEDCOLUMNS);
    }

    public void set_computedColumns(Asset_ComputedColumns _computedColumns) {
        set(PROPERTY__COMPUTEDCOLUMNS, _computedColumns);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
      return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
      return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<AmortizationLineAccountingDimension> getAmortizationLineAccountingDimensionList() {
      return (List<AmortizationLineAccountingDimension>) get(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setAmortizationLineAccountingDimensionList(List<AmortizationLineAccountingDimension> amortizationLineAccountingDimensionList) {
        set(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST, amortizationLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebt> getFINDoubtfulDebtList() {
      return (List<DoubtfulDebt>) get(PROPERTY_FINDOUBTFULDEBTLIST);
    }

    public void setFINDoubtfulDebtList(List<DoubtfulDebt> fINDoubtfulDebtList) {
        set(PROPERTY_FINDOUBTFULDEBTLIST, fINDoubtfulDebtList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
      return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<AmortizationLine> getFinancialMgmtAmortizationLineList() {
      return (List<AmortizationLine>) get(PROPERTY_FINANCIALMGMTAMORTIZATIONLINELIST);
    }

    public void setFinancialMgmtAmortizationLineList(List<AmortizationLine> financialMgmtAmortizationLineList) {
        set(PROPERTY_FINANCIALMGMTAMORTIZATIONLINELIST, financialMgmtAmortizationLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetEmCamParentIdList() {
      return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETEMCAMPARENTIDLIST);
    }

    public void setFinancialMgmtAssetEmCamParentIdList(List<Asset> financialMgmtAssetEmCamParentIdList) {
        set(PROPERTY_FINANCIALMGMTASSETEMCAMPARENTIDLIST, financialMgmtAssetEmCamParentIdList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetAccounts> getFinancialMgmtAssetAccountsList() {
      return (List<AssetAccounts>) get(PROPERTY_FINANCIALMGMTASSETACCOUNTSLIST);
    }

    public void setFinancialMgmtAssetAccountsList(List<AssetAccounts> financialMgmtAssetAccountsList) {
        set(PROPERTY_FINANCIALMGMTASSETACCOUNTSLIST, financialMgmtAssetAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getFinancialMgmtBudgetLineList() {
      return (List<BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getFinancialMgmtGLJournalList() {
      return (List<GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
      return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InOutLineAccountingDimension> getInOutLineAccountingDimensionList() {
      return (List<InOutLineAccountingDimension>) get(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInOutLineAccountingDimensionList(List<InOutLineAccountingDimension> inOutLineAccountingDimensionList) {
        set(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, inOutLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceList() {
      return (List<Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
      return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineAccountingDimension> getInvoiceLineAccountingDimensionList() {
      return (List<InvoiceLineAccountingDimension>) get(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInvoiceLineAccountingDimensionList(List<InvoiceLineAccountingDimension> invoiceLineAccountingDimensionList) {
        set(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, invoiceLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<MaintenanceSchedule> getManufacturingMaintenanceScheduleEmCamAssetIdList() {
      return (List<MaintenanceSchedule>) get(PROPERTY_MANUFACTURINGMAINTENANCESCHEDULEEMCAMASSETIDLIST);
    }

    public void setManufacturingMaintenanceScheduleEmCamAssetIdList(List<MaintenanceSchedule> manufacturingMaintenanceScheduleEmCamAssetIdList) {
        set(PROPERTY_MANUFACTURINGMAINTENANCESCHEDULEEMCAMASSETIDLIST, manufacturingMaintenanceScheduleEmCamAssetIdList);
    }

    @SuppressWarnings("unchecked")
    public List<InternalMovement> getMaterialMgmtInternalMovementList() {
      return (List<InternalMovement>) get(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLIST);
    }

    public void setMaterialMgmtInternalMovementList(List<InternalMovement> materialMgmtInternalMovementList) {
        set(PROPERTY_MATERIALMGMTINTERNALMOVEMENTLIST, materialMgmtInternalMovementList);
    }

    @SuppressWarnings("unchecked")
    public List<InventoryCount> getMaterialMgmtInventoryCountList() {
      return (List<InventoryCount>) get(PROPERTY_MATERIALMGMTINVENTORYCOUNTLIST);
    }

    public void setMaterialMgmtInventoryCountList(List<InventoryCount> materialMgmtInventoryCountList) {
        set(PROPERTY_MATERIALMGMTINVENTORYCOUNTLIST, materialMgmtInventoryCountList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionTransaction> getMaterialMgmtProductionTransactionList() {
      return (List<ProductionTransaction>) get(PROPERTY_MATERIALMGMTPRODUCTIONTRANSACTIONLIST);
    }

    public void setMaterialMgmtProductionTransactionList(List<ProductionTransaction> materialMgmtProductionTransactionList) {
        set(PROPERTY_MATERIALMGMTPRODUCTIONTRANSACTIONLIST, materialMgmtProductionTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
      return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOutLine> getMaterialMgmtShipmentInOutLineList() {
      return (List<ShipmentInOutLine>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST);
    }

    public void setMaterialMgmtShipmentInOutLineList(List<ShipmentInOutLine> materialMgmtShipmentInOutLineList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, materialMgmtShipmentInOutLineList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderList() {
      return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
      return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineAccountingDimension> getOrderLineAccountingDimensionList() {
      return (List<OrderLineAccountingDimension>) get(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setOrderLineAccountingDimensionList(List<OrderLineAccountingDimension> orderLineAccountingDimensionList) {
        set(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, orderLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<Sheet> getTimeAndExpenseSheetList() {
      return (List<Sheet>) get(PROPERTY_TIMEANDEXPENSESHEETLIST);
    }

    public void setTimeAndExpenseSheetList(List<Sheet> timeAndExpenseSheetList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLIST, timeAndExpenseSheetList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLine> getTimeAndExpenseSheetLineList() {
      return (List<SheetLine>) get(PROPERTY_TIMEANDEXPENSESHEETLINELIST);
    }

    public void setTimeAndExpenseSheetLineList(List<SheetLine> timeAndExpenseSheetLineList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINELIST, timeAndExpenseSheetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetPrintLine> getCamAssetprintlineList() {
      return (List<AssetPrintLine>) get(PROPERTY_CAMASSETPRINTLINELIST);
    }

    public void setCamAssetprintlineList(List<AssetPrintLine> camAssetprintlineList) {
        set(PROPERTY_CAMASSETPRINTLINELIST, camAssetprintlineList);
    }

    @SuppressWarnings("unchecked")
    public List<AssetItem> getCamItemList() {
      return (List<AssetItem>) get(PROPERTY_CAMITEMLIST);
    }

    public void setCamItemList(List<AssetItem> camItemList) {
        set(PROPERTY_CAMITEMLIST, camItemList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkOrder> getCamWorkorderList() {
      return (List<WorkOrder>) get(PROPERTY_CAMWORKORDERLIST);
    }

    public void setCamWorkorderList(List<WorkOrder> camWorkorderList) {
        set(PROPERTY_CAMWORKORDERLIST, camWorkorderList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkOrderAsset> getCamWorkorderassetList() {
      return (List<WorkOrderAsset>) get(PROPERTY_CAMWORKORDERASSETLIST);
    }

    public void setCamWorkorderassetList(List<WorkOrderAsset> camWorkorderassetList) {
        set(PROPERTY_CAMWORKORDERASSETLIST, camWorkorderassetList);
    }


    @Override
    public Object get(String propName) {
      if (COMPUTED_COLUMN_CAMBOOKVALUE.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getCamBookvalue();
      }
    
      return super.get(propName);
    }
}
