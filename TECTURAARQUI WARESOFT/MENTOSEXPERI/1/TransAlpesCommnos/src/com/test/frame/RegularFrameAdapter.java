package com.test.frame;

import com.test.dto.RegularFrameDTO;

/**
 * @class FrameDemultiplexer.java
 * @author Felipe
 * @Date Jun 15, 2013
 * @since 1.0
 */
class RegularFrameAdapter {

	private RegularFrameDTO regularFrameDTO;

	public RegularFrameAdapter() {
		regularFrameDTO = new RegularFrameDTO();
	}

	public void adapt(byte[] data) {
		addVehicleType(data);
		addLatitudeSign(data);
		addLatitudeGrades(data);
		addLatitudeMinutes(data);
		addLatitudeSeconds(data);
		addLongitudSign(data);
		addLontitudeGrades(data);
		addLongitudeMinutes(data);
		addLongitudeSeconds(data);
		addAvailableSpace(data);
		addRoadTime(data);
		addWareState(data);
		addTemperature(data);
		addVehicleId(data);
	}

	public RegularFrameDTO getFrameDTO() {
		return regularFrameDTO;
	}

	/**
	 * @param data
	 */
	private void addVehicleId(byte[] data) {
		regularFrameDTO.setVehicleId((data[10] << 4) | (data[11] >> 3));
	}

	/**
	 * @param data
	 */
	private void addTemperature(byte[] data) {
		regularFrameDTO.setTemperature(((data[8] & 1) << 7) | data[9]);
	}

	/**
	 * @param data
	 */
	private void addWareState(byte[] data) {
		regularFrameDTO.setWareState((data[8] >> 1) & 1);
	}

	/**
	 * @param data
	 */
	private void addRoadTime(byte[] data) {
		regularFrameDTO.setRoadTime(((data[7] & 31) << 5) | (data[8] >> 2));
	}

	/**
	 * @param data
	 */
	private void addAvailableSpace(byte[] data) {
		regularFrameDTO.setAvailableSpace(((data[6] & 31) << 2)
				| (data[7] >> 5));
	}

	/**
	 * @param data
	 */
	private void addLongitudeSeconds(byte[] data) {
		regularFrameDTO.setLoSeconds(((data[5] & 15) << 3) | (data[6] >> 5));
	}

	/**
	 * @param data
	 */
	private void addLongitudeMinutes(byte[] data) {
		regularFrameDTO.setLoMinutes(((data[4] & 7) << 4) | (data[5] >> 4));
	}

	/**
	 * @param data
	 */
	private void addLontitudeGrades(byte[] data) {
		regularFrameDTO.setLoGrades((((data[3]) & 15) << 3) | (data[4] >> 3));
	}

	/**
	 * @param data
	 */
	private void addLongitudSign(byte[] data) {
		regularFrameDTO.setLoSign(((data[3] >> 4) & 1));
	}

	/**
	 * @param data
	 */
	private void addLatitudeSeconds(byte[] data) {
		regularFrameDTO.setLaSeconds(((data[2] & 15) << 3) | (data[3] >> 5));
	}

	/**
	 * @param data
	 */
	private void addLatitudeMinutes(byte[] data) {
		regularFrameDTO.setLaMinutes(((data[1] & 7) << 4) | (data[2] >> 4));
	}

	/**
	 * @param data
	 */
	private void addLatitudeGrades(byte[] data) {
		regularFrameDTO.setLaGrades(((data[0] & 7) << 4) | (data[1] >> 3));
	}

	/**
	 * @param data
	 */
	private void addLatitudeSign(byte[] data) {
		regularFrameDTO.setLaSign((data[0] >> 3) & 1);
	}

	/**
	 * @param data
	 */
	private void addVehicleType(byte[] data) {
		regularFrameDTO.setVehicleType(data[0] >> 4);
	}
}
