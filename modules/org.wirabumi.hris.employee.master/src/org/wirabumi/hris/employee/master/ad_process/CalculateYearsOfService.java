package org.wirabumi.hris.employee.master.ad_process;

import java.util.Calendar;

import org.apache.tools.ant.types.resources.Restrict;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.HrisUtility;

public class CalculateYearsOfService extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		// TODO Auto-generated method stub
		Calendar kalender = Calendar.getInstance();
	    kalender.set(Calendar.HOUR, 0);
	    kalender.set(Calendar.MINUTE, 0);
	    kalender.set(Calendar.SECOND, 0);
	    kalender.set(Calendar.MILLISECOND, 0);
		int years = kalender.get(Calendar.YEAR);
		int months = kalender.get(Calendar.MONTH);
		int bulan = months+1;
		
		try {
			OBCriteria<BusinessPartner> employeeList = OBDal.getInstance().createCriteria(BusinessPartner.class);
			employeeList.add(Restrictions.eq(BusinessPartner.PROPERTY_EMPLOYEE, true));
			for(BusinessPartner employee:employeeList.list()){
				int yearsOfService = HrisUtility.GetYearsOfService(employee.getId(), years,bulan);
				int monthsOfService = HrisUtility.GetMonthOfService(employee.getId(),bulan);
				employee.setHrisYear((long)yearsOfService);
				employee.setHrisMonth((long)monthsOfService);
				OBDal.getInstance().save(employee);
			}
			OBDal.getInstance().commitAndClose();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
