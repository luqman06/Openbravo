/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import java.io.IOException;
import java.io.Writer;

import javax.enterprise.context.Dependent;

import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.OBEDLConfigOutput;
import org.openbravo.externaldata.integration.OBEDLRequestLine;
import org.openbravo.service.importprocess.ImportEntry;

/**
 * Class to be extended by each EDL Process. It is in charge of implementing the logic needed to
 * process each item.
 *
 * @param <T>
 *          the Type a Item defined by the Type of Data of the EDL Process.
 */
@Dependent
public abstract class ItemProcessor<T> {
  protected StringBuilder directResponse = new StringBuilder();
  protected JSONObject contextData;

  public void setContextData(JSONObject contextData) {
    this.contextData = contextData;
  }

  /**
   * Method in charge of processing each item.
   */
  protected abstract void processItem(T item) throws OBException;

  /**
   * Asynchronous execution process a number of items configured in the EDL Process before
   * committing them. In case of exception a rollback is done. This method determines if the
   * processes that have not been committed need to be processed again or not.
   * 
   * @return TRUE if the items have to be reprocessed.
   */
  protected abstract boolean reprocessNotCommitedItemsOnException();

  /**
   * Synchronous executions can implement a direct response. This method appends to the Writer the
   * response stored in the directResponse field.
   * 
   * @throws OBException
   *           In case of IOException writing the response it is thrown as a OBException.
   */
  public void writeDirectResponse(Writer writer) throws OBException {
    try {
      writer.append(directResponse.toString());
    } catch (IOException e) {
      throw new OBException(OBMessageUtils.messageBD("obedl_error_direct_response_writer"), e);
    }
  }

  /**
   * Method to update import entry with custom fields.
   */
  protected void updateCustomImportEntry(ImportEntry importEntry, OBEDLRequestLine newRequestLine) {
    // Not implemented by default
  }

  /**
   * Method called from the {@link OutputDataProcessor} when there is something more to do with the
   * output, for example reading the response of a webservice request.
   * 
   * @param outputDataProcessor
   *          The data processor that is being processed.
   * @param output
   *          The configuration that is being processed.
   */
  public void outputExtraActions(OutputDataProcessor outputDataProcessor, OBEDLConfigOutput output) {
    // Not implemented by default
  }

  /**
   * Method called from the {@link OutputDataProcessor} to write the response.
   * 
   * @param writer
   *          the writer configured and opened. It must remain opened when this method has finished.
   * @param configOutput
   *          configuration of the output processing. Null is the
   */
  public void writeResponse(Writer writer, OBEDLConfigOutput configOutput) throws IOException {
    // Not implemented by default
  };
}
