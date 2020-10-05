package org.wirabumi.localization.id.community.ActionButton;

import java.math.BigDecimal;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.localization.id.community.data.nomorfakturpajak;

public class GenerateNomorFakturPajak extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {

    final OBError msg = new OBError();

    // mendapatkan record id
    final String prefix = (String) bundle.getParams().get("prefix");
    final String nomorAwal = (String) bundle.getParams().get("nomorawal");
    final String nomorAkhir = (String) bundle.getParams().get("nomorakhir");

    BigDecimal awal = new BigDecimal(nomorAwal);
    BigDecimal akhir = new BigDecimal(nomorAkhir);
    if (awal.compareTo(akhir) > 0) {
      throw new OBException(
          "Nomor faktur pajak tidak valid. Nomor awal harus lebih rendah atau sama dengan nomor akhir");
    }

    do {
      OBContext.setAdminMode();
      nomorfakturpajak nomorFakturPajak = OBProvider.getInstance().get(nomorfakturpajak.class);
      nomorFakturPajak.setNomorfakturpajak(prefix.concat(awal.toString()));
      OBDal.getInstance().save(nomorFakturPajak);
      OBContext.restorePreviousMode();
      awal = awal.add(new BigDecimal(1));

    } while (awal.compareTo(akhir) <= 0);
    OBDal.getInstance().commitAndClose();

    msg.setType("Success");
    msg.setTitle("Nomor Faktur Pajak");
    msg.setMessage("Nomor faktur pajak telah dibuat dari nomor " + nomorAwal + "sampai "
        + nomorAkhir);
    bundle.setResult(msg);

  }

}
