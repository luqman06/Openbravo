/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.enterprise.context.Dependent;
import javax.servlet.ServletException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.kernel.ComponentProvider.Qualifier;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.OBEDLConfigOutput;
import org.openbravo.utils.FormatUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that implements the output data processor for a Rest WebService.
 *
 */
@Dependent
@Qualifier("WSRest")
public class OutputWSRestProcessor extends OutputDataProcessor {

  private static final Logger log = LoggerFactory.getLogger(OutputWSRestProcessor.class);

  private HttpURLConnection connection;
  private OutputStream outputStream;

  /**
   * Opens the connection using the configuration of the EDL Config Output. It returns the writer of
   * the WebService's OutputStream.
   */
  @Override
  protected Writer obtainWriter(OBEDLConfigOutput output) {
    Writer result = null;
    try {
      URL url = new URL(output.getPath());
      connection = (HttpURLConnection) url.openConnection();
      if (StringUtils.isNotEmpty(output.getUser()) && StringUtils.isNotEmpty(output.getPassword())) {
        String password = "";
        try {
          password = FormatUtilities.encryptDecrypt(output.getPassword(), false);
        } catch (ServletException e) {
          log.error("Error decrypting password", e);
        }
        String userPassword = output.getUser() + ":" + password;
        byte[] encoding = Base64.encodeBase64(userPassword.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + (new String(encoding)));
      }
      connection.setRequestMethod(output.getWebServiceMethod());
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setDoOutput(true);
      connection.connect();
      outputStream = connection.getOutputStream();

      result = new BufferedWriter(new OutputStreamWriter(outputStream));
    } catch (IOException e) {
      String errorMessage = OBMessageUtils.messageBD(ERROR_OBTAINING_WRITER);
      log.error(errorMessage + " " + output.getPath());
      throw new OBException(errorMessage + " " + output.getPath(), e);
    }
    return result;
  }

  /**
   * Returns the InputStream containing the response of the WebService call.
   */
  public InputStream getWSInputStream() throws IOException {
    if (connection.getResponseCode() != 200) {
      return connection.getErrorStream();
    }
    return connection.getInputStream();
  }

  /**
   * Closes the writer and the outputStream of the WebService call opened in hte obtainWriter
   * method.
   * 
   * @see OutputDataProcessor#closeWriter(Writer)
   */
  @Override
  protected void closeWriter(Writer writer) {
    try {
      writer.close();
      outputStream.close();
    } catch (IOException e) {
      String errorMessage = OBMessageUtils.messageBD(ERROR_CLOSING_RESOURCES);
      log.error(errorMessage);
      throw new OBException(errorMessage, e);
    }
  }

  /**
   * It reads the response code of the WebService call to throw a OBException if the code is
   * different than 200
   * 
   * @see org.openbravo.externaldata.integration.process.OutputDataProcessor#closeResources()
   */
  @Override
  protected void closeResources() {
    try {
      if (connection.getResponseCode() != 200) {
        throw new OBException(connection.getResponseMessage());
      }
    } catch (IOException e) {
      log.error("Error closing resources", e);
      throw new OBException(OBMessageUtils.messageBD(ERROR_CLOSING_RESOURCES), e);
    } finally {
      connection.disconnect();
    }

  }

  /**
   * (non-Javadoc)
   * 
   * @see org.openbravo.externaldata.integration.process.OutputDataProcessor#storeSendingData()
   */
  @Override
  protected boolean storeSendingData() {
    return true;
  }

}
