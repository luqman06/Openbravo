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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.financialmgmt.tax.TaxCategory;
import org.openbravo.model.financialmgmt.tax.TaxRate;
/**
 * Entity class for entity DataImportTax (stored in table I_Tax).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Tax extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_Tax";
    public static final String ENTITY_NAME = "DataImportTax";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_TAXNAME = "taxName";
    public static final String PROPERTY_TAXDESCRIPTION = "taxDescription";
    public static final String PROPERTY_TAXSEARCHKEY = "taxSearchKey";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_PARENTTAXRATE = "parentTaxRate";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_DESTINATIONCOUNTRY = "destinationCountry";
    public static final String PROPERTY_DESTINATIONREGION = "destinationRegion";
    public static final String PROPERTY_TAXEXEMPT = "taxExempt";
    public static final String PROPERTY_SALESPURCHASETYPE = "salesPurchaseType";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_CASCADE = "cascade";
    public static final String PROPERTY_TAXCATEGORY = "taxCategory";
    public static final String PROPERTY_TAXCATEGORYNAME = "taxCategoryName";
    public static final String PROPERTY_TAXCATDESCRIPTION = "taxCatDescription";
    public static final String PROPERTY_COMMODITYCODE = "commodityCode";
    public static final String PROPERTY_BUSINESSPARTNERTAXCATEGORY = "businessPartnerTaxCategory";
    public static final String PROPERTY_BPTAXCATEGORYNAME = "bPTaxCategoryName";
    public static final String PROPERTY_BPTAXCATEGORYDESCRIPTION = "bPTaxCategoryDescription";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_PARENTTAXNAME = "parentTaxName";
    public static final String PROPERTY_COUNTRYFROMCODE = "countryFromCode";
    public static final String PROPERTY_COUNTRYTOCODE = "countryToCode";
    public static final String PROPERTY_REGIONFROMCODE = "regionFromCode";
    public static final String PROPERTY_REGIONTOCODE = "regionToCode";

    public Tax() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_TAXEXEMPT, false);
        setDefaultValue(PROPERTY_CASCADE, false);
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

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public String getTaxName() {
        return (String) get(PROPERTY_TAXNAME);
    }

    public void setTaxName(String taxName) {
        set(PROPERTY_TAXNAME, taxName);
    }

    public String getTaxDescription() {
        return (String) get(PROPERTY_TAXDESCRIPTION);
    }

    public void setTaxDescription(String taxDescription) {
        set(PROPERTY_TAXDESCRIPTION, taxDescription);
    }

    public String getTaxSearchKey() {
        return (String) get(PROPERTY_TAXSEARCHKEY);
    }

    public void setTaxSearchKey(String taxSearchKey) {
        set(PROPERTY_TAXSEARCHKEY, taxSearchKey);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public BigDecimal getRate() {
        return (BigDecimal) get(PROPERTY_RATE);
    }

    public void setRate(BigDecimal rate) {
        set(PROPERTY_RATE, rate);
    }

    public TaxRate getParentTaxRate() {
        return (TaxRate) get(PROPERTY_PARENTTAXRATE);
    }

    public void setParentTaxRate(TaxRate parentTaxRate) {
        set(PROPERTY_PARENTTAXRATE, parentTaxRate);
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

    public Country getDestinationCountry() {
        return (Country) get(PROPERTY_DESTINATIONCOUNTRY);
    }

    public void setDestinationCountry(Country destinationCountry) {
        set(PROPERTY_DESTINATIONCOUNTRY, destinationCountry);
    }

    public Region getDestinationRegion() {
        return (Region) get(PROPERTY_DESTINATIONREGION);
    }

    public void setDestinationRegion(Region destinationRegion) {
        set(PROPERTY_DESTINATIONREGION, destinationRegion);
    }

    public Boolean isTaxExempt() {
        return (Boolean) get(PROPERTY_TAXEXEMPT);
    }

    public void setTaxExempt(Boolean taxExempt) {
        set(PROPERTY_TAXEXEMPT, taxExempt);
    }

    public String getSalesPurchaseType() {
        return (String) get(PROPERTY_SALESPURCHASETYPE);
    }

    public void setSalesPurchaseType(String salesPurchaseType) {
        set(PROPERTY_SALESPURCHASETYPE, salesPurchaseType);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Boolean isCascade() {
        return (Boolean) get(PROPERTY_CASCADE);
    }

    public void setCascade(Boolean cascade) {
        set(PROPERTY_CASCADE, cascade);
    }

    public TaxCategory getTaxCategory() {
        return (TaxCategory) get(PROPERTY_TAXCATEGORY);
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        set(PROPERTY_TAXCATEGORY, taxCategory);
    }

    public String getTaxCategoryName() {
        return (String) get(PROPERTY_TAXCATEGORYNAME);
    }

    public void setTaxCategoryName(String taxCategoryName) {
        set(PROPERTY_TAXCATEGORYNAME, taxCategoryName);
    }

    public String getTaxCatDescription() {
        return (String) get(PROPERTY_TAXCATDESCRIPTION);
    }

    public void setTaxCatDescription(String taxCatDescription) {
        set(PROPERTY_TAXCATDESCRIPTION, taxCatDescription);
    }

    public String getCommodityCode() {
        return (String) get(PROPERTY_COMMODITYCODE);
    }

    public void setCommodityCode(String commodityCode) {
        set(PROPERTY_COMMODITYCODE, commodityCode);
    }

    public org.openbravo.model.common.businesspartner.TaxCategory getBusinessPartnerTaxCategory() {
        return (org.openbravo.model.common.businesspartner.TaxCategory) get(PROPERTY_BUSINESSPARTNERTAXCATEGORY);
    }

    public void setBusinessPartnerTaxCategory(org.openbravo.model.common.businesspartner.TaxCategory businessPartnerTaxCategory) {
        set(PROPERTY_BUSINESSPARTNERTAXCATEGORY, businessPartnerTaxCategory);
    }

    public String getBPTaxCategoryName() {
        return (String) get(PROPERTY_BPTAXCATEGORYNAME);
    }

    public void setBPTaxCategoryName(String bPTaxCategoryName) {
        set(PROPERTY_BPTAXCATEGORYNAME, bPTaxCategoryName);
    }

    public String getBPTaxCategoryDescription() {
        return (String) get(PROPERTY_BPTAXCATEGORYDESCRIPTION);
    }

    public void setBPTaxCategoryDescription(String bPTaxCategoryDescription) {
        set(PROPERTY_BPTAXCATEGORYDESCRIPTION, bPTaxCategoryDescription);
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

    public String getParentTaxName() {
        return (String) get(PROPERTY_PARENTTAXNAME);
    }

    public void setParentTaxName(String parentTaxName) {
        set(PROPERTY_PARENTTAXNAME, parentTaxName);
    }

    public String getCountryFromCode() {
        return (String) get(PROPERTY_COUNTRYFROMCODE);
    }

    public void setCountryFromCode(String countryFromCode) {
        set(PROPERTY_COUNTRYFROMCODE, countryFromCode);
    }

    public String getCountryToCode() {
        return (String) get(PROPERTY_COUNTRYTOCODE);
    }

    public void setCountryToCode(String countryToCode) {
        set(PROPERTY_COUNTRYTOCODE, countryToCode);
    }

    public String getRegionFromCode() {
        return (String) get(PROPERTY_REGIONFROMCODE);
    }

    public void setRegionFromCode(String regionFromCode) {
        set(PROPERTY_REGIONFROMCODE, regionFromCode);
    }

    public String getRegionToCode() {
        return (String) get(PROPERTY_REGIONTOCODE);
    }

    public void setRegionToCode(String regionToCode) {
        set(PROPERTY_REGIONTOCODE, regionToCode);
    }

}
