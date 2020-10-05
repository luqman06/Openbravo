
package org.openbravo.erpWindows.org.wirabumi.hris.payroll.ImportIncidentalEarning;




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
public class ImportIncidentalEarning65B600C8EBE04FC6BBE08C5D661F18F6 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "0E0B43D5E508489E963CA6B4C23E0204";
  private static final String tabId = "65B600C8EBE04FC6BBE08C5D661F18F6";
  private static final int accesslevel = 3;
  private static final String moduleId = "F4C0EB8391244C619477DC6D85868976";
  
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
     
      if (command.contains("E17D7B1680EC44B5A93CC0E9B72DC119")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("E17D7B1680EC44B5A93CC0E9B72DC119");
        SessionInfo.setModuleId("F4C0EB8391244C619477DC6D85868976");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("E17D7B1680EC44B5A93CC0E9B72DC119") || (securedProcess && command.contains("E17D7B1680EC44B5A93CC0E9B72DC119"))) {
        classInfo.type = "P";
        classInfo.id = "E17D7B1680EC44B5A93CC0E9B72DC119";
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

    } else if (vars.commandIn("BUTTONImport_Inc_EarningE17D7B1680EC44B5A93CC0E9B72DC119")) {
        vars.setSessionValue("buttonE17D7B1680EC44B5A93CC0E9B72DC119.strimportIncEarning", vars.getStringParameter("inpimportIncEarning"));
        vars.setSessionValue("buttonE17D7B1680EC44B5A93CC0E9B72DC119.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonE17D7B1680EC44B5A93CC0E9B72DC119.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonE17D7B1680EC44B5A93CC0E9B72DC119.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonE17D7B1680EC44B5A93CC0E9B72DC119.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "E17D7B1680EC44B5A93CC0E9B72DC119", request.getServletPath());
      } else if (vars.commandIn("BUTTONE17D7B1680EC44B5A93CC0E9B72DC119")) {
        String strPYR_Inc_I_Earning_ID = vars.getGlobalVariable("inppyrIncIEarningId", windowId + "|PYR_Inc_I_Earning_ID", "");
        String strimportIncEarning = vars.getSessionValue("buttonE17D7B1680EC44B5A93CC0E9B72DC119.strimportIncEarning");
        String strProcessing = vars.getSessionValue("buttonE17D7B1680EC44B5A93CC0E9B72DC119.strProcessing");
        String strOrg = vars.getSessionValue("buttonE17D7B1680EC44B5A93CC0E9B72DC119.strOrg");
        String strClient = vars.getSessionValue("buttonE17D7B1680EC44B5A93CC0E9B72DC119.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonImport_Inc_EarningE17D7B1680EC44B5A93CC0E9B72DC119(response, vars, strPYR_Inc_I_Earning_ID, strimportIncEarning, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONImport_Inc_EarningE17D7B1680EC44B5A93CC0E9B72DC119")) {
        String strPYR_Inc_I_Earning_ID = vars.getGlobalVariable("inpKey", windowId + "|PYR_Inc_I_Earning_ID", "");
        
        ProcessBundle pb = new ProcessBundle("E17D7B1680EC44B5A93CC0E9B72DC119", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("PYR_Inc_I_Earning_ID", strPYR_Inc_I_Earning_ID);
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



    void printPageButtonImport_Inc_EarningE17D7B1680EC44B5A93CC0E9B72DC119(HttpServletResponse response, VariablesSecureApp vars, String strPYR_Inc_I_Earning_ID, String strimportIncEarning, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E17D7B1680EC44B5A93CC0E9B72DC119");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Import_Inc_EarningE17D7B1680EC44B5A93CC0E9B72DC119", discard).createXmlDocument();
      xmlDocument.setParameter("key", strPYR_Inc_I_Earning_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ImportIncidentalEarning65B600C8EBE04FC6BBE08C5D661F18F6_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "E17D7B1680EC44B5A93CC0E9B72DC119");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("E17D7B1680EC44B5A93CC0E9B72DC119");
        vars.removeMessage("E17D7B1680EC44B5A93CC0E9B72DC119");
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
