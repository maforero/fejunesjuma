package com.test.main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
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
		boolean showResults = showResults();
		if (!showResults) {
			ThreadManager.getInstance().loadFrames();
		}
	}

	/**
	 * @return
	 */
	private static boolean showResults() {
		try {
			boolean showResults = Boolean.valueOf(ConfigurationManager
					.getInstance().getProperty(Properties.SHOW_RESULTS.name()));
			if (showResults) {
				String nodes = ConfigurationManager.getInstance().getProperty(
						Properties.NODES.name());
				String nodeValues[] = nodes.split(";");
				DatagramSocket socket = new DatagramSocket();
				byte data[] = { 127, 127, 127, 127 };
				for (String nodeValue : nodeValues) {
					String value[] = nodeValue.split(":");
					InetAddress ip = InetAddress.getByName(value[0]);
					int port = Integer.parseInt(value[1]);
					DatagramPacket packet = new DatagramPacket(data,
							data.length, ip, port);
					socket.send(packet);
				}
			}
			return showResults;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
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
