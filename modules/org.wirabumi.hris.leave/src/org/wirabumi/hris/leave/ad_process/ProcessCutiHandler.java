package org.wirabumi.hris.leave.ad_process;

import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.wirabumi.hris.leave.lv_c_bp_leave;
import org.wirabumi.hris.leave.lv_mass_leave;
import org.wirabumi.hris.leave.lv_mass_leave_l;
import org.wirabumi.hris.leave.lv_set_leave;

public class ProcessCutiHandler extends BaseActionHandler {

	@Override
	protected JSONObject execute(Map<String, Object> parameters, String content) {
		// TODO Auto-generated method stub
		JSONObject hasil = new JSONObject();
		try {
			JSONObject data = new JSONObject(content);
			
			if(data.getString("command").equals("setData")){
				JSONArray recID = new JSONArray(data.getString("idMassLeave"));
				for(int i=0;i<recID.length();i++){
					lv_mass_leave massLeave = OBDal.getInstance().get(lv_mass_leave.class, recID.get(i));
					
					lv_set_leave employeeLeave = massLeave.getEmployeeLeave();
					
					OBCriteria<lv_c_bp_leave> cBpLeave = OBDal.getInstance().createCriteria(lv_c_bp_leave.class);
					cBpLeave.add(Restrictions.eq(lv_c_bp_leave.PROPERTY_LEAVECATALOGUE, employeeLeave));
					
					for(lv_c_bp_leave bp : cBpLeave.list()){
						lv_mass_leave_l massLeaveLine = OBProvider.getInstance().get(lv_mass_leave_l.class);
						massLeaveLine.setEmployee(bp.getBusinessPartner());
						massLeaveLine.setMassLeave(massLeave);
						
						OBDal.getInstance().save(massLeaveLine);
					}
				}
				OBDal.getInstance().commitAndClose();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			OBDal.getInstance().rollbackAndClose();
		}
		return hasil;
	}
}
