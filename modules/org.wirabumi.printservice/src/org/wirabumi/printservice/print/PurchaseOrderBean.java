package org.wirabumi.printservice.print;

import org.openbravo.data.FieldProvider;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.common.enterprise.Warehouse;
import org.wirabumi.printservice.utility.PrintServiceUtility;

public class PurchaseOrderBean implements FieldProvider{
	
	//private field declaration
	private final String orderdate;
	
	private final String documentno;
	private final String created_by;
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
	private final String user_email;
	private final String costcenter;
	private final String phone;
	private final String vendor_contact;
	private final String vendor_contact_email;
	private final String currency_iso;
	private final String payment_term;
	private final String delivery_notes;
	private final String address_organization;
	private final String product_name;
	private final String satuan;
	private final String linedescription_line;

	
	private PurchaseOrderBean(Builder builder){
		this.orderdate = builder.orderdate;
		
		this.documentno = builder.documentno;
		this.created_by = builder.created_by;
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
		this.user_email=builder.user_email;
		this.costcenter=builder.costcenter;
		this.phone=builder.phone;
		this.vendor_contact=builder.vendor_contact;
		this.vendor_contact_email=builder.vendor_contact_email;
		this.currency_iso=builder.currency_iso;
		this.payment_term=builder.payment_term;
		this.delivery_notes=builder.delivery_notes;
		this.address_organization=builder.address_organization;
		this.product_name=builder.product_name;
		this.satuan=builder.satuan;
		this.linedescription_line=builder.linedescription_line;
	
		
	}
	
	@Override
	public String getField(String fieldName) {
		switch (fieldName) {
		case "orderdate":
			return this.orderdate;
			
		case "documentno":
			return this.documentno;
			
		case "created_by" :
			return this.created_by;
			
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
			
		case "user_email":
			return this.user_email;
			
		case "costcenter":
			return this.costcenter;
			
		case "phone":
			return this.phone;
			
		case "vendor_contact":
			return this.vendor_contact;
			
		case "vendor_contact_email":
			return this.vendor_contact_email;
		
		case "currency_iso":
			return this.currency_iso;
		
		case "payment_term":
			return this.payment_term;
			
		case "delivery_notes":
			return this.delivery_notes;
			
		case "address_organization":
			return this.address_organization;
		
		case "product_name":
			return this.product_name;
		
		case "satuan":
			return this.satuan;
			
		case "totalline":
			return this.totalline;
			
		case "linedescription_line":
			return this.linedescription_line;

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
	
	public String getCreated_by(){
		return created_by;
	}

	public String getUser_email(){
		return user_email;
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
	
	public String getCostcenter(){
		return costcenter;
	}

	public String getPhone(){
		return phone;
	}
	
	public String getVendor_contact(){
		return vendor_contact;
	}
	
	public String getVendor_contact_email(){
		return vendor_contact_email;
	}
	
	public String getCurrency_iso(){
		return currency_iso;
	}
	
	public String getPayment_term(){
		return payment_term;
	}
	
	public String getDelivery_notes(){
		return delivery_notes;
	}

	public String getAddress_organization(){
		return address_organization;
	}
	
	public String getProduct_name(){
		return product_name;
	}
	
	public String getSatuan(){
		return satuan;
	}
	
	public String getLinedescription_line(){
		return linedescription_line;
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
		private String warehouse="N/A";
		private String warehouse_address="N/A";
		private String sub_total="";
		private String tax="";
		private String grand_total="";
		private String delivery_note="";
		private String termofpayment="";
		private String organizationid="";
		private String total="";
		private String status="";
		private String created_by="";
		private String user_email="";
		private String costcenter="";
		private String phone="";
		private String vendor_contact="";
		private String vendor_contact_email="";
		private String currency_iso="";
		private String payment_term="";
		private String delivery_notes="";
		private String address_organization="";
		private String product_name="";
		private String satuan="";
		private String linedescription_line="";
		
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
		
		public Builder warehouse(Warehouse warehouse){
			if (warehouse!=null){
				this.warehouse=warehouse.getName();
				this.warehouse_address=PrintServiceUtility.getLocationAddressFormat(warehouse.getLocationAddress()); 
			}
				 
			return this;
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
		
		public Builder created_by(String created_by){
			this.created_by=created_by; return this;
		}
		
		public Builder user_email(String user_email){
			this.user_email=user_email; return this;
		}
		
		public Builder costcenter(String costcenter){
			this.costcenter=costcenter;return this;
		}
		
		public Builder vendor_info(User vendor){
			if (vendor!=null){
				if (vendor.getPhone()!=null)
					this.phone=vendor.getPhone();
				if (vendor.getName()!=null)
					this.vendor_contact=vendor.getName();
				if (vendor.getEmail()!=null)
					this.vendor_contact_email=vendor.getEmail();
			}
			return this;
		}
		
		public Builder currency_iso(String currency_iso){
			this.currency_iso=currency_iso; return this;
		}
		
		public Builder payment_term(String payment_term){
			this.payment_term=payment_term; return this;
		}
		
		public Builder delivery_notes(String delivery_notes){
			this.delivery_notes=delivery_notes; return this;
		}
		
		public Builder address_organization(String address_organization){
			this.address_organization=address_organization; return this;
		}
		
		public Builder product_name(String product_name){
			this.product_name=product_name; return this;
		}
		
		public Builder satuan(String satuan){
			this.satuan=satuan; return this;
		}
		
		public Builder linedescription_line(String linedescription_line){
			this.linedescription_line=linedescription_line; return this;
		}
		
		public PurchaseOrderBean build() {
			return new PurchaseOrderBean(this);
		}
		
	}
	
}
