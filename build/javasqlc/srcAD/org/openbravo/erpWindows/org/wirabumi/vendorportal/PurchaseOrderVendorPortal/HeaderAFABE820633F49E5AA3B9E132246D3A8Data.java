//Sqlc generated V1.O00-1
package org.openbravo.erpWindows.org.wirabumi.vendorportal.PurchaseOrderVendorPortal;

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
class HeaderAFABE820633F49E5AA3B9E132246D3A8Data implements FieldProvider {
static Logger log4j = Logger.getLogger(HeaderAFABE820633F49E5AA3B9E132246D3A8Data.class);
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

  public static HeaderAFABE820633F49E5AA3B9E132246D3A8Data[] dummy(ConnectionProvider connectionProvider)    throws ServletException {
    return dummy(connectionProvider, 0, 0);
  }

  public static HeaderAFABE820633F49E5AA3B9E132246D3A8Data[] dummy(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT '' AS dummy from DUAL";

    ResultSet result;
    Vector<HeaderAFABE820633F49E5AA3B9E132246D3A8Data> vector = new Vector<HeaderAFABE820633F49E5AA3B9E132246D3A8Data>(0);
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
        HeaderAFABE820633F49E5AA3B9E132246D3A8Data objectHeaderAFABE820633F49E5AA3B9E132246D3A8Data = new HeaderAFABE820633F49E5AA3B9E132246D3A8Data();
        objectHeaderAFABE820633F49E5AA3B9E132246D3A8Data.dummy = UtilSql.getValue(result, "dummy");
        objectHeaderAFABE820633F49E5AA3B9E132246D3A8Data.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectHeaderAFABE820633F49E5AA3B9E132246D3A8Data);
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
    HeaderAFABE820633F49E5AA3B9E132246D3A8Data objectHeaderAFABE820633F49E5AA3B9E132246D3A8Data[] = new HeaderAFABE820633F49E5AA3B9E132246D3A8Data[vector.size()];
    vector.copyInto(objectHeaderAFABE820633F49E5AA3B9E132246D3A8Data);
    return(objectHeaderAFABE820633F49E5AA3B9E132246D3A8Data);
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
