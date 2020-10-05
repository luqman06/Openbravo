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
package org.openbravo.model.common.enterprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.RoleOrganization;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.calendar.Calendar;
import org.openbravo.model.financialmgmt.calendar.PeriodControlV;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.wirabumi.hris.employee.master.data.hris_education_permit;
import org.wirabumi.hris.employee.master.data.hris_employee_transfer;
import org.wirabumi.hris.employee.master.data.hris_ge_employee;
import org.wirabumi.hris.employee.master.data.hris_generalexam;
/**
 * Entity class for entity Organization (stored in table AD_Org).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Organization extends BaseOBObject implements Traceable, ClientEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Org";
    public static final String ENTITY_NAME = "Organization";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_ORGANIZATIONTYPE = "organizationType";
    public static final String PROPERTY_ALLOWPERIODCONTROL = "allowPeriodControl";
    public static final String PROPERTY_CALENDAR = "calendar";
    public static final String PROPERTY_READY = "ready";
    public static final String PROPERTY_OEZCREATEFISCALCALENDAR = "oezCreatefiscalcalendar";
    public static final String PROPERTY_SOCIALNAME = "socialName";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_OEZFICALCALENDARNAME = "oezFicalcalendarname";
    public static final String PROPERTY_OEZASSIGNCALTOORG = "oezAssigncaltoorg";
    public static final String PROPERTY_GENERALLEDGER = "generalLedger";
    public static final String PROPERTY_OEZSETREADYORG = "oezSetreadyorg";
    public static final String PROPERTY_APRMGLITEM = "aPRMGlitem";
    public static final String PROPERTY_OEZCREATEOBPERIOD = "oezCreateobperiod";
    public static final String PROPERTY_PERIODCONTROLALLOWEDORGANIZATION = "periodControlAllowedOrganization";
    public static final String PROPERTY_CALENDAROWNERORGANIZATION = "calendarOwnerOrganization";
    public static final String PROPERTY_OEZINTIALOBDATE = "oezIntialobdate";
    public static final String PROPERTY_OEZCREATEDEFACCTPERIOD = "oezCreatedefacctperiod";
    public static final String PROPERTY_LEGALENTITYORGANIZATION = "legalEntityOrganization";
    public static final String PROPERTY_OEZOPENDEFACCTPERIOD = "oezOpendefacctperiod";
    public static final String PROPERTY_INHERITEDCALENDAR = "inheritedCalendar";
    public static final String PROPERTY_BUSINESSUNITORGANIZATION = "businessUnitOrganization";
    public static final String PROPERTY_OEZACTIVATEALLACCTDOC = "oezActivateallacctdoc";
    public static final String PROPERTY_OEZACCTSCHEMA = "oezAcctschema";
    public static final String PROPERTY_OEZCREATEWAREHOUSE = "oezCreatewarehouse";
    public static final String PROPERTY_OEZWAREHOUSEKEY = "oezWarehousekey";
    public static final String PROPERTY_OEZWAREHOUSENAME = "oezWarehousename";
    public static final String PROPERTY_OEZWAREHOUSEADDRESS = "oezWarehouseaddress";
    public static final String PROPERTY_OEZCREATEBALANCESHEET = "oezCreatebalancesheet";
    public static final String PROPERTY_OEZBALANCESHEETNAME = "oezBalancesheetname";
    public static final String PROPERTY_OEZCREATEINCOMESTATEMENT = "oezCreateincomestatement";
    public static final String PROPERTY_OEZINCOMESTATEMENTNAME = "oezIncomestatementname";
    public static final String PROPERTY_OEZRUNCONFIGWIZARD = "oezRunconfigwizard";
    public static final String PROPERTY_OEZCONTROLDIGIT = "oezControldigit";
    public static final String PROPERTY_OEZCASHCURRENCY = "oezCashCurrency";
    public static final String PROPERTY_OEZBSACCTSCHME = "oezBsAcctschme";
    public static final String PROPERTY_OEZISACCTSCHEMA = "oezIsAcctschema";
    public static final String PROPERTY_ADROLEORGANIZATIONLIST = "aDRoleOrganizationList";
    public static final String PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST = "financialMgmtPeriodControlVList";
    public static final String PROPERTY_ORGANIZATIONINFORMATIONLIST = "organizationInformationList";
    public static final String PROPERTY_ORGANIZATIONMODULEVLIST = "organizationModuleVList";
    public static final String PROPERTY_ORGANIZATIONTREELIST = "organizationTreeList";
    public static final String PROPERTY_ORGANIZATIONWAREHOUSELIST = "organizationWarehouseList";
    public static final String PROPERTY_HRISEDUCATIONPERMITLIST = "hrisEducationPermitList";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERFROMADORGIDLIST = "hrisEmployeeTransferFromadOrgIDList";
    public static final String PROPERTY_HRISGEEMPLOYEELIST = "hrisGeEmployeeList";
    public static final String PROPERTY_HRISGENERALEXAMLIST = "hrisGeneralexamList";

    public Organization() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_ALLOWPERIODCONTROL, false);
        setDefaultValue(PROPERTY_READY, false);
        setDefaultValue(PROPERTY_OEZCREATEFISCALCALENDAR, true);
        setDefaultValue(PROPERTY_OEZASSIGNCALTOORG, true);
        setDefaultValue(PROPERTY_OEZSETREADYORG, true);
        setDefaultValue(PROPERTY_OEZCREATEOBPERIOD, true);
        setDefaultValue(PROPERTY_OEZCREATEDEFACCTPERIOD, true);
        setDefaultValue(PROPERTY_OEZOPENDEFACCTPERIOD, true);
        setDefaultValue(PROPERTY_OEZACTIVATEALLACCTDOC, true);
        setDefaultValue(PROPERTY_OEZCREATEWAREHOUSE, true);
        setDefaultValue(PROPERTY_OEZCREATEBALANCESHEET, true);
        setDefaultValue(PROPERTY_OEZBALANCESHEETNAME, "Balance Sheet");
        setDefaultValue(PROPERTY_OEZCREATEINCOMESTATEMENT, true);
        setDefaultValue(PROPERTY_OEZINCOMESTATEMENTNAME, "Profit and Loss Statement");
        setDefaultValue(PROPERTY_OEZRUNCONFIGWIZARD, false);
        setDefaultValue(PROPERTY_OEZCONTROLDIGIT, (long) 0);
        setDefaultValue(PROPERTY_ADROLEORGANIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONMODULEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONTREELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONWAREHOUSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONPERMITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERFROMADORGIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISGEEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISGENERALEXAMLIST, new ArrayList<Object>());
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public OrganizationType getOrganizationType() {
        return (OrganizationType) get(PROPERTY_ORGANIZATIONTYPE);
    }

    public void setOrganizationType(OrganizationType organizationType) {
        set(PROPERTY_ORGANIZATIONTYPE, organizationType);
    }

    public Boolean isAllowPeriodControl() {
        return (Boolean) get(PROPERTY_ALLOWPERIODCONTROL);
    }

    public void setAllowPeriodControl(Boolean allowPeriodControl) {
        set(PROPERTY_ALLOWPERIODCONTROL, allowPeriodControl);
    }

    public Calendar getCalendar() {
        return (Calendar) get(PROPERTY_CALENDAR);
    }

    public void setCalendar(Calendar calendar) {
        set(PROPERTY_CALENDAR, calendar);
    }

    public Boolean isReady() {
        return (Boolean) get(PROPERTY_READY);
    }

    public void setReady(Boolean ready) {
        set(PROPERTY_READY, ready);
    }

    public Boolean isOezCreatefiscalcalendar() {
        return (Boolean) get(PROPERTY_OEZCREATEFISCALCALENDAR);
    }

    public void setOezCreatefiscalcalendar(Boolean oezCreatefiscalcalendar) {
        set(PROPERTY_OEZCREATEFISCALCALENDAR, oezCreatefiscalcalendar);
    }

    public String getSocialName() {
        return (String) get(PROPERTY_SOCIALNAME);
    }

    public void setSocialName(String socialName) {
        set(PROPERTY_SOCIALNAME, socialName);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getOezFicalcalendarname() {
        return (String) get(PROPERTY_OEZFICALCALENDARNAME);
    }

    public void setOezFicalcalendarname(String oezFicalcalendarname) {
        set(PROPERTY_OEZFICALCALENDARNAME, oezFicalcalendarname);
    }

    public Boolean isOezAssigncaltoorg() {
        return (Boolean) get(PROPERTY_OEZASSIGNCALTOORG);
    }

    public void setOezAssigncaltoorg(Boolean oezAssigncaltoorg) {
        set(PROPERTY_OEZASSIGNCALTOORG, oezAssigncaltoorg);
    }

    public AcctSchema getGeneralLedger() {
        return (AcctSchema) get(PROPERTY_GENERALLEDGER);
    }

    public void setGeneralLedger(AcctSchema generalLedger) {
        set(PROPERTY_GENERALLEDGER, generalLedger);
    }

    public Boolean isOezSetreadyorg() {
        return (Boolean) get(PROPERTY_OEZSETREADYORG);
    }

    public void setOezSetreadyorg(Boolean oezSetreadyorg) {
        set(PROPERTY_OEZSETREADYORG, oezSetreadyorg);
    }

    public GLItem getAPRMGlitem() {
        return (GLItem) get(PROPERTY_APRMGLITEM);
    }

    public void setAPRMGlitem(GLItem aPRMGlitem) {
        set(PROPERTY_APRMGLITEM, aPRMGlitem);
    }

    public Boolean isOezCreateobperiod() {
        return (Boolean) get(PROPERTY_OEZCREATEOBPERIOD);
    }

    public void setOezCreateobperiod(Boolean oezCreateobperiod) {
        set(PROPERTY_OEZCREATEOBPERIOD, oezCreateobperiod);
    }

    public Organization getPeriodControlAllowedOrganization() {
        return (Organization) get(PROPERTY_PERIODCONTROLALLOWEDORGANIZATION);
    }

    public void setPeriodControlAllowedOrganization(Organization periodControlAllowedOrganization) {
        set(PROPERTY_PERIODCONTROLALLOWEDORGANIZATION, periodControlAllowedOrganization);
    }

    public Organization getCalendarOwnerOrganization() {
        return (Organization) get(PROPERTY_CALENDAROWNERORGANIZATION);
    }

    public void setCalendarOwnerOrganization(Organization calendarOwnerOrganization) {
        set(PROPERTY_CALENDAROWNERORGANIZATION, calendarOwnerOrganization);
    }

    public Date getOezIntialobdate() {
        return (Date) get(PROPERTY_OEZINTIALOBDATE);
    }

    public void setOezIntialobdate(Date oezIntialobdate) {
        set(PROPERTY_OEZINTIALOBDATE, oezIntialobdate);
    }

    public Boolean isOezCreatedefacctperiod() {
        return (Boolean) get(PROPERTY_OEZCREATEDEFACCTPERIOD);
    }

    public void setOezCreatedefacctperiod(Boolean oezCreatedefacctperiod) {
        set(PROPERTY_OEZCREATEDEFACCTPERIOD, oezCreatedefacctperiod);
    }

    public Organization getLegalEntityOrganization() {
        return (Organization) get(PROPERTY_LEGALENTITYORGANIZATION);
    }

    public void setLegalEntityOrganization(Organization legalEntityOrganization) {
        set(PROPERTY_LEGALENTITYORGANIZATION, legalEntityOrganization);
    }

    public Boolean isOezOpendefacctperiod() {
        return (Boolean) get(PROPERTY_OEZOPENDEFACCTPERIOD);
    }

    public void setOezOpendefacctperiod(Boolean oezOpendefacctperiod) {
        set(PROPERTY_OEZOPENDEFACCTPERIOD, oezOpendefacctperiod);
    }

    public Calendar getInheritedCalendar() {
        return (Calendar) get(PROPERTY_INHERITEDCALENDAR);
    }

    public void setInheritedCalendar(Calendar inheritedCalendar) {
        set(PROPERTY_INHERITEDCALENDAR, inheritedCalendar);
    }

    public Organization getBusinessUnitOrganization() {
        return (Organization) get(PROPERTY_BUSINESSUNITORGANIZATION);
    }

    public void setBusinessUnitOrganization(Organization businessUnitOrganization) {
        set(PROPERTY_BUSINESSUNITORGANIZATION, businessUnitOrganization);
    }

    public Boolean isOezActivateallacctdoc() {
        return (Boolean) get(PROPERTY_OEZACTIVATEALLACCTDOC);
    }

    public void setOezActivateallacctdoc(Boolean oezActivateallacctdoc) {
        set(PROPERTY_OEZACTIVATEALLACCTDOC, oezActivateallacctdoc);
    }

    public AcctSchema getOezAcctschema() {
        return (AcctSchema) get(PROPERTY_OEZACCTSCHEMA);
    }

    public void setOezAcctschema(AcctSchema oezAcctschema) {
        set(PROPERTY_OEZACCTSCHEMA, oezAcctschema);
    }

    public Boolean isOezCreatewarehouse() {
        return (Boolean) get(PROPERTY_OEZCREATEWAREHOUSE);
    }

    public void setOezCreatewarehouse(Boolean oezCreatewarehouse) {
        set(PROPERTY_OEZCREATEWAREHOUSE, oezCreatewarehouse);
    }

    public String getOezWarehousekey() {
        return (String) get(PROPERTY_OEZWAREHOUSEKEY);
    }

    public void setOezWarehousekey(String oezWarehousekey) {
        set(PROPERTY_OEZWAREHOUSEKEY, oezWarehousekey);
    }

    public String getOezWarehousename() {
        return (String) get(PROPERTY_OEZWAREHOUSENAME);
    }

    public void setOezWarehousename(String oezWarehousename) {
        set(PROPERTY_OEZWAREHOUSENAME, oezWarehousename);
    }

    public Location getOezWarehouseaddress() {
        return (Location) get(PROPERTY_OEZWAREHOUSEADDRESS);
    }

    public void setOezWarehouseaddress(Location oezWarehouseaddress) {
        set(PROPERTY_OEZWAREHOUSEADDRESS, oezWarehouseaddress);
    }

    public Boolean isOezCreatebalancesheet() {
        return (Boolean) get(PROPERTY_OEZCREATEBALANCESHEET);
    }

    public void setOezCreatebalancesheet(Boolean oezCreatebalancesheet) {
        set(PROPERTY_OEZCREATEBALANCESHEET, oezCreatebalancesheet);
    }

    public String getOezBalancesheetname() {
        return (String) get(PROPERTY_OEZBALANCESHEETNAME);
    }

    public void setOezBalancesheetname(String oezBalancesheetname) {
        set(PROPERTY_OEZBALANCESHEETNAME, oezBalancesheetname);
    }

    public Boolean isOezCreateincomestatement() {
        return (Boolean) get(PROPERTY_OEZCREATEINCOMESTATEMENT);
    }

    public void setOezCreateincomestatement(Boolean oezCreateincomestatement) {
        set(PROPERTY_OEZCREATEINCOMESTATEMENT, oezCreateincomestatement);
    }

    public String getOezIncomestatementname() {
        return (String) get(PROPERTY_OEZINCOMESTATEMENTNAME);
    }

    public void setOezIncomestatementname(String oezIncomestatementname) {
        set(PROPERTY_OEZINCOMESTATEMENTNAME, oezIncomestatementname);
    }

    public Boolean isOezRunconfigwizard() {
        return (Boolean) get(PROPERTY_OEZRUNCONFIGWIZARD);
    }

    public void setOezRunconfigwizard(Boolean oezRunconfigwizard) {
        set(PROPERTY_OEZRUNCONFIGWIZARD, oezRunconfigwizard);
    }

    public Long getOezControldigit() {
        return (Long) get(PROPERTY_OEZCONTROLDIGIT);
    }

    public void setOezControldigit(Long oezControldigit) {
        set(PROPERTY_OEZCONTROLDIGIT, oezControldigit);
    }

    public Currency getOezCashCurrency() {
        return (Currency) get(PROPERTY_OEZCASHCURRENCY);
    }

    public void setOezCashCurrency(Currency oezCashCurrency) {
        set(PROPERTY_OEZCASHCURRENCY, oezCashCurrency);
    }

    public AcctSchema getOezBsAcctschme() {
        return (AcctSchema) get(PROPERTY_OEZBSACCTSCHME);
    }

    public void setOezBsAcctschme(AcctSchema oezBsAcctschme) {
        set(PROPERTY_OEZBSACCTSCHME, oezBsAcctschme);
    }

    public AcctSchema getOezIsAcctschema() {
        return (AcctSchema) get(PROPERTY_OEZISACCTSCHEMA);
    }

    public void setOezIsAcctschema(AcctSchema oezIsAcctschema) {
        set(PROPERTY_OEZISACCTSCHEMA, oezIsAcctschema);
    }

    @SuppressWarnings("unchecked")
    public List<RoleOrganization> getADRoleOrganizationList() {
      return (List<RoleOrganization>) get(PROPERTY_ADROLEORGANIZATIONLIST);
    }

    public void setADRoleOrganizationList(List<RoleOrganization> aDRoleOrganizationList) {
        set(PROPERTY_ADROLEORGANIZATIONLIST, aDRoleOrganizationList);
    }

    @SuppressWarnings("unchecked")
    public List<PeriodControlV> getFinancialMgmtPeriodControlVList() {
      return (List<PeriodControlV>) get(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST);
    }

    public void setFinancialMgmtPeriodControlVList(List<PeriodControlV> financialMgmtPeriodControlVList) {
        set(PROPERTY_FINANCIALMGMTPERIODCONTROLVLIST, financialMgmtPeriodControlVList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationInformation> getOrganizationInformationList() {
      return (List<OrganizationInformation>) get(PROPERTY_ORGANIZATIONINFORMATIONLIST);
    }

    public void setOrganizationInformationList(List<OrganizationInformation> organizationInformationList) {
        set(PROPERTY_ORGANIZATIONINFORMATIONLIST, organizationInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationModuleV> getOrganizationModuleVList() {
      return (List<OrganizationModuleV>) get(PROPERTY_ORGANIZATIONMODULEVLIST);
    }

    public void setOrganizationModuleVList(List<OrganizationModuleV> organizationModuleVList) {
        set(PROPERTY_ORGANIZATIONMODULEVLIST, organizationModuleVList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationTree> getOrganizationTreeList() {
      return (List<OrganizationTree>) get(PROPERTY_ORGANIZATIONTREELIST);
    }

    public void setOrganizationTreeList(List<OrganizationTree> organizationTreeList) {
        set(PROPERTY_ORGANIZATIONTREELIST, organizationTreeList);
    }

    @SuppressWarnings("unchecked")
    public List<OrgWarehouse> getOrganizationWarehouseList() {
      return (List<OrgWarehouse>) get(PROPERTY_ORGANIZATIONWAREHOUSELIST);
    }

    public void setOrganizationWarehouseList(List<OrgWarehouse> organizationWarehouseList) {
        set(PROPERTY_ORGANIZATIONWAREHOUSELIST, organizationWarehouseList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_permit> getHrisEducationPermitList() {
      return (List<hris_education_permit>) get(PROPERTY_HRISEDUCATIONPERMITLIST);
    }

    public void setHrisEducationPermitList(List<hris_education_permit> hrisEducationPermitList) {
        set(PROPERTY_HRISEDUCATIONPERMITLIST, hrisEducationPermitList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferFromadOrgIDList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERFROMADORGIDLIST);
    }

    public void setHrisEmployeeTransferFromadOrgIDList(List<hris_employee_transfer> hrisEmployeeTransferFromadOrgIDList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERFROMADORGIDLIST, hrisEmployeeTransferFromadOrgIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ge_employee> getHrisGeEmployeeList() {
      return (List<hris_ge_employee>) get(PROPERTY_HRISGEEMPLOYEELIST);
    }

    public void setHrisGeEmployeeList(List<hris_ge_employee> hrisGeEmployeeList) {
        set(PROPERTY_HRISGEEMPLOYEELIST, hrisGeEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_generalexam> getHrisGeneralexamList() {
      return (List<hris_generalexam>) get(PROPERTY_HRISGENERALEXAMLIST);
    }

    public void setHrisGeneralexamList(List<hris_generalexam> hrisGeneralexamList) {
        set(PROPERTY_HRISGENERALEXAMLIST, hrisGeneralexamList);
    }

}
