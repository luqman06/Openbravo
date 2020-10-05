package org.wirabumi.hris.employee.master;

import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Window;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;

public class HrisBusinessEventHandler extends EntityPersistenceEventObserver {
  private static Entity[] entities = { ModelProvider.getInstance().getEntity(
      hris_ec_lines.ENTITY_NAME) };
  protected Logger logger = Logger.getLogger(this.getClass());

  @Override
  protected Entity[] getObservedEntities() {
    return entities;
  }

  public void onUpdate(@Observes EntityUpdateEvent event) {
    if (!isValidEvent(event)) {
      return;
    }
    final Entity candidateEntity = ModelProvider.getInstance().getEntity(hris_ec_lines.ENTITY_NAME);
    final hris_ec_lines candidate = (hris_ec_lines) event.getTargetInstance();
    final String DocStatus = candidate.getDocumentStatus();
    Window window = OBDal.getInstance().get(Window.class, "318A38AFC4A3420FAA6317F852BB5474");
    HrisPreferenceHandler.getPreferenceOnUpdate(candidateEntity, event, window, "ET#", DocStatus,
        "employementtype");
    HrisPreferenceHandler.getPreferenceOnUpdate(candidateEntity, event, window, "CT#", DocStatus,
        "contractype");
  }

  public void onSave(@Observes EntityNewEvent event) {
    if (!isValidEvent(event)) {
      return;
    }

    final Entity candidateEntity = ModelProvider.getInstance().getEntity(hris_ec_lines.ENTITY_NAME);
    final hris_ec_lines candidate = (hris_ec_lines) event.getTargetInstance();
    final String DocStatus = candidate.getDocumentStatus();
    Window window = OBDal.getInstance().get(Window.class, "318A38AFC4A3420FAA6317F852BB5474");

    HrisPreferenceHandler.getPreferenceOnsave(candidateEntity, event, window, "ET#", DocStatus,
        "employementtype");
    HrisPreferenceHandler.getPreferenceOnsave(candidateEntity, event, window, "CT#", DocStatus,
        "contractype");
  }

}
