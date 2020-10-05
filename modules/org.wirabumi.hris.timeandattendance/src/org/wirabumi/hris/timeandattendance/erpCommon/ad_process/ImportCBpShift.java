package org.wirabumi.hris.timeandattendance.erpCommon.ad_process;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.timeandattendance.TAShift;
import org.wirabumi.hris.timeandattendance.TA_ImportCBpShift;
import org.wirabumi.hris.timeandattendance.ta_c_bp_shift;

public class ImportCBpShift extends DalBaseProcess {

	//DEFINE EMPLOYEE MAP
	private final HashMap<String, BusinessPartner> employeeMap =  new HashMap<String, BusinessPartner>();
	//DEFINE OLD SHIFT MAP
	private final HashMap<ta_c_bp_shift, TAShift> oldShiftMap = new HashMap<ta_c_bp_shift, TAShift>();
	//DEFINE OLD EMPLOYEE SHIFT MAP
	private final HashMap<BusinessPartner, ta_c_bp_shift> oldEmployeeShiftMap = new HashMap<BusinessPartner, ta_c_bp_shift>();
	
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		//GET PARAMETER SHIFT ID 
		String shiftId = (String) bundle.getParams().get("taShiftId");
		TAShift shift = OBDal.getInstance().get(TAShift.class, shiftId);
		
		//GET EMPLOYEE SHIFT INTO OLD EMPLOYEE SHIFT AND OLD SHIFT MAP
		OBCriteria<ta_c_bp_shift> criteriaEmployeeShift = OBDal.getInstance().createCriteria(ta_c_bp_shift.class);
		criteriaEmployeeShift.add(Restrictions.ge(ta_c_bp_shift.PROPERTY_VALIDTODATE, shift.getValidFromDate()));
		if(criteriaEmployeeShift.list().size() > 0) {
			for(ta_c_bp_shift employeeShift : criteriaEmployeeShift.list()) {
				oldEmployeeShiftMap.put(employeeShift.getBusinessPartner(), employeeShift);
				oldShiftMap.put(employeeShift, employeeShift.getShift());
			}
		} 
		
		//GET EMPLOYEE SET INTO EMPLOYEE MAP
		OBCriteria<BusinessPartner> criteriaTimeMaster = OBDal.getInstance().createCriteria(BusinessPartner.class);
		if(criteriaTimeMaster.list().size() > 0) {
			for(BusinessPartner employee : criteriaTimeMaster.list()) {
				employeeMap.put(employee.getSearchKey(), employee);
			}
		}
		
		//GET RECORD IMPORT EMPLOYEE SHIFT
		OBCriteria<TA_ImportCBpShift> criteria = OBDal.getInstance().createCriteria(TA_ImportCBpShift.class);
		criteria.add(Restrictions.eq(TA_ImportCBpShift.PROPERTY_PROCESSED, false));
		criteria.add(Restrictions.eq(TA_ImportCBpShift.PROPERTY_IMPORTED, false));
		if(criteria.list().size() > 0) {
			createCBpShift(criteria, bundle, shift);
		} 		
	}

	//METHOD CREATE EMPLOYEE SHIFT RECORD TO TABLE
	private void createCBpShift(OBCriteria<TA_ImportCBpShift> criteria,
			ProcessBundle bundle, TAShift shift) {
		int recSuccess = 0, recFailed = 0;
		BusinessPartner employee = null;
		Calendar calendar = Calendar.getInstance();
		
		//LOOPING FOR ANY RECORD IMPORT EMPLOYEE SHIFT
		for(TA_ImportCBpShift importEmployeeShift : criteria.list()) {
			Date validFrom = shift.getValidFromDate();
			Date validTo = shift.getValidToDate();
			Date newValidTo = null;
			
			//CHECK IF EMPLOYEE KEY NOT NULL
			if(importEmployeeShift.getEmployeeKey().equalsIgnoreCase("") || importEmployeeShift.getEmployeeKey() == null) {
				recFailed++;
				continue;
			} else {
				if(employeeMap.containsKey(importEmployeeShift.getEmployeeKey())) 
					employee = employeeMap.get(importEmployeeShift.getEmployeeKey());
				else {
					recFailed++;
					continue;
				}
			}
			
			//CHECK IF EMPLOYEE HAVE SHIFT RECORD
			if(oldEmployeeShiftMap.containsKey(employee)) {
				//SET OLD EMPLOYEE SHIFT VALID TO = NEW SHIFT VALID FROM - 1 DAY
				ta_c_bp_shift employeeShift = oldEmployeeShiftMap.get(employee);
				calendar.setTime(validFrom);
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				newValidTo = calendar.getTime();
				employeeShift.setValidToDate(newValidTo);
				OBDal.getInstance().save(employeeShift);
				if(oldShiftMap.containsKey(employeeShift)) {
					TAShift oldShift = oldShiftMap.get(employeeShift);
					if(oldShift.getTaCBpShiftList().size() == 1) {
						oldShift.setValidToDate(newValidTo);
						OBDal.getInstance().save(oldShift);
					} 
				}
			}
			
			//SET EMPLOYEE SHIFT
			ta_c_bp_shift cBpShift = OBProvider.getInstance().get(ta_c_bp_shift.class);
			cBpShift.setShift(shift);
			cBpShift.setBusinessPartner(employee);
			cBpShift.setValidFromDate(validFrom);
			cBpShift.setValidToDate(validTo);
			OBDal.getInstance().save(cBpShift);
			
			importEmployeeShift.setProcessed(true);
			importEmployeeShift.setImported(true);
			OBDal.getInstance().save(importEmployeeShift);
			
			recSuccess++;
		}
		//MESSAGE RECORD CREATED SUCCESS
		if(recSuccess > 0 && recFailed == 0) {
			final OBError msg = new OBError();
		      msg.setType("Success");
		      msg.setTitle("Created Succes");
		      msg.setMessage(recSuccess + " " + "Record Created Success");
		      bundle.setResult(msg);
		}		
		//MESSAGE RECORD CREATED SUCCESS AND FAILED
		else if(recSuccess > 0 && recFailed > 0) {
			final OBError msg = new OBError();
		      msg.setType("Success");
		      msg.setTitle("Created Success with warning");
		      msg.setMessage(recSuccess + " " + "Record Created Success and " + recFailed + "Record Created Failed");
		      bundle.setResult(msg);
		}
		//MESSAGE RECORD CREATED FAILED
		else if(recSuccess == 0 && recFailed >= 0) {
			final OBError msg = new OBError();
		      msg.setType("Error");
		      msg.setTitle("Created Failed");
		      msg.setMessage("No One Record Created");
		      bundle.setResult(msg);
		}
		OBDal.getInstance().commitAndClose();
	}
}
