package com.test.integration;

import java.rmi.RemoteException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.tempuri.Service1SoapProxy;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.dto.AlarmFrameDTO;
import com.test.dto.FrameDTO;
import com.test.dto.RegularFrameDTO;
import com.test.persistence.FramePersister;

/**
 * @class IntegrationWS.java
 * @author Felipe
 * @Date Jul 6, 2013
 * @since 1.0
 */
@Stateless
@WebService
public class IntegrationWS {

	@EJB
	private FramePersister framePersister;

	@PostConstruct
	private void initConfigurations() {
		String propertiesPath = System.getenv("INTEGRATION_PROPERTIES");
		ConfigurationManager.getInstance().loadProperties(propertiesPath);
	}

	@WebMethod(operationName = "sendAlarm")
	public String sendAlarm(@WebParam(name = "alarm") AlarmFrameDTO alarm) {
		return sendAlarmToPolice(alarm);
	}

	@WebMethod(operationName = "getVehicleInfo")
	public String[] getVehicleInfo(
			@WebParam(name = "vehicleId") String vehicleId) {

		try {
			List<FrameDTO> frames = framePersister.searchFrameDTO(Integer
					.valueOf(vehicleId));

			if (isNotNullOrEmpty(frames)) {
				FrameDTO frame = frames.get(0);
				if (frame instanceof RegularFrameDTO) {
					return getRegularFrame(frame);
				}
				if (frame instanceof AlarmFrameDTO) {
					return getAlarmFrame(frame);
				}
			}

		} catch (NumberFormatException | RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param frames
	 * @return
	 */
	private boolean isNotNullOrEmpty(List<FrameDTO> frames) {
		return frames != null && !frames.isEmpty();
	}

	/**
	 * @param frame
	 * @return
	 */
	private String[] getRegularFrame(FrameDTO frame) {
		RegularFrameDTO regularFrame = (RegularFrameDTO) frame;
		return new String[] {
				String.valueOf(regularFrame.getVehicleType()),
				String.format("%s,%s,%s", regularFrame.getLaGrades(),
						regularFrame.getLaMinutes(),
						regularFrame.getLaSeconds()),
				String.format("%s,%s,%s", regularFrame.getLoGrades(),
						regularFrame.getLoMinutes(),
						regularFrame.getLoSeconds()),
				String.valueOf(regularFrame.getAvailableSpace()),
				String.valueOf(regularFrame.getRoadTime()),
				String.valueOf(regularFrame.getWareState()),
				String.valueOf(regularFrame.getTemperature()) };
	}

	/**
	 * @param frame
	 * @return
	 */
	private String[] getAlarmFrame(FrameDTO frame) {
		AlarmFrameDTO alarmFrameDTO = (AlarmFrameDTO) frame;
		return new String[] {
				String.format("%s,%s,%s", alarmFrameDTO.getLaGrades(),
						alarmFrameDTO.getLaMinutes(),
						alarmFrameDTO.getLaSeconds()),
				String.format("%s,%s,%s", alarmFrameDTO.getLoGrades(),
						alarmFrameDTO.getLoMinutes(),
						alarmFrameDTO.getLoSeconds()),
				String.valueOf(alarmFrameDTO.getEmergencyType()),
				String.valueOf(alarmFrameDTO.getDriverStatus()) };
	}

	/**
	 * @param alarm
	 */
	private String sendAlarmToPolice(AlarmFrameDTO alarm) {
		String policeEndPoint = getPoliceEndPoint();
		Service1SoapProxy proxy = new Service1SoapProxy(policeEndPoint);
		try {
			int[] alarmLatitude = getAlarmLatitude(alarm);
			int[] alarmLongitude = getAlarmLongitude(alarm);
			String vehicleId = String.valueOf(alarm.getVehicleId());
			return proxy
					.alarmaPolicia(vehicleId, alarmLatitude, alarmLongitude);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @return
	 */
	private String getPoliceEndPoint() {
		String policeEndPoint = ConfigurationManager.getInstance().getProperty(
				Properties.POLICE_END_POINT.name());
		return policeEndPoint;
	}

	/**
	 * @param alarm
	 * @return
	 */
	private int[] getAlarmLongitude(AlarmFrameDTO alarm) {
		return new int[] { alarm.getLoGrades(), alarm.getLoMinutes(),
				alarm.getLoSeconds() };
	}

	/**
	 * @param alarm
	 * @return
	 */
	private int[] getAlarmLatitude(AlarmFrameDTO alarm) {
		int[] alarmLatitude = new int[] { alarm.getLaGrades(),
				alarm.getLaMinutes(), alarm.getLaSeconds() };
		return alarmLatitude;
	}
}
