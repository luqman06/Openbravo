package org.wirabumi.hris.pph.utility;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.database.ConnectionProvider;
import org.wirabumi.hris.pph.pph_tarifprogresif;

public class TarifProgresifPPh {
	SortedMap<Double, Double> TarifProgresif = null;
	Set<Double> BatasAtas = null;
	
	//constructor untuk inisialiasi tarif progresif dan batas atas, diisi dari db, berdasarkan effective date
	public TarifProgresifPPh(ConnectionProvider conn, Connection connection, Date effectivedate){
		TarifProgresif = new TreeMap<Double, Double>();
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
	    	  TarifProgresif.put(tarif.getUperbound().doubleValue(), tarif.getProgresiverate().doubleValue());
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    
	    BatasAtas = TarifProgresif.keySet();
		
	}
	
	public double getprogresifpph(double pkp){
		double sisapenghasilan = pkp;
	    double pphterhutang = 0;
	    for (Double batasAtas : BatasAtas){
	    	if (pkp>=batasAtas){
	    		sisapenghasilan = sisapenghasilan-batasAtas;
	    		double tarifpph = TarifProgresif.get(batasAtas);
	    		pphterhutang = pphterhutang+(batasAtas*tarifpph/100.00);
	    	} else {
	    		double tarifpph = TarifProgresif.get(batasAtas);
	    		pphterhutang = pphterhutang+(sisapenghasilan*tarifpph/100.00);
	    		break;
	    	}
	    }
		return pphterhutang;
	}

}
