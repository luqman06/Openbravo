
package org.openbravo.erpWindows.org.wirabumi.hris.payroll.EmployeeSalary;


import org.openbravo.erpCommon.reference.*;



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
public class SalaryCE15636EA5924F58927F2D435176A1BA extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "FCBD7EC259414FA9BE415BB248842736";
  private static final String tabId = "CE15636EA5924F58927F2D435176A1BA";
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
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("141DCD15791C42A7946680181665DCF7")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("141DCD15791C42A7946680181665DCF7");
        SessionInfo.setModuleId("F4C0EB8391244C619477DC6D85868976");
        if (securedProcess || explicitAccess.contains("141DCD15791C42A7946680181665DCF7")) {
          classInfo.type = "P";
          classInfo.id = "141DCD15791C42A7946680181665DCF7";
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

     } else if (vars.commandIn("BUTTONEM_Pyr_Copy_From_Template141DCD15791C42A7946680181665DCF7")) {
        vars.setSessionValue("button141DCD15791C42A7946680181665DCF7.stremPyrCopyFromTemplate", vars.getStringParameter("inpemPyrCopyFromTemplate"));
        vars.setSessionValue("button141DCD15791C42A7946680181665DCF7.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button141DCD15791C42A7946680181665DCF7.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button141DCD15791C42A7946680181665DCF7.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button141DCD15791C42A7946680181665DCF7.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "141DCD15791C42A7946680181665DCF7", request.getServletPath());    
     } else if (vars.commandIn("BUTTON141DCD15791C42A7946680181665DCF7")) {
        String strC_Bp_Salcategory_ID = vars.getGlobalVariable("inpcBpSalcategoryId", windowId + "|C_Bp_Salcategory_ID", "");
        String stremPyrCopyFromTemplate = vars.getSessionValue("button141DCD15791C42A7946680181665DCF7.stremPyrCopyFromTemplate");
        String strProcessing = vars.getSessionValue("button141DCD15791C42A7946680181665DCF7.strProcessing");
        String strOrg = vars.getSessionValue("button141DCD15791C42A7946680181665DCF7.strOrg");
        String strClient = vars.getSessionValue("button141DCD15791C42A7946680181665DCF7.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Pyr_Copy_From_Template141DCD15791C42A7946680181665DCF7(response, vars, strC_Bp_Salcategory_ID, stremPyrCopyFromTemplate, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONEM_Pyr_Copy_From_Template141DCD15791C42A7946680181665DCF7")) {
        String strC_Bp_Salcategory_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Bp_Salcategory_ID", "");
        String stremPyrCopyFromTemplate = vars.getStringParameter("inpemPyrCopyFromTemplate");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "141DCD15791C42A7946680181665DCF7", (("C_Bp_Salcategory_ID".equalsIgnoreCase("AD_Language"))?"0":strC_Bp_Salcategory_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          String strcCurrencyId = vars.getStringParameter("inpcCurrencyId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "c_currency_id", strcCurrencyId, vars.getClient(), vars.getOrg(), vars.getUser());

          
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

    private void printPageButtonEM_Pyr_Copy_From_Template141DCD15791C42A7946680181665DCF7(HttpServletResponse response, VariablesSecureApp vars, String strC_Bp_Salcategory_ID, String stremPyrCopyFromTemplate, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 141DCD15791C42A7946680181665DCF7");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Pyr_Copy_From_Template141DCD15791C42A7946680181665DCF7", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Bp_Salcategory_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "SalaryCE15636EA5924F58927F2D435176A1BA_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "141DCD15791C42A7946680181665DCF7");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("141DCD15791C42A7946680181665DCF7");
        vars.removeMessage("141DCD15791C42A7946680181665DCF7");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("c_currency_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "c_currency_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button141DCD15791C42A7946680181665DCF7.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportc_currency_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }



}
