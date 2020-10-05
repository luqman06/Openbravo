
package org.openbravo.erpWindows.org.wirabumi.gen.oez.ImportWorkEffort;




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
public class ImportWorkEffortF43AA8CDC0294FD5BEC81155E0A9A664 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "C1FE9B7B666B4EE38E6C2C748EF7B80F";
  private static final String tabId = "F43AA8CDC0294FD5BEC81155E0A9A664";
  private static final int accesslevel = 7;
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
     
      if (command.contains("3D671B238B16410581EADAAF1091F625")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("3D671B238B16410581EADAAF1091F625");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("3D671B238B16410581EADAAF1091F625") || (securedProcess && command.contains("3D671B238B16410581EADAAF1091F625"))) {
        classInfo.type = "P";
        classInfo.id = "3D671B238B16410581EADAAF1091F625";
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

    } else if (vars.commandIn("BUTTONImportworkeffort3D671B238B16410581EADAAF1091F625")) {
        vars.setSessionValue("button3D671B238B16410581EADAAF1091F625.strimportworkeffort", vars.getStringParameter("inpimportworkeffort"));
        vars.setSessionValue("button3D671B238B16410581EADAAF1091F625.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button3D671B238B16410581EADAAF1091F625.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button3D671B238B16410581EADAAF1091F625.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button3D671B238B16410581EADAAF1091F625.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "3D671B238B16410581EADAAF1091F625", request.getServletPath());
      } else if (vars.commandIn("BUTTON3D671B238B16410581EADAAF1091F625")) {
        String strOEZ_I_Workeffort_ID = vars.getGlobalVariable("inpoezIWorkeffortId", windowId + "|OEZ_I_Workeffort_ID", "");
        String strimportworkeffort = vars.getSessionValue("button3D671B238B16410581EADAAF1091F625.strimportworkeffort");
        String strProcessing = vars.getSessionValue("button3D671B238B16410581EADAAF1091F625.strProcessing");
        String strOrg = vars.getSessionValue("button3D671B238B16410581EADAAF1091F625.strOrg");
        String strClient = vars.getSessionValue("button3D671B238B16410581EADAAF1091F625.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonImportworkeffort3D671B238B16410581EADAAF1091F625(response, vars, strOEZ_I_Workeffort_ID, strimportworkeffort, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONImportworkeffort3D671B238B16410581EADAAF1091F625")) {
        String strOEZ_I_Workeffort_ID = vars.getGlobalVariable("inpKey", windowId + "|OEZ_I_Workeffort_ID", "");
        
        ProcessBundle pb = new ProcessBundle("3D671B238B16410581EADAAF1091F625", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("OEZ_I_Workeffort_ID", strOEZ_I_Workeffort_ID);
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



    void printPageButtonImportworkeffort3D671B238B16410581EADAAF1091F625(HttpServletResponse response, VariablesSecureApp vars, String strOEZ_I_Workeffort_ID, String strimportworkeffort, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3D671B238B16410581EADAAF1091F625");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Importworkeffort3D671B238B16410581EADAAF1091F625", discard).createXmlDocument();
      xmlDocument.setParameter("key", strOEZ_I_Workeffort_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ImportWorkEffortF43AA8CDC0294FD5BEC81155E0A9A664_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "3D671B238B16410581EADAAF1091F625");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("3D671B238B16410581EADAAF1091F625");
        vars.removeMessage("3D671B238B16410581EADAAF1091F625");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Org_ID", Utility.getContext(this, vars, "AD_ORG_ID", "C1FE9B7B666B4EE38E6C2C748EF7B80F"));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button3D671B238B16410581EADAAF1091F625.originalParams"), comboTableData, windowId, Utility.getContext(this, vars, "AD_ORG_ID", "C1FE9B7B666B4EE38E6C2C748EF7B80F"));
    xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
