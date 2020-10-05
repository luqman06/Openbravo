package org.wirabumi.hris.pph.utility;

public class BiayaJabatan {
	private double rateBiayaJabatan;
	private double maxBiayaJabatan;
	
	public BiayaJabatan(double rateBiayaJabatan, double maxBiayaJabatan) {
		super();
		this.rateBiayaJabatan = rateBiayaJabatan;
		this.maxBiayaJabatan = maxBiayaJabatan;
	}
	
	public double getBiayaJabatan(double brutoSetahun){
		double biayajabatan = brutoSetahun*this.rateBiayaJabatan/100.00;
		if (biayajabatan>=maxBiayaJabatan)
			biayajabatan=maxBiayaJabatan;
		return biayajabatan;
	}

}
