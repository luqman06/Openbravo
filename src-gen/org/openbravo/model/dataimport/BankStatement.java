/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2008-2014 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.dataimport;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.bank.BankAccount;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.financialmgmt.cashmgmt.BankStatementLine;
import org.openbravo.model.financialmgmt.gl.GLCharge;
import org.openbravo.model.financialmgmt.payment.DebtPayment;
/**
 * Entity class for entity DataImportBankStatement (stored in table I_BankStatement).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class BankStatement extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "I_BankStatement";
    public static final String ENTITY_NAME = "DataImportBankStatement";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_IMPORTPROCESSCOMPLETE = "importProcessComplete";
    public static final String PROPERTY_IMPORTERRORMESSAGE = "importErrorMessage";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_BANKSTATEMENT = "bankStatement";
    public static final String PROPERTY_TRANSACTIONDATE = "transactionDate";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_BANKACCOUNT = "bankAccount";
    public static final String PROPERTY_ROUTINGNO = "routingNo";
    public static final String PROPERTY_BANKACCOUNTNO = "bankAccountNo";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_BUSINESSPARTNERSEARCHKEY = "businessPartnerSearchKey";
    public static final String PROPERTY_INVOICE = "invoice";
    public static final String PROPERTY_INVOICEDOCUMENTNO = "invoiceDocumentNo";
    public static final String PROPERTY_CHARGE = "charge";
    public static final String PROPERTY_CHARGENAME = "chargeName";
    public static final String PROPERTY_CHARGEAMOUNT = "chargeAmount";
    public static final String PROPERTY_BANKSTATEMENTLINE = "bankStatementLine";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_ACCOUNTINGDATE = "accountingDate";
    public static final String PROPERTY_TRANSACTIONTYPE = "transactionType";
    public static final String PROPERTY_REFERENCENO = "referenceNo";
    public static final String PROPERTY_MEMO = "memo";
    public static final String PROPERTY_EFFECTIVEDATE = "effectiveDate";
    public static final String PROPERTY_REVERSAL = "reversal";
    public static final String PROPERTY_INTERESTAMOUNT = "interestAmount";
    public static final String PROPERTY_TRANSACTIONAMOUNT = "transactionAmount";
    public static final String PROPERTY_LINEDESCRIPTION = "lineDescription";
    public static final String PROPERTY_STATEMENTAMOUNT = "statementAmount";
    public static final String PROPERTY_PAYMENT = "payment";

    public BankStatement() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_IMPORTPROCESSCOMPLETE, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_REVERSAL, false);
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public Boolean isImportProcessComplete() {
        return (Boolean) get(PROPERTY_IMPORTPROCESSCOMPLETE);
    }

    public void setImportProcessComplete(Boolean importProcessComplete) {
        set(PROPERTY_IMPORTPROCESSCOMPLETE, importProcessComplete);
    }

    public String getImportErrorMessage() {
        return (String) get(PROPERTY_IMPORTERRORMESSAGE);
    }

    public void setImportErrorMessage(String importErrorMessage) {
        set(PROPERTY_IMPORTERRORMESSAGE, importErrorMessage);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public org.openbravo.model.financialmgmt.cashmgmt.BankStatement getBankStatement() {
        return (org.openbravo.model.financialmgmt.cashmgmt.BankStatement) get(PROPERTY_BANKSTATEMENT);
    }

    public void setBankStatement(org.openbravo.model.financialmgmt.cashmgmt.BankStatement bankStatement) {
        set(PROPERTY_BANKSTATEMENT, bankStatement);
    }

    public Date getTransactionDate() {
        return (Date) get(PROPERTY_TRANSACTIONDATE);
    }

    public void setTransactionDate(Date transactionDate) {
        set(PROPERTY_TRANSACTIONDATE, transactionDate);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BankAccount getBankAccount() {
        return (BankAccount) get(PROPERTY_BANKACCOUNT);
    }

    public void setBankAccount(BankAccount bankAccount) {
        set(PROPERTY_BANKACCOUNT, bankAccount);
    }

    public String getRoutingNo() {
        return (String) get(PROPERTY_ROUTINGNO);
    }

    public void setRoutingNo(String routingNo) {
        set(PROPERTY_ROUTINGNO, routingNo);
    }

    public String getBankAccountNo() {
        return (String) get(PROPERTY_BANKACCOUNTNO);
    }

    public void setBankAccountNo(String bankAccountNo) {
        set(PROPERTY_BANKACCOUNTNO, bankAccountNo);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getBusinessPartnerSearchKey() {
        return (String) get(PROPERTY_BUSINESSPARTNERSEARCHKEY);
    }

    public void setBusinessPartnerSearchKey(String businessPartnerSearchKey) {
        set(PROPERTY_BUSINESSPARTNERSEARCHKEY, businessPartnerSearchKey);
    }

    public Invoice getInvoice() {
        return (Invoice) get(PROPERTY_INVOICE);
    }

    public void setInvoice(Invoice invoice) {
        set(PROPERTY_INVOICE, invoice);
    }

    public String getInvoiceDocumentNo() {
        return (String) get(PROPERTY_INVOICEDOCUMENTNO);
    }

    public void setInvoiceDocumentNo(String invoiceDocumentNo) {
        set(PROPERTY_INVOICEDOCUMENTNO, invoiceDocumentNo);
    }

    public GLCharge getCharge() {
        return (GLCharge) get(PROPERTY_CHARGE);
    }

    public void setCharge(GLCharge charge) {
        set(PROPERTY_CHARGE, charge);
    }

    public String getChargeName() {
        return (String) get(PROPERTY_CHARGENAME);
    }

    public void setChargeName(String chargeName) {
        set(PROPERTY_CHARGENAME, chargeName);
    }

    public BigDecimal getChargeAmount() {
        return (BigDecimal) get(PROPERTY_CHARGEAMOUNT);
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        set(PROPERTY_CHARGEAMOUNT, chargeAmount);
    }

    public BankStatementLine getBankStatementLine() {
        return (BankStatementLine) get(PROPERTY_BANKSTATEMENTLINE);
    }

    public void setBankStatementLine(BankStatementLine bankStatementLine) {
        set(PROPERTY_BANKSTATEMENTLINE, bankStatementLine);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public Date getAccountingDate() {
        return (Date) get(PROPERTY_ACCOUNTINGDATE);
    }

    public void setAccountingDate(Date accountingDate) {
        set(PROPERTY_ACCOUNTINGDATE, accountingDate);
    }

    public String getTransactionType() {
        return (String) get(PROPERTY_TRANSACTIONTYPE);
    }

    public void setTransactionType(String transactionType) {
        set(PROPERTY_TRANSACTIONTYPE, transactionType);
    }

    public String getReferenceNo() {
        return (String) get(PROPERTY_REFERENCENO);
    }

    public void setReferenceNo(String referenceNo) {
        set(PROPERTY_REFERENCENO, referenceNo);
    }

    public String getMemo() {
        return (String) get(PROPERTY_MEMO);
    }

    public void setMemo(String memo) {
        set(PROPERTY_MEMO, memo);
    }

    public Date getEffectiveDate() {
        return (Date) get(PROPERTY_EFFECTIVEDATE);
    }

    public void setEffectiveDate(Date effectiveDate) {
        set(PROPERTY_EFFECTIVEDATE, effectiveDate);
    }

    public Boolean isReversal() {
        return (Boolean) get(PROPERTY_REVERSAL);
    }

    public void setReversal(Boolean reversal) {
        set(PROPERTY_REVERSAL, reversal);
    }

    public BigDecimal getInterestAmount() {
        return (BigDecimal) get(PROPERTY_INTERESTAMOUNT);
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        set(PROPERTY_INTERESTAMOUNT, interestAmount);
    }

    public BigDecimal getTransactionAmount() {
        return (BigDecimal) get(PROPERTY_TRANSACTIONAMOUNT);
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        set(PROPERTY_TRANSACTIONAMOUNT, transactionAmount);
    }

    public String getLineDescription() {
        return (String) get(PROPERTY_LINEDESCRIPTION);
    }

    public void setLineDescription(String lineDescription) {
        set(PROPERTY_LINEDESCRIPTION, lineDescription);
    }

    public BigDecimal getStatementAmount() {
        return (BigDecimal) get(PROPERTY_STATEMENTAMOUNT);
    }

    public void setStatementAmount(BigDecimal statementAmount) {
        set(PROPERTY_STATEMENTAMOUNT, statementAmount);
    }

    public DebtPayment getPayment() {
        return (DebtPayment) get(PROPERTY_PAYMENT);
    }

    public void setPayment(DebtPayment payment) {
        set(PROPERTY_PAYMENT, payment);
    }

}
