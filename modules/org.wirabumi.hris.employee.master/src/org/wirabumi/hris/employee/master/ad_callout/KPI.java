package org.wirabumi.hris.employee.master.ad_callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.wirabumi.hris.employee.master.data.hris_kpi;

public class KPI extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		try {
			String keyPerformance = info.getStringParameter("inphrisKpiId", null);
			hris_kpi kpi = OBDal.getInstance().get(hris_kpi.class, keyPerformance);
			String uom = kpi.getUOM().getId();
			String polarity = kpi.getPolaritas();
			
			info.addResult("inpcUomId", uom);
			info.addResult("inppolaritas", polarity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
