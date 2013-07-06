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
		System.out.print(frame.getVehicleType());
		System.out.print(frame.getLaSign());
		System.out.print(frame.getLaGrades());
		System.out.print(frame.getLaMinutes());
		System.out.print(frame.getLaSeconds());
		System.out.print(frame.getLoSign());
		System.out.print(frame.getLoGrades());
		System.out.print(frame.getLoMinutes());
		System.out.print(frame.getLoSeconds());
		System.out.print(frame.getAvailableSpace());
		System.out.print(frame.getRoadTime());
		System.out.print(frame.getWareState());
		System.out.print(frame.getTemperature());
		System.out.println(frame.getVehicleId());
		
		System.out.println(counter++);
	}
}
