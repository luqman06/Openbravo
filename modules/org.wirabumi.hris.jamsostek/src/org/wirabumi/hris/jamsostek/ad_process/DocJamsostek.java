package org.wirabumi.hris.jamsostek.ad_process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.openbravo.service.db.DalBaseProcess;

public class DocJamsostek extends DalBaseProcess {
  private static Logger log4j = Logger.getLogger(DocJamsostek.class);
  private ProcessLogger logger;
  private static int counter = 0;

  public void doExecute(ProcessBundle bundle) throws Exception {
    // this logger logs into the LOG column of the AD_PROCESS_RUN database table
    logger = bundle.getLogger();
    logger.log("Starting background Calculate Jamsostek. Loop " + counter + "\n");

    try {
      Connection connectionProvider = OBDal.getInstance().getConnection();
      calculateJamsostek(connectionProvider);
      connectionProvider.close();
    } catch (Exception e) {
      log4j.error(e.getMessage(), e);
    }
  }

  private void calculateJamsostek(Connection connectionProvider) {
    String strSql;
    ResultSet result;
    PreparedStatement st = null;

    //--Loop jamsostek (JST)
    try {
      strSql = "SELECT result FROM jst_calculate_jamsostek(null)";
      st = connectionProvider.prepareStatement(strSql);
      result = st.executeQuery();
      int countRecord = 0;

      result.close();
    } catch (SQLException e) {
      log4j.error("SQLException:" + e);
    }
  }
}
