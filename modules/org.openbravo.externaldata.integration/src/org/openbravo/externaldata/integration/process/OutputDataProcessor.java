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
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.externaldata.integration.OBEDLConfigOutput;
import org.openbravo.externaldata.integration.OBEDLOutputContent;
import org.openbravo.externaldata.integration.OBEDLRequest;
import org.openbravo.externaldata.integration.OBEDLRequestLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to be overwritten in order to implement different output data processes.
 */
public abstract class OutputDataProcessor {
  protected static final String ERROR_OBTAINING_WRITER = "obedl_output_writer_error";
  protected static final String ERROR_CLOSING_RESOURCES = "obedl_output_close_resources_error";
  private static final Logger log = LoggerFactory.getLogger(OutputDataProcessor.class);
  protected JSONObject contextData;

  /**
   * Method that does the process of obtaining the writer, writing the response and closing the
   * resources.
   * 
   * @param output
   *          Configuration of the output.
   * @param itemProcessor
   *          Instance of the ItemProcessor used to execute the process
   */
  public final <T> void process(OBEDLConfigOutput output, ItemProcessor<T> itemProcessor,
      OBEDLRequest request, OBEDLRequestLine requestLine) throws IOException {
    try {
      contextData = itemProcessor.contextData;
      Writer writer = obtainWriter(output);
      itemProcessor.writeResponse(writer, output);
      closeWriter(writer);
      itemProcessor.outputExtraActions(this, output);
      closeResources();
    } finally {
      if (storeSendingData()) {
        Writer strWriter = new StringWriter();
        itemProcessor.writeResponse(strWriter, output);
        saveSendingData(request, requestLine, strWriter.toString());
        strWriter.close();
      }
    }
  }

  /**
   * Method that saves the output content that is written on the output processes.
   * 
   * @param request
   *          request related
   * @param requestLine
   *          request line related
   * @param content
   *          content to save
   */
  private void saveSendingData(OBEDLRequest request, OBEDLRequestLine requestLine, String content) {
    log.debug("Saving output content for request {}", request.getIdentifier());
    log.debug("Content: {}", content);
    OBEDLOutputContent outputContent = OBProvider.getInstance().get(OBEDLOutputContent.class);
    outputContent.setEDLRequest(request);
    outputContent.setRequestLine(requestLine);
    outputContent.setExecutionDate(new Date());
    outputContent.setContent(content);
    OBDal.getInstance().save(outputContent);
  }

  /**
   * Method that needs to be implemented in order to get the writer to set the result.
   * 
   * @param output
   *          Configuration of the output.
   * @return The {@link Writer}
   */
  protected abstract Writer obtainWriter(OBEDLConfigOutput output);

  /**
   * Method that has to close only the writer in order to be capable to get some response from the
   * output
   */
  protected abstract void closeWriter(Writer writer);

  /**
   * Method that can be overridden in order to close other resources apart from the writer.
   */
  protected void closeResources() {
  };

  /**
   * Method that has to be overriding to return true in case it is required to store in the database
   * the data that it is written in the output for each request line or request.
   */
  protected boolean storeSendingData() {
    return false;
  }
}
