package org.infinite.expensebudget.utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Restrictions;
import org.infinite.expensebudget.RealizedBudgetUsage;
import org.infinite.expensebudget.UnrealizedBudgetUsage;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.TreeUtility;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductCategory;
import org.openbravo.model.financialmgmt.accounting.BudgetLine;
import org.openbravo.model.financialmgmt.accounting.Costcenter;
import org.openbravo.model.financialmgmt.calendar.Calendar;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.calendar.Year;
import org.openbravo.model.procurement.RequisitionLine;

public class ExpenseBudgetUtility {
	
	public static BudgetLine getMatchedBudget(RequisitionLine requisitionLine, List<BudgetLine> openbudgetList) {
		/*
		 * empat (4) dimensi yang harus match:
		 * 1. organisasi, kalau ga ada di org, maka cari di parent
		 * 2. cost center, null = all
		 * 3. product group, null = all
		 * 4. product, null = all
		 * 5. period, null = all
		 * 
		 * cari purchase budget, kumpulkan dalam list
		 * shadow copy list tersebut
		 * kemudian loop 1 per 1:
		 * 	cek organisasi, jika tidak match, hapus
		 * 	cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi organisasi
		 *  cek cost center, jika tidak match, hapus
		 *  cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi costcenter
		 *  cek product group, jika tidak match, hapus
		 *  cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi product group
		 *  cek product, jika tidak match, hapus
		 *  cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi product
		 *  cek period, jika tidak match, hapus
		 *  cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi period
		 *  
		 */
		
		//shadow copy list tersebut
		List<BudgetLine> matchedBudget = new ArrayList<BudgetLine>();
		for (BudgetLine budgetLine : openbudgetList) {
			matchedBudget.add(budgetLine);
		}
		
		
		
		final String[] daftarDimensi = new String[5];
		daftarDimensi[0]="organisasi";
		daftarDimensi[1]="cost center";
		daftarDimensi[2]="product group";
		daftarDimensi[3]="product";
		daftarDimensi[4]="period";
		
		for (int i=0; i<daftarDimensi.length; i++) {
			List<BudgetLine> unmatchedBudget = new ArrayList<BudgetLine>();
			for (BudgetLine budgetLine : matchedBudget) {
				if (i==0) { //organisasi
					//cek organisasi, jika tidak match, hapus
					boolean isOrganisasiValid = cekOrganisasi(budgetLine.getOrganization(), requisitionLine.getOrganization());
					if (!isOrganisasiValid)
						unmatchedBudget.add(budgetLine);
					
				} else if (i==1) { //cost center
					// jika 2 2 nya null, maka cocok, continue
					Costcenter requestcostcenter = requisitionLine.getRequisition().getPbidCostcenter();
					Costcenter budgetcostcenter = budgetLine.getCostcenter();
					boolean isvalid = validasiBudgetDimensi(requestcostcenter, budgetcostcenter);
					if (!isvalid)
						unmatchedBudget.add(budgetLine);
				} else if (i==2) { //product group
					// jika 2 2 nya null, maka cocok, continue
					ProductCategory requestpgroup = requisitionLine.getProduct().getProductCategory();
					ProductCategory budgetpgroup = budgetLine.getProductCategory();
					boolean isvalid = validasiBudgetDimensi(requestpgroup, budgetpgroup);
					if (!isvalid)
						unmatchedBudget.add(budgetLine);
				} else if (i==3) { //product
					// jika 2 2 nya null, maka cocok, continue
					Product requestpproduct = requisitionLine.getProduct();
					Product budgetpproduct = budgetLine.getProduct();
					boolean isvalid = validasiBudgetDimensi(requestpproduct, budgetpproduct);
					if (!isvalid)
						unmatchedBudget.add(budgetLine);
				} else if (i==4) { //period
					// jika 2 2 nya null, maka cocok, continue
					Period requestperiod = getPeriodByDate(requisitionLine.getOrganization(), requisitionLine.getRequisition().getCreationDate());
					Period budgetperiod = budgetLine.getPeriod();
					boolean isvalid = validasiBudgetDimensi(requestperiod, budgetperiod);
					if (!isvalid)
						unmatchedBudget.add(budgetLine);
				}
			}
			
			//hapus unmatched budget
			for (BudgetLine budgetLine : unmatchedBudget)
				matchedBudget.remove(budgetLine);
			if (matchedBudget.size()==0)
				throw new OBException("gagal mendapatkan budget dengan dimensi "+daftarDimensi[i]+" yang cocok.");
			
			unmatchedBudget.clear();
		}
		
		//return single budget line that match
		if (matchedBudget.size()>0)
			return matchedBudget.get(0);
		else
			return null;
	}
	
	public static BudgetLine getMatchedBudget(OrderLine orderLine, List<BudgetLine> openbudgetList) {
		/*
		 * empat (4) dimensi yang harus match:
		 * 1. organisasi, kalau ga ada di org, maka cari di parent
		 * 2. cost center, null = all
		 * 3. product group, null = all
		 * 4. product, null = all
		 * 5. period, null = all
		 * 
		 * cari purchase budget, kumpulkan dalam list
		 * shadow copy list tersebut
		 * kemudian loop 1 per 1:
		 * 	cek organisasi, jika tidak match, hapus
		 * 	cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi organisasi
		 *  cek cost center, jika tidak match, hapus
		 *  cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi costcenter
		 *  cek product group, jika tidak match, hapus
		 *  cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi product group
		 *  cek product, jika tidak match, hapus
		 *  cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi product
		 *  cek period, jika tidak match, hapus
		 *  cek panjang list, jika nol >> tampilkan error message: tidak ada budgetline yang cocok pada dimensi period
		 *  
		 */
		
		//shadow copy list tersebut
		List<BudgetLine> matchedBudget = new ArrayList<BudgetLine>();
		for (BudgetLine budgetLine : openbudgetList) {
			matchedBudget.add(budgetLine);
		}
		
		
		
		final String[] daftarDimensi = new String[5];
		daftarDimensi[0]="organisasi";
		daftarDimensi[1]="cost center";
		daftarDimensi[2]="product group";
		daftarDimensi[3]="product";
		daftarDimensi[4]="period";
		
		for (int i=0; i<daftarDimensi.length; i++) {
			List<BudgetLine> unmatchedBudget = new ArrayList<BudgetLine>();
			for (BudgetLine budgetLine : matchedBudget) {
				if (i==0) { //organisasi
					//cek organisasi, jika tidak match, hapus
					boolean isOrganisasiValid = cekOrganisasi(budgetLine.getOrganization(), orderLine.getOrganization());
					if (!isOrganisasiValid)
						unmatchedBudget.add(budgetLine);
					
				} else if (i==1) { //cost center
					// jika 2 2 nya null, maka cocok, continue
					Costcenter requestcostcenter = orderLine.getCostcenter();
					if (requestcostcenter==null)
						requestcostcenter = orderLine.getSalesOrder().getCostcenter();
					Costcenter budgetcostcenter = budgetLine.getCostcenter();
					boolean isvalid = validasiBudgetDimensi(requestcostcenter, budgetcostcenter);
					if (!isvalid)
						unmatchedBudget.add(budgetLine);
				} else if (i==2) { //product group
					// jika 2 2 nya null, maka cocok, continue
					ProductCategory requestpgroup = orderLine.getProduct().getProductCategory();
					ProductCategory budgetpgroup = budgetLine.getProductCategory();
					boolean isvalid = validasiBudgetDimensi(requestpgroup, budgetpgroup);
					if (!isvalid)
						unmatchedBudget.add(budgetLine);
				} else if (i==3) { //product
					// jika 2 2 nya null, maka cocok, continue
					Product requestpproduct = orderLine.getProduct();
					Product budgetpproduct = budgetLine.getProduct();
					boolean isvalid = validasiBudgetDimensi(requestpproduct, budgetpproduct);
					if (!isvalid)
						unmatchedBudget.add(budgetLine);
				} else if (i==4) { //period
					// jika 2 2 nya null, maka cocok, continue
					Period requestperiod = getPeriodByDate(orderLine.getOrganization(), orderLine.getSalesOrder().getOrderDate());
					Period budgetperiod = budgetLine.getPeriod();
					boolean isvalid = validasiBudgetDimensi(requestperiod, budgetperiod);
					if (!isvalid)
						unmatchedBudget.add(budgetLine);
				}
			}
			
			//hapus unmatched budget
			for (BudgetLine budgetLine : unmatchedBudget)
				matchedBudget.remove(budgetLine);
			if (matchedBudget.size()==0)
				throw new OBException("gagal mendapatkan budget dengan dimensi "+daftarDimensi[i]+" yang cocok.");
			
			unmatchedBudget.clear();
		}
		
		//return single budget line that match
		if (matchedBudget.size()>0)
			return matchedBudget.get(0);
		else
			return null;
	}
	
	private static boolean validasiBudgetDimensi(BaseOBObject transDim, BaseOBObject budgetDim) {
		if (budgetDim==null && transDim==null)
			return true;
		
		//jika 2 2 nya tidak null, maka di samakan, jika sama maka cocok, continue;
		if (budgetDim!=null && transDim!=null)
			if (budgetDim.equals(transDim))
				return true;
		
		//jika budget null, tapi request ada, maka cocok sebab null untuk semua costcenter
		if (budgetDim==null && transDim!=null)
			return true;
		
		//jika budget tidak null, tapi request null, maka tidak cocok, sebab itu budget dimensi lain
		//ini kemungkinan ke 4 (terakhir), sehingga tidak perlu di cek. lansung saja nyatakan tidak cocok
		return false;
	}

	private static Period getPeriodByDate(Organization org, Date date) {
		/*
		 * dari requisition dapat org
		 * dari org dapat calendar owner
		 * dari calendar owner, dapat calendar
		 * dari calendar, dapat fiscal year
		 * dari requisition dapat tanggal create
		 * dari fiscal year dan tanggal create, maka dapat period
		 */
		
		Calendar fiscalcalendar = getCalendarByOrg(org.getId());
		Year fiscalyear = getFiscalYearByDate(fiscalcalendar, date);
		Period period = getPeriodByDate(fiscalyear, date);
		
		return period;
	}

	private static Period getPeriodByDate(Year fiscalyear, Date date) {
		
		OBCriteria<Period> periodC = OBDal.getInstance().createCriteria(Period.class);
		periodC.add(Restrictions.eq(Period.PROPERTY_YEAR, fiscalyear));
		periodC.add(Restrictions.le(Period.PROPERTY_STARTINGDATE, date));
		periodC.add(Restrictions.ge(Period.PROPERTY_ENDINGDATE, date));
		for (Period period : periodC.list())
			return period;
		
		throw new OBException("can not find period for date "+date);
	}

	private static Year getFiscalYearByDate(Calendar fiscalcalendar, Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		int y = cal.get(java.util.Calendar.YEAR);
		String strYear = new Integer(y).toString();
		
		OBCriteria<Year> yearC = OBDal.getInstance().createCriteria(Year.class);
		yearC.add(Restrictions.eq(Year.PROPERTY_CALENDAR, fiscalcalendar));
		yearC.add(Restrictions.eq(Year.PROPERTY_FISCALYEAR, strYear));
		
		for (Year year : yearC.list())
			return year;
		
		throw new OBException("can not find fiscal year by date "+date);
			
	}

	private static Calendar getCalendarByOrg(String orgID) {
		TreeUtility treeUtility = new TreeUtility();
		Set<String> orgNaturalTreeSet = treeUtility.getNaturalTree(orgID, "OO");
		for (String orgId : orgNaturalTreeSet) {
			Organization org = OBDal.getInstance().get(Organization.class, orgId);
			if (org.getCalendar()==null)
				continue;
			return org.getCalendar();
		}
		
		throw new OBException("can not find calendar owner organization for organization id "+orgID);
	}

	private static boolean cekOrganisasi(Organization orgInduk, Organization orgAnak) {
		String strOrgInduk = orgInduk.getId();
		String strOrgAnak = orgAnak.getId();
		
		if (strOrgInduk.equals(strOrgAnak))
			return true;
		
		TreeUtility treeUtility = new TreeUtility();
		Set<String> orgAnakSet = treeUtility.getChildNode(strOrgInduk, "OO");
		for (String orgAnakID : orgAnakSet) {
			if (orgAnakID.equals(strOrgAnak))
				return true;
		}
		
		return false;
	}

	public static BudgetLine getMatchedBudget(OrderLine requisitionLine) {
		
		return null;
	}
	
	public static void applyUnrealizedBudgetUsage(BudgetLine budgetLine, RequisitionLine requisitionLine) {
		BigDecimal unrealizedbudgetusage = budgetLine.getEbUnrealizedusage();
		BigDecimal unitprice = requisitionLine.getUnitPrice();
		if (unitprice==null)
			unitprice=BigDecimal.ZERO;
		BigDecimal quantity = requisitionLine.getQuantity();
		if (quantity==null)
			quantity=BigDecimal.ZERO;
		BigDecimal requestLineAmt = quantity.multiply(unitprice);
		BigDecimal akumulasibudgetusage = unrealizedbudgetusage.add(requestLineAmt);
		
		budgetLine.setEbUnrealizedusage(akumulasibudgetusage);
		OBDal.getInstance().save(budgetLine);
		
		UnrealizedBudgetUsage ubu = OBProvider.getInstance().get(UnrealizedBudgetUsage.class);
		ubu.setOrganization(budgetLine.getOrganization());
		ubu.setBudgetLine(budgetLine);
		ubu.setRequisitionLine(requisitionLine);
		ubu.setAmount(requestLineAmt);
		OBDal.getInstance().save(ubu);
		
	}
	
	public static void applyRealizedBudgetUsage(BudgetLine budgetLine, OrderLine orderLine) {
		BigDecimal realizedbudgetusage = budgetLine.getEbRealizedusage();
		BigDecimal unitprice = orderLine.getUnitPrice();
		if (unitprice==null)
			unitprice=BigDecimal.ZERO;
		BigDecimal quantity = orderLine.getOrderedQuantity();
		if (quantity==null)
			quantity=BigDecimal.ZERO;
		BigDecimal poLineAmt = quantity.multiply(unitprice);
		BigDecimal akumulasibudgetusage = realizedbudgetusage.add(poLineAmt);
		
		budgetLine.setEbRealizedusage(akumulasibudgetusage);
		OBDal.getInstance().save(budgetLine);
		
		RealizedBudgetUsage rbu = OBProvider.getInstance().get(RealizedBudgetUsage.class);
		rbu.setOrganization(budgetLine.getOrganization());
		rbu.setBudgetLine(budgetLine);
		rbu.setSalesOrderLine(orderLine);
		rbu.setAmount(poLineAmt);
		OBDal.getInstance().save(rbu);
		
	}

}


