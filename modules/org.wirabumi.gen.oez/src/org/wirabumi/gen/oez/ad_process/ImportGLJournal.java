package org.wirabumi.gen.oez.ad_process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.model.financialmgmt.gl.GLBatch;
import org.openbravo.model.financialmgmt.gl.GLCategory;
import org.openbravo.model.financialmgmt.gl.GLJournal;
import org.openbravo.model.financialmgmt.gl.GLJournalLine;
import org.openbravo.model.project.Project;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

public class ImportGLJournal extends DalBaseProcess {
	
	private final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	Logger logger = Logger.getLogger(ImportGLJournal.class);

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		//global variable preparation
		final String clientID = OBContext.getOBContext().getCurrentClient().getId();
		final String orgID = (String) bundle.getParams().get("adOrgId");
		Organization org = OBDal.getInstance().get(Organization.class, orgID);
		if (org==null)
			throw new OBException("can not find organization with id: "+orgID);

		ConnectionProvider conn = new DalConnectionProvider();
		Connection connection = conn.getConnection();
		
		// load master into hashmap
		HashMap<String, AccountingCombination> acctCombinationMap = loadAccountCombinationMap(connection, clientID);
		HashMap<String, DocumentType> docTypeMap = loadDocumentTypeMap(connection, clientID);
		HashMap<String, AcctSchema> acctSchemaMap = loadAcctSchemaMap(connection, clientID);
		HashMap<String, Currency> currencyMap = loadCurrencyMap(connection, clientID);
		HashMap<String, Period> periodMap = loadPeriodMap(connection, org); //dd-MM-yyyy --> Period object
		HashMap<String, GLCategory> glCategoryMap = loadGLCategoryMap(connection, clientID);
		HashMap<String, BusinessPartner> bpMap = loadBPMap(connection, clientID);
		HashMap<String, Product> productMap = loadProductMap(connection, clientID);
		HashMap<String, Project> projectMap = loadProjectMap(connection, clientID);
		
		
		// update master data on i_gljournal
		HashMap<String, org.openbravo.model.dataimport.GLJournal> pendingImportGL = updatePendingImportGL(connection, clientID, 
				acctSchemaMap, docTypeMap, acctCombinationMap, productMap, 
				projectMap, currencyMap, periodMap, glCategoryMap, bpMap); 
		
		// create bacth
		HashMap<String, GLBatch> glBatchMap = doProcessGLBatch(connection, clientID, org, currencyMap, periodMap, glCategoryMap);
		if (logger.isDebugEnabled()){
			logger.debug("loaded gl batch:");
			for (String id : glBatchMap.keySet()){
				GLBatch batch = glBatchMap.get(id);
				logger.debug("id: "+batch.getId()+" documentno: "+batch.getDocumentNo());
			}
		}
		
		// create header
		HashMap<String, GLJournal> glJournalMap = doProcessGLJournal(connection, clientID, currencyMap, periodMap, 
				glCategoryMap, glBatchMap, acctSchemaMap, docTypeMap);
		if (logger.isDebugEnabled()){
			logger.debug("loaded gl header");
			for (String id : glJournalMap.keySet()){
				GLJournal header = glJournalMap.get(id);
				logger.debug("id: "+header.getId()+" documentno: "+header.getDocumentNo());
			}
		}
		
		// create lines
		HashMap<org.openbravo.model.dataimport.GLJournal, GLJournalLine> glJournalLineMap = doProcessGLJournalLine(connection, clientID, 
				currencyMap, periodMap, glCategoryMap, glBatchMap,bpMap, productMap, projectMap, glJournalMap, pendingImportGL);
		
		//update import status
		updateImportStatus(glJournalLineMap);
		
		//return message
		OBError msg = new OBError();
		msg.setType("SUCCESS");
		msg.setTitle("Success");
		msg.setMessage(glJournalLineMap.keySet().size()+" GL journal(s) imported successfully.");
		bundle.setResult(msg);

	}

	private HashMap<String, Project> loadProjectMap(Connection connection, String clientID) {
		HashMap<String, Project> output = new HashMap<>();
		String sql = "select distinct a.projectvalue, b.c_project_id"
				+ " from i_gljournal a"
				+ " inner join c_project b on b.value=a.projectvalue and b.ad_client_id=a.ad_client_id"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String projectCode = rs.getString("projectvalue");
				String projectID = rs.getString("c_project_id");
				Project project = OBDal.getInstance().get(Project.class, projectID);
				if (project==null)
					continue;
				output.put(projectCode, project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	private HashMap<String, Product> loadProductMap(Connection connection, String clientID) {
		HashMap<String, Product> output = new HashMap<>();
		String sql = "select distinct a.productvalue, b.m_product_id"
				+ " from i_gljournal a"
				+ " inner join m_product b on b.value=a.productvalue and b.ad_client_id=a.ad_client_id"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String productCode = rs.getString("productvalue");
				String productID = rs.getString("m_product_id");
				Product product = OBDal.getInstance().get(Product.class, productID);
				if (product==null)
					continue;
				output.put(productCode, product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}

	private HashMap<String, AccountingCombination> loadAccountCombinationMap(Connection connection, String clientID) {
		HashMap<String, AccountingCombination> output = new HashMap<>();
		String sql = "select distinct a.accountvalue, c.c_validcombination_id"
				+ " from i_gljournal a"
				+ " inner join c_elementvalue b on b.value=a.accountvalue and b.ad_client_id=a.ad_client_id"
				+ " inner join c_validcombination c on c.account_id=b.c_elementvalue_id"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String accountCode = rs.getString("accountvalue");
				String accountID = rs.getString("c_validcombination_id");
				AccountingCombination account = OBDal.getInstance().get(AccountingCombination.class, accountID);
				if (account==null)
					continue;
				output.put(accountCode, account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	private HashMap<String, DocumentType> loadDocumentTypeMap(Connection connection, String clientID) {
		HashMap<String, DocumentType> output = new HashMap<>();
		String sql = "select distinct a.doctypename, b.c_doctype_id"
				+ " from i_gljournal a"
				+ " inner join c_doctype b on b.ad_client_id=a.ad_client_id and b.name=a.doctypename"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String doctypename = rs.getString("doctypename");
				String doctypeID = rs.getString("c_doctype_id");
				DocumentType doctype = OBDal.getInstance().get(DocumentType.class, doctypeID);
				if(doctype==null)
					continue;
				output.put(doctypename, doctype);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}

	private HashMap<String, AcctSchema> loadAcctSchemaMap(Connection connection, String clientID) {
		HashMap<String, AcctSchema> output = new HashMap<>();
		String sql = "select distinct a.acctschemaname, b.c_acctschema_id"
				+ " from i_gljournal a"
				+ " inner join c_acctschema b on b.name=a.acctschemaname and b.ad_client_id=a.ad_client_id"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String name = rs.getString("acctschemaname");
				String id = rs.getString("c_acctschema_id");
				AcctSchema acctschema = OBDal.getInstance().get(AcctSchema.class, id);
				if (acctschema==null)
					continue;
				output.put(name, acctschema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	private HashMap<String, org.openbravo.model.dataimport.GLJournal> updatePendingImportGL(Connection connection, String clientID,
			HashMap<String, AcctSchema> acctSchemaMap, HashMap<String, DocumentType> docTypeMap,
			HashMap<String, AccountingCombination> acctCombinationMap, HashMap<String, Product> productMap,
			HashMap<String, Project> projectMap,
			HashMap<String, Currency> currencyMap, HashMap<String, Period> periodMap,
			HashMap<String, GLCategory> glCategoryMap, HashMap<String, BusinessPartner> bpMap) {
		
		//mengapa output ini harus hashmap, bukan list? karena perlu dibandingkan apakah id nya sudah exists didalam map apa belum
		//kalau pakai list, maka object didalam arraylist harus implement comparable, sedangkan baseobobject tidak.
		//dari pada harus membuatnya jadi comparable, maka pakai string saja (alias id) yang sudah dijamin comparable
		//oleh sebab itu pakainya hashmap, bukan list.
		HashMap<String, org.openbravo.model.dataimport.GLJournal> output = new HashMap<>();
		
		OBCriteria<org.openbravo.model.dataimport.GLJournal> importGLC = 
				OBDal.getInstance().createCriteria(org.openbravo.model.dataimport.GLJournal.class);
		importGLC.add(Restrictions.eq(org.openbravo.model.dataimport.GLJournal.PROPERTY_IMPORTPROCESSCOMPLETE, false));
		for (org.openbravo.model.dataimport.GLJournal importgl : importGLC.list()){
			
			if (output.containsKey(importgl.getId()))
				continue; //record exists;
			
			StringBuilder sb = new StringBuilder();
			
			//acct schema
			String acctschemaname = importgl.getAccountSchemaName();
			if (acctSchemaMap.containsKey(acctschemaname)){
				AcctSchema acctschema = acctSchemaMap.get(acctschemaname);
				importgl.setAccountingSchema(acctschema);
			} else
				sb.append("invalid accounting schema name").append(System.getProperty("line.separator"));
			
			//document type
			String doctypeName = importgl.getDocumentTypeName();
			if (docTypeMap.containsKey(doctypeName)){
				DocumentType doctype = docTypeMap.get(doctypeName);
				importgl.setDocumentType(doctype);
			} else
				sb.append("invalid document type name").append(System.getProperty("line.separator"));
			
			//gl category
			String glcategoryname = importgl.getCategoryName();
			if (glCategoryMap.containsKey(glcategoryname)){
				GLCategory glcategory = glCategoryMap.get(glcategoryname);
				importgl.setGLCategory(glcategory);
			} else
				sb.append("invalid GL category name").append(System.getProperty("line.separator"));
			
			//period
			Date dateacct = importgl.getAccountingDate();
			String strdateacct = df.format(dateacct);
			if (periodMap.containsKey(strdateacct)){
				Period period = periodMap.get(strdateacct);
				importgl.setPeriod(period);
			} else
				sb.append("can not find period").append(System.getProperty("line.separator"));
			
			//currency
			String iso_code = importgl.getISOCode();
			if (currencyMap.containsKey(iso_code)){
				Currency currency = currencyMap.get(iso_code);
				importgl.setCurrency(currency);
			} else
				sb.append("can not find currency").append(System.getProperty("line.separator"));
			
			//account, accounting combination
			String accountCode = importgl.getAccountKey();
			if (acctCombinationMap.containsKey(accountCode)){
				AccountingCombination acctcomb = acctCombinationMap.get(accountCode);
				ElementValue account = acctcomb.getAccount();
				importgl.setAccountingCombination(acctcomb);
				importgl.setAccount(account);
			} else 
				sb.append("can not find account").append(System.getProperty("line.separator"));
			
			//optional: product
			String productCode = importgl.getProductSearchKey();
			if (productMap.containsKey(productCode)){
				Product product = productMap.get(productCode);
				importgl.setProduct(product);
			}
			
			//optional: business partner
			String bpCode = importgl.getBusinessPartnerSearchKey();
			if (bpMap.containsKey(bpCode)){
				BusinessPartner bp = bpMap.get(bpCode);
				importgl.setBusinessPartner(bp);
			}
			
			//optional: project
			String projectCode = importgl.getProjectKey();
			if (projectMap.containsKey(projectCode)){
				Project project = projectMap.get(projectCode);
				importgl.setProject(project);
			}
			
			//error message
			String err_msg=null;
			if (sb.length()>0)
				err_msg=sb.toString();
			if (sb.length()>2000)
				err_msg=sb.substring(0, 2000);
			if (err_msg!=null)
				importgl.setImportErrorMessage(err_msg);
			
			OBDal.getInstance().save(importgl);
			output.put(importgl.getId(), importgl);
		}
		
		return output;
	}

	private void updateImportStatus(HashMap<org.openbravo.model.dataimport.GLJournal, GLJournalLine> glJournalLineMap) {
		for (org.openbravo.model.dataimport.GLJournal pendingimport : glJournalLineMap.keySet()){
			GLJournalLine line = glJournalLineMap.get(pendingimport);
			pendingimport.setImportProcessComplete(true);
			pendingimport.setImportErrorMessage(null);
			pendingimport.setProcessed(true);
			pendingimport.setProcessNow(true);
			pendingimport.setJournalLine(line);
			pendingimport.setJournalEntry(line.getJournalEntry());
			pendingimport.setJournalBatch(line.getJournalEntry().getJournalBatch());
			OBDal.getInstance().save(pendingimport);
		}
		
	}

	private HashMap<org.openbravo.model.dataimport.GLJournal, GLJournalLine> doProcessGLJournalLine(Connection connection, String clientID,
			HashMap<String, Currency> currencyMap, HashMap<String, Period> periodMap,
			HashMap<String, GLCategory> glCategoryMap, HashMap<String, GLBatch> glBatchMap,
			HashMap<String, BusinessPartner> bpMap, HashMap<String, Product> productMap,
			HashMap<String, Project> projectMap, HashMap<String, GLJournal> glJournalMap,
			HashMap<String, org.openbravo.model.dataimport.GLJournal> pendingImportGL) {
		HashMap<org.openbravo.model.dataimport.GLJournal, GLJournalLine> output = new HashMap<>();
		
		for (org.openbravo.model.dataimport.GLJournal pendingimport : pendingImportGL.values()){
			String errormessage = pendingimport.getImportErrorMessage();
			if (errormessage!=null)
				continue; //not valid import record.
			GLJournalLine line = OBProvider.getInstance().get(GLJournalLine.class);
			String batchno = pendingimport.getBatchDocumentNo();
			if (!glBatchMap.containsKey(batchno))
				throw new OBException("can not find GL batch with document no: "+batchno);
			String headerno = pendingimport.getJournalDocumentNo();
			if (!glJournalMap.containsKey(batchno.concat(headerno)))
				throw new OBException("can not find GL Journal with document no: "+headerno);
			GLJournal header = glJournalMap.get(batchno.concat(headerno));
			line.setJournalEntry(header);
			line.setOrganization(header.getOrganization());
			line.setDescription(pendingimport.getDescription());
			line.setLineNo(pendingimport.getLineNo());
			line.setForeignCurrencyDebit(pendingimport.getForeignCurrencyDebit());
			line.setForeignCurrencyCredit(pendingimport.getForeignCurrencyCredit());
			line.setCurrency(header.getCurrency());
			line.setCurrencyRateType(header.getCurrencyRateType());
			line.setRate(header.getRate());
			line.setAccountingDate(header.getAccountingDate());
			line.setDebit(pendingimport.getDebit());
			line.setCredit(pendingimport.getCredit());
			line.setAccountingCombination(pendingimport.getAccountingCombination());
			line.setGLItem(null);
			line.setGLItems(null);
			
			//optional: product and uom
			Product product = pendingimport.getProduct();
			if (product!=null){
				line.setProduct(product);
				line.setUOM(product.getUOM());
				line.setQuantity(pendingimport.getQuantity());
			}
			
			//optional: project
			line.setProject(pendingimport.getProject());
			
			//optional: bp
			line.setBusinessPartner(pendingimport.getBusinessPartner());
			
			OBDal.getInstance().save(line);
			
			output.put(pendingimport, line);
		}
		
		return output;
	}

	private HashMap<String, GLJournal> doProcessGLJournal(Connection connection, String clientID,
			HashMap<String, Currency> currencyMap, HashMap<String, Period> periodMap,
			HashMap<String, GLCategory> glCategoryMap, HashMap<String, GLBatch> glBatchMap, 
			HashMap<String, AcctSchema> acctSchemaMap, HashMap<String, DocumentType> docTypeMap) {
		HashMap<String, GLJournal> output = getOpenGLJournalHeader();
		String sql = "select distinct batchdocumentno, journaldocumentno, batchdescription, acctschemaname, doctypename,"
				+ " currencyratetype, currencyrate"
				+ " from i_gljournal a"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String journaldocumentno = rs.getString("journaldocumentno");
				
				String batchdocumentno = rs.getString("batchdocumentno");
				if (!glBatchMap.containsKey(batchdocumentno))
					throw new OBException("can not find GL Batch with document No: "+batchdocumentno);
				GLBatch glbatch = glBatchMap.get(batchdocumentno);
				
				if (output.containsKey(batchdocumentno.concat(journaldocumentno)))
					continue; //gl journal header exists;
				
				String acctschemaname = rs.getString("acctschemaname");
				if (!acctSchemaMap.containsKey(acctschemaname))
					throw new OBException("can not find accounting schema with name : "+acctschemaname);
				AcctSchema acctschema = acctSchemaMap.get(acctschemaname);
				
				String doctypename = rs.getString("doctypename");
				if (!docTypeMap.containsKey(doctypename))
					throw new OBException("can not find document type with name : "+doctypename);
				DocumentType doctype = docTypeMap.get(doctypename);
				
				
				String desc = rs.getString("batchdescription");
				String currencyratetype = rs.getString("currencyratetype");
				BigDecimal currencyrate = rs.getBigDecimal("currencyrate");
				
				
				GLJournal gljournal = OBProvider.getInstance().get(GLJournal.class);
				gljournal.setOrganization(glbatch.getOrganization());
				gljournal.setJournalBatch(glbatch);
				gljournal.setDocumentNo(journaldocumentno);
				gljournal.setDescription(desc);
				gljournal.setAccountingSchema(acctschema);
				gljournal.setDocumentType(doctype);
				gljournal.setPostingType(glbatch.getPostingType());
				gljournal.setGLCategory(glbatch.getGLCategory());
				gljournal.setDocumentDate(glbatch.getDocumentDate());
				gljournal.setAccountingDate(glbatch.getAccountingDate());
				gljournal.setPeriod(glbatch.getPeriod());
				gljournal.setCurrency(glbatch.getCurrency());
				gljournal.setCurrencyRateType(currencyratetype);
				gljournal.setRate(currencyrate);
				
				OBDal.getInstance().save(gljournal);
				output.put(batchdocumentno.concat(journaldocumentno), gljournal);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	private HashMap<String, GLJournal> getOpenGLJournalHeader() {
		HashMap<String, GLJournal> output = new HashMap<>();
		OBCriteria<GLJournal> gljournalC = OBDal.getInstance().createCriteria(GLJournal.class);
		gljournalC.add(Restrictions.eq(GLJournal.PROPERTY_PROCESSED, false));
		for (GLJournal gljournal : gljournalC.list()){
			GLBatch glbatch = gljournal.getJournalBatch();
			String batchno="";
			if (glbatch!=null)
				batchno=glbatch.getDocumentNo();
			output.put(batchno.concat(gljournal.getDocumentNo()), gljournal);
		}
		return output;
	}

	private HashMap<String, GLBatch> doProcessGLBatch(Connection connection, String clientID,
			Organization org, HashMap<String, Currency> currencyMap, HashMap<String, Period> periodMap,
			HashMap<String, GLCategory> glCategoryMap) {
		HashMap<String, GLBatch> output = getOpenGLBatch();
		
		if (logger.isDebugEnabled()){
			logger.debug("gl batch size: "+output.size());
			logger.debug("loaded existing gl batch:");
			for (String documenno : output.keySet()){
				GLBatch batch = output.get(documenno);
				logger.debug("id: "+batch.getId()+" documentno: "+documenno);
			}
		}
		
		String sql="select distinct batchdocumentno, batchdescription, postingtype,"
				+ " categoryname, dateacct, iso_code from i_gljournal"
				+ " where ad_client_id=?"
				+ " and i_isimported='N'";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			int fetchsize = rs.getFetchSize();
			logger.debug("new batch fetch size: "+fetchsize);
			while (rs.next()){
				String batchNo = rs.getString("batchdocumentno");
				logger.debug("new batch documentno: "+batchNo);
				if (output.containsKey(batchNo))
					continue;
				
				String batchdescription = rs.getString("batchdescription");
				String postingType = rs.getString("postingtype");
				String categoryName = rs.getString("categoryname");
				if (!glCategoryMap.containsKey(categoryName))
					throw new OBException("error during batch creation preparation, can not find GL category name: "+categoryName);
					
				GLCategory glcategory = glCategoryMap.get(categoryName);
				
				Date dateacct = rs.getDate("dateacct");
				String strdateacct = df.format(dateacct);
				if (!periodMap.containsKey(strdateacct))
					throw new OBException("error during batch creation preparation, can not find accounting period: "+strdateacct);
					
				Period period = periodMap.get(strdateacct);
				
				String iso_code = rs.getString("iso_code");
				if (!currencyMap.containsKey(iso_code))
					continue;
				Currency currency = currencyMap.get(iso_code);
				
				logger.debug("start creating new bactch");
				GLBatch glbatch = OBProvider.getInstance().get(GLBatch.class);
				glbatch.setOrganization(org);
				glbatch.setDescription(batchdescription);
				glbatch.setDocumentNo(batchNo);
				glbatch.setDocumentDate(dateacct);
				glbatch.setAccountingDate(dateacct);
				glbatch.setPostingType(postingType);
				glbatch.setGLCategory(glcategory);
				glbatch.setPeriod(period);
				glbatch.setCurrency(currency);
				OBDal.getInstance().save(glbatch);
				
				output.put(batchNo, glbatch);
				logger.debug("end creating new bacth, current batch size: "+output.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (logger.isDebugEnabled())
			logger.debug("final gl batch size: "+output.size());
		return output;
	}

	private HashMap<String, GLBatch> getOpenGLBatch() {
		HashMap<String, GLBatch> output = new HashMap<>();
		OBCriteria<GLBatch> glbatchC = OBDal.getInstance().createCriteria(GLBatch.class);
		glbatchC.add(Restrictions.eq(GLBatch.PROPERTY_PROCESSED, false));
		for (GLBatch glbatch : glbatchC.list()){
			output.put(glbatch.getDocumentNo(), glbatch);
		}
		
		return output;
	}

	private HashMap<String, GLCategory> loadGLCategoryMap(Connection connection, String clientID) {
		HashMap<String, GLCategory> output = new HashMap<>();
		String sql = "select distinct b.gl_category_id"
				+ " from i_gljournal a"
				+ " inner join gl_category b on b.ad_client_id=a.ad_client_id and b.name=a.categoryname"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String id = rs.getString("gl_category_id");
				GLCategory glcategory = OBDal.getInstance().get(GLCategory.class, id);
				if (glcategory==null)
					continue;
				output.put(glcategory.getName(), glcategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	private HashMap<String, Period> loadPeriodMap(Connection connection, Organization org) {
		HashMap<String, Period> output = new HashMap<>(); //dd-MM-yyyy --> Period object
		String sql = "select distinct a.dateacct, b.c_period_id"
				+ " from i_gljournal a"
				+ " inner join c_period b on b.startdate<= a.dateacct and a.dateacct<= b.enddate"
				+ " inner join c_year c on c.c_year_id=b.c_year_id"
				+ " inner join c_calendar d on d.c_calendar_id=c.c_calendar_id"
				+ " inner join ad_org e on e.c_calendar_id=d.c_calendar_id"
				+ " where a.i_isimported='N'"
				+ " and e.ad_org_id=ad_org_getcalendarowner(?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, org.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				Date dateacct = rs.getDate("dateacct");
				String strDateAcct = df.format(dateacct);
				String periodID = rs.getString("c_period_id");
				Period period = OBDal.getInstance().get(Period.class, periodID);
				output.put(strDateAcct, period);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()){
			logger.debug("loaded period");
			for (String strdateacct : output.keySet()){
				logger.debug(strdateacct+" : "+output.get(strdateacct).getName());
			}
		}
		return output;
	}

	private HashMap<String, BusinessPartner> loadBPMap(Connection connection, String clientID) {
		HashMap<String, BusinessPartner> output = new HashMap<>();
		String sql = "select distinct a.bpartnervalue, b.c_bpartner_id"
				+ " from i_gljournal a"
				+ " inner join c_bpartner b on b.value=a.bpartnervalue and b.ad_client_id=a.ad_client_id"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String code = rs.getString("bpartnervalue");
				String id = rs.getString("c_bpartner_id");
				BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, id);
				if (bp==null)
					continue;
				output.put(code, bp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	private HashMap<String, Currency> loadCurrencyMap(Connection connection, String clientID) {
		HashMap<String, Currency> output = new HashMap<>();
		String sql = "select distinct a.iso_code, b.c_currency_id"
				+ " from i_gljournal a"
				+ " inner join c_currency b on b.iso_code=a.iso_code and b.ad_client_id in (a.ad_client_id,'0')"
				+ " where a.ad_client_id=?"
				+ " and a.i_isimported='N'";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, clientID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				String iso_code = rs.getString("iso_code");
				String id = rs.getString("c_currency_id");
				Currency currency = OBDal.getInstance().get(Currency.class, id);
				if (currency==null)
					continue;
				output.put(iso_code, currency);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

}

