
package org.openbravo.erpWindows.org.wirabumi.hris.employee.master.ImportEmployeeCandidate;




import org.openbravo.erpCommon.utility.*;
import org.openbravo.data.FieldProvider;
import org.openbravo.utils.FormatUtilities;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.exception.OBException;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessRunner;
import org.openbravo.xmlEngine.XmlDocument;
import org.openbravo.database.SessionInfo;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

// Generated old code, not worth to make i.e. java imports perfect
@SuppressWarnings("unused")
public class ImportEmployeeCandidate9C9914E7660644619BB8BE8F31740FC1 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "4D26E44245394F20A232A6B4185C8EC1";
  private static final String tabId = "9C9914E7660644619BB8BE8F31740FC1";
  private static final int accesslevel = 3;
  private static final String moduleId = "D5FD35D6C7604921BA951B7E0EEE5DC2";
  
  @Override
  public void init(ServletConfig config) {
    setClassInfo("W", tabId, moduleId);
    super.init(config);
  }
  
  
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String command = vars.getCommand();
    
    boolean securedProcess = false;
    if (command.contains("BUTTON")) {
     List<String> explicitAccess = Arrays.asList( "");
    
     SessionInfo.setUserId(vars.getSessionValue("#AD_User_ID"));
     SessionInfo.setSessionId(vars.getSessionValue("#AD_Session_ID"));
     SessionInfo.setQueryProfile("manualProcess");
     
      if (command.contains("7D1FD4B0FCBB4CC698DD502C8BB4DC4F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("7D1FD4B0FCBB4CC698DD502C8BB4DC4F");
        SessionInfo.setModuleId("D5FD35D6C7604921BA951B7E0EEE5DC2");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("7D1FD4B0FCBB4CC698DD502C8BB4DC4F") || (securedProcess && command.contains("7D1FD4B0FCBB4CC698DD502C8BB4DC4F"))) {
        classInfo.type = "P";
        classInfo.id = "7D1FD4B0FCBB4CC698DD502C8BB4DC4F";
      }
     
    }
    if (!securedProcess) {
      setClassInfo("W", tabId, moduleId);
    }
    super.service(request, response);
  }
  

  public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    Boolean saveRequest = (Boolean) request.getAttribute("autosave");
    
    if(saveRequest != null && saveRequest){
      throw new OBException("2.50 style request.autosave is no longer supported: " + vars.getCommand());
    }
    
    if (vars.commandIn("DEFAULT", "DIRECT", "TAB", "SEARCH", "RELATION", "NEW", "EDIT", "NEXT",
        "PREVIOUS", "FIRST_RELATION", "PREVIOUS_RELATION", "NEXT_RELATION", "LAST_RELATION",
        "LAST", "SAVE_NEW_RELATION", "SAVE_NEW_NEW", "SAVE_NEW_EDIT", "SAVE_EDIT_RELATION",
        "SAVE_EDIT_NEW", "SAVE_EDIT_EDIT", "SAVE_EDIT_NEXT", "DELETE", "SAVE_XHR")) {
      throw new OBException("2.50 style command is no longer supported: " + vars.getCommand());

    } else if (vars.commandIn("BUTTONimportemployeecandidate7D1FD4B0FCBB4CC698DD502C8BB4DC4F")) {
        vars.setSessionValue("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.strimportemployeecandidate", vars.getStringParameter("inpimportemployeecandidate"));
        vars.setSessionValue("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "7D1FD4B0FCBB4CC698DD502C8BB4DC4F", request.getServletPath());
      } else if (vars.commandIn("BUTTON7D1FD4B0FCBB4CC698DD502C8BB4DC4F")) {
        String strHris_I_Employee_Candidate_ID = vars.getGlobalVariable("inphrisIEmployeeCandidateId", windowId + "|Hris_I_Employee_Candidate_ID", "");
        String strimportemployeecandidate = vars.getSessionValue("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.strimportemployeecandidate");
        String strProcessing = vars.getSessionValue("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.strProcessing");
        String strOrg = vars.getSessionValue("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.strOrg");
        String strClient = vars.getSessionValue("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonimportemployeecandidate7D1FD4B0FCBB4CC698DD502C8BB4DC4F(response, vars, strHris_I_Employee_Candidate_ID, strimportemployeecandidate, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONimportemployeecandidate7D1FD4B0FCBB4CC698DD502C8BB4DC4F")) {
        String strHris_I_Employee_Candidate_ID = vars.getGlobalVariable("inpKey", windowId + "|Hris_I_Employee_Candidate_ID", "");
        
        ProcessBundle pb = new ProcessBundle("7D1FD4B0FCBB4CC698DD502C8BB4DC4F", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Hris_I_Employee_Candidate_ID", strHris_I_Employee_Candidate_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strhrisEmployeeCandidateId = vars.getStringParameter("inphrisEmployeeCandidateId");
params.put("hrisEmployeeCandidateId", strhrisEmployeeCandidateId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new ProcessRunner(pb).execute(this);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          } else vars.setMessage(tabId, myMessage);
        }
        //close popup
        if (myMessage!=null) {
          if (log4j.isDebugEnabled()) log4j.debug(myMessage.getMessage());
          vars.setMessage(tabId, myMessage);
        }
        printPageClosePopUp(response, vars);



    } else if (vars.getCommand().toUpperCase().startsWith("BUTTON") || vars.getCommand().toUpperCase().startsWith("SAVE_BUTTON")) {
      pageErrorPopUp(response);
    } else pageError(response);
  }

  private void printPageButtonFS(HttpServletResponse response, VariablesSecureApp vars, String strProcessId, String path) throws IOException, ServletException {
    log4j.debug("Output: Frames action button");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
        "org/openbravo/erpCommon/ad_actionButton/ActionButtonDefaultFrames").createXmlDocument();
    xmlDocument.ignoreTranslation = true;
    xmlDocument.setParameter("processId", strProcessId);
    xmlDocument.setParameter("trlFormType", "PROCESS");
    xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    xmlDocument.setParameter("type", strDireccion + path);
    out.println(xmlDocument.print());
    out.close();
  }



    void printPageButtonimportemployeecandidate7D1FD4B0FCBB4CC698DD502C8BB4DC4F(HttpServletResponse response, VariablesSecureApp vars, String strHris_I_Employee_Candidate_ID, String strimportemployeecandidate, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7D1FD4B0FCBB4CC698DD502C8BB4DC4F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/importemployeecandidate7D1FD4B0FCBB4CC698DD502C8BB4DC4F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strHris_I_Employee_Candidate_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ImportEmployeeCandidate9C9914E7660644619BB8BE8F31740FC1_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "7D1FD4B0FCBB4CC698DD502C8BB4DC4F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("7D1FD4B0FCBB4CC698DD502C8BB4DC4F");
        vars.removeMessage("7D1FD4B0FCBB4CC698DD502C8BB4DC4F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("hris_employee_candidate_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "hris_employee_candidate_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button7D1FD4B0FCBB4CC698DD502C8BB4DC4F.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reporthris_employee_candidate_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
