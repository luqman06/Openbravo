package org.wirabumi.gen.oez.modulescript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openbravo.modulescript.ModuleScript;

public class ModuleScriptHris extends ModuleScript {

	@Override
	public void execute() {
		System.err.println("starting oez module script");
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
		from = FileSystems.getDefault().getPath(attachPath+"/modules/org.wirabumi.hris/srcClient/org/openbravo/erpCommon/utility/WindowTreeChecks.java");
		to = FileSystems.getDefault().getPath(attachPath+"/src/org/openbravo/erpCommon/utility/WindowTreeChecks.java");
		fileToBeMoved.put(from, to);
		
		from = FileSystems.getDefault().getPath(attachPath+"/modules/org.wirabumi.hris/srcClient/org/openbravo/erpCommon/utility/WindowTree_data.xsql");
		to = FileSystems.getDefault().getPath(attachPath+"/src/org/openbravo/erpCommon/utility/WindowTree_data.xsql");
		fileToBeMoved.put(from, to);
		
		from = FileSystems.getDefault().getPath(attachPath+"/modules/org.wirabumi.hris/srcClient/org/openbravo/erpCommon/utility/WindowTreeUtility.java");
		to = FileSystems.getDefault().getPath(attachPath+"/src/org/openbravo/erpCommon/utility/WindowTreeUtility.java");
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

	}

}
