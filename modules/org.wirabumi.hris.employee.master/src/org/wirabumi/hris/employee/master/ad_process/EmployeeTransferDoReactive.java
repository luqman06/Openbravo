package org.wirabumi.hris.employee.master.ad_process;

import java.util.ArrayList;
import java.util.List;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.DalUtil;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;
import org.wirabumi.hris.employee.master.data.EmployeeTransfer;
import org.wirabumi.hris.employee.master.data.EmployeeTransferLine;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;

public class EmployeeTransferDoReactive extends DocumentRoutingHandlerAction {

	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List recordId) {
			// TODO Auto-generated method stub
		
		//list record id menyimpan total batch dari mutasi (list recordid)
		//itersi dari total batch mutasi
		for(int i=0;i<recordId.size();i++){
			//fetching 
			String employeeTransferId=(String) recordId.get(i);
			//jika tidak ada data pada batch tersebut maka lanjutkan ke 
			//elemen mutasi selanjutnya (next batch)
			if(employeeTransferId==null||employeeTransferId.isEmpty()){
				continue;
			}
			EmployeeTransfer et=OBDal.getInstance().get(EmployeeTransfer.class, employeeTransferId);
			//Ambil informasi line (karyawan2 yang akan dimutasi yang masuk dalam batch tersebut)
			//jika sudah dapat berapa total line(karyawan)
			List <EmployeeTransferLine> etLineList=et.getHrisEtLineList();
			//iterasi donk..
			for(EmployeeTransferLine etLine:etLineList){
				//lepas link SK baru
				HRIS_C_Bp_Empinfo skbaru = etLine.getNewcontract();
				if (skbaru!=null){
					etLine.setNewcontract(null);
					OBDal.getInstance().save(etLine);
					
					//delete SK baru
					OBDal.getInstance().remove(skbaru);
					
				}
					
				//aktifkan SK lama
				HRIS_C_Bp_Empinfo sklama = etLine.getOldcontract();
				if (sklama!=null){
					sklama.setCurrentpos(true);
					OBDal.getInstance().save(sklama);
					
					//lepas link SK lama
					etLine.setOldcontract(null);
					
				}
				
				//release ke DB
				OBDal.getInstance().flush();
			}
			OBDal.getInstance().commitAndClose();
		}


	}

	@Override
	public String getCoDocumentNo(String recordID, Tab tab) {
		// TODO Auto-generated method stub
		return null;
	}

}
