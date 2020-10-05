/*
 ************************************************************************************
 * Copyright (C) 2016 Openbravo S.L.U.
 * Licensed under the Openbravo Commercial License version 1.0
 * You may obtain a copy of the License at http://www.openbravo.com/legal/obcl.html
 * or in the legal folder of this module distribution.
 ************************************************************************************
 */
package org.openbravo.externaldata.integration.handlers;

import org.openbravo.dal.service.OBDal;
import org.openbravo.externaldata.integration.OBEDLRequestLine;
import org.openbravo.service.importprocess.ImportEntry;
import org.openbravo.service.importprocess.ImportEntryArchive;
import org.openbravo.service.importprocess.ImportEntryArchivePreProcessor;

/**
 * ImportEntryArchivePreProcessor implementation to update request lines archived import entry field
 * when the original import entry is archived.
 */
public class RequestLineArchiveProcessor extends ImportEntryArchivePreProcessor {

  /**
   * Loads the Request Line related to the deleted import entry to set the archived import entry
   * field with the created one.
   */
  public void beforeArchive(ImportEntry source, ImportEntryArchive target) {
    // The request line and its import entry share the same id.
    if ("OBEDL_Request".equals(source.getTypeofdata())) {
      OBEDLRequestLine reqLine = OBDal.getInstance().get(OBEDLRequestLine.class, source.getId());
      if (reqLine != null) {
        reqLine.setArchiveImportEntry(target);
      }
    }
  }
}
