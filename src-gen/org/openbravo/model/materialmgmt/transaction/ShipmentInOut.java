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
package org.openbravo.model.materialmgmt.transaction;

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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.UserDimension1;
import org.openbravo.model.financialmgmt.accounting.UserDimension2;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.gl.GLCharge;
import org.openbravo.model.marketing.Campaign;
import org.openbravo.model.materialmgmt.cost.ABCActivity;
import org.openbravo.model.materialmgmt.cost.LCReceipt;
import org.openbravo.model.materialmgmt.cost.LandedCostCost;
import org.openbravo.model.project.Project;
import org.openbravo.model.sales.ConditionGoods;
import org.openbravo.model.shipping.FreightCategory;
import org.openbravo.model.shipping.ShippingCompany;
import org.wirabumi.gen.oez.OEZ_I_Inout;
import org.wirabumi.gen.oez.oez_shipmentdata;
import org.wirabumi.gen.oez.oez_summary_lot_no;
/**
 * Entity class for entity MaterialMgmtShipmentInOut (stored in table M_InOut).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ShipmentInOut extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "M_InOut";
    public static final String ENTITY_NAME = "MaterialMgmtShipmentInOut";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SALESTRANSACTION = "salesTransaction";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_DOCUMENTACTION = "documentAction";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_POSTED = "posted";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_ORDERDATE = "orderDate";
    public static final String PROPERTY_PRINT = "print";
    public static final String PROPERTY_MOVEMENTTYPE = "movementType";
    public static final String PROPERTY_MOVEMENTDATE = "movementDate";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_ORDERREFERENCE = "orderReference";
    public static final String PROPERTY_DELIVERYTERMS = "deliveryTerms";
    public static final String PROPERTY_FREIGHTCOSTRULE = "freightCostRule";
    public static final String PROPERTY_FREIGHTAMOUNT = "freightAmount";
    public static final String PROPERTY_DELIVERYMETHOD = "deliveryMethod";
    public static final String PROPERTY_SHIPPINGCOMPANY = "shippingCompany";
    public static final String PROPERTY_CHARGE = "charge";
    public static final String PROPERTY_CHARGEAMOUNT = "chargeAmount";
    public static final String PROPERTY_PRIORITY = "priority";
    public static final String PROPERTY_DATEPRINTED = "datePrinted";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_CREATELINESFROM = "createLinesFrom";
    public static final String PROPERTY_GENERATETO = "generateTo";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_SALESREPRESENTATIVE = "salesRepresentative";
    public static final String PROPERTY_NUMBEROFPACKAGES = "numberOfPackages";
    public static final String PROPERTY_PICKDATE = "pickDate";
    public static final String PROPERTY_SHIPDATE = "shipDate";
    public static final String PROPERTY_TRACKINGNO = "trackingNo";
    public static final String PROPERTY_TRXORGANIZATION = "trxOrganization";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_SALESCAMPAIGN = "salesCampaign";
    public static final String PROPERTY_ACTIVITY = "activity";
    public static final String PROPERTY_STDIMENSION = "stDimension";
    public static final String PROPERTY_NDDIMENSION = "ndDimension";
    public static final String PROPERTY_UPDATELINES = "updateLines";
    public static final String PROPERTY_LOGISTIC = "logistic";
    public static final String PROPERTY_GENERATELINES = "generateLines";
    public static final String PROPERTY_CALCULATEFREIGHT = "calculateFreight";
    public static final String PROPERTY_DELIVERYLOCATION = "deliveryLocation";
    public static final String PROPERTY_FREIGHTCATEGORY = "freightCategory";
    public static final String PROPERTY_FREIGHTCURRENCY = "freightCurrency";
    public static final String PROPERTY_RECEIVEMATERIALS = "receiveMaterials";
    public static final String PROPERTY_SENDMATERIALS = "sendMaterials";
    public static final String PROPERTY_CONDITIONGOODS = "conditionGoods";
    public static final String PROPERTY_ASSET = "asset";
    public static final String PROPERTY_COSTCENTER = "costcenter";
    public static final String PROPERTY_PROCESSGOODSJAVA = "processGoodsJava";
    public static final String PROPERTY_OEZREIMBURSE = "oezReimburse";
    public static final String PROPERTY_OEZREIMBURSEFROM = "oezReimbursefrom";
    public static final String PROPERTY_OEZISREIMBURSEMENT = "oezIsreimbursement";
    public static final String PROPERTY_OEZCODOCUMENTNO = "oezCodocumentno";
    public static final String PROPERTY_ISNETTINGSHIPMENT = "isnettingshipment";
    public static final String PROPERTY_OEZBILLOFLADINGDATE = "oezBillOfLadingDate";
    public static final String PROPERTY_OEZVESSELNAME = "oezVesselName";
    public static final String PROPERTY_OEZBILLOFLADINGNO = "oezBillOfLadingNo";
    public static final String PROPERTY_OEZDRIVERNAME = "oezDriverName";
    public static final String PROPERTY_OEZVEHICLENO = "oezVehicleNo";
    public static final String PROPERTY_OEZBILLOFLADINGTYPE = "oezBillOfLadingType";
    public static final String PROPERTY_OEZVOYAGENUMBER = "oezVoyageNumber";
    public static final String PROPERTY_OEZISDIRECTSHIPMENT = "oezIsDirectShipment";
    public static final String PROPERTY_OEZPORTOFLOADING = "oezPortOfLoading";
    public static final String PROPERTY_OEZPORTOFDISCHARGE = "oezPortOfDischarge";
    public static final String PROPERTY_OEZCONNECTINGVESSELNAME = "oezConnectingVesselName";
    public static final String PROPERTY_OEZCONNECTINGPORTETA = "oezConnectingPortEta";
    public static final String PROPERTY_OEZCONNECTINGPORTETD = "oezConnectingPortEtd";
    public static final String PROPERTY_OEZLETTEROFCREDITDATE = "oezLetterofcreditDate";
    public static final String PROPERTY_OEZLETTEROFCREDITNUMBER = "oezLetterofcreditNumber";
    public static final String PROPERTY_OEZDESTPORT = "oezDestPort";
    public static final String PROPERTY_OEZDESTPORTETA = "oezDestPortEta";
    public static final String PROPERTY_OEZSHIPPERCONTACT = "oezShipperContact";
    public static final String PROPERTY_OEZCONTAINERSIZE = "oezContainerSize";
    public static final String PROPERTY_OEZDESTPORTCOUNTRY = "oezDestPortCountry";
    public static final String PROPERTY_OEZCONSIGNEE = "oezConsignee";
    public static final String PROPERTY_OEZSHIPPERCBPARTNER = "oezShipperCBpartner";
    public static final String PROPERTY_OEZTYPEOFGOODS = "oezTypeOfGoods";
    public static final String PROPERTY_OEZTOTALBILLOFLADING = "oezTotalBillOfLading";
    public static final String PROPERTY_OEZMARKING = "oezMarking";
    public static final String PROPERTY_OEZPRECOOLING = "oezPrecooling";
    public static final String PROPERTY_OEZRECOOLING = "oezRecooling";
    public static final String PROPERTY_OEZCTNARRIVED = "oezCtnArrived";
    public static final String PROPERTY_OEZCTNRECOOLING = "oezCtnRecooling";
    public static final String PROPERTY_OEZCTNPRECOOLING = "oezCtnPrecooling";
    public static final String PROPERTY_OEZCTNSTUFFING = "oezCtnStuffing";
    public static final String PROPERTY_OEZROUTE = "oezRoute";
    public static final String PROPERTY_COMPLETELYINVOICED = "completelyInvoiced";
    public static final String PROPERTY_OEZTMPFROMRECOLING = "oezTmpFromrecoling";
    public static final String PROPERTY_OEZTMPTORECOLING = "oezTmpTorecoling";
    public static final String PROPERTY_OEZCTNTMPARIRVED = "oezCtntmpArirved";
    public static final String PROPERTY_OEZCTNTMPFROMPRECOL = "oezCtntmpFromprecol";
    public static final String PROPERTY_OEZCTNTMPTOPRECOL = "oezCtntmpToprecol";
    public static final String PROPERTY_OEZCTNTMPSTUFING = "oezCtntmpStufing";
    public static final String PROPERTY_OEZCONTAINERNO = "oezContainerno";
    public static final String PROPERTY_OEZSEALNO = "oezSealno";
    public static final String PROPERTY_OEZPACKINGLISTNO = "oezPackinglistNo";
    public static final String PROPERTY_OEZEXPORTNO = "oezExportNo";
    public static final String PROPERTY_OEZDEPATURE = "oezDepature";
    public static final String PROPERTY_OEZDELIVERYNOTES = "oezDeliveryNotes";
    public static final String PROPERTY_OEZCOMERCIALNO = "oezComercialNo";
    public static final String PROPERTY_OEZLOTNO = "oezLotno";
    public static final String PROPERTY_OEZGENERATESUMMARY = "oezGenerateSummary";
    public static final String PROPERTY__COMPUTEDCOLUMNS = "_computedColumns";
    public static final String PROPERTY_LANDEDCOSTCOSTLIST = "landedCostCostList";
    public static final String PROPERTY_LANDEDCOSTRECEIPTLIST = "landedCostReceiptList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTEMOEZREIMBURSEFROMIDLIST = "materialMgmtShipmentInOutEmOezReimbursefromIdList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST = "materialMgmtShipmentInOutLineList";
    public static final String PROPERTY_OEZIINOUTLIST = "oEZIInoutList";
    public static final String PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST = "returnMaterialReceiptPickEditList";
    public static final String PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST = "returnMaterialShipmentPickEditList";
    public static final String PROPERTY_OEZSHIPMENTDATALIST = "oezShipmentdataList";
    public static final String PROPERTY_OEZSUMMARYLOTNOLIST = "oezSummaryLotNoList";


    // Computed columns properties, these properties cannot be directly accessed, they need
    // to be read through _commputedColumns proxy. They cannot be directly used in HQL, OBQuery
    // nor OBCriteria. 
    public static final String COMPUTED_COLUMN_INVOICESTATUS = "invoiceStatus";

    public ShipmentInOut() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DOCUMENTACTION, "CO");
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PRINT, false);
        setDefaultValue(PROPERTY_DELIVERYTERMS, "A");
        setDefaultValue(PROPERTY_FREIGHTCOSTRULE, "I");
        setDefaultValue(PROPERTY_FREIGHTAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DELIVERYMETHOD, "P");
        setDefaultValue(PROPERTY_CHARGEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRIORITY, "5");
        setDefaultValue(PROPERTY_CREATELINESFROM, false);
        setDefaultValue(PROPERTY_GENERATETO, false);
        setDefaultValue(PROPERTY_UPDATELINES, false);
        setDefaultValue(PROPERTY_GENERATELINES, false);
        setDefaultValue(PROPERTY_CALCULATEFREIGHT, false);
        setDefaultValue(PROPERTY_RECEIVEMATERIALS, false);
        setDefaultValue(PROPERTY_SENDMATERIALS, false);
        setDefaultValue(PROPERTY_PROCESSGOODSJAVA, "CO");
        setDefaultValue(PROPERTY_OEZREIMBURSE, false);
        setDefaultValue(PROPERTY_OEZISREIMBURSEMENT, false);
        setDefaultValue(PROPERTY_ISNETTINGSHIPMENT, false);
        setDefaultValue(PROPERTY_OEZBILLOFLADINGTYPE, "oez_master");
        setDefaultValue(PROPERTY_OEZISDIRECTSHIPMENT, true);
        setDefaultValue(PROPERTY_COMPLETELYINVOICED, false);
        setDefaultValue(PROPERTY_OEZGENERATESUMMARY, false);
        setDefaultValue(PROPERTY_LANDEDCOSTCOSTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LANDEDCOSTRECEIPTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTEMOEZREIMBURSEFROMIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZIINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZSHIPMENTDATALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZSUMMARYLOTNOLIST, new ArrayList<Object>());
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

    public Boolean isSalesTransaction() {
        return (Boolean) get(PROPERTY_SALESTRANSACTION);
    }

    public void setSalesTransaction(Boolean salesTransaction) {
        set(PROPERTY_SALESTRANSACTION, salesTransaction);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getDocumentAction() {
        return (String) get(PROPERTY_DOCUMENTACTION);
    }

    public void setDocumentAction(String documentAction) {
        set(PROPERTY_DOCUMENTACTION, documentAction);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public String getPosted() {
        return (String) get(PROPERTY_POSTED);
    }

    public void setPosted(String posted) {
        set(PROPERTY_POSTED, posted);
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

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Order getSalesOrder() {
        return (Order) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Order salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public Date getOrderDate() {
        return (Date) get(PROPERTY_ORDERDATE);
    }

    public void setOrderDate(Date orderDate) {
        set(PROPERTY_ORDERDATE, orderDate);
    }

    public Boolean isPrint() {
        return (Boolean) get(PROPERTY_PRINT);
    }

    public void setPrint(Boolean print) {
        set(PROPERTY_PRINT, print);
    }

    public String getMovementType() {
        return (String) get(PROPERTY_MOVEMENTTYPE);
    }

    public void setMovementType(String movementType) {
        set(PROPERTY_MOVEMENTTYPE, movementType);
    }

    public Date getMovementDate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementDate(Date movementDate) {
        set(PROPERTY_MOVEMENTDATE, movementDate);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public String getOrderReference() {
        return (String) get(PROPERTY_ORDERREFERENCE);
    }

    public void setOrderReference(String orderReference) {
        set(PROPERTY_ORDERREFERENCE, orderReference);
    }

    public String getDeliveryTerms() {
        return (String) get(PROPERTY_DELIVERYTERMS);
    }

    public void setDeliveryTerms(String deliveryTerms) {
        set(PROPERTY_DELIVERYTERMS, deliveryTerms);
    }

    public String getFreightCostRule() {
        return (String) get(PROPERTY_FREIGHTCOSTRULE);
    }

    public void setFreightCostRule(String freightCostRule) {
        set(PROPERTY_FREIGHTCOSTRULE, freightCostRule);
    }

    public BigDecimal getFreightAmount() {
        return (BigDecimal) get(PROPERTY_FREIGHTAMOUNT);
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        set(PROPERTY_FREIGHTAMOUNT, freightAmount);
    }

    public String getDeliveryMethod() {
        return (String) get(PROPERTY_DELIVERYMETHOD);
    }

    public void setDeliveryMethod(String deliveryMethod) {
        set(PROPERTY_DELIVERYMETHOD, deliveryMethod);
    }

    public ShippingCompany getShippingCompany() {
        return (ShippingCompany) get(PROPERTY_SHIPPINGCOMPANY);
    }

    public void setShippingCompany(ShippingCompany shippingCompany) {
        set(PROPERTY_SHIPPINGCOMPANY, shippingCompany);
    }

    public GLCharge getCharge() {
        return (GLCharge) get(PROPERTY_CHARGE);
    }

    public void setCharge(GLCharge charge) {
        set(PROPERTY_CHARGE, charge);
    }

    public BigDecimal getChargeAmount() {
        return (BigDecimal) get(PROPERTY_CHARGEAMOUNT);
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        set(PROPERTY_CHARGEAMOUNT, chargeAmount);
    }

    public String getPriority() {
        return (String) get(PROPERTY_PRIORITY);
    }

    public void setPriority(String priority) {
        set(PROPERTY_PRIORITY, priority);
    }

    public Date getDatePrinted() {
        return (Date) get(PROPERTY_DATEPRINTED);
    }

    public void setDatePrinted(Date datePrinted) {
        set(PROPERTY_DATEPRINTED, datePrinted);
    }

    public Invoice getInvoice() {
        return (Invoice) get(PROPERTY_INVOICE);
    }

    public void setInvoice(Invoice invoice) {
        set(PROPERTY_INVOICE, invoice);
    }

    public Boolean isCreateLinesFrom() {
        return (Boolean) get(PROPERTY_CREATELINESFROM);
    }

    public void setCreateLinesFrom(Boolean createLinesFrom) {
        set(PROPERTY_CREATELINESFROM, createLinesFrom);
    }

    public Boolean isGenerateTo() {
        return (Boolean) get(PROPERTY_GENERATETO);
    }

    public void setGenerateTo(Boolean generateTo) {
        set(PROPERTY_GENERATETO, generateTo);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public User getSalesRepresentative() {
        return (User) get(PROPERTY_SALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(User salesRepresentative) {
        set(PROPERTY_SALESREPRESENTATIVE, salesRepresentative);
    }

    public Long getNumberOfPackages() {
        return (Long) get(PROPERTY_NUMBEROFPACKAGES);
    }

    public void setNumberOfPackages(Long numberOfPackages) {
        set(PROPERTY_NUMBEROFPACKAGES, numberOfPackages);
    }

    public Date getPickDate() {
        return (Date) get(PROPERTY_PICKDATE);
    }

    public void setPickDate(Date pickDate) {
        set(PROPERTY_PICKDATE, pickDate);
    }

    public Date getShipDate() {
        return (Date) get(PROPERTY_SHIPDATE);
    }

    public void setShipDate(Date shipDate) {
        set(PROPERTY_SHIPDATE, shipDate);
    }

    public String getTrackingNo() {
        return (String) get(PROPERTY_TRACKINGNO);
    }

    public void setTrackingNo(String trackingNo) {
        set(PROPERTY_TRACKINGNO, trackingNo);
    }

    public Organization getTrxOrganization() {
        return (Organization) get(PROPERTY_TRXORGANIZATION);
    }

    public void setTrxOrganization(Organization trxOrganization) {
        set(PROPERTY_TRXORGANIZATION, trxOrganization);
    }

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Campaign getSalesCampaign() {
        return (Campaign) get(PROPERTY_SALESCAMPAIGN);
    }

    public void setSalesCampaign(Campaign salesCampaign) {
        set(PROPERTY_SALESCAMPAIGN, salesCampaign);
    }

    public ABCActivity getActivity() {
        return (ABCActivity) get(PROPERTY_ACTIVITY);
    }

    public void setActivity(ABCActivity activity) {
        set(PROPERTY_ACTIVITY, activity);
    }

    public UserDimension1 getStDimension() {
        return (UserDimension1) get(PROPERTY_STDIMENSION);
    }

    public void setStDimension(UserDimension1 stDimension) {
        set(PROPERTY_STDIMENSION, stDimension);
    }

    public UserDimension2 getNdDimension() {
        return (UserDimension2) get(PROPERTY_NDDIMENSION);
    }

    public void setNdDimension(UserDimension2 ndDimension) {
        set(PROPERTY_NDDIMENSION, ndDimension);
    }

    public Boolean isUpdateLines() {
        return (Boolean) get(PROPERTY_UPDATELINES);
    }

    public void setUpdateLines(Boolean updateLines) {
        set(PROPERTY_UPDATELINES, updateLines);
    }

    public Boolean isLogistic() {
        return (Boolean) get(PROPERTY_LOGISTIC);
    }

    public void setLogistic(Boolean logistic) {
        set(PROPERTY_LOGISTIC, logistic);
    }

    public Boolean isGenerateLines() {
        return (Boolean) get(PROPERTY_GENERATELINES);
    }

    public void setGenerateLines(Boolean generateLines) {
        set(PROPERTY_GENERATELINES, generateLines);
    }

    public Boolean isCalculateFreight() {
        return (Boolean) get(PROPERTY_CALCULATEFREIGHT);
    }

    public void setCalculateFreight(Boolean calculateFreight) {
        set(PROPERTY_CALCULATEFREIGHT, calculateFreight);
    }

    public Location getDeliveryLocation() {
        return (Location) get(PROPERTY_DELIVERYLOCATION);
    }

    public void setDeliveryLocation(Location deliveryLocation) {
        set(PROPERTY_DELIVERYLOCATION, deliveryLocation);
    }

    public FreightCategory getFreightCategory() {
        return (FreightCategory) get(PROPERTY_FREIGHTCATEGORY);
    }

    public void setFreightCategory(FreightCategory freightCategory) {
        set(PROPERTY_FREIGHTCATEGORY, freightCategory);
    }

    public Currency getFreightCurrency() {
        return (Currency) get(PROPERTY_FREIGHTCURRENCY);
    }

    public void setFreightCurrency(Currency freightCurrency) {
        set(PROPERTY_FREIGHTCURRENCY, freightCurrency);
    }

    public Boolean isReceiveMaterials() {
        return (Boolean) get(PROPERTY_RECEIVEMATERIALS);
    }

    public void setReceiveMaterials(Boolean receiveMaterials) {
        set(PROPERTY_RECEIVEMATERIALS, receiveMaterials);
    }

    public Boolean isSendMaterials() {
        return (Boolean) get(PROPERTY_SENDMATERIALS);
    }

    public void setSendMaterials(Boolean sendMaterials) {
        set(PROPERTY_SENDMATERIALS, sendMaterials);
    }

    public ConditionGoods getConditionGoods() {
        return (ConditionGoods) get(PROPERTY_CONDITIONGOODS);
    }

    public void setConditionGoods(ConditionGoods conditionGoods) {
        set(PROPERTY_CONDITIONGOODS, conditionGoods);
    }

    public Asset getAsset() {
        return (Asset) get(PROPERTY_ASSET);
    }

    public void setAsset(Asset asset) {
        set(PROPERTY_ASSET, asset);
    }

    public Costcenter getCostcenter() {
        return (Costcenter) get(PROPERTY_COSTCENTER);
    }

    public void setCostcenter(Costcenter costcenter) {
        set(PROPERTY_COSTCENTER, costcenter);
    }

    public String getProcessGoodsJava() {
        return (String) get(PROPERTY_PROCESSGOODSJAVA);
    }

    public void setProcessGoodsJava(String processGoodsJava) {
        set(PROPERTY_PROCESSGOODSJAVA, processGoodsJava);
    }

    public Boolean isOezReimburse() {
        return (Boolean) get(PROPERTY_OEZREIMBURSE);
    }

    public void setOezReimburse(Boolean oezReimburse) {
        set(PROPERTY_OEZREIMBURSE, oezReimburse);
    }

    public ShipmentInOut getOezReimbursefrom() {
        return (ShipmentInOut) get(PROPERTY_OEZREIMBURSEFROM);
    }

    public void setOezReimbursefrom(ShipmentInOut oezReimbursefrom) {
        set(PROPERTY_OEZREIMBURSEFROM, oezReimbursefrom);
    }

    public Boolean isOezIsreimbursement() {
        return (Boolean) get(PROPERTY_OEZISREIMBURSEMENT);
    }

    public void setOezIsreimbursement(Boolean oezIsreimbursement) {
        set(PROPERTY_OEZISREIMBURSEMENT, oezIsreimbursement);
    }

    public String getOezCodocumentno() {
        return (String) get(PROPERTY_OEZCODOCUMENTNO);
    }

    public void setOezCodocumentno(String oezCodocumentno) {
        set(PROPERTY_OEZCODOCUMENTNO, oezCodocumentno);
    }

    public Boolean isNettingshipment() {
        return (Boolean) get(PROPERTY_ISNETTINGSHIPMENT);
    }

    public void setNettingshipment(Boolean isnettingshipment) {
        set(PROPERTY_ISNETTINGSHIPMENT, isnettingshipment);
    }

    public Date getOezBillOfLadingDate() {
        return (Date) get(PROPERTY_OEZBILLOFLADINGDATE);
    }

    public void setOezBillOfLadingDate(Date oezBillOfLadingDate) {
        set(PROPERTY_OEZBILLOFLADINGDATE, oezBillOfLadingDate);
    }

    public String getOezVesselName() {
        return (String) get(PROPERTY_OEZVESSELNAME);
    }

    public void setOezVesselName(String oezVesselName) {
        set(PROPERTY_OEZVESSELNAME, oezVesselName);
    }

    public String getOezBillOfLadingNo() {
        return (String) get(PROPERTY_OEZBILLOFLADINGNO);
    }

    public void setOezBillOfLadingNo(String oezBillOfLadingNo) {
        set(PROPERTY_OEZBILLOFLADINGNO, oezBillOfLadingNo);
    }

    public String getOezDriverName() {
        return (String) get(PROPERTY_OEZDRIVERNAME);
    }

    public void setOezDriverName(String oezDriverName) {
        set(PROPERTY_OEZDRIVERNAME, oezDriverName);
    }

    public String getOezVehicleNo() {
        return (String) get(PROPERTY_OEZVEHICLENO);
    }

    public void setOezVehicleNo(String oezVehicleNo) {
        set(PROPERTY_OEZVEHICLENO, oezVehicleNo);
    }

    public String getOezBillOfLadingType() {
        return (String) get(PROPERTY_OEZBILLOFLADINGTYPE);
    }

    public void setOezBillOfLadingType(String oezBillOfLadingType) {
        set(PROPERTY_OEZBILLOFLADINGTYPE, oezBillOfLadingType);
    }

    public String getOezVoyageNumber() {
        return (String) get(PROPERTY_OEZVOYAGENUMBER);
    }

    public void setOezVoyageNumber(String oezVoyageNumber) {
        set(PROPERTY_OEZVOYAGENUMBER, oezVoyageNumber);
    }

    public Boolean isOezIsDirectShipment() {
        return (Boolean) get(PROPERTY_OEZISDIRECTSHIPMENT);
    }

    public void setOezIsDirectShipment(Boolean oezIsDirectShipment) {
        set(PROPERTY_OEZISDIRECTSHIPMENT, oezIsDirectShipment);
    }

    public String getOezPortOfLoading() {
        return (String) get(PROPERTY_OEZPORTOFLOADING);
    }

    public void setOezPortOfLoading(String oezPortOfLoading) {
        set(PROPERTY_OEZPORTOFLOADING, oezPortOfLoading);
    }

    public String getOezPortOfDischarge() {
        return (String) get(PROPERTY_OEZPORTOFDISCHARGE);
    }

    public void setOezPortOfDischarge(String oezPortOfDischarge) {
        set(PROPERTY_OEZPORTOFDISCHARGE, oezPortOfDischarge);
    }

    public String getOezConnectingVesselName() {
        return (String) get(PROPERTY_OEZCONNECTINGVESSELNAME);
    }

    public void setOezConnectingVesselName(String oezConnectingVesselName) {
        set(PROPERTY_OEZCONNECTINGVESSELNAME, oezConnectingVesselName);
    }

    public Date getOezConnectingPortEta() {
        return (Date) get(PROPERTY_OEZCONNECTINGPORTETA);
    }

    public void setOezConnectingPortEta(Date oezConnectingPortEta) {
        set(PROPERTY_OEZCONNECTINGPORTETA, oezConnectingPortEta);
    }

    public Date getOezConnectingPortEtd() {
        return (Date) get(PROPERTY_OEZCONNECTINGPORTETD);
    }

    public void setOezConnectingPortEtd(Date oezConnectingPortEtd) {
        set(PROPERTY_OEZCONNECTINGPORTETD, oezConnectingPortEtd);
    }

    public Date getOezLetterofcreditDate() {
        return (Date) get(PROPERTY_OEZLETTEROFCREDITDATE);
    }

    public void setOezLetterofcreditDate(Date oezLetterofcreditDate) {
        set(PROPERTY_OEZLETTEROFCREDITDATE, oezLetterofcreditDate);
    }

    public String getOezLetterofcreditNumber() {
        return (String) get(PROPERTY_OEZLETTEROFCREDITNUMBER);
    }

    public void setOezLetterofcreditNumber(String oezLetterofcreditNumber) {
        set(PROPERTY_OEZLETTEROFCREDITNUMBER, oezLetterofcreditNumber);
    }

    public String getOezDestPort() {
        return (String) get(PROPERTY_OEZDESTPORT);
    }

    public void setOezDestPort(String oezDestPort) {
        set(PROPERTY_OEZDESTPORT, oezDestPort);
    }

    public Date getOezDestPortEta() {
        return (Date) get(PROPERTY_OEZDESTPORTETA);
    }

    public void setOezDestPortEta(Date oezDestPortEta) {
        set(PROPERTY_OEZDESTPORTETA, oezDestPortEta);
    }

    public String getOezShipperContact() {
        return (String) get(PROPERTY_OEZSHIPPERCONTACT);
    }

    public void setOezShipperContact(String oezShipperContact) {
        set(PROPERTY_OEZSHIPPERCONTACT, oezShipperContact);
    }

    public String getOezContainerSize() {
        return (String) get(PROPERTY_OEZCONTAINERSIZE);
    }

    public void setOezContainerSize(String oezContainerSize) {
        set(PROPERTY_OEZCONTAINERSIZE, oezContainerSize);
    }

    public Country getOezDestPortCountry() {
        return (Country) get(PROPERTY_OEZDESTPORTCOUNTRY);
    }

    public void setOezDestPortCountry(Country oezDestPortCountry) {
        set(PROPERTY_OEZDESTPORTCOUNTRY, oezDestPortCountry);
    }

    public String getOezConsignee() {
        return (String) get(PROPERTY_OEZCONSIGNEE);
    }

    public void setOezConsignee(String oezConsignee) {
        set(PROPERTY_OEZCONSIGNEE, oezConsignee);
    }

    public String getOezShipperCBpartner() {
        return (String) get(PROPERTY_OEZSHIPPERCBPARTNER);
    }

    public void setOezShipperCBpartner(String oezShipperCBpartner) {
        set(PROPERTY_OEZSHIPPERCBPARTNER, oezShipperCBpartner);
    }

    public String getOezTypeOfGoods() {
        return (String) get(PROPERTY_OEZTYPEOFGOODS);
    }

    public void setOezTypeOfGoods(String oezTypeOfGoods) {
        set(PROPERTY_OEZTYPEOFGOODS, oezTypeOfGoods);
    }

    public String getOezTotalBillOfLading() {
        return (String) get(PROPERTY_OEZTOTALBILLOFLADING);
    }

    public void setOezTotalBillOfLading(String oezTotalBillOfLading) {
        set(PROPERTY_OEZTOTALBILLOFLADING, oezTotalBillOfLading);
    }

    public String getOezMarking() {
        return (String) get(PROPERTY_OEZMARKING);
    }

    public void setOezMarking(String oezMarking) {
        set(PROPERTY_OEZMARKING, oezMarking);
    }

    public String getOezPrecooling() {
        return (String) get(PROPERTY_OEZPRECOOLING);
    }

    public void setOezPrecooling(String oezPrecooling) {
        set(PROPERTY_OEZPRECOOLING, oezPrecooling);
    }

    public String getOezRecooling() {
        return (String) get(PROPERTY_OEZRECOOLING);
    }

    public void setOezRecooling(String oezRecooling) {
        set(PROPERTY_OEZRECOOLING, oezRecooling);
    }

    public String getOezCtnArrived() {
        return (String) get(PROPERTY_OEZCTNARRIVED);
    }

    public void setOezCtnArrived(String oezCtnArrived) {
        set(PROPERTY_OEZCTNARRIVED, oezCtnArrived);
    }

    public String getOezCtnRecooling() {
        return (String) get(PROPERTY_OEZCTNRECOOLING);
    }

    public void setOezCtnRecooling(String oezCtnRecooling) {
        set(PROPERTY_OEZCTNRECOOLING, oezCtnRecooling);
    }

    public String getOezCtnPrecooling() {
        return (String) get(PROPERTY_OEZCTNPRECOOLING);
    }

    public void setOezCtnPrecooling(String oezCtnPrecooling) {
        set(PROPERTY_OEZCTNPRECOOLING, oezCtnPrecooling);
    }

    public String getOezCtnStuffing() {
        return (String) get(PROPERTY_OEZCTNSTUFFING);
    }

    public void setOezCtnStuffing(String oezCtnStuffing) {
        set(PROPERTY_OEZCTNSTUFFING, oezCtnStuffing);
    }

    public String getOezRoute() {
        return (String) get(PROPERTY_OEZROUTE);
    }

    public void setOezRoute(String oezRoute) {
        set(PROPERTY_OEZROUTE, oezRoute);
    }

    public Boolean isCompletelyInvoiced() {
        return (Boolean) get(PROPERTY_COMPLETELYINVOICED);
    }

    public void setCompletelyInvoiced(Boolean completelyInvoiced) {
        set(PROPERTY_COMPLETELYINVOICED, completelyInvoiced);
    }

    public Date getOezTmpFromrecoling() {
        return (Date) get(PROPERTY_OEZTMPFROMRECOLING);
    }

    public void setOezTmpFromrecoling(Date oezTmpFromrecoling) {
        set(PROPERTY_OEZTMPFROMRECOLING, oezTmpFromrecoling);
    }

    public Date getOezTmpTorecoling() {
        return (Date) get(PROPERTY_OEZTMPTORECOLING);
    }

    public void setOezTmpTorecoling(Date oezTmpTorecoling) {
        set(PROPERTY_OEZTMPTORECOLING, oezTmpTorecoling);
    }

    public Date getOezCtntmpArirved() {
        return (Date) get(PROPERTY_OEZCTNTMPARIRVED);
    }

    public void setOezCtntmpArirved(Date oezCtntmpArirved) {
        set(PROPERTY_OEZCTNTMPARIRVED, oezCtntmpArirved);
    }

    public Date getOezCtntmpFromprecol() {
        return (Date) get(PROPERTY_OEZCTNTMPFROMPRECOL);
    }

    public void setOezCtntmpFromprecol(Date oezCtntmpFromprecol) {
        set(PROPERTY_OEZCTNTMPFROMPRECOL, oezCtntmpFromprecol);
    }

    public Date getOezCtntmpToprecol() {
        return (Date) get(PROPERTY_OEZCTNTMPTOPRECOL);
    }

    public void setOezCtntmpToprecol(Date oezCtntmpToprecol) {
        set(PROPERTY_OEZCTNTMPTOPRECOL, oezCtntmpToprecol);
    }

    public Date getOezCtntmpStufing() {
        return (Date) get(PROPERTY_OEZCTNTMPSTUFING);
    }

    public void setOezCtntmpStufing(Date oezCtntmpStufing) {
        set(PROPERTY_OEZCTNTMPSTUFING, oezCtntmpStufing);
    }

    public String getOezContainerno() {
        return (String) get(PROPERTY_OEZCONTAINERNO);
    }

    public void setOezContainerno(String oezContainerno) {
        set(PROPERTY_OEZCONTAINERNO, oezContainerno);
    }

    public String getOezSealno() {
        return (String) get(PROPERTY_OEZSEALNO);
    }

    public void setOezSealno(String oezSealno) {
        set(PROPERTY_OEZSEALNO, oezSealno);
    }

    public String getOezPackinglistNo() {
        return (String) get(PROPERTY_OEZPACKINGLISTNO);
    }

    public void setOezPackinglistNo(String oezPackinglistNo) {
        set(PROPERTY_OEZPACKINGLISTNO, oezPackinglistNo);
    }

    public String getOezExportNo() {
        return (String) get(PROPERTY_OEZEXPORTNO);
    }

    public void setOezExportNo(String oezExportNo) {
        set(PROPERTY_OEZEXPORTNO, oezExportNo);
    }

    public Date getOezDepature() {
        return (Date) get(PROPERTY_OEZDEPATURE);
    }

    public void setOezDepature(Date oezDepature) {
        set(PROPERTY_OEZDEPATURE, oezDepature);
    }

    public String getOezDeliveryNotes() {
        return (String) get(PROPERTY_OEZDELIVERYNOTES);
    }

    public void setOezDeliveryNotes(String oezDeliveryNotes) {
        set(PROPERTY_OEZDELIVERYNOTES, oezDeliveryNotes);
    }

    public String getOezComercialNo() {
        return (String) get(PROPERTY_OEZCOMERCIALNO);
    }

    public void setOezComercialNo(String oezComercialNo) {
        set(PROPERTY_OEZCOMERCIALNO, oezComercialNo);
    }

    public String getOezLotno() {
        return (String) get(PROPERTY_OEZLOTNO);
    }

    public void setOezLotno(String oezLotno) {
        set(PROPERTY_OEZLOTNO, oezLotno);
    }

    public Boolean isOezGenerateSummary() {
        return (Boolean) get(PROPERTY_OEZGENERATESUMMARY);
    }

    public void setOezGenerateSummary(Boolean oezGenerateSummary) {
        set(PROPERTY_OEZGENERATESUMMARY, oezGenerateSummary);
    }

    public Long getInvoiceStatus() {
        return (Long) get(COMPUTED_COLUMN_INVOICESTATUS);
    }

    public void setInvoiceStatus(Long invoiceStatus) {
        set(COMPUTED_COLUMN_INVOICESTATUS, invoiceStatus);
    }

    public ShipmentInOut_ComputedColumns get_computedColumns() {
        return (ShipmentInOut_ComputedColumns) get(PROPERTY__COMPUTEDCOLUMNS);
    }

    public void set_computedColumns(ShipmentInOut_ComputedColumns _computedColumns) {
        set(PROPERTY__COMPUTEDCOLUMNS, _computedColumns);
    }

    @SuppressWarnings("unchecked")
    public List<LandedCostCost> getLandedCostCostList() {
      return (List<LandedCostCost>) get(PROPERTY_LANDEDCOSTCOSTLIST);
    }

    public void setLandedCostCostList(List<LandedCostCost> landedCostCostList) {
        set(PROPERTY_LANDEDCOSTCOSTLIST, landedCostCostList);
    }

    @SuppressWarnings("unchecked")
    public List<LCReceipt> getLandedCostReceiptList() {
      return (List<LCReceipt>) get(PROPERTY_LANDEDCOSTRECEIPTLIST);
    }

    public void setLandedCostReceiptList(List<LCReceipt> landedCostReceiptList) {
        set(PROPERTY_LANDEDCOSTRECEIPTLIST, landedCostReceiptList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutEmOezReimbursefromIdList() {
      return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTEMOEZREIMBURSEFROMIDLIST);
    }

    public void setMaterialMgmtShipmentInOutEmOezReimbursefromIdList(List<ShipmentInOut> materialMgmtShipmentInOutEmOezReimbursefromIdList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTEMOEZREIMBURSEFROMIDLIST, materialMgmtShipmentInOutEmOezReimbursefromIdList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOutLine> getMaterialMgmtShipmentInOutLineList() {
      return (List<ShipmentInOutLine>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST);
    }

    public void setMaterialMgmtShipmentInOutLineList(List<ShipmentInOutLine> materialMgmtShipmentInOutLineList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, materialMgmtShipmentInOutLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OEZ_I_Inout> getOEZIInoutList() {
      return (List<OEZ_I_Inout>) get(PROPERTY_OEZIINOUTLIST);
    }

    public void setOEZIInoutList(List<OEZ_I_Inout> oEZIInoutList) {
        set(PROPERTY_OEZIINOUTLIST, oEZIInoutList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialReceiptPickEdit> getReturnMaterialReceiptPickEditList() {
      return (List<ReturnMaterialReceiptPickEdit>) get(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST);
    }

    public void setReturnMaterialReceiptPickEditList(List<ReturnMaterialReceiptPickEdit> returnMaterialReceiptPickEditList) {
        set(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, returnMaterialReceiptPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialShipmentPickEdit> getReturnMaterialShipmentPickEditList() {
      return (List<ReturnMaterialShipmentPickEdit>) get(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST);
    }

    public void setReturnMaterialShipmentPickEditList(List<ReturnMaterialShipmentPickEdit> returnMaterialShipmentPickEditList) {
        set(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, returnMaterialShipmentPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<oez_shipmentdata> getOezShipmentdataList() {
      return (List<oez_shipmentdata>) get(PROPERTY_OEZSHIPMENTDATALIST);
    }

    public void setOezShipmentdataList(List<oez_shipmentdata> oezShipmentdataList) {
        set(PROPERTY_OEZSHIPMENTDATALIST, oezShipmentdataList);
    }

    @SuppressWarnings("unchecked")
    public List<oez_summary_lot_no> getOezSummaryLotNoList() {
      return (List<oez_summary_lot_no>) get(PROPERTY_OEZSUMMARYLOTNOLIST);
    }

    public void setOezSummaryLotNoList(List<oez_summary_lot_no> oezSummaryLotNoList) {
        set(PROPERTY_OEZSUMMARYLOTNOLIST, oezSummaryLotNoList);
    }


    @Override
    public Object get(String propName) {
      if (COMPUTED_COLUMN_INVOICESTATUS.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getInvoiceStatus();
      }
    
      return super.get(propName);
    }
}
