package org.wirabumi.hris.payroll.ad_process;

import java.math.BigDecimal;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.EmployeeSalaryCategory;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.payroll.pyr_incidental_earning;
import org.wirabumi.hris.payroll.pyr_inc_i_earning;
import org.wirabumi.hris.payroll.pyr_earning;

public class ImportInsidentilEarning extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		//GET RECORD IMPORT INCIDENTAL EARNING
		OBCriteria<pyr_inc_i_earning> criteria = OBDal.getInstance().createCriteria(pyr_inc_i_earning.class);
		criteria.add(Restrictions.eq(pyr_inc_i_earning.PROPERTY_PROCESSED, false));
		criteria.add(Restrictions.eq(pyr_inc_i_earning.PROPERTY_PROCESSIMPORTCOMPLETE, false));
		if(criteria.list().size() > 0) {
			createInsidentilEarning(criteria, bundle);
		} 
		
	}

	//METHOD CREATE INCIDENTAL EARNING RECORD TO TABLE
	private void createInsidentilEarning(OBCriteria<pyr_inc_i_earning> criteria, ProcessBundle bundle) {
		BusinessPartner businessPartner = null;
		pyr_earning earning = null;
		int created = 0;

		//LOOPING FOR ANY RECORD IMPORT INCIDENTAL DEDUCTION
		for(pyr_inc_i_earning incEarning : criteria.list()) {

			//GET BUSINESS PARTNER
			OBCriteria<BusinessPartner> criteria1 = OBDal.getInstance().createCriteria(BusinessPartner.class);
			criteria1.add(Restrictions.eq(BusinessPartner.PROPERTY_SEARCHKEY, incEarning.getEmployeeID()));
			if(criteria1.list().size() > 0) {
				businessPartner = criteria1.list().get(0);
			}
			
			//GET DEDUTION MASTER
			OBCriteria<pyr_earning> criteria2 = OBDal.getInstance().createCriteria(pyr_earning.class);
			criteria2.add(Restrictions.eq(pyr_earning.PROPERTY_SEARCHKEY, incEarning.getEarningKey()));
			if(criteria2.list().size() > 0) {
				earning = criteria2.list().get(0);
			}
			
			//SET INCIDENTAL DEDUCTION
			pyr_incidental_earning setIncEarning = OBProvider.getInstance().get(pyr_incidental_earning.class);
			setIncEarning.setAmount(incEarning.getAmount());
			setIncEarning.setDate(incEarning.getDate());
			setIncEarning.setEarning(earning);
			setIncEarning.setEmployee(businessPartner);
			OBDal.getInstance().save(setIncEarning);
			
			incEarning.setProcessed(true);
			incEarning.setProcessImportComplete(true);
			OBDal.getInstance().save(incEarning);

			created++;
		}
		final OBError msg = new OBError();
	      msg.setType("Success");
	      msg.setTitle("Created Succes");
	      msg.setMessage(created + " " + "Record Created Success");
	      bundle.setResult(msg);
		OBDal.getInstance().commitAndClose();
		
	}

}
