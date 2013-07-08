/**
 * FrameDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.test.integration;

public class FrameDTO  implements java.io.Serializable {
    private int laGrades;

    private int laMinutes;

    private int laSeconds;

    private int laSign;

    private int loGrades;

    private int loMinutes;

    private int loSeconds;

    private int loSign;

    private int vehicleId;

    public FrameDTO() {
    }

    public FrameDTO(
           int laGrades,
           int laMinutes,
           int laSeconds,
           int laSign,
           int loGrades,
           int loMinutes,
           int loSeconds,
           int loSign,
           int vehicleId) {
           this.laGrades = laGrades;
           this.laMinutes = laMinutes;
           this.laSeconds = laSeconds;
           this.laSign = laSign;
           this.loGrades = loGrades;
           this.loMinutes = loMinutes;
           this.loSeconds = loSeconds;
           this.loSign = loSign;
           this.vehicleId = vehicleId;
    }


    /**
     * Gets the laGrades value for this FrameDTO.
     * 
     * @return laGrades
     */
    public int getLaGrades() {
        return laGrades;
    }


    /**
     * Sets the laGrades value for this FrameDTO.
     * 
     * @param laGrades
     */
    public void setLaGrades(int laGrades) {
        this.laGrades = laGrades;
    }


    /**
     * Gets the laMinutes value for this FrameDTO.
     * 
     * @return laMinutes
     */
    public int getLaMinutes() {
        return laMinutes;
    }


    /**
     * Sets the laMinutes value for this FrameDTO.
     * 
     * @param laMinutes
     */
    public void setLaMinutes(int laMinutes) {
        this.laMinutes = laMinutes;
    }


    /**
     * Gets the laSeconds value for this FrameDTO.
     * 
     * @return laSeconds
     */
    public int getLaSeconds() {
        return laSeconds;
    }


    /**
     * Sets the laSeconds value for this FrameDTO.
     * 
     * @param laSeconds
     */
    public void setLaSeconds(int laSeconds) {
        this.laSeconds = laSeconds;
    }


    /**
     * Gets the laSign value for this FrameDTO.
     * 
     * @return laSign
     */
    public int getLaSign() {
        return laSign;
    }


    /**
     * Sets the laSign value for this FrameDTO.
     * 
     * @param laSign
     */
    public void setLaSign(int laSign) {
        this.laSign = laSign;
    }


    /**
     * Gets the loGrades value for this FrameDTO.
     * 
     * @return loGrades
     */
    public int getLoGrades() {
        return loGrades;
    }


    /**
     * Sets the loGrades value for this FrameDTO.
     * 
     * @param loGrades
     */
    public void setLoGrades(int loGrades) {
        this.loGrades = loGrades;
    }


    /**
     * Gets the loMinutes value for this FrameDTO.
     * 
     * @return loMinutes
     */
    public int getLoMinutes() {
        return loMinutes;
    }


    /**
     * Sets the loMinutes value for this FrameDTO.
     * 
     * @param loMinutes
     */
    public void setLoMinutes(int loMinutes) {
        this.loMinutes = loMinutes;
    }


    /**
     * Gets the loSeconds value for this FrameDTO.
     * 
     * @return loSeconds
     */
    public int getLoSeconds() {
        return loSeconds;
    }


    /**
     * Sets the loSeconds value for this FrameDTO.
     * 
     * @param loSeconds
     */
    public void setLoSeconds(int loSeconds) {
        this.loSeconds = loSeconds;
    }


    /**
     * Gets the loSign value for this FrameDTO.
     * 
     * @return loSign
     */
    public int getLoSign() {
        return loSign;
    }


    /**
     * Sets the loSign value for this FrameDTO.
     * 
     * @param loSign
     */
    public void setLoSign(int loSign) {
        this.loSign = loSign;
    }


    /**
     * Gets the vehicleId value for this FrameDTO.
     * 
     * @return vehicleId
     */
    public int getVehicleId() {
        return vehicleId;
    }


    /**
     * Sets the vehicleId value for this FrameDTO.
     * 
     * @param vehicleId
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FrameDTO)) return false;
        FrameDTO other = (FrameDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.laGrades == other.getLaGrades() &&
            this.laMinutes == other.getLaMinutes() &&
            this.laSeconds == other.getLaSeconds() &&
            this.laSign == other.getLaSign() &&
            this.loGrades == other.getLoGrades() &&
            this.loMinutes == other.getLoMinutes() &&
            this.loSeconds == other.getLoSeconds() &&
            this.loSign == other.getLoSign() &&
            this.vehicleId == other.getVehicleId();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getLaGrades();
        _hashCode += getLaMinutes();
        _hashCode += getLaSeconds();
        _hashCode += getLaSign();
        _hashCode += getLoGrades();
        _hashCode += getLoMinutes();
        _hashCode += getLoSeconds();
        _hashCode += getLoSign();
        _hashCode += getVehicleId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FrameDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://integration.test.com/", "frameDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("laGrades");
        elemField.setXmlName(new javax.xml.namespace.QName("", "laGrades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("laMinutes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "laMinutes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("laSeconds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "laSeconds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("laSign");
        elemField.setXmlName(new javax.xml.namespace.QName("", "laSign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loGrades");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loGrades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loMinutes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loMinutes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loSeconds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loSeconds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loSign");
        elemField.setXmlName(new javax.xml.namespace.QName("", "loSign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vehicleId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vehicleId"));
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
