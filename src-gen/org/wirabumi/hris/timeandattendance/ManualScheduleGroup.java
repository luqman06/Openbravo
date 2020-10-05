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
package org.wirabumi.hris.timeandattendance;

import java.sql.Timestamp;
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
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ta_manualschedulegroup (stored in table ta_manualschedulegroup).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ManualScheduleGroup extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "ta_manualschedulegroup";
    public static final String ENTITY_NAME = "ta_manualschedulegroup";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CHECKIN = "checkIn";
    public static final String PROPERTY_CHECKOUT = "checkOut";
    public static final String PROPERTY_OFF = "off";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP1LIST = "taIManualscheduleScheduleGroup1List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP2LIST = "taIManualscheduleScheduleGroup2List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP3LIST = "taIManualscheduleScheduleGroup3List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP4LIST = "taIManualscheduleScheduleGroup4List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP5LIST = "taIManualscheduleScheduleGroup5List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP6LIST = "taIManualscheduleScheduleGroup6List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP7LIST = "taIManualscheduleScheduleGroup7List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP8LIST = "taIManualscheduleScheduleGroup8List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP9LIST = "taIManualscheduleScheduleGroup9List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP10LIST = "taIManualscheduleScheduleGroup10List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP11LIST = "taIManualscheduleScheduleGroup11List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP12LIST = "taIManualscheduleScheduleGroup12List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP13LIST = "taIManualscheduleScheduleGroup13List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP14LIST = "taIManualscheduleScheduleGroup14List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP15LIST = "taIManualscheduleScheduleGroup15List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP16LIST = "taIManualscheduleScheduleGroup16List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP17LIST = "taIManualscheduleScheduleGroup17List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP18LIST = "taIManualscheduleScheduleGroup18List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP19LIST = "taIManualscheduleScheduleGroup19List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP20LIST = "taIManualscheduleScheduleGroup20List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP21LIST = "taIManualscheduleScheduleGroup21List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP22LIST = "taIManualscheduleScheduleGroup22List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP23LIST = "taIManualscheduleScheduleGroup23List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP24LIST = "taIManualscheduleScheduleGroup24List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP25LIST = "taIManualscheduleScheduleGroup25List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP26LIST = "taIManualscheduleScheduleGroup26List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP27LIST = "taIManualscheduleScheduleGroup27List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP28LIST = "taIManualscheduleScheduleGroup28List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP29LIST = "taIManualscheduleScheduleGroup29List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP30LIST = "taIManualscheduleScheduleGroup30List";
    public static final String PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP31LIST = "taIManualscheduleScheduleGroup31List";
    public static final String PROPERTY_TAMANUALSCHEDULELIST = "taManualscheduleList";

    public ManualScheduleGroup() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_OFF, false);
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP1LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP3LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP4LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP5LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP6LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP7LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP8LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP9LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP10LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP11LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP12LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP13LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP14LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP15LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP16LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP17LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP18LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP19LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP20LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP21LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP22LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP23LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP24LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP25LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP26LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP27LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP28LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP29LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP30LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP31LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TAMANUALSCHEDULELIST, new ArrayList<Object>());
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

    public Timestamp getCheckIn() {
        return (Timestamp) get(PROPERTY_CHECKIN);
    }

    public void setCheckIn(Timestamp checkIn) {
        set(PROPERTY_CHECKIN, checkIn);
    }

    public Timestamp getCheckOut() {
        return (Timestamp) get(PROPERTY_CHECKOUT);
    }

    public void setCheckOut(Timestamp checkOut) {
        set(PROPERTY_CHECKOUT, checkOut);
    }

    public Boolean isOff() {
        return (Boolean) get(PROPERTY_OFF);
    }

    public void setOff(Boolean off) {
        set(PROPERTY_OFF, off);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup1List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP1LIST);
    }

    public void setTaIManualscheduleScheduleGroup1List(List<ImportManualSchedule> taIManualscheduleScheduleGroup1List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP1LIST, taIManualscheduleScheduleGroup1List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup2List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP2LIST);
    }

    public void setTaIManualscheduleScheduleGroup2List(List<ImportManualSchedule> taIManualscheduleScheduleGroup2List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP2LIST, taIManualscheduleScheduleGroup2List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup3List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP3LIST);
    }

    public void setTaIManualscheduleScheduleGroup3List(List<ImportManualSchedule> taIManualscheduleScheduleGroup3List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP3LIST, taIManualscheduleScheduleGroup3List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup4List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP4LIST);
    }

    public void setTaIManualscheduleScheduleGroup4List(List<ImportManualSchedule> taIManualscheduleScheduleGroup4List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP4LIST, taIManualscheduleScheduleGroup4List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup5List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP5LIST);
    }

    public void setTaIManualscheduleScheduleGroup5List(List<ImportManualSchedule> taIManualscheduleScheduleGroup5List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP5LIST, taIManualscheduleScheduleGroup5List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup6List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP6LIST);
    }

    public void setTaIManualscheduleScheduleGroup6List(List<ImportManualSchedule> taIManualscheduleScheduleGroup6List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP6LIST, taIManualscheduleScheduleGroup6List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup7List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP7LIST);
    }

    public void setTaIManualscheduleScheduleGroup7List(List<ImportManualSchedule> taIManualscheduleScheduleGroup7List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP7LIST, taIManualscheduleScheduleGroup7List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup8List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP8LIST);
    }

    public void setTaIManualscheduleScheduleGroup8List(List<ImportManualSchedule> taIManualscheduleScheduleGroup8List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP8LIST, taIManualscheduleScheduleGroup8List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup9List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP9LIST);
    }

    public void setTaIManualscheduleScheduleGroup9List(List<ImportManualSchedule> taIManualscheduleScheduleGroup9List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP9LIST, taIManualscheduleScheduleGroup9List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup10List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP10LIST);
    }

    public void setTaIManualscheduleScheduleGroup10List(List<ImportManualSchedule> taIManualscheduleScheduleGroup10List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP10LIST, taIManualscheduleScheduleGroup10List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup11List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP11LIST);
    }

    public void setTaIManualscheduleScheduleGroup11List(List<ImportManualSchedule> taIManualscheduleScheduleGroup11List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP11LIST, taIManualscheduleScheduleGroup11List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup12List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP12LIST);
    }

    public void setTaIManualscheduleScheduleGroup12List(List<ImportManualSchedule> taIManualscheduleScheduleGroup12List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP12LIST, taIManualscheduleScheduleGroup12List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup13List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP13LIST);
    }

    public void setTaIManualscheduleScheduleGroup13List(List<ImportManualSchedule> taIManualscheduleScheduleGroup13List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP13LIST, taIManualscheduleScheduleGroup13List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup14List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP14LIST);
    }

    public void setTaIManualscheduleScheduleGroup14List(List<ImportManualSchedule> taIManualscheduleScheduleGroup14List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP14LIST, taIManualscheduleScheduleGroup14List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup15List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP15LIST);
    }

    public void setTaIManualscheduleScheduleGroup15List(List<ImportManualSchedule> taIManualscheduleScheduleGroup15List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP15LIST, taIManualscheduleScheduleGroup15List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup16List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP16LIST);
    }

    public void setTaIManualscheduleScheduleGroup16List(List<ImportManualSchedule> taIManualscheduleScheduleGroup16List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP16LIST, taIManualscheduleScheduleGroup16List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup17List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP17LIST);
    }

    public void setTaIManualscheduleScheduleGroup17List(List<ImportManualSchedule> taIManualscheduleScheduleGroup17List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP17LIST, taIManualscheduleScheduleGroup17List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup18List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP18LIST);
    }

    public void setTaIManualscheduleScheduleGroup18List(List<ImportManualSchedule> taIManualscheduleScheduleGroup18List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP18LIST, taIManualscheduleScheduleGroup18List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup19List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP19LIST);
    }

    public void setTaIManualscheduleScheduleGroup19List(List<ImportManualSchedule> taIManualscheduleScheduleGroup19List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP19LIST, taIManualscheduleScheduleGroup19List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup20List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP20LIST);
    }

    public void setTaIManualscheduleScheduleGroup20List(List<ImportManualSchedule> taIManualscheduleScheduleGroup20List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP20LIST, taIManualscheduleScheduleGroup20List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup21List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP21LIST);
    }

    public void setTaIManualscheduleScheduleGroup21List(List<ImportManualSchedule> taIManualscheduleScheduleGroup21List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP21LIST, taIManualscheduleScheduleGroup21List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup22List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP22LIST);
    }

    public void setTaIManualscheduleScheduleGroup22List(List<ImportManualSchedule> taIManualscheduleScheduleGroup22List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP22LIST, taIManualscheduleScheduleGroup22List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup23List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP23LIST);
    }

    public void setTaIManualscheduleScheduleGroup23List(List<ImportManualSchedule> taIManualscheduleScheduleGroup23List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP23LIST, taIManualscheduleScheduleGroup23List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup24List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP24LIST);
    }

    public void setTaIManualscheduleScheduleGroup24List(List<ImportManualSchedule> taIManualscheduleScheduleGroup24List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP24LIST, taIManualscheduleScheduleGroup24List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup25List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP25LIST);
    }

    public void setTaIManualscheduleScheduleGroup25List(List<ImportManualSchedule> taIManualscheduleScheduleGroup25List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP25LIST, taIManualscheduleScheduleGroup25List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup26List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP26LIST);
    }

    public void setTaIManualscheduleScheduleGroup26List(List<ImportManualSchedule> taIManualscheduleScheduleGroup26List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP26LIST, taIManualscheduleScheduleGroup26List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup27List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP27LIST);
    }

    public void setTaIManualscheduleScheduleGroup27List(List<ImportManualSchedule> taIManualscheduleScheduleGroup27List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP27LIST, taIManualscheduleScheduleGroup27List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup28List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP28LIST);
    }

    public void setTaIManualscheduleScheduleGroup28List(List<ImportManualSchedule> taIManualscheduleScheduleGroup28List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP28LIST, taIManualscheduleScheduleGroup28List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup29List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP29LIST);
    }

    public void setTaIManualscheduleScheduleGroup29List(List<ImportManualSchedule> taIManualscheduleScheduleGroup29List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP29LIST, taIManualscheduleScheduleGroup29List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup30List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP30LIST);
    }

    public void setTaIManualscheduleScheduleGroup30List(List<ImportManualSchedule> taIManualscheduleScheduleGroup30List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP30LIST, taIManualscheduleScheduleGroup30List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getTaIManualscheduleScheduleGroup31List() {
      return (List<ImportManualSchedule>) get(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP31LIST);
    }

    public void setTaIManualscheduleScheduleGroup31List(List<ImportManualSchedule> taIManualscheduleScheduleGroup31List) {
        set(PROPERTY_TAIMANUALSCHEDULESCHEDULEGROUP31LIST, taIManualscheduleScheduleGroup31List);
    }

    @SuppressWarnings("unchecked")
    public List<ManualSchedule> getTaManualscheduleList() {
      return (List<ManualSchedule>) get(PROPERTY_TAMANUALSCHEDULELIST);
    }

    public void setTaManualscheduleList(List<ManualSchedule> taManualscheduleList) {
        set(PROPERTY_TAMANUALSCHEDULELIST, taManualscheduleList);
    }

}
