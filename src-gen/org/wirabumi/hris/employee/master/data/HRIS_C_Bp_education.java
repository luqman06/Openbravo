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
/**
 * Entity class for entity HRIS_C_Bp_education (stored in table HRIS_C_Bp_education).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class HRIS_C_Bp_education extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "HRIS_C_Bp_education";
    public static final String ENTITY_NAME = "HRIS_C_Bp_education";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_LEVEL = "level";
    public static final String PROPERTY_INSTITUTIONNAME = "institutionName";
    public static final String PROPERTY_COMMENT = "comment";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_GRADE = "grade";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_VALIDTODATE = "validToDate";
    public static final String PROPERTY_DICIPLINE = "dicipline";
    public static final String PROPERTY_GRADENOTE = "gradeNote";
    public static final String PROPERTY_GRADUATE = "graduate";
    public static final String PROPERTY_CERTIFICATENUMBER = "certificateNumber";
    public static final String PROPERTY_HRISACCREDITATION = "hrisAccreditation";

    public HRIS_C_Bp_education() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_GRADUATE, true);
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getLevel() {
        return (String) get(PROPERTY_LEVEL);
    }

    public void setLevel(String level) {
        set(PROPERTY_LEVEL, level);
    }

    public String getInstitutionName() {
        return (String) get(PROPERTY_INSTITUTIONNAME);
    }

    public void setInstitutionName(String institutionName) {
        set(PROPERTY_INSTITUTIONNAME, institutionName);
    }

    public String getComment() {
        return (String) get(PROPERTY_COMMENT);
    }

    public void setComment(String comment) {
        set(PROPERTY_COMMENT, comment);
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

    public BigDecimal getGrade() {
        return (BigDecimal) get(PROPERTY_GRADE);
    }

    public void setGrade(BigDecimal grade) {
        set(PROPERTY_GRADE, grade);
    }

    public Date getValidFromDate() {
        return (Date) get(PROPERTY_VALIDFROMDATE);
    }

    public void setValidFromDate(Date validFromDate) {
        set(PROPERTY_VALIDFROMDATE, validFromDate);
    }

    public Date getValidToDate() {
        return (Date) get(PROPERTY_VALIDTODATE);
    }

    public void setValidToDate(Date validToDate) {
        set(PROPERTY_VALIDTODATE, validToDate);
    }

    public hris_dicipline getDicipline() {
        return (hris_dicipline) get(PROPERTY_DICIPLINE);
    }

    public void setDicipline(hris_dicipline dicipline) {
        set(PROPERTY_DICIPLINE, dicipline);
    }

    public String getGradeNote() {
        return (String) get(PROPERTY_GRADENOTE);
    }

    public void setGradeNote(String gradeNote) {
        set(PROPERTY_GRADENOTE, gradeNote);
    }

    public Boolean isGraduate() {
        return (Boolean) get(PROPERTY_GRADUATE);
    }

    public void setGraduate(Boolean graduate) {
        set(PROPERTY_GRADUATE, graduate);
    }

    public String getCertificateNumber() {
        return (String) get(PROPERTY_CERTIFICATENUMBER);
    }

    public void setCertificateNumber(String certificateNumber) {
        set(PROPERTY_CERTIFICATENUMBER, certificateNumber);
    }

    public String getHrisAccreditation() {
        return (String) get(PROPERTY_HRISACCREDITATION);
    }

    public void setHrisAccreditation(String hrisAccreditation) {
        set(PROPERTY_HRISACCREDITATION, hrisAccreditation);
    }

}