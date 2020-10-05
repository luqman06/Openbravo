/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import org.openbravo.externaldata.integration.OBEDLRequest;

/**
 * Class to be extended by custom Storage Location implementations.
 */
public abstract class CustomStorageLocation {

  /**
   * Implements the store operation of the Storage Location.
   * 
   * @param rawData
   *          The Object with the data needed to be stored.
   * @param request
   *          the EDL Request related to the rawData.
   */
  public abstract void saveRawData(Object rawData, OBEDLRequest request);

  /**
   * Implements the load operation of the Storage Location.
   * 
   * @param request
   *          the EDL Request related to the rawData.
   * @return rawData The Object with the data loaded from the storage location.
   */
  public abstract Object loadStoredDataCustom(OBEDLRequest request);

  /**
   * Implements the delete temp data. Method executed on the request reprocess to delete the temp
   * files or data that might have been created in the loadStoredDataCustom() method.
   * 
   * @param request
   *          the EDL Request being executed.
   */
  public abstract void deleteTempData(OBEDLRequest request);

}
