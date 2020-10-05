package org.wirabumi.hris.employee.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.ad.domain.Preference;
import org.openbravo.model.ad.ui.Window;
import org.openbravo.model.common.businesspartner.BusinessPartner;

public class HrisUtility {
	
	public static boolean isSupervisor(String employeeId, String supervisorId){
		BusinessPartner employee = OBDal.getInstance().get(BusinessPartner.class, employeeId);
		String currentSupervisorId=employee.getHrisReportTo().getId();
		
		//karyawan tidak memiliki supervisor, atasan tertinggi,
		//atau kemungkinan karyawan dan supervisor tidak dalam 1 path
		//return false
		if (currentSupervisorId==null || currentSupervisorId.equals("")) return false;
		
		//supervisor sudah ketemu, return true
		if (currentSupervisorId.equals(supervisorId)) return true;
		
		//supervisor belum ketemu, rekursif
		return isSupervisor(currentSupervisorId, supervisorId);
	}
	
	public static int GetSupervisorLevel(String employeeId, String supervisorId){
		return GetSupervisorLevel(employeeId, supervisorId, 0);
	}
	
	private static int GetSupervisorLevel(String employeeId, String supervisorId, int loopcounter){
		BusinessPartner employee = OBDal.getInstance().get(BusinessPartner.class, employeeId);
		String currentSupervisorId=employee.getHrisReportTo().getId();
		
		//karyawan tidak memiliki supervisor, atasan tertinggi,
		//atau kemungkinan karyawan dan supervisor tidak dalam 1 path
		//return false
		if (currentSupervisorId==null || currentSupervisorId.equals("")) return -1;
		
		//supervisor sudah ketemu, return true
		if (currentSupervisorId.equals(supervisorId)) return loopcounter+1;
		
		//supervisor belum ketemu, rekursif
		loopcounter++;
		return GetSupervisorLevel(currentSupervisorId, supervisorId, loopcounter);
	}
	public static int GetYearsOfService(String C_BpartnerID,int Tahun,int bulan){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String tahun = "";
		int yearsOfService = 0;
		int years = 0;
		int months = 0;
		OBCriteria<BusinessPartner> Employee = OBDal.getInstance().createCriteria(BusinessPartner.class);
		Employee.add(Restrictions.eq(BusinessPartner.PROPERTY_ID, C_BpartnerID));
		for(BusinessPartner employee :Employee.list()){
			  tahun=sdf.format(employee.getHrisJoindate()); 
			  years = Integer.parseInt(tahun.substring(0, 4));
			  months = Integer.parseInt(tahun.substring(5, 7));
			  if(bulan<months){
				  yearsOfService = (Tahun - years)-1;
			  }else{
				  yearsOfService = Tahun - years;
			  }
		}
		
		return yearsOfService ;
	}
	
	public static int GetMonthOfService(String C_BpartnerID,int bulan){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String tahun = "";
		int monthsOfService = 0;
		int months = 0;
		OBCriteria<BusinessPartner> Employee = OBDal.getInstance().createCriteria(BusinessPartner.class);
		Employee.add(Restrictions.eq(BusinessPartner.PROPERTY_ID, C_BpartnerID));
		for(BusinessPartner employee : Employee.list()){
			tahun=sdf.format(employee.getHrisJoindate()); 
			months = Integer.parseInt(tahun.substring(5, 7));
			if(bulan<months){
				monthsOfService= bulan-months+12;
			}else{
				monthsOfService = bulan - months;
			}
			
		}
		return monthsOfService ;
	}
	
	 //ambil searchkey
	  public static String ambilsearchkey(String WindowId, String attribut) {
	    String searchkey = "";
	    Window window = OBDal.getInstance().get(Window.class, WindowId);
	    String clause = "where window=? and attribute=?";
	    List<Object> param = new ArrayList<Object>();
	    param.add(window);
	    param.add(attribut);
	    OBQuery<Preference> preference = OBDal.getInstance().createQuery(Preference.class, clause);
	    preference.setParameters(param);
	    if (preference.list().size() > 0) {
	      Preference preferences = preference.list().get(0);
	      searchkey = preferences.getSearchKey();
	    }
	    return searchkey;
	  }
}
