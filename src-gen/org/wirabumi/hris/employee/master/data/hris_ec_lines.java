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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.manufacturing.cost.CostCenter;
/**
 * Entity class for entity hris_ec_lines (stored in table hris_ec_lines).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_ec_lines extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_ec_lines";
    public static final String ENTITY_NAME = "hris_ec_lines";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_HRISEMPLOYEECANDIDATE = "hrisEmployeeCandidate";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_COMMERCIALNAME = "commercialName";
    public static final String PROPERTY_BIRTHPLACE = "birthplace";
    public static final String PROPERTY_TANGGALLAHIR = "tanggalLahir";
    public static final String PROPERTY_SEX = "sex";
    public static final String PROPERTY_RELIGION = "religion";
    public static final String PROPERTY_NATIONALITY = "nationality";
    public static final String PROPERTY_MARITALSTATUS = "maritalstatus";
    public static final String PROPERTY_TAXTID = "taxtid";
    public static final String PROPERTY_BLOODTYPE = "bloodtype";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_JOINDATE = "joindate";
    public static final String PROPERTY_YEAROFSERVICE = "yearofservice";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ADDRESSLINE1 = "addressLine1";
    public static final String PROPERTY_ADDRESSLINE2 = "addressLine2";
    public static final String PROPERTY_POSTALCODE = "postalCode";
    public static final String PROPERTY_CITYNAME = "cityName";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_PHONE = "phone";
    public static final String PROPERTY_ALTERNATIVEPHONE = "alternativePhone";
    public static final String PROPERTY_EDUCATIONLEVEL = "educationLevel";
    public static final String PROPERTY_HRISDICIPLINE = "hrisDicipline";
    public static final String PROPERTY_INSTITUTIONNAME = "institutionName";
    public static final String PROPERTY_IJAZAH = "ijazah";
    public static final String PROPERTY_DATEIJAZAH = "dateIjazah";
    public static final String PROPERTY_NILAI = "nilai";
    public static final String PROPERTY_CONTRACTNO = "contractno";
    public static final String PROPERTY_DOCUMENTDATE = "documentDate";
    public static final String PROPERTY_TMT = "tMT";
    public static final String PROPERTY_TENURE = "tenure";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_COSTCENTER = "costcenter";
    public static final String PROPERTY_JOBTITLE = "jobTitle";
    public static final String PROPERTY_GRADE = "grade";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_EMPLOYEMENTTYPE = "employementtype";
    public static final String PROPERTY_DATEDELAY = "dateDelay";
    public static final String PROPERTY_BANKNAME = "bankName";
    public static final String PROPERTY_GENERICACCOUNTNO = "genericAccountNo";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_TERBITKANNOSK = "terbitkanNoSK";
    public static final String PROPERTY_MONTHOFSERVICE = "monthofservice";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_PPHTAXMARITALSTATUS = "pphTaxmaritalstatus";
    public static final String PROPERTY_CONTRACTYPE = "contractype";
    public static final String PROPERTY_HRISIDCARDNO = "hrisIdcardNo";
    public static final String PROPERTY_HRISTITLENAME = "hrisTitleName";

    public hris_ec_lines() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BIRTHPLACE, "Unknown");
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_TERBITKANNOSK, false);
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

    public hris_employee_candidate getHrisEmployeeCandidate() {
        return (hris_employee_candidate) get(PROPERTY_HRISEMPLOYEECANDIDATE);
    }

    public void setHrisEmployeeCandidate(hris_employee_candidate hrisEmployeeCandidate) {
        set(PROPERTY_HRISEMPLOYEECANDIDATE, hrisEmployeeCandidate);
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

    public String getCommercialName() {
        return (String) get(PROPERTY_COMMERCIALNAME);
    }

    public void setCommercialName(String commercialName) {
        set(PROPERTY_COMMERCIALNAME, commercialName);
    }

    public String getBirthplace() {
        return (String) get(PROPERTY_BIRTHPLACE);
    }

    public void setBirthplace(String birthplace) {
        set(PROPERTY_BIRTHPLACE, birthplace);
    }

    public Date getTanggalLahir() {
        return (Date) get(PROPERTY_TANGGALLAHIR);
    }

    public void setTanggalLahir(Date tanggalLahir) {
        set(PROPERTY_TANGGALLAHIR, tanggalLahir);
    }

    public String getSex() {
        return (String) get(PROPERTY_SEX);
    }

    public void setSex(String sex) {
        set(PROPERTY_SEX, sex);
    }

    public String getReligion() {
        return (String) get(PROPERTY_RELIGION);
    }

    public void setReligion(String religion) {
        set(PROPERTY_RELIGION, religion);
    }

    public Country getNationality() {
        return (Country) get(PROPERTY_NATIONALITY);
    }

    public void setNationality(Country nationality) {
        set(PROPERTY_NATIONALITY, nationality);
    }

    public String getMaritalstatus() {
        return (String) get(PROPERTY_MARITALSTATUS);
    }

    public void setMaritalstatus(String maritalstatus) {
        set(PROPERTY_MARITALSTATUS, maritalstatus);
    }

    public String getTaxtid() {
        return (String) get(PROPERTY_TAXTID);
    }

    public void setTaxtid(String taxtid) {
        set(PROPERTY_TAXTID, taxtid);
    }

    public String getBloodtype() {
        return (String) get(PROPERTY_BLOODTYPE);
    }

    public void setBloodtype(String bloodtype) {
        set(PROPERTY_BLOODTYPE, bloodtype);
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

    public Long getYearofservice() {
        return (Long) get(PROPERTY_YEAROFSERVICE);
    }

    public void setYearofservice(Long yearofservice) {
        set(PROPERTY_YEAROFSERVICE, yearofservice);
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

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
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

    public String getEducationLevel() {
        return (String) get(PROPERTY_EDUCATIONLEVEL);
    }

    public void setEducationLevel(String educationLevel) {
        set(PROPERTY_EDUCATIONLEVEL, educationLevel);
    }

    public hris_dicipline getHrisDicipline() {
        return (hris_dicipline) get(PROPERTY_HRISDICIPLINE);
    }

    public void setHrisDicipline(hris_dicipline hrisDicipline) {
        set(PROPERTY_HRISDICIPLINE, hrisDicipline);
    }

    public String getInstitutionName() {
        return (String) get(PROPERTY_INSTITUTIONNAME);
    }

    public void setInstitutionName(String institutionName) {
        set(PROPERTY_INSTITUTIONNAME, institutionName);
    }

    public String getIjazah() {
        return (String) get(PROPERTY_IJAZAH);
    }

    public void setIjazah(String ijazah) {
        set(PROPERTY_IJAZAH, ijazah);
    }

    public Date getDateIjazah() {
        return (Date) get(PROPERTY_DATEIJAZAH);
    }

    public void setDateIjazah(Date dateIjazah) {
        set(PROPERTY_DATEIJAZAH, dateIjazah);
    }

    public BigDecimal getNilai() {
        return (BigDecimal) get(PROPERTY_NILAI);
    }

    public void setNilai(BigDecimal nilai) {
        set(PROPERTY_NILAI, nilai);
    }

    public String getContractno() {
        return (String) get(PROPERTY_CONTRACTNO);
    }

    public void setContractno(String contractno) {
        set(PROPERTY_CONTRACTNO, contractno);
    }

    public Date getDocumentDate() {
        return (Date) get(PROPERTY_DOCUMENTDATE);
    }

    public void setDocumentDate(Date documentDate) {
        set(PROPERTY_DOCUMENTDATE, documentDate);
    }

    public Date getTMT() {
        return (Date) get(PROPERTY_TMT);
    }

    public void setTMT(Date tMT) {
        set(PROPERTY_TMT, tMT);
    }

    public Long getTenure() {
        return (Long) get(PROPERTY_TENURE);
    }

    public void setTenure(Long tenure) {
        set(PROPERTY_TENURE, tenure);
    }

    public EmployeePosition getPosition() {
        return (EmployeePosition) get(PROPERTY_POSITION);
    }

    public void setPosition(EmployeePosition position) {
        set(PROPERTY_POSITION, position);
    }

    public CostCenter getCostcenter() {
        return (CostCenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostcenter(CostCenter costcenter) {
        set(PROPERTY_COSTCENTER, costcenter);
    }

    public hris_jobtitle getJobTitle() {
        return (hris_jobtitle) get(PROPERTY_JOBTITLE);
    }

    public void setJobTitle(hris_jobtitle jobTitle) {
        set(PROPERTY_JOBTITLE, jobTitle);
    }

    public String getGrade() {
        return (String) get(PROPERTY_GRADE);
    }

    public void setGrade(String grade) {
        set(PROPERTY_GRADE, grade);
    }

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getEmployementtype() {
        return (String) get(PROPERTY_EMPLOYEMENTTYPE);
    }

    public void setEmployementtype(String employementtype) {
        set(PROPERTY_EMPLOYEMENTTYPE, employementtype);
    }

    public Date getDateDelay() {
        return (Date) get(PROPERTY_DATEDELAY);
    }

    public void setDateDelay(Date dateDelay) {
        set(PROPERTY_DATEDELAY, dateDelay);
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

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public Boolean isTerbitkanNoSK() {
        return (Boolean) get(PROPERTY_TERBITKANNOSK);
    }

    public void setTerbitkanNoSK(Boolean terbitkanNoSK) {
        set(PROPERTY_TERBITKANNOSK, terbitkanNoSK);
    }

    public Long getMonthofservice() {
        return (Long) get(PROPERTY_MONTHOFSERVICE);
    }

    public void setMonthofservice(Long monthofservice) {
        set(PROPERTY_MONTHOFSERVICE, monthofservice);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Region getRegion() {
        return (Region) get(PROPERTY_REGION);
    }

    public void setRegion(Region region) {
        set(PROPERTY_REGION, region);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public String getPphTaxmaritalstatus() {
        return (String) get(PROPERTY_PPHTAXMARITALSTATUS);
    }

    public void setPphTaxmaritalstatus(String pphTaxmaritalstatus) {
        set(PROPERTY_PPHTAXMARITALSTATUS, pphTaxmaritalstatus);
    }

    public String getContractype() {
        return (String) get(PROPERTY_CONTRACTYPE);
    }

    public void setContractype(String contractype) {
        set(PROPERTY_CONTRACTYPE, contractype);
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
