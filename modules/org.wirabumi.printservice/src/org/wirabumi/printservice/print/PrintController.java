package org.wirabumi.printservice.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.application.report.ReportingUtils;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.erpCommon.utility.reporting.Report;
import org.openbravo.erpCommon.utility.reporting.ReportManager;
import org.openbravo.erpCommon.utility.reporting.ReportingException;
import org.openbravo.model.common.enterprise.DocumentTemplate;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.order.Order;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class PrintController extends org.openbravo.erpCommon.utility.reporting.printing.PrintController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
	ServletException{
		String basedesign = request.getSession().getServletContext().getRealPath("/")+"src-loc"+File.separator+"design";
		VariablesSecureApp vars = new VariablesSecureApp(request);
		vars.setSessionValue("basedesignpath", basedesign);

		//cara mendapatkan semua session variable untuk di debug
		HttpSession session = request.getSession();
		Enumeration<String> keys = session.getAttributeNames();
		while (keys.hasMoreElements())
		{
			String key = (String)keys.nextElement();
			System.out.println(key + ": " + vars.getSessionValue(key));
		}

		//cek if requisition
		String documentID = null;
		String strReportClass=null;
		if (vars.getSessionValue("INPTABID").equals("xxxxx")){ //fungsi ini di nonaktifkan diganti yang di bawah//requisition || header //800249
			//do get print service class for requisition
			documentID = vars.getSessionValue("PRINT_CONTROLLER.INPMREQUISITIONID");
			strReportClass = Utility.getPreference(vars, "PurchaseRequest_PrintServiceClass", "800092");
			//jika tidak ada report class, maka jalankan Print PR module core
			if (strReportClass==null){
				String strBaseDesign = getBaseDesignPath(vars.getLanguage());

				HashMap<String, Object> parameters = new HashMap<String, Object>();
				JasperReport jasperReportLines;
				try {
					jasperReportLines = ReportingUtils.compileReport(strBaseDesign
							+ "/org/openbravo/erpReports/RptM_Requisition_Lines.jrxml");
				} catch (JRException e) {
					e.printStackTrace();
					throw new ServletException(e.getMessage());
				}

				parameters.put("SR_LINES", jasperReportLines);
				parameters.put("REQUISITION_ID", documentID);
				renderJR(vars, response, null, "pdf", parameters, null, null);
			}

		} else if (vars.getSessionValue("INPTABID").equals("D9B758A7DB624AEAABDD6783353309D1")){ //requisition || proposal
			//do get print service class for requisition proposal
			documentID = vars.getSessionValue("800092|C_PROJECTPROPOSAL_ID");
			strReportClass = Utility.getPreference(vars, "Proposal_PrintServiceClass", "800092");
		} else if (vars.getSessionValue("INPTABID").equals("294")){ //purchase order || header
			//do get print service class for requisition proposal
			documentID = vars.getSessionValue("PRINT_CONTROLLER.INPCORDERID");
			documentID = documentID.replaceAll("\\(|\\)|'", "");
			System.out.println("document id: "+documentID);

			try {
				OBContext.setAdminMode();
				Order po = OBDal.getInstance().get(Order.class, documentID);	
				System.err.println("PO No: "+po.getDocumentNo());
				DocumentType doctype = po.getTransactionDocument();
				System.err.println("Document Type: "+doctype.getName());
				List<DocumentTemplate> templates = doctype.getDocumentTemplateList();
				if (templates.size()==0)
					throw new OBException("document type "+doctype.getName()+" does not have any report template.");
				DocumentTemplate reporttemplate=null;
				for (DocumentTemplate template : templates){
					reporttemplate = template;
					if (template.isDefault())
						break;
				}
				strReportClass = reporttemplate.getPrntPrintserviceclass();
			} finally {
				OBContext.restorePreviousMode();
			}

			System.err.println("print service class: "+strReportClass);
			//jika tidak ada report class, maka jalankan Print PO module core
			if (strReportClass==null)
				post(request, response, vars, 
						org.openbravo.erpCommon.utility.reporting.DocumentType.SALESORDER, 
						"PRINTORDERS", documentID);
		} else if (vars.getSessionValue("INPTABID").equals("800146")){ //process plan || version
			documentID = vars.getSessionValue("800051|MA_PROCESSPLAN_VERSION_ID");
			printStandardJR(vars, response, documentID, "ProcessPlan_PrintDesign");

		} else if (vars.getSessionValue("INPTABID").equals("C9B5394DBA8C465C9CE26A361696B06E")){ //production run || production run
			documentID = vars.getSessionValue("FF808181323E504701323E57E08D0017|M_PRODUCTION_ID");
			printStandardJR(vars, response, documentID, "ProductionRun_PrintDesign"); //800111

		} else if (vars.getSessionValue("INPTABID").equals("800111")){ //work requirement || header
			documentID = vars.getSessionValue("800052|MA_WORKREQUIREMENT_ID");
			printStandardJR(vars, response, documentID, "WorkRequirement_PrintDesign");
			//new
		} else if (vars.getSessionValue("INPTABID").equals("259")){ //good movement || header
			documentID = vars.getSessionValue("170|m_movement_id");  //m_movement_id
			printStandardJR(vars, response, documentID, "GoodMovement_PrintDesign");

		} else if (vars.getSessionValue("INPTABID").equals("800249")){ //requisition || header
			documentID = vars.getSessionValue("800092|m_requisition_id");  
			printStandardJR(vars, response, documentID, "Requisition_PrintDesign");

		} else if (vars.getSessionValue("INPTABID").equals("2DD6F1E2CAE0456AA9797A1D627BFF5E")){ //payment_proposal || table id
			documentID = vars.getSessionValue("1B7B3BB7FEAF41ED8D9727AB98779D3C|fin_payment_proposal_id");  //window id
			printStandardJR(vars, response, documentID, "PaymentProposal_PrintDesign");

		} else if (vars.getSessionValue("INPTABID").equals("255")){ //physical_inventory || header
			documentID = vars.getSessionValue("168|m_inventory_id");  //window id
			printStandardJR(vars, response, documentID, "physicalInventory_printDesign");

	    } else if (vars.getSessionValue("INPTABID").equals("1004400001")){ //manage_requisition || header
			documentID = vars.getSessionValue("1004400000|m_requisition_id");  //window id
			printStandardJR(vars, response, documentID, "M_Requisition_printDesign");

		} else
			throw new OBException("unidentified document id");

		if (strReportClass==null || strReportClass.isEmpty())
			throw new OBException("can not find print service class.");

		documentID = documentID.replaceAll("\\(|\\)|'", "");

		//print service object creation using abstract factory pattern
		OBContext.setAdminMode();
		PrintService printService;
		try{
			Class<?> c = Class.forName(strReportClass);
			printService = (PrintService) c
					.getConstructor(String.class, VariablesSecureApp.class)
					.newInstance(documentID, vars);
		}
		catch (InvocationTargetException e) {
			e.getCause().printStackTrace();
			throw new OBException(e.getCause().getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
		finally {
			OBDal.getInstance().commitAndClose();
			OBContext.restorePreviousMode();
		}

		//send saved report to output stream
		String pdffile = printService.getReportOutputPDFFile();
		File file = new File(pdffile);
		FileInputStream fin = new FileInputStream(file);
		String reportoutputfilename = printService.getReportOutputFileName();
		response.setHeader("Content-disposition", "attachment; filename="+reportoutputfilename);
		response.setContentType("application/pdf");
		ServletOutputStream out = response.getOutputStream();

		byte[] outputByte = new byte[4096];
		//copy binary contect to output stream
		while(fin.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}

		out.flush();
		response.flushBuffer();
		fin.close();
		out.close();

		//		printPageClosePopUp(response, vars);

	}

	protected void post(HttpServletRequest request, HttpServletResponse response, VariablesSecureApp vars,
			org.openbravo.erpCommon.utility.reporting.DocumentType documentType, String sessionValuePrefix, String strDocumentId)
					throws IOException, ServletException {
		String localStrDocumentId = strDocumentId;
		// Checks are maintained in this way for mulithread safety
		HashMap<String, Boolean> checks = new HashMap<String, Boolean>();
		checks.put("moreThanOneCustomer", Boolean.FALSE);
		checks.put("moreThanOnesalesRep", Boolean.FALSE);

		String documentIds[] = null;
		if (log4j.isDebugEnabled())
			log4j.debug("strDocumentId: " + localStrDocumentId);
		// normalize the string of ids to a comma separated list
		localStrDocumentId = localStrDocumentId.replaceAll("\\(|\\)|'", "");
		if (localStrDocumentId.length() == 0)
			throw new ServletException(Utility.messageBD(this, "NoDocument", vars.getLanguage()));

		documentIds = localStrDocumentId.split(",");

		if (log4j.isDebugEnabled())
			log4j.debug("Number of documents selected: " + documentIds.length);

		final ReportManager reportManager = new ReportManager(globalParameters.strFTPDirectory,
				strReplaceWithFull, globalParameters.strBaseDesignPath,
				globalParameters.strDefaultDesignPath, globalParameters.prefix, false);

		/*
		 * PRINT option will print directly to the UI for a single report. For multiple reports the
		 * documents will each be saved individually and the concatenated in the same manner as the
		 * saved reports. After concatenating the reports they will be deleted.
		 */
		Report report = null;
		JasperPrint jasperPrint = null;
		Collection<JasperPrint> jrPrintReports = new ArrayList<JasperPrint>();
		final Collection<Report> savedReports = new ArrayList<Report>();
		for (int i = 0; i < documentIds.length; i++) {
			String documentId = documentIds[i];
			report = buildReport(response, vars, documentId, reportManager, documentType,
					Report.OutputTypeEnum.PRINT);
			try {
				jasperPrint = reportManager.processReport(report, vars);
				jrPrintReports.add(jasperPrint);
			} catch (final ReportingException e) {
				advisePopUp(request, response, "Report processing failed",
						"Unable to process report selection");
				log4j.error(e.getMessage());
				e.getStackTrace();
			}
			savedReports.add(report);
		}
		printReports(response, jrPrintReports, savedReports, false);
	}

	private void printStandardJR(VariablesSecureApp vars, HttpServletResponse response, String documentID, String reportDesignPathKey) {
		String strReportDesign = Utility.getPreference(vars, reportDesignPathKey, "");
		if (strReportDesign.equals(""))
			throw new OBException("can not find report design in preference "+reportDesignPathKey);
		String strBaseDesign = getBaseDesignPath(vars.getLanguage());
		String strReportDesignPath = strBaseDesign+strReportDesign;
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("DOCUMENT_ID", documentID);
		try {
			renderJR(vars, response, strReportDesignPath, "pdf", parameters, null, null);
		} catch (ServletException e) {
			e.printStackTrace();
			throw new OBException(e.getMessage());
		}
	}

}
