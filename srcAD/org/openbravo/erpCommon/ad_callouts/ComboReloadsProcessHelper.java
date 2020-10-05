
package org.openbravo.erpCommon.ad_callouts;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.xmlEngine.XmlDocument;


public class ComboReloadsProcessHelper extends CalloutHelper {
  private static final long serialVersionUID = 1L;

  void printPage(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
   String strProcessId = vars.getStringParameter("inpadProcessId");
   
     if (strProcessId.equals("176")) {
       process176(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("6255BE488882480599C81284B70CD9B3")) {
       process6255BE488882480599C81284B70CD9B3(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("58A9261BACEF45DDA526F29D8557272D")) {
       process58A9261BACEF45DDA526F29D8557272D(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("DDCB419F5A964DFA99419C772268160C")) {
       processDDCB419F5A964DFA99419C772268160C(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("0BDC2164ED3E48539FCEF4D306F29EFD")) {
       process0BDC2164ED3E48539FCEF4D306F29EFD(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("5A2A0AF88AF54BB085DCC52FCC9B17B7")) {
       process5A2A0AF88AF54BB085DCC52FCC9B17B7(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("692AEB22612F4A39B7912019D797FC2D")) {
       process692AEB22612F4A39B7912019D797FC2D(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("140")) {
       process140(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("F5C44E6E034B42FF8865AC0417C799FE")) {
       processF5C44E6E034B42FF8865AC0417C799FE(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("B504959087344D36A84F97CAF6FE10C4")) {
       processB504959087344D36A84F97CAF6FE10C4(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("F68F2890E96D4D85A1DEF0274D105BCE")) {
       processF68F2890E96D4D85A1DEF0274D105BCE(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("B9DBEEFEE8B44DCA91F00D2D8E6E6435")) {
       processB9DBEEFEE8B44DCA91F00D2D8E6E6435(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("6327905FF65D4D42A3A667B8ADE5380D")) {
       process6327905FF65D4D42A3A667B8ADE5380D(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800136")) {
       process800136(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("154")) {
       process154(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("D234AE084F7040DCB66E281A4237FF99")) {
       processD234AE084F7040DCB66E281A4237FF99(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800172")) {
       process800172(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("4CD83C2A2470454DA1762133E0744572")) {
       process4CD83C2A2470454DA1762133E0744572(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("C4AEFC3C35174E5EB01DAF7187F3CCD0")) {
       processC4AEFC3C35174E5EB01DAF7187F3CCD0(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("23D1B163EC0B41F790CE39BF01DA320E")) {
       process23D1B163EC0B41F790CE39BF01DA320E(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("224")) {
       process224(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("108")) {
       process108(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("207")) {
       process207(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("155")) {
       process155(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("135")) {
       process135(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("206")) {
       process206(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("2DDE7D3618034C38A4462B7F3456C28D")) {
       process2DDE7D3618034C38A4462B7F3456C28D(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("221")) {
       process221(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("220")) {
       process220(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("6BF16EFC772843AC9A17552AE0B26AB7")) {
       process6BF16EFC772843AC9A17552AE0B26AB7(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("8722C6EB947C4597AE913430DD84C9C3")) {
       process8722C6EB947C4597AE913430DD84C9C3(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("E264309FF8244A94936502BF51829109")) {
       processE264309FF8244A94936502BF51829109(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("175")) {
       process175(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("29954ADED42947329B05E01379B0C8A2")) {
       process29954ADED42947329B05E01379B0C8A2(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("2CFFB51D2FB04D85B18AAD4C1741FED3")) {
       process2CFFB51D2FB04D85B18AAD4C1741FED3(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800163")) {
       process800163(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("1004400000")) {
       process1004400000(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800075")) {
       process800075(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("6A8A69B7F47E444DAACA0C142573D174")) {
       process6A8A69B7F47E444DAACA0C142573D174(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("3D671B238B16410581EADAAF1091F625")) {
       process3D671B238B16410581EADAAF1091F625(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("225")) {
       process225(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("525D95D3B9A34B7CA49384A2FF775818")) {
       process525D95D3B9A34B7CA49384A2FF775818(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("5779FA163F6F40EBB956765E0B2AB3D6")) {
       process5779FA163F6F40EBB956765E0B2AB3D6(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("800131")) {
       process800131(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("151AE91995A24463A7861B81CB403F43")) {
       process151AE91995A24463A7861B81CB403F43(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("017312F51139438A9665775E3B5392A1")) {
       process017312F51139438A9665775E3B5392A1(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("6D8D3AFBFD4F43669DCBB78054D15E4A")) {
       process6D8D3AFBFD4F43669DCBB78054D15E4A(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("112")) {
       process112(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("F3B32C87EC0A49EB938D28FB64F12E35")) {
       processF3B32C87EC0A49EB938D28FB64F12E35(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("FF8080812E2F8EAE012E2F94CF470014")) {
       processFF8080812E2F8EAE012E2F94CF470014(response, vars, strTabId, windowId);
       return;
     }
    
     if (strProcessId.equals("34C1DF029D234BA29FC8DBCBC4AC4B28")) {
       process34C1DF029D234BA29FC8DBCBC4AC4B28(response, vars, strTabId, windowId);
       return;
     }
    
    
    pageError(response);
  }
  
  
    private void process176(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads176';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#userClient")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inp#adLanguage")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadTableId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process6255BE488882480599C81284B70CD9B3(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads6255BE488882480599C81284B70CD9B3';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpemAprmProcessPayment", "inpemAprmProcessPayment", "inpfinPaymentId", "inpemAprmProcessPayment", "inpfinPaymentId", "inpemAprmProcessPayment", "inpstatus", "inpemAprmProcessPayment")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "36972531DA994BB38ECB91993058282F", "575E470ABADB4C278132C957A78C47E3", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process58A9261BACEF45DDA526F29D8557272D(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads58A9261BACEF45DDA526F29D8557272D';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpprocessed", "inpprocessed")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "EC75B6F5A9504DB6B3F3356EA85F15EE", "CA425689672A42D7BE2158EE41E44F94", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processDDCB419F5A964DFA99419C772268160C(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsDDCB419F5A964DFA99419C772268160C';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId", "inp#adClientId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "c_doctype_id", "", "102", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcDoctypeId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process0BDC2164ED3E48539FCEF4D306F29EFD(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads0BDC2164ED3E48539FCEF4D306F29EFD';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpemAprmProcess", "inpemAprmProcess")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "798239EB069F41A9BA8EE040C63DDBBC", "3842B167CA6F44239C3357A721E3BA6A", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process5A2A0AF88AF54BB085DCC52FCC9B17B7(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads5A2A0AF88AF54BB085DCC52FCC9B17B7';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpresStatus", "inpresStatus", "inpresStatus", "inpresStatus")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "RES_Action", "440DDA64A43F4799AAFF48BC86DC8F78", "1645143617E44289A08A1EA4D617A184", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpresAction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process692AEB22612F4A39B7912019D797FC2D(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads692AEB22612F4A39B7912019D797FC2D';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process140(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads140';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FF8081812F06A183012F07323A2A001C", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processF5C44E6E034B42FF8865AC0417C799FE(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsF5C44E6E034B42FF8865AC0417C799FE';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#adClientId", "inpadOrgId", "inp#adClientId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "C_DocTypeInvoice_ID", "170", "8AC5A5643A6740A0A17457C00ED0DF41", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcDoctypeinvoiceId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inp#adClientId", "inpadOrgId", "inp#adClientId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "C_DocTypeShipment_ID", "170", "A0171DD95AE84C42A83AFE2228EF8310", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcDoctypeshipmentId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "FIN_PaymentMethod_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpfinPaymentmethodId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmWarehouseId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_Bpartner_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcBpartnerId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcPaymenttermId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmPricelistId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processB504959087344D36A84F97CAF6FE10C4(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsB504959087344D36A84F97CAF6FE10C4';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "PYR_Salarypayment_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inppyrSalarypaymentId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processF68F2890E96D4D85A1DEF0274D105BCE(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsF68F2890E96D4D85A1DEF0274D105BCE';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpprocessed", "inpprocessed")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "F671DDEA466D41A996F605590CB545BC", "FAE0D7C8A9D84FAFAE3C10CD5DCE6E30", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processB9DBEEFEE8B44DCA91F00D2D8E6E6435(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsB9DBEEFEE8B44DCA91F00D2D8E6E6435';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Org_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process6327905FF65D4D42A3A667B8ADE5380D(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads6327905FF65D4D42A3A667B8ADE5380D';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpoutputtype")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800136(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800136';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FF8081812F06A183012F07323A2A001C", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process154(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads154';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpissotrx", "inpadOrgId", "inpadClientId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_Version_ID", "", "26D8602C48004E1182B46310DF7015AE", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmPricelistVersionId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processD234AE084F7040DCB66E281A4237FF99(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsD234AE084F7040DCB66E281A4237FF99';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#adRoleId", "inp#adRoleId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "D9463AFD77E44F619D396C19BF9E6A15", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "reportType", "B82C3C28E51F4AA6B87D98E7ABBF92F0", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpreporttype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800172(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800172';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpoutputtype")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "1000200002", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process4CD83C2A2470454DA1762133E0744572(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads4CD83C2A2470454DA1762133E0744572';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpcYearId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "C_Period_Id", "275", "C3AB162BAFED423CB8D5107902FD621B", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcPeriodId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processC4AEFC3C35174E5EB01DAF7187F3CCD0(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsC4AEFC3C35174E5EB01DAF7187F3CCD0';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadClientId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_DocType_Id", "", "8E0486B4548F4A0290AEA692B707F744", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcDoctypeId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcPaymenttermId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Org_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_Pricelist_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmPricelistId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmWarehouseId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_Bpartner_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcBpartnerId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "FIN_PaymentMethod_Id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpfinPaymentmethodId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process23D1B163EC0B41F790CE39BF01DA320E(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads23D1B163EC0B41F790CE39BF01DA320E';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpissotrx", "inpadClientId", "inpadOrgId", "inp#adClientId", "inpmProductId", "inpissotrx", "inpadOrgId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_Tax_ID", "", "299FA667CF374AC5ACC74739C3251134", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcTaxId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process224(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads224';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpcProjectId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_ProjectLine_ID", "", "175", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcProjectlineId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process108(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads108';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpcAcctschemaId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FDA7BA9355A6468DAF67E1C5288990A6", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process207(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads207';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "DocAction", "135", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process155(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads155';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinppaymentrule")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "PaymentRule", "195", "162", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inppaymentrule";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process135(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads135';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadImpformatId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_ImpFormat_ID", "", "1001100000", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadImpformatId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process206(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads206';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "DocAction", "135", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpdocaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process2DDE7D3618034C38A4462B7F3456C28D(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads2DDE7D3618034C38A4462B7F3456C28D';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpprocessed", "inpprocessed")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "EC75B6F5A9504DB6B3F3356EA85F15EE", "CA425689672A42D7BE2158EE41E44F94", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process221(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads221';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_BankAccount_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcBankaccountId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process220(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads220';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process6BF16EFC772843AC9A17552AE0B26AB7(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads6BF16EFC772843AC9A17552AE0B26AB7';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpprocessed", "inpprocessed")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "FF8080812E443491012E443C053A001A", "FF808081332719060133271E5BB1001B", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process8722C6EB947C4597AE913430DD84C9C3(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads8722C6EB947C4597AE913430DD84C9C3';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "c_bpartner_id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcBpartnerId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "hris_jobtitle_id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inphrisJobtitleId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processE264309FF8244A94936502BF51829109(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsE264309FF8244A94936502BF51829109';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#userClient")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inp#adLanguage")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadTableId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process175(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads175';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#userClient")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inp#adLanguage")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadTableId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process29954ADED42947329B05E01379B0C8A2(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads29954ADED42947329B05E01379B0C8A2';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpoutputtype")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process2CFFB51D2FB04D85B18AAD4C1741FED3(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads2CFFB51D2FB04D85B18AAD4C1741FED3';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpoutputtype")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800163(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800163';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_ID", "", "71188F0005494DA08311B4FFB2C5A993", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmWarehouseId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process1004400000(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads1004400000';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_ID", "", "A3DCDE5EDD4A4403AC205B131F10F84D", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmWarehouseId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800075(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800075';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inp#adClientId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "M_Warehouse_ID", "197", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpmWarehouseId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process6A8A69B7F47E444DAACA0C142573D174(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads6A8A69B7F47E444DAACA0C142573D174';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process3D671B238B16410581EADAAF1091F625(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads3D671B238B16410581EADAAF1091F625';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process225(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads225';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpcProjectId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_ProjectLine_ID", "", "174", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcProjectlineId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process525D95D3B9A34B7CA49384A2FF775818(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads525D95D3B9A34B7CA49384A2FF775818';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "ad_client_id", "", "116", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadClientId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "PYR_Salarypayment_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inppyrSalarypaymentId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process5779FA163F6F40EBB956765E0B2AB3D6(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads5779FA163F6F40EBB956765E0B2AB3D6';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpoutputtype")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process800131(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads800131';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpstatus", "inpstatus")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "657B89EF105149F2B011CF8F5034FF92", "C5A7AABB91A440EBAA53A0222B99FF2F", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process151AE91995A24463A7861B81CB403F43(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads151AE91995A24463A7861B81CB403F43';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpoutputtype")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process017312F51139438A9665775E3B5392A1(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads017312F51139438A9665775E3B5392A1';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpemAprmProcess", "inpemAprmProcess")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "798239EB069F41A9BA8EE040C63DDBBC", "3842B167CA6F44239C3357A721E3BA6A", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process6D8D3AFBFD4F43669DCBB78054D15E4A(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads6D8D3AFBFD4F43669DCBB78054D15E4A';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpinpoutputtype")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "outputType", "800104", "95588BA11F5640B79FCA52E570B597F5", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpoutputtype";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process112(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads112';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId", "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "C_AcctSchema_ID", "", "FF8081812F06A183012F07323A2A001C", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcAcctschemaId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processF3B32C87EC0A49EB938D28FB64F12E35(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsF3B32C87EC0A49EB938D28FB64F12E35';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpadOrgId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpadOrgId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void processFF8080812E2F8EAE012E2F94CF470014(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloadsFF8080812E2F8EAE012E2F94CF470014';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpprocessed", "inpprocessed")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "17", "action", "FF8080812E443491012E443C053A001A", "FF808081332719060133271E5BB1001B", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpaction";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
    private void process34C1DF029D234BA29FC8DBCBC4AC4B28(HttpServletResponse response, VariablesSecureApp vars, String strTabId, String windowId) throws IOException, ServletException {
        String resultField;
        String command = vars.getStringParameter("Command", "DEFAULT");
        XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_callouts/CallOut").createXmlDocument();
        
        StringBuffer resultado = new StringBuffer();
        boolean isFirst=true;
        ComboTableData comboTableData = null;
        resultado.append("var calloutName='ComboReloads34C1DF029D234BA29FC8DBCBC4AC4B28';\n\n");
        resultado.append("var respuesta = new Array(\n");
    
        try {
          
      if (CalloutHelper.commandInCommandList(command, "inpcYearId")) {
        if (!isFirst) resultado.append(", \n");
        comboTableData = new ComboTableData(vars, this, "18", "C_Period_Id", "275", "C3AB162BAFED423CB8D5107902FD621B", Utility.getReferenceableOrg(vars, vars.getStringParameter("inpadOrgId")), Utility.getContext(this, vars, "#User_Client", windowId), 0);
        comboTableData.fillParameters(null, windowId, "");
        resultField = "inpcPeriodId";

        resultado.append("new Array(\"" + resultField + "\", ");
        resultado.append(generateArray(comboTableData.select(false), vars.getStringParameter(resultField)));
        comboTableData = null;
        resultado.append(")");
        isFirst=false;
      }
    
        } catch (ServletException ex) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        } catch (Exception ex1) {
          OBError myError = Utility.translateError(this, vars, vars.getLanguage(), ex1.toString());
          bdErrorHidden(response, myError.getType(), myError.getTitle(), myError.getMessage());
          return;
        }
    
        resultado.append("\n);");
    
        xmlDocument.setParameter("array", resultado.toString());
        xmlDocument.setParameter("frameName", "mainframe");
        xmlDocument.setParameter("frameName1", "mainframe");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
 
       return;
     }
    
}
