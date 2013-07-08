package com.test.integration;

import java.rmi.RemoteException;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.tempuri.Service1SoapProxy;

import com.test.configuration.ConfigurationManager;
import com.test.configuration.Properties;
import com.test.dto.AlarmFrameDTO;

/**
 * @class IntegrationWS.java
 * @author Felipe
 * @Date Jul 6, 2013
 * @since 1.0
 */
@Stateless
@WebService
public class IntegrationWS {

	@PostConstruct
	private void initConfigurations() {
		String propertiesPath = System.getenv("INTEGRATION_PROPERTIES");
		ConfigurationManager.getInstance().loadProperties(propertiesPath);
	}

	@WebMethod(operationName = "sendAlarm")
	public String sendAlarm(@WebParam(name = "alarm") AlarmFrameDTO alarm) {
		String policeEndPoint = ConfigurationManager.getInstance().getProperty(
				Properties.POLICE_END_POINT.name());
		System.out.println(policeEndPoint+" --------------");
		Service1SoapProxy proxy = new Service1SoapProxy(policeEndPoint);
		try {
			int[] alarmLatitude = new int[] { alarm.getLaGrades(),
					alarm.getLaMinutes(), alarm.getLaSeconds() };
			return proxy.alarmaPolicia(String.valueOf(alarm.getVehicleId()),
					alarmLatitude,
					new int[] { alarm.getLoGrades(), alarm.getLoMinutes(),
							alarm.getLoSeconds() });
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}

	@WebMethod(operationName = "getVehicleInfo")
	public String[] getVehicleInfo(
			@WebParam(name = "vehicleId") String vechileId) {

		return new String[] { "latitud, longitud, id" };
	}

}
