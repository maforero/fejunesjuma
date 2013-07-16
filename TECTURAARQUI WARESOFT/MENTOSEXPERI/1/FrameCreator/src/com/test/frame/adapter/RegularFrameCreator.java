package com.test.frame.adapter;

import com.test.dto.RegularFrameDTO;

/**
 * @class FrameDemultiplexer.java
 * @author Felipe
 * @Date Jun 15, 2013
 * @since 1.0
 */
class RegularFrameCreator extends AbstractFrameCreator {

	private RegularFrameDTO regularFrameDTO;

	public RegularFrameCreator(RegularFrameDTO regularFrameDTO) {
		this.regularFrameDTO = regularFrameDTO;
	}

	public void adapt() {
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
		bitFrame.append("0000");
		super.createFrame();
	}

	/**
	 * @param data
	 */
	private void addVehicleId() {
		createBitFrame(11, regularFrameDTO.getVehicleId());
	}

	/**
	 * @param data
	 */
	private void addTemperature() {
		if (regularFrameDTO.getTemperature() >= 0) {
			createBitFrame(1, 0);
		} else {
			regularFrameDTO.setTemperature(regularFrameDTO.getTemperature()
					* -1);
			createBitFrame(1, 1);
		}
		createBitFrame(7, (regularFrameDTO.getTemperature()));
	}

	/**
	 * @param data
	 */
	private void addWareState() {
		createBitFrame(1, (regularFrameDTO.getWareState()));
	}

	/**
	 * @param data
	 */
	private void addRoadTime() {
		createBitFrame(10, regularFrameDTO.getRoadTime());
	}

	/**
	 * @param data
	 */
	private void addAvailableSpace() {
		createBitFrame(7, regularFrameDTO.getAvailableSpace());
	}

	/**
	 * @param data
	 */
	private void addLongitudeSeconds() {
		createBitFrame(6, regularFrameDTO.getLoSeconds());
	}

	/**
	 * @param data
	 */
	private void addLongitudeMinutes() {
		createBitFrame(6, regularFrameDTO.getLoMinutes());
	}

	/**
	 * @param data
	 */
	private void addLontitudeGrades() {
		createBitFrame(7, regularFrameDTO.getLoGrades());
	}

	/**
	 * @param data
	 */
	private void addLongitudSign() {
		createBitFrame(1, regularFrameDTO.getLoSign());
	}

	/**
	 * @param data
	 */
	private void addLatitudeSeconds() {
		createBitFrame(6, regularFrameDTO.getLaSeconds());
	}

	/**
	 * @param data
	 */
	private void addLatitudeMinutes() {
		createBitFrame(6, regularFrameDTO.getLaMinutes());
	}

	/**
	 * @param data
	 */
	private void addLatitudeGrades() {
		createBitFrame(7, regularFrameDTO.getLaGrades());
	}

	/**
	 * @param data
	 */
	private void addLatitudeSign() {
		createBitFrame(1, regularFrameDTO.getLaSign());
	}

	/**
	 * @param data
	 */
	private void addVehicleType() {
		createBitFrame(3, regularFrameDTO.getVehicleType());
	}

	public static void main(String[] args) {
		RegularFrameDTO regularFrameDTO = new RegularFrameDTO();
		regularFrameDTO.setAvailableSpace(30);
		regularFrameDTO.setLaGrades(6);
		regularFrameDTO.setLaMinutes(16);
		regularFrameDTO.setLaSeconds(31);
		regularFrameDTO.setLaSign(0);
		regularFrameDTO.setLoSign(1);
		regularFrameDTO.setLoGrades(75);
		regularFrameDTO.setLoMinutes(33);
		regularFrameDTO.setLoSeconds(13);
		regularFrameDTO.setRoadTime(340);
		regularFrameDTO.setTemperature(20);
		regularFrameDTO.setVehicleId(10);
		regularFrameDTO.setVehicleType(7);
		regularFrameDTO.setWareState(1);
		
		RegularFrameCreator creator = new RegularFrameCreator(regularFrameDTO);
		creator.adapt();
		System.out.println(creator.getFrame());
	}
}
