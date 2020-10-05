package org.wirabumi.hris.payroll.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.wirabumi.hris.employee.master.data.hris_site;
import org.wirabumi.hris.payroll.pyr_deduction_param;

public class DeductionParamUtility {
	
	Logger log4j = Logger.getLogger(this.getClass());
	
	private final HashMap<EarningDeductionParameterBean, Double> deductionParamMap = new HashMap<EarningDeductionParameterBean, Double>();
	private final HashMap<EarningDeductionParameterBean, HashMap<Double, Double>> deductionParamPoinMap = 
			new HashMap<EarningDeductionParameterBean, HashMap<Double,Double>>();
	
	public DeductionParamUtility(Date effectivedate){
		super();
		
		//load multi layer
		OBCriteria<pyr_deduction_param> epC = OBDal.getInstance().createCriteria(pyr_deduction_param.class);
		epC.add(Restrictions.isNotNull(pyr_deduction_param.PROPERTY_MAXIMUMRANGE));
		epC.add(Restrictions.isNotNull(pyr_deduction_param.PROPERTY_MINIMUMRANGE));
		epC.add(Restrictions.le(pyr_deduction_param.PROPERTY_VALIDFROMDATE, effectivedate));
		epC.add(Restrictions.ge(pyr_deduction_param.PROPERTY_VALIDTODATE, effectivedate));
		epC.addOrderBy(pyr_deduction_param.PROPERTY_SEARCHKEY, true);
		epC.addOrderBy(pyr_deduction_param.PROPERTY_ECHELON, true);
		epC.addOrderBy(pyr_deduction_param.PROPERTY_POSITION, true);
		epC.addOrderBy(pyr_deduction_param.PROPERTY_HRISSITE, true);
		epC.addOrderBy(pyr_deduction_param.PROPERTY_MINIMUMRANGE, true);
		List<pyr_deduction_param> epL = epC.list();
		int i=0;
		EarningDeductionParameterBean oldedpb = null;
		for (pyr_deduction_param ec : epL){
			EarningDeductionParameterBean edpb = new EarningDeductionParameterBean(ec.getSearchKey(), ec.getEchelon(), ec.getPosition(), ec.getHrisSite());
			if (oldedpb==null || !oldedpb.equals(edpb))
				i=0;
			
			HashMap<Double, Double> layerMap = null;
			if (deductionParamPoinMap.containsKey(edpb))
				layerMap=deductionParamPoinMap.get(edpb);
			else
				layerMap=new HashMap<Double, Double>();
			
			if (i==0)
				layerMap.put(ec.getMinimumRange().doubleValue(), null);
			
			layerMap.put(ec.getMaximumRange().doubleValue(), ec.getParamAmount().doubleValue());
			
			deductionParamPoinMap.put(edpb, layerMap);
			i++;
			
			try {
				oldedpb=edpb.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		
		//load single layer
		epC = OBDal.getInstance().createCriteria(pyr_deduction_param.class);
		epC.add(Restrictions.isNull(pyr_deduction_param.PROPERTY_MAXIMUMRANGE));
		epC.add(Restrictions.isNull(pyr_deduction_param.PROPERTY_MINIMUMRANGE));
		epC.add(Restrictions.le(pyr_deduction_param.PROPERTY_VALIDFROMDATE, effectivedate));
		epC.add(Restrictions.ge(pyr_deduction_param.PROPERTY_VALIDTODATE, effectivedate));
		epC.addOrderBy(pyr_deduction_param.PROPERTY_SEARCHKEY, true);
		for (pyr_deduction_param ec : epC.list()){
			EarningDeductionParameterBean edpb = new EarningDeductionParameterBean(ec.getSearchKey(), ec.getEchelon(), ec.getPosition(), ec.getHrisSite());
			deductionParamMap.put(edpb, ec.getParamAmount().doubleValue());
		}
		
	}
	
	public double getValue(String paramCode, String echelon, String position, hris_site site){
		/*
ada 8 kemungkinnan, yaitu:
[3]
null		null				null

[2]
jabatan		null				null
null		jenjang jabatan		null
null		null				site

[1]
jabatan		jenjang jabatan		null
null		jenjang jabatan		site
jabatan		null				site

[0]
jabatan		jenjang jabatan		site

		 */
		
		//kemungkinan 1 dari 8
		EarningDeductionParameterBean edpb = new EarningDeductionParameterBean(paramCode, null, null, null);
		if (deductionParamMap.containsKey(edpb))
			return deductionParamMap.get(edpb);
		
		//kemungkinan 2 dari 8
		edpb = new EarningDeductionParameterBean(paramCode, echelon, null, null);
		if (deductionParamMap.containsKey(edpb))
			return deductionParamMap.get(edpb);
		
		//kemungkinan 3 dari 8
		edpb = new EarningDeductionParameterBean(paramCode, null, position, null);
		if (deductionParamMap.containsKey(edpb))
			return deductionParamMap.get(edpb);
		
		//kemungkinan 4 dari 8
		edpb = new EarningDeductionParameterBean(paramCode, null, null, site);
		if (deductionParamMap.containsKey(edpb))
			return deductionParamMap.get(edpb);
		
		//kemungkinan 5 dari 8
		edpb = new EarningDeductionParameterBean(paramCode, echelon, position, null);
		if (deductionParamMap.containsKey(edpb))
			return deductionParamMap.get(edpb);
		
		//kemungkinan 6 dari 8
		edpb = new EarningDeductionParameterBean(paramCode, null, position, site);
		if (deductionParamMap.containsKey(edpb))
			return deductionParamMap.get(edpb);
		
		//kemungkinan 7 dari 8
		edpb = new EarningDeductionParameterBean(paramCode, echelon, null, site);
		if (deductionParamMap.containsKey(edpb))
			return deductionParamMap.get(edpb);
		
		//kemungkinan 8 dari 8
		edpb = new EarningDeductionParameterBean(paramCode, echelon, position, site);
		if (deductionParamMap.containsKey(edpb))
			return deductionParamMap.get(edpb);
		else
			return 0.00;
		
	}
	
	public double getValue(String paramCode, String echelon, String position, hris_site site, double poin){
		HashMap<Double, Double> layerMap = null;
		
		//kemungkinan 1 dari 8
		EarningDeductionParameterBean edpb = new EarningDeductionParameterBean(paramCode, null, null, null);
		if (deductionParamPoinMap.containsKey(edpb))
			layerMap=deductionParamPoinMap.get(edpb);
		
		//kemungkinan 2 dari 8
		if (layerMap==null){
			edpb = new EarningDeductionParameterBean(paramCode, echelon, null, null);
			if (deductionParamPoinMap.containsKey(edpb))
				layerMap=deductionParamPoinMap.get(edpb);
		}
		
		
		//kemungkinan 3 dari 8
		if (layerMap==null){
			edpb = new EarningDeductionParameterBean(paramCode, null, position, null);
			if (deductionParamPoinMap.containsKey(edpb))
				layerMap=deductionParamPoinMap.get(edpb);
		}
		
		
		//kemungkinan 4 dari 8
		if (layerMap==null){
			edpb = new EarningDeductionParameterBean(paramCode, null, null, site);
			if (deductionParamPoinMap.containsKey(edpb))
				layerMap=deductionParamPoinMap.get(edpb);
		}
		
		
		//kemungkinan 5 dari 8
		if (layerMap==null){
			edpb = new EarningDeductionParameterBean(paramCode, echelon, position, null);
			if (deductionParamPoinMap.containsKey(edpb))
				layerMap=deductionParamPoinMap.get(edpb);
		}
		
		
		//kemungkinan 6 dari 8
		if (layerMap==null){
			edpb = new EarningDeductionParameterBean(paramCode, null, position, site);
			if (deductionParamPoinMap.containsKey(edpb))
				layerMap=deductionParamPoinMap.get(edpb);
		}
		
		
		//kemungkinan 7 dari 8
		if (layerMap==null){
			edpb = new EarningDeductionParameterBean(paramCode, echelon, null, site);
			if (deductionParamPoinMap.containsKey(edpb))
				layerMap=deductionParamPoinMap.get(edpb);
		}
		
		
		//kemungkinan 8 dari 8
		if (layerMap==null){
			edpb = new EarningDeductionParameterBean(paramCode, echelon, position, site);
			if (deductionParamPoinMap.containsKey(edpb))
				layerMap=deductionParamPoinMap.get(edpb);
		}
		
		//sudah dapat hashmap, tinggal di puter untuk mendapatkan amount
		Double output = null;
		if (layerMap!=null){
			Set<Double> layers = new TreeSet<Double>(layerMap.keySet()); 
			for (Double layer : layers){
				if (poin<layer){
					output = layerMap.get(layer);
					if (output==null){
						log4j.debug("deduction param "+paramCode+" has minimum range "+layer+" but poin "+poin);
						return 0.00;
					}
					else
						return output.doubleValue();
				}
			}
		}
		
		return 0.00;
			
	}

}
