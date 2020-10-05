
package org.openbravo.erpWindows.org.wirabumi.hris.employee.master.EmployeeCase;


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
public class EmployeeCase66A501261DF545E5BF3207E311863E5C extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "F71F1E2860D94878AF710DDF5530A9D9";
  private static final String tabId = "66A501261DF545E5BF3207E311863E5C";
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
     
      if (command.contains("9E673FD989FB49779E7E9B3DFB58040D")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("9E673FD989FB49779E7E9B3DFB58040D");
        SessionInfo.setModuleId("D5FD35D6C7604921BA951B7E0EEE5DC2");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("9E673FD989FB49779E7E9B3DFB58040D") || (securedProcess && command.contains("9E673FD989FB49779E7E9B3DFB58040D"))) {
        classInfo.type = "P";
        classInfo.id = "9E673FD989FB49779E7E9B3DFB58040D";
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

    } else if (vars.commandIn("BUTTONCreatepunishment9E673FD989FB49779E7E9B3DFB58040D")) {
        vars.setSessionValue("button9E673FD989FB49779E7E9B3DFB58040D.strcreatepunishment", vars.getStringParameter("inpcreatepunishment"));
        vars.setSessionValue("button9E673FD989FB49779E7E9B3DFB58040D.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button9E673FD989FB49779E7E9B3DFB58040D.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button9E673FD989FB49779E7E9B3DFB58040D.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button9E673FD989FB49779E7E9B3DFB58040D.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "9E673FD989FB49779E7E9B3DFB58040D", request.getServletPath());
      } else if (vars.commandIn("BUTTON9E673FD989FB49779E7E9B3DFB58040D")) {
        String strHris_Case_ID = vars.getGlobalVariable("inphrisCaseId", windowId + "|Hris_Case_ID", "");
        String strcreatepunishment = vars.getSessionValue("button9E673FD989FB49779E7E9B3DFB58040D.strcreatepunishment");
        String strProcessing = vars.getSessionValue("button9E673FD989FB49779E7E9B3DFB58040D.strProcessing");
        String strOrg = vars.getSessionValue("button9E673FD989FB49779E7E9B3DFB58040D.strOrg");
        String strClient = vars.getSessionValue("button9E673FD989FB49779E7E9B3DFB58040D.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCreatepunishment9E673FD989FB49779E7E9B3DFB58040D(response, vars, strHris_Case_ID, strcreatepunishment, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONCreatepunishment9E673FD989FB49779E7E9B3DFB58040D")) {
        String strHris_Case_ID = vars.getGlobalVariable("inpKey", windowId + "|Hris_Case_ID", "");
        
        ProcessBundle pb = new ProcessBundle("9E673FD989FB49779E7E9B3DFB58040D", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Hris_Case_ID", strHris_Case_ID);
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



    void printPageButtonCreatepunishment9E673FD989FB49779E7E9B3DFB58040D(HttpServletResponse response, VariablesSecureApp vars, String strHris_Case_ID, String strcreatepunishment, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9E673FD989FB49779E7E9B3DFB58040D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Createpunishment9E673FD989FB49779E7E9B3DFB58040D", discard).createXmlDocument();
      xmlDocument.setParameter("key", strHris_Case_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "EmployeeCase66A501261DF545E5BF3207E311863E5C_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "9E673FD989FB49779E7E9B3DFB58040D");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("9E673FD989FB49779E7E9B3DFB58040D");
        vars.removeMessage("9E673FD989FB49779E7E9B3DFB58040D");
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
