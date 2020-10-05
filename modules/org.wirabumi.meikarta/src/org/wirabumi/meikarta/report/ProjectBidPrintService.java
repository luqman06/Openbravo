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
import org.openbravo.model.ad.datamodel.Column;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.utility.AuditTrail;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectLine;
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

public final class ProjectBidPrintService implements PrintService {
	
	//private properties
	private final Project project;
	private final VariablesSecureApp vars;
	private final String outputFileName;
	private final FieldProvider[] dataSourceByArray;
	private final List<FieldProvider> dataSourceByList;
	private final HashMap<String, Object> parameters;
	private final DecimalFormat formater = new DecimalFormat("###,###.##");
	private final String reportTemplatePath;
	private final String reportOutputPath;
	
	//public constructor
	public ProjectBidPrintService(String documentID, VariablesSecureApp vars){
		this.vars=vars;
		if (documentID==null || documentID.isEmpty())
			throw new OBException("document ID is null or empty.");
		this.project = OBDal.getInstance().get(Project.class, documentID);
		if (project==null)
			throw new OBException("document ID "+documentID+" is not valid project bid ID.");
		
		String strBaseDesign = OBPropertiesProvider.getInstance().getOpenbravoProperties().getProperty("basedesign.path");
		reportTemplatePath = Replace.replace(getReportTemplatePath(), "@basedesign@", strBaseDesign);
		String attachPath = OBPropertiesProvider.getInstance().getOpenbravoProperties().getProperty("attach.path");
		reportOutputPath = attachPath+File.separator+getReportOutputFileName();

		outputFileName = buildOutputFileName(documentID);
		dataSourceByList = buildDataSource(project);
		dataSourceByArray= dataSourceByList.toArray(new PurchaseRequestBean[dataSourceByList.size()]);
		parameters = buildReportParam(project);

	}


	private HashMap<String, Object> buildReportParam(Project project) {
		//get PR total lines
		BigDecimal total_lines = BigDecimal.ZERO;
		for (ProjectLine prLine : project.getProjectLineList()){
			total_lines = total_lines.add(prLine.getPlannedQuantity().multiply(prLine.getPlannedPrice()));
		}
		HashMap<String, Object> parameters = new HashMap<>();
		parameters.put("organizationid", project.getOrganization().getId());
		String strEstimateTotal = formater.format(total_lines);
		parameters.put("est_total", strEstimateTotal);
		
		//audit trail
		AuditInfo auditinfo = getAuditInfo(project.getId());
		parameters.put("released_by", auditinfo.getReleasedby());
		parameters.put("released_date", auditinfo.getReleaseddate());
		parameters.put("reviewed_by", auditinfo.getReviewedby());
		parameters.put("reviewed_date", auditinfo.getRevieweddate());
		parameters.put("completed_by", auditinfo.getApprovedby());
		parameters.put("completed_date", auditinfo.getApproveddate());
		
		return parameters;
	}


	private List<FieldProvider> buildDataSource(Project pr) {
		String headerdescription = pr.getDescription();
		String strDateFormat = vars.getJavaDateFormat();
		SimpleDateFormat df = new SimpleDateFormat(strDateFormat);
		String strDateRequested = "N/A";
		if (pr.getStartingDate()!=null)
			strDateRequested=df.format(pr.getStartingDate());
		String strDateRequired ="N/A";
		if (pr.getEndingDate()!=null)
			strDateRequired=df.format(pr.getEndingDate());
		String documentNo = pr.getName();
		String organization = pr.getOrganization().getName();
		String requester = pr.getCreatedBy().getName();
		String costcenter = "";
		if (pr.getPbidCostcenter()!=null)
			costcenter = pr.getPbidCostcenter().getName();
		
		List<ProjectLine> pbidLineLIst = pr.getProjectLineList();
		if (pbidLineLIst.size()==0)
			throw new OBException("Purchase request with Document No: "+pr.getName()+" has no lines.");
		
		List<FieldProvider> prBeanList = new ArrayList<>();
		BigDecimal total_lines=BigDecimal.ZERO;
		
		Integer i=0;
		for (ProjectLine projectLine : pbidLineLIst){
			i++;
			Product product = projectLine.getProduct();
			String uom = product.getUOM().getName();
			String lineDescription = product.getName();
			if (projectLine.getDescription()!=null)
				lineDescription=lineDescription+" - "+projectLine.getDescription();
			
			BigDecimal quantity = projectLine.getPlannedQuantity().setScale(2, RoundingMode.HALF_DOWN);
			String strQuantity = formater.format(quantity);
			BigDecimal plannedPrice = projectLine.getPlannedPrice().setScale(2, RoundingMode.HALF_DOWN);
			String estimatevalue="";
			String total_estimatevalue="";
			if (plannedPrice!=null){
				estimatevalue=formater.format(plannedPrice);
				BigDecimal total = plannedPrice.multiply(quantity);
				total_estimatevalue=formater.format(total);
				total_lines = total_lines.add(total);
			}
			
			PurchaseRequestBean prb = new PurchaseRequestBean.Builder(documentNo, organization, requester, 
					strDateRequested, lineDescription, uom, strQuantity)
					.estimateValue(estimatevalue)
					.lineNo(i.toString())
					.totalEstimateValue(total_estimatevalue)
					.jobarea(costcenter)
					.daterequired(strDateRequired)
					.headerdescription(headerdescription)
					.build();
			
			prBeanList.add(prb);
		}
		
		return prBeanList;
	}


	@Override
	public String getReportTemplatePath() {
		String reportPath = Utility.getPreference(vars, "ProjectBid_ReportTemplate", "9EB8F7B68B2C4FBBBC217A1871977866");
		if (reportPath==null || reportPath.isEmpty())
			reportPath = "@basedesign@/org/wirabumi/meikarta/report/purchase_request.jrxml";
		return reportPath;
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
	
	private String buildOutputFileName(String documentID) {
		Long seqno=new Long(0);
		Date date = new Date();
		OBContext.setAdminMode();
		OBCriteria<PrintLog> printLogCrit = OBDal.getInstance().createCriteria(PrintLog.class);
		printLogCrit.add(Restrictions.eq(PrintLog.PROPERTY_RECORDID, documentID));
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

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
		String strdate = df.format(date);

		return "PurchaseRequest-"+strdate+"-"+seqno.toString()+".pdf";

	}
	
	private AuditInfo getAuditInfo(String documentid) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		OBContext.setAdminMode();
		
		Table table = OBDal.getInstance().get(Table.class, "203");
		Column column = OBDal.getInstance().get(Column.class, "37003CA477AD4F95A437F593B27215E9");
		final String release = "OEZ_RELEASE";
		final String review = "OEZ_REVIEW";
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
		
		//released
		String releasedby="(______________)", releaseddate=" date:";
		for (AuditTrail au : auditTrailList){
			if (au.getNEWChar().equals(release)){
				releasedby=au.getUserContact().getName();
				releaseddate=df.format(au.getEventTime());
				break;
			}
		}
		
		//review
		String reviewedby="(______________)", revieweddate=" date:";
		for (AuditTrail au : auditTrailList){
			if (au.getNEWChar().equals(review)){
				reviewedby=au.getUserContact().getName();
				revieweddate=df.format(au.getEventTime());
				break;
			}
		}
		
		//complete
		String completeby="(______________)", completedate=" date:";
		for (AuditTrail au : auditTrailList){
			if (au.getNEWChar().equals(complete)){
				completeby=au.getUserContact().getName();
				completedate=df.format(au.getEventTime());
				break;
			}
		}
		
		OBContext.restorePreviousMode();
		return new AuditInfo(releasedby, releaseddate, reviewedby, revieweddate, completeby, completedate);
	}

	
	//audit info inner class
	private class AuditInfo {
		private final String releasedby;
		private final String releaseddate;
		private final String reviewedby;
		private final String revieweddate;
		private final String approvedby;
		private final String approveddate;
		
		public AuditInfo(String releasedby, String releaseddate, String reviewedby, String revieweddate, String approvedby,
				String approveddate) {
			super();
			this.releasedby = releasedby;
			this.releaseddate = releaseddate;
			this.reviewedby = reviewedby;
			this.revieweddate = revieweddate;
			this.approvedby = approvedby;
			this.approveddate = approveddate;
		}

		public String getReleasedby() {
			return releasedby;
		}

		public String getReleaseddate() {
			return releaseddate;
		}

		public String getReviewedby() {
			return reviewedby;
		}

		public String getRevieweddate() {
			return revieweddate;
		}

		public String getApprovedby() {
			return approvedby;
		}

		public String getApproveddate() {
			return approveddate;
		}
		
	}

	@Override
	public List<FieldProvider> getDataSourceByCollection() {
		return this.dataSourceByList;
	}

}
