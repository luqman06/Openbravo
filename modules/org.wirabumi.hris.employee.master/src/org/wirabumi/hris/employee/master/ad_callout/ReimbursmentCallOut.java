package org.wirabumi.hris.employee.master.ad_callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;

public class ReimbursmentCallOut extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		final BusinessPartner bp=OBDal.getInstance().get(BusinessPartner.class, 
				info.getStringParameter("inpcBpartnerId", null));
		
		if(!(bp.getSearchKey()==null)){
			info.addResult("inpvalue", bp.getSearchKey());
		}else{
			info.addResult("inpvalue", "");
		}
		
		if(!(bp.getHRISLevel()==null)){
			info.addResult("inplevel",bp.getHRISLevel());
		}else{
			info.addResult("inplevel","");
		}
		
		if(!(bp.getHrisEchelon()==null)){
			info.addResult("inpechelon", bp.getHrisEchelon());
		}else{
			info.addResult("inpechelon", "");
		}
		
		if(!(bp.getHRISPosition()==null)){
			info.addResult("inpposition", bp.getHRISPosition());
		}else{
			info.addResult("inpposition", "");
		}
		
		if(!(bp.getHrisJobtitle()==null)){
			info.addResult("inphrisJobtitleId", bp.getHrisJobtitle().getId());
		}else{
			info.addResult("inphrisJobtitleId", "");
		}
		
		if(!(bp.getHrisCostcenter()==null)){
			info.addResult("inpmaCostcenterId", bp.getHrisCostcenter().getId());
		}else{
			info.addResult("inpmaCostcenterId", "");
		}
	}
}
