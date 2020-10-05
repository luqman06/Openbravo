
package org.openbravo.erpWindows.SalesOrder;




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
  
  private static final String windowId = "143";
  private static final String tabId = "186";
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
     
      if (command.contains("23D1B163EC0B41F790CE39BF01DA320E")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("23D1B163EC0B41F790CE39BF01DA320E");
        SessionInfo.setModuleId("0");
      }
     
      if (command.contains("3270DC8DF5C143F280771F1200F2A11C")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("3270DC8DF5C143F280771F1200F2A11C");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
      }
     
      if (command.contains("C4AEFC3C35174E5EB01DAF7187F3CCD0")) {
        SessionInfo.setProcessType("P");
        SessionInfo.setProcessId("C4AEFC3C35174E5EB01DAF7187F3CCD0");
        SessionInfo.setModuleId("27445C8270364452BEB29669FBB21CED");
      }
     
      try {
        securedProcess = "Y".equals(org.openbravo.erpCommon.businessUtility.Preferences
            .getPreferenceValue("SecuredProcess", true, vars.getClient(), vars.getOrg(), vars
                .getUser(), vars.getRole(), windowId));
      } catch (PropertyException e) {
      }
     

     
      if (explicitAccess.contains("23D1B163EC0B41F790CE39BF01DA320E") || (securedProcess && command.contains("23D1B163EC0B41F790CE39BF01DA320E"))) {
        classInfo.type = "P";
        classInfo.id = "23D1B163EC0B41F790CE39BF01DA320E";
      }
     
      if (explicitAccess.contains("3270DC8DF5C143F280771F1200F2A11C") || (securedProcess && command.contains("3270DC8DF5C143F280771F1200F2A11C"))) {
        classInfo.type = "P";
        classInfo.id = "3270DC8DF5C143F280771F1200F2A11C";
      }
     
      if (explicitAccess.contains("C4AEFC3C35174E5EB01DAF7187F3CCD0") || (securedProcess && command.contains("C4AEFC3C35174E5EB01DAF7187F3CCD0"))) {
        classInfo.type = "P";
        classInfo.id = "C4AEFC3C35174E5EB01DAF7187F3CCD0";
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

    } else if (vars.commandIn("BUTTONRM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E")) {
        vars.setSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strrmAddorphanline", vars.getStringParameter("inprmAddorphanline"));
        vars.setSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("IsSOTrx", vars.getStringParameter("inpissotrx"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button23D1B163EC0B41F790CE39BF01DA320E.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "23D1B163EC0B41F790CE39BF01DA320E", request.getServletPath());
      } else if (vars.commandIn("BUTTON23D1B163EC0B41F790CE39BF01DA320E")) {
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String strrmAddorphanline = vars.getSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strrmAddorphanline");
        String strProcessing = vars.getSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strProcessing");
        String strOrg = vars.getSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strOrg");
        String strClient = vars.getSessionValue("button23D1B163EC0B41F790CE39BF01DA320E.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonRM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E(response, vars, strC_Order_ID, strrmAddorphanline, strProcessing);
        }
    } else if (vars.commandIn("BUTTONConvertquotation3270DC8DF5C143F280771F1200F2A11C")) {
        vars.setSessionValue("button3270DC8DF5C143F280771F1200F2A11C.strconvertquotation", vars.getStringParameter("inpconvertquotation"));
        vars.setSessionValue("button3270DC8DF5C143F280771F1200F2A11C.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("button3270DC8DF5C143F280771F1200F2A11C.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("button3270DC8DF5C143F280771F1200F2A11C.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        
        
        //Save in session needed params for combos if needed
        vars.setSessionObject("button3270DC8DF5C143F280771F1200F2A11C.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "3270DC8DF5C143F280771F1200F2A11C", request.getServletPath());
      } else if (vars.commandIn("BUTTON3270DC8DF5C143F280771F1200F2A11C")) {
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String strconvertquotation = vars.getSessionValue("button3270DC8DF5C143F280771F1200F2A11C.strconvertquotation");
        String strProcessing = vars.getSessionValue("button3270DC8DF5C143F280771F1200F2A11C.strProcessing");
        String strOrg = vars.getSessionValue("button3270DC8DF5C143F280771F1200F2A11C.strOrg");
        String strClient = vars.getSessionValue("button3270DC8DF5C143F280771F1200F2A11C.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonConvertquotation3270DC8DF5C143F280771F1200F2A11C(response, vars, strC_Order_ID, strconvertquotation, strProcessing);
        }
    } else if (vars.commandIn("BUTTONEM_Oez_Create_PurchaseorderC4AEFC3C35174E5EB01DAF7187F3CCD0")) {
        vars.setSessionValue("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.stremOezCreatePurchaseorder", vars.getStringParameter("inpemOezCreatePurchaseorder"));
        vars.setSessionValue("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.strProcessing", vars.getStringParameter("inpprocessing", "Y"));
        vars.setSessionValue("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.strOrg", vars.getStringParameter("inpadOrgId"));
        vars.setSessionValue("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.strClient", vars.getStringParameter("inpadClientId"));
        
        
        HashMap<String, String> p = new HashMap<String, String>();
        p.put("AD_Client_ID", vars.getStringParameter("inpadClientId"));

        
        //Save in session needed params for combos if needed
        vars.setSessionObject("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.originalParams", FieldProviderFactory.getFieldProvider(p));
        printPageButtonFS(response, vars, "C4AEFC3C35174E5EB01DAF7187F3CCD0", request.getServletPath());
      } else if (vars.commandIn("BUTTONC4AEFC3C35174E5EB01DAF7187F3CCD0")) {
        String strC_Order_ID = vars.getGlobalVariable("inpcOrderId", windowId + "|C_Order_ID", "");
        String stremOezCreatePurchaseorder = vars.getSessionValue("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.stremOezCreatePurchaseorder");
        String strProcessing = vars.getSessionValue("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.strProcessing");
        String strOrg = vars.getSessionValue("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.strOrg");
        String strClient = vars.getSessionValue("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.strClient");

        
        if ((org.openbravo.erpCommon.utility.WindowAccessData.hasReadOnlyAccess(this, vars.getRole(), tabId)) || !(Utility.isElementInList(Utility.getContext(this, vars, "#User_Client", windowId, accesslevel),strClient)  && Utility.isElementInList(Utility.getContext(this, vars, "#User_Org", windowId, accesslevel),strOrg))){
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), Utility.messageBD(this, "NoWriteAccess", vars.getLanguage()));
          vars.setMessage(tabId, myError);
          printPageClosePopUp(response, vars);
        }else{       
          printPageButtonEM_Oez_Create_PurchaseorderC4AEFC3C35174E5EB01DAF7187F3CCD0(response, vars, strC_Order_ID, stremOezCreatePurchaseorder, strProcessing);
        }


    } else if (vars.commandIn("SAVE_BUTTONRM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        
        ProcessBundle pb = new ProcessBundle("23D1B163EC0B41F790CE39BF01DA320E", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Order_ID", strC_Order_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strmProductId = vars.getStringParameter("inpmProductId");
params.put("mProductId", strmProductId);
String strmAttributesetinstanceId = vars.getStringParameter("inpmAttributesetinstanceId");
params.put("mAttributesetinstanceId", strmAttributesetinstanceId);
String strreturned = vars.getNumericParameter("inpreturned");
params.put("returned", strreturned);
String strpricestd = vars.getNumericParameter("inppricestd");
params.put("pricestd", strpricestd);
String strcTaxId = vars.getStringParameter("inpcTaxId");
params.put("cTaxId", strcTaxId);
String strcReturnReasonId = vars.getStringParameter("inpcReturnReasonId");
params.put("cReturnReasonId", strcReturnReasonId);

        
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
    } else if (vars.commandIn("SAVE_BUTTONConvertquotation3270DC8DF5C143F280771F1200F2A11C")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        
        ProcessBundle pb = new ProcessBundle("3270DC8DF5C143F280771F1200F2A11C", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Order_ID", strC_Order_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String strrecalculateprices = vars.getStringParameter("inprecalculateprices", "N");
params.put("recalculateprices", strrecalculateprices);

        
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
    } else if (vars.commandIn("SAVE_BUTTONEM_Oez_Create_PurchaseorderC4AEFC3C35174E5EB01DAF7187F3CCD0")) {
        String strC_Order_ID = vars.getGlobalVariable("inpKey", windowId + "|C_Order_ID", "");
        
        ProcessBundle pb = new ProcessBundle("C4AEFC3C35174E5EB01DAF7187F3CCD0", vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        params.put("C_Order_ID", strC_Order_ID);
        params.put("adOrgId", vars.getStringParameter("inpadOrgId"));
        params.put("adClientId", vars.getStringParameter("inpadClientId"));
        params.put("tabId", tabId);
        
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
params.put("cBpartnerId", strcBpartnerId);
String strmPricelistId = vars.getStringParameter("inpmPricelistId");
params.put("mPricelistId", strmPricelistId);
String strcDoctypeId = vars.getStringParameter("inpcDoctypeId");
params.put("cDoctypeId", strcDoctypeId);
String strdateordered = vars.getStringParameter("inpdateordered");
params.put("dateordered", strdateordered);
String strdatepromised = vars.getStringParameter("inpdatepromised");
params.put("datepromised", strdatepromised);
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



    void printPageButtonRM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String strrmAddorphanline, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 23D1B163EC0B41F790CE39BF01DA320E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/RM_AddOrphanLine23D1B163EC0B41F790CE39BF01DA320E", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "23D1B163EC0B41F790CE39BF01DA320E");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("23D1B163EC0B41F790CE39BF01DA320E");
        vars.removeMessage("23D1B163EC0B41F790CE39BF01DA320E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_Product_ID", "");
    xmlDocument.setParameter("M_AttributeSetInstance_ID", "");
    xmlDocument.setParameter("M_AttributeSetInstance_IDR", HeaderData.selectActDefM_AttributeSetInstance_ID(this, ""));
    xmlDocument.setParameter("Returned", "");
    xmlDocument.setParameter("PriceStd", "");
    xmlDocument.setParameter("C_Tax_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Tax_ID", "", "299FA667CF374AC5ACC74739C3251134", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button23D1B163EC0B41F790CE39BF01DA320E.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_Tax_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Return_Reason_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Return_Reason_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("button23D1B163EC0B41F790CE39BF01DA320E.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_Return_Reason_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonConvertquotation3270DC8DF5C143F280771F1200F2A11C(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String strconvertquotation, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3270DC8DF5C143F280771F1200F2A11C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/Convertquotation3270DC8DF5C143F280771F1200F2A11C", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "3270DC8DF5C143F280771F1200F2A11C");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("3270DC8DF5C143F280771F1200F2A11C");
        vars.removeMessage("3270DC8DF5C143F280771F1200F2A11C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("RecalculatePrices", "Y");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonEM_Oez_Create_PurchaseorderC4AEFC3C35174E5EB01DAF7187F3CCD0(HttpServletResponse response, VariablesSecureApp vars, String strC_Order_ID, String stremOezCreatePurchaseorder, String strProcessing)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C4AEFC3C35174E5EB01DAF7187F3CCD0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/EM_Oez_Create_PurchaseorderC4AEFC3C35174E5EB01DAF7187F3CCD0", discard).createXmlDocument();
      xmlDocument.setParameter("key", strC_Order_ID);
      xmlDocument.setParameter("processing", strProcessing);
      xmlDocument.setParameter("form", "Header_Edition.html");
      xmlDocument.setParameter("window", windowId);
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("processId", "C4AEFC3C35174E5EB01DAF7187F3CCD0");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      
      {
        OBError myMessage = vars.getMessage("C4AEFC3C35174E5EB01DAF7187F3CCD0");
        vars.removeMessage("C4AEFC3C35174E5EB01DAF7187F3CCD0");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Org_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Org_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Bpartner_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Bpartner_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_Bpartner_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("M_Pricelist_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Pricelist_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportM_Pricelist_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_DocType_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_DocType_Id", "", "8E0486B4548F4A0290AEA692B707F744", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_DocType_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DateOrdered", "");
    xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DatePromised", "");
    xmlDocument.setParameter("DatePromised_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("M_Warehouse_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportM_Warehouse_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_PaymentTerm_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportC_PaymentTerm_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("FIN_PaymentMethod_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "FIN_PaymentMethod_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, (FieldProvider) vars.getSessionObject("buttonC4AEFC3C35174E5EB01DAF7187F3CCD0.originalParams"), comboTableData, windowId, "");
    xmlDocument.setData("reportFIN_PaymentMethod_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      
      out.println(xmlDocument.print());
      out.close();
    }

}
