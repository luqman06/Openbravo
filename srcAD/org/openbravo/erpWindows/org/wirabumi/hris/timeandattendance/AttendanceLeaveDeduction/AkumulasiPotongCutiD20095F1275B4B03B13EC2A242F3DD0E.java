
package org.openbravo.erpWindows.org.wirabumi.hris.timeandattendance.AttendanceLeaveDeduction;


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
public class AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0E extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "8CDF1B5FFEE842D697C3CFE0DAEE357A";
  private static final String tabId = "D20095F1275B4B03B13EC2A242F3DD0E";
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
     
      if (command.contains("5FECC86201AA4D5197A719945980B2EF")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("5FECC86201AA4D5197A719945980B2EF");
        SessionInfo.setModuleId("13F494C4C03141258F1193B673570F63");
        if (securedProcess || explicitAccess.contains("5FECC86201AA4D5197A719945980B2EF")) {
          classInfo.type = "P";
          classInfo.id = "5FECC86201AA4D5197A719945980B2EF";
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

     } else if (vars.commandIn("BUTTONDocAction5FECC86201AA4D5197A719945980B2EF")) {
        vars.setSessionValue("button5FECC86201AA4D5197A719945980B2EF.strdocaction", vars.getStringParameter("inpdocaction"));
        vars.setSessionValue("button5FECC86201AA4D5197A719945980B2EF.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button5FECC86201AA4D5197A719945980B2EF.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button5FECC86201AA4D5197A719945980B2EF.strClient", vars.getStringParameter("inpadClientId"));
        vars.setSessionValue("button5FECC86201AA4D5197A719945980B2EF.inpdocstatus", vars.getRequiredStringParameter("inpdocstatus"));

        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button5FECC86201AA4D5197A719945980B2EF.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "5FECC86201AA4D5197A719945980B2EF", request.getServletPath());    
     } else if (vars.commandIn("BUTTON5FECC86201AA4D5197A719945980B2EF")) {
        String strTA_Akumulasi_Pot_Cuti_ID = vars.getGlobalVariable("inptaAkumulasiPotCutiId", windowId + "|TA_Akumulasi_Pot_Cuti_ID", "");
        String strdocaction = vars.getSessionValue("button5FECC86201AA4D5197A719945980B2EF.strdocaction");
        String strProcessing = vars.getSessionValue("button5FECC86201AA4D5197A719945980B2EF.strProcessing");
        String strOrg = vars.getSessionValue("button5FECC86201AA4D5197A719945980B2EF.strOrg");
        String strClient = vars.getSessionValue("button5FECC86201AA4D5197A719945980B2EF.strClient");
        
        String strdocstatus = vars.getSessionValue("button5FECC86201AA4D5197A719945980B2EF.inpdocstatus");
String stradTableId = "595380A2D3D84B0EAEFC01540694BEC8";

        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonDocAction5FECC86201AA4D5197A719945980B2EF(response, vars, strTA_Akumulasi_Pot_Cuti_ID, strdocaction, strProcessing, strdocstatus, stradTableId);
        }


    } else if (vars.commandIn("SAVE_BUTTONDocAction5FECC86201AA4D5197A719945980B2EF")) {
        String strTA_Akumulasi_Pot_Cuti_ID = vars.getGlobalVariable("inpKey", windowId + "|TA_Akumulasi_Pot_Cuti_ID", "");
        String strdocaction = vars.getStringParameter("inpdocaction");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "5FECC86201AA4D5197A719945980B2EF", (("TA_Akumulasi_Pot_Cuti_ID".equalsIgnoreCase("AD_Language"))?"0":strTA_Akumulasi_Pot_Cuti_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0EData.updateDocAction(this, strdocaction, strTA_Akumulasi_Pot_Cuti_ID);

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

    private void printPageButtonDocAction5FECC86201AA4D5197A719945980B2EF(HttpServletResponse response, VariablesSecureApp vars, String strTA_Akumulasi_Pot_Cuti_ID, String strdocaction, String strProcessing, String strdocstatus, String stradTableId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 5FECC86201AA4D5197A719945980B2EF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/DocAction", discard).createXmlDocument();
      xmlDocument.setParameter("key", strTA_Akumulasi_Pot_Cuti_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "AkumulasiPotongCutiD20095F1275B4B03B13EC2A242F3DD0E_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "5FECC86201AA4D5197A719945980B2EF");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("5FECC86201AA4D5197A719945980B2EF");
        vars.removeMessage("5FECC86201AA4D5197A719945980B2EF");
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
xmlDocument.setParameter("processId", "5FECC86201AA4D5197A719945980B2EF");
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