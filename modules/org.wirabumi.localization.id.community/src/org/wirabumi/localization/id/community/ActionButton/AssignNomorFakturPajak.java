package org.wirabumi.localization.id.community.ActionButton;

import java.util.ArrayList;
import java.util.List;

import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.localization.id.community.data.id_nomorfakturpajak_v;
import org.wirabumi.localization.id.community.data.nomorfakturpajak;

public class AssignNomorFakturPajak extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		OBQuery<nomorfakturpajak> nfpQuery = null;
		List<Object> param = new ArrayList<Object>();
		
		//return message object
		final OBError msg = new OBError();
		
		//get record_id
		final String invoiceID = (String) bundle.getParams().get("C_Invoice_ID");
		final String nomorFakturPajak = (String) bundle.getParams().get("nomorfakturpajak");
		final String strLepasNomorFakturPajak = (String) bundle.getParams().get("lepasnomorfakturpajak");
		if (strLepasNomorFakturPajak.equalsIgnoreCase("Y")){
			Invoice invoice = OBDal.getInstance().get(Invoice.class, invoiceID);
			String nomorFakturPajakLama = invoice.getIdNomorfakturpajak().getNomorfakturpajak();
			invoice.setIdNomorfakturpajak(null);
			OBDal.getInstance().save(invoice);
			msg.setType("Success");
			msg.setTitle("Lepas Nomor Faktur Pajak");
			msg.setMessage("Nomor faktur pajak "+nomorFakturPajakLama+" telah dilepas");
			bundle.setResult(msg);
			return;
		}
		if (nomorFakturPajak!=null && !nomorFakturPajak.isEmpty()){
			param.add("%"+nomorFakturPajak);
			nfpQuery = OBDal.getInstance().
					createQuery(nomorfakturpajak.class,
							"where nomorfakturpajak like ? order by nomorfakturpajak", param);
		} else {
			nfpQuery = OBDal.getInstance().
					createQuery(nomorfakturpajak.class, "order by nomorfakturpajak");
		}
		
		
		for (nomorfakturpajak nfp : nfpQuery.list()){
			Invoice invoice = OBDal.getInstance().get(Invoice.class, invoiceID);
			invoice.setIdNomorfakturpajak(nfp);
			OBDal.getInstance().save(invoice);
			msg.setType("Success");
			msg.setTitle("Nomor Faktur Pajak");
			msg.setMessage("Nomor faktur pajak "+nfp.getNomorfakturpajak()+" telah dipakai");
			bundle.setResult(msg);
			return;
		}
		
		msg.setType("Error");
		msg.setTitle("Nomor Faktur Pajak");
		if (nomorFakturPajak!=null && !nomorFakturPajak.isEmpty()){
			msg.setMessage("Nomor faktur pajak "+nomorFakturPajak+" tidak ditemukan");
		} else {
			msg.setMessage("Nomor faktur pajak yang masih belum dipakai tidak ditemukan");
		}
		
		bundle.setResult(msg);
		return;
	}

}
