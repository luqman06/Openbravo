
package org.openbravo.erpWindows.org.wirabumi.hris.leave.LeavePermit;


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
public class IzinTidakMasuk232E04612F9847C080A9393F93607629 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "F51244E514C04E7E8E8669564CD1EBFC";
  private static final String tabId = "232E04612F9847C080A9393F93607629";
  private static final int accesslevel = 3;
  private static final String moduleId = "9D09529FB5794D439ACCC15A2CCC91AF";
  
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
     
      if (command.contains("D755875FCAAA4E5F94DCB9A823EC4B12")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("D755875FCAAA4E5F94DCB9A823EC4B12");
        SessionInfo.setModuleId("9D09529FB5794D439ACCC15A2CCC91AF");
        if (securedProcess || explicitAccess.contains("D755875FCAAA4E5F94DCB9A823EC4B12")) {
          classInfo.type = "P";
          classInfo.id = "D755875FCAAA4E5F94DCB9A823EC4B12";
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

     } else if (vars.commandIn("BUTTONDocActionD755875FCAAA4E5F94DCB9A823EC4B12")) {
        vars.setSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.strdocaction", vars.getStringParameter("inpdocaction"));
        vars.setSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.inpdocstatus", vars.getRequiredStringParameter("inpdocstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonD755875FCAAA4E5F94DCB9A823EC4B12.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "D755875FCAAA4E5F94DCB9A823EC4B12", request.getServletPath());    
     } else if (vars.commandIn("BUTTOND755875FCAAA4E5F94DCB9A823EC4B12")) {
        String strLV_Tidakmasuk_ID = vars.getGlobalVariable("inplvTidakmasukId", windowId + "|LV_Tidakmasuk_ID", "");
        String strdocaction = vars.getSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.strdocaction");
        String strProcessing = vars.getSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.strProcessing");
        String strOrg = vars.getSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.strOrg");
        String strClient = vars.getSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.strClient");
        
        String strdocstatus = vars.getSessionValue("buttonD755875FCAAA4E5F94DCB9A823EC4B12.inpdocstatus");
String stradTableId = "E60EEF6D59424BB1808113B95BA9D2D9";

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocActionD755875FCAAA4E5F94DCB9A823EC4B12(response, vars, strLV_Tidakmasuk_ID, strdocaction, strProcessing, strdocstatus, stradTableId);
        }


    } else if (vars.commandIn("SAVE_BUTTONDocActionD755875FCAAA4E5F94DCB9A823EC4B12")) {
        String strLV_Tidakmasuk_ID = vars.getGlobalVariable("inpKey", windowId + "|LV_Tidakmasuk_ID", "");
        String strdocaction = vars.getStringParameter("inpdocaction");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "D755875FCAAA4E5F94DCB9A823EC4B12", (("LV_Tidakmasuk_ID".equalsIgnoreCase("AD_Language"))?"0":strLV_Tidakmasuk_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          IzinTidakMasuk232E04612F9847C080A9393F93607629Data.updateDocAction(this, strdocaction, strLV_Tidakmasuk_ID);

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

    private void printPageButtonDocActionD755875FCAAA4E5F94DCB9A823EC4B12(HttpServletResponse response, VariablesSecureApp vars, String strLV_Tidakmasuk_ID, String strdocaction, String strProcessing, String strdocstatus, String stradTableId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D755875FCAAA4E5F94DCB9A823EC4B12");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocAction", discard).createXmlDocument();
      xmlDocument.setParameter("key", strLV_Tidakmasuk_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "IzinTidakMasuk232E04612F9847C080A9393F93607629_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "D755875FCAAA4E5F94DCB9A823EC4B12");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("D755875FCAAA4E5F94DCB9A823EC4B12");
        vars.removeMessage("D755875FCAAA4E5F94DCB9A823EC4B12");
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
xmlDocument.setParameter("processId", "D755875FCAAA4E5F94DCB9A823EC4B12");
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
