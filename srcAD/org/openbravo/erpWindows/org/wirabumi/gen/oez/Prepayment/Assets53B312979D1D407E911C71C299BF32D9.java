
package org.openbravo.erpWindows.org.wirabumi.gen.oez.Prepayment;


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
public class Assets53B312979D1D407E911C71C299BF32D9 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "B8EF74664A17470EA140155450DC68ED";
  private static final String tabId = "53B312979D1D407E911C71C299BF32D9";
  private static final int accesslevel = 1;
  private static final String moduleId = "27445C8270364452BEB29669FBB21CED";
  
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
     
      if (command.contains("8C6B905EDA8742A0ABF4092902D54090")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("8C6B905EDA8742A0ABF4092902D54090");
        SessionInfo.setModuleId("5C40CF085DCA477AB9DA89559237F7EB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("800125")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("800125");
        SessionInfo.setModuleId("0");
        if (securedProcess || explicitAccess.contains("800125")) {
          classInfo.type = "P";
          classInfo.id = "800125";
        }
      }
     

     
      if (explicitAccess.contains("8C6B905EDA8742A0ABF4092902D54090") || (securedProcess && command.contains("8C6B905EDA8742A0ABF4092902D54090"))) {
        classInfo.type = "P";
        classInfo.id = "8C6B905EDA8742A0ABF4092902D54090";
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

     } else if (vars.commandIn("BUTTONProcessed800125")) {
        vars.setSessionValue("button800125.strprocessed", vars.getStringParameter("inpprocessed"));
        vars.setSessionValue("button800125.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button800125.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button800125.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button800125.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "800125", request.getServletPath());    
     } else if (vars.commandIn("BUTTON800125")) {
        String strA_Asset_ID = vars.getGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID", "");
        String strprocessed = vars.getSessionValue("button800125.strprocessed");
        String strProcessing = vars.getSessionValue("button800125.strProcessing");
        String strOrg = vars.getSessionValue("button800125.strOrg");
        String strClient = vars.getSessionValue("button800125.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessed800125(response, vars, strA_Asset_ID, strprocessed, strProcessing);
        }

    } else if (vars.commandIn("BUTTONProcess_Asset8C6B905EDA8742A0ABF4092902D54090")) {
        vars.setSessionValue("button8C6B905EDA8742A0ABF4092902D54090.strprocessAsset", vars.getStringParameter("inpprocessAsset"));
        vars.setSessionValue("button8C6B905EDA8742A0ABF4092902D54090.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button8C6B905EDA8742A0ABF4092902D54090.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button8C6B905EDA8742A0ABF4092902D54090.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button8C6B905EDA8742A0ABF4092902D54090.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "8C6B905EDA8742A0ABF4092902D54090", request.getServletPath());
      } else if (vars.commandIn("BUTTON8C6B905EDA8742A0ABF4092902D54090")) {
        String strA_Asset_ID = vars.getGlobalVariable("inpaAssetId", windowId + "|A_Asset_ID", "");
        String strprocessAsset = vars.getSessionValue("button8C6B905EDA8742A0ABF4092902D54090.strprocessAsset");
        String strProcessing = vars.getSessionValue("button8C6B905EDA8742A0ABF4092902D54090.strProcessing");
        String strOrg = vars.getSessionValue("button8C6B905EDA8742A0ABF4092902D54090.strOrg");
        String strClient = vars.getSessionValue("button8C6B905EDA8742A0ABF4092902D54090.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcess_Asset8C6B905EDA8742A0ABF4092902D54090(response, vars, strA_Asset_ID, strprocessAsset, strProcessing);
        }

    } else if (vars.commandIn("SAVE_BUTTONProcessed800125")) {
        String strA_Asset_ID = vars.getGlobalVariable("inpKey", windowId + "|A_Asset_ID", "");
        String strprocessed = vars.getStringParameter("inpprocessed");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "800125", (("A_Asset_ID".equalsIgnoreCase("AD_Language"))?"0":strA_Asset_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
          ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
          new ProcessRunner(bundle).execute(this);
          
          PInstanceProcessData[] pinstanceData = PInstanceProcessData.select(this, pinstance);
          myMessage = Utility.getProcessInstanceMessage(this, vars, pinstanceData);
        } catch (ServletException ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
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

    } else if (vars.commandIn("SAVE_BUTTONProcess_Asset8C6B905EDA8742A0ABF4092902D54090")) {
        String strA_Asset_ID = vars.getGlobalVariable("inpKey", windowId + "|A_Asset_ID", "");
        
        ProcessBundle pb = new ProcessBundle("8C6B905EDA8742A0ABF4092902D54090", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("A_Asset_ID", strA_Asset_ID);
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

    private void printPageButtonProcessed800125(HttpServletResponse response, VariablesSecureApp vars, String strA_Asset_ID, String strprocessed, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800125");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Processed800125", discard).createXmlDocument();
      xmlDocument.setParameter("key", strA_Asset_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Assets53B312979D1D407E911C71C299BF32D9_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "800125");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("800125");
        vars.removeMessage("800125");
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


    void printPageButtonProcess_Asset8C6B905EDA8742A0ABF4092902D54090(HttpServletResponse response, VariablesSecureApp vars, String strA_Asset_ID, String strprocessAsset, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8C6B905EDA8742A0ABF4092902D54090");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Process_Asset8C6B905EDA8742A0ABF4092902D54090", discard).createXmlDocument();
      xmlDocument.setParameter("key", strA_Asset_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Assets53B312979D1D407E911C71C299BF32D9_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "8C6B905EDA8742A0ABF4092902D54090");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("8C6B905EDA8742A0ABF4092902D54090");
        vars.removeMessage("8C6B905EDA8742A0ABF4092902D54090");
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
