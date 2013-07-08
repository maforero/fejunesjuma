/**
 * IntegrationWSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.test.integration;

public interface IntegrationWSService extends javax.xml.rpc.Service {
    public java.lang.String getIntegrationWSPortAddress();

    public com.test.integration.IntegrationWS getIntegrationWSPort() throws javax.xml.rpc.ServiceException;

    public com.test.integration.IntegrationWS getIntegrationWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
