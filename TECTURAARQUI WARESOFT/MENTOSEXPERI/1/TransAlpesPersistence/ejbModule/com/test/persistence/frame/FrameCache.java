package com.test.persistence.frame;

import java.util.HashMap;
import java.util.Map;

import com.test.dto.FrameDTO;

/**
 * @class FrameCache.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
class FrameCache<T extends FrameDTO> {

	private Map<Integer, T> frames;

	FrameCache() {
		this.frames = new HashMap<Integer, T>();
	}
	
	public void putFrame(Integer vehicleId, T t) {
		this.frames.put(vehicleId, t);
	}
	
	public T getFrame(Integer vehicleId) {
		return this.frames.get(vehicleId);
	}
	
	public boolean hasVehicleId(Integer vehicleId) {
		return this.frames.containsKey(vehicleId);
	}
}
