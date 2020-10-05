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
package org.openbravo.model.common.geography;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.bank.Bank;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.dataimport.GLJournal;
import org.openbravo.model.dataimport.Invoice;
import org.openbravo.model.dataimport.Order;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchemaElement;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.project.Project;
import org.wirabumi.gen.oez.ImportAsset;
import org.wirabumi.gen.oez.OEZ_I_Inout;
import org.wirabumi.hris.employee.master.data.hris_contact;
import org.wirabumi.hris.employee.master.data.hris_insured_payee;
import org.wirabumi.hris.employee.master.data.hris_pengdata_kel;
/**
 * Entity class for entity Location (stored in table C_Location).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Location extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Location";
    public static final String ENTITY_NAME = "Location";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ADDRESSLINE1 = "addressLine1";
    public static final String PROPERTY_ADDRESSLINE2 = "addressLine2";
    public static final String PROPERTY_CITYNAME = "cityName";
    public static final String PROPERTY_POSTALCODE = "postalCode";
    public static final String PROPERTY_POSTALADD = "postalAdd";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_CITY = "city";
    public static final String PROPERTY_REGIONNAME = "regionName";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONFROMADDRESSLIST = "aPRMFinAccTransactionAcctVLocationFromAddressList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONTOADDRESSLIST = "aPRMFinAccTransactionAcctVLocationToAddressList";
    public static final String PROPERTY_BANKLIST = "bankList";
    public static final String PROPERTY_BUSINESSPARTNERLOCATIONLIST = "businessPartnerLocationList";
    public static final String PROPERTY_DATAIMPORTGLJOURNALLOCATIONTOADDRESSLIST = "dataImportGLJournalLocationToAddressList";
    public static final String PROPERTY_DATAIMPORTGLJOURNALLOCATIONFROMADDRESSLIST = "dataImportGLJournalLocationFromAddressList";
    public static final String PROPERTY_DATAIMPORTINVOICELIST = "dataImportInvoiceList";
    public static final String PROPERTY_DATAIMPORTORDERLIST = "dataImportOrderList";
    public static final String PROPERTY_FINFINANCIALACCOUNTLIST = "fINFinancialAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONFROMADDRESSLIST = "financialMgmtAccountingCombinationLocationFromAddressList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONTOADDRESSLIST = "financialMgmtAccountingCombinationLocationToAddressList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONFROMADDRESSLIST = "financialMgmtAccountingFactLocationFromAddressList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONTOADDRESSLIST = "financialMgmtAccountingFactLocationToAddressList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST = "financialMgmtAcctSchemaElementList";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_OEZIINOUTLIST = "oEZIInoutList";
    public static final String PROPERTY_OEZIINOUTCSHIPLOCATIONIDLIST = "oEZIInoutCShipLocationIDList";
    public static final String PROPERTY_ORGANIZATIONEMOEZWAREHOUSEADDRESSLIST = "organizationEMOezWarehouseaddressList";
    public static final String PROPERTY_ORGANIZATIONINFORMATIONLIST = "organizationInformationList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_WAREHOUSELIST = "warehouseList";
    public static final String PROPERTY_HRISCONTACTLIST = "hrisContactList";
    public static final String PROPERTY_HRISINSUREDPAYEELIST = "hrisInsuredPayeeList";
    public static final String PROPERTY_HRISPENGDATAKELLIST = "hrisPengdataKelList";
    public static final String PROPERTY_OEZIASSETLIST = "oezIAssetList";

    public Location() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONFROMADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONTOADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERLOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTGLJOURNALLOCATIONTOADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTGLJOURNALLOCATIONFROMADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONFROMADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONTOADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONFROMADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONTOADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZIINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZIINOUTCSHIPLOCATIONIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONEMOEZWAREHOUSEADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCONTACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISINSUREDPAYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISPENGDATAKELLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZIASSETLIST, new ArrayList<Object>());
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

    public String getAddressLine1() {
        return (String) get(PROPERTY_ADDRESSLINE1);
    }

    public void setAddressLine1(String addressLine1) {
        set(PROPERTY_ADDRESSLINE1, addressLine1);
    }

    public String getAddressLine2() {
        return (String) get(PROPERTY_ADDRESSLINE2);
    }

    public void setAddressLine2(String addressLine2) {
        set(PROPERTY_ADDRESSLINE2, addressLine2);
    }

    public String getCityName() {
        return (String) get(PROPERTY_CITYNAME);
    }

    public void setCityName(String cityName) {
        set(PROPERTY_CITYNAME, cityName);
    }

    public String getPostalCode() {
        return (String) get(PROPERTY_POSTALCODE);
    }

    public void setPostalCode(String postalCode) {
        set(PROPERTY_POSTALCODE, postalCode);
    }

    public String getPostalAdd() {
        return (String) get(PROPERTY_POSTALADD);
    }

    public void setPostalAdd(String postalAdd) {
        set(PROPERTY_POSTALADD, postalAdd);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public Region getRegion() {
        return (Region) get(PROPERTY_REGION);
    }

    public void setRegion(Region region) {
        set(PROPERTY_REGION, region);
    }

    public City getCity() {
        return (City) get(PROPERTY_CITY);
    }

    public void setCity(City city) {
        set(PROPERTY_CITY, city);
    }

    public String getRegionName() {
        return (String) get(PROPERTY_REGIONNAME);
    }

    public void setRegionName(String regionName) {
        set(PROPERTY_REGIONNAME, regionName);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVLocationFromAddressList() {
      return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONFROMADDRESSLIST);
    }

    public void setAPRMFinAccTransactionAcctVLocationFromAddressList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVLocationFromAddressList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONFROMADDRESSLIST, aPRMFinAccTransactionAcctVLocationFromAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVLocationToAddressList() {
      return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONTOADDRESSLIST);
    }

    public void setAPRMFinAccTransactionAcctVLocationToAddressList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVLocationToAddressList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONTOADDRESSLIST, aPRMFinAccTransactionAcctVLocationToAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<Bank> getBankList() {
      return (List<Bank>) get(PROPERTY_BANKLIST);
    }

    public void setBankList(List<Bank> bankList) {
        set(PROPERTY_BANKLIST, bankList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.businesspartner.Location> getBusinessPartnerLocationList() {
      return (List<org.openbravo.model.common.businesspartner.Location>) get(PROPERTY_BUSINESSPARTNERLOCATIONLIST);
    }

    public void setBusinessPartnerLocationList(List<org.openbravo.model.common.businesspartner.Location> businessPartnerLocationList) {
        set(PROPERTY_BUSINESSPARTNERLOCATIONLIST, businessPartnerLocationList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getDataImportGLJournalLocationToAddressList() {
      return (List<GLJournal>) get(PROPERTY_DATAIMPORTGLJOURNALLOCATIONTOADDRESSLIST);
    }

    public void setDataImportGLJournalLocationToAddressList(List<GLJournal> dataImportGLJournalLocationToAddressList) {
        set(PROPERTY_DATAIMPORTGLJOURNALLOCATIONTOADDRESSLIST, dataImportGLJournalLocationToAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getDataImportGLJournalLocationFromAddressList() {
      return (List<GLJournal>) get(PROPERTY_DATAIMPORTGLJOURNALLOCATIONFROMADDRESSLIST);
    }

    public void setDataImportGLJournalLocationFromAddressList(List<GLJournal> dataImportGLJournalLocationFromAddressList) {
        set(PROPERTY_DATAIMPORTGLJOURNALLOCATIONFROMADDRESSLIST, dataImportGLJournalLocationFromAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getDataImportInvoiceList() {
      return (List<Invoice>) get(PROPERTY_DATAIMPORTINVOICELIST);
    }

    public void setDataImportInvoiceList(List<Invoice> dataImportInvoiceList) {
        set(PROPERTY_DATAIMPORTINVOICELIST, dataImportInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getDataImportOrderList() {
      return (List<Order>) get(PROPERTY_DATAIMPORTORDERLIST);
    }

    public void setDataImportOrderList(List<Order> dataImportOrderList) {
        set(PROPERTY_DATAIMPORTORDERLIST, dataImportOrderList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccount> getFINFinancialAccountList() {
      return (List<FIN_FinancialAccount>) get(PROPERTY_FINFINANCIALACCOUNTLIST);
    }

    public void setFINFinancialAccountList(List<FIN_FinancialAccount> fINFinancialAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTLIST, fINFinancialAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationLocationFromAddressList() {
      return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONFROMADDRESSLIST);
    }

    public void setFinancialMgmtAccountingCombinationLocationFromAddressList(List<AccountingCombination> financialMgmtAccountingCombinationLocationFromAddressList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONFROMADDRESSLIST, financialMgmtAccountingCombinationLocationFromAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationLocationToAddressList() {
      return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONTOADDRESSLIST);
    }

    public void setFinancialMgmtAccountingCombinationLocationToAddressList(List<AccountingCombination> financialMgmtAccountingCombinationLocationToAddressList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONTOADDRESSLIST, financialMgmtAccountingCombinationLocationToAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactLocationFromAddressList() {
      return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONFROMADDRESSLIST);
    }

    public void setFinancialMgmtAccountingFactLocationFromAddressList(List<AccountingFact> financialMgmtAccountingFactLocationFromAddressList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONFROMADDRESSLIST, financialMgmtAccountingFactLocationFromAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactLocationToAddressList() {
      return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONTOADDRESSLIST);
    }

    public void setFinancialMgmtAccountingFactLocationToAddressList(List<AccountingFact> financialMgmtAccountingFactLocationToAddressList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONTOADDRESSLIST, financialMgmtAccountingFactLocationToAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaElement> getFinancialMgmtAcctSchemaElementList() {
      return (List<AcctSchemaElement>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaElementList(List<AcctSchemaElement> financialMgmtAcctSchemaElementList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, financialMgmtAcctSchemaElementList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
      return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<OEZ_I_Inout> getOEZIInoutList() {
      return (List<OEZ_I_Inout>) get(PROPERTY_OEZIINOUTLIST);
    }

    public void setOEZIInoutList(List<OEZ_I_Inout> oEZIInoutList) {
        set(PROPERTY_OEZIINOUTLIST, oEZIInoutList);
    }

    @SuppressWarnings("unchecked")
    public List<OEZ_I_Inout> getOEZIInoutCShipLocationIDList() {
      return (List<OEZ_I_Inout>) get(PROPERTY_OEZIINOUTCSHIPLOCATIONIDLIST);
    }

    public void setOEZIInoutCShipLocationIDList(List<OEZ_I_Inout> oEZIInoutCShipLocationIDList) {
        set(PROPERTY_OEZIINOUTCSHIPLOCATIONIDLIST, oEZIInoutCShipLocationIDList);
    }

    @SuppressWarnings("unchecked")
    public List<Organization> getOrganizationEMOezWarehouseaddressList() {
      return (List<Organization>) get(PROPERTY_ORGANIZATIONEMOEZWAREHOUSEADDRESSLIST);
    }

    public void setOrganizationEMOezWarehouseaddressList(List<Organization> organizationEMOezWarehouseaddressList) {
        set(PROPERTY_ORGANIZATIONEMOEZWAREHOUSEADDRESSLIST, organizationEMOezWarehouseaddressList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationInformation> getOrganizationInformationList() {
      return (List<OrganizationInformation>) get(PROPERTY_ORGANIZATIONINFORMATIONLIST);
    }

    public void setOrganizationInformationList(List<OrganizationInformation> organizationInformationList) {
        set(PROPERTY_ORGANIZATIONINFORMATIONLIST, organizationInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
      return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<Warehouse> getWarehouseList() {
      return (List<Warehouse>) get(PROPERTY_WAREHOUSELIST);
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        set(PROPERTY_WAREHOUSELIST, warehouseList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_contact> getHrisContactList() {
      return (List<hris_contact>) get(PROPERTY_HRISCONTACTLIST);
    }

    public void setHrisContactList(List<hris_contact> hrisContactList) {
        set(PROPERTY_HRISCONTACTLIST, hrisContactList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_insured_payee> getHrisInsuredPayeeList() {
      return (List<hris_insured_payee>) get(PROPERTY_HRISINSUREDPAYEELIST);
    }

    public void setHrisInsuredPayeeList(List<hris_insured_payee> hrisInsuredPayeeList) {
        set(PROPERTY_HRISINSUREDPAYEELIST, hrisInsuredPayeeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_pengdata_kel> getHrisPengdataKelList() {
      return (List<hris_pengdata_kel>) get(PROPERTY_HRISPENGDATAKELLIST);
    }

    public void setHrisPengdataKelList(List<hris_pengdata_kel> hrisPengdataKelList) {
        set(PROPERTY_HRISPENGDATAKELLIST, hrisPengdataKelList);
    }

    @SuppressWarnings("unchecked")
    public List<ImportAsset> getOezIAssetList() {
      return (List<ImportAsset>) get(PROPERTY_OEZIASSETLIST);
    }

    public void setOezIAssetList(List<ImportAsset> oezIAssetList) {
        set(PROPERTY_OEZIASSETLIST, oezIAssetList);
    }

}
