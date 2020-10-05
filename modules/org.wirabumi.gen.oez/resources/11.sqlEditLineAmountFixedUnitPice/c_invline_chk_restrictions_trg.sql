CREATE OR REPLACE FUNCTION public.c_invline_chk_restrictions_trg()
 RETURNS trigger
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
  v_Processed VARCHAR(60) ;
  v_Posted VARCHAR(60) ;
  v_C_INVOICE_ID VARCHAR(32) ; --OBTG:VARCHAR2--
  v_Prec NUMERIC:=2;
  v_Currency     VARCHAR(32); --OBTG:VARCHAR2--
  v_productname m_product.name%TYPE;
  v_isgeneric   CHAR(1);
  v_Issotrx     CHAR(1);
  v_makenetunitpricefixed boolean;
    
BEGIN
    
    IF AD_isTriggerEnabled()='N' THEN IF TG_OP = 'DELETE' THEN RETURN OLD; ELSE RETURN NEW; END IF; 
    END IF;
    
  IF TG_OP = 'INSERT' THEN
    v_C_INVOICE_ID:=NEW.C_INVOICE_ID;
    if (new.iseditlinenetamt='Y' and new.em_oez_makenetunitpricefixed='Y' and new.em_oez_cancelpricead='Y') then
      v_makenetunitpricefixed=true;
    else
      v_makenetunitpricefixed=false;
    end if;
  ELSE
    v_C_INVOICE_ID:=OLD.C_INVOICE_ID;
    if (old.iseditlinenetamt='Y' and old.em_oez_makenetunitpricefixed='Y' and old.em_oez_cancelpricead='Y') then
      v_makenetunitpricefixed=true;
    else
      v_makenetunitpricefixed=false;
    end if;
  END IF;
  SELECT PROCESSED, POSTED,
    C_CURRENCY_ID, Issotrx
  INTO v_Processed, v_Posted,
    v_Currency, v_Issotrx
  FROM C_INVOICE
  WHERE C_INVOICE_ID=v_C_INVOICE_ID;
  IF TG_OP = 'UPDATE' THEN
    IF(v_Processed='Y' AND ((COALESCE(OLD.LINE, 0) <> COALESCE(NEW.LINE, 0))
         OR(COALESCE(OLD.M_PRODUCT_ID, '0') <> COALESCE(NEW.M_PRODUCT_ID, '0'))
      OR(COALESCE(OLD.QTYINVOICED, 0) <> COALESCE(NEW.QTYINVOICED, 0))
      OR(COALESCE(old.LINE, 0) <> COALESCE(NEW.LINE, 0))
      OR(COALESCE(OLD.PRICELIST, 0) <> COALESCE(NEW.PRICELIST, 0))
      OR(COALESCE(OLD.PRICEACTUAL, 0) <> COALESCE(NEW.PRICEACTUAL, 0))
      OR(COALESCE(OLD.PRICELIMIT, 0) <> COALESCE(NEW.PRICELIMIT, 0))
      OR(COALESCE(OLD.LINENETAMT, 0) <> COALESCE(NEW.LINENETAMT, 0))
      OR(COALESCE(OLD.C_CHARGE_ID, '0') <> COALESCE(NEW.C_CHARGE_ID, '0'))
      OR(COALESCE(OLD.CHARGEAMT, 0) <> COALESCE(NEW.CHARGEAMT, 0))
      OR(COALESCE(OLD.C_UOM_ID, '0') <> COALESCE(NEW.C_UOM_ID, '0'))
      OR(COALESCE(OLD.C_TAX_ID, '0') <> COALESCE(NEW.C_TAX_ID, '0'))
      OR(COALESCE(OLD.TAXAMT, 0) <> COALESCE(NEW.TAXAMT, 0))
      OR(COALESCE(OLD.M_ATTRIBUTESETINSTANCE_ID, '0') <> COALESCE(NEW.M_ATTRIBUTESETINSTANCE_ID, '0'))
      OR(COALESCE(OLD.QUANTITYORDER, 0) <> COALESCE(NEW.QUANTITYORDER, 0))
      OR(COALESCE(OLD.C_ORDERLINE_ID, '0') <> COALESCE(NEW.C_ORDERLINE_ID, '0'))
      OR(COALESCE(OLD.M_PRODUCT_UOM_ID, '0') <> COALESCE(NEW.M_PRODUCT_UOM_ID, '0'))
      OR(COALESCE(OLD.C_AUM, '0') <> COALESCE(NEW.C_AUM, '0'))
      OR(COALESCE(OLD.AUMQTY, 0) <> COALESCE(NEW.AUMQTY, 0))	
      OR(COALESCE(OLD.AD_ORG_ID, '0') <> COALESCE(NEW.AD_ORG_ID, '0'))
      OR(COALESCE(OLD.AD_CLIENT_ID, '0') <> COALESCE(NEW.AD_CLIENT_ID, '0'))
      )) THEN
      RAISE EXCEPTION '%', '@20501@' ; --OBTG:-20000--
    END IF;
    IF(v_Posted='Y' AND ((COALESCE(OLD.ISDEFERRED, '0') <> COALESCE(NEW.ISDEFERRED, '0'))
         OR(COALESCE(OLD.C_PERIOD_ID, '0') <> COALESCE(NEW.C_PERIOD_ID, '0'))
      OR(COALESCE(OLD.DEFPLANTYPE, '0') <> COALESCE(NEW.DEFPLANTYPE, '0'))
      OR(COALESCE(OLD.PERIODNUMBER, 0) <> COALESCE(NEW.PERIODNUMBER, 0))
      OR(COALESCE(OLD.C_PROJECT_ID, '0') <> COALESCE(NEW.C_PROJECT_ID, '0'))
      OR(COALESCE(OLD.C_COSTCENTER_ID, '0') <> COALESCE(NEW.C_COSTCENTER_ID, '0'))
      OR(COALESCE(OLD.A_ASSET_ID, '0') <> COALESCE(NEW.A_ASSET_ID, '0'))
      OR(COALESCE(OLD.USER1_ID, '0') <> COALESCE(NEW.USER1_ID, '0'))
      OR(COALESCE(OLD.USER2_ID, '0') <> COALESCE(NEW.USER2_ID, '0'))
      )) THEN
      RAISE EXCEPTION '%', '@20501@' ; --OBTG:-20000--
    END IF;

    IF(NEW.M_Product_ID <> OLD.M_Product_ID) THEN
      IF(NEW.C_OrderLine_ID IS NOT NULL) THEN
	RAISE EXCEPTION '%', '@ProductDefinedByOrder@' ; --OBTG:-20000--
      ELSE IF(NEW.M_InOutLine_ID IS NOT NULL AND v_Issotrx = 'N') THEN
	RAISE EXCEPTION '%', '@20206@' ; --OBTG:-20000--
      ELSE IF(NEW.M_InOutLine_ID IS NOT NULL AND v_Issotrx = 'Y') THEN
        RAISE EXCEPTION '%', '@ProductDefinedByShipment@' ; --OBTG:-20000--
      END IF;
      END IF;
      END IF;
    END IF;
  END IF;
  IF((TG_OP = 'DELETE' OR TG_OP = 'INSERT') AND v_Processed='Y') THEN
    RAISE EXCEPTION '%', '@20501@' ; --OBTG:-20000--
  END IF;
  -- Rounds linenetAmt and ChargeAmt
  IF(TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
    SELECT STDPRECISION
    INTO v_Prec
    FROM C_CURRENCY
    WHERE C_CURRENCY_ID=v_Currency;

    if (v_makenetunitpricefixed=false) then
      IF (NEW.ISEDITLINENETAMT='Y' AND ROUND(TO_NUMBER(NEW.QTYINVOICED) * TO_NUMBER(NEW.PRICEACTUAL),v_Prec)!=TO_NUMBER(NEW.LINENETAMT)) THEN
        RAISE EXCEPTION '%', '@LineAmountNotCorrect@' ; --OBTG:-20000--
      END IF;
    end if;
    
    IF (NEW.account_id is null AND NEW.m_product_id is null AND NEW.linenetamt <> 0) THEN
      RAISE EXCEPTION '%', '@InvoiceLineAmountMustBeZero@' ; --OBTG:-20000--
    END IF;
    
  END IF;
  IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
    IF (NEW.m_product_id IS NOT NULL) THEN
      SELECT isgeneric, name INTO v_isgeneric, v_productname
      FROM m_product
      WHERE m_product_id = NEW.m_product_id;
      IF (v_isgeneric = 'Y') THEN
        RAISE EXCEPTION '%', '@CannotUseGenericProduct@'; --OBTG:-20000--
      END IF;
    END IF;
  END IF;

  IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
    IF (NEW.C_AUM IS NOT NULL AND NEW.AUMQTY IS NOT NULL AND NEW.M_PRODUCT_UOM_ID IS NOT NULL AND NEW.QUANTITYORDER IS NOT NULL) THEN
      RAISE EXCEPTION '%', '@CannotUseAUMandSecondUOM@'; --OBTG:-20000--
    END IF;
  END IF;
  
IF TG_OP = 'DELETE' THEN RETURN OLD; ELSE RETURN NEW; END IF; 

END 

; $function$;

