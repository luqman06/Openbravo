
package org.openbravo.erpCommon.ad_actionButton;


import org.openbravo.erpCommon.utility.*;
import org.openbravo.erpCommon.reference.*;
import org.openbravo.utils.Replace;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessRunner;
import org.openbravo.xmlEngine.XmlDocument;
import org.openbravo.database.SessionInfo;
import org.openbravo.erpCommon.obps.ActivationKey;
import org.openbravo.erpCommon.obps.ActivationKey.FeatureRestriction;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Process;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("unused")
public class ActionButton_Responser extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  protected static final String windowId = "ActionButtonResponser";
  
  public void init (ServletConfig config) {
    super.init(config);
    boolHist = false;
  }
  
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String strProcessId = getProcessId(vars);

    // set process type and id for audit
    SessionInfo.setProcessType("P");
    SessionInfo.setProcessId(strProcessId);
    SessionInfo.setUserId(vars.getSessionValue("#AD_User_ID"));
    SessionInfo.setSessionId(vars.getSessionValue("#AD_Session_ID"));
    SessionInfo.setQueryProfile("manualProcess");

    try {
      OBContext.setAdminMode();
      Process process = OBDal.getInstance().get(Process.class, strProcessId);
      if (process != null) {
        SessionInfo.setModuleId(process.getModule().getId());
      }
    } finally {
      OBContext.restorePreviousMode();
    }
    super.service(request, response);
  }

  private String getProcessId(VariablesSecureApp vars) throws ServletException {
    String command = vars.getCommand();
    if (command.equals("DEFAULT")) {
      return vars.getRequiredStringParameter("inpadProcessId");
    } else if (command.startsWith("BUTTON")) {
      return command.substring("BUTTON".length());
    } else if (command.startsWith("FRAMES")) {
      return command.substring("FRAMES".length());
    } else if (command.startsWith("SAVE_BUTTONActionButton")) {
      return command.substring("SAVE_BUTTONActionButton".length());
    }
    return null;
  }

  public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String strProcessId = getProcessId(vars);

    if (vars.getCommand().startsWith("FRAMES")) {
      printPageFrames(response, vars, strProcessId);
    }
    
    if (!vars.commandIn("DEFAULT")) {
      //Check access
      FeatureRestriction featureRestriction = ActivationKey.getInstance().hasLicenseAccess("P",
          strProcessId);
      if (featureRestriction != FeatureRestriction.NO_RESTRICTION) {
        licenseError("P", strProcessId, featureRestriction, response, request, vars, true);
      }
      if (!hasGeneralAccess(vars, "P", strProcessId)) {
        bdErrorGeneralPopUp(request, response,
            Utility.messageBD(this, "Error", vars.getLanguage()), Utility.messageBD(this,
                "AccessTableNoView", vars.getLanguage()));
      }
    }
    
      
    if (vars.commandIn("DEFAULT")) {
      printPageDefault(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON172")) {
        
        printPageButton172(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800087")) {
        
        printPageButton800087(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800075")) {
        
        printPageButton800075(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON119")) {
        
        printPageButton119(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON193")) {
        
        printPageButton193(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON185")) {
        
        printPageButton185(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON175")) {
        
        printPageButton175(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800130")) {
        
        printPageButton800130(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800151")) {
        
        printPageButton800151(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800109")) {
        
        printPageButton800109(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON800085")) {
        
        printPageButton800085(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6445135367544A329CFEC7506B7475FD")) {
        
        printPageButton6445135367544A329CFEC7506B7475FD(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND234AE084F7040DCB66E281A4237FF99")) {
        
        printPageButtonD234AE084F7040DCB66E281A4237FF99(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7A7374B370DB4A23A6576EE0E1ED0388")) {
        
        printPageButton7A7374B370DB4A23A6576EE0E1ED0388(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON74C7F572A6B54BFEB85EA6830B9E826A")) {
        
        printPageButton74C7F572A6B54BFEB85EA6830B9E826A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONBCD057684E0A46498C90229A55C1D301")) {
        
        printPageButtonBCD057684E0A46498C90229A55C1D301(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2CFFB51D2FB04D85B18AAD4C1741FED3")) {
        
        printPageButton2CFFB51D2FB04D85B18AAD4C1741FED3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON21178259304B46DF9EB234CCC094623E")) {
        
        printPageButton21178259304B46DF9EB234CCC094623E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONAE7FE5ABB6084E0ABAC9A557542F09D8")) {
        
        printPageButtonAE7FE5ABB6084E0ABAC9A557542F09D8(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2DD838352350470DAB65A5CF015CC166")) {
        
        printPageButton2DD838352350470DAB65A5CF015CC166(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONDCB42D1AA1C546A6811C46BE10686CF0")) {
        
        printPageButtonDCB42D1AA1C546A6811C46BE10686CF0(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA9680AB37C8B48C0A0711E7AE8C41770")) {
        
        printPageButtonA9680AB37C8B48C0A0711E7AE8C41770(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON25D06F40232C4463A2AC83287D9F0B02")) {
        
        printPageButton25D06F40232C4463A2AC83287D9F0B02(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON525D95D3B9A34B7CA49384A2FF775818")) {
        
        printPageButton525D95D3B9A34B7CA49384A2FF775818(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONAA4664E339E94FFDAF2448EB1562234C")) {
        
        printPageButtonAA4664E339E94FFDAF2448EB1562234C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB504959087344D36A84F97CAF6FE10C4")) {
        
        printPageButtonB504959087344D36A84F97CAF6FE10C4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON303A9E52AE9046EDAAC23DD6D91828B8")) {
        
        printPageButton303A9E52AE9046EDAAC23DD6D91828B8(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON29954ADED42947329B05E01379B0C8A2")) {
        
        printPageButton29954ADED42947329B05E01379B0C8A2(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON107764141FA94DC8BB87C35E7BDAEBE0")) {
        
        printPageButton107764141FA94DC8BB87C35E7BDAEBE0(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON79C2622162B6439E96A15E366ACF326C")) {
        
        printPageButton79C2622162B6439E96A15E366ACF326C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONAD870F99388C404487034D642E315D97")) {
        
        printPageButtonAD870F99388C404487034D642E315D97(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE222FF23CCB7480E9D354B33517E8C6B")) {
        
        printPageButtonE222FF23CCB7480E9D354B33517E8C6B(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4A81E29CC06E4D169A2E37F9053250F3")) {
        
        printPageButton4A81E29CC06E4D169A2E37F9053250F3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON151AE91995A24463A7861B81CB403F43")) {
        
        printPageButton151AE91995A24463A7861B81CB403F43(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON5059DC89F5C34BAA99A967E39E606562")) {
        
        printPageButton5059DC89F5C34BAA99A967E39E606562(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON14E82A449A504DA09627CF13191D7BB4")) {
        
        printPageButton14E82A449A504DA09627CF13191D7BB4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8F5EF0FCF01C41D2AA51E16CFD697D6E")) {
        
        printPageButton8F5EF0FCF01C41D2AA51E16CFD697D6E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6327905FF65D4D42A3A667B8ADE5380D")) {
        
        printPageButton6327905FF65D4D42A3A667B8ADE5380D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND7088D427D6A462FBD2DD03C58C4F299")) {
        
        printPageButtonD7088D427D6A462FBD2DD03C58C4F299(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6D8D3AFBFD4F43669DCBB78054D15E4A")) {
        
        printPageButton6D8D3AFBFD4F43669DCBB78054D15E4A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON77B4C27E61674B9595DE2ADA83B99925")) {
        
        printPageButton77B4C27E61674B9595DE2ADA83B99925(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0C32C66672F1458598940A308E75DBF4")) {
        
        printPageButton0C32C66672F1458598940A308E75DBF4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFE3C92FD61224B66B872C7150EA5BB37")) {
        
        printPageButtonFE3C92FD61224B66B872C7150EA5BB37(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3800D291C19F4336B0EF5094667022D1")) {
        
        printPageButton3800D291C19F4336B0EF5094667022D1(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB10F1B7314DD45B8A4F3015E54F4E428")) {
        
        printPageButtonB10F1B7314DD45B8A4F3015E54F4E428(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8722C6EB947C4597AE913430DD84C9C3")) {
        
        printPageButton8722C6EB947C4597AE913430DD84C9C3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFFB36346586F49FF8E590DD00A037318")) {
        
        printPageButtonFFB36346586F49FF8E590DD00A037318(response, vars, strProcessId);

    } else if (vars.commandIn("SAVE_BUTTONActionButton172")) {
       process172(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800087")) {
       process800087(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800075")) {
       process800075(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton119")) {
       process119(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton193")) {
       process193(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton185")) {
       process185(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton175")) {
       process175(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800130")) {
       process800130(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800151")) {
       process800151(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800109")) {
       process800109(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton800085")) {
       process800085(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton6445135367544A329CFEC7506B7475FD")) {
       process6445135367544A329CFEC7506B7475FD(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD234AE084F7040DCB66E281A4237FF99")) {
       processD234AE084F7040DCB66E281A4237FF99(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton7A7374B370DB4A23A6576EE0E1ED0388")) {
       process7A7374B370DB4A23A6576EE0E1ED0388(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton74C7F572A6B54BFEB85EA6830B9E826A")) {
       process74C7F572A6B54BFEB85EA6830B9E826A(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonBCD057684E0A46498C90229A55C1D301")) {
       processBCD057684E0A46498C90229A55C1D301(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton2CFFB51D2FB04D85B18AAD4C1741FED3")) {
       process2CFFB51D2FB04D85B18AAD4C1741FED3(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton21178259304B46DF9EB234CCC094623E")) {
       process21178259304B46DF9EB234CCC094623E(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonAE7FE5ABB6084E0ABAC9A557542F09D8")) {
       processAE7FE5ABB6084E0ABAC9A557542F09D8(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton2DD838352350470DAB65A5CF015CC166")) {
       process2DD838352350470DAB65A5CF015CC166(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonDCB42D1AA1C546A6811C46BE10686CF0")) {
       processDCB42D1AA1C546A6811C46BE10686CF0(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA9680AB37C8B48C0A0711E7AE8C41770")) {
       processA9680AB37C8B48C0A0711E7AE8C41770(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton25D06F40232C4463A2AC83287D9F0B02")) {
       process25D06F40232C4463A2AC83287D9F0B02(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton525D95D3B9A34B7CA49384A2FF775818")) {
       process525D95D3B9A34B7CA49384A2FF775818(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonAA4664E339E94FFDAF2448EB1562234C")) {
       processAA4664E339E94FFDAF2448EB1562234C(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB504959087344D36A84F97CAF6FE10C4")) {
       processB504959087344D36A84F97CAF6FE10C4(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton303A9E52AE9046EDAAC23DD6D91828B8")) {
       process303A9E52AE9046EDAAC23DD6D91828B8(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton29954ADED42947329B05E01379B0C8A2")) {
       process29954ADED42947329B05E01379B0C8A2(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton107764141FA94DC8BB87C35E7BDAEBE0")) {
       process107764141FA94DC8BB87C35E7BDAEBE0(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton79C2622162B6439E96A15E366ACF326C")) {
       process79C2622162B6439E96A15E366ACF326C(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonAD870F99388C404487034D642E315D97")) {
       processAD870F99388C404487034D642E315D97(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE222FF23CCB7480E9D354B33517E8C6B")) {
       processE222FF23CCB7480E9D354B33517E8C6B(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton4A81E29CC06E4D169A2E37F9053250F3")) {
       process4A81E29CC06E4D169A2E37F9053250F3(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton151AE91995A24463A7861B81CB403F43")) {
       process151AE91995A24463A7861B81CB403F43(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton5059DC89F5C34BAA99A967E39E606562")) {
       process5059DC89F5C34BAA99A967E39E606562(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton14E82A449A504DA09627CF13191D7BB4")) {
       process14E82A449A504DA09627CF13191D7BB4(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton8F5EF0FCF01C41D2AA51E16CFD697D6E")) {
       process8F5EF0FCF01C41D2AA51E16CFD697D6E(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton6327905FF65D4D42A3A667B8ADE5380D")) {
       process6327905FF65D4D42A3A667B8ADE5380D(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD7088D427D6A462FBD2DD03C58C4F299")) {
       processD7088D427D6A462FBD2DD03C58C4F299(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton6D8D3AFBFD4F43669DCBB78054D15E4A")) {
       process6D8D3AFBFD4F43669DCBB78054D15E4A(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton77B4C27E61674B9595DE2ADA83B99925")) {
       process77B4C27E61674B9595DE2ADA83B99925(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton0C32C66672F1458598940A308E75DBF4")) {
       process0C32C66672F1458598940A308E75DBF4(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFE3C92FD61224B66B872C7150EA5BB37")) {
       processFE3C92FD61224B66B872C7150EA5BB37(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton3800D291C19F4336B0EF5094667022D1")) {
       process3800D291C19F4336B0EF5094667022D1(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB10F1B7314DD45B8A4F3015E54F4E428")) {
       processB10F1B7314DD45B8A4F3015E54F4E428(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButton8722C6EB947C4597AE913430DD84C9C3")) {
       process8722C6EB947C4597AE913430DD84C9C3(vars, request, response);    
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFFB36346586F49FF8E590DD00A037318")) {
       processFFB36346586F49FF8E590DD00A037318(vars, request, response);    

    } else pageErrorPopUp(response);
  }

  void printPageDefault(HttpServletResponse response, VariablesSecureApp vars, String strProcessId) throws IOException, ServletException {
    log4j.debug("Output: Default");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDefault").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
	xmlDocument.setParameter("trlFormType", "PROCESS");
	xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    out.println(xmlDocument.print());
    out.close();
  }
  
  void printPageFrames(HttpServletResponse response, VariablesSecureApp vars, String strProcessId) throws IOException, ServletException {
    log4j.debug("Output: Default");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDefaultFrames").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
    xmlDocument.setParameter("trlFormType", "PROCESS");
    xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    out.println(xmlDocument.print());
    out.close();
  }

    void printPageButton172(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 172");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton172", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("172");
        vars.removeMessage("172");
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
    void printPageButton800087(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800087");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800087", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800087");
        vars.removeMessage("800087");
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
    void printPageButton800075(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800075");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800075", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800075");
        vars.removeMessage("800075");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("M_Warehouse_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "M_Warehouse_ID", "197", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_Warehouse_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_BPartner_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_BPartner_ID", "192", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_BPartner_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("ReferenceNo", "");
    xmlDocument.setParameter("DateInvoiced", "");
    xmlDocument.setParameter("DateInvoiced_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton119(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 119");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton119", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("119");
        vars.removeMessage("119");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateInvoiced", "");
    xmlDocument.setParameter("DateInvoiced_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("AD_Org_ID", Utility.getContext(this, vars, "#AD_Org_ID", windowId));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "FDC45D1AE2404B8384D14DB6DED90DCF", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "#AD_Org_ID", windowId));
    xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("IncludeChildOrgs", "");
    xmlDocument.setParameter("C_Order_ID", "");
    xmlDocument.setParameter("C_BPartner_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_BPartner_ID", "", "135", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_BPartner_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("InvoiceToDate", "");
    xmlDocument.setParameter("InvoiceToDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton193(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 193");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton193", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("193");
        vars.removeMessage("193");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DateOrdered", "");
    xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("C_BPartner_ID", "");
    xmlDocument.setParameter("C_BPartner_IDR", "");
    xmlDocument.setParameter("Vendor_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "Vendor_ID", "192", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportVendor_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Order_ID", "");
    xmlDocument.setParameter("C_Order_IDR", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton185(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 185");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton185", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("185");
        vars.removeMessage("185");
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
    void printPageButton175(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 175");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton175", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("175");
        vars.removeMessage("175");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Client_ID", Utility.getContext(this, vars, "AD_Client_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Client_ID", ""));
    xmlDocument.setData("reportAD_Client_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("AD_Table_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Table_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton800130(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800130");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800130", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800130");
        vars.removeMessage("800130");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Initdate", DateTimeData.today(this));
    xmlDocument.setParameter("Initdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("EndDate", "");
    xmlDocument.setParameter("EndDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton800151(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800151");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800151", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800151");
        vars.removeMessage("800151");
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
    void printPageButton800109(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800109");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800109", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800109");
        vars.removeMessage("800109");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("C_Budget_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Budget_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Budget_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("MA_Processplan_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "MA_Processplan_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportMA_Processplan_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("CalcDate", "");
    xmlDocument.setParameter("CalcDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton800085(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 800085");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton800085", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("800085");
        vars.removeMessage("800085");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("IsIncremental", "N");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6445135367544A329CFEC7506B7475FD(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6445135367544A329CFEC7506B7475FD");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6445135367544A329CFEC7506B7475FD", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6445135367544A329CFEC7506B7475FD");
        vars.removeMessage("6445135367544A329CFEC7506B7475FD");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("Start_Date", "");
    xmlDocument.setParameter("Start_Date_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("EndDate", "");
    xmlDocument.setParameter("EndDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("checkin_status", "");
    comboTableData = new ComboTableData(vars, this, "17", "checkin_status", "30A96D1AC9014C66B5F73B1C2A7D7437", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportcheckin_status", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("checkout_status", "");
    comboTableData = new ComboTableData(vars, this, "17", "checkout_status", "30A96D1AC9014C66B5F73B1C2A7D7437", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportcheckout_status", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD234AE084F7040DCB66E281A4237FF99(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D234AE084F7040DCB66E281A4237FF99");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD234AE084F7040DCB66E281A4237FF99", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D234AE084F7040DCB66E281A4237FF99");
        vars.removeMessage("D234AE084F7040DCB66E281A4237FF99");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("reportType", "cus");
    comboTableData = new ComboTableData(vars, this, "17", "reportType", "B82C3C28E51F4AA6B87D98E7ABBF92F0", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "cus");
    xmlDocument.setData("reportreportType", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("AD_Org_ID", Utility.getContext(this, vars, "AD_Org_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "D9463AFD77E44F619D396C19BF9E6A15", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Org_ID", ""));
    xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_AcctSchema_ID", Utility.getContext(this, vars, "C_AcctSchema_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "C_AcctSchema_ID", ""));
    xmlDocument.setData("reportC_AcctSchema_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_BPartner_ID", Utility.getContext(this, vars, "C_BPartner_ID", ""));
    xmlDocument.setParameter("C_BPartner_IDR", "");
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton7A7374B370DB4A23A6576EE0E1ED0388(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7A7374B370DB4A23A6576EE0E1ED0388");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7A7374B370DB4A23A6576EE0E1ED0388", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7A7374B370DB4A23A6576EE0E1ED0388");
        vars.removeMessage("7A7374B370DB4A23A6576EE0E1ED0388");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("reportType", "");
    comboTableData = new ComboTableData(vars, this, "17", "reportType", "B82C3C28E51F4AA6B87D98E7ABBF92F0", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportreportType", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_AcctSchema_ID", Utility.getContext(this, vars, "C_AcctSchema_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "C_AcctSchema_ID", ""));
    xmlDocument.setData("reportC_AcctSchema_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DateFrom", "");
    xmlDocument.setParameter("DateFrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DateTo", "");
    xmlDocument.setParameter("DateTo_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "html");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "html");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton74C7F572A6B54BFEB85EA6830B9E826A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 74C7F572A6B54BFEB85EA6830B9E826A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton74C7F572A6B54BFEB85EA6830B9E826A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("74C7F572A6B54BFEB85EA6830B9E826A");
        vars.removeMessage("74C7F572A6B54BFEB85EA6830B9E826A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("m_warehouse_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "m_warehouse_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportm_warehouse_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("tdate", "");
    xmlDocument.setParameter("tdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("m_product_category_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "m_product_category_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportm_product_category_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonBCD057684E0A46498C90229A55C1D301(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process BCD057684E0A46498C90229A55C1D301");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonBCD057684E0A46498C90229A55C1D301", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("BCD057684E0A46498C90229A55C1D301");
        vars.removeMessage("BCD057684E0A46498C90229A55C1D301");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("Hris_C_Bp_Department_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "Hris_C_Bp_Department_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportHris_C_Bp_Department_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2CFFB51D2FB04D85B18AAD4C1741FED3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2CFFB51D2FB04D85B18AAD4C1741FED3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2CFFB51D2FB04D85B18AAD4C1741FED3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2CFFB51D2FB04D85B18AAD4C1741FED3");
        vars.removeMessage("2CFFB51D2FB04D85B18AAD4C1741FED3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton21178259304B46DF9EB234CCC094623E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 21178259304B46DF9EB234CCC094623E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton21178259304B46DF9EB234CCC094623E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("21178259304B46DF9EB234CCC094623E");
        vars.removeMessage("21178259304B46DF9EB234CCC094623E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("startdate", "");
    xmlDocument.setParameter("startdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("enddate", "");
    xmlDocument.setParameter("enddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonAE7FE5ABB6084E0ABAC9A557542F09D8(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process AE7FE5ABB6084E0ABAC9A557542F09D8");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonAE7FE5ABB6084E0ABAC9A557542F09D8", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("AE7FE5ABB6084E0ABAC9A557542F09D8");
        vars.removeMessage("AE7FE5ABB6084E0ABAC9A557542F09D8");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("PYR_Salarypayment_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "PYR_Salarypayment_ID", "56154CC700A44BD780D817B7DE245FD7", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportPYR_Salarypayment_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2DD838352350470DAB65A5CF015CC166(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2DD838352350470DAB65A5CF015CC166");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2DD838352350470DAB65A5CF015CC166", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2DD838352350470DAB65A5CF015CC166");
        vars.removeMessage("2DD838352350470DAB65A5CF015CC166");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("dateorderedfrom", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedfrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("dateorderedto", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedto_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonDCB42D1AA1C546A6811C46BE10686CF0(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DCB42D1AA1C546A6811C46BE10686CF0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDCB42D1AA1C546A6811C46BE10686CF0", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("DCB42D1AA1C546A6811C46BE10686CF0");
        vars.removeMessage("DCB42D1AA1C546A6811C46BE10686CF0");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("Hris_C_Bp_Department_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "Hris_C_Bp_Department_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportHris_C_Bp_Department_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("Start_Date", "");
    xmlDocument.setParameter("Start_Date_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("EndDate", "");
    xmlDocument.setParameter("EndDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA9680AB37C8B48C0A0711E7AE8C41770(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A9680AB37C8B48C0A0711E7AE8C41770");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA9680AB37C8B48C0A0711E7AE8C41770", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A9680AB37C8B48C0A0711E7AE8C41770");
        vars.removeMessage("A9680AB37C8B48C0A0711E7AE8C41770");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("C_Order_ID", "");
    xmlDocument.setParameter("outputType", "html");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "html");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton25D06F40232C4463A2AC83287D9F0B02(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 25D06F40232C4463A2AC83287D9F0B02");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton25D06F40232C4463A2AC83287D9F0B02", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("25D06F40232C4463A2AC83287D9F0B02");
        vars.removeMessage("25D06F40232C4463A2AC83287D9F0B02");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", Utility.getContext(this, vars, "AD_Client_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Client_ID", ""));
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton525D95D3B9A34B7CA49384A2FF775818(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 525D95D3B9A34B7CA49384A2FF775818");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton525D95D3B9A34B7CA49384A2FF775818", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("525D95D3B9A34B7CA49384A2FF775818");
        vars.removeMessage("525D95D3B9A34B7CA49384A2FF775818");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("ad_org_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_org_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("PYR_Salarypayment_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "PYR_Salarypayment_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportPYR_Salarypayment_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonAA4664E339E94FFDAF2448EB1562234C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process AA4664E339E94FFDAF2448EB1562234C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonAA4664E339E94FFDAF2448EB1562234C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("AA4664E339E94FFDAF2448EB1562234C");
        vars.removeMessage("AA4664E339E94FFDAF2448EB1562234C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_InOut_ID", "");
    xmlDocument.setParameter("outputType", "html");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "html");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB504959087344D36A84F97CAF6FE10C4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B504959087344D36A84F97CAF6FE10C4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB504959087344D36A84F97CAF6FE10C4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B504959087344D36A84F97CAF6FE10C4");
        vars.removeMessage("B504959087344D36A84F97CAF6FE10C4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("ad_org_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_org_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("PYR_Salarypayment_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "PYR_Salarypayment_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportPYR_Salarypayment_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton303A9E52AE9046EDAAC23DD6D91828B8(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 303A9E52AE9046EDAAC23DD6D91828B8");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton303A9E52AE9046EDAAC23DD6D91828B8", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("303A9E52AE9046EDAAC23DD6D91828B8");
        vars.removeMessage("303A9E52AE9046EDAAC23DD6D91828B8");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_CLIENT_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_CLIENT_ID", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_CLIENT_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("STARTDATE", "");
    xmlDocument.setParameter("STARTDATE_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("ENDDATE", "");
    xmlDocument.setParameter("ENDDATE_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("EM_HRIS_C_BP_DEPARTMENT_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "EM_HRIS_C_BP_DEPARTMENT_ID", "7454C1A032C5402FA3D3A0ABA71E1786", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportEM_HRIS_C_BP_DEPARTMENT_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton29954ADED42947329B05E01379B0C8A2(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 29954ADED42947329B05E01379B0C8A2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton29954ADED42947329B05E01379B0C8A2", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("29954ADED42947329B05E01379B0C8A2");
        vars.removeMessage("29954ADED42947329B05E01379B0C8A2");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton107764141FA94DC8BB87C35E7BDAEBE0(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 107764141FA94DC8BB87C35E7BDAEBE0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton107764141FA94DC8BB87C35E7BDAEBE0", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("107764141FA94DC8BB87C35E7BDAEBE0");
        vars.removeMessage("107764141FA94DC8BB87C35E7BDAEBE0");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("pyr_salarypayment_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "pyr_salarypayment_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportpyr_salarypayment_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton79C2622162B6439E96A15E366ACF326C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 79C2622162B6439E96A15E366ACF326C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton79C2622162B6439E96A15E366ACF326C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("79C2622162B6439E96A15E366ACF326C");
        vars.removeMessage("79C2622162B6439E96A15E366ACF326C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("m_product_id", "");
    xmlDocument.setParameter("m_product_idR", "");
    xmlDocument.setParameter("m_warehouse_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "m_warehouse_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportm_warehouse_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonAD870F99388C404487034D642E315D97(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process AD870F99388C404487034D642E315D97");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonAD870F99388C404487034D642E315D97", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("AD870F99388C404487034D642E315D97");
        vars.removeMessage("AD870F99388C404487034D642E315D97");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("pyr_salarypayment_id", "");
    comboTableData = new ComboTableData(vars, this, "18", "pyr_salarypayment_id", "56154CC700A44BD780D817B7DE245FD7", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportpyr_salarypayment_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE222FF23CCB7480E9D354B33517E8C6B(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E222FF23CCB7480E9D354B33517E8C6B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE222FF23CCB7480E9D354B33517E8C6B", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E222FF23CCB7480E9D354B33517E8C6B");
        vars.removeMessage("E222FF23CCB7480E9D354B33517E8C6B");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("dateorderedfrom", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedfrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("dateorderedto", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedto_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton4A81E29CC06E4D169A2E37F9053250F3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4A81E29CC06E4D169A2E37F9053250F3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4A81E29CC06E4D169A2E37F9053250F3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("4A81E29CC06E4D169A2E37F9053250F3");
        vars.removeMessage("4A81E29CC06E4D169A2E37F9053250F3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_CLIENT_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_CLIENT_ID", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_CLIENT_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("STARTDATE", "");
    xmlDocument.setParameter("STARTDATE_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("ENDDATE", "");
    xmlDocument.setParameter("ENDDATE_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("EM_HRIS_C_BP_DEPARTMENT_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "EM_HRIS_C_BP_DEPARTMENT_ID", "7454C1A032C5402FA3D3A0ABA71E1786", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportEM_HRIS_C_BP_DEPARTMENT_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton151AE91995A24463A7861B81CB403F43(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 151AE91995A24463A7861B81CB403F43");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton151AE91995A24463A7861B81CB403F43", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("151AE91995A24463A7861B81CB403F43");
        vars.removeMessage("151AE91995A24463A7861B81CB403F43");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton5059DC89F5C34BAA99A967E39E606562(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 5059DC89F5C34BAA99A967E39E606562");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton5059DC89F5C34BAA99A967E39E606562", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("5059DC89F5C34BAA99A967E39E606562");
        vars.removeMessage("5059DC89F5C34BAA99A967E39E606562");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("m_product_id", "");
    xmlDocument.setParameter("m_product_idR", "");
    xmlDocument.setParameter("dateorderedfrom", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedfrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("dateorderedto", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedto_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton14E82A449A504DA09627CF13191D7BB4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 14E82A449A504DA09627CF13191D7BB4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton14E82A449A504DA09627CF13191D7BB4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("14E82A449A504DA09627CF13191D7BB4");
        vars.removeMessage("14E82A449A504DA09627CF13191D7BB4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("pyr_salarypayment_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "pyr_salarypayment_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportpyr_salarypayment_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8F5EF0FCF01C41D2AA51E16CFD697D6E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8F5EF0FCF01C41D2AA51E16CFD697D6E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8F5EF0FCF01C41D2AA51E16CFD697D6E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8F5EF0FCF01C41D2AA51E16CFD697D6E");
        vars.removeMessage("8F5EF0FCF01C41D2AA51E16CFD697D6E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("m_product_id", "");
    xmlDocument.setParameter("m_product_idR", "");
    xmlDocument.setParameter("dateorderedfrom", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedfrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("dateorderedto", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedto_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6327905FF65D4D42A3A667B8ADE5380D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6327905FF65D4D42A3A667B8ADE5380D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6327905FF65D4D42A3A667B8ADE5380D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6327905FF65D4D42A3A667B8ADE5380D");
        vars.removeMessage("6327905FF65D4D42A3A667B8ADE5380D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("hris_jobtitle_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "hris_jobtitle_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reporthris_jobtitle_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD7088D427D6A462FBD2DD03C58C4F299(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D7088D427D6A462FBD2DD03C58C4F299");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD7088D427D6A462FBD2DD03C58C4F299", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D7088D427D6A462FBD2DD03C58C4F299");
        vars.removeMessage("D7088D427D6A462FBD2DD03C58C4F299");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("pyr_salarypayment_id", "");
    comboTableData = new ComboTableData(vars, this, "18", "pyr_salarypayment_id", "56154CC700A44BD780D817B7DE245FD7", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportpyr_salarypayment_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("outputType", "");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6D8D3AFBFD4F43669DCBB78054D15E4A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6D8D3AFBFD4F43669DCBB78054D15E4A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6D8D3AFBFD4F43669DCBB78054D15E4A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6D8D3AFBFD4F43669DCBB78054D15E4A");
        vars.removeMessage("6D8D3AFBFD4F43669DCBB78054D15E4A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Client_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Client_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_BPartner_ID", "");
    xmlDocument.setParameter("C_BPartner_IDR", "");
    xmlDocument.setParameter("StartDate", "");
    xmlDocument.setParameter("StartDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("EndDate", "");
    xmlDocument.setParameter("EndDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton77B4C27E61674B9595DE2ADA83B99925(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 77B4C27E61674B9595DE2ADA83B99925");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton77B4C27E61674B9595DE2ADA83B99925", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("77B4C27E61674B9595DE2ADA83B99925");
        vars.removeMessage("77B4C27E61674B9595DE2ADA83B99925");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Client_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Client_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("M_Product_ID", "");
    xmlDocument.setParameter("M_Product_IDR", "");
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton0C32C66672F1458598940A308E75DBF4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0C32C66672F1458598940A308E75DBF4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0C32C66672F1458598940A308E75DBF4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("0C32C66672F1458598940A308E75DBF4");
        vars.removeMessage("0C32C66672F1458598940A308E75DBF4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("start_date", DateTimeData.today(this));
    xmlDocument.setParameter("start_date_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("end_date", DateTimeData.today(this));
    xmlDocument.setParameter("end_date_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputtype", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputtype", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputtype", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFE3C92FD61224B66B872C7150EA5BB37(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FE3C92FD61224B66B872C7150EA5BB37");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFE3C92FD61224B66B872C7150EA5BB37", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FE3C92FD61224B66B872C7150EA5BB37");
        vars.removeMessage("FE3C92FD61224B66B872C7150EA5BB37");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("fin_financial_account_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "fin_financial_account_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportfin_financial_account_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("startdate", DateTimeData.today(this));
    xmlDocument.setParameter("startdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("enddate", DateTimeData.today(this));
    xmlDocument.setParameter("enddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3800D291C19F4336B0EF5094667022D1(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3800D291C19F4336B0EF5094667022D1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3800D291C19F4336B0EF5094667022D1", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3800D291C19F4336B0EF5094667022D1");
        vars.removeMessage("3800D291C19F4336B0EF5094667022D1");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "c_bpartner_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportc_bpartner_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("dirut", "");
    xmlDocument.setParameter("direk", "");
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB10F1B7314DD45B8A4F3015E54F4E428(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B10F1B7314DD45B8A4F3015E54F4E428");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB10F1B7314DD45B8A4F3015E54F4E428", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B10F1B7314DD45B8A4F3015E54F4E428");
        vars.removeMessage("B10F1B7314DD45B8A4F3015E54F4E428");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_InOut_ID", "");
    xmlDocument.setParameter("outputType", "html");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "html");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8722C6EB947C4597AE913430DD84C9C3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8722C6EB947C4597AE913430DD84C9C3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8722C6EB947C4597AE913430DD84C9C3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8722C6EB947C4597AE913430DD84C9C3");
        vars.removeMessage("8722C6EB947C4597AE913430DD84C9C3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("ad_org_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_org_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "c_bpartner_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportc_bpartner_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("hris_jobtitle_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "hris_jobtitle_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reporthris_jobtitle_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("startdate", "");
    xmlDocument.setParameter("startdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("enddate", "");
    xmlDocument.setParameter("enddate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFFB36346586F49FF8E590DD00A037318(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FFB36346586F49FF8E590DD00A037318");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFFB36346586F49FF8E590DD00A037318", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButton_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FFB36346586F49FF8E590DD00A037318");
        vars.removeMessage("FFB36346586F49FF8E590DD00A037318");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_client_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportad_client_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("c_bpartner_id", "");
    xmlDocument.setParameter("c_bpartner_idR", "");
    xmlDocument.setParameter("m_product_id", "");
    xmlDocument.setParameter("m_product_idR", "");
    xmlDocument.setParameter("dateorderedfrom", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedfrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("dateorderedto", DateTimeData.today(this));
    xmlDocument.setParameter("dateorderedto_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("outputType", "pdf");
    comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "pdf");
    xmlDocument.setData("reportoutputType", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }



    private void process172(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "172", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800087(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800087", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800075(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800075", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String strmWarehouseId = vars.getStringParameter("inpmWarehouseId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "M_Warehouse_ID", strmWarehouseId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strreferenceno = vars.getStringParameter("inpreferenceno");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "ReferenceNo", strreferenceno, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateinvoiced = vars.getStringParameter("inpdateinvoiced");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "60", "DateInvoiced", strdateinvoiced, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process119(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "119", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateinvoiced = vars.getStringParameter("inpdateinvoiced");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateInvoiced", strdateinvoiced, vars.getClient(), vars.getOrg(), vars.getUser());
String stradOrgId = vars.getStringParameter("inpadOrgId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "15", "AD_Org_ID", stradOrgId, vars.getClient(), vars.getOrg(), vars.getUser());
String strincludechildorgs = vars.getStringParameter("inpincludechildorgs", "N");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "16", "IncludeChildOrgs", strincludechildorgs, vars.getClient(), vars.getOrg(), vars.getUser());
String strcOrderId = vars.getStringParameter("inpcOrderId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "C_Order_ID", strcOrderId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strinvoicetodate = vars.getStringParameter("inpinvoicetodate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "InvoiceToDate", strinvoicetodate, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process193(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "193", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strdateordered = vars.getStringParameter("inpdateordered");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "DateOrdered", strdateordered, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strvendorId = vars.getStringParameter("inpvendorId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "Vendor_ID", strvendorId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcOrderId = vars.getStringParameter("inpcOrderId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "C_Order_ID", strcOrderId, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process185(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "185", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process175(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "175", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_Client_ID", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stradTableId = vars.getStringParameter("inpadTableId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "AD_Table_ID", stradTableId, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800130(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800130", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strinitdate = vars.getStringParameter("inpinitdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "Initdate", strinitdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "EndDate", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800151(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800151", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        
        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800109(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800109", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcBudgetId = vars.getStringParameter("inpcBudgetId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "C_Budget_ID", strcBudgetId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmaProcessplanId = vars.getStringParameter("inpmaProcessplanId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "MA_Processplan_ID", strmaProcessplanId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcalcdate = vars.getStringParameter("inpcalcdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "CalcDate", strcalcdate, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process800085(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "800085", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strisincremental = vars.getStringParameter("inpisincremental", "N");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "IsIncremental", strisincremental, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process6445135367544A329CFEC7506B7475FD(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "6445135367544A329CFEC7506B7475FD", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strstartDate = vars.getStringParameter("inpstartDate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "Start_Date", strstartDate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "EndDate", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcheckinStatus = vars.getStringParameter("inpcheckinStatus");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "checkin_status", strcheckinStatus, vars.getClient(), vars.getOrg(), vars.getUser());
String strcheckoutStatus = vars.getStringParameter("inpcheckoutStatus");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "checkout_status", strcheckoutStatus, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "90", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processD234AE084F7040DCB66E281A4237FF99(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "D234AE084F7040DCB66E281A4237FF99", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strreporttype = vars.getStringParameter("inpreporttype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "reportType", strreporttype, vars.getClient(), vars.getOrg(), vars.getUser());
String stradOrgId = vars.getStringParameter("inpadOrgId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "AD_Org_ID", stradOrgId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcAcctschemaId = vars.getStringParameter("inpcAcctschemaId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "C_AcctSchema_ID", strcAcctschemaId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "50", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "60", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "70", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process7A7374B370DB4A23A6576EE0E1ED0388(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "7A7374B370DB4A23A6576EE0E1ED0388", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strreporttype = vars.getStringParameter("inpreporttype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "reportType", strreporttype, vars.getClient(), vars.getOrg(), vars.getUser());
String strcAcctschemaId = vars.getStringParameter("inpcAcctschemaId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "C_AcctSchema_ID", strcAcctschemaId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdatefrom = vars.getStringParameter("inpdatefrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "DateFrom", strdatefrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateto = vars.getStringParameter("inpdateto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "DateTo", strdateto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process74C7F572A6B54BFEB85EA6830B9E826A(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "74C7F572A6B54BFEB85EA6830B9E826A", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strmWarehouseId = vars.getStringParameter("inpmWarehouseId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "m_warehouse_id", strmWarehouseId, vars.getClient(), vars.getOrg(), vars.getUser());
String strtdate = vars.getStringParameter("inptdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "tdate", strtdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strmProductCategoryId = vars.getStringParameter("inpmProductCategoryId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "m_product_category_id", strmProductCategoryId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processBCD057684E0A46498C90229A55C1D301(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "BCD057684E0A46498C90229A55C1D301", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strhrisCBpDepartmentId = vars.getStringParameter("inphrisCBpDepartmentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "Hris_C_Bp_Department_ID", strhrisCBpDepartmentId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process2CFFB51D2FB04D85B18AAD4C1741FED3(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "2CFFB51D2FB04D85B18AAD4C1741FED3", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process21178259304B46DF9EB234CCC094623E(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "21178259304B46DF9EB234CCC094623E", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strstartdate = vars.getStringParameter("inpstartdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "10", "startdate", strstartdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "enddate", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processAE7FE5ABB6084E0ABAC9A557542F09D8(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "AE7FE5ABB6084E0ABAC9A557542F09D8", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strpyrSalarypaymentId = vars.getStringParameter("inppyrSalarypaymentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "PYR_Salarypayment_ID", strpyrSalarypaymentId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process2DD838352350470DAB65A5CF015CC166(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "2DD838352350470DAB65A5CF015CC166", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedfrom = vars.getStringParameter("inpdateorderedfrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "dateorderedfrom", strdateorderedfrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedto = vars.getStringParameter("inpdateorderedto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "dateorderedto", strdateorderedto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processDCB42D1AA1C546A6811C46BE10686CF0(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "DCB42D1AA1C546A6811C46BE10686CF0", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strhrisCBpDepartmentId = vars.getStringParameter("inphrisCBpDepartmentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "Hris_C_Bp_Department_ID", strhrisCBpDepartmentId, vars.getClient(), vars.getOrg(), vars.getUser());
String strstartDate = vars.getStringParameter("inpstartDate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "Start_Date", strstartDate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "50", "EndDate", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "60", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processA9680AB37C8B48C0A0711E7AE8C41770(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "A9680AB37C8B48C0A0711E7AE8C41770", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcOrderId = vars.getStringParameter("inpcOrderId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "C_Order_ID", strcOrderId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process25D06F40232C4463A2AC83287D9F0B02(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "25D06F40232C4463A2AC83287D9F0B02", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process525D95D3B9A34B7CA49384A2FF775818(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "525D95D3B9A34B7CA49384A2FF775818", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stradOrgId = vars.getStringParameter("inpadOrgId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "ad_org_id", stradOrgId, vars.getClient(), vars.getOrg(), vars.getUser());
String strpyrSalarypaymentId = vars.getStringParameter("inppyrSalarypaymentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "PYR_Salarypayment_ID", strpyrSalarypaymentId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "60", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processAA4664E339E94FFDAF2448EB1562234C(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "AA4664E339E94FFDAF2448EB1562234C", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strmInoutId = vars.getStringParameter("inpmInoutId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "M_InOut_ID", strmInoutId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processB504959087344D36A84F97CAF6FE10C4(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "B504959087344D36A84F97CAF6FE10C4", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stradOrgId = vars.getStringParameter("inpadOrgId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "ad_org_id", stradOrgId, vars.getClient(), vars.getOrg(), vars.getUser());
String strpyrSalarypaymentId = vars.getStringParameter("inppyrSalarypaymentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "PYR_Salarypayment_ID", strpyrSalarypaymentId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process303A9E52AE9046EDAAC23DD6D91828B8(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "303A9E52AE9046EDAAC23DD6D91828B8", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_CLIENT_ID", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strstartdate = vars.getStringParameter("inpstartdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "STARTDATE", strstartdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "ENDDATE", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());
String stremHrisCBpDepartmentId = vars.getStringParameter("inpemHrisCBpDepartmentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "EM_HRIS_C_BP_DEPARTMENT_ID", stremHrisCBpDepartmentId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process29954ADED42947329B05E01379B0C8A2(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "29954ADED42947329B05E01379B0C8A2", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process107764141FA94DC8BB87C35E7BDAEBE0(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "107764141FA94DC8BB87C35E7BDAEBE0", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strpyrSalarypaymentId = vars.getStringParameter("inppyrSalarypaymentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "pyr_salarypayment_id", strpyrSalarypaymentId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process79C2622162B6439E96A15E366ACF326C(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "79C2622162B6439E96A15E366ACF326C", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmProductId = vars.getStringParameter("inpmProductId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "m_product_id", strmProductId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmWarehouseId = vars.getStringParameter("inpmWarehouseId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "m_warehouse_id", strmWarehouseId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processAD870F99388C404487034D642E315D97(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "AD870F99388C404487034D642E315D97", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strpyrSalarypaymentId = vars.getStringParameter("inppyrSalarypaymentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "pyr_salarypayment_id", strpyrSalarypaymentId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processE222FF23CCB7480E9D354B33517E8C6B(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "E222FF23CCB7480E9D354B33517E8C6B", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedfrom = vars.getStringParameter("inpdateorderedfrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "dateorderedfrom", strdateorderedfrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedto = vars.getStringParameter("inpdateorderedto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "dateorderedto", strdateorderedto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process4A81E29CC06E4D169A2E37F9053250F3(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "4A81E29CC06E4D169A2E37F9053250F3", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_CLIENT_ID", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strstartdate = vars.getStringParameter("inpstartdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "STARTDATE", strstartdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "ENDDATE", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());
String stremHrisCBpDepartmentId = vars.getStringParameter("inpemHrisCBpDepartmentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "EM_HRIS_C_BP_DEPARTMENT_ID", stremHrisCBpDepartmentId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process151AE91995A24463A7861B81CB403F43(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "151AE91995A24463A7861B81CB403F43", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process5059DC89F5C34BAA99A967E39E606562(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "5059DC89F5C34BAA99A967E39E606562", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmProductId = vars.getStringParameter("inpmProductId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "m_product_id", strmProductId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedfrom = vars.getStringParameter("inpdateorderedfrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "dateorderedfrom", strdateorderedfrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedto = vars.getStringParameter("inpdateorderedto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "50", "dateorderedto", strdateorderedto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "60", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process14E82A449A504DA09627CF13191D7BB4(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "14E82A449A504DA09627CF13191D7BB4", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strpyrSalarypaymentId = vars.getStringParameter("inppyrSalarypaymentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "pyr_salarypayment_id", strpyrSalarypaymentId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process8F5EF0FCF01C41D2AA51E16CFD697D6E(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "8F5EF0FCF01C41D2AA51E16CFD697D6E", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmProductId = vars.getStringParameter("inpmProductId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "m_product_id", strmProductId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedfrom = vars.getStringParameter("inpdateorderedfrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "dateorderedfrom", strdateorderedfrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedto = vars.getStringParameter("inpdateorderedto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "50", "dateorderedto", strdateorderedto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "60", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process6327905FF65D4D42A3A667B8ADE5380D(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "6327905FF65D4D42A3A667B8ADE5380D", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strhrisJobtitleId = vars.getStringParameter("inphrisJobtitleId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "hris_jobtitle_id", strhrisJobtitleId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processD7088D427D6A462FBD2DD03C58C4F299(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "D7088D427D6A462FBD2DD03C58C4F299", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strpyrSalarypaymentId = vars.getStringParameter("inppyrSalarypaymentId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "pyr_salarypayment_id", strpyrSalarypaymentId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process6D8D3AFBFD4F43669DCBB78054D15E4A(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "6D8D3AFBFD4F43669DCBB78054D15E4A", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_Client_ID", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "15", "C_BPartner_ID", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strstartdate = vars.getStringParameter("inpstartdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "StartDate", strstartdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "EndDate", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process77B4C27E61674B9595DE2ADA83B99925(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "77B4C27E61674B9595DE2ADA83B99925", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "AD_Client_ID", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmProductId = vars.getStringParameter("inpmProductId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "M_Product_ID", strmProductId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process0C32C66672F1458598940A308E75DBF4(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "0C32C66672F1458598940A308E75DBF4", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strstartDate = vars.getStringParameter("inpstartDate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "start_date", strstartDate, vars.getClient(), vars.getOrg(), vars.getUser());
String strendDate = vars.getStringParameter("inpendDate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "end_date", strendDate, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputtype", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processFE3C92FD61224B66B872C7150EA5BB37(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "FE3C92FD61224B66B872C7150EA5BB37", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strfinFinancialAccountId = vars.getStringParameter("inpfinFinancialAccountId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "fin_financial_account_id", strfinFinancialAccountId, vars.getClient(), vars.getOrg(), vars.getUser());
String strstartdate = vars.getStringParameter("inpstartdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "20", "startdate", strstartdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "enddate", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process3800D291C19F4336B0EF5094667022D1(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "3800D291C19F4336B0EF5094667022D1", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdirut = vars.getStringParameter("inpdirut");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "dirut", strdirut, vars.getClient(), vars.getOrg(), vars.getUser());
String strdirek = vars.getStringParameter("inpdirek");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "direk", strdirek, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "40", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processB10F1B7314DD45B8A4F3015E54F4E428(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "B10F1B7314DD45B8A4F3015E54F4E428", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String strmInoutId = vars.getStringParameter("inpmInoutId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "M_InOut_ID", strmInoutId, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void process8722C6EB947C4597AE913430DD84C9C3(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "8722C6EB947C4597AE913430DD84C9C3", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String stradOrgId = vars.getStringParameter("inpadOrgId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "15", "ad_org_id", stradOrgId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strhrisJobtitleId = vars.getStringParameter("inphrisJobtitleId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "25", "hris_jobtitle_id", strhrisJobtitleId, vars.getClient(), vars.getOrg(), vars.getUser());
String strstartdate = vars.getStringParameter("inpstartdate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "30", "startdate", strstartdate, vars.getClient(), vars.getOrg(), vars.getUser());
String strenddate = vars.getStringParameter("inpenddate");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "enddate", strenddate, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "50", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }
    private void processFFB36346586F49FF8E590DD00A037318(VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException{
         
        String strProcessing = vars.getStringParameter("inpprocessing");
        String pinstance = SequenceIdData.getUUID();
        PInstanceProcessData.insertPInstance(this, pinstance, "FFB36346586F49FF8E590DD00A037318", "0", strProcessing, vars.getUser(), vars.getClient(), vars.getOrg());
        String stradClientId = vars.getStringParameter("inpadClientId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "10", "ad_client_id", stradClientId, vars.getClient(), vars.getOrg(), vars.getUser());
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "20", "c_bpartner_id", strcBpartnerId, vars.getClient(), vars.getOrg(), vars.getUser());
String strmProductId = vars.getStringParameter("inpmProductId");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "30", "m_product_id", strmProductId, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedfrom = vars.getStringParameter("inpdateorderedfrom");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "40", "dateorderedfrom", strdateorderedfrom, vars.getClient(), vars.getOrg(), vars.getUser());
String strdateorderedto = vars.getStringParameter("inpdateorderedto");
PInstanceProcessData.insertPInstanceParamDate(this, pinstance, "50", "dateorderedto", strdateorderedto, vars.getClient(), vars.getOrg(), vars.getUser());
String stroutputtype = vars.getStringParameter("inpoutputtype");
PInstanceProcessData.insertPInstanceParam(this, pinstance, "60", "outputType", stroutputtype, vars.getClient(), vars.getOrg(), vars.getUser());

        
        ProcessBundle bundle = ProcessBundle.pinstance(pinstance, vars, this);
        new ProcessRunner(bundle).execute(this);

        processButtonHelper(request, response, vars, pinstance);    
  }


  public String getServletInfo() {
    return "Servlet ActionButton_Responser. This Servlet was made by Wad constructor";
  } // end of the getServletInfo() method

  private void processButtonHelper(HttpServletRequest request, HttpServletResponse response, VariablesSecureApp vars, String pinstance) 
     throws ServletException, IOException {
      OBError myMessage;
      try {
        PInstanceProcessData[] pinstanceData = PInstanceProcessData.select(this, pinstance);
      myMessage = Utility.getProcessInstanceMessage(this, vars, pinstanceData);
      } catch (Exception e) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), e.getMessage());
          e.printStackTrace();
          log4j.warn("Error");
      }
      advisePopUp(request, response, myMessage.getType(), myMessage.getTitle(), myMessage.getMessage());
  }
}
