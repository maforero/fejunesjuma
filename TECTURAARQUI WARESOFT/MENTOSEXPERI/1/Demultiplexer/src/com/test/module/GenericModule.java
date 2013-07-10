package com.test.module;

import com.test.dto.RegularFrameDTO;

/**
 * @class GenericModule.java
 * @author Felipe
 * @Date Jun 15, 2013
 * @since 1.0
 */
public class GenericModule {

	static int counter;
	
	public void doSomething(RegularFrameDTO frame) {
		System.out.println("vehicleId "+frame.getVehicleId());
		System.out.println("vehicleType "+frame.getVehicleType());
		System.out.println("la grades "+frame.getLaGrades());
		System.out.println("la minutes "+frame.getLaMinutes());
		System.out.println("la seconds "+frame.getLaSeconds());
		System.out.println("lo grades "+frame.getLoGrades());
		System.out.println("lo minutes "+frame.getLoMinutes());
		System.out.println("lo seconds "+frame.getLoSeconds());
		System.out.println("available space "+frame.getAvailableSpace());
		System.out.println("roadTime "+frame.getRoadTime());
		System.out.println("wareState "+frame.getWareState());
		System.out.println("temperature "+frame.getTemperature());
	}
}
