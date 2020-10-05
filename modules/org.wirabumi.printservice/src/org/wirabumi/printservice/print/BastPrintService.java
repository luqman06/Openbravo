package org.wirabumi.printservice.print;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.utility.TreeUtility;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.utility.AuditTrail;
import org.openbravo.model.common.enterprise.DocumentTemplate;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.utils.Replace;
import org.wirabumi.printservice.PrintLog;
import org.wirabumi.printservice.print.PrintService;

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
public final class BastPrintService implements PrintService {
	
	//private properties
	private final ShipmentInOut goodreceipt;
	private final VariablesSecureApp vars;
	private final String outputFileName;
	private final FieldProvider[] dataSourceByArray;
	private final List<FieldProvider> dataSourceByList;
	private final HashMap<String, Object> parameters;
	private final String reportTemplatePath;
	private final String reportOutputPath;
	
	//public constructor
	public BastPrintService(String documentID, VariablesSecureApp vars){
		this.vars=vars;
		
		//gr object creation
		if (documentID==null || documentID.isEmpty())
			throw new OBException("document ID is null or empty.");
		goodreceipt = OBDal.getInstance().get(ShipmentInOut.class, documentID );
		if (goodreceipt==null)
			throw new OBException("document ID "+documentID+" is not valid purchase order ID.");
		
		//report template path
		//prioritas 1: document type
		DocumentType doctype = goodreceipt.getDocumentType();
		String strReportName = null;
		List<DocumentTemplate> templateList = doctype.getDocumentTemplateList();
		if (templateList.size()!=0){
			DocumentTemplate template = templateList.get(0);
			String location = template.getTemplateLocation();
			String filename = template.getTemplateFilename();
			if (location!=null && filename!=null)
				strReportName=location+File.separator+filename;
		} 
		
		if (strReportName==null)
			strReportName = Utility.getPreference(this.vars, "Bast_ReportTemplate", "9EB8F7B68B2C4FBBBC217A1871977866"); //prioritas 2
		
		if (strReportName==null || strReportName.isEmpty() || strReportName.equals(""))
			strReportName = "@basedesign@/org/wirabumi/meikarta/report/bast_report.jrxml"; //prioritas 3
		
		String strBaseDesign = vars.getSessionValue("basedesignpath");
		strReportName = Replace.replace(strReportName, "@basedesign@", strBaseDesign);
		
		this.reportTemplatePath = strReportName;
		
		//report output path
		String attachPath = OBPropertiesProvider.getInstance().getOpenbravoProperties().getProperty("attach.path");
		this.outputFileName = buildOutputFileName(documentID);
		reportOutputPath = attachPath+File.separator+outputFileName;
		
		
		this.dataSourceByList = buildDataSourceFromBean(goodreceipt);
		this.dataSourceByArray = this.dataSourceByList.toArray(new BastBean[this.dataSourceByList.size()]);
		parameters = buildReportParam(goodreceipt);
		
	}
	

	private HashMap<String, Object> buildReportParam(ShipmentInOut gr) {
		DecimalFormat formater = new DecimalFormat("###,###.##");
		HashMap<String, Object> parameters = new HashMap<>();
		if (gr.isProcessed())
			parameters.put("processed", "Y");
		else
			parameters.put("processed", "N");
	
		
		//audit trail
		AuditInfo auditinfo=getAuditInfo(gr.getId());
		parameters.put("completed_by", auditinfo.getApprovedby());
		parameters.put("role_completed_by", auditinfo.getApproverTitle());
		
		//get PO line
		BigDecimal totaltransaksi = BigDecimal.ZERO;
		for (ShipmentInOutLine line : gr.getMaterialMgmtShipmentInOutLineList()){
			OrderLine poline = line.getSalesOrderLine();
			
			if (poline==null)
				continue;
			
			BigDecimal unitprice = poline.getUnitPrice();
			BigDecimal quantity = line.getMovementQuantity();
			BigDecimal receivedamount = quantity.multiply(unitprice);
			totaltransaksi=totaltransaksi.add(receivedamount);
		}
		
		parameters.put("nilai_transaksi", formater.format(totaltransaksi));
		
		return parameters;
	}
	
	

	private List<FieldProvider> buildDataSourceFromBean(ShipmentInOut gr) {
		//Information internal
		DecimalFormat formater = new DecimalFormat("###,###.##");
		String strDateFormat = vars.getJavaDateFormat();
		SimpleDateFormat df = new SimpleDateFormat(strDateFormat);
		String strOrderDate = df.format(gr.getOrderDate());
		String documentNo = gr.getDocumentNo();
		String grdate = df.format(gr.getMovementDate());
		String description ="";
		if(gr.getDescription()!=null)
			description=gr.getDescription();
		
		String orgid = gr.getOrganization().getId();
		TreeUtility treeUtility = new TreeUtility();
		Set<String> orgs = treeUtility.getNaturalTree(orgid, "OO");
		Organization legalEntityOrg = null;
		for (String org : orgs){
			Organization organization = OBDal.getInstance().get(Organization.class, org);
			if (organization.getOrganizationType().isLegalEntity()){
				legalEntityOrg=organization;
				break;
			}
		}
		
		String supplier ="";
		if(gr.getBusinessPartner().getName()!=null)
			supplier = gr.getBusinessPartner().getName();
		
		List<ShipmentInOutLine> grLineList = gr.getMaterialMgmtShipmentInOutLineList();
		if(grLineList.size()==0)
			throw new OBException("Good Receipt No" +gr.getDocumentNo()+"has no lines");
				
		List<FieldProvider> grBeanList = new ArrayList<>();
		Integer i=1;
		for(ShipmentInOutLine grLine : grLineList){
			
			BigDecimal quntity = grLine.getMovementQuantity().setScale(2, RoundingMode.HALF_DOWN);
			
			String product = grLine.getProduct().getName();
			
			String uom ="";
			if(grLine.getUOM()!=null)
				uom = grLine.getUOM().getName();
					
			BastBean grBean = 
						new BastBean.Builder(strOrderDate, documentNo, supplier, 
								formater.format(quntity))
						.description(description)
						.grdate(grdate)
						.product_name(product)
						.satuan(uom)
						.lineNo(i.toString())
						.vendor_info(gr.getUserContact())
						.organization_gr(legalEntityOrg.getName())
						.build();
			
			grBeanList.add(grBean);
			i++;
			
		}
		return grBeanList;
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
		
		ShipmentInOut gr = OBDal.getInstance().get(ShipmentInOut.class, documentID);
		
		SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
		String strdate = df.format(date);
		String strseqno = formater.format(seqno);
		return "GR-"+strdate+strseqno+"-"+gr.getDocumentNo()+".pdf";
		
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
	private AuditInfo getAuditInfo(String documentid) {
		OBContext.setAdminMode();
		
		Table table = OBDal.getInstance().get(Table.class, "296"); //ad_table_id for m_inout
		Column column = OBDal.getInstance().get(Column.class, "4323"); //ad_column_id from m_inout.docstatus
		final String complete = "CO";
		
		OBCriteria<AuditTrail> auditTrailC = OBDal.getInstance().createCriteria(AuditTrail.class);
		auditTrailC.add(Restrictions.eq(AuditTrail.PROPERTY_TABLE, table));
		auditTrailC.add(Restrictions.eq(AuditTrail.PROPERTY_COLUMN, column));
		auditTrailC.add(Restrictions.eq(AuditTrail.PROPERTY_RECORDID, documentid));
		auditTrailC.add(Restrictions.isNotNull(AuditTrail.PROPERTY_NEWCHAR));
		auditTrailC.addOrderBy(AuditTrail.PROPERTY_EVENTTIME, false);
		List<AuditTrail> auditTrailList = auditTrailC.list();
		if (auditTrailList==null)
			auditTrailList=new ArrayList<>();
	
		//complete
		String completeby="(______________)";
		String approverTitle = "(______________)";
		for (AuditTrail au : auditTrailList){
			if (au.getNEWChar().equals(complete)){
				User approver = au.getUserContact();
				completeby=approver.getName();
				if (approver.getPosition()!=null)
					approverTitle=approver.getPosition();
				break;
			}
		}
		
		OBContext.restorePreviousMode();
		return new AuditInfo( completeby, approverTitle);
	}
	
	//audit info inner class
			private class AuditInfo {
			private final String approvedby;
			private final String approverTitle;
			
			public AuditInfo(String approvedby, String approverTitle) {
				super();
				this.approvedby = approvedby;
				this.approverTitle = approverTitle;
			}

			public String getApprovedby() {
				return approvedby;
			}

			public String getApproverTitle() {
				return approverTitle;
			}
			
		}

}
