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

class RptPromissoryNoteTreeData implements FieldProvider {
static Logger log4j = Logger.getLogger(RptPromissoryNoteTreeData.class);
  private String InitRecordNumber="0";
  public String cDebtPaymentId;
  public String cInvoiceId;
  public String issotrx;
  public String documentno;
  public String description;
  public String poreference;
  public String grandtotal;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("c_debt_payment_id") || fieldName.equals("cDebtPaymentId"))
      return cDebtPaymentId;
    else if (fieldName.equalsIgnoreCase("c_invoice_id") || fieldName.equals("cInvoiceId"))
      return cInvoiceId;
    else if (fieldName.equalsIgnoreCase("issotrx"))
      return issotrx;
    else if (fieldName.equalsIgnoreCase("documentno"))
      return documentno;
    else if (fieldName.equalsIgnoreCase("description"))
      return description;
    else if (fieldName.equalsIgnoreCase("poreference"))
      return poreference;
    else if (fieldName.equalsIgnoreCase("grandtotal"))
      return grandtotal;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static RptPromissoryNoteTreeData[] select(ConnectionProvider connectionProvider, String cDebtPaymentId)    throws ServletException {
    return select(connectionProvider, cDebtPaymentId, 0, 0);
  }

  public static RptPromissoryNoteTreeData[] select(ConnectionProvider connectionProvider, String cDebtPaymentId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "          SELECT C_DEBT_PAYMENT.C_DEBT_PAYMENT_ID, C_DEBT_PAYMENT.C_INVOICE_ID, C_INVOICE.ISSOTRX, C_INVOICE.DOCUMENTNO, C_DEBT_PAYMENT.DESCRIPTION, C_INVOICE.POREFERENCE, TO_CHAR(C_INVOICE.GRANDTOTAL, 'FM99999999990.00') AS GRANDTOTAL" +
      "          FROM C_DEBT_PAYMENT left join  C_INVOICE on C_DEBT_PAYMENT.C_INVOICE_ID = C_INVOICE.C_INVOICE_ID" +
      "          WHERE C_DEBT_PAYMENT.C_SETTLEMENT_CANCEL_ID IN (SELECT C_SETTLEMENT_GENERATE_ID FROM  C_DEBT_PAYMENT)" +
      "          AND 1=1";
    strSql = strSql + ((cDebtPaymentId==null || cDebtPaymentId.equals(""))?"":" AND C_DEBT_PAYMENT_ID IN " + cDebtPaymentId);

    ResultSet result;
    Vector<RptPromissoryNoteTreeData> vector = new Vector<RptPromissoryNoteTreeData>(0);
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);
      QueryTimeOutUtil.getInstance().setQueryTimeOut(st, SessionInfo.getQueryProfile());
      if (cDebtPaymentId != null && !(cDebtPaymentId.equals(""))) {
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
        RptPromissoryNoteTreeData objectRptPromissoryNoteTreeData = new RptPromissoryNoteTreeData();
        objectRptPromissoryNoteTreeData.cDebtPaymentId = UtilSql.getValue(result, "c_debt_payment_id");
        objectRptPromissoryNoteTreeData.cInvoiceId = UtilSql.getValue(result, "c_invoice_id");
        objectRptPromissoryNoteTreeData.issotrx = UtilSql.getValue(result, "issotrx");
        objectRptPromissoryNoteTreeData.documentno = UtilSql.getValue(result, "documentno");
        objectRptPromissoryNoteTreeData.description = UtilSql.getValue(result, "description");
        objectRptPromissoryNoteTreeData.poreference = UtilSql.getValue(result, "poreference");
        objectRptPromissoryNoteTreeData.grandtotal = UtilSql.getValue(result, "grandtotal");
        objectRptPromissoryNoteTreeData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectRptPromissoryNoteTreeData);
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
    RptPromissoryNoteTreeData objectRptPromissoryNoteTreeData[] = new RptPromissoryNoteTreeData[vector.size()];
    vector.copyInto(objectRptPromissoryNoteTreeData);
    return(objectRptPromissoryNoteTreeData);
  }
}
