/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.kernel.ComponentProvider;
import org.openbravo.erpCommon.utility.OBMessageUtils;

/**
 * AsynchronousProcessor implementation of JSON Type of Data. This type of data processes the items
 * as JSONObject and the batches of items as JSONArray
 */
@ComponentProvider.Qualifier("JSON")
public class JSONAsynchProcessor extends AsynchronousProcessor<JSONObject, JSONArray> {
  /**
   * Retrieves the items JSONArray from {@link DataProcessor.#getDataFromRaw()} and returns a
   * JSONBatcherIterator
   * 
   * @see org.openbravo.externaldata.integration.process.AsynchronousProcessor#getDataBatcher()
   */
  @Override
  protected Iterator<String> getDataBatcher() {
    JSONArray data = dataProcessor.getDataFromRaw();
    return new JSONBatcherIterator(data, recordSize);
  }

  /**
   * Converts a List<JSONObject> of items into a JSONArray and returns it as a String.
   * 
   * @see org.openbravo.externaldata.integration.process.AsynchronousProcessor#getBatchFromList(java.util.List)
   */
  @Override
  protected String getBatchFromList(List<JSONObject> items) {
    JSONArray errorArray = new JSONArray();
    for (JSONObject item : items) {
      errorArray.put(item);
    }
    return errorArray.toString();
  }

  /**
   * Loads the batch as a String from the requestLine and converts it to JSONArray. It returns a
   * JSONObject iterator based on the batch JSONArray.
   * 
   * @see org.openbravo.externaldata.integration.process.AsynchronousProcessor#getItemIterator()
   */
  @Override
  protected Iterator<JSONObject> getItemIterator() throws OBException {
    String batch = requestLine.getLinedata();
    JSONArray itemArray;
    try {
      itemArray = new JSONArray(batch);
      return new JSONObjectIterator(itemArray);
    } catch (JSONException e) {
      throw new OBException(OBMessageUtils.messageBD("obedl_json_asynch_batch_error"), e);
    }
  }

  /**
   * Iterator implementation based on a JSONArray. Each element returned by the next() method is a
   * JSONObject. If some element of the JSONArray is not a JSONObject the next() method throws an
   * Exception.
   */
  private class JSONObjectIterator implements Iterator<JSONObject> {
    private JSONArray array;
    private int length;
    private int position = 0;

    public JSONObjectIterator(JSONArray _array) {
      this.array = _array;
      length = array.length();
    }

    @Override
    public boolean hasNext() {
      return (position < length);
    }

    @Override
    public JSONObject next() {
      if (position == length) {
        throw new NoSuchElementException();
      }
      return array.optJSONObject(position++);
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * Iterator implementation based on a JSONArray. Each element returned by the next() method is a
   * JSONArray of the batchSize length. If some element of the JSONArray is not a JSONObject the
   * next() method throws an Exception.
   */
  private class JSONBatcherIterator implements Iterator<String> {
    private JSONArray data;
    private int length;
    private int batchSize;
    private int position = 0;

    public JSONBatcherIterator(JSONArray _data, int batchSize) {
      this.data = _data;
      this.batchSize = batchSize;

      length = data.length();
    }

    @Override
    public boolean hasNext() {
      return position < length;
    }

    @Override
    public String next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      JSONArray currentBatch = new JSONArray();
      int partialLength = position + batchSize;
      while (position < partialLength && position < length) {
        currentBatch.put(data.optJSONObject(position++));
      }
      return currentBatch.toString();
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
}
