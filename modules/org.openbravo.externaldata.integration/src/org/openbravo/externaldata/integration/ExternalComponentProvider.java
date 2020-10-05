/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */

package org.openbravo.externaldata.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.openbravo.client.kernel.BaseComponentProvider;
import org.openbravo.client.kernel.Component;
import org.openbravo.client.kernel.ComponentProvider;

/**
 * Class to load static resources added by the module.
 */
@ApplicationScoped
@ComponentProvider.Qualifier(ExternalComponentProvider.QUALIFIER)
public class ExternalComponentProvider extends BaseComponentProvider {

  public static final String QUALIFIER = "OBEDL";

  @Override
  public Component getComponent(String componentId, Map<String, Object> parameters) {
    throw new IllegalArgumentException("Component id " + componentId + " not supported.");
  }

  @Override
  public List<ComponentResource> getGlobalComponentResources() {
    final List<ComponentResource> globalResources = new ArrayList<ComponentResource>();
    globalResources.add(createStaticResource(
        "web/org.openbravo.externaldata.integration/js/cancelRequests.js", false));
    globalResources.add(createStaticResource(
        "web/org.openbravo.externaldata.integration/js/reprocessRequestLine.js", false));

    return globalResources;
  }
}
