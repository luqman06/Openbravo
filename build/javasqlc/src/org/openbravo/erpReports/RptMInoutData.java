//Sqlc generated V1.O00-1
package org.openbravo.erpReports;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import org.openbravo.service.db.QueryTimeOutUtil;
import org.openbravo.database.SessionInfo;
import java.util.*;

class RptMInoutData implements FieldProvider {
static Logger log4j = Logger.getLogger(RptMInoutData.class);
  private String InitRecordNumber="0";
  public String mInoutId;
  public String documentcopies;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("m_inout_id") || fieldName.equals("mInoutId"))
      return mInoutId;
    else if (fieldName.equalsIgnoreCase("documentcopies"))
      return documentcopies;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static RptMInoutData[] select(ConnectionProvider connectionProvider, String mInoutId)    throws ServletException {
    return select(connectionProvider, mInoutId, 0, 0);
  }

  public static RptMInoutData[] select(ConnectionProvider connectionProvider, String mInoutId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT M_INOUT.M_INOUT_ID, C_DOCTYPE.DOCUMENTCOPIES" +
      "        FROM M_INOUT left join  C_DOCTYPE on M_INOUT.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID" +
      "        WHERE 1=1 ";
    strSql = strSql + ((mInoutId==null || mInoutId.equals(""))?"":"           AND M_INOUT.M_INOUT_ID IN          " + mInoutId);
    strSql = strSql + 
      "        ORDER BY M_INOUT.DOCUMENTNO ASC";

    ResultSet result;
    Vector<RptMInoutData> vector = new Vector<RptMInoutData>(0);
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      if (mInoutId != null && !(mInoutId.equals(""))) {
        }

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
        RptMInoutData objectRptMInoutData = new RptMInoutData();
        objectRptMInoutData.mInoutId = UtilSql.getValue(result, "m_inout_id");
        objectRptMInoutData.documentcopies = UtilSql.getValue(result, "documentcopies");
        objectRptMInoutData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectRptMInoutData);
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
    RptMInoutData objectRptMInoutData[] = new RptMInoutData[vector.size()];
    vector.copyInto(objectRptMInoutData);
    return(objectRptMInoutData);
  }

  public static String selectDocumentcopies(ConnectionProvider connectionProvider, String mInoutId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT COALESCE(C_DOCTYPE.DOCUMENTCOPIES, 1) AS DOCUMENTCOPIES" +
      "        FROM M_INOUT left join C_DOCTYPE on  M_INOUT.C_DOCTYPE_ID = C_DOCTYPE.C_DOCTYPE_ID" +
      "        WHERE M_INOUT.M_INOUT_ID = ?";

    ResultSet result;
    String strReturn = "0";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, mInoutId);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "documentcopies");
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
    return(strReturn);
  }

  public static RptMInoutData[] selectNumCopies(ConnectionProvider connectionProvider, String rownum)    throws ServletException {
    return selectNumCopies(connectionProvider, rownum, 0, 0);
  }

  public static RptMInoutData[] selectNumCopies(ConnectionProvider connectionProvider, String rownum, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "          SELECT VALUE AS M_INOUT_ID" +
      "          FROM AD_INTEGER" +
      "          WHERE VALUE <= ?";

    ResultSet result;
    Vector<RptMInoutData> vector = new Vector<RptMInoutData>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, rownum);

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
        RptMInoutData objectRptMInoutData = new RptMInoutData();
        objectRptMInoutData.mInoutId = UtilSql.getValue(result, "m_inout_id");
        objectRptMInoutData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectRptMInoutData);
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
    RptMInoutData objectRptMInoutData[] = new RptMInoutData[vector.size()];
    vector.copyInto(objectRptMInoutData);
    return(objectRptMInoutData);
  }
}
