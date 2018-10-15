/**
 * MaintenanceObjInput.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.provisioner.IntrawayWSDL_pkg;

public class MaintenanceObjInput  implements java.io.Serializable {
    private java.lang.String idEntradaCaller;

    private java.lang.String idEmpresaCRM;

    private java.lang.String idServicio;

    private java.lang.String idVenta;

    private java.lang.String idProducto;

    private java.lang.String xmlEncoding;

    public MaintenanceObjInput() {
    }

    public MaintenanceObjInput(
           java.lang.String idEntradaCaller,
           java.lang.String idEmpresaCRM,
           java.lang.String idServicio,
           java.lang.String idVenta,
           java.lang.String idProducto,
           java.lang.String xmlEncoding) {
           this.idEntradaCaller = idEntradaCaller;
           this.idEmpresaCRM = idEmpresaCRM;
           this.idServicio = idServicio;
           this.idVenta = idVenta;
           this.idProducto = idProducto;
           this.xmlEncoding = xmlEncoding;
    }


    /**
     * Gets the idEntradaCaller value for this MaintenanceObjInput.
     * 
     * @return idEntradaCaller
     */
    public java.lang.String getIdEntradaCaller() {
        return idEntradaCaller;
    }


    /**
     * Sets the idEntradaCaller value for this MaintenanceObjInput.
     * 
     * @param idEntradaCaller
     */
    public void setIdEntradaCaller(java.lang.String idEntradaCaller) {
        this.idEntradaCaller = idEntradaCaller;
    }


    /**
     * Gets the idEmpresaCRM value for this MaintenanceObjInput.
     * 
     * @return idEmpresaCRM
     */
    public java.lang.String getIdEmpresaCRM() {
        return idEmpresaCRM;
    }


    /**
     * Sets the idEmpresaCRM value for this MaintenanceObjInput.
     * 
     * @param idEmpresaCRM
     */
    public void setIdEmpresaCRM(java.lang.String idEmpresaCRM) {
        this.idEmpresaCRM = idEmpresaCRM;
    }


    /**
     * Gets the idServicio value for this MaintenanceObjInput.
     * 
     * @return idServicio
     */
    public java.lang.String getIdServicio() {
        return idServicio;
    }


    /**
     * Sets the idServicio value for this MaintenanceObjInput.
     * 
     * @param idServicio
     */
    public void setIdServicio(java.lang.String idServicio) {
        this.idServicio = idServicio;
    }


    /**
     * Gets the idVenta value for this MaintenanceObjInput.
     * 
     * @return idVenta
     */
    public java.lang.String getIdVenta() {
        return idVenta;
    }


    /**
     * Sets the idVenta value for this MaintenanceObjInput.
     * 
     * @param idVenta
     */
    public void setIdVenta(java.lang.String idVenta) {
        this.idVenta = idVenta;
    }


    /**
     * Gets the idProducto value for this MaintenanceObjInput.
     * 
     * @return idProducto
     */
    public java.lang.String getIdProducto() {
        return idProducto;
    }


    /**
     * Sets the idProducto value for this MaintenanceObjInput.
     * 
     * @param idProducto
     */
    public void setIdProducto(java.lang.String idProducto) {
        this.idProducto = idProducto;
    }


    /**
     * Gets the xmlEncoding value for this MaintenanceObjInput.
     * 
     * @return xmlEncoding
     */
    public java.lang.String getXmlEncoding() {
        return xmlEncoding;
    }


    /**
     * Sets the xmlEncoding value for this MaintenanceObjInput.
     * 
     * @param xmlEncoding
     */
    public void setXmlEncoding(java.lang.String xmlEncoding) {
        this.xmlEncoding = xmlEncoding;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MaintenanceObjInput)) return false;
        MaintenanceObjInput other = (MaintenanceObjInput) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idEntradaCaller==null && other.getIdEntradaCaller()==null) || 
             (this.idEntradaCaller!=null &&
              this.idEntradaCaller.equals(other.getIdEntradaCaller()))) &&
            ((this.idEmpresaCRM==null && other.getIdEmpresaCRM()==null) || 
             (this.idEmpresaCRM!=null &&
              this.idEmpresaCRM.equals(other.getIdEmpresaCRM()))) &&
            ((this.idServicio==null && other.getIdServicio()==null) || 
             (this.idServicio!=null &&
              this.idServicio.equals(other.getIdServicio()))) &&
            ((this.idVenta==null && other.getIdVenta()==null) || 
             (this.idVenta!=null &&
              this.idVenta.equals(other.getIdVenta()))) &&
            ((this.idProducto==null && other.getIdProducto()==null) || 
             (this.idProducto!=null &&
              this.idProducto.equals(other.getIdProducto()))) &&
            ((this.xmlEncoding==null && other.getXmlEncoding()==null) || 
             (this.xmlEncoding!=null &&
              this.xmlEncoding.equals(other.getXmlEncoding())));
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
        if (getIdEntradaCaller() != null) {
            _hashCode += getIdEntradaCaller().hashCode();
        }
        if (getIdEmpresaCRM() != null) {
            _hashCode += getIdEmpresaCRM().hashCode();
        }
        if (getIdServicio() != null) {
            _hashCode += getIdServicio().hashCode();
        }
        if (getIdVenta() != null) {
            _hashCode += getIdVenta().hashCode();
        }
        if (getIdProducto() != null) {
            _hashCode += getIdProducto().hashCode();
        }
        if (getXmlEncoding() != null) {
            _hashCode += getXmlEncoding().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MaintenanceObjInput.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "MaintenanceObjInput"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEntradaCaller");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idEntradaCaller"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEmpresaCRM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idEmpresaCRM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServicio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idServicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idVenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idVenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProducto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idProducto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xmlEncoding");
        elemField.setXmlName(new javax.xml.namespace.QName("", "xmlEncoding"));
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
