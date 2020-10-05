package org.wirabumi.hris.timeandattendance.utility;

//
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.application.FilterExpression;
import org.openbravo.client.application.OBBindingsConstants;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.erpCommon.info.PriceListVersionFilterExpressionName;
import org.openbravo.erpCommon.utility.OBDateUtils;

public class AttendanceFilterExspresion implements FilterExpression {
  private Logger log = Logger.getLogger(PriceListVersionFilterExpressionName.class);
  private Map<String, String> requestMap;
  private HttpSession httpSession;
  private String windowId;
  VariablesSecureApp vars = new VariablesSecureApp(RequestContext.get().getRequest());

  @Override
  public String getExpression(Map<String, String> _requestMap) {
    requestMap = _requestMap;
    httpSession = RequestContext.get().getSession();
    windowId = requestMap.get(OBBindingsConstants.WINDOW_ID_PARAM);
    Calendar calendar = Calendar.getInstance();
    calendar.clear(calendar.DAY_OF_MONTH);
    Date date = calendar.getTime();
    String tanggal = OBDateUtils.formatDate(date, vars.getJavaDateFormat());
    // tanggal = "Since ".concat(tanggal);
    return tanggal;
  }
}
