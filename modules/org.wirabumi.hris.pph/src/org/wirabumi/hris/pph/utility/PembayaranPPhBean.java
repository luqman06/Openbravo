package org.wirabumi.hris.pph.utility;

import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.pph.pph_pph21;

public class PembayaranPPhBean {
	//java bean untuk membuat record di table pph_pph21
	/*
	 * SPOK: pendapatan <rutin>/<lain> <gross>/<nett> <sebulan>/<setahun> [bulan lalu]
	 * contoh: Pendapatan Rutin Gross Setahun pada Bulan Lalu
	 */
	private pyr_sp_employee spe;
	private boolean pendapatanlainsaja;
	private boolean pajakrampung;
	private int fiscalyear;
	private int month;
	private String taxmaritalstatus;
	private double masapph;
	private double pendapatanRutinGrossSetahun;
	private double pendapatanRutinNettSetahun;
	private double pendapatanLainGross;
	private double pendapatanLainNett;
	private double pendapatanBrutoTotalSetahun;
	private double biayaJabatanSetahun;
	private double pengurangPajakSetahun;
	private double ptkpSetahun;
	private double pkpSetahun;
	private double pph21setahun;
	private double pph21sebulan;
	private double tunjanganPPh21BulanIni;
	
	//field untuk pendapatan bulan lalu
	private double pendapatanRutinGrossSebulanBulanLalu;
	private double pendapatanRutinNettSebulanBulanLalu;
	private double pendapatanLainGrossBulanLalu;
	private double pendapatanLainNettBulanLalu;
	private double pengurangPajakSebulanBulanLalu;
	private pph_pph21 pph21BulanLalu;
	
	//tanpa bulan lalu
	public PembayaranPPhBean(pyr_sp_employee spe,
			boolean pendapatanlainsaja, boolean pajakrampung, int fiscalyear,
			int month, String taxmaritalstatus, double masapph,
			double penghasilanrutinsetahungross, double penghasilanrutinsetahunnett,
			double penghasilanlaingross, double penghasilanlaingnett, double penghasilanbrutosetahuntotal,
			double biayajabatansetahun, double pengurangpajaksetahun, double ptkpsetahun,
			double pkpsetahun, double pph21setahun, double pph21sebulan, double tunjanganpajaksebulan) {
		
		super();
		
		if (spe==null)
			throw new IllegalArgumentException("employee salary payment is null");
		if (taxmaritalstatus==null)
			throw new IllegalArgumentException("tax marital status is null");
		
		this.spe = spe;
		this.pendapatanlainsaja = pendapatanlainsaja;
		this.pajakrampung = pajakrampung;
		this.fiscalyear = fiscalyear;
		this.month = month;
		this.taxmaritalstatus = taxmaritalstatus;
		this.masapph = masapph;
		this.pendapatanRutinGrossSetahun = penghasilanrutinsetahungross;
		this.pendapatanRutinNettSetahun = penghasilanrutinsetahunnett;
		this.pendapatanLainGross = penghasilanlaingross;
		this.pendapatanLainNett = penghasilanlaingnett;
		this.pendapatanBrutoTotalSetahun = penghasilanbrutosetahuntotal;
		this.biayaJabatanSetahun = biayajabatansetahun;
		this.pengurangPajakSetahun = pengurangpajaksetahun;
		this.ptkpSetahun = ptkpsetahun;
		this.pkpSetahun = pkpsetahun;
		this.pph21setahun = pph21setahun;
		this.pph21sebulan = pph21sebulan;
		this.tunjanganPPh21BulanIni = tunjanganpajaksebulan;
	}
	
	
	//dengan bulan lalu
	public PembayaranPPhBean(pyr_sp_employee spe,
			boolean pendapatanlainsaja, boolean pajakrampung, int fiscalyear,
			int month, String taxmaritalstatus, double masapph, 
			double penghasilanrutinsetahungross, double penghasilanrutinsetahunnett,
			double penghasilanlaingross, double penghasilanlaingnett, double penghasilanbrutosetahuntotal,
			double biayajabatansetahun, double pengurangpajaksetahun, double ptkpsetahun,
			double pkpsetahun, double pph21setahun, double pph21sebulan, double tunjanganpajaksebulan,
			double penghasilanrutinsebulangrossbulanlalu, double penghasilanrutinsebulannettbulanlalu,
			double penghasilanlainsebulangrossbulanlalu, double penghasilanlainsebulannettbulanlalu,
			double pengurangpajakbulanlalu, pph_pph21 pph21bulanlalu) {
		super();
		
		if (spe==null)
			throw new IllegalArgumentException("employee salary payment is null");
		if (taxmaritalstatus==null)
			throw new IllegalArgumentException("tax marital status is null");
		if (pph21bulanlalu==null)
			throw new IllegalArgumentException("pph21 bulan sebelumya is null");
		
		this.spe = spe;
		this.pendapatanlainsaja = pendapatanlainsaja;
		this.pajakrampung = pajakrampung;
		this.fiscalyear = fiscalyear;
		this.month = month;
		this.taxmaritalstatus = taxmaritalstatus;
		this.masapph = masapph;
		this.pendapatanRutinGrossSetahun = penghasilanrutinsetahungross;
		this.pendapatanRutinNettSetahun = penghasilanrutinsetahunnett;
		this.pendapatanLainGross = penghasilanlaingross;
		this.pendapatanLainNett = penghasilanlaingnett;
		this.pendapatanBrutoTotalSetahun = penghasilanbrutosetahuntotal;
		this.biayaJabatanSetahun = biayajabatansetahun;
		this.pengurangPajakSetahun = pengurangpajaksetahun;
		this.ptkpSetahun = ptkpsetahun;
		this.pkpSetahun = pkpsetahun;
		this.pph21setahun = pph21setahun;
		this.pph21sebulan = pph21sebulan;
		this.tunjanganPPh21BulanIni = tunjanganpajaksebulan;
		this.pendapatanRutinGrossSebulanBulanLalu = penghasilanrutinsebulangrossbulanlalu;
		this.pendapatanRutinNettSebulanBulanLalu = penghasilanrutinsebulannettbulanlalu;
		this.pendapatanLainGrossBulanLalu = penghasilanlainsebulangrossbulanlalu;
		this.pendapatanLainNettBulanLalu = penghasilanlainsebulannettbulanlalu;
		this.pengurangPajakSebulanBulanLalu = pengurangpajakbulanlalu;
		this.pph21BulanLalu = pph21bulanlalu;
	}
	
	
	public double getMasapph() {
		return masapph;
	}

	public pph_pph21 getPph21bulanlalu() {
		return pph21BulanLalu;
	}

	public pyr_sp_employee getEmployeeSalaryPayment() {
		return spe;
	}

	public boolean isPendapatanlainsaja() {
		return pendapatanlainsaja;
	}

	public boolean isPajakrampung() {
		return pajakrampung;
	}

	public int getFiscalyear() {
		return fiscalyear;
	}

	public int getMonth() {
		return month;
	}

	public String getTaxmaritalstatus() {
		return taxmaritalstatus;
	}

	public double getPendapatanRutinGrossSetahun() {
		return pendapatanRutinGrossSetahun;
	}

	public double getPendapatanRutinNettSetahun() {
		return pendapatanRutinNettSetahun;
	}

	public double getPendapatanLainGross() {
		return pendapatanLainGross;
	}

	public double getPendapatanLainNett() {
		return pendapatanLainNett;
	}

	public double getPendapatanBrutoTotalSetahun() {
		return pendapatanBrutoTotalSetahun;
	}

	public double getBiayaJabatanSetahun() {
		return biayaJabatanSetahun;
	}

	public double getPengurangPajakSetahun() {
		return pengurangPajakSetahun;
	}

	public double getPTKPSetahun() {
		return ptkpSetahun;
	}

	public double getPKPSetahun() {
		return pkpSetahun;
	}

	public double getPPh21Setahun() {
		return pph21setahun;
	}

	public double getPPh21Sebulan() {
		return pph21sebulan;
	}

	public double getTunjanganPPh21BulanIni() {
		return tunjanganPPh21BulanIni;
	}
	
	public double getPendapatanRutinGrossSebulanBulanLalu() {
		return pendapatanRutinGrossSebulanBulanLalu;
	}

	public double getPendapatanRutinNettSebulanBulanLalu() {
		return pendapatanRutinNettSebulanBulanLalu;
	}

	public double getPendapatanLainGrossBulanLalu() {
		return pendapatanLainGrossBulanLalu;
	}

	public double getPendapatanLainNettBulanLalu() {
		return pendapatanLainNettBulanLalu;
	}

	public double getPengurangPajakSebulanBulanLalu() {
		return pengurangPajakSebulanBulanLalu;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(biayaJabatanSetahun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + fiscalyear;
		result = prime * result + month;
		result = prime * result + (pajakrampung ? 1231 : 1237);
		result = prime * result + (pendapatanlainsaja ? 1231 : 1237);
		temp = Double.doubleToLongBits(pendapatanBrutoTotalSetahun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pendapatanLainNett);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pendapatanLainGross);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pendapatanLainGrossBulanLalu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pendapatanLainNettBulanLalu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pendapatanRutinGrossSebulanBulanLalu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pendapatanRutinNettSebulanBulanLalu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pendapatanRutinGrossSetahun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pendapatanRutinNettSetahun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pengurangPajakSebulanBulanLalu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pengurangPajakSetahun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pkpSetahun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((pph21BulanLalu == null) ? 0 : pph21BulanLalu.hashCode());
		temp = Double.doubleToLongBits(pph21sebulan);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pph21setahun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(ptkpSetahun);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((spe == null) ? 0 : spe.hashCode());
		result = prime
				* result
				+ ((taxmaritalstatus == null) ? 0 : taxmaritalstatus.hashCode());
		temp = Double.doubleToLongBits(tunjanganPPh21BulanIni);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PembayaranPPhBean other = (PembayaranPPhBean) obj;
		if (Double.doubleToLongBits(biayaJabatanSetahun) != Double
				.doubleToLongBits(other.biayaJabatanSetahun))
			return false;
		if (fiscalyear != other.fiscalyear)
			return false;
		if (month != other.month)
			return false;
		if (pajakrampung != other.pajakrampung)
			return false;
		if (pendapatanlainsaja != other.pendapatanlainsaja)
			return false;
		if (Double.doubleToLongBits(pendapatanBrutoTotalSetahun) != Double
				.doubleToLongBits(other.pendapatanBrutoTotalSetahun))
			return false;
		if (Double.doubleToLongBits(pendapatanLainNett) != Double
				.doubleToLongBits(other.pendapatanLainNett))
			return false;
		if (Double.doubleToLongBits(pendapatanLainGross) != Double
				.doubleToLongBits(other.pendapatanLainGross))
			return false;
		if (Double.doubleToLongBits(pendapatanLainGrossBulanLalu) != Double
				.doubleToLongBits(other.pendapatanLainGrossBulanLalu))
			return false;
		if (Double.doubleToLongBits(pendapatanLainNettBulanLalu) != Double
				.doubleToLongBits(other.pendapatanLainNettBulanLalu))
			return false;
		if (Double.doubleToLongBits(pendapatanRutinGrossSebulanBulanLalu) != Double
				.doubleToLongBits(other.pendapatanRutinGrossSebulanBulanLalu))
			return false;
		if (Double.doubleToLongBits(pendapatanRutinNettSebulanBulanLalu) != Double
				.doubleToLongBits(other.pendapatanRutinNettSebulanBulanLalu))
			return false;
		if (Double.doubleToLongBits(pendapatanRutinGrossSetahun) != Double
				.doubleToLongBits(other.pendapatanRutinGrossSetahun))
			return false;
		if (Double.doubleToLongBits(pendapatanRutinNettSetahun) != Double
				.doubleToLongBits(other.pendapatanRutinNettSetahun))
			return false;
		if (Double.doubleToLongBits(pengurangPajakSebulanBulanLalu) != Double
				.doubleToLongBits(other.pengurangPajakSebulanBulanLalu))
			return false;
		if (Double.doubleToLongBits(pengurangPajakSetahun) != Double
				.doubleToLongBits(other.pengurangPajakSetahun))
			return false;
		if (Double.doubleToLongBits(pkpSetahun) != Double
				.doubleToLongBits(other.pkpSetahun))
			return false;
		if (pph21BulanLalu == null) {
			if (other.pph21BulanLalu != null)
				return false;
		} else if (!pph21BulanLalu.equals(other.pph21BulanLalu))
			return false;
		if (Double.doubleToLongBits(pph21sebulan) != Double
				.doubleToLongBits(other.pph21sebulan))
			return false;
		if (Double.doubleToLongBits(pph21setahun) != Double
				.doubleToLongBits(other.pph21setahun))
			return false;
		if (Double.doubleToLongBits(ptkpSetahun) != Double
				.doubleToLongBits(other.ptkpSetahun))
			return false;
		if (spe == null) {
			if (other.spe != null)
				return false;
		} else if (!spe.equals(other.spe))
			return false;
		if (taxmaritalstatus == null) {
			if (other.taxmaritalstatus != null)
				return false;
		} else if (!taxmaritalstatus.equals(other.taxmaritalstatus))
			return false;
		if (Double.doubleToLongBits(tunjanganPPh21BulanIni) != Double
				.doubleToLongBits(other.tunjanganPPh21BulanIni))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "PembayaranPPhBean [spe=" + spe
				+ ", pendapatanlainsaja=" + pendapatanlainsaja
				+ ", pajakrampung=" + pajakrampung + ", fiscalyear="
				+ fiscalyear + ", month=" + month + ", taxmaritalstatus="
				+ taxmaritalstatus + ", penghasilanrutinsetahungross="
				+ pendapatanRutinGrossSetahun
				+ ", penghasilanrutinsetahunnett="
				+ pendapatanRutinNettSetahun + ", penghasilanlaingross="
				+ pendapatanLainGross + ", penghasilanlaingnett="
				+ pendapatanLainNett
				+ ", penghasilanbrutosetahuntotal="
				+ pendapatanBrutoTotalSetahun + ", biayajabatansetahun="
				+ biayaJabatanSetahun + ", pengurangpajaksetahun="
				+ pengurangPajakSetahun + ", ptkpsetahun=" + ptkpSetahun
				+ ", pkpsetahun=" + pkpSetahun + ", pph21setahun="
				+ pph21setahun + ", pph21sebulan=" + pph21sebulan
				+ ", tunjanganpajaksebulan=" + tunjanganPPh21BulanIni
				+ ", penghasilanrutinsebulangrossbulanlalu="
				+ pendapatanRutinGrossSebulanBulanLalu
				+ ", penghasilanrutinsebulannettbulanlalu="
				+ pendapatanRutinNettSebulanBulanLalu
				+ ", penghasilanlainsebulangrossbulanlalu="
				+ pendapatanLainGrossBulanLalu
				+ ", penghasilanlainsebulannettbulanlalu="
				+ pendapatanLainNettBulanLalu
				+ ", pengurangpajakbulanlalu=" + pengurangPajakSebulanBulanLalu
				+ ", pph21bulanlalu=" + pph21BulanLalu+"]";
	}
	
	

}
