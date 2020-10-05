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
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.shipping.ShippingCompany;
/**
 * Entity class for entity oez_shipment_proposal (stored in table oez_shipment_proposal).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class oez_shipment_proposal extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "oez_shipment_proposal";
    public static final String ENTITY_NAME = "oez_shipment_proposal";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_ISSELECTED = "isSelected";
    public static final String PROPERTY_ISDIRECTSHIPMENT = "isDirectShipment";
    public static final String PROPERTY_GATECLOSINGDATE = "gateClosingDate";
    public static final String PROPERTY_GATECLOSINGTIME = "gateClosingTime";
    public static final String PROPERTY_VESSELNAME = "vesselName";
    public static final String PROPERTY_VOYAGENUMBER = "voyageNumber";
    public static final String PROPERTY_LOADINGPORTNAME = "loadingPortName";
    public static final String PROPERTY_LOADINGPORTCITY = "loadingPortCity";
    public static final String PROPERTY_LOADINGPORTETD = "loadingPortETD";
    public static final String PROPERTY_LOADINGPORTCOUNTRY = "loadingPortCountry";
    public static final String PROPERTY_DESTINATIONPORTNAME = "destinationPortName";
    public static final String PROPERTY_DESTINATIONPORTETA = "destinationPortETA";
    public static final String PROPERTY_DESTINATIONPORTCITY = "destinationPortCity";
    public static final String PROPERTY_DESTINATIONPORTCOUNTRY = "destinationPortCountry";
    public static final String PROPERTY_CONNECTINGVESSELNAME = "connectingVesselName";
    public static final String PROPERTY_CONNECTINGPORTETA = "connectingPortETA";
    public static final String PROPERTY_CONNECTINGPORTETACITY = "connectingPortETACity";
    public static final String PROPERTY_CONNECTINGPORTETD = "connectingPortETD";
    public static final String PROPERTY_CONNECTINGPORTETDCITY = "connectingPortETDCity";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SHIPPINGCOMPANY = "shippingCompany";

    public oez_shipment_proposal() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISSELECTED, false);
        setDefaultValue(PROPERTY_ISDIRECTSHIPMENT, true);
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

    public Order getSalesOrder() {
        return (Order) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Order salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Boolean isSelected() {
        return (Boolean) get(PROPERTY_ISSELECTED);
    }

    public void setSelected(Boolean isSelected) {
        set(PROPERTY_ISSELECTED, isSelected);
    }

    public Boolean isDirectShipment() {
        return (Boolean) get(PROPERTY_ISDIRECTSHIPMENT);
    }

    public void setDirectShipment(Boolean isDirectShipment) {
        set(PROPERTY_ISDIRECTSHIPMENT, isDirectShipment);
    }

    public Date getGateClosingDate() {
        return (Date) get(PROPERTY_GATECLOSINGDATE);
    }

    public void setGateClosingDate(Date gateClosingDate) {
        set(PROPERTY_GATECLOSINGDATE, gateClosingDate);
    }

    public Date getGateClosingTime() {
        return (Date) get(PROPERTY_GATECLOSINGTIME);
    }

    public void setGateClosingTime(Date gateClosingTime) {
        set(PROPERTY_GATECLOSINGTIME, gateClosingTime);
    }

    public String getVesselName() {
        return (String) get(PROPERTY_VESSELNAME);
    }

    public void setVesselName(String vesselName) {
        set(PROPERTY_VESSELNAME, vesselName);
    }

    public String getVoyageNumber() {
        return (String) get(PROPERTY_VOYAGENUMBER);
    }

    public void setVoyageNumber(String voyageNumber) {
        set(PROPERTY_VOYAGENUMBER, voyageNumber);
    }

    public String getLoadingPortName() {
        return (String) get(PROPERTY_LOADINGPORTNAME);
    }

    public void setLoadingPortName(String loadingPortName) {
        set(PROPERTY_LOADINGPORTNAME, loadingPortName);
    }

    public String getLoadingPortCity() {
        return (String) get(PROPERTY_LOADINGPORTCITY);
    }

    public void setLoadingPortCity(String loadingPortCity) {
        set(PROPERTY_LOADINGPORTCITY, loadingPortCity);
    }

    public Date getLoadingPortETD() {
        return (Date) get(PROPERTY_LOADINGPORTETD);
    }

    public void setLoadingPortETD(Date loadingPortETD) {
        set(PROPERTY_LOADINGPORTETD, loadingPortETD);
    }

    public Country getLoadingPortCountry() {
        return (Country) get(PROPERTY_LOADINGPORTCOUNTRY);
    }

    public void setLoadingPortCountry(Country loadingPortCountry) {
        set(PROPERTY_LOADINGPORTCOUNTRY, loadingPortCountry);
    }

    public String getDestinationPortName() {
        return (String) get(PROPERTY_DESTINATIONPORTNAME);
    }

    public void setDestinationPortName(String destinationPortName) {
        set(PROPERTY_DESTINATIONPORTNAME, destinationPortName);
    }

    public Date getDestinationPortETA() {
        return (Date) get(PROPERTY_DESTINATIONPORTETA);
    }

    public void setDestinationPortETA(Date destinationPortETA) {
        set(PROPERTY_DESTINATIONPORTETA, destinationPortETA);
    }

    public String getDestinationPortCity() {
        return (String) get(PROPERTY_DESTINATIONPORTCITY);
    }

    public void setDestinationPortCity(String destinationPortCity) {
        set(PROPERTY_DESTINATIONPORTCITY, destinationPortCity);
    }

    public Country getDestinationPortCountry() {
        return (Country) get(PROPERTY_DESTINATIONPORTCOUNTRY);
    }

    public void setDestinationPortCountry(Country destinationPortCountry) {
        set(PROPERTY_DESTINATIONPORTCOUNTRY, destinationPortCountry);
    }

    public String getConnectingVesselName() {
        return (String) get(PROPERTY_CONNECTINGVESSELNAME);
    }

    public void setConnectingVesselName(String connectingVesselName) {
        set(PROPERTY_CONNECTINGVESSELNAME, connectingVesselName);
    }

    public Date getConnectingPortETA() {
        return (Date) get(PROPERTY_CONNECTINGPORTETA);
    }

    public void setConnectingPortETA(Date connectingPortETA) {
        set(PROPERTY_CONNECTINGPORTETA, connectingPortETA);
    }

    public String getConnectingPortETACity() {
        return (String) get(PROPERTY_CONNECTINGPORTETACITY);
    }

    public void setConnectingPortETACity(String connectingPortETACity) {
        set(PROPERTY_CONNECTINGPORTETACITY, connectingPortETACity);
    }

    public Date getConnectingPortETD() {
        return (Date) get(PROPERTY_CONNECTINGPORTETD);
    }

    public void setConnectingPortETD(Date connectingPortETD) {
        set(PROPERTY_CONNECTINGPORTETD, connectingPortETD);
    }

    public String getConnectingPortETDCity() {
        return (String) get(PROPERTY_CONNECTINGPORTETDCITY);
    }

    public void setConnectingPortETDCity(String connectingPortETDCity) {
        set(PROPERTY_CONNECTINGPORTETDCITY, connectingPortETDCity);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public ShippingCompany getShippingCompany() {
        return (ShippingCompany) get(PROPERTY_SHIPPINGCOMPANY);
    }

    public void setShippingCompany(ShippingCompany shippingCompany) {
        set(PROPERTY_SHIPPINGCOMPANY, shippingCompany);
    }

}
