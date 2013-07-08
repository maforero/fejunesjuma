/**
 * AlarmFrameDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.test.integration;

public class AlarmFrameDTO  extends com.test.integration.FrameDTO  implements java.io.Serializable {
    private int driverStatus;

    private int emergencyType;

    public AlarmFrameDTO() {
    }

    public AlarmFrameDTO(
           int laGrades,
           int laMinutes,
           int laSeconds,
           int laSign,
           int loGrades,
           int loMinutes,
           int loSeconds,
           int loSign,
           int vehicleId,
           int driverStatus,
           int emergencyType) {
        super(
            laGrades,
            laMinutes,
            laSeconds,
            laSign,
            loGrades,
            loMinutes,
            loSeconds,
            loSign,
            vehicleId);
        this.driverStatus = driverStatus;
        this.emergencyType = emergencyType;
    }


    /**
     * Gets the driverStatus value for this AlarmFrameDTO.
     * 
     * @return driverStatus
     */
    public int getDriverStatus() {
        return driverStatus;
    }


    /**
     * Sets the driverStatus value for this AlarmFrameDTO.
     * 
     * @param driverStatus
     */
    public void setDriverStatus(int driverStatus) {
        this.driverStatus = driverStatus;
    }


    /**
     * Gets the emergencyType value for this AlarmFrameDTO.
     * 
     * @return emergencyType
     */
    public int getEmergencyType() {
        return emergencyType;
    }


    /**
     * Sets the emergencyType value for this AlarmFrameDTO.
     * 
     * @param emergencyType
     */
    public void setEmergencyType(int emergencyType) {
        this.emergencyType = emergencyType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlarmFrameDTO)) return false;
        AlarmFrameDTO other = (AlarmFrameDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.driverStatus == other.getDriverStatus() &&
            this.emergencyType == other.getEmergencyType();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += getDriverStatus();
        _hashCode += getEmergencyType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlarmFrameDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://integration.test.com/", "alarmFrameDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("driverStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "driverStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emergencyType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "emergencyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
