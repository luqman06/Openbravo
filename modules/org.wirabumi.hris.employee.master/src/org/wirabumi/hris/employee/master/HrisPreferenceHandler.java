package org.wirabumi.hris.employee.master;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.Property;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.domain.Preference;
import org.openbravo.model.ad.ui.Window;

public class HrisPreferenceHandler {
  public static void getPreferenceOnUpdate(Entity pObj, EntityUpdateEvent event, Window pWindow,
      String preferenceAttributePrefix, String pAtribut, String pUpdateProperty) {
    OBCriteria<Preference> preference = OBDal.getInstance().createCriteria(Preference.class);
    preference.add(Restrictions.eq(Preference.PROPERTY_WINDOW, pWindow));
    preference.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, preferenceAttributePrefix.concat(pAtribut)));

    List<Preference> listPreference = preference.list();
    if (listPreference.size() > 0) {
      Preference preferenceObj = listPreference.get(0);
      String value = preferenceObj.getSearchKey();
      final Property objSet = pObj.getProperty(pUpdateProperty);
      event.setCurrentState(objSet, value);

    }
  }

  public static void getPreferenceOnsave(Entity pObj, EntityNewEvent event, Window pWindow,
      String pKey, String pAtribut, String pUpdateProperty) {
    OBCriteria<Preference> preference = OBDal.getInstance().createCriteria(Preference.class);
    preference.add(Restrictions.eq(Preference.PROPERTY_WINDOW, pWindow));
    preference.add(Restrictions.eq(Preference.PROPERTY_ATTRIBUTE, pKey.concat(pAtribut)));

    List<Preference> listPreference = preference.list();
    if (listPreference.size() > 0) {
      Preference preferenceObj = listPreference.get(0);
      String value = preferenceObj.getSearchKey();
      final Property objSet = pObj.getProperty(pUpdateProperty);
      event.setCurrentState(objSet, value);

    }
  }

}
