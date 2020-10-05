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
 * Entity class for entity hris_competency (stored in table hris_competency).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_competency extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_competency";
    public static final String ENTITY_NAME = "hris_competency";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_COMMENT = "comment";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_COMPETENCYTYPE = "competencytype";
    public static final String PROPERTY_HRISCBPCOMPETENCYLIST = "hrisCBpCompetencyList";
    public static final String PROPERTY_HRISCOMPETENCYDEPENDENCYLIST = "hrisCompetencyDependencyList";
    public static final String PROPERTY_HRISCOMPETENCYDEPENDENCYHRISCOMPETENCYDEPENDANTIDLIST = "hrisCompetencyDependencyHrisCompetencyDependantIDList";
    public static final String PROPERTY_HRISCOMPETENCYJOBTITLELIST = "hrisCompetencyJobtitleList";
    public static final String PROPERTY_HRISTECOMPETENCYLIST = "hrisTeCompetencyList";
    public static final String PROPERTY_HRISTRAININGCOMPETENCYLIST = "hrisTrainingCompetencyList";

    public hris_competency() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_COMPETENCYTYPE, "hard skill");
        setDefaultValue(PROPERTY_HRISCBPCOMPETENCYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCOMPETENCYDEPENDENCYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCOMPETENCYDEPENDENCYHRISCOMPETENCYDEPENDANTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCOMPETENCYJOBTITLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTECOMPETENCYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTRAININGCOMPETENCYLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getComment() {
        return (String) get(PROPERTY_COMMENT);
    }

    public void setComment(String comment) {
        set(PROPERTY_COMMENT, comment);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
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

    public String getCompetencytype() {
        return (String) get(PROPERTY_COMPETENCYTYPE);
    }

    public void setCompetencytype(String competencytype) {
        set(PROPERTY_COMPETENCYTYPE, competencytype);
    }

    @SuppressWarnings("unchecked")
    public List<hris_c_bp_competency> getHrisCBpCompetencyList() {
      return (List<hris_c_bp_competency>) get(PROPERTY_HRISCBPCOMPETENCYLIST);
    }

    public void setHrisCBpCompetencyList(List<hris_c_bp_competency> hrisCBpCompetencyList) {
        set(PROPERTY_HRISCBPCOMPETENCYLIST, hrisCBpCompetencyList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_competency_dependency> getHrisCompetencyDependencyList() {
      return (List<hris_competency_dependency>) get(PROPERTY_HRISCOMPETENCYDEPENDENCYLIST);
    }

    public void setHrisCompetencyDependencyList(List<hris_competency_dependency> hrisCompetencyDependencyList) {
        set(PROPERTY_HRISCOMPETENCYDEPENDENCYLIST, hrisCompetencyDependencyList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_competency_dependency> getHrisCompetencyDependencyHrisCompetencyDependantIDList() {
      return (List<hris_competency_dependency>) get(PROPERTY_HRISCOMPETENCYDEPENDENCYHRISCOMPETENCYDEPENDANTIDLIST);
    }

    public void setHrisCompetencyDependencyHrisCompetencyDependantIDList(List<hris_competency_dependency> hrisCompetencyDependencyHrisCompetencyDependantIDList) {
        set(PROPERTY_HRISCOMPETENCYDEPENDENCYHRISCOMPETENCYDEPENDANTIDLIST, hrisCompetencyDependencyHrisCompetencyDependantIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_competency_jobtitle> getHrisCompetencyJobtitleList() {
      return (List<hris_competency_jobtitle>) get(PROPERTY_HRISCOMPETENCYJOBTITLELIST);
    }

    public void setHrisCompetencyJobtitleList(List<hris_competency_jobtitle> hrisCompetencyJobtitleList) {
        set(PROPERTY_HRISCOMPETENCYJOBTITLELIST, hrisCompetencyJobtitleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_te_competency> getHrisTeCompetencyList() {
      return (List<hris_te_competency>) get(PROPERTY_HRISTECOMPETENCYLIST);
    }

    public void setHrisTeCompetencyList(List<hris_te_competency> hrisTeCompetencyList) {
        set(PROPERTY_HRISTECOMPETENCYLIST, hrisTeCompetencyList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_training_competency> getHrisTrainingCompetencyList() {
      return (List<hris_training_competency>) get(PROPERTY_HRISTRAININGCOMPETENCYLIST);
    }

    public void setHrisTrainingCompetencyList(List<hris_training_competency> hrisTrainingCompetencyList) {
        set(PROPERTY_HRISTRAININGCOMPETENCYLIST, hrisTrainingCompetencyList);
    }

}
