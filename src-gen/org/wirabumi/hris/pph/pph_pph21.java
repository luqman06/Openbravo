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
import org.openbravo.model.common.enterprise.Organization;
import org.wirabumi.hris.payroll.pyr_sp_employee;
/**
 * Entity class for entity pph_pph21 (stored in table pph_pph21).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class pph_pph21 extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "pph_pph21";
    public static final String ENTITY_NAME = "pph_pph21";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_PYRSPEMPLOYEE = "pYRSpEmployee";
    public static final String PROPERTY_TAXMARITALSTATUS = "taxMaritalStatus";
    public static final String PROPERTY_PENDAPATANRUTINGROSSSETAHUN = "pendapatanRutinGrossSetahun";
    public static final String PROPERTY_BIAYAJABATANSEBULAN = "biayaJabatanSebulan";
    public static final String PROPERTY_PTKPSETAHUN = "pTKPSetahun";
    public static final String PROPERTY_PKPSETAHUN = "pKPSetahun";
    public static final String PROPERTY_PPH21DIBAYARSAMPAIDENGANBULANSEBELUMNYA = "pPh21DibayarSampaiDenganBulanSebelumnya";
    public static final String PROPERTY_PPH21SETAHUN = "pPh21Setahun";
    public static final String PROPERTY_PPH21TERHUTANGBULANINI = "pPh21TerhutangBulanIni";
    public static final String PROPERTY_PPH21TERPOTONGBULANINI = "pPh21TerpotongBulanIni";
    public static final String PROPERTY_MONTH = "month";
    public static final String PROPERTY_FISCALYEAR = "fiscalYear";
    public static final String PROPERTY_PPH21DIBAYARBULANLALU = "pPh21DibayarBulanLalu";
    public static final String PROPERTY_PENDAPATANBRUTOSETAHUNGROSS = "pendapatanBrutoSetahunGross";
    public static final String PROPERTY_PENDAPATANLAIN = "pendapatanLain";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_PENDAPATANLAINGROSS = "pendapatanLainGross";
    public static final String PROPERTY_TUNJANGANPPH21BULANINI = "tunjanganPPh21BulanIni";
    public static final String PROPERTY_PENGURANGPAJAKSETAHUN = "pengurangPajakSetahun";
    public static final String PROPERTY_BIAYAJABATANSETAHUN = "biayaJabatanSetahun";
    public static final String PROPERTY_PENDAPATANLAINSAJA = "pendapatanLainSaja";
    public static final String PROPERTY_PENDAPATANRUTINGROSSSEBULAN = "pendapatanRutinGrossSebulan";
    public static final String PROPERTY_PENDAPATANRUTINNETTSEBULAN = "pendapatanRutinNettSebulan";
    public static final String PROPERTY_PENDAPATANRUTINNETTSETAHUN = "pendapatanRutinNettSetahun";
    public static final String PROPERTY_PENDAPATANLAINNETT = "pendapatanLainNett";
    public static final String PROPERTY_PENGURANGPAJAKSEBULAN = "pengurangPajakSebulan";
    public static final String PROPERTY_PAJAKRAMPUNG = "pajakRampung";
    public static final String PROPERTY_PPH21BULANLALU = "pPh21BulanLalu";
    public static final String PROPERTY_PENDAPATANBRUTOTOTALSETAHUN = "pendapatanBrutoTotalSetahun";
    public static final String PROPERTY_PENDAPATANRUTINGROSSSETAHUNPADABULANLALU = "pendapatanRutinGrossSetahunPadaBulanLalu";
    public static final String PROPERTY_PENDAPATANRUTINGROSSSEBULANPADABULANLALU = "pendapatanRutinGrossSebulanPadaBulanLalu";
    public static final String PROPERTY_PENDAPATANRUTINNETTSEBULANPADABULANLALU = "pendapatanRutinNettSebulanPadaBulanLalu";
    public static final String PROPERTY_PENDAPATANLAINGROSSBULANLALU = "pendapatanLainGrossBulanLalu";
    public static final String PROPERTY_PENDAPATANLAINNETTBULANLALU = "pendapatanLainNettBulanLalu";
    public static final String PROPERTY_PENGURANGPAJAKBULANLALU = "pengurangPajakBulanLalu";
    public static final String PROPERTY_MASAPPH = "masaPPh";
    public static final String PROPERTY_PPHIPPH21LIST = "pphIPph21List";
    public static final String PROPERTY_PPHPPH21PPH21BULANLALULIST = "pphPph21PPh21BulanLaluList";

    public pph_pph21() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PENDAPATANLAINSAJA, false);
        setDefaultValue(PROPERTY_PAJAKRAMPUNG, false);
        setDefaultValue(PROPERTY_PENDAPATANBRUTOTOTALSETAHUN, new BigDecimal(0));
        setDefaultValue(PROPERTY_PENDAPATANRUTINGROSSSETAHUNPADABULANLALU, new BigDecimal(0));
        setDefaultValue(PROPERTY_PENDAPATANRUTINGROSSSEBULANPADABULANLALU, new BigDecimal(0));
        setDefaultValue(PROPERTY_PENDAPATANRUTINNETTSEBULANPADABULANLALU, new BigDecimal(0));
        setDefaultValue(PROPERTY_PENDAPATANLAINGROSSBULANLALU, new BigDecimal(0));
        setDefaultValue(PROPERTY_PENDAPATANLAINNETTBULANLALU, new BigDecimal(0));
        setDefaultValue(PROPERTY_PENGURANGPAJAKBULANLALU, new BigDecimal(0));
        setDefaultValue(PROPERTY_PPHIPPH21LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PPHPPH21PPH21BULANLALULIST, new ArrayList<Object>());
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

    public BusinessPartner getEmployee() {
        return (BusinessPartner) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(BusinessPartner employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public pyr_sp_employee getPYRSpEmployee() {
        return (pyr_sp_employee) get(PROPERTY_PYRSPEMPLOYEE);
    }

    public void setPYRSpEmployee(pyr_sp_employee pYRSpEmployee) {
        set(PROPERTY_PYRSPEMPLOYEE, pYRSpEmployee);
    }

    public String getTaxMaritalStatus() {
        return (String) get(PROPERTY_TAXMARITALSTATUS);
    }

    public void setTaxMaritalStatus(String taxMaritalStatus) {
        set(PROPERTY_TAXMARITALSTATUS, taxMaritalStatus);
    }

    public BigDecimal getPendapatanRutinGrossSetahun() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINGROSSSETAHUN);
    }

    public void setPendapatanRutinGrossSetahun(BigDecimal pendapatanRutinGrossSetahun) {
        set(PROPERTY_PENDAPATANRUTINGROSSSETAHUN, pendapatanRutinGrossSetahun);
    }

    public BigDecimal getBiayaJabatanSebulan() {
        return (BigDecimal) get(PROPERTY_BIAYAJABATANSEBULAN);
    }

    public void setBiayaJabatanSebulan(BigDecimal biayaJabatanSebulan) {
        set(PROPERTY_BIAYAJABATANSEBULAN, biayaJabatanSebulan);
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

    public Long getMonth() {
        return (Long) get(PROPERTY_MONTH);
    }

    public void setMonth(Long month) {
        set(PROPERTY_MONTH, month);
    }

    public Long getFiscalYear() {
        return (Long) get(PROPERTY_FISCALYEAR);
    }

    public void setFiscalYear(Long fiscalYear) {
        set(PROPERTY_FISCALYEAR, fiscalYear);
    }

    public BigDecimal getPPh21DibayarBulanLalu() {
        return (BigDecimal) get(PROPERTY_PPH21DIBAYARBULANLALU);
    }

    public void setPPh21DibayarBulanLalu(BigDecimal pPh21DibayarBulanLalu) {
        set(PROPERTY_PPH21DIBAYARBULANLALU, pPh21DibayarBulanLalu);
    }

    public BigDecimal getPendapatanBrutoSetahunGross() {
        return (BigDecimal) get(PROPERTY_PENDAPATANBRUTOSETAHUNGROSS);
    }

    public void setPendapatanBrutoSetahunGross(BigDecimal pendapatanBrutoSetahunGross) {
        set(PROPERTY_PENDAPATANBRUTOSETAHUNGROSS, pendapatanBrutoSetahunGross);
    }

    public BigDecimal getPendapatanLain() {
        return (BigDecimal) get(PROPERTY_PENDAPATANLAIN);
    }

    public void setPendapatanLain(BigDecimal pendapatanLain) {
        set(PROPERTY_PENDAPATANLAIN, pendapatanLain);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public BigDecimal getPendapatanLainGross() {
        return (BigDecimal) get(PROPERTY_PENDAPATANLAINGROSS);
    }

    public void setPendapatanLainGross(BigDecimal pendapatanLainGross) {
        set(PROPERTY_PENDAPATANLAINGROSS, pendapatanLainGross);
    }

    public BigDecimal getTunjanganPPh21BulanIni() {
        return (BigDecimal) get(PROPERTY_TUNJANGANPPH21BULANINI);
    }

    public void setTunjanganPPh21BulanIni(BigDecimal tunjanganPPh21BulanIni) {
        set(PROPERTY_TUNJANGANPPH21BULANINI, tunjanganPPh21BulanIni);
    }

    public BigDecimal getPengurangPajakSetahun() {
        return (BigDecimal) get(PROPERTY_PENGURANGPAJAKSETAHUN);
    }

    public void setPengurangPajakSetahun(BigDecimal pengurangPajakSetahun) {
        set(PROPERTY_PENGURANGPAJAKSETAHUN, pengurangPajakSetahun);
    }

    public BigDecimal getBiayaJabatanSetahun() {
        return (BigDecimal) get(PROPERTY_BIAYAJABATANSETAHUN);
    }

    public void setBiayaJabatanSetahun(BigDecimal biayaJabatanSetahun) {
        set(PROPERTY_BIAYAJABATANSETAHUN, biayaJabatanSetahun);
    }

    public Boolean isPendapatanLainSaja() {
        return (Boolean) get(PROPERTY_PENDAPATANLAINSAJA);
    }

    public void setPendapatanLainSaja(Boolean pendapatanLainSaja) {
        set(PROPERTY_PENDAPATANLAINSAJA, pendapatanLainSaja);
    }

    public BigDecimal getPendapatanRutinGrossSebulan() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINGROSSSEBULAN);
    }

    public void setPendapatanRutinGrossSebulan(BigDecimal pendapatanRutinGrossSebulan) {
        set(PROPERTY_PENDAPATANRUTINGROSSSEBULAN, pendapatanRutinGrossSebulan);
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

    public BigDecimal getPendapatanLainNett() {
        return (BigDecimal) get(PROPERTY_PENDAPATANLAINNETT);
    }

    public void setPendapatanLainNett(BigDecimal pendapatanLainNett) {
        set(PROPERTY_PENDAPATANLAINNETT, pendapatanLainNett);
    }

    public BigDecimal getPengurangPajakSebulan() {
        return (BigDecimal) get(PROPERTY_PENGURANGPAJAKSEBULAN);
    }

    public void setPengurangPajakSebulan(BigDecimal pengurangPajakSebulan) {
        set(PROPERTY_PENGURANGPAJAKSEBULAN, pengurangPajakSebulan);
    }

    public Boolean isPajakRampung() {
        return (Boolean) get(PROPERTY_PAJAKRAMPUNG);
    }

    public void setPajakRampung(Boolean pajakRampung) {
        set(PROPERTY_PAJAKRAMPUNG, pajakRampung);
    }

    public pph_pph21 getPPh21BulanLalu() {
        return (pph_pph21) get(PROPERTY_PPH21BULANLALU);
    }

    public void setPPh21BulanLalu(pph_pph21 pPh21BulanLalu) {
        set(PROPERTY_PPH21BULANLALU, pPh21BulanLalu);
    }

    public BigDecimal getPendapatanBrutoTotalSetahun() {
        return (BigDecimal) get(PROPERTY_PENDAPATANBRUTOTOTALSETAHUN);
    }

    public void setPendapatanBrutoTotalSetahun(BigDecimal pendapatanBrutoTotalSetahun) {
        set(PROPERTY_PENDAPATANBRUTOTOTALSETAHUN, pendapatanBrutoTotalSetahun);
    }

    public BigDecimal getPendapatanRutinGrossSetahunPadaBulanLalu() {
        return (BigDecimal) get(PROPERTY_PENDAPATANRUTINGROSSSETAHUNPADABULANLALU);
    }

    public void setPendapatanRutinGrossSetahunPadaBulanLalu(BigDecimal pendapatanRutinGrossSetahunPadaBulanLalu) {
        set(PROPERTY_PENDAPATANRUTINGROSSSETAHUNPADABULANLALU, pendapatanRutinGrossSetahunPadaBulanLalu);
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

    public Long getMasaPPh() {
        return (Long) get(PROPERTY_MASAPPH);
    }

    public void setMasaPPh(Long masaPPh) {
        set(PROPERTY_MASAPPH, masaPPh);
    }

    @SuppressWarnings("unchecked")
    public List<ImportPPh21> getPphIPph21List() {
      return (List<ImportPPh21>) get(PROPERTY_PPHIPPH21LIST);
    }

    public void setPphIPph21List(List<ImportPPh21> pphIPph21List) {
        set(PROPERTY_PPHIPPH21LIST, pphIPph21List);
    }

    @SuppressWarnings("unchecked")
    public List<pph_pph21> getPphPph21PPh21BulanLaluList() {
      return (List<pph_pph21>) get(PROPERTY_PPHPPH21PPH21BULANLALULIST);
    }

    public void setPphPph21PPh21BulanLaluList(List<pph_pph21> pphPph21PPh21BulanLaluList) {
        set(PROPERTY_PPHPPH21PPH21BULANLALULIST, pphPph21PPh21BulanLaluList);
    }

}
