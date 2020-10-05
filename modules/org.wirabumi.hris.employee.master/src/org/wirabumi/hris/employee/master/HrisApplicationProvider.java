package org.wirabumi.hris.employee.master;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.openbravo.client.kernel.BaseComponentProvider;
import org.openbravo.client.kernel.Component;
import org.openbravo.client.kernel.ComponentProvider;

//import org.openbravo.client.kernel.ComponentProvider;

@ApplicationScoped
@ComponentProvider.Qualifier(HrisApplicationProvider.HRISSTATICCOMPONENT)
public class HrisApplicationProvider extends BaseComponentProvider {
  public static final String HRISSTATICCOMPONENT = "HrisStaticComponent";

  @Override
  public List<ComponentResource> getGlobalComponentResources() {
    final List<ComponentResource> globalResources = new ArrayList<ComponentResource>();
    globalResources.add(createStaticResource(
        "web/org.wirabumi.hris.employee.master/js/HrisDocumentUpdater.js", true));
    globalResources.add(createStaticResource(
            "web/org.wirabumi.hris.leave/js/ProcessCutiBersama.js", true));

    globalResources.add(createStaticResource(
            "web/org.wirabumi.hris.employee.master/js/HrisGenerateKpiMeasurement.js", true));
    return globalResources;
  }

  @Override
  public Component getComponent(String componentId, Map<String, Object> parameters) {
    return null;
  }

}