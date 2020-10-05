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
package org.wirabumi.gen.oez;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.Role;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.ad.ui.Window;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity oez_documentrouting (stored in table oez_documentrouting).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_documentrouting extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_documentrouting";
    public static final String ENTITY_NAME = "oez_documentrouting";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ROLE = "role";
    public static final String PROPERTY_WINDOW = "window";
    public static final String PROPERTY_TAB = "tab";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_MINAMOUNT = "minAmount";
    public static final String PROPERTY_MINOVERDUE = "minoverdue";
    public static final String PROPERTY_MAXAMOUNT = "maxAmount";
    public static final String PROPERTY_MAXOVERDUE = "maxoverdue";
    public static final String PROPERTY_DOCUMENTSTATUSFORORDER = "documentStatusForOrder";
    public static final String PROPERTY_OTMINIMUMOVERTIMEDURATION = "oTMinimumOvertimeDuration";
    public static final String PROPERTY_DOCUMENTACTIONFORORDER = "documentActionForOrder";
    public static final String PROPERTY_OTMAXIMUMOVERTIMEDURATION = "oTMaximumOvertimeDuration";

    public oez_documentrouting() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MINAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_MINOVERDUE, (long) 0);
        setDefaultValue(PROPERTY_MAXAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_MAXOVERDUE, (long) 0);
        setDefaultValue(PROPERTY_OTMINIMUMOVERTIMEDURATION, new BigDecimal(0));
        setDefaultValue(PROPERTY_OTMAXIMUMOVERTIMEDURATION, new BigDecimal(0));
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

    public Role getRole() {
        return (Role) get(PROPERTY_ROLE);
    }

    public void setRole(Role role) {
        set(PROPERTY_ROLE, role);
    }

    public Window getWindow() {
        return (Window) get(PROPERTY_WINDOW);
    }

    public void setWindow(Window window) {
        set(PROPERTY_WINDOW, window);
    }

    public Tab getTab() {
        return (Tab) get(PROPERTY_TAB);
    }

    public void setTab(Tab tab) {
        set(PROPERTY_TAB, tab);
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BigDecimal getMinAmount() {
        return (BigDecimal) get(PROPERTY_MINAMOUNT);
    }

    public void setMinAmount(BigDecimal minAmount) {
        set(PROPERTY_MINAMOUNT, minAmount);
    }

    public Long getMinoverdue() {
        return (Long) get(PROPERTY_MINOVERDUE);
    }

    public void setMinoverdue(Long minoverdue) {
        set(PROPERTY_MINOVERDUE, minoverdue);
    }

    public BigDecimal getMaxAmount() {
        return (BigDecimal) get(PROPERTY_MAXAMOUNT);
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        set(PROPERTY_MAXAMOUNT, maxAmount);
    }

    public Long getMaxoverdue() {
        return (Long) get(PROPERTY_MAXOVERDUE);
    }

    public void setMaxoverdue(Long maxoverdue) {
        set(PROPERTY_MAXOVERDUE, maxoverdue);
    }

    public String getDocumentStatusForOrder() {
        return (String) get(PROPERTY_DOCUMENTSTATUSFORORDER);
    }

    public void setDocumentStatusForOrder(String documentStatusForOrder) {
        set(PROPERTY_DOCUMENTSTATUSFORORDER, documentStatusForOrder);
    }

    public BigDecimal getOTMinimumOvertimeDuration() {
        return (BigDecimal) get(PROPERTY_OTMINIMUMOVERTIMEDURATION);
    }

    public void setOTMinimumOvertimeDuration(BigDecimal oTMinimumOvertimeDuration) {
        set(PROPERTY_OTMINIMUMOVERTIMEDURATION, oTMinimumOvertimeDuration);
    }

    public String getDocumentActionForOrder() {
        return (String) get(PROPERTY_DOCUMENTACTIONFORORDER);
    }

    public void setDocumentActionForOrder(String documentActionForOrder) {
        set(PROPERTY_DOCUMENTACTIONFORORDER, documentActionForOrder);
    }

    public BigDecimal getOTMaximumOvertimeDuration() {
        return (BigDecimal) get(PROPERTY_OTMAXIMUMOVERTIMEDURATION);
    }

    public void setOTMaximumOvertimeDuration(BigDecimal oTMaximumOvertimeDuration) {
        set(PROPERTY_OTMAXIMUMOVERTIMEDURATION, oTMaximumOvertimeDuration);
    }

}
