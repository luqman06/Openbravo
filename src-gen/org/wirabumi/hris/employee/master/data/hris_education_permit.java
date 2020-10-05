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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.manufacturing.cost.CostCenter;
/**
 * Entity class for entity hris_education_permit (stored in table hris_education_permit).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_education_permit extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_education_permit";
    public static final String ENTITY_NAME = "hris_education_permit";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_EMPLOYEEID = "employeeID";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_JOBTITLE = "jobTitle";
    public static final String PROPERTY_GRADE = "grade";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_EMPLOYMENTTYPE = "employmentType";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_LETTERNO = "letterno";
    public static final String PROPERTY_INSTITUTIONLETTER = "institutionletter";
    public static final String PROPERTY_DICIPLINELETTER = "diciplineletter";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_TIMEMODE = "timemode";
    public static final String PROPERTY_PAYMENTMODE = "paymentmode";
    public static final String PROPERTY_CERTIFICATENO = "certificateno";
    public static final String PROPERTY_INSTITUTIONNAME = "institutionname";
    public static final String PROPERTY_EDUCATIONDICIPLINE = "educationDicipline";
    public static final String PROPERTY_CERTIFICATEDATE = "certificateDate";
    public static final String PROPERTY_REMARK = "remark";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_HRISLASTEDUCATIONGRADE = "hrisLasteducationGrade";
    public static final String PROPERTY_HRISLASTINSTITUTIONNAME = "hrisLastinstitutionName";
    public static final String PROPERTY_HRISACCREDITATION = "hrisAccreditation";
    public static final String PROPERTY_HRISEDUCATIONEXAMLIST = "hrisEducationExamList";

    public hris_education_permit() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_HRISEDUCATIONEXAMLIST, new ArrayList<Object>());
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

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
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

    public String getEmploymentType() {
        return (String) get(PROPERTY_EMPLOYMENTTYPE);
    }

    public void setEmploymentType(String employmentType) {
        set(PROPERTY_EMPLOYMENTTYPE, employmentType);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getLetterno() {
        return (String) get(PROPERTY_LETTERNO);
    }

    public void setLetterno(String letterno) {
        set(PROPERTY_LETTERNO, letterno);
    }

    public String getInstitutionletter() {
        return (String) get(PROPERTY_INSTITUTIONLETTER);
    }

    public void setInstitutionletter(String institutionletter) {
        set(PROPERTY_INSTITUTIONLETTER, institutionletter);
    }

    public hris_dicipline getDiciplineletter() {
        return (hris_dicipline) get(PROPERTY_DICIPLINELETTER);
    }

    public void setDiciplineletter(hris_dicipline diciplineletter) {
        set(PROPERTY_DICIPLINELETTER, diciplineletter);
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

    public BigDecimal getRemark() {
        return (BigDecimal) get(PROPERTY_REMARK);
    }

    public void setRemark(BigDecimal remark) {
        set(PROPERTY_REMARK, remark);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public hris_dicipline getHrisLasteducationGrade() {
        return (hris_dicipline) get(PROPERTY_HRISLASTEDUCATIONGRADE);
    }

    public void setHrisLasteducationGrade(hris_dicipline hrisLasteducationGrade) {
        set(PROPERTY_HRISLASTEDUCATIONGRADE, hrisLasteducationGrade);
    }

    public String getHrisLastinstitutionName() {
        return (String) get(PROPERTY_HRISLASTINSTITUTIONNAME);
    }

    public void setHrisLastinstitutionName(String hrisLastinstitutionName) {
        set(PROPERTY_HRISLASTINSTITUTIONNAME, hrisLastinstitutionName);
    }

    public String getHrisAccreditation() {
        return (String) get(PROPERTY_HRISACCREDITATION);
    }

    public void setHrisAccreditation(String hrisAccreditation) {
        set(PROPERTY_HRISACCREDITATION, hrisAccreditation);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_exam> getHrisEducationExamList() {
      return (List<hris_education_exam>) get(PROPERTY_HRISEDUCATIONEXAMLIST);
    }

    public void setHrisEducationExamList(List<hris_education_exam> hrisEducationExamList) {
        set(PROPERTY_HRISEDUCATIONEXAMLIST, hrisEducationExamList);
    }

}
