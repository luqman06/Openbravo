package org.wirabumi.hris.timeandattendance.erpCommon.ad_process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.openbravo.service.db.DalBaseProcess;

public class DocAkumulasiPCuti extends DalBaseProcess {
  private static Logger log4j = Logger.getLogger(DocAkumulasiPCuti.class);
  private ProcessLogger logger;
  private static int counter = 0;

  public void doExecute(ProcessBundle bundle) throws Exception {
    // this logger logs into the LOG column of the AD_PROCESS_RUN database table
    logger = bundle.getLogger();
    logger.log("Starting background Window Calculate Potong Gaji. Loop " + counter + "\n");

    try {
      Connection connectionProvider = OBDal.getInstance().getConnection();
      AkumulasiPotongCuti(connectionProvider);
      connectionProvider.close();
    } catch (Exception e) {
      log4j.error(e.getMessage(), e);
    }
  }

  private void AkumulasiPotongCuti(Connection connectionProvider) {
    String strSql;
    ResultSet result;
    PreparedStatement st = null;

    //--Get function kalkulasi potong gaji
    try {
      strSql = "SELECT result FROM ta_kalkulasi_potong_cuti(null, null)";
      st = connectionProvider.prepareStatement(strSql);
      result = st.executeQuery();
      int countRecord = 0;

      result.close();
    } catch (SQLException e) {
      log4j.error("SQLException:" + e);
    }
  }
}