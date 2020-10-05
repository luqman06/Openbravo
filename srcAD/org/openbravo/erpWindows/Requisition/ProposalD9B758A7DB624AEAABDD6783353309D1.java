
package org.openbravo.erpWindows.Requisition;




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
public class ProposalD9B758A7DB624AEAABDD6783353309D1 extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "800092";
  private static final String tabId = "D9B758A7DB624AEAABDD6783353309D1";
  private static final int accesslevel = 1;
  private static final String moduleId = "94BACCCED37948B6905184458B3279C1";
  
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
     
      if (command.contains("AA3FEBF2A14147FAA0FE5AA479D8890F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("AA3FEBF2A14147FAA0FE5AA479D8890F");
        SessionInfo.setModuleId("94BACCCED37948B6905184458B3279C1");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("AA3FEBF2A14147FAA0FE5AA479D8890F") || (securedProcess && command.contains("AA3FEBF2A14147FAA0FE5AA479D8890F"))) {
        classInfo.type = "P";
        classInfo.id = "AA3FEBF2A14147FAA0FE5AA479D8890F";
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

    } else if (vars.commandIn("BUTTONProjectwonAA3FEBF2A14147FAA0FE5AA479D8890F")) {
        vars.setSessionValue("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.strprojectwon", vars.getStringParameter("inpprojectwon"));
        vars.setSessionValue("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "AA3FEBF2A14147FAA0FE5AA479D8890F", request.getServletPath());
      } else if (vars.commandIn("BUTTONAA3FEBF2A14147FAA0FE5AA479D8890F")) {
        String strC_Projectproposal_ID = vars.getGlobalVariable("inpcProjectproposalId", windowId + "|C_Projectproposal_ID", "");
        String strprojectwon = vars.getSessionValue("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.strprojectwon");
        String strProcessing = vars.getSessionValue("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.strProcessing");
        String strOrg = vars.getSessionValue("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.strOrg");
        String strClient = vars.getSessionValue("buttonAA3FEBF2A14147FAA0FE5AA479D8890F.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonProjectwonAA3FEBF2A14147FAA0FE5AA479D8890F(response, vars, strC_Projectproposal_ID, strprojectwon, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONProjectwonAA3FEBF2A14147FAA0FE5AA479D8890F")) {
        String strC_Projectproposal_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Projectproposal_ID", "");
        
        ProcessBundle pb = new ProcessBundle("AA3FEBF2A14147FAA0FE5AA479D8890F", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Projectproposal_ID", strC_Projectproposal_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strdateordered = vars.getStringParameter("inpdateordered");
params.put("dateordered", strdateordered);

        
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



    void printPageButtonProjectwonAA3FEBF2A14147FAA0FE5AA479D8890F(HttpServletResponse response, VariablesSecureApp vars, String strC_Projectproposal_ID, String strprojectwon, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process AA3FEBF2A14147FAA0FE5AA479D8890F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ProjectwonAA3FEBF2A14147FAA0FE5AA479D8890F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Projectproposal_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "ProposalD9B758A7DB624AEAABDD6783353309D1_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "AA3FEBF2A14147FAA0FE5AA479D8890F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("AA3FEBF2A14147FAA0FE5AA479D8890F");
        vars.removeMessage("AA3FEBF2A14147FAA0FE5AA479D8890F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("DateOrdered", DateTimeData.today(this));
    xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
