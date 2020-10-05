package org.infinite.idolmart.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.plm.ApprovedVendor;
import org.openbravo.model.common.plm.Product;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.gen.oez.SalesPerformance;

import com.google.common.collect.HashBasedTable;

public class GenerateSPD extends DalBaseProcess {
	
	private static final Logger log4j = Logger.getLogger(GenerateSPD.class);
	
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		//get period
		Period period = getPeriod(bundle);
		
		HashMap<Product, Long> leadtimeDC = getLeadTimeDC();
		HashMap<Product, BigDecimal> ssDC = getSafetyStockDC();
		HashBasedTable<Product, Warehouse, Long> leadtimeToko = getLeadTimeToko();
		HashBasedTable<Product, Warehouse, BigDecimal> ssToko = getSafetyStockToko();
		
		//get distribution center
		Warehouse dc = getDistributionCenter();
		if (dc==null)
			throw new OBException("Distribution center not found. Please set one of warehouse as distribution center.");
		
		//delete existing SPD
		log4j.debug("deleting existing SPD");
		deleteExistingSPD();
		log4j.debug("existing SPD deleted");
		
		BigDecimal avgleadtimetoko = getAverageLeadTimeToko();
		log4j.debug("average lead time toko "+avgleadtimetoko);
		
		HashMap<Product, BigDecimal> tsmDCMap = new HashMap<>();
		HashMap<Product, BigDecimal> spdDCMap = new HashMap<>();
		
		//get total sales
		String sql = "select a.m_warehouse_id, c.m_product_id, sum(c.qtyordered) as totalsales\n" + 
				" from c_externalpos a\n" + 
				" inner join c_order b on a.m_warehouse_id=b.m_warehouse_id and b.issotrx='Y' and b.processed='Y'\n" + 
				" inner join c_orderline c on c.c_order_id=b.c_order_id\n" + 
				" where a.ad_client_id=?\n" + 
				" and b.dateordered>=?\n" + 
				" and b.dateordered<=?\n" + 
				" group by a.m_warehouse_id, c.m_product_id";
		Connection conn = OBDal.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
		ps.setDate(2, period.getFromSql());
		ps.setDate(3, period.getToSql());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			//untuk setiap toko
			String warehouseID = rs.getString("m_warehouse_id");
			Warehouse warehouse = OBDal.getInstance().get(Warehouse.class, warehouseID);
			log4j.debug("processing warehouse "+warehouse.getName());
			String productID = rs.getString("m_product_id");
			Product product = OBDal.getInstance().get(Product.class, productID);
			log4j.debug("processing product "+product.getName());
			BigDecimal totalsales = rs.getBigDecimal("totalsales");
			log4j.debug("total sales of "+product.getName()+" "+totalsales);
			BigDecimal averagesales = totalsales.divide(new BigDecimal(period.getPeriod()), RoundingMode.HALF_DOWN);
			log4j.debug("average sales of "+product.getName()+" "+averagesales);
			SalesPerformance salesPerformance = OBProvider.getInstance().get(SalesPerformance.class);
			salesPerformance.setWarehouse(warehouse);
			salesPerformance.setProduct(product);
			salesPerformance.setValidFromDate(period.getFrom());
			salesPerformance.setValidToDate(period.getTo());
			salesPerformance.setAverageDailySalesQTY(averagesales);
			
			BigDecimal spddc = averagesales;
			if (spdDCMap.containsKey(product)) {
				spddc = spdDCMap.get(product);
				spddc = spddc.add(averagesales);
			}
			spdDCMap.put(product, spddc);
			log4j.debug("SPD DC of "+product.getName()+" "+spddc);
			
			//TSM toko = SPD toko x (LT toko  +  SS toko)
			BigDecimal leadtimetoko = BigDecimal.ZERO;
			if (leadtimeToko.contains(product, warehouse))
				leadtimetoko = new BigDecimal(leadtimeToko.get(product, warehouse));
			log4j.debug("lead time of "+product.getName()+" "+leadtimetoko);
			BigDecimal sstoko = BigDecimal.ZERO;
			if (ssToko.contains(product, warehouse))
					sstoko = ssToko.get(product, warehouse);
			log4j.debug("SS toko of "+product.getName()+" "+sstoko);
			BigDecimal faktorkali = leadtimetoko.add(sstoko);
			BigDecimal tsm = averagesales.multiply(faktorkali);
			log4j.debug("TSM of "+product.getName()+" "+tsm);
			salesPerformance.setMaximumStock(tsm);
			
			//TSM DC = SPD DC x (LT toko  + LT DC + SS DC)
			BigDecimal leadtimedc = BigDecimal.ZERO;
			if (leadtimeDC.containsKey(product))
				leadtimedc = new BigDecimal(leadtimeDC.get(product));
			log4j.debug("lead time DC of "+product.getName()+" "+leadtimedc);
			BigDecimal ssdc = BigDecimal.ZERO;
			if (ssDC.containsKey(product))
				ssdc = ssDC.get(product);
			log4j.debug("SS DC of "+product.getName()+" "+ssdc);
			BigDecimal tsmdc =  spddc.multiply(avgleadtimetoko.add(leadtimedc).add(ssdc));
			tsmDCMap.put(product, tsmdc);
			log4j.debug("TSM DC of "+product.getName()+" "+tsmdc);

			OBDal.getInstance().save(salesPerformance);
		}
		
		//add record for TSM DC
		for (Product product : spdDCMap.keySet()) {
			BigDecimal spd = spdDCMap.get(product);
			SalesPerformance salesPerformance = OBProvider.getInstance().get(SalesPerformance.class);
			salesPerformance.setWarehouse(dc);
			salesPerformance.setProduct(product);
			salesPerformance.setValidFromDate(period.getFrom());
			salesPerformance.setValidToDate(period.getTo());
			salesPerformance.setAverageDailySalesQTY(spd);
			
			if (tsmDCMap.containsKey(product)) {
				BigDecimal tsm = tsmDCMap.get(product);
				salesPerformance.setMaximumStock(tsm);
			}
			
			OBDal.getInstance().save(salesPerformance);
		}
		
		OBDal.getInstance().commitAndClose();
		
		OBError msg = new OBError();
		msg.setTitle("Success");
		msg.setType("Success");
		msg.setMessage("process executed successfully.");
		bundle.setResult(msg);
		
	}

	private BigDecimal getAverageLeadTimeToko() throws SQLException {
		String sql = "select coalesce(avg(a.delaymin),0) as avgleadtimetoko \n" + 
				"from m_product_org a\n" + 
				"inner join m_locator b on b.m_locator_id=a.m_locator_id\n" + 
				"inner join m_warehouse c on c.m_warehouse_id=b.m_warehouse_id\n" + 
				"inner join c_externalpos d on d.m_warehouse_id=c.m_warehouse_id --warehousenya selalu toko\n" + 
				"where a.ad_client_id=?";
		
		Connection conn = OBDal.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			BigDecimal avgleadtimetoko = rs.getBigDecimal("avgleadtimetoko");
			return avgleadtimetoko;
		}
		
		return BigDecimal.ZERO;
		
	}

	private void deleteExistingSPD() throws SQLException {
		String sql = "delete from oez_salespreformance where ad_client_id=?";
		Connection conn = OBDal.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
		ps.executeUpdate();
		conn.commit();
		
	}

	private HashMap<Product, BigDecimal> getSafetyStockDC() {
		HashMap<Product, BigDecimal> output = new HashMap<>();
		OBCriteria<ApprovedVendor> approvedVendorCriteria = OBDal.getInstance().createCriteria(ApprovedVendor.class);
		for (ApprovedVendor approvedVendor : approvedVendorCriteria.list()) {
			output.put(approvedVendor.getProduct(), approvedVendor.getOezSafetystockindays());
		}
		
		return output;
	}

	private HashBasedTable<Product, Warehouse, BigDecimal> getSafetyStockToko() throws SQLException {
		HashBasedTable<Product, Warehouse, BigDecimal> output = HashBasedTable.create();
		String sql = "select a.m_warehouse_id, b.m_product_id, avg(b.em_oez_safetystockindays) as sstoko\n" + 
				" from c_externalpos a\n" + 
				" inner join m_warehouse c on c.m_warehouse_id=a.m_warehouse_id\n" + 
				" inner join m_locator d on d.m_warehouse_id=a.m_warehouse_id\n" + 
				" inner join m_product_org b on b.m_locator_id=d.m_locator_id\n" + 
				" where a.ad_client_id=?\n" + 
				" group by a.m_warehouse_id, b.m_product_id";
		
		Connection conn = OBDal.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String warehouseID = rs.getString("m_warehouse_id");
			Warehouse warehouse = OBDal.getInstance().get(Warehouse.class, warehouseID);
			String productID = rs.getString("m_product_id");
			Product product = OBDal.getInstance().get(Product.class, productID);
			BigDecimal sstoko = rs.getBigDecimal("sstoko");
			output.put(product, warehouse, sstoko);
		}
		
		return output;
	}

	private Period getPeriod(ProcessBundle bundle) throws ParseException {
		//get params
		String strvalidfrom = (String) bundle.getParams().get("validfrom");
		String strvalidto = (String) bundle.getParams().get("validto");
		SimpleDateFormat df = new SimpleDateFormat(bundle.getContext().getJavaDateFormat());

		Date validfrom = df.parse(strvalidfrom);
		Date validto = df.parse(strvalidto);

		if (validfrom==null || validto==null) {
			//run from background
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			validto = cal.getTime();
			validfrom = cal.getTime();
		}

		java.sql.Date validfromsql = new java.sql.Date(validfrom.getTime());
		java.sql.Date validtosql = new java.sql.Date(validto.getTime());

		//get period
		long timediff = validto.getTime()-validfrom.getTime();
		long periodl = TimeUnit.DAYS.convert(timediff, TimeUnit.MILLISECONDS) + 1;
		Period period = new Period(validfrom, validto, validfromsql, validtosql, periodl);
		return period;
	}

	private Warehouse getDistributionCenter() {
		OBCriteria<Warehouse> whCriteria = OBDal.getInstance().createCriteria(Warehouse.class);
		whCriteria.add(Restrictions.eq(Warehouse.PROPERTY_OEZIDDISTRIBUTIONCENTER, true));
		whCriteria.setFetchSize(1);
		List<Warehouse> whList = whCriteria.list();
		if (whList.size()>0)
			return whList.get(0);
		return null;
	}

	private HashBasedTable<Product, Warehouse, Long> getLeadTimeToko() throws SQLException {
		HashBasedTable<Product, Warehouse, Long> output = HashBasedTable.create();
		String sql = "select a.m_warehouse_id, b.m_product_id, avg(b.delaymin) as leadtimetoko\n" + 
				" from c_externalpos a\n" + 
				" inner join m_warehouse c on c.m_warehouse_id=a.m_warehouse_id\n" + 
				" inner join m_locator d on d.m_warehouse_id=a.m_warehouse_id\n" + 
				" inner join m_product_org b on b.m_locator_id=d.m_locator_id\n" + 
				" where a.ad_client_id=?\n" + 
				" group by a.m_warehouse_id, b.m_product_id";
		
		Connection conn = OBDal.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, OBContext.getOBContext().getCurrentClient().getId());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String warehouseID = rs.getString("m_warehouse_id");
			Warehouse warehouse = OBDal.getInstance().get(Warehouse.class, warehouseID);
			String productID = rs.getString("m_product_id");
			Product product = OBDal.getInstance().get(Product.class, productID);
			Long leadtimetoko = rs.getLong("leadtimetoko");
			output.put(product, warehouse, leadtimetoko);
		}
		
		return output;
	}

	private HashMap<Product, Long> getLeadTimeDC() {
		HashMap<Product, Long> output = new HashMap<>();
		OBCriteria<ApprovedVendor> approvedVendorCriteria = OBDal.getInstance().createCriteria(ApprovedVendor.class);
		for (ApprovedVendor approvedVendor : approvedVendorCriteria.list()) {
			output.put(approvedVendor.getProduct(), approvedVendor.getPurchasingLeadTime());
		}
		
		return output;
	}
	
	//private nested class (aka struc) of period
	private class Period{
		private final Date from;
		private final Date to;
		private final java.sql.Date fromSql;
		private final java.sql.Date toSql;
		private long period;
		public Date getFrom() {
			return from;
		}
		public Date getTo() {
			return to;
		}
		public java.sql.Date getFromSql() {
			return fromSql;
		}
		public java.sql.Date getToSql() {
			return toSql;
		}
		public long getPeriod() {
			return period;
		}
		public Period(Date from, Date to, java.sql.Date fromSql, java.sql.Date toSql, long period) {
			super();
			this.from = from;
			this.to = to;
			this.fromSql = fromSql;
			this.toSql = toSql;
			this.period = period;
		}
		
	}

}
