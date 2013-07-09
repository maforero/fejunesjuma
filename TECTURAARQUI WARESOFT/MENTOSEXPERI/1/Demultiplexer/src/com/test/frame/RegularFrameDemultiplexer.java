package com.test.frame;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

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

	private RegularFrameDTO frame;
	private PersistenceServiceLocator serviceLocator;
	private FrameSender frameSender;

	public RegularFrameDemultiplexer() {
		serviceLocator = new PersistenceServiceLocator();
		initNode();
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
	private void initNode() {
		try {
			InetAddress nodeIP = InetAddress.getByName(ConfigurationManager
					.getInstance().getProperty(Properties.NODE_1_IP.name()));
			int nodePort = Integer.parseInt(ConfigurationManager.getInstance()
					.getProperty(Properties.NODE_1_PORT.name()));

			frameSender = new FrameSender(nodeIP, nodePort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
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
	private void sendFrame() {
		frameSender.sendFrame(frame);
	}

	/**
	 * @param frameDTO
	 */
	private void persistFrame() {
		try {
			serviceLocator.lookup();
			serviceLocator.getPersister().insert(frame);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
