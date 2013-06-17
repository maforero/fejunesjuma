package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.test.monitoring.TransAlpesMonitor;
import com.test.queue.QueueManager;

/**
 * @class MessageListener.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class MessageListener implements Runnable {

	private DatagramSocket socket;
	private boolean keepRunning;
	private QueueManager queueManager;

	public MessageListener() {
		queueManager = QueueManager.getInstance();
		keepRunning = true;
		try {
			socket = new DatagramSocket(9999);
			socket.setReceiveBufferSize(1000000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (keepRunning) {
			byte values[] = new byte[12];
			DatagramPacket packet = new DatagramPacket(values, values.length);
			try {
				socket.receive(packet);
				TransAlpesMonitor monitor = TransAlpesMonitor.getInstance();
				monitor.addInitTrace(System.nanoTime());
				byte[] data = packet.getData();
				monitor.setData(data);
				queueManager.addMessage(monitor);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
