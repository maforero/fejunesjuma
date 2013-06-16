package com.test.dto;

/**
 * @class RegularFrame.java
 * @author Felipe
 * @Date Jun 15, 2013
 * @since 1.0
 */
public class RegularFrameDTO extends FrameDTO {

	private int vehicleType;
	private int availableSpace;
	private int roadTime;
	// estado de la carga
	private int wareState;
	private int temperature;

	/**
	 * @return the vehicleType
	 */
	public int getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType
	 *            the vehicleType to set
	 */
	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the availableSpace
	 */
	public int getAvailableSpace() {
		return availableSpace;
	}

	/**
	 * @param availableSpace
	 *            the availableSpace to set
	 */
	public void setAvailableSpace(int availableSpace) {
		this.availableSpace = availableSpace;
	}

	/**
	 * @return the roadTime
	 */
	public int getRoadTime() {
		return roadTime;
	}

	/**
	 * @param roadTime
	 *            the roadTime to set
	 */
	public void setRoadTime(int roadTime) {
		this.roadTime = roadTime;
	}

	/**
	 * @return the wareState
	 */
	public int getWareState() {
		return wareState;
	}

	/**
	 * @param wareState
	 *            the wareState to set
	 */
	public void setWareState(int wareState) {
		this.wareState = wareState;
	}

	/**
	 * @return the temperature
	 */
	public int getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

}
