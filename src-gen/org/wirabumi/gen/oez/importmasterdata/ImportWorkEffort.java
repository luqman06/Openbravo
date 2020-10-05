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
package org.wirabumi.gen.oez.importmasterdata;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.manufacturing.cost.CostcenterVersion;
import org.openbravo.model.manufacturing.transaction.WorkRequirementOperation;
import org.openbravo.model.materialmgmt.transaction.ProductionLine;
import org.openbravo.model.materialmgmt.transaction.ProductionPlan;
import org.openbravo.model.materialmgmt.transaction.ProductionTransaction;
/**
 * Entity class for entity OEZ_I_Workeffort (stored in table OEZ_I_Workeffort).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ImportWorkEffort extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OEZ_I_Workeffort";
    public static final String ENTITY_NAME = "OEZ_I_Workeffort";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ISIMPORTED = "isimported";
    public static final String PROPERTY_MOVEMENTDATE = "movementdate";
    public static final String PROPERTY_STARTINGHOUR = "startinghour";
    public static final String PROPERTY_ENDINGHOUR = "endinghour";
    public static final String PROPERTY_DOCUMENTNO = "documentno";
    public static final String PROPERTY_PRODUCTION = "production";
    public static final String PROPERTY_WRDOCNO = "wrdocno";
    public static final String PROPERTY_WRSEQNO = "wrseqno";
    public static final String PROPERTY_COMPLETEQTY = "completeqty";
    public static final String PROPERTY_REJECTEDQTY = "rejectedqty";
    public static final String PROPERTY_COSTCENTERKEY = "costcenterkey";
    public static final String PROPERTY_COSTCENTERUSE = "costcenteruse";
    public static final String PROPERTY_COSTCENTERVERSION = "costcenterVersion";
    public static final String PROPERTY_OURSOURCED = "oursourced";
    public static final String PROPERTY_CLOSEPHASE = "closephase";
    public static final String PROPERTY_PRODUCTIONPLAN = "productionplan";
    public static final String PROPERTY_PRODUCTKEY = "productkey";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_PRODUCTIONTYPE = "productiontype";
    public static final String PROPERTY_STORAGEKEY = "storagekey";
    public static final String PROPERTY_LOCATOR = "locator";
    public static final String PROPERTY_PRODUCTIONLINE = "productionline";
    public static final String PROPERTY_IMPORTWORKEFFORT = "importworkeffort";
    public static final String PROPERTY_WRPHASE = "wrphase";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_PRODUCTREJECTQTY = "productRejectQty";

    public ImportWorkEffort() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISIMPORTED, false);
        setDefaultValue(PROPERTY_OURSOURCED, false);
        setDefaultValue(PROPERTY_CLOSEPHASE, false);
        setDefaultValue(PROPERTY_IMPORTWORKEFFORT, false);
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

    public Boolean isImported() {
        return (Boolean) get(PROPERTY_ISIMPORTED);
    }

    public void setImported(Boolean isimported) {
        set(PROPERTY_ISIMPORTED, isimported);
    }

    public Date getMovementdate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementdate(Date movementdate) {
        set(PROPERTY_MOVEMENTDATE, movementdate);
    }

    public Long getStartinghour() {
        return (Long) get(PROPERTY_STARTINGHOUR);
    }

    public void setStartinghour(Long startinghour) {
        set(PROPERTY_STARTINGHOUR, startinghour);
    }

    public Long getEndinghour() {
        return (Long) get(PROPERTY_ENDINGHOUR);
    }

    public void setEndinghour(Long endinghour) {
        set(PROPERTY_ENDINGHOUR, endinghour);
    }

    public String getDocumentno() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentno(String documentno) {
        set(PROPERTY_DOCUMENTNO, documentno);
    }

    public ProductionTransaction getProduction() {
        return (ProductionTransaction) get(PROPERTY_PRODUCTION);
    }

    public void setProduction(ProductionTransaction production) {
        set(PROPERTY_PRODUCTION, production);
    }

    public String getWrdocno() {
        return (String) get(PROPERTY_WRDOCNO);
    }

    public void setWrdocno(String wrdocno) {
        set(PROPERTY_WRDOCNO, wrdocno);
    }

    public Long getWrseqno() {
        return (Long) get(PROPERTY_WRSEQNO);
    }

    public void setWrseqno(Long wrseqno) {
        set(PROPERTY_WRSEQNO, wrseqno);
    }

    public BigDecimal getCompleteqty() {
        return (BigDecimal) get(PROPERTY_COMPLETEQTY);
    }

    public void setCompleteqty(BigDecimal completeqty) {
        set(PROPERTY_COMPLETEQTY, completeqty);
    }

    public BigDecimal getRejectedqty() {
        return (BigDecimal) get(PROPERTY_REJECTEDQTY);
    }

    public void setRejectedqty(BigDecimal rejectedqty) {
        set(PROPERTY_REJECTEDQTY, rejectedqty);
    }

    public String getCostcenterkey() {
        return (String) get(PROPERTY_COSTCENTERKEY);
    }

    public void setCostcenterkey(String costcenterkey) {
        set(PROPERTY_COSTCENTERKEY, costcenterkey);
    }

    public BigDecimal getCostcenteruse() {
        return (BigDecimal) get(PROPERTY_COSTCENTERUSE);
    }

    public void setCostcenteruse(BigDecimal costcenteruse) {
        set(PROPERTY_COSTCENTERUSE, costcenteruse);
    }

    public CostcenterVersion getCostcenterVersion() {
        return (CostcenterVersion) get(PROPERTY_COSTCENTERVERSION);
    }

    public void setCostcenterVersion(CostcenterVersion costcenterVersion) {
        set(PROPERTY_COSTCENTERVERSION, costcenterVersion);
    }

    public Boolean isOursourced() {
        return (Boolean) get(PROPERTY_OURSOURCED);
    }

    public void setOursourced(Boolean oursourced) {
        set(PROPERTY_OURSOURCED, oursourced);
    }

    public Boolean isClosephase() {
        return (Boolean) get(PROPERTY_CLOSEPHASE);
    }

    public void setClosephase(Boolean closephase) {
        set(PROPERTY_CLOSEPHASE, closephase);
    }

    public ProductionPlan getProductionplan() {
        return (ProductionPlan) get(PROPERTY_PRODUCTIONPLAN);
    }

    public void setProductionplan(ProductionPlan productionplan) {
        set(PROPERTY_PRODUCTIONPLAN, productionplan);
    }

    public String getProductkey() {
        return (String) get(PROPERTY_PRODUCTKEY);
    }

    public void setProductkey(String productkey) {
        set(PROPERTY_PRODUCTKEY, productkey);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getProductiontype() {
        return (String) get(PROPERTY_PRODUCTIONTYPE);
    }

    public void setProductiontype(String productiontype) {
        set(PROPERTY_PRODUCTIONTYPE, productiontype);
    }

    public String getStoragekey() {
        return (String) get(PROPERTY_STORAGEKEY);
    }

    public void setStoragekey(String storagekey) {
        set(PROPERTY_STORAGEKEY, storagekey);
    }

    public Locator getLocator() {
        return (Locator) get(PROPERTY_LOCATOR);
    }

    public void setLocator(Locator locator) {
        set(PROPERTY_LOCATOR, locator);
    }

    public ProductionLine getProductionline() {
        return (ProductionLine) get(PROPERTY_PRODUCTIONLINE);
    }

    public void setProductionline(ProductionLine productionline) {
        set(PROPERTY_PRODUCTIONLINE, productionline);
    }

    public Boolean isImportworkeffort() {
        return (Boolean) get(PROPERTY_IMPORTWORKEFFORT);
    }

    public void setImportworkeffort(Boolean importworkeffort) {
        set(PROPERTY_IMPORTWORKEFFORT, importworkeffort);
    }

    public WorkRequirementOperation getWrphase() {
        return (WorkRequirementOperation) get(PROPERTY_WRPHASE);
    }

    public void setWrphase(WorkRequirementOperation wrphase) {
        set(PROPERTY_WRPHASE, wrphase);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
    }

    public BigDecimal getProductRejectQty() {
        return (BigDecimal) get(PROPERTY_PRODUCTREJECTQTY);
    }

    public void setProductRejectQty(BigDecimal productRejectQty) {
        set(PROPERTY_PRODUCTREJECTQTY, productRejectQty);
    }

}
