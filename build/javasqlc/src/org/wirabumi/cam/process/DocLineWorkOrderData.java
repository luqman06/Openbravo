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

class DocLineWorkOrderData implements FieldProvider {
static Logger log4j = Logger.getLogger(DocLineWorkOrderData.class);
  private String InitRecordNumber="0";
  public String adOrgId;
  public String aAssetId;
  public String description;
  public String camWorkorderassetId;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("ad_org_id") || fieldName.equals("adOrgId"))
      return adOrgId;
    else if (fieldName.equalsIgnoreCase("a_asset_id") || fieldName.equals("aAssetId"))
      return aAssetId;
    else if (fieldName.equalsIgnoreCase("description"))
      return description;
    else if (fieldName.equalsIgnoreCase("cam_workorderasset_id") || fieldName.equals("camWorkorderassetId"))
      return camWorkorderassetId;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static DocLineWorkOrderData[] select(ConnectionProvider connectionProvider, String Cam_Workorder_ID)    throws ServletException {
    return select(connectionProvider, Cam_Workorder_ID, 0, 0);
  }

  public static DocLineWorkOrderData[] select(ConnectionProvider connectionProvider, String Cam_Workorder_ID, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "	  SELECT IL.AD_ORG_ID, IL.a_asset_id, IL.DESCRIPTION," +
      "	  IL.cam_workorderasset_id" +
      "	  FROM cam_workorderasset IL " +
      "	  WHERE cam_workorder_id=? " +
      "	  AND isdisposed='Y'" +
      "	  ORDER BY cam_workorderasset_id";

    ResultSet result;
    Vector<DocLineWorkOrderData> vector = new Vector<DocLineWorkOrderData>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, Cam_Workorder_ID);

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
        DocLineWorkOrderData objectDocLineWorkOrderData = new DocLineWorkOrderData();
        objectDocLineWorkOrderData.adOrgId = UtilSql.getValue(result, "ad_org_id");
        objectDocLineWorkOrderData.aAssetId = UtilSql.getValue(result, "a_asset_id");
        objectDocLineWorkOrderData.description = UtilSql.getValue(result, "description");
        objectDocLineWorkOrderData.camWorkorderassetId = UtilSql.getValue(result, "cam_workorderasset_id");
        objectDocLineWorkOrderData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectDocLineWorkOrderData);
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
    DocLineWorkOrderData objectDocLineWorkOrderData[] = new DocLineWorkOrderData[vector.size()];
    vector.copyInto(objectDocLineWorkOrderData);
    return(objectDocLineWorkOrderData);
  }
}
