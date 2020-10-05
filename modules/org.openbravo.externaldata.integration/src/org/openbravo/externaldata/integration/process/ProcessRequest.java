/*
 ************************************************************************************
 * Copyright (C) 2016-2017 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session.LockRequest;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.util.Check;
import org.openbravo.base.weld.WeldUtils;
import org.openbravo.client.kernel.ComponentProvider;
import org.openbravo.client.kernel.ComponentProvider.Selector;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.core.SessionHandler;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.OBEDLConfigOutput;
import org.openbravo.externaldata.integration.OBEDLConfiguration;
import org.openbravo.externaldata.integration.OBEDLProcess;
import org.openbravo.externaldata.integration.OBEDLRequest;
import org.openbravo.externaldata.integration.OBEDLRequestLine;
import org.openbravo.service.db.DbUtility;
import org.openbravo.service.importprocess.ImportEntryManager;
import org.openbravo.service.json.JsonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class that manages EDL Requests. It is used to add new request or to reprocess any request.
 *
 */
@Dependent
public class ProcessRequest<T, U> {
  private static final Logger log4j = LoggerFactory.getLogger(ProcessRequest.class);

  public static final String STATUS_SCHEDULED = "Initial";
  public static final String STATUS_ERROR = "Error";
  public static final String STATUS_VOIDED = "Voided";

  public static enum ExecutionMode {
    ASYNCHRONOUS("ASYNC"), SYNCHRONOUS("SYNC"), PROCESS_DEFAULT("PROC_DEFAULT");
    String value;

    ExecutionMode(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    public static ExecutionMode getExecutionMode(String value) {
      if (StringUtils.isEmpty(value)) {
        return PROCESS_DEFAULT;
      }
      switch (value) {
      case "ASYNC":
        return ASYNCHRONOUS;
      case "SYNC":
        return SYNCHRONOUS;
      }
      return PROCESS_DEFAULT;
    }
  };

  // public static final String ASYNCHRONOUS_MODE = "ASYNC";
  // public static final String SYNCHRONOUS_MODE = "SYNC";

  private static final String REQUEST_STATUS_PROCESSING = "Processing";
  private static final String REQUEST_STATUS_SUCCESS = "Success";
  private OBEDLRequest request;
  private OBEDLRequestLine requestLine;
  private boolean hasInput;
  private boolean hasOutput;
  private boolean isSynchronous;

  private DataProcessor<U> dataProcessor;
  private ProcessDataProcessor<U> processDataProcessor;
  private SynchronousProcessor<T, U> synchProcessor;
  private AsynchronousProcessor<T, U> asynchProcessor;
  private ItemProcessor<T> itemProcessor;

  @Inject
  @Any
  private Instance<DataProcessor<U>> dataProcessors;

  @Inject
  @Any
  private Instance<ProcessDataProcessor<U>> processDataProcessors;

  @Inject
  @Any
  private Instance<SynchronousProcessor<T, U>> synchProcessors;

  @Inject
  @Any
  private Instance<AsynchronousProcessor<T, U>> asynchProcessors;

  @Inject
  @Any
  private Instance<ItemProcessor<T>> itemProcessors;

  @Inject
  @Any
  private Instance<CustomValidation> customValidations;

  @Inject
  @Any
  private Instance<OutputDataProcessor> outputProcessors;

  /**
   * Static ProcessRequest instance getter
   */
  public static ProcessRequest<?, ?> getProcessRequestInstance() {
    ProcessRequest<?, ?> processRequest = WeldUtils
        .getInstanceFromStaticBeanManager(ProcessRequest.class);
    return processRequest;
  }

  /**
   * Initializes the required fields based on the EDL Process that it is being processed.
   * 
   * itemProcessor and processDataProcessor if available are loaded based on the EDL Process of the
   * request.
   * 
   * dataProcessor, synchProcessor and asynchProcessor are loaded based on the EDL Process' Type of
   * Data
   * 
   * @param process
   *          EDLProcess of the request to be processed.
   */
  private void initializeRequest(OBEDLProcess process) {
    hasInput = process.isHasInput();
    hasOutput = process.isHasOutput();
    ExecutionMode execMode = ExecutionMode.getExecutionMode(request.getExecutionMode());
    switch (execMode) {
    case ASYNCHRONOUS:
      isSynchronous = false;
      break;
    case SYNCHRONOUS:
      isSynchronous = true;
      break;
    case PROCESS_DEFAULT:
      isSynchronous = process.isSynchronous();
    }
    Selector processSelector = new ComponentProvider.Selector(process.getSearchKey());
    itemProcessor = itemProcessors.select(processSelector).get();
    if (!processDataProcessors.select(processSelector).isUnsatisfied()) {
      processDataProcessor = processDataProcessors.select(processSelector).get();
    }
    Selector dataTypeSelector = new ComponentProvider.Selector(process.getTypeOfData());
    dataProcessor = dataProcessors.select(dataTypeSelector).get();
    dataProcessor.init(process, request, processDataProcessor);
    if (isSynchronous) {
      synchProcessor = synchProcessors.select(dataTypeSelector).get();
      synchProcessor.init(request, dataProcessor, itemProcessor);
    } else {
      asynchProcessor = asynchProcessors.select(dataTypeSelector).get();
      asynchProcessor.init(request, dataProcessor, itemProcessor);
    }
    if (processDataProcessor != null) {
      processDataProcessor.init(request, dataProcessor);
    }
  }

  /**
   * Adds a new EDL Request that does not have any response writer.
   * 
   * @see #addRequest(String, Object, Writer, Boolean)
   * 
   * @param processId
   *          ID of the EDL Process to execute
   * @param data
   *          input data if needed to process the request
   * @return the new EDL Request object
   * @throws OBException
   *           when a validation fails or there is any error in the process.
   */
  public OBEDLRequest addRequest(String processId, Object data) throws OBException {
    return addRequest(processId, data, null, ExecutionMode.PROCESS_DEFAULT);
  }

  /**
   * Adds a new EDL Request that does not have any response writer with the synchrony overridden.
   * 
   * @see #addRequest(String, Object, Writer, Boolean)
   * 
   * @param processId
   *          ID of the EDL Process to execute
   * @param data
   *          input data if needed to process the request
   * @param executionMode
   *          parameter with the Execution Mode to be used in the new Request
   * @return the new EDL Request object
   * @throws OBException
   *           when a validation fails or there is any error in the process.
   */
  public OBEDLRequest addRequestForceExecutionMode(String processId, Object data,
      ExecutionMode executionMode) throws OBException {
    return addRequest(processId, data, null, executionMode);
  }

  /**
   * Adds a new EDL Request.
   * 
   * @see #addRequest(String, Object, Writer, Boolean)
   * 
   * @param processId
   *          ID of the EDL Process to execute
   * @param data
   *          input data if needed to process the request
   * @return the new EDL Request object
   * @param responseWriter
   *          Writer where the direct response is written on synchronous processes that have this
   *          feature enabled.
   * @throws OBException
   *           when a validation fails or there is any error in the process.
   */
  public OBEDLRequest addRequest(String processId, Object data, Writer responseWriter)
      throws OBException {
    return addRequest(processId, data, responseWriter, ExecutionMode.PROCESS_DEFAULT);
  }

  /**
   * Adds and executes a new EDL Request.
   * 
   * Synchronous processes: Data is read and the process is executed. If a responseWriter is given a
   * direct response is written on it.
   * 
   * Asynchronous processes: Data is read and scheduled in request lines that are executed as Import
   * Entries.
   * 
   * @param processId
   *          ID of the EDL Process to execute
   * @param data
   *          input data if needed to process the request
   * @param responseWriter
   *          Writer where the direct response is written on synchronous processes that have this
   *          feature enabled.
   * @param executionMode
   *          Defines the Execution Mode to be used in the new EDL Request. In case the EDL Process
   *          default is required use the ExecutionMode.PROCESS_DEFAULT value.
   * @return the new EDL Request object
   * @throws OBException
   *           when a validation fails or there is any error in the process.
   */
  public OBEDLRequest addRequest(String processId, Object data, Writer responseWriter,
      ExecutionMode executionMode) throws OBException {
    try {
      OBContext.setAdminMode(true);
      OBEDLProcess process = OBDal.getInstance().get(OBEDLProcess.class, processId);
      processValidations(processId, process, responseWriter);
      createRequest(process, executionMode);
      initializeRequest(process);
      if (hasInput) {
        try {
          dataProcessor.initializeRawData(data);
        } catch (IOException e) {
          OBDal.getInstance().rollbackAndClose();
          throw new OBException(OBMessageUtils.messageBD("obedl_error_incorrect_data"), e);
        }
      }
      JSONObject contextData = dataProcessor.getContextData();
      request.setContextData(contextData.toString());
      // Request already created. If there is an error while processing it we want to keep the
      // request to be able to reprocess and see the error.
      doCommit();
      configurationValidations(process);
      try {
        process();
        if (responseWriter != null && isSynchronous) {
          synchProcessor.writeDirectResponse(responseWriter);
        }
        return request;
      } catch (OBException e) {
        try {
          OBDal.getInstance().getConnection(false).rollback();
        } catch (SQLException ignore) {
        }
        OBDal.getInstance().getSession().buildLockRequest(LockOptions.NONE)
            .lock(OBEDLRequest.ENTITY_NAME, request);
        request.setStatus(STATUS_ERROR);
        request.setProcessed(Boolean.FALSE);
        request.setResponse(e.getMessage());
        OBDal.getInstance().save(request);
        OBDal.getInstance().commitAndClose();
        throw e;
      } catch (Exception e) {
        log4j.error("Unhandled exception", e);
        try {
          OBDal.getInstance().getConnection(false).rollback();
        } catch (SQLException ignore) {
        }
        String errorMsg = e.getMessage();
        if (errorMsg == null) {
          errorMsg = e.toString();
        }
        OBDal.getInstance().getSession().buildLockRequest(LockOptions.NONE)
            .lock(OBEDLRequest.ENTITY_NAME, request);
        request.setStatus(STATUS_ERROR);
        request.setProcessed(Boolean.FALSE);
        request.setResponse(errorMsg);
        OBDal.getInstance().save(request);
        OBDal.getInstance().commitAndClose();
        throw new OBException(errorMsg, e);
      }
    } finally {
      OBContext.restorePreviousMode();
    }
  }

  /**
   * Method to reprocess a Request. It loads the stored input data and tries to process again the
   * request.
   * 
   * @param requestId
   *          Id of the EDL Request to reprocess
   * @param onlyErrors
   *          in case of asynchronous a flag to reprocess only the request lines in error status.
   * @return JSONObject with the result and message to show
   */
  public JSONObject reprocessRequest(String requestId, boolean onlyErrors) {
    String message;
    boolean error = false;
    request = OBDal.getInstance().get(OBEDLRequest.class, requestId);
    if (request == null) {
      throw new OBException(OBMessageUtils.messageBD("obedl_request_not_found"));
    }

    request.setStatus(REQUEST_STATUS_PROCESSING);
    request.setProcessed(Boolean.FALSE);
    request.setExecutionDate(new Date());
    OBDal.getInstance().save(request);

    try {
      OBEDLProcess process = request.getEDLProcess();
      initializeRequest(process);
      if (isSynchronous) {
        if (hasInput) {
          dataProcessor.loadStoredData();
        }
        process();
        message = OBMessageUtils.messageBD("obedl_request_succesful");
      } else {
        reprocessRequestLines(onlyErrors);
        updateRequestStatus();
        message = OBMessageUtils.messageBD("obedl_request_scheduled");
      }

    } catch (OBException e) {
      error = true;
      message = e.getMessage();
    } catch (SQLException e) {
      error = true;
      message = e.getMessage();
    } catch (Exception e) {
      log4j.error("Unhandled exception", e);
      error = true;
      message = e.getMessage();
    } finally {
      if (isSynchronous) {
        if (hasInput) {
          dataProcessor.deleteTempData();
        }
      }
    }
    if (error) {
      request.setStatus(STATUS_ERROR);
    }
    JSONObject result = new JSONObject();
    try {
      result.put(JsonConstants.RESPONSE_ERROR, error);
      result.put(JsonConstants.RESPONSE_ERRORMESSAGE, message);
    } catch (JSONException ignore) {
    }
    return result;
  }

  /**
   * Method that processes a EDL Request Line. Processes the batch of items stored in the request.
   * If the result is success it executes the configured output processes.
   * 
   * @param requestLine
   *          The request line to process
   * @return JSONObject with the result and message to show
   */
  public JSONObject processRequestLine(OBEDLRequestLine _requestLine) throws OBException {
    requestLine = _requestLine;
    request = requestLine.getEDLRequest();
    request.setExecutionDate(new Date());
    String status = REQUEST_STATUS_SUCCESS;
    boolean error = false;
    String errorMsg = "";

    OBEDLProcess process = request.getEDLProcess();
    try {
      initializeRequest(process);
      asynchProcessor.setRequestLine(requestLine);
      if (request.getContextData() != null) {
        try {
          itemProcessor.setContextData(new JSONObject(request.getContextData()));
        } catch (JSONException e) {
          log4j.error("Error loading stored context data.", e);
        }
      }
      String processedBatch = asynchProcessor.processBatch();

      LockRequest lockRequest = OBDal.getInstance().getSession().buildLockRequest(LockOptions.NONE);
      lockRequest.lock(OBEDLProcess.ENTITY_NAME, process);
      lockRequest.lock(OBEDLRequestLine.ENTITY_NAME, requestLine);
      if (processedBatch == null && hasInput) {
        OBDal.getInstance().remove(requestLine);
        requestLine = null;
        status = "Deleted";
      } else {
        requestLine.setLinedata(processedBatch);
        OBDal.getInstance().save(requestLine);

        if (hasOutput) {
          OBCriteria<OBEDLConfiguration> configCriteria = OBDal.getInstance().createCriteria(
              OBEDLConfiguration.class);
          configCriteria.add(Restrictions.eq(OBEDLConfiguration.PROPERTY_CLIENT,
              request.getClient()));
          configCriteria.add(Restrictions.eq(OBEDLConfiguration.PROPERTY_EDLPROCESS, process));
          configCriteria.setMaxResults(1);
          OBEDLConfiguration config = (OBEDLConfiguration) configCriteria.uniqueResult();
          endProcess(config);
        }
      }

    } catch (OBException e) {
      log4j.error("Error processing request line", e);
      error = true;
      status = STATUS_ERROR;
      errorMsg = e.getMessage();
    } catch (Exception e) {
      try {
        SessionHandler.getInstance().getConnection().rollback();
      } catch (SQLException e1) {
        log4j.error("Error while doing rollback.", e1);
      }
      log4j.error("Unhandled exception processing request line", e);
      error = true;
      status = STATUS_ERROR;
      errorMsg = e.getMessage();
      if (errorMsg == null) {
        errorMsg = e.toString();
      }
    }
    if (requestLine != null) {
      OBDal.getInstance().getSession().buildLockRequest(LockOptions.NONE)
          .lock(OBEDLRequest.ENTITY_NAME, requestLine);
      requestLine.setStatus(status);
      requestLine.setErrorMsg(errorMsg);

      OBDal.getInstance().save(requestLine);
      OBDal.getInstance().flush();
    }
    doCommit();
    updateRequestStatus();
    doCommit();

    JSONObject result = new JSONObject();
    try {
      result.put(JsonConstants.RESPONSE_ERROR, error);
      result.put(JsonConstants.RESPONSE_ERRORMESSAGE, errorMsg);
    } catch (JSONException ignore) {
    }
    return result;
  }

  /**
   * Cancels a request in Error status by updating it and its request lines, in case it is
   * asynchronous, to Void status.
   * 
   * @param requestId
   *          The id of the request to be discarded.
   * @throws OBException
   *           When the given request is not found or it is not in Error status.
   */
  public void cancelRequest(String requestId) throws OBException {
    request = OBDal.getInstance().get(OBEDLRequest.class, requestId);
    if (request == null) {
      throw new OBException(OBMessageUtils.messageBD("obedl_request_not_found"));
    }
    if (!STATUS_ERROR.equals(request.getStatus())) {
      throw new OBException(OBMessageUtils.messageBD("obedl_only_void_error_requests"));
    }
    request.setStatus(STATUS_VOIDED);
    request.setProcessed(Boolean.TRUE);
    OBDal.getInstance().save(request);
    ExecutionMode execMode = ExecutionMode.getExecutionMode(request.getExecutionMode());
    switch (execMode) {
    case ASYNCHRONOUS:
      isSynchronous = false;
      break;
    case SYNCHRONOUS:
      isSynchronous = true;
      break;
    default:
      isSynchronous = request.getEDLProcess().isSynchronous();
    }

    if (!isSynchronous) {
      // If it is an asynchronous request cancel all the request line in error status.
      ScrollableResults requestLines = getRequestLinesInStatus(STATUS_ERROR);
      int i = 0;
      while (requestLines.next()) {
        OBEDLRequestLine reqLine = (OBEDLRequestLine) requestLines.get()[0];
        reqLine.setStatus(STATUS_VOIDED);
        if (reqLine.getImportEntry() != null) {
          OBDal.getInstance().remove(reqLine.getImportEntry());
          reqLine.setImportEntry(null);
        }
        if (++i % 1000 == 0) {
          OBDal.getInstance().flush();
          OBDal.getInstance().getSession().clear();
        }
      }
    }
  }

  /**
   * Method that processes the EDL Request. In case of synchronous processes the received data is
   * processed and the output processes are executed in case of success. In asynchronous processes
   * the received data is batched in request lines and scheduled to be executed by the Import Entry
   * Manager.
   * 
   * @throws OBException
   *           in case of any error reading the data to process.
   */
  private void process() throws OBException {
    OBEDLProcess process = request.getEDLProcess();
    itemProcessor.setContextData(dataProcessor.getContextData());
    if (isSynchronous) {
      synchProcessor.processData();
      if (hasOutput) {
        OBCriteria<OBEDLConfiguration> configCriteria = OBDal.getInstance().createCriteria(
            OBEDLConfiguration.class);
        configCriteria
            .add(Restrictions.eq(OBEDLConfiguration.PROPERTY_CLIENT, request.getClient()));
        configCriteria.add(Restrictions.eq(OBEDLConfiguration.PROPERTY_EDLPROCESS, process));
        configCriteria.setMaxResults(1);
        OBEDLConfiguration config = (OBEDLConfiguration) configCriteria.uniqueResult();
        try {
          endProcess(config);
        } catch (IOException e) {
          throw new OBException(OBMessageUtils.messageBD("obedl_error_incorrect_data"), e);
        }
      }

      // Reattach request to active session. In case processData or endProcess have cleared or
      // closed the session.
      OBDal.getInstance().getSession().buildLockRequest(LockOptions.NONE)
          .lock(OBEDLRequest.ENTITY_NAME, request);
      request.setExecutionDate(new Date());
      request.setStatus(ProcessRequest.REQUEST_STATUS_SUCCESS);
      request.setProcessed(Boolean.TRUE);
    } else {
      asynchProcessor.scheduleData();
      OBDal.getInstance().getSession().buildLockRequest(LockOptions.NONE)
          .lock(OBEDLRequest.ENTITY_NAME, request);
      request.setContextData(dataProcessor.getContextData().toString());
      request.setStatus(ProcessRequest.STATUS_SCHEDULED);
      doCommit();
      ImportEntryManager importEntryManager = WeldUtils
          .getInstanceFromStaticBeanManager(ImportEntryManager.class);
      importEntryManager.notifyNewImportEntryCreated();
    }

    request.setResponse(getLog());
    doCommit();
  }

  /**
   * Sets all the Import Entries related to the EDL Request in "Initial" status to rescehedule them.
   * 
   * @param onlyErrors
   *          if true it filters the Import Entries so only those in Error status are rescheduled.
   * 
   * @param requestLinesScroll
   *          list of the request lines
   * @throws SQLException
   *           if something goes wrong with the database
   */
  private void reprocessRequestLines(boolean onlyErrors) throws SQLException, OBException {
    ScrollableResults requestLinesScroll = getRequestLinesInStatus(onlyErrors ? STATUS_ERROR : "");
    try {
      asynchProcessor.reprocessRequestLines(requestLinesScroll);
    } finally {
      requestLinesScroll.close();
    }
  }

  private ScrollableResults getRequestLinesInStatus(String status) {
    OBCriteria<OBEDLRequestLine> requestLines = OBDal.getInstance().createCriteria(
        OBEDLRequestLine.class);
    requestLines.add(Restrictions.eq(OBEDLRequestLine.PROPERTY_EDLREQUEST, request));
    if (StringUtils.isNotEmpty(status)) {
      requestLines.add(Restrictions.eq(OBEDLRequestLine.PROPERTY_STATUS, status));
    }
    requestLines.setFetchSize(1000);
    return requestLines.scroll();
  }

  /**
   * Validations executed when a new EDL Request is added. It executes {@link
   * CustomValidation.#doProcessValidations(OBEDLProcess) processValidations} implemented by the EDL
   * Process to be used.
   * 
   * @param processId
   *          ID of the process
   * @param process
   *          Object with the process loaded
   * @param responseWriter
   *          Writer where it is expected to write the direct response.
   */
  private void processValidations(String processId, OBEDLProcess process, Writer responseWriter)
      throws OBException {
    // EDL Process must exist.
    if (process == null) {
      String errorMessage = OBMessageUtils.messageBD("obedl_not_process_defined");
      Map<String, String> errorParam = new HashMap<String, String>();
      errorParam.put("processid", processId);
      throw new OBException(OBMessageUtils.parseTranslation(errorMessage, errorParam));
    }
    // If the process is defined with direct response a Writer must be set in the addRequest call.
    if (process.isHasDirectResponse() && responseWriter == null) {
      throw new OBException(OBMessageUtils.messageBD("obedl_missing_direct_response_writer"));
    }

    Selector processSelector = new ComponentProvider.Selector(process.getSearchKey());
    for (CustomValidation customValidation : customValidations.select(processSelector)) {
      customValidation.doProcessValidations(process);
    }
  }

  /**
   * Configuration validations of the EDL Process to be executed just before starting to process the
   * request. It executes {@link CustomValidation.#doConfigurationValidations(OBEDLProcess)
   * configurationValidations} implemented by the EDL Process to be used.
   * 
   * @param client
   *          Client of the request
   * @param process
   *          Process object
   */
  private void configurationValidations(OBEDLProcess process) throws OBException {
    if (process.isHasOutput()) {
      OBCriteria<OBEDLConfiguration> configCriteria = OBDal.getInstance().createCriteria(
          OBEDLConfiguration.class);
      configCriteria.add(Restrictions.eq(OBEDLConfiguration.PROPERTY_CLIENT, request.getClient()));
      configCriteria.add(Restrictions.eq(OBEDLConfiguration.PROPERTY_EDLPROCESS, process));
      configCriteria.setMaxResults(1);
      OBEDLConfiguration config = (OBEDLConfiguration) configCriteria.uniqueResult();
      if (config == null) {
        String errorMessage = OBMessageUtils.messageBD("obedl_not_config_defined");
        Map<String, String> errorParam = new HashMap<String, String>();
        errorParam.put("processid", process.getId());
        throw new OBException(OBMessageUtils.parseTranslation(errorMessage, errorParam));
      }
      if (config.getOBEDLConfigOutputList() == null || config.getOBEDLConfigOutputList().isEmpty()) {
        String errorMessage = OBMessageUtils.messageBD("obedl_not_output_config_defined");
        Map<String, String> errorParam = new HashMap<String, String>();
        errorParam.put("processid", process.getId());
        throw new OBException(OBMessageUtils.parseTranslation(errorMessage, errorParam));
      }
    }

    Selector processSelector = new ComponentProvider.Selector(process.getSearchKey());
    for (CustomValidation customValidation : customValidations.select(processSelector)) {
      customValidation.doConfigurationValidations(process);
    }
  }

  /**
   * Executes all the Output Processes configured for the EDL Process.
   * 
   * @param config
   *          the configuration of the client for the request's process
   * @throws IOException
   *           If there has been any problem writing the response in the configured output
   * @throws OBException
   *           when there is an error processing the OutputProcess.
   */
  private void endProcess(OBEDLConfiguration config) throws IOException, OBException {
    OBCriteria<OBEDLConfigOutput> outputsSortedCrit = OBDal.getInstance().createCriteria(
        OBEDLConfigOutput.class);
    outputsSortedCrit.add(Restrictions.eq(OBEDLConfigOutput.PROPERTY_CONFIGURATION, config));
    outputsSortedCrit.addOrder(Order.asc(OBEDLConfigOutput.PROPERTY_SEQUENCENUMBER));
    for (OBEDLConfigOutput output : outputsSortedCrit.list()) {
      executeEndProcessOutput(output);
    }
  }

  /**
   * Executes the OutputProcess. In case the retry is enabled the same output process can be
   * executed several times in case of exceptions until there is a success or the maximum retry
   * attempts is reached.
   * 
   * @param output
   *          The OutputProcess configuration.
   * @throws IOException
   *           If there has been any problem writing the response in the configured output
   * @throws OBException
   *           when there is an error processing the OutputProcess.
   */
  private void executeEndProcessOutput(OBEDLConfigOutput output) throws IOException, OBException {
    OutputDataProcessor outputProcessor = outputProcessors.select(
        new ComponentProvider.Selector(output.getOutputType().getSearchKey())).get();
    long curRetry = 0L;
    boolean isRetryEnabled = output.getConfiguration().getEDLProcess().isEnableOutputRetry()
        && output.isRetryEnabled();
    long maxRetry = isRetryEnabled ? output.getMaxRetryNumber() : 0L;
    long waitForRetry = isRetryEnabled ? output.getRetryInterval() : 0L;
    boolean success = false;
    log4j.debug("EDL Process {} processing Output Processor {} for ", output.getConfiguration()
        .getEDLProcess().getName(), output.getOutputType().getSearchKey());
    do {
      log4j.debug("OutputConfig: {} Process attempt {}", output.getId(), curRetry + " of "
          + maxRetry);
      try {
        outputProcessor.process(output, itemProcessor, request, requestLine);
        success = true;
      } catch (Exception e) {
        if (++curRetry > maxRetry) {
          throw e;
        }
        manageException(e, waitForRetry);
      }
      OBDal.getInstance().refresh(output);
    } while (!success);
  }

  /**
   * Logs the exception message and stacktrace and waits the defined milliseconds to retry the
   * endProcess execution.
   * 
   * @param e
   *          the Exception thrown by the outputProcessor.
   * @param waitForRetry
   *          the milliseconds to sleep the thread until next retry.
   */
  private void manageException(Exception e, long waitForRetry) {
    log4j.error("Error processing output process for request {}", request.getId(), e);
    log4j.error("Sleeping {} ms for next attempt", waitForRetry);
    try {
      Thread.sleep(waitForRetry);
    } catch (InterruptedException ie) {
      log4j.error("End process interruption while sleeping", ie);
    }
  }

  /**
   * Creates a new EDL Request object with the given EDL Process in Initial status.
   * 
   * @param process
   *          Determines the EDL Process to be executed on the new Request.
   * @param executionMode
   *          Sets the Execution Mode of the Request. If PROCESS_DEFAULT is set the Default mode
   *          defined in the EDL Process is used.
   */
  private void createRequest(OBEDLProcess process, ExecutionMode executionMode) {
    request = OBProvider.getInstance().get(OBEDLRequest.class);
    request.setRequestDate(new Date());
    request.setEDLProcess(process);
    request.setStatus(STATUS_SCHEDULED);
    Check.isNotNull(executionMode, OBMessageUtils.messageBD("OBEDL_MandatoryExecutionMode"));
    String executionModeValue;
    switch (executionMode) {
    case ASYNCHRONOUS:
    case SYNCHRONOUS:
      executionModeValue = executionMode.getValue();
      break;
    case PROCESS_DEFAULT:
    default:
      executionModeValue = process.isSynchronous() ? ExecutionMode.SYNCHRONOUS.getValue()
          : ExecutionMode.ASYNCHRONOUS.getValue();
      break;
    }
    request.setExecutionMode(executionModeValue);
    OBDal.getInstance().save(request);
  }

  /**
   * Updates the EDL Request status of an asynchronous process based on the current status of its
   * request lines.
   * <ul>
   * <li>If there is any line in Initial status the Request status is set to Processing.
   * <li>If there is any line in Error status and not Initial the Request status is set to Error.
   * <li>If there isn't any line in Error or Initial the Request status is set to Success.
   * </ul>
   */
  private void updateRequestStatus() {
    OBDal.getInstance().getSession().buildLockRequest(LockOptions.NONE)
        .lock(OBEDLRequest.ENTITY_NAME, request);
    String reqStatus = REQUEST_STATUS_SUCCESS;
    StringBuilder hql = new StringBuilder();
    hql.append(" select count(*) from ").append(OBEDLRequestLine.ENTITY_NAME);
    hql.append(" where ").append(OBEDLRequestLine.PROPERTY_EDLREQUEST).append(" = :request");
    hql.append(" and ").append(OBEDLRequestLine.PROPERTY_STATUS).append(" = '")
        .append(STATUS_ERROR).append("'");
    Query qry = OBDal.getInstance().getSession().createQuery(hql.toString());
    qry.setParameter("request", request);
    int errorCount = ((Number) qry.uniqueResult()).intValue();
    String responseMessage = "";
    if (errorCount > 0) {
      reqStatus = STATUS_ERROR;
      String errorMessage = OBMessageUtils.messageBD("obedl_error_lines");
      Map<String, String> errorParam = new HashMap<String, String>();
      errorParam.put("items", String.valueOf(errorCount));
      responseMessage = responseMessage.concat(OBMessageUtils.parseTranslation(errorMessage,
          errorParam));
    }
    hql = new StringBuilder();
    hql.append(" select count(*) from ").append(OBEDLRequestLine.ENTITY_NAME);
    hql.append(" where ").append(OBEDLRequestLine.PROPERTY_EDLREQUEST).append(" = :request");
    hql.append(" and (");
    hql.append(OBEDLRequestLine.PROPERTY_STATUS).append(" = '").append(REQUEST_STATUS_PROCESSING)
        .append("'");
    hql.append("  or ").append(OBEDLRequestLine.PROPERTY_STATUS).append(" = '")
        .append(STATUS_SCHEDULED).append("'");
    hql.append("  )");
    qry = OBDal.getInstance().getSession().createQuery(hql.toString());
    qry.setParameter("request", request);
    int scheduledCount = ((Number) qry.uniqueResult()).intValue();
    if (scheduledCount > 0) {
      reqStatus = REQUEST_STATUS_PROCESSING;
      String errorMessage = OBMessageUtils.messageBD("obedl_scheduled_lines");
      Map<String, String> errorParam = new HashMap<String, String>();
      errorParam.put("items", String.valueOf(scheduledCount));
      if (StringUtils.isEmpty(responseMessage)) {
        responseMessage.concat("\n");
      }
      responseMessage = responseMessage.concat(OBMessageUtils.parseTranslation(errorMessage,
          errorParam));
    }
    if (reqStatus.equals(REQUEST_STATUS_SUCCESS)) {
      request.setProcessed(Boolean.TRUE);
      request.setExecutionDate(new Date());
    } else {
      request.setProcessed(Boolean.FALSE);
    }
    request.setResponse(responseMessage);
    request.setStatus(reqStatus);
    OBDal.getInstance().save(request);
  }

  /**
   * Commits the current transaction performing a flush.
   * 
   * @throws OBException
   *           in case there is a SQLException it is thrown as a OBException.
   */
  private void doCommit() throws OBException {
    try {
      OBDal.getInstance().flush();
      SessionHandler.getInstance().getConnection().commit();
    } catch (HibernateException | SQLException e) {
      // Some Database exceptions as constraint checks are thrown as HibernateException
      Throwable ex = DbUtility.getUnderlyingSQLException(e);
      String message = OBMessageUtils.translateError(ex.getMessage()).getMessage();
      throw new OBException(message, e);
    }
  }

  private StringBuilder log = new StringBuilder();

  private String getLog() {
    return log.toString();
  }

  /**
   * Log a message. Messages with the timestamp are set in the Response field of the EDL Request
   * when the request is processed.
   * 
   * @param msg
   *          the message to log
   */
  public void log(String msg) {
    log.append(new Timestamp(System.currentTimeMillis()).toString() + " - " + msg + "\n");
  }

}
