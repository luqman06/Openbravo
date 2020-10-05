
package org.openbravo.erpWindows.org.wirabumi.hris.overtime.OvertimeRequest;




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
public class HeaderEE1B3C1660634EC9B2C7B2888B6082B5 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "B28898E7BCB848ECB4A228A6B3703EE1";
  private static final String tabId = "EE1B3C1660634EC9B2C7B2888B6082B5";
  private static final int accesslevel = 3;
  private static final String moduleId = "3D7EA38195044083A47056E9A032004B";
  
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
     
      if (command.contains("C6790B3A3DF24785B25CE94CA4F7CC29")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("C6790B3A3DF24785B25CE94CA4F7CC29");
        SessionInfo.setModuleId("3D7EA38195044083A47056E9A032004B");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("C6790B3A3DF24785B25CE94CA4F7CC29") || (securedProcess && command.contains("C6790B3A3DF24785B25CE94CA4F7CC29"))) {
        classInfo.type = "P";
        classInfo.id = "C6790B3A3DF24785B25CE94CA4F7CC29";
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

    } else if (vars.commandIn("BUTTONCalculateovertimeC6790B3A3DF24785B25CE94CA4F7CC29")) {
        vars.setSessionValue("buttonC6790B3A3DF24785B25CE94CA4F7CC29.strcalculateovertime", vars.getStringParameter("inpcalculateovertime"));
        vars.setSessionValue("buttonC6790B3A3DF24785B25CE94CA4F7CC29.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonC6790B3A3DF24785B25CE94CA4F7CC29.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonC6790B3A3DF24785B25CE94CA4F7CC29.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonC6790B3A3DF24785B25CE94CA4F7CC29.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "C6790B3A3DF24785B25CE94CA4F7CC29", request.getServletPath());
      } else if (vars.commandIn("BUTTONC6790B3A3DF24785B25CE94CA4F7CC29")) {
        String strOT_Overtime_ID = vars.getGlobalVariable("inpotOvertimeId", windowId + "|OT_Overtime_ID", "");
        String strcalculateovertime = vars.getSessionValue("buttonC6790B3A3DF24785B25CE94CA4F7CC29.strcalculateovertime");
        String strProcessing = vars.getSessionValue("buttonC6790B3A3DF24785B25CE94CA4F7CC29.strProcessing");
        String strOrg = vars.getSessionValue("buttonC6790B3A3DF24785B25CE94CA4F7CC29.strOrg");
        String strClient = vars.getSessionValue("buttonC6790B3A3DF24785B25CE94CA4F7CC29.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCalculateovertimeC6790B3A3DF24785B25CE94CA4F7CC29(response, vars, strOT_Overtime_ID, strcalculateovertime, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONCalculateovertimeC6790B3A3DF24785B25CE94CA4F7CC29")) {
        String strOT_Overtime_ID = vars.getGlobalVariable("inpKey", windowId + "|OT_Overtime_ID", "");
        
        ProcessBundle pb = new ProcessBundle("C6790B3A3DF24785B25CE94CA4F7CC29", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("OT_Overtime_ID", strOT_Overtime_ID);
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



    void printPageButtonCalculateovertimeC6790B3A3DF24785B25CE94CA4F7CC29(HttpServletResponse response, VariablesSecureApp vars, String strOT_Overtime_ID, String strcalculateovertime, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C6790B3A3DF24785B25CE94CA4F7CC29");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/CalculateovertimeC6790B3A3DF24785B25CE94CA4F7CC29", discard).createXmlDocument();
      xmlDocument.setParameter("key", strOT_Overtime_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "HeaderEE1B3C1660634EC9B2C7B2888B6082B5_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "C6790B3A3DF24785B25CE94CA4F7CC29");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("C6790B3A3DF24785B25CE94CA4F7CC29");
        vars.removeMessage("C6790B3A3DF24785B25CE94CA4F7CC29");
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
