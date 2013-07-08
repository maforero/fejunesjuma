/**
 * IntegrationWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.test.integration;

public interface IntegrationWS extends java.rmi.Remote {
    public java.lang.String sendAlarm(com.test.integration.AlarmFrameDTO alarm) throws java.rmi.RemoteException;
    public java.lang.String[] getVehicleInfo(java.lang.String vehicleId) throws java.rmi.RemoteException;
    public void initConfigurations() throws java.rmi.RemoteException;
}
