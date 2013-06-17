package com.test.frame;

import com.test.dto.AlarmFrameDTO;
import com.test.dto.RegularFrameDTO;
import com.test.module.GenericModule;
import com.test.monitoring.TransAlpesMonitor;

/**
 * @class FrameDemultiplexer.java
 * @author Felipe
 * @Date Jun 15, 2013
 * @since 1.0
 */
public class FrameDemultiplexer {

	public RegularFrameDTO demultiplexFrame(TransAlpesMonitor monitor) {

		byte data[] = monitor.getData();
		
		RegularFrameDTO regularFrameDTO = new RegularFrameDTO();
		regularFrameDTO.setVehicleType(data[0] >> 4);
		regularFrameDTO.setLaSign((data[0] >> 3) & 1);
		regularFrameDTO.setLaGrades(((data[0] & 7) << 4) | (data[1] >> 3));
		regularFrameDTO.setLaMinutes(((data[1] & 7) << 4) | (data[2] >> 4));
		regularFrameDTO.setLaSeconds(((data[2] & 15) << 3) | (data[3] >> 5));
		regularFrameDTO.setLoSign(((data[3] >> 4) & 1));
		regularFrameDTO.setLoGrades((((data[3]) & 15) << 3) | (data[4] >> 3));
		regularFrameDTO.setLoMinutes(((data[4] & 7) << 4) | (data[5] >> 4));
		regularFrameDTO.setLoSeconds(((data[5] & 15) << 3) | (data[6] >> 5));
		regularFrameDTO.setAvailableSpace(((data[6] & 31) << 2) | (data[7] >> 5));
		regularFrameDTO.setRoadTime(((data[7] & 31) << 5) | (data[8] >> 2));
		regularFrameDTO.setWareState((data[8] >> 1) & 1);
		regularFrameDTO.setTemperature(((data[8] & 1) << 7) | data[9]);
		regularFrameDTO.setVehicleId((data[10] << 4) | (data[11] >> 3));
		
		monitor.addEndTrace(System.nanoTime());
		monitor.printTrace();
		return regularFrameDTO;
	}

	public AlarmFrameDTO demultiplexAlarmFrame(byte[] data) {

		return null;
	}
}
