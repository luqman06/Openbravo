package org.wirabumi.hris.payroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openbravo.client.kernel.BaseComponentProvider;
import org.openbravo.client.kernel.Component;

public class PayrollAplicationProvider extends BaseComponentProvider {

  @Override
  public List<ComponentResource> getGlobalComponentResources() {
    final List<ComponentResource> globalResources = new ArrayList<ComponentResource>();
    globalResources.add(createStaticResource(
        "web/org.wirabumi.hris.payroll/js/PayrollSalaryComponent.js", true));
    return globalResources;
  }

  @Override
  public Component getComponent(String componentId, Map<String, Object> parameters) {
    // TODO Auto-generated method stub
    return null;
  }

}
