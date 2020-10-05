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

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.manufacturing.transaction.WorkRequirementOperation;
import org.openbravo.model.materialmgmt.transaction.ProductionPlan;
import org.openbravo.model.materialmgmt.transaction.ProductionTransaction;
/**
 * Entity class for entity oez_productionnew_v (stored in table oez_productionnew_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_productionnew_v extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_productionnew_v";
    public static final String ENTITY_NAME = "oez_productionnew_v";
    public static final String PROPERTY_PRODUCTIONPLAN = "productionplan";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_PRODUCTION = "production";
    public static final String PROPERTY_WRPHASE = "wrphase";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_PRODUCTIONPLANDATE = "productionplandate";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PRODUCTCODE = "productCode";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DOCUMENTNO = "documentno";
    public static final String PROPERTY_STARTDATE = "startdate";
    public static final String PROPERTY_ENDDATE = "enddate";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_MOVEMENTQTY = "movementqty";
    public static final String PROPERTY_PRODUCTIONTYPE = "productiontype";
    public static final String PROPERTY_NEEDEDQUANTITY = "neededquantity";
    public static final String PROPERTY_REJECTEDQUANTITY = "rejectedquantity";
    public static final String PROPERTY_PRODUCTIONQTY = "productionqty";
    public static final String PROPERTY_SECONDARYQTY = "secondaryqty";
    public static final String PROPERTY_VALUE = "value";
    public static final String PROPERTY_OEZLOTNO = "oezLotno";
    public static final String PROPERTY_OEZNOTE = "oezNote";

    public oez_productionnew_v() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public ProductionPlan getProductionplan() {
        return (ProductionPlan) get(PROPERTY_PRODUCTIONPLAN);
    }

    public void setProductionplan(ProductionPlan productionplan) {
        set(PROPERTY_PRODUCTIONPLAN, productionplan);
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public ProductionTransaction getProduction() {
        return (ProductionTransaction) get(PROPERTY_PRODUCTION);
    }

    public void setProduction(ProductionTransaction production) {
        set(PROPERTY_PRODUCTION, production);
    }

    public WorkRequirementOperation getWrphase() {
        return (WorkRequirementOperation) get(PROPERTY_WRPHASE);
    }

    public void setWrphase(WorkRequirementOperation wrphase) {
        set(PROPERTY_WRPHASE, wrphase);
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Date getProductionplandate() {
        return (Date) get(PROPERTY_PRODUCTIONPLANDATE);
    }

    public void setProductionplandate(Date productionplandate) {
        set(PROPERTY_PRODUCTIONPLANDATE, productionplandate);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getProductCode() {
        return (String) get(PROPERTY_PRODUCTCODE);
    }

    public void setProductCode(String productCode) {
        set(PROPERTY_PRODUCTCODE, productCode);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDocumentno() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentno(String documentno) {
        set(PROPERTY_DOCUMENTNO, documentno);
    }

    public Date getStartdate() {
        return (Date) get(PROPERTY_STARTDATE);
    }

    public void setStartdate(Date startdate) {
        set(PROPERTY_STARTDATE, startdate);
    }

    public Date getEnddate() {
        return (Date) get(PROPERTY_ENDDATE);
    }

    public void setEnddate(Date enddate) {
        set(PROPERTY_ENDDATE, enddate);
    }

    public Long getQuantity() {
        return (Long) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(Long quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public Long getMovementqty() {
        return (Long) get(PROPERTY_MOVEMENTQTY);
    }

    public void setMovementqty(Long movementqty) {
        set(PROPERTY_MOVEMENTQTY, movementqty);
    }

    public String getProductiontype() {
        return (String) get(PROPERTY_PRODUCTIONTYPE);
    }

    public void setProductiontype(String productiontype) {
        set(PROPERTY_PRODUCTIONTYPE, productiontype);
    }

    public Long getNeededquantity() {
        return (Long) get(PROPERTY_NEEDEDQUANTITY);
    }

    public void setNeededquantity(Long neededquantity) {
        set(PROPERTY_NEEDEDQUANTITY, neededquantity);
    }

    public Long getRejectedquantity() {
        return (Long) get(PROPERTY_REJECTEDQUANTITY);
    }

    public void setRejectedquantity(Long rejectedquantity) {
        set(PROPERTY_REJECTEDQUANTITY, rejectedquantity);
    }

    public Long getProductionqty() {
        return (Long) get(PROPERTY_PRODUCTIONQTY);
    }

    public void setProductionqty(Long productionqty) {
        set(PROPERTY_PRODUCTIONQTY, productionqty);
    }

    public Long getSecondaryqty() {
        return (Long) get(PROPERTY_SECONDARYQTY);
    }

    public void setSecondaryqty(Long secondaryqty) {
        set(PROPERTY_SECONDARYQTY, secondaryqty);
    }

    public String getValue() {
        return (String) get(PROPERTY_VALUE);
    }

    public void setValue(String value) {
        set(PROPERTY_VALUE, value);
    }

    public String getOezLotno() {
        return (String) get(PROPERTY_OEZLOTNO);
    }

    public void setOezLotno(String oezLotno) {
        set(PROPERTY_OEZLOTNO, oezLotno);
    }

    public String getOezNote() {
        return (String) get(PROPERTY_OEZNOTE);
    }

    public void setOezNote(String oezNote) {
        set(PROPERTY_OEZNOTE, oezNote);
    }

}
