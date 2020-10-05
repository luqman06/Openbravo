package org.wirabumi.printservice.print;

import org.openbravo.data.FieldProvider;
import org.openbravo.model.ad.access.User;

public class BastBean implements FieldProvider{
	
	//private field declaration
	private final String description;
	private final String orderdate;
	private final String documentno;
	private final String grdate;
	
	private final String organization_gr;
	
	private final String vendor_contact;
	private final String vendor_title;
	private final String bussiness_partner;
	
	private final String line_no;
	private final String quantity;
	private final String satuan;
	private final String product_name;
		
	private BastBean(Builder builder){
		this.description = builder.description;
		this.orderdate = builder.orderdate;
		this.documentno = builder.documentno;
		this.grdate = builder.grdate;
		
		this.organization_gr = builder.organization_gr;
		
		this.vendor_contact=builder.vendor_contact;
		this.vendor_title = builder.vendor_title;
		this.bussiness_partner = builder.bussiness_partner;
		
		this.line_no = builder.line_no;
		this.quantity = builder.quantity;
		this.satuan=builder.satuan;
		this.product_name=builder.product_name;
		
	}
	
	@Override
	public String getField(String fieldName) {
		switch (fieldName) {
		
		case "description":
			return this.description;
		
		case "orderdate":
			return this.orderdate;
			
		case "documentno":
			return this.documentno;
			
		case "grdate":
			return this.grdate;
			
		case "organization_gr":
			return this.organization_gr;
			
		case "bussiness_partner":
			return this.bussiness_partner;
			
		case "line_no":
			return this.line_no;
			
		case "quantity":
			return this.quantity;
			
		case "vendor_title":
			return this.vendor_title;
			
		case "vendor_contact":
			return this.vendor_contact;

		case "product_name":
			return this.product_name;
		
		case "satuan":
			return this.satuan;
			
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
	
	public String getDescription(){
		return description;
	}

	public String getGrdate(){
		return grdate;
	}
	public String getBussiness_partner() {
		return bussiness_partner;
	}

	public String getOrganization_gr() {
		return organization_gr;
	}

	public String getVendor_title() {
		return vendor_title;
	}

	public String getLine_no() {
		return line_no;
	}

	public String getQuantity() {
		return quantity;
	}
	
	public String getVendor_contact(){
		return vendor_contact;
	}
	
	
	public String getProduct_name(){
		return product_name;
	}
	
	public String getSatuan(){
		return satuan;
	}
	
	public static class Builder{
		
		
		//private field
		private final String orderdate;
		private final String documentno;
		private final String bussiness_partner;
		private final String quantity;
	
		//optinal field
		private String line_no="";
		private String description="";
		private String grdate="";
		private String organization_gr="";
		private String vendor_title="";
		private String vendor_contact="";
		private String product_name="";
		private String satuan="";
		
		//private constructor
		public Builder(String orderdate, String documentno, String bussiness_partner,
				String quantity) {
			super();
			this.orderdate = orderdate;
			this.documentno = documentno;
			this.bussiness_partner = bussiness_partner;
			this.quantity = quantity;
		}
		
		public Builder lineNo(String lineNo){
			this.line_no=lineNo; return this;
		}
		
		
		public Builder vendor_contact(String vendor_contact){
			this.vendor_contact=vendor_contact; return this;
		}
		
		public Builder product_name(String product_name){
			this.product_name=product_name; return this;
		}
		
		public Builder satuan(String satuan){
			this.satuan=satuan; return this;
		}
		
		public Builder description(String description){
			this.description=description;return this;
		}
		
		public Builder grdate(String grdate){
			this.grdate=grdate; return this;
		}
		
		public Builder organization_gr(String organization_gr){
			this.organization_gr=organization_gr; return this;
		}
		
		
		public Builder vendor_info(User vendor){
			if (vendor!=null){
				if(vendor.getPosition()!=null);
					this.vendor_title=vendor.getPosition();
				if (vendor.getName()!=null)
					this.vendor_contact=vendor.getName();
		
			}
			return this;
		}
		public BastBean build() {
			return new BastBean(this);
		}
		
	}
	
}
