package com.test.frame;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.dto.AlarmFrameDTO;
import com.test.persistence.FramePersister;
import com.test.persistence.PersistenceServiceLocator;

/**
 * @class AlarmFrameDemultiplexer.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
class AlarmFrameDemultiplexer implements FrameDemultiplexer {

	private AlarmFrameDTO alarmFrameDTO;
	private PersistenceServiceLocator serviceLocator;
	private FrameSender frameSender;

	/**
	 * 
	 */
	public AlarmFrameDemultiplexer() {
		initServiceLocator();
		initNode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.frame.FrameDemultiplexer#demultiplex(byte[])
	 */
	@Override
	public void demultiplex(byte[] data) {
		getAlarmFrameDTOFromBytes(data);
		sendFrame();
		persistAlarm();
	}

	/**
	 * 
	 */
	private void initServiceLocator() {
		serviceLocator = new PersistenceServiceLocator();
	}

	/**
	 * 
	 */
	private void initNode() {
		try {
			InetAddress nodeIP = InetAddress.getByName(ConfigurationManager
					.getInstance().getProperty(Properties.NODE_2_IP.name()));
			int nodePort = Integer.parseInt(ConfigurationManager.getInstance()
					.getProperty(Properties.NODE_PORT2.name()));

			frameSender = new FrameSender(nodeIP, nodePort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param data
	 * @return
	 */
	private void getAlarmFrameDTOFromBytes(byte[] data) {
		AlarmFrameAdapter adapter = new AlarmFrameAdapter();
		adapter.adapt(data);
		alarmFrameDTO = adapter.getFrameDTO();
	}

	/**
	 * 
	 */
	private void sendFrame() {
		frameSender.sendFrame(alarmFrameDTO);
	}

	/**
	 * @param alarmFrameDTO
	 */
	private void persistAlarm() {
		try {
			serviceLocator.lookup();
			FramePersister persister = serviceLocator.getPersister();
			persister.insert(alarmFrameDTO);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
