/*
 ************************************************************************************
 * Copyright (C) 2016-2017 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import java.io.Writer;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.OBEDLRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement by any Type of Data available to be executed synchronously. Each implementor
 * has to define the {@link #getItemIterator()}
 *
 * @param <T>
 *          determines the class each item to be processed is defined as. e.g. JSON type of data
 *          requires each item to be a JSONObject.
 * @param <U>
 *          determines the class a batch of items is defined as. e.g. JSON type of data requires
 *          that batches of items are send as a JSONArray
 */
public abstract class SynchronousProcessor<T, U> {
  private static final String UNEXPECTED_ERROR_KEY = "obedl_unexpected_error";
  private static final String ITERATOR_ERROR_KEY = "obedl_iterator_error";
  private static final Logger log = LoggerFactory.getLogger(SynchronousProcessor.class);

  protected OBEDLRequest request;
  protected boolean hasInput;
  protected DataProcessor<U> dataProcessor;
  protected ItemProcessor<T> itemProcessor;

  /**
   * Method to initialize the fields with the instances used in this {@link ProcessRequest}
   */
  public void init(OBEDLRequest _request, DataProcessor<U> _dataProcessor,
      ItemProcessor<T> _itemProcessor) {
    request = _request;
    hasInput = request.getEDLProcess().isHasInput();
    dataProcessor = _dataProcessor;
    itemProcessor = _itemProcessor;
  }

  /**
   * Method that processes the data.
   */
  public void processData() throws OBException {
    if (hasInput) {
      processInputData();
    } else {
      processNoInput();
    }
  }

  /**
   * Method that processes the data. Iterates the item batch and calling {@link
   * ItemProcessor.#processItem(T)} on each item.
   * 
   * @throws Exception
   */
  private void processInputData() throws OBException {
    Iterator<T> itemIter = getItemIterator();
    try {
      while (itemIter.hasNext()) {
        T item = itemIter.next();
        processItem(item);
      }
    } catch (NoSuchElementException e) {
      log.error("Error iterating batches", e);
      throw new OBException(OBMessageUtils.messageBD(ITERATOR_ERROR_KEY), e);
    }
  }

  /**
   * Used by EDL Processes that has no input. It calls the {@link
   * ItemProcessor.#processItem(Object)} method with a null item.
   */
  private void processNoInput() {
    processItem(null);
  }

  /**
   * Processes the Item by calling the {@link ItemProcessor.#processItem(Object)} method
   * 
   * @param item
   *          The item to be processed.
   */
  private void processItem(T item) {
    try {
      itemProcessor.processItem(item);
    } catch (OBException e) {
      OBDal.getInstance().rollbackAndClose();
      throw e;
    } catch (Exception e) {
      OBDal.getInstance().rollbackAndClose();
      throw new OBException(OBMessageUtils.messageBD(UNEXPECTED_ERROR_KEY), e);
    }
  }

  /**
   * Method that gets the data to be processed and returns a item Iterator. The batch of items can
   * be retrieved from {@link DataProcessor.#getDataFromRaw()} method.
   * 
   * @return a Iterator of items to process.
   */
  protected abstract Iterator<T> getItemIterator();

  /**
   * When the process has direct response this method is called. Any "Type of Data" implementation
   * can override it to customize the direct response. By default it calls the {@link
   * ItemProcessor.#writeDirectResponse(Writer)} implemented by each EDL Process.
   */
  public void writeDirectResponse(Writer responseWriter) throws OBException {
    itemProcessor.writeDirectResponse(responseWriter);
  }

}
