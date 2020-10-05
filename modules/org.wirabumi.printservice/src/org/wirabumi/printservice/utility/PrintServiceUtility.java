package org.wirabumi.printservice.utility;

import org.openbravo.model.common.geography.Location;

public final class PrintServiceUtility {
	public static String getLocationAddressFormat(Location location) {
		String address=null;
		StringBuilder supplierLocationSB = new StringBuilder();
		if (location.getAddressLine1()!=null)
			supplierLocationSB.append(location.getAddressLine1()).append(System.getProperty("line.separator"));
		if (location.getAddressLine2()!=null)
			supplierLocationSB.append(location.getAddressLine2()).append(System.getProperty("setting","")+", ");
		if (location.getPostalCode()!=null)
			supplierLocationSB.append(location.getPostalCode()).append(System.getProperty("setting","")+", ");
		if (location.getCityName()!=null)
			supplierLocationSB.append(location.getCityName()).append(System.getProperty("setting","")+", ");
		if (location.getCountry()!=null)
			supplierLocationSB.append(location.getCountry().getName()).append(System.getProperty("setting","")+", ");
		if (location.getRegion()!=null)
			supplierLocationSB.append(location.getRegion().getName());
		if (supplierLocationSB.length()>0)
			address=supplierLocationSB.toString();
		else
			address="";
		return address;
	}

}
