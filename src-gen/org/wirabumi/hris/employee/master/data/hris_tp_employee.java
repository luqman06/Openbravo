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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity hris_tp_employee (stored in table hris_tp_employee).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_tp_employee extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_tp_employee";
    public static final String ENTITY_NAME = "hris_tp_employee";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_TRAINING = "training";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_HRISTRAININGPLAN = "hrisTrainingPlan";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingdate";
    public static final String PROPERTY_HRISTRAININGCALENDAR = "hrisTrainingCalendar";
    public static final String PROPERTY_HRISCERTIFICATE = "hrisCertificate";
    public static final String PROPERTY_CERTIFICATENUMBER = "certificateNumber";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentaction";
    public static final String PROPERTY_CREATECOMPETENCY = "createcompetency";
    public static final String PROPERTY_REMARK = "remark";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HRISTECOMPETENCYLIST = "hrisTeCompetencyList";

    public hris_tp_employee() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_CREATECOMPETENCY, false);
        setDefaultValue(PROPERTY_HRISTECOMPETENCYLIST, new ArrayList<Object>());
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

    public hris_training getTraining() {
        return (hris_training) get(PROPERTY_TRAINING);
    }

    public void setTraining(hris_training training) {
        set(PROPERTY_TRAINING, training);
    }

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public hris_training_plan getHrisTrainingPlan() {
        return (hris_training_plan) get(PROPERTY_HRISTRAININGPLAN);
    }

    public void setHrisTrainingPlan(hris_training_plan hrisTrainingPlan) {
        set(PROPERTY_HRISTRAININGPLAN, hrisTrainingPlan);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingdate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingdate(Date endingdate) {
        set(PROPERTY_ENDINGDATE, endingdate);
    }

    public hris_training_calendar getHrisTrainingCalendar() {
        return (hris_training_calendar) get(PROPERTY_HRISTRAININGCALENDAR);
    }

    public void setHrisTrainingCalendar(hris_training_calendar hrisTrainingCalendar) {
        set(PROPERTY_HRISTRAININGCALENDAR, hrisTrainingCalendar);
    }

    public hris_certificate getHrisCertificate() {
        return (hris_certificate) get(PROPERTY_HRISCERTIFICATE);
    }

    public void setHrisCertificate(hris_certificate hrisCertificate) {
        set(PROPERTY_HRISCERTIFICATE, hrisCertificate);
    }

    public String getCertificateNumber() {
        return (String) get(PROPERTY_CERTIFICATENUMBER);
    }

    public void setCertificateNumber(String certificateNumber) {
        set(PROPERTY_CERTIFICATENUMBER, certificateNumber);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getDocumentaction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentaction(String documentaction) {
        set(PROPERTY_DOCUMENTACTION, documentaction);
    }

    public Boolean isCreatecompetency() {
        return (Boolean) get(PROPERTY_CREATECOMPETENCY);
    }

    public void setCreatecompetency(Boolean createcompetency) {
        set(PROPERTY_CREATECOMPETENCY, createcompetency);
    }

    public String getRemark() {
        return (String) get(PROPERTY_REMARK);
    }

    public void setRemark(String remark) {
        set(PROPERTY_REMARK, remark);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    @SuppressWarnings("unchecked")
    public List<hris_te_competency> getHrisTeCompetencyList() {
      return (List<hris_te_competency>) get(PROPERTY_HRISTECOMPETENCYLIST);
    }

    public void setHrisTeCompetencyList(List<hris_te_competency> hrisTeCompetencyList) {
        set(PROPERTY_HRISTECOMPETENCYLIST, hrisTeCompetencyList);
    }

}
