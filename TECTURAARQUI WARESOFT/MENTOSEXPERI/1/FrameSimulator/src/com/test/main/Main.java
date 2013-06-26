package com.test.main;

import com.test.configuration.ConfigurationManager;
import com.test.thread.ThreadManager;


/**
 * @class Main.java
 * @author Felipe
 * @Date Jun 18, 2013
 * @since 1.0
 */
public class Main {

	public static void main(String[] args) {
		loadConfigurations(args);
		ThreadManager.getInstance().loadFrames();
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
