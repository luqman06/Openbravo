package org.wirabumi.gen.oez.callout;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout.CalloutInfo;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.system.ClientInformation;
import org.openbravo.model.ad.utility.Tree;
import org.openbravo.model.ad.utility.TreeNode;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductCategory;

public class IdolMartGenerateProductSearchKey implements GenerateProductSearchKey{

	public String getProductSearchKey(CalloutInfo info) { 
		//get key from sub category 
		String strProductCategoryId = info.getStringParameter("inpmProductCategoryId", null);

		// get preference 
		String myPreference = Utility.getPreference(info.vars, "IdolMartPreference", null);

		ClientInformation ClientInfo = OBContext.getOBContext().getCurrentClient().getClientInformationList().get(0);
		Tree adTree = ClientInfo.getPrimaryTreeProductCategory();
		String adTreeId = adTree.getId();

		//get product category ID
		ProductCategory pcSubId = OBDal.getInstance().get(ProductCategory.class, strProductCategoryId);
		String subKey = pcSubId.getSearchKey();
		String[] subKeyParts = subKey.split("-");
		String SK = subKeyParts[0].trim();

		//get key from main category 
		OBCriteria<TreeNode> treeNodeCriteria = OBDal.getInstance().createCriteria(TreeNode.class);
		treeNodeCriteria.add(Restrictions.eq(TreeNode.PROPERTY_TREE, adTree));
		treeNodeCriteria.add(Restrictions.eq(TreeNode.PROPERTY_NODE, strProductCategoryId));

		TreeNode treeNode = treeNodeCriteria.list().get(0);
		String pcMainId = treeNode.getReportSet();
		if (pcMainId.equalsIgnoreCase("0")) {
			pcMainId = subKey;
		}
		ProductCategory pcMain = OBDal.getInstance().get(ProductCategory.class, pcMainId);
		String mainKey = pcMain.getSearchKey();
		String[] mainKeyParts = mainKey.split("-");
		String MK = mainKeyParts[0].trim();    

		String MaxSquenceSql = " select max(em_oez_productsequence) as maxSquence" + 
				" from m_product " + 
				" where m_product_category_id=?";
		java.sql.Connection connMaxSquence = OBDal.getInstance().getConnection();
		String searchKey = null;
		try {
			PreparedStatement psMaxSquence = connMaxSquence.prepareStatement(MaxSquenceSql);
			psMaxSquence.setString(1, strProductCategoryId);
			ResultSet rsMaxSquence = psMaxSquence.executeQuery();
			int maxSquenceProduct = 0;
			while (rsMaxSquence.next()) {
				maxSquenceProduct = rsMaxSquence.getInt("maxSquence");				
			}
			boolean maxSquenceIsNull = maxSquenceProduct == 0;

			if (maxSquenceIsNull) { 
				//put to field search key window product
				String strJumlahProduct = generateSkuSquenceIsNull(pcMainId, adTreeId, strProductCategoryId, pcMain, info);
				searchKey = myPreference+MK+SK+strJumlahProduct;
			}
			else {
				//add from last squence
				int addMaxSquence = maxSquenceProduct + 1;
		
				//save squence to product
				Long addMaxSquenceLong = Long.valueOf(addMaxSquence);
				info.addResult("inpemOezProductsequence", addMaxSquenceLong);
				
				DecimalFormat myFormatter = new DecimalFormat("00000000");
				String productSequenceString = myFormatter.format(addMaxSquenceLong);
				searchKey = myPreference+MK+SK+productSequenceString;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return searchKey;
	}


	public String generateSkuSquenceIsNull (String pcMainId, String adTreeId, String strProductCategoryId, ProductCategory pcMain, CalloutInfo info) {
		//get sequence number 
		String sql = "select count(*) as jumlahproduct" +
				" from ad_treenode a" +
				" inner join m_product b on b.m_product_category_id=a.node_id" +
				" where a.parent_id=? " +
				" and a.ad_tree_id=? "  + 
				" and b.m_product_category_id=? ";
		java.sql.Connection conn = OBDal.getInstance().getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pcMainId);
			ps.setString(2, adTreeId);
			ps.setString(3, strProductCategoryId);
			ResultSet rs = ps.executeQuery();
			int jumlahProduct = 0;
			while (rs.next()) {
				jumlahProduct = rs.getInt("jumlahProduct");
			}
			jumlahProduct++;

			DecimalFormat myFormatter = new DecimalFormat("00000000");
			String strJumlahProduct = myFormatter.format(jumlahProduct);
			
			//set value product sequence on tabel 
			Long JumlahProductLong = Long.valueOf(jumlahProduct);
			info.addResult("inpemOezProductsequence", JumlahProductLong);
			return strJumlahProduct;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new OBException(e);
		}

	}



}