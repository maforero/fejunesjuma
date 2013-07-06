package com.test.integration;

import java.rmi.RemoteException;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.tempuri.Service1SoapProxy;

/**
 * @class IntegrationWS.java
 * @author Felipe
 * @Date Jul 6, 2013
 * @since 1.0
 */
@Stateless
@WebService
public class IntegrationWS {

	@WebMethod(operationName = "sendAlarm")
	public String sendAlarm(@WebParam(name = "name") String name) {
		Service1SoapProxy proxy = new Service1SoapProxy("http://localhost/WebPolicia/Service1.asmx?WSDL");
		try {
			return proxy.alarmaPolicia("12345", new int[] { 123, 234, 434 },
					new int[] { 23, 456, 234 });
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}

	@WebMethod(operationName = "getVehicleInfo")
	public String[] getVehicleInfo(
			@WebParam(name = "vehicleId") String vechileId) {
		
		return new String[]{"latitud, longitud, id"};
	}
	
}
