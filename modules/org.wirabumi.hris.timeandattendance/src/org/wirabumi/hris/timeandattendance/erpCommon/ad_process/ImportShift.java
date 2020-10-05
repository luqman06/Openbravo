package org.wirabumi.hris.timeandattendance.erpCommon.ad_process;

import java.util.HashMap;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.domain.List;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.timeandattendance.LabourTimeMaster;
import org.wirabumi.hris.timeandattendance.TAShift;
import org.wirabumi.hris.timeandattendance.TA_ImportShift;
import org.wirabumi.hris.timeandattendance.TA_ShiftLine;

public class ImportShift extends DalBaseProcess {

	//DEFINE TIME MASTER MAP
	private final HashMap<String, LabourTimeMaster> timeMasterMap =  new HashMap<String, LabourTimeMaster>();
	
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
	
		//GET REFERENCE LIST
		OBCriteria<List> crit = OBDal.getInstance().createCriteria(List.class);
		if(crit.list().size() < 0) {
			throw new OBException("Reference list is null"); 
		}
		
		//GET TIME MASTER SET INTO TIME MASTER MAP
		OBCriteria<LabourTimeMaster> criteriaTimeMaster = OBDal.getInstance().createCriteria(LabourTimeMaster.class);
		if(criteriaTimeMaster.list().size() < 0) {
			for(LabourTimeMaster timeMaster : criteriaTimeMaster.list()) {
				timeMasterMap.put(timeMaster.getName(), timeMaster);
			}
		}
		
		//GET RECORD IMPORT SHIFT
		OBCriteria<TA_ImportShift> criteria = OBDal.getInstance().createCriteria(TA_ImportShift.class);
		criteria.add(Restrictions.eq(TA_ImportShift.PROPERTY_PROCESSED, false));
		criteria.add(Restrictions.eq(TA_ImportShift.PROPERTY_IMPORTED, false));
		if(criteria.list().size() > 0) {
			createShift(criteria, bundle, crit);
		} 		
	}

	//METHOD CREATE SHIFT RECORD TO TABLE
	private void createShift(OBCriteria<TA_ImportShift> criteria,
			ProcessBundle bundle, OBCriteria<List> refList) {
		int recSuccess = 0, shiftModeNull = 0, shiftModeNoRef = 0;
		LabourTimeMaster timeMaster = null;
		
		//LOOPING FOR ANY RECORD IMPORT SHIFT
		for(TA_ImportShift importShift : criteria.list()) {
			
			//CHECK IF SHIFT MODE NOT NULL AND HAVE REFERENCE
			if(importShift.getShiftModeKey().equalsIgnoreCase("") || importShift.getShiftModeKey() == null) {
				shiftModeNull++;
				continue;
			} else if(!refList.list().contains(importShift.getShiftModeKey())) {
				shiftModeNoRef++;
				continue;
			}
			
			//CHECK IF TIME MASTER NAME NOT NULL AND HAVE ANY RECORD IN TIME MASTER MAP
			if(!importShift.getTimemasterName().equalsIgnoreCase("") && importShift.getTimemasterName() != null) {
				if(timeMasterMap.containsKey(importShift.getTimemasterName()))
					timeMaster = timeMasterMap.get(importShift.getTimemasterName());
			}
			
			//SET SHIFT
			TAShift shift = OBProvider.getInstance().get(TAShift.class);
			shift.setCommercialName(importShift.getName());
			shift.setValidFromDate(importShift.getValidFromDate());
			shift.setValidToDate(importShift.getValidToDate());
			shift.setDescription(importShift.getDescription());
			//shift.setBrpShiftmode(importShift.getShiftModeKey()); //TODO fixme
			OBDal.getInstance().save(shift);
			
			//SET SHIFT LINE
			TA_ShiftLine shiftLine = OBProvider.getInstance().get(TA_ShiftLine.class);
			shiftLine.setLineNo(new Long(10));
			shiftLine.setCheckIn(importShift.getCheckIn());
			shiftLine.setCheckOut(importShift.getCheckOut());
			shiftLine.setOff(importShift.isOff());
			shiftLine.setGrayareaAfter(importShift.getGrayAreaAfter());
			shiftLine.setGrayareaBefore(importShift.getGrayAreaBefore());
			shiftLine.setGrayareaCheckin(importShift.getGrayAreaCheckIn());
			shiftLine.setGrayareaCheckout(importShift.getGrayAreaCheckOut());
			shiftLine.setTimemaster(timeMaster);
			
			importShift.setImported(true);
			importShift.setProcessed(true);
			OBDal.getInstance().save(importShift);
			
			recSuccess++;
		}
		//MESSAGE RECORD CREATED SUCCESS
		if(recSuccess > 0 && shiftModeNull == 0 && shiftModeNoRef == 0) {
			final OBError msg = new OBError();
		      msg.setType("Success");
		      msg.setTitle("Created Succes");
		      msg.setMessage(recSuccess + " " + "Record Created Success");
		      bundle.setResult(msg);
		}
		//MESSAGE RECORD CREATED SUCCESS AND FAILED
		else if(recSuccess > 0 && shiftModeNull > 0 || shiftModeNoRef > 0) {
			final OBError msg = new OBError();
		      msg.setType("Success");
		      msg.setTitle("Created Success with warning");
		      msg.setMessage(recSuccess + " " + "Record Created Success, " + shiftModeNull + " " + "Record Created Failed Shift Mode Null, " + shiftModeNoRef + " " + "Record Created Failed Shift Mode No Reference");
		      bundle.setResult(msg);
		}
		//MESSAGE RECORD CREATED FAILED
		else if(recSuccess == 0 && shiftModeNull >= 0 || shiftModeNoRef >= 0) {
			final OBError msg = new OBError();
		      msg.setType("Error");
		      msg.setTitle("Created Failed");
		      msg.setMessage("No One Record Created");
		      bundle.setResult(msg);
		}
		OBDal.getInstance().commitAndClose();
	}

}
