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
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.geography.Region;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
/**
 * Entity class for entity OEZ_I_Inout (stored in table OEZ_I_Inout).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OEZ_I_Inout extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OEZ_I_Inout";
    public static final String ENTITY_NAME = "OEZ_I_Inout";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ISIMPORTED = "isimported";
    public static final String PROPERTY_ERRORMSG = "errormsg";
    public static final String PROPERTY_INOUT = "inout";
    public static final String PROPERTY_INOUTLINE = "inoutline";
    public static final String PROPERTY_PROCESSING = "processing";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_ISSOTRX = "issotrx";
    public static final String PROPERTY_DOCTYPENAME = "doctypename";
    public static final String PROPERTY_DOCTYPE = "doctype";
    public static final String PROPERTY_DOCUMENTNO = "documentno";
    public static final String PROPERTY_WAREHOUSE = "warehouse";
    public static final String PROPERTY_WAREHOUSEVALUE = "warehousevalue";
    public static final String PROPERTY_BPARTNERVALUE = "bpartnervalue";
    public static final String PROPERTY_BPARTNER = "bpartner";
    public static final String PROPERTY_BPARTNERLOCATION = "bpartnerLocation";
    public static final String PROPERTY_LOCATION = "location";
    public static final String PROPERTY_ADDRESS1 = "address1";
    public static final String PROPERTY_ADDRESS2 = "address2";
    public static final String PROPERTY_POSTAL = "postal";
    public static final String PROPERTY_CITY = "city";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_COUNTRYNAME = "countryname";
    public static final String PROPERTY_MOVEMENTDATE = "movementdate";
    public static final String PROPERTY_DATEACCT = "dateacct";
    public static final String PROPERTY_DELIVERYLOCATION = "deliveryLocation";
    public static final String PROPERTY_SHIPLOCATION = "shipLocation";
    public static final String PROPERTY_SHIPADDRESS1 = "shipAddress1";
    public static final String PROPERTY_SHIPADDRESS2 = "shipAddress2";
    public static final String PROPERTY_SHIPPOSTAL = "shipPostal";
    public static final String PROPERTY_SHIPCITY = "shipCity";
    public static final String PROPERTY_SHIPCOUNTRY = "shipCountry";
    public static final String PROPERTY_SHIPCOUNTRYNAME = "shipCountryname";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_LINE = "line";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_PRODUCTVALUE = "productvalue";
    public static final String PROPERTY_MOVEMENTQTY = "movementqty";
    public static final String PROPERTY_UOM = "uom";
    public static final String PROPERTY_EDICODE = "edicode";
    public static final String PROPERTY_LOCATOR = "locator";
    public static final String PROPERTY_STORAGEBINVALUE = "storagebinvalue";
    public static final String PROPERTY_PRODUCTDESCRIPTION = "productDescription";
    public static final String PROPERTY_MOVEMENTTYPE = "movementtype";
    public static final String PROPERTY_ISLOGISTIC = "islogistic";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_REGIONNAME = "regionname";
    public static final String PROPERTY_IMPORTSHIPMENT = "importshipment";

    public OEZ_I_Inout() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISIMPORTED, false);
        setDefaultValue(PROPERTY_PROCESSING, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_ISSOTRX, true);
        setDefaultValue(PROPERTY_ISLOGISTIC, false);
        setDefaultValue(PROPERTY_IMPORTSHIPMENT, false);
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

    public String getErrormsg() {
        return (String) get(PROPERTY_ERRORMSG);
    }

    public void setErrormsg(String errormsg) {
        set(PROPERTY_ERRORMSG, errormsg);
    }

    public ShipmentInOut getInout() {
        return (ShipmentInOut) get(PROPERTY_INOUT);
    }

    public void setInout(ShipmentInOut inout) {
        set(PROPERTY_INOUT, inout);
    }

    public ShipmentInOutLine getInoutline() {
        return (ShipmentInOutLine) get(PROPERTY_INOUTLINE);
    }

    public void setInoutline(ShipmentInOutLine inoutline) {
        set(PROPERTY_INOUTLINE, inoutline);
    }

    public Boolean isProcessing() {
        return (Boolean) get(PROPERTY_PROCESSING);
    }

    public void setProcessing(Boolean processing) {
        set(PROPERTY_PROCESSING, processing);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isSotrx() {
        return (Boolean) get(PROPERTY_ISSOTRX);
    }

    public void setSotrx(Boolean issotrx) {
        set(PROPERTY_ISSOTRX, issotrx);
    }

    public String getDoctypename() {
        return (String) get(PROPERTY_DOCTYPENAME);
    }

    public void setDoctypename(String doctypename) {
        set(PROPERTY_DOCTYPENAME, doctypename);
    }

    public DocumentType getDoctype() {
        return (DocumentType) get(PROPERTY_DOCTYPE);
    }

    public void setDoctype(DocumentType doctype) {
        set(PROPERTY_DOCTYPE, doctype);
    }

    public String getDocumentno() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentno(String documentno) {
        set(PROPERTY_DOCUMENTNO, documentno);
    }

    public Warehouse getWarehouse() {
        return (Warehouse) get(PROPERTY_WAREHOUSE);
    }

    public void setWarehouse(Warehouse warehouse) {
        set(PROPERTY_WAREHOUSE, warehouse);
    }

    public String getWarehousevalue() {
        return (String) get(PROPERTY_WAREHOUSEVALUE);
    }

    public void setWarehousevalue(String warehousevalue) {
        set(PROPERTY_WAREHOUSEVALUE, warehousevalue);
    }

    public String getBpartnervalue() {
        return (String) get(PROPERTY_BPARTNERVALUE);
    }

    public void setBpartnervalue(String bpartnervalue) {
        set(PROPERTY_BPARTNERVALUE, bpartnervalue);
    }

    public BusinessPartner getBpartner() {
        return (BusinessPartner) get(PROPERTY_BPARTNER);
    }

    public void setBpartner(BusinessPartner bpartner) {
        set(PROPERTY_BPARTNER, bpartner);
    }

    public Location getBpartnerLocation() {
        return (Location) get(PROPERTY_BPARTNERLOCATION);
    }

    public void setBpartnerLocation(Location bpartnerLocation) {
        set(PROPERTY_BPARTNERLOCATION, bpartnerLocation);
    }

    public org.openbravo.model.common.geography.Location getLocation() {
        return (org.openbravo.model.common.geography.Location) get(PROPERTY_LOCATION);
    }

    public void setLocation(org.openbravo.model.common.geography.Location location) {
        set(PROPERTY_LOCATION, location);
    }

    public String getAddress1() {
        return (String) get(PROPERTY_ADDRESS1);
    }

    public void setAddress1(String address1) {
        set(PROPERTY_ADDRESS1, address1);
    }

    public String getAddress2() {
        return (String) get(PROPERTY_ADDRESS2);
    }

    public void setAddress2(String address2) {
        set(PROPERTY_ADDRESS2, address2);
    }

    public String getPostal() {
        return (String) get(PROPERTY_POSTAL);
    }

    public void setPostal(String postal) {
        set(PROPERTY_POSTAL, postal);
    }

    public String getCity() {
        return (String) get(PROPERTY_CITY);
    }

    public void setCity(String city) {
        set(PROPERTY_CITY, city);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public String getCountryname() {
        return (String) get(PROPERTY_COUNTRYNAME);
    }

    public void setCountryname(String countryname) {
        set(PROPERTY_COUNTRYNAME, countryname);
    }

    public Date getMovementdate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementdate(Date movementdate) {
        set(PROPERTY_MOVEMENTDATE, movementdate);
    }

    public Date getDateacct() {
        return (Date) get(PROPERTY_DATEACCT);
    }

    public void setDateacct(Date dateacct) {
        set(PROPERTY_DATEACCT, dateacct);
    }

    public Location getDeliveryLocation() {
        return (Location) get(PROPERTY_DELIVERYLOCATION);
    }

    public void setDeliveryLocation(Location deliveryLocation) {
        set(PROPERTY_DELIVERYLOCATION, deliveryLocation);
    }

    public org.openbravo.model.common.geography.Location getShipLocation() {
        return (org.openbravo.model.common.geography.Location) get(PROPERTY_SHIPLOCATION);
    }

    public void setShipLocation(org.openbravo.model.common.geography.Location shipLocation) {
        set(PROPERTY_SHIPLOCATION, shipLocation);
    }

    public String getShipAddress1() {
        return (String) get(PROPERTY_SHIPADDRESS1);
    }

    public void setShipAddress1(String shipAddress1) {
        set(PROPERTY_SHIPADDRESS1, shipAddress1);
    }

    public String getShipAddress2() {
        return (String) get(PROPERTY_SHIPADDRESS2);
    }

    public void setShipAddress2(String shipAddress2) {
        set(PROPERTY_SHIPADDRESS2, shipAddress2);
    }

    public String getShipPostal() {
        return (String) get(PROPERTY_SHIPPOSTAL);
    }

    public void setShipPostal(String shipPostal) {
        set(PROPERTY_SHIPPOSTAL, shipPostal);
    }

    public String getShipCity() {
        return (String) get(PROPERTY_SHIPCITY);
    }

    public void setShipCity(String shipCity) {
        set(PROPERTY_SHIPCITY, shipCity);
    }

    public Country getShipCountry() {
        return (Country) get(PROPERTY_SHIPCOUNTRY);
    }

    public void setShipCountry(Country shipCountry) {
        set(PROPERTY_SHIPCOUNTRY, shipCountry);
    }

    public String getShipCountryname() {
        return (String) get(PROPERTY_SHIPCOUNTRYNAME);
    }

    public void setShipCountryname(String shipCountryname) {
        set(PROPERTY_SHIPCOUNTRYNAME, shipCountryname);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Long getLine() {
        return (Long) get(PROPERTY_LINE);
    }

    public void setLine(Long line) {
        set(PROPERTY_LINE, line);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getProductvalue() {
        return (String) get(PROPERTY_PRODUCTVALUE);
    }

    public void setProductvalue(String productvalue) {
        set(PROPERTY_PRODUCTVALUE, productvalue);
    }

    public Long getMovementqty() {
        return (Long) get(PROPERTY_MOVEMENTQTY);
    }

    public void setMovementqty(Long movementqty) {
        set(PROPERTY_MOVEMENTQTY, movementqty);
    }

    public UOM getUom() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUom(UOM uom) {
        set(PROPERTY_UOM, uom);
    }

    public String getEdicode() {
        return (String) get(PROPERTY_EDICODE);
    }

    public void setEdicode(String edicode) {
        set(PROPERTY_EDICODE, edicode);
    }

    public Locator getLocator() {
        return (Locator) get(PROPERTY_LOCATOR);
    }

    public void setLocator(Locator locator) {
        set(PROPERTY_LOCATOR, locator);
    }

    public String getStoragebinvalue() {
        return (String) get(PROPERTY_STORAGEBINVALUE);
    }

    public void setStoragebinvalue(String storagebinvalue) {
        set(PROPERTY_STORAGEBINVALUE, storagebinvalue);
    }

    public String getProductDescription() {
        return (String) get(PROPERTY_PRODUCTDESCRIPTION);
    }

    public void setProductDescription(String productDescription) {
        set(PROPERTY_PRODUCTDESCRIPTION, productDescription);
    }

    public String getMovementtype() {
        return (String) get(PROPERTY_MOVEMENTTYPE);
    }

    public void setMovementtype(String movementtype) {
        set(PROPERTY_MOVEMENTTYPE, movementtype);
    }

    public Boolean isLogistic() {
        return (Boolean) get(PROPERTY_ISLOGISTIC);
    }

    public void setLogistic(Boolean islogistic) {
        set(PROPERTY_ISLOGISTIC, islogistic);
    }

    public Region getRegion() {
        return (Region) get(PROPERTY_REGION);
    }

    public void setRegion(Region region) {
        set(PROPERTY_REGION, region);
    }

    public String getRegionname() {
        return (String) get(PROPERTY_REGIONNAME);
    }

    public void setRegionname(String regionname) {
        set(PROPERTY_REGIONNAME, regionname);
    }

    public Boolean isImportshipment() {
        return (Boolean) get(PROPERTY_IMPORTSHIPMENT);
    }

    public void setImportshipment(Boolean importshipment) {
        set(PROPERTY_IMPORTSHIPMENT, importshipment);
    }

}
