//Sqlc generated V1.O00-1
package org.openbravo.erpWindows.org.wirabumi.hris.employee.master.FamilyInformationChangeRequest;

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
class UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData implements FieldProvider {
static Logger log4j = Logger.getLogger(UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData.class);
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

  public static UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData[] dummy(ConnectionProvider connectionProvider)    throws ServletException {
    return dummy(connectionProvider, 0, 0);
  }

  public static UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData[] dummy(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT '' AS dummy from DUAL";

    ResultSet result;
    Vector<UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData> vector = new Vector<UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData>(0);
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
        UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData objectUsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData = new UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData();
        objectUsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData.dummy = UtilSql.getValue(result, "dummy");
        objectUsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectUsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData);
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
    UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData objectUsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData[] = new UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData[vector.size()];
    vector.copyInto(objectUsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData);
    return(objectUsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65BData);
  }

  public static int updateDocaction(ConnectionProvider connectionProvider, String docaction, String hrisChangeFamilyId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE hris_change_family" +
      "        SET docaction = ? " +
      "        WHERE hris_change_family.Hris_Change_Family_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, docaction);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, hrisChangeFamilyId);

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
