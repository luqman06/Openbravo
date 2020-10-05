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
import org.wirabumi.hris.payroll.pyr_incidental_deduction;
import org.wirabumi.hris.payroll.pyr_inc_i_deduction;
import org.wirabumi.hris.payroll.pyr_deduction;

public class ImportInsidentilDeduction extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {

		//GET RECORD IMPORT INCIDENTAL DEDUCTION
		OBCriteria<pyr_inc_i_deduction> criteria = OBDal.getInstance().createCriteria(pyr_inc_i_deduction.class);
		criteria.add(Restrictions.eq(pyr_inc_i_deduction.PROPERTY_PROCESSED, false));
		criteria.add(Restrictions.eq(pyr_inc_i_deduction.PROPERTY_PROCESSIMPORTCOMPLETE, false));
		if(criteria.list().size() > 0) {
			createInsidentilEarning(criteria, bundle);
		} 
		
	}

	//METHOD CREATE INCIDENTAL DEDUCTION RECORD TO TABLE
	private void createInsidentilEarning(OBCriteria<pyr_inc_i_deduction> criteria, ProcessBundle bundle) {
		BusinessPartner businessPartner = null;
		pyr_deduction deduction = null;
		int created = 0;

		//LOOPING FOR ANY RECORD IMPORT INCIDENTAL DEDUCTION
		for(pyr_inc_i_deduction incDeduction : criteria.list()) {
			
			//GET BUSINESS PARTNER
			OBCriteria<BusinessPartner> criteria1 = OBDal.getInstance().createCriteria(BusinessPartner.class);
			criteria1.add(Restrictions.eq(BusinessPartner.PROPERTY_SEARCHKEY, incDeduction.getEmployeeID()));
			if(criteria1.list().size() > 0) {
				businessPartner = criteria1.list().get(0);
			}
			
			//GET DEDUTION MASTER
			OBCriteria<pyr_deduction> criteria2 = OBDal.getInstance().createCriteria(pyr_deduction.class);
			criteria2.add(Restrictions.eq(pyr_deduction.PROPERTY_SEARCHKEY, incDeduction.getDeductionKey()));
			if(criteria2.list().size() > 0) {
				deduction = criteria2.list().get(0);
			}
			
			//SET INCIDENTAL DEDUCTION
			pyr_incidental_deduction setIncDeduction = OBProvider.getInstance().get(pyr_incidental_deduction.class);
			setIncDeduction.setAmount(incDeduction.getAmount());
			setIncDeduction.setDate(incDeduction.getDate());
			setIncDeduction.setDeduction(deduction);
			setIncDeduction.setEmployee(businessPartner);
			OBDal.getInstance().save(setIncDeduction);
			
			incDeduction.setProcessed(true);
			incDeduction.setProcessImportComplete(true);
			OBDal.getInstance().save(incDeduction);

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


