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
package org.openbravo.model.dataimport;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.coa.Element;
/**
 * Entity class for entity DataImportElementValue (stored in table I_ElementValue).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ElementValue extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_ElementValue";
    public static final String ENTITY_NAME = "DataImportElementValue";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACCOUNTINGELEMENT = "accountingElement";
    public static final String PROPERTY_ELEMENTNAME = "elementName";
    public static final String PROPERTY_ACCOUNTELEMENT = "accountElement";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_ACCOUNTTYPE = "accountType";
    public static final String PROPERTY_ACCOUNTSIGN = "accountSign";
    public static final String PROPERTY_DOCUMENTCONTROLLED = "documentControlled";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_PARENTKEY = "parentKey";
    public static final String PROPERTY_PARENTACCOUNT = "parentAccount";
    public static final String PROPERTY_POSTACTUAL = "postActual";
    public static final String PROPERTY_POSTBUDGET = "postBudget";
    public static final String PROPERTY_POSTSTATISTICAL = "postStatistical";
    public static final String PROPERTY_POSTENCUMBRANCE = "postEncumbrance";
    public static final String PROPERTY_DEFAULTACCOUNT = "defaultAccount";
    public static final String PROPERTY_COLUMN = "column";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_ELEMENTLEVEL = "elementLevel";
    public static final String PROPERTY_OPERANDS = "operands";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";

    public ElementValue() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTCONTROLLED, false);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_POSTACTUAL, false);
        setDefaultValue(PROPERTY_POSTBUDGET, false);
        setDefaultValue(PROPERTY_POSTSTATISTICAL, false);
        setDefaultValue(PROPERTY_POSTENCUMBRANCE, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
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

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
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

    public Element getAccountingElement() {
        return (Element) get(PROPERTY_ACCOUNTINGELEMENT);
    }

    public void setAccountingElement(Element accountingElement) {
        set(PROPERTY_ACCOUNTINGELEMENT, accountingElement);
    }

    public String getElementName() {
        return (String) get(PROPERTY_ELEMENTNAME);
    }

    public void setElementName(String elementName) {
        set(PROPERTY_ELEMENTNAME, elementName);
    }

    public org.openbravo.model.financialmgmt.accounting.coa.ElementValue getAccountElement() {
        return (org.openbravo.model.financialmgmt.accounting.coa.ElementValue) get(PROPERTY_ACCOUNTELEMENT);
    }

    public void setAccountElement(org.openbravo.model.financialmgmt.accounting.coa.ElementValue accountElement) {
        set(PROPERTY_ACCOUNTELEMENT, accountElement);
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

    public String getAccountType() {
        return (String) get(PROPERTY_ACCOUNTTYPE);
    }

    public void setAccountType(String accountType) {
        set(PROPERTY_ACCOUNTTYPE, accountType);
    }

    public String getAccountSign() {
        return (String) get(PROPERTY_ACCOUNTSIGN);
    }

    public void setAccountSign(String accountSign) {
        set(PROPERTY_ACCOUNTSIGN, accountSign);
    }

    public Boolean isDocumentControlled() {
        return (Boolean) get(PROPERTY_DOCUMENTCONTROLLED);
    }

    public void setDocumentControlled(Boolean documentControlled) {
        set(PROPERTY_DOCUMENTCONTROLLED, documentControlled);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public String getParentKey() {
        return (String) get(PROPERTY_PARENTKEY);
    }

    public void setParentKey(String parentKey) {
        set(PROPERTY_PARENTKEY, parentKey);
    }

    public org.openbravo.model.financialmgmt.accounting.coa.ElementValue getParentAccount() {
        return (org.openbravo.model.financialmgmt.accounting.coa.ElementValue) get(PROPERTY_PARENTACCOUNT);
    }

    public void setParentAccount(org.openbravo.model.financialmgmt.accounting.coa.ElementValue parentAccount) {
        set(PROPERTY_PARENTACCOUNT, parentAccount);
    }

    public Boolean isPostActual() {
        return (Boolean) get(PROPERTY_POSTACTUAL);
    }

    public void setPostActual(Boolean postActual) {
        set(PROPERTY_POSTACTUAL, postActual);
    }

    public Boolean isPostBudget() {
        return (Boolean) get(PROPERTY_POSTBUDGET);
    }

    public void setPostBudget(Boolean postBudget) {
        set(PROPERTY_POSTBUDGET, postBudget);
    }

    public Boolean isPostStatistical() {
        return (Boolean) get(PROPERTY_POSTSTATISTICAL);
    }

    public void setPostStatistical(Boolean postStatistical) {
        set(PROPERTY_POSTSTATISTICAL, postStatistical);
    }

    public Boolean isPostEncumbrance() {
        return (Boolean) get(PROPERTY_POSTENCUMBRANCE);
    }

    public void setPostEncumbrance(Boolean postEncumbrance) {
        set(PROPERTY_POSTENCUMBRANCE, postEncumbrance);
    }

    public String getDefaultAccount() {
        return (String) get(PROPERTY_DEFAULTACCOUNT);
    }

    public void setDefaultAccount(String defaultAccount) {
        set(PROPERTY_DEFAULTACCOUNT, defaultAccount);
    }

    public Column getColumn() {
        return (Column) get(PROPERTY_COLUMN);
    }

    public void setColumn(Column column) {
        set(PROPERTY_COLUMN, column);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
    }

    public Boolean isImportProcessComplete() {
        return (Boolean) get(PROPERTY_IMPORTPROCESSCOMPLETE);
    }

    public void setImportProcessComplete(Boolean importProcessComplete) {
        set(PROPERTY_IMPORTPROCESSCOMPLETE, importProcessComplete);
    }

    public String getElementLevel() {
        return (String) get(PROPERTY_ELEMENTLEVEL);
    }

    public void setElementLevel(String elementLevel) {
        set(PROPERTY_ELEMENTLEVEL, elementLevel);
    }

    public String getOperands() {
        return (String) get(PROPERTY_OPERANDS);
    }

    public void setOperands(String operands) {
        set(PROPERTY_OPERANDS, operands);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

}
