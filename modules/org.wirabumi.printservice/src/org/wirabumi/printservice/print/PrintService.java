package org.wirabumi.printservice.print;

import java.util.HashMap;
import java.util.List;

import org.openbravo.data.FieldProvider;

public interface PrintService {
	
	public String getReportTemplatePath();
	public String getReportOutputFileName();
	public HashMap<String, Object> getReportParameter();
	public FieldProvider[] getDataSourceByArray();
	public List<FieldProvider> getDataSourceByCollection();
	public String getReportOutputPDFFile();
	
}
