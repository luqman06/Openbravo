package org.wirabumi.hris.employee.master.Actionbutton;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.geography.Location;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.hris_contact;
import org.wirabumi.hris.employee.master.data.hris_pengdata_kel;

public class CreateContactData extends DalBaseProcess {

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    ConnectionProvider conectionProvider = bundle.getConnection();
    VariablesSecureApp vars = bundle.getContext().toVars();
    try {
      int processedData = 0;

      OBCriteria<hris_pengdata_kel> getValidData = OBDal.getInstance().createCriteria(
          hris_pengdata_kel.class);
      getValidData.add(Restrictions.isNull(hris_pengdata_kel.PROPERTY_HRISCONTACTFK));
      getValidData.add(Restrictions.eq(hris_pengdata_kel.PROPERTY_DOCUMENTSTATUS, "AP"));
      getValidData.add(Restrictions.isNotNull(hris_pengdata_kel.PROPERTY_COMMERCIALNAME));

      List<hris_pengdata_kel> listData = getValidData.list();

      if (listData.size() <= 0) {
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            listData.size() + " @HRIS_DataMatch@");
        final OBError msg = new OBError();
        msg.setType("Info");
        msg.setTitle("Processed Data");
        msg.setMessage(message);
        bundle.setResult(msg);
      } else {
        for (hris_pengdata_kel contactData : listData) {
          // get fron peng data kel
          BusinessPartner businessPartner = contactData.getBusinessPartner();
          String contactName = contactData.getCommercialName();
          Location location = contactData.getLocationAddress();
          Date birrthday = contactData.getBirthday();
          String relation = contactData.getRelation();
          String phone = contactData.getPhone();
          String alternatifPhone = contactData.getAlternativePhone();
          boolean insured = contactData.isInsured();
          boolean emergency = contactData.isEmergencyContact();
          String maritalStatus = contactData.getMaritalStatus();

          hris_contact newContact = OBProvider.getInstance().get(hris_contact.class);
          // set contact data
          newContact.setEmployee(businessPartner);
          newContact.setName(contactName);
          newContact.setLocationAddress(location);
          newContact.setBirthday(birrthday);
          newContact.setRelation(relation);
          newContact.setPhone(phone);
          newContact.setAlternativePhone(alternatifPhone);
          newContact.setMaritalStatus(maritalStatus);
          newContact.setInsured(insured);
          newContact.setEmergencyContact(emergency);

          contactData.setHrisContactFk(newContact);
          processedData++;
          OBDal.getInstance().save(newContact);
          OBDal.getInstance().save(contactData);
        }
        OBDal.getInstance().commitAndClose();
        String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
            processedData + " @Hris_DataCreated@");
        final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage(message);
        bundle.setResult(msg);
      }
    } catch (Exception e) {
      String message = Utility.parseTranslation(conectionProvider, vars, vars.getLanguage(),
          " @Hris_DataFailed@");
      final OBError msg = new OBError();
      msg.setType("Error");
      msg.setTitle("Failed");
      msg.setMessage(message);
      bundle.setResult(msg);
    }

  }

}
