package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.monitoring.Monitor;
import com.test.monitoring.Trace;

/**
 * @class MessageListener.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class MessageListener implements Runnable {

	private DatagramSocket socket;
	private boolean keepRunning;
	private MessageProcessor processor;
	private int packetSize;

	public MessageListener(int port) {
		processor = MessageProcessorFactory.getInstance().getMessageProcessor();
		keepRunning = true;
		getPacketSize();
		startSocket(port);
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
				Properties.PACKET_SIZE.name());
		packetSize = Integer.valueOf(packet);
	}

	public void run() {
		while (keepRunning) {
			byte values[] = new byte[packetSize];
			DatagramPacket packet = new DatagramPacket(values, values.length);
			try {
				socket.receive(packet);
				if (packet.getData()[0] == 127) {
					Monitor.getInstance().printTraces();
					continue;
				}
				Trace monitor = Trace.getInstance();
				long nanoTime = System.nanoTime();
				monitor.addTime(nanoTime);
				byte[] data = packet.getData();
				monitor.setData(data);
				processor.processMessage(monitor);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
