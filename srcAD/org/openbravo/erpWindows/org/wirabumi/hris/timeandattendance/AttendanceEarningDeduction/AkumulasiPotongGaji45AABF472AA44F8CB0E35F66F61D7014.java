
package org.openbravo.erpWindows.org.wirabumi.hris.timeandattendance.AttendanceEarningDeduction;


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
public class AkumulasiPotongGaji45AABF472AA44F8CB0E35F66F61D7014 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "FFA4E1934BE94BCEA91C460BF7FF4237";
  private static final String tabId = "45AABF472AA44F8CB0E35F66F61D7014";
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
     
      if (command.contains("1AFD4E178B5F46508D625C1B4BD6168C")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("1AFD4E178B5F46508D625C1B4BD6168C");
        SessionInfo.setModuleId("13F494C4C03141258F1193B673570F63");
        if (securedProcess || explicitAccess.contains("1AFD4E178B5F46508D625C1B4BD6168C")) {
          classInfo.type = "P";
          classInfo.id = "1AFD4E178B5F46508D625C1B4BD6168C";
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

     } else if (vars.commandIn("BUTTONDocAction1AFD4E178B5F46508D625C1B4BD6168C")) {
        vars.setSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.strdocaction", vars.getStringParameter("inpdocaction"));
        vars.setSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.inpdocstatus", vars.getRequiredStringParameter("inpdocstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button1AFD4E178B5F46508D625C1B4BD6168C.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "1AFD4E178B5F46508D625C1B4BD6168C", request.getServletPath());    
     } else if (vars.commandIn("BUTTON1AFD4E178B5F46508D625C1B4BD6168C")) {
        String strTA_Akumulasi_Pot_Gaji_ID = vars.getGlobalVariable("inptaAkumulasiPotGajiId", windowId + "|TA_Akumulasi_Pot_Gaji_ID", "");
        String strdocaction = vars.getSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.strdocaction");
        String strProcessing = vars.getSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.strProcessing");
        String strOrg = vars.getSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.strOrg");
        String strClient = vars.getSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.strClient");
        
        String strdocstatus = vars.getSessionValue("button1AFD4E178B5F46508D625C1B4BD6168C.inpdocstatus");
String stradTableId = "D26AC7E0E5CB4A01867C0036A818CD95";

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocAction1AFD4E178B5F46508D625C1B4BD6168C(response, vars, strTA_Akumulasi_Pot_Gaji_ID, strdocaction, strProcessing, strdocstatus, stradTableId);
        }


    } else if (vars.commandIn("SAVE_BUTTONDocAction1AFD4E178B5F46508D625C1B4BD6168C")) {
        String strTA_Akumulasi_Pot_Gaji_ID = vars.getGlobalVariable("inpKey", windowId + "|TA_Akumulasi_Pot_Gaji_ID", "");
        String strdocaction = vars.getStringParameter("inpdocaction");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "1AFD4E178B5F46508D625C1B4BD6168C", (("TA_Akumulasi_Pot_Gaji_ID".equalsIgnoreCase("AD_Language"))?"0":strTA_Akumulasi_Pot_Gaji_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          AkumulasiPotongGaji45AABF472AA44F8CB0E35F66F61D7014Data.updateDocAction(this, strdocaction, strTA_Akumulasi_Pot_Gaji_ID);

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

    private void printPageButtonDocAction1AFD4E178B5F46508D625C1B4BD6168C(HttpServletResponse response, VariablesSecureApp vars, String strTA_Akumulasi_Pot_Gaji_ID, String strdocaction, String strProcessing, String strdocstatus, String stradTableId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1AFD4E178B5F46508D625C1B4BD6168C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocAction", discard).createXmlDocument();
      xmlDocument.setParameter("key", strTA_Akumulasi_Pot_Gaji_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "AkumulasiPotongGaji45AABF472AA44F8CB0E35F66F61D7014_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "1AFD4E178B5F46508D625C1B4BD6168C");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("1AFD4E178B5F46508D625C1B4BD6168C");
        vars.removeMessage("1AFD4E178B5F46508D625C1B4BD6168C");
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
xmlDocument.setParameter("processId", "1AFD4E178B5F46508D625C1B4BD6168C");
xmlDocument.setParameter("processDescription", "Process For Window Akumulasi Potong Gaji");
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
