package org.wirabumi.hris.pph.ad_process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.exception.NoConnectionAvailableException;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.calendar.Calendar;
import org.openbravo.model.financialmgmt.calendar.Year;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.wirabumi.hris.pph.ImportPPh21;
import org.wirabumi.hris.pph.pph_pph21;

public class ProcessImportPPh21 extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		//build message
		OBError oberror = new OBError();
		
		//get not imported receord
		OBCriteria<ImportPPh21> importPPhC = OBDal.getInstance().createCriteria(ImportPPh21.class);
		importPPhC.add(Restrictions.eq(ImportPPh21.PROPERTY_IMPORTPROCESSCOMPLETE, false));
		
		List<ImportPPh21> importPPhList = importPPhC.list();
		if (importPPhList==null || importPPhList.size()==0){
			oberror.setType(ERROR);
			oberror.setTitle("Error");
			oberror.setMessage("no record to be imported.");
			
			bundle.setResult(oberror);
			return;
		}	
		
		final HashMap<String, BusinessPartner> employeeMap = getEmployeeMap();
		final HashMap<String, Year> fiscalYearMap = getFiscalYearMap();
		
		//load to transaction table
		int a=0;
		for (ImportPPh21 importPPh : importPPhC.list()){
			//data preparation
			StringBuilder sb = new StringBuilder();
			if (importPPh.getEmployee()==null && importPPh.getEmployeekey()!=null){
				String employeekey = importPPh.getEmployeekey();
				if (!employeeMap.containsKey(employeekey)){
					sb.append("employee with key "+employeekey+" not exists").append("\n");
				} else {
					BusinessPartner employee = employeeMap.get(employeekey);
					importPPh.setEmployee(employee);
				}
			} else
				sb.append("employee key is empty").append("\n");
			
			if (importPPh.getYear()==null && importPPh.getFiscalyear()!=null){
				String yearkey = importPPh.getFiscalyear();
				if (!fiscalYearMap.containsKey(yearkey)){
					sb.append("fiscal year "+yearkey+" not exists").append("\n");
					
				} else {
					Year fiscalyear = fiscalYearMap.get(yearkey);
					importPPh.setYear(fiscalyear);
				}
				
			} else
				sb.append("fiscal year is empty").append("\n");
			
			//cek apakah ada error
			if (sb.length()>0){
				importPPh.setProcessed(false);
				importPPh.setImportProcessComplete(false);
				importPPh.setImportErrorMessage(sb.toString());
				importPPh.setPPh21(null);
				OBDal.getInstance().save(importPPh);
				OBDal.getInstance().flush();
				
				continue;
			}
			
			
			//employee information
			pph_pph21 pph21 = OBProvider.getInstance().get(pph_pph21.class);
			pph21.setEmployee(importPPh.getEmployee());
			pph21.setTaxMaritalStatus(importPPh.getTaxMaritalStatus());
			pph21.setFiscalYear(new Long(importPPh.getYear().getFiscalYear()));
			pph21.setMonth(importPPh.getMonth());
			pph21.setMasaPPh(importPPh.getMasaPPh());
			pph21.setPajakRampung(importPPh.isPajakRampung());
			
			//penghasilan bulan ini
			pph21.setPendapatanRutinGrossSebulan(importPPh.getPendapatanRutinGrossSebulan());
			pph21.setPendapatanRutinGrossSetahun(importPPh.getPendapatanRutinGrossSetahun());
			pph21.setPendapatanRutinNettSebulan(importPPh.getPendapatanRutinNettSebulan());
			pph21.setPendapatanRutinNettSetahun(importPPh.getPendapatanRutinNettSetahun());
			pph21.setPendapatanLainGross(importPPh.getPendapatanLainGross());
			pph21.setPendapatanLainNett(importPPh.getPendapatanLainNett());
			pph21.setPendapatanBrutoTotalSetahun(importPPh.getPendapatanBrutoTotalSetahun());
			
			//penghasilan lain
			pph21.setPendapatanLainSaja(importPPh.isPendapatanLainSaja());
			pph21.setPendapatanRutinGrossSebulanPadaBulanLalu(importPPh.getPendapatanRutinGrossSebulanPadaBulanLalu());
			pph21.setPendapatanRutinNettSebulanPadaBulanLalu(importPPh.getPendapatanRutinNettSebulanPadaBulanLalu());
			pph21.setPendapatanLainGrossBulanLalu(importPPh.getPendapatanLainGrossBulanLalu());
			pph21.setPendapatanLainNettBulanLalu(importPPh.getPendapatanLainNettBulanLalu());
			pph21.setPengurangPajakBulanLalu(importPPh.getPengurangPajakBulanLalu());
			pph21.setPPh21DibayarBulanLalu(importPPh.getPPh21DibayarBulanLalu());
			
			//pengurang pajak
			pph21.setBiayaJabatanSetahun(importPPh.getBiayaJabatanSetahun());
			pph21.setPengurangPajakSetahun(importPPh.getPengurangPajakSetahun());
			pph21.setPTKPSetahun(importPPh.getPTKPSetahun());
			pph21.setPKPSetahun(importPPh.getPKPSetahun());
			
			//hasil perhitungan
			pph21.setPPh21DibayarSampaiDenganBulanSebelumnya(importPPh.getPPh21DibayarSampaiDenganBulanSebelumnya());
			pph21.setPPh21Setahun(importPPh.getPPh21Setahun());
			pph21.setPPh21TerhutangBulanIni(importPPh.getPPh21TerhutangBulanIni());
			pph21.setPPh21TerpotongBulanIni(importPPh.getPPh21TerpotongBulanIni());
			pph21.setTunjanganPPh21BulanIni(importPPh.getTunjanganPPh21BulanIni());
			
			//save object
			OBDal.getInstance().save(pph21);
			
			//tandai sudah diimport
			importPPh.setProcessed(true);
			importPPh.setImportProcessComplete(true);
			importPPh.setImportErrorMessage(null);
			importPPh.setPPh21(pph21);
			OBDal.getInstance().save(importPPh);
			OBDal.getInstance().flush();
			a++;
		}
		
		oberror.setType(SUCCESS);
		oberror.setTitle("Success");
		oberror.setMessage("Employee income tax imported successfully, "+a+" record(s) imported.");
		
		bundle.setResult(oberror);
		return;

	}

	private HashMap<String, Year> getFiscalYearMap() throws NoConnectionAvailableException, SQLException {
		HashMap<String, Year> output = new HashMap<String, Year>();
		String calendarOwner = getCalendarOwner(OBContext.getOBContext().getCurrentOrganization().getId());
		Organization calendarOwnerOrg = OBDal.getInstance().get(Organization.class, calendarOwner);
		Calendar fiscalCalendar = calendarOwnerOrg.getCalendar();
		for (Year fiscalYear : fiscalCalendar.getFinancialMgmtYearList()){
			output.put(fiscalYear.getFiscalYear(), fiscalYear);
		}
		return output;
	}

	private String getCalendarOwner(String currentOrganization) throws NoConnectionAvailableException, SQLException {
		String query = "select ad_org_getcalendarowner from ad_org_getcalendarowner(?)";
		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection = conn.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, currentOrganization);
		ResultSet rs = ps.executeQuery();
		String output = null;
		while(rs.next()){
			output = rs.getString("ad_org_getcalendarowner");
		}
		
		return output;
	}

	private HashMap<String, BusinessPartner> getEmployeeMap() {
		OBCriteria<BusinessPartner> employeeC = OBDal.getInstance().createCriteria(BusinessPartner.class);
		employeeC.add(Restrictions.eq(BusinessPartner.PROPERTY_EMPLOYEE, true));
		HashMap<String, BusinessPartner> output = new HashMap<String, BusinessPartner>();
		for (BusinessPartner bp : employeeC.list()){
			String key = bp.getSearchKey();
			output.put(key, bp);
		}
		return output;
	}

}
