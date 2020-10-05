package org.wirabumi.hris.employee.master.ad_process;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.hris_dicipline;
import org.wirabumi.hris.employee.master.data.hris_education_exam;
import org.wirabumi.hris.employee.master.data.hris_education_permit;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;

public class UjianPenyesuaianIjazah extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    // TODO Auto-generated method stub

    try {
      OBCriteria<hris_education_permit> Education_Permit = OBDal.getInstance().createCriteria(
          hris_education_permit.class);

      Education_Permit.add(Restrictions.isNotNull(hris_education_permit.PROPERTY_CERTIFICATENO));

      List<hris_education_permit> EducationPermit = Education_Permit.list();
      for (hris_education_permit EP : EducationPermit) {
        BusinessPartner BusinessPartner = EP.getBusinessPartner();
        String Position = EP.getPosition();
        CostCenter CostCenter = EP.getCostCenter();
        hris_jobtitle Jobtitle = EP.getJobTitle();
        String Grade = EP.getGrade();
        String Echelon = EP.getEchelon();
        String EmployementType = EP.getEmploymentType();
        String Value = EP.getEmployeeID();
        String DocStatus = EP.getDocumentStatus();
        String CertificateNo = EP.getCertificateno();
        String InstitutionName = EP.getInstitutionname();
        // String EducationLevel = EP.getEducationlevel();
        hris_dicipline Dicipline = EP.getEducationDicipline();
        Date CertificateDate = EP.getCertificateDate();
       

        hris_education_exam EducationExam = OBProvider.getInstance().get(hris_education_exam.class);
        EducationExam.setBusinessPartner(BusinessPartner);
        EducationExam.setPosition(Position);
        EducationExam.setCostCenter(CostCenter);
        EducationExam.setJobTitle(Jobtitle);
        EducationExam.setGrade(Grade);
        EducationExam.setEchelon(Echelon);
        EducationExam.setEmploymenttype(EmployementType);
        EducationExam.setEmployeeID(Value);
        EducationExam.setDocumentStatus(DocStatus);
        EducationExam.setCertificateno(CertificateNo);
        EducationExam.setInstitutionname(InstitutionName);
        // EducationExam.setEducationlevel(EducationLevel);
        EducationExam.setEducationDicipline(Dicipline);
        // EducationExam.setFaculty(EducationLevel);
        EducationExam.setCertificateDate(CertificateDate);
        EducationExam.setRemark(EP.getRemark());
        EducationExam.setHrisEducationPermit(EP);

        OBDal.getInstance().save(EducationExam);
      }
      OBDal.getInstance().commitAndClose();
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

}
