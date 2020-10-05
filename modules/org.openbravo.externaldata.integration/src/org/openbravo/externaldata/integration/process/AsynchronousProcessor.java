/*
 ************************************************************************************
 * Copyright (C) 2016-2017 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.HibernateException;
import org.hibernate.ScrollableResults;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.base.model.Property;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.weld.WeldUtils;
import org.openbravo.dal.core.DalUtil;
import org.openbravo.dal.core.SessionHandler;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.OBEDLProcess;
import org.openbravo.externaldata.integration.OBEDLRequest;
import org.openbravo.externaldata.integration.OBEDLRequestLine;
import org.openbravo.service.db.DbUtility;
import org.openbravo.service.importprocess.ImportEntry;
import org.openbravo.service.importprocess.ImportEntryArchive;
import org.openbravo.service.importprocess.ImportEntryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement by any Type of Data available to be executed asynchronously. Each implementor
 * has to define the {@link #getDataBatcher()}, {@link #getBatchFromList()} and
 * {@link #getItemIterator()} abstract methods.
 * 
 * The items to be processed are grouped in batches of the type defined by the Type of Data. The
 * size of the batch is defined by the EDL Process. Each batch is stored as a String in a Request
 * Line that it is processed asynchronously. When the request line is processed the batch has to be
 * iterated to retrieve the items in the type defined by the Type of Data.
 *
 * @param <T>
 *          determines the class each item to be processed is defined as. e.g. JSON type of data
 *          requires each item to be a JSONObject.
 * @param <U>
 *          determines the class a batch of items is defined as. e.g. JSON type of data requires
 *          that batches of items are send as a JSONArray
 */
public abstract class AsynchronousProcessor<T, U> {
  private static final String UNEXPECTED_ERROR_KEY = "obedl_unexpected_error";
  private static final String ITERATOR_ERROR_KEY = "obedl_iterator_error";
  private static final String NO_LINES_ERROR_KEY = "obedl_error_asynch_nolines";

  private static final Logger log = LoggerFactory.getLogger(AsynchronousProcessor.class);
  protected OBEDLRequest request;
  protected DataProcessor<U> dataProcessor;
  protected OBEDLRequestLine requestLine = null;
  protected ItemProcessor<T> itemProcessor;
  protected int commitLimit;
  protected int recordSize;
  protected boolean hasInput;

  /**
   * Method to initialize the fields with the instances used in this {@link ProcessRequest}
   */
  public void init(OBEDLRequest _request, DataProcessor<U> _dataProcessor,
      ItemProcessor<T> _itemProcessor) {
    request = _request;
    OBEDLProcess process = request.getEDLProcess();
    commitLimit = process.getCommitItems().intValue();
    recordSize = process.getRecordSize().intValue();
    hasInput = process.isHasInput();

    dataProcessor = _dataProcessor;
    itemProcessor = _itemProcessor;
  }

  /**
   * Setter for requestLine
   */
  public void setRequestLine(OBEDLRequestLine requestLine) {
    this.requestLine = requestLine;
  }

  /**
   * Iterates through the DataBatcher to create and schedule the required RequestLines.
   */
  public void scheduleData() {
    if (!hasInput) {
      saveScheduledItems(null, ProcessRequest.STATUS_SCHEDULED, null);
      return;
    }
    Iterator<String> itBatchs = getDataBatcher();
    try {
      boolean hasBatches = false;
      while (itBatchs.hasNext()) {
        hasBatches = true;
        String batch = itBatchs.next();
        saveScheduledItems(batch, ProcessRequest.STATUS_SCHEDULED, null);
      }
      if (!hasBatches) {
        throw new OBException(OBMessageUtils.messageBD(NO_LINES_ERROR_KEY));
      }
    } catch (NoSuchElementException e) {
      log.error("Error iterating batches", e);
      throw new OBException(OBMessageUtils.messageBD(ITERATOR_ERROR_KEY), e);
    }
  }

  /**
   * In case of failure processing an item this method is called to create a new RequestLine in
   * Error status with the failing item as the Request Line Data.
   * 
   * @param requestLineData
   *          The item that has failed as a String.
   * @param errorMsg
   *          String with the error message.
   */
  public void saveFailedItem(String requestLineData, String errorMsg) {
    saveScheduledItems(requestLineData, ProcessRequest.STATUS_ERROR, errorMsg);
  }

  /**
   * Creates or updates a RequestLine and schedules it when needed.
   * 
   * @param requestLineData
   *          the data of the requestLine
   * @param status
   *          status of the requestLine and ImportEntry
   * @param errorMsg
   *          error message for the requestLine and importEntry
   */
  private void saveScheduledItems(String requestLineData, String status, String errorMsg) {
    OBEDLRequestLine newRequestLine;
    if (requestLine != null) {
      newRequestLine = (OBEDLRequestLine) DalUtil.copy(requestLine, false);
      newRequestLine.setImportEntry(null);
      newRequestLine.setArchiveImportEntry(null);
    } else {
      newRequestLine = OBProvider.getInstance().get(OBEDLRequestLine.class);
      newRequestLine.setEDLRequest(request);
    }
    newRequestLine.setStatus(status);
    newRequestLine.setLinedata(requestLineData);
    newRequestLine.setErrorMsg(errorMsg);
    OBDal.getInstance().save(newRequestLine);
    createImportEntry(status, newRequestLine);
  }

  /**
   * Creates a import entry to process the Request Line.
   * 
   * @param status
   *          String with the status to be set in the import entry. Possible values are "Initial"
   *          for entries that requires to be executed and "Error" for request lines that have
   *          failed.
   * @param newRequestLine
   *          the Request Line related to the Import Entry to be created.
   * @return the new ImportEntry
   */
  private ImportEntry createImportEntry(String status, OBEDLRequestLine newRequestLine) {
    ImportEntryManager importEntryManager = WeldUtils
        .getInstanceFromStaticBeanManager(ImportEntryManager.class);
    // If an Import Entry with the request line id already exists nothing is created.
    importEntryManager.createImportEntry(newRequestLine.getId(), "OBEDL_Request", null, false);
    ImportEntry importEntry = OBDal.getInstance().get(ImportEntry.class, newRequestLine.getId());
    importEntry.setImportStatus(status);
    importEntry.setJsonInfo(dataProcessor.getContextData().toString());
    itemProcessor.updateCustomImportEntry(importEntry, newRequestLine);
    newRequestLine.setImportEntry(importEntry);
    return importEntry;
  }

  /**
   * Process the data related to the Request Line being processed.
   * 
   * @return a String with the items that have been successfully processed. In case the EDL Process
   *         has no input a null String is returned.
   */
  public String processBatch() throws OBException {
    String itemsBatch = null;
    if (hasInput) {
      itemsBatch = processBatchWithInput();
    } else {
      processNoInput();
    }
    return itemsBatch;
  }

  /**
   * Iterates the items of the batch calling the {@link ItemProcessor.#processItem(Object)} method.
   * The commit frequency is configured on each process.
   * 
   * @return a String with the items that have been successfully processed.
   */
  private String processBatchWithInput() throws OBException {
    Iterator<T> itemIter = getItemIterator();
    ArrayList<T> processedItems = new ArrayList<T>(recordSize);
    ArrayList<T> uncommitedItems = new ArrayList<T>(commitLimit);
    boolean allItemsProcessed = true;
    int i = 0;
    try {
      while (itemIter.hasNext()) {
        i++;
        T item = itemIter.next();
        try {
          itemProcessor.processItem(item);
          processedItems.add(item);
          uncommitedItems.add(item);
        } catch (Exception e) {
          log.error("Unexpected error processing item", e);
          allItemsProcessed = false;
          try {
            OBDal.getInstance().getConnection(true).rollback();
          } catch (SQLException e1) {
            throw new OBException(OBMessageUtils.messageBD(UNEXPECTED_ERROR_KEY),
                DbUtility.getUnderlyingSQLException(e1));
          }
          if (itemProcessor.reprocessNotCommitedItemsOnException()) {
            for (T reprocessItem : uncommitedItems) {
              itemProcessor.processItem(reprocessItem);
            }
          }
          ArrayList<T> errorItem = new ArrayList<T>(1);
          errorItem.add(item);
          saveFailedItem(getBatchFromList(errorItem), e.getMessage());
          doCommit(uncommitedItems, processedItems);
        }
        if (i % commitLimit == 0) {
          doCommit(uncommitedItems, processedItems);
        }
      }
    } catch (NoSuchElementException e) {
      log.error("Error iterating batches", e);
      throw new OBException(OBMessageUtils.messageBD(ITERATOR_ERROR_KEY), e);
    }
    if (!uncommitedItems.isEmpty()) {
      // Do a final commit in case there are some items not committed.
      doCommit(uncommitedItems, processedItems);
    }

    if (processedItems.isEmpty()) {
      return null;
    }
    // If all items have been successfully processed there is no need to generate again the batch
    if (allItemsProcessed) {
      return requestLine.getLinedata();
    }
    return getBatchFromList(processedItems);
  }

  /**
   * Used by EDL Processes that has no input. It calls the {@link
   * ItemProcessor.#processItem(Object)} method with a null item and performs a commit.
   */
  private void processNoInput() throws OBException {
    try {
      itemProcessor.processItem(null);
    } catch (Exception e) {
      log.error("Unexpected error processing item", e);
      try {
        OBDal.getInstance().getConnection(true).rollback();
      } catch (SQLException e1) {
        throw new OBException(OBMessageUtils.messageBD(UNEXPECTED_ERROR_KEY),
            DbUtility.getUnderlyingSQLException(e1));
      }
      saveFailedItem(null, e.getMessage());
    }
    doCommit();
  }

  /**
   * Flush, commits and clears the current transaction. Clears the list of the items that are
   * pending to commit.
   * 
   * @param uncommitedItems
   *          the list of Items that have been processed successfully but are not committed.
   * @param processedItems
   *          the list of Items that have been processed successfully including those already
   *          committed.
   * @throws OBException
   *           the EDL Process is configured to not retry on exception and there is an exception.
   *           {@link ItemProcessor.#reprocessNotCommitedItemsOnException()}
   * 
   */
  private void doCommit(List<T> uncommitedItems, List<T> processedItems) throws OBException {
    try {
      OBDal.getInstance().getConnection(true).commit();
      OBDal.getInstance().getSession().clear();
    } catch (HibernateException e) {
      manageException(e, uncommitedItems, processedItems);
    } catch (SQLException e) {
      manageException(e, uncommitedItems, processedItems);
    }
    uncommitedItems.clear();
  }

  /**
   * Commits the current transaction performing a flush and clears the Session.
   * 
   * @throws OBException
   *           in case there is a SQLException it is thrown as a OBException.
   */
  private void doCommit() throws OBException {
    try {
      OBDal.getInstance().flush();
      SessionHandler.getInstance().getConnection().commit();
      OBDal.getInstance().getSession().clear();
    } catch (HibernateException | SQLException e) {
      // Some Database exceptions as constraint checks are thrown as HibernateException
      Throwable ex = DbUtility.getUnderlyingSQLException(e);
      String message = OBMessageUtils.translateError(ex.getMessage()).getMessage();
      throw new OBException(message, e);
    }
  }

  /**
   * If there is an Exception doing the commit and the process is configured to retry on exception
   * it adds all the uncommitted items to a new Request Line in error status and removes them from
   * the list of processed items.
   * 
   * @throws OBException
   *           when the uncommitedItems list is empty. This happens only when the EDL Process is
   *           configured to not retry on exception. {@link
   *           ItemProcessor.#reprocessNotCommitedItemsOnException()}
   * 
   */
  private void manageException(Exception e, List<T> uncommitedItems, List<T> processedItems)
      throws OBException {
    Throwable t = DbUtility.getUnderlyingSQLException(e);
    log.error("Unexpected exception in commit", t);
    try {
      OBDal.getInstance().getConnection(true).rollback();
    } catch (SQLException e1) {
      throw new OBException(OBMessageUtils.messageBD(UNEXPECTED_ERROR_KEY),
          DbUtility.getUnderlyingSQLException(e1));
    }
    if (uncommitedItems.isEmpty()) {
      // The process does not retry items on exceptions. Raise the SqlException as a OBException.
      // In this scenario there shouldn't be exceptions on any commit so we stop the execution.
      String errorMessage = OBMessageUtils.messageBD(UNEXPECTED_ERROR_KEY);
      throw new OBException(errorMessage, t);
    }
    // If there are uncommitedItems there are expected errors. In this scenario add the
    // uncommited items to an error request line and remove them from the processed items list.
    processedItems.removeAll(uncommitedItems);
    saveFailedItem(getBatchFromList(uncommitedItems), t.getMessage());
  }

  /**
   * Creates a Iterator of String based on the rawdata of the EDL Request. Each element of the
   * iterator should be a String representation of the type defined by the Type of Data for the
   * batches. The raw data is retrieved by calling {@link DataProcessor.#getDataFromRaw()}
   * 
   * e.g. the JSON Type of Data uses a JSONArray of JSONObjects.
   * 
   */
  protected abstract Iterator<String> getDataBatcher();

  /**
   * Converts a List of items to a batch String.
   */
  protected abstract String getBatchFromList(List<T> items);

  /**
   * Creates a Iterator of the item type defined by the Type of Data based on the batch being
   * processed. The batch String is retrieved from the requestLine field by calling {@link
   * OBEDLRequestLine.#getLinedata()}.
   */
  protected abstract Iterator<T> getItemIterator();

  /**
   * Iterates through the Request Lines to reschedule them. In case the import entry is already
   * archived it is moved back to the ImportEntry table. In case that it does not exists at all a
   * new ImportEntry is created.
   * 
   * @param requestLinesScroll
   *          The RequestLines required to reprocess.
   */
  protected void reprocessRequestLines(ScrollableResults requestLinesScroll) {
    int i = 0;
    while (requestLinesScroll.next()) {
      OBEDLRequestLine reqLine = (OBEDLRequestLine) requestLinesScroll.get(0);
      ImportEntry importEntry = OBDal.getInstance().get(ImportEntry.class, reqLine.getId());
      if (importEntry == null && reqLine.getArchiveImportEntry() != null) {
        // Import entry was archived move it back to the import entry table.
        importEntry = rollbackArchiveEntry(reqLine.getArchiveImportEntry());
        OBDal.getInstance().save(importEntry);
        reqLine.setImportEntry(importEntry);
        OBDal.getInstance().remove(reqLine.getArchiveImportEntry());
      } else if (importEntry == null) {
        importEntry = createImportEntry(ProcessRequest.STATUS_SCHEDULED, reqLine);
        OBDal.getInstance().save(importEntry);
        reqLine.setImportEntry(importEntry);
      }
      importEntry.setImportStatus(ProcessRequest.STATUS_SCHEDULED);
      reqLine.setStatus(ProcessRequest.STATUS_SCHEDULED);
      if (++i % 1000 == 0) {
        OBDal.getInstance().flush();
        OBDal.getInstance().getSession().clear();
      }
    }
    doCommit();
    ImportEntryManager.getInstance().notifyNewImportEntryCreated();
  }

  /**
   * It moves the given ImportEntryArchive record to the ImportEntry table.
   * 
   * @param archiveEntry
   *          the archived ImportEntry to be recovered.
   * @return the new ImportEntry.
   */
  private ImportEntry rollbackArchiveEntry(ImportEntryArchive archiveEntry) {
    ImportEntry importEntry = OBProvider.getInstance().get(ImportEntry.class);
    Entity entryEntity = ModelProvider.getInstance().getEntity(ImportEntry.ENTITY_NAME);
    Entity archiveEntity = ModelProvider.getInstance().getEntity(ImportEntryArchive.ENTITY_NAME);
    // copy properties with the same name
    for (Property sourceProperty : archiveEntity.getProperties()) {
      // ignore these ones
      if (sourceProperty.isOneToMany() || !entryEntity.hasProperty(sourceProperty.getName())) {
        continue;
      }
      Property targetProperty = entryEntity.getProperty(sourceProperty.getName());
      // should be the same type
      if (targetProperty.getDomainType().getClass() != sourceProperty.getDomainType().getClass()) {
        continue;
      }
      importEntry.set(targetProperty.getName(), archiveEntry.getValue(sourceProperty.getName()));
    }
    // as the id is also copied set it explicitly to new
    importEntry.setNewOBObject(true);
    return importEntry;
  }

}
