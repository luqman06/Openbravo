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
import org.openbravo.model.manufacturing.cost.CostCenter;
/**
 * Entity class for entity hris_education_admission (stored in table hris_education_admission).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_education_admission extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_education_admission";
    public static final String ENTITY_NAME = "hris_education_admission";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEEID = "employeeID";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_JOBTITLE = "jobTitle";
    public static final String PROPERTY_GRADE = "grade";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_EMPLOYMENTTYPE = "employmenttype";
    public static final String PROPERTY_CERTIFICATENO = "certificateno";
    public static final String PROPERTY_INSTITUTIONNAME = "institutionname";
    public static final String PROPERTY_EDUCATIONLEVEL = "educationlevel";
    public static final String PROPERTY_EDUCATIONDICIPLINE = "educationDicipline";
    public static final String PROPERTY_CERTIFICATEDATE = "certificateDate";
    public static final String PROPERTY_REMARK = "remark";
    public static final String PROPERTY_LETTERNO = "letterno";
    public static final String PROPERTY_INSTITUSIONNAMELETTER = "institusionnameletter";
    public static final String PROPERTY_EDUCATIONLEVELLETTER = "educationlevelletter";
    public static final String PROPERTY_EDUCATIONDISCIPLINE = "educationDiscipline";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_TIMEMODE = "timemode";
    public static final String PROPERTY_PAYMENTMODE = "paymentmode";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_APPROVE = "approve";
    public static final String PROPERTY_FACULTY = "faculty";
    public static final String PROPERTY_CREATEEDUCATION = "createeducation";

    public hris_education_admission() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_APPROVE, false);
        setDefaultValue(PROPERTY_CREATEEDUCATION, false);
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

    public String getEmployeeID() {
        return (String) get(PROPERTY_EMPLOYEEID);
    }

    public void setEmployeeID(String employeeID) {
        set(PROPERTY_EMPLOYEEID, employeeID);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getPosition() {
        return (String) get(PROPERTY_POSITION);
    }

    public void setPosition(String position) {
        set(PROPERTY_POSITION, position);
    }

    public CostCenter getCostCenter() {
        return (CostCenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(CostCenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
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

    public String getEmploymenttype() {
        return (String) get(PROPERTY_EMPLOYMENTTYPE);
    }

    public void setEmploymenttype(String employmenttype) {
        set(PROPERTY_EMPLOYMENTTYPE, employmenttype);
    }

    public String getCertificateno() {
        return (String) get(PROPERTY_CERTIFICATENO);
    }

    public void setCertificateno(String certificateno) {
        set(PROPERTY_CERTIFICATENO, certificateno);
    }

    public String getInstitutionname() {
        return (String) get(PROPERTY_INSTITUTIONNAME);
    }

    public void setInstitutionname(String institutionname) {
        set(PROPERTY_INSTITUTIONNAME, institutionname);
    }

    public String getEducationlevel() {
        return (String) get(PROPERTY_EDUCATIONLEVEL);
    }

    public void setEducationlevel(String educationlevel) {
        set(PROPERTY_EDUCATIONLEVEL, educationlevel);
    }

    public hris_dicipline getEducationDicipline() {
        return (hris_dicipline) get(PROPERTY_EDUCATIONDICIPLINE);
    }

    public void setEducationDicipline(hris_dicipline educationDicipline) {
        set(PROPERTY_EDUCATIONDICIPLINE, educationDicipline);
    }

    public Date getCertificateDate() {
        return (Date) get(PROPERTY_CERTIFICATEDATE);
    }

    public void setCertificateDate(Date certificateDate) {
        set(PROPERTY_CERTIFICATEDATE, certificateDate);
    }

    public Long getRemark() {
        return (Long) get(PROPERTY_REMARK);
    }

    public void setRemark(Long remark) {
        set(PROPERTY_REMARK, remark);
    }

    public String getLetterno() {
        return (String) get(PROPERTY_LETTERNO);
    }

    public void setLetterno(String letterno) {
        set(PROPERTY_LETTERNO, letterno);
    }

    public String getInstitusionnameletter() {
        return (String) get(PROPERTY_INSTITUSIONNAMELETTER);
    }

    public void setInstitusionnameletter(String institusionnameletter) {
        set(PROPERTY_INSTITUSIONNAMELETTER, institusionnameletter);
    }

    public String getEducationlevelletter() {
        return (String) get(PROPERTY_EDUCATIONLEVELLETTER);
    }

    public void setEducationlevelletter(String educationlevelletter) {
        set(PROPERTY_EDUCATIONLEVELLETTER, educationlevelletter);
    }

    public hris_dicipline getEducationDiscipline() {
        return (hris_dicipline) get(PROPERTY_EDUCATIONDISCIPLINE);
    }

    public void setEducationDiscipline(hris_dicipline educationDiscipline) {
        set(PROPERTY_EDUCATIONDISCIPLINE, educationDiscipline);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public String getTimemode() {
        return (String) get(PROPERTY_TIMEMODE);
    }

    public void setTimemode(String timemode) {
        set(PROPERTY_TIMEMODE, timemode);
    }

    public String getPaymentmode() {
        return (String) get(PROPERTY_PAYMENTMODE);
    }

    public void setPaymentmode(String paymentmode) {
        set(PROPERTY_PAYMENTMODE, paymentmode);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Boolean isApprove() {
        return (Boolean) get(PROPERTY_APPROVE);
    }

    public void setApprove(Boolean approve) {
        set(PROPERTY_APPROVE, approve);
    }

    public String getFaculty() {
        return (String) get(PROPERTY_FACULTY);
    }

    public void setFaculty(String faculty) {
        set(PROPERTY_FACULTY, faculty);
    }

    public Boolean isCreateeducation() {
        return (Boolean) get(PROPERTY_CREATEEDUCATION);
    }

    public void setCreateeducation(Boolean createeducation) {
        set(PROPERTY_CREATEEDUCATION, createeducation);
    }

}
