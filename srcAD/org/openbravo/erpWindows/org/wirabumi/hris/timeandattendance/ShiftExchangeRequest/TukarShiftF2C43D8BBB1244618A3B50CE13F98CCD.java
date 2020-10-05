
package org.openbravo.erpWindows.org.wirabumi.hris.timeandattendance.ShiftExchangeRequest;


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
public class TukarShiftF2C43D8BBB1244618A3B50CE13F98CCD extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "7C2A70A67C6D44FA9FBB01255B272A1C";
  private static final String tabId = "F2C43D8BBB1244618A3B50CE13F98CCD";
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
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("A70A8A5AEF284DEB89F36BAEB7CE077A")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("A70A8A5AEF284DEB89F36BAEB7CE077A");
        SessionInfo.setModuleId("13F494C4C03141258F1193B673570F63");
        if (securedProcess || explicitAccess.contains("A70A8A5AEF284DEB89F36BAEB7CE077A")) {
          classInfo.type = "P";
          classInfo.id = "A70A8A5AEF284DEB89F36BAEB7CE077A";
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

     } else if (vars.commandIn("BUTTONDocActionA70A8A5AEF284DEB89F36BAEB7CE077A")) {
        vars.setSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.strdocaction", vars.getStringParameter("inpdocaction"));
        vars.setSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.inpdocstatus", vars.getRequiredStringParameter("inpdocstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "A70A8A5AEF284DEB89F36BAEB7CE077A", request.getServletPath());    
     } else if (vars.commandIn("BUTTONA70A8A5AEF284DEB89F36BAEB7CE077A")) {
        String strTA_Tukar_Shift_ID = vars.getGlobalVariable("inptaTukarShiftId", windowId + "|TA_Tukar_Shift_ID", "");
        String strdocaction = vars.getSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.strdocaction");
        String strProcessing = vars.getSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.strProcessing");
        String strOrg = vars.getSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.strOrg");
        String strClient = vars.getSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.strClient");
        
        String strdocstatus = vars.getSessionValue("buttonA70A8A5AEF284DEB89F36BAEB7CE077A.inpdocstatus");
String stradTableId = "4AA8D908587C495DBB232C9BE9B6E78D";

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocActionA70A8A5AEF284DEB89F36BAEB7CE077A(response, vars, strTA_Tukar_Shift_ID, strdocaction, strProcessing, strdocstatus, stradTableId);
        }


    } else if (vars.commandIn("SAVE_BUTTONDocActionA70A8A5AEF284DEB89F36BAEB7CE077A")) {
        String strTA_Tukar_Shift_ID = vars.getGlobalVariable("inpKey", windowId + "|TA_Tukar_Shift_ID", "");
        String strdocaction = vars.getStringParameter("inpdocaction");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "A70A8A5AEF284DEB89F36BAEB7CE077A", (("TA_Tukar_Shift_ID".equalsIgnoreCase("AD_Language"))?"0":strTA_Tukar_Shift_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          TukarShiftF2C43D8BBB1244618A3B50CE13F98CCDData.updateDocAction(this, strdocaction, strTA_Tukar_Shift_ID);

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

    private void printPageButtonDocActionA70A8A5AEF284DEB89F36BAEB7CE077A(HttpServletResponse response, VariablesSecureApp vars, String strTA_Tukar_Shift_ID, String strdocaction, String strProcessing, String strdocstatus, String stradTableId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A70A8A5AEF284DEB89F36BAEB7CE077A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocAction", discard).createXmlDocument();
      xmlDocument.setParameter("key", strTA_Tukar_Shift_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "TukarShiftF2C43D8BBB1244618A3B50CE13F98CCD_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "A70A8A5AEF284DEB89F36BAEB7CE077A");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("A70A8A5AEF284DEB89F36BAEB7CE077A");
        vars.removeMessage("A70A8A5AEF284DEB89F36BAEB7CE077A");
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
xmlDocument.setParameter("processId", "A70A8A5AEF284DEB89F36BAEB7CE077A");
xmlDocument.setParameter("processDescription", "");
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
