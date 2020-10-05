create type oez_advmovementrecord as (
     gudang varchar,
     productname varchar,
     keluar numeric,
     masuk numeric,
     movementdate timestamp without time zone,
     noref varchar,
     stockawal numeric,
     stockakhir numeric,
     movementtype varchar,
     description varchar
); 

-- Function: oez_advproductmovementreport(character varying, timestamp without time zone, timestamp without time zone, character varying, character varying)

-- DROP FUNCTION oez_advproductmovementreport(character varying, timestamp without time zone, timestamp without time zone, character varying, character varying);

CREATE OR REPLACE FUNCTION oez_advproductmovementreport(p_client_id character varying, p_startdate timestamp without time zone, p_enddate timestamp without time zone, p_product_id character varying, p_warehouse_id character varying)
  RETURNS SETOF oez_advmovementrecord AS
$BODY$
declare
r oez_advmovementrecord%rowtype;
rec record;
v_count numeric:=0; --counter loop, untuk indikator apakah dia loop pertama
v_stockakhir numeric:=0; --nilai stock akhir disimpan dalam local variable, digunakan untuk record N+1
v_oldproduct varchar(32):=''; --jika oldproduct maka v_count akan reset kembali ke 0
v_oldlocator varchar(32):=''; --jika oldlocator maka v_count akan reset kembali ke 0

begin
	for rec in (
			select m_locator_id, m_product_id, movementtype,
			(case when movementqty <0 then abs(movementqty)
						else 0 end) as keluar,
			(case when movementqty >0 then movementqty
						else 0 end) as masuk,
			movementdate,
			(case when m_inoutline_id is not null then
					(select substring(array_agg(documentno)::character varying, 2,
					(select length(array_agg(documentno)::character varying)-2)) from c_invoice
					 where c_invoice_id in (select c_invoice_id from c_invoiceline
							   where c_invoiceline.m_inoutline_id=m_transaction.m_inoutline_id))
			      when m_movementline_id is not null then
					(select documentno from m_movement
					 where m_movement_id=(select m_movement_id from m_movementline
							   where m_movementline_id=m_transaction.m_movementline_id))
			      when m_productionline_id is not null then
					(select documentno from m_production
					 where m_production_id=(select m_production_id from m_productionline
						           where m_productionline_id=m_transaction.m_productionline_id) limit 1)
			      when m_inventoryline_id is not null then
					(select name from m_inventory
					 where m_inventory_id=(select m_inventory_id from m_inventoryline
							   where m_inventoryline_id=m_transaction.m_inventoryline_id))
			end) as noref,
			(case when m_inoutline_id is not null then
			      (select REGEXP_REPLACE((select REGEXP_REPLACE((select case when (select REGEXP_REPLACE(
			                (select substring(array_agg(c_bpartner.name)::character varying, 2,
					(select length(array_agg(c_bpartner.name)::character varying)-2)) from c_invoice
					 left join c_bpartner on c_bpartner.c_bpartner_id=c_invoice.c_bpartner_id 
					 where c_invoice_id in (select c_invoice_id from c_invoiceline
							   where c_invoiceline.m_inoutline_id=m_transaction.m_inoutline_id))
					, 'NULL', '', 'g')::character varying)=',' then '' else
					(select REGEXP_REPLACE(
			                (select substring(array_agg(c_bpartner.name)::character varying, 2,
					(select length(array_agg(c_bpartner.name)::character varying)-2)) from c_invoice
					 left join c_bpartner on c_bpartner.c_bpartner_id=c_invoice.c_bpartner_id 
					 where c_invoice_id in (select c_invoice_id from c_invoiceline
							   where c_invoiceline.m_inoutline_id=m_transaction.m_inoutline_id))
					, 'NULL', '', 'g')::character varying) end), '"', '', 'g'))::character varying
					, ',', ', ', 'g'))
		              when m_movementline_id is not null then
					(select description from m_movement
					 where m_movement_id=(select m_movement_id from m_movementline
							   where m_movementline_id=m_transaction.m_movementline_id))
			      when m_productionline_id is not null then
					(select description from m_production
					 where m_production_id=(select m_production_id from m_productionline
						           where m_productionline_id=m_transaction.m_productionline_id) limit 1)
			      when m_inventoryline_id is not null then
					(select description from m_inventory
					 where m_inventory_id=(select m_inventory_id from m_inventoryline
							   where m_inventoryline_id=m_transaction.m_inventoryline_id))
			end) as description
		from m_transaction
		where ad_client_id=p_client_id
		--and m_product_id=p_product_id
		--and m_locator_id in (select m_locator_id from m_locator where m_warehouse_id=p_warehouse_id)
		and movementdate>=p_startdate
		and movementdate<=p_enddate
		order by m_locator_id asc, m_product_id asc, movementdate asc
	)
	loop
		if (rec.m_product_id <> v_oldproduct or rec.m_locator_id<>v_oldlocator) then v_count:=0;
		end if;

		r.keluar=rec.keluar;
		r.masuk=rec.masuk;
		r.movementdate=rec.movementdate;
		r.noref=rec.noref;
		r.description=rec.description;
			
		if v_count=0 then
		  raise notice 'client_id: %, movementdate: %, product: %, locator: %', p_client_id, r.movementdate, rec.m_product_id, rec.m_locator_id;
		  select coalesce(sum(movementqty),0) into r.stockawal from m_transaction
		    where movementdate<r.movementdate
		    and ad_client_id=p_client_id
		    and m_product_id=rec.m_product_id
		    and m_locator_id=rec.m_locator_id;
		else
		  r.stockawal:= v_stockakhir;
		end if;

		v_count:= v_count+1;
		v_oldproduct:= rec.m_product_id;
		v_oldlocator:= rec.m_locator_id;
		v_stockakhir:= r.stockawal+r.masuk-r.keluar;
		r.stockakhir:= v_stockakhir;
		
		select name into r.gudang from m_warehouse where m_warehouse_id=(select m_warehouse_id
								   from m_locator where m_locator_id=rec.m_locator_id);
		select name into r.productname from m_product where m_product_id=rec.m_product_id;
		select name into r.movementtype from ad_ref_list where ad_reference_id = '189' and value = rec.movementtype;

		return next r;
	end loop;

	return;

end; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION oez_advproductmovementreport(character varying, timestamp without time zone, timestamp without time zone, character varying, character varying)
  OWNER TO postgres;
