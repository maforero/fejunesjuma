package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

public class HeartBeatListener implements Runnable {

	private DatagramSocket socket;
	private boolean keepRunning;
	//It represents IpAddress -> Last heartbeat message received
	private HashMap<String, Long> lastBeatMessage;
	private int packetSize;
	
	
	public HeartBeatListener(int port) {
		keepRunning = true;
		lastBeatMessage = new HashMap<>();
		startSocket(port);
		getPacketSize();
	}
	
	/**
	 * @param port
	 */
	private void startSocket(int port) {
		try {
			socket = new DatagramSocket(port);
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
					System.out.println(ip);
					lastBeatMessage.put(
							ip, System.nanoTime());
					
				}
				
				String ipNode1 = InetAddress.getByName(
						ConfigurationManager.getInstance().getProperty(Properties.NODE_1_IP.name())).toString();
				String ipNode2 = InetAddress.getByName(
						ConfigurationManager.getInstance().getProperty(Properties.NODE_2_IP.name())).toString();
								
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
		double elapsedSecsTime = (double) elapsedNanoTime/1000000000.0;

		//Maximun reponse time node (Seconds)
		int maxRespondeTimeNode = Integer.valueOf(ConfigurationManager.getInstance().getProperty(
				Properties.MAX_REPONSE_TIME_NODE.name()));
		if (elapsedSecsTime <= maxRespondeTimeNode) {
			return true;
		}
		
		return false;
		
	}

}
