package com.test.thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;

/**
 * @class ThreadExecutor.java
 * @author Felipe
 * @Date Jun 19, 2013
 * @since 1.0
 */
public class ThreadExecutor implements Runnable {

	private List<byte[]> frames;
	// by thread
	private long loadSize;
	// miliseconds
	private long sleepTime;
	private DatagramSocket socket;
	private InetAddress address;
	private int port;
	
	public ThreadExecutor(List<byte[]> frames) {
		ConfigurationManager configurationManager = ConfigurationManager.getInstance();
		initSocket(configurationManager);
		this.frames = frames;
		port = Integer.valueOf(configurationManager.getProperty(Properties.BALANCER_PORT.name()));
		loadSize = Long.parseLong(configurationManager.getProperty(Properties.LOAD_SIZE.name()));
		long loadTime = Long.parseLong(configurationManager.getProperty(Properties.LOAD_TIME.name()));
		sleepTime = loadTime / loadSize;
	}

	/**
	 * @param configurationManager
	 */
	private void initSocket(ConfigurationManager configurationManager) {
		try {
			address = InetAddress.getByName(configurationManager
					.getProperty(Properties.BALANCER_IP.name()));
			socket=new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		int index = 0;
		int packetIndex = 0;
		while (index < loadSize) {
			long initTime = System.currentTimeMillis();
			byte data[] = frames.get(packetIndex++);
			packetIndex = getPacketIndex(packetIndex);
			sendPacket(data);
			index++;
			sleep(initTime);			
		}
	}

	/**
	 * @param packetIndex
	 * @return
	 */
	private int getPacketIndex(int packetIndex) {
		if (packetIndex >= frames.size()) {
			packetIndex = 0;
		}
		return packetIndex;
	}

	/**
	 * @param data
	 */
	private void sendPacket(byte[] data) {
		try {
			DatagramPacket enviarPaquete = new DatagramPacket(data,
					data.length, address, port);
			socket.send(enviarPaquete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param initTime
	 */
	private void sleep(long initTime) {
		try {
			long elapsedTime = System.currentTimeMillis() - initTime;
			long sleep = sleepTime - elapsedTime;
			if (sleep > 0) {
				Thread.sleep(sleep);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
