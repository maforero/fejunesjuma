package com.test.persistence.frame;

import java.util.LinkedList;
import java.util.List;

import com.test.dto.AlarmFrameDTO;
import com.test.dto.FrameDTO;
import com.test.dto.RegularFrameDTO;

/**
 * @class FrameDao.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
public class FrameDAO {

	private static FrameDAO instance = new FrameDAO();

	private FrameCache<RegularFrameDTO> regularFrameCache;
	private FrameCache<AlarmFrameDTO> alarmFrameCache;

	private FrameDAO() {
		this.regularFrameCache = new FrameCache<RegularFrameDTO>();
		this.alarmFrameCache = new FrameCache<AlarmFrameDTO>();
	}

	public static FrameDAO getInstance() {
		return instance;
	}

	public void addFrame(FrameDTO frame) {
		if (frame instanceof RegularFrameDTO) {
			regularFrameCache.putFrame(frame.getVehicleId(),
					(RegularFrameDTO) frame);
		}
		if (frame instanceof AlarmFrameDTO) {
			alarmFrameCache.putFrame(frame.getVehicleId(),
					(AlarmFrameDTO) frame);
		}
	}

	public List<FrameDTO> searchFrameDTO(Integer vehicleId) {
		List<FrameDTO> frames = new LinkedList<FrameDTO>();

		if (regularFrameCache.hasVehicleId(vehicleId)) {
			frames.add(regularFrameCache.getFrame(vehicleId));
		}
		if (alarmFrameCache.hasVehicleId(vehicleId)) {
			frames.add(alarmFrameCache.getFrame(vehicleId));
		}

		return frames;
	}
}
