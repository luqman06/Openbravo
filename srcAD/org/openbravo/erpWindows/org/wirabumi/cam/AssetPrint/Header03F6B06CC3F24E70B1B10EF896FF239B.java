
package org.openbravo.erpWindows.org.wirabumi.cam.AssetPrint;




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
public class Header03F6B06CC3F24E70B1B10EF896FF239B extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "CEDD67DCA2064B54BD39C0CB14A51C7D";
  private static final String tabId = "03F6B06CC3F24E70B1B10EF896FF239B";
  private static final int accesslevel = 1;
  private static final String moduleId = "5C40CF085DCA477AB9DA89559237F7EB";
  
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
     
      if (command.contains("E068B05336BC48459E3BF33241C67B04")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("E068B05336BC48459E3BF33241C67B04");
        SessionInfo.setModuleId("5C40CF085DCA477AB9DA89559237F7EB");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("E068B05336BC48459E3BF33241C67B04") || (securedProcess && command.contains("E068B05336BC48459E3BF33241C67B04"))) {
        classInfo.type = "P";
        classInfo.id = "E068B05336BC48459E3BF33241C67B04";
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

    } else if (vars.commandIn("BUTTONprintassetE068B05336BC48459E3BF33241C67B04")) {
        vars.setSessionValue("buttonE068B05336BC48459E3BF33241C67B04.strprintasset", vars.getStringParameter("inpprintasset"));
        vars.setSessionValue("buttonE068B05336BC48459E3BF33241C67B04.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonE068B05336BC48459E3BF33241C67B04.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonE068B05336BC48459E3BF33241C67B04.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonE068B05336BC48459E3BF33241C67B04.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "E068B05336BC48459E3BF33241C67B04", request.getServletPath());
      } else if (vars.commandIn("BUTTONE068B05336BC48459E3BF33241C67B04")) {
        String strCAM_Assetprint_ID = vars.getGlobalVariable("inpcamAssetprintId", windowId + "|CAM_Assetprint_ID", "");
        String strprintasset = vars.getSessionValue("buttonE068B05336BC48459E3BF33241C67B04.strprintasset");
        String strProcessing = vars.getSessionValue("buttonE068B05336BC48459E3BF33241C67B04.strProcessing");
        String strOrg = vars.getSessionValue("buttonE068B05336BC48459E3BF33241C67B04.strOrg");
        String strClient = vars.getSessionValue("buttonE068B05336BC48459E3BF33241C67B04.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonprintassetE068B05336BC48459E3BF33241C67B04(response, vars, strCAM_Assetprint_ID, strprintasset, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONprintassetE068B05336BC48459E3BF33241C67B04")) {
        String strCAM_Assetprint_ID = vars.getGlobalVariable("inpKey", windowId + "|CAM_Assetprint_ID", "");
        
        ProcessBundle pb = new ProcessBundle("E068B05336BC48459E3BF33241C67B04", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("CAM_Assetprint_ID", strCAM_Assetprint_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strprinteraddress = vars.getStringParameter("inpprinteraddress");
params.put("printeraddress", strprinteraddress);
String strprinterport = vars.getStringParameter("inpprinterport");
params.put("printerport", strprinterport);

        
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



    void printPageButtonprintassetE068B05336BC48459E3BF33241C67B04(HttpServletResponse response, VariablesSecureApp vars, String strCAM_Assetprint_ID, String strprintasset, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E068B05336BC48459E3BF33241C67B04");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/printassetE068B05336BC48459E3BF33241C67B04", discard).createXmlDocument();
      xmlDocument.setParameter("key", strCAM_Assetprint_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header03F6B06CC3F24E70B1B10EF896FF239B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "E068B05336BC48459E3BF33241C67B04");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("E068B05336BC48459E3BF33241C67B04");
        vars.removeMessage("E068B05336BC48459E3BF33241C67B04");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("PRINTERADDRESS", Utility.getContext(this, vars, "RAWTEXTPRINTERADDRESS", "CEDD67DCA2064B54BD39C0CB14A51C7D"));
    xmlDocument.setParameter("PRINTERPORT", Utility.getContext(this, vars, "RAWTEXTPRINTERPORT", "CEDD67DCA2064B54BD39C0CB14A51C7D"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
