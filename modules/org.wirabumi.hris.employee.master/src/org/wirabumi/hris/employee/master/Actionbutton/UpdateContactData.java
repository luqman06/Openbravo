package org.wirabumi.hris.employee.master.Actionbutton;

import java.util.Date;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.hris_change_family;
import org.wirabumi.hris.employee.master.data.hris_contact;

public class UpdateContactData extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		// TODO Auto-generated method stub
		try {
			OBCriteria<hris_change_family> hrisChangeFamily = OBDal.getInstance().createCriteria(hris_change_family.class);
			hrisChangeFamily.add(Restrictions.eq(hris_change_family.PROPERTY_DOCUMENTSTATUS, "AP"));
			
			for(hris_change_family changeFamily : hrisChangeFamily.list()){
				 String noAkta = changeFamily.getNoakta();
				 Date datemMarried = changeFamily.getDateMarried();
				 String maritalStatus = changeFamily.getMaritalStatus();
				 Date dateLetter = changeFamily.getDateAkta();
				 String statusDead = changeFamily.getStatusdead();
				 String educationStatus = changeFamily.getEducationStatus();
				 hris_contact contactID = changeFamily.getHrisContact();

				 //set contact
				 contactID.setMaritalStatus(maritalStatus);
				 contactID.setStatusdead(statusDead);
				 contactID.setEducationStatus(educationStatus);
				 contactID.setNoAkta(noAkta);
				 contactID.setDateLetter(dateLetter);
				 contactID.setDateMarried(datemMarried);
				 
				 String taxMaritalStatus = changeFamily.getTaxmaritalstatusNew();
				 BusinessPartner bpId = changeFamily.getEmployee(); 
				 //update tax maritas status harusnya masuk hris id
				 //bpId.setPphTaxmaritalstatus(taxMaritalStatus);
				 
				 OBDal.getInstance().save(contactID);
				 OBDal.getInstance().save(bpId);
			}
			OBDal.getInstance().commitAndClose();
		} catch (Exception e) {
			// TODO: handle exception
			OBDal.getInstance().rollbackAndClose();
			e.printStackTrace();
		}
	}
}
