package org.wirabumi.hris.payroll.utility;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.wirabumi.hris.employee.master.data.hris_site;
import org.wirabumi.hris.payroll.pyr_earning_param;

public class EarnParamUtility {
  /**
   * 
   * 
   * @param searchKey
   * @param position
   * @return
   */
  public static double util(String searchKey, String position) {
    return utility(null, position, searchKey, null, null, null, null, null, null);
  }

  /**
   * 
   * @param echelon
   * @param position
   * @param searchKey
   * @return
   */
  public static double util(String echelon, String position, String searchKey) {
    return utility(echelon, position, searchKey, null, null, null, null, null, null);
  }

  /**
   * 
   * @param echelon
   * @param position
   * @param searchKey
   * @param level
   * @param site
   * @param minimumRange
   * @param maximumRange
   * @param validFrom
   * @param validTo
   * @return
   */
  public static double utility(String echelon, String position, String searchKey, String level,
      hris_site site, BigDecimal minimumRange, BigDecimal maximumRange, Date validFrom, Date validTo) {
    BigDecimal paramAmount = new BigDecimal(0);
    List<Object> param = new ArrayList<Object>();
    String str = "where ";

    if (!(echelon == null)) {
      if (str.length() == 6) {
        str += "echelon=? ";
        param.add(echelon);
      } else {
        str += "and echelon=? ";
        param.add(echelon);
      }
    }

    if (!(position == null)) {
      if (str.length() == 6) {
        str += "position=? ";
        param.add(position);
      } else {
        str += "and position=? ";
        param.add(position);
      }
    }

    if (!(searchKey == null)) {
      if (str.length() == 6) {
        str += "searchKey=? ";
        param.add(searchKey);
      } else {
        str += "and searchKey=? ";
        param.add(searchKey);
      }
    }

    if (!(minimumRange == null)) {
      if (str.length() == 6) {
        str += "minimumRange=? ";
        param.add(minimumRange);
      } else {
        str += "and minimumRange=? ";
        param.add(minimumRange);
      }
    }

    if (!(maximumRange == null)) {
      if (str.length() == 6) {
        str += "maximumRange=? ";
        param.add(maximumRange);
      } else {
        str += "and maximumRange=? ";
        param.add(maximumRange);
      }
    }

    if (!(validFrom == null)) {
      if (str.length() == 6) {
        str += "validFromDate=? ";
        param.add(validFrom);
      } else {
        str += "and validFromDate=? ";
        param.add(validFrom);
      }
    }

    if (!(validTo == null)) {
      if (str.length() == 6) {
        str += "validToDate=? ";
        param.add(validTo);
      } else {
        str += "and validToDate=? ";
        param.add(validTo);
      }
    }

    OBQuery<pyr_earning_param> parame = null;
    try {
      if (str.length() == 6) {
        return 0;
      } else {
        parame = OBDal.getInstance().createQuery(pyr_earning_param.class, str, param);
      }
      if (!(parame.list().size() <= 0)) {
        parame.setMaxResult(1);
      }
      List<pyr_earning_param> eparam = parame.list();
      for (pyr_earning_param earningParam : eparam) {
        paramAmount = earningParam.getParamAmount();

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return paramAmount.doubleValue();
  }
}
