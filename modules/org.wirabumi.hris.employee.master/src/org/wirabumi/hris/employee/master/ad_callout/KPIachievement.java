package org.wirabumi.hris.employee.master.ad_callout;

import java.math.BigDecimal;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.wirabumi.hris.employee.master.data.KPIJobTitle;;

public class KPIachievement extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		try {
			String KpiversionID = info.getStringParameter("inphrisJtKpiId", null);
			KPIJobTitle jobtitleKPI = OBDal.getInstance().get(KPIJobTitle.class, KpiversionID);
			String polaritas = jobtitleKPI.getPolarity();
			BigDecimal Weight = jobtitleKPI.getWeight();
			String strActual = info.getStringParameter("inpactual", null);
			String strTarget = info.getStringParameter("inptarget", null);
			
			double Actual = strActual == "" ? 0:Double.parseDouble(strActual);
			double Target = Double.parseDouble(strTarget);
			double angka = 1;
			BigDecimal Achievement = new BigDecimal(0);
			BigDecimal weightAchievement = new BigDecimal(0);
			if (Actual != 0) {
				if(polaritas.equalsIgnoreCase("HIGHER BETTER")){
					double hasil = (Actual / Target)*100;
					Achievement = new BigDecimal(hasil);
				}else if(polaritas.equalsIgnoreCase("LOWER BETTER")){
					double kurang = Target+(Target - Actual);
					double bagi = kurang / Target;
					double hasil = bagi*100;
					Achievement = new BigDecimal(hasil);
				}else if(polaritas.equalsIgnoreCase("ZERO")){
					double kurang = Actual - Target;
					double bagi = Math.abs(kurang)/ Target;
					double hasil = (angka - bagi)*100;
					Achievement = new BigDecimal(hasil);
				}
				
				Achievement = Achievement.setScale(2, BigDecimal.ROUND_HALF_UP);
				weightAchievement = (Achievement.multiply(Weight)).divide(new BigDecimal(100));
			}

			info.addResult("inpachievement", Achievement);
			info.addResult("inpweightedachievment", weightAchievement);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
