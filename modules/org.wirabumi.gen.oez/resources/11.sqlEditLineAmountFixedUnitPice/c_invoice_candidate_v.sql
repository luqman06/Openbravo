CREATE OR REPLACE VIEW public.c_invoice_candidate_v AS
 SELECT sq.ad_client_id,
    sq.ad_org_id,
    sq.c_bpartner_id,
    sq.c_order_id,
    sq.documentno,
    sq.dateordered,
    sq.c_doctype_id,
    sq.amountlines,
    sq.amountlinesgross,
    sq.notinvoicedlines,
    round(sq.notinvoicedlinesgross, c.stdprecision) AS notinvoicedlinesgross,
    sq.term,
    sq.pendinglines,
    round(sq.pendinglinesgross, c.stdprecision) AS pendinglinesgross,
    sq.qtyordered,
    sq.qtydelivered
   FROM ( SELECT o.ad_client_id,
            o.ad_org_id,
            o.c_bpartner_id,
            o.c_order_id,
            o.documentno,
            o.dateordered,
            o.c_doctype_id,
            o.totallines AS amountlines,
            o.grandtotal AS amountlinesgross,
            sum(round((l.qtyordered - l.qtyinvoiced) * l.priceactual, c_1.stdprecision)) AS notinvoicedlines,
            sum(round((l.qtyordered - l.qtyinvoiced) * l.priceactual, c_1.stdprecision) * (1::numeric + t.rate / 100::numeric)) AS notinvoicedlinesgross,
            o.invoicerule AS term,
            sum(round((l.qtydelivered - l.qtyinvoiced) * l.priceactual, c_1.stdprecision)) AS pendinglines,
            sum(round((l.qtydelivered - l.qtyinvoiced) * l.priceactual, c_1.stdprecision) * (1::numeric + t.rate / 100::numeric)) AS pendinglinesgross,
            sum(abs(l.qtyordered)) AS qtyordered,
            sum(abs(l.qtydelivered)) AS qtydelivered,
            o.m_pricelist_id,
            c_1.c_currency_id
           FROM c_order o
             JOIN m_pricelist p_1 ON o.m_pricelist_id::text = p_1.m_pricelist_id::text
             JOIN c_currency c_1 ON p_1.c_currency_id::text = c_1.c_currency_id::text
             JOIN c_orderline l ON o.c_order_id::text = l.c_order_id::text
             JOIN c_bpartner bp ON o.c_bpartner_id::text = bp.c_bpartner_id::text
             JOIN c_tax t ON t.c_tax_id::text = l.c_tax_id::text
             LEFT JOIN c_invoiceschedule si ON bp.c_invoiceschedule_id::text = si.c_invoiceschedule_id::text
          WHERE (o.docstatus::text = ANY (ARRAY['CO'::character varying::text, 'CL'::character varying::text, 'IP'::character varying::text])) AND (o.c_doctype_id::text IN ( SELECT c_doctype.c_doctype_id
                   FROM c_doctype
                  WHERE c_doctype.docbasetype::text = 'SOO'::text AND (c_doctype.docsubtypeso::text <> ALL (ARRAY['ON'::character varying::text, 'OB'::character varying::text, 'WR'::character varying::text])))) AND (o.invoicerule::text = 'I'::text OR o.invoicerule::text = 'O'::text OR o.invoicerule::text = 'N'::text OR o.invoicerule::text = 'D'::text OR o.invoicerule::text = 'S'::text AND (si.invoicefrequency IS NULL OR si.invoicefrequency::text = 'D'::text OR si.invoicefrequency::text = 'W'::text OR si.invoicefrequency::text = 'T'::text AND trunc(o.dateordered) <= (date_trunc('month'::text, now()) + si.invoicedaycutoff - 1) AND trunc(now()) >= (date_trunc('month'::text, o.dateordered)::timestamp with time zone + si.invoiceday - 1) OR trunc(o.dateordered) <= (date_trunc('month'::text, now()) + si.invoicedaycutoff + 14) AND trunc(now()) >= (date_trunc('month'::text, o.dateordered)::timestamp with time zone + si.invoiceday + 14) OR si.invoicefrequency::text = 'M'::text AND trunc(o.dateordered) <= (date_trunc('month'::text, now()) + si.invoicedaycutoff - 1) AND trunc(now()) >= (date_trunc('month'::text, o.dateordered)::timestamp with time zone + si.invoiceday - 1))) AND (abs(l.qtyordered - l.qtyinvoiced) <> 0::numeric OR abs(l.qtydelivered - l.qtyinvoiced) <> 0::numeric)
          GROUP BY o.ad_client_id, o.ad_org_id, o.c_bpartner_id, o.c_order_id, o.documentno, o.dateordered, o.c_doctype_id, o.totallines, o.grandtotal, o.invoicerule, o.m_pricelist_id, c_1.c_currency_id) sq
     JOIN m_pricelist p ON sq.m_pricelist_id::text = p.m_pricelist_id::text
     JOIN c_currency c ON sq.c_currency_id::text = c.c_currency_id::text;

