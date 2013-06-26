package com.test.thread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.dto.RegularFrameDTO;
import com.test.frame.FrameDemultiplexer;
import com.test.monitoring.Trace;

/**
 * @class PoolThread.java
 * @author Felipe
 * @Date Jun 14, 2013
 * @since 1.0
 */
public class Worker extends Thread {

	private BlockingQueue<Trace> threadQueue;
	private boolean keepRunning;
	private FrameDemultiplexer demultiplexer;
	private InetAddress nodeIP;
	private int nodePort;
	private DatagramSocket socket;

	public Worker(BlockingQueue<Trace> threadQueue) {
		this.threadQueue = threadQueue;
		keepRunning = true;
		demultiplexer = new FrameDemultiplexer();
		initNode();
		initSocket();
	}

	/**
	 * 
	 */
	private void initNode() {
		try {
			nodeIP = InetAddress.getByName(ConfigurationManager.getInstance()
					.getProperty(Properties.NODE_1_IP.name()));
			nodePort = Integer.parseInt(ConfigurationManager.getInstance()
					.getProperty(Properties.NODE_1_PORT.name()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void initSocket() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void run() {
		while (keepRunning) {
			Trace monitor;
			try {
				monitor = threadQueue.take();
				demultiplexData(monitor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param data
	 */
	private void demultiplexData(Trace monitor) {
		RegularFrameDTO frame = demultiplexer.demultiplexFrame(monitor);
		byte data[] = serialize(frame);
		sendFrames(data);
	}
	
	/**
	 * @param trace
	 */
	private void sendFrames(byte data[]) {
		DatagramPacket packet = new DatagramPacket(data, data.length, nodeIP,
				nodePort);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private byte[] serialize(Object obj) {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    try {
			ObjectOutputStream os = new ObjectOutputStream(out);
			os.writeObject(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public synchronized void stopThread() {
		keepRunning = false;
		this.interrupt();
	}

	public boolean isRunning() {
		return keepRunning;
	}
}
