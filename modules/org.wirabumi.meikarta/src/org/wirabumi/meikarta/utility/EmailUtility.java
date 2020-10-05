package org.wirabumi.meikarta.utility;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.poc.EmailManager;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.EmailServerConfiguration;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.utils.FormatUtilities;

public class EmailUtility {
	public static void SendEmail(String recipientAddress, String replaytoAddress, String subject, String message){
		/*
		 * utility untuk send email notifikasi
		 * recipient address ambil dari parameter
		 * from address ambil dari email sender di config
		 * sender address ambil dari email sender di config
		 * 
		 */
		
		if (recipientAddress==null || recipientAddress.isEmpty() || recipientAddress.equals(""))
			return;
		
		String host = null;
	    boolean auth = true;
	    String username = null;
	    String password = null;
	    String connSecurity = null;
	    int port = 25;
		
		final EmailServerConfiguration mailConfig = getEmailConfig();
		
		host = mailConfig.getSmtpServer();

		if (!mailConfig.isSMTPAuthentification()) {
			auth = false;
		}
		username = mailConfig.getSmtpServerAccount();
		try {
			password = FormatUtilities.encryptDecrypt(mailConfig.getSmtpServerPassword(), false);
		} catch (ServletException e) {
			throw new OBException(e.getMessage());
		}
		connSecurity = mailConfig.getSmtpConnectionSecurity();
		String senderaddress = mailConfig.getSmtpServerSenderAddress();
		port = mailConfig.getSmtpPort().intValue();
		final String contentType = "text/plain; charset=utf-8";
		
		try {
			EmailManager.sendEmail(host, auth, username, password, connSecurity, port, senderaddress,
					recipientAddress, null, null, replaytoAddress, subject, message, contentType,
					null, new Date(), null);
		} catch (Exception exception) {
			final String exceptionClass = exception.getClass().toString().replace("class ", "");
			String exceptionString = "Problems while sending the email" + exception;
			exceptionString = exceptionString.replace(exceptionClass, "");
			throw new OBException(exceptionString);
		}

	}

	private static EmailServerConfiguration getEmailConfig() {
		try {
			OBContext.setAdminMode();
			Client client = OBContext.getOBContext().getCurrentClient();
			OBCriteria<EmailServerConfiguration> emailconfigCrit = OBDal.getInstance().createCriteria(EmailServerConfiguration.class);
			emailconfigCrit.add(Restrictions.eq(EmailServerConfiguration.PROPERTY_CLIENT, client));
			emailconfigCrit.setFetchSize(1);
			List<EmailServerConfiguration> emailconfigList = emailconfigCrit.list();
			if (emailconfigList.size()==0)
				throw new OBException("no email configuration defined.");
			return emailconfigList.get(0);
		} finally {
			OBContext.restorePreviousMode();
		}
	}
	
	public static void sendEmailCompletedShipmentInOut(ShipmentInOut shipmentInOut){
		User clientContact = shipmentInOut.getUserContact();
		if (clientContact==null || clientContact.getEmail()==null) //no contact or contact does not have any email address, then do nothing
			return;
		User salesrep = shipmentInOut.getSalesRepresentative();
		String replaytoAddress = null;
		if (salesrep!=null)
			replaytoAddress=salesrep.getEmail();
		
		String subject = "barang/jasa dari "+shipmentInOut.getBusinessPartner().getName()+" telah di terima.";
		StringBuilder sb = new StringBuilder();
		sb.append("Yth. ").append(shipmentInOut.getBusinessPartner().getName()).append(System.lineSeparator()).append(System.lineSeparator());
		sb.append("Telah diterima barang/jasa sebagai berikut:").append(System.lineSeparator());
		int i=1;
		for (ShipmentInOutLine line : shipmentInOut.getMaterialMgmtShipmentInOutLineList()){
			sb.append("[").append(i).append("] ").append(line.getMovementQuantity()).append(" ").append(line.getUOM().getName());
			sb.append(" ").append(line.getProduct().getName()).append(System.lineSeparator());
			i++;
		}
		
		sb.append("Terima kasih.");
		
		//send email to supplier
		SendEmail(clientContact.getEmail(), replaytoAddress, subject, sb.toString());
		
		//send email to PO creator
		Order po = shipmentInOut.getMaterialMgmtShipmentInOutLineList().get(0).getSalesOrderLine().getSalesOrder();
		SendEmail(po.getCreatedBy().getEmail(), replaytoAddress, subject, sb.toString());
	}
	
	public static void sendEmailReactivateOrder(Order order){
		String recipientAddress = order.getCreatedBy().getEmail();
		if (recipientAddress==null || recipientAddress.isEmpty()) //creator has no email.
			return;
		String subject = null;
		if (order.isSalesTransaction())
			subject = "pesanan penjualan dari ";
		else
			subject = "pesanan pembelian kepada ";
		subject += order.getBusinessPartner().getName()+" telah di batalkan.";
		StringBuilder sb = new StringBuilder();
		if (order.isSalesTransaction())
			sb.append("pesanan penjualan dari ");
		else
			sb.append("pesanan pembelian kepada ");
		
		sb.append(order.getBusinessPartner().getName()).append(" dengan nomor ").append(order.getDocumentNo()).append(" telah dibatalkan.")
			.append(System.lineSeparator()).append(System.lineSeparator());
		int i=1;
		for (OrderLine line : order.getOrderLineList()){
			sb.append("[").append(i).append("] ").append(line.getOrderedQuantity()).append(" ").append(line.getUOM().getName());
			sb.append(" ").append(line.getProduct().getName()).append(System.lineSeparator());
			i++;
		}
		
		sb.append("Mohon perhatian dan terima kasih.");
		
		SendEmail(recipientAddress, null, subject, sb.toString());
	}
	
	public static void sendEmailReactivedRequsition(Requisition requisition){
		String recipientAddress = requisition.getUserContact().getEmail();
		if (recipientAddress==null || recipientAddress.isEmpty()) //creator has no email.
			return;
		StringBuilder sb = new StringBuilder();
		sb.append("requisition dari ").append(requisition.getUserContact().getName())
		.append(" dengan nomor ").append(requisition.getDocumentNo()).append(" telah dibatalkan");
		String subject = sb.toString();
		
		sb.append(".").append(System.lineSeparator()).append(System.lineSeparator());
			
		int i=1;
		for (RequisitionLine line : requisition.getProcurementRequisitionLineList()){
			sb.append("[").append(i).append("] ").append(line.getQuantity()).append(" ").append(line.getUOM().getName());
			sb.append(" ").append(line.getProduct().getName()).append(System.lineSeparator());
			i++;
		}
		
		sb.append(System.lineSeparator()).append("Mohon perhatian dan terima kasih.");
		
		SendEmail(recipientAddress, null, subject, sb.toString());
	}
	
	public static void sendEmailNextWeekDueInvoice(Invoice invoice){
		String recipientAddress = invoice.getCreatedBy().getEmail();
		if (recipientAddress==null || recipientAddress.isEmpty()) //creator has no email.
			return;
		
		String subject = "purchase invoice "+invoice.getDocumentNo()+" akan jatuh tempo minggu depan";
				
		StringBuilder sb = new StringBuilder();
		sb.append("purchase invoice kepada ").append(invoice.getBusinessPartner().getName()).append(" dengan nomor ").append(invoice.getDocumentNo()).append(" akan jatuh tempo minggu depan");
		
		sb.append(".").append(System.lineSeparator()).append(System.lineSeparator());
			
		int i=1;
		for (InvoiceLine line : invoice.getInvoiceLineList()){
			sb.append("[").append(i).append("] ").append(line.getInvoicedQuantity()).append(" ").append(line.getUOM().getName());
			sb.append(" ").append(line.getProduct().getName()).append(System.lineSeparator());
			i++;
		}
		
		sb.append(System.lineSeparator()).append("Mohon perhatian dan terima kasih.");
		
		SendEmail(recipientAddress, null, subject, sb.toString());
	}
	
	public static void sendEscalationEmail(String documentType, String documentIdentifier, String recipientAddress){
		if (recipientAddress==null || recipientAddress.isEmpty()) //creator has no email.
			return;
		
		String subject = documentType+" "+documentIdentifier+" need your approval";
				
		StringBuilder sb = new StringBuilder();
		sb.append(documentType).append(" ").append(documentIdentifier).append(" sudah seminggu belum mendapatkan persetujuan dari anda");
		
		sb.append(System.lineSeparator()).append("Mohon perhatian dan terima kasih.");
		
		SendEmail(recipientAddress, null, subject, sb.toString());
	}
}
