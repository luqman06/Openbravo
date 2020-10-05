package org.wirabumi.mobileinfra.service.PrintService;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.enterprise.DocumentTemplate;
import org.openbravo.service.web.WebService;
import org.wirabumi.meikarta.report.PurchaseOrderPrintService;
import org.wirabumi.mobileinfra.PendingNotification;
import org.wirabumi.printservice.print.PrintService;

public class PrintDocumentService implements WebService {

	@Override
	public void doGet(String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * cek tipe dokumen
		 * dapatkan concrete class
		 * buat object concrete class
		 * castting to PrintService
		 * get printservice pdf
		 * return pdf stream
		 */
		
		VariablesSecureApp vars = new VariablesSecureApp(request);
		OBContext.setOBContext(request);
		String pendingnotifID = request.getParameter("pendingnotifID");
		if (pendingnotifID==null || pendingnotifID.isEmpty())
			throw new OBException("pending notif id is null or empty");
		PendingNotification pendingnotif = OBDal.getInstance().get(PendingNotification.class, pendingnotifID);
		if (pendingnotif==null)
			throw new OBException("pending notif "+pendingnotifID+" is not valid pending notification ID");
		String recordid = pendingnotif.getDocumentid();
		String printServiceClass = getPrintServiceClass(pendingnotif);
		if (printServiceClass==null || printServiceClass.isEmpty())
			throw new OBException("document template has no print service class.");
		
		Class<?> c = Class.forName(printServiceClass);
		OBContext.setAdminMode();
		PrintService printService;
		try{
			printService = (PrintService) PurchaseOrderPrintService.class.asSubclass(c)
					.getConstructor(String.class, VariablesSecureApp.class)
					.newInstance(recordid, vars);
		}
		catch (InvocationTargetException e) {
			e.getCause().printStackTrace();
			throw new OBException(e.getCause().getMessage());
		}
		finally {
			OBContext.restorePreviousMode();
		}
		
		String pdffile = printService.getReportOutputPDFFile();
		File file = new File(pdffile);
		FileInputStream fin = new FileInputStream(file);
		String reportoutputfilename = printService.getReportOutputFileName();
		response.setHeader("Content-disposition", "inline; filename="+reportoutputfilename);
		response.setContentType("application/pdf");
		ServletOutputStream out = response.getOutputStream();
		
		byte[] outputByte = new byte[4096];
		//copy binary contect to output stream
		while(fin.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}
		fin.close();
		out.flush();
		out.close();
		
		OBContext.setAdminMode();
		OBDal.getInstance().commitAndClose();
		OBContext.restorePreviousMode();

	}

	private String getPrintServiceClass(PendingNotification pendingnotif) {
		OBContext.setAdminMode();
		try{
			OBCriteria<DocumentTemplate> dtCrit = OBDal.getInstance().createCriteria(DocumentTemplate.class);
			dtCrit.add(Restrictions.eq(DocumentTemplate.PROPERTY_DOCUMENTTYPE, pendingnotif.getDocumentType()));
			dtCrit.addOrderBy(DocumentTemplate.PROPERTY_ISDEFAULT, false);
			dtCrit.setMaxResults(1);
			List<DocumentTemplate> dtList = dtCrit.list();
			if (dtList.size()==0)
				throw new OBException("document type has no print template definition");
			
			return dtList.get(0).getPrntPrintserviceclass();
		}
		finally {
			OBContext.restorePreviousMode();
		}
		
	}

	@Override
	public void doPost(String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPut(String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

	}

}
