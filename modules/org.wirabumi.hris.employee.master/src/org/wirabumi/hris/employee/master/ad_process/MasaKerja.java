package org.wirabumi.hris.employee.master.ad_process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.ad.domain.Preference;
import org.openbravo.model.ad.ui.Window;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
//import org.wirabumi.hris.employee.master.MasaKerjaUtility;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;

public class MasaKerja extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		// TODO Auto-generated method stub
		try {
			OBCriteria<BusinessPartner> businessPartner = OBDal.getInstance().createCriteria(BusinessPartner.class);
//			businessPartner.add(Restrictions.eq(BusinessPartner.PROPERTY_ID, "85B740ED1C744A018C20B4E4069B350D"));

			for(BusinessPartner CBpInfo : businessPartner.list()){
				List<Object> param = new ArrayList<Object>();
				param.add(CBpInfo);
				OBQuery<HRIS_C_Bp_Empinfo> hrisBpInfo = OBDal.getInstance().createQuery(HRIS_C_Bp_Empinfo.class, "where businessPartner=? " +
						"order by validFromDate asc");
				hrisBpInfo.setParameters(param);
				for(HRIS_C_Bp_Empinfo BpInfo : hrisBpInfo.list() ){
					String ContractType = BpInfo.getHrisContracttype();
					String SK = findContractType(ContractType);
					Date ValidFrom = BpInfo.getValidFromDate();

					if(SK.equals("MUTASI")){
						//double Year = MasaKerjaUtility.getYear(ValidFrom,new Date());
						//double Month = MasaKerjaUtility.getMonth(ValidFrom,new Date());
						
						//CBpInfo.setBlgMaskerEchThn((long) Year);
						//CBpInfo.setBlgMaskerEchBln((long) Month);
						//CBpInfo.setBlgMaskerJabTh((long) Year);
						//CBpInfo.setBlgMaskerJabBln((long) Month);
						//CBpInfo.setBlgSkMutasi(BpInfo);
					}else if(SK.equals("PENGANGKATAN")){
						//double Year = MasaKerjaUtility.getYear(ValidFrom,new Date());
						//double Month = MasaKerjaUtility.getMonth(ValidFrom,new Date());
						
						//CBpInfo.setBlgMaskerEchThn((long) Year);
						//CBpInfo.setBlgMaskerEchBln((long) Month);
						//CBpInfo.setBlgMaskerJabTh((long) Year);
						//CBpInfo.setBlgMaskerJabBln((long) Month);
						//CBpInfo.setBlgMasagolongan(new BigDecimal(Year));
						//CBpInfo.setBlgMaskerGolBln((long) Month);
						//CBpInfo.setBlgMasakenaikangaji(new BigDecimal(Year));
						
					}else if(SK.equals("KENAIKANGOL")){
						//double Year = MasaKerjaUtility.getYear(ValidFrom,new Date());
						//double Month = MasaKerjaUtility.getMonth(ValidFrom,new Date());
						
						//CBpInfo.setBlgMasagolongan(new BigDecimal(Year));
						//CBpInfo.setBlgMaskerGolBln((long) Month);
						//CBpInfo.setBlgSkGol(BpInfo);
					}else if(SK.equals("KENAIKANGAJI")){
						//double Year = MasaKerjaUtility.getYear(ValidFrom,new Date());
						//double Month = MasaKerjaUtility.getMonth(ValidFrom,new Date());
						
						//CBpInfo.setBlgMasakenaikangaji(new BigDecimal(Year));
						//CBpInfo.setBlgSkkenaikangaji(BpInfo);
					}
				}
				OBDal.getInstance().save(CBpInfo);
			}
			OBDal.getInstance().commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			OBDal.getInstance().rollbackAndClose();
		}
	}

	
	private  String findContractType(String Attribute){
		String SearchKey ="";
		String hasil ="";
		
		Window win = OBDal.getInstance().get(Window.class, "86C8B5CFAF5B46ADB7AA4B23BD9ED17D");
		
		List<Object> param = new ArrayList<Object>();
		param.add(Attribute);
		param.add(win);
		
		OBQuery<Preference> preference = OBDal.getInstance().createQuery(Preference.class, "where attribute=? and window=?");
		preference.setParameters(param);
		if(preference.list().size() > 0){
			Preference prefer = preference.list().get(0);
			SearchKey = prefer.getSearchKey();
		}
		hasil = SearchKey;
		return hasil;
	}
}
