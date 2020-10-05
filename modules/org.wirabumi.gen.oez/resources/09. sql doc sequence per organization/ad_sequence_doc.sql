-- Function: ad_sequence_doc(character varying, character varying, character varying, character)

-- DROP FUNCTION ad_sequence_doc(character varying, character varying, character varying, character);

CREATE OR REPLACE FUNCTION ad_sequence_doc(IN p_sequencename character varying, IN p_ad_client_id character varying, IN p_ad_org_id character varying, IN p_update_next character, OUT p_documentno character varying)
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
  * Contributions are Copyright (C) 2001-2012 Openbravo, S.L.U.
  *
  * Specifically, this derivative work is based upon the following Compiere
  * file and version.
  *************************************************************************
  * $Id: AD_Sequence_Doc.sql,v 1.6 2003/08/06 06:51:26 jjanke Exp $
  ***
  * Title: Get the next DocumentNo of TableName
  * Description:
  *  store in parameter p_DocumentNo
  *  if ID < 1000000, use System Doc Sequence
  ************************************************************************/
  v_NextNo VARCHAR(32); --OBTG:VARCHAR2--
  v_NextNoSys NUMERIC;
  v_Prefix VARCHAR(30) ; --OBTG:VARCHAR2--
  v_Suffix VARCHAR(30) ; --OBTG:VARCHAR2--
  v_sequence_id VARCHAR(32) ;
  v_org_id VARCHAR(32) ;
BEGIN
  
  v_org_id:=p_ad_org_id;
  loop
    --cari doc sequence
    select ad_sequence_id into v_sequence_id
    from ad_sequence
    where Name=p_SequenceName
    AND ad_org_id=v_org_id;

    --jika ketemu maka keluar loop
    if v_sequence_id is not null then exit; end if;
    
    --jika tidak ketemu, cari parent
      --jika ternyata sudah di root org, maka keluar loop
      if v_org_id='0' then exit; end if;
      
      --lookup parent
      select parent_id into v_org_id
      from ad_treenode
      where node_id=v_org_id
      and ad_tree_id=(select ad_tree_org_id from ad_clientinfo where ad_client_id=
			 (select ad_client_id from ad_org where ad_org_id=ad_treenode.node_id));
    
  end loop;

  SELECT CurrentNext, Prefix, Suffix
  INTO v_NextNo, v_Prefix, v_Suffix
  FROM AD_Sequence
  WHERE ad_sequence_id=v_sequence_id;

  IF p_Update_Next='Y' THEN
    UPDATE AD_Sequence
      SET CurrentNext=CurrentNext + IncrementNo, Updated=TO_DATE(NOW())
    WHERE ad_sequence_id=v_sequence_id;
  END IF;
  p_DocumentNo:=COALESCE(v_Prefix, '') || v_NextNo || COALESCE(v_Suffix, '') ;

EXCEPTION
WHEN DATA_EXCEPTION THEN
  RAISE EXCEPTION '%', '@DocumentSequenceNotFound@' || p_SequenceName ; --OBTG:-20000--
END ; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION ad_sequence_doc(character varying, character varying, character varying, character)
  OWNER TO postgres;
