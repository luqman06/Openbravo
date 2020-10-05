package org.wirabumi.projectbid.event;

import java.util.List;

import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.base.model.Property;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectLine;

public class ProjectBidCompletionEventHandler extends EntityPersistenceEventObserver {
	private static Entity[] entities = { ModelProvider.getInstance().getEntity(Project.ENTITY_NAME) };
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	protected Entity[] getObservedEntities() {
		return entities;
	}
	
	public void onUpdate(@Observes EntityUpdateEvent event) {
		if (!isValidEvent(event)) {
			return;
		}
		
		final Entity projectEntity = ModelProvider.getInstance().getEntity(Project.ENTITY_NAME);
		final Property projectProcessedProperty = projectEntity.getProperty(Project.PROPERTY_PROCESSED);
		boolean isProcessed = (boolean)event.getCurrentState(projectProcessedProperty);
		
		final Property projectLineProperty = projectEntity.getProperty(Project.PROPERTY_PROJECTLINELIST);
		@SuppressWarnings("unchecked")
		List<ProjectLine> projectLineList = (List<ProjectLine>)event.getCurrentState(projectLineProperty);
		
		for (ProjectLine projectLine : projectLineList){
			projectLine.setProcessed(isProcessed);
		}
		
	}

}
