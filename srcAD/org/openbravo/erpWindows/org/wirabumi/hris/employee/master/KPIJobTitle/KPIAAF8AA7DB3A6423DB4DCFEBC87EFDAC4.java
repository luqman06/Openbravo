
package org.openbravo.erpWindows.org.wirabumi.hris.employee.master.KPIJobTitle;




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
public class KPIAAF8AA7DB3A6423DB4DCFEBC87EFDAC4 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "7531D0DBE5F84F0BB6DACC328E9B9C39";
  private static final String tabId = "AAF8AA7DB3A6423DB4DCFEBC87EFDAC4";
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
     
      if (command.contains("4CD83C2A2470454DA1762133E0744572")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("4CD83C2A2470454DA1762133E0744572");
        SessionInfo.setModuleId("D5FD35D6C7604921BA951B7E0EEE5DC2");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("4CD83C2A2470454DA1762133E0744572") || (securedProcess && command.contains("4CD83C2A2470454DA1762133E0744572"))) {
        classInfo.type = "P";
        classInfo.id = "4CD83C2A2470454DA1762133E0744572";
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

    } else if (vars.commandIn("BUTTONProcess_Kpi_Measurement4CD83C2A2470454DA1762133E0744572")) {
        vars.setSessionValue("button4CD83C2A2470454DA1762133E0744572.strprocessKpiMeasurement", vars.getStringParameter("inpprocessKpiMeasurement"));
        vars.setSessionValue("button4CD83C2A2470454DA1762133E0744572.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button4CD83C2A2470454DA1762133E0744572.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button4CD83C2A2470454DA1762133E0744572.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button4CD83C2A2470454DA1762133E0744572.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "4CD83C2A2470454DA1762133E0744572", request.getServletPath());
      } else if (vars.commandIn("BUTTON4CD83C2A2470454DA1762133E0744572")) {
        String strHris_Jt_Kpi_ID = vars.getGlobalVariable("inphrisJtKpiId", windowId + "|Hris_Jt_Kpi_ID", "");
        String strprocessKpiMeasurement = vars.getSessionValue("button4CD83C2A2470454DA1762133E0744572.strprocessKpiMeasurement");
        String strProcessing = vars.getSessionValue("button4CD83C2A2470454DA1762133E0744572.strProcessing");
        String strOrg = vars.getSessionValue("button4CD83C2A2470454DA1762133E0744572.strOrg");
        String strClient = vars.getSessionValue("button4CD83C2A2470454DA1762133E0744572.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProcess_Kpi_Measurement4CD83C2A2470454DA1762133E0744572(response, vars, strHris_Jt_Kpi_ID, strprocessKpiMeasurement, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProcess_Kpi_Measurement4CD83C2A2470454DA1762133E0744572")) {
        String strHris_Jt_Kpi_ID = vars.getGlobalVariable("inpKey", windowId + "|Hris_Jt_Kpi_ID", "");
        
        ProcessBundle pb = new ProcessBundle("4CD83C2A2470454DA1762133E0744572", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("Hris_Jt_Kpi_ID", strHris_Jt_Kpi_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strcYearId = vars.getStringParameter("inpcYearId");
params.put("cYearId", strcYearId);
String strcPeriodId = vars.getStringParameter("inpcPeriodId");
params.put("cPeriodId", strcPeriodId);

        
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



    void printPageButtonProcess_Kpi_Measurement4CD83C2A2470454DA1762133E0744572(HttpServletResponse response, VariablesSecureApp vars, String strHris_Jt_Kpi_ID, String strprocessKpiMeasurement, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4CD83C2A2470454DA1762133E0744572");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Process_Kpi_Measurement4CD83C2A2470454DA1762133E0744572", discard).createXmlDocument();
      xmlDocument.setParameter("key", strHris_Jt_Kpi_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "KPIAAF8AA7DB3A6423DB4DCFEBC87EFDAC4_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "4CD83C2A2470454DA1762133E0744572");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("4CD83C2A2470454DA1762133E0744572");
        vars.removeMessage("4CD83C2A2470454DA1762133E0744572");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("C_Year_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Year_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button4CD83C2A2470454DA1762133E0744572.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_Year_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Period_Id", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_Period_Id", "275", "C3AB162BAFED423CB8D5107902FD621B", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button4CD83C2A2470454DA1762133E0744572.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_Period_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
