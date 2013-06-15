package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

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
	private Runnable queueMonitor;

	public MessageListener(Runnable queueMonitor) {
		queueManager = QueueManager.getInstance();
		this.queueMonitor = queueMonitor;
		keepRunning = true;
		try {
			socket = new DatagramSocket(9999);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (keepRunning) {
			byte values[] = new byte[1000];
			DatagramPacket packet = new DatagramPacket(values, values.length);
			try {
				socket.receive(packet);
				String message = new String(packet.getData());
				queueManager.addMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
