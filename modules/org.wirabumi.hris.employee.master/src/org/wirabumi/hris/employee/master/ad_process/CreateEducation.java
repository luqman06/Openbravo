package org.wirabumi.hris.employee.master.ad_process;

import java.math.BigDecimal;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_education;
import org.wirabumi.hris.employee.master.data.hris_education_exam;

public class CreateEducation extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		// TODO Auto-generated method stub
		//System.out.print("xx");
		//select hris_education_exam where isgenerated <> 'Y' and docstatus = "AP"
		try {
			
		
		final OBCriteria<hris_education_exam> eduexam = OBDal.getInstance().createCriteria(hris_education_exam.class);
			eduexam.add(Restrictions.eq(hris_education_exam.PROPERTY_DOCUMENTSTATUS, "AP"));
			eduexam.add(Restrictions.eq(hris_education_exam.PROPERTY_HRISISGENERATED, false));
	      //productList.add(Restrictions.eq(Product.PROPERTY_SALE, true));
			BigDecimal gradee;
			for (hris_education_exam ee : eduexam.list()) {
	            HRIS_C_Bp_education hrisedu = OBProvider.getInstance().get(HRIS_C_Bp_education.class);
	            hrisedu.setLevel(ee.getEducationlevel());
	            hrisedu.setBusinessPartner(ee.getBusinessPartner());
	            hrisedu.setDicipline(ee.getEducationDicipline());
	            hrisedu.setInstitutionName(ee.getInstitutionname());
	            gradee = ee.getRemark();
	            hrisedu.setGrade( gradee);
	            hrisedu.setGradeNote(ee.getGrade());
	            hrisedu.setValidFromDate(ee.getCertificateDate());
	            hrisedu.setGraduate(true);
	            OBDal.getInstance().save(hrisedu);
	            ee.setHrisIsgenerated(true);
	            OBDal.getInstance().save(ee);
	            //hrisedu.setLifetimeRevenueQty(totalqty.longValue());
	           // hrisedu.setBusinessPartner(bp);
	            //hrisedu.setProduct(product);
	            
			}
			OBDal.getInstance().commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
