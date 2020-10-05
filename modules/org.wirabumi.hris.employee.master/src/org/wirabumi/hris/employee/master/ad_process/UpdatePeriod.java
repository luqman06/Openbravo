package org.wirabumi.hris.employee.master.ad_process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessLogger;
import org.openbravo.service.db.DalBaseProcess;

public class UpdatePeriod extends DalBaseProcess {
  private static Logger log4j = Logger.getLogger(UpdatePeriod.class);
  private ProcessLogger logger;
  private static int counter = 0;

  public void doExecute(ProcessBundle bundle) throws Exception {
    // this logger logs into the LOG column of the AD_PROCESS_RUN database table
    logger = bundle.getLogger();
    logger.log("Starting background Period Employee. Loop " + counter + "\n");

    try {
      Connection connectionProvider = OBDal.getInstance().getConnection();
      updatePeriod(connectionProvider);
      connectionProvider.close();
    } catch (Exception e) {
      log4j.error(e.getMessage(), e);
    }
  }

  private void updatePeriod(Connection connectionProvider) {
    String strSql;
    PreparedStatement st = null;

    //--Update Period Employee (PC)
    try {
      strSql = "update c_bpartner set em_hris_year=coalesce(date_part('year',age(em_hris_joindate)),0), em_hris_month=coalesce(date_part('month',age(em_hris_joindate)),0), updated=TO_DATE(NOW()) where isemployee='Y' and isactive='Y'";
      st = connectionProvider.prepareStatement(strSql);
      st.executeUpdate();

    } catch (SQLException e) {
      log4j.error("SQLException:" + e);
    }
  }
}
