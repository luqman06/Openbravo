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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.NotImplementedException;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.application.attachment.AttachImplementationManager;
import org.openbravo.client.application.window.ApplicationDictionaryCachedStructures;
import org.openbravo.client.kernel.ComponentProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.OBEDLProcess;
import org.openbravo.externaldata.integration.OBEDLRequest;
import org.openbravo.model.ad.utility.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to be extended by EDL Type of Data implementations. It is in charge of managing the data to
 * be processed by each EDL Request.
 */
@Dependent
public abstract class DataProcessor<U> {

  protected static final String STORAGE_LOCATION_ATTACHMENT = "A";
  protected static final String STORAGE_LOCATION_DATABASE = "DB";
  protected static final String STORAGE_LOCATION_NOT_STORED = "NS";
  private static final String FILENAME = "Attachment";
  private static final String REQUEST_TABLE_ID = "C0F82AE97B764C7B9F700396D5E89A26";
  private static final String REQUEST_TAB_ID = "7ED5CD293A57489B84C7629A975546E1";
  private static final String CORE_ATTACH_DESC_PARAMETER = "E22E8E3B737D4A47A691A073951BBF16";

  private static final Logger log = LoggerFactory.getLogger(DataProcessor.class);

  protected Object rawData;
  protected JSONObject contextData;
  protected OBEDLRequest request;
  protected boolean hasInput;
  protected String storageLocation;
  protected ProcessDataProcessor<U> processDataProcessor;

  @Inject
  @Any
  private Instance<CustomStorageLocation> customStorageLocations;

  @Inject
  private AttachImplementationManager aim;

  @Inject
  private ApplicationDictionaryCachedStructures adcs;

  /**
   * Method to initialize the fields with the instances used in this {@link ProcessRequest}
   */
  public void init(OBEDLProcess process, OBEDLRequest _request,
      ProcessDataProcessor<U> _processDataProcessor) {
    hasInput = process.isHasInput();
    storageLocation = process.getStorageLocation();
    request = _request;
    processDataProcessor = _processDataProcessor;
  }

  /**
   * Method to get the initial data
   * 
   * @return the raw data as it is received.
   */
  public Object getRawData() {
    return rawData;
  }

  /**
   * Method to set the initial data
   * 
   * @param rawData
   *          the requests' initial data as set in addRequest
   * @throws IOException
   *           when the process to save the data in the storage location fails.
   */
  public void initializeRawData(Object _rawData) throws IOException {
    if (processDataProcessor != null) {
      this.rawData = processDataProcessor.initializeRawData(_rawData);
    } else {
      this.rawData = _rawData;
    }
    if (!STORAGE_LOCATION_NOT_STORED.equals(storageLocation)) {
      saveData();
    }
  }

  /**
   * Initializes the rawData field loading the data from the storage location.
   */
  public void loadStoredData() {
    if (STORAGE_LOCATION_ATTACHMENT.equals(storageLocation)) {
      File attFile = downloadAttachmentFile(request.getId());
      rawData = attFile;
    } else if (STORAGE_LOCATION_DATABASE.equals(storageLocation)) {
      rawData = request.getRequestData();
    } else {
      CustomStorageLocation customStorageLocation = customStorageLocations.select(
          new ComponentProvider.Selector(storageLocation)).get();
      if (customStorageLocation == null) {
        log.error("Custom storage location implementation not found: {}", storageLocation);
        throw new NotImplementedException();
      }
      rawData = customStorageLocation.loadStoredDataCustom(request);
    }
  }

  /**
   * Method called on reprocess request to delete temp file created when the storage location is
   * Attachment. If the storage location is custom the custom implementation to delete temp data is
   * called in case is necessary.
   */
  public void deleteTempData() {
    if (STORAGE_LOCATION_ATTACHMENT.equals(storageLocation)) {
      FileUtils.deleteQuietly((File) rawData);
    } else if (STORAGE_LOCATION_DATABASE.equals(storageLocation)) {
      // Nothing to delete
    } else {
      CustomStorageLocation customStorageLocation = customStorageLocations.select(
          new ComponentProvider.Selector(storageLocation)).get();
      if (customStorageLocation == null) {
        log.error("Custom storage location implementation not found: {}", storageLocation);
        throw new NotImplementedException();
      }
      customStorageLocation.deleteTempData(request);
    }
  }

  /**
   * Converts the raw data received by the request to the format defined by the Type of Data of the
   * EDL Process.
   */
  public abstract U getDataFromRaw();

  /**
   * Downloads the file attached for the EDL Request when the Database Storage Location is used.
   * 
   * @param requestId
   *          the id of the EDL Request.
   * @return a File with the stored raw data.
   */
  private File downloadAttachmentFile(String requestId) throws OBException {
    OBCriteria<Attachment> attachCrit = OBDal.getInstance().createCriteria(Attachment.class);
    attachCrit.add(Restrictions.eq(Attachment.PROPERTY_TABLE, adcs.getTable(REQUEST_TABLE_ID)));
    attachCrit.add(Restrictions.eq(Attachment.PROPERTY_RECORD, requestId));
    attachCrit.add(Restrictions.eq(Attachment.PROPERTY_NAME, FILENAME));
    Attachment attach = null;
    try {
      attach = (Attachment) attachCrit.uniqueResult();
    } catch (HibernateException e) {
      throw new OBException(OBMessageUtils.messageBD("obedl_multiple_attachments"), e);
    }
    if (attach == null) {
      throw new OBException(OBMessageUtils.messageBD("obedl_no_attachments"));
    }
    File file;
    try {
      file = createAttachmentFile();
      aim.download(attach.getId(), new FileOutputStream(file));
    } catch (IOException e1) {
      throw new OBException(OBMessageUtils.messageBD("obedl_no_attachments"));
    }

    return file;
  }

  /**
   * Loads the contextData field by calling the {@link ProcessDataProcessor.#loadContextData()}
   * method.
   */
  protected void loadContextData() {
    if (processDataProcessor != null) {
      contextData = processDataProcessor.loadContextData();
    }
    if (contextData == null) {
      contextData = new JSONObject();
    }
  }

  /**
   * Returns the JSONObject with the context data. In this JSONObject EDL Processes can load data
   * common to all the items to be processed in the EDL Request.
   */
  public JSONObject getContextData() {
    if (contextData == null) {
      loadContextData();
    }
    return contextData;
  }

  /**
   * Method that saves the initial data based on the defined storage location.
   */
  protected void saveData() throws IOException {
    if (STORAGE_LOCATION_ATTACHMENT.equals(storageLocation)) {
      File attFile = createAttachmentFile();
      try {
        if (rawData instanceof File) {
          FileUtils.copyFile((File) rawData, attFile);
        } else if (rawData instanceof String) {
          FileUtils.writeStringToFile(attFile, (String) rawData);
        } else if (rawData instanceof InputStream) {
          FileUtils.copyInputStreamToFile((InputStream) rawData, attFile);
        } else if (rawData instanceof JSONArray || rawData instanceof JSONObject) {
          FileUtils.writeStringToFile(attFile, rawData.toString());
        } else {
          writeRawDataToFile(attFile);
        }

        // Add Core's default desc parameter id with textForAttachment for backwards compatibility
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put(CORE_ATTACH_DESC_PARAMETER,
            OBMessageUtils.messageBD("obedl_attach_description"));

        aim.upload(requestParams, REQUEST_TAB_ID, request.getId(), request.getOrganization()
            .getId(), attFile);
      } finally {
        // Always delete temp file with raw data.
        FileUtils.deleteQuietly(attFile);
      }
    } else if (STORAGE_LOCATION_DATABASE.equals(storageLocation)) {
      String requestDataStr = null;
      if (rawData instanceof String) {
        requestDataStr = (String) rawData;
      } else if (rawData instanceof JSONArray || rawData instanceof JSONObject) {
        requestDataStr = rawData.toString();
      } else {
        requestDataStr = getRawDataAsString();
      }
      request.setRequestData(requestDataStr);
    } else {
      CustomStorageLocation customStorageLocation = customStorageLocations.select(
          new ComponentProvider.Selector(storageLocation)).get();
      if (customStorageLocation == null) {
        log.error("Custom storage location implementation not found: {}", storageLocation);
        throw new NotImplementedException();
      }
      customStorageLocation.saveRawData(rawData, request);
    }
  }

  /**
   * Method that creates a File in the core attachment folder for a EDL Request
   * 
   * @param requestId
   *          Id of the request from where the attachment will be created
   * @return the attachment as a file
   */
  private File createAttachmentFile() throws IOException {
    File attachment = new File(System.getProperty("java.io.tmpdir") + File.separator + FILENAME);
    return attachment;
  }

  /**
   * Writes in the attFile parameter the raw data. This method has to be implemented in case the raw
   * data is not an instance of File, String, InputStream, JSONObject or JSONArray and the Storage
   * Location is Attachment.
   * 
   * @param attFile
   *          Empty file where raw data is written.
   */
  protected void writeRawDataToFile(File attFile) {
    if (processDataProcessor == null || !processDataProcessor.isWriteRawDataToFileImplemented()) {
      log.error("Unable to save rawData into a file.");
      log.error("rawData instanceof {}", rawData.getClass());
      log.error("Content of rawData {}", rawData);
      throw new NotImplementedException();
    }
    processDataProcessor.writeRawDataToFile(attFile);
  }

  /**
   * Returns the rawData converted to String. This method can be implemented in case the initial
   * data is not a String, JSONObject or JSONArray and the Storage Location is Database.
   */
  protected String getRawDataAsString() {
    if (processDataProcessor != null && processDataProcessor.isGetRawDataAsStringImplemented()) {
      return processDataProcessor.getRawDataAsString();
    }
    log.error("Unable to parse rawData to a String.");
    log.error("rawData instanceof {}", rawData.getClass());
    log.error("Content of rawData {}", rawData);
    throw new NotImplementedException();
  }

}
