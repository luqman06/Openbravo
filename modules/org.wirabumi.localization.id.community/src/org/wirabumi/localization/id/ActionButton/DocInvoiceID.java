package org.wirabumi.localization.id.ActionButton;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.ad_forms.Account;
import org.openbravo.erpCommon.ad_forms.AcctSchema;
import org.openbravo.erpCommon.ad_forms.AcctServer;
import org.openbravo.erpCommon.ad_forms.DocInvoice;
import org.openbravo.erpCommon.ad_forms.DocInvoiceTemplate;
import org.openbravo.erpCommon.ad_forms.DocLine;
import org.openbravo.erpCommon.ad_forms.DocLine_FinPaymentSchedule;
import org.openbravo.erpCommon.ad_forms.DocLine_Invoice;
import org.openbravo.erpCommon.ad_forms.DocTax;
import org.openbravo.erpCommon.ad_forms.Fact;
import org.openbravo.erpCommon.ad_forms.FactLine;
import org.openbravo.erpCommon.ad_forms.ProductInfo;
import org.openbravo.erpCommon.utility.AccDefUtility;
import org.openbravo.erpCommon.utility.OBDateUtils;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.model.ad.access.InvoiceLineTax;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.invoice.InvoiceTax;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchemaGL;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.wirabumi.localization.id.Utility;

public class DocInvoiceID extends DocInvoiceTemplate {
  Logger log4jDocInvoiceID = Logger.getLogger(this.getClass());
  String SeqNo = "0";

  @Override
  public Fact createFact(DocInvoice docInvoice, AcctSchema as, ConnectionProvider conn,
      Connection con, VariablesSecureApp vars) throws ServletException {
    log4jDocInvoiceID.debug("Starting create fact");
    DocLine_FinPaymentSchedule[] m_payments = docInvoice.getM_payments();
    // create Fact Header
    Fact fact = new Fact(docInvoice, as, Fact.POST_Actual);
    String Fact_Acct_Group_ID = SequenceIdData.getUUID();
    Boolean convertedTax = false;
    // ARI, ARF, ARI_RM
    if (docInvoice.DocumentType.equals(AcctServer.DOCTYPE_ARInvoice)
        || docInvoice.DocumentType.equals(AcctServer.DOCTYPE_ARProForma)
        || docInvoice.DocumentType.equals(AcctServer.DOCTYPE_RMSalesInvoice)) {
      log4jDocInvoiceID.debug("Point 1");
      // Receivables DR
      if (m_payments == null || m_payments.length == 0)
        for (int i = 0; docInvoice.m_debt_payments != null && i < docInvoice.m_debt_payments.length; i++) {
          if (docInvoice.m_debt_payments[i].getIsReceipt().equals("Y"))
            fact.createLine(docInvoice.m_debt_payments[i], docInvoice.getAccountBPartner(
                docInvoice.C_BPartner_ID, as, true, docInvoice.m_debt_payments[i].getDpStatus(),
                conn), docInvoice.C_Currency_ID, AcctServer.getConvertedAmt( //convert ori
                docInvoice.m_debt_payments[i].getAmount(),
                docInvoice.m_debt_payments[i].getC_Currency_ID_From(), docInvoice.C_Currency_ID,
                docInvoice.DateAcct, "", docInvoice.AD_Client_ID, docInvoice.AD_Org_ID, conn), "",
                Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          else
            fact.createLine(docInvoice.m_debt_payments[i], docInvoice.getAccountBPartner(
                docInvoice.C_BPartner_ID, as, false, docInvoice.m_debt_payments[i].getDpStatus(),
                conn), docInvoice.C_Currency_ID, "", AcctServer.getConvertedAmt(
                docInvoice.m_debt_payments[i].getAmount(),
                docInvoice.m_debt_payments[i].getC_Currency_ID_From(), docInvoice.C_Currency_ID,
                docInvoice.DateAcct, "", docInvoice.AD_Client_ID, docInvoice.AD_Org_ID, conn),
                Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        }
      else
        for (int i = 0; m_payments != null && i < m_payments.length; i++) {

          double diffAmt = 0.0;
          for (int tax = 0; docInvoice.getM_taxes() != null && tax < docInvoice.getM_taxes().length; tax++) {
            if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[tax].m_C_Tax_ID)) {
              BigDecimal taxAmount = indonesianVAT(docInvoice.getM_taxes()[tax].m_rate,
                  docInvoice.Record_ID, docInvoice.getM_taxes()[tax].m_C_Tax_ID, true, 6,
                  BigDecimal.ROUND_DOWN);
              String differenceamount = Utility.getDiferenceConversion(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxAmount.toString());
              double differenceLine = Double.parseDouble(differenceamount);
              diffAmt += differenceLine;
              BigDecimal cvrtdTax = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxAmount);
              long currency = cvrtdTax.longValue();
              BigDecimal roundingCurrency = cvrtdTax.subtract(new BigDecimal(currency)).negate();
              diffAmt += roundingCurrency.doubleValue();

            }

          }
          // ...:: Debet
          if (diffAmt != 0) {
            String amount = "0";
            String sourceAmt = getGrossAmount(docInvoice.Record_ID).toString();// m_payments[i].getAmount();
            BigDecimal revAmt = new BigDecimal(sourceAmt);
            BigDecimal cvtAmt = Utility.getConvertedAmt(docInvoice.C_Currency_ID,
                as.getC_Currency_ID(), docInvoice.DateAcct, revAmt);
            BigDecimal totalAmt = cvtAmt.add(new BigDecimal(diffAmt));
            amount = totalAmt.toString();
            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, false, conn),
                as.getC_Currency_ID(), amount, "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
                docInvoice.DocumentType, conn);
          } else {
            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, false, conn),
                docInvoice.C_Currency_ID, m_payments[i].getAmount(), "", Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          }

          if (m_payments[i].getC_Currency_ID_From().equals(as.m_C_Currency_ID)
              && new BigDecimal(m_payments[i].getPrepaidAmount()).compareTo(docInvoice.ZERO) != 0) {
            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, true, conn),
                docInvoice.C_Currency_ID, m_payments[i].getPrepaidAmount(), "", Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          } else {
            try {
              DocInvoiceIDData[] prepayments = DocInvoiceIDData.selectPrepayments(conn,
                  m_payments[i].getLine_ID());
              for (int j = 0; j < prepayments.length; j++) {
                BigDecimal prePaymentAmt = docInvoice.convertAmount(new BigDecimal(
                    prepayments[j].prepaidamt), true, docInvoice.DateAcct,
                    DocInvoice.TABLEID_Payment, prepayments[j].finPaymentId, m_payments[i]
                        .getC_Currency_ID_From(), as.m_C_Currency_ID, m_payments[i], as, fact,
                    Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), conn);
                fact.createLine(m_payments[i],
                    docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, true, conn),
                    m_payments[i].getC_Currency_ID_From(), prePaymentAmt.toString(), "",
                    Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
              }
            } catch (ServletException e) {
              log4jDocInvoiceID.warn(e);
            }
          }
        }
      if ((m_payments == null || m_payments.length == 0)
          && (docInvoice.m_debt_payments == null || docInvoice.m_debt_payments.length == 0)) {
        fact.createLine(null,
            docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, false, conn),
            docInvoice.C_Currency_ID, docInvoice.Amounts[DocInvoice.AMTTYPE_Gross], "",
            Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
      }
      // Charge CR
      log4jDocInvoiceID.debug("The first create line.");
      fact.createLine(null, docInvoice.getAccount(AcctServer.ACCTTYPE_Charge, as, conn),
          docInvoice.C_Currency_ID, "", docInvoice.getAmount(AcctServer.AMTTYPE_Charge),
          Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
      // TaxDue CR
      log4jDocInvoiceID.debug("m_taxes.length: " + docInvoice.getM_taxes());
      for (int i = 0; docInvoice.getM_taxes() != null && i < docInvoice.getM_taxes().length; i++) {
        // New docLine created to assign C_Tax_ID value to the entry
        DocLine docLine = new DocLine(docInvoice.DocumentType, docInvoice.Record_ID, "");
        docLine.m_C_Tax_ID = docInvoice.getM_taxes()[i].m_C_Tax_ID;
        if (docInvoice.IsReversal.equals("Y")) {
          if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[i].m_C_Tax_ID)) {
            // converting using tax rate
            BigDecimal taxableAmount = indonesianVAT(docInvoice.getM_taxes()[i].m_rate,
                docInvoice.Record_ID, docInvoice.getM_taxes()[i].m_C_Tax_ID, true, 6,
                BigDecimal.ROUND_DOWN);
            BigDecimal convertedTaxAmt = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
                as.getC_Currency_ID(), docInvoice.DateAcct, taxableAmount);
            convertedTaxAmt = convertedTaxAmt.setScale(0, BigDecimal.ROUND_DOWN);
            fact.createLine(docLine,
                docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
                as.getC_Currency_ID(), convertedTaxAmt.toString(), "", Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);

          } else {
            fact.createLine(docLine,
                docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
                docInvoice.C_Currency_ID, docInvoice.getM_taxes()[i].m_amount, "",
                Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          }
        } else if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[i].m_C_Tax_ID)
            && !docInvoice.C_Currency_ID.equalsIgnoreCase(as.getC_Currency_ID())) {
          // converting using tax rate Conversi Round Down
          BigDecimal taxableAmount = indonesianVAT(docInvoice.getM_taxes()[i].m_rate,
              docInvoice.Record_ID, docInvoice.getM_taxes()[i].m_C_Tax_ID, true, 6,
              BigDecimal.ROUND_DOWN);
          BigDecimal convertedTaxAmt = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
              as.getC_Currency_ID(), docInvoice.DateAcct, taxableAmount);
          convertedTaxAmt = convertedTaxAmt.setScale(0, BigDecimal.ROUND_DOWN);
          fact.createLine(docLine,
              docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
              as.getC_Currency_ID(), "", convertedTaxAmt.toString(), Fact_Acct_Group_ID,
              docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        } else {
          fact.createLine(docLine,
              docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
              docInvoice.C_Currency_ID, "", docInvoice.getM_taxes()[i].m_amount,
              Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        }
      }
      // Revenue CR
      if (docInvoice.p_lines != null && docInvoice.p_lines.length > 0) {
        for (int i = 0; i < docInvoice.p_lines.length; i++) {
          Account account = ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(
              ProductInfo.ACCTTYPE_P_Revenue, as, conn);
          if (docInvoice.DocumentType.equals(AcctServer.DOCTYPE_RMSalesInvoice)) {
            Account accountReturnMaterial = ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(
                ProductInfo.ACCTTYPE_P_RevenueReturn, as, conn);
            if (accountReturnMaterial != null) {
              account = accountReturnMaterial;
            }
          }
          String amount = docInvoice.p_lines[i].getAmount();
          if (((DocLine_Invoice) docInvoice.p_lines[i]).isDeferred()) {
            amount = createAccDefRevenueFact(docInvoice, fact,
                (DocLine_Invoice) docInvoice.p_lines[i], account,
                ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(
                    ProductInfo.ACCTTYPE_P_DefRevenue, as, conn), amount, conn);
          }

          // if reimbursement, get transaction cost, adjust amount, create line for reimbursement
          // price variance
          Invoice invoice = OBDal.getInstance().get(Invoice.class, docInvoice.Record_ID);
          BigDecimal selisih = null;
          BigDecimal convertedRevenueAmount = null;
          BigDecimal transactionCost = null;
          if (invoice.isOezIsreimbursement()) {
            InvoiceLine line = OBDal.getInstance().get(InvoiceLine.class,
                docInvoice.p_lines[i].m_TrxLine_ID);
            ShipmentInOutLine inoutline = line.getGoodsShipmentLine();
            if (inoutline != null) {
              List<MaterialTransaction> transaction = inoutline
                  .getMaterialMgmtMaterialTransactionList();
              if (transaction.size() > 0) {
                transactionCost = transaction.get(0).getTransactionCost();
                if (transactionCost != null && (transactionCost.compareTo(BigDecimal.ZERO) != 0)) {
                  BigDecimal revenueAmount = new BigDecimal(amount);
                  convertedRevenueAmount = Utility.getConvertedAmt(docInvoice.C_Currency_ID,
                      as.m_C_Currency_ID, docInvoice.DateAcct, revenueAmount);
                  selisih = convertedRevenueAmount.subtract(transactionCost);
                }

              }
            }
          }

          if (selisih != null && (selisih.compareTo(BigDecimal.ZERO) != 0)) {
            // ada selisih antara reimbursement revenue dengan cost nya, dijurnal ke reimbursement
            // variance
            org.openbravo.model.financialmgmt.accounting.coa.AcctSchema accountingSchema = OBDal
                .getInstance().get(
                    org.openbravo.model.financialmgmt.accounting.coa.AcctSchema.class,
                    as.m_C_AcctSchema_ID);
            List<AcctSchemaGL> acctSchemaGLList = accountingSchema
                .getFinancialMgmtAcctSchemaGLList();
            AccountingCombination reimbursementVariance = null;
            Account reimbursementVarianceAccount = null;
            if (acctSchemaGLList.size() > 0) {
              reimbursementVariance = acctSchemaGLList.get(0).getOezPurcaseAcct();
              if (reimbursementVariance == null) {
                throw new OBException("Reimbursement Variance Account not found");
              }
              reimbursementVarianceAccount = new Account(conn, reimbursementVariance.getId());
            }
            if (docInvoice.IsReversal.equals("Y")) {
              fact.createLine(docInvoice.p_lines[i], account, as.m_C_Currency_ID,
                  transactionCost.toString(), "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
                  docInvoice.DocumentType, conn);
              fact.createLine(docInvoice.p_lines[i], reimbursementVarianceAccount,
                  as.m_C_Currency_ID, selisih.toString(), "", Fact_Acct_Group_ID,
                  docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            } else {
              fact.createLine(docInvoice.p_lines[i], account, as.m_C_Currency_ID, "",
                  transactionCost.toString(), Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
                  docInvoice.DocumentType, conn);
              fact.createLine(docInvoice.p_lines[i], reimbursementVarianceAccount,
                  as.m_C_Currency_ID, "", selisih.toString(), Fact_Acct_Group_ID,
                  docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            }
          } else {
            if (docInvoice.IsReversal.equals("Y")) {
              fact.createLine(docInvoice.p_lines[i], account, docInvoice.C_Currency_ID, amount, "",
                  Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            } else {
              fact.createLine(docInvoice.p_lines[i], account, docInvoice.C_Currency_ID, "", amount,
                  Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            }
          }

          // If revenue has been deferred
          if (!amount.equals(docInvoice.p_lines[i].getAmount())) {
            amount = new BigDecimal(docInvoice.p_lines[i].getAmount()).subtract(
                new BigDecimal(amount)).toString();
            if (docInvoice.IsReversal.equals("Y")) {
              fact.createLine(docInvoice.p_lines[i], ((DocLine_Invoice) docInvoice.p_lines[i])
                  .getAccount(ProductInfo.ACCTTYPE_P_DefRevenue, as, conn),
                  docInvoice.C_Currency_ID, amount, "", Fact_Acct_Group_ID, docInvoice
                      .nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            } else {
              fact.createLine(docInvoice.p_lines[i], ((DocLine_Invoice) docInvoice.p_lines[i])
                  .getAccount(ProductInfo.ACCTTYPE_P_DefRevenue, as, conn),
                  docInvoice.C_Currency_ID, "", amount, Fact_Acct_Group_ID, docInvoice
                      .nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            }
          }
        }
      }
      // Set Locations
      FactLine[] fLines = fact.getLines();
      for (int i = 0; i < fLines.length; i++) {
        if (fLines[i] != null) {
          fLines[i].setLocationFromOrg(fLines[i].getAD_Org_ID(conn), true, conn); // from Loc
          fLines[i].setLocationFromBPartner(docInvoice.C_BPartner_Location_ID, false, conn); // to
                                                                                             // Loc
        }
      }
    }
    // ARC
    else if (docInvoice.DocumentType.equals(AcctServer.DOCTYPE_ARCredit)) {
      log4jDocInvoiceID.debug("Point 2");
      // Receivables CR
      if (m_payments == null || m_payments.length == 0)
        for (int i = 0; docInvoice.m_debt_payments != null && i < docInvoice.m_debt_payments.length; i++) {
          BigDecimal amount = new BigDecimal(docInvoice.m_debt_payments[i].getAmount());
          // BigDecimal docInvoice.ZERO = BigDecimal.docInvoice.ZERO;
          fact.createLine(docInvoice.m_debt_payments[i],
              docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true,
                  docInvoice.m_debt_payments[i].getDpStatus(), conn), docInvoice.C_Currency_ID, "",
              AcctServer.getConvertedAmt(((amount.negate())).toPlainString(),
                  docInvoice.m_debt_payments[i].getC_Currency_ID_From(), docInvoice.C_Currency_ID,
                  docInvoice.DateAcct, "", docInvoice.AD_Client_ID, docInvoice.AD_Org_ID, conn),
              Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        }
      else
        for (int i = 0; m_payments != null && i < m_payments.length; i++) {
          BigDecimal amount = new BigDecimal(m_payments[i].getAmount());
          BigDecimal prepaidAmount = new BigDecimal(m_payments[i].getPrepaidAmount());

          // balancing with tax if not

          double diffAmt = 0.0;
          for (int tax = 0; docInvoice.getM_taxes() != null && tax < docInvoice.getM_taxes().length; tax++) {
            if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[tax].m_C_Tax_ID)) {
              BigDecimal taxAmount = indonesianVAT(docInvoice.getM_taxes()[tax].m_rate,
                  docInvoice.Record_ID, docInvoice.getM_taxes()[tax].m_C_Tax_ID, true, 6,
                  BigDecimal.ROUND_DOWN);
              String differenceamount = Utility.getDiferenceConversion(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxAmount.toString());
              double differenceLine = Double.parseDouble(differenceamount);
              diffAmt += differenceLine;
              BigDecimal cvrtdTax = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxAmount);
              long currency = cvrtdTax.longValue();
              BigDecimal roundingCurrency = cvrtdTax.subtract(new BigDecimal(currency)).negate();
              diffAmt += roundingCurrency.doubleValue();
            }
          }

          if (diffAmt != 0) {
            // Credit Tidak Di Rounding
            String amountCvrt = "0";
            String sourceAmt = getGrossAmount(docInvoice.Record_ID).toString();// m_payments[i].getAmount();
            BigDecimal revAmt = new BigDecimal(sourceAmt);
            BigDecimal cvtAmt = Utility.getConvertedAmt(docInvoice.C_Currency_ID,
                as.getC_Currency_ID(), docInvoice.DateAcct, revAmt);
            BigDecimal totalAmt = cvtAmt.add(new BigDecimal(diffAmt));
            amountCvrt = totalAmt.negate().toString();
            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, false, conn),
                as.getC_Currency_ID(), "", amountCvrt, Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          } else {
            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, false, conn),
                docInvoice.C_Currency_ID, "", amount.negate().toString(), Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          }

          // Pre-payment: Probably not needed as at this point we can not generate pre-payments
          // against ARC. Amount is negated
          if (m_payments[i].getC_Currency_ID_From().equals(as.m_C_Currency_ID)
              && prepaidAmount.compareTo(docInvoice.ZERO) != 0) {
            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, true, conn),
                docInvoice.C_Currency_ID, "", prepaidAmount.negate().toString(),
                Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          } else {
            try {
              DocInvoiceIDData[] prepayments = DocInvoiceIDData.selectPrepayments(conn,
                  m_payments[i].getLine_ID());
              for (int j = 0; j < prepayments.length; j++) {
                BigDecimal prePaymentAmt = docInvoice.convertAmount(new BigDecimal(
                    prepayments[j].prepaidamt).negate(), true, docInvoice.DateAcct,
                    DocInvoice.TABLEID_Payment, prepayments[j].finPaymentId, m_payments[i]
                        .getC_Currency_ID_From(), as.m_C_Currency_ID, m_payments[i], as, fact,
                    Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), conn);
                fact.createLine(m_payments[i],
                    docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, true, conn),
                    m_payments[i].getC_Currency_ID_From(), "", prePaymentAmt.toString(),
                    Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
              }
            } catch (ServletException e) {
              log4jDocInvoiceID.warn(e);
            }
          }
        }
      if ((m_payments == null || m_payments.length == 0)
          && (docInvoice.m_debt_payments == null || docInvoice.m_debt_payments.length == 0)) {
        fact.createLine(null,
            docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, true, false, conn),
            docInvoice.C_Currency_ID, "", docInvoice.Amounts[DocInvoice.AMTTYPE_Gross],
            Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
      }
      // Charge DR
      fact.createLine(null, docInvoice.getAccount(AcctServer.ACCTTYPE_Charge, as, conn),
          docInvoice.C_Currency_ID, docInvoice.getAmount(AcctServer.AMTTYPE_Charge), "",
          Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);

      // TaxDue DR
      for (int i = 0; docInvoice.getM_taxes() != null && i < docInvoice.getM_taxes().length; i++) {
        // New docLine created to assign C_Tax_ID value to the entry
        DocLine docLine = new DocLine(docInvoice.DocumentType, docInvoice.Record_ID, "");
        docLine.m_C_Tax_ID = docInvoice.getM_taxes()[i].m_C_Tax_ID;
        if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[i].m_C_Tax_ID)
            && !docInvoice.C_Currency_ID.equalsIgnoreCase(as.getC_Currency_ID())) {
          // converting using tax rate
          BigDecimal taxableAmount = indonesianVAT(docInvoice.getM_taxes()[i].m_rate,
              docInvoice.Record_ID, docInvoice.getM_taxes()[i].m_C_Tax_ID, true, 6,
              BigDecimal.ROUND_DOWN);
          BigDecimal convertedTaxAmt = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
              as.getC_Currency_ID(), docInvoice.DateAcct, taxableAmount);
          convertedTaxAmt = convertedTaxAmt.setScale(0, BigDecimal.ROUND_DOWN);
          fact.createLine(docLine,
              docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
              as.getC_Currency_ID(), convertedTaxAmt.toString(), "", Fact_Acct_Group_ID,
              docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);

        } else {
          fact.createLine(docLine,
              docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
              docInvoice.C_Currency_ID, docInvoice.getM_taxes()[i].getAmount(), "",
              Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        }
      }
      // Revenue CR
      for (int i = 0; docInvoice.p_lines != null && i < docInvoice.p_lines.length; i++) {
        String amount = docInvoice.p_lines[i].getAmount();
        Account account = ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(
            ProductInfo.ACCTTYPE_P_Revenue, as, conn);
        if (((DocLine_Invoice) docInvoice.p_lines[i]).isDeferred()) {
          amount = createAccDefRevenueFact(docInvoice, fact,
              (DocLine_Invoice) docInvoice.p_lines[i], account,
              ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(
                  ProductInfo.ACCTTYPE_P_DefRevenue, as, conn), amount, conn);
        }
        fact.createLine(docInvoice.p_lines[i], account, docInvoice.C_Currency_ID, amount, "",
            Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        // If revenue has been deferred
        if (!amount.equals(docInvoice.p_lines[i].getAmount())) {
          amount = new BigDecimal(docInvoice.p_lines[i].getAmount()).subtract(
              new BigDecimal(amount)).toString();
          fact.createLine(docInvoice.p_lines[i], ((DocLine_Invoice) docInvoice.p_lines[i])
              .getAccount(ProductInfo.ACCTTYPE_P_DefRevenue, as, conn), docInvoice.C_Currency_ID,
              amount, "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType,
              conn);
        }
      }
      // Set Locations
      FactLine[] fLines = fact.getLines();
      for (int i = 0; fLines != null && i < fLines.length; i++) {
        if (fLines[i] != null) {
          fLines[i].setLocationFromOrg(fLines[i].getAD_Org_ID(conn), true, conn); // from Loc
          fLines[i].setLocationFromBPartner(docInvoice.C_BPartner_Location_ID, false, conn); // to
                                                                                             // Loc
        }
      }

    }
    // API
    else if (docInvoice.DocumentType.equals(AcctServer.DOCTYPE_APInvoice)) {
      log4jDocInvoiceID.debug("Point 3");
      // Liability CR
      if (m_payments == null || m_payments.length == 0)
        for (int i = 0; docInvoice.m_debt_payments != null && i < docInvoice.m_debt_payments.length; i++) {
          if (docInvoice.m_debt_payments[i].getIsReceipt().equals("Y"))
            fact.createLine(docInvoice.m_debt_payments[i], docInvoice.getAccountBPartner(
                docInvoice.C_BPartner_ID, as, true, docInvoice.m_debt_payments[i].getDpStatus(),
                conn), docInvoice.C_Currency_ID, AcctServer.getConvertedAmt(
                docInvoice.m_debt_payments[i].getAmount(),
                docInvoice.m_debt_payments[i].getC_Currency_ID_From(), docInvoice.C_Currency_ID,
                docInvoice.DateAcct, "", docInvoice.AD_Client_ID, docInvoice.AD_Org_ID, conn), "",
                Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          else
            fact.createLine(docInvoice.m_debt_payments[i], docInvoice.getAccountBPartner(
                docInvoice.C_BPartner_ID, as, false, docInvoice.m_debt_payments[i].getDpStatus(),
                conn), docInvoice.C_Currency_ID, "", AcctServer.getConvertedAmt(
                docInvoice.m_debt_payments[i].getAmount(),
                docInvoice.m_debt_payments[i].getC_Currency_ID_From(), docInvoice.C_Currency_ID,
                docInvoice.DateAcct, "", docInvoice.AD_Client_ID, docInvoice.AD_Org_ID, conn),
                Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        }
      else
        for (int i = 0; m_payments != null && i < m_payments.length; i++) {
          double diffAmt = 0.0;
          for (int tax = 0; docInvoice.getM_taxes() != null && tax < docInvoice.getM_taxes().length; tax++) {
            if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[tax].m_C_Tax_ID)) {
              BigDecimal taxAmount = indonesianVAT(docInvoice.getM_taxes()[tax].m_rate,
                  docInvoice.Record_ID, docInvoice.getM_taxes()[tax].m_C_Tax_ID, true, 6,
                  BigDecimal.ROUND_DOWN);
              String differenceamount = Utility.getDiferenceConversion(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxAmount.toString());
              double differenceLine = Double.parseDouble(differenceamount);
              diffAmt += differenceLine;
              BigDecimal cvrtdTax = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxAmount);
              long currency = cvrtdTax.longValue();
              BigDecimal roundingCurrency = cvrtdTax.subtract(new BigDecimal(currency)).negate();
              diffAmt += roundingCurrency.doubleValue();
            }
          }

          if (diffAmt != 0) {
            String amount = "0";
            String sourceAmt = getGrossAmount(docInvoice.Record_ID).toString();// m_payments[i].getAmount();
            BigDecimal revAmt = new BigDecimal(sourceAmt);
            BigDecimal cvtAmt = Utility.getConvertedAmt(docInvoice.C_Currency_ID,
                as.getC_Currency_ID(), docInvoice.DateAcct, revAmt);
            BigDecimal totalAmt = cvtAmt.add(new BigDecimal(diffAmt));
            amount = totalAmt.toString();

            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, false, conn),
                as.getC_Currency_ID(), "", amount, Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
                docInvoice.DocumentType, conn);
          } else

            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, false, conn),
                docInvoice.C_Currency_ID, "", m_payments[i].getAmount(), Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          if (m_payments[i].getC_Currency_ID_From().equals(as.m_C_Currency_ID)
              && new BigDecimal(m_payments[i].getPrepaidAmount()).compareTo(docInvoice.ZERO) != 0) {
            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, true, conn),
                docInvoice.C_Currency_ID, "", m_payments[i].getPrepaidAmount(), Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          } else {
            try {
              DocInvoiceIDData[] prepayments = DocInvoiceIDData.selectPrepayments(conn,
                  m_payments[i].getLine_ID());
              for (int j = 0; j < prepayments.length; j++) {
                BigDecimal prePaymentAmt = docInvoice.convertAmount(new BigDecimal(
                    prepayments[j].prepaidamt), false, docInvoice.DateAcct,
                    DocInvoice.TABLEID_Payment, prepayments[j].finPaymentId, m_payments[i]
                        .getC_Currency_ID_From(), as.m_C_Currency_ID, m_payments[i], as, fact,
                    Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), conn);
                fact.createLine(m_payments[i],
                    docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, true, conn),
                    m_payments[i].getC_Currency_ID_From(), "", prePaymentAmt.toString(),
                    Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
              }
            } catch (ServletException e) {
              log4jDocInvoiceID.warn(e);
            }
          }
        }
      if ((m_payments == null || m_payments.length == 0)
          && (docInvoice.m_debt_payments == null || docInvoice.m_debt_payments.length == 0)) {
        fact.createLine(null,
            docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, false, conn),
            docInvoice.C_Currency_ID, "", docInvoice.Amounts[DocInvoice.AMTTYPE_Gross],
            Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
      }
      // Charge DR
      fact.createLine(null, docInvoice.getAccount(AcctServer.ACCTTYPE_Charge, as, conn),
          docInvoice.C_Currency_ID, docInvoice.getAmount(AcctServer.AMTTYPE_Charge), "",
          Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
      // TaxCredit DR
      for (int i = 0; docInvoice.getM_taxes() != null && i < docInvoice.getM_taxes().length; i++) {
        // New docLine created to assign C_Tax_ID value to the entry
        DocLine docLine = new DocLine(docInvoice.DocumentType, docInvoice.Record_ID, "");
        docLine.m_C_Tax_ID = docInvoice.getM_taxes()[i].m_C_Tax_ID;

        if (docInvoice.getM_taxes()[i].m_isTaxUndeductable) {
          computeTaxUndeductableLine(conn, as, fact, docLine, Fact_Acct_Group_ID,
              docInvoice.getM_taxes()[i].m_C_Tax_ID, docInvoice.getM_taxes()[i].getAmount(),
              docInvoice);
        } else {
          if (docInvoice.IsReversal.equals("Y")) {
            if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[i].m_C_Tax_ID)) {
              // converting using tax rate
              BigDecimal taxableAmount = indonesianVAT(docInvoice.getM_taxes()[i].m_rate,
                  docInvoice.Record_ID, docInvoice.getM_taxes()[i].m_C_Tax_ID, true, 6,
                  BigDecimal.ROUND_DOWN);
              BigDecimal convertedTaxAmt = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxableAmount);
              convertedTaxAmt = convertedTaxAmt.setScale(0, BigDecimal.ROUND_DOWN);
              fact.createLine(docLine,
                  docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
                  as.getC_Currency_ID(), "", convertedTaxAmt.toString(), Fact_Acct_Group_ID,
                  docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            } else {
              fact.createLine(docLine,
                  docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxCredit, as, conn),
                  docInvoice.C_Currency_ID, "", docInvoice.getM_taxes()[i].getAmount(),
                  Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            }
          } else {
            if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[i].m_C_Tax_ID)) {
              // converting using tax rate
              BigDecimal taxableAmount = indonesianVAT(docInvoice.getM_taxes()[i].m_rate,
                  docInvoice.Record_ID, docInvoice.getM_taxes()[i].m_C_Tax_ID, true, 6,
                  BigDecimal.ROUND_DOWN);
              BigDecimal convertedTaxAmt = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxableAmount);
              convertedTaxAmt = convertedTaxAmt.setScale(0, BigDecimal.ROUND_DOWN);
              fact.createLine(docLine,
                  docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
                  as.getC_Currency_ID(), convertedTaxAmt.toString(), "", Fact_Acct_Group_ID,
                  docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            } else {
              fact.createLine(docLine,
                  docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxCredit, as, conn),
                  docInvoice.C_Currency_ID, docInvoice.getM_taxes()[i].getAmount(), "",
                  Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
            }
          }
        }
      }
      // Expense DR
      for (int i = 0; docInvoice.p_lines != null && i < docInvoice.p_lines.length; i++) {
        String amount = docInvoice.p_lines[i].getAmount();
        if (((DocLine_Invoice) docInvoice.p_lines[i]).isDeferred()) {
          amount = createAccDefExpenseFact(docInvoice, fact,
              (DocLine_Invoice) docInvoice.p_lines[i],
              ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(ProductInfo.ACCTTYPE_P_Expense,
                  as, conn), ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(
                  ProductInfo.ACCTTYPE_P_DefExpense, as, conn), amount, conn);
        }
        if (docInvoice.IsReversal.equals("Y"))
          fact.createLine(docInvoice.p_lines[i], ((DocLine_Invoice) docInvoice.p_lines[i])
              .getAccount(ProductInfo.ACCTTYPE_P_Expense, as, conn), docInvoice.C_Currency_ID, "",
              amount, Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType,
              conn);
        else
          fact.createLine(docInvoice.p_lines[i], ((DocLine_Invoice) docInvoice.p_lines[i])
              .getAccount(ProductInfo.ACCTTYPE_P_Expense, as, conn), docInvoice.C_Currency_ID,
              amount, "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType,
              conn);
        // If expense has been deferred
        if (!amount.equals(docInvoice.p_lines[i].getAmount())) {
          amount = new BigDecimal(docInvoice.p_lines[i].getAmount()).subtract(
              new BigDecimal(amount)).toString();
          if (docInvoice.IsReversal.equals("Y")) {
            fact.createLine(docInvoice.p_lines[i], ((DocLine_Invoice) docInvoice.p_lines[i])
                .getAccount(ProductInfo.ACCTTYPE_P_DefExpense, as, conn), docInvoice.C_Currency_ID,
                "", amount, Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
                docInvoice.DocumentType, conn);
          } else {
            fact.createLine(docInvoice.p_lines[i], ((DocLine_Invoice) docInvoice.p_lines[i])
                .getAccount(ProductInfo.ACCTTYPE_P_DefExpense, as, conn), docInvoice.C_Currency_ID,
                amount, "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
                docInvoice.DocumentType, conn);
          }
        }
      }
      // Set Locations
      FactLine[] fLines = fact.getLines();
      for (int i = 0; fLines != null && i < fLines.length; i++) {
        if (fLines[i] != null) {
          fLines[i].setLocationFromBPartner(docInvoice.C_BPartner_Location_ID, true, conn); // from
                                                                                            // Loc
          fLines[i].setLocationFromOrg(fLines[i].getAD_Org_ID(conn), false, conn); // to Loc
        }
      }
      if (convertedTax) {
        BigDecimal curencySelisih = getSourceBalance(fact);
        fact.createLine(null, as.getCurrencyBalancing_Acct(), as.getC_Currency_ID(), "",
            curencySelisih.toString(), Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
      }
      docInvoice.updateProductInfo(as.getC_AcctSchema_ID(), conn, con); // only API
    }
    // APC
    else if (docInvoice.DocumentType.equals(AcctServer.DOCTYPE_APCredit)) {
      log4jDocInvoiceID.debug("Point 4");
      // Liability DR
      if (m_payments == null || m_payments.length == 0)
        for (int i = 0; docInvoice.m_debt_payments != null && i < docInvoice.m_debt_payments.length; i++) {
          BigDecimal amount = new BigDecimal(docInvoice.m_debt_payments[i].getAmount());
          // BigDecimal docInvoice.ZERO = BigDecimal.docInvoice.ZERO;
          fact.createLine(docInvoice.m_debt_payments[i], docInvoice.getAccountBPartner(
              docInvoice.C_BPartner_ID, as, false, docInvoice.m_debt_payments[i].getDpStatus(),
              conn), docInvoice.C_Currency_ID, AcctServer.getConvertedAmt(
              ((amount.negate())).toPlainString(),
              docInvoice.m_debt_payments[i].getC_Currency_ID_From(), docInvoice.C_Currency_ID,
              docInvoice.DateAcct, "", docInvoice.AD_Client_ID, docInvoice.AD_Org_ID, conn), "",
              Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        }
      else
        for (int i = 0; m_payments != null && i < m_payments.length; i++) {
          BigDecimal amount = new BigDecimal(m_payments[i].getAmount());
          BigDecimal prepaidAmount = new BigDecimal(m_payments[i].getPrepaidAmount());

          double diffAmt = 0.0;
          for (int tax = 0; docInvoice.getM_taxes() != null && tax < docInvoice.getM_taxes().length; tax++) {
            if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[tax].m_C_Tax_ID)) {
              BigDecimal taxAmount = indonesianVAT(docInvoice.getM_taxes()[tax].m_rate,
                  docInvoice.Record_ID, docInvoice.getM_taxes()[tax].m_C_Tax_ID, true, 6,
                  BigDecimal.ROUND_DOWN);
              String differenceamount = Utility.getDiferenceConversion(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxAmount.toString());
              double differenceLine = Double.parseDouble(differenceamount);
              diffAmt += differenceLine;
              BigDecimal cvrtdTax = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
                  as.getC_Currency_ID(), docInvoice.DateAcct, taxAmount);
              long currency = cvrtdTax.longValue();
              BigDecimal roundingCurrency = cvrtdTax.subtract(new BigDecimal(currency)).negate();
              diffAmt += roundingCurrency.doubleValue();
            }
          }

          if (diffAmt != 0) {
            String amountCvrt = "0";
            String sourceAmt = getGrossAmount(docInvoice.Record_ID).toString();// m_payments[i].getAmount();
            BigDecimal revAmt = new BigDecimal(sourceAmt);
            BigDecimal cvtAmt = Utility.getConvertedAmt(docInvoice.C_Currency_ID,
                as.getC_Currency_ID(), docInvoice.DateAcct, revAmt);
            BigDecimal totalAmt = cvtAmt.subtract(new BigDecimal(diffAmt));
            amountCvrt = totalAmt.negate().toString();

            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, false, conn),
                as.getC_Currency_ID(), amountCvrt, "", Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          } else

            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, false, conn),
                docInvoice.C_Currency_ID, amount.negate().toString(), "", Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          // Pre-payment: Probably not needed as at this point we can not generate pre-payments
          // against APC. Amount is negated
          if (m_payments[i].getC_Currency_ID_From().equals(as.m_C_Currency_ID)
              && prepaidAmount.compareTo(docInvoice.ZERO) != 0) {
            fact.createLine(m_payments[i],
                docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, true, conn),
                docInvoice.C_Currency_ID, prepaidAmount.negate().toString(), "",
                Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          } else {
            try {
              DocInvoiceIDData[] prepayments = DocInvoiceIDData.selectPrepayments(conn,
                  m_payments[i].getLine_ID());
              for (int j = 0; j < prepayments.length; j++) {
                BigDecimal prePaymentAmt = docInvoice.convertAmount(new BigDecimal(
                    prepayments[j].prepaidamt).negate(), false, docInvoice.DateAcct,
                    DocInvoice.TABLEID_Payment, prepayments[j].finPaymentId, m_payments[i]
                        .getC_Currency_ID_From(), as.m_C_Currency_ID, m_payments[i], as, fact,
                    Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), conn);
                fact.createLine(m_payments[i],
                    docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, true, conn),
                    m_payments[i].getC_Currency_ID_From(), prePaymentAmt.toString(), "",
                    Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
              }
            } catch (ServletException e) {
              log4jDocInvoiceID.warn(e);
            }
          }
        }
      if ((m_payments == null || m_payments.length == 0)
          && (docInvoice.m_debt_payments == null || docInvoice.m_debt_payments.length == 0)) {
        fact.createLine(null,
            docInvoice.getAccountBPartner(docInvoice.C_BPartner_ID, as, false, false, conn),
            docInvoice.C_Currency_ID, docInvoice.Amounts[DocInvoice.AMTTYPE_Gross], "",
            Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
      }
      // Charge CR
      fact.createLine(null, docInvoice.getAccount(AcctServer.ACCTTYPE_Charge, as, conn),
          docInvoice.C_Currency_ID, "", docInvoice.getAmount(AcctServer.AMTTYPE_Charge),
          Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
      // TaxCredit CR
      for (int i = 0; docInvoice.getM_taxes() != null && i < docInvoice.getM_taxes().length; i++) {
        // New docLine created to assign C_Tax_ID value to the entry
        DocLine docLine = new DocLine(docInvoice.DocumentType, docInvoice.Record_ID, "");
        docLine.m_C_Tax_ID = docInvoice.getM_taxes()[i].m_C_Tax_ID;
        if (docInvoice.getM_taxes()[i].m_isTaxUndeductable) {
          computeTaxUndeductableLine(conn, as, fact, docLine, Fact_Acct_Group_ID,
              docInvoice.getM_taxes()[i].m_C_Tax_ID, docInvoice.getM_taxes()[i].getAmount(),
              docInvoice);

        } else {
          if (Utility.isConversionByTaxRate(docInvoice.getM_taxes()[i].m_C_Tax_ID)) {
            // converting using tax rate
            BigDecimal taxableAmount = indonesianVAT(docInvoice.getM_taxes()[i].m_rate,
                docInvoice.Record_ID, docInvoice.getM_taxes()[i].m_C_Tax_ID, true, 6,
                BigDecimal.ROUND_DOWN);
            BigDecimal convertedTaxAmt = Utility.getTaxConvertedAmt(docInvoice.C_Currency_ID,
                as.getC_Currency_ID(), docInvoice.DateAcct, taxableAmount);
            convertedTaxAmt = convertedTaxAmt.setScale(0, BigDecimal.ROUND_DOWN);
            fact.createLine(docLine,
                docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxDue, as, conn),
                as.getC_Currency_ID(), "", convertedTaxAmt.toString(), Fact_Acct_Group_ID,
                docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          } else {
            fact.createLine(docLine,
                docInvoice.getM_taxes()[i].getAccount(DocTax.ACCTTYPE_TaxCredit, as, conn),
                docInvoice.C_Currency_ID, "", docInvoice.getM_taxes()[i].getAmount(),
                Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
          }
        }
      }
      // Expense CR
      for (int i = 0; docInvoice.p_lines != null && i < docInvoice.p_lines.length; i++) {
        String amount = docInvoice.p_lines[i].getAmount();
        Account account = ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(
            ProductInfo.ACCTTYPE_P_Expense, as, conn);
        if (((DocLine_Invoice) docInvoice.p_lines[i]).isDeferred()) {
          amount = createAccDefExpenseFact(docInvoice, fact,
              (DocLine_Invoice) docInvoice.p_lines[i], account,
              ((DocLine_Invoice) docInvoice.p_lines[i]).getAccount(
                  ProductInfo.ACCTTYPE_P_DefExpense, as, conn), amount, conn);
        }
        fact.createLine(docInvoice.p_lines[i], account, docInvoice.C_Currency_ID, "", amount,
            Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        // If expense has been deferred
        if (!amount.equals(docInvoice.p_lines[i].getAmount())) {
          amount = new BigDecimal(docInvoice.p_lines[i].getAmount()).subtract(
              new BigDecimal(amount)).toString();
          fact.createLine(docInvoice.p_lines[i], ((DocLine_Invoice) docInvoice.p_lines[i])
              .getAccount(ProductInfo.ACCTTYPE_P_DefExpense, as, conn), docInvoice.C_Currency_ID,
              "", amount, Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType,
              conn);
        }

      }
      // Set Locations
      FactLine[] fLines = fact.getLines();
      for (int i = 0; fLines != null && i < fLines.length; i++) {
        if (fLines[i] != null) {
          fLines[i].setLocationFromBPartner(docInvoice.C_BPartner_Location_ID, true, conn); // from
                                                                                            // Loc
          fLines[i].setLocationFromOrg(fLines[i].getAD_Org_ID(conn), false, conn); // to Loc
        }
      }
      if (convertedTax) {
        BigDecimal curencySelisih = getSourceBalance(fact);
        fact.createLine(null, as.getCurrencyBalancing_Acct(), as.getC_Currency_ID(), "",
            curencySelisih.toString(), Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
      }
    } else {
      log4jDocInvoiceID.warn("Doc_Invoice - docInvoice.DocumentType unknown: "
          + docInvoice.DocumentType);
      fact = null;
    }
    SeqNo = "0";
    return fact;
  }// createFact

  protected BigDecimal getSourceBalance(Fact fact) {

    BigDecimal result = new BigDecimal("0");
    for (int i = 0; i < fact.getLines().length; i++) {
      FactLine line = (FactLine) fact.getLines()[i];
      BigDecimal lineAmount = line.getAccountingBalance();
      result = result.add(lineAmount);
    }
    // log.debug ("getSourceBalance - " + result.toString());
    return result;
  } // getSourceBalance

  private void computeTaxUndeductableLine(ConnectionProvider conn, AcctSchema as, Fact fact,
      DocLine docLine, String Fact_Acct_Group_ID, String taxId, String strTaxAmount,
      DocInvoice docInvoice) {
    int invoiceLineTaxCount = 0;
    int totalInvoiceLineTax = getTaxLineCount(conn, taxId, docInvoice);
    BigDecimal cumulativeTaxLineAmount = new BigDecimal(0);
    BigDecimal taxAmount = new BigDecimal(strTaxAmount.equals("") ? "0.00" : strTaxAmount);
    DocInvoiceIDData[] data = null;
    try {
      // We can have some lines from product or some lines from general ledger
      data = DocInvoiceIDData.selectProductAcct(conn, as.getC_AcctSchema_ID(), taxId,
          docInvoice.Record_ID);
      cumulativeTaxLineAmount = createLineForTaxUndeductable(invoiceLineTaxCount,
          totalInvoiceLineTax, cumulativeTaxLineAmount, taxAmount, data, conn, fact, docLine,
          Fact_Acct_Group_ID, docInvoice);
      invoiceLineTaxCount = data.length;
      // check whether gl item is selected instead of product in invoice line
      data = DocInvoiceIDData.selectGLItemAcctForTaxLine(conn, as.getC_AcctSchema_ID(), taxId,
          docInvoice.Record_ID);
      createLineForTaxUndeductable(invoiceLineTaxCount, totalInvoiceLineTax,
          cumulativeTaxLineAmount, taxAmount, data, conn, fact, docLine, Fact_Acct_Group_ID,
          docInvoice);
    } catch (ServletException e) {
      log4jDocInvoiceID.error("Exception in computeTaxUndeductableLine method: " + e);
    }
  }

  private int getTaxLineCount(ConnectionProvider conn, String taxId, DocInvoice docInvoice) {
    DocInvoiceIDData[] data = null;
    try {
      data = DocInvoiceIDData.getTaxLineCount(conn, taxId, docInvoice.Record_ID);
    } catch (ServletException e) {
      log4jDocInvoiceID.error("Exception in getTaxLineCount method: " + e);
    }
    if (data.length > 0) {
      return Integer.parseInt(data[0].totallines);
    }
    return 0;
  }

  private BigDecimal createLineForTaxUndeductable(int invoiceLineTaxCount, int totalInvoiceLineTax,
      BigDecimal cumulativeTaxLineAmount, BigDecimal taxAmount, DocInvoiceIDData[] data,
      ConnectionProvider conn, Fact fact, DocLine docLine, String Fact_Acct_Group_ID,
      DocInvoice docInvoice) {
    for (int j = 0; j < data.length; j++) {
      invoiceLineTaxCount++;
      // We have to adjust the amount in last line of tax
      if (invoiceLineTaxCount == totalInvoiceLineTax) {
        data[j].taxamt = taxAmount.subtract(cumulativeTaxLineAmount).toPlainString();
      }
      try {
        // currently applicable for API and APC
        if (docInvoice.DocumentType.equals(AcctServer.DOCTYPE_APInvoice)) {
          fact.createLine(docLine, Account.getAccount(conn, data[j].pExpenseAcct),
              docInvoice.C_Currency_ID, data[j].taxamt, "", Fact_Acct_Group_ID,
              docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        } else if (docInvoice.DocumentType.equals(AcctServer.DOCTYPE_APCredit)) {
          fact.createLine(docLine, Account.getAccount(conn, data[j].pExpenseAcct),
              docInvoice.C_Currency_ID, "", data[j].taxamt, Fact_Acct_Group_ID,
              docInvoice.nextSeqNo(SeqNo), docInvoice.DocumentType, conn);
        }
        cumulativeTaxLineAmount = cumulativeTaxLineAmount.add(new BigDecimal(data[j].taxamt));
      } catch (ServletException e) {
        log4jDocInvoiceID.error("Exception in createLineForTaxUndeductable method: " + e);
      }
    }
    return cumulativeTaxLineAmount;
  }

  String createAccDefRevenueFact(DocInvoice docInvoice, Fact fact, DocLine_Invoice line,
      Account prodRevAccount, Account prodDefRevAccount, String lineAmount, ConnectionProvider conn) {
    BigDecimal amount = new BigDecimal(lineAmount);
    String Fact_Acct_Group_ID = SequenceIdData.getUUID();
    ArrayList<HashMap<String, String>> plan = new ArrayList<HashMap<String, String>>();
    Period startingPeriod = OBDal.getInstance().get(Period.class, line.getStartingPeriodId());
    plan = calculateAccDefPlan(docInvoice, conn, startingPeriod, line.getPeriodNumber(), amount);
    for (HashMap<String, String> planLine : plan) {
      DocLine planDocLine = new DocLine(docInvoice.DocumentType, docInvoice.Record_ID,
          line.m_TrxLine_ID);
      planDocLine.copyInfo(line);
      planDocLine.m_DateAcct = planLine.get("date");
      if (docInvoice.IsReversal.equals("Y")) {
        // Revenue Account
        fact.createLine(planDocLine, prodRevAccount, docInvoice.C_Currency_ID,
            planLine.get("amount"), "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
        // Deferred Revenue Account
        fact.createLine(planDocLine, prodDefRevAccount, docInvoice.C_Currency_ID, "",
            planLine.get("amount"), Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
      } else {
        // Deferred Revenue Account
        fact.createLine(planDocLine, prodDefRevAccount, docInvoice.C_Currency_ID,
            planLine.get("amount"), "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
        // Revenue Account
        fact.createLine(planDocLine, prodRevAccount, docInvoice.C_Currency_ID, "",
            planLine.get("amount"), Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
      }
      amount = amount.subtract(new BigDecimal(planLine.get("amount")));
      Fact_Acct_Group_ID = SequenceIdData.getUUID();
    }

    return amount.toString();
  }

  private ArrayList<HashMap<String, String>> calculateAccDefPlan(DocInvoice docInvoice,
      ConnectionProvider conn, Period startingPeriod, int periodNumber, BigDecimal amount) {
    Period period = startingPeriod;
    Date date = period.getEndingDate();
    ArrayList<HashMap<String, String>> plan = new ArrayList<HashMap<String, String>>();
    int i = 1;
    BigDecimal total = BigDecimal.ZERO;
    BigDecimal periodAmount = amount.divide(new BigDecimal(periodNumber), BigDecimal.ROUND_HALF_UP);
    while (i <= periodNumber) {
      if (!OBDateUtils.formatDate(date).equals(docInvoice.DateAcct)) {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("date", OBDateUtils.formatDate(date));
        hm.put("amount",
            i == periodNumber ? amount.subtract(total).toString() : periodAmount.toString());
        plan.add(hm);
      }
      period = AccDefUtility.getNextPeriod(period);
      try {
        AcctServerData[] data = AcctServerData.periodOpen(conn, docInvoice.AD_Client_ID,
            docInvoice.DocumentType, docInvoice.AD_Org_ID,
            OBDateUtils.formatDate(period.getEndingDate()));
        if ("".equals(data[0].period)) {
          docInvoice.setStatus(DocInvoice.STATUS_PeriodClosed);
          throw new IllegalStateException("DocInvoice - Error getting next year period");
        }
      } catch (ServletException e) {
        log4jDocInvoiceID.warn("DocInvoice - Error checking period open.", e);
        e.printStackTrace();
      }
      date = period.getEndingDate();
      total = total.add(periodAmount);
      i++;
    }
    return plan;
  }

  private BigDecimal getGrossAmount(String invoiceID) {
    BigDecimal grossAmount = new BigDecimal(0);
    try {
      String whereClause = "as ltax where ltax.invoiceLine.invoice.id=?";
      List<Object> params = new ArrayList<Object>();
      params.add(invoiceID);
      OBQuery<InvoiceLineTax> lineTaxInvoice = OBDal.getInstance().createQuery(
          InvoiceLineTax.class, whereClause, params);
      List<InvoiceLineTax> lineTax = lineTaxInvoice.list();
      for (InvoiceLineTax invoiceLineTax : lineTax) {
        BigDecimal taxableAmount = invoiceLineTax.getTaxableAmount();
        BigDecimal taxRate = invoiceLineTax.getTax().getRate();
        BigDecimal taxAmount = taxableAmount.multiply((taxRate.divide(new BigDecimal("100"))));
        BigDecimal currentAmount = taxableAmount.add(taxAmount);
        grossAmount = grossAmount.add(currentAmount);
      }
    } catch (Exception e) {
      log4jDocInvoiceID.error(e.getLocalizedMessage());
    }
    return grossAmount;
  }

  /**
   * 
   * @param taxRate
   * @param invoiceID
   *          ,String taxID
   * @param usePecision
   * @param precision
   * @param roundinMode
   * @return
   */
  private BigDecimal indonesianVAT(String taxRate, String invoiceID, String taxID,
      boolean usePecision, int precision, int roundinMode) {
    BigDecimal taxAmount = null;
    try {
      BigDecimal taxableAmount = new BigDecimal(0);
      String whereClause = "where invoice.id=? and tax.id=?";
      List<Object> params = new ArrayList<Object>();
      params.add(invoiceID);
      params.add(taxID);
      OBQuery<InvoiceTax> invoiceTax = OBDal.getInstance().createQuery(InvoiceTax.class,
          whereClause, params);
      InvoiceTax listTax = invoiceTax.list().get(0);
      taxableAmount = listTax != null ? listTax.getTaxableAmount() : taxableAmount;
      taxAmount = taxableAmount.multiply((new BigDecimal(taxRate).divide(new BigDecimal("100"))));
      if (usePecision) {
        taxAmount = taxAmount.setScale(precision, roundinMode);
      }
    } catch (Exception e) {
      log4jDocInvoiceID.warn(e.getLocalizedMessage());
      e.printStackTrace();
    }
    return taxAmount;
  }

  String createAccDefExpenseFact(DocInvoice docInvoice, Fact fact, DocLine_Invoice line,
      Account prodExpAccount, Account prodDefExpAccount, String lineAmount, ConnectionProvider conn) {
    BigDecimal amount = new BigDecimal(lineAmount);
    String Fact_Acct_Group_ID = SequenceIdData.getUUID();
    ArrayList<HashMap<String, String>> plan = new ArrayList<HashMap<String, String>>();
    Period startingPeriod = OBDal.getInstance().get(Period.class, line.getStartingPeriodId());
    plan = calculateAccDefPlan(docInvoice, conn, startingPeriod, line.getPeriodNumber(), amount);
    for (HashMap<String, String> planLine : plan) {
      DocLine planDocLine = new DocLine(docInvoice.DocumentType, docInvoice.Record_ID,
          line.m_TrxLine_ID);
      planDocLine.copyInfo(line);
      planDocLine.m_DateAcct = planLine.get("date");
      if (docInvoice.IsReversal.equals("Y")) {
        // Expense Account
        fact.createLine(planDocLine, prodExpAccount, docInvoice.C_Currency_ID, "",
            planLine.get("amount"), Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
        // Deferred Expense Account
        fact.createLine(planDocLine, prodDefExpAccount, docInvoice.C_Currency_ID,
            planLine.get("amount"), "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
      } else {
        // Deferred Expense Account
        fact.createLine(planDocLine, prodDefExpAccount, docInvoice.C_Currency_ID, "",
            planLine.get("amount"), Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
        // Expense Account
        fact.createLine(planDocLine, prodExpAccount, docInvoice.C_Currency_ID,
            planLine.get("amount"), "", Fact_Acct_Group_ID, docInvoice.nextSeqNo(SeqNo),
            docInvoice.DocumentType, conn);
      }
      amount = amount.subtract(new BigDecimal(planLine.get("amount")));
      Fact_Acct_Group_ID = SequenceIdData.getUUID();
    }
    return amount.toString();
  }
}
