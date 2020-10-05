package org.wirabumi.meikarta.report;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.utils.Replace;
import org.wirabumi.printservice.PrintLog;
import org.wirabumi.printservice.print.PrintService;
import org.wirabumi.printservice.utility.PrintServiceUtility;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

//make final class to avoid unintended inheritance
public final class PurchaseOrderPrintService implements PrintService {
	
	//private properties
	private final Order purchaseorder;
	private final VariablesSecureApp vars;
	private final String outputFileName;
	private final FieldProvider[] dataSourceByArray;
	private final List<FieldProvider> dataSourceByList;
	private final HashMap<String, Object> parameters;
	private final String reportTemplatePath;
	private final String reportOutputPath;
	
	//public constructor
	public PurchaseOrderPrintService(String documentID, VariablesSecureApp vars){
		this.vars=vars;
		
		//PO object creation
		if (documentID==null || documentID.isEmpty())
			throw new OBException("document ID is null or empty.");
		purchaseorder = OBDal.getInstance().get(Order.class, documentID);
		if (purchaseorder==null)
			throw new OBException("document ID "+documentID+" is not valid purchase order ID.");
		
		//report template path
		String strBaseDesign = vars.getSessionValue("basedesignpath");
		if (strBaseDesign==null || strBaseDesign.isEmpty())
			strBaseDesign = OBPropertiesProvider.getInstance().getOpenbravoProperties().getProperty("basedesign.path");
		String strReportName = Utility.getPreference(this.vars, "PurchaseOrder_ReportTemplate", "9EB8F7B68B2C4FBBBC217A1871977866");
		if (strReportName==null || strReportName.isEmpty() || strReportName.equals(""))
			strReportName = "@basedesign@/org/wirabumi/meikarta/report/purchase_order.jrxml";
		strReportName = Replace.replace(strReportName, "@basedesign@", strBaseDesign);
		this.reportTemplatePath = strReportName;
		
		//report output path
		String attachPath = OBPropertiesProvider.getInstance().getOpenbravoProperties().getProperty("attach.path");
		this.outputFileName = buildOutputFileName(documentID);
		reportOutputPath = attachPath+File.separator+outputFileName;
		
		this.dataSourceByList = buildDataSourceFromBean(purchaseorder);
		this.dataSourceByArray = this.dataSourceByList.toArray(new PurchaseOrderBean[this.dataSourceByList.size()]);
		parameters = buildReportParam(purchaseorder);
		
	}

	private HashMap<String, Object> buildReportParam(Order po) {
		HashMap<String, Object> parameters = new HashMap<>();
		if (po.isProcessed())
			parameters.put("processed", "Y");
		else
			parameters.put("processed", "N");
		return parameters;
	}

	private List<FieldProvider> buildDataSourceFromBean(Order po) {
		String strDateFormat = vars.getJavaDateFormat();
		SimpleDateFormat df = new SimpleDateFormat(strDateFormat);
		String strOrderDate = df.format(po.getOrderDate());
		String documentNo = po.getDocumentNo();
		String supplier = po.getBusinessPartner().getName();
		
		Location supplierLocation = po.getPartnerAddress().getLocationAddress();
		String address=PrintServiceUtility.getLocationAddressFormat(supplierLocation);
		
		
		Warehouse warehouse = po.getWarehouse();
		String warehousename = warehouse.getName();
		
		Location warehouselocation = warehouse.getLocationAddress();
		String warehouseaddress=PrintServiceUtility.getLocationAddressFormat(warehouselocation);
		String deliverynotes=po.getDeliveryNotes();
		
		DecimalFormat formater = new DecimalFormat("###,###.##");
		
		BigDecimal totalLine = po.getSummedLineAmount();
		String total_line = formater.format(totalLine);
		BigDecimal grandtotal = po.getGrandTotalAmount();
		String strGrandTotal = formater.format(grandtotal);
		BigDecimal taxamount = grandtotal.subtract(totalLine);
		String strTaxAmount = formater.format(taxamount);
		String top = po.getPaymentTerms().getName();
		String docstatus = Utility.getListValueName("All_Document Status", po.getDocumentStatus(), "en_US");
		
		List<OrderLine> poLineList = po.getOrderLineList();
		if (poLineList.size()==0)
			throw new OBException("Purchase Order with Document No: "+po.getDocumentNo()+" has no lines.");
		
		List<FieldProvider> poBeanList = new ArrayList<>();
		Integer i=1;
		for (OrderLine poLine : poLineList){
			BigDecimal quantity = poLine.getOrderedQuantity().setScale(2, RoundingMode.HALF_DOWN);
			String strQuantity = formater.format(quantity);
			
			BigDecimal hargasatuan = poLine.getUnitPrice().setScale(2, RoundingMode.HALF_DOWN);
			String strHargaSatuan = formater.format(hargasatuan);
			
			Product product = poLine.getProduct();
			String satuan = product.getUOM().getName();
			String linedescription=null;
			if (poLine.getDescription()==null)
				linedescription=product.getName();
			else
				linedescription=poLine.getDescription();
			strQuantity=strQuantity+" "+satuan;
			
			BigDecimal total = BigDecimal.ZERO;
			if (quantity!=null && hargasatuan!=null)
				total=quantity.multiply(hargasatuan);
			String strTotal = formater.format(total);
			
			PurchaseOrderBean poBean = 
					new PurchaseOrderBean.Builder(strOrderDate, documentNo, 
							supplier, address, 
							linedescription, strQuantity, strHargaSatuan, 
							strTotal)
					.warehouse(warehousename)
					.warehouseAddress(warehouseaddress)
					.grand_total(strGrandTotal)
					.sub_total(total_line)
					.tax(strTaxAmount)
					.termofpayment(top)
					.delivery_note(deliverynotes)
					.status(docstatus)
					.lineNo(i.toString())
					.build();
					
			poBeanList.add(poBean);
			i++;
		}
		return poBeanList;
	}

	private String buildOutputFileName(String documentID) {
		final DecimalFormat formater = new DecimalFormat("###,#00");
		Long seqno=new Long(0);
		Date date = new Date();
		OBContext.setAdminMode();
		OBCriteria<PrintLog> printLogCrit = OBDal.getInstance().createCriteria(PrintLog.class);
		printLogCrit.add(Restrictions.eq(PrintLog.PROPERTY_RECORDID, documentID));
		printLogCrit.addOrderBy(PrintLog.PROPERTY_PRINTSEQNO, false);
		List<PrintLog> printLogList = printLogCrit.list();
		if (printLogList.size()>0){
			PrintLog printLog = printLogList.get(0); 
			seqno=printLog.getPrintseqno();
			seqno++;
			printLog.setPrintseqno(seqno);
			printLog.setLastprinted(date);
			OBDal.getInstance().save(printLog);
		}
		else{
			//belum pernah di print, maka buat log
			PrintLog printLog = OBProvider.getInstance().get(PrintLog.class);
			printLog.setRecordID(documentID);
			printLog.setPrintseqno(seqno++);
			printLog.setLastprinted(date);
			OBDal.getInstance().save(printLog);
		}
		OBContext.restorePreviousMode();
		
		Order po = OBDal.getInstance().get(Order.class, documentID);
		
		SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
		String strdate = df.format(date);
		String strseqno = formater.format(seqno);
		return "PO-"+strdate+strseqno+"-"+po.getDocumentNo()+".pdf";
		
	}

	@Override
	public String getReportTemplatePath() {
		return this.reportTemplatePath;
	}

	@Override
	public String getReportOutputFileName() {
		return this.outputFileName;
	}

	@Override
	public HashMap<String, Object> getReportParameter() {
		return this.parameters;
	}

	@Override
	public FieldProvider[] getDataSourceByArray() {
		return this.dataSourceByArray;
	}

	@Override
	public String getReportOutputPDFFile() {
		
		JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        JasperDesign jasperDesign = null;
        
        try {
			jasperDesign = JRXmlLoader.load(reportTemplatePath);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, 
	        		new JRBeanCollectionDataSource(this.dataSourceByList));
	        JasperExportManager.exportReportToPdfFile(jasperPrint,reportOutputPath);
		} catch (JRException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
        
		return reportOutputPath;
	}
	
	@Override
	public List<FieldProvider> getDataSourceByCollection() {
		return this.dataSourceByList;
	}

}
