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
		int i = 0;
		while (keepRunning) {			
			byte values[] = new byte[packetSize];
			DatagramPacket packet = new DatagramPacket(values, values.length);
			String node1 = ipNode1.replace("/", "");
			String node2 = ipNode2.replace("/", "");
//			System.out.println(ipNode1+" "+ipNode2);
			try {
				socket.receive(packet);
				String ip = "";
				if (packet.getData() != null) {
					ip = packet.getAddress().toString().replace("/", "");
					lastBeatMessage.put(
							ip, System.nanoTime());
				}
//				System.out.println(lastBeatMessage);
				if (i > 3) {
					if (lastBeatMessage.get(node1) != null) {
						if (!isNodeAlive(node1)) {
							lastBeatMessage.remove(node1);
						}
					}
					if (lastBeatMessage.get(node2) != null) {
						if (!isNodeAlive(node2)) {
							lastBeatMessage.remove(node2);
						}
					}
				}
				i++;
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
//		System.out.println(ipNode);
//		System.out.println("nano "+elapsedNanoTime);
//		System.out.println("sec "+elapsedSecsTime);

		//Maximun reponse time node (Seconds)
		int maxRespondeTimeNode = Integer.valueOf(ConfigurationManager.getInstance().getProperty(
				Properties.MAX_REPONSE_TIME_NODE.name()));
		if (elapsedSecsTime <= maxRespondeTimeNode) {
			return true;
		}
		
		return false;
		
	}

}
