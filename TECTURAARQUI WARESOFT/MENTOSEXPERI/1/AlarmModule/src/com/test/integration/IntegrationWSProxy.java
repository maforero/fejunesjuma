package com.test.integration;

public class IntegrationWSProxy implements com.test.integration.IntegrationWS {
  private String _endpoint = null;
  private com.test.integration.IntegrationWS integrationWS = null;
  
  public IntegrationWSProxy() {
    _initIntegrationWSProxy();
  }
  
  public IntegrationWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initIntegrationWSProxy();
  }
  
  private void _initIntegrationWSProxy() {
    try {
      integrationWS = (new com.test.integration.IntegrationWSServiceLocator()).getIntegrationWSPort();
      if (integrationWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)integrationWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)integrationWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (integrationWS != null)
      ((javax.xml.rpc.Stub)integrationWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.test.integration.IntegrationWS getIntegrationWS() {
    if (integrationWS == null)
      _initIntegrationWSProxy();
    return integrationWS;
  }
  
  public java.lang.String sendAlarm(com.test.integration.AlarmFrameDTO alarm) throws java.rmi.RemoteException{
    if (integrationWS == null)
      _initIntegrationWSProxy();
    return integrationWS.sendAlarm(alarm);
  }
  
  public java.lang.String[] getVehicleInfo(java.lang.String vehicleId) throws java.rmi.RemoteException{
    if (integrationWS == null)
      _initIntegrationWSProxy();
    return integrationWS.getVehicleInfo(vehicleId);
  }
  
  public void initConfigurations() throws java.rmi.RemoteException{
    if (integrationWS == null)
      _initIntegrationWSProxy();
    integrationWS.initConfigurations();
  }
  
  
}