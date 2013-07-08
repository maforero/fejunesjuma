package com.test.frame;

import com.test.dto.AlarmFrameDTO;
import com.test.dto.FrameDTO;

/**
 * @class AlarmFrameDemultiplexer.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
class AlarmFrameAdapter {

	private AlarmFrameDTO alarmFrameDTO;

	public AlarmFrameAdapter() {
		alarmFrameDTO = new AlarmFrameDTO();
	}

	public void adapt(byte[] data) {

		addVehicleId(data);
		addLatitudeSign(data);
		addLatitudeGrades(data);
		addLatitudeMinutes(data);
		addLatitudeSeconds(data);
		addLongitudeSign(data);
		addLongitudeGrades(data);
		addLongitudeMinutes(data);
		addLongitudeSeconds(data);
		addEmergencyType(data);
		addDriverStatus(data);
	}

	public AlarmFrameDTO getFrameDTO() {
		return alarmFrameDTO;
	}
	
	/**
	 * @param data
	 */
	private void addDriverStatus(byte[] data) {
		alarmFrameDTO.setDriverStatus(data[7] >> 3 & 1);
	}

	/**
	 * @param data
	 */
	private void addEmergencyType(byte[] data) {
		alarmFrameDTO.setEmergencyType(data[7] >> 4 & 1);
	}

	/**
	 * @param data
	 */
	private void addLongitudeSeconds(byte[] data) {
		alarmFrameDTO.setLoSeconds((data[6] << 3) | (data[7] >> 5));
	}

	/**
	 * @param data
	 */
	private void addLongitudeMinutes(byte[] data) {
		alarmFrameDTO.setLoMinutes((data[5] << 4) | (data[6] >> 4));
	}

	/**
	 * @param data
	 */
	private void addLongitudeGrades(byte[] data) {
		alarmFrameDTO.setLoGrades((data[4] << 4) | (data[5] >> 3));
	}

	/**
	 * @param data
	 */
	private void addLongitudeSign(byte[] data) {
		alarmFrameDTO.setLoSign(data[4] >> 3 & 1);
	}

	/**
	 * @param data
	 */
	private void addLatitudeSeconds(byte[] data) {
		alarmFrameDTO.setLaSeconds((data[3] << 4) | (data[4] >> 4));
	}

	/**
	 * @param data
	 */
	private void addLatitudeMinutes(byte[] data) {
		alarmFrameDTO.setLaMinutes((data[2] << 5) | (data[3] >> 3));
	}

	/**
	 * @param data
	 */
	private void addLatitudeGrades(byte[] data) {
		alarmFrameDTO.setLaGrades(((data[1] & 3) << 5) | (data[2] >> 2));
	}

	/**
	 * @param data
	 */
	private void addLatitudeSign(byte[] data) {
		alarmFrameDTO.setLaSign(data[1] >> 2 & 1);
	}

	/**
	 * @param data
	 */
	private void addVehicleId(byte[] data) {
		alarmFrameDTO.setVehicleId((data[0] << 4) | (data[1] >> 3));
	}

}
