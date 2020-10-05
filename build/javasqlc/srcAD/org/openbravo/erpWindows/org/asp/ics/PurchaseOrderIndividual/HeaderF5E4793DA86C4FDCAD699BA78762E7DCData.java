//Sqlc generated V1.O00-1
package org.openbravo.erpWindows.org.asp.ics.PurchaseOrderIndividual;

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
class HeaderF5E4793DA86C4FDCAD699BA78762E7DCData implements FieldProvider {
static Logger log4j = Logger.getLogger(HeaderF5E4793DA86C4FDCAD699BA78762E7DCData.class);
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

  public static HeaderF5E4793DA86C4FDCAD699BA78762E7DCData[] dummy(ConnectionProvider connectionProvider)    throws ServletException {
    return dummy(connectionProvider, 0, 0);
  }

  public static HeaderF5E4793DA86C4FDCAD699BA78762E7DCData[] dummy(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT '' AS dummy from DUAL";

    ResultSet result;
    Vector<HeaderF5E4793DA86C4FDCAD699BA78762E7DCData> vector = new Vector<HeaderF5E4793DA86C4FDCAD699BA78762E7DCData>(0);
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
        HeaderF5E4793DA86C4FDCAD699BA78762E7DCData objectHeaderF5E4793DA86C4FDCAD699BA78762E7DCData = new HeaderF5E4793DA86C4FDCAD699BA78762E7DCData();
        objectHeaderF5E4793DA86C4FDCAD699BA78762E7DCData.dummy = UtilSql.getValue(result, "dummy");
        objectHeaderF5E4793DA86C4FDCAD699BA78762E7DCData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectHeaderF5E4793DA86C4FDCAD699BA78762E7DCData);
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
    HeaderF5E4793DA86C4FDCAD699BA78762E7DCData objectHeaderF5E4793DA86C4FDCAD699BA78762E7DCData[] = new HeaderF5E4793DA86C4FDCAD699BA78762E7DCData[vector.size()];
    vector.copyInto(objectHeaderF5E4793DA86C4FDCAD699BA78762E7DCData);
    return(objectHeaderF5E4793DA86C4FDCAD699BA78762E7DCData);
  }

/**
Select for action search
 */
  public static String selectActDefM_AttributeSetInstance_ID(ConnectionProvider connectionProvider, String M_AttributeSetInstance_ID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT Description FROM M_AttributeSetInstance WHERE isActive='Y' AND M_AttributeSetInstance_ID = ?  ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, M_AttributeSetInstance_ID);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "description");
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
}
