package org.wirabumi.hris.employee.master.ad_callout;

import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;

public class EmployeeInfoCallOut extends SimpleCallout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		String jobtitleID = info.getStringParameter("inphrisJobtitleId", null);
		if (jobtitleID==null || jobtitleID.isEmpty())
			return;
		hris_jobtitle jobtitle = OBDal.getInstance().get(hris_jobtitle.class, jobtitleID);
		if (jobtitle==null)
			return;
		Costcenter costcenter = jobtitle.getCostCenter();
		if (costcenter==null)
			return;
		info.addResult("inpcCostcenterId", costcenter.getId());
		OBCriteria<CostCenter> maCostcenterC = OBDal.getInstance().createCriteria(CostCenter.class);
		maCostcenterC.add(Restrictions.eq(CostCenter.PROPERTY_SEARCHKEY, costcenter.getSearchKey()));
		List<CostCenter> maCostcenterL = maCostcenterC.list();
		if (maCostcenterL.size()==0)
			return;
		CostCenter maCostcenter = maCostcenterL.get(0);
		info.addResult("inpmaCostcenterId", maCostcenter.getId());

	}

}
