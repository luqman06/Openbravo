package org.wirabumi.hris.employee.master.ad_callout;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;

public class ResignationEmployeeData extends SimpleCallout {

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    String BussinessPartnerID = info.getStringParameter("inpcBpartnerId", null);

    final BusinessPartner businessPartner = OBDal.getInstance().get(BusinessPartner.class,
        BussinessPartnerID);
    String searchKey = businessPartner.getSearchKey();
    info.addResult("inpvalue", searchKey);

    String employmentType = businessPartner.getHRISEmployementType();
    info.addResult("inpemployementtype", employmentType);
    // String employeCategory = businessPartner.getBLGEmployeeCategory();
    String position = businessPartner.getHRISPosition();
    info.addResult("inpposition", position);
    if (businessPartner.getHrisCostcenter() != null) {
      String costcenter = businessPartner.getHrisCostcenter().getId();
      info.addResult("inpmaCostcenterId", costcenter);
    }
    if (businessPartner.getHrisJobtitle() != null) {
      String jobtitle = businessPartner.getHrisJobtitle().getId();
      info.addResult("inpjobtitle", jobtitle);
    }
    String echelon = businessPartner.getHrisEchelon();
    info.addResult("inpechelon", echelon);
    String level = businessPartner.getHRISLevel();
    info.addResult("inpemployeegrade", level);
    // String golPNS = businessPartner.getBlgGolpns();
    Date joinDate = businessPartner.getHrisJoindate();
    if (joinDate != null) {
      SimpleDateFormat formatDate = new SimpleDateFormat(
          info.vars.getSessionValue("#AD_JavaDateFormat"));
      String formatedDate = formatDate.format(joinDate);

      String yearofservice;
      if (businessPartner.getHrisYear() != null) {
        yearofservice = businessPartner.getHrisYear().toString();
      } else {
        yearofservice = "0";
      }

      String monthofservice;
      if (businessPartner.getHrisMonth() != null) {
        monthofservice = businessPartner.getHrisMonth().toString();
      } else {
        monthofservice = "0";
      }
      info.addResult("inpjoindate", formatedDate);
      info.addResult("inpyearofservice", yearofservice);
      info.addResult("inpmonthofservice", monthofservice);
    }

    // info.addResult("inpemBlgEmployeecategory", employeCategory);
    // info.addResult("inpemBlgGolPns", golPNS);

  }

}

