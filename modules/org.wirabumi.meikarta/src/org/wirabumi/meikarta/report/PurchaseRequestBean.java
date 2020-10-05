package org.wirabumi.meikarta.report;

import org.openbravo.data.FieldProvider;

public class PurchaseRequestBean implements FieldProvider{
	private final String documentNo;
	private final String department;
	private final String jobarea;
	private final String requester;
	private final String daterequested;
	private final String daterequired;
	private final String lineNo;
	private final String lineDescription;
	private final String headerdescription;
	private final String uom;
	private final String quantity;
	private final String costCode;
	private final String estimateValue;
	private final String totalEstimateValue;
	private final String remark;
	private final String isfood;
	private final String ismaterial;
	private final String isservice;
	private final String releaseddate;
	private final String realesedby;
	private final String revieweddate;
	private final String reviewedby;
	private final String approveddate;
	private final String approvedby;
	
	private PurchaseRequestBean(Builder builder) {
		this.documentNo = builder.documentNo;
		this.department = builder.department;
		this.jobarea = builder.jobarea;
		this.requester = builder.requester;
		this.daterequested = builder.daterequested;
		this.daterequired = builder.daterequired;
		this.lineNo = builder.lineNo;
		this.lineDescription = builder.lineDescription;
		this.uom = builder.uom;
		this.quantity = builder.quantity;
		this.costCode = builder.costCode;
		this.estimateValue = builder.estimateValue;
		this.totalEstimateValue = builder.totalEstimateValue;
		this.remark = builder.remark;
		this.isfood = builder.isfood;
		this.ismaterial = builder.ismaterial;
		this.isservice = builder.isservice;
		this.releaseddate = builder.releaseddate;
		this.realesedby = builder.realesedby;
		this.revieweddate = builder.revieweddate;
		this.reviewedby = builder.reviewedby;
		this.approveddate = builder.approveddate;
		this.approvedby = builder.approvedby;
		this.headerdescription = builder.headerdescription;
	}

	@Override
	public String getField(String fieldName) {

		switch (fieldName) {
		case "documentNo": return this.documentNo;
		case "department": return this.department;
		case "jobarea": return this.jobarea;
		case "requester": return this.requester;
		case "daterequested": return this.daterequested;
		case "daterequired": return this.daterequired;
		case "lineNo": return this.lineNo;
		case "lineDescription": return this.lineDescription;
		case "uom": return this.uom;
		case "quantity": return this.quantity;
		case "costCode": return this.costCode;
		case "estimateValue": return this.estimateValue;
		case "totalEstimateValue": return this.totalEstimateValue;
		case "remark": return this.remark;
		case "isfood": return this.isfood;
		case "ismaterial": return this.ismaterial;
		case "isservice": return this.isservice;
		case "releaseddate": return this.releaseddate;
		case "realesedby": return this.realesedby;
		case "revieweddate": return this.revieweddate;
		case "reviewedby": return this.reviewedby;
		case "approveddate": return this.approveddate;
		case "approvedby": return this.approvedby;
		case "headerdescription": return this.headerdescription;

		default: return null;
			
		}
		
	}
	
	public static class Builder{
		
		//mandatory
		private final String documentNo;
		private final String department;
		private final String requester;
		private final String daterequested;
		private final String lineDescription;
		private final String uom;
		private final String quantity;
		
		//opsional
		private String estimateValue="N/A";
		private String totalEstimateValue="N/A";
		private String jobarea="N/A";
		private String daterequired="N/A";
		private String lineNo="N/A";
		private String costCode="N/A";
		private String remark="N/A";
		private String isfood="N/A";
		private String ismaterial="N/A";
		private String isservice="N/A";
		private String releaseddate=" date:";
		private String realesedby="(______________)";
		private String revieweddate=" date:";
		private String reviewedby="(______________)";
		private String approveddate=" date:";
		private String approvedby="(______________)";
		private String headerdescription;
		
		public Builder(String documentNo, String department, String requester, String daterequested,
				String lineDescription, String uom, String quantity) {
			super();
			this.documentNo = documentNo;
			this.department = department;
			this.requester = requester;
			this.daterequested = daterequested;
			this.lineDescription = lineDescription;
			this.uom = uom;
			this.quantity = quantity;
		}
		
		/*
		private final String reviewedby="";
		private final String approveddate="";
		private final String approvedby="";
		 */
		
		public Builder estimateValue(String estimateValue){
			this.estimateValue=estimateValue; return this;
		}
		
		public Builder totalEstimateValue(String totalEstimateValue){
			this.totalEstimateValue=totalEstimateValue; return this;
		}
		public Builder jobarea(String jobarea){
			this.jobarea=jobarea; return this;
		}
		
		public Builder daterequired(String daterequired){
			this.daterequired=daterequired; return this;
		}
		
		public Builder lineNo(String lineNo){
			this.lineNo=lineNo; return this;
		}
		
		public Builder costCode(String costCode){
			this.costCode=costCode; return this;
		}
		
		public Builder remark(String remark){
			this.remark=remark; return this;
		}
		
		public Builder isfood(String isfood){
			this.isfood=isfood; return this;
		}
		
		public Builder ismaterial(String ismaterial){
			this.ismaterial=ismaterial; return this;
		}
		
		public Builder isservice(String isservice){
			this.isservice=isservice; return this;
		}
		
		public Builder realesedby(String realesedby){
			this.realesedby=realesedby; return this;
		}
		
		public Builder revieweddate(String revieweddate){
			this.revieweddate=revieweddate; return this;
		}
		
		public Builder reviewedby(String reviewedby){
			this.reviewedby=reviewedby; return this;
		}
		
		public Builder approveddate(String approveddate){
			this.approveddate=approveddate; return this;
		}
		
		public Builder approvedby(String approvedby){
			this.approvedby=approvedby; return this;
		}
		
		public Builder headerdescription(String headerdescription){
			this.headerdescription=headerdescription; return this;
		}
		
		public PurchaseRequestBean build(){
			return new PurchaseRequestBean(this);
		}
		
	}

	//getter
	public String getDocumentNo() {
		return documentNo;
	}

	public String getDepartment() {
		return department;
	}

	public String getJobarea() {
		return jobarea;
	}

	public String getRequester() {
		return requester;
	}

	public String getDaterequested() {
		return daterequested;
	}

	public String getDaterequired() {
		return daterequired;
	}

	public String getLineNo() {
		return lineNo;
	}

	public String getLineDescription() {
		return lineDescription;
	}

	public String getHeaderdescription() {
		return headerdescription;
	}

	public String getUom() {
		return uom;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getCostCode() {
		return costCode;
	}

	public String getEstimateValue() {
		return estimateValue;
	}

	public String getTotalEstimateValue() {
		return totalEstimateValue;
	}

	public String getRemark() {
		return remark;
	}

	public String getIsfood() {
		return isfood;
	}

	public String getIsmaterial() {
		return ismaterial;
	}

	public String getIsservice() {
		return isservice;
	}

	public String getReleaseddate() {
		return releaseddate;
	}

	public String getRealesedby() {
		return realesedby;
	}

	public String getRevieweddate() {
		return revieweddate;
	}

	public String getReviewedby() {
		return reviewedby;
	}

	public String getApproveddate() {
		return approveddate;
	}

	public String getApprovedby() {
		return approvedby;
	}
	
}
