/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.handlers;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.client.application.process.BaseProcessActionHandler;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.process.ProcessRequest;
import org.openbravo.service.json.JsonConstants;

/**
 * Implementation of {@link BaseProcessActionHandler} to manually execute a EDL Request.
 */
public class RequestReprocessHandler extends BaseProcessActionHandler {
  private static final Logger log = Logger.getLogger(RequestReprocessHandler.class);
  private static final String ERROR_ONLY_PARAM = "errorsOnly";

  /**
   * Gets the EDL Request id and the errorsOnly flag and calls the {@link
   * ProcessRequest.#reprocessRequest(String, boolean)} method.
   */
  @Override
  protected JSONObject doExecute(Map<String, Object> parameters, String content) {
    try {
      JSONObject result = new JSONObject();

      JSONObject request = new JSONObject(content);
      String requestId = request.getString("Obedl_Request_ID");
      JSONObject params = request.getJSONObject("_params");
      boolean errorsOnly = params.optBoolean(ERROR_ONLY_PARAM, false);
      ProcessRequest<?, ?> processRequest = ProcessRequest.getProcessRequestInstance();
      JSONObject responseJson = processRequest.reprocessRequest(requestId, errorsOnly);
      JSONObject viewMsg = new JSONObject();
      if (responseJson.getBoolean(JsonConstants.RESPONSE_ERROR)) {
        viewMsg.put("msgType", "error");
      } else {
        viewMsg.put("msgType", "success");
      }
      viewMsg.put("msgTitle", OBMessageUtils.messageBD("obedl_reprocess_title"));
      viewMsg.put("msgText", responseJson.getString(JsonConstants.RESPONSE_ERRORMESSAGE));

      JSONObject viewMsgAction = new JSONObject();
      viewMsgAction.put("showMsgInView", viewMsg);
      // Execute process and prepare an array with actions to be executed after execution
      JSONArray actions = new JSONArray();
      actions.put(viewMsgAction);
      result.put("responseActions", actions);

      return result;
    } catch (JSONException e) {
      log.error("Error in process", e);
      return new JSONObject();
    }
  }

}
