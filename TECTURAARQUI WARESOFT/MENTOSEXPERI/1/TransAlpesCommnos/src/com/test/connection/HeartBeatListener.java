package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Calendar;
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
	 * 
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
				
				//AGREGAR Y VERIFICAR EL ULTIMO
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
