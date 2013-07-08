/**
 * IntegrationWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.test.integration;

public class IntegrationWSServiceLocator extends org.apache.axis.client.Service implements com.test.integration.IntegrationWSService {

    public IntegrationWSServiceLocator() {
    }


    public IntegrationWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IntegrationWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IntegrationWSPort
    private java.lang.String IntegrationWSPort_address = "http://felipe-pc:8080/IntegrationWSService/IntegrationWS";

    public java.lang.String getIntegrationWSPortAddress() {
        return IntegrationWSPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IntegrationWSPortWSDDServiceName = "IntegrationWSPort";

    public java.lang.String getIntegrationWSPortWSDDServiceName() {
        return IntegrationWSPortWSDDServiceName;
    }

    public void setIntegrationWSPortWSDDServiceName(java.lang.String name) {
        IntegrationWSPortWSDDServiceName = name;
    }

    public com.test.integration.IntegrationWS getIntegrationWSPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IntegrationWSPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIntegrationWSPort(endpoint);
    }

    public com.test.integration.IntegrationWS getIntegrationWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.test.integration.IntegrationWSPortBindingStub _stub = new com.test.integration.IntegrationWSPortBindingStub(portAddress, this);
            _stub.setPortName(getIntegrationWSPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIntegrationWSPortEndpointAddress(java.lang.String address) {
        IntegrationWSPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.test.integration.IntegrationWS.class.isAssignableFrom(serviceEndpointInterface)) {
                com.test.integration.IntegrationWSPortBindingStub _stub = new com.test.integration.IntegrationWSPortBindingStub(new java.net.URL(IntegrationWSPort_address), this);
                _stub.setPortName(getIntegrationWSPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("IntegrationWSPort".equals(inputPortName)) {
            return getIntegrationWSPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://integration.test.com/", "IntegrationWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://integration.test.com/", "IntegrationWSPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IntegrationWSPort".equals(portName)) {
            setIntegrationWSPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
