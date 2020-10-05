
package org.openbravo.erpWindows.org.openbravo.importdata.ImportGLJournal;




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
public class GeneralLedger508 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "278";
  private static final String tabId = "508";
  private static final int accesslevel = 7;
  private static final String moduleId = "FF8081812FBF677A012FBF6A0D3E0003";
  
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
     
      if (command.contains("9B26EF017C244CD59C77BE203A2D14A8")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("9B26EF017C244CD59C77BE203A2D14A8");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("9B26EF017C244CD59C77BE203A2D14A8") || (securedProcess && command.contains("9B26EF017C244CD59C77BE203A2D14A8"))) {
        classInfo.type = "P";
        classInfo.id = "9B26EF017C244CD59C77BE203A2D14A8";
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

    } else if (vars.commandIn("BUTTONProcessing9B26EF017C244CD59C77BE203A2D14A8")) {
        vars.setSessionValue("button9B26EF017C244CD59C77BE203A2D14A8.strprocessing", vars.getStringParameter("inpprocessing"));
        vars.setSessionValue("button9B26EF017C244CD59C77BE203A2D14A8.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button9B26EF017C244CD59C77BE203A2D14A8.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button9B26EF017C244CD59C77BE203A2D14A8.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button9B26EF017C244CD59C77BE203A2D14A8.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "9B26EF017C244CD59C77BE203A2D14A8", request.getServletPath());
      } else if (vars.commandIn("BUTTON9B26EF017C244CD59C77BE203A2D14A8")) {
        String strI_GLJournal_ID = vars.getGlobalVariable("inpiGljournalId", windowId + "|I_GLJournal_ID", "");
        String strprocessing = vars.getSessionValue("button9B26EF017C244CD59C77BE203A2D14A8.strprocessing");
        String strProcessing = vars.getSessionValue("button9B26EF017C244CD59C77BE203A2D14A8.strProcessing");
        String strOrg = vars.getSessionValue("button9B26EF017C244CD59C77BE203A2D14A8.strOrg");
        String strClient = vars.getSessionValue("button9B26EF017C244CD59C77BE203A2D14A8.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessing9B26EF017C244CD59C77BE203A2D14A8(response, vars, strI_GLJournal_ID, strprocessing, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcessing9B26EF017C244CD59C77BE203A2D14A8")) {
        String strI_GLJournal_ID = vars.getGlobalVariable("inpKey", windowId + "|I_GLJournal_ID", "");
        
        ProcessBundle pb = new ProcessBundle("9B26EF017C244CD59C77BE203A2D14A8", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("I_GLJournal_ID", strI_GLJournal_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);

        
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



    void printPageButtonProcessing9B26EF017C244CD59C77BE203A2D14A8(HttpServletResponse response, VariablesSecureApp vars, String strI_GLJournal_ID, String strprocessing, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9B26EF017C244CD59C77BE203A2D14A8");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Processing9B26EF017C244CD59C77BE203A2D14A8", discard).createXmlDocument();
      xmlDocument.setParameter("key", strI_GLJournal_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "GeneralLedger508_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "9B26EF017C244CD59C77BE203A2D14A8");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("9B26EF017C244CD59C77BE203A2D14A8");
        vars.removeMessage("9B26EF017C244CD59C77BE203A2D14A8");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_org_id", Utility.getContext(this, vars, "#AD_Org_ID", windowId));
    comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "49DC1D6F086945AB82F84C66F5F13F16", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button9B26EF017C244CD59C77BE203A2D14A8.originalParams"), comboTableData, windowId, Utility.getContext(this, vars, "#AD_Org_ID", windowId));
    xmlDocument.setData("reportad_org_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
