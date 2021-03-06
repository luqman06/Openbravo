
package org.openbravo.erpWindows.GoodsReceipt;


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
public class Header extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  
  private static final String windowId = "184";
  private static final String tabId = "296";
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
     
      if (command.contains("F5C44E6E034B42FF8865AC0417C799FE")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("F5C44E6E034B42FF8865AC0417C799FE");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     
      if (command.contains("154")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("154");
        SessionInfo.setModuleId("0");
        if (securedProcess || explicitAccess.contains("154")) {
          classInfo.type = "P";
          classInfo.id = "154";
        }
      }
     

     
      if (explicitAccess.contains("F5C44E6E034B42FF8865AC0417C799FE") || (securedProcess && command.contains("F5C44E6E034B42FF8865AC0417C799FE"))) {
        classInfo.type = "P";
        classInfo.id = "F5C44E6E034B42FF8865AC0417C799FE";
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

     } else if (vars.commandIn("BUTTONGenerateTo154")) {
        vars.setSessionValue("button154.strgenerateto", vars.getStringParameter("inpgenerateto"));
        vars.setSessionValue("button154.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button154.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button154.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("isSOTrx", vars.getStringParameter("inpissotrx"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button154.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "154", request.getServletPath());    
     } else if (vars.commandIn("BUTTON154")) {
        String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
        String strgenerateto = vars.getSessionValue("button154.strgenerateto");
        String strProcessing = vars.getSessionValue("button154.strProcessing");
        String strOrg = vars.getSessionValue("button154.strOrg");
        String strClient = vars.getSessionValue("button154.strClient");
        
        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonGenerateTo154(response, vars, strM_InOut_ID, strgenerateto, strProcessing);
        }

    } else if (vars.commandIn("BUTTONem_oez_reimburseF5C44E6E034B42FF8865AC0417C799FE")) {
        vars.setSessionValue("buttonF5C44E6E034B42FF8865AC0417C799FE.stremOezReimburse", vars.getStringParameter("inpemOezReimburse"));
        vars.setSessionValue("buttonF5C44E6E034B42FF8865AC0417C799FE.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonF5C44E6E034B42FF8865AC0417C799FE.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonF5C44E6E034B42FF8865AC0417C799FE.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("AD_Org_ID", vars.getStringParameter("inpadOrgId"));
p.put("AD_Org_ID", vars.getStringParameter("inpadOrgId"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "F5C44E6E034B42FF8865AC0417C799FE", request.getServletPath());
      } else if (vars.commandIn("BUTTONF5C44E6E034B42FF8865AC0417C799FE")) {
        String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
        String stremOezReimburse = vars.getSessionValue("buttonF5C44E6E034B42FF8865AC0417C799FE.stremOezReimburse");
        String strProcessing = vars.getSessionValue("buttonF5C44E6E034B42FF8865AC0417C799FE.strProcessing");
        String strOrg = vars.getSessionValue("buttonF5C44E6E034B42FF8865AC0417C799FE.strOrg");
        String strClient = vars.getSessionValue("buttonF5C44E6E034B42FF8865AC0417C799FE.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonem_oez_reimburseF5C44E6E034B42FF8865AC0417C799FE(response, vars, strM_InOut_ID, stremOezReimburse, strProcessing);
        }

    } else if (vars.commandIn("SAVE_BUTTONGenerateTo154")) {
        String strM_InOut_ID = vars.getGlobalVariable("inpKey", windowId + "|M_InOut_ID", "");
        String strgenerateto = vars.getStringParameter("inpgenerateto");
        String strProcessing = vars.getStringParameter("inpprocessing");
        OBError myMessage = null;
        try {
          String pinstance = SequenceIdData.getUUID();
          PInstanceProcessData.insertPInstance(this, pinstance, "154", (("M_InOut_ID".equalsIgnoreCase("AD_Language"))?"0":strM_InOut_ID), strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
          String strmPricelistVersionId = vars.getStringParameter("inpmPricelistVersionId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "M_PriceList_Version_ID", strmPricelistVersionId, vars.getClient(), vars.getOrg(), vars.getUser());

          
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

    } else if (vars.commandIn("SAVE_BUTTONem_oez_reimburseF5C44E6E034B42FF8865AC0417C799FE")) {
        String strM_InOut_ID = vars.getGlobalVariable("inpKey", windowId + "|M_InOut_ID", "");
        
        ProcessBundle pb = new ProcessBundle("F5C44E6E034B42FF8865AC0417C799FE", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("M_InOut_ID", strM_InOut_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
params.put("cBpartnerId", strcBpartnerId);
String strmPricelistId = vars.getStringParameter("inpmPricelistId");
params.put("mPricelistId", strmPricelistId);
String strcDoctypeinvoiceId = vars.getStringParameter("inpcDoctypeinvoiceId");
params.put("cDoctypeinvoiceId", strcDoctypeinvoiceId);
String strcDoctypeshipmentId = vars.getStringParameter("inpcDoctypeshipmentId");
params.put("cDoctypeshipmentId", strcDoctypeshipmentId);
String strdateordered = vars.getStringParameter("inpdateordered");
params.put("dateordered", strdateordered);
String strmWarehouseId = vars.getStringParameter("inpmWarehouseId");
params.put("mWarehouseId", strmWarehouseId);
String strcPaymenttermId = vars.getStringParameter("inpcPaymenttermId");
params.put("cPaymenttermId", strcPaymenttermId);
String strfinPaymentmethodId = vars.getStringParameter("inpfinPaymentmethodId");
params.put("finPaymentmethodId", strfinPaymentmethodId);

        
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

    } else if (vars.commandIn("BUTTONCreateFrom")) {
        String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
        String strTableId = "319";
        String strProcessId = "";
        String strDateInvoiced = vars.getStringParameter("inpdateinvoiced", "");
        String strBPartnerLocation = vars.getStringParameter("inpcBpartnerLocationId", "");
        String strPriceList = vars.getStringParameter("inpmPricelistId", "");
        String strBPartner = vars.getStringParameter("inpcBpartnerId", "");
        String strBankAccount = vars.getStringParameter("inpcBankaccountId");
        String strStatementDate = vars.getStringParameter("inpstatementdate");
        String strOrg = vars.getStringParameter("inpadOrgId");
        String strClient = vars.getStringParameter("inpadClientId");
        String strIsreceipt = vars.getStringParameter("inpisreceipt");
        log4j.debug("Loading CreateFrom button in table: " + strTableId);
        vars.setSessionValue("CreateFrom|key", strM_InOut_ID);
        vars.setSessionValue("CreateFrom|tableId", strTableId);
        vars.setSessionValue("CreateFrom|tabId", tabId);
        vars.setSessionValue("CreateFrom|processId", strProcessId);
        vars.setSessionValue("CreateFrom|path", strDireccion + request.getServletPath());
        vars.setSessionValue("CreateFrom|bpartnerLocation", strBPartnerLocation);
        vars.setSessionValue("CreateFrom|dateInvoiced", strDateInvoiced);
        vars.setSessionValue("CreateFrom|pricelist", strPriceList);
        vars.setSessionValue("CreateFrom|bpartner", strBPartner);
        vars.setSessionValue("CreateFrom|windowId", windowId);
        vars.setSessionValue("CreateFrom|bankAccount", strBankAccount);
        vars.setSessionValue("CreateFrom|statementDate", strStatementDate);
        vars.setSessionValue("CreateFrom|adOrgId", strOrg);
        vars.setSessionValue("CreateFrom|isreceipt", strIsreceipt);
        vars.setSessionValue("CreateFrom|tabName", "Header");
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          response.sendRedirect(strDireccion + "/ad_actionButton/CreateFrom.html");
        }

    } else if (vars.commandIn("BUTTONPosted")) {
        String strM_InOut_ID = vars.getGlobalVariable("inpmInoutId", windowId + "|M_InOut_ID", "");
        String strTableId = "319";
        String strPosted = vars.getStringParameter("inpposted");
        String strProcessId = "";
        log4j.debug("Loading Posted button in table: " + strTableId);
        String strOrg = vars.getStringParameter("inpadOrgId");
        String strClient = vars.getStringParameter("inpadClientId");
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{
          vars.setSessionValue("Posted|key", strM_InOut_ID);
          vars.setSessionValue("Posted|tableId", strTableId);
          vars.setSessionValue("Posted|tabId", tabId);
          vars.setSessionValue("Posted|posted", strPosted);
          vars.setSessionValue("Posted|processId", strProcessId);
          vars.setSessionValue("Posted|path", strDireccion + request.getServletPath());
          vars.setSessionValue("Posted|windowId", windowId);
          vars.setSessionValue("Posted|tabName", "Header");
          response.sendRedirect(strDireccion + "/ad_actionButton/Posted.html");
        }

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

    private void printPageButtonGenerateTo154(HttpServletResponse response, VariablesSecureApp vars, String strM_InOut_ID, String strgenerateto, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 154");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/GenerateTo154", discard).createXmlDocument();
      xmlDocument.setParameter("key", strM_InOut_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "154");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("154");
        vars.removeMessage("154");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_PriceList_Version_ID", HeaderData.selectActP154_M_PriceList_Version_ID(this, "N", Utility.getContext(this, vars, "C_BPARTNER_ID", "184")));
    comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_Version_ID", "", "26D8602C48004E1182B46310DF7015AE", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button154.originalParams"), comboTableData, windowId, HeaderData.selectActP154_M_PriceList_Version_ID(this, "N", Utility.getContext(this, vars, "C_BPARTNER_ID", "184")));
    xmlDocument.setData("reportM_PriceList_Version_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }


    void printPageButtonem_oez_reimburseF5C44E6E034B42FF8865AC0417C799FE(HttpServletResponse response, VariablesSecureApp vars, String strM_InOut_ID, String stremOezReimburse, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F5C44E6E034B42FF8865AC0417C799FE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/em_oez_reimburseF5C44E6E034B42FF8865AC0417C799FE", discard).createXmlDocument();
      xmlDocument.setParameter("key", strM_InOut_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "F5C44E6E034B42FF8865AC0417C799FE");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("F5C44E6E034B42FF8865AC0417C799FE");
        vars.removeMessage("F5C44E6E034B42FF8865AC0417C799FE");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Org_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_Id", "", "49DC1D6F086945AB82F84C66F5F13F16", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Org_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Bpartner_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Bpartner_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_Bpartner_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("M_PriceList_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportM_PriceList_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_DocTypeInvoice_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_DocTypeInvoice_ID", "170", "8AC5A5643A6740A0A17457C00ED0DF41", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_DocTypeInvoice_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_DocTypeShipment_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_DocTypeShipment_ID", "170", "A0171DD95AE84C42A83AFE2228EF8310", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_DocTypeShipment_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DateOrdered", "");
    xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("M_Warehouse_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportM_Warehouse_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_PaymentTerm_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_PaymentTerm_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("FIN_PaymentMethod_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "FIN_PaymentMethod_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonF5C44E6E034B42FF8865AC0417C799FE.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportFIN_PaymentMethod_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
