//Sqlc generated V1.O00-1
package org.openbravo.erpCommon.ad_process;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import org.openbravo.service.db.QueryTimeOutUtil;
import org.openbravo.database.SessionInfo;
import java.util.*;

class ImportBPartnerData implements FieldProvider {
static Logger log4j = Logger.getLogger(ImportBPartnerData.class);
  private String InitRecordNumber="0";
  public String iBpartnerId;
  public String cBpartnerId;
  public String cBpartnerLocationId;
  public String addr;
  public String adUserId;
  public String contactname;
  public String address1;
  public String address2;
  public String city;
  public String postal;
  public String regionname;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("i_bpartner_id") || fieldName.equals("iBpartnerId"))
      return iBpartnerId;
    else if (fieldName.equalsIgnoreCase("c_bpartner_id") || fieldName.equals("cBpartnerId"))
      return cBpartnerId;
    else if (fieldName.equalsIgnoreCase("c_bpartner_location_id") || fieldName.equals("cBpartnerLocationId"))
      return cBpartnerLocationId;
    else if (fieldName.equalsIgnoreCase("addr"))
      return addr;
    else if (fieldName.equalsIgnoreCase("ad_user_id") || fieldName.equals("adUserId"))
      return adUserId;
    else if (fieldName.equalsIgnoreCase("contactname"))
      return contactname;
    else if (fieldName.equalsIgnoreCase("address1"))
      return address1;
    else if (fieldName.equalsIgnoreCase("address2"))
      return address2;
    else if (fieldName.equalsIgnoreCase("city"))
      return city;
    else if (fieldName.equalsIgnoreCase("postal"))
      return postal;
    else if (fieldName.equalsIgnoreCase("regionname"))
      return regionname;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static ImportBPartnerData[] select(ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    return select(connectionProvider, ad_client_id, 0, 0);
  }

  public static ImportBPartnerData[] select(ConnectionProvider connectionProvider, String ad_client_id, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT I_BPartner_ID, C_BPartner_ID, C_BPartner_Location_ID," +
      "        COALESCE (Address1,Address2,City) AS Addr, AD_User_ID, ContactName," +
      "		Address1, Address2, City, Postal, RegionName" +
      "			  FROM I_BPartner" +
      "			  WHERE I_IsImported='N'" +
      "        AND AD_Client_ID = ?";

    ResultSet result;
    Vector<ImportBPartnerData> vector = new Vector<ImportBPartnerData>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

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
        ImportBPartnerData objectImportBPartnerData = new ImportBPartnerData();
        objectImportBPartnerData.iBpartnerId = UtilSql.getValue(result, "i_bpartner_id");
        objectImportBPartnerData.cBpartnerId = UtilSql.getValue(result, "c_bpartner_id");
        objectImportBPartnerData.cBpartnerLocationId = UtilSql.getValue(result, "c_bpartner_location_id");
        objectImportBPartnerData.addr = UtilSql.getValue(result, "addr");
        objectImportBPartnerData.adUserId = UtilSql.getValue(result, "ad_user_id");
        objectImportBPartnerData.contactname = UtilSql.getValue(result, "contactname");
        objectImportBPartnerData.address1 = UtilSql.getValue(result, "address1");
        objectImportBPartnerData.address2 = UtilSql.getValue(result, "address2");
        objectImportBPartnerData.city = UtilSql.getValue(result, "city");
        objectImportBPartnerData.postal = UtilSql.getValue(result, "postal");
        objectImportBPartnerData.regionname = UtilSql.getValue(result, "regionname");
        objectImportBPartnerData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectImportBPartnerData);
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
    ImportBPartnerData objectImportBPartnerData[] = new ImportBPartnerData[vector.size()];
    vector.copyInto(objectImportBPartnerData);
    return(objectImportBPartnerData);
  }

  public static int deleteOld(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      DELETE FROM I_BPartner WHERE I_IsImported='Y'" +
      "      AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateRecords(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "        SET AD_Client_ID = COALESCE(AD_Client_ID, ?)," +
      "        AD_Org_ID = COALESCE(AD_Org_ID, '0')," +
      "        IsActive = COALESCE(IsActive, 'Y')," +
      "        Created = COALESCE(Created, now())," +
      "        CreatedBy = COALESCE(CreatedBy, '0')," +
      "        Updated = COALESCE(Updated, now())," +
      "        UpdatedBy = COALESCE(UpdatedBy, '0')," +
      "        I_ErrorMsg = NULL," +
      "        I_IsImported = 'N' " +
      "        WHERE I_IsImported<>'Y' OR I_IsImported IS NULL";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateBPGroupDefault(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET GroupValue=(" +
      "            SELECT MIN(Value) FROM C_BP_Group g" +
      "            WHERE g.IsDefault='Y'" +
      "              AND g.AD_Client_ID=I_BPartner.AD_Client_ID" +
      "            ) " +
      "			  WHERE GroupValue IS NULL" +
      "          AND C_BP_Group_ID IS NULL" +
      "			    AND I_IsImported<>'Y'" +
      "          AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateBPGroupId(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET C_BP_Group_ID=(" +
      "          SELECT C_BP_Group_ID FROM C_BP_Group g" +
      "			    WHERE I_BPartner.GroupValue=g.Value AND g.AD_Client_ID=I_BPartner.AD_Client_ID" +
      "        )" +
      "			  WHERE C_BP_Group_ID IS NULL" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateGroupError(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'')||'ERR=Invalid Group, ' " +
      "			  WHERE C_BP_Group_ID IS NULL" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateCountryCodeDefault(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET CountryCode=(" +
      "          SELECT MIN(CountryCode) FROM C_Country c" +
      "          WHERE c.IsDefault='Y'" +
      "            AND c.AD_Client_ID IN ('0', I_BPartner.AD_Client_ID)" +
      "        )" +
      "			  WHERE CountryCode IS NULL" +
      "        AND C_Country_ID IS NULL" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateCountryId(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET C_Country_ID=(" +
      "          SELECT C_Country_ID FROM C_Country c" +
      "  			  WHERE I_BPartner.CountryCode=c.CountryCode" +
      "          AND c.AD_Client_ID IN ('0', I_BPartner.AD_Client_ID)" +
      "        )" +
      "			  WHERE C_Country_ID IS NULL" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateCountryError(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'')||'ERR=Invalid Country, '" +
      "			  WHERE C_Country_ID IS NULL" +
      "  			  AND I_IsImported<>'Y'" +
      "          AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateRegionDefault(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET RegionName=(" +
      "          SELECT MIN(Name) FROM C_Region r" +
      "			    WHERE r.IsDefault='Y'" +
      "          AND r.C_Country_ID=I_BPartner.C_Country_ID" +
      "			    AND r.AD_Client_ID IN ('0', I_BPartner.AD_Client_ID)" +
      "        )" +
      "			  WHERE RegionName IS NULL" +
      "        AND C_Region_ID IS NULL" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateRegionId(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET C_Region_ID=(" +
      "          SELECT C_Region_ID FROM C_Region r" +
      "			    WHERE r.Name=I_BPartner.RegionName" +
      "          AND r.C_Country_ID=I_BPartner.C_Country_ID" +
      "			    AND r.AD_Client_ID IN ('0', I_BPartner.AD_Client_ID)" +
      "        )" +
      "			  WHERE C_Region_ID IS NULL" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateRegionError(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'')||'ERR=Invalid Region, '" +
      "			  WHERE C_Region_ID IS NULL" +
      "			  AND EXISTS (" +
      "          SELECT * FROM C_Country c" +
      "			    WHERE c.C_Country_ID=I_BPartner.C_Country_ID" +
      "          AND c.HasRegion='Y'" +
      "        )" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateGreetingId(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET C_Greeting_ID=(" +
      "          SELECT C_Greeting_ID FROM C_Greeting g" +
      "			    WHERE I_BPartner.BPContactGreeting=g.Name" +
      "          AND g.AD_Client_ID IN ('0', I_BPartner.AD_Client_ID)" +
      "        )" +
      "			  WHERE C_Greeting_ID IS NULL" +
      "        AND BPContactGreeting IS NOT NULL" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateGreetingError(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'')||'ERR=Invalid Greeting, '" +
      "			  WHERE C_Greeting_ID IS NULL" +
      "        AND BPContactGreeting IS NOT NULL" +
      "			  AND I_IsImported<>'Y'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateBPartnerId(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET C_BPartner_ID=(" +
      "          SELECT C_BPartner_ID FROM C_BPartner p" +
      "			    WHERE I_BPartner.Value=p.Value" +
      "          AND p.AD_Client_ID=I_BPartner.AD_Client_ID" +
      "        )" +
      "			  WHERE C_BPartner_ID IS NULL" +
      "        AND Value IS NOT NULL" +
      "			  AND I_IsImported='N'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateADUserId(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET AD_User_ID=(" +
      "          SELECT AD_User_ID FROM AD_User c" +
      "			    WHERE I_BPartner.ContactName=c.Name" +
      "          AND I_BPartner.C_BPartner_ID=c.C_BPartner_ID" +
      "          AND c.AD_Client_ID=I_BPartner.AD_Client_ID limit 1" +
      "        )" +
      "			  WHERE C_BPartner_ID IS NOT NULL" +
      "        AND AD_User_ID IS NULL" +
      "        AND ContactName IS NOT NULL" +
      "			  AND I_IsImported='N'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateLocationId(Connection conn, ConnectionProvider connectionProvider, String ad_client_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner " +
      "			  SET C_BPartner_Location_ID=(" +
      "          SELECT C_BPartner_Location_ID" +
      "			    FROM C_BPartner_Location bpl INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)" +
      "			    WHERE I_BPartner.C_BPartner_ID=bpl.C_BPartner_ID" +
      "          AND bpl.AD_Client_ID=I_BPartner.AD_Client_ID" +
      "			    AND DUMP(I_BPartner.Address1)=DUMP(l.Address1)" +
      "          AND DUMP(I_BPartner.Address2)=DUMP(l.Address2)" +
      "			    AND DUMP(I_BPartner.City)=DUMP(l.City)" +
      "          AND DUMP(I_BPartner.Postal)=DUMP(l.Postal)" +
      "          AND DUMP(I_BPartner.Postal_Add)=DUMP(l.Postal_Add)" +
      "			    AND DUMP(I_BPartner.C_Region_ID)=DUMP(l.C_Region_ID)" +
      "          AND DUMP(I_BPartner.C_Country_ID)=DUMP(l.C_Country_ID)" +
      "        )" +
      "			  WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL" +
      "			  AND I_IsImported='N'" +
      "        AND AD_Client_ID = ?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ad_client_id);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int insertBPartner(Connection conn, ConnectionProvider connectionProvider, String CBPartnerID, String IBPartnerID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "			  INSERT INTO C_BPartner (C_BPartner_ID, AD_Client_ID, AD_Org_ID," +
      "          IsActive,Created,CreatedBy,Updated,UpdatedBy,Value,Name,Name2," +
      "          Description,DUNS,TaxID,NAICS,C_BP_Group_ID,IsSummary, iscustomer, isvendor, isemployee, fin_paymentmethod_id, po_paymentmethod_id)" +
      "  				  SELECT ?, AD_Client_ID, AD_Org_ID," +
      "            'Y',now(),CreatedBy,now(),UpdatedBy,Value,Name,Name2," +
      "            Description,DUNS,TaxID,NAICS,C_BP_Group_ID,'N',em_oez_iscustomer,em_oez_isvendor,em_oez_isemployee, " +
      "			(select fin_paymentmethod_id from fin_paymentmethod where payin_allow='Y' " +
      "			 and ad_isorgincluded(I_BPartner.ad_org_id,fin_paymentmethod.ad_org_id,fin_paymentmethod.ad_client_id)=1 limit 1), " +
      "			(select fin_paymentmethod_id from fin_paymentmethod where payout_allow='Y'" +
      "			 and ad_isorgincluded(I_BPartner.ad_org_id,fin_paymentmethod.ad_org_id,fin_paymentmethod.ad_client_id)=1 limit 1) " +
      "			  	  FROM I_BPartner" +
      "				    WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int insertBPartnerError(ConnectionProvider connectionProvider, String error, String i_bpartner_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'') || 'Insert BPartner error: ' || ? " +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, error);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, i_bpartner_id);

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

  public static int updateBPartner(Connection conn, ConnectionProvider connectionProvider, String IBPartnerID, String CBPartnerID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "				UPDATE C_BPartner" +
      "				SET " +
      "          Value=(" +
      "          SELECT Value" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )," +
      "          Name=(" +
      "          SELECT Name" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )," +
      "          Name2=(" +
      "          SELECT Name2" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )," +
      "          Description=(" +
      "          SELECT Description" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )," +
      "          DUNS=(" +
      "          SELECT DUNS" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )," +
      "          TaxID=(" +
      "          SELECT TaxID" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )," +
      "          NAICS=(" +
      "          SELECT NAICS" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )," +
      "          C_BP_Group_ID=(" +
      "          SELECT C_BP_Group_ID" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )," +
      "          Updated = now()," +
      "          UpdatedBy" +
      "        =(" +
      "          SELECT UpdatedBy" +
      "          FROM I_BPartner" +
      "				  WHERE I_BPartner_ID=?" +
      "        )" +
      "				WHERE C_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPartnerID);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateBPartnerError(ConnectionProvider connectionProvider, String error, String i_bpartner_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'') || 'Update BPartner error: ' || ? " +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, error);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, i_bpartner_id);

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

  public static int insertLocation(Connection conn, ConnectionProvider connectionProvider, String CLocationID, String IBPartnerID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "				INSERT INTO C_Location (" +
      "          C_Location_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
      "          Address1,Address2,City,Postal,Postal_Add,C_Country_ID,C_Region_ID" +
      "        )" +
      "				SELECT ?,AD_Client_ID,AD_Org_ID,'Y',now(),CreatedBy,now(),UpdatedBy," +
      "				Address1,Address2,City,Postal,Postal_Add,C_Country_ID,C_Region_ID" +
      "				FROM I_BPartner" +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CLocationID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int insertLocationError(ConnectionProvider connectionProvider, String error, String i_bpartner_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'') || 'Insert location error: ' || ? " +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, error);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, i_bpartner_id);

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

  public static int insertBPLocation(Connection conn, ConnectionProvider connectionProvider, String CBPLocationID, String locationName, String CBPartnerID, String CLocationID, String IBPartnerID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "				INSERT INTO C_BPartner_Location (" +
      "          C_BPartner_Location_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
      "				  Name," +
      "				  IsBillTo,IsShipTo,IsPayFrom,IsRemitTo,Phone,Phone2,Fax, C_BPartner_ID,C_Location_ID" +
      "        )" +
      "				SELECT ?,AD_Client_ID,AD_Org_ID,'Y',now(),CreatedBy,now(),UpdatedBy," +
      "				TO_CHAR(?)," +
      "				'Y','Y','Y','Y',Phone,Phone2,Fax, ?,?" +
      "				FROM I_BPartner" +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPLocationID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, locationName);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CLocationID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int insertBPLocationError(ConnectionProvider connectionProvider, String error, String i_bpartner_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'') || 'Insert BPartner location error: ' || ?" +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, error);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, i_bpartner_id);

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

  public static int insertBPContact(Connection conn, ConnectionProvider connectionProvider, String ADUserID, String CBPartnerID, String CBPLocationID, String IBPartnerID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "				INSERT INTO AD_User (" +
      "          AD_User_ID,AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy," +
      "				  C_BPartner_ID,C_BPartner_Location_ID,C_Greeting_ID,Name,Title,Description,Comments,Phone,Phone2,Fax,EMail,Birthday" +
      "        )" +
      "				SELECT ?,AD_Client_ID,AD_Org_ID,'Y',now(),CreatedBy,now(),UpdatedBy," +
      "				?,?,C_Greeting_ID,ContactName,Title,ContactDescription,Comments,Phone,Phone2,Fax,EMail,Birthday" +
      "				FROM I_BPartner" +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ADUserID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPLocationID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int insertBPContactError(ConnectionProvider connectionProvider, String error, String i_bpartner_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'') || 'Insert BPartner contact error: ' || ?" +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, error);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, i_bpartner_id);

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

  public static int updateBPContact(Connection conn, ConnectionProvider connectionProvider, String IBPartnerID, String ADUserID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "				UPDATE AD_User" +
      "				SET " +
      "          C_Greeting_ID=(" +
      "          SELECT C_Greeting_ID" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Name=(" +
      "          SELECT ContactName" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Title=(" +
      "          SELECT Title" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Description=(" +
      "          SELECT ContactDescription" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Comments=(" +
      "          SELECT Comments" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Phone=(" +
      "          SELECT Phone" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Phone2=(" +
      "          SELECT Phone2" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Fax=(" +
      "          SELECT Fax" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          EMail=(" +
      "          SELECT EMail" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Birthday=(" +
      "          SELECT Birthday" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )," +
      "          Updated=now()" +
      "         ," +
      "          UpdatedBy=(" +
      "          SELECT UpdatedBy" +
      "				  FROM I_BPartner" +
      "          WHERE I_BPartner_ID=?" +
      "        )" +
      "				WHERE AD_User_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ADUserID);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateBPContactError(ConnectionProvider connectionProvider, String error, String i_bpartner_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'') || 'Update BPartner contact error: ' || ?" +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, error);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, i_bpartner_id);

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

  public static int updateSetImportedError(ConnectionProvider connectionProvider, String error, String i_bpartner_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        UPDATE I_BPartner" +
      "			  SET I_IsImported='E', I_ErrorMsg=COALESCE(TO_CHAR(I_ErrorMsg),'') || 'Set Imported error: ' || ? " +
      "				WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, error);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, i_bpartner_id);

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

  public static int setImported(Connection conn, ConnectionProvider connectionProvider, String CBPartnerID, String CBPLocationID, String ADUserID, String IBPartnerID)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "				UPDATE I_BPartner" +
      "        SET I_IsImported='Y', C_BPartner_id = ?, C_BPartner_Location_id = ?, AD_User_id = ?, Updated=now(), Processed='Y'" +
      "        WHERE I_BPartner_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(conn, strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPartnerID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, CBPLocationID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, ADUserID);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, IBPartnerID);

      SessionInfo.saveContextInfoIntoDB(conn);
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
        connectionProvider.releaseTransactionalPreparedStatement(st);
      } catch(Exception e){
        log4j.error("Error during release*Statement of query: " + strSql, e);
      }
    }
    return(updateCount);
  }

  public static int updateNotImported(ConnectionProvider connectionProvider, String adClientId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "				UPDATE I_BPartner" +
      "        SET I_IsImported='N', Updated=now()" +
      "			  WHERE I_IsImported<>'Y'" +
      "        AND AD_CLIENT_ID=?";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adClientId);

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
