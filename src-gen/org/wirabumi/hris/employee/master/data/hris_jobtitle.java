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
import org.openbravo.model.ad.access.Role;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.termination.tm_termination;
/**
 * Entity class for entity hris_jobtitle (stored in table hris_jobtitle).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class hris_jobtitle extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "hris_jobtitle";
    public static final String ENTITY_NAME = "hris_jobtitle";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_JOBTITLETYPE = "jobTitleType";
    public static final String PROPERTY_MINIMUMEMPLOYEEGRADE = "minimumEmployeeGrade";
    public static final String PROPERTY_HRISADORGTRX = "hrisAdOrgtrx";
    public static final String PROPERTY_ROLE = "role";
    public static final String PROPERTY_COSTCENTER = "costCenter";
    public static final String PROPERTY_BUSINESSPARTNEREMHRISJOBTITLEIDLIST = "businessPartnerEMHrisJobtitleIDList";
    public static final String PROPERTY_HRISCBPEMPINFOLIST = "hRISCBpEmpinfoList";
    public static final String PROPERTY_HRISARCIACCOUNTABLELIST = "hrisArciAccountableList";
    public static final String PROPERTY_HRISARCICONSULTLIST = "hrisArciConsultList";
    public static final String PROPERTY_HRISARCIINFORMLIST = "hrisArciInformList";
    public static final String PROPERTY_HRISARCIRESPONSIBLELIST = "hrisArciResponsibleList";
    public static final String PROPERTY_HRISBENEFITSLIST = "hrisBenefitsList";
    public static final String PROPERTY_HRISCASELIST = "hrisCaseList";
    public static final String PROPERTY_HRISCOMPETENCYJOBTITLELIST = "hrisCompetencyJobtitleList";
    public static final String PROPERTY_HRISECLINESLIST = "hrisEcLinesList";
    public static final String PROPERTY_HRISEDUCATIONADMISSIONLIST = "hrisEducationAdmissionList";
    public static final String PROPERTY_HRISEDUCATIONEXAMLIST = "hrisEducationExamList";
    public static final String PROPERTY_HRISEDUCATIONPERMITLIST = "hrisEducationPermitList";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERFROMHRISJOBTITLEIDLIST = "hrisEmployeeTransferFromhrisJobtitleIDList";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERTOHRISJOBTITLEIDLIST = "hrisEmployeeTransferTohrisJobtitleIDList";
    public static final String PROPERTY_HRISEMPLOYEETREEVLIST = "hrisEmployeeTreeVList";
    public static final String PROPERTY_HRISEMPLOYEETREEVREPORTSETLIST = "hrisEmployeeTreeVReportSetList";
    public static final String PROPERTY_HRISEMPLOYEECLOSURENODEIDLIST = "hrisEmployeeclosureNodeIDList";
    public static final String PROPERTY_HRISEMPLOYEECLOSUREPARENTIDLIST = "hrisEmployeeclosureParentIDList";
    public static final String PROPERTY_HRISETLINEOLDJOBTITLEIDLIST = "hrisEtLineOLDJobtitleIDList";
    public static final String PROPERTY_HRISETLINENEWJOBTITLEIDLIST = "hrisEtLineNEWJobtitleIDList";
    public static final String PROPERTY_HRISEXCJOBTITLELIST = "hrisExcJobtitleList";
    public static final String PROPERTY_HRISGEEMPLOYEELIST = "hrisGeEmployeeList";
    public static final String PROPERTY_HRISIEMPLOYEECANDIDATELIST = "hrisIEmployeeCandidateList";
    public static final String PROPERTY_HRISJOBTITLERULELIST = "hrisJobtitleruleList";
    public static final String PROPERTY_HRISJTEDUCATIONLIST = "hrisJtEducationList";
    public static final String PROPERTY_HRISJTJOBEXPERIENCELIST = "hrisJtJobexperienceList";
    public static final String PROPERTY_HRISJTJOBEXPERIENCEHRISJOBEXPERIENCEIDLIST = "hrisJtJobexperienceHrisJobexperienceIDList";
    public static final String PROPERTY_HRISJTKPIVERSIONLIST = "hrisJtKpiVersionList";
    public static final String PROPERTY_HRISJTMAJORCHALLENGELIST = "hrisJtMajorchallengeList";
    public static final String PROPERTY_HRISPENGUNDURANDIRIJOBTITLELIST = "hrisPengundurandiriJobTitleList";
    public static final String PROPERTY_HRISRLINEJOBTITLELIST = "hrisRLineJobtitleList";
    public static final String PROPERTY_HRISREIMBURSMENTLIST = "hrisReimbursmentList";
    public static final String PROPERTY_OTOVERTIMEJOBTITLELIST = "otOvertimeJobtitleList";
    public static final String PROPERTY_PYRSPEMPLOYEELIST = "pyrSpEmployeeList";
    public static final String PROPERTY_TMTERMINATIONLIST = "tmTerminationList";

    public hris_jobtitle() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_BUSINESSPARTNEREMHRISJOBTITLEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEMPINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISARCIACCOUNTABLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISARCICONSULTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISARCIINFORMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISARCIRESPONSIBLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISBENEFITSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCASELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCOMPETENCYJOBTITLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISECLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONADMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONEXAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONPERMITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERFROMHRISJOBTITLEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERTOHRISJOBTITLEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETREEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETREEVREPORTSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEECLOSURENODEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEECLOSUREPARENTIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINEOLDJOBTITLEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINENEWJOBTITLEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEXCJOBTITLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISGEEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISIEMPLOYEECANDIDATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJOBTITLERULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJTEDUCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJTJOBEXPERIENCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJTJOBEXPERIENCEHRISJOBEXPERIENCEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJTKPIVERSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISJTMAJORCHALLENGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISPENGUNDURANDIRIJOBTITLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRLINEJOBTITLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISREIMBURSMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OTOVERTIMEJOBTITLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSPEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TMTERMINATIONLIST, new ArrayList<Object>());
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

    public EmployeePosition getPosition() {
        return (EmployeePosition) get(PROPERTY_POSITION);
    }

    public void setPosition(EmployeePosition position) {
        set(PROPERTY_POSITION, position);
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

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
    }

    public String getJobTitleType() {
        return (String) get(PROPERTY_JOBTITLETYPE);
    }

    public void setJobTitleType(String jobTitleType) {
        set(PROPERTY_JOBTITLETYPE, jobTitleType);
    }

    public String getMinimumEmployeeGrade() {
        return (String) get(PROPERTY_MINIMUMEMPLOYEEGRADE);
    }

    public void setMinimumEmployeeGrade(String minimumEmployeeGrade) {
        set(PROPERTY_MINIMUMEMPLOYEEGRADE, minimumEmployeeGrade);
    }

    public Organization getHrisAdOrgtrx() {
        return (Organization) get(PROPERTY_HRISADORGTRX);
    }

    public void setHrisAdOrgtrx(Organization hrisAdOrgtrx) {
        set(PROPERTY_HRISADORGTRX, hrisAdOrgtrx);
    }

    public Role getRole() {
        return (Role) get(PROPERTY_ROLE);
    }

    public void setRole(Role role) {
        set(PROPERTY_ROLE, role);
    }

    public Costcenter getCostCenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostCenter(Costcenter costCenter) {
        set(PROPERTY_COSTCENTER, costCenter);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerEMHrisJobtitleIDList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNEREMHRISJOBTITLEIDLIST);
    }

    public void setBusinessPartnerEMHrisJobtitleIDList(List<BusinessPartner> businessPartnerEMHrisJobtitleIDList) {
        set(PROPERTY_BUSINESSPARTNEREMHRISJOBTITLEIDLIST, businessPartnerEMHrisJobtitleIDList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Empinfo> getHRISCBpEmpinfoList() {
      return (List<HRIS_C_Bp_Empinfo>) get(PROPERTY_HRISCBPEMPINFOLIST);
    }

    public void setHRISCBpEmpinfoList(List<HRIS_C_Bp_Empinfo> hRISCBpEmpinfoList) {
        set(PROPERTY_HRISCBPEMPINFOLIST, hRISCBpEmpinfoList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_arci_accountable> getHrisArciAccountableList() {
      return (List<hris_arci_accountable>) get(PROPERTY_HRISARCIACCOUNTABLELIST);
    }

    public void setHrisArciAccountableList(List<hris_arci_accountable> hrisArciAccountableList) {
        set(PROPERTY_HRISARCIACCOUNTABLELIST, hrisArciAccountableList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_arci_consult> getHrisArciConsultList() {
      return (List<hris_arci_consult>) get(PROPERTY_HRISARCICONSULTLIST);
    }

    public void setHrisArciConsultList(List<hris_arci_consult> hrisArciConsultList) {
        set(PROPERTY_HRISARCICONSULTLIST, hrisArciConsultList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_arci_inform> getHrisArciInformList() {
      return (List<hris_arci_inform>) get(PROPERTY_HRISARCIINFORMLIST);
    }

    public void setHrisArciInformList(List<hris_arci_inform> hrisArciInformList) {
        set(PROPERTY_HRISARCIINFORMLIST, hrisArciInformList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_arci_responsible> getHrisArciResponsibleList() {
      return (List<hris_arci_responsible>) get(PROPERTY_HRISARCIRESPONSIBLELIST);
    }

    public void setHrisArciResponsibleList(List<hris_arci_responsible> hrisArciResponsibleList) {
        set(PROPERTY_HRISARCIRESPONSIBLELIST, hrisArciResponsibleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_benefits> getHrisBenefitsList() {
      return (List<hris_benefits>) get(PROPERTY_HRISBENEFITSLIST);
    }

    public void setHrisBenefitsList(List<hris_benefits> hrisBenefitsList) {
        set(PROPERTY_HRISBENEFITSLIST, hrisBenefitsList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_case> getHrisCaseList() {
      return (List<hris_case>) get(PROPERTY_HRISCASELIST);
    }

    public void setHrisCaseList(List<hris_case> hrisCaseList) {
        set(PROPERTY_HRISCASELIST, hrisCaseList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_competency_jobtitle> getHrisCompetencyJobtitleList() {
      return (List<hris_competency_jobtitle>) get(PROPERTY_HRISCOMPETENCYJOBTITLELIST);
    }

    public void setHrisCompetencyJobtitleList(List<hris_competency_jobtitle> hrisCompetencyJobtitleList) {
        set(PROPERTY_HRISCOMPETENCYJOBTITLELIST, hrisCompetencyJobtitleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ec_lines> getHrisEcLinesList() {
      return (List<hris_ec_lines>) get(PROPERTY_HRISECLINESLIST);
    }

    public void setHrisEcLinesList(List<hris_ec_lines> hrisEcLinesList) {
        set(PROPERTY_HRISECLINESLIST, hrisEcLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_admission> getHrisEducationAdmissionList() {
      return (List<hris_education_admission>) get(PROPERTY_HRISEDUCATIONADMISSIONLIST);
    }

    public void setHrisEducationAdmissionList(List<hris_education_admission> hrisEducationAdmissionList) {
        set(PROPERTY_HRISEDUCATIONADMISSIONLIST, hrisEducationAdmissionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_exam> getHrisEducationExamList() {
      return (List<hris_education_exam>) get(PROPERTY_HRISEDUCATIONEXAMLIST);
    }

    public void setHrisEducationExamList(List<hris_education_exam> hrisEducationExamList) {
        set(PROPERTY_HRISEDUCATIONEXAMLIST, hrisEducationExamList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_permit> getHrisEducationPermitList() {
      return (List<hris_education_permit>) get(PROPERTY_HRISEDUCATIONPERMITLIST);
    }

    public void setHrisEducationPermitList(List<hris_education_permit> hrisEducationPermitList) {
        set(PROPERTY_HRISEDUCATIONPERMITLIST, hrisEducationPermitList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferFromhrisJobtitleIDList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERFROMHRISJOBTITLEIDLIST);
    }

    public void setHrisEmployeeTransferFromhrisJobtitleIDList(List<hris_employee_transfer> hrisEmployeeTransferFromhrisJobtitleIDList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERFROMHRISJOBTITLEIDLIST, hrisEmployeeTransferFromhrisJobtitleIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferTohrisJobtitleIDList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERTOHRISJOBTITLEIDLIST);
    }

    public void setHrisEmployeeTransferTohrisJobtitleIDList(List<hris_employee_transfer> hrisEmployeeTransferTohrisJobtitleIDList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERTOHRISJOBTITLEIDLIST, hrisEmployeeTransferTohrisJobtitleIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_tree_v> getHrisEmployeeTreeVList() {
      return (List<hris_employee_tree_v>) get(PROPERTY_HRISEMPLOYEETREEVLIST);
    }

    public void setHrisEmployeeTreeVList(List<hris_employee_tree_v> hrisEmployeeTreeVList) {
        set(PROPERTY_HRISEMPLOYEETREEVLIST, hrisEmployeeTreeVList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_tree_v> getHrisEmployeeTreeVReportSetList() {
      return (List<hris_employee_tree_v>) get(PROPERTY_HRISEMPLOYEETREEVREPORTSETLIST);
    }

    public void setHrisEmployeeTreeVReportSetList(List<hris_employee_tree_v> hrisEmployeeTreeVReportSetList) {
        set(PROPERTY_HRISEMPLOYEETREEVREPORTSETLIST, hrisEmployeeTreeVReportSetList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeClosure> getHrisEmployeeclosureNodeIDList() {
      return (List<EmployeeClosure>) get(PROPERTY_HRISEMPLOYEECLOSURENODEIDLIST);
    }

    public void setHrisEmployeeclosureNodeIDList(List<EmployeeClosure> hrisEmployeeclosureNodeIDList) {
        set(PROPERTY_HRISEMPLOYEECLOSURENODEIDLIST, hrisEmployeeclosureNodeIDList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeClosure> getHrisEmployeeclosureParentIDList() {
      return (List<EmployeeClosure>) get(PROPERTY_HRISEMPLOYEECLOSUREPARENTIDLIST);
    }

    public void setHrisEmployeeclosureParentIDList(List<EmployeeClosure> hrisEmployeeclosureParentIDList) {
        set(PROPERTY_HRISEMPLOYEECLOSUREPARENTIDLIST, hrisEmployeeclosureParentIDList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineOLDJobtitleIDList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINEOLDJOBTITLEIDLIST);
    }

    public void setHrisEtLineOLDJobtitleIDList(List<EmployeeTransferLine> hrisEtLineOLDJobtitleIDList) {
        set(PROPERTY_HRISETLINEOLDJOBTITLEIDLIST, hrisEtLineOLDJobtitleIDList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineNEWJobtitleIDList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINENEWJOBTITLEIDLIST);
    }

    public void setHrisEtLineNEWJobtitleIDList(List<EmployeeTransferLine> hrisEtLineNEWJobtitleIDList) {
        set(PROPERTY_HRISETLINENEWJOBTITLEIDLIST, hrisEtLineNEWJobtitleIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_exc_jobtitle> getHrisExcJobtitleList() {
      return (List<hris_exc_jobtitle>) get(PROPERTY_HRISEXCJOBTITLELIST);
    }

    public void setHrisExcJobtitleList(List<hris_exc_jobtitle> hrisExcJobtitleList) {
        set(PROPERTY_HRISEXCJOBTITLELIST, hrisExcJobtitleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ge_employee> getHrisGeEmployeeList() {
      return (List<hris_ge_employee>) get(PROPERTY_HRISGEEMPLOYEELIST);
    }

    public void setHrisGeEmployeeList(List<hris_ge_employee> hrisGeEmployeeList) {
        set(PROPERTY_HRISGEEMPLOYEELIST, hrisGeEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_i_employee_candidate> getHrisIEmployeeCandidateList() {
      return (List<hris_i_employee_candidate>) get(PROPERTY_HRISIEMPLOYEECANDIDATELIST);
    }

    public void setHrisIEmployeeCandidateList(List<hris_i_employee_candidate> hrisIEmployeeCandidateList) {
        set(PROPERTY_HRISIEMPLOYEECANDIDATELIST, hrisIEmployeeCandidateList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_jobtitlerule> getHrisJobtitleruleList() {
      return (List<hris_jobtitlerule>) get(PROPERTY_HRISJOBTITLERULELIST);
    }

    public void setHrisJobtitleruleList(List<hris_jobtitlerule> hrisJobtitleruleList) {
        set(PROPERTY_HRISJOBTITLERULELIST, hrisJobtitleruleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_jt_education> getHrisJtEducationList() {
      return (List<hris_jt_education>) get(PROPERTY_HRISJTEDUCATIONLIST);
    }

    public void setHrisJtEducationList(List<hris_jt_education> hrisJtEducationList) {
        set(PROPERTY_HRISJTEDUCATIONLIST, hrisJtEducationList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_jt_jobexperience> getHrisJtJobexperienceList() {
      return (List<hris_jt_jobexperience>) get(PROPERTY_HRISJTJOBEXPERIENCELIST);
    }

    public void setHrisJtJobexperienceList(List<hris_jt_jobexperience> hrisJtJobexperienceList) {
        set(PROPERTY_HRISJTJOBEXPERIENCELIST, hrisJtJobexperienceList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_jt_jobexperience> getHrisJtJobexperienceHrisJobexperienceIDList() {
      return (List<hris_jt_jobexperience>) get(PROPERTY_HRISJTJOBEXPERIENCEHRISJOBEXPERIENCEIDLIST);
    }

    public void setHrisJtJobexperienceHrisJobexperienceIDList(List<hris_jt_jobexperience> hrisJtJobexperienceHrisJobexperienceIDList) {
        set(PROPERTY_HRISJTJOBEXPERIENCEHRISJOBEXPERIENCEIDLIST, hrisJtJobexperienceHrisJobexperienceIDList);
    }

    @SuppressWarnings("unchecked")
    public List<KPIVersionJobTitle> getHrisJtKpiVersionList() {
      return (List<KPIVersionJobTitle>) get(PROPERTY_HRISJTKPIVERSIONLIST);
    }

    public void setHrisJtKpiVersionList(List<KPIVersionJobTitle> hrisJtKpiVersionList) {
        set(PROPERTY_HRISJTKPIVERSIONLIST, hrisJtKpiVersionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_jt_majorchallenge> getHrisJtMajorchallengeList() {
      return (List<hris_jt_majorchallenge>) get(PROPERTY_HRISJTMAJORCHALLENGELIST);
    }

    public void setHrisJtMajorchallengeList(List<hris_jt_majorchallenge> hrisJtMajorchallengeList) {
        set(PROPERTY_HRISJTMAJORCHALLENGELIST, hrisJtMajorchallengeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_pengundurandiri> getHrisPengundurandiriJobTitleList() {
      return (List<hris_pengundurandiri>) get(PROPERTY_HRISPENGUNDURANDIRIJOBTITLELIST);
    }

    public void setHrisPengundurandiriJobTitleList(List<hris_pengundurandiri> hrisPengundurandiriJobTitleList) {
        set(PROPERTY_HRISPENGUNDURANDIRIJOBTITLELIST, hrisPengundurandiriJobTitleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_r_line> getHrisRLineJobtitleList() {
      return (List<hris_r_line>) get(PROPERTY_HRISRLINEJOBTITLELIST);
    }

    public void setHrisRLineJobtitleList(List<hris_r_line> hrisRLineJobtitleList) {
        set(PROPERTY_HRISRLINEJOBTITLELIST, hrisRLineJobtitleList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_reimbursment> getHrisReimbursmentList() {
      return (List<hris_reimbursment>) get(PROPERTY_HRISREIMBURSMENTLIST);
    }

    public void setHrisReimbursmentList(List<hris_reimbursment> hrisReimbursmentList) {
        set(PROPERTY_HRISREIMBURSMENTLIST, hrisReimbursmentList);
    }

    @SuppressWarnings("unchecked")
    public List<ot_overtime> getOtOvertimeJobtitleList() {
      return (List<ot_overtime>) get(PROPERTY_OTOVERTIMEJOBTITLELIST);
    }

    public void setOtOvertimeJobtitleList(List<ot_overtime> otOvertimeJobtitleList) {
        set(PROPERTY_OTOVERTIMEJOBTITLELIST, otOvertimeJobtitleList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_sp_employee> getPyrSpEmployeeList() {
      return (List<pyr_sp_employee>) get(PROPERTY_PYRSPEMPLOYEELIST);
    }

    public void setPyrSpEmployeeList(List<pyr_sp_employee> pyrSpEmployeeList) {
        set(PROPERTY_PYRSPEMPLOYEELIST, pyrSpEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<tm_termination> getTmTerminationList() {
      return (List<tm_termination>) get(PROPERTY_TMTERMINATIONLIST);
    }

    public void setTmTerminationList(List<tm_termination> tmTerminationList) {
        set(PROPERTY_TMTERMINATIONLIST, tmTerminationList);
    }

}
