package com.test.persistence;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;

import com.test.dto.FrameDTO;
import com.test.persistence.frame.FrameDAO;

/**
 * @class FramePersister.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
@Stateless
public class FramePersisterBean implements FramePersister {

	private FrameDAO frameDAO = FrameDAO.getInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.persistence.FramePersister#insert(com.test.dto.FrameDTO)
	 */
	@Override
	public void insert(FrameDTO frameDTO) throws RemoteException {
		System.out.println(frameDTO.getVehicleId());
		frameDAO.addFrame(frameDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.persistence.FramePersister#searchFrameDTO(int)
	 */
	@Override
	public List<FrameDTO> searchFrameDTO(int vehicleId) throws RemoteException {
		return frameDAO.searchFrameDTO(vehicleId);
	}

}
