
package org.openbravo.erpWindows.org.wirabumi.hris.payroll.SalaryPayment;




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
public class Employee96053D5524774B4CAE849212A857C0A0 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "3232B3E1FE8944B599670929913352A8";
  private static final String tabId = "96053D5524774B4CAE849212A857C0A0";
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
     
      if (command.contains("0B9BC0C6675E48E083EF02153A8C6D4B")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("0B9BC0C6675E48E083EF02153A8C6D4B");
        SessionInfo.setModuleId("F4C0EB8391244C619477DC6D85868976");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("0B9BC0C6675E48E083EF02153A8C6D4B") || (securedProcess && command.contains("0B9BC0C6675E48E083EF02153A8C6D4B"))) {
        classInfo.type = "P";
        classInfo.id = "0B9BC0C6675E48E083EF02153A8C6D4B";
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

    } else if (vars.commandIn("BUTTONcalculatesalaryformula0B9BC0C6675E48E083EF02153A8C6D4B")) {
        vars.setSessionValue("button0B9BC0C6675E48E083EF02153A8C6D4B.strcalculatesalaryformula", vars.getStringParameter("inpcalculatesalaryformula"));
        vars.setSessionValue("button0B9BC0C6675E48E083EF02153A8C6D4B.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button0B9BC0C6675E48E083EF02153A8C6D4B.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button0B9BC0C6675E48E083EF02153A8C6D4B.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button0B9BC0C6675E48E083EF02153A8C6D4B.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "0B9BC0C6675E48E083EF02153A8C6D4B", request.getServletPath());
      } else if (vars.commandIn("BUTTON0B9BC0C6675E48E083EF02153A8C6D4B")) {
        String strPYR_Sp_Employee_ID = vars.getGlobalVariable("inppyrSpEmployeeId", windowId + "|PYR_Sp_Employee_ID", "");
        String strcalculatesalaryformula = vars.getSessionValue("button0B9BC0C6675E48E083EF02153A8C6D4B.strcalculatesalaryformula");
        String strProcessing = vars.getSessionValue("button0B9BC0C6675E48E083EF02153A8C6D4B.strProcessing");
        String strOrg = vars.getSessionValue("button0B9BC0C6675E48E083EF02153A8C6D4B.strOrg");
        String strClient = vars.getSessionValue("button0B9BC0C6675E48E083EF02153A8C6D4B.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtoncalculatesalaryformula0B9BC0C6675E48E083EF02153A8C6D4B(response, vars, strPYR_Sp_Employee_ID, strcalculatesalaryformula, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONcalculatesalaryformula0B9BC0C6675E48E083EF02153A8C6D4B")) {
        String strPYR_Sp_Employee_ID = vars.getGlobalVariable("inpKey", windowId + "|PYR_Sp_Employee_ID", "");
        
        ProcessBundle pb = new ProcessBundle("0B9BC0C6675E48E083EF02153A8C6D4B", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("PYR_Sp_Employee_ID", strPYR_Sp_Employee_ID);
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



    void printPageButtoncalculatesalaryformula0B9BC0C6675E48E083EF02153A8C6D4B(HttpServletResponse response, VariablesSecureApp vars, String strPYR_Sp_Employee_ID, String strcalculatesalaryformula, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0B9BC0C6675E48E083EF02153A8C6D4B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/calculatesalaryformula0B9BC0C6675E48E083EF02153A8C6D4B", discard).createXmlDocument();
      xmlDocument.setParameter("key", strPYR_Sp_Employee_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Employee96053D5524774B4CAE849212A857C0A0_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "0B9BC0C6675E48E083EF02153A8C6D4B");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("0B9BC0C6675E48E083EF02153A8C6D4B");
        vars.removeMessage("0B9BC0C6675E48E083EF02153A8C6D4B");
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
