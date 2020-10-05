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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity oez_prod_octopus (stored in table oez_prod_octopus).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_prod_octopus extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_prod_octopus";
    public static final String ENTITY_NAME = "oez_prod_octopus";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_OEZRAWTYPE = "oezRawType";
    public static final String PROPERTY_BPARTNER = "bpartner";
    public static final String PROPERTY_SUPLIERNAME = "supliername";
    public static final String PROPERTY_PRODUCTTYPE = "producttype";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_PRODUCTIONDATE = "productiondate";
    public static final String PROPERTY_QTYRM = "qtyrm";
    public static final String PROPERTY_QTYDEFROST = "qtydefrost";
    public static final String PROPERTY_QTYPROCESS = "qtyprocess";
    public static final String PROPERTY_QTYGUTTING = "qtygutting";
    public static final String PROPERTY_QTYRENDGUTTING = "qtyrendgutting";
    public static final String PROPERTY_QTYCUTTINGRH = "qtycuttingrh";
    public static final String PROPERTY_QTYCUTTINGRL = "qtycuttingrl";
    public static final String PROPERTY_QTYCUTTINGRN = "qtycuttingrn";
    public static final String PROPERTY_QTYSOAKINGHEAD = "qtysoakinghead";
    public static final String PROPERTY_QTYSOAKINGLEG = "qtysoakingleg";
    public static final String PROPERTY_QTYBOILINGHEAD = "qtyboilinghead";
    public static final String PROPERTY_QTYBOELINGLEG = "qtyboelingleg";
    public static final String PROPERTY_QTYCUTTINGCH = "qtycuttingch";
    public static final String PROPERTY_QTYCUTTINGCL = "qtycuttingcl";
    public static final String PROPERTY_QTYBSHEAD = "qtybshead";
    public static final String PROPERTY_QTYBSLEG = "qtybsleg";
    public static final String PROPERTY_QTYFG = "qtyfg";
    public static final String PROPERTY_QTYBLOCKEDMENTAH = "qtyblockedmentah";
    public static final String PROPERTY_QTYBLOCKEDHITAM = "qtyblockedhitam";
    public static final String PROPERTY_QTYBLOCKEDBAU = "qtyblockedbau";
    public static final String PROPERTY_PRODUCT = "product";

    public oez_prod_octopus() {
        setDefaultValue(PROPERTY_ACTIVE, true);
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
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

    public String getOezRawType() {
        return (String) get(PROPERTY_OEZRAWTYPE);
    }

    public void setOezRawType(String oezRawType) {
        set(PROPERTY_OEZRAWTYPE, oezRawType);
    }

    public BusinessPartner getBpartner() {
        return (BusinessPartner) get(PROPERTY_BPARTNER);
    }

    public void setBpartner(BusinessPartner bpartner) {
        set(PROPERTY_BPARTNER, bpartner);
    }

    public String getSupliername() {
        return (String) get(PROPERTY_SUPLIERNAME);
    }

    public void setSupliername(String supliername) {
        set(PROPERTY_SUPLIERNAME, supliername);
    }

    public String getProducttype() {
        return (String) get(PROPERTY_PRODUCTTYPE);
    }

    public void setProducttype(String producttype) {
        set(PROPERTY_PRODUCTTYPE, producttype);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public Date getProductiondate() {
        return (Date) get(PROPERTY_PRODUCTIONDATE);
    }

    public void setProductiondate(Date productiondate) {
        set(PROPERTY_PRODUCTIONDATE, productiondate);
    }

    public Long getQtyrm() {
        return (Long) get(PROPERTY_QTYRM);
    }

    public void setQtyrm(Long qtyrm) {
        set(PROPERTY_QTYRM, qtyrm);
    }

    public Long getQtydefrost() {
        return (Long) get(PROPERTY_QTYDEFROST);
    }

    public void setQtydefrost(Long qtydefrost) {
        set(PROPERTY_QTYDEFROST, qtydefrost);
    }

    public Long getQtyprocess() {
        return (Long) get(PROPERTY_QTYPROCESS);
    }

    public void setQtyprocess(Long qtyprocess) {
        set(PROPERTY_QTYPROCESS, qtyprocess);
    }

    public Long getQtygutting() {
        return (Long) get(PROPERTY_QTYGUTTING);
    }

    public void setQtygutting(Long qtygutting) {
        set(PROPERTY_QTYGUTTING, qtygutting);
    }

    public Long getQtyrendgutting() {
        return (Long) get(PROPERTY_QTYRENDGUTTING);
    }

    public void setQtyrendgutting(Long qtyrendgutting) {
        set(PROPERTY_QTYRENDGUTTING, qtyrendgutting);
    }

    public Long getQtycuttingrh() {
        return (Long) get(PROPERTY_QTYCUTTINGRH);
    }

    public void setQtycuttingrh(Long qtycuttingrh) {
        set(PROPERTY_QTYCUTTINGRH, qtycuttingrh);
    }

    public Long getQtycuttingrl() {
        return (Long) get(PROPERTY_QTYCUTTINGRL);
    }

    public void setQtycuttingrl(Long qtycuttingrl) {
        set(PROPERTY_QTYCUTTINGRL, qtycuttingrl);
    }

    public Long getQtycuttingrn() {
        return (Long) get(PROPERTY_QTYCUTTINGRN);
    }

    public void setQtycuttingrn(Long qtycuttingrn) {
        set(PROPERTY_QTYCUTTINGRN, qtycuttingrn);
    }

    public Long getQtysoakinghead() {
        return (Long) get(PROPERTY_QTYSOAKINGHEAD);
    }

    public void setQtysoakinghead(Long qtysoakinghead) {
        set(PROPERTY_QTYSOAKINGHEAD, qtysoakinghead);
    }

    public Long getQtysoakingleg() {
        return (Long) get(PROPERTY_QTYSOAKINGLEG);
    }

    public void setQtysoakingleg(Long qtysoakingleg) {
        set(PROPERTY_QTYSOAKINGLEG, qtysoakingleg);
    }

    public Long getQtyboilinghead() {
        return (Long) get(PROPERTY_QTYBOILINGHEAD);
    }

    public void setQtyboilinghead(Long qtyboilinghead) {
        set(PROPERTY_QTYBOILINGHEAD, qtyboilinghead);
    }

    public Long getQtyboelingleg() {
        return (Long) get(PROPERTY_QTYBOELINGLEG);
    }

    public void setQtyboelingleg(Long qtyboelingleg) {
        set(PROPERTY_QTYBOELINGLEG, qtyboelingleg);
    }

    public Long getQtycuttingch() {
        return (Long) get(PROPERTY_QTYCUTTINGCH);
    }

    public void setQtycuttingch(Long qtycuttingch) {
        set(PROPERTY_QTYCUTTINGCH, qtycuttingch);
    }

    public Long getQtycuttingcl() {
        return (Long) get(PROPERTY_QTYCUTTINGCL);
    }

    public void setQtycuttingcl(Long qtycuttingcl) {
        set(PROPERTY_QTYCUTTINGCL, qtycuttingcl);
    }

    public Long getQtybshead() {
        return (Long) get(PROPERTY_QTYBSHEAD);
    }

    public void setQtybshead(Long qtybshead) {
        set(PROPERTY_QTYBSHEAD, qtybshead);
    }

    public Long getQtybsleg() {
        return (Long) get(PROPERTY_QTYBSLEG);
    }

    public void setQtybsleg(Long qtybsleg) {
        set(PROPERTY_QTYBSLEG, qtybsleg);
    }

    public Long getQtyfg() {
        return (Long) get(PROPERTY_QTYFG);
    }

    public void setQtyfg(Long qtyfg) {
        set(PROPERTY_QTYFG, qtyfg);
    }

    public Long getQtyblockedmentah() {
        return (Long) get(PROPERTY_QTYBLOCKEDMENTAH);
    }

    public void setQtyblockedmentah(Long qtyblockedmentah) {
        set(PROPERTY_QTYBLOCKEDMENTAH, qtyblockedmentah);
    }

    public Long getQtyblockedhitam() {
        return (Long) get(PROPERTY_QTYBLOCKEDHITAM);
    }

    public void setQtyblockedhitam(Long qtyblockedhitam) {
        set(PROPERTY_QTYBLOCKEDHITAM, qtyblockedhitam);
    }

    public Long getQtyblockedbau() {
        return (Long) get(PROPERTY_QTYBLOCKEDBAU);
    }

    public void setQtyblockedbau(Long qtyblockedbau) {
        set(PROPERTY_QTYBLOCKEDBAU, qtyblockedbau);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

}
