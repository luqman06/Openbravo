
package org.openbravo.erpWindows.org.wirabumi.hris.employee.master.Retirement;




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
public class RetirementD07D79692A714EB2B1AEFA56EDFE62FE extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "00F6A69B3C8B4277A1A2A3B2D98C7121";
  private static final String tabId = "D07D79692A714EB2B1AEFA56EDFE62FE";
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
     
      if (command.contains("BC22C0CB91014A6CB8AEA65E2460F003")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("BC22C0CB91014A6CB8AEA65E2460F003");
        SessionInfo.setModuleId("D5FD35D6C7604921BA951B7E0EEE5DC2");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("BC22C0CB91014A6CB8AEA65E2460F003") || (securedProcess && command.contains("BC22C0CB91014A6CB8AEA65E2460F003"))) {
        classInfo.type = "P";
        classInfo.id = "BC22C0CB91014A6CB8AEA65E2460F003";
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

    } else if (vars.commandIn("BUTTONPublishcontractBC22C0CB91014A6CB8AEA65E2460F003")) {
        vars.setSessionValue("buttonBC22C0CB91014A6CB8AEA65E2460F003.strpublishcontract", vars.getStringParameter("inppublishcontract"));
        vars.setSessionValue("buttonBC22C0CB91014A6CB8AEA65E2460F003.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonBC22C0CB91014A6CB8AEA65E2460F003.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonBC22C0CB91014A6CB8AEA65E2460F003.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonBC22C0CB91014A6CB8AEA65E2460F003.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "BC22C0CB91014A6CB8AEA65E2460F003", request.getServletPath());
      } else if (vars.commandIn("BUTTONBC22C0CB91014A6CB8AEA65E2460F003")) {
        String strHris_Retirement_ID = vars.getGlobalVariable("inphrisRetirementId", windowId + "|Hris_Retirement_ID", "");
        String strpublishcontract = vars.getSessionValue("buttonBC22C0CB91014A6CB8AEA65E2460F003.strpublishcontract");
        String strProcessing = vars.getSessionValue("buttonBC22C0CB91014A6CB8AEA65E2460F003.strProcessing");
        String strOrg = vars.getSessionValue("buttonBC22C0CB91014A6CB8AEA65E2460F003.strOrg");
        String strClient = vars.getSessionValue("buttonBC22C0CB91014A6CB8AEA65E2460F003.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonPublishcontractBC22C0CB91014A6CB8AEA65E2460F003(response, vars, strHris_Retirement_ID, strpublishcontract, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONPublishcontractBC22C0CB91014A6CB8AEA65E2460F003")) {
        String strHris_Retirement_ID = vars.getGlobalVariable("inpKey", windowId + "|Hris_Retirement_ID", "");
        
        ProcessBundle pb = new ProcessBundle("BC22C0CB91014A6CB8AEA65E2460F003", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Hris_Retirement_ID", strHris_Retirement_ID);
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



    void printPageButtonPublishcontractBC22C0CB91014A6CB8AEA65E2460F003(HttpServletResponse response, VariablesSecureApp vars, String strHris_Retirement_ID, String strpublishcontract, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process BC22C0CB91014A6CB8AEA65E2460F003");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/PublishcontractBC22C0CB91014A6CB8AEA65E2460F003", discard).createXmlDocument();
      xmlDocument.setParameter("key", strHris_Retirement_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "RetirementD07D79692A714EB2B1AEFA56EDFE62FE_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "BC22C0CB91014A6CB8AEA65E2460F003");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("BC22C0CB91014A6CB8AEA65E2460F003");
        vars.removeMessage("BC22C0CB91014A6CB8AEA65E2460F003");
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
