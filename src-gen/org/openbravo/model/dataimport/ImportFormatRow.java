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
/**
 * Entity class for entity DataImportFormatRow (stored in table AD_ImpFormat_Row).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ImportFormatRow extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_ImpFormat_Row";
    public static final String ENTITY_NAME = "DataImportFormatRow";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_IMPORTFORMAT = "importFormat";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_COLUMN = "column";
    public static final String PROPERTY_STARTINGNO = "startingNo";
    public static final String PROPERTY_ENDNO = "endNo";
    public static final String PROPERTY_DATATYPE = "dataType";
    public static final String PROPERTY_DATAFORMAT = "dataFormat";
    public static final String PROPERTY_DECIMALPOINT = "decimalPoint";
    public static final String PROPERTY_DIVIDEBY100 = "divideBy100";
    public static final String PROPERTY_CONSTANTVALUE = "constantValue";
    public static final String PROPERTY_CALLOUTFUNCTION = "calloutFunction";
    public static final String PROPERTY_SCRIPT = "script";

    public ImportFormatRow() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DECIMALPOINT, ".");
        setDefaultValue(PROPERTY_DIVIDEBY100, false);
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

    public ImportFormat getImportFormat() {
        return (ImportFormat) get(PROPERTY_IMPORTFORMAT);
    }

    public void setImportFormat(ImportFormat importFormat) {
        set(PROPERTY_IMPORTFORMAT, importFormat);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public Column getColumn() {
        return (Column) get(PROPERTY_COLUMN);
    }

    public void setColumn(Column column) {
        set(PROPERTY_COLUMN, column);
    }

    public Long getStartingNo() {
        return (Long) get(PROPERTY_STARTINGNO);
    }

    public void setStartingNo(Long startingNo) {
        set(PROPERTY_STARTINGNO, startingNo);
    }

    public Long getEndNo() {
        return (Long) get(PROPERTY_ENDNO);
    }

    public void setEndNo(Long endNo) {
        set(PROPERTY_ENDNO, endNo);
    }

    public String getDataType() {
        return (String) get(PROPERTY_DATATYPE);
    }

    public void setDataType(String dataType) {
        set(PROPERTY_DATATYPE, dataType);
    }

    public String getDataFormat() {
        return (String) get(PROPERTY_DATAFORMAT);
    }

    public void setDataFormat(String dataFormat) {
        set(PROPERTY_DATAFORMAT, dataFormat);
    }

    public String getDecimalPoint() {
        return (String) get(PROPERTY_DECIMALPOINT);
    }

    public void setDecimalPoint(String decimalPoint) {
        set(PROPERTY_DECIMALPOINT, decimalPoint);
    }

    public Boolean isDivideBy100() {
        return (Boolean) get(PROPERTY_DIVIDEBY100);
    }

    public void setDivideBy100(Boolean divideBy100) {
        set(PROPERTY_DIVIDEBY100, divideBy100);
    }

    public String getConstantValue() {
        return (String) get(PROPERTY_CONSTANTVALUE);
    }

    public void setConstantValue(String constantValue) {
        set(PROPERTY_CONSTANTVALUE, constantValue);
    }

    public String getCalloutFunction() {
        return (String) get(PROPERTY_CALLOUTFUNCTION);
    }

    public void setCalloutFunction(String calloutFunction) {
        set(PROPERTY_CALLOUTFUNCTION, calloutFunction);
    }

    public String getScript() {
        return (String) get(PROPERTY_SCRIPT);
    }

    public void setScript(String script) {
        set(PROPERTY_SCRIPT, script);
    }

}
