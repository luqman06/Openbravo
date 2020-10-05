CREATE OR REPLACE FUNCTION public.c_order_post1(p_pinstance_id character varying, p_order_id character varying, p_recalculatediscounts character varying)
 RETURNS void
 LANGUAGE plpgsql
AS $function$ DECLARE 
/*************************************************************************
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
* All portions are Copyright (C) 2001-2017 Openbravo SLU
* All Rights Reserved.
* Contributor(s):  ______________________________________.
************************************************************************/
  -- Logistics
  v_ResultStr VARCHAR(2000):=''; --OBTG:VARCHAR2--
  v_Message VARCHAR(2000):=''; --OBTG:VARCHAR2--
  v_Record_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_User VARCHAR(32); --OBTG:VARCHAR2--
  v_IsProcessing CHAR(1) ;
  v_IsProcessed VARCHAR(60) ;
  v_Result NUMERIC:=1; -- Success
  v_is_included NUMERIC:=0;
  v_is_ready AD_Org.IsReady%TYPE;
  v_is_tr_allow AD_OrgType.IsTransactionsAllowed%TYPE;
  -- Parameter
  --TYPE RECORD IS REFCURSOR;
    Cur_Parameter RECORD;
    Cur_line RECORD;
    Cur_Order RECORD;
  -- Record Info
  v_Client_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_Org_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_Warehouse_Org VARCHAR(32); --OBTG:VARCHAR2--
  v_Org_Name VARCHAR(60); --OBTG:VARCHAR2--
  v_UpdatedBy VARCHAR(32); --OBTG:VARCHAR2--
  v_DocAction VARCHAR(60) ;
  v_DocStatus VARCHAR(60) ;
  v_InvoiceRule VARCHAR(60) ;
  v_M_Warehouse_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_DocType_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_DocTypeTarget_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_DocSubTypeSO VARCHAR(60) ;
  v_DocSubTypeSOTarget VARCHAR(60) ;
  v_IsReturnDocType CHAR(1);
  v_IsBinding CHAR(1):='Y';
  v_istaxincluded CHAR(1);
  --
  ToDeliver NUMERIC:=0;
  ToInvoice NUMERIC:=0;
  ToDeliverOrToInvoice NUMERIC:=0;
  --
  InOut_ID VARCHAR(32); --OBTG:VARCHAR2--
  Invoice_ID VARCHAR(32); --OBTG:VARCHAR2--
  --Added by P.SAROBE
  v_documentno_Settlement VARCHAR(40); --OBTG:VARCHAR2--
  v_dateSettlement TIMESTAMP;
  v_Cancel_Processed VARCHAR(60);
  v_nameBankstatement VARCHAR (60); --OBTG:VARCHAR2--
  v_dateBankstatement TIMESTAMP;
  v_nameCash VARCHAR (60); --OBTG:VARCHAR2--
  v_dateCash TIMESTAMP;
  v_Bankstatementline_ID VARCHAR(32); --OBTG:VARCHAR2--
  --Finish added by P.Sarobe
  v_CashLine_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_ispaid CHAR(1);
  v_Settlement_Cancel_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_Cash_ID VARCHAR(32):=NULL; --OBTG:VARCHAR2--
  v_Line NUMERIC:=0;
  v_CashBook_ID VARCHAR(32):=NULL; --OBTG:VARCHAR2--
  v_Debtpayment_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_ISO_Code VARCHAR(10) ;
  v_DocumentNo VARCHAR(200) ; --OBTG:VARCHAR2--
  v_GrandTotal NUMERIC;
  v_Multiplier NUMERIC:=1;
  v_Date TIMESTAMP;
  v_WarehouseName VARCHAR(60) ; --OBTG:VARCHAR2--
  v_count NUMERIC;
  v_isSoTrx CHAR(1) ;
  v_Aux NUMERIC;
  v_c_Bpartner_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_c_currency_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_C_PROJECT_ID VARCHAR(32); --OBTG:VARCHAR2--
  FINISH_PROCESS BOOLEAN:=FALSE;
  END_PROCESSING BOOLEAN:=FALSE;
  v_CBPartner_ID VARCHAR(32); --OBTG:VARCHAR2--
  rowcount NUMERIC;

  v_CumDiscount NUMERIC;
  v_OldCumDiscount NUMERIC;
  v_OrderLineSeqNo NUMERIC;
  Cur_COrderDiscount RECORD;
  Cur_TaxDiscount RECORD;
  v_OrderLine VARCHAR(32); --OBTG:VARCHAR2--
  v_Discount NUMERIC;
  v_pricePrecision C_Currency.PricePrecision%TYPE;
  v_stdPrecision C_Currency.StdPrecision%TYPE;
  Cur_OrderLine RECORD;
  v_DiscountExist NUMERIC;
  v_gross_unit_price NUMERIC;
  v_line_gross_amount NUMERIC;
  v_acctAmount NUMERIC;
  v_reject_reason VARCHAR(32);
  v_dummy VARCHAR(2000); --OBTG:VARCHAR2--
  v_bpartner_blocked VARCHAR(1):='N'; --OBTG:VARCHAR2--
  v_orderBlocking VARCHAR(1):='N'; --OBTG:VARCHAR2--
  v_bpartner_name c_bpartner.name%TYPE;
  v_productname m_product.name%TYPE;

  v_iscashvat C_Order.IsCashVat%TYPE;
  v_recalculateDiscounts VARCHAR(1):='Y';
  v_isactive VARCHAR(1):='N'; --OBTG:VARCHAR2--
  z_linenetamt numeric;
  z_priceactual numeric;
  z_qtyordered numeric;

  BEGIN
	  IF (p_PInstance_ID IS NOT NULL) THEN
      --  Update AD_PInstance
      RAISE NOTICE '%','Updating PInstance - Processing ' || p_PInstance_ID ;
      v_ResultStr:='PInstanceNotFound';
      PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'Y', NULL, NULL) ;
      -- Get Parameters
      v_ResultStr:='ReadingParameters';
      FOR Cur_Parameter IN
        (SELECT i.Record_ID, i.AD_User_ID, p.ParameterName, p.P_String,
          p.P_Number, p.P_Date
        FROM AD_PINSTANCE i
        LEFT JOIN AD_PINSTANCE_PARA p
          ON i.AD_PInstance_ID=p.AD_PInstance_ID
        WHERE i.AD_PInstance_ID=p_PInstance_ID
        ORDER BY p.SeqNo
        )
      LOOP
        v_Record_ID:=Cur_Parameter.Record_ID;
        v_User:=Cur_Parameter.AD_User_ID;
        IF (Cur_Parameter.ParameterName = 'recalculateDiscounts') THEN
          v_recalculateDiscounts := Cur_Parameter.P_String;
        END IF;
      END LOOP; -- Get Parameter
    ELSE
      v_Record_ID:=p_Order_ID;
      v_recalculateDiscounts := p_recalculateDiscounts;
      SELECT CREATEDBY INTO v_User  FROM C_ORDER  WHERE C_ORDER_ID=p_Order_ID;
    END IF;
    RAISE NOTICE '%','  Record_ID=' || v_Record_ID ;
  BEGIN --BODY

  SELECT o.ISSOTRX, o.c_bpartner_id, o.DocAction, c.PricePrecision, c.StdPrecision, p.IsTaxIncluded
  INTO v_IsSOTrx, v_c_Bpartner_ID, v_DocAction, v_pricePrecision, v_stdPrecision, v_isTaxIncluded
  FROM C_ORDER o
  JOIN C_CURRENCY c
  ON o.C_CURRENCY_ID = c.C_CURRENCY_ID
  JOIN M_PRICELIST p
  ON o.M_PRICELIST_ID = p.M_PRICELIST_ID
  WHERE o.C_ORDER_ID = v_Record_ID;

  /*Orderline acct dimension*/
  IF (v_IsSOTrx = 'N') THEN
    FOR Cur_line IN
      (SELECT C_ORDERLINE.C_OrderLine_ID,
       C_ORDERLINE.LinenetAmt
       FROM C_ORDERLINE
       WHERE C_Order_ID = v_Record_ID
      )
    LOOP
      SELECT SUM(Amt) INTO v_acctAmount
      FROM C_ORDERLINE_ACCTDIMENSION
      WHERE C_OrderLine_ID = Cur_line.C_OrderLine_ID;
      IF (v_acctAmount <> Cur_line.LinenetAmt) THEN
        v_Message:='@QuantitiesNotMatch@';
        RAISE EXCEPTION '%', '@QuantitiesNotMatch@' ; --OBTG:-20000--
      END IF;
    END LOOP;
  ELSE
     IF (v_DocAction IN ('CO', 'PR')) THEN
       SELECT COUNT(1) 
         INTO v_Count
       FROM DUAL 
       WHERE EXISTS(SELECT 1
		      FROM C_ORDERLINE, M_PRODUCT
		      WHERE C_ORDERLINE.M_PRODUCT_ID = M_PRODUCT.M_PRODUCT_ID 
		      AND ISLINKEDTOPRODUCT = 'Y'
		      AND C_ORDER_ID = v_Record_ID
		      AND NOT EXISTS (SELECT 1 FROM C_ORDERLINE_SERVICERELATION WHERE C_ORDERLINE_SERVICERELATION.C_ORDERLINE_ID = C_ORDERLINE.C_ORDERLINE_ID));
       IF (v_Count <> 0) THEN
         v_Message:='@MissingServiceRelation@';
         RAISE EXCEPTION '%', '@MissingServiceRelation@' ; --OBTG:-20000--
       END IF;
     END IF;
  END IF;

  /* Check active business partner*/
  SELECT isactive INTO v_isactive
  FROM C_Bpartner
  WHERE C_Bpartner_ID = v_C_Bpartner_ID;

  IF(v_isactive = 'N') THEN
    RAISE EXCEPTION '%', '@InActiveBusinessPartner@'; --OBTG:-20000--
  END IF;
  
    /**
    * Read Order
    */
    v_ResultStr:='ReadingOrder';
    SELECT Processing, Processed, DocAction, DocStatus,
      C_DocType_ID, C_DocTypeTarget_ID, c_order.AD_Client_ID,
      c_order.AD_Org_ID, c_order.UpdatedBy, M_Warehouse_ID, TRUNC(DateOrdered),
      Issotrx, c_Bpartner_Id, c_order.c_currency_id, C_PROJECT_ID,
      C_BPartner_ID, invoicerule, c_order.IsCashVat
    INTO v_IsProcessing, v_IsProcessed, v_DocAction, v_DocStatus,
      v_DocType_ID, v_DocTypeTarget_ID, v_Client_ID,
      v_Org_ID, v_UpdatedBy, v_M_Warehouse_ID, v_Date,
      v_isSoTrx, v_c_Bpartner_Id, v_c_currency_id, v_C_PROJECT_ID,
      v_CBPartner_ID, v_invoicerule, v_iscashvat
    FROM C_ORDER
    WHERE C_Order_ID=v_Record_ID  FOR UPDATE; --OBTG:  --
    
    -- Get current DocSubTypeSO
    SELECT DocSubTypeSO
      INTO v_DocSubTypeSO
    FROM C_DOCTYPE
    WHERE C_DocType_ID=v_DocType_ID;
    SELECT DocSubTypeSO, isreturn
      INTO v_DocSubTypeSOTarget, v_isreturndoctype
    FROM C_DOCTYPE
    WHERE C_DocType_ID=v_DocTypetarget_ID;

  IF(v_isreturndoctype='Y' AND v_DocAction='CO') THEN
    DECLARE
    v_RefReturnQty NUMERIC:=0;
    v_RefMovementQty NUMERIC:=0;
    BEGIN
      v_Message:=NULL;
      FOR Cur_line IN
        (SELECT OL.M_INOUTLINE_ID, OL.LINE, OL.QTYORDERED
        FROM C_ORDERLINE OL
        WHERE OL.C_Order_ID = v_Record_ID
        AND OL.M_INOUTLINE_ID IS NOT NULL
        )
      LOOP
        SELECT COALESCE(SUM(OL.QtyOrdered),0) INTO v_RefReturnQty
        FROM C_ORDERLINE OL, C_ORDER O
        WHERE OL.M_INOUTLINE_ID = Cur_line.M_INOUTLINE_ID
        AND OL.C_ORDER_ID = O.C_ORDER_ID
        AND O.PROCESSED='Y';
        SELECT MovementQty INTO v_RefMovementQty
        FROM M_INOUTLINE WHERE M_INOUTLINE_ID = Cur_line.M_INOUTLINE_ID;
        v_RefReturnQty:=ABS(v_RefReturnQty) + ABS(Cur_Line.QtyOrdered);
        IF(v_RefReturnQty > v_RefMovementQty) THEN
          IF(v_Message IS NULL) THEN
            v_Message:=Cur_line.LINE;
          ELSE
            v_Message:=v_Message||', '||Cur_line.LINE;
          END IF;
        END IF;
      END LOOP;
      IF(v_Message IS NOT NULL) THEN
        RAISE EXCEPTION '%','@ReturnQtyMismatch@'||v_Message; --OBTG:-20000--
      END IF;
    END;
  END IF;

    --Check whether warehouse belongs to the organization.
    SELECT count(AD_ORG_ID)
    INTO v_count
    FROM AD_Org_Warehouse
    WHERE M_Warehouse_ID=v_M_Warehouse_ID
    AND AD_Org_ID = v_Org_ID;

    IF v_count = 0 AND v_IsSOTrx = 'Y' THEN
    RAISE EXCEPTION '%','@WrongWarehouse@' ; --OBTG:-20000--
    END IF;

    SELECT AD_Org_ID
    INTO v_Warehouse_Org
    FROM M_Warehouse
    WHERE M_Warehouse_ID = v_M_Warehouse_ID;

    IF(ad_org_isinnaturaltree(v_Warehouse_Org, v_Org_ID, v_Client_ID) = 'N' AND v_isSoTrx = 'N') THEN
      RAISE EXCEPTION '%','@WrongWarehouse@'; --OBTG:-20000--
    END IF;

    SELECT CASE WHEN (m.ISSOTRX='Y') THEN customer_blocking  ELSE vendor_blocking END ,  
    CASE WHEN (m.ISSOTRX='Y') THEN so_order_blocking ELSE po_order_blocking  END, name, DocAction
      INTO v_bpartner_blocked, v_orderBlocking, v_bpartner_name, v_DocAction
    FROM C_ORDER m, C_BPartner bp
    WHERE m.c_bpartner_id=bp.c_bpartner_id
    AND m.C_ORDER_ID=v_Record_ID
    AND m.C_BPARTNER_ID=v_c_Bpartner_ID;
    IF (v_DocAction = 'CO' AND v_bpartner_blocked = 'Y' AND v_orderBlocking = 'Y' AND v_isreturndoctype='N' ) THEN
      RAISE EXCEPTION '%','@ThebusinessPartner@'||' '|| v_bpartner_name ||' '||'@BusinessPartnerBlocked@'; --OBTG:-20000--
    END IF;
    -- Get the name of the org of the Order. Added by P.Sarobe
    SELECT name INTO v_Org_Name FROM AD_ORG WHERE ad_org_id = v_Org_ID;
    RAISE NOTICE '%','DocAction=' || v_DocAction || ', DocStatus=' || v_DocStatus || ', DocType_ID=' || v_DocType_ID || ', DocTypeTarget_ID=' || v_DocTypeTarget_ID || ', DocSubTypeSO=' || v_DocSubTypeSO ;

    SELECT count(*) INTO v_count
    FROM dual
    WHERE EXISTS (
        SELECT 1
        FROM c_orderline ol JOIN m_product p ON ol.m_product_id = p.m_product_id
        WHERE ol.c_order_id = v_record_id
          AND p.isgeneric = 'Y');
    IF (v_count > 0) THEN
      SELECT max(p.name) INTO v_productname
      FROM c_orderline ol JOIN m_product p ON ol.m_product_id = p.m_product_id
      WHERE ol.c_order_id = v_record_id
        AND p.isgeneric = 'Y';
      RAISE EXCEPTION '%', '@CannotUseGenericProduct@ ' || v_productName; --OBTG:-20000--
    END IF;

    /** 
    * Quotations
    */
    IF (v_DocSubTypeSOTarget = 'OB'AND v_DocAction = 'RJ') THEN
      SELECT c_reject_reason_id
        INTO v_reject_reason
      FROM C_ORDER
      WHERE C_Order_ID=v_Record_ID;
      IF (v_reject_reason IS NULL) THEN
        RAISE EXCEPTION '%', '@NoRejectReason@' ; --OBTG:-20000--
      END IF;
      /*
        * Undo inventory reservation
        */
        BEGIN
          v_ResultStr:='ReserveInventory';
          -- Set reserved quantity to 0
          UPDATE C_ORDERLINE
          SET QtyReserved = 0,
              Updated=TO_DATE(NOW()),
              UpdatedBy=v_User
          WHERE c_orderline_id IN (select c_orderline_id
                                   from c_orderline
                                   where c_order_id = v_Record_id);
        END;
      UPDATE C_ORDER
      SET DocStatus='CJ',
          DocAction='--',
          Processed='Y',
          Updated=TO_DATE(NOW()),
          UpdatedBy=v_User
      WHERE C_Order_ID=v_Record_ID;
      IF (p_PInstance_ID IS NOT NULL) THEN
      	--  Update AD_PInstance
      	RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message ;
        PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', v_Result, v_Message) ;
      END IF;
      RETURN;
    END IF;

    /**
    * Check if order has lines
    */
    IF (v_DocAction = 'CO' OR v_DocAction = 'PR') THEN
      SELECT COUNT(*)
      INTO v_Aux
      FROM C_ORDERLINE
      WHERE C_ORDER_ID = v_Record_ID;
      IF (v_Aux = 0) THEN
        RAISE EXCEPTION '%', '@OrderWithoutLines@'; --OBTG:-20000--
      END IF;
    END IF;

    /**
    * Check if already processed / reactivated
    */
    IF (v_DocAction = 'CO' OR v_DocAction = 'PR') THEN
      IF (v_IsProcessed = 'Y') THEN
        RAISE EXCEPTION '%','@AlreadyPosted@'; --OBTG:-20000--
      END IF;
    ELSIF (v_DocAction='RE') THEN
      IF (v_IsProcessed = 'N') THEN
        RAISE EXCEPTION '%','@ActionNotSupported@'; --OBTG:-20000--
      END IF;
    END IF;

    -- Check the cash vat flag for all the taxes matches the order one
    IF (v_DocAction IN ('CO', 'PR')) THEN
      select count(1)
      into v_Aux
      from c_ordertax ot inner join c_tax t on (ot.c_tax_id = t.c_tax_id)
      where ot.c_order_id = v_Record_ID
      and t.iswithholdingtax = 'N'
      and t.rate <> 0
      and t.IsCashVat <> v_iscashvat;

      IF (v_Aux > 0) THEN
        RAISE EXCEPTION '%', '@CashVATNotMatch@'; --OBTG:-20000--
      END IF;
    END IF;

    /**
    * Order Closed, Voided or Reversed - No action possible
    */
    IF(v_DocStatus IN('CL', 'VO', 'RE')) THEN
      RAISE EXCEPTION '%', '@AlreadyPosted@' ; --OBTG:-20000--
    END IF;

    /**
    * Waiting on Prepayment  can only be closed
    */
    IF(v_DocStatus='WP' AND v_DocAction<>'CL') THEN
      RAISE EXCEPTION '%', '@WaitingPayment@' ; --OBTG:-20000--
    END IF;

    IF (v_DocSubTypeSOTarget='PR' AND v_invoicerule <> 'I') THEN
      RAISE EXCEPTION '%', '@PrepayMustImmediate@'; --OBTG:-20000--
    END IF;
    /**
    * Unlock
    */
    IF(v_DocAction='XL') THEN
      UPDATE C_ORDER
        SET Processing='N',
        DocAction='--',
        Updated=TO_DATE(NOW()),
        UpdatedBy=v_User
      WHERE C_Order_ID=v_Record_ID;
      FINISH_PROCESS:=TRUE;
    END IF;
    IF(NOT FINISH_PROCESS) THEN
      IF(v_IsProcessing='Y') THEN
        RAISE EXCEPTION '%', '@OtherProcessActive@' ; --OBTG:-20000--
      END IF;

      /**
      * Re-activate
      */
      IF (v_DocAction='RE') THEN
        IF (v_DocSubTypeSO IN ('WR', 'WI')) THEN
          RAISE EXCEPTION '%', '@ActionNotSupported@'; --OBTG:-20000--
        END IF;
        --Verify not managed debtPayments added by ALO
        --Added by P.Sarobe. New messages
        SELECT max(c_debt_payment_id), COUNT(*)
          INTO v_Debtpayment_ID, v_Aux
        FROM C_DEBT_PAYMENT
        WHERE C_Order_ID=v_Record_ID
          AND C_Debt_Payment_Status(C_Settlement_Cancel_ID, Cancel_Processed, Generate_Processed, IsPaid, IsValid, C_CashLine_ID, C_BankStatementLine_ID)!='P';
        IF (v_Aux != 0) THEN
          --Added by P.Sarobe. New messages
          SELECT c_Bankstatementline_Id, c_cashline_id, c_settlement_cancel_id, ispaid, cancel_processed
            INTO v_Bankstatementline_ID, v_CashLine_ID, v_Settlement_Cancel_ID, v_ispaid, v_Cancel_Processed
          FROM C_DEBT_PAYMENT WHERE C_Debt_Payment_ID = v_Debtpayment_ID;

          IF (v_Bankstatementline_ID IS NOT NULL) THEN
            SELECT C_BANKSTATEMENT.NAME, C_BANKSTATEMENT.STATEMENTDATE
              INTO v_nameBankstatement, v_dateBankstatement
            FROM C_BANKSTATEMENT, C_BANKSTATEMENTLINE
            WHERE C_BANKSTATEMENT.C_BANKSTATEMENT_ID = C_BANKSTATEMENTLINE.C_BANKSTATEMENT_ID
              AND C_BANKSTATEMENTLINE.C_BANKSTATEMENTLINE_ID = v_Bankstatementline_ID;
            RAISE EXCEPTION '%', '@ManagedDebtPaymentOrderBank@'||v_nameBankstatement||' '||'@Bydate@'||v_dateBankstatement ; --OBTG:-20000--
          END IF;
          IF (v_CashLine_ID IS NOT NULL) THEN
            SELECT C_CASH.NAME, C_CASH.STATEMENTDATE
              INTO v_nameCash, v_dateCash
            FROM C_CASH, C_CASHLINE
            WHERE C_CASH.C_CASH_ID = C_CASHLINE.C_CASH_ID
              AND C_CASHLINE.C_CASHLINE_ID = v_CashLine_ID;
            RAISE EXCEPTION '%', '@ManagedDebtPaymentOrderCash@'||v_nameCash||' '||'@Bydate@'||v_dateCash ; --OBTG:-20000--
          END IF;
          IF (v_Cancel_Processed='Y' AND v_ispaid='N') THEN
            SELECT documentno, datetrx
              INTO v_documentno_Settlement, v_dateSettlement
            FROM C_SETTLEMENT
            WHERE C_SETTLEMENT_ID = v_Settlement_Cancel_ID;
            RAISE EXCEPTION '%', '@ManagedDebtPaymentOrderCancel@'||v_documentno_Settlement||' '||'@Bydate@'||v_dateSettlement ; --OBTG:-20000--
          END IF;
        END IF;

        RAISE NOTICE '%','Re-Activating ' || v_DocSubTypeSO || ': ' || v_Record_ID ;
        IF(v_DocSubTypeSO IN ('WI', 'WP', 'WR')) THEN
          -- Cancel existing Deli very + Invoice Documents
          PERFORM M_INOUT_CANCEL(NULL, v_Record_ID) ;
          IF (v_DocSubTypeSO<>'WP') THEN
            PERFORM C_INVOICE_CANCEL(NULL, v_Record_ID);
          END IF;
        END IF;
        -- Update Order
        v_ResultStr:='ReActivate';
        UPDATE C_ORDER
        SET DocStatus='IP', -- In Progress
            DocAction='CO',
            Processing='N',
            Processed='N',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
        UPDATE M_INOUTLINE SET C_ORDERLINE_ID = NULL
        WHERE (SELECT DISTINCT A.DOCSTATUS FROM M_INOUT A, M_INOUTLINE B, C_ORDERLINE C
               WHERE A.M_INOUT_ID = B.M_INOUT_ID AND B.C_ORDERLINE_ID = C.C_ORDERLINE_ID
                 AND C.C_ORDER_DISCOUNT_ID IS NOT NULL AND C.C_ORDER_ID = v_Record_ID) = 'VO'
                 AND C_ORDERLINE_ID IN (SELECT C_ORDERLINE_ID FROM C_ORDERLINE WHERE C_ORDER_DISCOUNT_ID IS NOT NULL 
                 AND C_ORDER_ID = v_Record_ID);
	DELETE
	FROM C_ORDERLINETAX
	WHERE EXISTS (SELECT 1
                     FROM C_ORDERLINE
                     WHERE C_ORDER_DISCOUNT_ID IS NOT NULL
		      AND C_ORDER_ID = v_Record_ID
		      AND C_ORDERLINE.C_ORDERLINE_ID = C_ORDERLINETAX.C_ORDERLINE_ID);
        DELETE
        FROM C_ORDERLINE
        WHERE C_ORDER_DISCOUNT_ID IS NOT NULL
          AND C_ORDER_ID=v_Record_ID;
        --ADDED BY E.ARGAL
        --Invalidate debt payments added by ALO
        UPDATE C_DEBT_PAYMENT
        SET IsValid='N',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID
          AND IsAutomaticGenerated='N';

        DELETE
        FROM C_CASHLINE
        WHERE C_Debt_Payment_Id IN
            (SELECT C_DEBT_PAYMENT_ID
            FROM C_DEBT_PAYMENT
            WHERE C_Order_ID=v_Record_ID
              AND COALESCE(IsAutomaticGenerated, 'Y')='Y'
            );
        DELETE
        FROM C_DEBT_PAYMENT
        WHERE C_Order_ID=v_Record_ID
          AND COALESCE(IsAutomaticGenerated, 'Y')='Y';
        /*
        * Undo inventory reservation
        */
        DECLARE
          Cur_ResLine RECORD;
          v_QtySO NUMERIC; -- Reserved
          v_QtyOrderSO NUMERIC;
          v_QtyPO NUMERIC; -- Ordered
          v_QtyOrderPO NUMERIC;
          v_UOM_ID VARCHAR(32); --OBTG:VARCHAR2--
        BEGIN
          v_ResultStr:='ReserveInventory';
          -- For all lines needing reservation
          FOR Cur_ResLine IN
            (SELECT l.M_Warehouse_ID, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_OrderLine_ID,
                 l.QtyOrdered AS Qty, l.QUANTITYORDER, l.qtyreserved, l.qtydelivered,
                l.C_UOM_ID, l.M_PRODUCT_UOM_ID, l.C_AUM
             FROM C_ORDERLINE l, M_PRODUCT p
             WHERE l.C_Order_ID=v_Record_ID  -- Reserve Products (not: services, null products)
               AND l.M_Product_ID=p.M_Product_ID
               AND p.IsStocked='Y'
               AND p.ProductType='I' 
             )
            LOOP
            -- Qty corrected for SO/PO
            IF (v_DocSubTypeSO IS NULL) THEN
              v_QtySO:=0;
              v_QtyOrderSO:=NULL;
              v_QtyPO:=Cur_ResLine.qtydelivered-Cur_ResLine.qty;
              v_QtyOrderPO:=NULL;
              IF (Cur_ResLine.QtyDelivered=0) THEN
                v_QtyOrderPO := -Cur_ResLine.QuantityOrder;
              ELSIF Cur_ResLine.C_AUM IS NULL AND Cur_ResLine.M_Product_UOM_ID IS NOT NULL THEN
                SELECT c_uom_id
                INTO v_UOM_ID
                FROM m_product_uom
                WHERE m_product_uom_id = Cur_ResLine.M_Product_UOM_ID;
                v_QtyOrderPO := -C_Uom_Convert(v_QtyPO, Cur_ResLine.C_UOM_ID, v_UOM_ID, 'Y');
              END IF;
            ELSE
              v_QtySO:=-Cur_ResLine.QtyReserved;
              IF (Cur_ResLine.QtyReserved=Cur_ResLine.Qty) THEN
                v_QtyOrderSO := -Cur_ResLine.QuantityOrder;
              ELSIF Cur_ResLine.C_AUM IS NULL AND Cur_ResLine.M_Product_UOM_ID IS NOT NULL THEN
                SELECT c_uom_id
                INTO v_UOM_ID
                FROM m_product_uom
                WHERE m_product_uom_id = Cur_ResLine.M_Product_UOM_ID;
                v_QtyOrderSO := -C_Uom_Convert(v_QtySO, Cur_ResLine.C_UOM_ID, v_UOM_ID, 'Y');
              END IF;
              v_QtyPO:=0;
              v_QtyOrderPO:=NULL;
            END IF;
            IF ((v_DocStatus<>'IP' OR v_DocAction<>'CO') AND COALESCE(v_DocSubTypeSO, '')<>'OB') THEN
              PERFORM M_UPDATE_STORAGE_PENDING(v_Client_ID, v_Org_ID, v_UpdatedBy, Cur_ResLine.M_Product_ID, Cur_ResLine.M_Warehouse_ID, Cur_ResLine.M_AttributeSetInstance_ID, Cur_ResLine.C_UOM_ID, Cur_ResLine.M_PRODUCT_UOM_ID, v_QtySO, v_QtyOrderSO, v_QtyPO, v_QtyOrderPO) ;
            END IF;
          END LOOP;
          -- Set reserved quantity to 0
          UPDATE C_ORDERLINE
          SET QtyReserved = 0,
              Updated=TO_DATE(NOW()),
              UpdatedBy=v_User
          WHERE c_orderline_id IN (select c_orderline_id
                                   from c_orderline
                                   where c_order_id = v_Record_id);
        END;
        /**
        *  Manage Stock Reservations
        */
        SELECT COUNT(1) INTO v_count
        FROM ad_preference
        WHERE property = 'StockReservations';
        IF (v_count > 1) THEN
          v_dummy := AD_GET_PREFERENCE_VALUE('StockReservations', 'Y', v_client_id, v_org_id, NULL, NULL, NULL);
        ELSIF (v_count = 1) THEN
          UPDATE c_orderline
          SET so_res_status = NULL
          WHERE c_order_id = v_Record_id;
          UPDATE c_order
          SET so_res_status = NULL
          WHERE c_order_id = v_record_id;
        END IF;

        --ADDED BY P.SAROBE but to be deprecated 26052007
        SELECT MAX(C_CASHLINE_ID)
          INTO v_CashLine_ID
        FROM C_CASHLINE
        WHERE C_ORDER_ID=v_Record_ID;
        IF (v_CashLine_ID IS NOT NULL) THEN
          SELECT PROCESSED
            INTO v_IsProcessed
          FROM C_CASH, C_CASHLINE
          WHERE C_CASH.C_CASH_ID=C_CASHLINE.C_CASH_ID
            AND C_CASHLINE_ID=v_CashLine_ID;
          IF (v_IsProcessed='N') THEN
            DELETE FROM C_CASHLINE WHERE C_CASHLINE_ID=v_CashLine_ID;
          ELSE
            SELECT C_CASH.NAME, C_CASH.STATEMENTDATE, C_CASHLINE.LINE
              INTO v_nameCash, v_dateCash, v_Line
            FROM C_CASH, C_CASHLINE
            WHERE C_CASH.C_CASH_ID = C_CASHLINE.C_CASH_ID
              AND C_CASHLINE.C_CASHLINE_ID = v_CashLine_ID;
            RAISE EXCEPTION '%', '@Ordercahslineprocessed@'||v_nameCash||' '||'@Bydate@'||v_dateCash||' '||'@line@'||v_Line ; --OBTG:-20000--
          END IF;
        END IF;

        UPDATE C_ORDER
        SET DocStatus='DR', -- Draft
            DocAction='CO',
            Processing='N',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;

        FINISH_PROCESS:=TRUE;
      END IF;
    END IF;--FINISH_PROCESS
    IF (NOT FINISH_PROCESS) THEN
      SELECT COUNT(*)
        INTO v_Count
      FROM C_ORDER C, C_DOCTYPE
      WHERE C_DocType.DocBaseType IN ('SOO', 'POO')
        AND C_DocType.IsSOTrx=C.ISSOTRX
        AND AD_ISORGINCLUDED(C.AD_Org_ID,C_DocType.AD_Org_ID, C.AD_Client_ID) <> -1
        AND C.C_DOCTYPETARGET_ID = C_DOCTYPE.C_DOCTYPE_ID
        AND C.C_ORDER_ID = v_Record_ID;
      IF (v_Count=0) THEN
        RAISE EXCEPTION '%', '@NotCorrectOrgDoctypeOrder@' ; --OBTG:-20000--
      END IF;

      SELECT COUNT(*)
        INTO v_Count
      FROM C_ORDER C, C_ORDERLINE OL
      WHERE C.C_ORDER_ID = OL.C_ORDER_ID
        AND AD_ISORGINCLUDED(OL.AD_Org_ID, C.AD_Org_ID, C.AD_Client_ID) = -1
        AND C.C_ORDER_ID = v_Record_ID;
      IF (v_Count>0) THEN
        RAISE EXCEPTION '%', '@NotCorrectOrgLines@' ; --OBTG:-20000--
      END IF;

      /**
      * Close Order - prepare
      */
      DECLARE
          Cur_Inventory RECORD;
          v_QtyOrdered NUMERIC;
          v_QtyAum NUMERIC;
          v_QuantityOrder NUMERIC;
          v_linenetamt NUMERIC;
          v_linegrossamt NUMERIC;
          v_ProductUOM M_PRODUCT_UOM.C_UOM_ID%TYPE;
      BEGIN

        -- When closing the order it calculates the difference between the ordered and received/delivered quantities ant it
        -- updates the m_storage_pending.
        IF (v_DocAction='CL') THEN
          -- Cancel undelivered Items
          IF (v_isSoTrx='Y') THEN --Sales orders
            FOR Cur_Inventory IN (
              SELECT QtyInvoiced, QtyDelivered ,QtyOrdered, QuantityOrder, priceactual, gross_unit_price,
	      
	      C_ORDERLINE_ID AS ID,
	      M_Product_ID,
	      M_Warehouse_ID,
	      M_AttributeSetInstance_ID,
	      C_UOM_ID,
             C_AUM,
	      M_PRODUCT_UOM_ID,
          C_Currency_ID,
          em_oez_makenetunitpricefixed, linenetamt
	      FROM C_ORDERLINE
	      WHERE C_Order_ID=v_Record_ID
                AND QtyOrdered <> (SELECT (CASE WHEN (qtyinvoiced = 0) THEN QtyDelivered ELSE 
			  	    (CASE WHEN (QtyDelivered = 0) THEN qtyinvoiced ELSE 
					(CASE WHEN (QtyDelivered < 0 AND qtyinvoiced < 0) THEN LEAST(QtyDelivered, qtyinvoiced) ELSE GREATEST(QtyDelivered, qtyinvoiced) END) END) END)
                                  FROM C_ORDERLINE COL
                                  WHERE COL.C_ORDERLINE_ID = C_ORDERLINE.C_ORDERLINE_ID)
                AND m_product_id IS NOT NULL
            )
            LOOP
              raise exception 'fixed net unit price %, linenet amt %', Cur_Inventory.em_oez_makenetunitpricefixed, Cur_Inventory.linenetamt;
              v_QtyOrdered := CASE WHEN (Cur_Inventory.QtyDelivered < 0) THEN LEAST(Cur_Inventory.QtyDelivered, Cur_Inventory.QtyInvoiced) ELSE GREATEST(Cur_Inventory.QtyDelivered, Cur_Inventory.QtyInvoiced) END;
              v_QtyAum := M_GET_CONVERTED_AUMQTY(Cur_Inventory.M_Product_ID, v_QtyOrdered, Cur_Inventory.C_AUM);
              IF (Cur_Inventory.em_oez_makenetunitpricefixed='N') then
              	v_linenetamt := ROUND(v_QtyOrdered * Cur_Inventory.priceactual, C_GET_CURRENCY_PRECISION(Cur_Inventory.C_Currency_ID, 'A'));
              else
              	v_linenetamt := Cur_Inventory.linenetamt;
              end if;
              
              v_linegrossamt := ROUND(v_QtyOrdered * Cur_Inventory.gross_unit_price, C_GET_CURRENCY_PRECISION(Cur_Inventory.C_Currency_ID, 'A'));
              SELECT MAX(UOM.C_UOM_ID)
              INTO v_ProductUOM
              FROM M_PRODUCT_UOM UOM 
              WHERE UOM.M_PRODUCT_UOM_ID=Cur_Inventory.M_PRODUCT_UOM_ID;
              v_QuantityOrder := CASE WHEN Cur_Inventory.C_AUM IS NOT NULL OR (Cur_Inventory.C_AUM IS NULL AND Cur_Inventory.M_PRODUCT_UOM_ID IS NULL) THEN Cur_Inventory.QuantityOrder
                                 ELSE (c_uom_convert((CASE WHEN (Cur_Inventory.QtyDelivered < 0) THEN LEAST(Cur_Inventory.QtyDelivered, Cur_Inventory.QtyInvoiced) ELSE GREATEST(Cur_Inventory.QtyDelivered, Cur_Inventory.QtyInvoiced) END), 
              Cur_Inventory.C_UOM_ID, v_ProductUOM,'Y')) END;

              IF (Cur_Inventory.QtyOrdered <> v_QtyOrdered) THEN
                PERFORM M_UPDATE_STORAGE_PENDING(v_Client_ID, v_Org_ID, v_UpdatedBy, Cur_Inventory.M_Product_ID, Cur_Inventory.M_Warehouse_ID, Cur_Inventory.M_AttributeSetInstance_ID,
                Cur_Inventory.C_UOM_ID, Cur_Inventory.M_PRODUCT_UOM_ID, -(Cur_Inventory.QtyOrdered - v_QtyOrdered), -(Cur_Inventory.QuantityOrder - v_QuantityOrder), 0, null);
              END IF;

              -- UPDATE C_ORDERLINE
              UPDATE C_ORDERLINE
              SET QtyOrdered=v_QtyOrdered,
                AumQty = v_QtyAum,
                linenetamt=v_linenetamt,
                line_gross_amount=v_linegrossamt,
                QuantityOrder=v_QuantityOrder,
                Updated=TO_DATE(NOW())
              WHERE C_ORDERLINE_ID = Cur_Inventory.ID;
            END LOOP;
            -- For Purchase orders, M_MatchPO table used. Notice that only delivered lines(C_Invoiceline_Id is null) using
          ELSE
            FOR Cur_Inventory IN (
              SELECT 
                COALESCE((SELECT SUM(CASE WHEN M_MATCHPO.M_INOUTLINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END)
                FROM M_MATCHPO
                WHERE M_MATCHPO.C_ORDERLINE_ID=C_ORDERLINE.C_ORDERLINE_ID), 0) AS QtyDelivered,
		COALESCE((SELECT   SUM(CASE WHEN M_MATCHPO.C_INVOICELINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END)
                FROM M_MATCHPO
                WHERE M_MATCHPO.C_ORDERLINE_ID=C_ORDERLINE.C_ORDERLINE_ID), 0) AS QtyInvoiced,
                QtyOrdered,
                QuantityOrder,
		priceactual,
		gross_unit_price,
		linenetamt,
                C_ORDERLINE_ID AS ID,
	        M_Product_ID,
	        M_Warehouse_ID,
	        M_AttributeSetInstance_ID,
	        C_UOM_ID,
               C_AUM,
	        M_PRODUCT_UOM_ID,
	        C_Currency_ID
              FROM C_ORDERLINE
              WHERE C_ORDERLINE.C_ORDER_ID=v_Record_ID
                AND qtyordered <> COALESCE((
                    SELECT (CASE WHEN (SUM(CASE WHEN M_MATCHPO.M_INOUTLINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END) = 0) 
				THEN SUM(CASE WHEN M_MATCHPO.C_INVOICELINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END) ELSE 
					(CASE WHEN (SUM(CASE WHEN M_MATCHPO.C_INVOICELINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END) = 0) 
						THEN SUM(CASE WHEN M_MATCHPO.M_INOUTLINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END)  
						ELSE (CASE WHEN (SUM(CASE WHEN M_MATCHPO.M_INOUTLINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END) < 0 AND SUM(CASE WHEN M_MATCHPO.C_INVOICELINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END) < 0)  
							THEN LEAST(SUM(CASE WHEN M_MATCHPO.M_INOUTLINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END),SUM(CASE WHEN M_MATCHPO.C_INVOICELINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END)) 
							ELSE GREATEST(SUM(CASE WHEN M_MATCHPO.M_INOUTLINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END),SUM(CASE WHEN M_MATCHPO.C_INVOICELINE_ID IS NULL THEN M_MATCHPO.QTY ELSE 0 END)) END) 
					END)
				END)
                    FROM M_MATCHPO
                    WHERE M_MATCHPO.C_ORDERLINE_ID=C_ORDERLINE.C_ORDERLINE_ID
                    ), 0)
            )
            LOOP
              v_QtyOrdered := COALESCE(CASE WHEN (Cur_Inventory.QtyDelivered < 0) THEN LEAST(Cur_Inventory.QtyDelivered, Cur_Inventory.QtyInvoiced) ELSE GREATEST(Cur_Inventory.QtyDelivered, Cur_Inventory.QtyInvoiced) END, 0);
              v_QtyAum := M_GET_CONVERTED_AUMQTY(Cur_Inventory.M_Product_ID, v_QtyOrdered, Cur_Inventory.C_AUM);
              v_linenetamt := ROUND(COALESCE(v_QtyOrdered, 0) * Cur_Inventory.priceactual, C_GET_CURRENCY_PRECISION(Cur_Inventory.C_Currency_ID, 'A'));
              v_linegrossamt := ROUND(COALESCE(v_QtyOrdered, 0) * Cur_Inventory.gross_unit_price, C_GET_CURRENCY_PRECISION(Cur_Inventory.C_Currency_ID, 'A'));

              SELECT MAX(UOM.C_UOM_ID)
              INTO v_ProductUOM
              FROM M_PRODUCT_UOM UOM 
              WHERE UOM.M_PRODUCT_UOM_ID=Cur_Inventory.M_PRODUCT_UOM_ID;
              v_QuantityOrder := CASE WHEN Cur_Inventory.C_AUM IS NOT NULL OR (Cur_Inventory.C_AUM IS NULL AND Cur_Inventory.M_PRODUCT_UOM_ID IS NULL) THEN Cur_Inventory.QuantityOrder
                                 ELSE (COALESCE(c_uom_convert((CASE WHEN (Cur_Inventory.QtyDelivered < 0) THEN LEAST(Cur_Inventory.QtyDelivered, Cur_Inventory.QtyInvoiced) ELSE GREATEST(Cur_Inventory.QtyDelivered, Cur_Inventory.QtyInvoiced) END),
              Cur_Inventory.C_UOM_ID, v_ProductUOM,'Y'), 0)) END;
              IF (Cur_Inventory.QtyOrdered <> v_QtyOrdered) THEN
                PERFORM M_UPDATE_STORAGE_PENDING(v_Client_ID, v_Org_ID, v_UpdatedBy, Cur_Inventory.M_Product_ID, Cur_Inventory.M_Warehouse_ID, Cur_Inventory.M_AttributeSetInstance_ID,
                Cur_Inventory.C_UOM_ID, Cur_Inventory.M_PRODUCT_UOM_ID, 0, null, -(Cur_Inventory.QtyOrdered - v_QtyOrdered), -(Cur_Inventory.QuantityOrder - v_QuantityOrder));
              END IF;

              -- UPDATE C_ORDERLINE
              UPDATE C_ORDERLINE
              SET QtyOrdered=v_QtyOrdered,
                AumQty = v_QtyAum,
                linenetamt=v_linenetamt,
                line_gross_amount=v_linegrossamt,
                QuantityOrder=v_QuantityOrder,
                Updated=TO_DATE(NOW())
              WHERE C_ORDERLINE_ID = Cur_Inventory.ID;
            END LOOP;
          END IF;
          -- if there is no change, the tax calculation, etc. is not needed.
          -- potential problem, if posted (i.e. encumbered) for full amount
          -- and the rest then cancelled out.
        END IF;
      END;

      /**
      *Update Product purchasing Plan Table
      * Return Material orders do not update the last price.
      */
      IF (v_isSoTrx ='N' AND v_isreturndoctype = 'N') THEN
        FOR Cur_OrderLine IN (SELECT * FROM C_ORDERLINE WHERE C_Order_Id =  v_Record_ID)
        LOOP
          UPDATE M_PRODUCT_PO SET PriceLastPO=Cur_OrderLine.PriceActual 
          Where C_BPARTNER_ID = v_CBPartner_ID AND M_PRODUCT_ID = Cur_OrderLine.M_PRODUCT_ID
          AND Ad_Isorgincluded(Cur_OrderLine.AD_ORG_ID,AD_ORG_ID, Cur_OrderLine.AD_Client_ID) <> -1;
        END LOOP;
      END IF;
      /**
      * Void Order - prepare
      */
      IF (v_DocAction='VO') THEN
        -- Cancel all Items
        UPDATE C_ORDERLINE
          SET QtyOrdered=0,
          --MODIFIED BY F.IRIAZABAL
          QuantityOrder = CASE WHEN C_AUM IS NOT NULL OR (C_AUM IS NULL AND M_PRODUCT_UOM_ID IS NULL) THEN NULL ELSE 0 END,
          LineNetAmt=0,
          Updated=TO_DATE(NOW())
        WHERE C_Order_ID=v_Record_ID
          AND QtyOrdered<>0;
      END IF;

     /**************************************************************************
      * Start Processing ------------------------------------------------------
      *************************************************************************/
      -- Check the header belongs to a organization where transactions are posible and ready to use
      SELECT AD_Org.IsReady, Ad_OrgType.IsTransactionsAllowed
        INTO v_is_ready, v_is_tr_allow
      FROM C_ORDER, AD_Org, AD_OrgType
      WHERE AD_Org.AD_Org_ID=C_ORDER.AD_Org_ID
        AND AD_Org.AD_OrgType_ID=AD_OrgType.AD_OrgType_ID
        AND C_ORDER.C_ORDER_ID=v_Record_ID;
      IF (v_is_ready='N') THEN
        RAISE EXCEPTION '%', '@OrgHeaderNotReady@'; --OBTG:-20000--
      END IF;
      IF (v_is_tr_allow='N') THEN
        RAISE EXCEPTION '%', '@OrgHeaderNotTransAllowed@'; --OBTG:-20000--
      END IF;
      SELECT AD_ORG_CHK_DOCUMENTS('C_ORDER', 'C_ORDERLINE', v_Record_ID, 'C_ORDER_ID', 'C_ORDER_ID') INTO v_is_included FROM dual;
      IF (v_is_included=-1) THEN
        RAISE EXCEPTION '%', '@LinesAndHeaderDifferentLEorBU@'; --OBTG:-20000--
      END IF;

      IF (p_PInstance_ID IS NOT NULL) THEN
        v_ResultStr:='LockingOrder';
        UPDATE C_ORDER  SET Processing='Y'  WHERE C_Order_ID=v_Record_ID;
        -- COMMIT;
        -- Now, needs to go to END_PROCESSING to unlock
      END IF;
      /**
      * Allowed Actions:  AProve, COmplete, PRocess, CLose, VOid
      */
      IF (v_DocAction IN('AP', 'CO', 'PR', 'CL', 'VO')) THEN
        NULL;
      ELSE
        RAISE EXCEPTION '%', '@ActionNotAllowedHere@' ; --OBTG:-20000--
      END IF;

      SELECT COUNT(*)
        INTO v_count
      FROM AD_CLIENTINFO
      WHERE AD_CLIENT_ID=v_Client_ID
        AND CHECKORDERORG='Y';
      IF (v_count > 0) THEN
        v_ResultStr:='CheckingRestrictions - C_ORDER ORG IS IN C_BPARTNER ORG TREE';
        SELECT COUNT(*)
          INTO v_count
        FROM C_ORDER c, C_BPARTNER bp
        WHERE c.C_Order_ID=v_Record_ID
          AND c.C_BPARTNER_ID=bp.C_BPARTNER_ID
          AND Ad_Isorgincluded(c.AD_ORG_ID, bp.AD_ORG_ID, bp.AD_CLIENT_ID)=-1;
        IF (v_count > 0) THEN
          RAISE EXCEPTION '%', '@NotCorrectOrgBpartnerOrder@' ; --OBTG:-20000--
        END IF;
      END IF;

     /**************************************************************************
      * Calculate promotions                                                   
      *************************************************************************/
      IF (v_DocAction = 'CO' AND v_isreturndoctype = 'N' AND v_recalculateDiscounts = 'Y') THEN
         PERFORM M_PROMOTION_CALCULATE('O', v_Record_ID, v_User);
      END IF;
     /**************************************************************************
      * Calculate Discounts
      *************************************************************************/

      -- if sales order was created from quotation with "firm quotation" check
      -- then discounts are not recalculated
      IF (v_recalculateDiscounts = 'Y') THEN
        -- Delete first previous discounts (if possible) and then recalculate them
        UPDATE C_ORDER
        SET DocStatus='IP', -- In progress
            Processing='N',
            Processed='N',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;

        DELETE
        FROM C_ORDERLINE
        WHERE C_ORDER_DISCOUNT_ID IS NOT NULL
          AND C_ORDER_ID = v_Record_ID
          AND NOT EXISTS (SELECT C_INVOICELINE_ID FROM C_INVOICELINE WHERE C_INVOICELINE.C_ORDERLINE_ID = C_ORDERLINE.C_ORDERLINE_ID)
          AND NOT EXISTS (SELECT M_INOUTLINE_ID FROM M_INOUTLINE WHERE M_INOUTLINE.C_ORDERLINE_ID = C_ORDERLINE.C_ORDERLINE_ID)
          AND NOT EXISTS (SELECT M_MATCHPO_ID FROM M_MATCHPO WHERE M_MATCHPO.C_ORDERLINE_ID = C_ORDERLINE.C_ORDERLINE_ID);

        UPDATE C_ORDERLINE
        SET pricelist = 0, priceactual = 0, pricelimit = 0, linenetamt = 0, pricestd = 0
        WHERE C_ORDER_DISCOUNT_ID IS NOT NULL
          AND C_ORDER_ID=v_Record_ID;

        UPDATE C_ORDER
        SET DocStatus=v_DocStatus, -- restore
            Processing=v_IsProcessing,
            Processed=v_IsProcessed,
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;

        v_CumDiscount:=0;
        v_OldCumDiscount:=0;
        v_Line:=10;
        SELECT MAX(LINE)
          INTO v_OrderLineSeqNo
        FROM C_ORDERLINE
        WHERE C_ORDER_ID=v_Record_ID;
        FOR Cur_COrderDiscount IN
           (SELECT C_ORDER_DISCOUNT.C_ORDER_DISCOUNT_ID, C_DISCOUNT.DISCOUNT, C_DISCOUNT.M_PRODUCT_ID, C_DISCOUNT.NAME,
              C_ORDER_DISCOUNT.CASCADE, C_DISCOUNT.C_DISCOUNT_ID, M_PRODUCT.C_UOM_ID
            FROM C_ORDER_DISCOUNT, C_DISCOUNT, M_PRODUCT
            WHERE C_ORDER_DISCOUNT.C_DISCOUNT_ID=C_DISCOUNT.C_DISCOUNT_ID
              AND C_DISCOUNT.M_PRODUCT_ID=M_PRODUCT.M_PRODUCT_ID
              AND C_ORDER_DISCOUNT.C_ORDER_ID=v_Record_ID
              AND C_ORDER_DISCOUNT.ISACTIVE='Y'
              AND C_DISCOUNT.ISACTIVE='Y'
            ORDER BY C_ORDER_DISCOUNT.LINE
            )
        LOOP
          v_CumDiscount:=(1-v_OldCumDiscount) * Cur_COrderDiscount.Discount/100;
          v_OldCumDiscount:=v_OldCumDiscount + v_CumDiscount;
          FOR Cur_TaxDiscount IN
             (SELECT C_ORDERLINE.C_TAX_ID,
                SUM(C_ORDERLINE.LINENETAMT) AS LINENETAMT,
                SUM(C_ORDERLINE.LINE_GROSS_AMOUNT) AS LINEGROSSAMT
              FROM C_ORDERLINE
              WHERE C_ORDER_ID=v_Record_ID
                AND C_ORDERLINE.LINENETAMT<>0
                AND C_ORDER_DISCOUNT_ID IS NULL
              GROUP BY C_TAX_ID
              )
          LOOP
            IF (v_istaxincluded = 'Y') THEN
              IF (Cur_COrderDiscount.CASCADE='Y') THEN
                v_line_gross_amount:=(-1) * Cur_TaxDiscount.LINEGROSSAMT * v_CumDiscount;
              ELSE
                v_line_gross_amount:=(-1) * Cur_TaxDiscount.LINEGROSSAMT * Cur_COrderDiscount.Discount/100;
              END IF;
              v_Discount:= C_GET_NET_AMOUNT_FROM_GROSS(Cur_TaxDiscount.C_TAX_ID, v_line_gross_amount, 0, v_stdPrecision);
              v_gross_unit_price:= v_line_gross_amount;
            ELSE
              IF (Cur_COrderDiscount.CASCADE='Y') THEN
                v_Discount:=(-1) * Cur_TaxDiscount.LINENETAMT * v_CumDiscount;
              ELSE
                v_Discount:=(-1) * Cur_TaxDiscount.LINENETAMT * Cur_COrderDiscount.Discount/100;
              END IF;
              v_gross_unit_price:= 0;
              v_line_gross_amount:= 0;
            END IF;

            SELECT COUNT(*) INTO v_DiscountExist FROM C_ORDERLINE
            WHERE C_ORDERLINE.C_ORDER_DISCOUNT_ID = Cur_COrderDiscount.C_ORDER_DISCOUNT_ID
              AND C_ORDERLINE.C_TAX_ID = Cur_TaxDiscount.C_TAX_ID;

            IF (v_DiscountExist = 0) THEN
              v_OrderLineSeqNo:=10 + v_OrderLineSeqNo;
              v_OrderLine:=get_uuid();
              INSERT INTO c_orderline
                (
                  c_orderline_id, ad_client_id, ad_org_id, isactive, created, createdby,
                  updated, updatedby, c_order_id, line, c_bpartner_id, c_bpartner_location_id,
                  dateordered, datepromised, datedelivered, dateinvoiced, description,
                  m_product_id, m_warehouse_id, directship, c_uom_id, qtyordered,
                  qtyreserved, qtydelivered, qtyinvoiced, m_shipper_id, c_currency_id,
                  pricelist, priceactual, pricelimit, linenetamt, discount, freightamt,
                  c_charge_id, chargeamt, c_tax_id, s_resourceassignment_id, ref_orderline_id,
                  m_attributesetinstance_id, isdescription, quantityorder, m_product_uom_id, aumqty, c_aum,
                  m_offer_id, pricestd, C_ORDER_DISCOUNT_ID,
                  gross_unit_price, taxbaseamt,line_gross_amount
                )
              VALUES
                (
                  v_OrderLine, v_Client_ID, v_Org_ID, 'Y', TO_DATE(NOW()), v_UpdatedBy,
                  TO_DATE(NOW()), v_UpdatedBy, v_Record_ID, v_OrderLineSeqNo, NULL, NULL,
                  TO_DATE(NOW()), TO_DATE(NOW()), TO_DATE(NOW()), TO_DATE(NOW()), Cur_COrderDiscount.NAME,
                  Cur_COrderDiscount.M_PRODUCT_ID, v_M_Warehouse_ID, 'N', Cur_COrderDiscount.C_UOM_ID, 1,
                  0, 0, 0, NULL, v_c_currency_id,
                  ROUND(v_Discount, v_pricePrecision), ROUND(v_Discount, v_pricePrecision), ROUND(v_Discount, v_pricePrecision), ROUND(v_Discount, v_pricePrecision), 0, 0,
                  NULL, NULL, Cur_TaxDiscount.C_TAX_ID, NULL, NULL,
                  NULL, 'N', NULL, NULL, NULL, NULL,
                  NULL, ROUND(v_Discount, v_pricePrecision), NULL,
                  ROUND(v_gross_unit_price, v_pricePrecision), ROUND(v_Discount, v_pricePrecision),ROUND(v_line_gross_amount, v_pricePrecision)
                );

              UPDATE C_ORDERLINE
              SET C_ORDER_DISCOUNT_ID=Cur_COrderDiscount.C_ORDER_DISCOUNT_ID
              WHERE C_ORDERLINE_ID=v_OrderLine;
            ELSE
              UPDATE C_ORDERLINE 
              SET pricelist = ROUND(v_Discount, v_pricePrecision), priceactual = ROUND(v_Discount, v_pricePrecision), pricelimit = ROUND(v_Discount, v_pricePrecision), linenetamt = ROUND(v_Discount, v_pricePrecision), pricestd = ROUND(v_Discount, v_pricePrecision)
              WHERE C_ORDERLINE.C_ORDER_DISCOUNT_ID = Cur_COrderDiscount.C_ORDER_DISCOUNT_ID
                AND C_ORDERLINE.C_TAX_ID = Cur_TaxDiscount.C_TAX_ID;
            END IF;
          END LOOP;
          v_Line:=v_Line + 10;
        END LOOP;
      END IF;

      /**
      * Convert to Target DocType
      */
      DECLARE
        v_DocSubTypeSO_Target VARCHAR(60) ;
      BEGIN
        v_ResultStr:='ConvertingDocType';
        IF (v_DocType_ID <> v_DocTypeTarget_ID) THEN
          -- New
          IF (v_DocStatus='DR' OR v_DocType_ID='0') THEN
            -- Update to Target Document Type
            WHILE(v_DocType_ID<>v_DocTypeTarget_ID)
            LOOP
              BEGIN
                v_ResultStr:='UpdateDocType';
                UPDATE C_ORDER
                SET C_DocType_ID=v_DocTypeTarget_ID,
                    Updated=TO_DATE(NOW()),
                    UpdatedBy=v_User
                WHERE C_Order_ID=v_Record_ID;
                v_DocType_ID:=v_DocTypeTarget_ID;
              EXCEPTION
              WHEN OTHERS THEN
                v_ResultStr:='UpdateDocumentNo';
                UPDATE C_ORDER  SET DocumentNo=DocumentNo || '.'  WHERE C_Order_ID=v_Record_ID;
              END;
            END LOOP;
          ELSE
            v_ResultStr:='GetTargetDocType';
            SELECT DocSubTypeSO
              INTO v_DocSubTypeSO_Target
            FROM C_DOCTYPE
            WHERE C_DocType_ID=v_DocTypeTarget_ID;
            RAISE NOTICE '%','Changing DocType from ' || v_DocSubTypeSO || ' to ' || v_DocSubTypeSO_Target ;
            -- Change Offer to anything, Change InProcess to anything
            IF (v_DocSubTypeSO IN('ON', 'OB') OR v_DocStatus='IP') THEN
              -- Update to Target Document Type
              WHILE(v_DocType_ID<>v_DocTypeTarget_ID)
              LOOP
                BEGIN
                  v_ResultStr:='UpdateDocType';
                  UPDATE C_ORDER
                  SET C_DocType_ID=v_DocTypeTarget_ID,
                      Updated=TO_DATE(NOW()),
                      UpdatedBy=v_User
                  WHERE C_Order_ID=v_Record_ID;
                  v_DocType_ID:=v_DocTypeTarget_ID;
                EXCEPTION
                WHEN OTHERS THEN
                  v_ResultStr:='UpdateDocumentNo';
                  UPDATE C_ORDER  SET DocumentNo=DocumentNo || '.'  WHERE C_Order_ID=v_Record_ID;
                END;
              END LOOP;
            ELSE
              -- Change Back
              UPDATE C_ORDER
                SET C_DocTypeTarget_ID=v_DocType_ID
              WHERE C_Order_ID=v_Record_ID;
              RAISE EXCEPTION '%', '@CannotChangeDocType@' ; --OBTG:-20000--
            END IF;
          END IF;
        END IF; -- C_DocType_ID <> C_DocTypeTarget_ID
      END; -- Conversion

    /**
      * Get DocSubTypeSO + Is it Binding :1:2
      */
      v_ResultStr:='TestBinding DocType_ID=' || v_DocType_ID;
      SELECT CASE DocSubTypeSO WHEN 'ON' THEN 'N' ELSE 'Y' END, DocSubTypeSO
        INTO v_IsBinding, v_DocSubTypeSO
      FROM C_DOCTYPE
      WHERE C_DocType_ID=v_DocType_ID;
      RAISE NOTICE '%','DocSubTypeSO=' || v_DocSubTypeSO || ' IsBinding=' || v_IsBinding ;

    /**************************************************************************
      * Resolve not-stocked BOMs
      *************************************************************************/
      DECLARE
        -- Order Lines with non-stocked BOMs
        CUR_BOM_Line RECORD;
      BEGIN
        FOR CUR_BOM_Line IN
          (SELECT l.c_orderline_id
           FROM C_ORDERLINE l
           WHERE l.C_Order_ID=v_Record_ID
             AND l.IsActive='Y'
             AND l.explode='N'
             AND EXISTS
               (SELECT *
                FROM M_PRODUCT p
                WHERE l.M_Product_ID=p.M_Product_ID
                  AND p.IsBOM='Y'
                  AND p.IsStocked='N'
                )
              ORDER BY l.Line
             )
        LOOP
          PERFORM M_EXPLODEBOMNOTSTOCK(null, CUR_BOM_Line.c_orderline_ID);
        END LOOP;
      END;
    /**************************************************************************
      * Always check and (un) Reserve Inventory  (counterpart: M_InOut_Post)
      *************************************************************************/
      IF (v_DocAction<>'CL') THEN
        DECLARE
          Cur_ResLine RECORD;

          v_QtySO       NUMERIC; -- Reserved
          v_QtyOrderSO  NUMERIC;
          v_QtyPO       NUMERIC; -- Ordered
          v_QtyOrderPO  NUMERIC;
          v_UOM_ID      VARCHAR(32); --OBTG:VARCHAR2--
        BEGIN
          v_ResultStr := 'ReserveInventory';
          -- For all lines needing reservation
          FOR Cur_ResLine IN (SELECT l.M_Warehouse_ID, l.M_Product_ID, l.M_AttributeSetInstance_ID, l.C_OrderLine_ID,
                -- Target Level = 0 if DirectShip='Y' or Binding='N'
                (CASE l.DirectShip WHEN 'Y' THEN 0 ELSE (CASE v_IsBinding WHEN 'N' THEN 0 ELSE l.QtyOrdered END) END)
                 -l.QtyReserved-l.QtyDelivered AS Qty, l.QUANTITYORDER,
                l.QtyReserved, l.QtyDelivered, l.DatePromised, l.C_UOM_ID, l.C_AUM,
                l.M_PRODUCT_UOM_ID
              FROM C_ORDERLINE l, M_PRODUCT p
              WHERE l.C_Order_ID=v_Record_ID
                -- Reserve Products (not: services, null products) --
                AND l.M_Product_ID=p.M_Product_ID
                AND p.IsStocked='Y' AND p.ProductType='I'
                -- Target Level = 0 if DirectShip='Y' or Binding='N'
                AND (CASE l.DirectShip WHEN 'Y' THEN 0 ELSE (CASE v_IsBinding WHEN 'N' THEN 0 ELSE l.QtyOrdered END)END)
                -l.QtyReserved-l.QtyDelivered <> 0)
          LOOP
          
            -- Qty corrected for SO/PO
            IF (v_DocSubTypeSO IS NOT NULL) THEN
              v_QtySO   := Cur_ResLine.Qty;
              v_QtyOrderSO := NULL;
              IF (Cur_ResLine.QtyReserved = 0 AND Cur_ResLine.QtyDelivered = 0) THEN
                v_QtyOrderSO := Cur_ResLine.QuantityOrder;
              ELSIF (Cur_ResLine.C_AUM IS NULL AND Cur_ResLine.M_Product_UOM_ID IS NOT NULL) THEN
                SELECT c_uom_id
                INTO v_UOM_ID
                FROM m_product_uom
                WHERE m_product_uom_id = Cur_ResLine.M_Product_UOM_ID;
                v_QtyOrderSO := C_Uom_Convert(v_QtySO, Cur_ResLine.C_UOM_ID, v_UOM_ID, 'Y');
              END IF;
              v_QtyPO   := 0;
              v_QtyOrderPO := NULL;
            ELSE -- PO
              v_QtySO := 0;
              v_QtyOrderSO := NULL;
              v_QtyPO := Cur_ResLine.Qty;
              v_QtyOrderPO := NULL;
              IF (Cur_ResLine.QtyReserved = 0 AND Cur_ResLine.QtyDelivered = 0) THEN
                v_QtyOrderPO := Cur_ResLine.QuantityOrder;
              ELSIF (Cur_ResLine.C_AUM IS NULL AND Cur_ResLine.M_Product_UOM_ID IS NOT NULL) THEN
                SELECT c_uom_id
                INTO v_UOM_ID
                FROM m_product_uom
                WHERE m_product_uom_id = Cur_ResLine.M_Product_UOM_ID;
                v_QtyOrderPO := C_Uom_Convert(v_QtyPO, Cur_ResLine.C_UOM_ID, v_UOM_ID, 'Y');
              END IF;
            END IF;
            IF ((v_DocStatus<>'IP' OR v_DocAction<>'CO') AND COALESCE(v_DocSubTypeSO, '')<>'OB') THEN
              PERFORM M_UPDATE_STORAGE_PENDING(v_Client_ID, v_Org_ID, v_UpdatedBy, Cur_ResLine.M_Product_ID, Cur_ResLine.M_Warehouse_ID, Cur_ResLine.M_AttributeSetInstance_ID,
              Cur_ResLine.C_UOM_ID, Cur_ResLine.M_PRODUCT_UOM_ID, v_QtySO, v_QtyOrderSO, v_QtyPO, v_QtyOrderPO);
            END IF;
            RAISE NOTICE '%','Reserved Warehouse=' || Cur_ResLine.M_Warehouse_ID || ', Product=' || Cur_ResLine.M_Product_ID || ', Attrib=' || Cur_ResLine.M_AttributeSetInstance_ID || ', Qty=' || v_QtySO || '/' || v_QtyPO;

            -- Update Order Line
            IF (v_DocSubTypeSO IS NOT NULL) THEN
              UPDATE C_ORDERLINE
              SET QtyReserved = QtyReserved + v_QtySO
              WHERE C_OrderLine_ID = Cur_ResLine.C_OrderLine_ID;
            END IF;
            GET DIAGNOSTICS  rowcount:=ROW_COUNT;
            IF (rowcount <> 1) THEN
              IF (p_PInstance_ID IS NOT NULL) THEN
                -- ROLLBACK;
                v_ResultStr := 'LockingOrder';
                UPDATE C_ORDER
                SET Processing = 'N',
                Updated=TO_DATE(NOW()),
                UpdatedBy=v_User
                WHERE C_Order_ID = v_Record_ID;
                RAISE EXCEPTION '%','DATA_EXCEPTION';
                -- COMMIT;
              END IF;
              RAISE EXCEPTION '%', '@20011@'; --OBTG:-20000--
            END IF;
          END LOOP; -- For all lines needing reservation
        END;
      END IF; -- Reserve Inventory

     /**************************************************************************
      * Stock Reservations management.
      *************************************************************************/
      SELECT COUNT(1) INTO v_count
      FROM ad_preference
      WHERE property = 'StockReservations';
      IF (v_count > 0) THEN
        IF (v_count > 1) THEN
          v_dummy := AD_GET_PREFERENCE_VALUE('StockReservations', 'Y', v_client_id, v_org_id, NULL, NULL, NULL);
        END IF;
        DECLARE
          v_reservation_id      VARCHAR(32); --OBTG:VARCHAR2--
          v_quantity            NUMERIC;
          v_reservedqty         NUMERIC;
          v_releasedqty         NUMERIC;
          v_allocated           NUMERIC;
          v_pendingtounreserve  NUMERIC;
          v_qtyaux              NUMERIC;
          v_res_status          M_RESERVATION.RES_STATUS%TYPE;
          v_linecount           NUMERIC;
          v_creservedcount      NUMERIC;
          v_preservedcount      NUMERIC;
          
          cur_res_stock         RECORD;
        BEGIN
          IF (v_issotrx = 'Y') THEN
            FOR cur_orderline IN (
                SELECT ol.c_orderline_id, ol.create_reservation, ol.qtyordered
                FROM c_orderline ol
                    JOIN m_product p ON ol.m_product_id = p.m_product_id
                    LEFT JOIN m_reservation r ON ol.c_orderline_id = r.c_orderline_id
                WHERE ol.c_order_id = v_record_id
                  AND ((
                      ol.qtyordered > 0
                      AND p.isstocked = 'Y'
                      AND p.producttype = 'I'
                    ) OR (
                      r.m_reservation_id IS NOT NULL
                    )
                  )
            ) LOOP
              SELECT count(*), max(m_reservation_id)
              INTO v_aux, v_reservation_id
              FROM m_reservation
              WHERE c_orderline_id = cur_orderline.c_orderline_id
              AND res_status <> 'CL';
              -- Initialize so_res_status
              UPDATE c_orderline
              SET so_res_status = 'NR'
              WHERE c_orderline_id = cur_orderline.c_orderline_id;
              IF (v_aux > 1) THEN
                RAISE EXCEPTION '%', '@SOLineWithMoreThanOneOpenReservation@'; --OBTG:-20000--
              ELSIF (v_aux = 1) THEN
                -- Update reservation when possible.
                -- Read reservation.
                SELECT r.quantity, r.reservedqty, r.releasedqty, r.res_status,
                      COALESCE(SUM(CASE rs.isallocated WHEN 'Y' THEN rs.quantity - COALESCE(rs.releasedqty, 0) ELSE 0 END), 0)
                  INTO v_quantity, v_reservedqty, v_releasedqty, v_res_status,
                      v_allocated
                FROM m_reservation r
                    LEFT JOIN m_reservation_stock rs ON r.m_reservation_id = rs.m_reservation_id
                WHERE r.m_reservation_id = v_reservation_id
                GROUP BY r.quantity, r.reservedqty, r.releasedqty, r.res_status;
                IF (v_quantity != cur_orderline.qtyordered) THEN
                  IF (v_allocated <> 0) THEN
                    RAISE EXCEPTION '%', '@ThereIsMoreAllocatedQtyThanOrdered@'; --OBTG:-20000--
                  END IF;
                  IF (cur_orderline.qtyordered < v_releasedqty) THEN
                    RAISE EXCEPTION '%', '@CannotOrderLessThanReleasedQty@'; --OBTG:-20000--
                  END IF;
                  IF (cur_orderline.qtyordered < v_reservedqty) OR (v_releasedqty = 0 AND cur_orderline.qtyordered > 0) THEN
                    --Reservation quantity to decrease with more reserved quantity than new quantity, unreserve stock
                    v_pendingtounreserve := v_reservedqty - cur_orderline.qtyordered;
                    FOR cur_res_stock IN (
                        SELECT m_reservation_stock_id, quantity - COALESCE(releasedqty, 0) AS reservedqty
                        FROM m_reservation_stock
                        WHERE m_reservation_id = v_reservation_id
                        ORDER BY COALESCE(releasedqty, 0), quantity - COALESCE(releasedqty, 0)
                    ) LOOP
                      v_qtyaux := LEAST(v_pendingtounreserve, cur_res_stock.reservedqty);
                      UPDATE m_reservation_stock
                      SET quantity = quantity - v_qtyaux,
                          updated = TO_DATE(NOW()),
                          updatedby = v_user
                      WHERE m_reservation_stock_id = cur_res_stock.m_reservation_stock_id;
                      v_reservedqty := v_reservedqty - v_qtyaux;
                      v_pendingtounreserve := v_pendingtounreserve - v_qtyaux;
                      IF (v_pendingtounreserve = 0) THEN
                        EXIT;
                      END IF;
                    END LOOP;
                    IF (v_pendingtounreserve > 0) THEN
                      RAISE EXCEPTION '%', '@CouldNotUnreserveNeededQty@'; --OBTG:-20000--
                    END IF;
                    -- Delete reservation lines with zero releasedqty
                    DELETE FROM m_reservation_stock
                    WHERE COALESCE(releasedqty, 0) = 0
                      AND m_reservation_id = v_reservation_id;
                  END IF;
                  -- Order line orderedqty greater than reservation releasedqty
                  IF (cur_orderline.qtyordered > v_releasedqty) THEN
                    UPDATE m_reservation_stock
                    SET quantity = releasedqty,
                        updated = TO_DATE(NOW()),
                        updatedby = v_user
                    WHERE m_reservation_id = v_reservation_id;
                  END IF;
                  UPDATE m_reservation
                  SET quantity = cur_orderline.qtyordered,
                      res_status = 'CL',
                      updated = TO_DATE(NOW()),
                      updatedby = v_user
                  WHERE m_reservation_id = v_reservation_id;
                  IF (v_quantity < cur_orderline.qtyordered AND v_res_status = 'CO') THEN
                    --Reservation processed with higher quantity. Try to reserve the new quantity.
                    SELECT * INTO  v_reservedqty FROM M_RESERVE_STOCK_AUTO(v_reservation_id, v_user);
                  END IF;
                END IF;
                
                IF (v_res_status <> 'DR') THEN
                  -- Update so_res_status
                  UPDATE c_orderline
                  SET so_res_status = CASE WHEN cur_orderline.qtyordered = v_reservedqty THEN 'CR'
					   WHEN cur_orderline.qtyordered > v_reservedqty AND v_reservedqty > 0 THEN 'PR'
                                           ELSE 'NR'
                                      END
                  WHERE c_orderline_id = cur_orderline.c_orderline_id;
                END IF;
              ELSE
                SELECT count(*)
                INTO v_aux
                FROM dual
                WHERE EXISTS (
                  SELECT 1
                  FROM m_reservation
                  WHERE c_orderline_id = cur_orderline.c_orderline_id
                );
                IF (v_aux = 0 AND cur_orderline.create_reservation = 'CRP') THEN
                  SELECT * INTO  v_reservation_id FROM M_CREATE_RESERVE_FROM_SOL(cur_orderline.c_orderline_id, 'Y', v_user);
                ELSIF (v_aux = 0 AND cur_orderline.create_reservation = 'CR') THEN
                  SELECT * INTO  v_reservation_id FROM M_CREATE_RESERVE_FROM_SOL(cur_orderline.c_orderline_id, 'N', v_user);
                END IF;
              END IF;
            END LOOP;
            SELECT COUNT(*), SUM(CASE ol.so_res_status WHEN 'CR' THEN 1 ELSE 0 END), SUM(CASE ol.so_res_status WHEN 'PR' THEN 1 ELSE 0 END)
              INTO v_linecount, v_creservedcount, v_preservedcount
            FROM c_orderline ol
                JOIN m_product p ON ol.m_product_id = p.m_product_id
            WHERE ol.c_order_id = v_record_id
              AND ol.qtyordered > 0
              AND p.isstocked = 'Y'
              AND p.producttype = 'I';
            UPDATE c_order
            SET so_res_status = CASE WHEN v_linecount = v_creservedcount THEN 'CR'
                                     WHEN v_creservedcount + v_preservedcount > 0 THEN 'PR'
                                     ELSE 'NR'
                                END
            WHERE c_order_id = v_record_id;
          END IF;
        END;
      END IF;
      
      -- Synchronize Client/Org Ownership
      UPDATE C_ORDERLINE
      SET AD_Client_ID=v_Client_ID
      WHERE C_Order_ID=v_Record_ID
        AND (AD_Client_ID<>v_Client_ID) ;

      IF (v_docaction = 'CO' AND v_issotrx = 'N') THEN
          UPDATE m_transaction
          SET checkpricedifference = 'Y'
          WHERE m_transaction_id IN (
            SELECT trx.m_transaction_id 
            FROM c_orderline  ol
                 JOIN m_matchpo mpo ON mpo.c_orderline_id = ol.c_orderline_id
                 JOIN m_transaction trx ON mpo.m_inoutline_id = trx.m_inoutline_id
            WHERE trx.iscostcalculated = 'Y' AND  ol.c_order_id = v_record_id);
      END IF;

    /**************************************************************************
     * Order Complete:5 - Something to do:6
     */
      BEGIN
        v_ResultStr:='OrderCompleteCheck';
        SELECT COUNT(*) INTO ToDeliverOrToInvoice FROM DUAL
        WHERE 0 <> ANY (select QtyOrdered - QtyDelivered from c_orderline where c_order_id = v_Record_ID)
        OR    0 <> ANY (select QtyOrdered - QtyInvoiced  from c_orderline where c_order_id = v_Record_ID);
        -- If something to deliver or to invoice, then ToDeliverOrToInvoice = 1
        IF (ToDeliverOrToInvoice = 0) THEN
          RAISE NOTICE '%','OrderComplete' ;
          IF (v_DocAction='CL') THEN
            END_PROCESSING:=TRUE;
          ELSIF (v_DocAction='VO') THEN
            UPDATE C_ORDER
            SET DocStatus='VO',
                DocAction='--',
                Processed='Y',
                Updated=TO_DATE(NOW()),
                UpdatedBy=v_User
            WHERE C_Order_ID=v_Record_ID;
            END_PROCESSING:=TRUE;
          ELSE
            UPDATE C_ORDER
            SET DocStatus='CO',
                DocAction='--',
                Processed='Y',
                Updated=TO_DATE(NOW()),
                UpdatedBy=v_User
            WHERE C_Order_ID=v_Record_ID;
            END_PROCESSING:=TRUE;
          END IF;
          IF (NOT END_PROCESSING) THEN
            RAISE EXCEPTION '%', '@AlreadyPosted@'; --OBTG:-20000--
          END IF;--END_PROCESSING
        END IF;
      END;
    END IF;--FINISH_PROCESS
    IF (NOT FINISH_PROCESS AND NOT END_PROCESSING) THEN
     /**
      * In Progress -----------------------------------------------------------
      */
      UPDATE C_ORDER
      SET DocStatus='IP',
          DateAcct=DateOrdered,
          Updated=TO_DATE(NOW()),
          UpdatedBy=v_User
      WHERE C_Order_ID=v_Record_ID;
      IF (p_PInstance_ID IS NOT NULL) THEN
        -- COMMIT;
      END IF;

    /**
      * Finished with processing
      */
      IF (v_DocAction='PR') THEN
        v_ResultStr:='FinishProcessing';
        UPDATE C_ORDER
        SET DocStatus='IP',
            DocAction='CO',
            Processed='N',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
        -- C_Order_PickList(NULL, v_Record_ID);  -- Print PickList
        END_PROCESSING:=TRUE;
      END IF;
    END IF;--FINISH_PROCESS
    IF (NOT FINISH_PROCESS AND NOT END_PROCESSING) THEN
    /**************************************************************************
      * Prepayment Order  Create Invoice
      *************************************************************************/
      IF (v_DocSubTypeSO='PR' AND v_DocStatus<>'WP') THEN
        RAISE NOTICE '%','Create PreInvoice - ' || v_Record_ID ;
        v_ResultStr:='CreatePreInvoice';
        SELECT * INTO  Invoice_ID FROM C_Invoice_Create(NULL, v_Record_ID) ;
        RAISE NOTICE '%','  PreInvoice - ' || Invoice_ID ;
        IF (Invoice_ID='0') THEN
          RAISE EXCEPTION '%', '@PreInvoiceCreateFailed@'; --OBTG:-20000--
        END IF;
        PERFORM C_INVOICE_POST(NULL, Invoice_ID) ;
        --
        UPDATE C_ORDER
        SET DocStatus='WP',
            DocAction='--',
            Processed='Y',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
        --
        END_PROCESSING:=TRUE;
      END IF;
      IF (NOT END_PROCESSING) THEN
       /**
        * Deliver Direct Shipments
        */
        v_ResultStr:='NonInventoryDelivery';
        UPDATE C_ORDERLINE
        SET QtyDelivered=QtyOrdered
        WHERE DirectShip='Y'
          AND C_Order_ID=v_Record_ID;
      END IF;--END_PROCESSING
    END IF;--FINISH_PROCESS
    IF (NOT FINISH_PROCESS AND NOT END_PROCESSING) THEN
     /**************************************************************************
      * Will-Call + Walk In Processing
      * --
      * (W)illCall(I)nvoice - (W)illCall(P)ickup - (W)alkIn(R)eceipt
      * --
      *************************************************************************/
      IF (v_DocSubTypeSO IN('WI', 'WP', 'WR')) THEN
       /************
        * Shipment
        */
        RAISE NOTICE '%','Create Shipment - ' || v_Record_ID ;
        v_ResultStr:='CreateShipment';

        SELECT * INTO  InOut_ID FROM M_Inout_Create(NULL, v_Record_ID, NULL, 'Y') ; -- Force Delivery

        RAISE NOTICE '%','  Shipment - ' || InOut_ID ;
        IF (InOut_ID='0') THEN
          RAISE EXCEPTION '%', '@InOutCreateFailed@'; --OBTG:-20000--
        ELSE
          SELECT documentno
            INTO v_DocumentNo
          FROM M_INOUT
          WHERE M_INOUT_ID = InOut_ID;
          v_Message:='@InoutDocumentno@ ' || v_DocumentNo || ' @beenCreated@';
        END IF;
        IF (v_DocSubTypeSO IN('WI', 'WR')) THEN
         /************
          * Invoice
          */
          RAISE NOTICE '%','Create Invoice - ' || v_Record_ID ;
          v_ResultStr:='CreateInvoice';
          SELECT * INTO  Invoice_ID FROM C_Invoice_Create(NULL, v_Record_ID) ;
          RAISE NOTICE '%','  Invoice - ' || Invoice_ID ;
          IF (Invoice_ID IS NULL OR Invoice_ID='0') THEN
            RAISE EXCEPTION '%', '@InvoiceCreateFailed@'; --OBTG:-20000--
          ELSE
            SELECT documentno
              INTO v_DocumentNo
            FROM C_INVOICE
            WHERE C_INVOICE_ID = Invoice_ID;
            v_Message:=v_Message||' , '||'@InvoiceDocumentno@ ' || v_DocumentNo || ' @invbeenCreated@';
          END IF;
        END IF;
      END IF;

     /**
      * Final Completeness check
      */
      SELECT COUNT(*) INTO ToDeliver FROM DUAL
      WHERE 0 <> ANY (select QtyOrdered - QtyDelivered from c_orderline where c_order_id = v_Record_ID);
      SELECT COUNT(*) INTO ToInvoice FROM DUAL
      WHERE 0 <> ANY (select QtyOrdered - QtyInvoiced from c_orderline where c_order_id = v_Record_ID);
      RAISE NOTICE '%','To deliver - ' || ToDeliver ;
      RAISE NOTICE '%','ToInvoice - ' || ToInvoice ;
      RAISE NOTICE '%','v_DocSubTypeSO - ' || v_DocSubTypeSO ;
      -- Nothing to Deliver + Invoice for (W)illCall(I)nvoice and (W)alkIn(R)eceipt
      IF (v_DocSubTypeSO IN ('WI', 'WR') AND ToDeliver=0 AND ToInvoice=0) THEN
        UPDATE C_ORDER
        SET DocStatus='CO',
            DocAction='--',
            IsDelivered='Y',
            IsInvoiced='Y',
            Processed='Y',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
        RAISE NOTICE '%','DocAction - ' || v_DocAction ;
        IF (v_DocAction='VO') THEN
          UPDATE C_ORDER  SET DocStatus='VO'  WHERE C_Order_ID=v_Record_ID;
        END IF;
      END IF;
      -- Nothing to Deliver for (W)illCall(P)ickup (Invoice generated independently)
      IF (v_DocSubTypeSO='WP' AND ToDeliver=0) THEN
        UPDATE C_ORDER
        SET DocStatus='CO',
            DocAction='--',
            IsDelivered='Y',
            Processed='Y',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
        IF (v_DocAction='VO') THEN
          UPDATE C_ORDER  SET DocStatus='VO'  WHERE C_Order_ID=v_Record_ID;
        END IF;
      END IF;
      -- We are done with standard sales orders
      IF (v_DocSubTypeSO = 'RM' OR v_isreturndoctype = 'Y') THEN

        FOR Cur_Order IN( SELECT ol.qtyordered, ol.c_order_discount_id FROM C_order o,C_orderline ol
            WHERE o.C_Order_ID = v_Record_ID
              AND ol.C_Order_ID = o.C_Order_ID)
        LOOP
          IF (Cur_Order.qtyordered >0 AND Cur_Order.c_order_discount_id IS NULL) THEN 
            RAISE EXCEPTION '%', '@ReturnMaterialOrderType@' ; --OBTG:-20000--
          END IF;
        END LOOP;
      END IF;
      IF (v_DocAction IN('CO', 'CL', 'VO') AND v_DocSubTypeSO IN('SO','RM')) THEN
        UPDATE C_ORDER
        SET DocStatus='CO',
            DocAction='--',
            Processed='Y',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
      END IF;
      -- Purchase Orders
      IF (v_DocAction IN('CO', 'CL', 'VO') AND v_DocSubTypeSO IS NULL) THEN
        UPDATE C_ORDER
        SET DocStatus='CO',
            DocAction='--',
            Processed='Y',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
      END IF;
      IF (v_DocAction IN('CO') AND v_DocSubTypeSO IN('OB')) THEN
        UPDATE C_ORDER
        SET DocStatus='UE',
            DocAction='--',
            Processed='Y',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
      END IF;
      -- Only create cash entry if docAction is Complete
      IF (v_DocAction NOT IN('CO')) THEN
        END_PROCESSING:=TRUE;
      END IF;
    END IF;--FINISH_PROCESS
    IF (NOT FINISH_PROCESS AND NOT END_PROCESSING) THEN
     /**************************************************************************
      * Create default Cash entry
      *************************************************************************/
      DECLARE
        v_PaymentRule VARCHAR(60) ;
        CUR_CB RECORD;
        v_debtPaymentID VARCHAR(32); --OBTG:varchar2--
        v_totalCash NUMERIC;
        v_CB_Curr VARCHAR(32); --OBTG:varchar2--
      BEGIN
       /* ALO
        */
        UPDATE C_DEBT_PAYMENT  SET IsValid='Y'  WHERE C_Order_ID=v_Record_ID;
        SELECT C_ORDER.PAYMENTRULE,
            (CASE 
		WHEN (length(C_ORDER.DOCUMENTNO||' - '||C_BPARTNER.NAME||' - '||C_ORDER.GRANDTOTAL) > 200) 
			THEN substr(C_ORDER.DOCUMENTNO||' - '||C_BPARTNER.NAME||' - '||C_ORDER.GRANDTOTAL,1,197)||'...'
		ELSE 
			C_ORDER.DOCUMENTNO||' - '||C_BPARTNER.NAME||' - '||C_ORDER.GRANDTOTAL
	    END) AS CONCATENATION,
            C_ORDER.GRANDTOTAL,
            C_ORDER.M_WAREHOUSE_ID
          INTO v_PaymentRule,
            v_DocumentNo,
            v_GrandTotal,
            v_M_Warehouse_ID
        FROM C_ORDER, C_BPARTNER
        WHERE C_ORDER.C_BPARTNER_ID=C_BPARTNER.C_BPARTNER_ID
          AND C_ORDER_ID=v_Record_ID;
        SELECT MAX(NAME)
          INTO v_WarehouseName
        FROM M_WAREHOUSE
        WHERE M_WAREHOUSE_ID=v_M_Warehouse_ID;

        --PaymentRule C, WI and WR have already created DP, this won't be inserted in cashline
        --because it will processed with the invoice.
        IF (v_PaymentRule='C') AND (v_DocSubTypeSO NOT IN ('WI', 'WR') OR v_DocSubTypeSO IS NULL) THEN
          IF (v_CashLine_ID IS NULL OR v_CashLine_ID='0') THEN
            -- Create CashLine
            -- Find Defaylt CashBook
            v_ResultStr:='Find C_CashBook Org_ID=' || v_Org_ID;
            BEGIN
              -- First active default Book of Org
              FOR CUR_CB IN
                 (SELECT cb.C_CashBook_ID, c.ISO_Code, cb.NAME, cb.c_currency_id
                  FROM C_CASHBOOK cb, C_CURRENCY c
                  WHERE cb.AD_Org_ID=v_Org_ID
                    AND cb.C_Currency_ID=c.C_Currency_ID
                    AND cb.IsActive='Y'
                  ORDER BY cb.IsDefault DESC
                 )
              LOOP
                IF (v_CashBook_ID IS NULL) THEN
                  v_CashBook_ID:=CUR_CB.C_CashBook_ID;
                  v_ISO_Code:=CUR_CB.ISO_Code;
                  v_CB_Curr:=CUR_CB.C_Currency_ID;
                ELSIF (CUR_CB.NAME=v_WarehouseName) THEN
                  v_CashBook_ID:=CUR_CB.C_CashBook_ID;
                  v_ISO_Code:=CUR_CB.ISO_Code;
                  v_CB_Curr:=CUR_CB.C_Currency_ID;
                END IF;
              END LOOP;
            END;
            IF (v_CashBook_ID IS NULL) THEN
              RAISE EXCEPTION '%', '@CashBookPRSCnotfoundOrg@' || v_Org_Name ; --OBTG:-20000--
            END IF;
            RAISE NOTICE '%','CashBook_ID=' || v_CashBook_ID ;
            -- Find/Create Cash Journal
            v_ResultStr:='Find C_Cash for ' || v_Date;
            DECLARE
              Cur_CashId RECORD;
            BEGIN
              FOR Cur_CashId IN
                 (SELECT C_Cash_ID AS Cash_ID
                  FROM C_CASH
                  WHERE C_CashBook_ID=v_CashBook_ID
                    AND TRUNC(StatementDate)=v_Date
                    AND Processed='N'
                 )
              LOOP
                v_Cash_ID:=Cur_CashId.Cash_ID;
                EXIT;
              END LOOP;
            EXCEPTION
              WHEN DATA_EXCEPTION THEN
                NULL;
            END;
           /**************************************************************************
            * Credit Multiplier
            *************************************************************************/
            DECLARE
              v_DocBaseType C_DOCTYPE.DocBaseType%TYPE;
            BEGIN
              -- Is it a Credit Memo?
              SELECT DocBaseType
                INTO v_DocBaseType
              FROM C_DOCTYPE
              WHERE C_DocType_ID=v_DocType_ID;
              IF (v_DocBaseType IN('ARC', 'API')) THEN
                v_Multiplier:=-1;
              END IF;
            END;

            IF (v_Cash_ID IS NULL) THEN
              v_ResultStr:='Create C_Cash';
              SELECT * INTO  v_Cash_ID FROM Ad_Sequence_Next('C_Cash', v_Org_ID) ;
              INSERT
              INTO C_CASH
                (
                  C_Cash_ID, AD_Client_ID, AD_Org_ID, IsActive,
                  Created, CreatedBy, Updated, UpdatedBy,
                  C_CashBook_ID, NAME, StatementDate, DateAcct,
                  BeginningBalance, EndingBalance, StatementDifference, Processing,
                  Processed, Posted
                )
              VALUES
                (
                  v_Cash_ID, v_Client_ID, v_Org_ID, 'Y',
                  TO_DATE(NOW()), v_UpdatedBy, TO_DATE(NOW()), v_UpdatedBy,
                  v_CashBook_ID, TO_CHAR(v_Date, 'YYYY-MM-DD') || ' ' || v_ISO_Code, v_Date, v_Date,
                  0, 0, 0, 'N',
                  'N', 'N'
                )
              ;
            END IF;
           /*
            Create a debt payment for paymentrule=C
            Note: for WI and WR we have already created an invoice and its DP, we only have to link it
            */
            SELECT COALESCE(SUM(C_Currency_Round(C_Currency_Convert((Amount + WriteOffAmt), C_Currency_ID, v_CB_Curr, v_Date, NULL, v_Client_ID, v_Org_ID), v_c_Currency_ID, NULL)), 0)
              INTO v_totalCash
            FROM C_DEBT_PAYMENT_V dp
            WHERE C_Order_ID=v_Record_ID;

            SELECT * INTO  v_debtPaymentID FROM Ad_Sequence_Next('C_Debt_Payment', v_Record_ID) ;
            INSERT INTO C_DEBT_PAYMENT
               (C_DEBT_PAYMENT_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE,
                CREATED, CREATEDBY, UPDATED, UPDATEDBY,
                ISRECEIPT, C_SETTLEMENT_CANCEL_ID, C_SETTLEMENT_GENERATE_ID, DESCRIPTION,
                C_ORDER_ID, C_BPARTNER_ID, C_CURRENCY_ID, C_CASHLINE_ID,
                C_BANKACCOUNT_ID, C_CASHBOOK_ID, PAYMENTRULE, ISPAID,
                AMOUNT, WRITEOFFAMT, DATEPLANNED, ISMANUAL,
                ISVALID, C_BANKSTATEMENTLINE_ID, CHANGESETTLEMENTCANCEL, CANCEL_PROCESSED,
                GENERATE_PROCESSED, c_project_id,IsAutomaticGenerated, STATUS_INITIAL)
            VALUES
               (v_debtPaymentID, v_Client_ID, v_Org_ID, 'Y',
                TO_DATE(NOW()), v_UpdatedBy, TO_DATE(NOW()), v_UpdatedBy,
                v_isSoTrx, NULL, NULL, '',
                v_Record_ID, v_c_Bpartner_Id, v_c_currency_id, null,
                NULL, v_CashBook_ID, 'C', 'N',
                (v_GrandTotal-v_totalCash), 0, v_Date, 'N',
                'Y', NULL, 'N', 'N',
                'N', v_C_PROJECT_ID,'Y', 'DE');

            RAISE NOTICE '%','  Cash_ID=' || v_Cash_ID ;
            -- Create CashJournal Line in invoice currency
            v_ResultStr:='Create C_CashLine';
            SELECT * INTO  v_CashLine_ID FROM Ad_Sequence_Next('C_CashLine', v_Org_ID) ;

            SELECT COALESCE(MAX(Line), 0) +10
              INTO v_Line
            FROM C_CASHLINE
            WHERE C_Cash_ID=v_Cash_ID;
            --
            INSERT
            INTO C_CASHLINE
              (
                C_CashLine_ID, AD_Client_ID, AD_Org_ID, IsActive,
                Created, CreatedBy, Updated, UpdatedBy,
                C_Cash_ID, C_Debt_Payment_ID, Line, Description,
                Amount, CashType, DiscountAmt, WriteOffAmt,
                IsGenerated
              )
            VALUES
              (
                v_CashLine_ID, v_Client_ID, v_Org_ID, 'Y',
                TO_DATE(NOW()), v_UpdatedBy, TO_DATE(NOW()), v_UpdatedBy,
                v_Cash_ID, v_debtPaymentID, v_Line, v_DocumentNo,
                (v_GrandTotal-v_totalCash) * (CASE WHEN v_isSoTrx='N' THEN -1 ELSE 1 END), 'P', 0, 0,
                'Y'
              )
            ;
            RAISE NOTICE '%','  CashLine_ID=' || v_CashLine_ID ;

          END IF; -- CashLine_ID IS NULL OR CashLine_ID = '0'
        END IF; -- v_PaymentRule = 'C'
      END;
    END IF;--FINISH_PROCESS
    IF (NOT FINISH_PROCESS) THEN
      -- End Processing --------------------------------------------------------
      ---- <<END_PROCESSING>>
      -- Cloase Order
      IF (v_DocAction='CL') THEN
        UPDATE C_ORDER
        SET DocStatus='CL',
            DocAction='--',
            Processed='Y'
        WHERE C_Order_ID=v_Record_ID;
      END IF;
    END IF;--FINISH_PROCESS

    -- Round and Adjust taxes when 'CO' and Recalculate amounts and taxes when 'RE'
    IF (v_isTaxIncluded = 'Y' AND v_DocAction IN ('CO', 'RE')) THEN
      PERFORM C_ORDERTAX_ADJUSTMENT(v_Record_ID, v_stdPrecision, v_DocAction);
    END IF;

    --C_Order_Post - Finish_Process Extension Point
    SELECT count(*) INTO v_count
    FROM DUAL
    where exists (select 1 from ad_ep_procedures where ad_extension_points_id = 'CB68FC0E8A4547D9943C785761977E77');
    IF (v_count=1) THEN
      DECLARE
        v_ep_instance VARCHAR(32); --OBTG:VARCHAR2--
        v_extension_point_id VARCHAR(32) := 'CB68FC0E8A4547D9943C785761977E77'; --OBTG:VARCHAR2--
      BEGIN
        v_ep_instance := get_uuid();
        PERFORM AD_EP_INSTANCE_PARA_INSERT(v_ep_instance, v_extension_point_id, 'Record_ID',
          v_record_id, NULL, NULL, NULL, NULL, NULL, NULL);
        PERFORM AD_EP_INSTANCE_PARA_INSERT(v_ep_instance, v_extension_point_id, 'DocAction',
          v_DocAction, NULL, NULL, NULL, NULL, NULL, NULL);
        PERFORM AD_EP_INSTANCE_PARA_INSERT(v_ep_instance, v_extension_point_id, 'User',
          v_User, NULL, NULL, NULL, NULL, NULL, NULL);
        PERFORM AD_EP_INSTANCE_PARA_INSERT(v_ep_instance, v_extension_point_id, 'Message',
          NULL, NULL, NULL, NULL, NULL, NULL, v_Message);
        PERFORM AD_EP_INSTANCE_PARA_INSERT(v_ep_instance, v_extension_point_id, 'Result',
          NULL, NULL, v_result, NULL, NULL, NULL, NULL);
        PERFORM AD_EXTENSION_POINT_HANDLER(v_ep_instance, v_extension_point_id);
        SELECT p_number INTO v_Result
        FROM ad_ep_instance_para
        WHERE ad_ep_instance_id = v_ep_instance
          AND parametername LIKE 'Result';
        SELECT p_text INTO v_Message
        FROM ad_ep_instance_para
        WHERE ad_ep_instance_id = v_ep_instance
          AND parametername LIKE 'Message';

        DELETE FROM ad_ep_instance_para
        WHERE ad_ep_instance_id = v_ep_instance;
      END;
    END IF;

    IF (NOT FINISH_PROCESS) THEN
      IF (p_PInstance_ID IS NOT NULL) THEN
        v_ResultStr:='UnLockingOrder';
        UPDATE C_ORDER
        SET Processing='N',
            Updated=TO_DATE(NOW()),
            UpdatedBy=v_User
        WHERE C_Order_ID=v_Record_ID;
        -- COMMIT;
      END IF;
    END IF;--FINISH_PROCESS

    ---- <<FINISH_PROCESS>>
    IF (p_PInstance_ID IS NOT NULL) THEN
      --  Update AD_PInstance
      RAISE NOTICE '%','Updating PInstance - Finished - ' || v_Message ;
      PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', v_Result, v_Message) ;
    END IF;
    RETURN;
  END; --BODY
EXCEPTION
WHEN OTHERS THEN
  RAISE NOTICE '%',v_ResultStr ;
  v_ResultStr:= '@ERROR=' || SQLERRM;
  IF(p_PInstance_ID IS NOT NULL) THEN
    -- ROLLBACK;
    --Inserted by Carlos Romero 062706
    UPDATE C_ORDER  SET Processing='N'  WHERE C_Order_ID=v_Record_ID;
    RAISE NOTICE '%',v_ResultStr ;
    PERFORM AD_UPDATE_PINSTANCE(p_PInstance_ID, NULL, 'N', 0, v_ResultStr) ;
  ELSE
    RAISE EXCEPTION '%', SQLERRM;
  END IF;
  RETURN;
END ; $function$;

