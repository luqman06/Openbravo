-- Function: ad_sequence_doctype(character varying, character varying, character)

-- DROP FUNCTION ad_sequence_doctype(character varying, character varying, character);

CREATE OR REPLACE FUNCTION ad_sequence_doctype(IN p_doctype_id character varying, IN p_id character varying, IN p_org_id character varying, IN p_update_next character, OUT p_documentno character varying)
  RETURNS character varying AS
$BODY$ DECLARE 
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
  * Contributions are Copyright (C) 2001-2009 Openbravo, S.L.U.
  *
  * Specifically, this derivative work is based upon the following Compiere
  * file and version.
  *************************************************************************
  * $Id: AD_Sequence_DocType.sql,v 1.9 2003/08/06 06:51:27 jjanke Exp $
  ***
  * Title: Get the next DocumentNo of Document Type
  * Description:
  *  store in parameter p_DocumentNo
  *  If ID < 1000000, use System Doc Sequence
  *  If no Document Sequence is defined, return null !
  *   Use AD_Sequence_Doc('DocumentNo_myTable',.. to get it directly
  ************************************************************************/
  v_NextNo VARCHAR(32); --OBTG:VARCHAR2--

  v_Sequence_ID VARCHAR(32):=NULL; --OBTG:VARCHAR2--
  v_Prefix VARCHAR(30) ; --OBTG:VARCHAR2--
  v_Suffix VARCHAR(30) ; --OBTG:VARCHAR2--
BEGIN
  -- Is a document Sequence defined and valid
BEGIN
  SELECT DocNoSequence_ID
  INTO v_Sequence_ID
  FROM C_DocType
  WHERE C_DocType_ID=p_DocType_ID -- parameter
    AND IsDocNoControlled='Y'  AND IsActive='Y';
EXCEPTION
WHEN OTHERS THEN
  NULL;
END;
IF(v_Sequence_ID IS NULL) THEN -- No Sequence Number
  p_DocumentNo:= NULL; -- Return NULL
  RAISE NOTICE '%','[AD_Sequence_DocType: not found - C_DocType_ID=' || p_DocType_ID || ']' ;
  RETURN;
END IF;
-- Get the numbers
SELECT s.CurrentNext, s.Prefix, s.Suffix
INTO v_NextNo, v_Prefix, v_Suffix
FROM AD_Sequence s
WHERE s.AD_Sequence_ID = v_Sequence_ID  AND s.IsActive='Y'  AND s.IsTableID='N'  AND s.IsAutoSequence='Y'  FOR UPDATE; --OBTG: OF CurrentNext--

  IF p_Update_Next='Y' THEN
    UPDATE AD_Sequence
      SET CurrentNext=CurrentNext + IncrementNo
    WHERE AD_Sequence_ID=v_Sequence_ID;
  END IF;
  p_DocumentNo:=COALESCE(v_Prefix, '') || v_NextNo || COALESCE(v_Suffix, '') ;

-- DBMS_OUTPUT.PUT_LINE(p_DocumentNo);
EXCEPTION
WHEN DATA_EXCEPTION THEN
  RAISE EXCEPTION '%', '@DocumentTypeSequenceNotFound@' ; --OBTG:-20000--
END ; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ad_sequence_doctype(character varying, character varying, character)
  OWNER TO postgres;
