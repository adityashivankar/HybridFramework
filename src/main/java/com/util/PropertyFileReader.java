package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.base.PreDefinedMethods;
import com.constant.constant;

public class PropertyFileReader {
	private final static Logger logger = Logger.getLogger(PreDefinedMethods.class);

	Properties prop = null;

	// Returning the properties object
	public Properties readPropertyFile(String propFileName) {
		File file = new File(constant.PROPERTIES + File.separator + propFileName + ".properties");
		try {
			FileInputStream input = new FileInputStream(file);
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(propFileName + " :Properties file read succesfully.");
		return prop;
	}
	
	public Properties readPropertyFile(String propFileLocation, String propFileName) {
		File file = new File(propFileLocation + File.separator + propFileName + ".properties");
		try {
			FileInputStream input = new FileInputStream(file);
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(propFileName + " :2Properties file read succesfully.");
		return prop;
	}
}
