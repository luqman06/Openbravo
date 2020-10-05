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
package org.wirabumi.hris.payroll;

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
import org.openbravo.model.common.enterprise.Organization;
import org.wirabumi.hris.employee.master.data.hris_site;
import org.wirabumi.hris.termination.tm_set_phk_detail;
/**
 * Entity class for entity pyr_earning_param (stored in table pyr_earning_param).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class pyr_earning_param extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "pyr_earning_param";
    public static final String ENTITY_NAME = "pyr_earning_param";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ECHELON = "echelon";
    public static final String PROPERTY_POSITION = "position";
    public static final String PROPERTY_PARAMNAME = "paramName";
    public static final String PROPERTY_PARAMAMOUNT = "paramAmount";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_NOTE = "note";
    public static final String PROPERTY_MAXIMUMRANGE = "maximumRange";
    public static final String PROPERTY_MINIMUMRANGE = "minimumRange";
    public static final String PROPERTY_LEVEL = "level";
    public static final String PROPERTY_HRISSITE = "hrisSite";
    public static final String PROPERTY_VALIDFROMDATE = "validFromDate";
    public static final String PROPERTY_VALIDTODATE = "validToDate";
    public static final String PROPERTY_TMISTERMINATION = "tmIstermination";
    public static final String PROPERTY_HRISBENEFITSTYPE = "hrisBenefitsType";
    public static final String PROPERTY_TMSETPHKDETAILEMPYREARNINGPARAMIDLIST = "tmSetPhkDetailEMPyrEarningParamIDList";

    public pyr_earning_param() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TMISTERMINATION, false);
        setDefaultValue(PROPERTY_TMSETPHKDETAILEMPYREARNINGPARAMIDLIST, new ArrayList<Object>());
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

    public String getEchelon() {
        return (String) get(PROPERTY_ECHELON);
    }

    public void setEchelon(String echelon) {
        set(PROPERTY_ECHELON, echelon);
    }

    public String getPosition() {
        return (String) get(PROPERTY_POSITION);
    }

    public void setPosition(String position) {
        set(PROPERTY_POSITION, position);
    }

    public String getParamName() {
        return (String) get(PROPERTY_PARAMNAME);
    }

    public void setParamName(String paramName) {
        set(PROPERTY_PARAMNAME, paramName);
    }

    public BigDecimal getParamAmount() {
        return (BigDecimal) get(PROPERTY_PARAMAMOUNT);
    }

    public void setParamAmount(BigDecimal paramAmount) {
        set(PROPERTY_PARAMAMOUNT, paramAmount);
    }

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getNote() {
        return (String) get(PROPERTY_NOTE);
    }

    public void setNote(String note) {
        set(PROPERTY_NOTE, note);
    }

    public BigDecimal getMaximumRange() {
        return (BigDecimal) get(PROPERTY_MAXIMUMRANGE);
    }

    public void setMaximumRange(BigDecimal maximumRange) {
        set(PROPERTY_MAXIMUMRANGE, maximumRange);
    }

    public BigDecimal getMinimumRange() {
        return (BigDecimal) get(PROPERTY_MINIMUMRANGE);
    }

    public void setMinimumRange(BigDecimal minimumRange) {
        set(PROPERTY_MINIMUMRANGE, minimumRange);
    }

    public String getLevel() {
        return (String) get(PROPERTY_LEVEL);
    }

    public void setLevel(String level) {
        set(PROPERTY_LEVEL, level);
    }

    public hris_site getHrisSite() {
        return (hris_site) get(PROPERTY_HRISSITE);
    }

    public void setHrisSite(hris_site hrisSite) {
        set(PROPERTY_HRISSITE, hrisSite);
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

    public Boolean isTmIstermination() {
        return (Boolean) get(PROPERTY_TMISTERMINATION);
    }

    public void setTmIstermination(Boolean tmIstermination) {
        set(PROPERTY_TMISTERMINATION, tmIstermination);
    }

    public String getHrisBenefitsType() {
        return (String) get(PROPERTY_HRISBENEFITSTYPE);
    }

    public void setHrisBenefitsType(String hrisBenefitsType) {
        set(PROPERTY_HRISBENEFITSTYPE, hrisBenefitsType);
    }

    @SuppressWarnings("unchecked")
    public List<tm_set_phk_detail> getTmSetPhkDetailEMPyrEarningParamIDList() {
      return (List<tm_set_phk_detail>) get(PROPERTY_TMSETPHKDETAILEMPYREARNINGPARAMIDLIST);
    }

    public void setTmSetPhkDetailEMPyrEarningParamIDList(List<tm_set_phk_detail> tmSetPhkDetailEMPyrEarningParamIDList) {
        set(PROPERTY_TMSETPHKDETAILEMPYREARNINGPARAMIDLIST, tmSetPhkDetailEMPyrEarningParamIDList);
    }

}
