package org.wirabumi.hris.employee.master.ad_process;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.wirabumi.hris.employee.master.data.KPIMeasurement;

public class ApproveKPI extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		String dateFormatString = OBPropertiesProvider.getInstance().getOpenbravoProperties().getProperty("dateFormat.java");
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		    // Recover context and variables
		ConnectionProvider conn = bundle.getConnection();
		VariablesSecureApp varsAux = bundle.getContext().toVars();
		HttpServletRequest request = RequestContext.get().getRequest();

		OBContext.setOBContext(varsAux.getUser(), varsAux.getRole(), varsAux.getClient(),varsAux.getOrg());
		VariablesSecureApp vars = new VariablesSecureApp(request);
		    try {
		        final String KPIId = (String) bundle.getParams().get("Hris_Kpi_Measurement_ID");
		        String docAction = vars.getStringParameter("inpdocaction");
		        String docStatus = vars.getStringParameter("inpdocstatus");
		        if("RE".equals(docAction)){
		          docStatus = "DR";
		        }else{
		          docStatus=docAction;
		        }
		        KPIMeasurement kpiMeasurement = OBDal.getInstance().get(KPIMeasurement.class, KPIId);
		        kpiMeasurement.setDocumentStatus(docStatus);
		        OBDal.getInstance().flush();
		        OBDal.getInstance().refresh(kpiMeasurement);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

}
