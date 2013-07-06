package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @class MessageListener.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class HeartBeatMessageListener implements Runnable {

	private DatagramSocket socket;
	private boolean keepRunning;
	private HeartbeatProcessor heartbeatProcessor;

	public HeartBeatMessageListener(int port) {
		keepRunning = true;
		startSocket(port);
		heartbeatProcessor = new HeartbeatProcessor(); 
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

	public void run() {
		while (keepRunning) {
			byte values[] = {1};
			DatagramPacket packet = new DatagramPacket(values, values.length);
			try {
				socket.receive(packet);
				heartbeatProcessor.processMessage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
