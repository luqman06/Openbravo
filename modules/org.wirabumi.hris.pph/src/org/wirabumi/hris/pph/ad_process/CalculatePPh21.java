package org.wirabumi.hris.pph.ad_process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.payroll.pyr_spe_deduction;
import org.wirabumi.hris.payroll.pyr_spe_earning;
import org.wirabumi.hris.payroll.utility.SalaryUtility;
import org.wirabumi.hris.pph.pph_pph21;
import org.wirabumi.hris.pph.utility.BiayaJabatan;
import org.wirabumi.hris.pph.utility.PPHUtility;
import org.wirabumi.hris.pph.utility.PPhRampungBean;
import org.wirabumi.hris.pph.utility.PembayaranPPhBean;
import org.wirabumi.hris.pph.utility.TarifProgresifPPh;

public class CalculatePPh21 extends DalBaseProcess {
	
	private static Logger log4j = Logger.getLogger(CalculatePPh21.class);
	
    private final String pph21key = "PPH21";
    private final String tunjanganPPH21key = "TUNJANGAN PPH21";
    private final String gross="GROSS";
    private final String nett="NETT";
    private final String deductible = "DEDUCTIBLE";
    private final List<PembayaranPPhBean> pembayaranppList = new ArrayList<PembayaranPPhBean>();
    private final HashMap<pyr_sp_employee, pyr_spe_earning> speeMap = new HashMap<pyr_sp_employee, pyr_spe_earning>();
    private final HashMap<pyr_sp_employee, pyr_spe_deduction> spedMap = new HashMap<pyr_sp_employee, pyr_spe_deduction>();
    
    //hashmap untuk pajak
    private HashMap<BusinessPartner, Double> masapph = new HashMap<BusinessPartner, Double>();
    private HashMap<String, Double> ptkpMap = new HashMap<String, Double>();
    TarifProgresifPPh tarifProgresifPPh = null;
    BiayaJabatan biayaJabatan = null;
    
    //hashmap untuk gaji tidak teratur saja
    private HashMap<BusinessPartner, Boolean> pajakTidakTeraturSaja = new HashMap<BusinessPartner, Boolean>(); //DONE
    private HashMap<BusinessPartner, pph_pph21> pphPeriodeSebelumnya = new HashMap<BusinessPartner, pph_pph21>();
    
    //hashmap untuk pajak rampung
    private double ratebiayajabatan = 0;
    private double maxbiayajabatan = 0;
    private boolean pajakrampung;
    private int tahuntakwim=0;
    private int bulantakwim=0;
    private HashMap<BusinessPartner, PPhRampungBean> pphRampungMap = null;
    private HashMap<BusinessPartner, pph_pph21> pph21Map = null;
    
  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
	  //proses hitung pph 21 sudah tidak lagi dari process menu. semua dari salary payment.
	  
	  //paramter initialization
	  pyr_salarypayment sp = null;
	  pyr_sp_employee spe = null;
	  int recordUpdated = 0;
	  final OBError msg = new OBError();
	  ConnectionProvider conn = new DalConnectionProvider();
	  Connection connection = conn.getConnection();
	  
	  //get SP ID dan SPE ID
	  String spID = (String) bundle.getParams().get("PYR_Salarypayment_ID");
	  String speID = null;
	  if (spID==null || spID.isEmpty()){
		  speID = (String) bundle.getParams().get("PYR_Sp_Employee_ID");
		  if (speID==null || speID.isEmpty())
    			throw new OBException("tidak ada payment date, sp ID null, dan juga spe ID null, pajak tidak bisa dihitung");
		  spe = OBDal.getInstance().get(pyr_sp_employee.class, speID);
		  if (spe==null)
    			throw new OBException("invalid spe ID");
		  sp = spe.getSalaryPayment();
    		
	  } else
		  sp = OBDal.getInstance().get(pyr_salarypayment.class, spID);
	  
	  Date effectivedate = sp.getEffectiveDate();
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(effectivedate);
	  tahuntakwim = cal.get(Calendar.YEAR);
	  bulantakwim = cal.get(Calendar.MONTH)+1;
	  pph21Map = getPPh21Map(sp);
	  
	  pphRampungMap = PPHUtility.getPPhRampungMap(tahuntakwim, spe);
	  /*
	   * model data untuk pajak jan-nov:
	   * bpartner, total penghasilan yg pajaknya ditanggung, total penghasilan yang pajaknya tidak ditanggung,
	   * total penghasilan tidak kena pajak, total potongan iuran, total uang muka pph21   
	   */
	  
	  //load spee dan sped
  	  loadspeemap(sp, spe);
  	  loadspedmap(sp, spe);
  	  
  	  //tunjangan pph dan potongan pph dibuat nol dulu
  	  SalaryUtility.setAmountOfEarningPaymentAllEmployee(spID, speID, tunjanganPPH21key, BigDecimal.ZERO);
  	  SalaryUtility.setAmountOfDeductionPaymentAllEmployee(spID, speID, pph21key, BigDecimal.ZERO);
  	  
  	  //load hashmap
  	  //load gross rutin bulan ini
  	  ptkpMap = PPHUtility.loadPTKP(conn, connection, effectivedate);
  	  tarifProgresifPPh = new TarifProgresifPPh(conn, connection, effectivedate);
  	  masapph = PPHUtility.getMasaPPh(sp, spe);
  	  
  	  ratebiayajabatan = PPHUtility.getRateBiayaJabatanSetahun(sp.getEffectiveDate());
  	  maxbiayajabatan = PPHUtility.getMaxBiayaJabatanSetahun(sp.getEffectiveDate());
  	  biayaJabatan = new BiayaJabatan(ratebiayajabatan, maxbiayajabatan);
  	  pajakTidakTeraturSaja = PPHUtility.loadPenghasilanTidakTeraturSaja(conn, connection, sp, spe, tunjanganPPH21key);
  	  pphPeriodeSebelumnya = PPHUtility.loadPPhPeriodeSebelumnya(conn, connection, sp, spe);
  	  
  	  List<pyr_sp_employee> speList = null;
  	  if (spe!=null){
  		  speList = new ArrayList<pyr_sp_employee>();
  		  speList.add(spe);
  	  } else if (sp!=null){
  		  speList = sp.getPyrSpEmployeeList();
  	  }
  	  
  	//bedasarkan SP ID, mencari mana saja SPE yang mengandung PPH 21.	  
	  int rsSize = speList.size();
	  for (pyr_sp_employee spe2 : speList){
		  recordUpdated++;
		  BusinessPartner employee = spe2.getBusinessPartner();
	      String nik = employee.getSearchKey();
	      String name = employee.getName();
	      log4j.debug("processing record ke "+recordUpdated+" dari "+rsSize+" atas nama "+nik+" - "+name);
	      //hitung PPH
	      doProcessPPH(spe2);
	      log4j.debug(" done");
	  }
	  
	  //simpan riwayat PPh
	  simpanpph(pembayaranppList);
  	  
	  //commit to db and build return message
  	  OBDal.getInstance().commitAndClose();
      msg.setType("Success");
      msg.setTitle("Done");
      msg.setMessage("Process Berjalan dengan " + recordUpdated + " di update");
      bundle.setResult(msg);
      
  }

private HashMap<BusinessPartner, pph_pph21> getPPh21Map(pyr_salarypayment sp) {
	HashMap<BusinessPartner, pph_pph21> output = new HashMap<BusinessPartner, pph_pph21>();
	//TODO load pph_pph21 map
	String where = "as pph21 where pph21.pYRSpEmployee.salaryPayment.id= :spID";
	OBQuery<pph_pph21> pph21Q = OBDal.getInstance().createQuery(pph_pph21.class, where);
	pph21Q.setNamedParameter("spID", sp.getId());
	for (pph_pph21 pph21 : pph21Q.list()){
		output.put(pph21.getEmployee(), pph21);
	}
	return output;
}

/**
   * menghitung pph21 untuk salary payment employee tertentu
   * @param sp_employee salary payment employee yang akan dihitung pajaknya
   * @param cutOffDate tanggal pembayara
   * @throws SQLException 
   * @throws NoConnectionAvailableException 
   */
  private void doProcessPPH(pyr_sp_employee sp_employee) throws NoConnectionAvailableException, SQLException {
	/*
	 * method ini tidak thread safe
	 * jika ingin menjadikan thread safe, maka properti yang sifatnya privat (yang membuat method ini tidak thread safe,
	 * harus dimasukkan saat instantiate object yang thread safe. sehingga saat thread di-start, maka properti yg private itu akan terbawa
	 * dan tidak tercampur dengan thread yang lain.
	 * sementara hashmap tetap diletakkan sebagi global variable (lawan dari privare properti tadi yang class variable) 
	 * */
	  
	//inisialisasi variable (alias persiapan)
	BusinessPartner employee = sp_employee.getBusinessPartner();
	pyr_salarypayment sp = sp_employee.getSalaryPayment();
	Date effectiveDate = sp.getEffectiveDate();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(effectiveDate);
	int monthEffectiveDate = calendar.get(Calendar.MONTH);
	int yearEffectiveDate = calendar.get(Calendar.YEAR);
	
	//hitung tanggal awal pensiun = tanggal 2 bulan ini
	calendar.set(Calendar.DATE, 2);
	long startpensiun=calendar.getTimeInMillis();
	
	//hitung tanggal akhir pensiun = tanggal 1 bulan depan
	if (monthEffectiveDate==11){
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, yearEffectiveDate+1);
	} else{
		calendar.set(Calendar.MONTH, monthEffectiveDate+1);
	}
	calendar.set(Calendar.DATE, 1);
	long endpensiun = calendar.getTimeInMillis();
	
	//tentukan apakah karyawan ini pensiun
	boolean karyawanpensiun = false;
	Date tanggalberhenti = employee.getHrisRetirementdate();
	if (tanggalberhenti==null){
		calendar.set(Calendar.YEAR, 9999);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		tanggalberhenti=calendar.getTime();
	} else
		calendar.setTime(tanggalberhenti);
	
	if (tanggalberhenti!=null){
		long tanggalberhenti_l = calendar.getTimeInMillis();
		if (tanggalberhenti_l>=startpensiun && tanggalberhenti_l<=endpensiun)
			karyawanpensiun=true;
	}
	
	//jika karyawan tidak aktif, maka biaya jabatan nol.
	boolean karyawanaktif = employee.isActive();
	if (!karyawanaktif)
		maxbiayajabatan=0;
	
	//tentukan apakah pajak rampung. (1) karyawan pensiun/resign dibuktikan dengan tanggal berhenti bekerja. (2) tanggal efektif 31 desember
	if (karyawanpensiun)
		pajakrampung=true;
	else {
		calendar.setTime(effectiveDate);
		int day_effectivedate = calendar.get(Calendar.DAY_OF_MONTH);
		int month_effectivedate = calendar.get(Calendar.MONTH);
		if (day_effectivedate==31 && month_effectivedate==11) //31 desember selalu rampung
			pajakrampung=true;
	}
	
	if (pajakrampung)
		doPajakRampung(sp_employee);
	else
		doPajakTidakRampung(sp_employee);
	
    return;
	
  }
  
  /**
   * proses pph21 untuk kasus pajak tidak rampung, termasuk didalamnya penghasilan yang rutin saja, rutin+penghasilan lain,
   * dan penghasilan tidak teratur saja.
   * sudah termauk update obdal object untuk potongan dan tunjangan pph 21.
   * @param spe
   */
  private void doPajakTidakRampung(pyr_sp_employee spe) {
	  BusinessPartner employee = spe.getBusinessPartner();
	  String taxmaritalstatus = employee.getPphTaxmaritalstatus();
	  if (!pajakTidakTeraturSaja.containsKey(employee))
		  throw new OBException("@brp_cannotfindemployee@ on Pajak Tidak Teratur Saja map");
	  boolean pajaktidakteratursaja = pajakTidakTeraturSaja.get(employee);
	  if  (pajaktidakteratursaja){
		  //proses pajak tidak teratur saja
		  doPajakTidakTeratursaja(spe);
		  return;
	  }
		  
	  //proses pajak rutin, atau campur (rutin+penghasilan lain)
	  double penghasilanRutinGrossBulanIni=PPHUtility.getPendapatanBulanIni(spe, true, gross);
	  double penghasilanRutinNettBulanIni=PPHUtility.getPendapatanBulanIni(spe, true, nett);
	  double penghasilanLainGrossBulanIni=PPHUtility.getPendapatanBulanIni(spe, false, gross);
	  double penghasilanLainNettBulanIni = PPHUtility.getPendapatanBulanIni(spe, false, nett);
	  double pengurangPajakBulanIni=PPHUtility.getPotonganBulanIni(spe, deductible);	  
	  
	  double PTKPSetahun = 0;
	  if (ptkpMap.containsKey(taxmaritalstatus))
		  PTKPSetahun=ptkpMap.get(taxmaritalstatus);
	  Double masaPPh=Double.valueOf(12);
	  if (masapph.containsKey(employee))
		  masaPPh = masapph.get(employee);
		
	  //hitung tunjangan pph untuk penghasilan rutin
	  double brutoRutinGrosSsetahun = penghasilanRutinGrossBulanIni*masaPPh;
	  double tunjanganPPh21BulanIni = hitungTunjanganPPh(employee,
			  penghasilanRutinGrossBulanIni, masaPPh, penghasilanLainGrossBulanIni,pengurangPajakBulanIni, PTKPSetahun,
			  false, 0);
	  
	  penghasilanRutinGrossBulanIni=penghasilanRutinGrossBulanIni+tunjanganPPh21BulanIni;
	  brutoRutinGrosSsetahun=penghasilanRutinGrossBulanIni*masaPPh;
	  double brutototalgrosssetahun=brutoRutinGrosSsetahun+penghasilanLainGrossBulanIni;
	  double brutorutinnettsetahun = penghasilanRutinNettBulanIni*masaPPh;
	  double brutototalnettsetahun = brutorutinnettsetahun+penghasilanLainNettBulanIni;
	  double brutototalsetahun = brutototalgrosssetahun+brutototalnettsetahun;
	  double biayajabatansetahun = biayaJabatan.getBiayaJabatan(brutototalgrosssetahun);
	  double pengurangpajaksetahun = pengurangPajakBulanIni*masaPPh;
	  double penghasilannetto = brutototalsetahun-biayajabatansetahun-pengurangpajaksetahun;
	  double pkpsetahun = penghasilannetto-PTKPSetahun;
	  pkpsetahun = new BigDecimal(pkpsetahun).setScale(-3, RoundingMode.FLOOR).doubleValue(); //PKP dibulatkan seribu
	  double pphterhutang = tarifProgresifPPh.getprogresifpph(pkpsetahun);
	  masaPPh=masaPPh+0.00;
	  double pphsebulan = pphterhutang/masaPPh;
	  if (pphsebulan<0)
		  pphsebulan=0;
	  
	  //pembulatan tanpa koma
	  pphsebulan = new BigDecimal(pphsebulan).setScale(0, RoundingMode.FLOOR).doubleValue();
	  tunjanganPPh21BulanIni = new BigDecimal(tunjanganPPh21BulanIni).setScale(0, RoundingMode.FLOOR).doubleValue();
	  
	  //create riwayat pembayaran pph
	  PembayaranPPhBean ppb = new PembayaranPPhBean(spe, false, false, tahuntakwim, bulantakwim, taxmaritalstatus,
			  masaPPh,
			  brutoRutinGrosSsetahun, brutorutinnettsetahun, penghasilanLainGrossBulanIni, 
			  penghasilanLainNettBulanIni, brutototalsetahun, biayajabatansetahun,
			  pengurangpajaksetahun, 
			  PTKPSetahun, pkpsetahun, pphterhutang, 
			  pphsebulan, tunjanganPPh21BulanIni);
	  if (!pembayaranppList.contains(ppb))
		  pembayaranppList.add(ppb);
	    	
	  return;
  }

private void doPajakTidakTeratursaja(pyr_sp_employee spe) {
	  double masaPPh = masapph.get(spe.getBusinessPartner());
	  BusinessPartner employee = spe.getBusinessPartner();
	  String taxmaritalstatus = employee.getPphTaxmaritalstatus();
	  pph_pph21 prevPPH = pphPeriodeSebelumnya.get(employee);
	  boolean adagajiperiodesebelumnya=false; //jika false artinya penghasilan lain ini langsung dikali tarif, dan tidak ditunjang
	  double penghasilanLainGrossBulanIni=PPHUtility.getPendapatanBulanIni(spe, false, gross);
	  double penghasilanLainNettBulanIni = PPHUtility.getPendapatanBulanIni(spe, false, nett);
	  double pengurangPajakBulanIni=PPHUtility.getPotonganBulanIni(spe, deductible);	  
	  if (!adagajiperiodesebelumnya){
		  //TODO implementasi tunjangan pph untuk penghasilan tidak teratur saja yg pada periode sebelumnya tidak ada ada gaji.
		  //jika tanpa tunjangan pajak penghasilan, paka pph simply tarif * penghasilan
		  //bagaimana jika ada tunjangan penghasilannya?
		  //saat ini tunjangan penghasilannya diabaikan
		  double pph21terhutang = tarifProgresifPPh.getprogresifpph(penghasilanLainGrossBulanIni+penghasilanLainNettBulanIni);
		  
		//buat riawayat pembayaran pph
		  PembayaranPPhBean ppb = new PembayaranPPhBean(spe, true, true, tahuntakwim, bulantakwim, taxmaritalstatus, 
				  masaPPh, 0, 0,
				  penghasilanLainGrossBulanIni, penghasilanLainNettBulanIni, 
				  penghasilanLainGrossBulanIni+penghasilanLainNettBulanIni, 
				  0, 0, 0, penghasilanLainGrossBulanIni+penghasilanLainNettBulanIni, 
				  pph21terhutang, pph21terhutang, 0, 
				  0, 0, 
				  0, 0, 
				  0, prevPPH);
		  if (!pembayaranppList.contains(ppb))
			  pembayaranppList.add(ppb);
		  
	  } else {
		  double PTKPSetahun = 0;
		  if (ptkpMap.containsKey(taxmaritalstatus))
			  PTKPSetahun=ptkpMap.get(taxmaritalstatus);
		  masaPPh=12.0;
		  if (masapph.containsKey(employee))
			  masaPPh = masapph.get(employee);
			
		  //hitung tunjangan pph untuk penghasilan rutin
		  double pphPeriodeSebelumnya = prevPPH.getPPh21Setahun().doubleValue();
		  double penghasilanLainGrossBulanLalu = prevPPH.getPendapatanLainGross().doubleValue();
		  double penghasilanLainNettBulanLalu = prevPPH.getPendapatanLainNett().doubleValue();
		  double brutoRutinGrossSetahunBulanLalu = prevPPH.getPendapatanRutinGrossSetahun().doubleValue();
		  double brutoRutinGrossSebulanBulanLalu = prevPPH.getPendapatanRutinGrossSebulan().doubleValue();
		  double brutoRutinNettSetahunBulanLalu = prevPPH.getPendapatanRutinNettSetahun().doubleValue();
		  double brutoRutinNettSebulanBulanLalu = prevPPH.getPendapatanRutinNettSebulan().doubleValue();
		  double pengurangpajakbulanlalu = prevPPH.getPengurangPajakSetahun().doubleValue();
		  
		  double tunjanganpph21bulanini = hitungTunjanganPPh(employee, brutoRutinGrossSebulanBulanLalu, masaPPh, 
				  penghasilanLainGrossBulanLalu+penghasilanLainGrossBulanIni, pengurangpajakbulanlalu, PTKPSetahun,
				  true, pphPeriodeSebelumnya);
		   
		  double brutototalgrosssetahun=brutoRutinGrossSetahunBulanLalu+penghasilanLainGrossBulanLalu
				  +penghasilanLainGrossBulanIni+tunjanganpph21bulanini;
		  double brutototalnettsetahun = brutoRutinNettSetahunBulanLalu+penghasilanLainNettBulanLalu;
		  double brutototalsetahun = brutototalgrosssetahun+brutototalnettsetahun;
		  double biayajabatansetahun = biayaJabatan.getBiayaJabatan(brutototalsetahun);
		  double pengurangpajaksetahun = pengurangPajakBulanIni*masaPPh;
		  double penghasilannetto = brutototalsetahun-biayajabatansetahun-pengurangpajaksetahun;
		  double pkpsetahun = penghasilannetto-PTKPSetahun;
		  pkpsetahun = new BigDecimal(pkpsetahun).setScale(-3).doubleValue(); //PKP dibulatkan seribu
		  double pphterhutang = tarifProgresifPPh.getprogresifpph(pkpsetahun);
		  double pphsebulan = pphterhutang-pphPeriodeSebelumnya;
		  
		//buat riawayat pembayaran pph
		  PembayaranPPhBean ppb = new PembayaranPPhBean(spe, true, true, tahuntakwim, bulantakwim, taxmaritalstatus,
				  masaPPh, brutoRutinGrossSetahunBulanLalu, brutoRutinNettSetahunBulanLalu,
				  penghasilanLainGrossBulanLalu+penghasilanLainGrossBulanIni+tunjanganpph21bulanini, penghasilanLainNettBulanLalu, 
				  brutototalsetahun, 
				  biayajabatansetahun, pengurangpajaksetahun, PTKPSetahun, pkpsetahun, 
				  pphterhutang, pphsebulan, tunjanganpph21bulanini, 
				  brutoRutinGrossSebulanBulanLalu, brutoRutinNettSebulanBulanLalu, 
				  penghasilanLainGrossBulanLalu, penghasilanLainNettBulanLalu, 
				  pengurangpajakbulanlalu, prevPPH);
		  if (!pembayaranppList.contains(ppb))
			  pembayaranppList.add(ppb);
	  }
	  
	  return;
	  
}

private void doPajakRampung(pyr_sp_employee spe) {
	BusinessPartner bp = spe.getBusinessPartner();
	double masaPPh = masapph.get(bp);
	String taxmaritalstatus = bp.getPphTaxmaritalstatus();
	double ptkp = ptkpMap.get(taxmaritalstatus);
	PPhRampungBean prb = pphRampungMap.get(bp);
	
	double tunjanganpph21 = hitungTunjanganPPh(bp, prb.getGrossIncome(), 1, 0, prb.getDeductibleDeduction(), ptkp, false, 0);
	
	double brutoGrossSetahun = prb.getGrossIncome()+tunjanganpph21;
	double brutonett = prb.getNetIncome();
	double totalbruto = brutoGrossSetahun+brutonett;
	double biayajabatansetahun = biayaJabatan.getBiayaJabatan(totalbruto);
	double pengurangpajak = prb.getDeductibleDeduction();
	double penghasilannetto = totalbruto-biayajabatansetahun-pengurangpajak;
	double pkpsetahun = penghasilannetto-ptkp;
	pkpsetahun = new BigDecimal(pkpsetahun).setScale(-3, RoundingMode.FLOOR).doubleValue(); //PKP dibulatkan seribu
	if (pkpsetahun<0)
		pkpsetahun=0.00;
	double pphterhutang = tarifProgresifPPh.getprogresifpph(pkpsetahun);
	double pphsebulan = pphterhutang-prb.getPaidIncomeTax();
	
	//buat riawayat pembayaran pph
	PembayaranPPhBean ppb = new PembayaranPPhBean(spe, false, true, tahuntakwim, bulantakwim, taxmaritalstatus, 
			  masaPPh, 0, 0, 0, 0, totalbruto, 
			  biayajabatansetahun, pengurangpajak, ptkp,
			  pkpsetahun, pphterhutang, pphsebulan, tunjanganpph21);
	if (!pembayaranppList.contains(ppb))
		pembayaranppList.add(ppb);
}


  /**
   * hitung tunjangan PPh untuk penghasilan rutin dan campur BUKAN rampung
   * @param penghasilanRutinGrossBulanIniSebelumTunjanganPPh
   * @param masapph
   * @param penghasilanLainGrossBulanIni
   * @param pengurangPajakBulanIni
   * @param PTKP
   * @return
   */
  private double hitungTunjanganPPh(BusinessPartner employee, double penghasilanRutinGrossBulanIniSebelumTunjanganPPh,
		  double masapph, double penghasilanLainGrossBulanIni, double pengurangPajakBulanIni, double PTKP,
		  boolean penghasilanTidakTeraturSaja, double pphPeriodeSebelumnya) {
	  
	  return hitungTunjanganPPh(employee, penghasilanRutinGrossBulanIniSebelumTunjanganPPh, 0.00,
			  0.00, 0.00, masapph, penghasilanLainGrossBulanIni, pengurangPajakBulanIni, PTKP, 0,
			  penghasilanTidakTeraturSaja, pphPeriodeSebelumnya);
	  
  }
  private double hitungTunjanganPPh(BusinessPartner employee, double penghasilanRutinGrossBulanIniSebelumTunjanganPPh, double tunjanganPPhBulanIni,
		  double batasAtasTunjanganPPh, double batasBawahTunjanganPPh,
		  double masapph, double penghasilanLainGrossBulanIni, double pengurangPajakBulanIni, double PTKP, int jumlahIterasi,
		  boolean penghasilanTidakTeraturSaja, double pphPeriodeSebelumnya) {
	  
	  
	  
	  double brutoRutinSebulanGross = penghasilanRutinGrossBulanIniSebelumTunjanganPPh+tunjanganPPhBulanIni;
	  double brutoRutinSetahunGross = brutoRutinSebulanGross*masapph;
	  double brutoTotalSetahunGross = brutoRutinSetahunGross+penghasilanLainGrossBulanIni;
	  double biayaJabatanSetahun = biayaJabatan.getBiayaJabatan(brutoTotalSetahunGross);
	  double pengurangPajakSetahun = pengurangPajakBulanIni*masapph;
	  double nettoSetahun = brutoTotalSetahunGross-biayaJabatanSetahun-pengurangPajakSetahun;
	  double PKP = nettoSetahun-PTKP;
	  PKP = new BigDecimal(PKP).setScale(-3, RoundingMode.FLOOR).doubleValue(); //dibulatkan seribu
	  if (PKP<0)
		  return 0;
	  double pph21setahun=tarifProgresifPPh.getprogresifpph(PKP);
	  masapph=masapph+0.00;
	  double pph21sebulan=0.00;
	  if (penghasilanTidakTeraturSaja)
		  pph21sebulan=pph21setahun-pphPeriodeSebelumnya;
	  else
		  pph21sebulan=pph21setahun/masapph;
      double selisihtunjangan = tunjanganPPhBulanIni-pph21sebulan;
      if (jumlahIterasi==100){//iterasi dibatasi 100x supaya tidak infinite loop
    	  return tunjanganPPhBulanIni;
      }
      
      //iterasi pertama jika batasAtasTunjanganPPh=batasBawahTunjanganPPh=0, maka iterasi ke 2 adalah batas atas 2xpphsebulan
      //hal ini dilakukan supaya iterasi kedua memiliki selisih tunjangan pajak yg berbeda tanda dengan iterasi pertama
      if (batasAtasTunjanganPPh==0.00 && batasBawahTunjanganPPh==0.00){
    	  batasAtasTunjanganPPh=2.00*pph21sebulan;
    	  brutoRutinSebulanGross = penghasilanRutinGrossBulanIniSebelumTunjanganPPh;
    	  if (!penghasilanTidakTeraturSaja)
    		  brutoRutinSebulanGross += batasAtasTunjanganPPh;
    	  brutoRutinSetahunGross = brutoRutinSebulanGross*masapph;
    	  brutoTotalSetahunGross = brutoRutinSetahunGross+penghasilanLainGrossBulanIni;
    	  if (penghasilanTidakTeraturSaja)
    		  brutoTotalSetahunGross += batasAtasTunjanganPPh;
    	  biayaJabatanSetahun = biayaJabatan.getBiayaJabatan(brutoTotalSetahunGross);
    	  nettoSetahun = brutoTotalSetahunGross-biayaJabatanSetahun-pengurangPajakSetahun;
    	  PKP = nettoSetahun-PTKP;
    	  PKP = new BigDecimal(PKP).setScale(-3, RoundingMode.FLOOR).doubleValue(); //dibulatkan seribu
    	  double pph21setahun_2=tarifProgresifPPh.getprogresifpph(PKP);
    	  if (pph21setahun_2>pph21setahun){
    		  tunjanganPPhBulanIni = ((batasAtasTunjanganPPh-pph21sebulan)/2.00)+pph21sebulan; 
    		  return hitungTunjanganPPh(employee, penghasilanRutinGrossBulanIniSebelumTunjanganPPh, tunjanganPPhBulanIni, batasAtasTunjanganPPh, pph21sebulan,
    				  masapph, penghasilanLainGrossBulanIni, pengurangPajakBulanIni, PTKP, ++jumlahIterasi,
    				  penghasilanTidakTeraturSaja, pphPeriodeSebelumnya);
    	  } else
    		  throw new OBException("solusi biseksi tunjangan pajak divergen untuk employee "+employee.getSearchKey()+"-"+employee.getName());
    	  
      }
      
      //jika selisih di range -1 dan 1, maka sudah selesai
      if (selisihtunjangan>-1 && selisihtunjangan<1)
    	  return pph21sebulan;
      
      if (selisihtunjangan>0)//selisihnya positif, artinya solusi ada diantara batas atas dan tengah-nya
    	  batasAtasTunjanganPPh=tunjanganPPhBulanIni;
    	  
      if (selisihtunjangan<0)//selisihnya negatif, artinya solusi ada diantara batas bawah dan tengah-nya
    	  batasBawahTunjanganPPh=tunjanganPPhBulanIni;
      
      tunjanganPPhBulanIni=((batasAtasTunjanganPPh-batasBawahTunjanganPPh)/2.00)+batasBawahTunjanganPPh;
	  return hitungTunjanganPPh(employee, penghasilanRutinGrossBulanIniSebelumTunjanganPPh, tunjanganPPhBulanIni, batasAtasTunjanganPPh, batasBawahTunjanganPPh,
			  masapph, penghasilanLainGrossBulanIni, pengurangPajakBulanIni, PTKP, ++jumlahIterasi,
			  penghasilanTidakTeraturSaja, pphPeriodeSebelumnya);
	  
  }
  
  
  private void simpanpph(List<PembayaranPPhBean> pph){
	  
	  for (PembayaranPPhBean riwayat : pph){
		  
		  //simpan potongan di SPED dan tunjangan pph di SPEE
		  pyr_sp_employee spe = riwayat.getEmployeeSalaryPayment();
		  double pphsebulan = riwayat.getPPh21Sebulan();
		  double tunjanganpph = riwayat.getTunjanganPPh21BulanIni();
		  BusinessPartner employee = spe.getBusinessPartner();
		  String npwp=employee.getTaxID();
		  if (npwp==null || npwp.isEmpty())
			  pphsebulan=pphsebulan*1.20; //tanpa NPWP tarif jadi 20% lebih mahal, sementara tunjangan tetap.
		  pyr_spe_earning komponentunjanganpph = getspee(spe, tunjanganPPH21key);
		  OBDal.getInstance().refresh(komponentunjanganpph);
		  pyr_spe_deduction komponenpotonganpph = getsped(spe, pph21key);
		  OBDal.getInstance().refresh(komponenpotonganpph);
		  komponentunjanganpph.setAmount(new BigDecimal(tunjanganpph));
		  komponenpotonganpph.setAmount(new BigDecimal(pphsebulan));
		  OBDal.getInstance().save(komponenpotonganpph);
		  OBDal.getInstance().save(komponentunjanganpph);
		  
		  //simpan potongan pph di pph_pph21
		  pph_pph21 pph21;
		  if (pph21Map.containsKey(employee))
			  pph21 = pph21Map.get(employee);
		  else
			  pph21 = OBProvider.getInstance().get(pph_pph21.class);
		  
		  //employee information
		  pph21.setOrganization(spe.getOrganization());
		  pph21.setPYRSpEmployee(spe);
		  pph21.setEmployee(employee);
		  pph21.setTaxMaritalStatus(riwayat.getTaxmaritalstatus());
		  pph21.setFiscalYear(new Long(riwayat.getFiscalyear()));
		  pph21.setMonth(new Long(riwayat.getMonth()));
		  pph21.setMasaPPh(Double.valueOf(riwayat.getMasapph()).longValue());
		  pph21.setPajakRampung(riwayat.isPajakrampung());
		  
		  //pendapatan
//		  pph21.setPendapatanRutinGrossSebulan(riwayat.);
		  
		  //pengurang pajak
		  
		  //pendapatan lain saja
		  if (riwayat.isPendapatanlainsaja()){
			  //pendapatan tidak teratur saja
			  pph_pph21 pph21BulanLalu = riwayat.getPph21bulanlalu();
			  pph21.setPendapatanLainSaja(true);
			  pph21.setPendapatanRutinGrossSebulanPadaBulanLalu(pph21BulanLalu.getPendapatanRutinGrossSebulan());
			  pph21.setPendapatanRutinNettSebulanPadaBulanLalu(pph21BulanLalu.getPendapatanRutinNettSebulan());
			  pph21.setPendapatanLainGrossBulanLalu(pph21BulanLalu.getPendapatanLainGross());
			  pph21.setPendapatanLainNettBulanLalu(pph21BulanLalu.getPendapatanLainNett());
			  pph21.setPengurangPajakBulanLalu(pph21BulanLalu.getPengurangPajakSebulan());
			  pph21.setPPh21DibayarBulanLalu(pph21BulanLalu.getPPh21TerhutangBulanIni());
			  
		  }
		  
		  //perhitungan PPh21
		  
//		  pph21.setRutinsetahungross(new BigDecimal(riwayat.getPenghasilanrutinsetahungross()));
//		  pph21.setBiayajabatan(new BigDecimal(riwayat.getBiayajabatansetahun()/riwayat.getMasapph()));
//		  pph21.setPtkp(new BigDecimal(riwayat.getPtkpsetahun()));
//		  pph21.setPkpsetahun(new BigDecimal(riwayat.getPkpsetahun()));
//		  pph21.setPph21dibayar(new BigDecimal(riwayat.getPph21sebulan()));
//		  pph21.setPph21terhutang(new BigDecimal(riwayat.getPph21setahun()));
//		  pph21.setPph21terhutangbulanini(new BigDecimal(riwayat.getPph21sebulan()));
//		  pph21.setPph21terpotong(new BigDecimal(riwayat.getPph21sebulan()));
//		  
//		  
//		  
//		  pph21.setBrutosetahungross(new BigDecimal(riwayat.getPenghasilanbrutosetahungross()));
//		  pph21.setPengurangpajak(new BigDecimal(riwayat.getPengurangpajaksetahun()/riwayat.getMasapph()));
//		  pph21.setTunjanganpph21bulanini(new BigDecimal(riwayat.getTunjanganpajaksebulan()));
//		  pph21.setPengurangpajaksetahun(new BigDecimal(riwayat.getPengurangpajaksetahun()));
//		  pph21.setBiayajabatansetahun(new BigDecimal(riwayat.getBiayajabatansetahun()));
//		  pph21.setPendapatantidakteratursaja(riwayat.isPendapatanlainsaja());
//		  pph21.setRutinsebulangross(new BigDecimal(riwayat.getPenghasilanrutinsetahungross()/riwayat.getMasapph()));
//		  pph21.setRutinsebulannett(new BigDecimal(riwayat.getPenghasilanrutinsetahunnett()/riwayat.getMasapph()));
//		  pph21.setRutinsetahunnett(new BigDecimal(riwayat.getPenghasilanrutinsetahunnett()));
//		  pph21.setPenghasilantidakteraturnett(new BigDecimal(riwayat.getPenghasilanlaingnett()));
//		  pph21.setPengurangpajaksebulan(new BigDecimal(riwayat.getPengurangpajaksetahun()/riwayat.getMasapph()));
//		  
//		  pph21.setPenghasilanbrutototalsetahun(new BigDecimal(riwayat.getPenghasilanbrutosetahuntotal()));
		  
		  OBDal.getInstance().save(pph21);
	  }
  }
  
  private pyr_spe_earning getspee(pyr_sp_employee spe, String earningcode){
	  if (speeMap.containsKey(spe))
		  return speeMap.get(spe);
	  else
		  return null;
	  
  }
  
  private pyr_spe_deduction getsped(pyr_sp_employee spe, String deductioncode){
	  if (spedMap.containsKey(spe))
		  return spedMap.get(spe);
	  else
		  return null;
	  
  }
  
  private void loadspeemap(pyr_salarypayment sp, pyr_sp_employee spe) throws NoConnectionAvailableException, SQLException{
	  if (sp==null)
		  throw new OBException("@brp_failedloadspeemap_SPisNull@");
	  String sqlquery = "select spee.pyr_spe_earning_id, spe.pyr_sp_employee_id"
	  		+ " from pyr_salarypayment sp"
	  		+ " inner join pyr_sp_employee spe on spe.pyr_salarypayment_id=sp.pyr_salarypayment_id"
	  		+ " inner join pyr_spe_earning spee on spee.pyr_sp_employee_id=spe.pyr_sp_employee_id"
	  		+ " inner join pyr_earning e on e.pyr_earning_id=spee.pyr_earning_id"
	  		+ " where sp.pyr_salarypayment_id=?"
	  		+ " and e.value=?";
	  if (spe!=null)
		  sqlquery=sqlquery+" and spe.pyr_sp_employee_id=?";
	  
	  PreparedStatement ps;
      ConnectionProvider conn = new DalConnectionProvider();
      Connection connection = conn.getConnection();
      ps = connection.prepareStatement(sqlquery);
      ps.setString(1, sp.getId());
      ps.setString(2, tunjanganPPH21key);
      if (spe!=null)
    	  ps.setString(3, spe.getId());
      
      ResultSet result=ps.executeQuery();	      
      while (result.next()) {
    	  String speID = result.getString("pyr_spe_earning_id");
    	  pyr_spe_earning spee = OBDal.getInstance().get(pyr_spe_earning.class, speID);
    	  String spID = result.getString("pyr_sp_employee_id");
    	  pyr_sp_employee spe2 = OBDal.getInstance().get(pyr_sp_employee.class, spID);
    	  if (!speeMap.containsKey(spe2))
    		  speeMap.put(spe2, spee);
      }

  }
  
  private void loadspedmap(pyr_salarypayment sp, pyr_sp_employee spe) throws NoConnectionAvailableException, SQLException{
	  if (sp==null)
		  throw new OBException("@brp_failedloadspedmap_SPisNull@");
	  String sqlquery = "select sped.pyr_spe_deduction_id, spe.pyr_sp_employee_id"
	  		+ " from pyr_salarypayment sp"
	  		+ " inner join pyr_sp_employee spe on spe.pyr_salarypayment_id=sp.pyr_salarypayment_id"
	  		+ " inner join pyr_spe_deduction sped on sped.pyr_sp_employee_id=spe.pyr_sp_employee_id"
	  		+ " inner join pyr_deduction d on d.pyr_deduction_id=sped.pyr_deduction_id"
	  		+ " where sp.pyr_salarypayment_id=?"
	  		+ " and d.value=?";
	  if (spe!=null)
		  sqlquery=sqlquery+" and spe.pyr_sp_employee_id=?";
	  
	  PreparedStatement ps;
      ConnectionProvider conn = new DalConnectionProvider();
      Connection connection = conn.getConnection();
      ps = connection.prepareStatement(sqlquery);
      ps.setString(1, sp.getId());
      ps.setString(2, pph21key);
      if (spe!=null)
    	  ps.setString(3, spe.getId());
      ResultSet result=ps.executeQuery();	      
      while (result.next()) {
    	  String speID = result.getString("pyr_spe_deduction_id");
    	  pyr_spe_deduction sped = OBDal.getInstance().get(pyr_spe_deduction.class, speID);
    	  String spID = result.getString("pyr_sp_employee_id");
    	  pyr_sp_employee spe2 = OBDal.getInstance().get(pyr_sp_employee.class, spID);
    	  if (!spedMap.containsKey(spe2))
    		  spedMap.put(spe2, sped);
      }

  }
  
  
}
