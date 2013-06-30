package com.test.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @class ConfigurationManager.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class ConfigurationManager {

	/**
	 * The DEFAULT_PROPERTIES
	 */
	private static final String DEFAULT_PROPERTIES = "TransAlpesProperties.properties";
	private static ConfigurationManager instance;
	private Properties properties;

	private ConfigurationManager() {
	}

	public static ConfigurationManager getInstance() {
		if (instance == null) {
			instance = new ConfigurationManager();
		}

		return instance;
	}

	public void loadProperties() {
		loadProperties(DEFAULT_PROPERTIES);
	}

	public void loadProperties(String propertiesPath) {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(propertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getProperty(String property) {

		if (properties == null) {
			throw new IllegalStateException("No properties were loaded");
		}

		return properties.getProperty(property);
	}
}
