package com.test.frame;

import com.test.dto.AlarmFrameDTO;

/**
 * @class AlarmFrameDemultiplexer.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
class AlarmFrameAdapter extends AbstractFrameAdapter {

	private AlarmFrameDTO alarmFrameDTO;

	public AlarmFrameAdapter() {
		alarmFrameDTO = new AlarmFrameDTO();
	}

	public void adapt(byte[] data) {
		createBitFrame(data);
		addVehicleId();
		addLatitudeSign();
		addLatitudeGrades();
		addLatitudeMinutes();
		addLatitudeSeconds();
		addLongitudeSign();
		addLongitudeGrades();
		addLongitudeMinutes();
		addLongitudeSeconds();
		addEmergencyType();
		addDriverStatus();
		setLocationSigns();
	}

	public AlarmFrameDTO getFrameDTO() {
		return alarmFrameDTO;
	}

	/**
	 * @param data
	 */
	private void addDriverStatus() {
		int value = getFrameBitValue(1);
		alarmFrameDTO.setDriverStatus(value);
	}

	/**
	 * @param data
	 */
	private void addEmergencyType() {
		int value = getFrameBitValue(1);
		alarmFrameDTO.setEmergencyType(value);
	}

	/**
	 * @param data
	 */
	private void addLongitudeSeconds() {
		int value = getFrameBitValue(6);
		alarmFrameDTO.setLoSeconds(value);
	}

	/**
	 * @param data
	 */
	private void addLongitudeMinutes() {
		int value = getFrameBitValue(6);
		alarmFrameDTO.setLoMinutes(value);
	}

	/**
	 * @param data
	 */
	private void addLongitudeGrades() {
		int value = getFrameBitValue(7);
		alarmFrameDTO.setLoGrades(value);
	}

	/**
	 * @param data
	 */
	private void addLongitudeSign() {
		int value = getFrameBitValue(1);
		alarmFrameDTO.setLoSign(value);
	}

	/**
	 * @param data
	 */
	private void addLatitudeSeconds() {
		int value = getFrameBitValue(6);
		alarmFrameDTO.setLaSeconds(value);
	}

	/**
	 * @param data
	 */
	private void addLatitudeMinutes() {
		int value = getFrameBitValue(6);
		alarmFrameDTO.setLaMinutes(value);
	}

	/**
	 * @param data
	 */
	private void addLatitudeGrades() {
		int value = getFrameBitValue(7);
		alarmFrameDTO.setLaGrades(value);
	}

	/**
	 * @param data
	 */
	private void addLatitudeSign() {
		int value = getFrameBitValue(1);
		alarmFrameDTO.setLaSign(value);
	}

	/**
	 * @param data
	 */
	private void addVehicleId() {
		int value = getFrameBitValue(11);
		alarmFrameDTO.setVehicleId(value);
	}

	/**
	 * 
	 */
	private void setLocationSigns() {
		if (alarmFrameDTO.getLaSign() == 1) {
			alarmFrameDTO.setLaGrades(alarmFrameDTO.getLaGrades() * -1);
		}
		if (alarmFrameDTO.getLoSign() == 1) {
			alarmFrameDTO.setLoGrades(alarmFrameDTO.getLoGrades() * -1);
		}
	}
	
	public static void main(String[] args) {
		byte data[] = {127,126,107,95,77,87,62,120};
		AlarmFrameAdapter adapter = new AlarmFrameAdapter();
		adapter.adapt(data);
		AlarmFrameDTO frame = adapter.getFrameDTO();
		System.out.println(frame.getVehicleId());
		System.out.println(frame.getLaSign());
		System.out.println(frame.getLaGrades());
		System.out.println(frame.getLaMinutes());
		System.out.println(frame.getLaSeconds());
		System.out.println(frame.getLoSign());
		System.out.println(frame.getLoGrades());
		System.out.println(frame.getLoMinutes());
		System.out.println(frame.getLoSeconds());
		System.out.println(frame.getEmergencyType());
		System.out.println(frame.getDriverStatus());
	}
}
