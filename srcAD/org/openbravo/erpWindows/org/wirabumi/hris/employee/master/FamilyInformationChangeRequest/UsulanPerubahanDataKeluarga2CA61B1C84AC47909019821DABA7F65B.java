
package org.openbravo.erpWindows.org.wirabumi.hris.employee.master.FamilyInformationChangeRequest;


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
public class UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65B extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "FF10269006494B1F971010036D75B39E";
  private static final String tabId = "2CA61B1C84AC47909019821DABA7F65B";
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
     
      if (command.contains("1511B5CDC40B4F7CBD99EAB57F53AD5E")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("1511B5CDC40B4F7CBD99EAB57F53AD5E");
        SessionInfo.setModuleId("D5FD35D6C7604921BA951B7E0EEE5DC2");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("1511B5CDC40B4F7CBD99EAB57F53AD5E") || (securedProcess && command.contains("1511B5CDC40B4F7CBD99EAB57F53AD5E"))) {
        classInfo.type = "P";
        classInfo.id = "1511B5CDC40B4F7CBD99EAB57F53AD5E";
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

    } else if (vars.commandIn("BUTTONCreatedata1511B5CDC40B4F7CBD99EAB57F53AD5E")) {
        vars.setSessionValue("button1511B5CDC40B4F7CBD99EAB57F53AD5E.strcreatedata", vars.getStringParameter("inpcreatedata"));
        vars.setSessionValue("button1511B5CDC40B4F7CBD99EAB57F53AD5E.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button1511B5CDC40B4F7CBD99EAB57F53AD5E.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button1511B5CDC40B4F7CBD99EAB57F53AD5E.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button1511B5CDC40B4F7CBD99EAB57F53AD5E.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "1511B5CDC40B4F7CBD99EAB57F53AD5E", request.getServletPath());
      } else if (vars.commandIn("BUTTON1511B5CDC40B4F7CBD99EAB57F53AD5E")) {
        String strHris_Change_Family_ID = vars.getGlobalVariable("inphrisChangeFamilyId", windowId + "|Hris_Change_Family_ID", "");
        String strcreatedata = vars.getSessionValue("button1511B5CDC40B4F7CBD99EAB57F53AD5E.strcreatedata");
        String strProcessing = vars.getSessionValue("button1511B5CDC40B4F7CBD99EAB57F53AD5E.strProcessing");
        String strOrg = vars.getSessionValue("button1511B5CDC40B4F7CBD99EAB57F53AD5E.strOrg");
        String strClient = vars.getSessionValue("button1511B5CDC40B4F7CBD99EAB57F53AD5E.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonCreatedata1511B5CDC40B4F7CBD99EAB57F53AD5E(response, vars, strHris_Change_Family_ID, strcreatedata, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONCreatedata1511B5CDC40B4F7CBD99EAB57F53AD5E")) {
        String strHris_Change_Family_ID = vars.getGlobalVariable("inpKey", windowId + "|Hris_Change_Family_ID", "");
        
        ProcessBundle pb = new ProcessBundle("1511B5CDC40B4F7CBD99EAB57F53AD5E", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Hris_Change_Family_ID", strHris_Change_Family_ID);
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



    void printPageButtonCreatedata1511B5CDC40B4F7CBD99EAB57F53AD5E(HttpServletResponse response, VariablesSecureApp vars, String strHris_Change_Family_ID, String strcreatedata, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1511B5CDC40B4F7CBD99EAB57F53AD5E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Createdata1511B5CDC40B4F7CBD99EAB57F53AD5E", discard).createXmlDocument();
      xmlDocument.setParameter("key", strHris_Change_Family_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "UsulanPerubahanDataKeluarga2CA61B1C84AC47909019821DABA7F65B_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "1511B5CDC40B4F7CBD99EAB57F53AD5E");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("1511B5CDC40B4F7CBD99EAB57F53AD5E");
        vars.removeMessage("1511B5CDC40B4F7CBD99EAB57F53AD5E");
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
