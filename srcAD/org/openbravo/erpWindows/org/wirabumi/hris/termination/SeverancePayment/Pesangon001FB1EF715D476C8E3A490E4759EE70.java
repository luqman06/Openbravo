
package org.openbravo.erpWindows.org.wirabumi.hris.termination.SeverancePayment;


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
public class Pesangon001FB1EF715D476C8E3A490E4759EE70 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "512451ECA3854CF79F47393E87791B5B";
  private static final String tabId = "001FB1EF715D476C8E3A490E4759EE70";
  private static final int accesslevel = 3;
  private static final String moduleId = "EEF85801B64F4FB982F0E08DCEDCF98A";
  
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
     
      if (command.contains("0E7B87FE7F194CAB9F0970E238681177")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("0E7B87FE7F194CAB9F0970E238681177");
        SessionInfo.setModuleId("EEF85801B64F4FB982F0E08DCEDCF98A");
        if (securedProcess || explicitAccess.contains("0E7B87FE7F194CAB9F0970E238681177")) {
          classInfo.type = "P";
          classInfo.id = "0E7B87FE7F194CAB9F0970E238681177";
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

     } else if (vars.commandIn("BUTTONDocAction0E7B87FE7F194CAB9F0970E238681177")) {
        vars.setSessionValue("button0E7B87FE7F194CAB9F0970E238681177.strdocaction", vars.getStringParameter("inpdocaction"));
        vars.setSessionValue("button0E7B87FE7F194CAB9F0970E238681177.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button0E7B87FE7F194CAB9F0970E238681177.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button0E7B87FE7F194CAB9F0970E238681177.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("button0E7B87FE7F194CAB9F0970E238681177.inpdocstatus", vars.getRequiredStringParameter("inpdocstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button0E7B87FE7F194CAB9F0970E238681177.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "0E7B87FE7F194CAB9F0970E238681177", request.getServletPath());    
     } else if (vars.commandIn("BUTTON0E7B87FE7F194CAB9F0970E238681177")) {
        String strTM_Form_Pesangon_ID = vars.getGlobalVariable("inptmFormPesangonId", windowId + "|TM_Form_Pesangon_ID", "");
        String strdocaction = vars.getSessionValue("button0E7B87FE7F194CAB9F0970E238681177.strdocaction");
        String strProcessing = vars.getSessionValue("button0E7B87FE7F194CAB9F0970E238681177.strProcessing");
        String strOrg = vars.getSessionValue("button0E7B87FE7F194CAB9F0970E238681177.strOrg");
        String strClient = vars.getSessionValue("button0E7B87FE7F194CAB9F0970E238681177.strClient");
        
        String strdocstatus = vars.getSessionValue("button0E7B87FE7F194CAB9F0970E238681177.inpdocstatus");
String stradTableId = "130B6EC1C7244CD186785E9C549260B0";

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocAction0E7B87FE7F194CAB9F0970E238681177(response, vars, strTM_Form_Pesangon_ID, strdocaction, strProcessing, strdocstatus, stradTableId);
        }


    } else if (vars.commandIn("SAVE_BUTTONDocAction0E7B87FE7F194CAB9F0970E238681177")) {
        String strTM_Form_Pesangon_ID = vars.getGlobalVariable("inpKey", windowId + "|TM_Form_Pesangon_ID", "");
        String strdocaction = vars.getStringParameter("inpdocaction");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "0E7B87FE7F194CAB9F0970E238681177", (("TM_Form_Pesangon_ID".equalsIgnoreCase("AD_Language"))?"0":strTM_Form_Pesangon_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          Pesangon001FB1EF715D476C8E3A490E4759EE70Data.updateDocAction(this, strdocaction, strTM_Form_Pesangon_ID);

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

    private void printPageButtonDocAction0E7B87FE7F194CAB9F0970E238681177(HttpServletResponse response, VariablesSecureApp vars, String strTM_Form_Pesangon_ID, String strdocaction, String strProcessing, String strdocstatus, String stradTableId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0E7B87FE7F194CAB9F0970E238681177");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocAction", discard).createXmlDocument();
      xmlDocument.setParameter("key", strTM_Form_Pesangon_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Pesangon001FB1EF715D476C8E3A490E4759EE70_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "0E7B87FE7F194CAB9F0970E238681177");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("0E7B87FE7F194CAB9F0970E238681177");
        vars.removeMessage("0E7B87FE7F194CAB9F0970E238681177");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

      xmlDocument.setParameter("docstatus", strdocstatus);
xmlDocument.setParameter("adTableId", stradTableId);
    try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }
xmlDocument.setParameter("processId", "0E7B87FE7F194CAB9F0970E238681177");
xmlDocument.setParameter("processDescription", "Post Pesangon");
xmlDocument.setParameter("docaction", (strdocaction.equals("--")?"CL":strdocaction));
FieldProvider[] dataDocAction = ActionButtonUtility.docAction(this, vars, strdocaction, "135", strdocstatus, strProcessing, stradTableId, tabId);
xmlDocument.setData("reportdocaction", "liststructure", dataDocAction);
StringBuffer dact = new StringBuffer();
if (dataDocAction!=null) {
  dact.append("var arrDocAction = new Array(\n");
  for (int i=0;i<dataDocAction.length;i++) {
    dact.append("new Array(\"" + dataDocAction[i].getField("id") + "\", \"" + dataDocAction[i].getField("name") + "\", \"" + dataDocAction[i].getField("description") + "\")\n");
    if (i<dataDocAction.length-1) dact.append(",\n");
  }
  dact.append(");");
} else dact.append("var arrDocAction = null");
xmlDocument.setParameter("array", dact.toString());

      
      out.println(xmlDocument.print());
      out.close();
    }



}
