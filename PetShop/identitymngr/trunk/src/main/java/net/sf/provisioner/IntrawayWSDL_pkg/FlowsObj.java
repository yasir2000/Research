/**
 * FlowsObj.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.provisioner.IntrawayWSDL_pkg;

public class FlowsObj  implements java.io.Serializable {
    private java.lang.String serviceFlow;

    private java.lang.String serviceClassifier;

    public FlowsObj() {
    }

    public FlowsObj(
           java.lang.String serviceFlow,
           java.lang.String serviceClassifier) {
           this.serviceFlow = serviceFlow;
           this.serviceClassifier = serviceClassifier;
    }


    /**
     * Gets the serviceFlow value for this FlowsObj.
     * 
     * @return serviceFlow
     */
    public java.lang.String getServiceFlow() {
        return serviceFlow;
    }


    /**
     * Sets the serviceFlow value for this FlowsObj.
     * 
     * @param serviceFlow
     */
    public void setServiceFlow(java.lang.String serviceFlow) {
        this.serviceFlow = serviceFlow;
    }


    /**
     * Gets the serviceClassifier value for this FlowsObj.
     * 
     * @return serviceClassifier
     */
    public java.lang.String getServiceClassifier() {
        return serviceClassifier;
    }


    /**
     * Sets the serviceClassifier value for this FlowsObj.
     * 
     * @param serviceClassifier
     */
    public void setServiceClassifier(java.lang.String serviceClassifier) {
        this.serviceClassifier = serviceClassifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FlowsObj)) return false;
        FlowsObj other = (FlowsObj) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceFlow==null && other.getServiceFlow()==null) || 
             (this.serviceFlow!=null &&
              this.serviceFlow.equals(other.getServiceFlow()))) &&
            ((this.serviceClassifier==null && other.getServiceClassifier()==null) || 
             (this.serviceClassifier!=null &&
              this.serviceClassifier.equals(other.getServiceClassifier())));
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
        if (getServiceFlow() != null) {
            _hashCode += getServiceFlow().hashCode();
        }
        if (getServiceClassifier() != null) {
            _hashCode += getServiceClassifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FlowsObj.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "flowsObj"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceFlow");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceFlow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceClassifier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceClassifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
