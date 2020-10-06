package org.wirabumi.hris.payroll.ad_process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.openbravo.service.db.DalBaseProcess;

public class DocSalaryProrate extends DalBaseProcess {
  private static Logger log4j = Logger.getLogger(DocSalaryProrate.class);
  private ProcessLogger logger;
  private static int counter = 0;

  public void doExecute(ProcessBundle bundle) throws Exception {
    // this logger logs into the LOG column of the AD_PROCESS_RUN database table
    logger = bundle.getLogger();
    logger.log("Starting background Calculate Salary Prorate. Loop " + counter + "\n");

    try {
      Connection connectionProvider = OBDal.getInstance().getConnection();
      SalaryProRate(connectionProvider);
      connectionProvider.close();
    } catch (Exception e) {
      log4j.error(e.getMessage(), e);
    }
  }

  private void SalaryProRate(Connection connectionProvider) {
    String strSql;
    ResultSet result;
    PreparedStatement st = null;

    //--Update salary payment from salary prorate
    try {
      strSql = "select result from pyr_salary_prorate(null)";
      st = connectionProvider.prepareStatement(strSql);
      result = st.executeQuery();
      int countRecord = 0;

      result.close();
    } catch (SQLException e) {
      log4j.error("SQLException:" + e);
    }
  }
}