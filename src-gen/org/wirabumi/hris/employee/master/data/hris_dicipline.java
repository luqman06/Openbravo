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
/**
 * Entity class for entity hris_dicipline (stored in table hris_dicipline).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_dicipline extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_dicipline";
    public static final String ENTITY_NAME = "hris_dicipline";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_FACULTY = "faculty";
    public static final String PROPERTY_EDUCATIONLEVEL = "educationLevel";
    public static final String PROPERTY_ACCREDITATION = "accreditation";
    public static final String PROPERTY_INSTITUTIONNAME = "institutionname";
    public static final String PROPERTY_HRISCBPEDUCATIONDICIPLINELIST = "hRISCBpEducationDiciplineList";
    public static final String PROPERTY_HRISECLINESLIST = "hrisEcLinesList";
    public static final String PROPERTY_HRISEDUCATIONADMISSIONLIST = "hrisEducationAdmissionList";
    public static final String PROPERTY_HRISEDUCATIONADMISSIONEDUCATIONDISCIPLINELIST = "hrisEducationAdmissionEducationDisciplineList";
    public static final String PROPERTY_HRISEDUCATIONEXAMLIST = "hrisEducationExamList";
    public static final String PROPERTY_HRISEDUCATIONPERMITDICIPLINELETTERLIST = "hrisEducationPermitDiciplineletterList";
    public static final String PROPERTY_HRISEDUCATIONPERMITLIST = "hrisEducationPermitList";
    public static final String PROPERTY_HRISEDUCATIONPERMITHRISLASTEDUCATIONGRADELIST = "hrisEducationPermitHrisLasteducationGradeList";
    public static final String PROPERTY_HRISGEEMPLOYEELIST = "hrisGeEmployeeList";
    public static final String PROPERTY_HRISIEMPLOYEECANDIDATELIST = "hrisIEmployeeCandidateList";
    public static final String PROPERTY_HRISJTEDUCATIONMAJORLIST = "hrisJtEducationMajorList";

    public hris_dicipline() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_HRISCBPEDUCATIONDICIPLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISECLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONADMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONADMISSIONEDUCATIONDISCIPLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONEXAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONPERMITDICIPLINELETTERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONPERMITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONPERMITHRISLASTEDUCATIONGRADELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISGEEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISIEMPLOYEECANDIDATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJTEDUCATIONMAJORLIST, new ArrayList<Object>());
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

    public String getFaculty() {
        return (String) get(PROPERTY_FACULTY);
    }

    public void setFaculty(String faculty) {
        set(PROPERTY_FACULTY, faculty);
    }

    public String getEducationLevel() {
        return (String) get(PROPERTY_EDUCATIONLEVEL);
    }

    public void setEducationLevel(String educationLevel) {
        set(PROPERTY_EDUCATIONLEVEL, educationLevel);
    }

    public String getAccreditation() {
        return (String) get(PROPERTY_ACCREDITATION);
    }

    public void setAccreditation(String accreditation) {
        set(PROPERTY_ACCREDITATION, accreditation);
    }

    public String getInstitutionname() {
        return (String) get(PROPERTY_INSTITUTIONNAME);
    }

    public void setInstitutionname(String institutionname) {
        set(PROPERTY_INSTITUTIONNAME, institutionname);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_education> getHRISCBpEducationDiciplineList() {
      return (List<HRIS_C_Bp_education>) get(PROPERTY_HRISCBPEDUCATIONDICIPLINELIST);
    }

    public void setHRISCBpEducationDiciplineList(List<HRIS_C_Bp_education> hRISCBpEducationDiciplineList) {
        set(PROPERTY_HRISCBPEDUCATIONDICIPLINELIST, hRISCBpEducationDiciplineList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ec_lines> getHrisEcLinesList() {
      return (List<hris_ec_lines>) get(PROPERTY_HRISECLINESLIST);
    }

    public void setHrisEcLinesList(List<hris_ec_lines> hrisEcLinesList) {
        set(PROPERTY_HRISECLINESLIST, hrisEcLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_admission> getHrisEducationAdmissionList() {
      return (List<hris_education_admission>) get(PROPERTY_HRISEDUCATIONADMISSIONLIST);
    }

    public void setHrisEducationAdmissionList(List<hris_education_admission> hrisEducationAdmissionList) {
        set(PROPERTY_HRISEDUCATIONADMISSIONLIST, hrisEducationAdmissionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_admission> getHrisEducationAdmissionEducationDisciplineList() {
      return (List<hris_education_admission>) get(PROPERTY_HRISEDUCATIONADMISSIONEDUCATIONDISCIPLINELIST);
    }

    public void setHrisEducationAdmissionEducationDisciplineList(List<hris_education_admission> hrisEducationAdmissionEducationDisciplineList) {
        set(PROPERTY_HRISEDUCATIONADMISSIONEDUCATIONDISCIPLINELIST, hrisEducationAdmissionEducationDisciplineList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_exam> getHrisEducationExamList() {
      return (List<hris_education_exam>) get(PROPERTY_HRISEDUCATIONEXAMLIST);
    }

    public void setHrisEducationExamList(List<hris_education_exam> hrisEducationExamList) {
        set(PROPERTY_HRISEDUCATIONEXAMLIST, hrisEducationExamList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_permit> getHrisEducationPermitDiciplineletterList() {
      return (List<hris_education_permit>) get(PROPERTY_HRISEDUCATIONPERMITDICIPLINELETTERLIST);
    }

    public void setHrisEducationPermitDiciplineletterList(List<hris_education_permit> hrisEducationPermitDiciplineletterList) {
        set(PROPERTY_HRISEDUCATIONPERMITDICIPLINELETTERLIST, hrisEducationPermitDiciplineletterList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_permit> getHrisEducationPermitList() {
      return (List<hris_education_permit>) get(PROPERTY_HRISEDUCATIONPERMITLIST);
    }

    public void setHrisEducationPermitList(List<hris_education_permit> hrisEducationPermitList) {
        set(PROPERTY_HRISEDUCATIONPERMITLIST, hrisEducationPermitList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_permit> getHrisEducationPermitHrisLasteducationGradeList() {
      return (List<hris_education_permit>) get(PROPERTY_HRISEDUCATIONPERMITHRISLASTEDUCATIONGRADELIST);
    }

    public void setHrisEducationPermitHrisLasteducationGradeList(List<hris_education_permit> hrisEducationPermitHrisLasteducationGradeList) {
        set(PROPERTY_HRISEDUCATIONPERMITHRISLASTEDUCATIONGRADELIST, hrisEducationPermitHrisLasteducationGradeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ge_employee> getHrisGeEmployeeList() {
      return (List<hris_ge_employee>) get(PROPERTY_HRISGEEMPLOYEELIST);
    }

    public void setHrisGeEmployeeList(List<hris_ge_employee> hrisGeEmployeeList) {
        set(PROPERTY_HRISGEEMPLOYEELIST, hrisGeEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_i_employee_candidate> getHrisIEmployeeCandidateList() {
      return (List<hris_i_employee_candidate>) get(PROPERTY_HRISIEMPLOYEECANDIDATELIST);
    }

    public void setHrisIEmployeeCandidateList(List<hris_i_employee_candidate> hrisIEmployeeCandidateList) {
        set(PROPERTY_HRISIEMPLOYEECANDIDATELIST, hrisIEmployeeCandidateList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_jt_education> getHrisJtEducationMajorList() {
      return (List<hris_jt_education>) get(PROPERTY_HRISJTEDUCATIONMAJORLIST);
    }

    public void setHrisJtEducationMajorList(List<hris_jt_education> hrisJtEducationMajorList) {
        set(PROPERTY_HRISJTEDUCATIONMAJORLIST, hrisJtEducationMajorList);
    }

}
