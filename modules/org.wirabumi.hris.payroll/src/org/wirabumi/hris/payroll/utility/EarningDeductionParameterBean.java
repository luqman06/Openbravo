package org.wirabumi.hris.payroll.utility;

import org.openbravo.base.exception.OBException;
import org.wirabumi.hris.employee.master.data.hris_site;

public class EarningDeductionParameterBean {
	
	private String kodeParameter;
	private String jenjangJabatan;
	private String jabatan;
	private hris_site site;
	
	@Override
	protected EarningDeductionParameterBean clone() throws CloneNotSupportedException {
		return new EarningDeductionParameterBean(this.kodeParameter, this.jenjangJabatan, this.jabatan, this.site);
	}
	
	public EarningDeductionParameterBean(String kodeParameter, String jenjangJabatan,
			String jabatan, hris_site site) {
		super();
		if (kodeParameter==null)
			throw new OBException("kode parameter tidak boleh null");
		
		this.kodeParameter = kodeParameter;
		this.jenjangJabatan = jenjangJabatan;
		this.jabatan = jabatan;
		this.site = site;
	}
	public String getKodeParameter() {
		return kodeParameter;
	}
	public String getJenjangJabatan() {
		return jenjangJabatan;
	}
	public String getJabatan() {
		return jabatan;
	}
	public hris_site getSite(){
		return site;
	}
	
	@Override
	public String toString() {
		String output = "EarningParamBean [kodeParameter=" + kodeParameter
				+ ", jenjangJabatan=";
		if (jenjangJabatan!=null)
			output += jenjangJabatan;
		else
			output += "NULL";
		
		output += ", jabatan=";
		
		if (jabatan!=null)
			output += jabatan;
		else
			output += "NULL";
		
		output += ", site=";
		
		if (site!=null)
			output += site.getCommercialName();
		else
			output += "NULL";
		
		output += "]";
		
		return output;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jabatan == null) ? 0 : jabatan.hashCode());
		result = prime * result
				+ ((jenjangJabatan == null) ? 0 : jenjangJabatan.hashCode());
		result = prime * result
				+ ((kodeParameter == null) ? 0 : kodeParameter.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
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
		EarningDeductionParameterBean other = (EarningDeductionParameterBean) obj;
		if (jabatan == null) {
			if (other.jabatan != null)
				return false;
		} else if (!jabatan.equals(other.jabatan))
			return false;
		if (jenjangJabatan == null) {
			if (other.jenjangJabatan != null)
				return false;
		} else if (!jenjangJabatan.equals(other.jenjangJabatan))
			return false;
		if (kodeParameter == null) {
			if (other.kodeParameter != null)
				return false;
		} else if (!kodeParameter.equals(other.kodeParameter))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		return true;
	}
	
	
}
