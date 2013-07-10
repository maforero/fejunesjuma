package com.test.frame.adapter;

import com.test.dto.AlarmFrameDTO;

/**
 * @class AlarmFrameDemultiplexer.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
class AlarmFrameCreator extends AbstractFrameCreator {

	private AlarmFrameDTO alarmFrameDTO;

	public AlarmFrameCreator(AlarmFrameDTO alarmFrameDTO) {
		this.alarmFrameDTO = alarmFrameDTO;
	}

	public void createFrame() {
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
		bitFrame.append("000");
		super.createFrame();
	}

	/**
	 * @param data
	 */
	private void addDriverStatus() {
		createBitFrame(1, alarmFrameDTO.getDriverStatus());
	}

	/**
	 * @param data
	 */
	private void addEmergencyType() {
		createBitFrame(1, alarmFrameDTO.getEmergencyType());
	}

	/**
	 * @param data
	 */
	private void addLongitudeSeconds() {
		createBitFrame(6, alarmFrameDTO.getLoSeconds());
	}

	/**
	 * @param data
	 */
	private void addLongitudeMinutes() {
		createBitFrame(6, alarmFrameDTO.getLoMinutes());
	}

	/**
	 * @param data
	 */
	private void addLongitudeGrades() {
		createBitFrame(7, alarmFrameDTO.getLoGrades());
	}

	/**
	 * @param data
	 */
	private void addLongitudeSign() {
		createBitFrame(1, alarmFrameDTO.getLoSign());
	}

	/**
	 * @param data
	 */
	private void addLatitudeSeconds() {
		createBitFrame(6, alarmFrameDTO.getLaSeconds());
	}

	/**
	 * @param data
	 */
	private void addLatitudeMinutes() {
		createBitFrame(6, alarmFrameDTO.getLaMinutes());
	}

	/**
	 * @param data
	 */
	private void addLatitudeGrades() {
		createBitFrame(7, alarmFrameDTO.getLaGrades());
	}

	/**
	 * @param data
	 */
	private void addLatitudeSign() {
		createBitFrame(1, alarmFrameDTO.getLaSign());
	}

	/**
	 * @param data
	 */
	private void addVehicleId() {
		createBitFrame(11, alarmFrameDTO.getVehicleId());
	}
	
	public static void main(String[] args) {
		AlarmFrameDTO alarmFrameDTO = new AlarmFrameDTO();
		alarmFrameDTO.setDriverStatus(1);
		alarmFrameDTO.setEmergencyType(1);
		alarmFrameDTO.setLaGrades(4);
		alarmFrameDTO.setLaMinutes(43);
		alarmFrameDTO.setLaSeconds(20);
		alarmFrameDTO.setLaSign(0);
		alarmFrameDTO.setLoGrades(74);
		alarmFrameDTO.setLoMinutes(3);
		alarmFrameDTO.setLoSeconds(3);
		alarmFrameDTO.setLoSign(1);
		alarmFrameDTO.setVehicleId(1);
		
		AlarmFrameCreator alarmFrameCreator = new AlarmFrameCreator(alarmFrameDTO);
		alarmFrameCreator.createFrame();
		System.out.println(alarmFrameCreator.getFrame());
	}

}
