package org.wirabumi.hris.payroll.bean;

public class SalaryComponentBean implements Comparable<SalaryComponentBean>{
	private boolean isEarning;
	private String searchKey;
	private String name;
	private String formulaType;
	private double constantAmount;
	private String javaScriptFormula;
	private String javaClassFormula;
	private String id;
	
	public SalaryComponentBean(boolean isEarning, String searchKey,
			String name, String formulaType, double constantAmount,
			String javaScriptFormula, String javaClassFormula, String id) {
		super();
		this.isEarning = isEarning;
		this.searchKey = searchKey;
		this.name = name;
		this.formulaType = formulaType;
		this.constantAmount = constantAmount;
		this.javaScriptFormula = javaScriptFormula;
		this.javaClassFormula = javaClassFormula;
		this.id = id;
	}

	public boolean isEarning() {
		return isEarning;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public String getName() {
		return name;
	}

	public String getFormulaType() {
		return formulaType;
	}

	public double getConstantAmount() {
		return constantAmount;
	}

	public String getJavaScriptFormula() {
		return javaScriptFormula;
	}

	public String getJavaClassFormula() {
		return javaClassFormula;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(constantAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((formulaType == null) ? 0 : formulaType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isEarning ? 1231 : 1237);
		result = prime
				* result
				+ ((javaClassFormula == null) ? 0 : javaClassFormula.hashCode());
		result = prime
				* result
				+ ((javaScriptFormula == null) ? 0 : javaScriptFormula
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((searchKey == null) ? 0 : searchKey.hashCode());
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
		SalaryComponentBean other = (SalaryComponentBean) obj;
		if (Double.doubleToLongBits(constantAmount) != Double
				.doubleToLongBits(other.constantAmount))
			return false;
		if (formulaType == null) {
			if (other.formulaType != null)
				return false;
		} else if (!formulaType.equals(other.formulaType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isEarning != other.isEarning)
			return false;
		if (javaClassFormula == null) {
			if (other.javaClassFormula != null)
				return false;
		} else if (!javaClassFormula.equals(other.javaClassFormula))
			return false;
		if (javaScriptFormula == null) {
			if (other.javaScriptFormula != null)
				return false;
		} else if (!javaScriptFormula.equals(other.javaScriptFormula))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (searchKey == null) {
			if (other.searchKey != null)
				return false;
		} else if (!searchKey.equals(other.searchKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.searchKey+"-"+this.name;
	}

	@Override
	public int compareTo(SalaryComponentBean o) {
		int a = this.hashCode();
		int b = o.hashCode();
		
		return a-b;
	}
	
	

}
