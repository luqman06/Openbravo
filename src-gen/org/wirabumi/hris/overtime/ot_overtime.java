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
package org.wirabumi.hris.overtime;

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
import org.wirabumi.hris.employee.master.data.EmployeePosition;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.timeandattendance.TAAttendance;
/**
 * Entity class for entity ot_overtime (stored in table ot_overtime).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ot_overtime extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "ot_overtime";
    public static final String ENTITY_NAME = "ot_overtime";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_CALCULATED = "calculated";
    public static final String PROPERTY_AMOUNT = "amount";
    public static final String PROPERTY_CHECKIN = "checkin";
    public static final String PROPERTY_CHECKOUT = "checkout";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DURATION = "duration";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_VALIDREQUSTFINISH = "validrequstfinish";
    public static final String PROPERTY_VALIDREQUESTSTART = "validrequeststart";
    public static final String PROPERTY_CALCULATEOVERTIME = "calculateovertime";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_JOBTITLE = "jobtitle";
    public static final String PROPERTY_EMPLOYEEGRADE = "employeegrade";
    public static final String PROPERTY_PAID = "paid";
    public static final String PROPERTY_INTDURATION = "intduration";
    public static final String PROPERTY_ATTENDANCE = "attendance";
    public static final String PROPERTY_PYRSALARYPAYMENT = "pYRSalarypayment";
    public static final String PROPERTY_VALIDATIONNOTE = "validationnote";
    public static final String PROPERTY_MANUAL = "manual";
    public static final String PROPERTY_ISCHECKINONHOLIDAY = "ischeckinonholiday";
    public static final String PROPERTY_ISCHECKOUTONHOLIDAY = "ischeckoutonholiday";
    public static final String PROPERTY_OTOVERTIMEDETAILLIST = "otOvertimeDetailList";

    public ot_overtime() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "OT_Release");
        setDefaultValue(PROPERTY_DOCUMENTACTION, "AP");
        setDefaultValue(PROPERTY_CALCULATED, false);
        setDefaultValue(PROPERTY_AMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_STATUS, "OT");
        setDefaultValue(PROPERTY_CALCULATEOVERTIME, true);
        setDefaultValue(PROPERTY_PAID, false);
        setDefaultValue(PROPERTY_INTDURATION, new BigDecimal(0));
        setDefaultValue(PROPERTY_MANUAL, false);
        setDefaultValue(PROPERTY_ISCHECKINONHOLIDAY, false);
        setDefaultValue(PROPERTY_ISCHECKOUTONHOLIDAY, false);
        setDefaultValue(PROPERTY_OTOVERTIMEDETAILLIST, new ArrayList<Object>());
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
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

    public Boolean isCalculated() {
        return (Boolean) get(PROPERTY_CALCULATED);
    }

    public void setCalculated(Boolean calculated) {
        set(PROPERTY_CALCULATED, calculated);
    }

    public BigDecimal getAmount() {
        return (BigDecimal) get(PROPERTY_AMOUNT);
    }

    public void setAmount(BigDecimal amount) {
        set(PROPERTY_AMOUNT, amount);
    }

    public Timestamp getCheckin() {
        return (Timestamp) get(PROPERTY_CHECKIN);
    }

    public void setCheckin(Timestamp checkin) {
        set(PROPERTY_CHECKIN, checkin);
    }

    public Timestamp getCheckout() {
        return (Timestamp) get(PROPERTY_CHECKOUT);
    }

    public void setCheckout(Timestamp checkout) {
        set(PROPERTY_CHECKOUT, checkout);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Timestamp getDuration() {
        return (Timestamp) get(PROPERTY_DURATION);
    }

    public void setDuration(Timestamp duration) {
        set(PROPERTY_DURATION, duration);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public Date getValidrequstfinish() {
        return (Date) get(PROPERTY_VALIDREQUSTFINISH);
    }

    public void setValidrequstfinish(Date validrequstfinish) {
        set(PROPERTY_VALIDREQUSTFINISH, validrequstfinish);
    }

    public Date getValidrequeststart() {
        return (Date) get(PROPERTY_VALIDREQUESTSTART);
    }

    public void setValidrequeststart(Date validrequeststart) {
        set(PROPERTY_VALIDREQUESTSTART, validrequeststart);
    }

    public Boolean isCalculateovertime() {
        return (Boolean) get(PROPERTY_CALCULATEOVERTIME);
    }

    public void setCalculateovertime(Boolean calculateovertime) {
        set(PROPERTY_CALCULATEOVERTIME, calculateovertime);
    }

    public EmployeePosition getPosition() {
        return (EmployeePosition) get(PROPERTY_POSITION);
    }

    public void setPosition(EmployeePosition position) {
        set(PROPERTY_POSITION, position);
    }

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
    }

    public hris_jobtitle getJobtitle() {
        return (hris_jobtitle) get(PROPERTY_JOBTITLE);
    }

    public void setJobtitle(hris_jobtitle jobtitle) {
        set(PROPERTY_JOBTITLE, jobtitle);
    }

    public String getEmployeegrade() {
        return (String) get(PROPERTY_EMPLOYEEGRADE);
    }

    public void setEmployeegrade(String employeegrade) {
        set(PROPERTY_EMPLOYEEGRADE, employeegrade);
    }

    public Boolean isPaid() {
        return (Boolean) get(PROPERTY_PAID);
    }

    public void setPaid(Boolean paid) {
        set(PROPERTY_PAID, paid);
    }

    public BigDecimal getIntduration() {
        return (BigDecimal) get(PROPERTY_INTDURATION);
    }

    public void setIntduration(BigDecimal intduration) {
        set(PROPERTY_INTDURATION, intduration);
    }

    public TAAttendance getAttendance() {
        return (TAAttendance) get(PROPERTY_ATTENDANCE);
    }

    public void setAttendance(TAAttendance attendance) {
        set(PROPERTY_ATTENDANCE, attendance);
    }

    public pyr_salarypayment getPYRSalarypayment() {
        return (pyr_salarypayment) get(PROPERTY_PYRSALARYPAYMENT);
    }

    public void setPYRSalarypayment(pyr_salarypayment pYRSalarypayment) {
        set(PROPERTY_PYRSALARYPAYMENT, pYRSalarypayment);
    }

    public String getValidationnote() {
        return (String) get(PROPERTY_VALIDATIONNOTE);
    }

    public void setValidationnote(String validationnote) {
        set(PROPERTY_VALIDATIONNOTE, validationnote);
    }

    public Boolean isManual() {
        return (Boolean) get(PROPERTY_MANUAL);
    }

    public void setManual(Boolean manual) {
        set(PROPERTY_MANUAL, manual);
    }

    public Boolean isCheckinonholiday() {
        return (Boolean) get(PROPERTY_ISCHECKINONHOLIDAY);
    }

    public void setCheckinonholiday(Boolean ischeckinonholiday) {
        set(PROPERTY_ISCHECKINONHOLIDAY, ischeckinonholiday);
    }

    public Boolean isCheckoutonholiday() {
        return (Boolean) get(PROPERTY_ISCHECKOUTONHOLIDAY);
    }

    public void setCheckoutonholiday(Boolean ischeckoutonholiday) {
        set(PROPERTY_ISCHECKOUTONHOLIDAY, ischeckoutonholiday);
    }

    @SuppressWarnings("unchecked")
    public List<ot_overtime_detail> getOtOvertimeDetailList() {
      return (List<ot_overtime_detail>) get(PROPERTY_OTOVERTIMEDETAILLIST);
    }

    public void setOtOvertimeDetailList(List<ot_overtime_detail> otOvertimeDetailList) {
        set(PROPERTY_OTOVERTIMEDETAILLIST, otOvertimeDetailList);
    }

}
