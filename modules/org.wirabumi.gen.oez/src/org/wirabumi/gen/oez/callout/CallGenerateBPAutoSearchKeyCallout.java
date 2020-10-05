package org.wirabumi.gen.oez.callout;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.utility.Utility;

public class CallGenerateBPAutoSearchKeyCallout extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) { 
		try { 
			//getParameter 
			String bpSearchKeyRule = Utility.getPreference(info.vars, "bpSearchKeyRule", null);
			boolean strValid = bpSearchKeyRule !=null && !bpSearchKeyRule.isEmpty();

			if(!strValid) {
				return;
			}
			Class cls = Class.forName(bpSearchKeyRule);
			Object clsInstance = (Object) cls.newInstance();
			GenerateBPSearchKey concrete = null;
			concrete = (GenerateBPSearchKey) clsInstance;
			String searchKey = concrete.getBPSearchKey(info);
			info.addResult("inpvalue", searchKey);
			
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
