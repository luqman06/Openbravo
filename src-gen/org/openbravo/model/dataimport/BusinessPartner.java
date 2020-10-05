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

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.Category;
import org.openbravo.model.common.businesspartner.Greeting;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
/**
 * Entity class for entity DataImportBusinessPartner (stored in table I_BPartner).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class BusinessPartner extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_BPartner";
    public static final String ENTITY_NAME = "DataImportBusinessPartner";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_NAME2 = "name2";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_DUNS = "dUNS";
    public static final String PROPERTY_TAXID = "taxID";
    public static final String PROPERTY_NAICSSIC = "nAICSSIC";
    public static final String PROPERTY_GROUPKEY = "groupKey";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORY = "businessPartnerCategory";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_ADDRESSLINE1 = "addressLine1";
    public static final String PROPERTY_ADDRESSLINE2 = "addressLine2";
    public static final String PROPERTY_POSTALCODE = "postalCode";
    public static final String PROPERTY_POSTALADD = "postalAdd";
    public static final String PROPERTY_CITYNAME = "cityName";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_REGIONNAME = "regionName";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_ISOCOUNTRYCODE = "iSOCountryCode";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_CONTACTNAME = "contactName";
    public static final String PROPERTY_CONTACTDESCRIPTION = "contactDescription";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_PHONE = "phone";
    public static final String PROPERTY_ALTERNATIVEPHONE = "alternativePhone";
    public static final String PROPERTY_FAX = "fax";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_PASSWORD = "password";
    public static final String PROPERTY_BIRTHDAY = "birthday";
    public static final String PROPERTY_GREETING = "greeting";
    public static final String PROPERTY_BPCONTACTGREETING = "bPContactGreeting";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_OEZISCUSTOMER = "oezIscustomer";
    public static final String PROPERTY_OEZISEMPLOYEE = "oezIsemployee";
    public static final String PROPERTY_OEZISVENDOR = "oezIsvendor";

    public BusinessPartner() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
        setDefaultValue(PROPERTY_OEZISCUSTOMER, false);
        setDefaultValue(PROPERTY_OEZISEMPLOYEE, false);
        setDefaultValue(PROPERTY_OEZISVENDOR, false);
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

    public org.openbravo.model.common.businesspartner.BusinessPartner getBusinessPartner() {
        return (org.openbravo.model.common.businesspartner.BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(org.openbravo.model.common.businesspartner.BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
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

    public String getName2() {
        return (String) get(PROPERTY_NAME2);
    }

    public void setName2(String name2) {
        set(PROPERTY_NAME2, name2);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getDUNS() {
        return (String) get(PROPERTY_DUNS);
    }

    public void setDUNS(String dUNS) {
        set(PROPERTY_DUNS, dUNS);
    }

    public String getTaxID() {
        return (String) get(PROPERTY_TAXID);
    }

    public void setTaxID(String taxID) {
        set(PROPERTY_TAXID, taxID);
    }

    public String getNAICSSIC() {
        return (String) get(PROPERTY_NAICSSIC);
    }

    public void setNAICSSIC(String nAICSSIC) {
        set(PROPERTY_NAICSSIC, nAICSSIC);
    }

    public String getGroupKey() {
        return (String) get(PROPERTY_GROUPKEY);
    }

    public void setGroupKey(String groupKey) {
        set(PROPERTY_GROUPKEY, groupKey);
    }

    public Category getBusinessPartnerCategory() {
        return (Category) get(PROPERTY_BUSINESSPARTNERCATEGORY);
    }

    public void setBusinessPartnerCategory(Category businessPartnerCategory) {
        set(PROPERTY_BUSINESSPARTNERCATEGORY, businessPartnerCategory);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
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

    public String getCityName() {
        return (String) get(PROPERTY_CITYNAME);
    }

    public void setCityName(String cityName) {
        set(PROPERTY_CITYNAME, cityName);
    }

    public Region getRegion() {
        return (Region) get(PROPERTY_REGION);
    }

    public void setRegion(Region region) {
        set(PROPERTY_REGION, region);
    }

    public String getRegionName() {
        return (String) get(PROPERTY_REGIONNAME);
    }

    public void setRegionName(String regionName) {
        set(PROPERTY_REGIONNAME, regionName);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public String getISOCountryCode() {
        return (String) get(PROPERTY_ISOCOUNTRYCODE);
    }

    public void setISOCountryCode(String iSOCountryCode) {
        set(PROPERTY_ISOCOUNTRYCODE, iSOCountryCode);
    }

    public String getPosition() {
        return (String) get(PROPERTY_POSITION);
    }

    public void setPosition(String position) {
        set(PROPERTY_POSITION, position);
    }

    public String getContactName() {
        return (String) get(PROPERTY_CONTACTNAME);
    }

    public void setContactName(String contactName) {
        set(PROPERTY_CONTACTNAME, contactName);
    }

    public String getContactDescription() {
        return (String) get(PROPERTY_CONTACTDESCRIPTION);
    }

    public void setContactDescription(String contactDescription) {
        set(PROPERTY_CONTACTDESCRIPTION, contactDescription);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public String getPhone() {
        return (String) get(PROPERTY_PHONE);
    }

    public void setPhone(String phone) {
        set(PROPERTY_PHONE, phone);
    }

    public String getAlternativePhone() {
        return (String) get(PROPERTY_ALTERNATIVEPHONE);
    }

    public void setAlternativePhone(String alternativePhone) {
        set(PROPERTY_ALTERNATIVEPHONE, alternativePhone);
    }

    public String getFax() {
        return (String) get(PROPERTY_FAX);
    }

    public void setFax(String fax) {
        set(PROPERTY_FAX, fax);
    }

    public String getEmail() {
        return (String) get(PROPERTY_EMAIL);
    }

    public void setEmail(String email) {
        set(PROPERTY_EMAIL, email);
    }

    public String getPassword() {
        return (String) get(PROPERTY_PASSWORD);
    }

    public void setPassword(String password) {
        set(PROPERTY_PASSWORD, password);
    }

    public Date getBirthday() {
        return (Date) get(PROPERTY_BIRTHDAY);
    }

    public void setBirthday(Date birthday) {
        set(PROPERTY_BIRTHDAY, birthday);
    }

    public Greeting getGreeting() {
        return (Greeting) get(PROPERTY_GREETING);
    }

    public void setGreeting(Greeting greeting) {
        set(PROPERTY_GREETING, greeting);
    }

    public String getBPContactGreeting() {
        return (String) get(PROPERTY_BPCONTACTGREETING);
    }

    public void setBPContactGreeting(String bPContactGreeting) {
        set(PROPERTY_BPCONTACTGREETING, bPContactGreeting);
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

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
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

    public Boolean isOezIscustomer() {
        return (Boolean) get(PROPERTY_OEZISCUSTOMER);
    }

    public void setOezIscustomer(Boolean oezIscustomer) {
        set(PROPERTY_OEZISCUSTOMER, oezIscustomer);
    }

    public Boolean isOezIsemployee() {
        return (Boolean) get(PROPERTY_OEZISEMPLOYEE);
    }

    public void setOezIsemployee(Boolean oezIsemployee) {
        set(PROPERTY_OEZISEMPLOYEE, oezIsemployee);
    }

    public Boolean isOezIsvendor() {
        return (Boolean) get(PROPERTY_OEZISVENDOR);
    }

    public void setOezIsvendor(Boolean oezIsvendor) {
        set(PROPERTY_OEZISVENDOR, oezIsvendor);
    }

}
