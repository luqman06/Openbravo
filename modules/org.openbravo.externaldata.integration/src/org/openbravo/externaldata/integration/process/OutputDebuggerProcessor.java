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
import java.io.StringWriter;
import java.io.Writer;

import javax.enterprise.context.Dependent;

import org.openbravo.client.kernel.ComponentProvider.Qualifier;
import org.openbravo.externaldata.integration.OBEDLConfigOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OutputDataProcessor implementation that writes the result of the process in Openbravo Log files
 * with info level.
 *
 */
@Dependent
@Qualifier("Debug")
public class OutputDebuggerProcessor extends OutputDataProcessor {

  private static final Logger log = LoggerFactory.getLogger(OutputDebuggerProcessor.class);

  /**
   * Returns a empty StringWriter
   * 
   * @see org.openbravo.externaldata.integration.process.OutputDataProcessor#obtainWriter(org.openbravo.externaldata.integration.OBEDLConfigOutput)
   */
  @Override
  protected Writer obtainWriter(OBEDLConfigOutput output) {
    return new StringWriter();
  }

  /**
   * Writes the content of the writer in the logger and closes it.
   * 
   * @see org.openbravo.externaldata.integration.process.OutputDataProcessor#closeWriter(java.io.Writer)
   */
  @Override
  protected void closeWriter(Writer writer) {
    StringWriter sw = (StringWriter) writer;
    try {
      log.info(sw.toString());
      sw.close();
    } catch (IOException ignore) {
    }

  }

}
