//Sqlc generated V1.O00-1
package org.wirabumi.cam.process;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import org.openbravo.service.db.QueryTimeOutUtil;
import org.openbravo.database.SessionInfo;
import java.util.*;

class DocWorkOrderData implements FieldProvider {
static Logger log4j = Logger.getLogger(DocWorkOrderData.class);
  private String InitRecordNumber="0";
  public String adClientId;
  public String adOrgId;
  public String adOrgtrxId;
  public String cCostcenterId;
  public String posted;
  public String dateacct;
  public String description;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("ad_client_id") || fieldName.equals("adClientId"))
      return adClientId;
    else if (fieldName.equalsIgnoreCase("ad_org_id") || fieldName.equals("adOrgId"))
      return adOrgId;
    else if (fieldName.equalsIgnoreCase("ad_orgtrx_id") || fieldName.equals("adOrgtrxId"))
      return adOrgtrxId;
    else if (fieldName.equalsIgnoreCase("c_costcenter_id") || fieldName.equals("cCostcenterId"))
      return cCostcenterId;
    else if (fieldName.equalsIgnoreCase("posted"))
      return posted;
    else if (fieldName.equalsIgnoreCase("dateacct"))
      return dateacct;
    else if (fieldName.equalsIgnoreCase("description"))
      return description;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static DocWorkOrderData[] select(ConnectionProvider connectionProvider, String client, String id)    throws ServletException {
    return select(connectionProvider, client, id, 0, 0);
  }

  public static DocWorkOrderData[] select(ConnectionProvider connectionProvider, String client, String id, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT I.AD_CLIENT_ID, I.AD_ORG_ID, I.AD_ORG_ID as AD_ORGTRX_ID, " +
      "        I.C_COSTCENTER_ID, I.POSTED, I.startdate AS DATEACCT, I.description" +
      "        FROM cam_workorder I" +
      "        WHERE AD_CLIENT_ID=? " +
      "        AND cam_workorder_id=?";

    ResultSet result;
    Vector<DocWorkOrderData> vector = new Vector<DocWorkOrderData>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, client);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, id);

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
        DocWorkOrderData objectDocWorkOrderData = new DocWorkOrderData();
        objectDocWorkOrderData.adClientId = UtilSql.getValue(result, "ad_client_id");
        objectDocWorkOrderData.adOrgId = UtilSql.getValue(result, "ad_org_id");
        objectDocWorkOrderData.adOrgtrxId = UtilSql.getValue(result, "ad_orgtrx_id");
        objectDocWorkOrderData.cCostcenterId = UtilSql.getValue(result, "c_costcenter_id");
        objectDocWorkOrderData.posted = UtilSql.getValue(result, "posted");
        objectDocWorkOrderData.dateacct = UtilSql.getDateValue(result, "dateacct", "dd-MM-yyyy");
        objectDocWorkOrderData.description = UtilSql.getValue(result, "description");
        objectDocWorkOrderData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectDocWorkOrderData);
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
    DocWorkOrderData objectDocWorkOrderData[] = new DocWorkOrderData[vector.size()];
    vector.copyInto(objectDocWorkOrderData);
    return(objectDocWorkOrderData);
  }

  public static String selectTemplateDoc(ConnectionProvider connectionProvider, String cAcctschemaId, String docbasetype)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_CREATEFACT_TEMPLATE.CLASSNAME" +
      "      FROM C_ACCTSCHEMA_TABLE, C_ACCTSCHEMA_TABLE_DOCTYPE, AD_CREATEFACT_TEMPLATE" +
      "      WHERE C_ACCTSCHEMA_TABLE_DOCTYPE.AD_CREATEFACT_TEMPLATE_ID = AD_CREATEFACT_TEMPLATE.AD_CREATEFACT_TEMPLATE_ID" +
      "      AND C_ACCTSCHEMA_TABLE.C_ACCTSCHEMA_TABLE_ID = C_ACCTSCHEMA_TABLE_DOCTYPE.C_ACCTSCHEMA_TABLE_ID" +
      "      AND C_ACCTSCHEMA_TABLE.C_ACCTSCHEMA_ID = ?" +
      "      AND C_ACCTSCHEMA_TABLE_DOCTYPE.DOCBASETYPE = ?";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, cAcctschemaId);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, docbasetype);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "classname");
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

  public static String selectTemplate(ConnectionProvider connectionProvider, String cAcctschemaId, String adTableId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT CLASSNAME" +
      "        FROM C_ACCTSCHEMA_TABLE, AD_CREATEFACT_TEMPLATE" +
      "        WHERE C_ACCTSCHEMA_TABLE.AD_CREATEFACT_TEMPLATE_ID = AD_CREATEFACT_TEMPLATE.AD_CREATEFACT_TEMPLATE_ID" +
      "        AND C_ACCTSCHEMA_ID = ?" +
      "        AND C_ACCTSCHEMA_TABLE.AD_TABLE_ID = ?";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, cAcctschemaId);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adTableId);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "classname");
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
