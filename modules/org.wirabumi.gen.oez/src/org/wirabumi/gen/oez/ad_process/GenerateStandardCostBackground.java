package org.wirabumi.gen.oez.ad_process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.cost.Costing;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 * background process untuk membantu membuatkan record standard cost.
 * record standard cost dibuatkan untuk proses transisi pada organisasi
 * yang baru saja menggunakan openbravo.
 * 
 * pada awalnya, standard cost harus diinput satu per-satu di setiap SKU.
 * jika keberatan, maka bisa menggunakan background process ini untuk membantu proses tersebut.
 * 
 * background process ini akan mencari SKU SKU yang memiliki cost type standard cost
 * dan yang belum memiliki 1 pun record standard cost.
 * kemudian dicari pembelian pertama, untuk kemudian dibuatkan record costing
 * berdasarkan informasi tersebut.
 * 
 * pada record costing yang akan dibuat, cost didapat dari price actual (net unit price)
 * dari pembelian pertama itu. tanggal awal costing juga didapatkan dari tanggal pembelian
 * pertama itu. sedangkan tanggal akhir nya adalah 31-12-9999.
 * 
 * @author zaien
 *
 */
public class GenerateStandardCostBackground extends DalBaseProcess {
	
	private ProcessLogger logger;
	
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		/*
		 * 1. cari produk dengan costtype STA dan belum punya record costing
		 * 2. cari pembelian pertama
		 * 3. buat record costing
		 */
		
		logger = bundle.getLogger();
		
		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection = conn.getConnection();
		
		final Date endDate = new SimpleDateFormat("dd-MM-yyyy").parse("31-12-9999");
		final String clientID = OBContext.getOBContext().getCurrentClient().getId();
		final String orgID = OBContext.getOBContext().getCurrentOrganization().getId();
		final String calendarOwnerID = getCalendarOwner(connection, orgID);
		if (calendarOwnerID==null){
			String errMessage = "can not find calendar owner orgnization for org ID "+orgID;
			logger.log(errMessage);
			throw new OBException(errMessage);
		}	
		Organization orgCalendarOwner = OBDal.getInstance().get(Organization.class, calendarOwnerID);
		
		//langkah 1
		List<String> productIDList = getProductWithoutCosting(connection, clientID);
		logger.log("product without standard costing size: "+productIDList.size());
		
		int i=0;
		for (String productID : productIDList){
			//langkah 2
			OrderLineBean orderline = getPembelianPertama(connection, productID);
			
			//langkah 3
			if (orderline==null)
				continue;
			Product product = OBDal.getInstance().get(Product.class, productID);
			Costing costing = OBProvider.getInstance().get(Costing.class);
			costing.setOrganization(orgCalendarOwner);
			costing.setProduct(product);
			costing.setStartingDate(orderline.dateordered);
			costing.setEndingDate(endDate);
			Currency currency = OBDal.getInstance().get(Currency.class, orderline.c_currency_id);
			costing.setCurrency(currency);
			costing.setCostType("STA");
			costing.setQuantity(orderline.qtyordered);
			costing.setCost(orderline.priceactual);
			if (orderline.m_inoutline_id!=null){
				ShipmentInOutLine inoutline = OBDal.getInstance().get(ShipmentInOutLine.class, orderline.m_inoutline_id);
				costing.setGoodsShipmentLine(inoutline);
			}
			
			if (orderline.c_invoiceline_id!=null){
				InvoiceLine invoiceline = OBDal.getInstance().get(InvoiceLine.class, orderline.c_invoiceline_id);
				costing.setInvoiceLine(invoiceline);
			}
			
			OBDal.getInstance().save(costing);
			i++;
		}
		logger.log(i+" product costing record(s) generated");
		
	}

	private String getCalendarOwner(Connection connection, String orgID) {
		String sql = "select ad_org_getcalendarowner from ad_org_getcalendarowner(?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, orgID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String ad_org_getcalendarowner = rs.getString("ad_org_getcalendarowner");
				return ad_org_getcalendarowner;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private OrderLineBean getPembelianPertama(Connection connection, String productID) {
		OrderLineBean output = null;
		String sql = "select c.m_inoutline_id, c.c_invoiceline_id,"
				+ " b.dateordered, e.movementdate, g.dateinvoiced,"
				+ " a.priceactual as order_price, f.priceactual as invoice_price,"
				+ " a.qtyordered, f.qtyinvoiced,"
				+ " b.c_currency_id as order_currency, g.c_currency_id as invoice_currency"
				+ " from m_matchpo c"
				+ " inner join m_inoutline d on d.m_inoutline_id=c.m_inoutline_id"
				+ " inner join m_inout e on e.m_inout_id=d.m_inout_id"
				+ " left join c_orderline a on a.c_orderline_id=c.c_orderline_id"
				+ " left join c_order b on b.c_order_id=a.c_order_id"
				+ " left join c_invoiceline f on f.c_invoiceline_id=c.c_invoiceline_id"
				+ " left join c_invoice g on g.c_invoice_id=f.c_invoice_id"
				+ " where d.m_product_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, productID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String minoutline = rs.getString("m_inoutline_id");
				boolean useOrder=true;
				String invoiceline = rs.getString("c_invoiceline_id");
				if (invoiceline!=null)
					useOrder=false;
				Date dateordered = rs.getDate("dateordered");
				Date dateinvoiced = rs.getDate("dateinvoiced");
				Date movementdate = rs.getDate("movementdate");
				if (!useOrder)
					dateordered=dateinvoiced;
				if (movementdate.before(dateordered))
					dateordered=movementdate;
				
				BigDecimal priceactual = rs.getBigDecimal("order_price");
				if (!useOrder)
					priceactual = rs.getBigDecimal("invoice_price");
				
				BigDecimal qtyordered = rs.getBigDecimal("qtyordered");
				if (!useOrder)
					qtyordered = rs.getBigDecimal("qtyinvoiced");
				
				String currency = rs.getString("order_currency");
				if (!useOrder)
					currency = rs.getString("invoice_currency");
				
				output = new OrderLineBean();
				output.c_invoiceline_id=invoiceline;
				output.m_inoutline_id=minoutline;
				output.dateordered=dateordered;
				output.priceactual=priceactual;
				output.qtyordered=qtyordered;
				output.c_currency_id=currency;
				break;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	private List<String> getProductWithoutCosting(Connection connection, String clientID) {
		List<String> output = new ArrayList<>();
				
		String sql = "select m_product_id"
				+ " from m_product a"
				+ " where a.ad_client_id=?"
				+ " and costtype='STA'"
				+ " and not exists (select 1 from m_costing "
				+ "					where m_product_id=a.m_product_id"
				+ "					and costtype='STA')";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String productID = rs.getString("m_product_id");
				output.add(productID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	private class OrderLineBean {
		String c_invoiceline_id;
		String m_inoutline_id;
		Date dateordered;
		BigDecimal priceactual;
		BigDecimal qtyordered;
		String c_currency_id;
	}

}
