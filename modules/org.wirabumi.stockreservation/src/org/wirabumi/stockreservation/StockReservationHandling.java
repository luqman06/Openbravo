package org.wirabumi.stockreservation;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.plm.AttributeSetInstance;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.onhandquantity.StorageDetail;
import org.openbravo.model.materialmgmt.transaction.InternalMovement;
import org.openbravo.model.materialmgmt.transaction.InternalMovementLine;
import org.openbravo.model.materialmgmt.transaction.InventoryCount;
import org.openbravo.model.materialmgmt.transaction.InventoryCountLine;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;


public class StockReservationHandling {

	public static final int ADD_RESERVED_STOCK_IN = 1;
	public static final int RELEASED_STOCK_IN = 2;
	public static final int ADD_RESERVED_STOCK_OUT = 3;
	public static final int RELEASED_STOCK_OUT = 4;
	public static final int VOID_RESERVED_STOCK_OUT = 5;
	public static final int VOID_RESERVED_STOCK_IN = 6;
	public static final int VOID_RELEASED_STOCK_OUT = 7;
	public static final int VOID_RELEASED_STOCK_IN = 8;
	public static final int VOID_COMPLETE = 9;

	enum EnumSR {
		addReserved, completeReserved, voidReserved, voidComplete
	}

	public static void prosesValidation(Table table, Entity entity,
			String windowId, String docStatus, String movementType,
			List<String> listId) {
		int type = -1;
		
		String column = null;
		String adTableId = table.getId();
		// m_inout
		// shipment
		String lineName = null;
		if (movementType == null || movementType.length() <= 1) {
			throw new OBException("MovementType '" + movementType
					+ "' is not registered.");
		}
		if (adTableId.equalsIgnoreCase("319")) {
			// m_inout
			lineName = ShipmentInOut.PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST;
			column = ShipmentInOutLine.PROPERTY_MOVEMENTQUANTITY;
		} else if (adTableId.equalsIgnoreCase("323")) {
			// m_movement
			lineName = InternalMovement.PROPERTY_MATERIALMGMTINTERNALMOVEMENTLINELIST;
			column = InternalMovementLine.PROPERTY_MOVEMENTQUANTITY;
		} else if (adTableId.equalsIgnoreCase("321")) {
			// m_inventory
			lineName = InventoryCount.PROPERTY_MATERIALMGMTINVENTORYCOUNTLINELIST;
			column = InventoryCountLine.PROPERTY_ORDERQUANTITY;
		}

		for (Object o : listId) {
			String recordId = (String) o;
			BaseOBObject header = OBDal.getInstance().get(entity.getName(),
					recordId);
			List<BaseOBObject> lineList = (List<BaseOBObject>) header
					.get(lineName);
			for (BaseOBObject line : lineList) {
				Locator locator = (Locator) line
						.get(ShipmentInOutLine.PROPERTY_STORAGEBIN);
				Product product = (Product) line
						.get(ShipmentInOutLine.PROPERTY_PRODUCT);
				AttributeSetInstance asi = (AttributeSetInstance) line
						.get(ShipmentInOutLine.PROPERTY_ATTRIBUTESETVALUE);
				BigDecimal qty = (BigDecimal) line.get(column);
				String lineId = (String) line.getId();
				SR_StockReservation sr = calculate(product, locator, asi,
						table, lineId, movementType, docStatus, qty, type);
			}
		}
	}

	private static SR_StockReservation calculate(Product product,
			Locator storageBin, AttributeSetInstance asi, Table table,
			String record_id, String movementType, String docstatus,
			BigDecimal qty, int type) {
		boolean inout = false;
		BigDecimal currentIn = BigDecimal.ZERO;
		BigDecimal currentOut = BigDecimal.ZERO;
		BigDecimal qtyOnhand = BigDecimal.ZERO;
		BigDecimal sisa = BigDecimal.ZERO;
		SR_StockReservation sr = null;
		EnumSR typeSr = EnumSR.addReserved;
		// validasi apakah in / out
		String tanda = movementType.substring(movementType.length() - 1);
		if (tanda.equalsIgnoreCase("+")) {
			inout = true;
		} else if (tanda.equalsIgnoreCase("-")) {
			inout = false;
		} else {
			throw new OBException("MovementType '" + movementType
					+ "' is not registered.");
		}
		if (qty.compareTo(BigDecimal.ZERO) < 0) {
			inout = !inout;
			qty = qty.negate();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("where recordID='").append(record_id).append("'");
		sb.append(" and table.id='").append(table.getId()).append("'");
		OBQuery<SR_StockReservation> query = OBDal.getInstance().createQuery(
				SR_StockReservation.class, sb.toString());
		String statusRecord = null;

		if (query.list().size() > 0) {
			sr = query.list().get(0);
			statusRecord = sr.getDocumentStatus();
		}
		if (docstatus.compareToIgnoreCase("sr_rv") == 0 && statusRecord == null) {
			typeSr = typeSr.addReserved;
		} else if (docstatus.compareToIgnoreCase("vo") == 0
				&& statusRecord.compareToIgnoreCase("co") == 0) {
			typeSr = typeSr.voidComplete;
		} else if (docstatus.compareToIgnoreCase("co") == 0) {
			typeSr = typeSr.completeReserved;
		} else if (docstatus.compareToIgnoreCase("vo") == 0
				&& statusRecord.compareToIgnoreCase("sr_rv") == 0) {
			typeSr = typeSr.voidReserved;
		} else {
			return null;
		}
		if (asi == null) {
			AttributeSetInstance a = OBDal.getInstance().get(
					AttributeSetInstance.class, "0");
			asi = a;
		}
		OBCriteria<SR_StockReservation> criteria = OBDal.getInstance()
				.createCriteria(SR_StockReservation.class);
		criteria.add(Restrictions.eq(
				SR_StockReservation.PROPERTY_ATTRIBUTESETVALUE, asi));
		criteria.add(Restrictions.eq(SR_StockReservation.PROPERTY_PRODUCT,
				product));
		criteria.add(Restrictions.eq(SR_StockReservation.PROPERTY_STORAGEBIN,
				storageBin));
		if (criteria.list().size() > 0) {
			SR_StockReservation tm = criteria.list().get(0);
			currentIn = tm.getReservedqtyIn();
			currentOut = tm.getReservedqtyOut();
		}
		qtyOnhand = getOnhandQty(product, storageBin, asi);
		if (sr == null) {
			sr = OBProvider.getInstance().get(SR_StockReservation.class);
			sr.setTable(table);
			sr.setDocumentStatus(docstatus);
			sr.setProduct(product);
			sr.setAttributeSetValue(asi);
			sr.setStorageBin(storageBin);
			sr.setRecordID(record_id);
			sr.setMovementType(movementType.toString());
			sr.setReservedqtyIn(BigDecimal.ZERO);
			sr.setReservedqtyOut(BigDecimal.ZERO);
			OBDal.getInstance().save(sr);
			sisa = qtyOnhand.add(currentIn).subtract(currentOut).subtract(qty);
		} else {
			sisa = qtyOnhand.add(currentIn).subtract(currentOut);
		}
		// inout = true -> IN
		// inout = false -> OUT
		switch (typeSr) {
		case addReserved:
			if (inout) {
				currentIn = currentIn.add(qty);
			} else {
				if (sisa.compareTo(BigDecimal.ZERO) < 0) {
					throw new OBException("Insufficient stock.");
				}
				currentOut = currentOut.add(qty);
			}
			break;
		case voidComplete:
			break;
		case completeReserved:
		case voidReserved:
			if (inout) {
				if (currentIn.compareTo(BigDecimal.ZERO) > 0) {
					currentIn = currentIn.subtract(qty);
				}
			} else {
				if (sisa.compareTo(BigDecimal.ZERO) < 0) {
					throw new OBException("Insufficient stock.");
				}
				if (currentOut.compareTo(BigDecimal.ZERO) > 0) {
					currentOut = currentOut.subtract(qty);
				}

			}
			break;
		}
		// update documentstatus
		sr.setReservedqtyIn(currentIn);
		sr.setReservedqtyOut(currentOut);
		sr.setDocumentStatus(docstatus);
		OBDal.getInstance().save(sr);
		// update qty in/out sesuai yang sekarang
		for (SR_StockReservation stockReservation : criteria.list()) {
			stockReservation.setReservedqtyIn(currentIn);
			stockReservation.setReservedqtyOut(currentOut);
			OBDal.getInstance().save(stockReservation);
		}
		return sr;

	}

	public static BigDecimal getOnhandQty(Product product, Locator storageBin,
			AttributeSetInstance asi) {
		BigDecimal onhandqty = null;
		OBCriteria<StorageDetail> sd = OBDal.getInstance().createCriteria(
				StorageDetail.class);
		sd.add(Restrictions.eq(StorageDetail.PROPERTY_PRODUCT, product));
		sd.add(Restrictions.eq(StorageDetail.PROPERTY_STORAGEBIN, storageBin));
		if (asi == null) {
			AttributeSetInstance a = OBDal.getInstance().get(
					AttributeSetInstance.class, "0");
			sd.add(Restrictions.eq(StorageDetail.PROPERTY_ATTRIBUTESETVALUE, a));
		} else {
			sd.add(Restrictions.eq(StorageDetail.PROPERTY_ATTRIBUTESETVALUE,
					asi));
		}
		if (sd.list().size() > 0) {
			StorageDetail s = sd.list().get(0);
			onhandqty = s.getQuantityOnHand();
		}
		if (onhandqty == null) {
			throw new OBException("Product not found in storage detail.");
		}
		return onhandqty;
	}

}
