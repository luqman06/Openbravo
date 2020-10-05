/*
 ************************************************************************************
 * Copyright (C) 2016-2017 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import java.io.File;

import javax.enterprise.context.Dependent;

import org.apache.commons.lang.NotImplementedException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.externaldata.integration.OBEDLRequest;

/**
 * Class implemented by each EDL Process to customize how the data is stored and managed.
 *
 * @param <U>
 *          type defined by the Type of Data where batches of items are stored.
 */
@Dependent
public abstract class ProcessDataProcessor<U> {

  protected OBEDLRequest request;

  protected DataProcessor<U> dataProcessor;

  public void init(OBEDLRequest _request, DataProcessor<U> _dataProcessor) {
    dataProcessor = _dataProcessor;
    request = _request;
    doInit();
  }

  /**
   * Method to be overridden by ProcessDataProcessor implementors that requires a custom
   * initialization.
   */
  protected void doInit() {
    // Do nothing.
  }

  /**
   * It received the data as it is set in the addRequest method. This method can be overridden if it
   * is required to transform it by the EDL Process to get the rawData with the items to be
   * processed.
   */
  public Object initializeRawData(Object rawData) {
    return rawData;
  }

  /**
   * Method to be overridden in case the process has some context data shared on all items to be
   * processed.
   */
  public JSONObject loadContextData() {
    return dataProcessor.contextData;
  }

  /**
   * Method to be overridden to return true in case the {@link #writeRawDataToFile()} method is
   * implemented.
   */
  public boolean isWriteRawDataToFileImplemented() {
    return false;
  }

  /**
   * Writes in the attFile parameter the raw data. This method has to be implemented in case the raw
   * data is not an instance of File, String, InputStream, JSONObject or JSONArray and the Storage
   * Location is Attachment.
   * 
   * @param attFile
   *          Empty file where raw data is written.
   */
  public void writeRawDataToFile(File attFile) {
    throw new NotImplementedException();
  }

  /**
   * Method to be overridden to return true in case the {@link #getRawDataAsString()} method is
   * implemented.
   */
  public boolean isGetRawDataAsStringImplemented() {
    return false;
  }

  /**
   * Returns the rawData converted to String. This method has to be implemented in case the initial
   * data is not a String, JSONObject or JSONArray and the Storage Location is Database.
   */
  public String getRawDataAsString() {
    throw new NotImplementedException();
  }

  /**
   * Method that it's called by the TypeOfData implementation of {@link
   * DataProcessor.#getDataFromRaw()}. It returns the received data transformed to a U object with
   * the batch of items. The raw data in the original format can be retrieved from the
   * dataProcessor.
   */
  public abstract U getDataFromRaw();
}
