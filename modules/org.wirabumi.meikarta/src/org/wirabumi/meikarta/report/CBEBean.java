package org.wirabumi.meikarta.report;

import org.openbravo.data.FieldProvider;

public class CBEBean implements FieldProvider {
	private final String lineNo;
	private final String product;
	private final String quantity;
	private final String uom;
	private final String warehouse;
	private final String organization;
	private final String description;
	private final String name;
	private final String vendor1;
	private final String vendor2;
	private final String vendor3;
	private final String unitprice1;
	private final String unitprice2;
	private final String unitprice3;
	private final String totalprice1;
	private final String totalprice2;
	private final String totalprice3;
	private final String jobarea;
	private final String requester;
	
	private CBEBean(Builder builder){
		this.lineNo=builder.lineNo;
		this.product=builder.product;
		this.quantity=builder.quantity;
		this.uom=builder.uom;
		this.warehouse=builder.warehouse;
		this.organization=builder.organization;		
		this.description=builder.description;
		this.name=builder.name;
		this.vendor1=builder.vendor1;
		this.vendor2=builder.vendor2;
		this.vendor3=builder.vendor3;
		this.unitprice1=builder.unitprice1;
		this.unitprice2=builder.unitprice2;
		this.unitprice3=builder.unitprice3;
		this.totalprice1=builder.totalprice1;
		this.totalprice2=builder.totalprice2;
		this.totalprice3=builder.totalprice3;
		this.jobarea=builder.jobarea;
		this.requester=builder.requester;
	
	}
	
	public String getLineNo() {
		return lineNo;
	}
	
	public String getProduct() {
		return product;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getUom() {
		return uom;
	}
	
	public String getWarehouse(){
		return warehouse;
	}
	public String getOrganization(){
		return organization;
	}
	public String getDescription(){
		return description;
	}
	
	
	public String getName(){
		return name;
	}
	
	public String getunitprice1(){
		return unitprice1;
	}
	
	public String getunitprice2(){
		return unitprice2;
	}
	
	public String getunitprice3(){
		return unitprice3;
	}
	
	public String gettotalprice1(){
		return totalprice1;
	}
	
	public String jobarea(){
		return jobarea;
	}
	
	public String requester(){
		return requester;
	}
	
	public String getVendor1() {
		return vendor1;
	}

	public String getVendor2() {
		return vendor2;
	}

	public String getVendor3() {
		return vendor3;
	}

	public String getUnitprice1() {
		return unitprice1;
	}

	public String getUnitprice2() {
		return unitprice2;
	}

	public String getUnitprice3() {
		return unitprice3;
	}

	public String getTotalprice1() {
		return totalprice1;
	}

	public String getTotalprice2() {
		return totalprice2;
	}

	public String getTotalprice3() {
		return totalprice3;
	}

	public String getJobarea() {
		return jobarea;
	}

	public String getRequester() {
		return requester;
	}



	public static class Builder{
		private String lineNo="";
		private String product="";
		private String quantity="";
		private String uom="";
		private String warehouse="";
		private String organization="";
		private String description="";
		private String name="";
		private String vendor1="";
		private String vendor2="";
		private String vendor3="";
		private String unitprice1="";
		private String unitprice2="";
		private String unitprice3="";
		private String totalprice1="";
		private String totalprice2="";
		private String totalprice3="";
		private String jobarea="";
		private String requester="";
		
		public Builder lineNo(String lineNo){
			this.lineNo=lineNo; return this;
		}
		
		public Builder product(String product){
			this.product=product; return this;
		}
		
		public Builder quantity(String quantity){
			this.quantity=quantity; return this;
		}
		
		public Builder uom(String uom){
			this.uom=uom; return this;
		}
		
		public Builder warehouse(String warehouse){
			this.warehouse=warehouse; return this;
		}
		
		
		public Builder organization(String organization){
			this.organization=organization; 
			return this;
		}
		
		public Builder description(String description){
			this.description=description;
			return this;
		}
		
		public Builder name(String name){
			this.name=name;
			return this;
		}
		
		public Builder vendor1(String vendor1){
			this.vendor1=vendor1;
			return this;
		}
		
		public Builder vendor2(String vendor2){
			this.vendor2=vendor2;
			return this;
		}
		
		public Builder vendor3(String vendor3){
			this.vendor3=vendor3;
			return this;
		}
		
		public Builder unitprice1(String unitprice1){
			this.unitprice1=unitprice1;
			return this;
		}
		

		
		public Builder unitprice2(String unitprice2){
			this.unitprice2=unitprice2;
			return this;
		}
		
		public Builder unitprice3(String unitprice3){
			this.unitprice3=unitprice3;
			return this;
		}
		
		public Builder totalprice1(String totalprice1){
			this.totalprice1=totalprice1;
			return this;
		}
		
		public Builder totalprice2(String totalprice2){
			this.totalprice2=totalprice2;
			return this;
		}
		
		public Builder totalprice3(String totalprice3){
			this.totalprice3=totalprice3;
			return this;
		}
		
		public Builder jobarea(String jobarea) {
			this.jobarea=jobarea;
			return this;
			
		}
		
		public Builder requester(String requester){
			this.requester=requester;
			return this;
		}
	
		public CBEBean build(){
			return new CBEBean(this);
		}
		
	}

	@Override
	public String getField(String fieldname) {
		switch (fieldname) {
		case "lineNo": return this.lineNo;
		case "product": return this.product;
		case "quantity": return this.quantity;
		case "uom": return this.uom;
		case "warehouse": return this.warehouse;
		case "organization": return this.organization;
		case "description" : return this.description;
		case "name" : return this.name;
		case "vendor1" : return this.vendor1;
		case "vendor2" : return this.vendor2;
		case "vendor3" : return this.vendor3;
		case "unitprice1" : return this.unitprice1;
		case "unitprice2" : return this.unitprice2;
		case "unitprice3" : return this.unitprice3;
		case "totalprice1" : return this.totalprice1;
		case "totalprice2" : return this.totalprice2;
		case "totalprice3" : return this.totalprice3;
		case "jobarea": return this.jobarea;
		case "requester" : return this.requester;
		default: return null;			
		}
	}

}
