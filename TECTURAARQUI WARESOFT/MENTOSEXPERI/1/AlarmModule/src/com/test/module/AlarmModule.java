package com.test.module;

import com.test.dto.AlarmFrameDTO;

/**
 * @class GenericModule.java
 * @author Felipe
 * @Date Jun 15, 2013
 * @since 1.0
 */
public class AlarmModule {

	public void doSomething(AlarmFrameDTO frame) {
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
