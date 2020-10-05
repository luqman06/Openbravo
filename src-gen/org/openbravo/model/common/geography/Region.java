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

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.dataimport.BusinessPartner;
import org.openbravo.model.dataimport.Invoice;
import org.openbravo.model.dataimport.Order;
import org.openbravo.model.dataimport.Tax;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.financialmgmt.tax.TaxZone;
import org.openbravo.model.shipping.ShippingCompanyFreight;
import org.wirabumi.gen.oez.OEZ_I_Inout;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;
import org.wirabumi.hris.employee.master.data.hris_i_employee_candidate;
/**
 * Entity class for entity Region (stored in table C_Region).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Region extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Region";
    public static final String ENTITY_NAME = "Region";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_CITYLIST = "cityList";
    public static final String PROPERTY_DATAIMPORTBUSINESSPARTNERLIST = "dataImportBusinessPartnerList";
    public static final String PROPERTY_DATAIMPORTINVOICELIST = "dataImportInvoiceList";
    public static final String PROPERTY_DATAIMPORTORDERLIST = "dataImportOrderList";
    public static final String PROPERTY_DATAIMPORTTAXLIST = "dataImportTaxList";
    public static final String PROPERTY_DATAIMPORTTAXDESTINATIONREGIONLIST = "dataImportTaxDestinationRegionList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATELIST = "financialMgmtTaxRateList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEDESTINATIONREGIONLIST = "financialMgmtTaxRateDestinationRegionList";
    public static final String PROPERTY_FINANCIALMGMTTAXZONEFROMREGIONLIST = "financialMgmtTaxZoneFromRegionList";
    public static final String PROPERTY_FINANCIALMGMTTAXZONEDESTINATIONREGIONLIST = "financialMgmtTaxZoneDestinationRegionList";
    public static final String PROPERTY_LOCATIONLIST = "locationList";
    public static final String PROPERTY_OEZIINOUTLIST = "oEZIInoutList";
    public static final String PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTLIST = "shippingShippingCompanyFreightList";
    public static final String PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTDESTINATIONREGIONLIST = "shippingShippingCompanyFreightDestinationRegionList";
    public static final String PROPERTY_HRISECLINESLIST = "hrisEcLinesList";
    public static final String PROPERTY_HRISIEMPLOYEECANDIDATELIST = "hrisIEmployeeCandidateList";

    public Region() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_CITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTTAXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTTAXDESTINATIONREGIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEDESTINATIONREGIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXZONEFROMREGIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXZONEDESTINATIONREGIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZIINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTDESTINATIONREGIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISECLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISIEMPLOYEECANDIDATELIST, new ArrayList<Object>());
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

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    @SuppressWarnings("unchecked")
    public List<City> getCityList() {
      return (List<City>) get(PROPERTY_CITYLIST);
    }

    public void setCityList(List<City> cityList) {
        set(PROPERTY_CITYLIST, cityList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getDataImportBusinessPartnerList() {
      return (List<BusinessPartner>) get(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST);
    }

    public void setDataImportBusinessPartnerList(List<BusinessPartner> dataImportBusinessPartnerList) {
        set(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST, dataImportBusinessPartnerList);
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
    public List<Tax> getDataImportTaxList() {
      return (List<Tax>) get(PROPERTY_DATAIMPORTTAXLIST);
    }

    public void setDataImportTaxList(List<Tax> dataImportTaxList) {
        set(PROPERTY_DATAIMPORTTAXLIST, dataImportTaxList);
    }

    @SuppressWarnings("unchecked")
    public List<Tax> getDataImportTaxDestinationRegionList() {
      return (List<Tax>) get(PROPERTY_DATAIMPORTTAXDESTINATIONREGIONLIST);
    }

    public void setDataImportTaxDestinationRegionList(List<Tax> dataImportTaxDestinationRegionList) {
        set(PROPERTY_DATAIMPORTTAXDESTINATIONREGIONLIST, dataImportTaxDestinationRegionList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRate> getFinancialMgmtTaxRateList() {
      return (List<TaxRate>) get(PROPERTY_FINANCIALMGMTTAXRATELIST);
    }

    public void setFinancialMgmtTaxRateList(List<TaxRate> financialMgmtTaxRateList) {
        set(PROPERTY_FINANCIALMGMTTAXRATELIST, financialMgmtTaxRateList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRate> getFinancialMgmtTaxRateDestinationRegionList() {
      return (List<TaxRate>) get(PROPERTY_FINANCIALMGMTTAXRATEDESTINATIONREGIONLIST);
    }

    public void setFinancialMgmtTaxRateDestinationRegionList(List<TaxRate> financialMgmtTaxRateDestinationRegionList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEDESTINATIONREGIONLIST, financialMgmtTaxRateDestinationRegionList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxZone> getFinancialMgmtTaxZoneFromRegionList() {
      return (List<TaxZone>) get(PROPERTY_FINANCIALMGMTTAXZONEFROMREGIONLIST);
    }

    public void setFinancialMgmtTaxZoneFromRegionList(List<TaxZone> financialMgmtTaxZoneFromRegionList) {
        set(PROPERTY_FINANCIALMGMTTAXZONEFROMREGIONLIST, financialMgmtTaxZoneFromRegionList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxZone> getFinancialMgmtTaxZoneDestinationRegionList() {
      return (List<TaxZone>) get(PROPERTY_FINANCIALMGMTTAXZONEDESTINATIONREGIONLIST);
    }

    public void setFinancialMgmtTaxZoneDestinationRegionList(List<TaxZone> financialMgmtTaxZoneDestinationRegionList) {
        set(PROPERTY_FINANCIALMGMTTAXZONEDESTINATIONREGIONLIST, financialMgmtTaxZoneDestinationRegionList);
    }

    @SuppressWarnings("unchecked")
    public List<Location> getLocationList() {
      return (List<Location>) get(PROPERTY_LOCATIONLIST);
    }

    public void setLocationList(List<Location> locationList) {
        set(PROPERTY_LOCATIONLIST, locationList);
    }

    @SuppressWarnings("unchecked")
    public List<OEZ_I_Inout> getOEZIInoutList() {
      return (List<OEZ_I_Inout>) get(PROPERTY_OEZIINOUTLIST);
    }

    public void setOEZIInoutList(List<OEZ_I_Inout> oEZIInoutList) {
        set(PROPERTY_OEZIINOUTLIST, oEZIInoutList);
    }

    @SuppressWarnings("unchecked")
    public List<ShippingCompanyFreight> getShippingShippingCompanyFreightList() {
      return (List<ShippingCompanyFreight>) get(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTLIST);
    }

    public void setShippingShippingCompanyFreightList(List<ShippingCompanyFreight> shippingShippingCompanyFreightList) {
        set(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTLIST, shippingShippingCompanyFreightList);
    }

    @SuppressWarnings("unchecked")
    public List<ShippingCompanyFreight> getShippingShippingCompanyFreightDestinationRegionList() {
      return (List<ShippingCompanyFreight>) get(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTDESTINATIONREGIONLIST);
    }

    public void setShippingShippingCompanyFreightDestinationRegionList(List<ShippingCompanyFreight> shippingShippingCompanyFreightDestinationRegionList) {
        set(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTDESTINATIONREGIONLIST, shippingShippingCompanyFreightDestinationRegionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ec_lines> getHrisEcLinesList() {
      return (List<hris_ec_lines>) get(PROPERTY_HRISECLINESLIST);
    }

    public void setHrisEcLinesList(List<hris_ec_lines> hrisEcLinesList) {
        set(PROPERTY_HRISECLINESLIST, hrisEcLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_i_employee_candidate> getHrisIEmployeeCandidateList() {
      return (List<hris_i_employee_candidate>) get(PROPERTY_HRISIEMPLOYEECANDIDATELIST);
    }

    public void setHrisIEmployeeCandidateList(List<hris_i_employee_candidate> hrisIEmployeeCandidateList) {
        set(PROPERTY_HRISIEMPLOYEECANDIDATELIST, hrisIEmployeeCandidateList);
    }

}
