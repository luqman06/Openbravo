package org.wirabumi.gen.oez.callout;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.utility.Utility;

public class CallGenerateAutoSearchKey extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) { 
		try {
			//get parameter 
			String SearchKeyRule = Utility.getPreference(info.vars, "SearchKeyRules", null);		
			boolean strValid = SearchKeyRule != null && !SearchKeyRule.isEmpty();
			
			if (strValid) { 
				Class cls = Class.forName(SearchKeyRule);
				Object clsInstance = (Object) cls.newInstance();
				GenerateProductSearchKey concrete = null;
				concrete = (GenerateProductSearchKey) clsInstance;
				String searchKey = concrete.getProductSearchKey(info);
				info.addResult("inpvalue", searchKey);
			} 	
			else { 
				return;
			}
		} catch (ClassNotFoundException e) {
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