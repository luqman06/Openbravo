/*
 ************************************************************************************
 * Copyright (C) 2016-2017 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.process;

import javax.enterprise.context.ApplicationScoped;

import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.weld.WeldUtils;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.core.SessionHandler;
import org.openbravo.dal.service.OBDal;
import org.openbravo.externaldata.integration.OBEDLRequestLine;
import org.openbravo.service.importprocess.ImportEntry;
import org.openbravo.service.importprocess.ImportEntryManager;
import org.openbravo.service.importprocess.ImportEntryManager.ImportEntryQualifier;
import org.openbravo.service.importprocess.ImportEntryProcessor;
import org.openbravo.service.json.JsonConstants;

/**
 * Class implementing {@link ImportEntryProcessor} to execute the asynchronous request Import
 * Entries identified by "OBEDL_Request" qualifier.
 */
@ImportEntryQualifier(entity = "OBEDL_Request")
@ApplicationScoped
public class EDLImportEntryProcessor extends ImportEntryProcessor {

  /**
   * @see org.openbravo.service.importprocess.ImportEntryProcessor#createImportEntryProcessRunnable()
   */
  @Override
  protected ImportEntryProcessRunnable createImportEntryProcessRunnable() {
    return WeldUtils.getInstanceFromStaticBeanManager(EDLEntryProcessRunnable.class);
  }

  /**
   * @see org.openbravo.service.importprocess.ImportEntryProcessor#canHandleImportEntry(org.openbravo.service.importprocess.ImportEntry)
   */
  @Override
  protected boolean canHandleImportEntry(ImportEntry importEntryInformation) {
    return "OBEDL_Request".equals(importEntryInformation.getTypeofdata());
  }

  /**
   * @see org.openbravo.service.importprocess.ImportEntryProcessor#getProcessSelectionKey(org.openbravo.service.importprocess.ImportEntry)
   */
  @Override
  protected String getProcessSelectionKey(ImportEntry importEntry) {
    // EDL Request lines can be executed in parallel by default. Use as much as 20 runnables in
    // parallel based on the mod 20 of the creation timestamp.
    long mod = importEntry.getCreationDate().getTime() % 20L;
    return Long.toString(mod);
  }

  /**
   * {@link ImportEntryProcessRunnable} implementation for EDL.
   */
  protected static class EDLEntryProcessRunnable extends ImportEntryProcessRunnable {

    /**
     * Executes the RequestLine related to the ImportEntry. The status of the Import Entry is set
     * based on the response of the RequestLine process.
     * 
     * @see org.openbravo.service.importprocess.ImportEntryProcessor.ImportEntryProcessRunnable#processEntry(org.openbravo.service.importprocess.ImportEntry)
     */
    @Override
    protected void processEntry(ImportEntry importEntry) throws Exception {
      OBContext.setAdminMode(true);
      try {
        ProcessRequest<?, ?> request = ProcessRequest.getProcessRequestInstance();
        OBEDLRequestLine requestLine = OBDal.getInstance().get(OBEDLRequestLine.class,
            importEntry.getId());
        JSONObject response = request.processRequestLine(requestLine);

        if (response.getBoolean(JsonConstants.RESPONSE_ERROR)) {
          ImportEntryManager.getInstance().setImportEntryError(importEntry.getId(),
              new Throwable(response.getString(JsonConstants.RESPONSE_ERRORMESSAGE)));
        } else {
          ImportEntryManager.getInstance().setImportEntryProcessed(importEntry.getId());
        }

        if (SessionHandler.isSessionHandlerPresent()) {
          OBDal.getInstance().commitAndClose();
        }
      } catch (OBException e) {
        ImportEntryManager.getInstance().setImportEntryError(importEntry.getId(), e);
      } finally {
        OBContext.restorePreviousMode();
      }
    }
  }
}
