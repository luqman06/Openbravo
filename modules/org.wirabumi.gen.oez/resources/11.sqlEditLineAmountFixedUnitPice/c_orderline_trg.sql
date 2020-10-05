CREATE OR REPLACE FUNCTION public.c_orderline_trg()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$ DECLARE 

/*************************************************************************
  * The contents of this file are subject to the Compiere Public
  * License 1.1 ("License"); You may not use this file except in
  * compliance with the License. You may obtain a copy of the License in
  * the legal folder of your Openbravo installation.
  * Software distributed under the License is distributed on an
  * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
  * implied. See the License for the specific language governing rights
  * and limitations under the License.
  * The Original Code is  Compiere  ERP &  Business Solution
  * The Initial Developer of the Original Code is Jorg Janke and ComPiere, Inc.
  * Portions created by Jorg Janke are Copyright (C) 1999-2001 Jorg Janke,
  * parts created by ComPiere are Copyright (C) ComPiere, Inc.;
  * All Rights Reserved.
  * Contributor(s): Openbravo SLU
  * Contributions are Copyright (C) 2001-2017 Openbravo, S.L.U.
  *
  * Specifically, this derivative work is based upon the following Compiere
  * file and version.
  *************************************************************************/

  v_Prec   NUMERIC;
  v_price_prec NUMERIC;
  v_ID   VARCHAR(32); --OBTG:VARCHAR2--
  v_UOM_ID    VARCHAR(32); --OBTG:VARCHAR2--
  v_IsSOTrx CHAR(1);
  v_Count NUMERIC;
  v_CountRelations NUMERIC;
  v_reservation_id    VARCHAR(32); --OBTG:VARCHAR2--
  v_istaxincluded CHAR(1);
  v_PriceActual NUMERIC;
  
  v_Warehouse_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_BPartner_Location_ID VARCHAR(32); --OBTG:VARCHAR2--
  v_Project_ID VARCHAR(32);   --OBTG:VARCHAR2--
  v_AsBOM CHAR(1);  
  v_TotalRatio NUMERIC;
  v_TotalProducts NUMERIC;
  
  v_BaseLine NUMERIC;
  v_Line NUMERIC;
  v_LineAcum NUMERIC;   
  v_NetActual NUMERIC;
  v_PriceLine NUMERIC;
  v_CalcLine NUMERIC;

  --TYPE RECORD IS REFCURSOR;
  Cur_BOM RECORD;

BEGIN
    
    IF AD_isTriggerEnabled()='N' THEN IF TG_OP = 'DELETE' THEN RETURN OLD; ELSE RETURN NEW; END IF; 
    END IF;


IF (TG_OP = 'UPDATE') THEN
  IF NOT(COALESCE(old.M_Product_ID,'0') <> COALESCE(NEW.M_Product_ID,'0')
  OR COALESCE(old.LineNetAmt,0) <> COALESCE(NEW.LineNetAmt,0)
  OR COALESCE(old.FreightAmt,0) <> COALESCE(NEW.FreightAmt,0)
  OR COALESCE(old.ChargeAmt,0) <> COALESCE(NEW.ChargeAmt,0)
  OR COALESCE(old.C_Tax_ID,'0') <> COALESCE(NEW.C_Tax_ID,'0')
  OR COALESCE(old.C_Uom_ID,'0') <> COALESCE(NEW.C_Uom_ID,'0')
  OR COALESCE(old.qtyOrdered,0) <> COALESCE(NEW.qtyOrdered,0)
  OR COALESCE(old.PriceActual,0) <> COALESCE(NEW.PriceActual,0)
  OR COALESCE(old.gross_unit_price,0) <> COALESCE(NEW.gross_unit_price,0)
  OR COALESCE(old.line_gross_amount,0) <> COALESCE(NEW.line_gross_amount,0))
 THEN
  IF TG_OP = 'DELETE' THEN RETURN OLD; ELSE RETURN NEW; END IF; 
  END IF;
 END IF;
 /**
  * Check Product changes = not possible when reservation, invoice or delivery exists
  */
 IF (TG_OP = 'DELETE') THEN
  IF (old.QtyReserved <> 0 AND old.C_ORDER_DISCOUNT_ID IS NULL) THEN
   RAISE EXCEPTION '%', '@20200@' || ' ' ||old.QtyReserved; --OBTG:-20000--
  ELSIF (old.QtyDelivered <> 0) THEN
   RAISE EXCEPTION '%', '@20201@' || ' ' || old.QtyDelivered; --OBTG:-20000--
  ELSIF (old.QtyInvoiced <> 0) THEN
   RAISE EXCEPTION '%', '@20202@' || ' ' || old.QtyInvoiced; --OBTG:-20000--
  END IF;
  -- Check if reservation exists to delete it first.
  SELECT issotrx INTO v_IsSOTrx
  FROM c_order
  WHERE c_order_id = old.c_order_id;
  IF (v_issotrx = 'Y') THEN
    SELECT count(*) INTO v_count
    FROM m_reservation 
    WHERE c_orderline_id = OLD.c_orderline_id;
    IF (v_count > 0) THEN
      RAISE EXCEPTION '%', '@DeleteRelatedReservation@'; --OBTG:-20000--
    END IF;
  ELSE
    DELETE FROM m_reservation_stock
    WHERE c_orderline_id = OLD.c_orderline_id;
  END IF;
 ELSIF (TG_OP = 'UPDATE') THEN
   SELECT issotrx INTO v_IsSOTrx
   FROM c_order
   WHERE c_order_id = old.c_order_id;
   SELECT count(*) INTO v_count
    FROM m_reservation 
    WHERE c_orderline_id = OLD.c_orderline_id;

   IF (old.M_Product_ID <> NEW.M_Product_ID) THEN
     SELECT COUNT(1) into v_CountRelations FROM DUAL 
     WHERE EXISTS (SELECT 1 FROM C_ORDERLINE_SERVICERELATION WHERE C_ORDERLINE_ID = NEW.C_ORDERLINE_ID);
     IF(v_CountRelations <> 0) THEN
       RAISE EXCEPTION '%', '@RelationsExist@'; --OBTG:-20000--
     END IF;
     IF (old.QtyReserved <> 0) THEN
       RAISE EXCEPTION '%', '@20203@' || ' ' || old.QtyReserved; --OBTG:-20000--
     ELSIF (old.QtyDelivered <> 0) THEN
       RAISE EXCEPTION '%', '@20204@' || ' ' || old.QtyDelivered; --OBTG:-20000--
     ELSIF (old.QtyInvoiced <> 0) THEN
       RAISE EXCEPTION '%', '@20205@' || ' ' || old.QtyInvoiced; --OBTG:-20000--
     ELSIF (v_IsSOTrx = 'N') THEN
       SELECT count(*) INTO v_Count
       FROM m_inoutline
       WHERE c_orderline_id = old.c_orderline_id;
       IF (v_count > 0) THEN
         RAISE EXCEPTION '%', '@20206@'; --OBTG:-20000--
       END IF;
       SELECT count(*) INTO v_count
       FROM m_requisitionorder
       WHERE c_orderline_id = old.c_orderline_id;
       IF (v_count > 0) THEN
         RAISE EXCEPTION '%', '@ProductChangeLineInRequisition@'; --OBTG:-20000--
       END IF;
     -- Looks for a draft reservation for the order line and change its product
     ELSIF (v_count > 0) THEN
       SELECT count(*) INTO v_count
       FROM m_reservation 
       WHERE c_orderline_id = OLD.c_orderline_id
       AND res_status = 'DR';
       IF (v_count > 0) THEN
         SELECT max(m_reservation_id) INTO v_reservation_id
         FROM m_reservation 
         WHERE c_orderline_id = OLD.c_orderline_id
         AND m_product_id = OLD.m_product_id
         AND res_status = 'DR';
         DELETE FROM m_reservation_stock
         WHERE m_reservation_id = v_reservation_id;
         UPDATE m_reservation
         SET m_product_id = NEW.M_Product_ID
         WHERE m_reservation_id = v_reservation_id;
       ELSE
         RAISE EXCEPTION '%', '@ProductChangeLineInReservation@'; --OBTG:-20000--
       END IF;
     END IF;
   END IF;
 ELSIF (TG_OP = 'INSERT') THEN
   SELECT issotrx INTO v_IsSOTrx
   FROM c_order
   WHERE c_order_id = NEW.c_order_id;
 END IF;

 -- Get ID
 IF (TG_OP = 'UPDATE' OR TG_OP = 'INSERT') THEN
     IF (NEW.M_PRODUCT_ID IS NOT NULL) THEN
       SELECT C_UOM_ID INTO v_UOM_ID FROM M_PRODUCT WHERE M_PRODUCT_ID=NEW.M_PRODUCT_ID;
       IF (COALESCE(v_UOM_ID,'0') <> COALESCE(NEW.C_UOM_ID,'0')) THEN
         RAISE EXCEPTION '%', '@20111@'; --OBTG:-20000--
       END IF;
     END IF;
  v_ID := new.C_Order_ID;
 ELSE
  v_ID := old.C_Order_ID;
 END IF;

 SELECT stdPrecision, m_pricelist.istaxincluded, priceprecision, M_WAREHOUSE_ID, C_BPARTNER_LOCATION_ID, C_PROJECT_ID
   INTO v_prec, v_istaxincluded, v_price_prec, v_Warehouse_ID, v_Bpartner_Location_ID, v_Project_ID
 FROM c_order
        JOIN c_currency ON c_order.c_currency_id = c_currency.c_currency_id
        JOIN m_pricelist ON c_order.m_pricelist_id = m_pricelist.m_pricelist_id
 WHERE C_Order_ID = v_ID;
 /**
  * Round Base
  */
 IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
  IF (v_istaxincluded = 'Y') THEN

    -- If tax included taxes recalculate net price
    new.line_gross_amount := ROUND(new.gross_unit_price * new.qtyordered, v_prec);

    SELECT tc.ASBOM INTO v_AsBOM FROM C_TAXCATEGORY tc, C_tax t WHERE tc.C_TAXCATEGORY_ID = t.C_TAXCATEGORY_ID AND t.C_TAX_ID = new.C_Tax_ID;
    IF (v_AsBOM = 'Y') THEN
      -- BOM taxes
      -- Calculate the base for ratios.
      v_BaseLine := new.line_gross_amount;
      -- Calculate total ratio and number of products
      v_TotalRatio := 0;
      v_TotalProducts := 0;      
      FOR Cur_BOM IN (
        SELECT C_GETTAX(m_productbom_id, new.UPDATED, new.AD_Org_ID, v_Warehouse_ID, v_Bpartner_Location_ID, v_Bpartner_Location_ID, v_Project_ID, v_IsSOTRX) AS TAX,
        SUM(ROUND(BOMQTY * BOMPRICE, v_Prec)) AS RATIO FROM m_product_bom WHERE m_product_id = NEW.M_PRODUCT_ID
        GROUP BY C_GETTAX(m_productbom_id, new.UPDATED, new.AD_Org_ID, v_Warehouse_ID, v_Bpartner_Location_ID, v_Bpartner_Location_ID, v_Project_ID, v_IsSOTRX)
        ORDER BY RATIO ASC )
      LOOP
        v_TotalRatio := v_TotalRatio + Cur_BOM.RATIO;
        v_TotalProducts := v_TotalProducts + 1;
      END LOOP;
      
      -- Calculate taxes based on BOM of products  
      v_LineAcum := 0;
      v_NetActual := 0;
      
      FOR Cur_BOM IN (
        SELECT C_GETTAX(m_productbom_id, new.UPDATED, new.AD_Org_ID, v_Warehouse_ID, v_Bpartner_Location_ID, v_Bpartner_Location_ID, v_Project_ID, v_IsSOTRX) AS TAX,
        SUM(ROUND(BOMQTY * BOMPRICE, v_Prec)) AS RATIO FROM m_product_bom WHERE m_product_id = NEW.M_PRODUCT_ID
        GROUP BY C_GETTAX(m_productbom_id, new.UPDATED, new.AD_Org_ID, v_Warehouse_ID, v_Bpartner_Location_ID, v_Bpartner_Location_ID, v_Project_ID, v_IsSOTRX)
        ORDER BY RATIO ASC)
      LOOP
       v_TotalProducts := v_TotalProducts - 1;
        IF (v_TotalProducts > 0) THEN
          v_Line := ROUND(v_BaseLine * Cur_BOM.RATIO / v_TotalRatio , v_Prec);
          v_LineAcum := v_LineAcum + v_Line;
        ELSE -- The last willaccummulate rounding
          v_Line := v_BaseLine - v_LineAcum;
        END IF;
        v_CalcLine := C_GET_NET_AMOUNT_FROM_GROSS(Cur_BOM.TAX, v_Line, v_Line, v_Prec);
        v_NetActual := v_NetActual + v_CalcLine; -- Acum the net
      END LOOP;    
      v_PriceActual := ROUND(v_NetActual / new.qtyordered, v_price_prec);     
    ELSE
      -- Regular taxes
      v_NetActual := C_GET_NET_AMOUNT_FROM_GROSS(new.c_tax_id, new.line_gross_amount, new.taxbaseamt, v_Prec);
      IF (new.qtyordered <> 0) THEN
        v_PriceActual := ROUND(v_NetActual / new.qtyordered, v_price_prec);
      ELSE
        v_PriceActual := 0;
      END IF;
    END IF;      
    
    NEW.pricestd := v_priceactual;
    NEW.pricelist := v_priceactual;
    NEW.pricelimit := v_priceactual;
    NEW.priceactual := v_priceactual;
    new.taxbaseamt := v_NetActual;
    new.LineNetAmt := v_NetActual;

  ELSE
    -- Price including taxes = 'N'
    -- Modified by I.Ciordia. Sometimes js fails calculting lineNet
    IF (new.em_oez_makenetunitpricefixed='N') THEN
        new.LineNetAmt := ROUND(new.QtyOrdered*new.PriceActual, v_Prec);-- Modified by I.Ciordia  
    END IF;

  END IF;
  
  new.FreightAmt := ROUND(new.FreightAmt, v_Prec);
  new.ChargeAmt := ROUND(new.ChargeAmt, v_Prec);  
 END IF;

IF TG_OP = 'DELETE' THEN RETURN OLD; ELSE RETURN NEW; END IF; 

END 

; $function$;
