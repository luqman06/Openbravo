package org.wirabumi.hris.employee.master.Actionbutton;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.hris_case;
import org.wirabumi.hris.employee.master.data.hris_case_punishment;
import org.wirabumi.hris.employee.master.data.hris_case_violation;
import org.wirabumi.hris.employee.master.data.hris_punishment;
import org.wirabumi.hris.employee.master.data.hris_punishment_class;
import org.wirabumi.hris.employee.master.data.hris_violation_library;

public class CreatePunisment extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		// TODO Auto-generated method stub
		try {
			OBCriteria<hris_case> EmployeeCase = OBDal.getInstance().createCriteria(hris_case.class);

			for(hris_case empCase : EmployeeCase.list()){
				OBCriteria<hris_case_violation> caseViol = OBDal.getInstance().createCriteria(hris_case_violation.class);
				caseViol.add(Restrictions.eq(hris_case_violation.PROPERTY_HRISCASE, empCase));
				for(hris_case_violation cViol : caseViol.list()){
					hris_violation_library ViolCatal = cViol.getHrisViolationLibrary();
					hris_punishment_class punishClass = ViolCatal.getPunishmentClassification();
					
					OBCriteria<hris_punishment> Punishment = OBDal.getInstance().createCriteria(hris_punishment.class);
					Punishment.add(Restrictions.eq(hris_punishment.PROPERTY_HRISPUNISHMENTCLASS, punishClass));
					for(hris_punishment punish : Punishment.list()){
						hris_case_punishment casePunish = OBProvider.getInstance().get(hris_case_punishment.class);
						casePunish.setDisciplinaryCategory(punish);
						casePunish.setHrisCase(empCase);
						
						OBDal.getInstance().save(casePunish);
					}
				}
			} 
			OBDal.getInstance().commitAndClose();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			OBDal.getInstance().rollbackAndClose();
		}
	}
}
