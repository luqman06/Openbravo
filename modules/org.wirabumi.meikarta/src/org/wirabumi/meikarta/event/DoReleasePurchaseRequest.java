package org.wirabumi.meikarta.event;

import java.util.List;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectLine;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;;

public class DoReleasePurchaseRequest extends DocumentRoutingHandlerAction {

	/**
	 * khusus untuk barang IT, jika ada completion PR dari release, harus digagalkan.
	 * sebab completetion PR untuk barang IT, harus dari reviewed.
	 */
	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List<String> recordId) {
		for(String record_id : recordId){ //looping sebanyak yg di centang by recordId
			Project project = OBDal.getInstance().get(Project.class, record_id); //OBDal.getInstance = singel ton/ tidak boleh membuat new //object //project=cek table C_Project,Data Package
			if (!project.getOezDocstatus().equals("OEZ_RELEASE"))
				continue;
			List<ProjectLine> projectLineList = project.getProjectLineList();//membaca jumlah line //ProjectLine = cek table C_ProjectLine,Data Package
			for (ProjectLine projectLine : projectLineList){
				String kodeBarangIT = projectLine.getProduct().getProductCategory().getSearchKey();
				if (kodeBarangIT.equals("IT"))
					throw new OBException("@MEI_adaBarangITTidakbolehComplete@ Project Name: " + 
							project.getName()+" Sequence No: "+projectLine.getLineNo());
			}
			
			//validation pass
			//then set processed to true
			project.setProcessed(true);
			OBDal.getInstance().save(project);
		}
		
	}

	@Override
	public String getCoDocumentNo(String recordID, Tab tab) {
		// TODO Auto-generated method stub
		return null;
	}

}
