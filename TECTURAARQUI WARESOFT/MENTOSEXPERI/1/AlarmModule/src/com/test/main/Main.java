package com.test.main;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.connection.MessageListener;

/**
 * @class Main.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class Main {

	public static void main(String[] args) {
		loadConfigurations(args);
		String port = getPort();
		startMessageListener(port);
	}

	/**
	 * @param port
	 */
	private static void startMessageListener(String port) {
		Thread messageListener = new Thread(new MessageListener(
				Integer.valueOf(port)));
		messageListener.start();
	}

	/**
	 * @return
	 */
	private static String getPort() {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.NODE_PORT.name());
		return port;
	}
	
	/**
	 * 
	 */
	private static void loadConfigurations(String[] args) {
	    if (args != null && args.length > 0) {
            ConfigurationManager.getInstance().loadProperties(args[0]);
        } else {
            ConfigurationManager.getInstance().loadProperties();
        }
	}
}
