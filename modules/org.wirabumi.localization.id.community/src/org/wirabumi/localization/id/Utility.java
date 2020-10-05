package org.wirabumi.localization.id;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.model.common.currency.ConversionRate;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.wirabumi.localization.id.community.data.IDTaxConversionRate;

public class Utility {
  Logger log4IdUtility = Logger.getLogger(this.getClass());

  public static String getDiferenceConversion(String Curency, String CurencyTo, String convDate,
      String sourceAmount) {
    String diferneceConversion = "";
    BigDecimal sourceAmt = new BigDecimal(sourceAmount);
    try {
      BigDecimal taxAmount = getTaxConvertedAmt(Curency, CurencyTo, convDate, sourceAmt);
      BigDecimal GenericAmount = getConvertedAmt(Curency, CurencyTo, convDate, sourceAmt);
      BigDecimal originalRate = getConversionRate(Curency, CurencyTo, convDate);
      BigDecimal taxRate = getTaxConversionRate(Curency, CurencyTo, convDate);
      BigDecimal diference = taxAmount.subtract(GenericAmount);

      diferneceConversion = String.valueOf(diference);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return diferneceConversion;
  }

  public static boolean isConversionByTaxRate(String cTaxID) {
    boolean isConvertibleTax = false;
    try {
      TaxRate taxRate = OBDal.getInstance().get(TaxRate.class, cTaxID);
      isConvertibleTax = taxRate.isIdTaxconversion();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return isConvertibleTax;
  }

  public static BigDecimal getTaxConvertedAmt(String Curency, String CurencyTo, String convDate,
      BigDecimal sourceAmt) {
    BigDecimal convertedAmount = new BigDecimal(0);
    try {
      BigDecimal conversionRate = getTaxConversionRate(Curency, CurencyTo, convDate);
      convertedAmount = sourceAmt.multiply(conversionRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return convertedAmount;
  }

  public static BigDecimal getConvertedAmt(String Curency, String CurencyTo, String convDate,
      BigDecimal sourceAmt) {
    BigDecimal convertedAmount = new BigDecimal(0);
    try {
      BigDecimal conversionRate = getConversionRate(Curency, CurencyTo, convDate);
      convertedAmount = sourceAmt.multiply(conversionRate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return convertedAmount;
  }

  public static BigDecimal getTaxConversionRate(String Curency, String CurencyTo, String convDate) {
    BigDecimal conversionRate = new BigDecimal(0);
    Date conversionDate = null;
    Currency sourceCurrency = null;
    Currency destCurrency = null;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);

    String strDateFormat = OBPropertiesProvider.getInstance().getOpenbravoProperties()
        .getProperty("dateFormat.java");
    final SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);

    try {
      conversionDate = (convDate == null) ? null : dateFormat.parse(convDate);
      conversionDate = (conversionDate == null) ? calendar.getTime() : conversionDate;
      sourceCurrency = OBDal.getInstance().get(Currency.class, Curency);
      destCurrency = OBDal.getInstance().get(Currency.class, CurencyTo);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }

    try {
      String WhereClause = "where currency= ?" + " and toCurrency = ?"
          + " and ? between validFromDate and validToDate";
      List<Object> paramaters = new ArrayList<Object>();
      paramaters.add(sourceCurrency);
      paramaters.add(destCurrency);
      paramaters.add(conversionDate);
      OBQuery<IDTaxConversionRate> tabConversion = OBDal.getInstance().createQuery(
          IDTaxConversionRate.class, WhereClause);
      tabConversion.setParameters(paramaters);
      if (tabConversion.list().size() > 0) {
        IDTaxConversionRate taxRate = tabConversion.list().get(0);
        conversionRate = taxRate.getMultipleRateBy();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conversionRate;
  }

  public static BigDecimal getConversionRate(String Curency, String CurencyTo, String convDate) {
    BigDecimal conversionRate = new BigDecimal(0);
    Date conversionDate = null;
    Currency sourceCurrency = null;
    Currency destCurrency = null;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);

    String strDateFormat = OBPropertiesProvider.getInstance().getOpenbravoProperties()
        .getProperty("dateFormat.java");
    final SimpleDateFormat dateFormat = new SimpleDateFormat(strDateFormat);

    try {
      conversionDate = (convDate == null) ? null : dateFormat.parse(convDate);
      conversionDate = (conversionDate == null) ? calendar.getTime() : conversionDate;
      sourceCurrency = OBDal.getInstance().get(Currency.class, Curency);
      destCurrency = OBDal.getInstance().get(Currency.class, CurencyTo);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }

    try {
      String WhereClause = "where currency= ?" + " and toCurrency = ?"
          + " and ? between validFromDate and validToDate";
      List<Object> paramaters = new ArrayList<Object>();
      paramaters.add(sourceCurrency);
      paramaters.add(destCurrency);
      paramaters.add(conversionDate);
      OBQuery<ConversionRate> tabConversion = OBDal.getInstance().createQuery(ConversionRate.class,
          WhereClause);
      tabConversion.setParameters(paramaters);
      if (tabConversion.list().size() > 0) {
        ConversionRate taxRate = tabConversion.list().get(0);
        conversionRate = taxRate.getMultipleRateBy();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conversionRate;
  }
}
