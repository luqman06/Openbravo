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
/**
 * Entity class for entity ta_i_manualschedule (stored in table ta_i_manualschedule).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ImportManualSchedule extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "ta_i_manualschedule";
    public static final String ENTITY_NAME = "ta_i_manualschedule";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEEKEY = "employeekey";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_FISCALYEAR = "fiscalYear";
    public static final String PROPERTY_MONTH = "month";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_SCHEDULEGROUP1KEY = "scheduleGroup1Key";
    public static final String PROPERTY_SCHEDULEGROUP1 = "scheduleGroup1";
    public static final String PROPERTY_SCHEDULEGROUP2KEY = "scheduleGroup2Key";
    public static final String PROPERTY_SCHEDULEGROUP2 = "scheduleGroup2";
    public static final String PROPERTY_SCHEDULEGROUP3KEY = "scheduleGroup3Key";
    public static final String PROPERTY_SCHEDULEGROUP3 = "scheduleGroup3";
    public static final String PROPERTY_SCHEDULEGROUP4KEY = "scheduleGroup4Key";
    public static final String PROPERTY_SCHEDULEGROUP4 = "scheduleGroup4";
    public static final String PROPERTY_SCHEDULEGROUP5KEY = "scheduleGroup5Key";
    public static final String PROPERTY_SCHEDULEGROUP5 = "scheduleGroup5";
    public static final String PROPERTY_SCHEDULEGROUP6KEY = "scheduleGroup6Key";
    public static final String PROPERTY_SCHEDULEGROUP6 = "scheduleGroup6";
    public static final String PROPERTY_SCHEDULEGROUP7KEY = "scheduleGroup7Key";
    public static final String PROPERTY_SCHEDULEGROUP7 = "scheduleGroup7";
    public static final String PROPERTY_SCHEDULEGROUP8KEY = "scheduleGroup8Key";
    public static final String PROPERTY_SCHEDULEGROUP8 = "scheduleGroup8";
    public static final String PROPERTY_SCHEDULEGROUP9KEY = "scheduleGroup9Key";
    public static final String PROPERTY_SCHEDULEGROUP9 = "scheduleGroup9";
    public static final String PROPERTY_SCHEDULEGROUP10KEY = "scheduleGroup10Key";
    public static final String PROPERTY_SCHEDULEGROUP10 = "scheduleGroup10";
    public static final String PROPERTY_SCHEDULEGROUP11KEY = "scheduleGroup11Key";
    public static final String PROPERTY_SCHEDULEGROUP11 = "scheduleGroup11";
    public static final String PROPERTY_SCHEDULEGROUP12KEY = "scheduleGroup12Key";
    public static final String PROPERTY_SCHEDULEGROUP12 = "scheduleGroup12";
    public static final String PROPERTY_SCHEDULEGROUP13KEY = "scheduleGroup13Key";
    public static final String PROPERTY_SCHEDULEGROUP13 = "scheduleGroup13";
    public static final String PROPERTY_SCHEDULEGROUP14KEY = "scheduleGroup14Key";
    public static final String PROPERTY_SCHEDULEGROUP14 = "scheduleGroup14";
    public static final String PROPERTY_SCHEDULEGROUP15KEY = "scheduleGroup15Key";
    public static final String PROPERTY_SCHEDULEGROUP15 = "scheduleGroup15";
    public static final String PROPERTY_SCHEDULEGROUP16KEY = "scheduleGroup16Key";
    public static final String PROPERTY_SCHEDULEGROUP16 = "scheduleGroup16";
    public static final String PROPERTY_SCHEDULEGROUP17KEY = "scheduleGroup17Key";
    public static final String PROPERTY_SCHEDULEGROUP17 = "scheduleGroup17";
    public static final String PROPERTY_SCHEDULEGROUP18KEY = "scheduleGroup18Key";
    public static final String PROPERTY_SCHEDULEGROUP18 = "scheduleGroup18";
    public static final String PROPERTY_SCHEDULEGROUP19KEY = "scheduleGroup19Key";
    public static final String PROPERTY_SCHEDULEGROUP19 = "scheduleGroup19";
    public static final String PROPERTY_SCHEDULEGROUP20KEY = "scheduleGroup20Key";
    public static final String PROPERTY_SCHEDULEGROUP20 = "scheduleGroup20";
    public static final String PROPERTY_SCHEDULEGROUP21KEY = "scheduleGroup21Key";
    public static final String PROPERTY_SCHEDULEGROUP21 = "scheduleGroup21";
    public static final String PROPERTY_SCHEDULEGROUP22KEY = "scheduleGroup22Key";
    public static final String PROPERTY_SCHEDULEGROUP22 = "scheduleGroup22";
    public static final String PROPERTY_SCHEDULEGROUP23KEY = "scheduleGroup23Key";
    public static final String PROPERTY_SCHEDULEGROUP23 = "scheduleGroup23";
    public static final String PROPERTY_SCHEDULEGROUP24KEY = "scheduleGroup24Key";
    public static final String PROPERTY_SCHEDULEGROUP24 = "scheduleGroup24";
    public static final String PROPERTY_SCHEDULEGROUP25KEY = "scheduleGroup25Key";
    public static final String PROPERTY_SCHEDULEGROUP25 = "scheduleGroup25";
    public static final String PROPERTY_SCHEDULEGROUP26KEY = "scheduleGroup26Key";
    public static final String PROPERTY_SCHEDULEGROUP26 = "scheduleGroup26";
    public static final String PROPERTY_SCHEDULEGROUP27KEY = "scheduleGroup27Key";
    public static final String PROPERTY_SCHEDULEGROUP27 = "scheduleGroup27";
    public static final String PROPERTY_SCHEDULEGROUP28KEY = "scheduleGroup28Key";
    public static final String PROPERTY_SCHEDULEGROUP28 = "scheduleGroup28";
    public static final String PROPERTY_SCHEDULEGROUP29KEY = "scheduleGroup29Key";
    public static final String PROPERTY_SCHEDULEGROUP29 = "scheduleGroup29";
    public static final String PROPERTY_SCHEDULEGROUP30KEY = "scheduleGroup30Key";
    public static final String PROPERTY_SCHEDULEGROUP30 = "scheduleGroup30";
    public static final String PROPERTY_SCHEDULEGROUP31KEY = "scheduleGroup31Key";
    public static final String PROPERTY_SCHEDULEGROUP31 = "scheduleGroup31";

    public ImportManualSchedule() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
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

    public String getEmployeekey() {
        return (String) get(PROPERTY_EMPLOYEEKEY);
    }

    public void setEmployeekey(String employeekey) {
        set(PROPERTY_EMPLOYEEKEY, employeekey);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getFiscalYear() {
        return (String) get(PROPERTY_FISCALYEAR);
    }

    public void setFiscalYear(String fiscalYear) {
        set(PROPERTY_FISCALYEAR, fiscalYear);
    }

    public Long getMonth() {
        return (Long) get(PROPERTY_MONTH);
    }

    public void setMonth(Long month) {
        set(PROPERTY_MONTH, month);
    }

    public Boolean isImportProcessComplete() {
        return (Boolean) get(PROPERTY_IMPORTPROCESSCOMPLETE);
    }

    public void setImportProcessComplete(Boolean importProcessComplete) {
        set(PROPERTY_IMPORTPROCESSCOMPLETE, importProcessComplete);
    }

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public String getScheduleGroup1Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP1KEY);
    }

    public void setScheduleGroup1Key(String scheduleGroup1Key) {
        set(PROPERTY_SCHEDULEGROUP1KEY, scheduleGroup1Key);
    }

    public ManualScheduleGroup getScheduleGroup1() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP1);
    }

    public void setScheduleGroup1(ManualScheduleGroup scheduleGroup1) {
        set(PROPERTY_SCHEDULEGROUP1, scheduleGroup1);
    }

    public String getScheduleGroup2Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP2KEY);
    }

    public void setScheduleGroup2Key(String scheduleGroup2Key) {
        set(PROPERTY_SCHEDULEGROUP2KEY, scheduleGroup2Key);
    }

    public ManualScheduleGroup getScheduleGroup2() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP2);
    }

    public void setScheduleGroup2(ManualScheduleGroup scheduleGroup2) {
        set(PROPERTY_SCHEDULEGROUP2, scheduleGroup2);
    }

    public String getScheduleGroup3Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP3KEY);
    }

    public void setScheduleGroup3Key(String scheduleGroup3Key) {
        set(PROPERTY_SCHEDULEGROUP3KEY, scheduleGroup3Key);
    }

    public ManualScheduleGroup getScheduleGroup3() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP3);
    }

    public void setScheduleGroup3(ManualScheduleGroup scheduleGroup3) {
        set(PROPERTY_SCHEDULEGROUP3, scheduleGroup3);
    }

    public String getScheduleGroup4Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP4KEY);
    }

    public void setScheduleGroup4Key(String scheduleGroup4Key) {
        set(PROPERTY_SCHEDULEGROUP4KEY, scheduleGroup4Key);
    }

    public ManualScheduleGroup getScheduleGroup4() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP4);
    }

    public void setScheduleGroup4(ManualScheduleGroup scheduleGroup4) {
        set(PROPERTY_SCHEDULEGROUP4, scheduleGroup4);
    }

    public String getScheduleGroup5Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP5KEY);
    }

    public void setScheduleGroup5Key(String scheduleGroup5Key) {
        set(PROPERTY_SCHEDULEGROUP5KEY, scheduleGroup5Key);
    }

    public ManualScheduleGroup getScheduleGroup5() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP5);
    }

    public void setScheduleGroup5(ManualScheduleGroup scheduleGroup5) {
        set(PROPERTY_SCHEDULEGROUP5, scheduleGroup5);
    }

    public String getScheduleGroup6Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP6KEY);
    }

    public void setScheduleGroup6Key(String scheduleGroup6Key) {
        set(PROPERTY_SCHEDULEGROUP6KEY, scheduleGroup6Key);
    }

    public ManualScheduleGroup getScheduleGroup6() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP6);
    }

    public void setScheduleGroup6(ManualScheduleGroup scheduleGroup6) {
        set(PROPERTY_SCHEDULEGROUP6, scheduleGroup6);
    }

    public String getScheduleGroup7Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP7KEY);
    }

    public void setScheduleGroup7Key(String scheduleGroup7Key) {
        set(PROPERTY_SCHEDULEGROUP7KEY, scheduleGroup7Key);
    }

    public ManualScheduleGroup getScheduleGroup7() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP7);
    }

    public void setScheduleGroup7(ManualScheduleGroup scheduleGroup7) {
        set(PROPERTY_SCHEDULEGROUP7, scheduleGroup7);
    }

    public String getScheduleGroup8Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP8KEY);
    }

    public void setScheduleGroup8Key(String scheduleGroup8Key) {
        set(PROPERTY_SCHEDULEGROUP8KEY, scheduleGroup8Key);
    }

    public ManualScheduleGroup getScheduleGroup8() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP8);
    }

    public void setScheduleGroup8(ManualScheduleGroup scheduleGroup8) {
        set(PROPERTY_SCHEDULEGROUP8, scheduleGroup8);
    }

    public String getScheduleGroup9Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP9KEY);
    }

    public void setScheduleGroup9Key(String scheduleGroup9Key) {
        set(PROPERTY_SCHEDULEGROUP9KEY, scheduleGroup9Key);
    }

    public ManualScheduleGroup getScheduleGroup9() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP9);
    }

    public void setScheduleGroup9(ManualScheduleGroup scheduleGroup9) {
        set(PROPERTY_SCHEDULEGROUP9, scheduleGroup9);
    }

    public String getScheduleGroup10Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP10KEY);
    }

    public void setScheduleGroup10Key(String scheduleGroup10Key) {
        set(PROPERTY_SCHEDULEGROUP10KEY, scheduleGroup10Key);
    }

    public ManualScheduleGroup getScheduleGroup10() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP10);
    }

    public void setScheduleGroup10(ManualScheduleGroup scheduleGroup10) {
        set(PROPERTY_SCHEDULEGROUP10, scheduleGroup10);
    }

    public String getScheduleGroup11Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP11KEY);
    }

    public void setScheduleGroup11Key(String scheduleGroup11Key) {
        set(PROPERTY_SCHEDULEGROUP11KEY, scheduleGroup11Key);
    }

    public ManualScheduleGroup getScheduleGroup11() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP11);
    }

    public void setScheduleGroup11(ManualScheduleGroup scheduleGroup11) {
        set(PROPERTY_SCHEDULEGROUP11, scheduleGroup11);
    }

    public String getScheduleGroup12Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP12KEY);
    }

    public void setScheduleGroup12Key(String scheduleGroup12Key) {
        set(PROPERTY_SCHEDULEGROUP12KEY, scheduleGroup12Key);
    }

    public ManualScheduleGroup getScheduleGroup12() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP12);
    }

    public void setScheduleGroup12(ManualScheduleGroup scheduleGroup12) {
        set(PROPERTY_SCHEDULEGROUP12, scheduleGroup12);
    }

    public String getScheduleGroup13Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP13KEY);
    }

    public void setScheduleGroup13Key(String scheduleGroup13Key) {
        set(PROPERTY_SCHEDULEGROUP13KEY, scheduleGroup13Key);
    }

    public ManualScheduleGroup getScheduleGroup13() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP13);
    }

    public void setScheduleGroup13(ManualScheduleGroup scheduleGroup13) {
        set(PROPERTY_SCHEDULEGROUP13, scheduleGroup13);
    }

    public String getScheduleGroup14Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP14KEY);
    }

    public void setScheduleGroup14Key(String scheduleGroup14Key) {
        set(PROPERTY_SCHEDULEGROUP14KEY, scheduleGroup14Key);
    }

    public ManualScheduleGroup getScheduleGroup14() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP14);
    }

    public void setScheduleGroup14(ManualScheduleGroup scheduleGroup14) {
        set(PROPERTY_SCHEDULEGROUP14, scheduleGroup14);
    }

    public String getScheduleGroup15Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP15KEY);
    }

    public void setScheduleGroup15Key(String scheduleGroup15Key) {
        set(PROPERTY_SCHEDULEGROUP15KEY, scheduleGroup15Key);
    }

    public ManualScheduleGroup getScheduleGroup15() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP15);
    }

    public void setScheduleGroup15(ManualScheduleGroup scheduleGroup15) {
        set(PROPERTY_SCHEDULEGROUP15, scheduleGroup15);
    }

    public String getScheduleGroup16Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP16KEY);
    }

    public void setScheduleGroup16Key(String scheduleGroup16Key) {
        set(PROPERTY_SCHEDULEGROUP16KEY, scheduleGroup16Key);
    }

    public ManualScheduleGroup getScheduleGroup16() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP16);
    }

    public void setScheduleGroup16(ManualScheduleGroup scheduleGroup16) {
        set(PROPERTY_SCHEDULEGROUP16, scheduleGroup16);
    }

    public String getScheduleGroup17Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP17KEY);
    }

    public void setScheduleGroup17Key(String scheduleGroup17Key) {
        set(PROPERTY_SCHEDULEGROUP17KEY, scheduleGroup17Key);
    }

    public ManualScheduleGroup getScheduleGroup17() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP17);
    }

    public void setScheduleGroup17(ManualScheduleGroup scheduleGroup17) {
        set(PROPERTY_SCHEDULEGROUP17, scheduleGroup17);
    }

    public String getScheduleGroup18Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP18KEY);
    }

    public void setScheduleGroup18Key(String scheduleGroup18Key) {
        set(PROPERTY_SCHEDULEGROUP18KEY, scheduleGroup18Key);
    }

    public ManualScheduleGroup getScheduleGroup18() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP18);
    }

    public void setScheduleGroup18(ManualScheduleGroup scheduleGroup18) {
        set(PROPERTY_SCHEDULEGROUP18, scheduleGroup18);
    }

    public String getScheduleGroup19Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP19KEY);
    }

    public void setScheduleGroup19Key(String scheduleGroup19Key) {
        set(PROPERTY_SCHEDULEGROUP19KEY, scheduleGroup19Key);
    }

    public ManualScheduleGroup getScheduleGroup19() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP19);
    }

    public void setScheduleGroup19(ManualScheduleGroup scheduleGroup19) {
        set(PROPERTY_SCHEDULEGROUP19, scheduleGroup19);
    }

    public String getScheduleGroup20Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP20KEY);
    }

    public void setScheduleGroup20Key(String scheduleGroup20Key) {
        set(PROPERTY_SCHEDULEGROUP20KEY, scheduleGroup20Key);
    }

    public ManualScheduleGroup getScheduleGroup20() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP20);
    }

    public void setScheduleGroup20(ManualScheduleGroup scheduleGroup20) {
        set(PROPERTY_SCHEDULEGROUP20, scheduleGroup20);
    }

    public String getScheduleGroup21Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP21KEY);
    }

    public void setScheduleGroup21Key(String scheduleGroup21Key) {
        set(PROPERTY_SCHEDULEGROUP21KEY, scheduleGroup21Key);
    }

    public ManualScheduleGroup getScheduleGroup21() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP21);
    }

    public void setScheduleGroup21(ManualScheduleGroup scheduleGroup21) {
        set(PROPERTY_SCHEDULEGROUP21, scheduleGroup21);
    }

    public String getScheduleGroup22Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP22KEY);
    }

    public void setScheduleGroup22Key(String scheduleGroup22Key) {
        set(PROPERTY_SCHEDULEGROUP22KEY, scheduleGroup22Key);
    }

    public ManualScheduleGroup getScheduleGroup22() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP22);
    }

    public void setScheduleGroup22(ManualScheduleGroup scheduleGroup22) {
        set(PROPERTY_SCHEDULEGROUP22, scheduleGroup22);
    }

    public String getScheduleGroup23Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP23KEY);
    }

    public void setScheduleGroup23Key(String scheduleGroup23Key) {
        set(PROPERTY_SCHEDULEGROUP23KEY, scheduleGroup23Key);
    }

    public ManualScheduleGroup getScheduleGroup23() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP23);
    }

    public void setScheduleGroup23(ManualScheduleGroup scheduleGroup23) {
        set(PROPERTY_SCHEDULEGROUP23, scheduleGroup23);
    }

    public String getScheduleGroup24Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP24KEY);
    }

    public void setScheduleGroup24Key(String scheduleGroup24Key) {
        set(PROPERTY_SCHEDULEGROUP24KEY, scheduleGroup24Key);
    }

    public ManualScheduleGroup getScheduleGroup24() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP24);
    }

    public void setScheduleGroup24(ManualScheduleGroup scheduleGroup24) {
        set(PROPERTY_SCHEDULEGROUP24, scheduleGroup24);
    }

    public String getScheduleGroup25Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP25KEY);
    }

    public void setScheduleGroup25Key(String scheduleGroup25Key) {
        set(PROPERTY_SCHEDULEGROUP25KEY, scheduleGroup25Key);
    }

    public ManualScheduleGroup getScheduleGroup25() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP25);
    }

    public void setScheduleGroup25(ManualScheduleGroup scheduleGroup25) {
        set(PROPERTY_SCHEDULEGROUP25, scheduleGroup25);
    }

    public String getScheduleGroup26Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP26KEY);
    }

    public void setScheduleGroup26Key(String scheduleGroup26Key) {
        set(PROPERTY_SCHEDULEGROUP26KEY, scheduleGroup26Key);
    }

    public ManualScheduleGroup getScheduleGroup26() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP26);
    }

    public void setScheduleGroup26(ManualScheduleGroup scheduleGroup26) {
        set(PROPERTY_SCHEDULEGROUP26, scheduleGroup26);
    }

    public String getScheduleGroup27Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP27KEY);
    }

    public void setScheduleGroup27Key(String scheduleGroup27Key) {
        set(PROPERTY_SCHEDULEGROUP27KEY, scheduleGroup27Key);
    }

    public ManualScheduleGroup getScheduleGroup27() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP27);
    }

    public void setScheduleGroup27(ManualScheduleGroup scheduleGroup27) {
        set(PROPERTY_SCHEDULEGROUP27, scheduleGroup27);
    }

    public String getScheduleGroup28Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP28KEY);
    }

    public void setScheduleGroup28Key(String scheduleGroup28Key) {
        set(PROPERTY_SCHEDULEGROUP28KEY, scheduleGroup28Key);
    }

    public ManualScheduleGroup getScheduleGroup28() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP28);
    }

    public void setScheduleGroup28(ManualScheduleGroup scheduleGroup28) {
        set(PROPERTY_SCHEDULEGROUP28, scheduleGroup28);
    }

    public String getScheduleGroup29Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP29KEY);
    }

    public void setScheduleGroup29Key(String scheduleGroup29Key) {
        set(PROPERTY_SCHEDULEGROUP29KEY, scheduleGroup29Key);
    }

    public ManualScheduleGroup getScheduleGroup29() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP29);
    }

    public void setScheduleGroup29(ManualScheduleGroup scheduleGroup29) {
        set(PROPERTY_SCHEDULEGROUP29, scheduleGroup29);
    }

    public String getScheduleGroup30Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP30KEY);
    }

    public void setScheduleGroup30Key(String scheduleGroup30Key) {
        set(PROPERTY_SCHEDULEGROUP30KEY, scheduleGroup30Key);
    }

    public ManualScheduleGroup getScheduleGroup30() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP30);
    }

    public void setScheduleGroup30(ManualScheduleGroup scheduleGroup30) {
        set(PROPERTY_SCHEDULEGROUP30, scheduleGroup30);
    }

    public String getScheduleGroup31Key() {
        return (String) get(PROPERTY_SCHEDULEGROUP31KEY);
    }

    public void setScheduleGroup31Key(String scheduleGroup31Key) {
        set(PROPERTY_SCHEDULEGROUP31KEY, scheduleGroup31Key);
    }

    public ManualScheduleGroup getScheduleGroup31() {
        return (ManualScheduleGroup) get(PROPERTY_SCHEDULEGROUP31);
    }

    public void setScheduleGroup31(ManualScheduleGroup scheduleGroup31) {
        set(PROPERTY_SCHEDULEGROUP31, scheduleGroup31);
    }

}
