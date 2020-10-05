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
package org.openbravo.model.common.businesspartner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.APRM_FinaccTransactionV;
import org.openbravo.advpaymentmngt.APRM_Finacc_Trx_Full_Acct_V;
import org.openbravo.advpaymentmngt.APRM_PaymentProposalPickEdit;
import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.ClientInformation;
import org.openbravo.model.ad.system.Language;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.bank.Bank;
import org.openbravo.model.common.bank.BankAccount;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.enterprise.WarehouseShipper;
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.hcm.SalaryCategory;
import org.openbravo.model.common.interaction.EmailInteraction;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.invoice.InvoiceLineAccountingDimension;
import org.openbravo.model.common.invoice.InvoiceLineV2;
import org.openbravo.model.common.invoice.InvoiceSchedule;
import org.openbravo.model.common.invoice.InvoiceTaxCashVAT_V;
import org.openbravo.model.common.invoice.InvoiceV2;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.order.OrderLineAccountingDimension;
import org.openbravo.model.common.plm.ApprovedVendor;
import org.openbravo.model.common.plm.ProductCustomer;
import org.openbravo.model.dataimport.BankStatement;
import org.openbravo.model.dataimport.BudgetLine;
import org.openbravo.model.dataimport.GLJournal;
import org.openbravo.model.dataimport.Invoice;
import org.openbravo.model.dataimport.Order;
import org.openbravo.model.dataimport.Product;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchemaElement;
import org.openbravo.model.financialmgmt.assetmgmt.AmortizationLineAccountingDimension;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.financialmgmt.payment.DebtPayment;
import org.openbravo.model.financialmgmt.payment.DebtPaymentCancelV;
import org.openbravo.model.financialmgmt.payment.DebtPaymentGenerateV;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebt;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebtRun;
import org.openbravo.model.financialmgmt.payment.DoubtfulDebtV;
import org.openbravo.model.financialmgmt.payment.FIN_BankStatementLine;
import org.openbravo.model.financialmgmt.payment.FIN_FinaccTransaction;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.payment.FIN_Payment;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentDetailV;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentPropDetailV;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentProposal;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentScheduleDetail;
import org.openbravo.model.financialmgmt.payment.Incoterms;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.TaxPayment;
import org.openbravo.model.manufacturing.cost.CostCenter;
import org.openbravo.model.manufacturing.maintenance.Worker;
import org.openbravo.model.manufacturing.transaction.ProductionRunEmployee;
import org.openbravo.model.manufacturing.transaction.WorkEffortEmployee;
import org.openbravo.model.materialmgmt.onhandquantity.PrereservationManualPickEdit;
import org.openbravo.model.materialmgmt.onhandquantity.Reservation;
import org.openbravo.model.materialmgmt.transaction.InOutLineAccountingDimension;
import org.openbravo.model.materialmgmt.transaction.MaterialTransactionV;
import org.openbravo.model.materialmgmt.transaction.ReturnMaterialReceiptPickEdit;
import org.openbravo.model.materialmgmt.transaction.ReturnMaterialShipmentPickEdit;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.model.mrp.ProductionRun;
import org.openbravo.model.mrp.PurchasingRun;
import org.openbravo.model.mrp.PurchasingRunLine;
import org.openbravo.model.mrp.SalesForecast;
import org.openbravo.model.pos.ExternalPOS;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.model.pricing.pricelist.PriceListSchemeLine;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.project.ActiveProposal;
import org.openbravo.model.project.Project;
import org.openbravo.model.project.ProjectProposal;
import org.openbravo.model.project.ProjectVendor;
import org.openbravo.model.sales.Commission;
import org.openbravo.model.sales.CommissionLine;
import org.openbravo.model.shipping.ShippingCompany;
import org.openbravo.model.timeandexpense.Sheet;
import org.openbravo.model.timeandexpense.SheetLine;
import org.openbravo.model.timeandexpense.SheetLineV;
import org.wirabumi.cam.WorkOrderWorker;
import org.wirabumi.gen.oez.CustomerType;
import org.wirabumi.gen.oez.ImportAsset;
import org.wirabumi.gen.oez.OEZ_I_Inout;
import org.wirabumi.gen.oez.importmasterdata.oez_i_bpartnerbalance;
import org.wirabumi.gen.oez.oez_daily_ln_prod_shrimp;
import org.wirabumi.gen.oez.oez_prod_octopus;
import org.wirabumi.hris.businesstrip.bt_businesstrip;
import org.wirabumi.hris.employee.master.data.EmployeeTransferLine;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Department;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Empinfo;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_License;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_Training;
import org.wirabumi.hris.employee.master.data.HRIS_C_Bp_education;
import org.wirabumi.hris.employee.master.data.KPIMeasurement;
import org.wirabumi.hris.employee.master.data.hris_benefits;
import org.wirabumi.hris.employee.master.data.hris_c_bp_competency;
import org.wirabumi.hris.employee.master.data.hris_c_bp_experience;
import org.wirabumi.hris.employee.master.data.hris_c_bp_punishment;
import org.wirabumi.hris.employee.master.data.hris_c_bp_reward;
import org.wirabumi.hris.employee.master.data.hris_case;
import org.wirabumi.hris.employee.master.data.hris_change_family;
import org.wirabumi.hris.employee.master.data.hris_contact;
import org.wirabumi.hris.employee.master.data.hris_deductionterm;
import org.wirabumi.hris.employee.master.data.hris_ec_lines;
import org.wirabumi.hris.employee.master.data.hris_education_admission;
import org.wirabumi.hris.employee.master.data.hris_education_exam;
import org.wirabumi.hris.employee.master.data.hris_education_permit;
import org.wirabumi.hris.employee.master.data.hris_employee_transfer;
import org.wirabumi.hris.employee.master.data.hris_employee_tree_v;
import org.wirabumi.hris.employee.master.data.hris_ge_employee;
import org.wirabumi.hris.employee.master.data.hris_insurance;
import org.wirabumi.hris.employee.master.data.hris_insurance_employee;
import org.wirabumi.hris.employee.master.data.hris_jobtitle;
import org.wirabumi.hris.employee.master.data.hris_pengdata_kel;
import org.wirabumi.hris.employee.master.data.hris_pengundurandiri;
import org.wirabumi.hris.employee.master.data.hris_r_line;
import org.wirabumi.hris.employee.master.data.hris_rappel_salary;
import org.wirabumi.hris.employee.master.data.hris_reimbursment;
import org.wirabumi.hris.employee.master.data.hris_site;
import org.wirabumi.hris.employee.master.data.hris_tp_employee;
import org.wirabumi.hris.employee.master.data.hris_training_calendar;
import org.wirabumi.hris.leave.lv_c_bp_leave;
import org.wirabumi.hris.leave.lv_c_bp_leave_v;
import org.wirabumi.hris.leave.lv_leave;
import org.wirabumi.hris.leave.lv_mass_leave_l;
import org.wirabumi.hris.leave.lv_tidakmasuk;
import org.wirabumi.hris.loan.ln_bill_register;
import org.wirabumi.hris.loan.ln_loan;
import org.wirabumi.hris.loan.ln_loanstatement;
import org.wirabumi.hris.overtime.ot_emergency_call;
import org.wirabumi.hris.overtime.ot_overtime;
import org.wirabumi.hris.payroll.pyr_incidental_deduction;
import org.wirabumi.hris.payroll.pyr_incidental_earning;
import org.wirabumi.hris.payroll.pyr_sal_variable;
import org.wirabumi.hris.payroll.pyr_salarypayment;
import org.wirabumi.hris.payroll.pyr_sp_employee;
import org.wirabumi.hris.pph.ImportPPh21;
import org.wirabumi.hris.pph.pph_1721;
import org.wirabumi.hris.pph.pph_pph21;
import org.wirabumi.hris.termination.tm_detail_pesangon;
import org.wirabumi.hris.termination.tm_form_pesangon;
import org.wirabumi.hris.termination.tm_set_phk;
import org.wirabumi.hris.termination.tm_termination;
import org.wirabumi.hris.timeandattendance.ImportManualSchedule;
import org.wirabumi.hris.timeandattendance.ManualSchedule;
import org.wirabumi.hris.timeandattendance.TAAttendance;
import org.wirabumi.hris.timeandattendance.ta_akumulasi_pot_cuti;
import org.wirabumi.hris.timeandattendance.ta_akumulasi_pot_gaji;
import org.wirabumi.hris.timeandattendance.ta_c_bp_shift;
import org.wirabumi.hris.timeandattendance.ta_detail_pot_cuti;
import org.wirabumi.hris.timeandattendance.ta_detail_pot_gaji;
import org.wirabumi.hris.timeandattendance.ta_tukar_shift;
/**
 * Entity class for entity BusinessPartner (stored in table C_BPartner).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class BusinessPartner extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BPartner";
    public static final String ENTITY_NAME = "BusinessPartner";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_NAME2 = "name2";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_SUMMARYLEVEL = "summaryLevel";
    public static final String PROPERTY_BUSINESSPARTNERCATEGORY = "businessPartnerCategory";
    public static final String PROPERTY_ONETIMETRANSACTION = "oneTimeTransaction";
    public static final String PROPERTY_POTENTIALCUSTOMER = "potentialCustomer";
    public static final String PROPERTY_VENDOR = "vendor";
    public static final String PROPERTY_CUSTOMER = "customer";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_ISSALESREPRESENTATIVE = "isSalesRepresentative";
    public static final String PROPERTY_REFERENCENO = "referenceNo";
    public static final String PROPERTY_DUNS = "dUNS";
    public static final String PROPERTY_URL = "uRL";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_TAXID = "taxID";
    public static final String PROPERTY_TAXEXEMPT = "taxExempt";
    public static final String PROPERTY_INVOICESCHEDULE = "invoiceSchedule";
    public static final String PROPERTY_VALUATION = "valuation";
    public static final String PROPERTY_VOLUMEOFSALES = "volumeOfSales";
    public static final String PROPERTY_NOOFEMPLOYEES = "noOfEmployees";
    public static final String PROPERTY_NAICSSIC = "nAICSSIC";
    public static final String PROPERTY_DATEOFFIRSTSALE = "dateOfFirstSale";
    public static final String PROPERTY_ACQUISITIONCOST = "acquisitionCost";
    public static final String PROPERTY_EXPECTEDLIFETIMEREVENUE = "expectedLifetimeRevenue";
    public static final String PROPERTY_LIFETIMEREVENUETODATE = "lifetimeRevenueToDate";
    public static final String PROPERTY_SHARE = "share";
    public static final String PROPERTY_FORMOFPAYMENT = "formOfPayment";
    public static final String PROPERTY_CREDITLIMIT = "creditLimit";
    public static final String PROPERTY_CREDITUSED = "creditUsed";
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_PRINTDISCOUNT = "printDiscount";
    public static final String PROPERTY_ORDERDESCRIPTION = "orderDescription";
    public static final String PROPERTY_ORDERREFERENCE = "orderReference";
    public static final String PROPERTY_POFORMOFPAYMENT = "pOFormOfPayment";
    public static final String PROPERTY_PURCHASEPRICELIST = "purchasePricelist";
    public static final String PROPERTY_POPAYMENTTERMS = "pOPaymentTerms";
    public static final String PROPERTY_NUMBEROFCOPIES = "numberOfCopies";
    public static final String PROPERTY_GREETING = "greeting";
    public static final String PROPERTY_INVOICETERMS = "invoiceTerms";
    public static final String PROPERTY_DELIVERYTERMS = "deliveryTerms";
    public static final String PROPERTY_DELIVERYMETHOD = "deliveryMethod";
    public static final String PROPERTY_SALESREPRESENTATIVE = "salesRepresentative";
    public static final String PROPERTY_PARTNERPARENT = "partnerParent";
    public static final String PROPERTY_CREDITSTATUS = "creditStatus";
    public static final String PROPERTY_FORCEDORG = "forcedOrg";
    public static final String PROPERTY_PRICESSHOWNINORDER = "pricesShownInOrder";
    public static final String PROPERTY_INVOICEGROUPING = "invoiceGrouping";
    public static final String PROPERTY_MATURITYDATE1 = "maturityDate1";
    public static final String PROPERTY_MATURITYDATE2 = "maturityDate2";
    public static final String PROPERTY_MATURITYDATE3 = "maturityDate3";
    public static final String PROPERTY_OPERATOR = "operator";
    public static final String PROPERTY_UPCEAN = "uPCEAN";
    public static final String PROPERTY_SALARYCATEGORY = "salaryCategory";
    public static final String PROPERTY_INVOICEPRINTFORMAT = "invoicePrintformat";
    public static final String PROPERTY_CONSUMPTIONDAYS = "consumptionDays";
    public static final String PROPERTY_BANKACCOUNT = "bankAccount";
    public static final String PROPERTY_TAXCATEGORY = "taxCategory";
    public static final String PROPERTY_POMATURITYDATE1 = "pOMaturityDate1";
    public static final String PROPERTY_POMATURITYDATE2 = "pOMaturityDate2";
    public static final String PROPERTY_POMATURITYDATE3 = "pOMaturityDate3";
    public static final String PROPERTY_TRANSACTIONALBANKACCOUNT = "transactionalBankAccount";
    public static final String PROPERTY_SOBPTAXCATEGORY = "sOBPTaxCategory";
    public static final String PROPERTY_FISCALCODE = "fiscalcode";
    public static final String PROPERTY_ISOFISCALCODE = "isofiscalcode";
    public static final String PROPERTY_INCOTERMSPO = "incotermsPO";
    public static final String PROPERTY_INCOTERMSSO = "incotermsSO";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_POPAYMENTMETHOD = "pOPaymentMethod";
    public static final String PROPERTY_ACCOUNT = "account";
    public static final String PROPERTY_POFINANCIALACCOUNT = "pOFinancialAccount";
    public static final String PROPERTY_CUSTOMERBLOCKING = "customerBlocking";
    public static final String PROPERTY_VENDORBLOCKING = "vendorBlocking";
    public static final String PROPERTY_PAYMENTIN = "paymentIn";
    public static final String PROPERTY_PAYMENTOUT = "paymentOut";
    public static final String PROPERTY_SALESINVOICE = "salesInvoice";
    public static final String PROPERTY_PURCHASEINVOICE = "purchaseInvoice";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_PURCHASEORDER = "purchaseOrder";
    public static final String PROPERTY_GOODSSHIPMENT = "goodsShipment";
    public static final String PROPERTY_GOODSRECEIPT = "goodsReceipt";
    public static final String PROPERTY_CASHVAT = "cashVAT";
    public static final String PROPERTY_SETNEWCURRENCY = "setNewCurrency";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_BIRTHPLACE = "birthPlace";
    public static final String PROPERTY_OEZCUSTOMERTYPE = "oezCustomertype";
    public static final String PROPERTY_BIRTHDAY = "birthDay";
    public static final String PROPERTY_OEZACTIVATED = "oezActivated";
    public static final String PROPERTY_OEZDOCSTATUS = "oezDocstatus";
    public static final String PROPERTY_HRISSITE = "hrisSite";
    public static final String PROPERTY_HRISBIRTHDAY = "hrisBirthday";
    public static final String PROPERTY_HRISRELIGION = "hrisReligion";
    public static final String PROPERTY_HRISMARITALSTATUS = "hRISMaritalStatus";
    public static final String PROPERTY_HRISBLOODGROUP = "hrisBloodgroup";
    public static final String PROPERTY_HRISJOINDATE = "hrisJoindate";
    public static final String PROPERTY_HRISRETIREMENTDATE = "hrisRetirementdate";
    public static final String PROPERTY_HRISCBPDEPARTMENT = "hrisCBpDepartment";
    public static final String PROPERTY_PYRPAYROLLMASTER = "pyrPayrollmaster";
    public static final String PROPERTY_HRISEMPLOYEMENTTYPE = "hRISEmployementType";
    public static final String PROPERTY_PYRISPAYROLLMASTER = "pyrIspayrollmaster";
    public static final String PROPERTY_HRISCONTRACTNO = "hRISContractNo";
    public static final String PROPERTY_PPHTAXMARITALSTATUS = "pphTaxmaritalstatus";
    public static final String PROPERTY_HRISVALIDTO = "hRISValidTo";
    public static final String PROPERTY_TMSETPHK = "tmSetPhk";
    public static final String PROPERTY_HRISLEVEL = "hRISLevel";
    public static final String PROPERTY_HRISPOSITION = "hRISPosition";
    public static final String PROPERTY_HRISJOBTITLE = "hrisJobtitle";
    public static final String PROPERTY_HRISSTAFF = "hRISStaff";
    public static final String PROPERTY_HRISEMPLOYEEWINDOW = "hRISEmployeeWindow";
    public static final String PROPERTY_HRISVALIDFROM = "hrisValidfrom";
    public static final String PROPERTY_HRISREPORTTO = "hrisReportTo";
    public static final String PROPERTY_HRISDOCUMENTNO = "hrisDocumentno";
    public static final String PROPERTY_HRISDESCRIPTION = "hrisDescription";
    public static final String PROPERTY_HRISCBPKPI = "hrisCBpKpi";
    public static final String PROPERTY_HRISCOMMENT = "hrisComment";
    public static final String PROPERTY_HRISTARGET = "hrisTarget";
    public static final String PROPERTY_HRISMONTH = "hrisMonth";
    public static final String PROPERTY_HRISYEAR = "hrisYear";
    public static final String PROPERTY_HRISCCOSTCENTER = "hrisCCostcenter";
    public static final String PROPERTY_HRISISSITE = "hrisIssite";
    public static final String PROPERTY_HRISEMPLOYEEPIC = "hrisEmployeepic";
    public static final String PROPERTY_PYRFIXSALARY = "pyrFixsalary";
    public static final String PROPERTY_HRISFINGERPRINT = "hrisFingerprint";
    public static final String PROPERTY_PYRACTUALFIXSALARY = "pyrActualfixsalary";
    public static final String PROPERTY_HRISSEX = "hrisSex";
    public static final String PROPERTY_OTRATEAMOUNT = "otRateamount";
    public static final String PROPERTY_HRISAGE = "hrisAge";
    public static final String PROPERTY_HRISCOSTCENTER = "hrisCostcenter";
    public static final String PROPERTY_HRISNATIONALITY = "hrisNationality";
    public static final String PROPERTY_HRISEMAIL = "hrisEmail";
    public static final String PROPERTY_OEZISPKP = "oezIsPkp";
    public static final String PROPERTY_HRISBIRTHPLACE = "hrisBirthplace";
    public static final String PROPERTY_OEZALLOWRETURNVENDOR = "oezAllowReturnVendor";
    public static final String PROPERTY_HRISECHELON = "hrisEchelon";
    public static final String PROPERTY_OEZUNDERDELIVERY = "oezUnderDelivery";
    public static final String PROPERTY_HRISCONTRACTTYPE = "hrisContracttype";
    public static final String PROPERTY_OEZOVERDELIVERY = "oezOverDelivery";
    public static final String PROPERTY_PYRCOPYSALARYTEMPLATE = "pyrCopySalaryTemplate";
    public static final String PROPERTY_HRISISTRAININGPROVIDER = "hrisIstrainingprovider";
    public static final String PROPERTY_OEZBPARTNERSQUENCE = "oezBpartnersquence";
    public static final String PROPERTY_HRISISINSURANCEPROVIDER = "hrisIsinsuranceprovider";
    public static final String PROPERTY_HRISIDCARDNO = "hrisIdcardNo";
    public static final String PROPERTY_HRISTITLENAME = "hrisTitleName";
    public static final String PROPERTY_CAMISCUSTODIAN = "camIscustodian";
    public static final String PROPERTY_IDNPPKP = "idNppkp";
    public static final String PROPERTY__COMPUTEDCOLUMNS = "_computedColumns";
    public static final String PROPERTY_ADUSERLIST = "aDUserList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLIST = "aPRMFinAccTransactionAcctVList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONVLIST = "aPRMFinaccTransactionVList";
    public static final String PROPERTY_APRMFINACCTRXFULLACCTVLIST = "aPRMFinaccTrxFullAcctVList";
    public static final String PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST = "aPRMPaymentProposalPickEditList";
    public static final String PROPERTY_ACTIVEPROPOSALVLIST = "activeProposalVList";
    public static final String PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST = "amortizationLineAccountingDimensionList";
    public static final String PROPERTY_APPROVEDVENDORLIST = "approvedVendorList";
    public static final String PROPERTY_BANKLIST = "bankList";
    public static final String PROPERTY_BUSINESSPARTNERSALESREPRESENTATIVELIST = "businessPartnerSalesRepresentativeList";
    public static final String PROPERTY_BUSINESSPARTNEREMPYRPAYROLLMASTERIDLIST = "businessPartnerEMPyrPayrollmasterIDList";
    public static final String PROPERTY_BUSINESSPARTNEREMHRISREPORTTOLIST = "businessPartnerEMHrisReportToList";
    public static final String PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST = "businessPartnerBankAccountList";
    public static final String PROPERTY_BUSINESSPARTNERDISCOUNTLIST = "businessPartnerDiscountList";
    public static final String PROPERTY_BUSINESSPARTNERLOCATIONLIST = "businessPartnerLocationList";
    public static final String PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST = "businessPartnerProductTemplateList";
    public static final String PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST = "businessPartnerWithholdingList";
    public static final String PROPERTY_INVOICETAXCASHVATVLIST = "invoiceTaxCashVATVList";
    public static final String PROPERTY_CLIENTINFORMATIONTEMPLATEBPARTNERLIST = "clientInformationTemplateBPartnerList";
    public static final String PROPERTY_CUSTOMERACCOUNTSLIST = "customerAccountsList";
    public static final String PROPERTY_DATAIMPORTBANKSTATEMENTLIST = "dataImportBankStatementList";
    public static final String PROPERTY_DATAIMPORTBUDGETLINELIST = "dataImportBudgetLineList";
    public static final String PROPERTY_DATAIMPORTBUSINESSPARTNERLIST = "dataImportBusinessPartnerList";
    public static final String PROPERTY_DATAIMPORTGLJOURNALLIST = "dataImportGLJournalList";
    public static final String PROPERTY_DATAIMPORTINVOICELIST = "dataImportInvoiceList";
    public static final String PROPERTY_DATAIMPORTORDERLIST = "dataImportOrderList";
    public static final String PROPERTY_DATAIMPORTPRODUCTLIST = "dataImportProductList";
    public static final String PROPERTY_EMAILINTERACTIONLIST = "emailInteractionList";
    public static final String PROPERTY_EMPLOYEEACCOUNTSLIST = "employeeAccountsList";
    public static final String PROPERTY_EMPLOYEESALARYCATEGORYLIST = "employeeSalaryCategoryList";
    public static final String PROPERTY_EXTERNALPOSLIST = "externalPOSList";
    public static final String PROPERTY_FINBANKSTATEMENTLINELIST = "fINBankStatementLineList";
    public static final String PROPERTY_FINDOUBTFULDEBTLIST = "fINDoubtfulDebtList";
    public static final String PROPERTY_FINDOUBTFULDEBTRUNLIST = "fINDoubtfulDebtRunList";
    public static final String PROPERTY_FINDOUBTFULDEBTVLIST = "fINDoubtfulDebtVList";
    public static final String PROPERTY_FINFINACCTRANSACTIONLIST = "fINFinaccTransactionList";
    public static final String PROPERTY_FINFINANCIALACCOUNTLIST = "fINFinancialAccountList";
    public static final String PROPERTY_FINPAYMENTLIST = "fINPaymentList";
    public static final String PROPERTY_FINPAYMENTDETAILVLIST = "fINPaymentDetailVList";
    public static final String PROPERTY_FINPAYMENTDETAILVBUSINESSPARTNERDIMLIST = "fINPaymentDetailVBusinessPartnerdimList";
    public static final String PROPERTY_FINPAYMENTPROPDETAILVLIST = "fINPaymentPropDetailVList";
    public static final String PROPERTY_FINPAYMENTPROPOSALLIST = "fINPaymentProposalList";
    public static final String PROPERTY_FINPAYMENTSCHEDULEDETAILLIST = "fINPaymentScheduleDetailList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST = "financialMgmtAccountingCombinationList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST = "financialMgmtAccountingFactList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST = "financialMgmtAcctSchemaElementList";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_FINANCIALMGMTASSETEMCAMCUSTODIANIDLIST = "financialMgmtAssetEmCamCustodianIdList";
    public static final String PROPERTY_FINANCIALMGMTASSETEMCAMVENDORIDLIST = "financialMgmtAssetEMCamVendorIDList";
    public static final String PROPERTY_FINANCIALMGMTASSETEMCAMMANUFACTURERIDLIST = "financialMgmtAssetEMCamManufacturerIDList";
    public static final String PROPERTY_FINANCIALMGMTASSETEMCAMPURCHASEORDERIDLIST = "financialMgmtAssetEMCamPurchaseorderIDList";
    public static final String PROPERTY_FINANCIALMGMTBUDGETLINELIST = "financialMgmtBudgetLineList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST = "financialMgmtDebtPaymentList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST = "financialMgmtDebtPaymentCancelVList";
    public static final String PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST = "financialMgmtDebtPaymentGenerateVList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLIST = "financialMgmtGLJournalList";
    public static final String PROPERTY_FINANCIALMGMTGLJOURNALLINELIST = "financialMgmtGLJournalLineList";
    public static final String PROPERTY_FINANCIALMGMTTAXPAYMENTLIST = "financialMgmtTaxPaymentList";
    public static final String PROPERTY_FINANCIALMGMTWITHHOLDINGBENEFICIARYLIST = "financialMgmtWithholdingBeneficiaryList";
    public static final String PROPERTY_HRISCBPEMPINFOLIST = "hRISCBpEmpinfoList";
    public static final String PROPERTY_HRISCBPEMPINFOREPORTTOLIST = "hRISCBpEmpinfoReportToList";
    public static final String PROPERTY_HRISCBPEMPINFOEMPYRPAYROLLMASTERLIST = "hRISCBpEmpinfoEMPYRPayrollMasterList";
    public static final String PROPERTY_HRISCBPLICENSELIST = "hRISCBpLicenseList";
    public static final String PROPERTY_HRISCBPTRAININGLIST = "hRISCBpTrainingList";
    public static final String PROPERTY_HRISCBPEDUCATIONLIST = "hRISCBpEducationList";
    public static final String PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST = "inOutLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELIST = "invoiceList";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";
    public static final String PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST = "invoiceLineAccountingDimensionList";
    public static final String PROPERTY_INVOICELINEV2LIST = "invoiceLineV2List";
    public static final String PROPERTY_INVOICEV2LIST = "invoiceV2List";
    public static final String PROPERTY_MRPPRODUCTIONRUNLIST = "mRPProductionRunList";
    public static final String PROPERTY_MRPPURCHASINGRUNVENDORLIST = "mRPPurchasingRunVendorList";
    public static final String PROPERTY_MRPPURCHASINGRUNLIST = "mRPPurchasingRunList";
    public static final String PROPERTY_MRPPURCHASINGRUNLINELIST = "mRPPurchasingRunLineList";
    public static final String PROPERTY_MRPSALESFORECASTLIST = "mRPSalesForecastList";
    public static final String PROPERTY_MANUFACTURINGMAINTENANCEWORKERLIST = "manufacturingMaintenanceWorkerList";
    public static final String PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST = "manufacturingProductionRunEmployeeList";
    public static final String PROPERTY_MANUFACTURINGWORKEFFORTEMPLOYEELIST = "manufacturingWorkEffortEmployeeList";
    public static final String PROPERTY_MATERIALMGMTRESERVATIONLIST = "materialMgmtReservationList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST = "materialMgmtShipmentInOutList";
    public static final String PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST = "materialMgmtShipmentInOutLineList";
    public static final String PROPERTY_OEZIINOUTLIST = "oEZIInoutList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERDROPSHIPPARTNERLIST = "orderDropShipPartnerList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST = "orderLineAccountingDimensionList";
    public static final String PROPERTY_ORGANIZATIONINFORMATIONLIST = "organizationInformationList";
    public static final String PROPERTY_PRERESERVATIONMANUALPICKEDITLIST = "prereservationManualPickEditList";
    public static final String PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST = "pricingAdjustmentBusinessPartnerList";
    public static final String PROPERTY_PRICINGPRICELISTSCHEMELINELIST = "pricingPriceListSchemeLineList";
    public static final String PROPERTY_PRICINGVOLUMEDISCOUNTBUSINESSPARTNERLIST = "pricingVolumeDiscountBusinessPartnerList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLIST = "procurementRequisitionList";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLINELIST = "procurementRequisitionLineList";
    public static final String PROPERTY_PRODUCTLIST = "productList";
    public static final String PROPERTY_PRODUCTCUSTOMERLIST = "productCustomerList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_PROJECTPERSONINCHARGELIST = "projectPersonInChargeList";
    public static final String PROPERTY_PROJECTPROPOSALLIST = "projectProposalList";
    public static final String PROPERTY_PROJECTVENDORLIST = "projectVendorList";
    public static final String PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST = "returnMaterialReceiptPickEditList";
    public static final String PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST = "returnMaterialShipmentPickEditList";
    public static final String PROPERTY_SALESCOMMISSIONLIST = "salesCommissionList";
    public static final String PROPERTY_SALESCOMMISSIONLINELIST = "salesCommissionLineList";
    public static final String PROPERTY_SHIPPINGSHIPPINGCOMPANYLIST = "shippingShippingCompanyList";
    public static final String PROPERTY_ATTENDANCELIST = "attendanceList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLIST = "timeAndExpenseSheetList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINELIST = "timeAndExpenseSheetLineList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINEVLIST = "timeAndExpenseSheetLineVList";
    public static final String PROPERTY_TIMEANDEXPENSESHEETLINEVCHARGEDBUSINESSPARTNERLIST = "timeAndExpenseSheetLineVChargedBusinessPartnerList";
    public static final String PROPERTY_TRANSACTIONVLIST = "transactionVList";
    public static final String PROPERTY_VENDORACCOUNTSLIST = "vendorAccountsList";
    public static final String PROPERTY_WAREHOUSESHIPPERLIST = "warehouseShipperList";
    public static final String PROPERTY_BUSINESSTRIPLIST = "businesstripList";
    public static final String PROPERTY_CAMWOWORKERLIST = "camWoWorkerList";
    public static final String PROPERTY_HRISBENEFITSLIST = "hrisBenefitsList";
    public static final String PROPERTY_HRISCBPCOMPETENCYLIST = "hrisCBpCompetencyList";
    public static final String PROPERTY_HRISCBPEXPERIENCELIST = "hrisCBpExperienceList";
    public static final String PROPERTY_HRISCBPPUNISHMENTLIST = "hrisCBpPunishmentList";
    public static final String PROPERTY_HRISCBPREWARDLIST = "hrisCBpRewardList";
    public static final String PROPERTY_HRISCASELIST = "hrisCaseList";
    public static final String PROPERTY_HRISCHANGEFAMILYLIST = "hrisChangeFamilyList";
    public static final String PROPERTY_HRISCONTACTLIST = "hrisContactList";
    public static final String PROPERTY_HRISDEDUCTIONTERMLIST = "hrisDeductionTermList";
    public static final String PROPERTY_HRISECLINESLIST = "hrisEcLinesList";
    public static final String PROPERTY_HRISEDUCATIONADMISSIONLIST = "hrisEducationAdmissionList";
    public static final String PROPERTY_HRISEDUCATIONEXAMLIST = "hrisEducationExamList";
    public static final String PROPERTY_HRISEDUCATIONPERMITLIST = "hrisEducationPermitList";
    public static final String PROPERTY_HRISEMPLOYEETRANSFERLIST = "hrisEmployeeTransferList";
    public static final String PROPERTY_HRISEMPLOYEETREEVLIST = "hrisEmployeeTreeVList";
    public static final String PROPERTY_HRISETLINELIST = "hrisEtLineList";
    public static final String PROPERTY_HRISETLINEOLDPAYROLLMASTERIDLIST = "hrisEtLineOLDPayrollmasterIDList";
    public static final String PROPERTY_HRISETLINENEWPAYROLLMASTERIDLIST = "hrisEtLineNEWPayrollmasterIDList";
    public static final String PROPERTY_HRISGEEMPLOYEELIST = "hrisGeEmployeeList";
    public static final String PROPERTY_HRISINSURANCELIST = "hrisInsuranceList";
    public static final String PROPERTY_HRISINSURANCEEMPLOYEEEMPLOYEELIST = "hrisInsuranceEmployeeEmployeeList";
    public static final String PROPERTY_HRISKPIMEASUREMENTLIST = "hrisKpiMeasurementList";
    public static final String PROPERTY_HRISPENGDATAKELLIST = "hrisPengdataKelList";
    public static final String PROPERTY_HRISPENGUNDURANDIRILIST = "hrisPengundurandiriList";
    public static final String PROPERTY_HRISRLINELIST = "hrisRLineList";
    public static final String PROPERTY_HRISRAPPELSALARYLIST = "hrisRappelSalaryList";
    public static final String PROPERTY_HRISREIMBURSMENTLIST = "hrisReimbursmentList";
    public static final String PROPERTY_HRISTPEMPLOYEELIST = "hrisTpEmployeeList";
    public static final String PROPERTY_HRISTRAININGCALENDARLIST = "hrisTrainingCalendarList";
    public static final String PROPERTY_BILLREGISTERLIST = "billRegisterList";
    public static final String PROPERTY_LOANLIST = "loanList";
    public static final String PROPERTY_LOANSTATEMENTLIST = "loanstatementList";
    public static final String PROPERTY_CBPLEAVELIST = "cBpLeaveList";
    public static final String PROPERTY_CBPLEAVEVLIST = "cBpLeaveVList";
    public static final String PROPERTY_LEAVELIST = "leaveList";
    public static final String PROPERTY_LEAVEPGSIDLIST = "leavePGSIDList";
    public static final String PROPERTY_MASSLEAVELLIST = "massLeaveLList";
    public static final String PROPERTY_TIDAKMASUKLIST = "tidakmasukList";
    public static final String PROPERTY_TIDAKMASUKREPLACEMENTEMPLOYEELIST = "tidakmasukReplacementEmployeeList";
    public static final String PROPERTY_OEZDAILYLNPRODSHRIMPLIST = "oezDailyLnProdShrimpList";
    public static final String PROPERTY_OEZIASSETLIST = "oezIAssetList";
    public static final String PROPERTY_OEZIBPARTNERBALANCELIST = "oezIBpartnerbalanceList";
    public static final String PROPERTY_OEZPRODOCTOPUSLIST = "oezProdOctopusList";
    public static final String PROPERTY_EMERGENCYCALLLIST = "emergencyCallList";
    public static final String PROPERTY_OVERTIMELIST = "overtimeList";
    public static final String PROPERTY_PPH1721LIST = "pph1721List";
    public static final String PROPERTY_PPHIPPH21LIST = "pphIPph21List";
    public static final String PROPERTY_PPHPPH21LIST = "pphPph21List";
    public static final String PROPERTY_PYRINCIDENTALDEDUCTIONLIST = "pyrIncidentalDeductionList";
    public static final String PROPERTY_PYRINCIDENTALEARNINGLIST = "pyrIncidentalEarningList";
    public static final String PROPERTY_PYRSALVARIABLELIST = "pyrSalVariableList";
    public static final String PROPERTY_PYRSALARYPAYMENTPAYROLLMASTERIDLIST = "pyrSalarypaymentPayrollmasterIDList";
    public static final String PROPERTY_PYRSPEMPLOYEELIST = "pyrSpEmployeeList";
    public static final String PROPERTY_AKUMULASIPOTCUTILIST = "akumulasiPotCutiList";
    public static final String PROPERTY_AKUMULASIPOTGAJILIST = "akumulasiPotGajiList";
    public static final String PROPERTY_CBPSHIFTLIST = "cBpShiftList";
    public static final String PROPERTY_DETAILPOTCUTILIST = "detailPotCutiList";
    public static final String PROPERTY_DETAILPOTGAJILIST = "detailPotGajiList";
    public static final String PROPERTY_IMANUALSCHEDULELIST = "iManualscheduleList";
    public static final String PROPERTY_MANUALSCHEDULELIST = "manualscheduleList";
    public static final String PROPERTY_TUKARSHIFTLIST = "tukarShiftList";
    public static final String PROPERTY_TUKARSHIFTPGSIDLIST = "tukarShiftPGSIDList";
    public static final String PROPERTY_DETAILPESANGONLIST = "detailPesangonList";
    public static final String PROPERTY_FORMPESANGONLIST = "formPesangonList";
    public static final String PROPERTY_TERMINATIONLIST = "terminationList";


    // Computed columns properties, these properties cannot be directly accessed, they need
    // to be read through _commputedColumns proxy. They cannot be directly used in HQL, OBQuery
    // nor OBCriteria. 
    public static final String COMPUTED_COLUMN_PYRDEDUCTIONCOMPONENT = "pYRDeductionComponent";
    public static final String COMPUTED_COLUMN_PYREARNINGCOMPONENT = "pYREarningComponent";
    public static final String COMPUTED_COLUMN_PYRSALCATEGORY = "pYRSalcategory";

    public BusinessPartner() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SUMMARYLEVEL, false);
        setDefaultValue(PROPERTY_ONETIMETRANSACTION, false);
        setDefaultValue(PROPERTY_POTENTIALCUSTOMER, false);
        setDefaultValue(PROPERTY_VENDOR, false);
        setDefaultValue(PROPERTY_CUSTOMER, true);
        setDefaultValue(PROPERTY_EMPLOYEE, false);
        setDefaultValue(PROPERTY_ISSALESREPRESENTATIVE, false);
        setDefaultValue(PROPERTY_TAXEXEMPT, false);
        setDefaultValue(PROPERTY_PRINTDISCOUNT, false);
        setDefaultValue(PROPERTY_INVOICETERMS, "I");
        setDefaultValue(PROPERTY_PRICESSHOWNINORDER, true);
        setDefaultValue(PROPERTY_INVOICEGROUPING, "000000000000000");
        setDefaultValue(PROPERTY_OPERATOR, false);
        setDefaultValue(PROPERTY_CONSUMPTIONDAYS, (long) 1000);
        setDefaultValue(PROPERTY_CUSTOMERBLOCKING, false);
        setDefaultValue(PROPERTY_VENDORBLOCKING, false);
        setDefaultValue(PROPERTY_PAYMENTIN, false);
        setDefaultValue(PROPERTY_PAYMENTOUT, true);
        setDefaultValue(PROPERTY_SALESINVOICE, true);
        setDefaultValue(PROPERTY_PURCHASEINVOICE, true);
        setDefaultValue(PROPERTY_SALESORDER, true);
        setDefaultValue(PROPERTY_PURCHASEORDER, true);
        setDefaultValue(PROPERTY_GOODSSHIPMENT, true);
        setDefaultValue(PROPERTY_GOODSRECEIPT, false);
        setDefaultValue(PROPERTY_CASHVAT, false);
        setDefaultValue(PROPERTY_SETNEWCURRENCY, false);
        setDefaultValue(PROPERTY_OEZACTIVATED, false);
        setDefaultValue(PROPERTY_OEZDOCSTATUS, "DR");
        setDefaultValue(PROPERTY_PYRISPAYROLLMASTER, false);
        setDefaultValue(PROPERTY_HRISSTAFF, false);
        setDefaultValue(PROPERTY_HRISEMPLOYEEWINDOW, true);
        setDefaultValue(PROPERTY_HRISISSITE, false);
        setDefaultValue(PROPERTY_OTRATEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_OEZISPKP, false);
        setDefaultValue(PROPERTY_PYRCOPYSALARYTEMPLATE, false);
        setDefaultValue(PROPERTY_HRISISTRAININGPROVIDER, false);
        setDefaultValue(PROPERTY_HRISISINSURANCEPROVIDER, false);
        setDefaultValue(PROPERTY_CAMISCUSTODIAN, false);
        setDefaultValue(PROPERTY_ADUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRXFULLACCTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ACTIVEPROPOSALVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APPROVEDVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERSALESREPRESENTATIVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNEREMPYRPAYROLLMASTERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNEREMHRISREPORTTOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERDISCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERLOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICETAXCASHVATVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONTEMPLATEBPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CUSTOMERACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTBANKSTATEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTINVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DATAIMPORTPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMAILINTERACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLOYEEACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMPLOYEESALARYCATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EXTERNALPOSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINBANKSTATEMENTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINDOUBTFULDEBTVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINACCTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTDETAILVBUSINESSPARTNERDIMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPDETAILVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETEMCAMCUSTODIANIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETEMCAMVENDORIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETEMCAMMANUFACTURERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETEMCAMPURCHASEORDERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTBUDGETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTWITHHOLDINGBENEFICIARYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEMPINFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEMPINFOREPORTTOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEMPINFOEMPYRPAYROLLMASTERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPLICENSELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPTRAININGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEDUCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICELINEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_INVOICEV2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPSALESFORECASTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGMAINTENANCEWORKERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKEFFORTEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTRESERVATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZIINOUTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERDROPSHIPPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGPRICELISTSCHEMELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRICINGVOLUMEDISCOUNTBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCUSTOMERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPERSONINCHARGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTPROPOSALLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTVENDORLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SHIPPINGSHIPPINGCOMPANYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ATTENDANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIMEANDEXPENSESHEETLINEVCHARGEDBUSINESSPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TRANSACTIONVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_VENDORACCOUNTSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSESHIPPERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSTRIPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CAMWOWORKERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISBENEFITSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPCOMPETENCYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPEXPERIENCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPPUNISHMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCBPREWARDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCASELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCHANGEFAMILYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISCONTACTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISDEDUCTIONTERMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISECLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONADMISSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONEXAMLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEDUCATIONPERMITLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETRANSFERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISEMPLOYEETREEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINEOLDPAYROLLMASTERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISETLINENEWPAYROLLMASTERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISGEEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISINSURANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISINSURANCEEMPLOYEEEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISKPIMEASUREMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISPENGDATAKELLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISPENGUNDURANDIRILIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISRAPPELSALARYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISREIMBURSMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTPEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_HRISTRAININGCALENDARLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BILLREGISTERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LOANLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LOANSTATEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CBPLEAVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CBPLEAVEVLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LEAVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LEAVEPGSIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MASSLEAVELLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIDAKMASUKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TIDAKMASUKREPLACEMENTEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZDAILYLNPRODSHRIMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZIASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZIBPARTNERBALANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OEZPRODOCTOPUSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMERGENCYCALLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OVERTIMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PPH1721LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PPHIPPH21LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PPHPPH21LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRINCIDENTALDEDUCTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRINCIDENTALEARNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSALVARIABLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSALARYPAYMENTPAYROLLMASTERIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PYRSPEMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AKUMULASIPOTCUTILIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_AKUMULASIPOTGAJILIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CBPSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DETAILPOTCUTILIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DETAILPOTGAJILIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_IMANUALSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUALSCHEDULELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TUKARSHIFTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TUKARSHIFTPGSIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DETAILPESANGONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FORMPESANGONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_TERMINATIONLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getName2() {
        return (String) get(PROPERTY_NAME2);
    }

    public void setName2(String name2) {
        set(PROPERTY_NAME2, name2);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isSummaryLevel() {
        return (Boolean) get(PROPERTY_SUMMARYLEVEL);
    }

    public void setSummaryLevel(Boolean summaryLevel) {
        set(PROPERTY_SUMMARYLEVEL, summaryLevel);
    }

    public Category getBusinessPartnerCategory() {
        return (Category) get(PROPERTY_BUSINESSPARTNERCATEGORY);
    }

    public void setBusinessPartnerCategory(Category businessPartnerCategory) {
        set(PROPERTY_BUSINESSPARTNERCATEGORY, businessPartnerCategory);
    }

    public Boolean isOneTimeTransaction() {
        return (Boolean) get(PROPERTY_ONETIMETRANSACTION);
    }

    public void setOneTimeTransaction(Boolean oneTimeTransaction) {
        set(PROPERTY_ONETIMETRANSACTION, oneTimeTransaction);
    }

    public Boolean isPotentialCustomer() {
        return (Boolean) get(PROPERTY_POTENTIALCUSTOMER);
    }

    public void setPotentialCustomer(Boolean potentialCustomer) {
        set(PROPERTY_POTENTIALCUSTOMER, potentialCustomer);
    }

    public Boolean isVendor() {
        return (Boolean) get(PROPERTY_VENDOR);
    }

    public void setVendor(Boolean vendor) {
        set(PROPERTY_VENDOR, vendor);
    }

    public Boolean isCustomer() {
        return (Boolean) get(PROPERTY_CUSTOMER);
    }

    public void setCustomer(Boolean customer) {
        set(PROPERTY_CUSTOMER, customer);
    }

    public Boolean isEmployee() {
        return (Boolean) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(Boolean employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public Boolean isSalesRepresentative() {
        return (Boolean) get(PROPERTY_ISSALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(Boolean isSalesRepresentative) {
        set(PROPERTY_ISSALESREPRESENTATIVE, isSalesRepresentative);
    }

    public String getReferenceNo() {
        return (String) get(PROPERTY_REFERENCENO);
    }

    public void setReferenceNo(String referenceNo) {
        set(PROPERTY_REFERENCENO, referenceNo);
    }

    public String getDUNS() {
        return (String) get(PROPERTY_DUNS);
    }

    public void setDUNS(String dUNS) {
        set(PROPERTY_DUNS, dUNS);
    }

    public String getURL() {
        return (String) get(PROPERTY_URL);
    }

    public void setURL(String uRL) {
        set(PROPERTY_URL, uRL);
    }

    public Language getLanguage() {
        return (Language) get(PROPERTY_LANGUAGE);
    }

    public void setLanguage(Language language) {
        set(PROPERTY_LANGUAGE, language);
    }

    public String getTaxID() {
        return (String) get(PROPERTY_TAXID);
    }

    public void setTaxID(String taxID) {
        set(PROPERTY_TAXID, taxID);
    }

    public Boolean isTaxExempt() {
        return (Boolean) get(PROPERTY_TAXEXEMPT);
    }

    public void setTaxExempt(Boolean taxExempt) {
        set(PROPERTY_TAXEXEMPT, taxExempt);
    }

    public InvoiceSchedule getInvoiceSchedule() {
        return (InvoiceSchedule) get(PROPERTY_INVOICESCHEDULE);
    }

    public void setInvoiceSchedule(InvoiceSchedule invoiceSchedule) {
        set(PROPERTY_INVOICESCHEDULE, invoiceSchedule);
    }

    public String getValuation() {
        return (String) get(PROPERTY_VALUATION);
    }

    public void setValuation(String valuation) {
        set(PROPERTY_VALUATION, valuation);
    }

    public BigDecimal getVolumeOfSales() {
        return (BigDecimal) get(PROPERTY_VOLUMEOFSALES);
    }

    public void setVolumeOfSales(BigDecimal volumeOfSales) {
        set(PROPERTY_VOLUMEOFSALES, volumeOfSales);
    }

    public Long getNoOfEmployees() {
        return (Long) get(PROPERTY_NOOFEMPLOYEES);
    }

    public void setNoOfEmployees(Long noOfEmployees) {
        set(PROPERTY_NOOFEMPLOYEES, noOfEmployees);
    }

    public String getNAICSSIC() {
        return (String) get(PROPERTY_NAICSSIC);
    }

    public void setNAICSSIC(String nAICSSIC) {
        set(PROPERTY_NAICSSIC, nAICSSIC);
    }

    public Date getDateOfFirstSale() {
        return (Date) get(PROPERTY_DATEOFFIRSTSALE);
    }

    public void setDateOfFirstSale(Date dateOfFirstSale) {
        set(PROPERTY_DATEOFFIRSTSALE, dateOfFirstSale);
    }

    public BigDecimal getAcquisitionCost() {
        return (BigDecimal) get(PROPERTY_ACQUISITIONCOST);
    }

    public void setAcquisitionCost(BigDecimal acquisitionCost) {
        set(PROPERTY_ACQUISITIONCOST, acquisitionCost);
    }

    public BigDecimal getExpectedLifetimeRevenue() {
        return (BigDecimal) get(PROPERTY_EXPECTEDLIFETIMEREVENUE);
    }

    public void setExpectedLifetimeRevenue(BigDecimal expectedLifetimeRevenue) {
        set(PROPERTY_EXPECTEDLIFETIMEREVENUE, expectedLifetimeRevenue);
    }

    public BigDecimal getLifetimeRevenueToDate() {
        return (BigDecimal) get(PROPERTY_LIFETIMEREVENUETODATE);
    }

    public void setLifetimeRevenueToDate(BigDecimal lifetimeRevenueToDate) {
        set(PROPERTY_LIFETIMEREVENUETODATE, lifetimeRevenueToDate);
    }

    public Long getShare() {
        return (Long) get(PROPERTY_SHARE);
    }

    public void setShare(Long share) {
        set(PROPERTY_SHARE, share);
    }

    public String getFormOfPayment() {
        return (String) get(PROPERTY_FORMOFPAYMENT);
    }

    public void setFormOfPayment(String formOfPayment) {
        set(PROPERTY_FORMOFPAYMENT, formOfPayment);
    }

    public BigDecimal getCreditLimit() {
        return (BigDecimal) get(PROPERTY_CREDITLIMIT);
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        set(PROPERTY_CREDITLIMIT, creditLimit);
    }

    public BigDecimal getCreditUsed() {
        return (BigDecimal) get(PROPERTY_CREDITUSED);
    }

    public void setCreditUsed(BigDecimal creditUsed) {
        set(PROPERTY_CREDITUSED, creditUsed);
    }

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public Boolean isPrintDiscount() {
        return (Boolean) get(PROPERTY_PRINTDISCOUNT);
    }

    public void setPrintDiscount(Boolean printDiscount) {
        set(PROPERTY_PRINTDISCOUNT, printDiscount);
    }

    public String getOrderDescription() {
        return (String) get(PROPERTY_ORDERDESCRIPTION);
    }

    public void setOrderDescription(String orderDescription) {
        set(PROPERTY_ORDERDESCRIPTION, orderDescription);
    }

    public String getOrderReference() {
        return (String) get(PROPERTY_ORDERREFERENCE);
    }

    public void setOrderReference(String orderReference) {
        set(PROPERTY_ORDERREFERENCE, orderReference);
    }

    public String getPOFormOfPayment() {
        return (String) get(PROPERTY_POFORMOFPAYMENT);
    }

    public void setPOFormOfPayment(String pOFormOfPayment) {
        set(PROPERTY_POFORMOFPAYMENT, pOFormOfPayment);
    }

    public PriceList getPurchasePricelist() {
        return (PriceList) get(PROPERTY_PURCHASEPRICELIST);
    }

    public void setPurchasePricelist(PriceList purchasePricelist) {
        set(PROPERTY_PURCHASEPRICELIST, purchasePricelist);
    }

    public PaymentTerm getPOPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_POPAYMENTTERMS);
    }

    public void setPOPaymentTerms(PaymentTerm pOPaymentTerms) {
        set(PROPERTY_POPAYMENTTERMS, pOPaymentTerms);
    }

    public Long getNumberOfCopies() {
        return (Long) get(PROPERTY_NUMBEROFCOPIES);
    }

    public void setNumberOfCopies(Long numberOfCopies) {
        set(PROPERTY_NUMBEROFCOPIES, numberOfCopies);
    }

    public Greeting getGreeting() {
        return (Greeting) get(PROPERTY_GREETING);
    }

    public void setGreeting(Greeting greeting) {
        set(PROPERTY_GREETING, greeting);
    }

    public String getInvoiceTerms() {
        return (String) get(PROPERTY_INVOICETERMS);
    }

    public void setInvoiceTerms(String invoiceTerms) {
        set(PROPERTY_INVOICETERMS, invoiceTerms);
    }

    public String getDeliveryTerms() {
        return (String) get(PROPERTY_DELIVERYTERMS);
    }

    public void setDeliveryTerms(String deliveryTerms) {
        set(PROPERTY_DELIVERYTERMS, deliveryTerms);
    }

    public String getDeliveryMethod() {
        return (String) get(PROPERTY_DELIVERYMETHOD);
    }

    public void setDeliveryMethod(String deliveryMethod) {
        set(PROPERTY_DELIVERYMETHOD, deliveryMethod);
    }

    public BusinessPartner getSalesRepresentative() {
        return (BusinessPartner) get(PROPERTY_SALESREPRESENTATIVE);
    }

    public void setSalesRepresentative(BusinessPartner salesRepresentative) {
        set(PROPERTY_SALESREPRESENTATIVE, salesRepresentative);
    }

    public String getPartnerParent() {
        return (String) get(PROPERTY_PARTNERPARENT);
    }

    public void setPartnerParent(String partnerParent) {
        set(PROPERTY_PARTNERPARENT, partnerParent);
    }

    public String getCreditStatus() {
        return (String) get(PROPERTY_CREDITSTATUS);
    }

    public void setCreditStatus(String creditStatus) {
        set(PROPERTY_CREDITSTATUS, creditStatus);
    }

    public Organization getForcedOrg() {
        return (Organization) get(PROPERTY_FORCEDORG);
    }

    public void setForcedOrg(Organization forcedOrg) {
        set(PROPERTY_FORCEDORG, forcedOrg);
    }

    public Boolean isPricesShownInOrder() {
        return (Boolean) get(PROPERTY_PRICESSHOWNINORDER);
    }

    public void setPricesShownInOrder(Boolean pricesShownInOrder) {
        set(PROPERTY_PRICESSHOWNINORDER, pricesShownInOrder);
    }

    public String getInvoiceGrouping() {
        return (String) get(PROPERTY_INVOICEGROUPING);
    }

    public void setInvoiceGrouping(String invoiceGrouping) {
        set(PROPERTY_INVOICEGROUPING, invoiceGrouping);
    }

    public Long getMaturityDate1() {
        return (Long) get(PROPERTY_MATURITYDATE1);
    }

    public void setMaturityDate1(Long maturityDate1) {
        set(PROPERTY_MATURITYDATE1, maturityDate1);
    }

    public Long getMaturityDate2() {
        return (Long) get(PROPERTY_MATURITYDATE2);
    }

    public void setMaturityDate2(Long maturityDate2) {
        set(PROPERTY_MATURITYDATE2, maturityDate2);
    }

    public Long getMaturityDate3() {
        return (Long) get(PROPERTY_MATURITYDATE3);
    }

    public void setMaturityDate3(Long maturityDate3) {
        set(PROPERTY_MATURITYDATE3, maturityDate3);
    }

    public Boolean isOperator() {
        return (Boolean) get(PROPERTY_OPERATOR);
    }

    public void setOperator(Boolean operator) {
        set(PROPERTY_OPERATOR, operator);
    }

    public String getUPCEAN() {
        return (String) get(PROPERTY_UPCEAN);
    }

    public void setUPCEAN(String uPCEAN) {
        set(PROPERTY_UPCEAN, uPCEAN);
    }

    public SalaryCategory getSalaryCategory() {
        return (SalaryCategory) get(PROPERTY_SALARYCATEGORY);
    }

    public void setSalaryCategory(SalaryCategory salaryCategory) {
        set(PROPERTY_SALARYCATEGORY, salaryCategory);
    }

    public String getInvoicePrintformat() {
        return (String) get(PROPERTY_INVOICEPRINTFORMAT);
    }

    public void setInvoicePrintformat(String invoicePrintformat) {
        set(PROPERTY_INVOICEPRINTFORMAT, invoicePrintformat);
    }

    public Long getConsumptionDays() {
        return (Long) get(PROPERTY_CONSUMPTIONDAYS);
    }

    public void setConsumptionDays(Long consumptionDays) {
        set(PROPERTY_CONSUMPTIONDAYS, consumptionDays);
    }

    public BankAccount getBankAccount() {
        return (BankAccount) get(PROPERTY_BANKACCOUNT);
    }

    public void setBankAccount(BankAccount bankAccount) {
        set(PROPERTY_BANKACCOUNT, bankAccount);
    }

    public TaxCategory getTaxCategory() {
        return (TaxCategory) get(PROPERTY_TAXCATEGORY);
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        set(PROPERTY_TAXCATEGORY, taxCategory);
    }

    public Long getPOMaturityDate1() {
        return (Long) get(PROPERTY_POMATURITYDATE1);
    }

    public void setPOMaturityDate1(Long pOMaturityDate1) {
        set(PROPERTY_POMATURITYDATE1, pOMaturityDate1);
    }

    public Long getPOMaturityDate2() {
        return (Long) get(PROPERTY_POMATURITYDATE2);
    }

    public void setPOMaturityDate2(Long pOMaturityDate2) {
        set(PROPERTY_POMATURITYDATE2, pOMaturityDate2);
    }

    public Long getPOMaturityDate3() {
        return (Long) get(PROPERTY_POMATURITYDATE3);
    }

    public void setPOMaturityDate3(Long pOMaturityDate3) {
        set(PROPERTY_POMATURITYDATE3, pOMaturityDate3);
    }

    public BankAccount getTransactionalBankAccount() {
        return (BankAccount) get(PROPERTY_TRANSACTIONALBANKACCOUNT);
    }

    public void setTransactionalBankAccount(BankAccount transactionalBankAccount) {
        set(PROPERTY_TRANSACTIONALBANKACCOUNT, transactionalBankAccount);
    }

    public TaxCategory getSOBPTaxCategory() {
        return (TaxCategory) get(PROPERTY_SOBPTAXCATEGORY);
    }

    public void setSOBPTaxCategory(TaxCategory sOBPTaxCategory) {
        set(PROPERTY_SOBPTAXCATEGORY, sOBPTaxCategory);
    }

    public String getFiscalcode() {
        return (String) get(PROPERTY_FISCALCODE);
    }

    public void setFiscalcode(String fiscalcode) {
        set(PROPERTY_FISCALCODE, fiscalcode);
    }

    public String getIsofiscalcode() {
        return (String) get(PROPERTY_ISOFISCALCODE);
    }

    public void setIsofiscalcode(String isofiscalcode) {
        set(PROPERTY_ISOFISCALCODE, isofiscalcode);
    }

    public Incoterms getIncotermsPO() {
        return (Incoterms) get(PROPERTY_INCOTERMSPO);
    }

    public void setIncotermsPO(Incoterms incotermsPO) {
        set(PROPERTY_INCOTERMSPO, incotermsPO);
    }

    public Incoterms getIncotermsSO() {
        return (Incoterms) get(PROPERTY_INCOTERMSSO);
    }

    public void setIncotermsSO(Incoterms incotermsSO) {
        set(PROPERTY_INCOTERMSSO, incotermsSO);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public FIN_PaymentMethod getPOPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_POPAYMENTMETHOD);
    }

    public void setPOPaymentMethod(FIN_PaymentMethod pOPaymentMethod) {
        set(PROPERTY_POPAYMENTMETHOD, pOPaymentMethod);
    }

    public FIN_FinancialAccount getAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_ACCOUNT);
    }

    public void setAccount(FIN_FinancialAccount account) {
        set(PROPERTY_ACCOUNT, account);
    }

    public FIN_FinancialAccount getPOFinancialAccount() {
        return (FIN_FinancialAccount) get(PROPERTY_POFINANCIALACCOUNT);
    }

    public void setPOFinancialAccount(FIN_FinancialAccount pOFinancialAccount) {
        set(PROPERTY_POFINANCIALACCOUNT, pOFinancialAccount);
    }

    public Boolean isCustomerBlocking() {
        return (Boolean) get(PROPERTY_CUSTOMERBLOCKING);
    }

    public void setCustomerBlocking(Boolean customerBlocking) {
        set(PROPERTY_CUSTOMERBLOCKING, customerBlocking);
    }

    public Boolean isVendorBlocking() {
        return (Boolean) get(PROPERTY_VENDORBLOCKING);
    }

    public void setVendorBlocking(Boolean vendorBlocking) {
        set(PROPERTY_VENDORBLOCKING, vendorBlocking);
    }

    public Boolean isPaymentIn() {
        return (Boolean) get(PROPERTY_PAYMENTIN);
    }

    public void setPaymentIn(Boolean paymentIn) {
        set(PROPERTY_PAYMENTIN, paymentIn);
    }

    public Boolean isPaymentOut() {
        return (Boolean) get(PROPERTY_PAYMENTOUT);
    }

    public void setPaymentOut(Boolean paymentOut) {
        set(PROPERTY_PAYMENTOUT, paymentOut);
    }

    public Boolean isSalesInvoice() {
        return (Boolean) get(PROPERTY_SALESINVOICE);
    }

    public void setSalesInvoice(Boolean salesInvoice) {
        set(PROPERTY_SALESINVOICE, salesInvoice);
    }

    public Boolean isPurchaseInvoice() {
        return (Boolean) get(PROPERTY_PURCHASEINVOICE);
    }

    public void setPurchaseInvoice(Boolean purchaseInvoice) {
        set(PROPERTY_PURCHASEINVOICE, purchaseInvoice);
    }

    public Boolean isSalesOrder() {
        return (Boolean) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Boolean salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public Boolean isPurchaseOrder() {
        return (Boolean) get(PROPERTY_PURCHASEORDER);
    }

    public void setPurchaseOrder(Boolean purchaseOrder) {
        set(PROPERTY_PURCHASEORDER, purchaseOrder);
    }

    public Boolean isGoodsShipment() {
        return (Boolean) get(PROPERTY_GOODSSHIPMENT);
    }

    public void setGoodsShipment(Boolean goodsShipment) {
        set(PROPERTY_GOODSSHIPMENT, goodsShipment);
    }

    public Boolean isGoodsReceipt() {
        return (Boolean) get(PROPERTY_GOODSRECEIPT);
    }

    public void setGoodsReceipt(Boolean goodsReceipt) {
        set(PROPERTY_GOODSRECEIPT, goodsReceipt);
    }

    public Boolean isCashVAT() {
        return (Boolean) get(PROPERTY_CASHVAT);
    }

    public void setCashVAT(Boolean cashVAT) {
        set(PROPERTY_CASHVAT, cashVAT);
    }

    public Boolean isSetNewCurrency() {
        return (Boolean) get(PROPERTY_SETNEWCURRENCY);
    }

    public void setSetNewCurrency(Boolean setNewCurrency) {
        set(PROPERTY_SETNEWCURRENCY, setNewCurrency);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getBirthPlace() {
        return (String) get(PROPERTY_BIRTHPLACE);
    }

    public void setBirthPlace(String birthPlace) {
        set(PROPERTY_BIRTHPLACE, birthPlace);
    }

    public CustomerType getOezCustomertype() {
        return (CustomerType) get(PROPERTY_OEZCUSTOMERTYPE);
    }

    public void setOezCustomertype(CustomerType oezCustomertype) {
        set(PROPERTY_OEZCUSTOMERTYPE, oezCustomertype);
    }

    public Date getBirthDay() {
        return (Date) get(PROPERTY_BIRTHDAY);
    }

    public void setBirthDay(Date birthDay) {
        set(PROPERTY_BIRTHDAY, birthDay);
    }

    public Boolean isOezActivated() {
        return (Boolean) get(PROPERTY_OEZACTIVATED);
    }

    public void setOezActivated(Boolean oezActivated) {
        set(PROPERTY_OEZACTIVATED, oezActivated);
    }

    public String getOezDocstatus() {
        return (String) get(PROPERTY_OEZDOCSTATUS);
    }

    public void setOezDocstatus(String oezDocstatus) {
        set(PROPERTY_OEZDOCSTATUS, oezDocstatus);
    }

    public hris_site getHrisSite() {
        return (hris_site) get(PROPERTY_HRISSITE);
    }

    public void setHrisSite(hris_site hrisSite) {
        set(PROPERTY_HRISSITE, hrisSite);
    }

    public Date getHrisBirthday() {
        return (Date) get(PROPERTY_HRISBIRTHDAY);
    }

    public void setHrisBirthday(Date hrisBirthday) {
        set(PROPERTY_HRISBIRTHDAY, hrisBirthday);
    }

    public String getHrisReligion() {
        return (String) get(PROPERTY_HRISRELIGION);
    }

    public void setHrisReligion(String hrisReligion) {
        set(PROPERTY_HRISRELIGION, hrisReligion);
    }

    public String getHRISMaritalStatus() {
        return (String) get(PROPERTY_HRISMARITALSTATUS);
    }

    public void setHRISMaritalStatus(String hRISMaritalStatus) {
        set(PROPERTY_HRISMARITALSTATUS, hRISMaritalStatus);
    }

    public String getHrisBloodgroup() {
        return (String) get(PROPERTY_HRISBLOODGROUP);
    }

    public void setHrisBloodgroup(String hrisBloodgroup) {
        set(PROPERTY_HRISBLOODGROUP, hrisBloodgroup);
    }

    public Date getHrisJoindate() {
        return (Date) get(PROPERTY_HRISJOINDATE);
    }

    public void setHrisJoindate(Date hrisJoindate) {
        set(PROPERTY_HRISJOINDATE, hrisJoindate);
    }

    public Date getHrisRetirementdate() {
        return (Date) get(PROPERTY_HRISRETIREMENTDATE);
    }

    public void setHrisRetirementdate(Date hrisRetirementdate) {
        set(PROPERTY_HRISRETIREMENTDATE, hrisRetirementdate);
    }

    public HRIS_C_Bp_Department getHrisCBpDepartment() {
        return (HRIS_C_Bp_Department) get(PROPERTY_HRISCBPDEPARTMENT);
    }

    public void setHrisCBpDepartment(HRIS_C_Bp_Department hrisCBpDepartment) {
        set(PROPERTY_HRISCBPDEPARTMENT, hrisCBpDepartment);
    }

    public BusinessPartner getPyrPayrollmaster() {
        return (BusinessPartner) get(PROPERTY_PYRPAYROLLMASTER);
    }

    public void setPyrPayrollmaster(BusinessPartner pyrPayrollmaster) {
        set(PROPERTY_PYRPAYROLLMASTER, pyrPayrollmaster);
    }

    public String getHRISEmployementType() {
        return (String) get(PROPERTY_HRISEMPLOYEMENTTYPE);
    }

    public void setHRISEmployementType(String hRISEmployementType) {
        set(PROPERTY_HRISEMPLOYEMENTTYPE, hRISEmployementType);
    }

    public Boolean isPyrIspayrollmaster() {
        return (Boolean) get(PROPERTY_PYRISPAYROLLMASTER);
    }

    public void setPyrIspayrollmaster(Boolean pyrIspayrollmaster) {
        set(PROPERTY_PYRISPAYROLLMASTER, pyrIspayrollmaster);
    }

    public String getHRISContractNo() {
        return (String) get(PROPERTY_HRISCONTRACTNO);
    }

    public void setHRISContractNo(String hRISContractNo) {
        set(PROPERTY_HRISCONTRACTNO, hRISContractNo);
    }

    public String getPphTaxmaritalstatus() {
        return (String) get(PROPERTY_PPHTAXMARITALSTATUS);
    }

    public void setPphTaxmaritalstatus(String pphTaxmaritalstatus) {
        set(PROPERTY_PPHTAXMARITALSTATUS, pphTaxmaritalstatus);
    }

    public Date getHRISValidTo() {
        return (Date) get(PROPERTY_HRISVALIDTO);
    }

    public void setHRISValidTo(Date hRISValidTo) {
        set(PROPERTY_HRISVALIDTO, hRISValidTo);
    }

    public tm_set_phk getTmSetPhk() {
        return (tm_set_phk) get(PROPERTY_TMSETPHK);
    }

    public void setTmSetPhk(tm_set_phk tmSetPhk) {
        set(PROPERTY_TMSETPHK, tmSetPhk);
    }

    public String getHRISLevel() {
        return (String) get(PROPERTY_HRISLEVEL);
    }

    public void setHRISLevel(String hRISLevel) {
        set(PROPERTY_HRISLEVEL, hRISLevel);
    }

    public String getHRISPosition() {
        return (String) get(PROPERTY_HRISPOSITION);
    }

    public void setHRISPosition(String hRISPosition) {
        set(PROPERTY_HRISPOSITION, hRISPosition);
    }

    public hris_jobtitle getHrisJobtitle() {
        return (hris_jobtitle) get(PROPERTY_HRISJOBTITLE);
    }

    public void setHrisJobtitle(hris_jobtitle hrisJobtitle) {
        set(PROPERTY_HRISJOBTITLE, hrisJobtitle);
    }

    public Boolean isHRISStaff() {
        return (Boolean) get(PROPERTY_HRISSTAFF);
    }

    public void setHRISStaff(Boolean hRISStaff) {
        set(PROPERTY_HRISSTAFF, hRISStaff);
    }

    public Boolean isHRISEmployeeWindow() {
        return (Boolean) get(PROPERTY_HRISEMPLOYEEWINDOW);
    }

    public void setHRISEmployeeWindow(Boolean hRISEmployeeWindow) {
        set(PROPERTY_HRISEMPLOYEEWINDOW, hRISEmployeeWindow);
    }

    public Date getHrisValidfrom() {
        return (Date) get(PROPERTY_HRISVALIDFROM);
    }

    public void setHrisValidfrom(Date hrisValidfrom) {
        set(PROPERTY_HRISVALIDFROM, hrisValidfrom);
    }

    public BusinessPartner getHrisReportTo() {
        return (BusinessPartner) get(PROPERTY_HRISREPORTTO);
    }

    public void setHrisReportTo(BusinessPartner hrisReportTo) {
        set(PROPERTY_HRISREPORTTO, hrisReportTo);
    }

    public String getHrisDocumentno() {
        return (String) get(PROPERTY_HRISDOCUMENTNO);
    }

    public void setHrisDocumentno(String hrisDocumentno) {
        set(PROPERTY_HRISDOCUMENTNO, hrisDocumentno);
    }

    public String getHrisDescription() {
        return (String) get(PROPERTY_HRISDESCRIPTION);
    }

    public void setHrisDescription(String hrisDescription) {
        set(PROPERTY_HRISDESCRIPTION, hrisDescription);
    }

    public String getHrisCBpKpi() {
        return (String) get(PROPERTY_HRISCBPKPI);
    }

    public void setHrisCBpKpi(String hrisCBpKpi) {
        set(PROPERTY_HRISCBPKPI, hrisCBpKpi);
    }

    public String getHrisComment() {
        return (String) get(PROPERTY_HRISCOMMENT);
    }

    public void setHrisComment(String hrisComment) {
        set(PROPERTY_HRISCOMMENT, hrisComment);
    }

    public Long getHrisTarget() {
        return (Long) get(PROPERTY_HRISTARGET);
    }

    public void setHrisTarget(Long hrisTarget) {
        set(PROPERTY_HRISTARGET, hrisTarget);
    }

    public Long getHrisMonth() {
        return (Long) get(PROPERTY_HRISMONTH);
    }

    public void setHrisMonth(Long hrisMonth) {
        set(PROPERTY_HRISMONTH, hrisMonth);
    }

    public Long getHrisYear() {
        return (Long) get(PROPERTY_HRISYEAR);
    }

    public void setHrisYear(Long hrisYear) {
        set(PROPERTY_HRISYEAR, hrisYear);
    }

    public Costcenter getHrisCCostcenter() {
        return (Costcenter) get(PROPERTY_HRISCCOSTCENTER);
    }

    public void setHrisCCostcenter(Costcenter hrisCCostcenter) {
        set(PROPERTY_HRISCCOSTCENTER, hrisCCostcenter);
    }

    public Boolean isHrisIssite() {
        return (Boolean) get(PROPERTY_HRISISSITE);
    }

    public void setHrisIssite(Boolean hrisIssite) {
        set(PROPERTY_HRISISSITE, hrisIssite);
    }

    public Image getHrisEmployeepic() {
        return (Image) get(PROPERTY_HRISEMPLOYEEPIC);
    }

    public void setHrisEmployeepic(Image hrisEmployeepic) {
        set(PROPERTY_HRISEMPLOYEEPIC, hrisEmployeepic);
    }

    public BigDecimal getPyrFixsalary() {
        return (BigDecimal) get(PROPERTY_PYRFIXSALARY);
    }

    public void setPyrFixsalary(BigDecimal pyrFixsalary) {
        set(PROPERTY_PYRFIXSALARY, pyrFixsalary);
    }

    public String getHrisFingerprint() {
        return (String) get(PROPERTY_HRISFINGERPRINT);
    }

    public void setHrisFingerprint(String hrisFingerprint) {
        set(PROPERTY_HRISFINGERPRINT, hrisFingerprint);
    }

    public BigDecimal getPyrActualfixsalary() {
        return (BigDecimal) get(PROPERTY_PYRACTUALFIXSALARY);
    }

    public void setPyrActualfixsalary(BigDecimal pyrActualfixsalary) {
        set(PROPERTY_PYRACTUALFIXSALARY, pyrActualfixsalary);
    }

    public String getHrisSex() {
        return (String) get(PROPERTY_HRISSEX);
    }

    public void setHrisSex(String hrisSex) {
        set(PROPERTY_HRISSEX, hrisSex);
    }

    public BigDecimal getOtRateamount() {
        return (BigDecimal) get(PROPERTY_OTRATEAMOUNT);
    }

    public void setOtRateamount(BigDecimal otRateamount) {
        set(PROPERTY_OTRATEAMOUNT, otRateamount);
    }

    public Long getHrisAge() {
        return (Long) get(PROPERTY_HRISAGE);
    }

    public void setHrisAge(Long hrisAge) {
        set(PROPERTY_HRISAGE, hrisAge);
    }

    public CostCenter getHrisCostcenter() {
        return (CostCenter) get(PROPERTY_HRISCOSTCENTER);
    }

    public void setHrisCostcenter(CostCenter hrisCostcenter) {
        set(PROPERTY_HRISCOSTCENTER, hrisCostcenter);
    }

    public Country getHrisNationality() {
        return (Country) get(PROPERTY_HRISNATIONALITY);
    }

    public void setHrisNationality(Country hrisNationality) {
        set(PROPERTY_HRISNATIONALITY, hrisNationality);
    }

    public String getHrisEmail() {
        return (String) get(PROPERTY_HRISEMAIL);
    }

    public void setHrisEmail(String hrisEmail) {
        set(PROPERTY_HRISEMAIL, hrisEmail);
    }

    public Boolean isOezIsPkp() {
        return (Boolean) get(PROPERTY_OEZISPKP);
    }

    public void setOezIsPkp(Boolean oezIsPkp) {
        set(PROPERTY_OEZISPKP, oezIsPkp);
    }

    public String getHrisBirthplace() {
        return (String) get(PROPERTY_HRISBIRTHPLACE);
    }

    public void setHrisBirthplace(String hrisBirthplace) {
        set(PROPERTY_HRISBIRTHPLACE, hrisBirthplace);
    }

    public String getOezAllowReturnVendor() {
        return (String) get(PROPERTY_OEZALLOWRETURNVENDOR);
    }

    public void setOezAllowReturnVendor(String oezAllowReturnVendor) {
        set(PROPERTY_OEZALLOWRETURNVENDOR, oezAllowReturnVendor);
    }

    public String getHrisEchelon() {
        return (String) get(PROPERTY_HRISECHELON);
    }

    public void setHrisEchelon(String hrisEchelon) {
        set(PROPERTY_HRISECHELON, hrisEchelon);
    }

    public Long getOezUnderDelivery() {
        return (Long) get(PROPERTY_OEZUNDERDELIVERY);
    }

    public void setOezUnderDelivery(Long oezUnderDelivery) {
        set(PROPERTY_OEZUNDERDELIVERY, oezUnderDelivery);
    }

    public String getHrisContracttype() {
        return (String) get(PROPERTY_HRISCONTRACTTYPE);
    }

    public void setHrisContracttype(String hrisContracttype) {
        set(PROPERTY_HRISCONTRACTTYPE, hrisContracttype);
    }

    public Long getOezOverDelivery() {
        return (Long) get(PROPERTY_OEZOVERDELIVERY);
    }

    public void setOezOverDelivery(Long oezOverDelivery) {
        set(PROPERTY_OEZOVERDELIVERY, oezOverDelivery);
    }

    public Boolean isPyrCopySalaryTemplate() {
        return (Boolean) get(PROPERTY_PYRCOPYSALARYTEMPLATE);
    }

    public void setPyrCopySalaryTemplate(Boolean pyrCopySalaryTemplate) {
        set(PROPERTY_PYRCOPYSALARYTEMPLATE, pyrCopySalaryTemplate);
    }

    public Boolean isHrisIstrainingprovider() {
        return (Boolean) get(PROPERTY_HRISISTRAININGPROVIDER);
    }

    public void setHrisIstrainingprovider(Boolean hrisIstrainingprovider) {
        set(PROPERTY_HRISISTRAININGPROVIDER, hrisIstrainingprovider);
    }

    public Long getOezBpartnersquence() {
        return (Long) get(PROPERTY_OEZBPARTNERSQUENCE);
    }

    public void setOezBpartnersquence(Long oezBpartnersquence) {
        set(PROPERTY_OEZBPARTNERSQUENCE, oezBpartnersquence);
    }

    public Boolean isHrisIsinsuranceprovider() {
        return (Boolean) get(PROPERTY_HRISISINSURANCEPROVIDER);
    }

    public void setHrisIsinsuranceprovider(Boolean hrisIsinsuranceprovider) {
        set(PROPERTY_HRISISINSURANCEPROVIDER, hrisIsinsuranceprovider);
    }

    public String getHrisIdcardNo() {
        return (String) get(PROPERTY_HRISIDCARDNO);
    }

    public void setHrisIdcardNo(String hrisIdcardNo) {
        set(PROPERTY_HRISIDCARDNO, hrisIdcardNo);
    }

    public String getHrisTitleName() {
        return (String) get(PROPERTY_HRISTITLENAME);
    }

    public void setHrisTitleName(String hrisTitleName) {
        set(PROPERTY_HRISTITLENAME, hrisTitleName);
    }

    public Boolean isCamIscustodian() {
        return (Boolean) get(PROPERTY_CAMISCUSTODIAN);
    }

    public void setCamIscustodian(Boolean camIscustodian) {
        set(PROPERTY_CAMISCUSTODIAN, camIscustodian);
    }

    public String getIdNppkp() {
        return (String) get(PROPERTY_IDNPPKP);
    }

    public void setIdNppkp(String idNppkp) {
        set(PROPERTY_IDNPPKP, idNppkp);
    }

    public Long getPYRDeductionComponent() {
        return (Long) get(COMPUTED_COLUMN_PYRDEDUCTIONCOMPONENT);
    }

    public void setPYRDeductionComponent(Long pYRDeductionComponent) {
        set(COMPUTED_COLUMN_PYRDEDUCTIONCOMPONENT, pYRDeductionComponent);
    }

    public Long getPYREarningComponent() {
        return (Long) get(COMPUTED_COLUMN_PYREARNINGCOMPONENT);
    }

    public void setPYREarningComponent(Long pYREarningComponent) {
        set(COMPUTED_COLUMN_PYREARNINGCOMPONENT, pYREarningComponent);
    }

    public String getPYRSalcategory() {
        return (String) get(COMPUTED_COLUMN_PYRSALCATEGORY);
    }

    public void setPYRSalcategory(String pYRSalcategory) {
        set(COMPUTED_COLUMN_PYRSALCATEGORY, pYRSalcategory);
    }

    public BusinessPartner_ComputedColumns get_computedColumns() {
        return (BusinessPartner_ComputedColumns) get(PROPERTY__COMPUTEDCOLUMNS);
    }

    public void set_computedColumns(BusinessPartner_ComputedColumns _computedColumns) {
        set(PROPERTY__COMPUTEDCOLUMNS, _computedColumns);
    }

    @SuppressWarnings("unchecked")
    public List<User> getADUserList() {
      return (List<User>) get(PROPERTY_ADUSERLIST);
    }

    public void setADUserList(List<User> aDUserList) {
        set(PROPERTY_ADUSERLIST, aDUserList);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVList() {
      return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST);
    }

    public void setAPRMFinAccTransactionAcctVList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLIST, aPRMFinAccTransactionAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_FinaccTransactionV> getAPRMFinaccTransactionVList() {
      return (List<APRM_FinaccTransactionV>) get(PROPERTY_APRMFINACCTRANSACTIONVLIST);
    }

    public void setAPRMFinaccTransactionVList(List<APRM_FinaccTransactionV> aPRMFinaccTransactionVList) {
        set(PROPERTY_APRMFINACCTRANSACTIONVLIST, aPRMFinaccTransactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_Finacc_Trx_Full_Acct_V> getAPRMFinaccTrxFullAcctVList() {
      return (List<APRM_Finacc_Trx_Full_Acct_V>) get(PROPERTY_APRMFINACCTRXFULLACCTVLIST);
    }

    public void setAPRMFinaccTrxFullAcctVList(List<APRM_Finacc_Trx_Full_Acct_V> aPRMFinaccTrxFullAcctVList) {
        set(PROPERTY_APRMFINACCTRXFULLACCTVLIST, aPRMFinaccTrxFullAcctVList);
    }

    @SuppressWarnings("unchecked")
    public List<APRM_PaymentProposalPickEdit> getAPRMPaymentProposalPickEditList() {
      return (List<APRM_PaymentProposalPickEdit>) get(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST);
    }

    public void setAPRMPaymentProposalPickEditList(List<APRM_PaymentProposalPickEdit> aPRMPaymentProposalPickEditList) {
        set(PROPERTY_APRMPAYMENTPROPOSALPICKEDITLIST, aPRMPaymentProposalPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<ActiveProposal> getActiveProposalVList() {
      return (List<ActiveProposal>) get(PROPERTY_ACTIVEPROPOSALVLIST);
    }

    public void setActiveProposalVList(List<ActiveProposal> activeProposalVList) {
        set(PROPERTY_ACTIVEPROPOSALVLIST, activeProposalVList);
    }

    @SuppressWarnings("unchecked")
    public List<AmortizationLineAccountingDimension> getAmortizationLineAccountingDimensionList() {
      return (List<AmortizationLineAccountingDimension>) get(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setAmortizationLineAccountingDimensionList(List<AmortizationLineAccountingDimension> amortizationLineAccountingDimensionList) {
        set(PROPERTY_AMORTIZATIONLINEACCOUNTINGDIMENSIONLIST, amortizationLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<ApprovedVendor> getApprovedVendorList() {
      return (List<ApprovedVendor>) get(PROPERTY_APPROVEDVENDORLIST);
    }

    public void setApprovedVendorList(List<ApprovedVendor> approvedVendorList) {
        set(PROPERTY_APPROVEDVENDORLIST, approvedVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<Bank> getBankList() {
      return (List<Bank>) get(PROPERTY_BANKLIST);
    }

    public void setBankList(List<Bank> bankList) {
        set(PROPERTY_BANKLIST, bankList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerSalesRepresentativeList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNERSALESREPRESENTATIVELIST);
    }

    public void setBusinessPartnerSalesRepresentativeList(List<BusinessPartner> businessPartnerSalesRepresentativeList) {
        set(PROPERTY_BUSINESSPARTNERSALESREPRESENTATIVELIST, businessPartnerSalesRepresentativeList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerEMPyrPayrollmasterIDList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNEREMPYRPAYROLLMASTERIDLIST);
    }

    public void setBusinessPartnerEMPyrPayrollmasterIDList(List<BusinessPartner> businessPartnerEMPyrPayrollmasterIDList) {
        set(PROPERTY_BUSINESSPARTNEREMPYRPAYROLLMASTERIDLIST, businessPartnerEMPyrPayrollmasterIDList);
    }

    @SuppressWarnings("unchecked")
    public List<BusinessPartner> getBusinessPartnerEMHrisReportToList() {
      return (List<BusinessPartner>) get(PROPERTY_BUSINESSPARTNEREMHRISREPORTTOLIST);
    }

    public void setBusinessPartnerEMHrisReportToList(List<BusinessPartner> businessPartnerEMHrisReportToList) {
        set(PROPERTY_BUSINESSPARTNEREMHRISREPORTTOLIST, businessPartnerEMHrisReportToList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.businesspartner.BankAccount> getBusinessPartnerBankAccountList() {
      return (List<org.openbravo.model.common.businesspartner.BankAccount>) get(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST);
    }

    public void setBusinessPartnerBankAccountList(List<org.openbravo.model.common.businesspartner.BankAccount> businessPartnerBankAccountList) {
        set(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, businessPartnerBankAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<Discount> getBusinessPartnerDiscountList() {
      return (List<Discount>) get(PROPERTY_BUSINESSPARTNERDISCOUNTLIST);
    }

    public void setBusinessPartnerDiscountList(List<Discount> businessPartnerDiscountList) {
        set(PROPERTY_BUSINESSPARTNERDISCOUNTLIST, businessPartnerDiscountList);
    }

    @SuppressWarnings("unchecked")
    public List<Location> getBusinessPartnerLocationList() {
      return (List<Location>) get(PROPERTY_BUSINESSPARTNERLOCATIONLIST);
    }

    public void setBusinessPartnerLocationList(List<Location> businessPartnerLocationList) {
        set(PROPERTY_BUSINESSPARTNERLOCATIONLIST, businessPartnerLocationList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductTemplate> getBusinessPartnerProductTemplateList() {
      return (List<ProductTemplate>) get(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST);
    }

    public void setBusinessPartnerProductTemplateList(List<ProductTemplate> businessPartnerProductTemplateList) {
        set(PROPERTY_BUSINESSPARTNERPRODUCTTEMPLATELIST, businessPartnerProductTemplateList);
    }

    @SuppressWarnings("unchecked")
    public List<Withholding> getBusinessPartnerWithholdingList() {
      return (List<Withholding>) get(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST);
    }

    public void setBusinessPartnerWithholdingList(List<Withholding> businessPartnerWithholdingList) {
        set(PROPERTY_BUSINESSPARTNERWITHHOLDINGLIST, businessPartnerWithholdingList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceTaxCashVAT_V> getInvoiceTaxCashVATVList() {
      return (List<InvoiceTaxCashVAT_V>) get(PROPERTY_INVOICETAXCASHVATVLIST);
    }

    public void setInvoiceTaxCashVATVList(List<InvoiceTaxCashVAT_V> invoiceTaxCashVATVList) {
        set(PROPERTY_INVOICETAXCASHVATVLIST, invoiceTaxCashVATVList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationTemplateBPartnerList() {
      return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONTEMPLATEBPARTNERLIST);
    }

    public void setClientInformationTemplateBPartnerList(List<ClientInformation> clientInformationTemplateBPartnerList) {
        set(PROPERTY_CLIENTINFORMATIONTEMPLATEBPARTNERLIST, clientInformationTemplateBPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<CustomerAccounts> getCustomerAccountsList() {
      return (List<CustomerAccounts>) get(PROPERTY_CUSTOMERACCOUNTSLIST);
    }

    public void setCustomerAccountsList(List<CustomerAccounts> customerAccountsList) {
        set(PROPERTY_CUSTOMERACCOUNTSLIST, customerAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<BankStatement> getDataImportBankStatementList() {
      return (List<BankStatement>) get(PROPERTY_DATAIMPORTBANKSTATEMENTLIST);
    }

    public void setDataImportBankStatementList(List<BankStatement> dataImportBankStatementList) {
        set(PROPERTY_DATAIMPORTBANKSTATEMENTLIST, dataImportBankStatementList);
    }

    @SuppressWarnings("unchecked")
    public List<BudgetLine> getDataImportBudgetLineList() {
      return (List<BudgetLine>) get(PROPERTY_DATAIMPORTBUDGETLINELIST);
    }

    public void setDataImportBudgetLineList(List<BudgetLine> dataImportBudgetLineList) {
        set(PROPERTY_DATAIMPORTBUDGETLINELIST, dataImportBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.dataimport.BusinessPartner> getDataImportBusinessPartnerList() {
      return (List<org.openbravo.model.dataimport.BusinessPartner>) get(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST);
    }

    public void setDataImportBusinessPartnerList(List<org.openbravo.model.dataimport.BusinessPartner> dataImportBusinessPartnerList) {
        set(PROPERTY_DATAIMPORTBUSINESSPARTNERLIST, dataImportBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournal> getDataImportGLJournalList() {
      return (List<GLJournal>) get(PROPERTY_DATAIMPORTGLJOURNALLIST);
    }

    public void setDataImportGLJournalList(List<GLJournal> dataImportGLJournalList) {
        set(PROPERTY_DATAIMPORTGLJOURNALLIST, dataImportGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getDataImportInvoiceList() {
      return (List<Invoice>) get(PROPERTY_DATAIMPORTINVOICELIST);
    }

    public void setDataImportInvoiceList(List<Invoice> dataImportInvoiceList) {
        set(PROPERTY_DATAIMPORTINVOICELIST, dataImportInvoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getDataImportOrderList() {
      return (List<Order>) get(PROPERTY_DATAIMPORTORDERLIST);
    }

    public void setDataImportOrderList(List<Order> dataImportOrderList) {
        set(PROPERTY_DATAIMPORTORDERLIST, dataImportOrderList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getDataImportProductList() {
      return (List<Product>) get(PROPERTY_DATAIMPORTPRODUCTLIST);
    }

    public void setDataImportProductList(List<Product> dataImportProductList) {
        set(PROPERTY_DATAIMPORTPRODUCTLIST, dataImportProductList);
    }

    @SuppressWarnings("unchecked")
    public List<EmailInteraction> getEmailInteractionList() {
      return (List<EmailInteraction>) get(PROPERTY_EMAILINTERACTIONLIST);
    }

    public void setEmailInteractionList(List<EmailInteraction> emailInteractionList) {
        set(PROPERTY_EMAILINTERACTIONLIST, emailInteractionList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeAccounts> getEmployeeAccountsList() {
      return (List<EmployeeAccounts>) get(PROPERTY_EMPLOYEEACCOUNTSLIST);
    }

    public void setEmployeeAccountsList(List<EmployeeAccounts> employeeAccountsList) {
        set(PROPERTY_EMPLOYEEACCOUNTSLIST, employeeAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeSalaryCategory> getEmployeeSalaryCategoryList() {
      return (List<EmployeeSalaryCategory>) get(PROPERTY_EMPLOYEESALARYCATEGORYLIST);
    }

    public void setEmployeeSalaryCategoryList(List<EmployeeSalaryCategory> employeeSalaryCategoryList) {
        set(PROPERTY_EMPLOYEESALARYCATEGORYLIST, employeeSalaryCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<ExternalPOS> getExternalPOSList() {
      return (List<ExternalPOS>) get(PROPERTY_EXTERNALPOSLIST);
    }

    public void setExternalPOSList(List<ExternalPOS> externalPOSList) {
        set(PROPERTY_EXTERNALPOSLIST, externalPOSList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_BankStatementLine> getFINBankStatementLineList() {
      return (List<FIN_BankStatementLine>) get(PROPERTY_FINBANKSTATEMENTLINELIST);
    }

    public void setFINBankStatementLineList(List<FIN_BankStatementLine> fINBankStatementLineList) {
        set(PROPERTY_FINBANKSTATEMENTLINELIST, fINBankStatementLineList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebt> getFINDoubtfulDebtList() {
      return (List<DoubtfulDebt>) get(PROPERTY_FINDOUBTFULDEBTLIST);
    }

    public void setFINDoubtfulDebtList(List<DoubtfulDebt> fINDoubtfulDebtList) {
        set(PROPERTY_FINDOUBTFULDEBTLIST, fINDoubtfulDebtList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtRun> getFINDoubtfulDebtRunList() {
      return (List<DoubtfulDebtRun>) get(PROPERTY_FINDOUBTFULDEBTRUNLIST);
    }

    public void setFINDoubtfulDebtRunList(List<DoubtfulDebtRun> fINDoubtfulDebtRunList) {
        set(PROPERTY_FINDOUBTFULDEBTRUNLIST, fINDoubtfulDebtRunList);
    }

    @SuppressWarnings("unchecked")
    public List<DoubtfulDebtV> getFINDoubtfulDebtVList() {
      return (List<DoubtfulDebtV>) get(PROPERTY_FINDOUBTFULDEBTVLIST);
    }

    public void setFINDoubtfulDebtVList(List<DoubtfulDebtV> fINDoubtfulDebtVList) {
        set(PROPERTY_FINDOUBTFULDEBTVLIST, fINDoubtfulDebtVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinaccTransaction> getFINFinaccTransactionList() {
      return (List<FIN_FinaccTransaction>) get(PROPERTY_FINFINACCTRANSACTIONLIST);
    }

    public void setFINFinaccTransactionList(List<FIN_FinaccTransaction> fINFinaccTransactionList) {
        set(PROPERTY_FINFINACCTRANSACTIONLIST, fINFinaccTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccount> getFINFinancialAccountList() {
      return (List<FIN_FinancialAccount>) get(PROPERTY_FINFINANCIALACCOUNTLIST);
    }

    public void setFINFinancialAccountList(List<FIN_FinancialAccount> fINFinancialAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTLIST, fINFinancialAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_Payment> getFINPaymentList() {
      return (List<FIN_Payment>) get(PROPERTY_FINPAYMENTLIST);
    }

    public void setFINPaymentList(List<FIN_Payment> fINPaymentList) {
        set(PROPERTY_FINPAYMENTLIST, fINPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentDetailV> getFINPaymentDetailVList() {
      return (List<FIN_PaymentDetailV>) get(PROPERTY_FINPAYMENTDETAILVLIST);
    }

    public void setFINPaymentDetailVList(List<FIN_PaymentDetailV> fINPaymentDetailVList) {
        set(PROPERTY_FINPAYMENTDETAILVLIST, fINPaymentDetailVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentDetailV> getFINPaymentDetailVBusinessPartnerdimList() {
      return (List<FIN_PaymentDetailV>) get(PROPERTY_FINPAYMENTDETAILVBUSINESSPARTNERDIMLIST);
    }

    public void setFINPaymentDetailVBusinessPartnerdimList(List<FIN_PaymentDetailV> fINPaymentDetailVBusinessPartnerdimList) {
        set(PROPERTY_FINPAYMENTDETAILVBUSINESSPARTNERDIMLIST, fINPaymentDetailVBusinessPartnerdimList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentPropDetailV> getFINPaymentPropDetailVList() {
      return (List<FIN_PaymentPropDetailV>) get(PROPERTY_FINPAYMENTPROPDETAILVLIST);
    }

    public void setFINPaymentPropDetailVList(List<FIN_PaymentPropDetailV> fINPaymentPropDetailVList) {
        set(PROPERTY_FINPAYMENTPROPDETAILVLIST, fINPaymentPropDetailVList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentProposal> getFINPaymentProposalList() {
      return (List<FIN_PaymentProposal>) get(PROPERTY_FINPAYMENTPROPOSALLIST);
    }

    public void setFINPaymentProposalList(List<FIN_PaymentProposal> fINPaymentProposalList) {
        set(PROPERTY_FINPAYMENTPROPOSALLIST, fINPaymentProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_PaymentScheduleDetail> getFINPaymentScheduleDetailList() {
      return (List<FIN_PaymentScheduleDetail>) get(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST);
    }

    public void setFINPaymentScheduleDetailList(List<FIN_PaymentScheduleDetail> fINPaymentScheduleDetailList) {
        set(PROPERTY_FINPAYMENTSCHEDULEDETAILLIST, fINPaymentScheduleDetailList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationList() {
      return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST);
    }

    public void setFinancialMgmtAccountingCombinationList(List<AccountingCombination> financialMgmtAccountingCombinationList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLIST, financialMgmtAccountingCombinationList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactList() {
      return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST);
    }

    public void setFinancialMgmtAccountingFactList(List<AccountingFact> financialMgmtAccountingFactList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLIST, financialMgmtAccountingFactList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaElement> getFinancialMgmtAcctSchemaElementList() {
      return (List<AcctSchemaElement>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaElementList(List<AcctSchemaElement> financialMgmtAcctSchemaElementList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, financialMgmtAcctSchemaElementList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
      return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetEmCamCustodianIdList() {
      return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETEMCAMCUSTODIANIDLIST);
    }

    public void setFinancialMgmtAssetEmCamCustodianIdList(List<Asset> financialMgmtAssetEmCamCustodianIdList) {
        set(PROPERTY_FINANCIALMGMTASSETEMCAMCUSTODIANIDLIST, financialMgmtAssetEmCamCustodianIdList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetEMCamVendorIDList() {
      return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETEMCAMVENDORIDLIST);
    }

    public void setFinancialMgmtAssetEMCamVendorIDList(List<Asset> financialMgmtAssetEMCamVendorIDList) {
        set(PROPERTY_FINANCIALMGMTASSETEMCAMVENDORIDLIST, financialMgmtAssetEMCamVendorIDList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetEMCamManufacturerIDList() {
      return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETEMCAMMANUFACTURERIDLIST);
    }

    public void setFinancialMgmtAssetEMCamManufacturerIDList(List<Asset> financialMgmtAssetEMCamManufacturerIDList) {
        set(PROPERTY_FINANCIALMGMTASSETEMCAMMANUFACTURERIDLIST, financialMgmtAssetEMCamManufacturerIDList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetEMCamPurchaseorderIDList() {
      return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETEMCAMPURCHASEORDERIDLIST);
    }

    public void setFinancialMgmtAssetEMCamPurchaseorderIDList(List<Asset> financialMgmtAssetEMCamPurchaseorderIDList) {
        set(PROPERTY_FINANCIALMGMTASSETEMCAMPURCHASEORDERIDLIST, financialMgmtAssetEMCamPurchaseorderIDList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.accounting.BudgetLine> getFinancialMgmtBudgetLineList() {
      return (List<org.openbravo.model.financialmgmt.accounting.BudgetLine>) get(PROPERTY_FINANCIALMGMTBUDGETLINELIST);
    }

    public void setFinancialMgmtBudgetLineList(List<org.openbravo.model.financialmgmt.accounting.BudgetLine> financialMgmtBudgetLineList) {
        set(PROPERTY_FINANCIALMGMTBUDGETLINELIST, financialMgmtBudgetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPayment> getFinancialMgmtDebtPaymentList() {
      return (List<DebtPayment>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST);
    }

    public void setFinancialMgmtDebtPaymentList(List<DebtPayment> financialMgmtDebtPaymentList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTLIST, financialMgmtDebtPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentCancelV> getFinancialMgmtDebtPaymentCancelVList() {
      return (List<DebtPaymentCancelV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST);
    }

    public void setFinancialMgmtDebtPaymentCancelVList(List<DebtPaymentCancelV> financialMgmtDebtPaymentCancelVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTCANCELVLIST, financialMgmtDebtPaymentCancelVList);
    }

    @SuppressWarnings("unchecked")
    public List<DebtPaymentGenerateV> getFinancialMgmtDebtPaymentGenerateVList() {
      return (List<DebtPaymentGenerateV>) get(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST);
    }

    public void setFinancialMgmtDebtPaymentGenerateVList(List<DebtPaymentGenerateV> financialMgmtDebtPaymentGenerateVList) {
        set(PROPERTY_FINANCIALMGMTDEBTPAYMENTGENERATEVLIST, financialMgmtDebtPaymentGenerateVList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.gl.GLJournal> getFinancialMgmtGLJournalList() {
      return (List<org.openbravo.model.financialmgmt.gl.GLJournal>) get(PROPERTY_FINANCIALMGMTGLJOURNALLIST);
    }

    public void setFinancialMgmtGLJournalList(List<org.openbravo.model.financialmgmt.gl.GLJournal> financialMgmtGLJournalList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLIST, financialMgmtGLJournalList);
    }

    @SuppressWarnings("unchecked")
    public List<GLJournalLine> getFinancialMgmtGLJournalLineList() {
      return (List<GLJournalLine>) get(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST);
    }

    public void setFinancialMgmtGLJournalLineList(List<GLJournalLine> financialMgmtGLJournalLineList) {
        set(PROPERTY_FINANCIALMGMTGLJOURNALLINELIST, financialMgmtGLJournalLineList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxPayment> getFinancialMgmtTaxPaymentList() {
      return (List<TaxPayment>) get(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST);
    }

    public void setFinancialMgmtTaxPaymentList(List<TaxPayment> financialMgmtTaxPaymentList) {
        set(PROPERTY_FINANCIALMGMTTAXPAYMENTLIST, financialMgmtTaxPaymentList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.financialmgmt.tax.Withholding> getFinancialMgmtWithholdingBeneficiaryList() {
      return (List<org.openbravo.model.financialmgmt.tax.Withholding>) get(PROPERTY_FINANCIALMGMTWITHHOLDINGBENEFICIARYLIST);
    }

    public void setFinancialMgmtWithholdingBeneficiaryList(List<org.openbravo.model.financialmgmt.tax.Withholding> financialMgmtWithholdingBeneficiaryList) {
        set(PROPERTY_FINANCIALMGMTWITHHOLDINGBENEFICIARYLIST, financialMgmtWithholdingBeneficiaryList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Empinfo> getHRISCBpEmpinfoList() {
      return (List<HRIS_C_Bp_Empinfo>) get(PROPERTY_HRISCBPEMPINFOLIST);
    }

    public void setHRISCBpEmpinfoList(List<HRIS_C_Bp_Empinfo> hRISCBpEmpinfoList) {
        set(PROPERTY_HRISCBPEMPINFOLIST, hRISCBpEmpinfoList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Empinfo> getHRISCBpEmpinfoReportToList() {
      return (List<HRIS_C_Bp_Empinfo>) get(PROPERTY_HRISCBPEMPINFOREPORTTOLIST);
    }

    public void setHRISCBpEmpinfoReportToList(List<HRIS_C_Bp_Empinfo> hRISCBpEmpinfoReportToList) {
        set(PROPERTY_HRISCBPEMPINFOREPORTTOLIST, hRISCBpEmpinfoReportToList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Empinfo> getHRISCBpEmpinfoEMPYRPayrollMasterList() {
      return (List<HRIS_C_Bp_Empinfo>) get(PROPERTY_HRISCBPEMPINFOEMPYRPAYROLLMASTERLIST);
    }

    public void setHRISCBpEmpinfoEMPYRPayrollMasterList(List<HRIS_C_Bp_Empinfo> hRISCBpEmpinfoEMPYRPayrollMasterList) {
        set(PROPERTY_HRISCBPEMPINFOEMPYRPAYROLLMASTERLIST, hRISCBpEmpinfoEMPYRPayrollMasterList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_License> getHRISCBpLicenseList() {
      return (List<HRIS_C_Bp_License>) get(PROPERTY_HRISCBPLICENSELIST);
    }

    public void setHRISCBpLicenseList(List<HRIS_C_Bp_License> hRISCBpLicenseList) {
        set(PROPERTY_HRISCBPLICENSELIST, hRISCBpLicenseList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_Training> getHRISCBpTrainingList() {
      return (List<HRIS_C_Bp_Training>) get(PROPERTY_HRISCBPTRAININGLIST);
    }

    public void setHRISCBpTrainingList(List<HRIS_C_Bp_Training> hRISCBpTrainingList) {
        set(PROPERTY_HRISCBPTRAININGLIST, hRISCBpTrainingList);
    }

    @SuppressWarnings("unchecked")
    public List<HRIS_C_Bp_education> getHRISCBpEducationList() {
      return (List<HRIS_C_Bp_education>) get(PROPERTY_HRISCBPEDUCATIONLIST);
    }

    public void setHRISCBpEducationList(List<HRIS_C_Bp_education> hRISCBpEducationList) {
        set(PROPERTY_HRISCBPEDUCATIONLIST, hRISCBpEducationList);
    }

    @SuppressWarnings("unchecked")
    public List<InOutLineAccountingDimension> getInOutLineAccountingDimensionList() {
      return (List<InOutLineAccountingDimension>) get(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInOutLineAccountingDimensionList(List<InOutLineAccountingDimension> inOutLineAccountingDimensionList) {
        set(PROPERTY_INOUTLINEACCOUNTINGDIMENSIONLIST, inOutLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.invoice.Invoice> getInvoiceList() {
      return (List<org.openbravo.model.common.invoice.Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<org.openbravo.model.common.invoice.Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
      return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineAccountingDimension> getInvoiceLineAccountingDimensionList() {
      return (List<InvoiceLineAccountingDimension>) get(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST);
    }

    public void setInvoiceLineAccountingDimensionList(List<InvoiceLineAccountingDimension> invoiceLineAccountingDimensionList) {
        set(PROPERTY_INVOICELINEACCOUNTINGDIMENSIONLIST, invoiceLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLineV2> getInvoiceLineV2List() {
      return (List<InvoiceLineV2>) get(PROPERTY_INVOICELINEV2LIST);
    }

    public void setInvoiceLineV2List(List<InvoiceLineV2> invoiceLineV2List) {
        set(PROPERTY_INVOICELINEV2LIST, invoiceLineV2List);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceV2> getInvoiceV2List() {
      return (List<InvoiceV2>) get(PROPERTY_INVOICEV2LIST);
    }

    public void setInvoiceV2List(List<InvoiceV2> invoiceV2List) {
        set(PROPERTY_INVOICEV2LIST, invoiceV2List);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRun> getMRPProductionRunList() {
      return (List<ProductionRun>) get(PROPERTY_MRPPRODUCTIONRUNLIST);
    }

    public void setMRPProductionRunList(List<ProductionRun> mRPProductionRunList) {
        set(PROPERTY_MRPPRODUCTIONRUNLIST, mRPProductionRunList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRun> getMRPPurchasingRunVendorList() {
      return (List<PurchasingRun>) get(PROPERTY_MRPPURCHASINGRUNVENDORLIST);
    }

    public void setMRPPurchasingRunVendorList(List<PurchasingRun> mRPPurchasingRunVendorList) {
        set(PROPERTY_MRPPURCHASINGRUNVENDORLIST, mRPPurchasingRunVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRun> getMRPPurchasingRunList() {
      return (List<PurchasingRun>) get(PROPERTY_MRPPURCHASINGRUNLIST);
    }

    public void setMRPPurchasingRunList(List<PurchasingRun> mRPPurchasingRunList) {
        set(PROPERTY_MRPPURCHASINGRUNLIST, mRPPurchasingRunList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRunLine> getMRPPurchasingRunLineList() {
      return (List<PurchasingRunLine>) get(PROPERTY_MRPPURCHASINGRUNLINELIST);
    }

    public void setMRPPurchasingRunLineList(List<PurchasingRunLine> mRPPurchasingRunLineList) {
        set(PROPERTY_MRPPURCHASINGRUNLINELIST, mRPPurchasingRunLineList);
    }

    @SuppressWarnings("unchecked")
    public List<SalesForecast> getMRPSalesForecastList() {
      return (List<SalesForecast>) get(PROPERTY_MRPSALESFORECASTLIST);
    }

    public void setMRPSalesForecastList(List<SalesForecast> mRPSalesForecastList) {
        set(PROPERTY_MRPSALESFORECASTLIST, mRPSalesForecastList);
    }

    @SuppressWarnings("unchecked")
    public List<Worker> getManufacturingMaintenanceWorkerList() {
      return (List<Worker>) get(PROPERTY_MANUFACTURINGMAINTENANCEWORKERLIST);
    }

    public void setManufacturingMaintenanceWorkerList(List<Worker> manufacturingMaintenanceWorkerList) {
        set(PROPERTY_MANUFACTURINGMAINTENANCEWORKERLIST, manufacturingMaintenanceWorkerList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunEmployee> getManufacturingProductionRunEmployeeList() {
      return (List<ProductionRunEmployee>) get(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST);
    }

    public void setManufacturingProductionRunEmployeeList(List<ProductionRunEmployee> manufacturingProductionRunEmployeeList) {
        set(PROPERTY_MANUFACTURINGPRODUCTIONRUNEMPLOYEELIST, manufacturingProductionRunEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkEffortEmployee> getManufacturingWorkEffortEmployeeList() {
      return (List<WorkEffortEmployee>) get(PROPERTY_MANUFACTURINGWORKEFFORTEMPLOYEELIST);
    }

    public void setManufacturingWorkEffortEmployeeList(List<WorkEffortEmployee> manufacturingWorkEffortEmployeeList) {
        set(PROPERTY_MANUFACTURINGWORKEFFORTEMPLOYEELIST, manufacturingWorkEffortEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<Reservation> getMaterialMgmtReservationList() {
      return (List<Reservation>) get(PROPERTY_MATERIALMGMTRESERVATIONLIST);
    }

    public void setMaterialMgmtReservationList(List<Reservation> materialMgmtReservationList) {
        set(PROPERTY_MATERIALMGMTRESERVATIONLIST, materialMgmtReservationList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOut> getMaterialMgmtShipmentInOutList() {
      return (List<ShipmentInOut>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST);
    }

    public void setMaterialMgmtShipmentInOutList(List<ShipmentInOut> materialMgmtShipmentInOutList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLIST, materialMgmtShipmentInOutList);
    }

    @SuppressWarnings("unchecked")
    public List<ShipmentInOutLine> getMaterialMgmtShipmentInOutLineList() {
      return (List<ShipmentInOutLine>) get(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST);
    }

    public void setMaterialMgmtShipmentInOutLineList(List<ShipmentInOutLine> materialMgmtShipmentInOutLineList) {
        set(PROPERTY_MATERIALMGMTSHIPMENTINOUTLINELIST, materialMgmtShipmentInOutLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OEZ_I_Inout> getOEZIInoutList() {
      return (List<OEZ_I_Inout>) get(PROPERTY_OEZIINOUTLIST);
    }

    public void setOEZIInoutList(List<OEZ_I_Inout> oEZIInoutList) {
        set(PROPERTY_OEZIINOUTLIST, oEZIInoutList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.order.Order> getOrderList() {
      return (List<org.openbravo.model.common.order.Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<org.openbravo.model.common.order.Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.order.Order> getOrderDropShipPartnerList() {
      return (List<org.openbravo.model.common.order.Order>) get(PROPERTY_ORDERDROPSHIPPARTNERLIST);
    }

    public void setOrderDropShipPartnerList(List<org.openbravo.model.common.order.Order> orderDropShipPartnerList) {
        set(PROPERTY_ORDERDROPSHIPPARTNERLIST, orderDropShipPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
      return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLineAccountingDimension> getOrderLineAccountingDimensionList() {
      return (List<OrderLineAccountingDimension>) get(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST);
    }

    public void setOrderLineAccountingDimensionList(List<OrderLineAccountingDimension> orderLineAccountingDimensionList) {
        set(PROPERTY_ORDERLINEACCOUNTINGDIMENSIONLIST, orderLineAccountingDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationInformation> getOrganizationInformationList() {
      return (List<OrganizationInformation>) get(PROPERTY_ORGANIZATIONINFORMATIONLIST);
    }

    public void setOrganizationInformationList(List<OrganizationInformation> organizationInformationList) {
        set(PROPERTY_ORGANIZATIONINFORMATIONLIST, organizationInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<PrereservationManualPickEdit> getPrereservationManualPickEditList() {
      return (List<PrereservationManualPickEdit>) get(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST);
    }

    public void setPrereservationManualPickEditList(List<PrereservationManualPickEdit> prereservationManualPickEditList) {
        set(PROPERTY_PRERESERVATIONMANUALPICKEDITLIST, prereservationManualPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.priceadjustment.BusinessPartner> getPricingAdjustmentBusinessPartnerList() {
      return (List<org.openbravo.model.pricing.priceadjustment.BusinessPartner>) get(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST);
    }

    public void setPricingAdjustmentBusinessPartnerList(List<org.openbravo.model.pricing.priceadjustment.BusinessPartner> pricingAdjustmentBusinessPartnerList) {
        set(PROPERTY_PRICINGADJUSTMENTBUSINESSPARTNERLIST, pricingAdjustmentBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<PriceListSchemeLine> getPricingPriceListSchemeLineList() {
      return (List<PriceListSchemeLine>) get(PROPERTY_PRICINGPRICELISTSCHEMELINELIST);
    }

    public void setPricingPriceListSchemeLineList(List<PriceListSchemeLine> pricingPriceListSchemeLineList) {
        set(PROPERTY_PRICINGPRICELISTSCHEMELINELIST, pricingPriceListSchemeLineList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.pricing.volumediscount.BusinessPartner> getPricingVolumeDiscountBusinessPartnerList() {
      return (List<org.openbravo.model.pricing.volumediscount.BusinessPartner>) get(PROPERTY_PRICINGVOLUMEDISCOUNTBUSINESSPARTNERLIST);
    }

    public void setPricingVolumeDiscountBusinessPartnerList(List<org.openbravo.model.pricing.volumediscount.BusinessPartner> pricingVolumeDiscountBusinessPartnerList) {
        set(PROPERTY_PRICINGVOLUMEDISCOUNTBUSINESSPARTNERLIST, pricingVolumeDiscountBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<Requisition> getProcurementRequisitionList() {
      return (List<Requisition>) get(PROPERTY_PROCUREMENTREQUISITIONLIST);
    }

    public void setProcurementRequisitionList(List<Requisition> procurementRequisitionList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLIST, procurementRequisitionList);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitionLine> getProcurementRequisitionLineList() {
      return (List<RequisitionLine>) get(PROPERTY_PROCUREMENTREQUISITIONLINELIST);
    }

    public void setProcurementRequisitionLineList(List<RequisitionLine> procurementRequisitionLineList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLINELIST, procurementRequisitionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.plm.Product> getProductList() {
      return (List<org.openbravo.model.common.plm.Product>) get(PROPERTY_PRODUCTLIST);
    }

    public void setProductList(List<org.openbravo.model.common.plm.Product> productList) {
        set(PROPERTY_PRODUCTLIST, productList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductCustomer> getProductCustomerList() {
      return (List<ProductCustomer>) get(PROPERTY_PRODUCTCUSTOMERLIST);
    }

    public void setProductCustomerList(List<ProductCustomer> productCustomerList) {
        set(PROPERTY_PRODUCTCUSTOMERLIST, productCustomerList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
      return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectPersonInChargeList() {
      return (List<Project>) get(PROPERTY_PROJECTPERSONINCHARGELIST);
    }

    public void setProjectPersonInChargeList(List<Project> projectPersonInChargeList) {
        set(PROPERTY_PROJECTPERSONINCHARGELIST, projectPersonInChargeList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectProposal> getProjectProposalList() {
      return (List<ProjectProposal>) get(PROPERTY_PROJECTPROPOSALLIST);
    }

    public void setProjectProposalList(List<ProjectProposal> projectProposalList) {
        set(PROPERTY_PROJECTPROPOSALLIST, projectProposalList);
    }

    @SuppressWarnings("unchecked")
    public List<ProjectVendor> getProjectVendorList() {
      return (List<ProjectVendor>) get(PROPERTY_PROJECTVENDORLIST);
    }

    public void setProjectVendorList(List<ProjectVendor> projectVendorList) {
        set(PROPERTY_PROJECTVENDORLIST, projectVendorList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialReceiptPickEdit> getReturnMaterialReceiptPickEditList() {
      return (List<ReturnMaterialReceiptPickEdit>) get(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST);
    }

    public void setReturnMaterialReceiptPickEditList(List<ReturnMaterialReceiptPickEdit> returnMaterialReceiptPickEditList) {
        set(PROPERTY_RETURNMATERIALRECEIPTPICKEDITLIST, returnMaterialReceiptPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<ReturnMaterialShipmentPickEdit> getReturnMaterialShipmentPickEditList() {
      return (List<ReturnMaterialShipmentPickEdit>) get(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST);
    }

    public void setReturnMaterialShipmentPickEditList(List<ReturnMaterialShipmentPickEdit> returnMaterialShipmentPickEditList) {
        set(PROPERTY_RETURNMATERIALSHIPMENTPICKEDITLIST, returnMaterialShipmentPickEditList);
    }

    @SuppressWarnings("unchecked")
    public List<Commission> getSalesCommissionList() {
      return (List<Commission>) get(PROPERTY_SALESCOMMISSIONLIST);
    }

    public void setSalesCommissionList(List<Commission> salesCommissionList) {
        set(PROPERTY_SALESCOMMISSIONLIST, salesCommissionList);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionLine> getSalesCommissionLineList() {
      return (List<CommissionLine>) get(PROPERTY_SALESCOMMISSIONLINELIST);
    }

    public void setSalesCommissionLineList(List<CommissionLine> salesCommissionLineList) {
        set(PROPERTY_SALESCOMMISSIONLINELIST, salesCommissionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<ShippingCompany> getShippingShippingCompanyList() {
      return (List<ShippingCompany>) get(PROPERTY_SHIPPINGSHIPPINGCOMPANYLIST);
    }

    public void setShippingShippingCompanyList(List<ShippingCompany> shippingShippingCompanyList) {
        set(PROPERTY_SHIPPINGSHIPPINGCOMPANYLIST, shippingShippingCompanyList);
    }

    @SuppressWarnings("unchecked")
    public List<TAAttendance> getAttendanceList() {
      return (List<TAAttendance>) get(PROPERTY_ATTENDANCELIST);
    }

    public void setAttendanceList(List<TAAttendance> attendanceList) {
        set(PROPERTY_ATTENDANCELIST, attendanceList);
    }

    @SuppressWarnings("unchecked")
    public List<Sheet> getTimeAndExpenseSheetList() {
      return (List<Sheet>) get(PROPERTY_TIMEANDEXPENSESHEETLIST);
    }

    public void setTimeAndExpenseSheetList(List<Sheet> timeAndExpenseSheetList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLIST, timeAndExpenseSheetList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLine> getTimeAndExpenseSheetLineList() {
      return (List<SheetLine>) get(PROPERTY_TIMEANDEXPENSESHEETLINELIST);
    }

    public void setTimeAndExpenseSheetLineList(List<SheetLine> timeAndExpenseSheetLineList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINELIST, timeAndExpenseSheetLineList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLineV> getTimeAndExpenseSheetLineVList() {
      return (List<SheetLineV>) get(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST);
    }

    public void setTimeAndExpenseSheetLineVList(List<SheetLineV> timeAndExpenseSheetLineVList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINEVLIST, timeAndExpenseSheetLineVList);
    }

    @SuppressWarnings("unchecked")
    public List<SheetLineV> getTimeAndExpenseSheetLineVChargedBusinessPartnerList() {
      return (List<SheetLineV>) get(PROPERTY_TIMEANDEXPENSESHEETLINEVCHARGEDBUSINESSPARTNERLIST);
    }

    public void setTimeAndExpenseSheetLineVChargedBusinessPartnerList(List<SheetLineV> timeAndExpenseSheetLineVChargedBusinessPartnerList) {
        set(PROPERTY_TIMEANDEXPENSESHEETLINEVCHARGEDBUSINESSPARTNERLIST, timeAndExpenseSheetLineVChargedBusinessPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransactionV> getTransactionVList() {
      return (List<MaterialTransactionV>) get(PROPERTY_TRANSACTIONVLIST);
    }

    public void setTransactionVList(List<MaterialTransactionV> transactionVList) {
        set(PROPERTY_TRANSACTIONVLIST, transactionVList);
    }

    @SuppressWarnings("unchecked")
    public List<VendorAccounts> getVendorAccountsList() {
      return (List<VendorAccounts>) get(PROPERTY_VENDORACCOUNTSLIST);
    }

    public void setVendorAccountsList(List<VendorAccounts> vendorAccountsList) {
        set(PROPERTY_VENDORACCOUNTSLIST, vendorAccountsList);
    }

    @SuppressWarnings("unchecked")
    public List<WarehouseShipper> getWarehouseShipperList() {
      return (List<WarehouseShipper>) get(PROPERTY_WAREHOUSESHIPPERLIST);
    }

    public void setWarehouseShipperList(List<WarehouseShipper> warehouseShipperList) {
        set(PROPERTY_WAREHOUSESHIPPERLIST, warehouseShipperList);
    }

    @SuppressWarnings("unchecked")
    public List<bt_businesstrip> getBusinesstripList() {
      return (List<bt_businesstrip>) get(PROPERTY_BUSINESSTRIPLIST);
    }

    public void setBusinesstripList(List<bt_businesstrip> businesstripList) {
        set(PROPERTY_BUSINESSTRIPLIST, businesstripList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkOrderWorker> getCamWoWorkerList() {
      return (List<WorkOrderWorker>) get(PROPERTY_CAMWOWORKERLIST);
    }

    public void setCamWoWorkerList(List<WorkOrderWorker> camWoWorkerList) {
        set(PROPERTY_CAMWOWORKERLIST, camWoWorkerList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_benefits> getHrisBenefitsList() {
      return (List<hris_benefits>) get(PROPERTY_HRISBENEFITSLIST);
    }

    public void setHrisBenefitsList(List<hris_benefits> hrisBenefitsList) {
        set(PROPERTY_HRISBENEFITSLIST, hrisBenefitsList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_c_bp_competency> getHrisCBpCompetencyList() {
      return (List<hris_c_bp_competency>) get(PROPERTY_HRISCBPCOMPETENCYLIST);
    }

    public void setHrisCBpCompetencyList(List<hris_c_bp_competency> hrisCBpCompetencyList) {
        set(PROPERTY_HRISCBPCOMPETENCYLIST, hrisCBpCompetencyList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_c_bp_experience> getHrisCBpExperienceList() {
      return (List<hris_c_bp_experience>) get(PROPERTY_HRISCBPEXPERIENCELIST);
    }

    public void setHrisCBpExperienceList(List<hris_c_bp_experience> hrisCBpExperienceList) {
        set(PROPERTY_HRISCBPEXPERIENCELIST, hrisCBpExperienceList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_c_bp_punishment> getHrisCBpPunishmentList() {
      return (List<hris_c_bp_punishment>) get(PROPERTY_HRISCBPPUNISHMENTLIST);
    }

    public void setHrisCBpPunishmentList(List<hris_c_bp_punishment> hrisCBpPunishmentList) {
        set(PROPERTY_HRISCBPPUNISHMENTLIST, hrisCBpPunishmentList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_c_bp_reward> getHrisCBpRewardList() {
      return (List<hris_c_bp_reward>) get(PROPERTY_HRISCBPREWARDLIST);
    }

    public void setHrisCBpRewardList(List<hris_c_bp_reward> hrisCBpRewardList) {
        set(PROPERTY_HRISCBPREWARDLIST, hrisCBpRewardList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_case> getHrisCaseList() {
      return (List<hris_case>) get(PROPERTY_HRISCASELIST);
    }

    public void setHrisCaseList(List<hris_case> hrisCaseList) {
        set(PROPERTY_HRISCASELIST, hrisCaseList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_change_family> getHrisChangeFamilyList() {
      return (List<hris_change_family>) get(PROPERTY_HRISCHANGEFAMILYLIST);
    }

    public void setHrisChangeFamilyList(List<hris_change_family> hrisChangeFamilyList) {
        set(PROPERTY_HRISCHANGEFAMILYLIST, hrisChangeFamilyList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_contact> getHrisContactList() {
      return (List<hris_contact>) get(PROPERTY_HRISCONTACTLIST);
    }

    public void setHrisContactList(List<hris_contact> hrisContactList) {
        set(PROPERTY_HRISCONTACTLIST, hrisContactList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_deductionterm> getHrisDeductionTermList() {
      return (List<hris_deductionterm>) get(PROPERTY_HRISDEDUCTIONTERMLIST);
    }

    public void setHrisDeductionTermList(List<hris_deductionterm> hrisDeductionTermList) {
        set(PROPERTY_HRISDEDUCTIONTERMLIST, hrisDeductionTermList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ec_lines> getHrisEcLinesList() {
      return (List<hris_ec_lines>) get(PROPERTY_HRISECLINESLIST);
    }

    public void setHrisEcLinesList(List<hris_ec_lines> hrisEcLinesList) {
        set(PROPERTY_HRISECLINESLIST, hrisEcLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_admission> getHrisEducationAdmissionList() {
      return (List<hris_education_admission>) get(PROPERTY_HRISEDUCATIONADMISSIONLIST);
    }

    public void setHrisEducationAdmissionList(List<hris_education_admission> hrisEducationAdmissionList) {
        set(PROPERTY_HRISEDUCATIONADMISSIONLIST, hrisEducationAdmissionList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_exam> getHrisEducationExamList() {
      return (List<hris_education_exam>) get(PROPERTY_HRISEDUCATIONEXAMLIST);
    }

    public void setHrisEducationExamList(List<hris_education_exam> hrisEducationExamList) {
        set(PROPERTY_HRISEDUCATIONEXAMLIST, hrisEducationExamList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_education_permit> getHrisEducationPermitList() {
      return (List<hris_education_permit>) get(PROPERTY_HRISEDUCATIONPERMITLIST);
    }

    public void setHrisEducationPermitList(List<hris_education_permit> hrisEducationPermitList) {
        set(PROPERTY_HRISEDUCATIONPERMITLIST, hrisEducationPermitList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_transfer> getHrisEmployeeTransferList() {
      return (List<hris_employee_transfer>) get(PROPERTY_HRISEMPLOYEETRANSFERLIST);
    }

    public void setHrisEmployeeTransferList(List<hris_employee_transfer> hrisEmployeeTransferList) {
        set(PROPERTY_HRISEMPLOYEETRANSFERLIST, hrisEmployeeTransferList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_employee_tree_v> getHrisEmployeeTreeVList() {
      return (List<hris_employee_tree_v>) get(PROPERTY_HRISEMPLOYEETREEVLIST);
    }

    public void setHrisEmployeeTreeVList(List<hris_employee_tree_v> hrisEmployeeTreeVList) {
        set(PROPERTY_HRISEMPLOYEETREEVLIST, hrisEmployeeTreeVList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINELIST);
    }

    public void setHrisEtLineList(List<EmployeeTransferLine> hrisEtLineList) {
        set(PROPERTY_HRISETLINELIST, hrisEtLineList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineOLDPayrollmasterIDList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINEOLDPAYROLLMASTERIDLIST);
    }

    public void setHrisEtLineOLDPayrollmasterIDList(List<EmployeeTransferLine> hrisEtLineOLDPayrollmasterIDList) {
        set(PROPERTY_HRISETLINEOLDPAYROLLMASTERIDLIST, hrisEtLineOLDPayrollmasterIDList);
    }

    @SuppressWarnings("unchecked")
    public List<EmployeeTransferLine> getHrisEtLineNEWPayrollmasterIDList() {
      return (List<EmployeeTransferLine>) get(PROPERTY_HRISETLINENEWPAYROLLMASTERIDLIST);
    }

    public void setHrisEtLineNEWPayrollmasterIDList(List<EmployeeTransferLine> hrisEtLineNEWPayrollmasterIDList) {
        set(PROPERTY_HRISETLINENEWPAYROLLMASTERIDLIST, hrisEtLineNEWPayrollmasterIDList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_ge_employee> getHrisGeEmployeeList() {
      return (List<hris_ge_employee>) get(PROPERTY_HRISGEEMPLOYEELIST);
    }

    public void setHrisGeEmployeeList(List<hris_ge_employee> hrisGeEmployeeList) {
        set(PROPERTY_HRISGEEMPLOYEELIST, hrisGeEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_insurance> getHrisInsuranceList() {
      return (List<hris_insurance>) get(PROPERTY_HRISINSURANCELIST);
    }

    public void setHrisInsuranceList(List<hris_insurance> hrisInsuranceList) {
        set(PROPERTY_HRISINSURANCELIST, hrisInsuranceList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_insurance_employee> getHrisInsuranceEmployeeEmployeeList() {
      return (List<hris_insurance_employee>) get(PROPERTY_HRISINSURANCEEMPLOYEEEMPLOYEELIST);
    }

    public void setHrisInsuranceEmployeeEmployeeList(List<hris_insurance_employee> hrisInsuranceEmployeeEmployeeList) {
        set(PROPERTY_HRISINSURANCEEMPLOYEEEMPLOYEELIST, hrisInsuranceEmployeeEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<KPIMeasurement> getHrisKpiMeasurementList() {
      return (List<KPIMeasurement>) get(PROPERTY_HRISKPIMEASUREMENTLIST);
    }

    public void setHrisKpiMeasurementList(List<KPIMeasurement> hrisKpiMeasurementList) {
        set(PROPERTY_HRISKPIMEASUREMENTLIST, hrisKpiMeasurementList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_pengdata_kel> getHrisPengdataKelList() {
      return (List<hris_pengdata_kel>) get(PROPERTY_HRISPENGDATAKELLIST);
    }

    public void setHrisPengdataKelList(List<hris_pengdata_kel> hrisPengdataKelList) {
        set(PROPERTY_HRISPENGDATAKELLIST, hrisPengdataKelList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_pengundurandiri> getHrisPengundurandiriList() {
      return (List<hris_pengundurandiri>) get(PROPERTY_HRISPENGUNDURANDIRILIST);
    }

    public void setHrisPengundurandiriList(List<hris_pengundurandiri> hrisPengundurandiriList) {
        set(PROPERTY_HRISPENGUNDURANDIRILIST, hrisPengundurandiriList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_r_line> getHrisRLineList() {
      return (List<hris_r_line>) get(PROPERTY_HRISRLINELIST);
    }

    public void setHrisRLineList(List<hris_r_line> hrisRLineList) {
        set(PROPERTY_HRISRLINELIST, hrisRLineList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_rappel_salary> getHrisRappelSalaryList() {
      return (List<hris_rappel_salary>) get(PROPERTY_HRISRAPPELSALARYLIST);
    }

    public void setHrisRappelSalaryList(List<hris_rappel_salary> hrisRappelSalaryList) {
        set(PROPERTY_HRISRAPPELSALARYLIST, hrisRappelSalaryList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_reimbursment> getHrisReimbursmentList() {
      return (List<hris_reimbursment>) get(PROPERTY_HRISREIMBURSMENTLIST);
    }

    public void setHrisReimbursmentList(List<hris_reimbursment> hrisReimbursmentList) {
        set(PROPERTY_HRISREIMBURSMENTLIST, hrisReimbursmentList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_tp_employee> getHrisTpEmployeeList() {
      return (List<hris_tp_employee>) get(PROPERTY_HRISTPEMPLOYEELIST);
    }

    public void setHrisTpEmployeeList(List<hris_tp_employee> hrisTpEmployeeList) {
        set(PROPERTY_HRISTPEMPLOYEELIST, hrisTpEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<hris_training_calendar> getHrisTrainingCalendarList() {
      return (List<hris_training_calendar>) get(PROPERTY_HRISTRAININGCALENDARLIST);
    }

    public void setHrisTrainingCalendarList(List<hris_training_calendar> hrisTrainingCalendarList) {
        set(PROPERTY_HRISTRAININGCALENDARLIST, hrisTrainingCalendarList);
    }

    @SuppressWarnings("unchecked")
    public List<ln_bill_register> getBillRegisterList() {
      return (List<ln_bill_register>) get(PROPERTY_BILLREGISTERLIST);
    }

    public void setBillRegisterList(List<ln_bill_register> billRegisterList) {
        set(PROPERTY_BILLREGISTERLIST, billRegisterList);
    }

    @SuppressWarnings("unchecked")
    public List<ln_loan> getLoanList() {
      return (List<ln_loan>) get(PROPERTY_LOANLIST);
    }

    public void setLoanList(List<ln_loan> loanList) {
        set(PROPERTY_LOANLIST, loanList);
    }

    @SuppressWarnings("unchecked")
    public List<ln_loanstatement> getLoanstatementList() {
      return (List<ln_loanstatement>) get(PROPERTY_LOANSTATEMENTLIST);
    }

    public void setLoanstatementList(List<ln_loanstatement> loanstatementList) {
        set(PROPERTY_LOANSTATEMENTLIST, loanstatementList);
    }

    @SuppressWarnings("unchecked")
    public List<lv_c_bp_leave> getCBpLeaveList() {
      return (List<lv_c_bp_leave>) get(PROPERTY_CBPLEAVELIST);
    }

    public void setCBpLeaveList(List<lv_c_bp_leave> cBpLeaveList) {
        set(PROPERTY_CBPLEAVELIST, cBpLeaveList);
    }

    @SuppressWarnings("unchecked")
    public List<lv_c_bp_leave_v> getCBpLeaveVList() {
      return (List<lv_c_bp_leave_v>) get(PROPERTY_CBPLEAVEVLIST);
    }

    public void setCBpLeaveVList(List<lv_c_bp_leave_v> cBpLeaveVList) {
        set(PROPERTY_CBPLEAVEVLIST, cBpLeaveVList);
    }

    @SuppressWarnings("unchecked")
    public List<lv_leave> getLeaveList() {
      return (List<lv_leave>) get(PROPERTY_LEAVELIST);
    }

    public void setLeaveList(List<lv_leave> leaveList) {
        set(PROPERTY_LEAVELIST, leaveList);
    }

    @SuppressWarnings("unchecked")
    public List<lv_leave> getLeavePGSIDList() {
      return (List<lv_leave>) get(PROPERTY_LEAVEPGSIDLIST);
    }

    public void setLeavePGSIDList(List<lv_leave> leavePGSIDList) {
        set(PROPERTY_LEAVEPGSIDLIST, leavePGSIDList);
    }

    @SuppressWarnings("unchecked")
    public List<lv_mass_leave_l> getMassLeaveLList() {
      return (List<lv_mass_leave_l>) get(PROPERTY_MASSLEAVELLIST);
    }

    public void setMassLeaveLList(List<lv_mass_leave_l> massLeaveLList) {
        set(PROPERTY_MASSLEAVELLIST, massLeaveLList);
    }

    @SuppressWarnings("unchecked")
    public List<lv_tidakmasuk> getTidakmasukList() {
      return (List<lv_tidakmasuk>) get(PROPERTY_TIDAKMASUKLIST);
    }

    public void setTidakmasukList(List<lv_tidakmasuk> tidakmasukList) {
        set(PROPERTY_TIDAKMASUKLIST, tidakmasukList);
    }

    @SuppressWarnings("unchecked")
    public List<lv_tidakmasuk> getTidakmasukReplacementEmployeeList() {
      return (List<lv_tidakmasuk>) get(PROPERTY_TIDAKMASUKREPLACEMENTEMPLOYEELIST);
    }

    public void setTidakmasukReplacementEmployeeList(List<lv_tidakmasuk> tidakmasukReplacementEmployeeList) {
        set(PROPERTY_TIDAKMASUKREPLACEMENTEMPLOYEELIST, tidakmasukReplacementEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<oez_daily_ln_prod_shrimp> getOezDailyLnProdShrimpList() {
      return (List<oez_daily_ln_prod_shrimp>) get(PROPERTY_OEZDAILYLNPRODSHRIMPLIST);
    }

    public void setOezDailyLnProdShrimpList(List<oez_daily_ln_prod_shrimp> oezDailyLnProdShrimpList) {
        set(PROPERTY_OEZDAILYLNPRODSHRIMPLIST, oezDailyLnProdShrimpList);
    }

    @SuppressWarnings("unchecked")
    public List<ImportAsset> getOezIAssetList() {
      return (List<ImportAsset>) get(PROPERTY_OEZIASSETLIST);
    }

    public void setOezIAssetList(List<ImportAsset> oezIAssetList) {
        set(PROPERTY_OEZIASSETLIST, oezIAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<oez_i_bpartnerbalance> getOezIBpartnerbalanceList() {
      return (List<oez_i_bpartnerbalance>) get(PROPERTY_OEZIBPARTNERBALANCELIST);
    }

    public void setOezIBpartnerbalanceList(List<oez_i_bpartnerbalance> oezIBpartnerbalanceList) {
        set(PROPERTY_OEZIBPARTNERBALANCELIST, oezIBpartnerbalanceList);
    }

    @SuppressWarnings("unchecked")
    public List<oez_prod_octopus> getOezProdOctopusList() {
      return (List<oez_prod_octopus>) get(PROPERTY_OEZPRODOCTOPUSLIST);
    }

    public void setOezProdOctopusList(List<oez_prod_octopus> oezProdOctopusList) {
        set(PROPERTY_OEZPRODOCTOPUSLIST, oezProdOctopusList);
    }

    @SuppressWarnings("unchecked")
    public List<ot_emergency_call> getEmergencyCallList() {
      return (List<ot_emergency_call>) get(PROPERTY_EMERGENCYCALLLIST);
    }

    public void setEmergencyCallList(List<ot_emergency_call> emergencyCallList) {
        set(PROPERTY_EMERGENCYCALLLIST, emergencyCallList);
    }

    @SuppressWarnings("unchecked")
    public List<ot_overtime> getOvertimeList() {
      return (List<ot_overtime>) get(PROPERTY_OVERTIMELIST);
    }

    public void setOvertimeList(List<ot_overtime> overtimeList) {
        set(PROPERTY_OVERTIMELIST, overtimeList);
    }

    @SuppressWarnings("unchecked")
    public List<pph_1721> getPph1721List() {
      return (List<pph_1721>) get(PROPERTY_PPH1721LIST);
    }

    public void setPph1721List(List<pph_1721> pph1721List) {
        set(PROPERTY_PPH1721LIST, pph1721List);
    }

    @SuppressWarnings("unchecked")
    public List<ImportPPh21> getPphIPph21List() {
      return (List<ImportPPh21>) get(PROPERTY_PPHIPPH21LIST);
    }

    public void setPphIPph21List(List<ImportPPh21> pphIPph21List) {
        set(PROPERTY_PPHIPPH21LIST, pphIPph21List);
    }

    @SuppressWarnings("unchecked")
    public List<pph_pph21> getPphPph21List() {
      return (List<pph_pph21>) get(PROPERTY_PPHPPH21LIST);
    }

    public void setPphPph21List(List<pph_pph21> pphPph21List) {
        set(PROPERTY_PPHPPH21LIST, pphPph21List);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_incidental_deduction> getPyrIncidentalDeductionList() {
      return (List<pyr_incidental_deduction>) get(PROPERTY_PYRINCIDENTALDEDUCTIONLIST);
    }

    public void setPyrIncidentalDeductionList(List<pyr_incidental_deduction> pyrIncidentalDeductionList) {
        set(PROPERTY_PYRINCIDENTALDEDUCTIONLIST, pyrIncidentalDeductionList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_incidental_earning> getPyrIncidentalEarningList() {
      return (List<pyr_incidental_earning>) get(PROPERTY_PYRINCIDENTALEARNINGLIST);
    }

    public void setPyrIncidentalEarningList(List<pyr_incidental_earning> pyrIncidentalEarningList) {
        set(PROPERTY_PYRINCIDENTALEARNINGLIST, pyrIncidentalEarningList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_sal_variable> getPyrSalVariableList() {
      return (List<pyr_sal_variable>) get(PROPERTY_PYRSALVARIABLELIST);
    }

    public void setPyrSalVariableList(List<pyr_sal_variable> pyrSalVariableList) {
        set(PROPERTY_PYRSALVARIABLELIST, pyrSalVariableList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_salarypayment> getPyrSalarypaymentPayrollmasterIDList() {
      return (List<pyr_salarypayment>) get(PROPERTY_PYRSALARYPAYMENTPAYROLLMASTERIDLIST);
    }

    public void setPyrSalarypaymentPayrollmasterIDList(List<pyr_salarypayment> pyrSalarypaymentPayrollmasterIDList) {
        set(PROPERTY_PYRSALARYPAYMENTPAYROLLMASTERIDLIST, pyrSalarypaymentPayrollmasterIDList);
    }

    @SuppressWarnings("unchecked")
    public List<pyr_sp_employee> getPyrSpEmployeeList() {
      return (List<pyr_sp_employee>) get(PROPERTY_PYRSPEMPLOYEELIST);
    }

    public void setPyrSpEmployeeList(List<pyr_sp_employee> pyrSpEmployeeList) {
        set(PROPERTY_PYRSPEMPLOYEELIST, pyrSpEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_akumulasi_pot_cuti> getAkumulasiPotCutiList() {
      return (List<ta_akumulasi_pot_cuti>) get(PROPERTY_AKUMULASIPOTCUTILIST);
    }

    public void setAkumulasiPotCutiList(List<ta_akumulasi_pot_cuti> akumulasiPotCutiList) {
        set(PROPERTY_AKUMULASIPOTCUTILIST, akumulasiPotCutiList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_akumulasi_pot_gaji> getAkumulasiPotGajiList() {
      return (List<ta_akumulasi_pot_gaji>) get(PROPERTY_AKUMULASIPOTGAJILIST);
    }

    public void setAkumulasiPotGajiList(List<ta_akumulasi_pot_gaji> akumulasiPotGajiList) {
        set(PROPERTY_AKUMULASIPOTGAJILIST, akumulasiPotGajiList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_c_bp_shift> getCBpShiftList() {
      return (List<ta_c_bp_shift>) get(PROPERTY_CBPSHIFTLIST);
    }

    public void setCBpShiftList(List<ta_c_bp_shift> cBpShiftList) {
        set(PROPERTY_CBPSHIFTLIST, cBpShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_detail_pot_cuti> getDetailPotCutiList() {
      return (List<ta_detail_pot_cuti>) get(PROPERTY_DETAILPOTCUTILIST);
    }

    public void setDetailPotCutiList(List<ta_detail_pot_cuti> detailPotCutiList) {
        set(PROPERTY_DETAILPOTCUTILIST, detailPotCutiList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_detail_pot_gaji> getDetailPotGajiList() {
      return (List<ta_detail_pot_gaji>) get(PROPERTY_DETAILPOTGAJILIST);
    }

    public void setDetailPotGajiList(List<ta_detail_pot_gaji> detailPotGajiList) {
        set(PROPERTY_DETAILPOTGAJILIST, detailPotGajiList);
    }

    @SuppressWarnings("unchecked")
    public List<ImportManualSchedule> getIManualscheduleList() {
      return (List<ImportManualSchedule>) get(PROPERTY_IMANUALSCHEDULELIST);
    }

    public void setIManualscheduleList(List<ImportManualSchedule> iManualscheduleList) {
        set(PROPERTY_IMANUALSCHEDULELIST, iManualscheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<ManualSchedule> getManualscheduleList() {
      return (List<ManualSchedule>) get(PROPERTY_MANUALSCHEDULELIST);
    }

    public void setManualscheduleList(List<ManualSchedule> manualscheduleList) {
        set(PROPERTY_MANUALSCHEDULELIST, manualscheduleList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_tukar_shift> getTukarShiftList() {
      return (List<ta_tukar_shift>) get(PROPERTY_TUKARSHIFTLIST);
    }

    public void setTukarShiftList(List<ta_tukar_shift> tukarShiftList) {
        set(PROPERTY_TUKARSHIFTLIST, tukarShiftList);
    }

    @SuppressWarnings("unchecked")
    public List<ta_tukar_shift> getTukarShiftPGSIDList() {
      return (List<ta_tukar_shift>) get(PROPERTY_TUKARSHIFTPGSIDLIST);
    }

    public void setTukarShiftPGSIDList(List<ta_tukar_shift> tukarShiftPGSIDList) {
        set(PROPERTY_TUKARSHIFTPGSIDLIST, tukarShiftPGSIDList);
    }

    @SuppressWarnings("unchecked")
    public List<tm_detail_pesangon> getDetailPesangonList() {
      return (List<tm_detail_pesangon>) get(PROPERTY_DETAILPESANGONLIST);
    }

    public void setDetailPesangonList(List<tm_detail_pesangon> detailPesangonList) {
        set(PROPERTY_DETAILPESANGONLIST, detailPesangonList);
    }

    @SuppressWarnings("unchecked")
    public List<tm_form_pesangon> getFormPesangonList() {
      return (List<tm_form_pesangon>) get(PROPERTY_FORMPESANGONLIST);
    }

    public void setFormPesangonList(List<tm_form_pesangon> formPesangonList) {
        set(PROPERTY_FORMPESANGONLIST, formPesangonList);
    }

    @SuppressWarnings("unchecked")
    public List<tm_termination> getTerminationList() {
      return (List<tm_termination>) get(PROPERTY_TERMINATIONLIST);
    }

    public void setTerminationList(List<tm_termination> terminationList) {
        set(PROPERTY_TERMINATIONLIST, terminationList);
    }


    @Override
    public Object get(String propName) {
      if (COMPUTED_COLUMN_PYRDEDUCTIONCOMPONENT.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getPYRDeductionComponent();
      }
      if (COMPUTED_COLUMN_PYREARNINGCOMPONENT.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getPYREarningComponent();
      }
      if (COMPUTED_COLUMN_PYRSALCATEGORY.equals(propName)) {
        if (get_computedColumns() == null) {
          return null;
        }
        return get_computedColumns().getPYRSalcategory();
      }
    
      return super.get(propName);
    }
}
