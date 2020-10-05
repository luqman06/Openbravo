
package org.openbravo.erpWindows.org.wirabumi.hris.termination.EmployeeTermination;


import org.openbravo.erpCommon.reference.*;


import org.openbravo.erpCommon.ad_actionButton.*;


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
public class EmployeeTerminationC8BDF801F9B24CBDA9816D68BC8FB48D extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "B7660CB3E6B64A18B279E9910F6F75BA";
  private static final String tabId = "C8BDF801F9B24CBDA9816D68BC8FB48D";
  private static final int accesslevel = 3;
  private static final String moduleId = "EEF85801B64F4FB982F0E08DCEDCF98A";
  
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
     
      if (command.contains("CBCA1AE8F1F54A258EC83AA48D143DD5")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("CBCA1AE8F1F54A258EC83AA48D143DD5");
        SessionInfo.setModuleId("EEF85801B64F4FB982F0E08DCEDCF98A");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("CBCA1AE8F1F54A258EC83AA48D143DD5") || (securedProcess && command.contains("CBCA1AE8F1F54A258EC83AA48D143DD5"))) {
        classInfo.type = "P";
        classInfo.id = "CBCA1AE8F1F54A258EC83AA48D143DD5";
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

    } else if (vars.commandIn("BUTTONRelease_ContractCBCA1AE8F1F54A258EC83AA48D143DD5")) {
        vars.setSessionValue("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.strreleaseContract", vars.getStringParameter("inpreleaseContract"));
        vars.setSessionValue("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "CBCA1AE8F1F54A258EC83AA48D143DD5", request.getServletPath());
      } else if (vars.commandIn("BUTTONCBCA1AE8F1F54A258EC83AA48D143DD5")) {
        String strTM_Termination_ID = vars.getGlobalVariable("inptmTerminationId", windowId + "|TM_Termination_ID", "");
        String strreleaseContract = vars.getSessionValue("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.strreleaseContract");
        String strProcessing = vars.getSessionValue("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.strProcessing");
        String strOrg = vars.getSessionValue("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.strOrg");
        String strClient = vars.getSessionValue("buttonCBCA1AE8F1F54A258EC83AA48D143DD5.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonRelease_ContractCBCA1AE8F1F54A258EC83AA48D143DD5(response, vars, strTM_Termination_ID, strreleaseContract, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONRelease_ContractCBCA1AE8F1F54A258EC83AA48D143DD5")) {
        String strTM_Termination_ID = vars.getGlobalVariable("inpKey", windowId + "|TM_Termination_ID", "");
        
        ProcessBundle pb = new ProcessBundle("CBCA1AE8F1F54A258EC83AA48D143DD5", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("TM_Termination_ID", strTM_Termination_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        
        
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



    void printPageButtonRelease_ContractCBCA1AE8F1F54A258EC83AA48D143DD5(HttpServletResponse response, VariablesSecureApp vars, String strTM_Termination_ID, String strreleaseContract, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process CBCA1AE8F1F54A258EC83AA48D143DD5");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Release_ContractCBCA1AE8F1F54A258EC83AA48D143DD5", discard).createXmlDocument();
      xmlDocument.setParameter("key", strTM_Termination_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "EmployeeTerminationC8BDF801F9B24CBDA9816D68BC8FB48D_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "CBCA1AE8F1F54A258EC83AA48D143DD5");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("CBCA1AE8F1F54A258EC83AA48D143DD5");
        vars.removeMessage("CBCA1AE8F1F54A258EC83AA48D143DD5");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
