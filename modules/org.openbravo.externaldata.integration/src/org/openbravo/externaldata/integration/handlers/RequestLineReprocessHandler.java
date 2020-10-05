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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.client.application.process.BaseProcessActionHandler;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.externaldata.integration.OBEDLRequestLine;
import org.openbravo.externaldata.integration.process.ProcessRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link BaseProcessActionHandler} to manually schedule selected EDL Request
 * Lines.
 */
public class RequestLineReprocessHandler extends BaseActionHandler {

  private static final Logger log = LoggerFactory.getLogger(RequestLineReprocessHandler.class);

  private static final String STATUS_ERROR = "Error";
  private static final String UNEXPECTED_ERROR_KEY = "obedl_unexpected_error";

  @Inject
  ProcessRequest<?, ?> processRequest;

  /**
   * Gets the list of EDL Request Line ids and executes them calling the {@link
   * ProcessRequest.#processRequestLine(OBEDLRequestLine)} method.
   */
  @Override
  protected JSONObject execute(Map<String, Object> parameters, String content) {
    int errorCount = 0;
    try {
      OBContext.setAdminMode(true);
      JSONObject request = new JSONObject(content);
      final JSONArray requestLineIds = request.getJSONArray("requestLines");
      List<String> idList = new ArrayList<String>();
      for (int i = 0; i < requestLineIds.length(); i++) {
        String requestLineId = requestLineIds.getString(i);

        // Execute process and prepare an array with actions to be executed after execution
        OBEDLRequestLine reqLine = OBDal.getInstance().get(OBEDLRequestLine.class, requestLineId);
        processRequest.processRequestLine(reqLine);
        idList.add(requestLineId);
      }
      OBCriteria<OBEDLRequestLine> criteria = OBDal.getInstance().createCriteria(
          OBEDLRequestLine.class);
      criteria.add(Restrictions.eq(OBEDLRequestLine.PROPERTY_STATUS, STATUS_ERROR));
      criteria.add(Restrictions.in(OBEDLRequestLine.PROPERTY_ID, idList));
      errorCount = criteria.count();
    } catch (JSONException e) {
      String errorMessage = OBMessageUtils.messageBD(UNEXPECTED_ERROR_KEY);
      throw new OBException(errorMessage, e);
    } finally {
      OBContext.restorePreviousMode();
    }
    return createResponse(errorCount);
  }

  /**
   * Creates the JSONObject with the response.
   * 
   * @param errorCount
   *          Number of Request Lines in Error status, based on this number a Success or Error
   *          response is built.
   */
  private JSONObject createResponse(int errorCount) {
    // Result treatment
    JSONObject result = new JSONObject();
    JSONObject finalResult = new JSONObject();
    try {
      result.put("message", finalResult);
      if (errorCount == 0) {
        finalResult.put("severity", "success");
        finalResult.put("title", "");
        finalResult.put("text", OBMessageUtils.messageBD("Success"));
      } else {
        String errorMessage = OBMessageUtils.messageBD("obedl_errors_reprocessing");
        Map<String, String> errorParam = new HashMap<String, String>();
        errorParam.put("ErrorRecordNumber", String.valueOf(errorCount));
        String returningError = OBMessageUtils.parseTranslation(errorMessage, errorParam);
        finalResult.put("severity", "error");
        finalResult.put("title", OBMessageUtils.messageBD("Error"));
        finalResult.put("text", returningError);
      }
      JSONObject actions = new JSONObject();
      actions.put("refreshGrid", new JSONObject());
      result.put("responseActions", actions);
    } catch (JSONException e) {
      log.error("Error returning error message", e);
    }
    return result;
  }
}
