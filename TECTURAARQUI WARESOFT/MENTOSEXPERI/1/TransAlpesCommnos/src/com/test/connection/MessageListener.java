package com.test.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.test.byteutil.ByteUtils;
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
		this(port, MessageProcessorFactory.getInstance().getMessageProcessor());
	}
	
	public MessageListener(int port, MessageProcessor processor) {
		this.processor = processor;
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
				if (packet.getData()[0] == 127 && packet.getData()[1] == 127 && packet.getData()[2] == 127 && packet.getData()[3] == 127) {
					Monitor.getInstance().printTraces();
					continue;
				}
				Trace monitor = Trace.getInstance();
				long nanoTime = System.nanoTime();
				monitor.addTime(nanoTime);
				byte[] data = packet.getData();
				data = removeEmtpyBytes(data);
				monitor.setData(data);
				processor.processMessage(monitor);
				ByteUtils.getInstance().printFrames(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param data
	 * @return
	 */
	private byte[] removeEmtpyBytes(byte[] data) {
		
		byte[] realBytes;
		int index;
		byte lastValue = data[data.length - 1];
		for (index = data.length - 2; index >= 0; index--) {
			if (data[index] == 127 && lastValue == 127) {
				break;
			} else {
				lastValue = data[index]; 
			}
		}
		
		realBytes = new byte[index];
		
		for (int i = 0; i < index; i++) {
			realBytes[i] = data[i];
		}
		
		return realBytes;
	}

}
