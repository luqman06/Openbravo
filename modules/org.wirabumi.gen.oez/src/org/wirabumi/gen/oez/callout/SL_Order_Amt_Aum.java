/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html 
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License. 
 * The Original Code is Openbravo ERP. 
 * The Initial Developer of the Original Code is Openbravo SLU 
 * All portions are Copyright (C) 2001-2017 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.wirabumi.gen.oez.callout;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.openbravo.base.filter.IsIDFilter;
//import org.openbravo.base.weld.WeldUtils;
//import org.openbravo.common.hooks.OrderLineQtyChangedHookManager;
//import org.openbravo.common.hooks.OrderLineQtyChangedHookObject;
//import org.openbravo.dal.service.OBDal;
//import org.openbravo.erpCommon.businessUtility.PriceAdjustment;
//import org.openbravo.erpCommon.utility.Utility;
//import org.openbravo.model.common.order.Order;
//import org.openbravo.model.common.plm.Product;
//import org.openbravo.utils.FormatUtilities;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout.CalloutInfo;

public class SL_Order_Amt_Aum extends SimpleCallout {

  @Override
  protected void execute(CalloutInfo info) throws ServletException {

    // parse input parameters here; the names derive from the column
    // names of the table prepended by inp and stripped of all
    // underscore characters; letters following the underscore character
    // are capitalized; this way a database column named
    // M_PRODUCT_CATEGORY_ID that is shown on a tab will become
    // inpmProductCategoryId html field
    
    // Parameters
    BigDecimal qtyOrdered = info.getBigDecimalParameter("inpqtyordered");
    BigDecimal qtyOrderedAum = info.getBigDecimalParameter("inpaumqty");
    BigDecimal priceActualAum = info.getBigDecimalParameter("inpemOezAumPriceactual"); //em_oez_aum_priceactual

    if (qtyOrderedAum.compareTo(BigDecimal.ZERO) > 0) {
        
        BigDecimal lineNetAmtAum = priceActualAum.multiply(qtyOrderedAum).setScale(3);
        BigDecimal priceActual = lineNetAmtAum.divide(qtyOrdered, 3);
//        String description = "Order= " + qtyOrdered.toString() + " |Operative Qty= " +  qtyOrderedAum.toString() + " |Operative Price= " + priceActualAum.toString()
//                + " |Operative Qty x Operative Price= " + lineNetAmtAum.toString();

        info.addResult("inppriceactual", priceActual);
//        info.addResult("inpdescription", description);
    }

  }
}
