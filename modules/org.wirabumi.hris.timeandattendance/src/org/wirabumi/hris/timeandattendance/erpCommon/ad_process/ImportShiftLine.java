package org.wirabumi.hris.timeandattendance.erpCommon.ad_process;

import java.util.HashMap;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.system.Client;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.timeandattendance.LabourTimeMaster;
import org.wirabumi.hris.timeandattendance.TAShift;
import org.wirabumi.hris.timeandattendance.TA_ImportShiftLine;
import org.wirabumi.hris.timeandattendance.TA_ShiftLine;

public class ImportShiftLine extends DalBaseProcess {
	
	//DEFINE SHIFT MAP
	private final HashMap<String, TAShift> shiftMap = new HashMap<String, TAShift>();
	//DEFINE TIME MASTER MAP
	private final HashMap<String, LabourTimeMaster> timeMasterMap =  new HashMap<String, LabourTimeMaster>();

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		//GET SHIFT SET INTO SHIFT MAP
		OBCriteria<TAShift> criteriaShift = OBDal.getInstance().createCriteria(TAShift.class);
		if(criteriaShift.list().size() < 0) {
			for(TAShift shift : criteriaShift.list()) {
				shiftMap.put(shift.getCommercialName(), shift);
			}
		}
		
		//GET TIME MASTER SET INTO TIME MASTER MAP
		OBCriteria<LabourTimeMaster> criteriaTimeMaster = OBDal.getInstance().createCriteria(LabourTimeMaster.class);
		if(criteriaTimeMaster.list().size() < 0) {
			for(LabourTimeMaster timeMaster : criteriaTimeMaster.list()) {
				timeMasterMap.put(timeMaster.getName(), timeMaster);
			}
		}
		
		//GET RECORD IMPORT SHIFT LINE
		OBCriteria<TA_ImportShiftLine> criteria = OBDal.getInstance().createCriteria(TA_ImportShiftLine.class);
		criteria.add(Restrictions.eq(TA_ImportShiftLine.PROPERTY_PROCESSED, false));
		criteria.add(Restrictions.eq(TA_ImportShiftLine.PROPERTY_ISIMPORTED, false));
		if(criteria.list().size() > 0) {
			createShiftLine(criteria, bundle);
		} 		
		
	}

	private void createShiftLine(OBCriteria<TA_ImportShiftLine> criteria,
			ProcessBundle bundle) {
		int recSuccess = 0, recFailed = 0;
		TAShift shift = null;
		LabourTimeMaster timeMaster = null;
		
		//LOOPING FOR ANY RECORD IMPORT SHIFT LINE
		for(TA_ImportShiftLine importShiftLine : criteria.list()) {
			
			//CHECK IF SHIFT NAME NOT NULL AND HAVE ANY RECORD IN SHIFT MAP
			if(importShiftLine.getShiftName().equalsIgnoreCase("") || importShiftLine.getShiftName() == null) {
				recFailed++;
				continue;
			} else {
				if(shiftMap.containsKey(importShiftLine.getShiftName())) 
					shift = shiftMap.get(importShiftLine.getShiftName());
				 else {
					recFailed++;
					continue;
				}
			}
			
			//CHECK IF TIME MASTER NAME NOT NULL AND HAVE ANY RECORD IN TIME MASTER MAP
			if(!importShiftLine.getTimemasterName().equalsIgnoreCase("") && importShiftLine.getTimemasterName() != null) {
				if(timeMasterMap.containsKey(importShiftLine.getTimemasterName()))
					timeMaster = timeMasterMap.get(importShiftLine.getTimemasterName());
			}
			
			//CHECK IF LINE NO NOT NULL
			if(importShiftLine.getLineNo() == null) {
				recFailed++;
				continue;
			}
			
			//SET SHIFT LINE
			TA_ShiftLine shiftLine = OBProvider.getInstance().get(TA_ShiftLine.class);
			shiftLine.setShift(shift);
			shiftLine.setLineNo(importShiftLine.getLineNo());
			shiftLine.setCheckIn(importShiftLine.getCheckIn());
			shiftLine.setCheckOut(importShiftLine.getCheckOut());
			shiftLine.setOff(importShiftLine.isOff());
			shiftLine.setGrayareaAfter(importShiftLine.getGrayAreaAfter());
			shiftLine.setGrayareaBefore(importShiftLine.getGrayAreaBefore());
			shiftLine.setGrayareaCheckin(importShiftLine.getGrayAreaCheckIn());
			shiftLine.setGrayareaCheckout(importShiftLine.getGrayAreaCheckOut());
			shiftLine.setTimemaster(timeMaster);
			OBDal.getInstance().save(shiftLine);
			
			//SET RECORD COMPLETE IMPORT
			importShiftLine.setProcessed(true);
			importShiftLine.setImported(true);
			OBDal.getInstance().save(importShiftLine);
			
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
