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
/**
 * Entity class for entity pph_1721 (stored in table pph_1721).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class pph_1721 extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "pph_1721";
    public static final String ENTITY_NAME = "pph_1721";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_NOMORURUT = "nomorUrut";
    public static final String PROPERTY_NAMAPEMOTONGPAJAK = "namaPemotongPajak";
    public static final String PROPERTY_NPWPPEMOTONGPAJAK = "npwpPemotongPajak";
    public static final String PROPERTY_NPWPPEGAWAI = "npwpPegawai";
    public static final String PROPERTY_ALAMATPEGAWAI = "alamatPegawai";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_LAKI = "laki";
    public static final String PROPERTY_PEREMPUAN = "perempuan";
    public static final String PROPERTY_PEGAWAIASING = "pegawaiAsing";
    public static final String PROPERTY_JUMLAHTANGGUNGAN = "jumlahTanggungan";
    public static final String PROPERTY_JABATAN = "jabatan";
    public static final String PROPERTY_MASAPEROLEHANPENGHASILAN = "masaPerolehanPenghasilan";
    public static final String PROPERTY_SAMPAI = "sampai";
    public static final String PROPERTY_GAJI = "gaji";
    public static final String PROPERTY_TUNJANGANPPH = "tunjanganPph";
    public static final String PROPERTY_TUNJANGANLAINNYA = "tunjanganLainnya";
    public static final String PROPERTY_HONORARIUM = "honorarium";
    public static final String PROPERTY_PREMIASURANSI = "premiAsuransi";
    public static final String PROPERTY_NATURAKENAPAJAK = "naturaKenaPajak";
    public static final String PROPERTY_TANTIEM = "tantiem";
    public static final String PROPERTY_BIAYAJABATANATASGAJI = "biayaJabatanAtasGaji";
    public static final String PROPERTY_BIAYAJABATANATASBONUS = "biayaJabatanAtasBonus";
    public static final String PROPERTY_IURANPENSIUN = "iuranPensiun";
    public static final String PROPERTY_PENGHASILANNETTOSEBELUMNYA = "penghasilanNettoSebelumnya";
    public static final String PROPERTY_PPH21SETAHUN = "pph21Setahun";
    public static final String PROPERTY_PTKP = "pTKP";
    public static final String PROPERTY_PKPSETAHUN = "pKPSetahun";
    public static final String PROPERTY_PPH21PKPSETAHUN = "pph21Pkpsetahun";
    public static final String PROPERTY_PPH21DIBAYAR = "pPh21Dibayar";
    public static final String PROPERTY_PPH21TERHUTANG = "pPh21Terhutang";
    public static final String PROPERTY_PEMOTONGPAJAK = "pemotongPajak";
    public static final String PROPERTY_KUASA = "kuasa";
    public static final String PROPERTY_NAMA = "nama";
    public static final String PROPERTY_NPWP = "npwp";
    public static final String PROPERTY_ALAMAT2PEGAWAI = "alamat2Pegawai";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_PPH21KURANG = "pph21Kurang";
    public static final String PROPERTY_PPH21LEBIH = "pph21Lebih";
    public static final String PROPERTY_HITUNGPPH21 = "hitungPph21";
    public static final String PROPERTY_DIPOTONGGAJI = "dipotongGaji";
    public static final String PROPERTY_SSPDITANGGUNG = "sSPDitanggung";
    public static final String PROPERTY_SSP = "ssp";

    public pph_1721() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_LAKI, false);
        setDefaultValue(PROPERTY_PEREMPUAN, false);
        setDefaultValue(PROPERTY_PEGAWAIASING, false);
        setDefaultValue(PROPERTY_GAJI, new BigDecimal(0));
        setDefaultValue(PROPERTY_TUNJANGANPPH, new BigDecimal(0));
        setDefaultValue(PROPERTY_TUNJANGANLAINNYA, new BigDecimal(0));
        setDefaultValue(PROPERTY_HONORARIUM, new BigDecimal(0));
        setDefaultValue(PROPERTY_PREMIASURANSI, new BigDecimal(0));
        setDefaultValue(PROPERTY_NATURAKENAPAJAK, new BigDecimal(0));
        setDefaultValue(PROPERTY_TANTIEM, new BigDecimal(0));
        setDefaultValue(PROPERTY_BIAYAJABATANATASGAJI, new BigDecimal(0));
        setDefaultValue(PROPERTY_BIAYAJABATANATASBONUS, new BigDecimal(0));
        setDefaultValue(PROPERTY_IURANPENSIUN, new BigDecimal(0));
        setDefaultValue(PROPERTY_PENGHASILANNETTOSEBELUMNYA, new BigDecimal(0));
        setDefaultValue(PROPERTY_PPH21SETAHUN, new BigDecimal(0));
        setDefaultValue(PROPERTY_PTKP, new BigDecimal(0));
        setDefaultValue(PROPERTY_PKPSETAHUN, new BigDecimal(0));
        setDefaultValue(PROPERTY_PPH21PKPSETAHUN, new BigDecimal(0));
        setDefaultValue(PROPERTY_PPH21DIBAYAR, new BigDecimal(0));
        setDefaultValue(PROPERTY_PPH21TERHUTANG, new BigDecimal(0));
        setDefaultValue(PROPERTY_PEMOTONGPAJAK, false);
        setDefaultValue(PROPERTY_KUASA, false);
        setDefaultValue(PROPERTY_PPH21KURANG, new BigDecimal(0));
        setDefaultValue(PROPERTY_PPH21LEBIH, new BigDecimal(0));
        setDefaultValue(PROPERTY_HITUNGPPH21, new BigDecimal(0));
        setDefaultValue(PROPERTY_DIPOTONGGAJI, new BigDecimal(0));
        setDefaultValue(PROPERTY_SSPDITANGGUNG, new BigDecimal(0));
        setDefaultValue(PROPERTY_SSP, new BigDecimal(0));
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getNomorUrut() {
        return (String) get(PROPERTY_NOMORURUT);
    }

    public void setNomorUrut(String nomorUrut) {
        set(PROPERTY_NOMORURUT, nomorUrut);
    }

    public String getNamaPemotongPajak() {
        return (String) get(PROPERTY_NAMAPEMOTONGPAJAK);
    }

    public void setNamaPemotongPajak(String namaPemotongPajak) {
        set(PROPERTY_NAMAPEMOTONGPAJAK, namaPemotongPajak);
    }

    public String getNpwpPemotongPajak() {
        return (String) get(PROPERTY_NPWPPEMOTONGPAJAK);
    }

    public void setNpwpPemotongPajak(String npwpPemotongPajak) {
        set(PROPERTY_NPWPPEMOTONGPAJAK, npwpPemotongPajak);
    }

    public String getNpwpPegawai() {
        return (String) get(PROPERTY_NPWPPEGAWAI);
    }

    public void setNpwpPegawai(String npwpPegawai) {
        set(PROPERTY_NPWPPEGAWAI, npwpPegawai);
    }

    public String getAlamatPegawai() {
        return (String) get(PROPERTY_ALAMATPEGAWAI);
    }

    public void setAlamatPegawai(String alamatPegawai) {
        set(PROPERTY_ALAMATPEGAWAI, alamatPegawai);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public Boolean isLaki() {
        return (Boolean) get(PROPERTY_LAKI);
    }

    public void setLaki(Boolean laki) {
        set(PROPERTY_LAKI, laki);
    }

    public Boolean isPerempuan() {
        return (Boolean) get(PROPERTY_PEREMPUAN);
    }

    public void setPerempuan(Boolean perempuan) {
        set(PROPERTY_PEREMPUAN, perempuan);
    }

    public Boolean isPegawaiAsing() {
        return (Boolean) get(PROPERTY_PEGAWAIASING);
    }

    public void setPegawaiAsing(Boolean pegawaiAsing) {
        set(PROPERTY_PEGAWAIASING, pegawaiAsing);
    }

    public Long getJumlahTanggungan() {
        return (Long) get(PROPERTY_JUMLAHTANGGUNGAN);
    }

    public void setJumlahTanggungan(Long jumlahTanggungan) {
        set(PROPERTY_JUMLAHTANGGUNGAN, jumlahTanggungan);
    }

    public String getJabatan() {
        return (String) get(PROPERTY_JABATAN);
    }

    public void setJabatan(String jabatan) {
        set(PROPERTY_JABATAN, jabatan);
    }

    public Long getMasaPerolehanPenghasilan() {
        return (Long) get(PROPERTY_MASAPEROLEHANPENGHASILAN);
    }

    public void setMasaPerolehanPenghasilan(Long masaPerolehanPenghasilan) {
        set(PROPERTY_MASAPEROLEHANPENGHASILAN, masaPerolehanPenghasilan);
    }

    public Long getSampai() {
        return (Long) get(PROPERTY_SAMPAI);
    }

    public void setSampai(Long sampai) {
        set(PROPERTY_SAMPAI, sampai);
    }

    public BigDecimal getGaji() {
        return (BigDecimal) get(PROPERTY_GAJI);
    }

    public void setGaji(BigDecimal gaji) {
        set(PROPERTY_GAJI, gaji);
    }

    public BigDecimal getTunjanganPph() {
        return (BigDecimal) get(PROPERTY_TUNJANGANPPH);
    }

    public void setTunjanganPph(BigDecimal tunjanganPph) {
        set(PROPERTY_TUNJANGANPPH, tunjanganPph);
    }

    public BigDecimal getTunjanganLainnya() {
        return (BigDecimal) get(PROPERTY_TUNJANGANLAINNYA);
    }

    public void setTunjanganLainnya(BigDecimal tunjanganLainnya) {
        set(PROPERTY_TUNJANGANLAINNYA, tunjanganLainnya);
    }

    public BigDecimal getHonorarium() {
        return (BigDecimal) get(PROPERTY_HONORARIUM);
    }

    public void setHonorarium(BigDecimal honorarium) {
        set(PROPERTY_HONORARIUM, honorarium);
    }

    public BigDecimal getPremiAsuransi() {
        return (BigDecimal) get(PROPERTY_PREMIASURANSI);
    }

    public void setPremiAsuransi(BigDecimal premiAsuransi) {
        set(PROPERTY_PREMIASURANSI, premiAsuransi);
    }

    public BigDecimal getNaturaKenaPajak() {
        return (BigDecimal) get(PROPERTY_NATURAKENAPAJAK);
    }

    public void setNaturaKenaPajak(BigDecimal naturaKenaPajak) {
        set(PROPERTY_NATURAKENAPAJAK, naturaKenaPajak);
    }

    public BigDecimal getTantiem() {
        return (BigDecimal) get(PROPERTY_TANTIEM);
    }

    public void setTantiem(BigDecimal tantiem) {
        set(PROPERTY_TANTIEM, tantiem);
    }

    public BigDecimal getBiayaJabatanAtasGaji() {
        return (BigDecimal) get(PROPERTY_BIAYAJABATANATASGAJI);
    }

    public void setBiayaJabatanAtasGaji(BigDecimal biayaJabatanAtasGaji) {
        set(PROPERTY_BIAYAJABATANATASGAJI, biayaJabatanAtasGaji);
    }

    public BigDecimal getBiayaJabatanAtasBonus() {
        return (BigDecimal) get(PROPERTY_BIAYAJABATANATASBONUS);
    }

    public void setBiayaJabatanAtasBonus(BigDecimal biayaJabatanAtasBonus) {
        set(PROPERTY_BIAYAJABATANATASBONUS, biayaJabatanAtasBonus);
    }

    public BigDecimal getIuranPensiun() {
        return (BigDecimal) get(PROPERTY_IURANPENSIUN);
    }

    public void setIuranPensiun(BigDecimal iuranPensiun) {
        set(PROPERTY_IURANPENSIUN, iuranPensiun);
    }

    public BigDecimal getPenghasilanNettoSebelumnya() {
        return (BigDecimal) get(PROPERTY_PENGHASILANNETTOSEBELUMNYA);
    }

    public void setPenghasilanNettoSebelumnya(BigDecimal penghasilanNettoSebelumnya) {
        set(PROPERTY_PENGHASILANNETTOSEBELUMNYA, penghasilanNettoSebelumnya);
    }

    public BigDecimal getPph21Setahun() {
        return (BigDecimal) get(PROPERTY_PPH21SETAHUN);
    }

    public void setPph21Setahun(BigDecimal pph21Setahun) {
        set(PROPERTY_PPH21SETAHUN, pph21Setahun);
    }

    public BigDecimal getPTKP() {
        return (BigDecimal) get(PROPERTY_PTKP);
    }

    public void setPTKP(BigDecimal pTKP) {
        set(PROPERTY_PTKP, pTKP);
    }

    public BigDecimal getPKPSetahun() {
        return (BigDecimal) get(PROPERTY_PKPSETAHUN);
    }

    public void setPKPSetahun(BigDecimal pKPSetahun) {
        set(PROPERTY_PKPSETAHUN, pKPSetahun);
    }

    public BigDecimal getPph21Pkpsetahun() {
        return (BigDecimal) get(PROPERTY_PPH21PKPSETAHUN);
    }

    public void setPph21Pkpsetahun(BigDecimal pph21Pkpsetahun) {
        set(PROPERTY_PPH21PKPSETAHUN, pph21Pkpsetahun);
    }

    public BigDecimal getPPh21Dibayar() {
        return (BigDecimal) get(PROPERTY_PPH21DIBAYAR);
    }

    public void setPPh21Dibayar(BigDecimal pPh21Dibayar) {
        set(PROPERTY_PPH21DIBAYAR, pPh21Dibayar);
    }

    public BigDecimal getPPh21Terhutang() {
        return (BigDecimal) get(PROPERTY_PPH21TERHUTANG);
    }

    public void setPPh21Terhutang(BigDecimal pPh21Terhutang) {
        set(PROPERTY_PPH21TERHUTANG, pPh21Terhutang);
    }

    public Boolean isPemotongPajak() {
        return (Boolean) get(PROPERTY_PEMOTONGPAJAK);
    }

    public void setPemotongPajak(Boolean pemotongPajak) {
        set(PROPERTY_PEMOTONGPAJAK, pemotongPajak);
    }

    public Boolean isKuasa() {
        return (Boolean) get(PROPERTY_KUASA);
    }

    public void setKuasa(Boolean kuasa) {
        set(PROPERTY_KUASA, kuasa);
    }

    public String getNama() {
        return (String) get(PROPERTY_NAMA);
    }

    public void setNama(String nama) {
        set(PROPERTY_NAMA, nama);
    }

    public String getNpwp() {
        return (String) get(PROPERTY_NPWP);
    }

    public void setNpwp(String npwp) {
        set(PROPERTY_NPWP, npwp);
    }

    public String getAlamat2Pegawai() {
        return (String) get(PROPERTY_ALAMAT2PEGAWAI);
    }

    public void setAlamat2Pegawai(String alamat2Pegawai) {
        set(PROPERTY_ALAMAT2PEGAWAI, alamat2Pegawai);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public BigDecimal getPph21Kurang() {
        return (BigDecimal) get(PROPERTY_PPH21KURANG);
    }

    public void setPph21Kurang(BigDecimal pph21Kurang) {
        set(PROPERTY_PPH21KURANG, pph21Kurang);
    }

    public BigDecimal getPph21Lebih() {
        return (BigDecimal) get(PROPERTY_PPH21LEBIH);
    }

    public void setPph21Lebih(BigDecimal pph21Lebih) {
        set(PROPERTY_PPH21LEBIH, pph21Lebih);
    }

    public BigDecimal getHitungPph21() {
        return (BigDecimal) get(PROPERTY_HITUNGPPH21);
    }

    public void setHitungPph21(BigDecimal hitungPph21) {
        set(PROPERTY_HITUNGPPH21, hitungPph21);
    }

    public BigDecimal getDipotongGaji() {
        return (BigDecimal) get(PROPERTY_DIPOTONGGAJI);
    }

    public void setDipotongGaji(BigDecimal dipotongGaji) {
        set(PROPERTY_DIPOTONGGAJI, dipotongGaji);
    }

    public BigDecimal getSSPDitanggung() {
        return (BigDecimal) get(PROPERTY_SSPDITANGGUNG);
    }

    public void setSSPDitanggung(BigDecimal sSPDitanggung) {
        set(PROPERTY_SSPDITANGGUNG, sSPDitanggung);
    }

    public BigDecimal getSsp() {
        return (BigDecimal) get(PROPERTY_SSP);
    }

    public void setSsp(BigDecimal ssp) {
        set(PROPERTY_SSP, ssp);
    }

}
