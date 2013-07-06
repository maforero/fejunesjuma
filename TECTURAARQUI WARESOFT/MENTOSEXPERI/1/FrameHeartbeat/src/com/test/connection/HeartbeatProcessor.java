package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

/**
 * @class LoadMessageProcessor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class HeartbeatProcessor {

	private DatagramSocket socket;
	private InetAddress balancerIP;
	private int balancerPort;
	private List<Object[]> pingModules;

	public HeartbeatProcessor() {
		initBalancerNode();
		initSocket();
		initPingModules();
		sendHeartbeat(new byte[] { 1 });
	}
	
	public void processMessage() {
		byte data[] = { 1 };
		sendHeartbeat(data);
	}

	private void initPingModules() {
		try {
			String modulesConfig = ConfigurationManager.getInstance()
					.getProperty(Properties.MODULES_IP.name());
			String modules[] = modulesConfig.split(";");
			pingModules = new ArrayList<Object[]>(modules.length);
			for (String module : modules) {
				String values[] = module.split(":");
				Object moduleValues[] = new Object[2];
				moduleValues[0] = InetAddress.getByName(values[0]);
				moduleValues[1] = Integer.valueOf(values[1]);
				pingModules.add(moduleValues);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void initBalancerNode() {
		try {
			balancerIP = InetAddress.getByName(ConfigurationManager
					.getInstance().getProperty(Properties.BALANCER_IP.name()));
			balancerPort = Integer.parseInt(ConfigurationManager.getInstance()
					.getProperty(Properties.BALANCER_PORT.name()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void initSocket() {
		try {
			int pingPortListener = Integer.parseInt(ConfigurationManager
					.getInstance().getProperty(
							Properties.PING_PORT_LISTENER.name()));
			socket = new DatagramSocket(pingPortListener);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	private void sendPing(byte[] data) throws IOException {
		for (Object module[] : pingModules) {
			boolean reSend;
			do {
				reSend = false;
				try {
					DatagramPacket packet = new DatagramPacket(data,
							data.length, (InetAddress) module[0],
							(Integer) module[1]);
					socket.send(packet);
					socket.setSoTimeout(50);
					socket.receive(packet);
				} catch (SocketTimeoutException e) {
					reSend = true;
				}
			} while (reSend);
		}
	}

	/**
	 * @param data
	 * @throws IOException
	 */
	private void sendHeartbeat(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length,
				balancerIP, balancerPort);
		try {
			sendPing(data);
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			packet = null;
		}
	}

}
