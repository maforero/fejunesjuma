package com.test.persistence;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Remote;

import com.test.dto.FrameDTO;

/**
 * @class PersistenceInterface.java
 * @author Felipe
 * @Date Jul 7, 2013
 * @since 1.0
 */
@Remote
public interface FramePersister {

	public void insert(FrameDTO frameDTO) throws RemoteException;
	public List<FrameDTO> searchFrameDTO(int vehicleId) throws RemoteException;
}
