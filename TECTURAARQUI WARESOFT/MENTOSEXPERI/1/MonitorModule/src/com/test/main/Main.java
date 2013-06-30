package com.test.main;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.connection.MessageListener;
import com.test.queue.QueueMonitor;

/**
 * @class Main.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class Main {

	public static void main(String[] args) {
		loadConfigurations(args);
		startQueueMonitor();
		startMessageListener();
	}

	/**
	 * 
	 */
	private static void startMessageListener() {
		String port = ConfigurationManager.getInstance().getProperty(
				Properties.NODE_PORT.name());
		Thread messageListener = new Thread(new MessageListener(
				Integer.valueOf(port)));
		messageListener.start();
	}

	/**
	 * 
	 */
	private static void startQueueMonitor() {
		QueueMonitor queueMonitor = new QueueMonitor();
		Thread queueMonitorThread = new Thread(queueMonitor);
		queueMonitorThread.start();
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
