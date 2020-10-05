package org.wirabumi.gen.oez.event;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.base.model.Property;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.currency.ConversionRate;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.service.db.DbUtility;
import org.wirabumi.gen.oez.porting.GoodsMovementActionHandler;

public class DocumentRoutingHandler extends BaseActionHandler {
	final static Logger log4jDocRouting = Logger.getLogger(DocumentRoutingHandler.class);
	final String referenceId = "135"; //id untuk ad_reference All_Document Action

	ConnectionProvider connectionProvider = new DalConnectionProvider();

	@Override
	protected JSONObject execute(Map<String, Object> parameters, String content) {
		JSONObject response = new JSONObject();
		HttpServletRequest request = RequestContext.get().getRequest();
		VariablesSecureApp vars = new VariablesSecureApp(request);
		try {
			final JSONObject jsonData = new JSONObject(content);
			final String action = jsonData.getString("action");
			final String adTabId = jsonData.getString("adTabId");
			final String windowId = jsonData.getString("windowId");
				
			String doc_status_from = jsonData.getString("doc_status_from");
			final JSONArray RecordList = jsonData.getJSONArray("recordIdList");
			List<String> recordIdList = DocumentRoutingHandlerServer.parseJSON(RecordList);

			// cek apakah dalam record yang dicentang ada perbedaan docSatus, jika Ya maka exception
			OBContext.setAdminMode();
			doc_status_from = getCurrentStatus(windowId, adTabId, referenceId, recordIdList);
			if(doc_status_from == null || doc_status_from.equals("")){
				String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
						"@OEZ_MultipleDocStatus@");
				throw new Exception(message);
			}

			//set docAction, jika tidak terdefinisi di role | document routing, maka exception karena dianggap tidak memiliki otorisasi
			String doc_status_to=null;
			if(!action.equals("OpenPopupParamater")){
				doc_status_to=action;
			}
			if (action.equals("OpenPopupParamater")) {
				JSONObject comboItem=getActionComboBox(windowId, adTabId,referenceId,  recordIdList, vars,doc_status_from);
				if (comboItem!=null) {
					response.put("actionComboBox", comboItem);
				} else {
					String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),
							"@oez_documentapprovaldenied@ ");
					throw new Exception(message);
				}
			} else {
				DocumentRoutingHandlerServer.doRouting(windowId, adTabId, recordIdList, doc_status_to, doc_status_from, vars);
				String actionName = DocumentRoutingHandlerServer.getDocumentStatusName(referenceId, action);
				String message = Utility.parseTranslation(connectionProvider, vars, vars.getLanguage(),RecordList.length() + " @RowsUpdated@ " + actionName);
				JSONObject Message = new JSONObject();
				Message.put("severity", "success");
				Message.put("text", message);
				response.put("message", Message);
				return response;
			}
			OBContext.restorePreviousMode();
		} catch (Exception e) {
			OBDal.getInstance().rollbackAndClose();
			log4jDocRouting.error("Document Action Failed: " + e.getMessage(), e);
			Throwable ex = DbUtility.getUnderlyingSQLException(e);
			String message = OBMessageUtils.translateError(ex.getMessage()).getMessage();
			try {
				JSONObject errorMessage = new JSONObject();
				errorMessage.put("severity", "error");
				errorMessage.put("text", message);
				response.put("message", errorMessage);
			} catch (JSONException ignore) {
				e.printStackTrace();
			}
		}
		return response;
	}

	private String getCurrentStatus(String windowID, String tabID, String referenceID, List<String> recordId) throws Exception{
		log4jDocRouting.debug("start getCurrentStatus");
		Tab tab=OBDal.getInstance().get(Tab.class, tabID);
		Entity entityActive = ModelProvider.getInstance().getEntityByTableId(tab.getTable().getId());
		Property docStatusProp=null;
		String column="documentStatus";
		log4jDocRouting.debug("get value from documentStatus column");
		docStatusProp = entityActive.getProperty(column, false);
		if(docStatusProp==null)
		{
			log4jDocRouting.debug("get value from oezDocStatus column");
			column="oezDocstatus";
			docStatusProp=entityActive.getProperty(column, false);	
		}	        
		if(docStatusProp==null){
			log4jDocRouting.debug("get value from user defined document status column");
			Table table = tab.getTable();
			String columnStatus = table.getOezDocstatuscolumn();
			docStatusProp = entityActive.getProperty(columnStatus, false);
			if(docStatusProp!=null){
				column=columnStatus;
			}else{
				throw new Exception("Column documentStatus or oezDocstatus not found");	
			}
		}
		BaseOBObject obObject = OBDal.getInstance().get(entityActive.toString(), recordId.get(0));	         
		String currentStatus= (String)obObject.get(column);
		if(recordId.size()>1){
			for(int i=1;i<recordId.size();i++){
				BaseOBObject ob= OBDal.getInstance().get(entityActive.toString(), recordId.get(i));
				String tm= (String)ob.get(column);
				if(!tm.equalsIgnoreCase(currentStatus)){
					return null;
				}
			}	
		}
		return currentStatus;
	}
	
	private JSONObject getActionComboBox(String windowID, String tabID, String referenceID, List<String> recordId, 
			VariablesSecureApp vars,String currentStatus) throws Exception {
		OBContext.setAdminMode();
		BigDecimal totalTrxAmount = getTotalTrxAmount(tabID, recordId);
		Organization organization = GetOrganizationId(tabID, recordId);
		String defaultValue = null;
		JSONObject response = null;
		String roleID = vars.getRole();
		
		//tiko
		DocumentActionList[] docHandlerAcct = DocumentRoutingHandlerServer
				.actionData(referenceID, roleID,windowID, tabID, currentStatus, totalTrxAmount, organization);
		if(docHandlerAcct.length>0){
			JSONObject valueMap = new JSONObject();
			response=new JSONObject();
			for (DocumentActionList DocRouteAction : docHandlerAcct) {
				String key = DocRouteAction.getField("ID");
				String Name = DocRouteAction.getField("NAME");
				valueMap.put(key, Name);
			}
			response.put("valueMap", valueMap);
			response.put("defaultValue", defaultValue);
		}		    
		OBContext.restorePreviousMode();
		return response;
	}

	private Organization GetOrganizationId (String tabID, List<String> recordId) {
		OBContext.setAdminMode();
		Tab tab = OBDal.getInstance().get(Tab.class, tabID);
		Entity entityActive = ModelProvider.getInstance().getEntityByTableId(tab.getTable().getId());
		BaseOBObject obObject = OBDal.getInstance().get(entityActive.toString(), recordId.get(0));	
		Organization organization = (Organization)obObject.get("organization");
		return organization;
	}
	
	private BigDecimal getTotalTrxAmount(String tabID, List<String> recordId) {
		OBContext.setAdminMode();
		Tab tab=OBDal.getInstance().get(Tab.class, tabID);
		Entity entityActive = ModelProvider.getInstance().getEntityByTableId(tab.getTable().getId());

		Table table = tab.getTable();
		if (table.getOezTrxamtcolumn()==null)
			return BigDecimal.ZERO;
		String columnAmount = table.getOezTrxamtcolumn();
		Property trxAmountProp = entityActive.getProperty(columnAmount, false);

		if (trxAmountProp==null)
			return BigDecimal.ZERO; //kolom transaction amount di ad_table tidak didefinisikan artinya dokumen ini tidak ber-amount, return zero sebab dengan trx amount zero maka akan di-approve

		if(table.getOezTrxdatecolumn()==null) // jika null maka tidak menggunakan validasi currency 
			return BigDecimal.ZERO;

		BaseOBObject obObject = OBDal.getInstance().get(entityActive.toString(), recordId.get(0));	         
		BigDecimal trxAmount= (BigDecimal)obObject.get(columnAmount);
		String trxDate = table.getOezTrxdatecolumn();

		Currency trxCurrency = (Currency)obObject.get("currency");
		Date trxOrderDate = (Date)obObject.get(trxDate);
//		Organization currencyOrganization = (Organization)obObject.get("organization");
//		Currency organizationCurrency = currencyOrganization.getCurrency();
		Client clientCurrency = (Client)obObject.get("client");
		Currency clientBaseCurrency = clientCurrency.getCurrency();
		
		// filter jika perbandingan currency record sama base currency berbeda.
		if(!(trxCurrency.equals(clientBaseCurrency))) {					// rahmadi's change on parameter from organizationCurrency
			trxAmount = GetCurrencyConverter(clientCurrency, clientBaseCurrency, trxCurrency, trxOrderDate, trxAmount);
		}

		if(recordId.size()>1){
			for(int i=1;i<recordId.size();i++){
				BaseOBObject ob= OBDal.getInstance().get(entityActive.toString(), recordId.get(i));
				BigDecimal newAmount= (BigDecimal)ob.get(columnAmount);
				
				Currency trxCurrency2 = (Currency)ob.get("currency");
				Date trxOrderDate2 = (Date)obObject.get(trxDate);
				Organization currencyOrganization2 = (Organization)obObject.get("organization");
				Currency organizationCurrency2 = currencyOrganization2.getCurrency();
				Client clientCurrency2 = (Client)obObject.get("client");
				Currency clientBaseCurrency2 = clientCurrency.getCurrency();
				
				if(!(trxCurrency.equals(organizationCurrency2))) {
					newAmount = GetCurrencyConverter(clientCurrency2, clientBaseCurrency2, trxCurrency2, trxOrderDate2, trxAmount);
				}

				if (newAmount.compareTo(trxAmount)>0)
					trxAmount = newAmount;
			}	
		}
		OBContext.restorePreviousMode();
		return trxAmount;
	}

	private BigDecimal GetCurrencyConverter (Client clientCurrency, Currency clientBaseCurrency, Currency trxCurrency,Date trxOrderDate, BigDecimal trxAmount) {

		OBCriteria<ConversionRate> conversionRate = OBDal.getInstance().createCriteria(ConversionRate.class);
		conversionRate.add(Restrictions.eq(ConversionRate.PROPERTY_TOCURRENCY, clientBaseCurrency )); // memfilter column "to_currency" sesuai dengan base curency dari client
		conversionRate.add(Restrictions.eq(ConversionRate.PROPERTY_CURRENCY, trxCurrency )); // memfilter column "currency" sesuai dengan currency pada record yang di approve
		conversionRate.add(Restrictions.eq(ConversionRate.PROPERTY_CLIENT, clientCurrency)); // memfilter sesuai client yang digunakan

		List<ConversionRate> conversionRateList = conversionRate.list();
		String trxCurrencyIsoCode = trxCurrency.getISOCode();
		if(conversionRateList == null || conversionRateList.isEmpty()) {				
			String string = String.format("conversion rate for Currency Iso code \"%s\" no available", trxCurrencyIsoCode);
			throw new OBException(string);
		}
		int jumlahList = conversionRateList.size();
		for(ConversionRate conversionRateId : conversionRateList) {
			if( trxOrderDate.compareTo(conversionRateId.getValidFromDate()) >= 0 && trxOrderDate.compareTo(conversionRateId.getValidToDate()) <= 0 ) {
				trxAmount = trxAmount.multiply(conversionRateId.getMultipleRateBy()); 	
			}
			else {
				jumlahList --;
			}
		}
		if (jumlahList == 0) {
			String string = String.format("Currency Iso code \"%s\", does not have any available Date in Conversion Rates window that match for this Order Date", trxCurrencyIsoCode);
			throw new OBException(string);
		}

		return trxAmount;

	}

}