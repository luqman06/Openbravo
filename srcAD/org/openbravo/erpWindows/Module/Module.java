
package org.openbravo.erpWindows.Module;




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
public class Module extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "D586192D06C14EC182B44CAD34CA4295";
  private static final String tabId = "F53E35A11C564F20BE4082A7B8CFF6B7";
  private static final int accesslevel = 4;
  private static final String moduleId = "0";
  
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
     
      if (command.contains("48FF95BA0B944EB981C9EA012AE215C6")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("48FF95BA0B944EB981C9EA012AE215C6");
        SessionInfo.setModuleId("2582B76C228F409281D5DFD98FD2C856");
      }
     
      if (command.contains("7B0C4F8D4D1943FBAC960A860B5CFD2F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("7B0C4F8D4D1943FBAC960A860B5CFD2F");
        SessionInfo.setModuleId("2582B76C228F409281D5DFD98FD2C856");
      }
     
      if (command.contains("1E689A488E9F42EC9835A23FD845F91F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("1E689A488E9F42EC9835A23FD845F91F");
        SessionInfo.setModuleId("2582B76C228F409281D5DFD98FD2C856");
      }
     
      if (command.contains("FF80818132902152013290D3E5D2001A")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("FF80818132902152013290D3E5D2001A");
        SessionInfo.setModuleId("FF808181328C86B901328C94B7930007");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("48FF95BA0B944EB981C9EA012AE215C6") || (securedProcess && command.contains("48FF95BA0B944EB981C9EA012AE215C6"))) {
        classInfo.type = "P";
        classInfo.id = "48FF95BA0B944EB981C9EA012AE215C6";
      }
     
      if (explicitAccess.contains("7B0C4F8D4D1943FBAC960A860B5CFD2F") || (securedProcess && command.contains("7B0C4F8D4D1943FBAC960A860B5CFD2F"))) {
        classInfo.type = "P";
        classInfo.id = "7B0C4F8D4D1943FBAC960A860B5CFD2F";
      }
     
      if (explicitAccess.contains("1E689A488E9F42EC9835A23FD845F91F") || (securedProcess && command.contains("1E689A488E9F42EC9835A23FD845F91F"))) {
        classInfo.type = "P";
        classInfo.id = "1E689A488E9F42EC9835A23FD845F91F";
      }
     
      if (explicitAccess.contains("FF80818132902152013290D3E5D2001A") || (securedProcess && command.contains("FF80818132902152013290D3E5D2001A"))) {
        classInfo.type = "P";
        classInfo.id = "FF80818132902152013290D3E5D2001A";
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

    } else if (vars.commandIn("BUTTONEM_Obgpstm_Create_Trl_Module48FF95BA0B944EB981C9EA012AE215C6")) {
        vars.setSessionValue("button48FF95BA0B944EB981C9EA012AE215C6.stremObgpstmCreateTrlModule", vars.getStringParameter("inpemObgpstmCreateTrlModule"));
        vars.setSessionValue("button48FF95BA0B944EB981C9EA012AE215C6.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button48FF95BA0B944EB981C9EA012AE215C6.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button48FF95BA0B944EB981C9EA012AE215C6.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button48FF95BA0B944EB981C9EA012AE215C6.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "48FF95BA0B944EB981C9EA012AE215C6", request.getServletPath());
      } else if (vars.commandIn("BUTTON48FF95BA0B944EB981C9EA012AE215C6")) {
        String strAD_Module_ID = vars.getGlobalVariable("inpadModuleId", windowId + "|AD_Module_ID", "");
        String stremObgpstmCreateTrlModule = vars.getSessionValue("button48FF95BA0B944EB981C9EA012AE215C6.stremObgpstmCreateTrlModule");
        String strProcessing = vars.getSessionValue("button48FF95BA0B944EB981C9EA012AE215C6.strProcessing");
        String strOrg = vars.getSessionValue("button48FF95BA0B944EB981C9EA012AE215C6.strOrg");
        String strClient = vars.getSessionValue("button48FF95BA0B944EB981C9EA012AE215C6.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Obgpstm_Create_Trl_Module48FF95BA0B944EB981C9EA012AE215C6(response, vars, strAD_Module_ID, stremObgpstmCreateTrlModule, strProcessing);
        }
    } else if (vars.commandIn("BUTTONEM_Obgpstm_Import_Trl7B0C4F8D4D1943FBAC960A860B5CFD2F")) {
        vars.setSessionValue("button7B0C4F8D4D1943FBAC960A860B5CFD2F.stremObgpstmImportTrl", vars.getStringParameter("inpemObgpstmImportTrl"));
        vars.setSessionValue("button7B0C4F8D4D1943FBAC960A860B5CFD2F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button7B0C4F8D4D1943FBAC960A860B5CFD2F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button7B0C4F8D4D1943FBAC960A860B5CFD2F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button7B0C4F8D4D1943FBAC960A860B5CFD2F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "7B0C4F8D4D1943FBAC960A860B5CFD2F", request.getServletPath());
      } else if (vars.commandIn("BUTTON7B0C4F8D4D1943FBAC960A860B5CFD2F")) {
        String strAD_Module_ID = vars.getGlobalVariable("inpadModuleId", windowId + "|AD_Module_ID", "");
        String stremObgpstmImportTrl = vars.getSessionValue("button7B0C4F8D4D1943FBAC960A860B5CFD2F.stremObgpstmImportTrl");
        String strProcessing = vars.getSessionValue("button7B0C4F8D4D1943FBAC960A860B5CFD2F.strProcessing");
        String strOrg = vars.getSessionValue("button7B0C4F8D4D1943FBAC960A860B5CFD2F.strOrg");
        String strClient = vars.getSessionValue("button7B0C4F8D4D1943FBAC960A860B5CFD2F.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Obgpstm_Import_Trl7B0C4F8D4D1943FBAC960A860B5CFD2F(response, vars, strAD_Module_ID, stremObgpstmImportTrl, strProcessing);
        }
    } else if (vars.commandIn("BUTTONEM_Obgpstm_Export_Trl1E689A488E9F42EC9835A23FD845F91F")) {
        vars.setSessionValue("button1E689A488E9F42EC9835A23FD845F91F.stremObgpstmExportTrl", vars.getStringParameter("inpemObgpstmExportTrl"));
        vars.setSessionValue("button1E689A488E9F42EC9835A23FD845F91F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button1E689A488E9F42EC9835A23FD845F91F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button1E689A488E9F42EC9835A23FD845F91F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button1E689A488E9F42EC9835A23FD845F91F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "1E689A488E9F42EC9835A23FD845F91F", request.getServletPath());
      } else if (vars.commandIn("BUTTON1E689A488E9F42EC9835A23FD845F91F")) {
        String strAD_Module_ID = vars.getGlobalVariable("inpadModuleId", windowId + "|AD_Module_ID", "");
        String stremObgpstmExportTrl = vars.getSessionValue("button1E689A488E9F42EC9835A23FD845F91F.stremObgpstmExportTrl");
        String strProcessing = vars.getSessionValue("button1E689A488E9F42EC9835A23FD845F91F.strProcessing");
        String strOrg = vars.getSessionValue("button1E689A488E9F42EC9835A23FD845F91F.strOrg");
        String strClient = vars.getSessionValue("button1E689A488E9F42EC9835A23FD845F91F.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Obgpstm_Export_Trl1E689A488E9F42EC9835A23FD845F91F(response, vars, strAD_Module_ID, stremObgpstmExportTrl, strProcessing);
        }
    } else if (vars.commandIn("BUTTONEM_Mpack_PackagerFF80818132902152013290D3E5D2001A")) {
        vars.setSessionValue("buttonFF80818132902152013290D3E5D2001A.stremMpackPackager", vars.getStringParameter("inpemMpackPackager"));
        vars.setSessionValue("buttonFF80818132902152013290D3E5D2001A.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonFF80818132902152013290D3E5D2001A.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonFF80818132902152013290D3E5D2001A.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonFF80818132902152013290D3E5D2001A.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "FF80818132902152013290D3E5D2001A", request.getServletPath());
      } else if (vars.commandIn("BUTTONFF80818132902152013290D3E5D2001A")) {
        String strAD_Module_ID = vars.getGlobalVariable("inpadModuleId", windowId + "|AD_Module_ID", "");
        String stremMpackPackager = vars.getSessionValue("buttonFF80818132902152013290D3E5D2001A.stremMpackPackager");
        String strProcessing = vars.getSessionValue("buttonFF80818132902152013290D3E5D2001A.strProcessing");
        String strOrg = vars.getSessionValue("buttonFF80818132902152013290D3E5D2001A.strOrg");
        String strClient = vars.getSessionValue("buttonFF80818132902152013290D3E5D2001A.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Mpack_PackagerFF80818132902152013290D3E5D2001A(response, vars, strAD_Module_ID, stremMpackPackager, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_Obgpstm_Create_Trl_Module48FF95BA0B944EB981C9EA012AE215C6")) {
        String strAD_Module_ID = vars.getGlobalVariable("inpKey", windowId + "|AD_Module_ID", "");
        
        ProcessBundle pb = new ProcessBundle("48FF95BA0B944EB981C9EA012AE215C6", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("AD_Module_ID", strAD_Module_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String stradLanguage = vars.getStringParameter("inpadLanguage");
params.put("adLanguage", stradLanguage);

        
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
    } else if (vars.commandIn("SAVE_BUTTONEM_Obgpstm_Import_Trl7B0C4F8D4D1943FBAC960A860B5CFD2F")) {
        String strAD_Module_ID = vars.getGlobalVariable("inpKey", windowId + "|AD_Module_ID", "");
        
        ProcessBundle pb = new ProcessBundle("7B0C4F8D4D1943FBAC960A860B5CFD2F", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("AD_Module_ID", strAD_Module_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONEM_Obgpstm_Export_Trl1E689A488E9F42EC9835A23FD845F91F")) {
        String strAD_Module_ID = vars.getGlobalVariable("inpKey", windowId + "|AD_Module_ID", "");
        
        ProcessBundle pb = new ProcessBundle("1E689A488E9F42EC9835A23FD845F91F", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("AD_Module_ID", strAD_Module_ID);
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
    } else if (vars.commandIn("SAVE_BUTTONEM_Mpack_PackagerFF80818132902152013290D3E5D2001A")) {
        String strAD_Module_ID = vars.getGlobalVariable("inpKey", windowId + "|AD_Module_ID", "");
        
        ProcessBundle pb = new ProcessBundle("FF80818132902152013290D3E5D2001A", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("AD_Module_ID", strAD_Module_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strexportDatasets = vars.getStringParameter("inpexportDatasets", "N");
params.put("exportDatasets", strexportDatasets);
String strexportDatabase = vars.getStringParameter("inpexportDatabase", "N");
params.put("exportDatabase", strexportDatabase);
String strexportTranslation = vars.getStringParameter("inpexportTranslation", "N");
params.put("exportTranslation", strexportTranslation);
String strpackageModule = vars.getStringParameter("inppackageModule", "N");
params.put("packageModule", strpackageModule);

        
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



    void printPageButtonEM_Obgpstm_Create_Trl_Module48FF95BA0B944EB981C9EA012AE215C6(HttpServletResponse response, VariablesSecureApp vars, String strAD_Module_ID, String stremObgpstmCreateTrlModule, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 48FF95BA0B944EB981C9EA012AE215C6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Obgpstm_Create_Trl_Module48FF95BA0B944EB981C9EA012AE215C6", discard).createXmlDocument();
      xmlDocument.setParameter("key", strAD_Module_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Module_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "48FF95BA0B944EB981C9EA012AE215C6");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("48FF95BA0B944EB981C9EA012AE215C6");
        vars.removeMessage("48FF95BA0B944EB981C9EA012AE215C6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_language", Utility.getContext(this, vars, "#ad_language", windowId));
    comboTableData = new ComboTableData(vars, this, "18", "ad_language", "106", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button48FF95BA0B944EB981C9EA012AE215C6.originalParams"), comboTableData, windowId, Utility.getContext(this, vars, "#ad_language", windowId));
    xmlDocument.setData("reportad_language", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonEM_Obgpstm_Import_Trl7B0C4F8D4D1943FBAC960A860B5CFD2F(HttpServletResponse response, VariablesSecureApp vars, String strAD_Module_ID, String stremObgpstmImportTrl, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7B0C4F8D4D1943FBAC960A860B5CFD2F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Obgpstm_Import_Trl7B0C4F8D4D1943FBAC960A860B5CFD2F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strAD_Module_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Module_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "7B0C4F8D4D1943FBAC960A860B5CFD2F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("7B0C4F8D4D1943FBAC960A860B5CFD2F");
        vars.removeMessage("7B0C4F8D4D1943FBAC960A860B5CFD2F");
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
    void printPageButtonEM_Obgpstm_Export_Trl1E689A488E9F42EC9835A23FD845F91F(HttpServletResponse response, VariablesSecureApp vars, String strAD_Module_ID, String stremObgpstmExportTrl, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1E689A488E9F42EC9835A23FD845F91F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Obgpstm_Export_Trl1E689A488E9F42EC9835A23FD845F91F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strAD_Module_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Module_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "1E689A488E9F42EC9835A23FD845F91F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("1E689A488E9F42EC9835A23FD845F91F");
        vars.removeMessage("1E689A488E9F42EC9835A23FD845F91F");
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
    void printPageButtonEM_Mpack_PackagerFF80818132902152013290D3E5D2001A(HttpServletResponse response, VariablesSecureApp vars, String strAD_Module_ID, String stremMpackPackager, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF80818132902152013290D3E5D2001A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Mpack_PackagerFF80818132902152013290D3E5D2001A", discard).createXmlDocument();
      xmlDocument.setParameter("key", strAD_Module_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Module_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "FF80818132902152013290D3E5D2001A");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("FF80818132902152013290D3E5D2001A");
        vars.removeMessage("FF80818132902152013290D3E5D2001A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Export_Datasets", "");
    xmlDocument.setParameter("Export_Database", "");
    xmlDocument.setParameter("Export_Translation", "");
    xmlDocument.setParameter("Package_Module", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
