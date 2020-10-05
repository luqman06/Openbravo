package org.wirabumi.hris.employee.master.Actionbutton;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_License;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Training;
import org.wirabumi.hris.employee.master.data.hris_certificate;
import org.wirabumi.hris.employee.master.data.hris_tp_employee;
import org.wirabumi.hris.employee.master.data.hris_training;
import org.wirabumi.hris.employee.master.data.hris_training_calendar;
import org.wirabumi.hris.employee.master.data.hris_training_plan;

public class CreateHistoryTraining extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		// TODO Auto-generated method stub
		try {
			String ID = (String) bundle.getParams().get("Hris_Training_Calendar_ID");
			hris_training_calendar calendarID = OBDal.getInstance().get(hris_training_calendar.class, ID);
			OBCriteria<hris_tp_employee> tpEmployee = OBDal.getInstance().createCriteria(hris_tp_employee.class);
			tpEmployee.add(Restrictions.eq(hris_tp_employee.PROPERTY_DOCUMENTSTATUS, "CO"));
			tpEmployee.add(Restrictions.eq(hris_tp_employee.PROPERTY_HRISTRAININGCALENDAR, calendarID));
			
			int hasil = tpEmployee.list().size();
			for(hris_tp_employee tpE : tpEmployee.list()){
				
				BusinessPartner bp = tpE.getEmployee();
				Date startDate = calendarID.getStartingDate();
				Date endDate = calendarID.getEndingdate();
				String remark = tpE.getRemark();
				String description = tpE.getDescription();
				hris_training trainingID = calendarID.getTraining();
				hris_certificate certificateID = tpE.getHrisCertificate();
				
				OBCriteria<HRIS_C_Bp_Training> HRIStraining = OBDal.getInstance().createCriteria(HRIS_C_Bp_Training.class);
				HRIStraining.add(Restrictions.eq(HRIS_C_Bp_Training.PROPERTY_TRAINING, trainingID));
				
				if(HRIStraining.list().size() <= 0){
					HRIS_C_Bp_Training cBpTraining = OBProvider.getInstance().get(HRIS_C_Bp_Training.class);
					cBpTraining.setEmployee(bp);
					cBpTraining.setStartingDate(startDate);
					cBpTraining.setEndingDate(endDate);
					cBpTraining.setTraining(trainingID); 
					cBpTraining.setRemark(remark);
					cBpTraining.setComments(description);
					
					OBDal.getInstance().save(cBpTraining);
				}
				
				OBCriteria<HRIS_C_Bp_License> license = OBDal.getInstance().createCriteria(HRIS_C_Bp_License.class);
				license.add(Restrictions.eq(HRIS_C_Bp_License.PROPERTY_CERTIFICATE, certificateID));
				if(license.list().size() <= 0){
					HRIS_C_Bp_License cBpLisensi = OBProvider.getInstance().get(HRIS_C_Bp_License.class);
					cBpLisensi.setEmployee(bp);
					cBpLisensi.setStartingDate(startDate);
					cBpLisensi.setEndingDate(endDate);
					cBpLisensi.setCertificate(certificateID);
					cBpLisensi.setRemark(remark);
					cBpLisensi.setComment(description);
					
					OBDal.getInstance().save(cBpLisensi);
				}
			}
			OBDal.getInstance().commitAndClose();
		} catch (Exception e) {
			OBDal.getInstance().rollbackAndClose();
			e.printStackTrace();
		}
	}
}
