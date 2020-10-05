
package org.openbravo.erpWindows.org.wirabumi.hris.timeandattendance.ImportEmployeeManualSchedule;




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
public class HeaderD110ED6DBF344F8894CA414171412610 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "C4DE7DC3314C4029B6D1F38C70E919A4";
  private static final String tabId = "D110ED6DBF344F8894CA414171412610";
  private static final int accesslevel = 3;
  private static final String moduleId = "13F494C4C03141258F1193B673570F63";
  
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
     
      if (command.contains("DC1DA3F261684A1D8EFA223CF2FFA25F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("DC1DA3F261684A1D8EFA223CF2FFA25F");
        SessionInfo.setModuleId("13F494C4C03141258F1193B673570F63");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("DC1DA3F261684A1D8EFA223CF2FFA25F") || (securedProcess && command.contains("DC1DA3F261684A1D8EFA223CF2FFA25F"))) {
        classInfo.type = "P";
        classInfo.id = "DC1DA3F261684A1D8EFA223CF2FFA25F";
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

    } else if (vars.commandIn("BUTTONProcessingDC1DA3F261684A1D8EFA223CF2FFA25F")) {
        vars.setSessionValue("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.strprocessing", vars.getStringParameter("inpprocessing"));
        vars.setSessionValue("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "DC1DA3F261684A1D8EFA223CF2FFA25F", request.getServletPath());
      } else if (vars.commandIn("BUTTONDC1DA3F261684A1D8EFA223CF2FFA25F")) {
        String strTA_I_Manualschedule_ID = vars.getGlobalVariable("inptaIManualscheduleId", windowId + "|TA_I_Manualschedule_ID", "");
        String strprocessing = vars.getSessionValue("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.strprocessing");
        String strProcessing = vars.getSessionValue("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.strProcessing");
        String strOrg = vars.getSessionValue("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.strOrg");
        String strClient = vars.getSessionValue("buttonDC1DA3F261684A1D8EFA223CF2FFA25F.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcessingDC1DA3F261684A1D8EFA223CF2FFA25F(response, vars, strTA_I_Manualschedule_ID, strprocessing, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcessingDC1DA3F261684A1D8EFA223CF2FFA25F")) {
        String strTA_I_Manualschedule_ID = vars.getGlobalVariable("inpKey", windowId + "|TA_I_Manualschedule_ID", "");
        
        ProcessBundle pb = new ProcessBundle("DC1DA3F261684A1D8EFA223CF2FFA25F", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("TA_I_Manualschedule_ID", strTA_I_Manualschedule_ID);
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



    void printPageButtonProcessingDC1DA3F261684A1D8EFA223CF2FFA25F(HttpServletResponse response, VariablesSecureApp vars, String strTA_I_Manualschedule_ID, String strprocessing, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DC1DA3F261684A1D8EFA223CF2FFA25F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ProcessingDC1DA3F261684A1D8EFA223CF2FFA25F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strTA_I_Manualschedule_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "HeaderD110ED6DBF344F8894CA414171412610_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "DC1DA3F261684A1D8EFA223CF2FFA25F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("DC1DA3F261684A1D8EFA223CF2FFA25F");
        vars.removeMessage("DC1DA3F261684A1D8EFA223CF2FFA25F");
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
