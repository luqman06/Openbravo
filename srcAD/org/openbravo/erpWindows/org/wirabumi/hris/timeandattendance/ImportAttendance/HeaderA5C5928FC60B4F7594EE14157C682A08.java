
package org.openbravo.erpWindows.org.wirabumi.hris.timeandattendance.ImportAttendance;




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
public class HeaderA5C5928FC60B4F7594EE14157C682A08 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "994EC5B1F0034A7E9D471364F77DE19F";
  private static final String tabId = "A5C5928FC60B4F7594EE14157C682A08";
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
     
      if (command.contains("E31AE2EA06364D918674A3BF38A2760F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("E31AE2EA06364D918674A3BF38A2760F");
        SessionInfo.setModuleId("13F494C4C03141258F1193B673570F63");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("E31AE2EA06364D918674A3BF38A2760F") || (securedProcess && command.contains("E31AE2EA06364D918674A3BF38A2760F"))) {
        classInfo.type = "P";
        classInfo.id = "E31AE2EA06364D918674A3BF38A2760F";
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

    } else if (vars.commandIn("BUTTONImport_AttendanceE31AE2EA06364D918674A3BF38A2760F")) {
        vars.setSessionValue("buttonE31AE2EA06364D918674A3BF38A2760F.strimportAttendance", vars.getStringParameter("inpimportAttendance"));
        vars.setSessionValue("buttonE31AE2EA06364D918674A3BF38A2760F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonE31AE2EA06364D918674A3BF38A2760F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonE31AE2EA06364D918674A3BF38A2760F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonE31AE2EA06364D918674A3BF38A2760F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "E31AE2EA06364D918674A3BF38A2760F", request.getServletPath());
      } else if (vars.commandIn("BUTTONE31AE2EA06364D918674A3BF38A2760F")) {
        String strTA_I_Attendance_ID = vars.getGlobalVariable("inptaIAttendanceId", windowId + "|TA_I_Attendance_ID", "");
        String strimportAttendance = vars.getSessionValue("buttonE31AE2EA06364D918674A3BF38A2760F.strimportAttendance");
        String strProcessing = vars.getSessionValue("buttonE31AE2EA06364D918674A3BF38A2760F.strProcessing");
        String strOrg = vars.getSessionValue("buttonE31AE2EA06364D918674A3BF38A2760F.strOrg");
        String strClient = vars.getSessionValue("buttonE31AE2EA06364D918674A3BF38A2760F.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonImport_AttendanceE31AE2EA06364D918674A3BF38A2760F(response, vars, strTA_I_Attendance_ID, strimportAttendance, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONImport_AttendanceE31AE2EA06364D918674A3BF38A2760F")) {
        String strTA_I_Attendance_ID = vars.getGlobalVariable("inpKey", windowId + "|TA_I_Attendance_ID", "");
        
        ProcessBundle pb = new ProcessBundle("E31AE2EA06364D918674A3BF38A2760F", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("TA_I_Attendance_ID", strTA_I_Attendance_ID);
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



    void printPageButtonImport_AttendanceE31AE2EA06364D918674A3BF38A2760F(HttpServletResponse response, VariablesSecureApp vars, String strTA_I_Attendance_ID, String strimportAttendance, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E31AE2EA06364D918674A3BF38A2760F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Import_AttendanceE31AE2EA06364D918674A3BF38A2760F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strTA_I_Attendance_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "HeaderA5C5928FC60B4F7594EE14157C682A08_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "E31AE2EA06364D918674A3BF38A2760F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("E31AE2EA06364D918674A3BF38A2760F");
        vars.removeMessage("E31AE2EA06364D918674A3BF38A2760F");
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
