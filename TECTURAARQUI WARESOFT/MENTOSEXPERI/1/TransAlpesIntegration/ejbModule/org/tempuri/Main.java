package org.tempuri;

import java.rmi.RemoteException;

/**
 * @class Main.java
 * @author Felipe
 * @Date Jul 6, 2013
 * @since 1.0
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Service1SoapProxy proxy = new Service1SoapProxy("http://localhost/WebPolicia/Service1.asmx?WSDL");
		try {
			System.out.println(proxy.alarmaPolicia("1231", new int[]{3,3,3}, new int[]{3,3,3}));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
