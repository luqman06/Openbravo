package org.wirabumi.hris.employee.master.ad_callout;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.wirabumi.hris.employee.master.data.EmployeeTransfer;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Department;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.employee.master.data.hris_site;

public class EmployeeTransferGetContract extends SimpleCallout {

	private static final long serialVersionUID = 1L;
	Logger log4j = Logger.getLogger(EmployeeTransferGetContract.class);

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		 String strEmployeeID = info.getStringParameter("inpcBpartnerId", null);
		 log4j.debug("processing employee id: "+strEmployeeID);
		 
		 log4j.debug("dapatkan atribut karyawan, lalu dimasukkan ke SK lama dan SK baru secara otomatis");
		 BusinessPartner employee = OBDal.getInstance().get(BusinessPartner.class, strEmployeeID);
		 
		 log4j.debug("cari kepegawaian sesuai sk terakhir");
		 String kepegawaianLama = employee.getHRISEmployementType();
		 info.addResult("inpoldEmploymenttype", kepegawaianLama);
		 info.addResult("inpnewEmploymenttype", kepegawaianLama);
		 
		 log4j.debug("cari payroll master sesuai sk terakhir");
		 BusinessPartner payrollMaster=employee.getPyrPayrollmaster();
		 if(payrollMaster!=null){
			 info.addResult("inpnewPayrollmasterId", payrollMaster.getId());
			 info.addResult("inpoldPayrollmasterId", payrollMaster.getId());			 
		 }else{
			 info.addResult("inpnewPayrollmasterId", null);
			 info.addResult("inpoldPayrollmasterId", null);			 
		 }
		 
		 log4j.debug("cari departemen sesuai sk terakhir");
		 HRIS_C_Bp_Department departemenLama = employee.getHrisCBpDepartment();
		 if (departemenLama!=null){
			 info.addResult("inpoldDepartmentId", departemenLama.getId());
		 	info.addResult("inpnewDepartmentId", departemenLama.getId());
		 }else {
			 info.addResult("inpoldDepartmentId", null);
			 info.addResult("inpnewDepartmentId", null);
		 }
		 
		 log4j.debug("cari jenjang jabatan sesuai sk terakhir");
		 String echelon=employee.getHrisEchelon();
		 info.addResult("inpnewEchelon", echelon);
		 info.addResult("inpoldEchelon", echelon);
		 
		 log4j.debug("cari site karyawan sesuai sk terakhir");
		 hris_site site=employee.getHrisSite();
		 if(site!=null){
			 info.addResult("inpnewHrisSiteId", site.getId());
			 info.addResult("inpoldHrisSiteId", site.getId());			 
		 }else{
			 info.addResult("inpnewHrisSiteId", null);
			 info.addResult("inpoldHrisSiteId", null);			 
		 }
		 
		 log4j.debug("cari pangkat/golongan sesuai sk terakhir");
		 String pangkat=employee.getHRISLevel();
		 info.addResult("inpoldEmployeegrade", pangkat);
		 info.addResult("inpnewEmployeegrade", pangkat);
		 
		 log4j.debug("cari nomor SK dari header, lalu masukkan ke lines");
		 //user bisa mengubah nomor SK ini jika diperlukan, misalnya dalam 1 batch mutasi tiap-tiap orang memiliki nomor SK beda-beda,
		 //maka field ini harus diubah sesuai nomor SK yang diberikan.
		 String employeeTransferID=info.getStringParameter("inphrisEmployeetransferId", null);
		 if(employeeTransferID!=null&&!employeeTransferID.isEmpty()){
			 EmployeeTransfer et=OBDal.getInstance().get(EmployeeTransfer.class, employeeTransferID);
			 if(et!=null){
				 info.addResult("inpdocumentno",et.getDocumentNo());
			 }
		 }
		 
		 log4j.debug("cari jabatan karyawan sesuai SK terakhir");
		 String position=employee.getHRISPosition();
		 info.addResult("inpoldPosition", position);
		 info.addResult("inpnewPosition", position);
		 
		 log4j.debug("cari pusat biaya/Business Unit (BU) sesuai SK terakhir");
		 Costcenter costCenter=employee.getHrisCCostcenter();
		 if(costCenter!=null){
			 info.addResult("inpoldCostcenterId", costCenter.getId());
			 info.addResult("inpnewCostcenterId", costCenter.getId());
		 }else{
			 info.addResult("inpoldCostcenterId", null);
			 info.addResult("inpnewCostcenterId", null);		 
		 }
		 
		 log4j.debug("cari jabatan lengkap sesuai SK terakhir");
		 hris_jobtitle jobTitleLama = employee.getHrisJobtitle();
		 if (jobTitleLama!=null){
			 info.addResult("inpoldJobtitleId", jobTitleLama.getId());
			 info.addResult("inpnewJobtitleId", jobTitleLama.getId());
		 } else {
			 info.addResult("inpoldJobtitleId", null);
			 info.addResult("inpnewJobtitleId", null);
		 }
		 
	}
	
}
