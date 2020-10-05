package org.wirabumi.meikarta.process;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.meikarta.utility.EmailUtility;

public class InvoiceToPayEmailNofitification extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		/*
		 * jika ada invoice yang kurang 1 minggu jatuh tempo, maka kirim email notifikasi
		 * 1 minggu disini adalah daystilldue adalah 7.
		 * background process ini akan dijalankan 1 hari sekali, jam 23.00, untuk menjamin bahwa dokumen hanya di notifikasi 1x
		 */
		
		OBCriteria<Invoice> invoiceC = OBDal.getInstance().createCriteria(Invoice.class);
		invoiceC.add(Restrictions.eq(Invoice.PROPERTY_PROCESSED, true));
		invoiceC.add(Restrictions.eq(Invoice.PROPERTY_SALESTRANSACTION, false));
		invoiceC.add(Restrictions.eq(Invoice.PROPERTY_DAYSTILLDUE, new Long(7)));
		for (Invoice invoice : invoiceC.list()){
			EmailUtility.sendEmailNextWeekDueInvoice(invoice);
		}
		
	}

}
