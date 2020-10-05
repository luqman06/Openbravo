package org.wirabumi.meikarta.report;

import org.openbravo.data.FieldProvider;

public class PurchaseOrderBean implements FieldProvider{
	
	//private field declaration
	private final String orderdate;
	private final String documentno;
	private final String bussiness_partner;
	private final String address;
	private final String warehouse;
	private final String warehouse_address;
	private final String line_no;
	private final String linedescription;
	private final String quantity;
	private final String unit_price;
	private final String totalline;
	private final String sub_total;
	private final String tax;
	private final String grand_total;
	private final String delivery_note;
	private final String termofpayment;
	private final String organizationid;
	private final String total;
	private final String status;
	
	private PurchaseOrderBean(Builder builder){
		this.orderdate = builder.orderdate;
		this.documentno = builder.documentno;
		this.bussiness_partner = builder.bussiness_partner;
		this.address = builder.address;
		this.warehouse = builder.warehouse;
		this.warehouse_address = builder.warehouse_address;
		this.line_no = builder.line_no;
		this.linedescription = builder.linedescription;
		this.quantity = builder.quantity;
		this.unit_price = builder.unit_price;
		this.totalline = builder.totalline;
		this.sub_total=builder.sub_total;
		this.tax=builder.tax;
		this.grand_total=builder.grand_total;
		this.delivery_note=builder.delivery_note;
		this.termofpayment=builder.termofpayment;
		this.organizationid=builder.organizationid;
		this.total=builder.total;
		this.status=builder.status;
	}
	
	@Override
	public String getField(String fieldName) {
		switch (fieldName) {
		case "orderdate":
			return this.orderdate;
			
		case "documentno":
			return this.documentno;
			
		case "address":
			return this.address;
			
		case "bussiness_partner":
			return this.bussiness_partner;
			
		case "warehouse":
			return this.warehouse;
			
		case "warehouse_address":
			return this.warehouse_address;
			
		case "line_no":
			return this.line_no;
			
		case "linedescription":
			return this.linedescription;
			
		case "quantity":
			return this.quantity;
			
		case "unit_price":
			return this.unit_price;
			
		case "sub_total":
			return this.sub_total;
			
		case "tax":
			return this.tax;
			
		case "grand_total":
			return this.grand_total;
			
		case "delivery_note":
			return this.delivery_note;
			
		case "termofpayment":
			return this.termofpayment;
			
		case "organizationid":
			return this.organizationid;
			
		case "total":
			return this.total;
			
		case "status":
			return this.status;
			
		case "totalline":
			return this.totalline;

		default:
			break;
		}
		
		return null;
	}
	
	
	
	
	public String getOrderdate() {
		return orderdate;
	}

	public String getDocumentno() {
		return documentno;
	}

	public String getBussiness_partner() {
		return bussiness_partner;
	}

	public String getAddress() {
		return address;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public String getWarehouse_address() {
		return warehouse_address;
	}

	public String getLine_no() {
		return line_no;
	}

	public String getLinedescription() {
		return linedescription;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public String getTotalline() {
		return totalline;
	}

	public String getSub_total() {
		return sub_total;
	}

	public String getTax() {
		return tax;
	}

	public String getGrand_total() {
		return grand_total;
	}

	public String getDelivery_note() {
		return delivery_note;
	}

	public String getTermofpayment() {
		return termofpayment;
	}

	public String getOrganizationid() {
		return organizationid;
	}

	public String getTotal() {
		return total;
	}

	public String getStatus() {
		return status;
	}




	public static class Builder{
		
		//private field
		private final String orderdate;
		private final String documentno;
		private final String bussiness_partner;
		private final String address;
		private final String linedescription;
		private final String quantity;
		private final String unit_price;
		private final String totalline;
		
		//optinal field
		private String line_no="";
		private String warehouse="";
		private String warehouse_address="";
		private String sub_total="";
		private String tax="";
		private String grand_total="";
		private String delivery_note="";
		private String termofpayment="";
		private String organizationid="";
		private String total="";
		private String status="";
		
		//private constructor
		public Builder(String orderdate, String documentno, String bussiness_partner, String address,
				String linedescription, String quantity, String unit_price, String totalline) {
			super();
			this.orderdate = orderdate;
			this.documentno = documentno;
			this.bussiness_partner = bussiness_partner;
			this.address = address;
			this.linedescription = linedescription;
			this.quantity = quantity;
			this.unit_price = unit_price;
			this.totalline = totalline;
		}
		
		public Builder lineNo(String lineNo){
			this.line_no=lineNo; return this;
		}
		
		public Builder warehouse(String warehouse){
			this.warehouse=warehouse; return this;
		}
		
		public Builder warehouseAddress(String warehouseAddress){
			this.warehouse_address=warehouseAddress; return this;
		}
		
		public Builder sub_total(String sub_total){
			this.sub_total=sub_total; return this;
		}
		
		public Builder tax(String tax){
			this.tax=tax; return this;
		}
		
		public Builder grand_total(String grand_total){
			this.grand_total=grand_total; return this;
		}
		
		public Builder delivery_note(String delivery_note){
			this.delivery_note=delivery_note; return this;
		}
		
		public Builder termofpayment(String termofpayment){
			this.termofpayment=termofpayment; return this;
		}
		
		public Builder organizationid(String organizationid){
			this.organizationid=organizationid; return this;
		}
		
		public Builder total(String total){
			this.total=total; return this;
		}
		
		public Builder status(String status){
			this.status=status; return this;
		}
		
		public PurchaseOrderBean build() {
			return new PurchaseOrderBean(this);
		}
		
	}
	
}
