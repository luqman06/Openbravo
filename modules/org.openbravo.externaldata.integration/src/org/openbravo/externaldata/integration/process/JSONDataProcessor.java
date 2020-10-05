/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import java.io.File;
import java.io.IOException;

import javax.enterprise.context.Dependent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.NotImplementedException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.kernel.ComponentProvider;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DataProcessor implementation of JSON Type of Data. This type of data converts the rawData to a
 * JSONArray of JSONObjects.
 */
@Dependent
@ComponentProvider.Qualifier("JSON")
public class JSONDataProcessor extends DataProcessor<JSONArray> {
  private static final Logger log = LoggerFactory.getLogger(JSONDataProcessor.class);
  private static final String ERROR_READING_FILE = "OBUIAPP_ErrorWiththeFile";

  /**
   * It converts the rawData to a JSONArray. In case the EDL Process has its own implementation that
   * implementation is executed. If the rawData is not an instance of JSONArray, JSONObject or a
   * String that can be parsed to a JSON then a NotImplementedException is thrown.
   * 
   * @return the data as JSONArray
   */
  public JSONArray getDataFromRaw() {
    if (processDataProcessor != null) {
      return processDataProcessor.getDataFromRaw();
    } else if (rawData != null) {
      if (rawData instanceof JSONArray) {
        return (JSONArray) rawData;
      } else if (rawData instanceof JSONObject) {
        JSONArray array = new JSONArray();
        array.put(rawData);
        return array;
      } else if (rawData instanceof String || rawData instanceof File) {
        String data;
        if (rawData instanceof File) {
          try {
            data = FileUtils.readFileToString((File) rawData);
          } catch (IOException e) {
            log.error("Error reading file" + e.getMessage(), e);
            throw new OBException(OBMessageUtils.messageBD(ERROR_READING_FILE), e);
          }
        } else {
          data = (String) rawData;
        }
        try {
          if (data.startsWith("{")) {
            JSONObject item = new JSONObject(data);
            JSONArray array = new JSONArray();
            array.put(item);
            return array;
          } else if (data.startsWith("[")) {
            JSONArray array = new JSONArray(data);
            return array;
          }
        } catch (JSONException e) {
          log.error("Unable to automatically parse the data string to JSON");
          // Cannot parse string to JSON throw as NotImplementedException
        }
      }
    } else {
      log.error("Unable to load data. Received rawData is null");
      throw new NotImplementedException();
    }
    log.error("Unable to load data from raw");
    log.error("rawData instanceof {}", rawData.getClass());
    log.error("Content of rawData {}", rawData);
    throw new NotImplementedException();
  }

}
