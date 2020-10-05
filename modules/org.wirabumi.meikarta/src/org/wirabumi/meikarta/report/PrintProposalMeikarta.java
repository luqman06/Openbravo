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
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.project.ProjectProposal;
import org.openbravo.model.project.ProjectProposalLine;
import org.openbravo.utils.Replace;
import org.wirabumi.printservice.PrintLog;
import org.wirabumi.printservice.print.PrintService;

import com.google.common.collect.HashBasedTable;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class PrintProposalMeikarta implements PrintService {

	//private properties
	private final ProjectProposal proposal;
	private final VariablesSecureApp vars;
	private final String outputFileName;
	private final FieldProvider[] dataSourceByArray;
	private final List<FieldProvider> dataSourceByList;
	private final HashMap<String, Object> parameters;
	private final DecimalFormat formater = new DecimalFormat("###,###.##");
	private final String reportTemplatePath;
	private final String reportOutputPath;
	
	//public constructor
	public PrintProposalMeikarta(String documentID, VariablesSecureApp vars){
		this.vars=vars;

		//PR object creation
		if (documentID==null || documentID.isEmpty())
			throw new OBException("document ID is null or empty.");
		proposal = OBDal.getInstance().get(ProjectProposal.class, documentID);
		if (proposal==null)
			throw new OBException("document ID "+documentID+" is not valid proposal ID.");

		//report template path
		String strBaseDesign = vars.getSessionValue("basedesignpath");
		if (strBaseDesign==null || strBaseDesign.isEmpty())
			strBaseDesign = OBPropertiesProvider.getInstance().getOpenbravoProperties().getProperty("basedesign.path");
		String strReportName = Utility.getPreference(this.vars, "Proposal_ReportTemplate", "800092");
		if (strReportName==null || strReportName.isEmpty() || strReportName.equals(""))
			strReportName = "@basedesign@/org/wirabumi/meikarta/report/cbe_report.jrxml";
		strReportName = Replace.replace(strReportName, "@basedesign@", strBaseDesign);
		this.reportTemplatePath = strReportName;

		//report output path
		String attachPath = OBPropertiesProvider.getInstance().getOpenbravoProperties().getProperty("attach.path");
		outputFileName = buildOutputFileName(documentID);
		reportOutputPath = attachPath+File.separator+outputFileName;

		dataSourceByList = buildDataSource(proposal);
		this.dataSourceByArray = this.dataSourceByList.toArray(new CBEBean[this.dataSourceByList.size()]);
		parameters = buildReportParam(proposal);
	}

	private HashMap<String, Object> buildReportParam(ProjectProposal proposal) {
		HashMap<String, Object> output = new HashMap<>();
		output.put("client", proposal.getClient().getName());
		output.put("organizationid", proposal.getOrganization().getId());
		String dateformat = vars.getJavaDateFormat();
		if (dateformat==null || dateformat.isEmpty())
			dateformat="dd-MM-yyyy";
		SimpleDateFormat df = new SimpleDateFormat(dateformat);
		String daterequested = df.format(proposal.getPbidRequisition().getCreationDate());
		output.put("daterequested", daterequested);
		List<ProjectProposalLine> proposalLineList = proposal.getProjectProposalLineList();
		if (proposalLineList.size()==0)
			throw new OBException("proposal does not have any lines.");
		String daterequired = df.format(proposal.getPbidRequisition().getProcurementRequisitionLineList().get(0).getNeedByDate());
		output.put("daterequired", daterequired);
		
		return output;
	}

	private List<FieldProvider> buildDataSource(ProjectProposal proposal) {
		
		Requisition requisition = proposal.getPbidRequisition();
		String costcenter="";
		if (requisition.getPbidCostcenter()!=null)
			costcenter = requisition.getPbidCostcenter().getName();

		//load proposal table
		HashBasedTable<String, String, BigDecimal> proposalTable = HashBasedTable.create(); //vendor, product --> unit price
		//		HashBasedTable<String, String, String> proposalTable = HashBasedTable.create();
		OBCriteria<ProjectProposal> proposalC = OBDal.getInstance().createCriteria(ProjectProposal.class);
		proposalC.add(Restrictions.eq(ProjectProposal.PROPERTY_PBIDREQUISITION, requisition));
		proposalC.addOrderBy(ProjectProposal.PROPERTY_PBIDSCORE, false); //diambil prposal dengan score tertinggi
		proposalC.setFetchSize(3);//maksimal 3 proposal
		for (ProjectProposal prop : proposalC.list()){
			for (ProjectProposalLine line : prop.getProjectProposalLineList()){
				String vendor = prop.getBusinessPartner().getName();
				String product = line.getProduct().getName();

				BigDecimal unitprice = line.getPrice().setScale(2,RoundingMode.HALF_DOWN);
				proposalTable.put(vendor, product, unitprice);
			}
		}

		List<String> vendorList = new ArrayList<String>(proposalTable.rowKeySet());
		String vendor1="", vendor2="", vendor3="";
		if (vendorList.size()>=1)
			vendor1=vendorList.get(0);
		if (vendorList.size()>=2)
			vendor2=vendorList.get(1);
		if (vendorList.size()>=3)
			vendor3=vendorList.get(2);

		/*
		 * dari project bid, dapatkan list bid line
		 * loop untuk setiap bid line
		 * 	masukkan seqno, product, qty, satuan kedalam java bean
		 * ubah list of java bean into array of field provider
		 * render report
		 * 
		 */

		List<RequisitionLine> requisitionLineList = requisition.getProcurementRequisitionLineList();
		if (requisitionLineList.size()==0)
			throw new OBException("requisition does not have any lines.");
		Integer i=0;
		List<FieldProvider> cbeList = new ArrayList<FieldProvider>();
		for (RequisitionLine line : requisitionLineList){
			i++;
			String product = line.getProduct().getName();
			if (line.getDescription()!=null)
				product = line.getDescription();
			String uom = line.getProduct().getUOM().getName();
			String quantity = line.getQuantity().toString();
			System.out.println(quantity);
			String warehouse = "N/A";
			if (requisition.getPbidWarehouse()!=null)
				warehouse = requisition.getPbidWarehouse().getName();
			String organization = requisition.getOrganization().getName();
			String description = requisition.getDescription();
			String documentno = requisition.getDocumentNo();
			BigDecimal totalprice1=BigDecimal.ZERO ;
			BigDecimal totalprice2=BigDecimal.ZERO;
			BigDecimal totalprice3=BigDecimal.ZERO;
			String requester = requisition.getCreatedBy().getName();

			BigDecimal p1=BigDecimal.ZERO;
			BigDecimal p2=BigDecimal.ZERO;
			BigDecimal p3=BigDecimal.ZERO;


			String price1="", price2="", price3="";			
			if (vendorList.size()>=1){
				String productname=line.getProduct().getName();
				if (proposalTable.contains(vendor1, productname)){
					price1=proposalTable.get(vendor1, productname).toString();
					System.out.println(price1);
					p1=new BigDecimal(price1);

					totalprice1 =  new BigDecimal(proposalTable.get(vendor1, productname).toString());
					BigDecimal qty = new BigDecimal(Integer.valueOf(quantity));
					totalprice1 = totalprice1.multiply(qty);
				}

			}
			if (vendorList.size()>=2){
				String productname=line.getProduct().getName();
				if (proposalTable.contains(vendor2, productname)){
					price2=proposalTable.get(vendor2, productname).toString();
					System.out.println(price2);
					p2=new BigDecimal(price2);

					totalprice2 = new BigDecimal(proposalTable.get(vendor2, productname).toString());
					BigDecimal qty2 = new BigDecimal(Integer.valueOf(quantity));
					totalprice2=totalprice2.multiply(qty2);
				}
			}
			if (vendorList.size()>=3){
				String productname=line.getProduct().getName();
				if (proposalTable.contains(vendor3, productname)){
					price3=proposalTable.get(vendor3, productname).toString();
					System.out.println(price3);
					p3=new BigDecimal(price3);


					totalprice3 = new BigDecimal(proposalTable.get(vendor3, productname).toString());
					BigDecimal qty3 = new BigDecimal(Integer.valueOf(quantity));
					totalprice3=totalprice3.multiply(qty3);
				}

			}

			CBEBean cbeBean = new CBEBean.Builder()
					.lineNo(i.toString())
					.product(product)
					.quantity(quantity)
					.uom(uom)
					.warehouse(warehouse)
					.organization(organization)
					.description(description)
					.name(documentno)
					.vendor1(vendor1)
					.vendor2(vendor2)
					.vendor3(vendor3)
					.unitprice1(formater.format(p1))
					.unitprice2(formater.format(p2))
					.unitprice3(formater.format(p3))
					.totalprice1(formater.format(totalprice1))
					.totalprice2(formater.format(totalprice2))
					.totalprice3(formater.format(totalprice3))
					.requester(requester)
					.jobarea(costcenter)
					.build();
			cbeList.add(cbeBean);

		}
		
		return cbeList;
	}

	private String buildOutputFileName(String documentID) {
		final DecimalFormat formater = new DecimalFormat("###,#00");
		Long seqno=new Long(0);
		Date date = new Date();
		OBContext.setAdminMode();
		OBCriteria<PrintLog> printLogCrit = OBDal.getInstance().createCriteria(PrintLog.class);
		printLogCrit.addOrderBy(PrintLog.PROPERTY_PRINTSEQNO, false);
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
		
		ProjectProposal proposal = OBDal.getInstance().get(ProjectProposal.class, documentID);
		Requisition requisition = proposal.getPbidRequisition();
		
		SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
		String strdate = df.format(date);
		String strseqno = formater.format(seqno);
		return "CBE-"+strdate+strseqno+"-"+requisition.getDocumentNo()+".pdf";
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
	public List<FieldProvider> getDataSourceByCollection() {
		return this.dataSourceByList;
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
}
