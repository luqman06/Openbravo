package org.wirabumi.hris.employee.master.test;

import org.openbravo.dal.core.OBContext;
import org.openbravo.test.base.BaseTest;
import org.wirabumi.hris.employee.master.HrisUtility;

public class HrisUtilityTest extends BaseTest {
	
	public void testHrisUtility(){
		String userId="AB10E00B167844159CD56C48C861C43D"; //Zaien Aji Trahutomo
		String roleId="87C1059A4ADA4943A09CE08D57AE50B2"; //Wirabmi Software Admin
		String clientId="2340849EF79E4EE1BFA2CBC7CBB64716"; //Wirabumi Software
		String orgId="EA777102A60D46038C847AF8BED40585"; //Wirabumi Software		
		OBContext.setOBContext(userId, roleId, clientId,orgId);
		
		String employeeId="5682FC045E31404DA9790046BC4C7DE3";
		String supervisorId="D931E355865F4AEDA365BA4E168995DE";
		boolean isSupervisor=HrisUtility.isSupervisor(employeeId, supervisorId);
		assertTrue(isSupervisor);
		
		int supervisorLevel=HrisUtility.GetSupervisorLevel(employeeId, supervisorId);
		assertTrue(supervisorLevel>=1);
	}

}
