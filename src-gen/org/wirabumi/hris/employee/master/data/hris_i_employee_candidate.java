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
package org.wirabumi.hris.employee.master.data;

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
import org.openbravo.model.manufacturing.cost.CostCenter;
/**
 * Entity class for entity hris_i_employee_candidate (stored in table hris_i_employee_candidate).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_i_employee_candidate extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_i_employee_candidate";
    public static final String ENTITY_NAME = "hris_i_employee_candidate";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_EMPLOYEENAME = "employeeName";
    public static final String PROPERTY_BIRTHPLACE = "birthPlace";
    public static final String PROPERTY_BIRTHDAY = "birthday";
    public static final String PROPERTY_STRINGSEX = "stringSex";
    public static final String PROPERTY_SEX = "sex";
    public static final String PROPERTY_STRINGRELIGION = "stringReligion";
    public static final String PROPERTY_RELIGION = "religion";
    public static final String PROPERTY_STRINGNATIONALITY = "stringNationality";
    public static final String PROPERTY_NATIONALITY = "nationality";
    public static final String PROPERTY_STRINGMARITALSTATUS = "stringMaritalstatus";
    public static final String PROPERTY_MARITALSTATUS = "maritalStatus";
    public static final String PROPERTY_TAXID = "taxID";
    public static final String PROPERTY_STRINGTAXMARITALSTATUS = "stringTaxmaritalstatus";
    public static final String PROPERTY_PPHTAXMARITALSTATUS = "pPHTaxMaritalStatus";
    public static final String PROPERTY_STRINGBLODTYPE = "stringBlodtype";
    public static final String PROPERTY_BLOODTYPE = "bloodType";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_JOINDATE = "joindate";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ADDRESSLINE1 = "addressLine1";
    public static final String PROPERTY_ADDRESSLINE2 = "addressLine2";
    public static final String PROPERTY_POSTALCODE = "postalCode";
    public static final String PROPERTY_CITYNAME = "cityName";
    public static final String PROPERTY_STRINGCOUNTRY = "stringCountry";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_STRINGREGION = "stringRegion";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_PHONE = "phone";
    public static final String PROPERTY_ALTERNATIVEPHONE = "alternativePhone";
    public static final String PROPERTY_STRINGEDUCATIONLEVEL = "stringEducationLevel";
    public static final String PROPERTY_STRINGDICIPLINE = "stringDicipline";
    public static final String PROPERTY_EDUCATIONDICIPLINE = "educationDicipline";
    public static final String PROPERTY_INSTIUTIONNAME = "instiutionName";
    public static final String PROPERTY_CERTIFICATENUMBER = "certificateNumber";
    public static final String PROPERTY_CERTIFICATEDATE = "certificateDate";
    public static final String PROPERTY_NILAI = "nilai";
    public static final String PROPERTY_CONTRACTTYPE = "contractType";
    public static final String PROPERTY_STRINGPOSITION = "stringPosition";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_STRINGCOSTCENTER = "stringCostcenter";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_STRINGJOBTITLE = "stringJobTitle";
    public static final String PROPERTY_JOBTITLE = "jobTitle";
    public static final String PROPERTY_STRINGLEVEL = "stringLevel";
    public static final String PROPERTY_GRADE = "grade";
    public static final String PROPERTY_STRINGECHELON = "stringEchelon";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_BANKNAME = "bankName";
    public static final String PROPERTY_GENERICACCOUNTNO = "genericAccountNo";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_CREATECANDIDATE = "createCandidate";
    public static final String PROPERTY_IMPORTED = "imported";
    public static final String PROPERTY_INSTITUTION = "institution";
    public static final String PROPERTY_HRISIDCARDNO = "hrisIdcardNo";
    public static final String PROPERTY_HRISTITLENAME = "hrisTitleName";

    public hris_i_employee_candidate() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATECANDIDATE, false);
        setDefaultValue(PROPERTY_IMPORTED, false);
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

    public String getEmployeeName() {
        return (String) get(PROPERTY_EMPLOYEENAME);
    }

    public void setEmployeeName(String employeeName) {
        set(PROPERTY_EMPLOYEENAME, employeeName);
    }

    public String getBirthPlace() {
        return (String) get(PROPERTY_BIRTHPLACE);
    }

    public void setBirthPlace(String birthPlace) {
        set(PROPERTY_BIRTHPLACE, birthPlace);
    }

    public Date getBirthday() {
        return (Date) get(PROPERTY_BIRTHDAY);
    }

    public void setBirthday(Date birthday) {
        set(PROPERTY_BIRTHDAY, birthday);
    }

    public String getStringSex() {
        return (String) get(PROPERTY_STRINGSEX);
    }

    public void setStringSex(String stringSex) {
        set(PROPERTY_STRINGSEX, stringSex);
    }

    public String getSex() {
        return (String) get(PROPERTY_SEX);
    }

    public void setSex(String sex) {
        set(PROPERTY_SEX, sex);
    }

    public String getStringReligion() {
        return (String) get(PROPERTY_STRINGRELIGION);
    }

    public void setStringReligion(String stringReligion) {
        set(PROPERTY_STRINGRELIGION, stringReligion);
    }

    public String getReligion() {
        return (String) get(PROPERTY_RELIGION);
    }

    public void setReligion(String religion) {
        set(PROPERTY_RELIGION, religion);
    }

    public String getStringNationality() {
        return (String) get(PROPERTY_STRINGNATIONALITY);
    }

    public void setStringNationality(String stringNationality) {
        set(PROPERTY_STRINGNATIONALITY, stringNationality);
    }

    public Country getNationality() {
        return (Country) get(PROPERTY_NATIONALITY);
    }

    public void setNationality(Country nationality) {
        set(PROPERTY_NATIONALITY, nationality);
    }

    public String getStringMaritalstatus() {
        return (String) get(PROPERTY_STRINGMARITALSTATUS);
    }

    public void setStringMaritalstatus(String stringMaritalstatus) {
        set(PROPERTY_STRINGMARITALSTATUS, stringMaritalstatus);
    }

    public String getMaritalStatus() {
        return (String) get(PROPERTY_MARITALSTATUS);
    }

    public void setMaritalStatus(String maritalStatus) {
        set(PROPERTY_MARITALSTATUS, maritalStatus);
    }

    public String getTaxID() {
        return (String) get(PROPERTY_TAXID);
    }

    public void setTaxID(String taxID) {
        set(PROPERTY_TAXID, taxID);
    }

    public String getStringTaxmaritalstatus() {
        return (String) get(PROPERTY_STRINGTAXMARITALSTATUS);
    }

    public void setStringTaxmaritalstatus(String stringTaxmaritalstatus) {
        set(PROPERTY_STRINGTAXMARITALSTATUS, stringTaxmaritalstatus);
    }

    public String getPPHTaxMaritalStatus() {
        return (String) get(PROPERTY_PPHTAXMARITALSTATUS);
    }

    public void setPPHTaxMaritalStatus(String pPHTaxMaritalStatus) {
        set(PROPERTY_PPHTAXMARITALSTATUS, pPHTaxMaritalStatus);
    }

    public String getStringBlodtype() {
        return (String) get(PROPERTY_STRINGBLODTYPE);
    }

    public void setStringBlodtype(String stringBlodtype) {
        set(PROPERTY_STRINGBLODTYPE, stringBlodtype);
    }

    public String getBloodType() {
        return (String) get(PROPERTY_BLOODTYPE);
    }

    public void setBloodType(String bloodType) {
        set(PROPERTY_BLOODTYPE, bloodType);
    }

    public String getEmail() {
        return (String) get(PROPERTY_EMAIL);
    }

    public void setEmail(String email) {
        set(PROPERTY_EMAIL, email);
    }

    public Date getJoindate() {
        return (Date) get(PROPERTY_JOINDATE);
    }

    public void setJoindate(Date joindate) {
        set(PROPERTY_JOINDATE, joindate);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
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

    public String getCityName() {
        return (String) get(PROPERTY_CITYNAME);
    }

    public void setCityName(String cityName) {
        set(PROPERTY_CITYNAME, cityName);
    }

    public String getStringCountry() {
        return (String) get(PROPERTY_STRINGCOUNTRY);
    }

    public void setStringCountry(String stringCountry) {
        set(PROPERTY_STRINGCOUNTRY, stringCountry);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public String getStringRegion() {
        return (String) get(PROPERTY_STRINGREGION);
    }

    public void setStringRegion(String stringRegion) {
        set(PROPERTY_STRINGREGION, stringRegion);
    }

    public Region getRegion() {
        return (Region) get(PROPERTY_REGION);
    }

    public void setRegion(Region region) {
        set(PROPERTY_REGION, region);
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

    public String getStringEducationLevel() {
        return (String) get(PROPERTY_STRINGEDUCATIONLEVEL);
    }

    public void setStringEducationLevel(String stringEducationLevel) {
        set(PROPERTY_STRINGEDUCATIONLEVEL, stringEducationLevel);
    }

    public String getStringDicipline() {
        return (String) get(PROPERTY_STRINGDICIPLINE);
    }

    public void setStringDicipline(String stringDicipline) {
        set(PROPERTY_STRINGDICIPLINE, stringDicipline);
    }

    public hris_dicipline getEducationDicipline() {
        return (hris_dicipline) get(PROPERTY_EDUCATIONDICIPLINE);
    }

    public void setEducationDicipline(hris_dicipline educationDicipline) {
        set(PROPERTY_EDUCATIONDICIPLINE, educationDicipline);
    }

    public String getInstiutionName() {
        return (String) get(PROPERTY_INSTIUTIONNAME);
    }

    public void setInstiutionName(String instiutionName) {
        set(PROPERTY_INSTIUTIONNAME, instiutionName);
    }

    public String getCertificateNumber() {
        return (String) get(PROPERTY_CERTIFICATENUMBER);
    }

    public void setCertificateNumber(String certificateNumber) {
        set(PROPERTY_CERTIFICATENUMBER, certificateNumber);
    }

    public Date getCertificateDate() {
        return (Date) get(PROPERTY_CERTIFICATEDATE);
    }

    public void setCertificateDate(Date certificateDate) {
        set(PROPERTY_CERTIFICATEDATE, certificateDate);
    }

    public BigDecimal getNilai() {
        return (BigDecimal) get(PROPERTY_NILAI);
    }

    public void setNilai(BigDecimal nilai) {
        set(PROPERTY_NILAI, nilai);
    }

    public String getContractType() {
        return (String) get(PROPERTY_CONTRACTTYPE);
    }

    public void setContractType(String contractType) {
        set(PROPERTY_CONTRACTTYPE, contractType);
    }

    public String getStringPosition() {
        return (String) get(PROPERTY_STRINGPOSITION);
    }

    public void setStringPosition(String stringPosition) {
        set(PROPERTY_STRINGPOSITION, stringPosition);
    }

    public String getPosition() {
        return (String) get(PROPERTY_POSITION);
    }

    public void setPosition(String position) {
        set(PROPERTY_POSITION, position);
    }

    public String getStringCostcenter() {
        return (String) get(PROPERTY_STRINGCOSTCENTER);
    }

    public void setStringCostcenter(String stringCostcenter) {
        set(PROPERTY_STRINGCOSTCENTER, stringCostcenter);
    }

    public CostCenter getCostCenter() {
        return (CostCenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(CostCenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    public String getStringJobTitle() {
        return (String) get(PROPERTY_STRINGJOBTITLE);
    }

    public void setStringJobTitle(String stringJobTitle) {
        set(PROPERTY_STRINGJOBTITLE, stringJobTitle);
    }

    public hris_jobtitle getJobTitle() {
        return (hris_jobtitle) get(PROPERTY_JOBTITLE);
    }

    public void setJobTitle(hris_jobtitle jobTitle) {
        set(PROPERTY_JOBTITLE, jobTitle);
    }

    public String getStringLevel() {
        return (String) get(PROPERTY_STRINGLEVEL);
    }

    public void setStringLevel(String stringLevel) {
        set(PROPERTY_STRINGLEVEL, stringLevel);
    }

    public String getGrade() {
        return (String) get(PROPERTY_GRADE);
    }

    public void setGrade(String grade) {
        set(PROPERTY_GRADE, grade);
    }

    public String getStringEchelon() {
        return (String) get(PROPERTY_STRINGECHELON);
    }

    public void setStringEchelon(String stringEchelon) {
        set(PROPERTY_STRINGECHELON, stringEchelon);
    }

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
    }

    public String getBankName() {
        return (String) get(PROPERTY_BANKNAME);
    }

    public void setBankName(String bankName) {
        set(PROPERTY_BANKNAME, bankName);
    }

    public String getGenericAccountNo() {
        return (String) get(PROPERTY_GENERICACCOUNTNO);
    }

    public void setGenericAccountNo(String genericAccountNo) {
        set(PROPERTY_GENERICACCOUNTNO, genericAccountNo);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public Boolean isCreateCandidate() {
        return (Boolean) get(PROPERTY_CREATECANDIDATE);
    }

    public void setCreateCandidate(Boolean createCandidate) {
        set(PROPERTY_CREATECANDIDATE, createCandidate);
    }

    public Boolean isImported() {
        return (Boolean) get(PROPERTY_IMPORTED);
    }

    public void setImported(Boolean imported) {
        set(PROPERTY_IMPORTED, imported);
    }

    public String getInstitution() {
        return (String) get(PROPERTY_INSTITUTION);
    }

    public void setInstitution(String institution) {
        set(PROPERTY_INSTITUTION, institution);
    }

    public String getHrisIdcardNo() {
        return (String) get(PROPERTY_HRISIDCARDNO);
    }

    public void setHrisIdcardNo(String hrisIdcardNo) {
        set(PROPERTY_HRISIDCARDNO, hrisIdcardNo);
    }

    public String getHrisTitleName() {
        return (String) get(PROPERTY_HRISTITLENAME);
    }

    public void setHrisTitleName(String hrisTitleName) {
        set(PROPERTY_HRISTITLENAME, hrisTitleName);
    }

}
