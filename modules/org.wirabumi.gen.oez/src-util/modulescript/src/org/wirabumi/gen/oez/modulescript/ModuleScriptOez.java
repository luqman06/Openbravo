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

import org.apache.commons.io.FileUtils;
import org.openbravo.modulescript.ModuleScript;

public class ModuleScriptOez extends ModuleScript {

	@Override
	public void execute() {
		System.err.println("starting oez module script");
		
		//get attachpath
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
		
		//copy full folder of srcClient/org into src/org
		String source = attachPath+"/modules/org.wirabumi.gen.oez/srcClient";
		File srcDir = new File(source);

		String destination = attachPath+"/src";
		File destDir = new File(destination);

		try {
			FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}

}
