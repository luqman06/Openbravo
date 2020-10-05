package com.openbravo.gps.translationsmanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.ad_forms.TranslationManager;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.module.Module;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This process creates a standard translation module for the current module.
 * 
 */
public class ExportToXmlFiles extends DalBaseProcess {
  private static final long serialVersionUID = 1L;

  /** XML Element Tag */
  static final String XML_TAG = "compiereTrl";
  /** XML Attribute Table */
  static final String XML_ATTRIBUTE_TABLE = "table";
  /** XML Attribute Language */
  static final String XML_ATTRIBUTE_LANGUAGE = "language";
  /** XML row attribute original language */
  static final String XML_ATTRIBUTE_BASE_LANGUAGE = "baseLanguage";
  /** XML Attribute Version */
  static final String XML_ATTRIBUTE_VERSION = "version";
  /** XML Row Tag */

  static final String XML_ROW_TAG = "row";
  /** XML Row Attribute ID */
  static final String XML_ROW_ATTRIBUTE_ID = "id";
  /** XML Row Attribute Translated */
  static final String XML_ROW_ATTRIBUTE_TRANSLATED = "trl";

  /** XML Value Tag */
  static final String XML_VALUE_TAG = "value";
  /** XML Value Column */
  static final String XML_VALUE_ATTRIBUTE_COLUMN = "column";
  /** XML Value Original */
  static final String XML_VALUE_ATTRIBUTE_ORIGINAL = "original";
  /** XML Value Original */
  static final String XML_VALUE_ATTRIBUTE_ISTRL = "isTrl";

  static final String CONTRIBUTORS_FILENAME = "CONTRIBUTORS";
  static final String XML_CONTRIB = "Contributors";

  private static final Logger log4j = Logger.getLogger(TranslationManager.class);

  @Override
  public void execute(ProcessBundle bundle) throws Exception {

    final String StrSourceModuleId = (String) bundle.getParams().get("AD_Module_ID");

    Properties prop = OBPropertiesProvider.getInstance().getOpenbravoProperties();
    String OBmodulesdir = prop.getProperty("source.path") + "/modules/";

    OBContext.setAdminMode();
    try {

      Module TranslationModule = OBDal.getInstance().get(Module.class, StrSourceModuleId);
      // Check if Translation Language is system language
      if (!TranslationModule.getLanguage().isSystemLanguage()) {
        OBError msg = new OBError();
        msg.setType("Error");
        msg.setMessage(TranslationModule.getLanguage().getIdentifier()
            + " @obgpstm_not_system_language@");
        bundle.setResult(msg);
      }

      if (TranslationModule.getModuleDependencyList().size() != 1) {
        OBError msg = new OBError();
        msg.setType("Error");
        msg.setMessage(TranslationModule.getName() + " @obgpstm_module_dependency_wrong@");
        bundle.setResult(msg);
        return;
      }

      Module TranslatedModule = TranslationModule.getModuleDependencyList().get(0)
          .getDependentModule();

      String exportdir = OBmodulesdir + TranslationModule.getJavaPackage()
          + "/referencedata/translation/" + TranslationModule.getLanguage().getLanguage() + "/";

      String tempdir = exportdir + "/tmp/";
      (new File(exportdir)).mkdirs();

      final TranslationData[] modulesTables = TranslationData.trlModulesTables(bundle
          .getConnection());

      for (int i = 0; i < modulesTables.length; i++) {
        final String tableName = modulesTables[i].c;
        final int pos = tableName.indexOf("_TRL");
        final String Base_Table = tableName.substring(0, pos);
        boolean trl = true;
        exportTable(bundle.getConnection(), TranslationModule.getLanguage().getLanguage(), false,
            false, Base_Table, "0", exportdir, TranslatedModule.getId(), TranslatedModule
                .getLanguage().getLanguage(), "tmp", trl);
      }

      OBError msg = new OBError();
      msg.setType("Success");
      msg.setTitle("@Success@");
      msg.setMessage("@obgpstm_export_success@" + exportdir);
      bundle.setResult(msg);
    } finally {
      OBContext.restorePreviousMode();
    }
  }

  private static void exportTable(ConnectionProvider cp, String AD_Language,
      boolean exportReferenceData, boolean exportAll, String table, String tableID,
      String rootDirectory, String moduleId, String moduleLanguage, String javaPackage, boolean trl) {

    Statement st = null;
    try {
      String trlTable = table;
      if (trl && !table.endsWith("_TRL"))
        trlTable = table + "_TRL";
      final TranslationData[] trlColumns = getTrlColumns(cp, table);
      final String keyColumn = table + "_ID";

      boolean m_IsCentrallyMaintained = false;
      try {
        m_IsCentrallyMaintained = !(TranslationData.centrallyMaintained(cp, table).equals("0"));
        if (m_IsCentrallyMaintained)
          log4j.debug("table:" + table + " IS centrally maintained");
        else
          log4j.debug("table:" + table + " is NOT centrally maintained");
      } catch (final Exception e) {
        log4j.error("getTrlColumns (IsCentrallyMaintained)", e);
      }

      // Prepare query to retrieve translated rows
      final StringBuffer sql = new StringBuffer("SELECT ");
      if (trl)
        sql.append("t.IsTranslated,");
      else
        sql.append("'N', ");
      sql.append("t.").append(keyColumn);

      for (int i = 0; i < trlColumns.length; i++) {
        sql.append(", t.").append(trlColumns[i].c).append(",o.").append(trlColumns[i].c).append(
            " AS ").append(trlColumns[i].c).append("O");
      }

      sql.append(" FROM ").append(trlTable).append(" t").append(", ").append(table).append(" o");

      if (exportReferenceData && !exportAll) {
        sql.append(", AD_REF_DATA_LOADED DL");
      }

      sql.append(" WHERE ");
      if (trl)
        sql.append("t.AD_Language='" + AD_Language + "'").append(" AND ");
      sql.append("o.").append(keyColumn).append("= t.").append(keyColumn);

      if (m_IsCentrallyMaintained) {
        sql.append(" AND ").append("o.IsCentrallyMaintained='N'");
      }
      // AdClient !=0 not supported
      sql.append(" AND o.AD_Client_ID='0' ");

      if (!exportReferenceData) {
        String strParentTable = null;
        String tempTrlTableName = trlTable;
        if (!tempTrlTableName.toLowerCase().endsWith("_trl"))
          tempTrlTableName = tempTrlTableName + "_Trl";
        final TranslationData[] parentTable = TranslationData.parentTable(cp, tempTrlTableName);
        if (parentTable.length > 0) {
          strParentTable = parentTable[0].tablename;
        }
        if (strParentTable == null) {
          sql.append(" AND ").append(" o.ad_module_id='").append(moduleId).append("'");
        } else {
          /** Search for ad_module_id in the parent table */
          sql.append(" AND ");
          sql.append(" exists ( select 1 from ").append(strParentTable).append(" p ");
          sql.append("   where p.").append(strParentTable + "_ID").append("=").append(
              "o." + strParentTable + "_ID");
          sql.append("   and p.ad_module_id='").append(moduleId).append("')");
        }
      }
      if (exportReferenceData && !exportAll) {
        sql.append(" AND DL.GENERIC_ID = o.").append(keyColumn).append(" AND DL.AD_TABLE_ID = '")
            .append(tableID).append("'").append(" AND DL.AD_MODULE_ID = '").append(moduleId)
            .append("'");
      }

      sql.append(" ORDER BY t.").append(keyColumn);
      //

      if (log4j.isDebugEnabled())
        log4j.debug("SQL:" + sql.toString());
      st = cp.getStatement();
      if (log4j.isDebugEnabled())
        log4j.debug("st");

      final ResultSet rs = st.executeQuery(sql.toString());
      if (log4j.isDebugEnabled())
        log4j.debug("rs");
      int rows = 0;
      boolean hasRows = false;

      DocumentBuilderFactory factory = null;
      DocumentBuilder builder = null;
      Document document = null;
      Element root = null;
      File out = null;

      // Create xml file

      String directory = "";
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
      document = builder.newDocument();
      // Root
      root = document.createElement(XML_TAG);
      root.setAttribute(XML_ATTRIBUTE_LANGUAGE, AD_Language);
      root.setAttribute(XML_ATTRIBUTE_TABLE, table);
      root.setAttribute(XML_ATTRIBUTE_BASE_LANGUAGE, moduleLanguage);
      root.setAttribute(XML_ATTRIBUTE_VERSION, TranslationData.version(cp));
      document.appendChild(root);

      if (moduleId.equals("0"))
        directory = rootDirectory;
      else
        directory = rootDirectory;
      if (!new File(directory).exists())
        (new File(directory)).mkdir();

      String fileName = directory + trlTable + "_" + AD_Language + ".xml";
      log4j.info("exportTrl - " + fileName);
      out = new File(fileName);

      while (rs.next()) {
        if (!hasRows && !exportReferenceData) { // Create file only in
          // case it has contents
          // or it is not rd
          hasRows = true;

          factory = DocumentBuilderFactory.newInstance();
          builder = factory.newDocumentBuilder();
          document = builder.newDocument();
          // Root
          root = document.createElement(XML_TAG);
          root.setAttribute(XML_ATTRIBUTE_LANGUAGE, AD_Language);
          root.setAttribute(XML_ATTRIBUTE_TABLE, table);
          root.setAttribute(XML_ATTRIBUTE_BASE_LANGUAGE, moduleLanguage);
          root.setAttribute(XML_ATTRIBUTE_VERSION, TranslationData.version(cp));
          document.appendChild(root);

          if (moduleId.equals("0"))
            directory = rootDirectory;
          else
            directory = rootDirectory;
          if (!new File(directory).exists())
            (new File(directory)).mkdir();

          fileName = directory + trlTable + "_" + AD_Language + ".xml";
          log4j.info("exportTrl - " + fileName);
          out = new File(fileName);
        }

        final Element row = document.createElement(XML_ROW_TAG);
        row.setAttribute(XML_ROW_ATTRIBUTE_ID, String.valueOf(rs.getString(2))); // KeyColumn
        row.setAttribute(XML_ROW_ATTRIBUTE_TRANSLATED, rs.getString(1)); // IsTranslated
        for (int i = 0; i < trlColumns.length; i++) {
          final Element value = document.createElement(XML_VALUE_TAG);
          value.setAttribute(XML_VALUE_ATTRIBUTE_COLUMN, trlColumns[i].c);
          String origString = rs.getString(trlColumns[i].c + "O"); // Original
          String isTrlString = "Y";
          // Value
          if (origString == null) {
            origString = "";
            isTrlString = "N";
          }
          String valueString = rs.getString(trlColumns[i].c); // Value
          if (valueString == null) {
            valueString = "";
            isTrlString = "N";
          }
          if (origString.equals(valueString))
            isTrlString = "N";
          value.setAttribute(XML_VALUE_ATTRIBUTE_ISTRL, isTrlString);
          value.setAttribute(XML_VALUE_ATTRIBUTE_ORIGINAL, origString);
          value.appendChild(document.createTextNode(valueString));
          row.appendChild(value);
        }
        root.appendChild(row);
        rows++;
      }
      rs.close();

      log4j.info("exportTrl - Records=" + rows + ", DTD=" + document.getDoctype());

      final DOMSource source = new DOMSource(document);
      final TransformerFactory tFactory = TransformerFactory.newInstance();
      tFactory.setAttribute("indent-number", new Integer(2));
      final Transformer transformer = tFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      // Output
      out.createNewFile();
      // Transform
      final OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(out), "UTF-8");
      transformer.transform(source, new StreamResult(osw));
      osw.close();
    } catch (final Exception e) {
      log4j.error("Error exporting translation for table " + table, e);
    } finally {
      try {
        if (st != null)
          cp.releaseStatement(st);
      } catch (final Exception ignored) {
      }
    }

  }

  private static TranslationData[] getTrlColumns(ConnectionProvider cp, String Base_Table) {

    TranslationData[] list = null;

    try {
      list = TranslationData.trlColumns(cp, Base_Table + "_TRL");
    } catch (final Exception e) {
      log4j.error("getTrlColumns", e);
    }
    return list;
  }

  @Override
  protected void doExecute(ProcessBundle bundle) throws Exception {
    // TODO Auto-generated method stub

  }
}
