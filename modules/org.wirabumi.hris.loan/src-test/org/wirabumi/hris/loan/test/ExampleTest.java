package org.wirabumi.hris.loan.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.access.User;
import org.openbravo.test.base.BaseTest;
import org.openbravo.test.base.OBBaseTest;


public class ExampleTest extends OBBaseTest {

	@Test
	public void testUsersCount() {
	     setSystemAdministratorContext();
	     final OBCriteria<User> uCriteria = OBDal.getInstance().createCriteria(User.class);
	     final List<User> uList = uCriteria.list();
	     int userCount = 0;
	     for (User u : uList) {
	       if (u.getPassword().length() > 0)
	        userCount++;
	       
	     }
	     assertTrue(userCount > 0);
	     System.out.println("Total of users with password: " + (userCount));
	   }
	
}
