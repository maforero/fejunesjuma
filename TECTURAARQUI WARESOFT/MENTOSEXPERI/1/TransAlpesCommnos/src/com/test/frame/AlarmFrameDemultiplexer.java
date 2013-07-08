package com.test.frame;

import java.rmi.RemoteException;

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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.frame.FrameDemultiplexer#demultiplex(byte[])
	 */
	@Override
	public void demultiplex(byte[] data) {
		getAlarmFrameDTOFromBytes(data);
		persistAlarm();
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
	 * @param alarmFrameDTO
	 */
	private void persistAlarm() {
		PersistenceServiceLocator serviceLocator = new PersistenceServiceLocator();
		try {
			serviceLocator.getPersister().insert(alarmFrameDTO);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		AlarmFrameDTO alarmFrameDTO = new AlarmFrameDTO();
		alarmFrameDTO.setVehicleId(123);
		alarmFrameDTO.setLaGrades(234);
		
		PersistenceServiceLocator serviceLocator = new PersistenceServiceLocator();
		try {
			FramePersister persister = serviceLocator.getPersister();
			persister.insert(alarmFrameDTO);
			System.out.println(persister.searchFrameDTO(123));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
