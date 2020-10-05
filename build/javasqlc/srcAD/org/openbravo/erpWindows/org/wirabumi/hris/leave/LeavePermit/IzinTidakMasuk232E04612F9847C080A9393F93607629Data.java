//Sqlc generated V1.O00-1
package org.openbravo.erpWindows.org.wirabumi.hris.leave.LeavePermit;

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
class IzinTidakMasuk232E04612F9847C080A9393F93607629Data implements FieldProvider {
static Logger log4j = Logger.getLogger(IzinTidakMasuk232E04612F9847C080A9393F93607629Data.class);
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

  public static IzinTidakMasuk232E04612F9847C080A9393F93607629Data[] dummy(ConnectionProvider connectionProvider)    throws ServletException {
    return dummy(connectionProvider, 0, 0);
  }

  public static IzinTidakMasuk232E04612F9847C080A9393F93607629Data[] dummy(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT '' AS dummy from DUAL";

    ResultSet result;
    Vector<IzinTidakMasuk232E04612F9847C080A9393F93607629Data> vector = new Vector<IzinTidakMasuk232E04612F9847C080A9393F93607629Data>(0);
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
        IzinTidakMasuk232E04612F9847C080A9393F93607629Data objectIzinTidakMasuk232E04612F9847C080A9393F93607629Data = new IzinTidakMasuk232E04612F9847C080A9393F93607629Data();
        objectIzinTidakMasuk232E04612F9847C080A9393F93607629Data.dummy = UtilSql.getValue(result, "dummy");
        objectIzinTidakMasuk232E04612F9847C080A9393F93607629Data.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectIzinTidakMasuk232E04612F9847C080A9393F93607629Data);
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
    IzinTidakMasuk232E04612F9847C080A9393F93607629Data objectIzinTidakMasuk232E04612F9847C080A9393F93607629Data[] = new IzinTidakMasuk232E04612F9847C080A9393F93607629Data[vector.size()];
    vector.copyInto(objectIzinTidakMasuk232E04612F9847C080A9393F93607629Data);
    return(objectIzinTidakMasuk232E04612F9847C080A9393F93607629Data);
  }

  public static int updateDocAction(ConnectionProvider connectionProvider, String docaction, String lvTidakmasukId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE lv_tidakmasuk" +
      "        SET docaction = ? " +
      "        WHERE lv_tidakmasuk.LV_Tidakmasuk_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, docaction);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, lvTidakmasukId);

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
