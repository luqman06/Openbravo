package org.wirabumi.meikarta.report;

import java.util.List;

public class PurchaseOrderBeanFactory {
	private List<PurchaseOrderBean> poBeanList;
	
	private PurchaseOrderBeanFactory(List<PurchaseOrderBean> pobeanList){
		this.poBeanList=pobeanList;
	}
	
	public static PurchaseOrderBeanFactory getInstance(List<PurchaseOrderBean> pobeanList){
		return new PurchaseOrderBeanFactory(pobeanList);
	}
	
	public List<PurchaseOrderBean> getPurchaseOrderBeanList(){
		return poBeanList;
	}

}
