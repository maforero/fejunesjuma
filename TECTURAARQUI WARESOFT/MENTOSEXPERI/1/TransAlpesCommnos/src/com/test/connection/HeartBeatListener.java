package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

public class HeartBeatListener implements Runnable {

	private DatagramSocket socket;
	private boolean keepRunning;
	//It represents IpAddress -> Last heartbeat message received
	private HashMap<String, Long> lastBeatMessage;
	private int packetSize;
	private String ipNode1;
	private String ipNode2;
	
	
	public HeartBeatListener(int port, HashMap<String, Long> lastBeatMessage) {
		keepRunning = true;
		this.lastBeatMessage = lastBeatMessage;
		startSocket(port);
		getPacketSize();
		
		try {
			ipNode1 = InetAddress.getByName(
					ConfigurationManager.getInstance().getProperty(Properties.NODE_1_IP.name())).toString();
			ipNode2 = InetAddress.getByName(
					ConfigurationManager.getInstance().getProperty(Properties.NODE_2_IP.name())).toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param port
	 */
	private void startSocket(int port) {
		try {
			socket = new DatagramSocket(port);
//			socket.setSoTimeout(5);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets default packet size
	 */
	private void getPacketSize() {
		String packet = ConfigurationManager.getInstance().getProperty(
				Properties.HEARTBEAT_PACKET_SIZE.name());
		packetSize = Integer.valueOf(packet);
	}
	
	@Override
	public void run() {
		while (keepRunning) {			
			byte values[] = new byte[packetSize];
			DatagramPacket packet = new DatagramPacket(values, values.length);
			try {
				socket.receive(packet);
				if (packet.getData() != null) {
					String ip = packet.getAddress().toString().replace("/", "");
					lastBeatMessage.put(
							ip, System.nanoTime());
					
				}
								
				if (lastBeatMessage.get(ipNode1) != null) {
					if (!isNodeAlive(ipNode1)) {
						lastBeatMessage.remove(ipNode1);
					}
				}				
				if (lastBeatMessage.get(ipNode2) != null) {
					if (!isNodeAlive(ipNode2)) {
						lastBeatMessage.remove(ipNode1);
					}
				}
			} catch (SocketTimeoutException e) { 
				//continua
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Checks if the node is alive
	 * @param ipNode Node Ip Address
	 * @return true isAlive, false isNotAlive
	 */
	private boolean isNodeAlive(String ipNode) {
		Long actualTime = System.nanoTime();
		Long elapsedNanoTime = actualTime - lastBeatMessage.get(ipNode);
		double elapsedSecsTime = (double) elapsedNanoTime/1000000.0;

		//Maximun reponse time node (Seconds)
		int maxRespondeTimeNode = Integer.valueOf(ConfigurationManager.getInstance().getProperty(
				Properties.MAX_REPONSE_TIME_NODE.name()));
		if (elapsedSecsTime <= maxRespondeTimeNode) {
			return true;
		}
		
		return false;
		
	}

}
