package org.wirabumi.localization.id.community.modulescript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openbravo.database.ConnectionProvider;
import org.openbravo.modulescript.ModuleScript;

public class ModuleScriptID extends ModuleScript {

	@Override
	public void execute() {
		System.err.println("starting ID module script");
		ConnectionProvider conn = getConnectionProvider();
		PreparedStatement ps;
		int updatedrecord = 0;
		try {
			System.err.println("start module script ID");
			//update query ini jika berhasil akan return 1, maka artinya record exists
			ps = conn.getPreparedStatement("update ad_language set updated=now()"
					+ " where ad_language_id='3F3692D8B4504050A2E3E751587352AF'");
			updatedrecord = ps.executeUpdate();
			if (updatedrecord==1){
				System.err.println("endding module script ID: language id_ID already inserted before");
				return;
			}
				
			//record belum ada, maka insert
			String sqlQuery="INSERT INTO ad_language("
					+ " ad_language_id, ad_language, ad_client_id, ad_org_id, isactive,"
					+ " created, createdby, updated, updatedby, name, languageiso, countrycode,"
					+ " isbaselanguage, issystemlanguage, processing, pixelsize, translatedby,"
					+ " isrtl)"
					+ " VALUES ('3F3692D8B4504050A2E3E751587352AF', 'id_ID', '0', '0', 'Y',"
					+ " now(), '100', now(), '100', 'Bahasa Indonesia', 'id', 'ID',"
					+ " 'N', 'Y', null, 6, 'Wirabumi Software',"
					+ " 'N');";
			ps = conn.getPreparedStatement(sqlQuery);
			int exitcode = ps.executeUpdate();
			
			File obConfigFile = getPropertiesFile();
			InputStream obConfigInputStream;
			Properties obConfigProperties = new Properties();
			try {
				obConfigInputStream = new FileInputStream(obConfigFile);
				obConfigProperties.load(obConfigInputStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String attachPath = obConfigProperties.getProperty("source.path");
			HashMap<Path, Path> fileToBeMoved = new HashMap<Path, Path>();
			Path from = null;
			Path to = null;
			System.err.println("attach path: "+attachPath);
			from = FileSystems.getDefault().getPath(attachPath+"/modules/org.wirabumi.localization.id.community/srcClient/FactLine_data.xsql");
			to = FileSystems.getDefault().getPath(attachPath+"/src/org/openbravo/erpCommon/ad_forms/FactLine_data.xsql");
			fileToBeMoved.put(from, to);
			
			try {
				for (Map.Entry<Path, Path> entry : fileToBeMoved.entrySet()){
					Path fromPath = entry.getKey();
					Path toPath = entry.getValue();
					Files.copy(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.err.println("endding module script ID: language id_ID inserted with exit code "+exitcode);


		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
