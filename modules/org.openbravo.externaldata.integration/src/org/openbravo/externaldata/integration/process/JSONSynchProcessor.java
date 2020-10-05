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
import java.util.NoSuchElementException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.kernel.ComponentProvider;

/**
 * SynchronousProcessor implementation of JSON Type of Data. This type of data processes the items
 * as JSONObject and the batches of items as JSONArray
 */
@ComponentProvider.Qualifier("JSON")
public class JSONSynchProcessor extends SynchronousProcessor<JSONObject, JSONArray> {

  /**
   * Returns a JSONIterator based on the JSONArray retrieved from the dataProcessor.
   * 
   * @see org.openbravo.externaldata.integration.process.SynchronousProcessor#getItemIterator()
   */
  @Override
  protected Iterator<JSONObject> getItemIterator() {
    JSONArray data = dataProcessor.getDataFromRaw();
    return new JSONIterator(data);
  }

  /**
   * Iterator implementation based on a JSONArray. Each element returned by the next() method is a
   * JSONObject. If some element of the JSONArray is not a JSONObject the next() method throws an
   * Exception.
   */
  private class JSONIterator implements Iterator<JSONObject> {
    private JSONArray array;
    private int length;
    private int position = 0;

    public JSONIterator(JSONArray _array) {
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
}
