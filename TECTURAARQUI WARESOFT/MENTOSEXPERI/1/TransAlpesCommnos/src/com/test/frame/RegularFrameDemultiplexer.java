package com.test.frame;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import com.test.byteutil.ByteUtils;
import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.dto.RegularFrameDTO;
import com.test.persistence.PersistenceServiceLocator;

/**
 * @class RegularFrameDemultiplexer.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
class RegularFrameDemultiplexer implements FrameDemultiplexer {

	private InetAddress nodeIP;
	private int nodePort;
	private DatagramSocket socket;
	private RegularFrameDTO frame;

	public RegularFrameDemultiplexer() {
		startNode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.frame.FrameDemultiplexer#demultiplex(byte[])
	 */
	@Override
	public void demultiplex(byte[] data) {
		adaptToFrame(data);
		sendFrame();
		persistFrame();
	}

	/**
	 * 
	 */
	private void startNode() {
		initNode();
		initSocket();
	}

	/**
	 * 
	 */
	private void sendFrame() {
		byte serializedFrame[] = ByteUtils.getInstance().serialize(frame);
		sendFrames(serializedFrame);
	}

	/**
	 * @param data
	 */
	private void adaptToFrame(byte[] data) {
		RegularFrameAdapter adapter = new RegularFrameAdapter();
		adapter.adapt(data);
		frame = adapter.getFrameDTO();
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

	/**
	 * @param frameDTO
	 */
	private void persistFrame() {
		PersistenceServiceLocator serviceLocator = new PersistenceServiceLocator();
		try {
			serviceLocator.getPersister().insert(frame);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
