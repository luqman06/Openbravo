//Sqlc generated V1.O00-1
package org.openbravo.erpWindows.org.wirabumi.hris.timeandattendance.AttendanceLeaveDeduction;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import org.openbravo.service.db.QueryTimeOutUtil;
import org.openbravo.database.SessionInfo;
import java.util.*;

/**
WAD Generated class
 */
class AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData implements FieldProvider {
static Logger log4j = Logger.getLogger(AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData.class);
  private String InitRecordNumber="0";
  public String dummy;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("dummy"))
      return dummy;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData[] dummy(ConnectionProvider connectionProvider)    throws ServletException {
    return dummy(connectionProvider, 0, 0);
  }

  public static AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData[] dummy(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT '' AS dummy from DUAL";

    ResultSet result;
    Vector<AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData> vector = new Vector<AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData>(0);
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());

      result = st.executeQuery();
      long countRecord = 0;
      long countRecordSkip = 1;
      boolean continueResult = true;
      while(countRecordSkip < firstRegister && continueResult) {
        continueResult = result.next();
        countRecordSkip++;
      }
      while(continueResult && result.next()) {
        countRecord++;
        AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData objectAkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData = new AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData();
        objectAkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData.dummy = UtilSql.getValue(result, "dummy");
        objectAkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectAkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData);
        if (countRecord >= numberRegisters && numberRegisters != 0) {
          continueResult = false;
        }
      }
      result.close();
    } catch(SQLException e){
      if (log4j.isDebugEnabled()) {
        log4j.error("SQL error in query: " + strSql, e);
      } else {
        log4j.error("SQL error in query: " + strSql + " :" + e);
      }
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      if (log4j.isDebugEnabled()) {
        log4j.error("Exception in query: " + strSql, ex);
      } else {
        log4j.error("Exception in query: " + strSql + " :" + ex);
      }
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData objectAkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData[] = new AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData[vector.size()];
    vector.copyInto(objectAkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData);
    return(objectAkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData);
  }

  public static int updateDocAction(ConnectionProvider connectionProvider, String docaction, String taAkumulasiPotCutiId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE ta_akumulasi_pot_cuti" +
      "        SET docaction = ? " +
      "        WHERE ta_akumulasi_pot_cuti.TA_Akumulasi_Pot_Cuti_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, docaction);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, taAkumulasiPotCutiId);

      SessionInfo.saveContextInfoIntoDB(connectionProvider.getConnection());
      updateCount = st.executeUpdate();
    } catch(SQLException e){
      if (log4j.isDebugEnabled()) {
        log4j.error("SQL error in query: " + strSql, e);
      } else {
        log4j.error("SQL error in query: " + strSql + " :" + e);
      }
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      if (log4j.isDebugEnabled()) {
        log4j.error("Exception in query: " + strSql, ex);
      } else {
        log4j.error("Exception in query: " + strSql + " :" + ex);
      }
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }
}
