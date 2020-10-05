
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
import java.util.HashMap;

@SuppressWarnings("unused")
public class ActionButtonJava_Responser extends HttpSecureAppServlet {
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
    } else if (vars.commandIn("BUTTON7CB6B4D1ECCF4036B3F111D2CF11AADE")) {
        
        printPageButton7CB6B4D1ECCF4036B3F111D2CF11AADE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON970EAD9B846648A7AB1F0CCA5058356C")) {
        
        printPageButton970EAD9B846648A7AB1F0CCA5058356C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7EDBFEC35BDA4FF4AF05ED516CDAFB90")) {
        
        printPageButton7EDBFEC35BDA4FF4AF05ED516CDAFB90(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONABDFC8131D964936AD2EF7E0CED97FD9")) {
        
        printPageButtonABDFC8131D964936AD2EF7E0CED97FD9(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONEFDBF909811544DAAE4E876AA781E5DC")) {
        
        printPageButtonEFDBF909811544DAAE4E876AA781E5DC(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON107")) {
        
        printPageButton107(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA3FE1F9892394386A49FB707AA50A0FA")) {
        
        printPageButtonA3FE1F9892394386A49FB707AA50A0FA(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON136")) {
        
        printPageButton136(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFB740AB61B0E42B198D2C88D3A0D0CE6")) {
        
        printPageButtonFB740AB61B0E42B198D2C88D3A0D0CE6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON64B3FF29AC174F4B94538BD0A3CE1CD3")) {
        
        printPageButton64B3FF29AC174F4B94538BD0A3CE1CD3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON5BE14AA10165490A9ADEFB7532F7FA94")) {
        
        printPageButton5BE14AA10165490A9ADEFB7532F7FA94(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON58A9261BACEF45DDA526F29D8557272D")) {
        
        printPageButton58A9261BACEF45DDA526F29D8557272D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON15C8708DFC464C2D91286E59624FDD18")) {
        
        printPageButton15C8708DFC464C2D91286E59624FDD18(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON017312F51139438A9665775E3B5392A1")) {
        
        printPageButton017312F51139438A9665775E3B5392A1(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6255BE488882480599C81284B70CD9B3")) {
        
        printPageButton6255BE488882480599C81284B70CD9B3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON23D1B163EC0B41F790CE39BF01DA320E")) {
        
        printPageButton23D1B163EC0B41F790CE39BF01DA320E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6FBD65B0FDB74D1AB07F0EADF18D48AE")) {
        
        printPageButton6FBD65B0FDB74D1AB07F0EADF18D48AE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9EB2228A60684C0DBEC12D5CD8D85218")) {
        
        printPageButton9EB2228A60684C0DBEC12D5CD8D85218(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND85D5B5E368A49B1A6293BA4AE15F0F9")) {
        
        printPageButtonD85D5B5E368A49B1A6293BA4AE15F0F9(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF80808133362F6A013336781FCE0066")) {
        
        printPageButtonFF80808133362F6A013336781FCE0066(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF8081813219E68E013219ECFE930004")) {
        
        printPageButtonFF8081813219E68E013219ECFE930004(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF808181324D007801324D2AE1130066")) {
        
        printPageButtonFF808181324D007801324D2AE1130066(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF808181326CD80501326CE906D70042")) {
        
        printPageButtonFF808181326CD80501326CE906D70042(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF80818132A4F6AD0132A573DD7A0021")) {
        
        printPageButtonFF80818132A4F6AD0132A573DD7A0021(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON29D17F515727436DBCE32BC6CA28382B")) {
        
        printPageButton29D17F515727436DBCE32BC6CA28382B(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONDE1B382FDD2540199D223586F6E216D0")) {
        
        printPageButtonDE1B382FDD2540199D223586F6E216D0(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND16966FBF9604A3D91A50DC83C6EA8E3")) {
        
        printPageButtonD16966FBF9604A3D91A50DC83C6EA8E3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF8080812E2F8EAE012E2F94CF470014")) {
        
        printPageButtonFF8080812E2F8EAE012E2F94CF470014(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF8080812F348A97012F349DC24F0007")) {
        
        printPageButtonFF8080812F348A97012F349DC24F0007(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON48FF95BA0B944EB981C9EA012AE215C6")) {
        
        printPageButton48FF95BA0B944EB981C9EA012AE215C6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3C386BC12832466790E50F2F8C5EBD85")) {
        
        printPageButton3C386BC12832466790E50F2F8C5EBD85(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2DDE7D3618034C38A4462B7F3456C28D")) {
        
        printPageButton2DDE7D3618034C38A4462B7F3456C28D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6BF16EFC772843AC9A17552AE0B26AB7")) {
        
        printPageButton6BF16EFC772843AC9A17552AE0B26AB7(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON58591E3E0F7648E4A09058E037CE49FC")) {
        
        printPageButton58591E3E0F7648E4A09058E037CE49FC(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1E689A488E9F42EC9835A23FD845F91F")) {
        
        printPageButton1E689A488E9F42EC9835A23FD845F91F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7B0C4F8D4D1943FBAC960A860B5CFD2F")) {
        
        printPageButton7B0C4F8D4D1943FBAC960A860B5CFD2F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF80818132902152013290D3E5D2001A")) {
        
        printPageButtonFF80818132902152013290D3E5D2001A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON692AEB22612F4A39B7912019D797FC2D")) {
        
        printPageButton692AEB22612F4A39B7912019D797FC2D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6A8A69B7F47E444DAACA0C142573D174")) {
        
        printPageButton6A8A69B7F47E444DAACA0C142573D174(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9128A01FDF49406BBE7C19479DAC4414")) {
        
        printPageButton9128A01FDF49406BBE7C19479DAC4414(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF3B32C87EC0A49EB938D28FB64F12E35")) {
        
        printPageButtonF3B32C87EC0A49EB938D28FB64F12E35(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6DBBB591113D41AABDD74515D7E50480")) {
        
        printPageButton6DBBB591113D41AABDD74515D7E50480(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4A4DB835730245B29CB86D2FF2A48BC2")) {
        
        printPageButton4A4DB835730245B29CB86D2FF2A48BC2(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB9DBEEFEE8B44DCA91F00D2D8E6E6435")) {
        
        printPageButtonB9DBEEFEE8B44DCA91F00D2D8E6E6435(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3D671B238B16410581EADAAF1091F625")) {
        
        printPageButton3D671B238B16410581EADAAF1091F625(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF5C44E6E034B42FF8865AC0417C799FE")) {
        
        printPageButtonF5C44E6E034B42FF8865AC0417C799FE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND5ED0F4EA8E44A4A827CCBBFA16433C3")) {
        
        printPageButtonD5ED0F4EA8E44A4A827CCBBFA16433C3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONDDCB419F5A964DFA99419C772268160C")) {
        
        printPageButtonDDCB419F5A964DFA99419C772268160C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON926199266113402BAEEDAA0DA770E935")) {
        
        printPageButton926199266113402BAEEDAA0DA770E935(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONC4AEFC3C35174E5EB01DAF7187F3CCD0")) {
        
        printPageButtonC4AEFC3C35174E5EB01DAF7187F3CCD0(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON337F8E5C1B1B4F2AA1F70B54B09EF5DA")) {
        
        printPageButton337F8E5C1B1B4F2AA1F70B54B09EF5DA(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON62525FB7D809411C98C7AE053BBC3676")) {
        
        printPageButton62525FB7D809411C98C7AE053BBC3676(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONC384DFB29934414B84ED4204CE8B8F10")) {
        
        printPageButtonC384DFB29934414B84ED4204CE8B8F10(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND9BE934098084CD0807FE5795258B763")) {
        
        printPageButtonD9BE934098084CD0807FE5795258B763(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONC6790B3A3DF24785B25CE94CA4F7CC29")) {
        
        printPageButtonC6790B3A3DF24785B25CE94CA4F7CC29(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1511B5CDC40B4F7CBD99EAB57F53AD5E")) {
        
        printPageButton1511B5CDC40B4F7CBD99EAB57F53AD5E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONCCA562AC11004AE0AA35D2CA54CE8B3A")) {
        
        printPageButtonCCA562AC11004AE0AA35D2CA54CE8B3A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONC76AC67734AE4E359D28BB6AEFCE7FF1")) {
        
        printPageButtonC76AC67734AE4E359D28BB6AEFCE7FF1(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONCBCA1AE8F1F54A258EC83AA48D143DD5")) {
        
        printPageButtonCBCA1AE8F1F54A258EC83AA48D143DD5(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON34C1DF029D234BA29FC8DBCBC4AC4B28")) {
        
        printPageButton34C1DF029D234BA29FC8DBCBC4AC4B28(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4D1E97433C044E648DAE30B05D5AEB45")) {
        
        printPageButton4D1E97433C044E648DAE30B05D5AEB45(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND06D41A3C96B42D484834CC1CAD85E5C")) {
        
        printPageButtonD06D41A3C96B42D484834CC1CAD85E5C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON547E4F03BBA5495EAF63CA3B31023C48")) {
        
        printPageButton547E4F03BBA5495EAF63CA3B31023C48(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONAE856EB10CD8444EB7555625E1F48631")) {
        
        printPageButtonAE856EB10CD8444EB7555625E1F48631(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON556420A9A9F746E887227511AF3079AA")) {
        
        printPageButton556420A9A9F746E887227511AF3079AA(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0B9BC0C6675E48E083EF02153A8C6D4B")) {
        
        printPageButton0B9BC0C6675E48E083EF02153A8C6D4B(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE17D7B1680EC44B5A93CC0E9B72DC119")) {
        
        printPageButtonE17D7B1680EC44B5A93CC0E9B72DC119(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON03E56616272845EA9F662AD983F1595F")) {
        
        printPageButton03E56616272845EA9F662AD983F1595F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONBC22C0CB91014A6CB8AEA65E2460F003")) {
        
        printPageButtonBC22C0CB91014A6CB8AEA65E2460F003(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9E673FD989FB49779E7E9B3DFB58040D")) {
        
        printPageButton9E673FD989FB49779E7E9B3DFB58040D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7D1FD4B0FCBB4CC698DD502C8BB4DC4F")) {
        
        printPageButton7D1FD4B0FCBB4CC698DD502C8BB4DC4F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE2A09C3C7EE8431DAD0FBB6818D91DF4")) {
        
        printPageButtonE2A09C3C7EE8431DAD0FBB6818D91DF4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE2352DD134384FE595EF9170C787E661")) {
        
        printPageButtonE2352DD134384FE595EF9170C787E661(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB0FE443BA0AD453FAB67332729930F1C")) {
        
        printPageButtonB0FE443BA0AD453FAB67332729930F1C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONABA6FABCB88F4CD4976DD34BBAA3F226")) {
        
        printPageButtonABA6FABCB88F4CD4976DD34BBAA3F226(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON53A720D166714A208F2B43060E47A049")) {
        
        printPageButton53A720D166714A208F2B43060E47A049(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4CD83C2A2470454DA1762133E0744572")) {
        
        printPageButton4CD83C2A2470454DA1762133E0744572(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0A218C05221F4AD8AF530FAC0548FB09")) {
        
        printPageButton0A218C05221F4AD8AF530FAC0548FB09(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4A6B6F8C719049AEAA7E266B6417E0E8")) {
        
        printPageButton4A6B6F8C719049AEAA7E266B6417E0E8(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE31AE2EA06364D918674A3BF38A2760F")) {
        
        printPageButtonE31AE2EA06364D918674A3BF38A2760F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON273FE57D0E3F4A2492CD8D4309F90DEA")) {
        
        printPageButton273FE57D0E3F4A2492CD8D4309F90DEA(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON23421D87CC8D4574A8735D67EFBD8C45")) {
        
        printPageButton23421D87CC8D4574A8735D67EFBD8C45(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9459F204EC91466F9B6E3972488CF40C")) {
        
        printPageButton9459F204EC91466F9B6E3972488CF40C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3D364FBA35A74050BFAC48474C252161")) {
        
        printPageButton3D364FBA35A74050BFAC48474C252161(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONCE7D3B05AC8141D495698BC8F4A6F673")) {
        
        printPageButtonCE7D3B05AC8141D495698BC8F4A6F673(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE068B05336BC48459E3BF33241C67B04")) {
        
        printPageButtonE068B05336BC48459E3BF33241C67B04(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON860D3F7046F64A21BE86B2FF390A6187")) {
        
        printPageButton860D3F7046F64A21BE86B2FF390A6187(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON5F401EDB7EA54E24A8F002749AEC8F14")) {
        
        printPageButton5F401EDB7EA54E24A8F002749AEC8F14(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3270DC8DF5C143F280771F1200F2A11C")) {
        
        printPageButton3270DC8DF5C143F280771F1200F2A11C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE93DD2C121914B4DA6A1252015B893DE")) {
        
        printPageButtonE93DD2C121914B4DA6A1252015B893DE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9DB4D30BFC5144B9B431CB49DDE9270D")) {
        
        printPageButton9DB4D30BFC5144B9B431CB49DDE9270D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0BDC2164ED3E48539FCEF4D306F29EFD")) {
        
        printPageButton0BDC2164ED3E48539FCEF4D306F29EFD(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONCD7283DF804B449C97DA09446669EEEF")) {
        
        printPageButtonCD7283DF804B449C97DA09446669EEEF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON85601427EAEE401FA0250FF0A6DD62EF")) {
        
        printPageButton85601427EAEE401FA0250FF0A6DD62EF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF68F2890E96D4D85A1DEF0274D105BCE")) {
        
        printPageButtonF68F2890E96D4D85A1DEF0274D105BCE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9B26EF017C244CD59C77BE203A2D14A8")) {
        
        printPageButton9B26EF017C244CD59C77BE203A2D14A8(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONDC1DA3F261684A1D8EFA223CF2FFA25F")) {
        
        printPageButtonDC1DA3F261684A1D8EFA223CF2FFA25F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8C6B905EDA8742A0ABF4092902D54090")) {
        
        printPageButton8C6B905EDA8742A0ABF4092902D54090(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONAA3FEBF2A14147FAA0FE5AA479D8890F")) {
        
        printPageButtonAA3FEBF2A14147FAA0FE5AA479D8890F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON45505DCA177040C0A428F34AD86570E6")) {
        
        printPageButton45505DCA177040C0A428F34AD86570E6(response, vars, strProcessId);

    } else if (vars.commandIn("SAVE_BUTTONActionButton7CB6B4D1ECCF4036B3F111D2CF11AADE")) {
        process7CB6B4D1ECCF4036B3F111D2CF11AADE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton970EAD9B846648A7AB1F0CCA5058356C")) {
        process970EAD9B846648A7AB1F0CCA5058356C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton7EDBFEC35BDA4FF4AF05ED516CDAFB90")) {
        process7EDBFEC35BDA4FF4AF05ED516CDAFB90(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonABDFC8131D964936AD2EF7E0CED97FD9")) {
        processABDFC8131D964936AD2EF7E0CED97FD9(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonEFDBF909811544DAAE4E876AA781E5DC")) {
        processEFDBF909811544DAAE4E876AA781E5DC(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton107")) {
        process107(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA3FE1F9892394386A49FB707AA50A0FA")) {
        processA3FE1F9892394386A49FB707AA50A0FA(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton136")) {
        process136(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFB740AB61B0E42B198D2C88D3A0D0CE6")) {
        processFB740AB61B0E42B198D2C88D3A0D0CE6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton64B3FF29AC174F4B94538BD0A3CE1CD3")) {
        process64B3FF29AC174F4B94538BD0A3CE1CD3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton5BE14AA10165490A9ADEFB7532F7FA94")) {
        process5BE14AA10165490A9ADEFB7532F7FA94(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton58A9261BACEF45DDA526F29D8557272D")) {
        process58A9261BACEF45DDA526F29D8557272D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton15C8708DFC464C2D91286E59624FDD18")) {
        process15C8708DFC464C2D91286E59624FDD18(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton017312F51139438A9665775E3B5392A1")) {
        process017312F51139438A9665775E3B5392A1(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6255BE488882480599C81284B70CD9B3")) {
        process6255BE488882480599C81284B70CD9B3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton23D1B163EC0B41F790CE39BF01DA320E")) {
        process23D1B163EC0B41F790CE39BF01DA320E(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6FBD65B0FDB74D1AB07F0EADF18D48AE")) {
        process6FBD65B0FDB74D1AB07F0EADF18D48AE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9EB2228A60684C0DBEC12D5CD8D85218")) {
        process9EB2228A60684C0DBEC12D5CD8D85218(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD85D5B5E368A49B1A6293BA4AE15F0F9")) {
        processD85D5B5E368A49B1A6293BA4AE15F0F9(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF80808133362F6A013336781FCE0066")) {
        processFF80808133362F6A013336781FCE0066(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF8081813219E68E013219ECFE930004")) {
        processFF8081813219E68E013219ECFE930004(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF808181324D007801324D2AE1130066")) {
        processFF808181324D007801324D2AE1130066(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF808181326CD80501326CE906D70042")) {
        processFF808181326CD80501326CE906D70042(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF80818132A4F6AD0132A573DD7A0021")) {
        processFF80818132A4F6AD0132A573DD7A0021(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton29D17F515727436DBCE32BC6CA28382B")) {
        process29D17F515727436DBCE32BC6CA28382B(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonDE1B382FDD2540199D223586F6E216D0")) {
        processDE1B382FDD2540199D223586F6E216D0(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD16966FBF9604A3D91A50DC83C6EA8E3")) {
        processD16966FBF9604A3D91A50DC83C6EA8E3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF8080812E2F8EAE012E2F94CF470014")) {
        processFF8080812E2F8EAE012E2F94CF470014(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF8080812F348A97012F349DC24F0007")) {
        processFF8080812F348A97012F349DC24F0007(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton48FF95BA0B944EB981C9EA012AE215C6")) {
        process48FF95BA0B944EB981C9EA012AE215C6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3C386BC12832466790E50F2F8C5EBD85")) {
        process3C386BC12832466790E50F2F8C5EBD85(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton2DDE7D3618034C38A4462B7F3456C28D")) {
        process2DDE7D3618034C38A4462B7F3456C28D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6BF16EFC772843AC9A17552AE0B26AB7")) {
        process6BF16EFC772843AC9A17552AE0B26AB7(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton58591E3E0F7648E4A09058E037CE49FC")) {
        process58591E3E0F7648E4A09058E037CE49FC(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1E689A488E9F42EC9835A23FD845F91F")) {
        process1E689A488E9F42EC9835A23FD845F91F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton7B0C4F8D4D1943FBAC960A860B5CFD2F")) {
        process7B0C4F8D4D1943FBAC960A860B5CFD2F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF80818132902152013290D3E5D2001A")) {
        processFF80818132902152013290D3E5D2001A(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton692AEB22612F4A39B7912019D797FC2D")) {
        process692AEB22612F4A39B7912019D797FC2D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6A8A69B7F47E444DAACA0C142573D174")) {
        process6A8A69B7F47E444DAACA0C142573D174(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9128A01FDF49406BBE7C19479DAC4414")) {
        process9128A01FDF49406BBE7C19479DAC4414(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF3B32C87EC0A49EB938D28FB64F12E35")) {
        processF3B32C87EC0A49EB938D28FB64F12E35(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6DBBB591113D41AABDD74515D7E50480")) {
        process6DBBB591113D41AABDD74515D7E50480(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton4A4DB835730245B29CB86D2FF2A48BC2")) {
        process4A4DB835730245B29CB86D2FF2A48BC2(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB9DBEEFEE8B44DCA91F00D2D8E6E6435")) {
        processB9DBEEFEE8B44DCA91F00D2D8E6E6435(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3D671B238B16410581EADAAF1091F625")) {
        process3D671B238B16410581EADAAF1091F625(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF5C44E6E034B42FF8865AC0417C799FE")) {
        processF5C44E6E034B42FF8865AC0417C799FE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD5ED0F4EA8E44A4A827CCBBFA16433C3")) {
        processD5ED0F4EA8E44A4A827CCBBFA16433C3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonDDCB419F5A964DFA99419C772268160C")) {
        processDDCB419F5A964DFA99419C772268160C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton926199266113402BAEEDAA0DA770E935")) {
        process926199266113402BAEEDAA0DA770E935(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonC4AEFC3C35174E5EB01DAF7187F3CCD0")) {
        processC4AEFC3C35174E5EB01DAF7187F3CCD0(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton337F8E5C1B1B4F2AA1F70B54B09EF5DA")) {
        process337F8E5C1B1B4F2AA1F70B54B09EF5DA(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton62525FB7D809411C98C7AE053BBC3676")) {
        process62525FB7D809411C98C7AE053BBC3676(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonC384DFB29934414B84ED4204CE8B8F10")) {
        processC384DFB29934414B84ED4204CE8B8F10(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD9BE934098084CD0807FE5795258B763")) {
        processD9BE934098084CD0807FE5795258B763(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonC6790B3A3DF24785B25CE94CA4F7CC29")) {
        processC6790B3A3DF24785B25CE94CA4F7CC29(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1511B5CDC40B4F7CBD99EAB57F53AD5E")) {
        process1511B5CDC40B4F7CBD99EAB57F53AD5E(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonCCA562AC11004AE0AA35D2CA54CE8B3A")) {
        processCCA562AC11004AE0AA35D2CA54CE8B3A(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonC76AC67734AE4E359D28BB6AEFCE7FF1")) {
        processC76AC67734AE4E359D28BB6AEFCE7FF1(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonCBCA1AE8F1F54A258EC83AA48D143DD5")) {
        processCBCA1AE8F1F54A258EC83AA48D143DD5(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton34C1DF029D234BA29FC8DBCBC4AC4B28")) {
        process34C1DF029D234BA29FC8DBCBC4AC4B28(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton4D1E97433C044E648DAE30B05D5AEB45")) {
        process4D1E97433C044E648DAE30B05D5AEB45(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD06D41A3C96B42D484834CC1CAD85E5C")) {
        processD06D41A3C96B42D484834CC1CAD85E5C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton547E4F03BBA5495EAF63CA3B31023C48")) {
        process547E4F03BBA5495EAF63CA3B31023C48(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonAE856EB10CD8444EB7555625E1F48631")) {
        processAE856EB10CD8444EB7555625E1F48631(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton556420A9A9F746E887227511AF3079AA")) {
        process556420A9A9F746E887227511AF3079AA(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton0B9BC0C6675E48E083EF02153A8C6D4B")) {
        process0B9BC0C6675E48E083EF02153A8C6D4B(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE17D7B1680EC44B5A93CC0E9B72DC119")) {
        processE17D7B1680EC44B5A93CC0E9B72DC119(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton03E56616272845EA9F662AD983F1595F")) {
        process03E56616272845EA9F662AD983F1595F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonBC22C0CB91014A6CB8AEA65E2460F003")) {
        processBC22C0CB91014A6CB8AEA65E2460F003(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9E673FD989FB49779E7E9B3DFB58040D")) {
        process9E673FD989FB49779E7E9B3DFB58040D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton7D1FD4B0FCBB4CC698DD502C8BB4DC4F")) {
        process7D1FD4B0FCBB4CC698DD502C8BB4DC4F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE2A09C3C7EE8431DAD0FBB6818D91DF4")) {
        processE2A09C3C7EE8431DAD0FBB6818D91DF4(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE2352DD134384FE595EF9170C787E661")) {
        processE2352DD134384FE595EF9170C787E661(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB0FE443BA0AD453FAB67332729930F1C")) {
        processB0FE443BA0AD453FAB67332729930F1C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonABA6FABCB88F4CD4976DD34BBAA3F226")) {
        processABA6FABCB88F4CD4976DD34BBAA3F226(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton53A720D166714A208F2B43060E47A049")) {
        process53A720D166714A208F2B43060E47A049(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton4CD83C2A2470454DA1762133E0744572")) {
        process4CD83C2A2470454DA1762133E0744572(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton0A218C05221F4AD8AF530FAC0548FB09")) {
        process0A218C05221F4AD8AF530FAC0548FB09(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton4A6B6F8C719049AEAA7E266B6417E0E8")) {
        process4A6B6F8C719049AEAA7E266B6417E0E8(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE31AE2EA06364D918674A3BF38A2760F")) {
        processE31AE2EA06364D918674A3BF38A2760F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton273FE57D0E3F4A2492CD8D4309F90DEA")) {
        process273FE57D0E3F4A2492CD8D4309F90DEA(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton23421D87CC8D4574A8735D67EFBD8C45")) {
        process23421D87CC8D4574A8735D67EFBD8C45(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9459F204EC91466F9B6E3972488CF40C")) {
        process9459F204EC91466F9B6E3972488CF40C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3D364FBA35A74050BFAC48474C252161")) {
        process3D364FBA35A74050BFAC48474C252161(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonCE7D3B05AC8141D495698BC8F4A6F673")) {
        processCE7D3B05AC8141D495698BC8F4A6F673(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE068B05336BC48459E3BF33241C67B04")) {
        processE068B05336BC48459E3BF33241C67B04(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton860D3F7046F64A21BE86B2FF390A6187")) {
        process860D3F7046F64A21BE86B2FF390A6187(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton5F401EDB7EA54E24A8F002749AEC8F14")) {
        process5F401EDB7EA54E24A8F002749AEC8F14(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3270DC8DF5C143F280771F1200F2A11C")) {
        process3270DC8DF5C143F280771F1200F2A11C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE93DD2C121914B4DA6A1252015B893DE")) {
        processE93DD2C121914B4DA6A1252015B893DE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9DB4D30BFC5144B9B431CB49DDE9270D")) {
        process9DB4D30BFC5144B9B431CB49DDE9270D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton0BDC2164ED3E48539FCEF4D306F29EFD")) {
        process0BDC2164ED3E48539FCEF4D306F29EFD(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonCD7283DF804B449C97DA09446669EEEF")) {
        processCD7283DF804B449C97DA09446669EEEF(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton85601427EAEE401FA0250FF0A6DD62EF")) {
        process85601427EAEE401FA0250FF0A6DD62EF(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF68F2890E96D4D85A1DEF0274D105BCE")) {
        processF68F2890E96D4D85A1DEF0274D105BCE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9B26EF017C244CD59C77BE203A2D14A8")) {
        process9B26EF017C244CD59C77BE203A2D14A8(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonDC1DA3F261684A1D8EFA223CF2FFA25F")) {
        processDC1DA3F261684A1D8EFA223CF2FFA25F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton8C6B905EDA8742A0ABF4092902D54090")) {
        process8C6B905EDA8742A0ABF4092902D54090(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonAA3FEBF2A14147FAA0FE5AA479D8890F")) {
        processAA3FEBF2A14147FAA0FE5AA479D8890F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton45505DCA177040C0A428F34AD86570E6")) {
        process45505DCA177040C0A428F34AD86570E6(strProcessId, vars, request, response);

    } else pageErrorPopUp(response);
  }
  
  void printPageFrames(HttpServletResponse response, VariablesSecureApp vars, String strProcessId) throws IOException, ServletException {
    log4j.debug("Output: Default");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDefaultFrames").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
    xmlDocument.setParameter("trlFormType", "PROCESS");
    xmlDocument.setParameter("type", "ActionButtonJava_Responser.html");
    xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    out.println(xmlDocument.print());
    out.close();
  }

  void printPageDefault(HttpServletResponse response, VariablesSecureApp vars, String strProcessId) throws IOException, ServletException {
    log4j.debug("Output: Default");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDefault").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
	  xmlDocument.setParameter("trlFormType", "PROCESS");
	  xmlDocument.setParameter("type", "ActionButtonJava_Responser.html");
	  xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    out.println(xmlDocument.print());
    out.close();
  }

    void printPageButton7CB6B4D1ECCF4036B3F111D2CF11AADE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7CB6B4D1ECCF4036B3F111D2CF11AADE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7CB6B4D1ECCF4036B3F111D2CF11AADE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7CB6B4D1ECCF4036B3F111D2CF11AADE");
        vars.removeMessage("7CB6B4D1ECCF4036B3F111D2CF11AADE");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_Warehouse_ID", Utility.getContext(this, vars, "#M_Warehouse_ID", windowId));
    comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "#M_Warehouse_ID", windowId));
    xmlDocument.setData("reportM_Warehouse_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton970EAD9B846648A7AB1F0CCA5058356C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 970EAD9B846648A7AB1F0CCA5058356C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton970EAD9B846648A7AB1F0CCA5058356C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("970EAD9B846648A7AB1F0CCA5058356C");
        vars.removeMessage("970EAD9B846648A7AB1F0CCA5058356C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Name", "");
    xmlDocument.setParameter("ImportAuditInfo", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton7EDBFEC35BDA4FF4AF05ED516CDAFB90(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7EDBFEC35BDA4FF4AF05ED516CDAFB90");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7EDBFEC35BDA4FF4AF05ED516CDAFB90", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7EDBFEC35BDA4FF4AF05ED516CDAFB90");
        vars.removeMessage("7EDBFEC35BDA4FF4AF05ED516CDAFB90");
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
    void printPageButtonABDFC8131D964936AD2EF7E0CED97FD9(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process ABDFC8131D964936AD2EF7E0CED97FD9");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonABDFC8131D964936AD2EF7E0CED97FD9", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("ABDFC8131D964936AD2EF7E0CED97FD9");
        vars.removeMessage("ABDFC8131D964936AD2EF7E0CED97FD9");
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
    void printPageButtonEFDBF909811544DAAE4E876AA781E5DC(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process EFDBF909811544DAAE4E876AA781E5DC");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonEFDBF909811544DAAE4E876AA781E5DC", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("EFDBF909811544DAAE4E876AA781E5DC");
        vars.removeMessage("EFDBF909811544DAAE4E876AA781E5DC");
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
    void printPageButton107(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 107");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton107", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("107");
        vars.removeMessage("107");
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
    void printPageButtonA3FE1F9892394386A49FB707AA50A0FA(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A3FE1F9892394386A49FB707AA50A0FA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA3FE1F9892394386A49FB707AA50A0FA", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A3FE1F9892394386A49FB707AA50A0FA");
        vars.removeMessage("A3FE1F9892394386A49FB707AA50A0FA");
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
    void printPageButton136(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 136");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton136", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("136");
        vars.removeMessage("136");
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
    void printPageButtonFB740AB61B0E42B198D2C88D3A0D0CE6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FB740AB61B0E42B198D2C88D3A0D0CE6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFB740AB61B0E42B198D2C88D3A0D0CE6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FB740AB61B0E42B198D2C88D3A0D0CE6");
        vars.removeMessage("FB740AB61B0E42B198D2C88D3A0D0CE6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DueDate", Utility.getContext(this, vars, "Duedate", ""));
    xmlDocument.setParameter("DueDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("FIN_Payment_Priority_ID", Utility.getContext(this, vars, "FIN_Payment_Priority_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "FIN_Payment_Priority_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "FIN_Payment_Priority_ID", ""));
    xmlDocument.setData("reportFIN_Payment_Priority_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton64B3FF29AC174F4B94538BD0A3CE1CD3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 64B3FF29AC174F4B94538BD0A3CE1CD3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton64B3FF29AC174F4B94538BD0A3CE1CD3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("64B3FF29AC174F4B94538BD0A3CE1CD3");
        vars.removeMessage("64B3FF29AC174F4B94538BD0A3CE1CD3");
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
    void printPageButton5BE14AA10165490A9ADEFB7532F7FA94(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 5BE14AA10165490A9ADEFB7532F7FA94");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton5BE14AA10165490A9ADEFB7532F7FA94", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("5BE14AA10165490A9ADEFB7532F7FA94");
        vars.removeMessage("5BE14AA10165490A9ADEFB7532F7FA94");
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
    void printPageButton58A9261BACEF45DDA526F29D8557272D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 58A9261BACEF45DDA526F29D8557272D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton58A9261BACEF45DDA526F29D8557272D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("58A9261BACEF45DDA526F29D8557272D");
        vars.removeMessage("58A9261BACEF45DDA526F29D8557272D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "EM_APRM_Process_BS", ""));
    comboTableData = new ComboTableData(vars, this, "17", "action", "EC75B6F5A9504DB6B3F3356EA85F15EE", "CA425689672A42D7BE2158EE41E44F94", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "EM_APRM_Process_BS", ""));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton15C8708DFC464C2D91286E59624FDD18(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 15C8708DFC464C2D91286E59624FDD18");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton15C8708DFC464C2D91286E59624FDD18", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("15C8708DFC464C2D91286E59624FDD18");
        vars.removeMessage("15C8708DFC464C2D91286E59624FDD18");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("C_GLItem_ID", ActionButtonSQLDefaultData.selectActP15C8708DFC464C2D91286E59624FDD18_C_GLItem_ID(this, Utility.getContext(this, vars, "C_GLItem_ID", "")));
    xmlDocument.setParameter("M_Product_ID", Utility.getContext(this, vars, "M_Product_ID", ""));
    xmlDocument.setParameter("C_BPartner_ID", Utility.getContext(this, vars, "C_Bpartner_ID", ""));
    xmlDocument.setParameter("C_Project_ID", Utility.getContext(this, vars, "C_Project_ID", ""));
    xmlDocument.setParameter("C_Campaign_ID", Utility.getContext(this, vars, "C_Campaign_ID", ""));
    xmlDocument.setParameter("C_Activity_ID", Utility.getContext(this, vars, "C_Activity_ID", ""));
    xmlDocument.setParameter("C_SalesRegion_ID", Utility.getContext(this, vars, "C_Salesregion_ID", ""));
    xmlDocument.setParameter("C_Costcenter_ID", Utility.getContext(this, vars, "C_Costcenter_ID", ""));
    xmlDocument.setParameter("User1_ID", Utility.getContext(this, vars, "User1_ID", ""));
    xmlDocument.setParameter("User2_ID", Utility.getContext(this, vars, "User2_ID", ""));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton017312F51139438A9665775E3B5392A1(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 017312F51139438A9665775E3B5392A1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton017312F51139438A9665775E3B5392A1", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("017312F51139438A9665775E3B5392A1");
        vars.removeMessage("017312F51139438A9665775E3B5392A1");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", "");
    comboTableData = new ComboTableData(vars, this, "17", "action", "798239EB069F41A9BA8EE040C63DDBBC", "3842B167CA6F44239C3357A721E3BA6A", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6255BE488882480599C81284B70CD9B3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6255BE488882480599C81284B70CD9B3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6255BE488882480599C81284B70CD9B3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6255BE488882480599C81284B70CD9B3");
        vars.removeMessage("6255BE488882480599C81284B70CD9B3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "EM_APRM_Process_Payment", ""));
    comboTableData = new ComboTableData(vars, this, "17", "action", "36972531DA994BB38ECB91993058282F", "575E470ABADB4C278132C957A78C47E3", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "EM_APRM_Process_Payment", ""));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton23D1B163EC0B41F790CE39BF01DA320E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 23D1B163EC0B41F790CE39BF01DA320E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton23D1B163EC0B41F790CE39BF01DA320E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    xmlDocument.setParameter("M_AttributeSetInstance_IDR", "");
    xmlDocument.setParameter("Returned", "");
    xmlDocument.setParameter("PriceStd", "");
    xmlDocument.setParameter("C_Tax_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Tax_ID", "", "299FA667CF374AC5ACC74739C3251134", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Tax_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Return_Reason_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Return_Reason_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Return_Reason_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6FBD65B0FDB74D1AB07F0EADF18D48AE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6FBD65B0FDB74D1AB07F0EADF18D48AE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6FBD65B0FDB74D1AB07F0EADF18D48AE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6FBD65B0FDB74D1AB07F0EADF18D48AE");
        vars.removeMessage("6FBD65B0FDB74D1AB07F0EADF18D48AE");
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
    void printPageButton9EB2228A60684C0DBEC12D5CD8D85218(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9EB2228A60684C0DBEC12D5CD8D85218");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9EB2228A60684C0DBEC12D5CD8D85218", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9EB2228A60684C0DBEC12D5CD8D85218");
        vars.removeMessage("9EB2228A60684C0DBEC12D5CD8D85218");
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
    void printPageButtonD85D5B5E368A49B1A6293BA4AE15F0F9(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D85D5B5E368A49B1A6293BA4AE15F0F9");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD85D5B5E368A49B1A6293BA4AE15F0F9", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D85D5B5E368A49B1A6293BA4AE15F0F9");
        vars.removeMessage("D85D5B5E368A49B1A6293BA4AE15F0F9");
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
    xmlDocument.setParameter("ExportAuditInfo", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF80808133362F6A013336781FCE0066(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF80808133362F6A013336781FCE0066");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF80808133362F6A013336781FCE0066", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF80808133362F6A013336781FCE0066");
        vars.removeMessage("FF80808133362F6A013336781FCE0066");
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
    void printPageButtonFF8081813219E68E013219ECFE930004(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8081813219E68E013219ECFE930004");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF8081813219E68E013219ECFE930004", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF8081813219E68E013219ECFE930004");
        vars.removeMessage("FF8081813219E68E013219ECFE930004");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("Value", ActionButtonSQLDefaultData.selectActPFF8081813219E68E013219ECFE930004_Value(this, Utility.getContext(this, vars, "MA_SEQUENCEPRODUCT_ID", "")));
    xmlDocument.setParameter("Name", ActionButtonSQLDefaultData.selectActPFF8081813219E68E013219ECFE930004_Name(this, Utility.getContext(this, vars, "MA_SEQUENCEPRODUCT_ID", "")));
    xmlDocument.setParameter("M_Product_Category_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Product_Category_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_Product_Category_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("Productiontype", "+");
    comboTableData = new ComboTableData(vars, this, "17", "Productiontype", "800034", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "+");
    xmlDocument.setData("reportProductiontype", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("Qty", "0");
    xmlDocument.setParameter("Copyattribute", "Y");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF808181324D007801324D2AE1130066(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF808181324D007801324D2AE1130066");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF808181324D007801324D2AE1130066", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF808181324D007801324D2AE1130066");
        vars.removeMessage("FF808181324D007801324D2AE1130066");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Date", "");
    xmlDocument.setParameter("Date_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("Starttime", "");
    xmlDocument.setParameter("Endtime", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF808181326CD80501326CE906D70042(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF808181326CD80501326CE906D70042");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF808181326CD80501326CE906D70042", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF808181326CD80501326CE906D70042");
        vars.removeMessage("FF808181326CD80501326CE906D70042");
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
    void printPageButtonFF80818132A4F6AD0132A573DD7A0021(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF80818132A4F6AD0132A573DD7A0021");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF80818132A4F6AD0132A573DD7A0021", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF80818132A4F6AD0132A573DD7A0021");
        vars.removeMessage("FF80818132A4F6AD0132A573DD7A0021");
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
    void printPageButton29D17F515727436DBCE32BC6CA28382B(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 29D17F515727436DBCE32BC6CA28382B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton29D17F515727436DBCE32BC6CA28382B", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("29D17F515727436DBCE32BC6CA28382B");
        vars.removeMessage("29D17F515727436DBCE32BC6CA28382B");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", "RV");
    comboTableData = new ComboTableData(vars, this, "17", "action", "66F2DCC800A34F94923444C29478E70A", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "RV");
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("paymentDate", "");
    xmlDocument.setParameter("paymentDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonDE1B382FDD2540199D223586F6E216D0(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DE1B382FDD2540199D223586F6E216D0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDE1B382FDD2540199D223586F6E216D0", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("DE1B382FDD2540199D223586F6E216D0");
        vars.removeMessage("DE1B382FDD2540199D223586F6E216D0");
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
    void printPageButtonD16966FBF9604A3D91A50DC83C6EA8E3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D16966FBF9604A3D91A50DC83C6EA8E3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD16966FBF9604A3D91A50DC83C6EA8E3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D16966FBF9604A3D91A50DC83C6EA8E3");
        vars.removeMessage("D16966FBF9604A3D91A50DC83C6EA8E3");
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
    void printPageButtonFF8080812E2F8EAE012E2F94CF470014(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8080812E2F8EAE012E2F94CF470014");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF8080812E2F8EAE012E2F94CF470014", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF8080812E2F8EAE012E2F94CF470014");
        vars.removeMessage("FF8080812E2F8EAE012E2F94CF470014");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "Process_Reconciliation", ""));
    comboTableData = new ComboTableData(vars, this, "17", "action", "FF8080812E443491012E443C053A001A", "FF808081332719060133271E5BB1001B", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "Process_Reconciliation", ""));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF8080812F348A97012F349DC24F0007(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8080812F348A97012F349DC24F0007");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF8080812F348A97012F349DC24F0007", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF8080812F348A97012F349DC24F0007");
        vars.removeMessage("FF8080812F348A97012F349DC24F0007");
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
    void printPageButton48FF95BA0B944EB981C9EA012AE215C6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 48FF95BA0B944EB981C9EA012AE215C6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton48FF95BA0B944EB981C9EA012AE215C6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("48FF95BA0B944EB981C9EA012AE215C6");
        vars.removeMessage("48FF95BA0B944EB981C9EA012AE215C6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_language", Utility.getContext(this, vars, "#ad_language", windowId));
    comboTableData = new ComboTableData(vars, this, "18", "ad_language", "106", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "#ad_language", windowId));
    xmlDocument.setData("reportad_language", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3C386BC12832466790E50F2F8C5EBD85(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3C386BC12832466790E50F2F8C5EBD85");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3C386BC12832466790E50F2F8C5EBD85", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3C386BC12832466790E50F2F8C5EBD85");
        vars.removeMessage("3C386BC12832466790E50F2F8C5EBD85");
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
    void printPageButton2DDE7D3618034C38A4462B7F3456C28D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2DDE7D3618034C38A4462B7F3456C28D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2DDE7D3618034C38A4462B7F3456C28D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2DDE7D3618034C38A4462B7F3456C28D");
        vars.removeMessage("2DDE7D3618034C38A4462B7F3456C28D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "EM_APRM_Process_BS", ""));
    comboTableData = new ComboTableData(vars, this, "17", "action", "EC75B6F5A9504DB6B3F3356EA85F15EE", "CA425689672A42D7BE2158EE41E44F94", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "EM_APRM_Process_BS", ""));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6BF16EFC772843AC9A17552AE0B26AB7(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6BF16EFC772843AC9A17552AE0B26AB7");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6BF16EFC772843AC9A17552AE0B26AB7", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6BF16EFC772843AC9A17552AE0B26AB7");
        vars.removeMessage("6BF16EFC772843AC9A17552AE0B26AB7");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "Process_Reconciliation", ""));
    comboTableData = new ComboTableData(vars, this, "17", "action", "FF8080812E443491012E443C053A001A", "FF808081332719060133271E5BB1001B", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "Process_Reconciliation", ""));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton58591E3E0F7648E4A09058E037CE49FC(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 58591E3E0F7648E4A09058E037CE49FC");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton58591E3E0F7648E4A09058E037CE49FC", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("58591E3E0F7648E4A09058E037CE49FC");
        vars.removeMessage("58591E3E0F7648E4A09058E037CE49FC");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_Product_Id", "");
    xmlDocument.setParameter("M_Product_IdR", "");
    xmlDocument.setParameter("M_CH_Value_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_CH_Value_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_CH_Value_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1E689A488E9F42EC9835A23FD845F91F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1E689A488E9F42EC9835A23FD845F91F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1E689A488E9F42EC9835A23FD845F91F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1E689A488E9F42EC9835A23FD845F91F");
        vars.removeMessage("1E689A488E9F42EC9835A23FD845F91F");
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
    void printPageButton7B0C4F8D4D1943FBAC960A860B5CFD2F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7B0C4F8D4D1943FBAC960A860B5CFD2F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7B0C4F8D4D1943FBAC960A860B5CFD2F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7B0C4F8D4D1943FBAC960A860B5CFD2F");
        vars.removeMessage("7B0C4F8D4D1943FBAC960A860B5CFD2F");
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
    void printPageButtonFF80818132902152013290D3E5D2001A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF80818132902152013290D3E5D2001A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF80818132902152013290D3E5D2001A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF80818132902152013290D3E5D2001A");
        vars.removeMessage("FF80818132902152013290D3E5D2001A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Export_Datasets", "");
    xmlDocument.setParameter("Export_Database", "");
    xmlDocument.setParameter("Export_Translation", "");
    xmlDocument.setParameter("Package_Module", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton692AEB22612F4A39B7912019D797FC2D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 692AEB22612F4A39B7912019D797FC2D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton692AEB22612F4A39B7912019D797FC2D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("692AEB22612F4A39B7912019D797FC2D");
        vars.removeMessage("692AEB22612F4A39B7912019D797FC2D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_org_id", Utility.getContext(this, vars, "AD_Org_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Org_ID", ""));
    xmlDocument.setData("reportad_org_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6A8A69B7F47E444DAACA0C142573D174(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6A8A69B7F47E444DAACA0C142573D174");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6A8A69B7F47E444DAACA0C142573D174", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6A8A69B7F47E444DAACA0C142573D174");
        vars.removeMessage("6A8A69B7F47E444DAACA0C142573D174");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_org_id", Utility.getContext(this, vars, "AD_Org_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Org_ID", ""));
    xmlDocument.setData("reportad_org_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton9128A01FDF49406BBE7C19479DAC4414(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9128A01FDF49406BBE7C19479DAC4414");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9128A01FDF49406BBE7C19479DAC4414", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportyear", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF3B32C87EC0A49EB938D28FB64F12E35(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F3B32C87EC0A49EB938D28FB64F12E35");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF3B32C87EC0A49EB938D28FB64F12E35", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F3B32C87EC0A49EB938D28FB64F12E35");
        vars.removeMessage("F3B32C87EC0A49EB938D28FB64F12E35");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Org_ID", Utility.getContext(this, vars, "AD_ORG_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_ORG_ID", ""));
    xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6DBBB591113D41AABDD74515D7E50480(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6DBBB591113D41AABDD74515D7E50480");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6DBBB591113D41AABDD74515D7E50480", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6DBBB591113D41AABDD74515D7E50480");
        vars.removeMessage("6DBBB591113D41AABDD74515D7E50480");
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
    void printPageButton4A4DB835730245B29CB86D2FF2A48BC2(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4A4DB835730245B29CB86D2FF2A48BC2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4A4DB835730245B29CB86D2FF2A48BC2", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    void printPageButtonB9DBEEFEE8B44DCA91F00D2D8E6E6435(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B9DBEEFEE8B44DCA91F00D2D8E6E6435");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB9DBEEFEE8B44DCA91F00D2D8E6E6435", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B9DBEEFEE8B44DCA91F00D2D8E6E6435");
        vars.removeMessage("B9DBEEFEE8B44DCA91F00D2D8E6E6435");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Org_Id", Utility.getContext(this, vars, "AD_Org_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Org_ID", ""));
    xmlDocument.setData("reportAD_Org_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3D671B238B16410581EADAAF1091F625(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3D671B238B16410581EADAAF1091F625");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3D671B238B16410581EADAAF1091F625", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3D671B238B16410581EADAAF1091F625");
        vars.removeMessage("3D671B238B16410581EADAAF1091F625");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Org_ID", Utility.getContext(this, vars, "AD_ORG_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_ORG_ID", ""));
    xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF5C44E6E034B42FF8865AC0417C799FE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F5C44E6E034B42FF8865AC0417C799FE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF5C44E6E034B42FF8865AC0417C799FE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Org_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Bpartner_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Bpartner_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Bpartner_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("M_PriceList_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_PriceList_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_PriceList_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_DocTypeInvoice_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_DocTypeInvoice_ID", "170", "8AC5A5643A6740A0A17457C00ED0DF41", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_DocTypeInvoice_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_DocTypeShipment_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_DocTypeShipment_ID", "170", "A0171DD95AE84C42A83AFE2228EF8310", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_DocTypeShipment_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DateOrdered", "");
    xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("M_Warehouse_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_Warehouse_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_PaymentTerm_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_PaymentTerm_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("FIN_PaymentMethod_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "FIN_PaymentMethod_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportFIN_PaymentMethod_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD5ED0F4EA8E44A4A827CCBBFA16433C3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D5ED0F4EA8E44A4A827CCBBFA16433C3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD5ED0F4EA8E44A4A827CCBBFA16433C3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D5ED0F4EA8E44A4A827CCBBFA16433C3");
        vars.removeMessage("D5ED0F4EA8E44A4A827CCBBFA16433C3");
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
    void printPageButtonDDCB419F5A964DFA99419C772268160C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DDCB419F5A964DFA99419C772268160C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDDCB419F5A964DFA99419C772268160C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("DDCB419F5A964DFA99419C772268160C");
        vars.removeMessage("DDCB419F5A964DFA99419C772268160C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_org_id", Utility.getContext(this, vars, "#AD_ORG_ID", windowId));
    comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "49DC1D6F086945AB82F84C66F5F13F16", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "#AD_ORG_ID", windowId));
    xmlDocument.setData("reportad_org_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DateAcct", DateTimeData.today(this));
    xmlDocument.setParameter("DateAcct_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("c_doctype_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "c_doctype_id", "", "102", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportc_doctype_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton926199266113402BAEEDAA0DA770E935(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 926199266113402BAEEDAA0DA770E935");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton926199266113402BAEEDAA0DA770E935", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("926199266113402BAEEDAA0DA770E935");
        vars.removeMessage("926199266113402BAEEDAA0DA770E935");
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
    void printPageButtonC4AEFC3C35174E5EB01DAF7187F3CCD0(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C4AEFC3C35174E5EB01DAF7187F3CCD0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonC4AEFC3C35174E5EB01DAF7187F3CCD0", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Org_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Bpartner_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Bpartner_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Bpartner_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("M_Pricelist_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Pricelist_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_Pricelist_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_DocType_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_DocType_Id", "", "8E0486B4548F4A0290AEA692B707F744", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_DocType_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DateOrdered", "");
    xmlDocument.setParameter("DateOrdered_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("DatePromised", "");
    xmlDocument.setParameter("DatePromised_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("M_Warehouse_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_Warehouse_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_PaymentTerm_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_PaymentTerm_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_PaymentTerm_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("FIN_PaymentMethod_Id", "");
    comboTableData = new ComboTableData(vars, this, "19", "FIN_PaymentMethod_Id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportFIN_PaymentMethod_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton337F8E5C1B1B4F2AA1F70B54B09EF5DA(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 337F8E5C1B1B4F2AA1F70B54B09EF5DA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton337F8E5C1B1B4F2AA1F70B54B09EF5DA", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportc_year_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton62525FB7D809411C98C7AE053BBC3676(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 62525FB7D809411C98C7AE053BBC3676");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton62525FB7D809411C98C7AE053BBC3676", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("62525FB7D809411C98C7AE053BBC3676");
        vars.removeMessage("62525FB7D809411C98C7AE053BBC3676");
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
    void printPageButtonC384DFB29934414B84ED4204CE8B8F10(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C384DFB29934414B84ED4204CE8B8F10");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonC384DFB29934414B84ED4204CE8B8F10", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("C384DFB29934414B84ED4204CE8B8F10");
        vars.removeMessage("C384DFB29934414B84ED4204CE8B8F10");
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
    void printPageButtonD9BE934098084CD0807FE5795258B763(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D9BE934098084CD0807FE5795258B763");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD9BE934098084CD0807FE5795258B763", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D9BE934098084CD0807FE5795258B763");
        vars.removeMessage("D9BE934098084CD0807FE5795258B763");
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
    void printPageButtonC6790B3A3DF24785B25CE94CA4F7CC29(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C6790B3A3DF24785B25CE94CA4F7CC29");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonC6790B3A3DF24785B25CE94CA4F7CC29", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("C6790B3A3DF24785B25CE94CA4F7CC29");
        vars.removeMessage("C6790B3A3DF24785B25CE94CA4F7CC29");
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
    void printPageButton1511B5CDC40B4F7CBD99EAB57F53AD5E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1511B5CDC40B4F7CBD99EAB57F53AD5E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1511B5CDC40B4F7CBD99EAB57F53AD5E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1511B5CDC40B4F7CBD99EAB57F53AD5E");
        vars.removeMessage("1511B5CDC40B4F7CBD99EAB57F53AD5E");
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
    void printPageButtonCCA562AC11004AE0AA35D2CA54CE8B3A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process CCA562AC11004AE0AA35D2CA54CE8B3A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonCCA562AC11004AE0AA35D2CA54CE8B3A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("CCA562AC11004AE0AA35D2CA54CE8B3A");
        vars.removeMessage("CCA562AC11004AE0AA35D2CA54CE8B3A");
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
    void printPageButtonC76AC67734AE4E359D28BB6AEFCE7FF1(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C76AC67734AE4E359D28BB6AEFCE7FF1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonC76AC67734AE4E359D28BB6AEFCE7FF1", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("C76AC67734AE4E359D28BB6AEFCE7FF1");
        vars.removeMessage("C76AC67734AE4E359D28BB6AEFCE7FF1");
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
    void printPageButtonCBCA1AE8F1F54A258EC83AA48D143DD5(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process CBCA1AE8F1F54A258EC83AA48D143DD5");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonCBCA1AE8F1F54A258EC83AA48D143DD5", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("CBCA1AE8F1F54A258EC83AA48D143DD5");
        vars.removeMessage("CBCA1AE8F1F54A258EC83AA48D143DD5");
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
    void printPageButton34C1DF029D234BA29FC8DBCBC4AC4B28(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 34C1DF029D234BA29FC8DBCBC4AC4B28");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton34C1DF029D234BA29FC8DBCBC4AC4B28", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("34C1DF029D234BA29FC8DBCBC4AC4B28");
        vars.removeMessage("34C1DF029D234BA29FC8DBCBC4AC4B28");
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
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Year_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Period_Id", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_Period_Id", "275", "C3AB162BAFED423CB8D5107902FD621B", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Period_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton4D1E97433C044E648DAE30B05D5AEB45(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4D1E97433C044E648DAE30B05D5AEB45");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4D1E97433C044E648DAE30B05D5AEB45", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("4D1E97433C044E648DAE30B05D5AEB45");
        vars.removeMessage("4D1E97433C044E648DAE30B05D5AEB45");
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
    void printPageButtonD06D41A3C96B42D484834CC1CAD85E5C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D06D41A3C96B42D484834CC1CAD85E5C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD06D41A3C96B42D484834CC1CAD85E5C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D06D41A3C96B42D484834CC1CAD85E5C");
        vars.removeMessage("D06D41A3C96B42D484834CC1CAD85E5C");
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
    void printPageButton547E4F03BBA5495EAF63CA3B31023C48(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 547E4F03BBA5495EAF63CA3B31023C48");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton547E4F03BBA5495EAF63CA3B31023C48", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("547E4F03BBA5495EAF63CA3B31023C48");
        vars.removeMessage("547E4F03BBA5495EAF63CA3B31023C48");
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
    void printPageButtonAE856EB10CD8444EB7555625E1F48631(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process AE856EB10CD8444EB7555625E1F48631");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonAE856EB10CD8444EB7555625E1F48631", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("AE856EB10CD8444EB7555625E1F48631");
        vars.removeMessage("AE856EB10CD8444EB7555625E1F48631");
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
    void printPageButton556420A9A9F746E887227511AF3079AA(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 556420A9A9F746E887227511AF3079AA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton556420A9A9F746E887227511AF3079AA", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("556420A9A9F746E887227511AF3079AA");
        vars.removeMessage("556420A9A9F746E887227511AF3079AA");
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
    void printPageButton0B9BC0C6675E48E083EF02153A8C6D4B(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0B9BC0C6675E48E083EF02153A8C6D4B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0B9BC0C6675E48E083EF02153A8C6D4B", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    void printPageButtonE17D7B1680EC44B5A93CC0E9B72DC119(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E17D7B1680EC44B5A93CC0E9B72DC119");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE17D7B1680EC44B5A93CC0E9B72DC119", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E17D7B1680EC44B5A93CC0E9B72DC119");
        vars.removeMessage("E17D7B1680EC44B5A93CC0E9B72DC119");
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
    void printPageButton03E56616272845EA9F662AD983F1595F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 03E56616272845EA9F662AD983F1595F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton03E56616272845EA9F662AD983F1595F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("03E56616272845EA9F662AD983F1595F");
        vars.removeMessage("03E56616272845EA9F662AD983F1595F");
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
    void printPageButtonBC22C0CB91014A6CB8AEA65E2460F003(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process BC22C0CB91014A6CB8AEA65E2460F003");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonBC22C0CB91014A6CB8AEA65E2460F003", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("BC22C0CB91014A6CB8AEA65E2460F003");
        vars.removeMessage("BC22C0CB91014A6CB8AEA65E2460F003");
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
    void printPageButton9E673FD989FB49779E7E9B3DFB58040D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9E673FD989FB49779E7E9B3DFB58040D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9E673FD989FB49779E7E9B3DFB58040D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9E673FD989FB49779E7E9B3DFB58040D");
        vars.removeMessage("9E673FD989FB49779E7E9B3DFB58040D");
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
    void printPageButton7D1FD4B0FCBB4CC698DD502C8BB4DC4F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7D1FD4B0FCBB4CC698DD502C8BB4DC4F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7D1FD4B0FCBB4CC698DD502C8BB4DC4F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7D1FD4B0FCBB4CC698DD502C8BB4DC4F");
        vars.removeMessage("7D1FD4B0FCBB4CC698DD502C8BB4DC4F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("hris_employee_candidate_id", "");
    comboTableData = new ComboTableData(vars, this, "19", "hris_employee_candidate_id", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reporthris_employee_candidate_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE2A09C3C7EE8431DAD0FBB6818D91DF4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E2A09C3C7EE8431DAD0FBB6818D91DF4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE2A09C3C7EE8431DAD0FBB6818D91DF4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E2A09C3C7EE8431DAD0FBB6818D91DF4");
        vars.removeMessage("E2A09C3C7EE8431DAD0FBB6818D91DF4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Executiondate", "");
    xmlDocument.setParameter("Executiondate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE2352DD134384FE595EF9170C787E661(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E2352DD134384FE595EF9170C787E661");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE2352DD134384FE595EF9170C787E661", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E2352DD134384FE595EF9170C787E661");
        vars.removeMessage("E2352DD134384FE595EF9170C787E661");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("paymentdate", "");
    xmlDocument.setParameter("paymentdate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB0FE443BA0AD453FAB67332729930F1C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B0FE443BA0AD453FAB67332729930F1C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB0FE443BA0AD453FAB67332729930F1C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B0FE443BA0AD453FAB67332729930F1C");
        vars.removeMessage("B0FE443BA0AD453FAB67332729930F1C");
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
    void printPageButtonABA6FABCB88F4CD4976DD34BBAA3F226(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process ABA6FABCB88F4CD4976DD34BBAA3F226");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonABA6FABCB88F4CD4976DD34BBAA3F226", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("ABA6FABCB88F4CD4976DD34BBAA3F226");
        vars.removeMessage("ABA6FABCB88F4CD4976DD34BBAA3F226");
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
    void printPageButton53A720D166714A208F2B43060E47A049(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 53A720D166714A208F2B43060E47A049");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton53A720D166714A208F2B43060E47A049", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("53A720D166714A208F2B43060E47A049");
        vars.removeMessage("53A720D166714A208F2B43060E47A049");
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
    void printPageButton4CD83C2A2470454DA1762133E0744572(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4CD83C2A2470454DA1762133E0744572");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4CD83C2A2470454DA1762133E0744572", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Year_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Period_Id", "");
    comboTableData = new ComboTableData(vars, this, "18", "C_Period_Id", "275", "C3AB162BAFED423CB8D5107902FD621B", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Period_Id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton0A218C05221F4AD8AF530FAC0548FB09(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0A218C05221F4AD8AF530FAC0548FB09");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0A218C05221F4AD8AF530FAC0548FB09", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("0A218C05221F4AD8AF530FAC0548FB09");
        vars.removeMessage("0A218C05221F4AD8AF530FAC0548FB09");
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
    void printPageButton4A6B6F8C719049AEAA7E266B6417E0E8(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4A6B6F8C719049AEAA7E266B6417E0E8");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4A6B6F8C719049AEAA7E266B6417E0E8", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("4A6B6F8C719049AEAA7E266B6417E0E8");
        vars.removeMessage("4A6B6F8C719049AEAA7E266B6417E0E8");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("TA_Shift_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "TA_Shift_ID", "7EB743AA6E0F4A93B3C7712CE44FF16A", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportTA_Shift_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE31AE2EA06364D918674A3BF38A2760F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E31AE2EA06364D918674A3BF38A2760F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE31AE2EA06364D918674A3BF38A2760F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E31AE2EA06364D918674A3BF38A2760F");
        vars.removeMessage("E31AE2EA06364D918674A3BF38A2760F");
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
    void printPageButton273FE57D0E3F4A2492CD8D4309F90DEA(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 273FE57D0E3F4A2492CD8D4309F90DEA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton273FE57D0E3F4A2492CD8D4309F90DEA", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("273FE57D0E3F4A2492CD8D4309F90DEA");
        vars.removeMessage("273FE57D0E3F4A2492CD8D4309F90DEA");
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
    void printPageButton23421D87CC8D4574A8735D67EFBD8C45(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 23421D87CC8D4574A8735D67EFBD8C45");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton23421D87CC8D4574A8735D67EFBD8C45", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("23421D87CC8D4574A8735D67EFBD8C45");
        vars.removeMessage("23421D87CC8D4574A8735D67EFBD8C45");
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
    void printPageButton9459F204EC91466F9B6E3972488CF40C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9459F204EC91466F9B6E3972488CF40C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9459F204EC91466F9B6E3972488CF40C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9459F204EC91466F9B6E3972488CF40C");
        vars.removeMessage("9459F204EC91466F9B6E3972488CF40C");
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
    void printPageButton3D364FBA35A74050BFAC48474C252161(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3D364FBA35A74050BFAC48474C252161");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3D364FBA35A74050BFAC48474C252161", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3D364FBA35A74050BFAC48474C252161");
        vars.removeMessage("3D364FBA35A74050BFAC48474C252161");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("employmentType", "");
    comboTableData = new ComboTableData(vars, this, "17", "employmentType", "57E8D00FA32E4E969062DC850D285AF1", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportemploymentType", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("pyr_payment_group", "");
    comboTableData = new ComboTableData(vars, this, "17", "pyr_payment_group", "008A541A83264A18B7600B23293C87C7", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportpyr_payment_group", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonCE7D3B05AC8141D495698BC8F4A6F673(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process CE7D3B05AC8141D495698BC8F4A6F673");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonCE7D3B05AC8141D495698BC8F4A6F673", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("CE7D3B05AC8141D495698BC8F4A6F673");
        vars.removeMessage("CE7D3B05AC8141D495698BC8F4A6F673");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("NewRoleName", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE068B05336BC48459E3BF33241C67B04(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E068B05336BC48459E3BF33241C67B04");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE068B05336BC48459E3BF33241C67B04", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E068B05336BC48459E3BF33241C67B04");
        vars.removeMessage("E068B05336BC48459E3BF33241C67B04");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("PRINTERADDRESS", Utility.getContext(this, vars, "RAWTEXTPRINTERADDRESS", ""));
    xmlDocument.setParameter("PRINTERPORT", Utility.getContext(this, vars, "RAWTEXTPRINTERPORT", ""));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton860D3F7046F64A21BE86B2FF390A6187(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 860D3F7046F64A21BE86B2FF390A6187");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton860D3F7046F64A21BE86B2FF390A6187", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("860D3F7046F64A21BE86B2FF390A6187");
        vars.removeMessage("860D3F7046F64A21BE86B2FF390A6187");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("nomorfakturpajak", "");
    xmlDocument.setParameter("lepasnomorfakturpajak", "N");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton5F401EDB7EA54E24A8F002749AEC8F14(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 5F401EDB7EA54E24A8F002749AEC8F14");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton5F401EDB7EA54E24A8F002749AEC8F14", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("5F401EDB7EA54E24A8F002749AEC8F14");
        vars.removeMessage("5F401EDB7EA54E24A8F002749AEC8F14");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Prefix", "");
    xmlDocument.setParameter("nomorawal", "");
    xmlDocument.setParameter("nomorakhir", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3270DC8DF5C143F280771F1200F2A11C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3270DC8DF5C143F280771F1200F2A11C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3270DC8DF5C143F280771F1200F2A11C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    void printPageButtonE93DD2C121914B4DA6A1252015B893DE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E93DD2C121914B4DA6A1252015B893DE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE93DD2C121914B4DA6A1252015B893DE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E93DD2C121914B4DA6A1252015B893DE");
        vars.removeMessage("E93DD2C121914B4DA6A1252015B893DE");
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
    void printPageButton9DB4D30BFC5144B9B431CB49DDE9270D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9DB4D30BFC5144B9B431CB49DDE9270D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9DB4D30BFC5144B9B431CB49DDE9270D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9DB4D30BFC5144B9B431CB49DDE9270D");
        vars.removeMessage("9DB4D30BFC5144B9B431CB49DDE9270D");
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
    void printPageButton0BDC2164ED3E48539FCEF4D306F29EFD(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0BDC2164ED3E48539FCEF4D306F29EFD");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0BDC2164ED3E48539FCEF4D306F29EFD", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("0BDC2164ED3E48539FCEF4D306F29EFD");
        vars.removeMessage("0BDC2164ED3E48539FCEF4D306F29EFD");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", "");
    comboTableData = new ComboTableData(vars, this, "17", "action", "798239EB069F41A9BA8EE040C63DDBBC", "3842B167CA6F44239C3357A721E3BA6A", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonCD7283DF804B449C97DA09446669EEEF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process CD7283DF804B449C97DA09446669EEEF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonCD7283DF804B449C97DA09446669EEEF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("CD7283DF804B449C97DA09446669EEEF");
        vars.removeMessage("CD7283DF804B449C97DA09446669EEEF");
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
    void printPageButton85601427EAEE401FA0250FF0A6DD62EF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 85601427EAEE401FA0250FF0A6DD62EF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton85601427EAEE401FA0250FF0A6DD62EF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("85601427EAEE401FA0250FF0A6DD62EF");
        vars.removeMessage("85601427EAEE401FA0250FF0A6DD62EF");
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
    void printPageButtonF68F2890E96D4D85A1DEF0274D105BCE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F68F2890E96D4D85A1DEF0274D105BCE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF68F2890E96D4D85A1DEF0274D105BCE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F68F2890E96D4D85A1DEF0274D105BCE");
        vars.removeMessage("F68F2890E96D4D85A1DEF0274D105BCE");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", "");
    comboTableData = new ComboTableData(vars, this, "17", "action", "F671DDEA466D41A996F605590CB545BC", "FAE0D7C8A9D84FAFAE3C10CD5DCE6E30", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton9B26EF017C244CD59C77BE203A2D14A8(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9B26EF017C244CD59C77BE203A2D14A8");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9B26EF017C244CD59C77BE203A2D14A8", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9B26EF017C244CD59C77BE203A2D14A8");
        vars.removeMessage("9B26EF017C244CD59C77BE203A2D14A8");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("ad_org_id", Utility.getContext(this, vars, "#AD_Org_ID", windowId));
    comboTableData = new ComboTableData(vars, this, "19", "ad_org_id", "", "49DC1D6F086945AB82F84C66F5F13F16", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "#AD_Org_ID", windowId));
    xmlDocument.setData("reportad_org_id", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonDC1DA3F261684A1D8EFA223CF2FFA25F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DC1DA3F261684A1D8EFA223CF2FFA25F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDC1DA3F261684A1D8EFA223CF2FFA25F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("DC1DA3F261684A1D8EFA223CF2FFA25F");
        vars.removeMessage("DC1DA3F261684A1D8EFA223CF2FFA25F");
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
    void printPageButton8C6B905EDA8742A0ABF4092902D54090(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8C6B905EDA8742A0ABF4092902D54090");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8C6B905EDA8742A0ABF4092902D54090", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8C6B905EDA8742A0ABF4092902D54090");
        vars.removeMessage("8C6B905EDA8742A0ABF4092902D54090");
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
    void printPageButtonAA3FEBF2A14147FAA0FE5AA479D8890F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process AA3FEBF2A14147FAA0FE5AA479D8890F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonAA3FEBF2A14147FAA0FE5AA479D8890F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
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
    void printPageButton45505DCA177040C0A428F34AD86570E6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 45505DCA177040C0A428F34AD86570E6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton45505DCA177040C0A428F34AD86570E6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("45505DCA177040C0A428F34AD86570E6");
        vars.removeMessage("45505DCA177040C0A428F34AD86570E6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("validfrom", DateTimeData.today(this));
    xmlDocument.setParameter("validfrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("validto", DateTimeData.today(this));
    xmlDocument.setParameter("validto_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }


    private void process7CB6B4D1ECCF4036B3F111D2CF11AADE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strmWarehouseId = vars.getStringParameter("inpmWarehouseId");
params.put("mWarehouseId", strmWarehouseId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.MRPPurchaseCreateReservations().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process970EAD9B846648A7AB1F0CCA5058356C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strname = vars.getStringParameter("inpname");
params.put("name", strname);
String strimportauditinfo = vars.getStringParameter("inpimportauditinfo", "N");
params.put("importauditinfo", strimportauditinfo);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.service.db.ImportClientProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process7EDBFEC35BDA4FF4AF05ED516CDAFB90(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.CreateCustomModule().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processABDFC8131D964936AD2EF7E0CED97FD9(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.UpdateActuals().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processEFDBF909811544DAAE4E876AA781E5DC(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.EndYearClose().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process107(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.materialmgmt.InventoryCountProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processA3FE1F9892394386A49FB707AA50A0FA(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strrecalculateprices = vars.getStringParameter("inprecalculateprices", "N");
params.put("recalculateprices", strrecalculateprices);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.ConvertQuotationIntoOrder().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process136(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.VerifyBOM().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFB740AB61B0E42B198D2C88D3A0D0CE6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strduedate = vars.getStringParameter("inpduedate");
params.put("duedate", strduedate);
String strfinPaymentPriorityId = vars.getStringParameter("inpfinPaymentPriorityId");
params.put("finPaymentPriorityId", strfinPaymentPriorityId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.UpdatePaymentPlan().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process64B3FF29AC174F4B94538BD0A3CE1CD3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.costing.CostingMigrationProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process5BE14AA10165490A9ADEFB7532F7FA94(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_AddPaymentFromJournal().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process58A9261BACEF45DDA526F29D8557272D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_BankStatementProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process15C8708DFC464C2D91286E59624FDD18(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strcGlitemId = vars.getStringParameter("inpcGlitemId");
params.put("cGlitemId", strcGlitemId);
String strmProductId = vars.getStringParameter("inpmProductId");
params.put("mProductId", strmProductId);
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
params.put("cBpartnerId", strcBpartnerId);
String strcProjectId = vars.getStringParameter("inpcProjectId");
params.put("cProjectId", strcProjectId);
String strcCampaignId = vars.getStringParameter("inpcCampaignId");
params.put("cCampaignId", strcCampaignId);
String strcActivityId = vars.getStringParameter("inpcActivityId");
params.put("cActivityId", strcActivityId);
String strcSalesregionId = vars.getStringParameter("inpcSalesregionId");
params.put("cSalesregionId", strcSalesregionId);
String strcCostcenterId = vars.getStringParameter("inpcCostcenterId");
params.put("cCostcenterId", strcCostcenterId);
String struser1Id = vars.getStringParameter("inpuser1Id");
params.put("user1Id", struser1Id);
String struser2Id = vars.getStringParameter("inpuser2Id");
params.put("user2Id", struser2Id);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_TransactionModify().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process017312F51139438A9665775E3B5392A1(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_DoubtfulDebtRunProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6255BE488882480599C81284B70CD9B3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_PaymentProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process23D1B163EC0B41F790CE39BF01DA320E(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
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
          new org.openbravo.erpCommon.ad_actionButton.RMInsertOrphanLine().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6FBD65B0FDB74D1AB07F0EADF18D48AE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.MRPManufacturingPlanProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9EB2228A60684C0DBEC12D5CD8D85218(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.CalculatePromotions().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD85D5B5E368A49B1A6293BA4AE15F0F9(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradClientId = vars.getStringParameter("inpadClientId");
params.put("adClientId", stradClientId);
String strexportauditinfo = vars.getStringParameter("inpexportauditinfo", "N");
params.put("exportauditinfo", strexportauditinfo);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.service.db.ExportClientProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF80808133362F6A013336781FCE0066(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.RMCreateInvoice().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF8081813219E68E013219ECFE930004(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strvalue = vars.getStringParameter("inpvalue");
params.put("value", strvalue);
String strname = vars.getStringParameter("inpname");
params.put("name", strname);
String strmProductCategoryId = vars.getStringParameter("inpmProductCategoryId");
params.put("mProductCategoryId", strmProductCategoryId);
String strproductiontype = vars.getStringParameter("inpproductiontype");
params.put("productiontype", strproductiontype);
String strqty = vars.getNumericParameter("inpqty");
params.put("qty", strqty);
String strcopyattribute = vars.getStringParameter("inpcopyattribute", "N");
params.put("copyattribute", strcopyattribute);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.SequenceProductCreate().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF808181324D007801324D2AE1130066(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strdate = vars.getStringParameter("inpdate");
params.put("date", strdate);
String strstarttime = vars.getStringParameter("inpstarttime");
params.put("starttime", strstarttime);
String strendtime = vars.getStringParameter("inpendtime");
params.put("endtime", strendtime);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.CreateWorkEffort().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF808181326CD80501326CE906D70042(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.ValidateWorkEffort_ProductionRun().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF80818132A4F6AD0132A573DD7A0021(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.CreateStandards().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process29D17F515727436DBCE32BC6CA28382B(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);
String strpaymentdate = vars.getStringParameter("inppaymentdate");
params.put("paymentdate", strpaymentdate);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_PaymentProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processDE1B382FDD2540199D223586F6E216D0(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_AddPaymentFromJournalLine().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD16966FBF9604A3D91A50DC83C6EA8E3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_PaymentProposalProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF8080812E2F8EAE012E2F94CF470014(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_ReconciliationProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF8080812F348A97012F349DC24F0007(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.ad_actionbutton.DeleteTransaction().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process48FF95BA0B944EB981C9EA012AE215C6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradLanguage = vars.getStringParameter("inpadLanguage");
params.put("adLanguage", stradLanguage);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.openbravo.gps.translationsmanager.CreateTranslationModule().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3C386BC12832466790E50F2F8C5EBD85(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.materialmgmt.VariantAutomaticGenerationProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process2DDE7D3618034C38A4462B7F3456C28D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_BankStatementProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6BF16EFC772843AC9A17552AE0B26AB7(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_ReconciliationProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process58591E3E0F7648E4A09058E037CE49FC(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strmProductId = vars.getStringParameter("inpmProductId");
params.put("mProductId", strmProductId);
String strmChValueId = vars.getStringParameter("inpmChValueId");
params.put("mChValueId", strmChValueId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.materialmgmt.VariantChDescUpdateProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1E689A488E9F42EC9835A23FD845F91F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.openbravo.gps.translationsmanager.ExportToXmlFiles().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process7B0C4F8D4D1943FBAC960A860B5CFD2F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.openbravo.gps.translationsmanager.ImportFromXmlFiles().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF80818132902152013290D3E5D2001A(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strexportDatasets = vars.getStringParameter("inpexportDatasets", "N");
params.put("exportDatasets", strexportDatasets);
String strexportDatabase = vars.getStringParameter("inpexportDatabase", "N");
params.put("exportDatabase", strexportDatabase);
String strexportTranslation = vars.getStringParameter("inpexportTranslation", "N");
params.put("exportTranslation", strexportTranslation);
String strpackageModule = vars.getStringParameter("inppackageModule", "N");
params.put("packageModule", strpackageModule);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.module.packager.Packager().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process692AEB22612F4A39B7912019D797FC2D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ImportOrder().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6A8A69B7F47E444DAACA0C142573D174(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ImportInvoice().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9128A01FDF49406BBE7C19479DAC4414(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stryear = vars.getStringParameter("inpyear");
params.put("year", stryear);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ResetDocumentSequence().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processF3B32C87EC0A49EB938D28FB64F12E35(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ImportInventory().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6DBBB591113D41AABDD74515D7E50480(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.CreateCustomerReimbursementGR().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process4A4DB835730245B29CB86D2FF2A48BC2(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strcOrderId = vars.getStringParameter("inpcOrderId");
params.put("cOrderId", strcOrderId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.CopySOtoRequisition().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB9DBEEFEE8B44DCA91F00D2D8E6E6435(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ImportShipment().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3D671B238B16410581EADAAF1091F625(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ImportWorkEffort().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processF5C44E6E034B42FF8865AC0417C799FE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
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
          new org.wirabumi.gen.oez.ad_process.CreateCustomerReimbursementPI().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD5ED0F4EA8E44A4A827CCBBFA16433C3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ProcessImportAsset().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processDDCB419F5A964DFA99419C772268160C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);
String strdateacct = vars.getStringParameter("inpdateacct");
params.put("dateacct", strdateacct);
String strcDoctypeId = vars.getStringParameter("inpcDoctypeId");
params.put("cDoctypeId", strcDoctypeId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.CreateUnrealizeGainLossForex().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process926199266113402BAEEDAA0DA770E935(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ProcessImportInvoice().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processC4AEFC3C35174E5EB01DAF7187F3CCD0(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
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
          new org.wirabumi.gen.oez.CreatePurchaseOrder().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process337F8E5C1B1B4F2AA1F70B54B09EF5DA(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strcYearId = vars.getStringParameter("inpcYearId");
params.put("cYearId", strcYearId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.GenerateMonth().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process62525FB7D809411C98C7AE053BBC3676(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.CreateContactData().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processC384DFB29934414B84ED4204CE8B8F10(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.ad_process.KPIAcc().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD9BE934098084CD0807FE5795258B763(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.payroll.ad_process.CalculateFormula().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processC6790B3A3DF24785B25CE94CA4F7CC29(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.overtime.ad_process.CalculateOvertime().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1511B5CDC40B4F7CBD99EAB57F53AD5E(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.UpdateContactData().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processCCA562AC11004AE0AA35D2CA54CE8B3A(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.CreateTransferContract().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processC76AC67734AE4E359D28BB6AEFCE7FF1(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.overtime.ad_process.OvertimeAmountCalculationID().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processCBCA1AE8F1F54A258EC83AA48D143DD5(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.termination.ad_process.CreateTerminationContract().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process34C1DF029D234BA29FC8DBCBC4AC4B28(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strcYearId = vars.getStringParameter("inpcYearId");
params.put("cYearId", strcYearId);
String strcPeriodId = vars.getStringParameter("inpcPeriodId");
params.put("cPeriodId", strcPeriodId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.ad_process.GenerateKpiMeasurement().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process4D1E97433C044E648DAE30B05D5AEB45(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.overtime.ad_process.AllCalculateOvertime().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD06D41A3C96B42D484834CC1CAD85E5C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.leave.ad_process.ProcessLeaveRequest().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process547E4F03BBA5495EAF63CA3B31023C48(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.CreateContactData().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processAE856EB10CD8444EB7555625E1F48631(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ImportBPBalance().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process556420A9A9F746E887227511AF3079AA(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.CreateHistoryTraining().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process0B9BC0C6675E48E083EF02153A8C6D4B(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.payroll.ad_process.CalculateFormula().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE17D7B1680EC44B5A93CC0E9B72DC119(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.payroll.ad_process.ImportInsidentilEarning().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process03E56616272845EA9F662AD983F1595F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.ad_process.RunClosure().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processBC22C0CB91014A6CB8AEA65E2460F003(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.CreateRetirementContract().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9E673FD989FB49779E7E9B3DFB58040D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.CreatePunisment().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process7D1FD4B0FCBB4CC698DD502C8BB4DC4F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strhrisEmployeeCandidateId = vars.getStringParameter("inphrisEmployeeCandidateId");
params.put("hrisEmployeeCandidateId", strhrisEmployeeCandidateId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.ImportEmployeeCandidates().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE2A09C3C7EE8431DAD0FBB6818D91DF4(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strexecutiondate = vars.getStringParameter("inpexecutiondate");
params.put("executiondate", strexecutiondate);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.ad_process.SetApplicableContract().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE2352DD134384FE595EF9170C787E661(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strpaymentdate = vars.getStringParameter("inppaymentdate");
params.put("paymentdate", strpaymentdate);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.payroll.ad_process.takehomepay().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB0FE443BA0AD453FAB67332729930F1C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.payroll.ad_process.ImportInsidentilDeduction().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processABA6FABCB88F4CD4976DD34BBAA3F226(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.PublishContract().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process53A720D166714A208F2B43060E47A049(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.CreateResignContract().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process4CD83C2A2470454DA1762133E0744572(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strcYearId = vars.getStringParameter("inpcYearId");
params.put("cYearId", strcYearId);
String strcPeriodId = vars.getStringParameter("inpcPeriodId");
params.put("cPeriodId", strcPeriodId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.ad_process.GenerateKpiMeasurement().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process0A218C05221F4AD8AF530FAC0548FB09(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.employee.master.Actionbutton.CreateEmployee().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process4A6B6F8C719049AEAA7E266B6417E0E8(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strtaShiftId = vars.getStringParameter("inptaShiftId");
params.put("taShiftId", strtaShiftId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.timeandattendance.erpCommon.ad_process.ImportCBpShift().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE31AE2EA06364D918674A3BF38A2760F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.timeandattendance.erpCommon.ad_process.ImportAttendance2().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process273FE57D0E3F4A2492CD8D4309F90DEA(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.timeandattendance.erpCommon.ad_process.ImportAttendance().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process23421D87CC8D4574A8735D67EFBD8C45(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.timeandattendance.erpCommon.ad_process.ImportShiftLine().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9459F204EC91466F9B6E3972488CF40C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.timeandattendance.erpCommon.ad_process.ImportShift().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3D364FBA35A74050BFAC48474C252161(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stremploymenttype = vars.getStringParameter("inpemploymenttype");
params.put("employmenttype", stremploymenttype);
String strpyrPaymentGroup = vars.getStringParameter("inppyrPaymentGroup");
params.put("pyrPaymentGroup", strpyrPaymentGroup);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.payroll.ad_process.GenerateSalaryPayment().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processCE7D3B05AC8141D495698BC8F4A6F673(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strnewrolename = vars.getStringParameter("inpnewrolename");
params.put("newrolename", strnewrolename);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.gps.copyrole.CopyRoleProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE068B05336BC48459E3BF33241C67B04(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strprinteraddress = vars.getStringParameter("inpprinteraddress");
params.put("printeraddress", strprinteraddress);
String strprinterport = vars.getStringParameter("inpprinterport");
params.put("printerport", strprinterport);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.cam.process.PrintAssetBarcode().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process860D3F7046F64A21BE86B2FF390A6187(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strnomorfakturpajak = vars.getStringParameter("inpnomorfakturpajak");
params.put("nomorfakturpajak", strnomorfakturpajak);
String strlepasnomorfakturpajak = vars.getStringParameter("inplepasnomorfakturpajak", "N");
params.put("lepasnomorfakturpajak", strlepasnomorfakturpajak);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.localization.id.community.ActionButton.AssignNomorFakturPajak().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process5F401EDB7EA54E24A8F002749AEC8F14(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strprefix = vars.getStringParameter("inpprefix");
params.put("prefix", strprefix);
String strnomorawal = vars.getNumericParameter("inpnomorawal");
params.put("nomorawal", strnomorawal);
String strnomorakhir = vars.getNumericParameter("inpnomorakhir");
params.put("nomorakhir", strnomorakhir);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.localization.id.community.ActionButton.GenerateNomorFakturPajak().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3270DC8DF5C143F280771F1200F2A11C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strrecalculateprices = vars.getStringParameter("inprecalculateprices", "N");
params.put("recalculateprices", strrecalculateprices);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ConvertQuotationIntoOrder().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE93DD2C121914B4DA6A1252015B893DE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ImportWorkRequirement().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9DB4D30BFC5144B9B431CB49DDE9270D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.KillSession().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process0BDC2164ED3E48539FCEF4D306F29EFD(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_DoubtfulDebtProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processCD7283DF804B449C97DA09446669EEEF(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.ProcessBatch().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process85601427EAEE401FA0250FF0A6DD62EF(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.assets.AssetLinearDepreciationMethodProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processF68F2890E96D4D85A1DEF0274D105BCE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_TransactionProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9B26EF017C244CD59C77BE203A2D14A8(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.gen.oez.ad_process.ImportGLJournal().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processDC1DA3F261684A1D8EFA223CF2FFA25F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.hris.timeandattendance.erpCommon.ad_process.ProcessImportEmployeeManualSchedule().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process8C6B905EDA8742A0ABF4092902D54090(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.cam.process.AssetAmortizationProcess().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processAA3FEBF2A14147FAA0FE5AA479D8890F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strdateordered = vars.getStringParameter("inpdateordered");
params.put("dateordered", strdateordered);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.wirabumi.projectbid.process.AwardBidAndCreatePO().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process45505DCA177040C0A428F34AD86570E6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strvalidfrom = vars.getStringParameter("inpvalidfrom");
params.put("validfrom", strvalidfrom);
String strvalidto = vars.getStringParameter("inpvalidto");
params.put("validto", strvalidto);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.infinite.idolmart.process.GenerateSPD().execute(pb);
          if((OBError)pb.getResult()!=null){
            myMessage = (OBError) pb.getResult();
            myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
            myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
          }
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error("Error calling process", ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }


  public String getServletInfo() {
    return "Servlet ActionButton_Responser. This Servlet was made by Wad constructor";
  } // end of the getServletInfo() method

  private void processButtonHelper(HttpServletRequest request, HttpServletResponse response, VariablesSecureApp vars, OBError myMessage) 
     throws ServletException, IOException {
      advisePopUp(request, response, myMessage.getType(), myMessage.getTitle(), myMessage.getMessage());
  }
}
