package org.wirabumi.gen.oez.callout;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout.CalloutInfo;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.Category;

public class IcsBpSearchKey implements GenerateBPSearchKey{

	@Override
	public String getBPSearchKey(CalloutInfo info)  {
		// TODO Auto-generated method stub

		//get key from sub Category inpmProductCategoryId
		String strClientId = info.getStringParameter("inpadClientId", null);
		String bpCategoryID = info.getStringParameter("inpcBpGroupId", null);
		
		
		if (!SupplierIsTrue(info, bpCategoryID)) {
			return null;
		}
		// get preference 
		String bpPrefix = Utility.getPreference(info.vars, "BusinessPartnerPrefix", null);
		String bpSuffix = Utility.getPreference(info.vars, "BusinessPartnerSuffix", null);

		//getSquence 
		String maxSquence = " select max(em_oez_bpartnersquence) as jumlahSquence" + 
				" from c_bpartner " + 
				" where c_bp_group_id=?";
		java.sql.Connection MaxSquenceconn = OBDal.getInstance().getConnection();
		String searchKey = null;
		try {
			PreparedStatement psMaxSquence = MaxSquenceconn.prepareStatement(maxSquence);
			psMaxSquence.setString(1, bpCategoryID);
			ResultSet rsMaxSquence = psMaxSquence.executeQuery();
			int getMaxSquence = 0;
			while (rsMaxSquence.next())
			{
				getMaxSquence = rsMaxSquence.getInt("jumlahSquence");
			}
			boolean MaxSquenceNotNull =  getMaxSquence > 0;

			if (!MaxSquenceNotNull)
			{
				String strJumlahBPartner = generateSkuSquenceIsNull(strClientId, info);
				searchKey = bpPrefix+ strJumlahBPartner + bpSuffix;
			}
			else 
			{
				//add searchKey bp
				int strJumlahmaxsquence = getMaxSquence + 1;
				searchKey = bpPrefix+ strJumlahmaxsquence + bpSuffix;

				//add strJumlahmaxsquence to em_oezBusinessPartner field
				Long JumlahBpLong = Long.valueOf(strJumlahmaxsquence);
				info.addResult("inpemOezBpartnersquence", JumlahBpLong);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return searchKey;
	}
	
	public boolean SupplierIsTrue (CalloutInfo info, String bpCategoryID ) {
		//validation bahwa data merupakan supplier 
		
		Category bpCategory = OBDal.getInstance().get(Category.class, bpCategoryID);
		String bpCategoryName = bpCategory.getName().toLowerCase(); 

		String supplierValidation = Utility.getPreference(info.vars, "supplierValidation", null);
		return bpCategoryName.contains(supplierValidation);
	}
	
	public String generateSkuSquenceIsNull(String strClientId, CalloutInfo info) { 
		//get increment number 
		String sql = " select count(*) as JumlahBP " + 
				" from c_bpartner as a " + 
				" inner join c_bp_group as b on a.c_bp_group_id = b.c_bp_group_id " + 
				" where a.ad_client_id=? " + 
				" and lower(b.\"name\") like '%supplier%' ";
		java.sql.Connection conn = OBDal.getInstance().getConnection();
		String strJumlahBPartner = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, strClientId);
			ResultSet rs = ps.executeQuery();
			int JumlahBP = 0;
			while (rs.next()) {
				JumlahBP = rs.getInt("JumlahBP");
			}
			JumlahBP++;

			DecimalFormat myFormatter = new DecimalFormat("00000000");
			strJumlahBPartner = myFormatter.format(JumlahBP);

			//add jumlahBP to em_oezBusinessPartner field
			Long JumlahBpLong = Long.valueOf(JumlahBP);
			info.addResult("inpemOezBpartnersquence", JumlahBpLong);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return strJumlahBPartner;
	}

}
