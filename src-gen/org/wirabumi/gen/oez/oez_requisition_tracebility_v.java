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
/**
 * Entity class for entity oez_requisition_tracebility_v (stored in table oez_requisition_tracebility_v).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_requisition_tracebility_v extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_requisition_tracebility_v";
    public static final String ENTITY_NAME = "oez_requisition_tracebility_v";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_REQUESTOR = "requestor";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_QTYPR = "qtypr";
    public static final String PROPERTY_STATUSPR = "statuspr";
    public static final String PROPERTY_LISTPRICE = "listPrice";
    public static final String PROPERTY_QTYORDER = "qtyorder";
    public static final String PROPERTY_BPCODE = "bpcode";
    public static final String PROPERTY_BPNAME = "bpname";
    public static final String PROPERTY_ORDERPRICELIST = "orderpricelist";
    public static final String PROPERTY_ORDERPIRCEACTUAL = "orderpirceactual";
    public static final String PROPERTY_ORDERLINENETAMT = "orderlinenetamt";
    public static final String PROPERTY_PENDINGPR = "pendingpr";
    public static final String PROPERTY_PURCHASEORDERNO = "purchaseorderno";
    public static final String PROPERTY_MRPMOQ = "mRPMoq";
    public static final String PROPERTY_MRPORDERPACK = "mRPOrderpack";
    public static final String PROPERTY_MRPCOSPERORDER = "mRPCosperorder";
    public static final String PROPERTY_MRPQTYSTD = "mRPQtystd";
    public static final String PROPERTY_DOCUMENGR = "documengr";
    public static final String PROPERTY_QTYGR = "qtygr";
    public static final String PROPERTY_PENDINGGR = "pendinggr";
    public static final String PROPERTY_NAMAGUDANG = "namagudang";

    public oez_requisition_tracebility_v() {
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

    public String getRequestor() {
        return (String) get(PROPERTY_REQUESTOR);
    }

    public void setRequestor(String requestor) {
        set(PROPERTY_REQUESTOR, requestor);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
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

    public Long getQtypr() {
        return (Long) get(PROPERTY_QTYPR);
    }

    public void setQtypr(Long qtypr) {
        set(PROPERTY_QTYPR, qtypr);
    }

    public String getStatuspr() {
        return (String) get(PROPERTY_STATUSPR);
    }

    public void setStatuspr(String statuspr) {
        set(PROPERTY_STATUSPR, statuspr);
    }

    public Long getListPrice() {
        return (Long) get(PROPERTY_LISTPRICE);
    }

    public void setListPrice(Long listPrice) {
        set(PROPERTY_LISTPRICE, listPrice);
    }

    public Long getQtyorder() {
        return (Long) get(PROPERTY_QTYORDER);
    }

    public void setQtyorder(Long qtyorder) {
        set(PROPERTY_QTYORDER, qtyorder);
    }

    public String getBpcode() {
        return (String) get(PROPERTY_BPCODE);
    }

    public void setBpcode(String bpcode) {
        set(PROPERTY_BPCODE, bpcode);
    }

    public String getBpname() {
        return (String) get(PROPERTY_BPNAME);
    }

    public void setBpname(String bpname) {
        set(PROPERTY_BPNAME, bpname);
    }

    public Long getOrderpricelist() {
        return (Long) get(PROPERTY_ORDERPRICELIST);
    }

    public void setOrderpricelist(Long orderpricelist) {
        set(PROPERTY_ORDERPRICELIST, orderpricelist);
    }

    public Long getOrderpirceactual() {
        return (Long) get(PROPERTY_ORDERPIRCEACTUAL);
    }

    public void setOrderpirceactual(Long orderpirceactual) {
        set(PROPERTY_ORDERPIRCEACTUAL, orderpirceactual);
    }

    public Long getOrderlinenetamt() {
        return (Long) get(PROPERTY_ORDERLINENETAMT);
    }

    public void setOrderlinenetamt(Long orderlinenetamt) {
        set(PROPERTY_ORDERLINENETAMT, orderlinenetamt);
    }

    public Long getPendingpr() {
        return (Long) get(PROPERTY_PENDINGPR);
    }

    public void setPendingpr(Long pendingpr) {
        set(PROPERTY_PENDINGPR, pendingpr);
    }

    public String getPurchaseorderno() {
        return (String) get(PROPERTY_PURCHASEORDERNO);
    }

    public void setPurchaseorderno(String purchaseorderno) {
        set(PROPERTY_PURCHASEORDERNO, purchaseorderno);
    }

    public Long getMRPMoq() {
        return (Long) get(PROPERTY_MRPMOQ);
    }

    public void setMRPMoq(Long mRPMoq) {
        set(PROPERTY_MRPMOQ, mRPMoq);
    }

    public Long getMRPOrderpack() {
        return (Long) get(PROPERTY_MRPORDERPACK);
    }

    public void setMRPOrderpack(Long mRPOrderpack) {
        set(PROPERTY_MRPORDERPACK, mRPOrderpack);
    }

    public Long getMRPCosperorder() {
        return (Long) get(PROPERTY_MRPCOSPERORDER);
    }

    public void setMRPCosperorder(Long mRPCosperorder) {
        set(PROPERTY_MRPCOSPERORDER, mRPCosperorder);
    }

    public Long getMRPQtystd() {
        return (Long) get(PROPERTY_MRPQTYSTD);
    }

    public void setMRPQtystd(Long mRPQtystd) {
        set(PROPERTY_MRPQTYSTD, mRPQtystd);
    }

    public String getDocumengr() {
        return (String) get(PROPERTY_DOCUMENGR);
    }

    public void setDocumengr(String documengr) {
        set(PROPERTY_DOCUMENGR, documengr);
    }

    public Long getQtygr() {
        return (Long) get(PROPERTY_QTYGR);
    }

    public void setQtygr(Long qtygr) {
        set(PROPERTY_QTYGR, qtygr);
    }

    public Long getPendinggr() {
        return (Long) get(PROPERTY_PENDINGGR);
    }

    public void setPendinggr(Long pendinggr) {
        set(PROPERTY_PENDINGGR, pendinggr);
    }

    public String getNamagudang() {
        return (String) get(PROPERTY_NAMAGUDANG);
    }

    public void setNamagudang(String namagudang) {
        set(PROPERTY_NAMAGUDANG, namagudang);
    }

}
