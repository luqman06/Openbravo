package org.wirabumi.hris.employee.master.ad_process;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.OBContext;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class RunClosure extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		int iExitValue=0;
		
		VariablesSecureApp varsAux = bundle.getContext().toVars();
	    HttpServletRequest request = RequestContext.get().getRequest();
	    OBContext.setOBContext(varsAux.getUser(), varsAux.getRole(), varsAux.getClient(),
	        varsAux.getOrg());
	    VariablesSecureApp vars = new VariablesSecureApp(request);
	    
	    String closureCommand = Utility.getPreference(vars, "closurecommand", null);
	    if (closureCommand==null || closureCommand.isEmpty())
	    	throw new OBException("@hris_cannotfindclosurecommand@");
	    
		CommandLine commandline = CommandLine.parse(closureCommand);
		DefaultExecutor defaultexecutor = new DefaultExecutor();
		defaultexecutor.setExitValue(0);
		try {
			iExitValue = defaultexecutor.execute(commandline);
		} catch (ExecuteException e) {
			System.err.println("Execution failed: "+iExitValue);
            e.printStackTrace();
		} catch (IOException e) {
			System.err.println("permission denied: "+iExitValue);
            e.printStackTrace();
		}
		
		final OBError msg = new OBError();
		msg.setType("Success");
	    msg.setTitle("Success");
	    msg.setMessage("employee closure berhasil di-update");
		bundle.setResult(msg);

	}

}
