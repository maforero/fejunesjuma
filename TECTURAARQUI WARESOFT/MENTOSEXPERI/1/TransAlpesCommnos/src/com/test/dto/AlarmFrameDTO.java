package com.test.dto;

/**
 * @class AlarmFrame.java
 * @author Felipe
 * @Date Jun 15, 2013
 * @since 1.0
 */
public class AlarmFrameDTO extends FrameDTO {

	private int emergencyType;
	private int driverStatus;

	/**
	 * @return the emergencyType
	 */
	public int getEmergencyType() {
		return emergencyType;
	}

	/**
	 * @param emergencyType
	 *            the emergencyType to set
	 */
	public void setEmergencyType(int emergencyType) {
		this.emergencyType = emergencyType;
	}

	/**
	 * @return the driverStatus
	 */
	public int getDriverStatus() {
		return driverStatus;
	}

	/**
	 * @param driverStatus
	 *            the driverStatus to set
	 */
	public void setDriverStatus(int driverStatus) {
		this.driverStatus = driverStatus;
	}

}
