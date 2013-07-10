package com.test.frame;

import com.test.dto.RegularFrameDTO;

/**
 * @class FrameDemultiplexer.java
 * @author Felipe
 * @Date Jun 15, 2013
 * @since 1.0
 */
class RegularFrameAdapter extends AbstractFrameAdapter {

	private RegularFrameDTO regularFrameDTO;

	public RegularFrameAdapter() {
		regularFrameDTO = new RegularFrameDTO();
	}

	public void adapt(byte[] data) {
		createBitFrame(data);
		addVehicleType();
		addLatitudeSign();
		addLatitudeGrades();
		addLatitudeMinutes();
		addLatitudeSeconds();
		addLongitudSign();
		addLontitudeGrades();
		addLongitudeMinutes();
		addLongitudeSeconds();
		addAvailableSpace();
		addRoadTime();
		addWareState();
		addTemperature();
		addVehicleId();
		setLocationSigns();
	}

	public RegularFrameDTO getFrameDTO() {
		return regularFrameDTO;
	}
	
	/**
	 * 
	 */
	private void setLocationSigns() {
		if (regularFrameDTO.getLaSign() == 1) {
			regularFrameDTO.setLaGrades(regularFrameDTO.getLaGrades() * -1);
		}
		if (regularFrameDTO.getLoSign() == 1) {
			regularFrameDTO.setLoGrades(regularFrameDTO.getLoGrades() * -1);
		}
	}

	/**
	 * @param data
	 */
	private void addVehicleId() {
		int value = getFrameBitValue(11);
		regularFrameDTO.setVehicleId(value);
	}

	/**
	 * @param data
	 */
	private void addTemperature() {
		int signValue = getFrameBitValue(1);
		int value = getFrameBitValue(7);
		if (signValue == 1) {
			value = value * -1;
		}
		// regularFrameDTO.setTemperature(((data[8] & 1) << 7) | data[9]);
		regularFrameDTO.setTemperature(value);
	}

	/**
	 * @param data
	 */
	private void addWareState() {
		int value = getFrameBitValue(1);
		regularFrameDTO.setWareState(value);
	}

	/**
	 * @param data
	 */
	private void addRoadTime() {
		int value = getFrameBitValue(10);
		regularFrameDTO.setRoadTime(value);
	}

	/**
	 * @param data
	 */
	private void addAvailableSpace() {
		int value = getFrameBitValue(7);
		regularFrameDTO.setAvailableSpace(value);
	}

	/**
	 * @param data
	 */
	private void addLongitudeSeconds() {
		int value = getFrameBitValue(6);
		regularFrameDTO.setLoSeconds(value);
	}

	/**
	 * @param data
	 */
	private void addLongitudeMinutes() {
		int value = getFrameBitValue(6);
		regularFrameDTO.setLoMinutes(value);
	}

	/**
	 * @param data
	 */
	private void addLontitudeGrades() {
		int value = getFrameBitValue(7);
		regularFrameDTO.setLoGrades(value);
	}

	/**
	 * @param data
	 */
	private void addLongitudSign() {
		int value = getFrameBitValue(1);
		regularFrameDTO.setLoSign(value);
	}

	/**
	 * @param data
	 */
	private void addLatitudeSeconds() {
		int value = getFrameBitValue(6);
		regularFrameDTO.setLaSeconds(value);
	}

	/**
	 * @param data
	 */
	private void addLatitudeMinutes() {
		int value = getFrameBitValue(6);
		regularFrameDTO.setLaMinutes(value);
	}

	/**
	 * @param data
	 */
	private void addLatitudeGrades() {
		int value = getFrameBitValue(7);
		regularFrameDTO.setLaGrades(value);
	}

	/**
	 * @param data
	 */
	private void addLatitudeSign() {
		int value = getFrameBitValue(1);
		regularFrameDTO.setLaSign(value);
	}

	/**
	 * @param data
	 */
	private void addVehicleType() {
		int value = getFrameBitValue(3);
		regularFrameDTO.setVehicleType(value);
	}
	
}
