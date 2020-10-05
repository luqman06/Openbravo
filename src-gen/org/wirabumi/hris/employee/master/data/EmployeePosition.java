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
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.termination.tm_termination;
/**
 * Entity class for entity hris_employeeposition (stored in table hris_employeeposition).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EmployeePosition extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_employeeposition";
    public static final String ENTITY_NAME = "hris_employeeposition";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_HRISCBPEMPINFOPOSITIONLIST = "hRISCBpEmpinfoPositionList";
    public static final String PROPERTY_HRISECLINESPOSITIONLIST = "hrisEcLinesPositionList";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERFROMPOSITIONLIST = "hrisEmployeeTransferFrompositionList";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERTOPOSITIONEDLIST = "hrisEmployeeTransferTopositionedList";
    public static final String PROPERTY_HRISETLINEOLDPOSITIONLIST = "hrisEtLineOLDPositionList";
    public static final String PROPERTY_HRISETLINENEWPOSITIONLIST = "hrisEtLineNEWPositionList";
    public static final String PROPERTY_HRISJOBTITLEPOSITIONLIST = "hrisJobtitlePositionList";
    public static final String PROPERTY_HRISPENGUNDURANDIRIPOSITIONLIST = "hrisPengundurandiriPositionList";
    public static final String PROPERTY_HRISRLINEPOSITIONLIST = "hrisRLinePositionList";
    public static final String PROPERTY_OTOVERTIMEPOSITIONLIST = "otOvertimePositionList";
    public static final String PROPERTY_TMTERMINATIONPOSITIONLIST = "tmTerminationPositionList";

    public EmployeePosition() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_HRISCBPEMPINFOPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISECLINESPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERFROMPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERTOPOSITIONEDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINEOLDPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINENEWPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJOBTITLEPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISPENGUNDURANDIRIPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRLINEPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OTOVERTIMEPOSITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TMTERMINATIONPOSITIONLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Empinfo> getHRISCBpEmpinfoPositionList() {
      return (List<HRIS_C_Bp_Empinfo>) get(PROPERTY_HRISCBPEMPINFOPOSITIONLIST);
    }

    public void setHRISCBpEmpinfoPositionList(List<HRIS_C_Bp_Empinfo> hRISCBpEmpinfoPositionList) {
        set(PROPERTY_HRISCBPEMPINFOPOSITIONLIST, hRISCBpEmpinfoPositionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ec_lines> getHrisEcLinesPositionList() {
      return (List<hris_ec_lines>) get(PROPERTY_HRISECLINESPOSITIONLIST);
    }

    public void setHrisEcLinesPositionList(List<hris_ec_lines> hrisEcLinesPositionList) {
        set(PROPERTY_HRISECLINESPOSITIONLIST, hrisEcLinesPositionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferFrompositionList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERFROMPOSITIONLIST);
    }

    public void setHrisEmployeeTransferFrompositionList(List<hris_employee_transfer> hrisEmployeeTransferFrompositionList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERFROMPOSITIONLIST, hrisEmployeeTransferFrompositionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferTopositionedList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERTOPOSITIONEDLIST);
    }

    public void setHrisEmployeeTransferTopositionedList(List<hris_employee_transfer> hrisEmployeeTransferTopositionedList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERTOPOSITIONEDLIST, hrisEmployeeTransferTopositionedList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineOLDPositionList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINEOLDPOSITIONLIST);
    }

    public void setHrisEtLineOLDPositionList(List<EmployeeTransferLine> hrisEtLineOLDPositionList) {
        set(PROPERTY_HRISETLINEOLDPOSITIONLIST, hrisEtLineOLDPositionList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineNEWPositionList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINENEWPOSITIONLIST);
    }

    public void setHrisEtLineNEWPositionList(List<EmployeeTransferLine> hrisEtLineNEWPositionList) {
        set(PROPERTY_HRISETLINENEWPOSITIONLIST, hrisEtLineNEWPositionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_jobtitle> getHrisJobtitlePositionList() {
      return (List<hris_jobtitle>) get(PROPERTY_HRISJOBTITLEPOSITIONLIST);
    }

    public void setHrisJobtitlePositionList(List<hris_jobtitle> hrisJobtitlePositionList) {
        set(PROPERTY_HRISJOBTITLEPOSITIONLIST, hrisJobtitlePositionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_pengundurandiri> getHrisPengundurandiriPositionList() {
      return (List<hris_pengundurandiri>) get(PROPERTY_HRISPENGUNDURANDIRIPOSITIONLIST);
    }

    public void setHrisPengundurandiriPositionList(List<hris_pengundurandiri> hrisPengundurandiriPositionList) {
        set(PROPERTY_HRISPENGUNDURANDIRIPOSITIONLIST, hrisPengundurandiriPositionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_r_line> getHrisRLinePositionList() {
      return (List<hris_r_line>) get(PROPERTY_HRISRLINEPOSITIONLIST);
    }

    public void setHrisRLinePositionList(List<hris_r_line> hrisRLinePositionList) {
        set(PROPERTY_HRISRLINEPOSITIONLIST, hrisRLinePositionList);
    }

    @SuppressWarnings("unchecked")
    public List<ot_overtime> getOtOvertimePositionList() {
      return (List<ot_overtime>) get(PROPERTY_OTOVERTIMEPOSITIONLIST);
    }

    public void setOtOvertimePositionList(List<ot_overtime> otOvertimePositionList) {
        set(PROPERTY_OTOVERTIMEPOSITIONLIST, otOvertimePositionList);
    }

    @SuppressWarnings("unchecked")
    public List<tm_termination> getTmTerminationPositionList() {
      return (List<tm_termination>) get(PROPERTY_TMTERMINATIONPOSITIONLIST);
    }

    public void setTmTerminationPositionList(List<tm_termination> tmTerminationPositionList) {
        set(PROPERTY_TMTERMINATIONPOSITIONLIST, tmTerminationPositionList);
    }

}
