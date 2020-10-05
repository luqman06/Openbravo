package org.wirabumi.hris.employee.master.ad_callout;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;

public class TransferEmployeeData extends SimpleCallout {

  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    String BussinessPartnerID = info.getStringParameter("inpcBpartnerId", null);

    final BusinessPartner businessPartner = OBDal.getInstance().get(BusinessPartner.class,
        BussinessPartnerID);
    String searchKey = businessPartner.getSearchKey();
    info.addResult("inpvalue", searchKey);

    String employmentType = businessPartner.getHRISEmployementType();
    if (employmentType != null) {
      info.addResult("inpemployementtype", employmentType);
    } else {
      info.addResult("inpemployementtype", "");
    }
    String position = businessPartner.getHRISPosition();
    if (position != null) {
      info.addResult("inpfromposition", position);
      info.addResult("inptopositioned", position);
    } else {
      info.addResult("inpfrompositioned", "");
      info.addResult("inptopositioned", "");
    }
    if (businessPartner.getHrisCostcenter() != null) {
      String costcenter = businessPartner.getHrisCostcenter().getId();
      if (costcenter != null) {
        info.addResult("inpfrommaCostcenterId", costcenter);
        info.addResult("inptomaCostcenterId", costcenter);
      } else {
        info.addResult("inpfrommaCostcenterId", "");
        info.addResult("inptomaCostcenterId", "");
      }
    }
    if (businessPartner.getHrisJobtitle() != null) {
      String jobtitle = businessPartner.getHrisJobtitle().getId();
      if (jobtitle != null) {
        info.addResult("inpfromhrisJobtitleId", jobtitle);
        info.addResult("inptohrisJobtitleId", jobtitle);
      } else {
        info.addResult("inpfromhrisJobtitleId", "");
        info.addResult("inptohrisJobtitleId", "");
      }
    }
    String echelon = businessPartner.getHrisEchelon();
    if (echelon != null) {
      info.addResult("inpfromechelon", echelon);
      info.addResult("inptoechelon", echelon);
    } else {
      info.addResult("inpfromechelon", "");
      info.addResult("inptoechelon", "");

    }
    String level = businessPartner.getHRISLevel();
    if (level != null) {
      info.addResult("inpfromlevel", level);
    }
    if (businessPartner.getOrganization() != null) {
      String organization = businessPartner.getOrganization().getId();
      if (organization != null) {
        info.addResult("inptoadOrgId", organization);
        info.addResult("inpfromadOrgId", organization);
      } else {
        info.addResult("inptoadOrgId", "");
        info.addResult("inpfromadOrgId", "");
      }
    }
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

      String masakerja = yearofservice.concat("-tahun ").concat(monthofservice).concat("-bulan");
      if (formatDate != null) {
        info.addResult("inpjoindate", formatedDate);
      } else {
        info.addResult("inpjoindate", "");
      }
      if (yearofservice != null) {
        info.addResult("inptenure", yearofservice);
      } else {
        info.addResult("inptenure", "");
      }

    }

    // info.addResult("inpnip", nip);

    // String employeCategory = businessPartner.getBLGEmployeeCategory();
    // info.addResult("inpemBlgEmployeecategory", employeCategory);
    // info.addResult("inpemBlgFromGolPns", golPNS);

    // defult to=======================

    // info.addResult("inpyearofservice", yearofservice);
    // info.addResult("inpmonthofservice", monthofservice);
    // String golPNS = businessPartner.getBlgGolpns();
    // String nip = businessPartner.getBlgNip();
  }

}
