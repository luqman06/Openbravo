package org.wirabumi.hris.employee.master.ad_process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.DalUtil;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.access.Role;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.access.UserRoles;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.wirabumi.gen.oez.event.DocumentRoutingHandlerAction;
import org.wirabumi.hris.employee.master.data.EmployeeTransfer;
import org.wirabumi.hris.employee.master.data.EmployeeTransferLine;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;


public class EmployeeTransferDoComplete extends DocumentRoutingHandlerAction {
	
	private final HashMap<User, List<Role>> userRoleMap = new HashMap<User, List<Role>>();
	private final HashMap<String, CostCenter> manufacturingCostCenter = new HashMap<String, CostCenter>();

	@Override
	public void doRouting(String adWindowId, String adTabId,
			String doc_status_to, VariablesSecureApp vars, List<String> recordId) {
		
		//load all manufacturing cost center, simpan di hashmap
		OBCriteria<CostCenter> manfCostCenterCriteria = OBDal.getInstance().createCriteria(CostCenter.class);
		for (CostCenter mac : manfCostCenterCriteria.list()){
			manufacturingCostCenter.put(mac.getSearchKey(), mac);
		}
		//load all user roles, simpan di hashmap
		OBCriteria<UserRoles> urC = OBDal.getInstance().createCriteria(UserRoles.class);
		for (UserRoles ur : urC.list()){
			List<Role> roles = null;
			User user = ur.getUserContact();
			Role role = ur.getRole();
			if (userRoleMap.containsKey(user)){
				roles=userRoleMap.get(user);
			} else
				roles = new ArrayList<Role>();
			
			if (!roles.contains(role))
				roles.add(role);
			
			userRoleMap.put(user, roles);
		}
		
		//load all current employee contract to hashmap
		HashMap<BusinessPartner, HRIS_C_Bp_Empinfo> contracts = new HashMap<BusinessPartner, HRIS_C_Bp_Empinfo>();
		OBCriteria<HRIS_C_Bp_Empinfo> contractCriteria = OBDal.getInstance().createCriteria(HRIS_C_Bp_Empinfo.class);
		contractCriteria.add(Restrictions.eq(HRIS_C_Bp_Empinfo.PROPERTY_ISCURRENTPOS, true));
		for (HRIS_C_Bp_Empinfo contract : contractCriteria.list()){
			BusinessPartner employee = contract.getBusinessPartner();
			contracts.put(employee, contract);
		}
		
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
				//to do
				//untuk setiap karyawan, ub
				BusinessPartner employee=etLine.getEmployee();
				
				if(employee==null)
					continue;	
				if(!contracts.containsKey(employee))
					continue;
				
				//dapat sk lama
				HRIS_C_Bp_Empinfo skLama=contracts.get(employee);
				//sk lama dinonaktifkan
				skLama.setCurrentpos(false);
				//SK lama berakhir 1 hari sebelum sk baru
				Calendar cal = Calendar.getInstance();
				cal.setTime(et.getTransactionDate());
				cal.add(Calendar.DATE, -1);
				Date validToSKLama = cal.getTime();
				skLama.setValidToDate(validToSKLama);

				//disimpan ke tabel hris_c_bp_empinfo
				OBDal.getInstance().save(skLama);
			    etLine.setOldcontract(skLama);
			    OBDal.getInstance().save(etLine);
				//HRIS_C_Bp_Empinfo skBaru= OBProvider.getInstance().get(HRIS_C_Bp_Empinfo.class);
			    HRIS_C_Bp_Empinfo skBaru= (HRIS_C_Bp_Empinfo) DalUtil.copy(skLama);
//				skBaru.setBusinessPartner(employee);
				
				skBaru.setValidFromDate(et.getTransactionDate());
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				Date validTo=null;
				try {
					validTo = df.parse("31-12-9999");
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				setRole(etLine);
				skBaru.setValidToDate(validTo);
				skBaru.setCurrentpos(true);
				skBaru.setHrisCBpDepartment(etLine.getNEWDepartment());
				skBaru.setEmployementtype(etLine.getNEWEmploymenttype());
				skBaru.setEchelon(etLine.getNEWEchelon());
				skBaru.setSite(etLine.getNEWHrisSite());
				skBaru.setPYRPayrollMaster(etLine.getNEWPayrollmaster());
				skBaru.setLevel(etLine.getNEWEmployeegrade());
				Costcenter newCostcenter = etLine.getNEWCostcenter();
				if (newCostcenter!=null){
					String costcenterkey = newCostcenter.getSearchKey();
					if (manufacturingCostCenter.containsKey(costcenterkey)){
						CostCenter mac = manufacturingCostCenter.get(costcenterkey);
						skBaru.setCostcenter(mac);
					}
				} else
					throw new OBException("employee transger baris ke "+etLine.getLineNo()+" field pusat biaya baru tidak diisi");
				
				skBaru.setEmployeeCostCenter(newCostcenter);
				skBaru.setPosition(etLine.getNEWPosition());
				skBaru.setHrisJobtitle(etLine.getNEWJobtitle());
				skBaru.setHrisContracttype(etLine.getEmployeetransfertype());
				skBaru.setContractno(et.getDocumentNo());
				OBDal.getInstance().save(skBaru);
				
				etLine.setNewcontract(skBaru);
				OBDal.getInstance().save(etLine);
				
			}
			OBDal.getInstance().commitAndClose();
		}

	}

	//tambahan method memasukkan role baru
	private void setRole(EmployeeTransferLine etLine) {
		BusinessPartner employee = etLine.getEmployee();
		String nik = employee.getSearchKey();
		String nama = employee.getName();
		List<User> users = employee.getADUserList();
		if (users.size()==0)
			throw new OBException("employee "+nik+"-"+nama+" tidak memiliki user ID");
		
		User user = users.get(0);
		//ambil data user dari list
		if (!userRoleMap.containsKey(user)){
			throw new OBException("employee "+nik+"-"+nama+" tidak memiliki hak akses");
		}
		
		List<Role> roles = userRoleMap.get(user);
		hris_jobtitle newJobTitle = etLine.getNEWJobtitle();
		Role newRole = newJobTitle.getRole();
		if (newRole==null)
			throw new OBException("jabatan lengkap "+newJobTitle+" tidak memiliki hak ases");
		//set field default role yang baru dari job title
		user.setDefaultRole(newRole);
		//simpan
		OBDal.getInstance().save(user);
		
		//ubah user roles jika memang belum ada
		if (!roles.contains(newRole)){
			UserRoles userRole = user.getADUserRolesList().get(0);
			//set field role tab role access yang baru dari job title
			userRole.setRole(newRole);
			//simpan
			OBDal.getInstance().save(userRole);

		}
				
	}

	@Override
	public String getCoDocumentNo(String recordID, Tab tab) {
		// TODO Auto-generated method stub
		return null;
	}

}

