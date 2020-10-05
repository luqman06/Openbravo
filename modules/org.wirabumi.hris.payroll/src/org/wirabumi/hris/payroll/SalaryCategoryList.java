package org.wirabumi.hris.payroll;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.model.common.hcm.SalaryCategory;

public class SalaryCategoryList implements FieldProvider {
  private Hashtable<String, String> data = new Hashtable<String, String>();

  @Override
  public String getField(String fieldName) {
    return getData(fieldName);
  }

  public void setData(String name, String value) {
    if (name == null)
      return;
    if (this.data == null)
      this.data = new Hashtable<String, String>();
    if (value == null || value.equals(""))
      this.data.remove(name.toUpperCase());
    else
      this.data.put(name.toUpperCase(), value);
  }

  public String getData(String name) {
    return data.get(name.toUpperCase());
  }

  public static SalaryCategoryList[] SalaryCategory() {
    Vector<Object> vector = new Vector<Object>(0);
    final OBCriteria<SalaryCategory> salaryCategory = OBDal.getInstance().createCriteria(
        SalaryCategory.class);
    List<SalaryCategory> salCategory = salaryCategory.list();

    for (SalaryCategory salaryTemplateCategory : salCategory) {
      SalaryCategoryList objSalaryCategory = new SalaryCategoryList();
      String categoryID = salaryTemplateCategory.getId();
      String categoryName = salaryTemplateCategory.getName();
      objSalaryCategory.setData("ID", categoryID);
      objSalaryCategory.setData("NAME", categoryName);
      vector.addElement(objSalaryCategory);
    }
    SalaryCategoryList[] objSalaryCategory = new SalaryCategoryList[vector.size()];
    vector.copyInto(objSalaryCategory);
    return objSalaryCategory;
  }

}
