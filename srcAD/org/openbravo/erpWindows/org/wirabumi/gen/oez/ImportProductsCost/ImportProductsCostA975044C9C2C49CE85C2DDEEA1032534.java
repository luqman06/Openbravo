
package org.openbravo.erpWindows.org.wirabumi.gen.oez.ImportProductsCost;


import org.openbravo.erpCommon.reference.*;



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
public class ImportProductsCostA975044C9C2C49CE85C2DDEEA1032534 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "7BD03245396B4345909BCC97045E651A";
  private static final String tabId = "A975044C9C2C49CE85C2DDEEA1032534";
  private static final int accesslevel = 3;
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
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("7BFDF49C41354EE49E9BE08C3E3C1B3E")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("7BFDF49C41354EE49E9BE08C3E3C1B3E");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
        if (securedProcess || explicitAccess.contains("7BFDF49C41354EE49E9BE08C3E3C1B3E")) {
          classInfo.type = "P";
          classInfo.id = "7BFDF49C41354EE49E9BE08C3E3C1B3E";
        }
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

     } else if (vars.commandIn("BUTTONProcessed7BFDF49C41354EE49E9BE08C3E3C1B3E")) {
        vars.setSessionValue("button7BFDF49C41354EE49E9BE08C3E3C1B3E.strprocessed", vars.getStringParameter("inpprocessed"));
        vars.setSessionValue("button7BFDF49C41354EE49E9BE08C3E3C1B3E.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button7BFDF49C41354EE49E9BE08C3E3C1B3E.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button7BFDF49C41354EE49E9BE08C3E3C1B3E.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button7BFDF49C41354EE49E9BE08C3E3C1B3E.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "7BFDF49C41354EE49E9BE08C3E3C1B3E", request.getServletPath());    
     } else if (vars.commandIn("BUTTON7BFDF49C41354EE49E9BE08C3E3C1B3E")) {
        String strOEZ_I_Productcost_ID = vars.getGlobalVariable("inpoezIProductcostId", windowId + "|OEZ_I_Productcost_ID", "");
        String strprocessed = vars.getSessionValue("button7BFDF49C41354EE49E9BE08C3E3C1B3E.strprocessed");
        String strProcessing = vars.getSessionValue("button7BFDF49C41354EE49E9BE08C3E3C1B3E.strProcessing");
        String strOrg = vars.getSessionValue("button7BFDF49C41354EE49E9BE08C3E3C1B3E.strOrg");
        String strClient = vars.getSessionValue("button7BFDF49C41354EE49E9BE08C3E3C1B3E.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessed7BFDF49C41354EE49E9BE08C3E3C1B3E(response, vars, strOEZ_I_Productcost_ID, strprocessed, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcessed7BFDF49C41354EE49E9BE08C3E3C1B3E")) {
        String strOEZ_I_Productcost_ID = vars.getGlobalVariable("inpKey", windowId + "|OEZ_I_Productcost_ID", "");
        String strprocessed = vars.getStringParameter("inpprocessed");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "7BFDF49C41354EE49E9BE08C3E3C1B3E", (("OEZ_I_Productcost_ID".equalsIgnoreCase("AD_Language"))?"0":strOEZ_I_Productcost_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
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

    private void printPageButtonProcessed7BFDF49C41354EE49E9BE08C3E3C1B3E(HttpServletResponse response, VariablesSecureApp vars, String strOEZ_I_Productcost_ID, String strprocessed, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7BFDF49C41354EE49E9BE08C3E3C1B3E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Processed7BFDF49C41354EE49E9BE08C3E3C1B3E", discard).createXmlDocument();
      xmlDocument.setParameter("key", strOEZ_I_Productcost_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ImportProductsCostA975044C9C2C49CE85C2DDEEA1032534_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "7BFDF49C41354EE49E9BE08C3E3C1B3E");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("7BFDF49C41354EE49E9BE08C3E3C1B3E");
        vars.removeMessage("7BFDF49C41354EE49E9BE08C3E3C1B3E");
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
