/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import org.openbravo.base.exception.OBException;
import org.openbravo.externaldata.integration.OBEDLProcess;

/**
 * Defines validations implemented by a EDL Process.
 */
public abstract class CustomValidation {

  /**
   * Validations executed when the addRequest is called before the EDL Request is created. If any of
   * these validations fail no EDL Request is created.
   * 
   * @throws OBExecption
   *           when any of the validations fail.
   */
  protected abstract void doProcessValidations(OBEDLProcess process) throws OBException;

  /**
   * Validations executed when the EDL Request is going to be processed. If any of these validations
   * fail the process is stopped and the EDL Request is updated to Error status.
   * 
   * @throws OBExecption
   *           when any of the validations fail.
   */
  protected abstract void doConfigurationValidations(OBEDLProcess process) throws OBException;

}
