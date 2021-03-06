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
package org.wirabumi.hris.leave;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
 * Entity class for entity lv_leave (stored in table lv_leave).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class lv_leave extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "lv_leave";
    public static final String ENTITY_NAME = "lv_leave";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEELEAVE = "employeeleave";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_PLAFON = "plafon";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_PGS = "pGS";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_INTERNALNOTES = "internalNotes";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_USEDLEAVE = "usedLeave";
    public static final String PROPERTY_ISHOUR = "ishour";
    public static final String PROPERTY_SAMPAI = "sampai";
    public static final String PROPERTY_MULAI = "mulai";
    public static final String PROPERTY_DAYSUSEDINTHISLEAVE = "daysUsedInThisLeave";
    public static final String PROPERTY_LVMASSLEAVELLIST = "lvMassLeaveLList";

    public lv_leave() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_DOCUMENTACTION, "CO");
        setDefaultValue(PROPERTY_ISHOUR, false);
        setDefaultValue(PROPERTY_DAYSUSEDINTHISLEAVE, new BigDecimal(0));
        setDefaultValue(PROPERTY_LVMASSLEAVELLIST, new ArrayList<Object>());
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

    public lv_c_bp_leave_v getEmployeeleave() {
        return (lv_c_bp_leave_v) get(PROPERTY_EMPLOYEELEAVE);
    }

    public void setEmployeeleave(lv_c_bp_leave_v employeeleave) {
        set(PROPERTY_EMPLOYEELEAVE, employeeleave);
    }

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public BigDecimal getPlafon() {
        return (BigDecimal) get(PROPERTY_PLAFON);
    }

    public void setPlafon(BigDecimal plafon) {
        set(PROPERTY_PLAFON, plafon);
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

    public BusinessPartner getPGS() {
        return (BusinessPartner) get(PROPERTY_PGS);
    }

    public void setPGS(BusinessPartner pGS) {
        set(PROPERTY_PGS, pGS);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getInternalNotes() {
        return (String) get(PROPERTY_INTERNALNOTES);
    }

    public void setInternalNotes(String internalNotes) {
        set(PROPERTY_INTERNALNOTES, internalNotes);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public BigDecimal getUsedLeave() {
        return (BigDecimal) get(PROPERTY_USEDLEAVE);
    }

    public void setUsedLeave(BigDecimal usedLeave) {
        set(PROPERTY_USEDLEAVE, usedLeave);
    }

    public Boolean isHour() {
        return (Boolean) get(PROPERTY_ISHOUR);
    }

    public void setHour(Boolean ishour) {
        set(PROPERTY_ISHOUR, ishour);
    }

    public Timestamp getSampai() {
        return (Timestamp) get(PROPERTY_SAMPAI);
    }

    public void setSampai(Timestamp sampai) {
        set(PROPERTY_SAMPAI, sampai);
    }

    public Timestamp getMulai() {
        return (Timestamp) get(PROPERTY_MULAI);
    }

    public void setMulai(Timestamp mulai) {
        set(PROPERTY_MULAI, mulai);
    }

    public BigDecimal getDaysUsedInThisLeave() {
        return (BigDecimal) get(PROPERTY_DAYSUSEDINTHISLEAVE);
    }

    public void setDaysUsedInThisLeave(BigDecimal daysUsedInThisLeave) {
        set(PROPERTY_DAYSUSEDINTHISLEAVE, daysUsedInThisLeave);
    }

    @SuppressWarnings("unchecked")
    public List<lv_mass_leave_l> getLvMassLeaveLList() {
      return (List<lv_mass_leave_l>) get(PROPERTY_LVMASSLEAVELLIST);
    }

    public void setLvMassLeaveLList(List<lv_mass_leave_l> lvMassLeaveLList) {
        set(PROPERTY_LVMASSLEAVELLIST, lvMassLeaveLList);
    }

}
