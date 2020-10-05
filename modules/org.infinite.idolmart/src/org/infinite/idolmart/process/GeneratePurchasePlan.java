package org.infinite.idolmart.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.mrp.PurchasingRun;
import org.openbravo.model.mrp.PurchasingRunLine;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class GeneratePurchasePlan extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		//run from background
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date datesql = new java.sql.Date(cal.getTimeInMillis());
		Date date = cal.getTime();
		
		//get TSM DC = jumlahan TSM untuk semua toko
		HashMap<Product, BigDecimal> tsmDC = new HashMap<>();
		String sql = "select a.m_product_id, avg(maximumstock) as totaltsm\n" + 
				" from oez_salespreformance a\n" + 
				" inner join m_warehouse b on b.m_warehouse_id=a.m_warehouse_id\n" + 
				" where a.ad_client_id=?\n" + 
				" and a.validfrom<=?\n" + 
				" and a.validto>=?\n" + 
				" and b.em_oez_iddistributioncenter='Y'\n" + 
				" group by a.m_product_id";
		Connection conn = OBDal.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
		ps.setDate(2, datesql);
		ps.setDate(3, datesql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String productID = rs.getString("m_product_id");
			Product product = OBDal.getInstance().get(Product.class, productID);
			BigDecimal totaltsm = rs.getBigDecimal("totaltsm");
			tsmDC.put(product, totaltsm);
		}
		
		//get current stock
		HashMap<Product, BigDecimal> currentStockDC = new HashMap<>();
		sql = "select a.m_product_id, coalesce(sum(b.qtyonhand),0) as currentstock\n" + 
				" from m_product_org a\n" + 
				" inner join m_locator c on c.m_locator_id=a.m_locator_id\n" + 
				" inner join m_warehouse d on d.m_warehouse_id=c.m_warehouse_id\n" + 
				" left join m_storage_detail b on b.m_product_id=a.m_product_id\n" + 
				" 	and b.m_locator_id=a.m_locator_id\n" + 
				" where a.ad_client_id=?\n" + 
				" and d.em_oez_iddistributioncenter='Y'\n" + 
				" group by a.m_product_id";
		conn = OBDal.getInstance().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
		rs = ps.executeQuery();
		while (rs.next()) {
			String productID = rs.getString("m_product_id");
			Product product = OBDal.getInstance().get(Product.class, productID);
			BigDecimal currentstock = rs.getBigDecimal("currentstock");
			currentStockDC.put(product, currentstock);
		}
		
		//for each ROP
		PurchasingRun mrpHeader = null;
		sql = "select a.m_product_id, coalesce(a.stockmin,0) as safetystock, coalesce(a.qtymin,0) as reorderpoint\n" + 
				" from m_product_org a\n" + 
				" inner join m_locator b on b.m_locator_id=a.m_locator_id\n" + 
				" inner join m_warehouse c on c.m_warehouse_id=b.m_warehouse_id\n" + 
				" where a.ad_client_id=?\n" + 
				" and c.em_oez_iddistributioncenter='Y'";
		conn = OBDal.getInstance().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
		rs = ps.executeQuery();
		while (rs.next()) {
			String productID = rs.getString("m_product_id");
			Product product = OBDal.getInstance().get(Product.class, productID);
			
			//cek punya TSM
			if (!tsmDC.containsKey(product))
				continue;
			if (!currentStockDC.containsKey(product))
				continue;
			
			BigDecimal reorderpoint = rs.getBigDecimal("reorderpoint");
			
			BigDecimal currentstock = currentStockDC.get(product);
			if (currentstock.compareTo(reorderpoint)>0)
				continue;
			
			BigDecimal tsm = tsmDC.get(product);
			BigDecimal requiredqty = tsm.subtract(currentstock);
			
			BigDecimal safetystock = rs.getBigDecimal("safetystock");
			if (currentstock.compareTo(safetystock)<0)
				requiredqty=safetystock.subtract(currentstock).add(requiredqty); //pemenuhan safetystock
			
			if (requiredqty.compareTo(BigDecimal.ZERO)<0)
				continue;
			
			//create MRP header
			if (mrpHeader==null) {
				mrpHeader=OBProvider.getInstance().get(PurchasingRun.class);
				mrpHeader.setDocumentDate(date);
				mrpHeader.setName("generated purchasing plan");
				Long zero = 0L;
				mrpHeader.setSafetyLeadTime(zero);
				mrpHeader.setTimeHorizon(zero);
				OBDal.getInstance().save(mrpHeader);
			}
				
			//create MRP lines
			PurchasingRunLine mrpLine = OBProvider.getInstance().get(PurchasingRunLine.class);
			mrpLine.setPurchasingPlan(mrpHeader);
			mrpLine.setPlannedDate(date);
			mrpLine.setPlannedOrderDate(date);
			mrpLine.setProduct(product);
			mrpLine.setQuantity(requiredqty);
			mrpLine.setRequiredQuantity(requiredqty);
			mrpLine.setTransactionType("PP");
			OBDal.getInstance().save(mrpLine);
			
		}
		
		OBDal.getInstance().commitAndClose();
		
	}

}
