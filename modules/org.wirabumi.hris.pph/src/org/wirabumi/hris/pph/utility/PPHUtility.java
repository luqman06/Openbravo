package org.wirabumi.hris.pph.utility;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.pph.pph_pph21;
import org.wirabumi.hris.pph.pph_set_ptkp;
import org.wirabumi.hris.pph.pph_tarifpph21;
import org.wirabumi.hris.pph.pph_tarifprogresif;

public class PPHUtility {
	
	
  /**
   * Ambil Tahun Dari Tanggal
   * 
   * @param paramsDate
   * @return
   */
  public static Long getYear(Date paramsDate) {
    int year = 0;
    try {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(paramsDate);
      year = calendar.get(Calendar.YEAR);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new Long(year);
  }

  /**
   * Get Month
   * 
   * @param paramsDate
   * @return
   */
  public static Long getMonth(Date paramsDate) {
    int month = 0;
    try {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(paramsDate);
      month = calendar.get(Calendar.MONTH);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new Long(month);
  }

  /**
   * ambil faktor kali berdasarkan pada tanggal mulai kerja karyawan
   * 
   * @param employee
   * @return
   */
  public static double getMasaPPh(BusinessPartner employee) {
    double masaPPh = 0;
    try {
      Date joinDate = employee.getHrisJoindate();
      Calendar calendarJoinDate = Calendar.getInstance();
      calendarJoinDate.setTime(joinDate);

      Calendar calendarNow = Calendar.getInstance();

      if (calendarNow.get(Calendar.YEAR) > calendarJoinDate.get(Calendar.YEAR)) {
        masaPPh = 12;
      } else if (calendarJoinDate.get(Calendar.MONTH) == 0) {
        masaPPh = 12;
      } else if (calendarJoinDate.get(Calendar.MONTH) != 0) {
        double masa = calendarJoinDate.get(Calendar.MONTH);
        masaPPh = 12 - masa;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return masaPPh;
  }
  
  /**
   * ambil faktor kali berdasarkan pada tanggal mulai kerja karyawan
   * 
   * @param sp salary payment ID, not if this method call for processing in batch
   * @param spe salary payment employee ID, not if this method call for individual processing
   * @return list of employee with its masa pph. 12 for full year, proportional if join/retire in this year.
   */
  public static HashMap<BusinessPartner, Double> getMasaPPh(pyr_salarypayment sp, pyr_sp_employee spe) {
	  double masaPPh = 0;
	  HashMap<BusinessPartner, Double> output = new HashMap<BusinessPartner, Double>();
	  List<pyr_sp_employee> speList = new ArrayList<pyr_sp_employee>();
	  if (spe!=null){ 
		  speList.add(spe);
	  } else{
		  if (sp==null)
			  throw new OBException("@pph_bothSPandSPEisNull@");
		  speList = sp.getPyrSpEmployeeList();
	  }

	  Date endofday = new Date(new BigDecimal("253399640400000").longValue()); //31-12-9999
	  
	  
	  for (pyr_sp_employee spe2 : speList){
		  String employeeidentifier = spe2.getBusinessPartner().getSearchKey()+"-"+spe2.getBusinessPartner().getName();
		  System.err.println("processing "+employeeidentifier);
		  pyr_salarypayment sp2 = spe2.getSalaryPayment();
		  Date effectivedate = sp2.getEffectiveDate();
		  BusinessPartner employee = spe2.getBusinessPartner();
		  Date joinDate = employee.getHrisJoindate();
		  if (joinDate == null)
			  throw new OBException("Join date is empty for "+employeeidentifier+". Join date required to calculate masa PPh.");
		  Date retireDate = employee.getHrisRetirementdate();
		  if (retireDate==null)
			  retireDate = endofday;
		  
		  Calendar calendar = Calendar.getInstance();
	      calendar.setTime(effectivedate);
	      calendar.set(Calendar.MONTH, 11);
	      calendar.set(Calendar.DAY_OF_MONTH, 31);
	      Long endofyear = calendar.getTimeInMillis();
	      calendar.set(Calendar.MONTH, 0);
	      calendar.set(Calendar.DAY_OF_MONTH, 1);
	      Long startofyear = calendar.getTimeInMillis();
	      calendar.setTime(joinDate);
	      Long joindatelong = calendar.getTimeInMillis();
	      masaPPh=12;
	      if (startofyear<=joindatelong && joindatelong<=endofyear) {
	    	  calendar.setTime(joinDate);
	    	  int bulanmasuk = calendar.get(Calendar.MONTH);
	    	  masaPPh=12-bulanmasuk;
	      }
	      calendar.setTime(retireDate);
	      long retiredatelong = calendar.getTimeInMillis();
	      if (startofyear<=retiredatelong && retiredatelong<=endofyear) {
	    	  calendar.setTime(retireDate);
	    	  int bulanpensiun = calendar.get(Calendar.MONTH);
	    	  masaPPh=bulanpensiun+1;
	      }
		  output.put(employee, masaPPh);
	  }
	  
	  return output;
		  
  }
  

  /**
   * Mengambil Pendapatan bulan berjalan
   * 
   * @param sp_employee
   * @param businessPartner
   * @param isAnualize
   * @param taxmode
   * @param valuetadate
   * @param sp
   * @return
   */
  public static double getPotonganBulanIni(pyr_sp_employee sp_employee,
      String taxmode) { //TODO jika method ini dipanggil dalam loop, maka akan ada select in the loop, buruk dalam hal performance.
    double curdeduction = 0;

    try {
    	String strQuery = "select coalesce(sum(amount),0) from pyr_spe_deduction sped"
          		+ " where sped.employeeSalaryPayment=?"
          		+ " and sped.pYRDeduction.pphTaxmode=?";
        final Query q = OBDal.getInstance().getSession().createQuery(strQuery);
        q.setParameter(0, sp_employee);
        q.setParameter(1, taxmode);
        final ScrollableResults rs = q.scroll(ScrollMode.FORWARD_ONLY);
        while (rs.next()) {
          curdeduction = ((BigDecimal) rs.get()[0]).doubleValue();
          break;
        }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return Math.ceil(curdeduction);
  }

  /**
   * Mengambil Pendapatan bulan berjalan
   * 
   * @param sp_employee
   * @param isAnualize
   * @param taxmode
   * @return
   */
  public static double getPendapatanBulanIni(pyr_sp_employee sp_employee,
      Boolean isAnualize, String taxmode) { //TODO jika method ini dipanggil dalam loop, maka akan ada select in the loop, buruk dalam hal performance.
    double curincome = 0;

    try {
      String strQuery = "select coalesce(sum(amount),0) from pyr_spe_earning spee"
      		+ " where spee.employeeSalaryPayment=?"
      		+ " and spee.earning.pphTaxmode=? and spee.earning.pphIsannualized=?";
      final Query q = OBDal.getInstance().getSession().createQuery(strQuery);
      q.setParameter(0, sp_employee);
      q.setParameter(1, taxmode);
      q.setParameter(2, isAnualize);
      final ScrollableResults rs = q.scroll(ScrollMode.FORWARD_ONLY);
      while (rs.next()) {
    	  curincome = ((BigDecimal) rs.get()[0]).doubleValue();
    	  break;
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return curincome;

  }

  /**
   * memasukan tarif progresif
   * 
   * @param efectivedate
   *          tanggal eksekusi
   * @param pkp
   *          penghasilan kena pajak
   * @return
   */
  public static double getprogresifpph(Date efectivedate, double pkp) {
    double pph21terhutang = 0;
    try {
      StringBuilder str = new StringBuilder();
      str.append("as pph where pph.validFromDate<=? and pph.validToDate>=? ");
      str.append("order by lowerbound asc");
      OBQuery<pph_tarifprogresif> tarif = OBDal.getInstance().createQuery(pph_tarifprogresif.class,
          str.toString());
      List<Object> param = new ArrayList<Object>();
      param.add(0, efectivedate);
      param.add(1, efectivedate);
      tarif.setParameters(param);

      for (pph_tarifprogresif pph : tarif.list()) {
        if (pkp <= pph.getUperbound().doubleValue()) {
          pph21terhutang = pph21terhutang + (pkp - pph.getLowerbound().doubleValue())
              * pph.getProgresiverate().doubleValue() / 100;
          break;
        } else {
          pph21terhutang = pph21terhutang
              + (pph.getUperbound().doubleValue() - pph.getLowerbound().doubleValue())
              * pph.getProgresiverate().doubleValue() / 100;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    pph21terhutang = Math.floor(pph21terhutang);

    return pph21terhutang;
  }
  
  public static double getMaxBiayaJabatanSetahun(Date efectivedate) {
	  double maxbiayajabatan = 0;

	    try {
	      StringBuilder str = new StringBuilder();
	      str.append("as pph where pph.validFromDate<=? and pph.validToDate>=?");
	      OBQuery<pph_tarifpph21> tarifpph21 = OBDal.getInstance().createQuery(pph_tarifpph21.class,
	          str.toString());

	      List<Object> param = new ArrayList<Object>();
	      param.add(0, efectivedate);
	      param.add(1, efectivedate);
	      tarifpph21.setParameters(param);

	      List<pph_tarifpph21> tarif = tarifpph21.list();

	      for (pph_tarifpph21 pph : tarif) {
	        maxbiayajabatan = (pph.getMaximumPkp()) == null ? 0 : pph.getMaximumPkp().doubleValue();
	      }
	      
	      maxbiayajabatan=maxbiayajabatan*12;

	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return maxbiayajabatan;
	  
  }
  
  public static double getRateBiayaJabatanSetahun(Date efectivedate) {
	  double ratebiayajabatan = 0;

	    try {
	      StringBuilder str = new StringBuilder();
	      str.append("as pph where pph.validFromDate<=? and pph.validToDate>=?");
	      OBQuery<pph_tarifpph21> tarifpph21 = OBDal.getInstance().createQuery(pph_tarifpph21.class,
	          str.toString());

	      List<Object> param = new ArrayList<Object>();
	      param.add(0, efectivedate);
	      param.add(1, efectivedate);
	      tarifpph21.setParameters(param);

	      List<pph_tarifpph21> tarif = tarifpph21.list();

	      for (pph_tarifpph21 pph : tarif) {
	        ratebiayajabatan = (pph.getRate()) == null ? 0 : (double) pph.getRate();
	      }
	      

	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return ratebiayajabatan;
	  
  }
  
  public static HashMap<BusinessPartner, Double> loadBrutoTotalGrossJanNov(pyr_salarypayment sp, pyr_sp_employee employee, ConnectionProvider conn, Connection connection){
	  HashMap<BusinessPartner, Double> brutoGrossJanSampaiNovMap = new HashMap<BusinessPartner, Double>();
	  
	  Date paymentDate = sp.getEffectiveDate();
	  Long year = getYear(paymentDate);
	  double rutinsebulangross = 0.00;
	  double pendapatanlaingross = 0.00;
	  try {
		  //disetahunkan gross
		  String strQuery="select c_bpartner_id, sum(coalesce(rutinsebulangross,0)) as jumlah"
		  		+ " from pph_pph21"
		  		+ " where year=?"
		  		+ " and month>=1 and month<=11";
		  if (employee!=null)
			  strQuery=strQuery+" and c_bpartner_id=?";
		  strQuery=strQuery+" and pendapatantidakteratursaja='N'"
		  		+ " group by c_bpartner_id";
		  PreparedStatement ps;
	      ps = connection.prepareStatement(strQuery);
	      ps.setLong(1, year);
	      if (employee!=null)
	    	  ps.setString(2, employee.getBusinessPartner().getId());
	      ResultSet result=ps.executeQuery();	      
	      while (result.next()) {
	    	  rutinsebulangross = result.getBigDecimal("jumlah").doubleValue();
	    	  String bpID = result.getString("c_bpartner_id");
	    	  BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
	    	  brutoGrossJanSampaiNovMap.put(bp, rutinsebulangross);
	      }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }  
	  
	  try {
		  //tidak teratur gross
		  String strQuery="select c_bpartner_id, coalesce(sum(penghasilantidakteratur),0) as jumlah"
		  		+ " from pph_pph21"
		  		+ " where year=?"
		  		+ " and month>=1 and month<=11";
		  if (employee!=null)
			  strQuery = strQuery + " and c_bpartner_id=?";
		  strQuery = strQuery + " group by c_bpartner_id";
		  PreparedStatement ps;
	      ps = connection.prepareStatement(strQuery);
	      ps.setLong(1, year);
	      if (employee!=null)
	    	  ps.setString(2, employee.getBusinessPartner().getId());
	      ResultSet result=ps.executeQuery();
	      while (result.next()) {
	    	  pendapatanlaingross = result.getBigDecimal("jumlah").doubleValue();
	    	  String bpID = result.getString("c_bpartner_id");
	    	  BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
	    	  double brutotemp=0;
	    	  if (brutoGrossJanSampaiNovMap.containsKey(bp)){
	    		  brutotemp = brutoGrossJanSampaiNovMap.get(bp);
	    		  brutoGrossJanSampaiNovMap.put(bp, brutotemp+pendapatanlaingross);
	    	  }
	      }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  return brutoGrossJanSampaiNovMap;
  }
  
  public static HashMap<BusinessPartner, Double> loadBrutoTotalJanSampaiNov(pyr_salarypayment sp, pyr_sp_employee employee, 
		  ConnectionProvider conn, Connection connection){
	  HashMap<BusinessPartner, Double> output = new HashMap<BusinessPartner, Double>();
	  Date paymentDate = sp.getEffectiveDate();
	  Long year = getYear(paymentDate);
	  double rutinsebulangross = 0.00;
	  double pendapatantidakteratur = 0.00;
	  try {
		  String strQuery="select c_bpartner_id, coalesce(sum(rutinsebulangross),0) as jumlah"
		  		+ " from pph_pph21"
		  		+ " where year=?"
		  		+ " and month>=1 and month<=11";
		  if (employee!=null)
			  strQuery=strQuery+ " and c_bpartner_id=?";
		  strQuery=strQuery+ " and pendapatantidakteratursaja='N'"
		  		+ " group by c_bpartner_id";
		  PreparedStatement ps;
	      ps = connection.prepareStatement(strQuery);
	      ps.setLong(1, year);
	      if (employee!=null)
	    	  ps.setString(2, employee.getBusinessPartner().getId());
	      ResultSet result=ps.executeQuery();
	      while (result.next()) {
	    	  rutinsebulangross = result.getBigDecimal("jumlah").doubleValue();
	    	  String bpID = result.getString("c_bpartner_id");
	    	  BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
	    	  output.put(bp, rutinsebulangross);
	      }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }  
	  
	  try {
		  String strQuery="select c_bpartner_id, sum(coalesce(penghasilantidakteratur,0)+coalesce(penghasilantidakteraturnett,0)) as jumlah"
		  		+ " from pph_pph21"
		  		+ " where year=?"
		  		+ " and month>=1 and month<=11";
		  if (employee!=null)
			  strQuery=strQuery+ " and c_bpartner_id=?";
		  strQuery=strQuery+ " group by c_bpartner_id";
		  PreparedStatement ps;
	      ps = connection.prepareStatement(strQuery);
	      ps.setLong(1, year);
	      if (employee!=null)
	    	  ps.setString(2, employee.getBusinessPartner().getId());
	      ResultSet result=ps.executeQuery();
	      while (result.next()) {
	    	  pendapatantidakteratur = result.getBigDecimal("jumlah").doubleValue();
	    	  String bpID = result.getString("c_bpartner_id");
	    	  BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
	    	  if (output.containsKey(bp)){
	    		  double temp = output.get(bp);
	    		  output.put(bp, temp+pendapatantidakteratur);
	    	  } else
	    		  output.put(bp, rutinsebulangross);
	      }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  return output;
  }
 
  
 public static HashMap<BusinessPartner, Double> loadPengurangPajakBulanJanSampaiNov(pyr_salarypayment sp, pyr_sp_employee employee, 
		 ConnectionProvider conn, Connection connection) {
	 HashMap<BusinessPartner, Double> output = new HashMap<BusinessPartner, Double>();
	 Date paymentDate = sp.getEffectiveDate();
	 long year = getYear(paymentDate);
	 double pengurangPajak = 0.00;
	  try {
		  String strQuery="select c_bpartner_id, coalesce(sum(pengurangpajaksebulan),0) as jumlah"
		  		+ " from pph_pph21"
		  		+ " where year=?"
		  		+ " and month>=1 and month<=11";
		  if (employee!=null)
			  strQuery=strQuery+" and c_bpartner_id=?";
		  strQuery=strQuery+ " and pendapatantidakteratursaja='N'"
		  		+ " group by c_bpartner_id";
		  PreparedStatement ps;
	      ps = connection.prepareStatement(strQuery);
	      ps.setLong(1, year);
	      if (employee!=null)
	    	  ps.setString(2, employee.getBusinessPartner().getId());
	      ResultSet result=ps.executeQuery();
	      while (result.next()) {
	    	  pengurangPajak = result.getBigDecimal("jumlah").doubleValue();
	    	  String bpID = result.getString("c_bpartner_id");
	    	  BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
	    	  output.put(bp, pengurangPajak);
	        }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }  
	  
	  return output;
	    
  	}
 
 public static double getPendapatanLainNettBulanJanSampaiNov(pyr_sp_employee employee) {
	 pyr_salarypayment salarypayment = employee.getSalaryPayment();
	 Date paymentDate = salarypayment.getEffectiveDate();
	 long year = getYear(paymentDate);
	 double penghasilanTidakTeraturNett = 0.00;
	  try {
		  final String strQuery="select coalesce(sum(penghasilantidakteraturnett),0) as jumlah"
		  		+ " from pph_pph21"
		  		+ " where year=?"
		  		+ " and month>=? and month<=?"
		  		+ " and c_bpartner_id=?";
		  PreparedStatement ps;
	      ConnectionProvider conn = new DalConnectionProvider();
	      Connection connection = conn.getConnection();
	      ps = connection.prepareStatement(strQuery);
	      ps.setLong(1, year);
	      ps.setString(2, employee.getBusinessPartner().getId());
	      ResultSet rs=ps.executeQuery();
	      while (rs.next()) {
	    	  penghasilanTidakTeraturNett = rs.getBigDecimal("jumlah").doubleValue(); 
	        }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }  
	  
	  return penghasilanTidakTeraturNett;
	    
  	}

  
 
 public static HashMap<BusinessPartner, Double> loadPPh21GrossBulanJanSampaiNov(pyr_salarypayment sp, pyr_sp_employee employee, 
		 ConnectionProvider conn, Connection connection) {
	 HashMap<BusinessPartner, Double> pph21GrossBulanJanSampaiNovMap = new HashMap<BusinessPartner, Double>();
	 long year = getYear(sp.getEffectiveDate());
	 double pph21terpotong = 0.00;
	  try {
		  String strQuery="select c_bpartner_id, coalesce(sum(tunjanganpph21bulanini),0) as jumlah"
		  		+ " from pph_pph21 where year=? and month>=1 and month<=11 ";
		  if (employee!=null)
			  strQuery=strQuery+ " and c_bpartner_id=?";
		  strQuery=strQuery+" group by c_bpartner_id";
		  PreparedStatement ps;
	      ps = connection.prepareStatement(strQuery);
	      ps.setLong(1, year);
	      if (employee!=null)
	    	  ps.setString(2, employee.getBusinessPartner().getId());
	      ResultSet rs=ps.executeQuery();
	      while (rs.next()) {
	    	  pph21terpotong = rs.getBigDecimal("jumlah").doubleValue();
	    	  String bpID = rs.getString("c_bpartner_id");
	    	  BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
	    	  pph21GrossBulanJanSampaiNovMap.put(bp, pph21terpotong);
	        }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }  
	  
	  return pph21GrossBulanJanSampaiNovMap;
 }
 
 public static HashMap<BusinessPartner, Double> loadPPh21TotalBulanJanSampaiNov(pyr_salarypayment sp, pyr_sp_employee employee, 
		 ConnectionProvider conn, Connection connection) {
	 HashMap<BusinessPartner, Double> output = new HashMap<BusinessPartner, Double>();
	 long year = getYear(sp.getEffectiveDate());
	 double pph21terpotong = 0.00;
	  try {
		  String strQuery="select c_bpartner_id, coalesce(sum(pph21terpotong),0) as jumlah"
		  		+ " from pph_pph21 where year=? and month>=1 and month<=11 ";
		  if (employee!=null)
			  strQuery=strQuery+ " and c_bpartner_id=?";
		  strQuery=strQuery+ " group by c_bpartner_id";
		  PreparedStatement ps;
	      ps = connection.prepareStatement(strQuery);
	      ps.setLong(1, year);
	      if (employee!=null)
	    	  ps.setString(2, employee.getBusinessPartner().getId());
	      ResultSet rs=ps.executeQuery();
	      while (rs.next()) {
	    	  pph21terpotong = rs.getBigDecimal("jumlah").doubleValue();
	    	  String bpID = rs.getString("c_bpartner_id");
	    	  BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
	    	  output.put(bp, pph21terpotong);
	        }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
	  }  
	  
	  return output;
 }

 /**
  * menghitung penghasilan bruto setahun / disetahunkan atas pendapatan gross (pajaknya ditanggung perusahaan)
  * @param tunjanganPPh21RutinBulanIni tunjangan pph21 atas penghasilan rutin di bulan ini
  * @param brutoGrossJanSampaiNov penghasilan bruto total (rutin dan tidak rutin tertanggung/tidak tertanggung dari jan sd nov
  * @param penghasilanRutinBulanIni penghasilan rutin bulan ini
  * @param pendapatanLainGrossBulanIni pendapatan tidak rutin tertanggung bulan ini
  * @param tunjanganPPh21PendapatanGrossLainBulanIni pendapatan tidak rutin, yang pajaknya ditanggung perusahaan, di bulan ini
  * @param masaPPh masa pph
  * @param paymentDate tanggal bayar
  * @return
  */
 public static double hitungBrutoSetahun(double tunjanganPPh21RutinBulanIni, double brutoGrossJanSampaiNov,
		 double penghasilanRutinBulanIni, double pendapatanLainGrossBulanIni, 
		 double tunjanganPPh21PendapatanGrossLainBulanIni, double masaPPh, Date paymentDate, boolean pajakrampung){
	  /*
	   * pajak rampung dan tidak rampung bedanya:
	   * kalau pajak rampung: brutoTotalSetahun diambil dari pph_pph21 dari bulan 1 sd 11 + (penghasilanRutinBulanIni+tunjanganPPh21BulanIni+pendapatanLainBulanIni)
	   * kalau pajak tidak rampung: brutoTotalSetahun = ((penghasilanRutinBulanIni+tunjanganPPh21BulanIni)*masaPPh)+ pendapatanLainBulanIni
	   * */
	 
	 Long month = getMonth(paymentDate);
	 double brutoTotalSetahun=0;
	  
	  if (month==11 || pajakrampung){
		  brutoTotalSetahun = brutoGrossJanSampaiNov+(penghasilanRutinBulanIni+tunjanganPPh21RutinBulanIni+pendapatanLainGrossBulanIni);
	  } else {
		  brutoTotalSetahun = ((penghasilanRutinBulanIni+tunjanganPPh21RutinBulanIni)*masaPPh)+
				  (pendapatanLainGrossBulanIni+tunjanganPPh21PendapatanGrossLainBulanIni);
	  }
	  
	  return brutoTotalSetahun;
	 
 }
 
public static HashMap<String, Double> loadPTKP(ConnectionProvider conn,
		Connection connection, Date effectivedate) {
	
	HashMap<String, Double> output = new HashMap<String, Double>();
    try {
      StringBuilder str = new StringBuilder();
      str.append("as ptkp where ptkp.validFromDate<=? and ptkp.validToDate>=? ");
      OBQuery<pph_set_ptkp> setptkp = OBDal.getInstance().createQuery(pph_set_ptkp.class,
          str.toString());
      List<Object> param = new ArrayList<Object>();
      param.add(0, effectivedate);
      param.add(1, effectivedate);
      setptkp.setParameters(param);
      setptkp.setMaxResult(1);
      for (pph_set_ptkp ptk : setptkp.list()) {
    	  output.put("TK", ptk.getT0().doubleValue());
    	  output.put("K/0", ptk.getK0().doubleValue());
    	  output.put("K/1", ptk.getK1().doubleValue());
    	  output.put("K/2", ptk.getK2().doubleValue());
    	  output.put("K/3", ptk.getK3().doubleValue());
    	  output.put("K/4", ptk.getK3().doubleValue());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return output;
}

public static HashMap<Double, Double> loadTarifProgresifPPh(ConnectionProvider conn,
		Connection connection, Date effectivedate) {
	
	HashMap<Double, Double> output = new HashMap<Double, Double>();
    try {
      StringBuilder str = new StringBuilder();
      str.append("as pph where pph.validFromDate<=? and pph.validToDate>=? ");
      OBQuery<pph_tarifprogresif> tarifprogressPPhQ = OBDal.getInstance().createQuery(pph_tarifprogresif.class,
          str.toString());
      List<Object> param = new ArrayList<Object>();
      param.add(0, effectivedate);
      param.add(1, effectivedate);
      tarifprogressPPhQ.setParameters(param);
      for (pph_tarifprogresif tarif : tarifprogressPPhQ.list()) {
    	  output.put(tarif.getUperbound().doubleValue(), tarif.getProgresiverate().doubleValue());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return output;
}

public static HashMap<BusinessPartner, Boolean> loadPenghasilanTidakTeraturSaja(ConnectionProvider conn,
		Connection connection, pyr_salarypayment sp, pyr_sp_employee spe, String tunjanganPPh21Key) throws SQLException{
	
	if (tunjanganPPh21Key==null || tunjanganPPh21Key.isEmpty())
		throw new OBException("@pph_tunjanganpphisnull@");
	HashMap<BusinessPartner, Boolean> output = new HashMap<BusinessPartner, Boolean>();
	String sqlQuery="select spe.c_bpartner_id,"
			+ " (case when (select count(*) from pyr_spe_earning spee"
			+ " inner join pyr_earning e on e.pyr_earning_id=spee.pyr_earning_id"
			+ " where spee.pyr_sp_employee_id=spe.pyr_sp_employee_id"
			+ " and e.em_pph_isannualized='Y'"
			+ " and e.value<>?)=0 then 'Y' else 'N' end) as penghasilantidakteratursaja"
			+ " from pyr_sp_employee spe";
	if (spe!=null)
		sqlQuery=sqlQuery + " where spe.pyr_sp_employee_id=?";
	else
		sqlQuery=sqlQuery + " where spe.pyr_salarypayment_id=?";
	PreparedStatement ps;
    ps = connection.prepareStatement(sqlQuery);
    ps.setString(1, tunjanganPPh21Key);
    if (spe!=null)
    	ps.setString(2, spe.getId());
    else
    	ps.setString(2, sp.getId());
    ResultSet result=ps.executeQuery();	      
    while (result.next()) {
    	String bpID = result.getString("c_bpartner_id");
    	String penghasilantidakteratursaja = result.getString("c_bpartner_id");
    	Boolean penghasilantidakteratursaja2 = false;
    	if (penghasilantidakteratursaja.equalsIgnoreCase("Y"))
    		penghasilantidakteratursaja2=true;
    	BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
    	output.put(bp, penghasilantidakteratursaja2);
    }

	return output;
}

public static HashMap<BusinessPartner, pyr_sp_employee> loadSpePeriodeSebelumnya(
		ConnectionProvider conn, Connection connection, pyr_salarypayment sp,
		pyr_sp_employee spe) {
	
	HashMap<BusinessPartner, pyr_sp_employee> spePeriodeSebelumnya = new HashMap<BusinessPartner, pyr_sp_employee>();
	
	if (spe!=null)
		sp=spe.getSalaryPayment();
	pyr_salarypayment prevSP = sp.getPphPrevsalarypayment();
	for (pyr_sp_employee prevSPE : prevSP.getPyrSpEmployeeList()){
		BusinessPartner bp = prevSPE.getBusinessPartner();
		spePeriodeSebelumnya.put(bp, prevSPE);
	}
	return spePeriodeSebelumnya;
}

public static HashMap<BusinessPartner, pph_pph21> loadPPhPeriodeSebelumnya(
		ConnectionProvider conn, Connection connection, pyr_salarypayment sp,
		pyr_sp_employee spe) throws SQLException {
	
	HashMap<BusinessPartner, pph_pph21> pphPeriodeSebelumnya = new HashMap<BusinessPartner, pph_pph21>();
	
	if (spe!=null)
		sp=spe.getSalaryPayment();
	if (sp==null)
		throw new OBException("salary payment is null");
	
	pyr_salarypayment prevSP = sp.getPphPrevsalarypayment();
	if (prevSP==null)
		return pphPeriodeSebelumnya;
	
	String sqlquery = "select a.pph_pph21_id, b.c_bpartner_id"
			+ " from pph_pph21 a"
			+ " inner join pyr_sp_employee b on b.pyr_sp_employee_id=a.pyr_sp_employee_id"
			+ " where b.pyr_salarypayment_id=?";
	PreparedStatement ps = connection.prepareStatement(sqlquery);
	ps.setString(1, prevSP.getId());
	ResultSet rs = ps.executeQuery();
	while (rs.next()){
		String pphID = rs.getString("pph_pph21_id");
		pph_pph21 pph = OBDal.getInstance().get(pph_pph21.class, pphID);
		String bpID = rs.getString("c_bpartner_id");
		BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
		pphPeriodeSebelumnya.put(bp, pph);
	}
	
	return pphPeriodeSebelumnya;
}

public static HashMap<BusinessPartner, PPhRampungBean> getPPhRampungMap(
		int tahuntakwim, pyr_sp_employee spe) {
	
	//pendapatan
	HashMap<BusinessPartner, PPhRampungBean> output = new HashMap<BusinessPartner, PPhRampungBean>();
	String sqlQuery = "select b.c_bpartner_id, c.em_pph_taxmode, sum(a.amount) as amount from pyr_spe_earning a"
			+ " inner join pyr_sp_employee b on b.pyr_sp_employee_id=a.pyr_sp_employee_id"
			+ " inner join pyr_earning c on c.pyr_earning_id=a.pyr_earning_id"
			+ " inner join pyr_salarypayment d on d.pyr_salarypayment_id=b.pyr_salarypayment_id"
			+ " where date_part('year',d.valutadate)=?";
	if (spe!=null)
		sqlQuery += " and b.c_bpartner_id=?";
	
	sqlQuery += " group by b.c_bpartner_id, c.em_pph_taxmode";
	
	try {
		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection = conn.getConnection();
		PreparedStatement ps;
		ps = connection.prepareStatement(sqlQuery);
		ps.setInt(1, tahuntakwim);
		if (spe!=null)
			ps.setString(2, spe.getBusinessPartner().getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			String bpID = rs.getString("c_bpartner_id");
			String taxmode = rs.getString("em_pph_taxmode");
			BigDecimal amount_bd = rs.getBigDecimal("amount");
			double amount=0.00;
			if (amount_bd!=null)
				amount=amount_bd.doubleValue();
			PPhRampungBean prb = null;
			BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
			if (output.containsKey(bp)){
				prb = output.get(bp);
			} else
				prb = new PPhRampungBean(bp, 0, 0, 0, 0, 0);
			
			if (taxmode.equalsIgnoreCase("GROSS"))
				prb.setGrossIncome(amount);
			else if (taxmode.equalsIgnoreCase("NETT"))
				prb.setNetIncome(amount);
			else if (taxmode.equalsIgnoreCase("EXEMPT"))
				prb.setNonTaxIncome(amount);
			
			output.put(bp, prb);
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NoConnectionAvailableException e) {
		e.printStackTrace();
	}
	
	//iuran
	sqlQuery = "select b.c_bpartner_id, sum(a.amount) as amount from pyr_spe_deduction a"
			+ " inner join pyr_sp_employee b on b.pyr_sp_employee_id=a.pyr_sp_employee_id"
			+ " inner join pyr_deduction c on c.pyr_deduction_id=a.pyr_deduction_id"
			+ " inner join pyr_salarypayment d on d.pyr_salarypayment_id=b.pyr_salarypayment_id"
			+ " where date_part('year',d.valutadate)=?";
	if (spe!=null)
		sqlQuery += " and b.c_bpartner_id=?";
	
	sqlQuery += " and c.em_pph_taxmode='DEDUCTIBLE'"
			+ " group by b.c_bpartner_id";
	try {
		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection = conn.getConnection();
		PreparedStatement ps;
		ps = connection.prepareStatement(sqlQuery);
		ps.setInt(1, tahuntakwim);
		if (spe!=null)
			ps.setString(2, spe.getBusinessPartner().getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			String bpID = rs.getString("c_bpartner_id");
			BigDecimal amount_bd = rs.getBigDecimal("amount");
			double amount=0.00;
			if (amount_bd!=null)
				amount=amount_bd.doubleValue();
			PPhRampungBean prb = null;
			BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
			if (output.containsKey(bp)){
				prb = output.get(bp);
			} else
				prb = new PPhRampungBean(bp, 0, 0, 0, 0, 0);
			
			prb.setDeductibleDeduction(amount);
			output.put(bp, prb);
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NoConnectionAvailableException e) {
		e.printStackTrace();
	}
	
	//uang muka
	sqlQuery = "select b.c_bpartner_id, sum(a.pph21terpotong) as uangmukapph from pph_pph21 a"
			+ " inner join pyr_sp_employee b on b.pyr_sp_employee_id=a.pyr_sp_employee_id"
			+ " inner join pyr_salarypayment d on d.pyr_salarypayment_id=b.pyr_salarypayment_id"
			+ " where date_part('year',d.valutadate)=?";
	if (spe!=null)
		sqlQuery += " and b.c_bpartner_id=?";
	
	sqlQuery += " group by b.c_bpartner_id";
	try {
		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection = conn.getConnection();
		PreparedStatement ps;
		ps = connection.prepareStatement(sqlQuery);
		ps.setInt(1, tahuntakwim);
		if (spe!=null)
			ps.setString(2, spe.getBusinessPartner().getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			String bpID = rs.getString("c_bpartner_id");
			BigDecimal amount_bd = rs.getBigDecimal("uangmukapph");
			double amount=0.00;
			if (amount_bd!=null)
				amount=amount_bd.doubleValue();
			PPhRampungBean prb = null;
			BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, bpID);
			if (output.containsKey(bp)){
				prb = output.get(bp);
			} else
				prb = new PPhRampungBean(bp, 0, 0, 0, 0, 0);
			
			prb.setPaidIncomeTax(amount);
			output.put(bp, prb);
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NoConnectionAvailableException e) {
		e.printStackTrace();
	}
	
	return output;
}
	  
}