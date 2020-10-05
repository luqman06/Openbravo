package org.wirabumi.hris.employee.master.event;

import java.math.BigDecimal;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.calendar.Year;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.employee.master.data.KPIJobTitle;
import org.wirabumi.hris.employee.master.data.KPIVersionJobTitle;
import org.wirabumi.hris.employee.master.data.KPIMeasurement;

public class DataGenerateMeasurement extends BaseActionHandler {

	@Override
	protected JSONObject execute(Map<String, Object> parameters, String content) {
		JSONObject result = new JSONObject();
		try {
			JSONObject data = new JSONObject(content);
			if(data.getString("command").equals("ambilData")){
				result = ambilData(data);
			}else if(data.getString("command").equals("setData")){
				JSONArray idJtKpi= new JSONArray(data.getString("idJtKPI"));
				String idPegawai = data.getString("idPegawai");
				String idtahun = data.getString("year");
				String idBulan = data.getString("month");
				result = setData(data,idPegawai,idtahun,idBulan,idJtKpi);
			}else{
				System.out.println("Nothing");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private JSONObject ambilData(JSONObject data){
		JSONObject hasil = new JSONObject();
		try {
			JSONArray recID = new JSONArray(data.getString("data"));
			JSONArray tabel = new JSONArray();
			JSONArray tabelBulan = new JSONArray();
			
			for(int i=0;i<recID.length();i++){
				KPIJobTitle jtKpi = OBDal.getInstance().get(KPIJobTitle.class, recID.get(i));
				String idVersion = jtKpi.getJobTitleKPIVersion().getId();
				KPIVersionJobTitle versionKpi = OBDal.getInstance().get(KPIVersionJobTitle.class, idVersion);
				String idJobtitle = versionKpi.getJobTitle().getId();
				hris_jobtitle jabatan = OBDal.getInstance().get(hris_jobtitle.class, idJobtitle);
				String idClient = jabatan.getClient().getId();
				Client client = OBDal.getInstance().get(Client.class, idClient);
				OBCriteria<Year> tahun = OBDal.getInstance().createCriteria(Year.class);
				tahun.add(Restrictions.eq(Year.PROPERTY_CLIENT, client));
				for(Year tahunNow : tahun.list()){
					OBCriteria<Period> periode = OBDal.getInstance().createCriteria(Period.class);
					periode.add(Restrictions.eq(Period.PROPERTY_YEAR, tahunNow));
					for(Period bulanan : periode.list()){
						JSONObject perBulan = new JSONObject();
						perBulan.put("idTahun", bulanan.getYear().getId());
						perBulan.put("namaTahun", bulanan.getYear().getFiscalYear());
						perBulan.put("periodID", bulanan.getId());
						perBulan.put("namaBulan", bulanan.getName());
						tabelBulan.put(perBulan);
					}
				}
				OBCriteria<BusinessPartner> employee = OBDal.getInstance().createCriteria(BusinessPartner.class);
				employee.add(Restrictions.eq(BusinessPartner.PROPERTY_HRISJOBTITLE, jabatan));
				for(BusinessPartner pegawai : employee.list()){
					JSONObject baris = new JSONObject();
					baris.put("idPegawai",pegawai.getId());
					baris.put("pegawai", pegawai.getName());
					tabel.put(baris);
				}	
			}
			hasil.put("dataEmployee", tabel);
			hasil.put("periode", tabelBulan);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasil;
	}
	private JSONObject setData(JSONObject data,String idPegawai,String idtahun,String periodID,JSONArray idJtKpi){
		JSONObject hasil = new JSONObject();
		try {
			JSONArray recID = idJtKpi;
			BusinessPartner employee = OBDal.getInstance().get(BusinessPartner.class, idPegawai);
			Year tahun = OBDal.getInstance().get(Year.class, idtahun);
			Period bulan = OBDal.getInstance().get(Period.class, periodID);
			for(int i=0;i<recID.length();i++){
				OBCriteria<KPIJobTitle>kpi = OBDal.getInstance().createCriteria(KPIJobTitle.class);
				kpi.add(Restrictions.eq(KPIJobTitle.PROPERTY_ID, recID.get(i)));
				
				for(KPIJobTitle jtKpi : kpi.list()){
						BigDecimal target = jtKpi.getTarget();
						KPIMeasurement measurement = OBProvider.getInstance().get(KPIMeasurement.class);
						measurement.setJobTitleKPI(jtKpi);
						measurement.setEmployee(employee);
						measurement.setYear(tahun);
						measurement.setPeriod(bulan);
						measurement.setTarget(target);
						//measurement.setDocumentAction("CO");
						measurement.setDocumentStatus("DR");
						measurement.setWeightedachievment(BigDecimal.valueOf(0));
						measurement.setAchievement(BigDecimal.valueOf(0));
						measurement.setActualBySupervisor(BigDecimal.valueOf(0));
						measurement.setActualByReviewer(BigDecimal.valueOf(0));
						measurement.setActual(BigDecimal.valueOf(0));
						OBDal.getInstance().save(measurement);
				}
			}
			OBDal.getInstance().commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasil;
	}
}
