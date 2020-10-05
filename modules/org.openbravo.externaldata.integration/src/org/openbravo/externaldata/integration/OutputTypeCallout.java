/*
 ************************************************************************************
 * Copyright (C) 2016-2017 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration;

import javax.servlet.ServletException;

import org.openbravo.base.filter.IsIDFilter;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 * Callout executed when the Output type of a EDL Process is configured.
 */
public class OutputTypeCallout extends SimpleCallout {
  @Override
  protected void execute(CalloutInfo info) throws ServletException {
    String outputTypeId = info.getStringParameter("inpobedlOutputTypeId", IsIDFilter.instance);
    OBEDLOutputType outputType = (OBEDLOutputType) OBDal.getInstance().get(
        OBEDLOutputType.ENTITY_NAME, outputTypeId);
    if (outputType == null) {
      return;
    }
    info.addResult("OutputTypeRequiresUser", outputType.isRequiresUser());
    info.addResult("OutputTypeRequiresPassword", outputType.isRequiresPassword());
    info.addResult("OutputTypeRequiresPath", outputType.isRequiresPath());
    info.addResult("OutputTypeRequiresFilename", outputType.isRequiresFilename());
    info.addResult("OutputTypeRequiresWsmethod", outputType.isRequiresWebServiceMethod());
  }
}
