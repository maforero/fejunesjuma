package com.test.connection;

import java.rmi.RemoteException;

import com.test.byteutil.ByteUtils;
import com.test.dto.AlarmFrameDTO;
import com.test.integration.IntegrationWSProxy;
import com.test.monitoring.Trace;

/**
 * @class LoadMessageProcessor.java
 * @author Felipe
 * @Date Jun 16, 2013
 * @since 1.0
 */
public class AlarmMessageProcessor implements MessageProcessor {

	public AlarmMessageProcessor() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.test.connection.MessageProcessor#processMessage(com.test.monitoring
	 * .TransAlpesMonitor)
	 */
	@Override
	public void processMessage(Trace monitor) {
		IntegrationWSProxy proxy = new IntegrationWSProxy();
		try {
			AlarmFrameDTO alarmFrame = ByteUtils.getInstance().deserialize(
					monitor.getData());
			com.test.integration.AlarmFrameDTO wsAlarm = getWSAlarm(alarmFrame);
			proxy.sendAlarm(wsAlarm);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param alarmFrame
	 * @return
	 */
	private com.test.integration.AlarmFrameDTO getWSAlarm(
			AlarmFrameDTO alarmFrame) {
		com.test.integration.AlarmFrameDTO wsAlarm = new com.test.integration.AlarmFrameDTO();
		wsAlarm.setDriverStatus(alarmFrame.getDriverStatus());
		wsAlarm.setEmergencyType(alarmFrame.getEmergencyType());
		wsAlarm.setLaGrades(alarmFrame.getLaGrades());
		wsAlarm.setLaMinutes(alarmFrame.getLaMinutes());
		wsAlarm.setLaSeconds(alarmFrame.getLaSeconds());
		wsAlarm.setLaSign(alarmFrame.getLaSign());
		wsAlarm.setLoGrades(alarmFrame.getLoGrades());
		wsAlarm.setLoMinutes(alarmFrame.getLoMinutes());
		wsAlarm.setLoSeconds(alarmFrame.getLoSeconds());
		wsAlarm.setLoSign(alarmFrame.getLoSign());
		wsAlarm.setVehicleId(alarmFrame.getVehicleId());
		return wsAlarm;
	}
}
