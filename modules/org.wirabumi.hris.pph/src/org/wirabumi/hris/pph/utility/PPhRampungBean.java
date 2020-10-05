package org.wirabumi.hris.pph.utility;

import org.openbravo.base.exception.OBException;
import org.openbravo.model.common.businesspartner.BusinessPartner;

public class PPhRampungBean {
	private BusinessPartner employee;
	private double grossIncome, netIncome, nonTaxIncome, deductibleDeduction, paidIncomeTax;
	public PPhRampungBean(BusinessPartner employee, double grossIncome,
			double netIncome, double nonTaxIncome, double deductibleDeduction,
			double paidIncomeTax) {
		super();
		if (employee==null)
			throw new OBException("employee object can not null");
		this.employee = employee;
		this.grossIncome = grossIncome;
		this.netIncome = netIncome;
		this.nonTaxIncome = nonTaxIncome;
		this.deductibleDeduction = deductibleDeduction;
		this.paidIncomeTax = paidIncomeTax;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(deductibleDeduction);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		temp = Double.doubleToLongBits(grossIncome);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(netIncome);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(nonTaxIncome);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(paidIncomeTax);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PPhRampungBean other = (PPhRampungBean) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		return true;
	}
	public double getGrossIncome() {
		return grossIncome;
	}
	public void setGrossIncome(double grossIncome) {
		this.grossIncome = grossIncome;
	}
	public double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(double netIncome) {
		this.netIncome = netIncome;
	}
	public double getNonTaxIncome() {
		return nonTaxIncome;
	}
	public void setNonTaxIncome(double nonTaxIncome) {
		this.nonTaxIncome = nonTaxIncome;
	}
	public double getDeductibleDeduction() {
		return deductibleDeduction;
	}
	public void setDeductibleDeduction(double deductibleDeduction) {
		this.deductibleDeduction = deductibleDeduction;
	}
	public double getPaidIncomeTax() {
		return paidIncomeTax;
	}
	public void setPaidIncomeTax(double paidIncomeTax) {
		this.paidIncomeTax = paidIncomeTax;
	}
	public BusinessPartner getEmployee() {
		return employee;
	}
	@Override
	public String toString() {
		return "PPhRampungBean [employee=" + employee + ", grossIncome="
				+ grossIncome + ", netIncome=" + netIncome + ", nonTaxIncome="
				+ nonTaxIncome + ", deductibleDeduction=" + deductibleDeduction
				+ ", paidIncomeTax=" + paidIncomeTax + "]";
	}
	
	
	
	
}
