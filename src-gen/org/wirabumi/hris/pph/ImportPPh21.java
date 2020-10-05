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
package org.wirabumi.hris.pph;

import java.math.BigDecimal;
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
import org.openbravo.model.financialmgmt.calendar.Year;
/**
 * Entity class for entity pph_i_pph21 (stored in table pph_i_pph21).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ImportPPh21 extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "pph_i_pph21";
    public static final String ENTITY_NAME = "pph_i_pph21";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEEKEY = "employeekey";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_TAXMARITALSTATUS = "taxMaritalStatus";
    public static final String PROPERTY_FISCALYEAR = "fiscalyear";
    public static final String PROPERTY_YEAR = "year";
    public static final String PROPERTY_MONTH = "month";
    public static final String PROPERTY_MASAPPH = "masaPPh";
    public static final String PROPERTY_PAJAKRAMPUNG = "pajakRampung";
    public static final String PROPERTY_PENDAPATANRUTINGROSSSEBULAN = "pendapatanRutinGrossSebulan";
    public static final String PROPERTY_PENDAPATANRUTINGROSSSETAHUN = "pendapatanRutinGrossSetahun";
    public static final String PROPERTY_PENDAPATANRUTINNETTSEBULAN = "pendapatanRutinNettSebulan";
    public static final String PROPERTY_PENDAPATANRUTINNETTSETAHUN = "pendapatanRutinNettSetahun";
    public static final String PROPERTY_PENDAPATANLAINGROSS = "pendapatanLainGross";
    public static final String PROPERTY_PENDAPATANLAINNETT = "pendapatanLainNett";
    public static final String PROPERTY_PENDAPATANBRUTOTOTALSETAHUN = "pendapatanBrutoTotalSetahun";
    public static final String PROPERTY_BIAYAJABATANSETAHUN = "biayaJabatanSetahun";
    public static final String PROPERTY_PENGURANGPAJAKSETAHUN = "pengurangPajakSetahun";
    public static final String PROPERTY_PTKPSETAHUN = "pTKPSetahun";
    public static final String PROPERTY_PKPSETAHUN = "pKPSetahun";
    public static final String PROPERTY_PENDAPATANLAINSAJA = "pendapatanLainSaja";
    public static final String PROPERTY_PENDAPATANRUTINGROSSSEBULANPADABULANLALU = "pendapatanRutinGrossSebulanPadaBulanLalu";
    public static final String PROPERTY_PENDAPATANRUTINNETTSEBULANPADABULANLALU = "pendapatanRutinNettSebulanPadaBulanLalu";
    public static final String PROPERTY_PENDAPATANLAINGROSSBULANLALU = "pendapatanLainGrossBulanLalu";
    public static final String PROPERTY_PENDAPATANLAINNETTBULANLALU = "pendapatanLainNettBulanLalu";
    public static final String PROPERTY_PENGURANGPAJAKBULANLALU = "pengurangPajakBulanLalu";
    public static final String PROPERTY_PPH21DIBAYARBULANLALU = "pPh21DibayarBulanLalu";
    public static final String PROPERTY_PPH21DIBAYARSAMPAIDENGANBULANSEBELUMNYA = "pPh21DibayarSampaiDenganBulanSebelumnya";
    public static final String PROPERTY_PPH21SETAHUN = "pPh21Setahun";
    public static final String PROPERTY_PPH21TERHUTANGBULANINI = "pPh21TerhutangBulanIni";
    public static final String PROPERTY_PPH21TERPOTONGBULANINI = "pPh21TerpotongBulanIni";
    public static final String PROPERTY_TUNJANGANPPH21BULANINI = "tunjanganPPh21BulanIni";
    public static final String PROPERTY_PPH21 = "pPh21";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_PROCESSED = "processed";

    public ImportPPh21() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PAJAKRAMPUNG, false);
        setDefaultValue(PROPERTY_PENDAPATANLAINSAJA, false);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
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

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public String getTaxMaritalStatus() {
        return (String) get(PROPERTY_TAXMARITALSTATUS);
    }

    public void setTaxMaritalStatus(String taxMaritalStatus) {
        set(PROPERTY_TAXMARITALSTATUS, taxMaritalStatus);
    }

    public String getFiscalyear() {
        return (String) get(PROPERTY_FISCALYEAR);
    }

    public void setFiscalyear(String fiscalyear) {
        set(PROPERTY_FISCALYEAR, fiscalyear);
    }

    public Year getYear() {
        return (Year) get(PROPERTY_YEAR);
    }

    public void setYear(Year year) {
        set(PROPERTY_YEAR, year);
    }

    public Long getMonth() {
        return (Long) get(PROPERTY_MONTH);
    }

    public void setMonth(Long month) {
        set(PROPERTY_MONTH, month);
    }

    public Long getMasaPPh() {
        return (Long) get(PROPERTY_MASAPPH);
    }

    public void setMasaPPh(Long masaPPh) {
        set(PROPERTY_MASAPPH, masaPPh);
    }

    public Boolean isPajakRampung() {
        return (Boolean) get(PROPERTY_PAJAKRAMPUNG);
    }

    public void setPajakRampung(Boolean pajakRampung) {
        set(PROPERTY_PAJAKRAMPUNG, pajakRampung);
    }

    public BigDecimal getPendapatanRutinGrossSebulan() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINGROSSSEBULAN);
    }

    public void setPendapatanRutinGrossSebulan(BigDecimal pendapatanRutinGrossSebulan) {
        set(PROPERTY_PENDAPATANRUTINGROSSSEBULAN, pendapatanRutinGrossSebulan);
    }

    public BigDecimal getPendapatanRutinGrossSetahun() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINGROSSSETAHUN);
    }

    public void setPendapatanRutinGrossSetahun(BigDecimal pendapatanRutinGrossSetahun) {
        set(PROPERTY_PENDAPATANRUTINGROSSSETAHUN, pendapatanRutinGrossSetahun);
    }

    public BigDecimal getPendapatanRutinNettSebulan() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINNETTSEBULAN);
    }

    public void setPendapatanRutinNettSebulan(BigDecimal pendapatanRutinNettSebulan) {
        set(PROPERTY_PENDAPATANRUTINNETTSEBULAN, pendapatanRutinNettSebulan);
    }

    public BigDecimal getPendapatanRutinNettSetahun() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINNETTSETAHUN);
    }

    public void setPendapatanRutinNettSetahun(BigDecimal pendapatanRutinNettSetahun) {
        set(PROPERTY_PENDAPATANRUTINNETTSETAHUN, pendapatanRutinNettSetahun);
    }

    public BigDecimal getPendapatanLainGross() {
        return (BigDecimal) get(PROPERTY_PENDAPATANLAINGROSS);
    }

    public void setPendapatanLainGross(BigDecimal pendapatanLainGross) {
        set(PROPERTY_PENDAPATANLAINGROSS, pendapatanLainGross);
    }

    public BigDecimal getPendapatanLainNett() {
        return (BigDecimal) get(PROPERTY_PENDAPATANLAINNETT);
    }

    public void setPendapatanLainNett(BigDecimal pendapatanLainNett) {
        set(PROPERTY_PENDAPATANLAINNETT, pendapatanLainNett);
    }

    public BigDecimal getPendapatanBrutoTotalSetahun() {
        return (BigDecimal) get(PROPERTY_PENDAPATANBRUTOTOTALSETAHUN);
    }

    public void setPendapatanBrutoTotalSetahun(BigDecimal pendapatanBrutoTotalSetahun) {
        set(PROPERTY_PENDAPATANBRUTOTOTALSETAHUN, pendapatanBrutoTotalSetahun);
    }

    public BigDecimal getBiayaJabatanSetahun() {
        return (BigDecimal) get(PROPERTY_BIAYAJABATANSETAHUN);
    }

    public void setBiayaJabatanSetahun(BigDecimal biayaJabatanSetahun) {
        set(PROPERTY_BIAYAJABATANSETAHUN, biayaJabatanSetahun);
    }

    public BigDecimal getPengurangPajakSetahun() {
        return (BigDecimal) get(PROPERTY_PENGURANGPAJAKSETAHUN);
    }

    public void setPengurangPajakSetahun(BigDecimal pengurangPajakSetahun) {
        set(PROPERTY_PENGURANGPAJAKSETAHUN, pengurangPajakSetahun);
    }

    public BigDecimal getPTKPSetahun() {
        return (BigDecimal) get(PROPERTY_PTKPSETAHUN);
    }

    public void setPTKPSetahun(BigDecimal pTKPSetahun) {
        set(PROPERTY_PTKPSETAHUN, pTKPSetahun);
    }

    public BigDecimal getPKPSetahun() {
        return (BigDecimal) get(PROPERTY_PKPSETAHUN);
    }

    public void setPKPSetahun(BigDecimal pKPSetahun) {
        set(PROPERTY_PKPSETAHUN, pKPSetahun);
    }

    public Boolean isPendapatanLainSaja() {
        return (Boolean) get(PROPERTY_PENDAPATANLAINSAJA);
    }

    public void setPendapatanLainSaja(Boolean pendapatanLainSaja) {
        set(PROPERTY_PENDAPATANLAINSAJA, pendapatanLainSaja);
    }

    public BigDecimal getPendapatanRutinGrossSebulanPadaBulanLalu() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINGROSSSEBULANPADABULANLALU);
    }

    public void setPendapatanRutinGrossSebulanPadaBulanLalu(BigDecimal pendapatanRutinGrossSebulanPadaBulanLalu) {
        set(PROPERTY_PENDAPATANRUTINGROSSSEBULANPADABULANLALU, pendapatanRutinGrossSebulanPadaBulanLalu);
    }

    public BigDecimal getPendapatanRutinNettSebulanPadaBulanLalu() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINNETTSEBULANPADABULANLALU);
    }

    public void setPendapatanRutinNettSebulanPadaBulanLalu(BigDecimal pendapatanRutinNettSebulanPadaBulanLalu) {
        set(PROPERTY_PENDAPATANRUTINNETTSEBULANPADABULANLALU, pendapatanRutinNettSebulanPadaBulanLalu);
    }

    public BigDecimal getPendapatanLainGrossBulanLalu() {
        return (BigDecimal) get(PROPERTY_PENDAPATANLAINGROSSBULANLALU);
    }

    public void setPendapatanLainGrossBulanLalu(BigDecimal pendapatanLainGrossBulanLalu) {
        set(PROPERTY_PENDAPATANLAINGROSSBULANLALU, pendapatanLainGrossBulanLalu);
    }

    public BigDecimal getPendapatanLainNettBulanLalu() {
        return (BigDecimal) get(PROPERTY_PENDAPATANLAINNETTBULANLALU);
    }

    public void setPendapatanLainNettBulanLalu(BigDecimal pendapatanLainNettBulanLalu) {
        set(PROPERTY_PENDAPATANLAINNETTBULANLALU, pendapatanLainNettBulanLalu);
    }

    public BigDecimal getPengurangPajakBulanLalu() {
        return (BigDecimal) get(PROPERTY_PENGURANGPAJAKBULANLALU);
    }

    public void setPengurangPajakBulanLalu(BigDecimal pengurangPajakBulanLalu) {
        set(PROPERTY_PENGURANGPAJAKBULANLALU, pengurangPajakBulanLalu);
    }

    public BigDecimal getPPh21DibayarBulanLalu() {
        return (BigDecimal) get(PROPERTY_PPH21DIBAYARBULANLALU);
    }

    public void setPPh21DibayarBulanLalu(BigDecimal pPh21DibayarBulanLalu) {
        set(PROPERTY_PPH21DIBAYARBULANLALU, pPh21DibayarBulanLalu);
    }

    public BigDecimal getPPh21DibayarSampaiDenganBulanSebelumnya() {
        return (BigDecimal) get(PROPERTY_PPH21DIBAYARSAMPAIDENGANBULANSEBELUMNYA);
    }

    public void setPPh21DibayarSampaiDenganBulanSebelumnya(BigDecimal pPh21DibayarSampaiDenganBulanSebelumnya) {
        set(PROPERTY_PPH21DIBAYARSAMPAIDENGANBULANSEBELUMNYA, pPh21DibayarSampaiDenganBulanSebelumnya);
    }

    public BigDecimal getPPh21Setahun() {
        return (BigDecimal) get(PROPERTY_PPH21SETAHUN);
    }

    public void setPPh21Setahun(BigDecimal pPh21Setahun) {
        set(PROPERTY_PPH21SETAHUN, pPh21Setahun);
    }

    public BigDecimal getPPh21TerhutangBulanIni() {
        return (BigDecimal) get(PROPERTY_PPH21TERHUTANGBULANINI);
    }

    public void setPPh21TerhutangBulanIni(BigDecimal pPh21TerhutangBulanIni) {
        set(PROPERTY_PPH21TERHUTANGBULANINI, pPh21TerhutangBulanIni);
    }

    public BigDecimal getPPh21TerpotongBulanIni() {
        return (BigDecimal) get(PROPERTY_PPH21TERPOTONGBULANINI);
    }

    public void setPPh21TerpotongBulanIni(BigDecimal pPh21TerpotongBulanIni) {
        set(PROPERTY_PPH21TERPOTONGBULANINI, pPh21TerpotongBulanIni);
    }

    public BigDecimal getTunjanganPPh21BulanIni() {
        return (BigDecimal) get(PROPERTY_TUNJANGANPPH21BULANINI);
    }

    public void setTunjanganPPh21BulanIni(BigDecimal tunjanganPPh21BulanIni) {
        set(PROPERTY_TUNJANGANPPH21BULANINI, tunjanganPPh21BulanIni);
    }

    public pph_pph21 getPPh21() {
        return (pph_pph21) get(PROPERTY_PPH21);
    }

    public void setPPh21(pph_pph21 pPh21) {
        set(PROPERTY_PPH21, pPh21);
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

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

}
