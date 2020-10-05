
package org.openbravo.erpWindows.DocumentSequence;




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
public class Sequence extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "112";
  private static final String tabId = "146";
  private static final int accesslevel = 7;
  private static final String moduleId = "0";
  
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
     
      if (command.contains("9128A01FDF49406BBE7C19479DAC4414")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("9128A01FDF49406BBE7C19479DAC4414");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
      }
     
      if (command.contains("337F8E5C1B1B4F2AA1F70B54B09EF5DA")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("337F8E5C1B1B4F2AA1F70B54B09EF5DA");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("9128A01FDF49406BBE7C19479DAC4414") || (securedProcess && command.contains("9128A01FDF49406BBE7C19479DAC4414"))) {
        classInfo.type = "P";
        classInfo.id = "9128A01FDF49406BBE7C19479DAC4414";
      }
     
      if (explicitAccess.contains("337F8E5C1B1B4F2AA1F70B54B09EF5DA") || (securedProcess && command.contains("337F8E5C1B1B4F2AA1F70B54B09EF5DA"))) {
        classInfo.type = "P";
        classInfo.id = "337F8E5C1B1B4F2AA1F70B54B09EF5DA";
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

    } else if (vars.commandIn("BUTTONEM_Oez_Resetdocsequence9128A01FDF49406BBE7C19479DAC4414")) {
        vars.setSessionValue("button9128A01FDF49406BBE7C19479DAC4414.stremOezResetdocsequence", vars.getStringParameter("inpemOezResetdocsequence"));
        vars.setSessionValue("button9128A01FDF49406BBE7C19479DAC4414.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button9128A01FDF49406BBE7C19479DAC4414.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button9128A01FDF49406BBE7C19479DAC4414.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button9128A01FDF49406BBE7C19479DAC4414.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "9128A01FDF49406BBE7C19479DAC4414", request.getServletPath());
      } else if (vars.commandIn("BUTTON9128A01FDF49406BBE7C19479DAC4414")) {
        String strAD_Sequence_ID = vars.getGlobalVariable("inpadSequenceId", windowId + "|AD_Sequence_ID", "");
        String stremOezResetdocsequence = vars.getSessionValue("button9128A01FDF49406BBE7C19479DAC4414.stremOezResetdocsequence");
        String strProcessing = vars.getSessionValue("button9128A01FDF49406BBE7C19479DAC4414.strProcessing");
        String strOrg = vars.getSessionValue("button9128A01FDF49406BBE7C19479DAC4414.strOrg");
        String strClient = vars.getSessionValue("button9128A01FDF49406BBE7C19479DAC4414.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Oez_Resetdocsequence9128A01FDF49406BBE7C19479DAC4414(response, vars, strAD_Sequence_ID, stremOezResetdocsequence, strProcessing);
        }
    } else if (vars.commandIn("BUTTONEM_Oez_Generate_Month337F8E5C1B1B4F2AA1F70B54B09EF5DA")) {
        vars.setSessionValue("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.stremOezGenerateMonth", vars.getStringParameter("inpemOezGenerateMonth"));
        vars.setSessionValue("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "337F8E5C1B1B4F2AA1F70B54B09EF5DA", request.getServletPath());
      } else if (vars.commandIn("BUTTON337F8E5C1B1B4F2AA1F70B54B09EF5DA")) {
        String strAD_Sequence_ID = vars.getGlobalVariable("inpadSequenceId", windowId + "|AD_Sequence_ID", "");
        String stremOezGenerateMonth = vars.getSessionValue("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.stremOezGenerateMonth");
        String strProcessing = vars.getSessionValue("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.strProcessing");
        String strOrg = vars.getSessionValue("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.strOrg");
        String strClient = vars.getSessionValue("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Oez_Generate_Month337F8E5C1B1B4F2AA1F70B54B09EF5DA(response, vars, strAD_Sequence_ID, stremOezGenerateMonth, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_Oez_Resetdocsequence9128A01FDF49406BBE7C19479DAC4414")) {
        String strAD_Sequence_ID = vars.getGlobalVariable("inpKey", windowId + "|AD_Sequence_ID", "");
        
        ProcessBundle pb = new ProcessBundle("9128A01FDF49406BBE7C19479DAC4414", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("AD_Sequence_ID", strAD_Sequence_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String stryear = vars.getStringParameter("inpyear");
params.put("year", stryear);

        
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
    } else if (vars.commandIn("SAVE_BUTTONEM_Oez_Generate_Month337F8E5C1B1B4F2AA1F70B54B09EF5DA")) {
        String strAD_Sequence_ID = vars.getGlobalVariable("inpKey", windowId + "|AD_Sequence_ID", "");
        
        ProcessBundle pb = new ProcessBundle("337F8E5C1B1B4F2AA1F70B54B09EF5DA", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("AD_Sequence_ID", strAD_Sequence_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strcYearId = vars.getStringParameter("inpcYearId");
params.put("cYearId", strcYearId);

        
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



    void printPageButtonEM_Oez_Resetdocsequence9128A01FDF49406BBE7C19479DAC4414(HttpServletResponse response, VariablesSecureApp vars, String strAD_Sequence_ID, String stremOezResetdocsequence, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9128A01FDF49406BBE7C19479DAC4414");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Oez_Resetdocsequence9128A01FDF49406BBE7C19479DAC4414", discard).createXmlDocument();
      xmlDocument.setParameter("key", strAD_Sequence_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Sequence_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "9128A01FDF49406BBE7C19479DAC4414");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("9128A01FDF49406BBE7C19479DAC4414");
        vars.removeMessage("9128A01FDF49406BBE7C19479DAC4414");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("year", "");
    comboTableData = new ComboTableData(vars, this, "18", "year", "65A4C17DA97B411C975C7B1B258ACDB5", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button9128A01FDF49406BBE7C19479DAC4414.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportyear", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonEM_Oez_Generate_Month337F8E5C1B1B4F2AA1F70B54B09EF5DA(HttpServletResponse response, VariablesSecureApp vars, String strAD_Sequence_ID, String stremOezGenerateMonth, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 337F8E5C1B1B4F2AA1F70B54B09EF5DA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Oez_Generate_Month337F8E5C1B1B4F2AA1F70B54B09EF5DA", discard).createXmlDocument();
      xmlDocument.setParameter("key", strAD_Sequence_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Sequence_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "337F8E5C1B1B4F2AA1F70B54B09EF5DA");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("337F8E5C1B1B4F2AA1F70B54B09EF5DA");
        vars.removeMessage("337F8E5C1B1B4F2AA1F70B54B09EF5DA");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("c_year_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "c_year_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button337F8E5C1B1B4F2AA1F70B54B09EF5DA.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportc_year_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
