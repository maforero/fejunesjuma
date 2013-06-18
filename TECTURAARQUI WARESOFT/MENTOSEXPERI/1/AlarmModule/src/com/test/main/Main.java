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
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.NODE_PORT.name());
		Thread messageListener = new Thread(new MessageListener(
				Integer.valueOf(port)));
		messageListener.start();
	}
}
