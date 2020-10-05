
package org.openbravo.erpWindows.Requisition;


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
public class Header extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "800092";
  private static final String tabId = "800249";
  private static final int accesslevel = 1;
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
     
      if (command.contains("4A4DB835730245B29CB86D2FF2A48BC2")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("4A4DB835730245B29CB86D2FF2A48BC2");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("0D92C358A17F4063AB3C71827CA42D1F")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("0D92C358A17F4063AB3C71827CA42D1F");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
        if (securedProcess || explicitAccess.contains("0D92C358A17F4063AB3C71827CA42D1F")) {
          classInfo.type = "P";
          classInfo.id = "0D92C358A17F4063AB3C71827CA42D1F";
        }
      }
     

     
      if (explicitAccess.contains("4A4DB835730245B29CB86D2FF2A48BC2") || (securedProcess && command.contains("4A4DB835730245B29CB86D2FF2A48BC2"))) {
        classInfo.type = "P";
        classInfo.id = "4A4DB835730245B29CB86D2FF2A48BC2";
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

     } else if (vars.commandIn("BUTTONEM_Oez_Btn_Clone0D92C358A17F4063AB3C71827CA42D1F")) {
        vars.setSessionValue("button0D92C358A17F4063AB3C71827CA42D1F.stremOezBtnClone", vars.getStringParameter("inpemOezBtnClone"));
        vars.setSessionValue("button0D92C358A17F4063AB3C71827CA42D1F.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button0D92C358A17F4063AB3C71827CA42D1F.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button0D92C358A17F4063AB3C71827CA42D1F.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button0D92C358A17F4063AB3C71827CA42D1F.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "0D92C358A17F4063AB3C71827CA42D1F", request.getServletPath());    
     } else if (vars.commandIn("BUTTON0D92C358A17F4063AB3C71827CA42D1F")) {
        String strM_Requisition_ID = vars.getGlobalVariable("inpmRequisitionId", windowId + "|M_Requisition_ID", "");
        String stremOezBtnClone = vars.getSessionValue("button0D92C358A17F4063AB3C71827CA42D1F.stremOezBtnClone");
        String strProcessing = vars.getSessionValue("button0D92C358A17F4063AB3C71827CA42D1F.strProcessing");
        String strOrg = vars.getSessionValue("button0D92C358A17F4063AB3C71827CA42D1F.strOrg");
        String strClient = vars.getSessionValue("button0D92C358A17F4063AB3C71827CA42D1F.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Oez_Btn_Clone0D92C358A17F4063AB3C71827CA42D1F(response, vars, strM_Requisition_ID, stremOezBtnClone, strProcessing);
        }

    } else if (vars.commandIn("BUTTONEM_Oez_Copyfromso4A4DB835730245B29CB86D2FF2A48BC2")) {
        vars.setSessionValue("button4A4DB835730245B29CB86D2FF2A48BC2.stremOezCopyfromso", vars.getStringParameter("inpemOezCopyfromso"));
        vars.setSessionValue("button4A4DB835730245B29CB86D2FF2A48BC2.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button4A4DB835730245B29CB86D2FF2A48BC2.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button4A4DB835730245B29CB86D2FF2A48BC2.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button4A4DB835730245B29CB86D2FF2A48BC2.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "4A4DB835730245B29CB86D2FF2A48BC2", request.getServletPath());
      } else if (vars.commandIn("BUTTON4A4DB835730245B29CB86D2FF2A48BC2")) {
        String strM_Requisition_ID = vars.getGlobalVariable("inpmRequisitionId", windowId + "|M_Requisition_ID", "");
        String stremOezCopyfromso = vars.getSessionValue("button4A4DB835730245B29CB86D2FF2A48BC2.stremOezCopyfromso");
        String strProcessing = vars.getSessionValue("button4A4DB835730245B29CB86D2FF2A48BC2.strProcessing");
        String strOrg = vars.getSessionValue("button4A4DB835730245B29CB86D2FF2A48BC2.strOrg");
        String strClient = vars.getSessionValue("button4A4DB835730245B29CB86D2FF2A48BC2.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Oez_Copyfromso4A4DB835730245B29CB86D2FF2A48BC2(response, vars, strM_Requisition_ID, stremOezCopyfromso, strProcessing);
        }

    } else if (vars.commandIn("SAVE_BUTTONEM_Oez_Btn_Clone0D92C358A17F4063AB3C71827CA42D1F")) {
        String strM_Requisition_ID = vars.getGlobalVariable("inpKey", windowId + "|M_Requisition_ID", "");
        String stremOezBtnClone = vars.getStringParameter("inpemOezBtnClone");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "0D92C358A17F4063AB3C71827CA42D1F", (("M_Requisition_ID".equalsIgnoreCase("AD_Language"))?"0":strM_Requisition_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          
          
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

    } else if (vars.commandIn("SAVE_BUTTONEM_Oez_Copyfromso4A4DB835730245B29CB86D2FF2A48BC2")) {
        String strM_Requisition_ID = vars.getGlobalVariable("inpKey", windowId + "|M_Requisition_ID", "");
        
        ProcessBundle pb = new ProcessBundle("4A4DB835730245B29CB86D2FF2A48BC2", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("M_Requisition_ID", strM_Requisition_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strcOrderId = vars.getStringParameter("inpcOrderId");
params.put("cOrderId", strcOrderId);

        
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

    private void printPageButtonEM_Oez_Btn_Clone0D92C358A17F4063AB3C71827CA42D1F(HttpServletResponse response, VariablesSecureApp vars, String strM_Requisition_ID, String stremOezBtnClone, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0D92C358A17F4063AB3C71827CA42D1F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Oez_Btn_Clone0D92C358A17F4063AB3C71827CA42D1F", discard).createXmlDocument();
      xmlDocument.setParameter("key", strM_Requisition_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "0D92C358A17F4063AB3C71827CA42D1F");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("0D92C358A17F4063AB3C71827CA42D1F");
        vars.removeMessage("0D92C358A17F4063AB3C71827CA42D1F");
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


    void printPageButtonEM_Oez_Copyfromso4A4DB835730245B29CB86D2FF2A48BC2(HttpServletResponse response, VariablesSecureApp vars, String strM_Requisition_ID, String stremOezCopyfromso, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4A4DB835730245B29CB86D2FF2A48BC2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Oez_Copyfromso4A4DB835730245B29CB86D2FF2A48BC2", discard).createXmlDocument();
      xmlDocument.setParameter("key", strM_Requisition_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "4A4DB835730245B29CB86D2FF2A48BC2");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("4A4DB835730245B29CB86D2FF2A48BC2");
        vars.removeMessage("4A4DB835730245B29CB86D2FF2A48BC2");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("c_order_id", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
