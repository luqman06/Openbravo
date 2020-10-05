/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.application.process.BaseProcessActionHandler;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.OBEDLRequest;
import org.openbravo.externaldata.integration.process.ProcessRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link BaseProcessActionHandler} to cancel selected EDL Requests.
 */
public class CancelRequestHandler extends BaseActionHandler {

  private static final Logger log = LoggerFactory.getLogger(CancelRequestHandler.class);

  private static final String UNEXPECTED_ERROR_KEY = "obedl_unexpected_error";
  private static final String CANCEL_REQUEST_ERROR = "obedl_cancel_request_errors";
  private static final String MSG_LINE_SEPARATOR = "<br/>";

  @Inject
  ProcessRequest<?, ?> processRequest;

  /**
   * Gets the list of EDL Request ids and cancels them calling the {@link
   * ProcessRequest.#cancelRequest(OBEDLRequest)} method.
   */
  @Override
  protected JSONObject execute(Map<String, Object> parameters, String rawContent) {
    try {
      OBContext.setAdminMode(true);
      JSONObject content = new JSONObject(rawContent);
      final JSONArray requestIds = content.getJSONArray("requests");
      List<String> errors = new ArrayList<String>();
      for (int i = 0; i < requestIds.length(); i++) {
        final String requestId = requestIds.getString(i);
        // Execute process and prepare an array with actions to be executed after execution
        try {
          processRequest.cancelRequest(requestId);
        } catch (OBException e) {
          OBEDLRequest request = OBDal.getInstance().get(OBEDLRequest.class, requestId);
          log.error("Error canceling request", e);
          if (request == null) {
            errors.add(requestId + ": " + e.getMessage());
          } else {
            errors.add(request.getIdentifier() + ": " + e.getMessage());
          }
        }
      }
      if (!errors.isEmpty()) {
        String errorMsg = OBMessageUtils.messageBD(CANCEL_REQUEST_ERROR);
        errorMsg += MSG_LINE_SEPARATOR;
        errorMsg += StringUtils.join(errors, MSG_LINE_SEPARATOR);
        return buildErrorResponse(errorMsg);
      }
    } catch (JSONException e) {
      log.error("Error generating JSONObject", e);
      return buildErrorResponse(OBMessageUtils.messageBD(UNEXPECTED_ERROR_KEY));
    } finally {
      OBContext.restorePreviousMode();
    }
    return createResponse();
  }

  /**
   * Creates the JSONObject with the success response.
   * 
   * @param errorCount
   *          Number of Request Lines in Error status, based on this number a Success or Error
   *          response is built.
   */
  private JSONObject createResponse() {
    // Result treatment
    JSONObject result = new JSONObject();
    JSONObject message = new JSONObject();
    try {
      result.put("message", message);
      message.put("severity", "success");
      message.put("title", "");
      message.put("text", OBMessageUtils.messageBD("Success"));
    } catch (JSONException e) {
      log.error("Error returning error message", e);
    }
    return result;
  }

  /**
   * Creates the JSONObject with the error response.
   * 
   * @param errorMsg
   *          Error message to be shown to the user.
   */
  private JSONObject buildErrorResponse(String errorMsg) {
    JSONObject result = new JSONObject();
    JSONObject message = new JSONObject();
    try {
      result.put("message", message);
      message.put("severity", "error");
      message.put("title", OBMessageUtils.messageBD("Error"));
      message.put("text", errorMsg);
    } catch (JSONException ignore) {
    }
    return result;
  }
}
