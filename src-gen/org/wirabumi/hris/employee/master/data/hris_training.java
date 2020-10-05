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
 * Entity class for entity hris_training (stored in table hris_training).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_training extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_training";
    public static final String ENTITY_NAME = "hris_training";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_COMMENT = "comment";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_TRAININGTYPE = "trainingtype";
    public static final String PROPERTY_TRAININGLOCATION = "traininglocation";
    public static final String PROPERTY_TRAININGCATEGORY = "trainingCategory";
    public static final String PROPERTY_HRISCBPTRAININGLIST = "hRISCBpTrainingList";
    public static final String PROPERTY_HRISTPEMPLOYEELIST = "hrisTpEmployeeList";
    public static final String PROPERTY_HRISTRAININGCALENDARLIST = "hrisTrainingCalendarList";
    public static final String PROPERTY_HRISTRAININGCOMPETENCYLIST = "hrisTrainingCompetencyList";
    public static final String PROPERTY_HRISTRAININGDEPENDENCYLIST = "hrisTrainingDependencyList";
    public static final String PROPERTY_HRISTRAININGDEPENDENCYHRISTRAININGDEPENDANTIDLIST = "hrisTrainingDependencyHrisTrainingDependantIDList";
    public static final String PROPERTY_HRISTRAININGPLANLIST = "hrisTrainingPlanList";

    public hris_training() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_HRISCBPTRAININGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTPEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTRAININGCALENDARLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTRAININGCOMPETENCYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTRAININGDEPENDENCYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTRAININGDEPENDENCYHRISTRAININGDEPENDANTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTRAININGPLANLIST, new ArrayList<Object>());
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

    public String getTrainingtype() {
        return (String) get(PROPERTY_TRAININGTYPE);
    }

    public void setTrainingtype(String trainingtype) {
        set(PROPERTY_TRAININGTYPE, trainingtype);
    }

    public String getTraininglocation() {
        return (String) get(PROPERTY_TRAININGLOCATION);
    }

    public void setTraininglocation(String traininglocation) {
        set(PROPERTY_TRAININGLOCATION, traininglocation);
    }

    public String getTrainingCategory() {
        return (String) get(PROPERTY_TRAININGCATEGORY);
    }

    public void setTrainingCategory(String trainingCategory) {
        set(PROPERTY_TRAININGCATEGORY, trainingCategory);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Training> getHRISCBpTrainingList() {
      return (List<HRIS_C_Bp_Training>) get(PROPERTY_HRISCBPTRAININGLIST);
    }

    public void setHRISCBpTrainingList(List<HRIS_C_Bp_Training> hRISCBpTrainingList) {
        set(PROPERTY_HRISCBPTRAININGLIST, hRISCBpTrainingList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_tp_employee> getHrisTpEmployeeList() {
      return (List<hris_tp_employee>) get(PROPERTY_HRISTPEMPLOYEELIST);
    }

    public void setHrisTpEmployeeList(List<hris_tp_employee> hrisTpEmployeeList) {
        set(PROPERTY_HRISTPEMPLOYEELIST, hrisTpEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_training_calendar> getHrisTrainingCalendarList() {
      return (List<hris_training_calendar>) get(PROPERTY_HRISTRAININGCALENDARLIST);
    }

    public void setHrisTrainingCalendarList(List<hris_training_calendar> hrisTrainingCalendarList) {
        set(PROPERTY_HRISTRAININGCALENDARLIST, hrisTrainingCalendarList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_training_competency> getHrisTrainingCompetencyList() {
      return (List<hris_training_competency>) get(PROPERTY_HRISTRAININGCOMPETENCYLIST);
    }

    public void setHrisTrainingCompetencyList(List<hris_training_competency> hrisTrainingCompetencyList) {
        set(PROPERTY_HRISTRAININGCOMPETENCYLIST, hrisTrainingCompetencyList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_training_dependency> getHrisTrainingDependencyList() {
      return (List<hris_training_dependency>) get(PROPERTY_HRISTRAININGDEPENDENCYLIST);
    }

    public void setHrisTrainingDependencyList(List<hris_training_dependency> hrisTrainingDependencyList) {
        set(PROPERTY_HRISTRAININGDEPENDENCYLIST, hrisTrainingDependencyList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_training_dependency> getHrisTrainingDependencyHrisTrainingDependantIDList() {
      return (List<hris_training_dependency>) get(PROPERTY_HRISTRAININGDEPENDENCYHRISTRAININGDEPENDANTIDLIST);
    }

    public void setHrisTrainingDependencyHrisTrainingDependantIDList(List<hris_training_dependency> hrisTrainingDependencyHrisTrainingDependantIDList) {
        set(PROPERTY_HRISTRAININGDEPENDENCYHRISTRAININGDEPENDANTIDLIST, hrisTrainingDependencyHrisTrainingDependantIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_training_plan> getHrisTrainingPlanList() {
      return (List<hris_training_plan>) get(PROPERTY_HRISTRAININGPLANLIST);
    }

    public void setHrisTrainingPlanList(List<hris_training_plan> hrisTrainingPlanList) {
        set(PROPERTY_HRISTRAININGPLANLIST, hrisTrainingPlanList);
    }

}
