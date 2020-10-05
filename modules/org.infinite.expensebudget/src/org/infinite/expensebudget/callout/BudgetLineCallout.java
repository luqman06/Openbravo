package org.infinite.expensebudget.callout;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;

public class BudgetLineCallout extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		String lastchanged = info.getLastFieldChanged();
		if (lastchanged.equals("inpmProductId")) {
			//otomatiskan product group supaya match
			String productID = info.getStringParameter("inpmProductId");
			Product product = OBDal.getInstance().get(Product.class, productID);
			info.addResult("inpmProductCategoryId", product.getProductCategory().getId());
			
		} else if (lastchanged.equals("inpmProductCategoryId")) {
			//jika produknya tidak match, maka hilangkah isi field produk
			String productID = info.getStringParameter("inpmProductId");
			if (productID==null)
				return;
			String productgroupID = info.getStringParameter("inpmProductCategoryId");
            if (productgroupID==null)
				return;
			Product product = OBDal.getInstance().get(Product.class, productID);
			if (!productgroupID.equals(product.getProductCategory().getId()))
				info.addResult("inpmProductId", null);
		}

	}

}
