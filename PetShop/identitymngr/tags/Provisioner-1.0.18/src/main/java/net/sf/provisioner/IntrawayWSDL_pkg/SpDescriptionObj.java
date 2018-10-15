/**
 * SpDescriptionObj.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.provisioner.IntrawayWSDL_pkg;

public class SpDescriptionObj  implements java.io.Serializable {
    private java.lang.String servicePackageName;

    private java.lang.String servicePackageCRMId;

    private java.lang.String COS;

    private net.sf.provisioner.IntrawayWSDL_pkg.FlowsObj[] serviceFlowsUpstream;

    private net.sf.provisioner.IntrawayWSDL_pkg.FlowsObj[] serviceFlowsDownstream;

    private net.sf.provisioner.IntrawayWSDL_pkg.ComandosObj[] grupoComandosSNMP;

    private net.sf.provisioner.IntrawayWSDL_pkg.OpcionesObj[] opcionesAdicionales;

    public SpDescriptionObj() {
    }

    public SpDescriptionObj(
           java.lang.String servicePackageName,
           java.lang.String servicePackageCRMId,
           java.lang.String COS,
           net.sf.provisioner.IntrawayWSDL_pkg.FlowsObj[] serviceFlowsUpstream,
           net.sf.provisioner.IntrawayWSDL_pkg.FlowsObj[] serviceFlowsDownstream,
           net.sf.provisioner.IntrawayWSDL_pkg.ComandosObj[] grupoComandosSNMP,
           net.sf.provisioner.IntrawayWSDL_pkg.OpcionesObj[] opcionesAdicionales) {
           this.servicePackageName = servicePackageName;
           this.servicePackageCRMId = servicePackageCRMId;
           this.COS = COS;
           this.serviceFlowsUpstream = serviceFlowsUpstream;
           this.serviceFlowsDownstream = serviceFlowsDownstream;
           this.grupoComandosSNMP = grupoComandosSNMP;
           this.opcionesAdicionales = opcionesAdicionales;
    }


    /**
     * Gets the servicePackageName value for this SpDescriptionObj.
     * 
     * @return servicePackageName
     */
    public java.lang.String getServicePackageName() {
        return servicePackageName;
    }


    /**
     * Sets the servicePackageName value for this SpDescriptionObj.
     * 
     * @param servicePackageName
     */
    public void setServicePackageName(java.lang.String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }


    /**
     * Gets the servicePackageCRMId value for this SpDescriptionObj.
     * 
     * @return servicePackageCRMId
     */
    public java.lang.String getServicePackageCRMId() {
        return servicePackageCRMId;
    }


    /**
     * Sets the servicePackageCRMId value for this SpDescriptionObj.
     * 
     * @param servicePackageCRMId
     */
    public void setServicePackageCRMId(java.lang.String servicePackageCRMId) {
        this.servicePackageCRMId = servicePackageCRMId;
    }


    /**
     * Gets the COS value for this SpDescriptionObj.
     * 
     * @return COS
     */
    public java.lang.String getCOS() {
        return COS;
    }


    /**
     * Sets the COS value for this SpDescriptionObj.
     * 
     * @param COS
     */
    public void setCOS(java.lang.String COS) {
        this.COS = COS;
    }


    /**
     * Gets the serviceFlowsUpstream value for this SpDescriptionObj.
     * 
     * @return serviceFlowsUpstream
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.FlowsObj[] getServiceFlowsUpstream() {
        return serviceFlowsUpstream;
    }


    /**
     * Sets the serviceFlowsUpstream value for this SpDescriptionObj.
     * 
     * @param serviceFlowsUpstream
     */
    public void setServiceFlowsUpstream(net.sf.provisioner.IntrawayWSDL_pkg.FlowsObj[] serviceFlowsUpstream) {
        this.serviceFlowsUpstream = serviceFlowsUpstream;
    }


    /**
     * Gets the serviceFlowsDownstream value for this SpDescriptionObj.
     * 
     * @return serviceFlowsDownstream
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.FlowsObj[] getServiceFlowsDownstream() {
        return serviceFlowsDownstream;
    }


    /**
     * Sets the serviceFlowsDownstream value for this SpDescriptionObj.
     * 
     * @param serviceFlowsDownstream
     */
    public void setServiceFlowsDownstream(net.sf.provisioner.IntrawayWSDL_pkg.FlowsObj[] serviceFlowsDownstream) {
        this.serviceFlowsDownstream = serviceFlowsDownstream;
    }


    /**
     * Gets the grupoComandosSNMP value for this SpDescriptionObj.
     * 
     * @return grupoComandosSNMP
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.ComandosObj[] getGrupoComandosSNMP() {
        return grupoComandosSNMP;
    }


    /**
     * Sets the grupoComandosSNMP value for this SpDescriptionObj.
     * 
     * @param grupoComandosSNMP
     */
    public void setGrupoComandosSNMP(net.sf.provisioner.IntrawayWSDL_pkg.ComandosObj[] grupoComandosSNMP) {
        this.grupoComandosSNMP = grupoComandosSNMP;
    }


    /**
     * Gets the opcionesAdicionales value for this SpDescriptionObj.
     * 
     * @return opcionesAdicionales
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.OpcionesObj[] getOpcionesAdicionales() {
        return opcionesAdicionales;
    }


    /**
     * Sets the opcionesAdicionales value for this SpDescriptionObj.
     * 
     * @param opcionesAdicionales
     */
    public void setOpcionesAdicionales(net.sf.provisioner.IntrawayWSDL_pkg.OpcionesObj[] opcionesAdicionales) {
        this.opcionesAdicionales = opcionesAdicionales;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SpDescriptionObj)) return false;
        SpDescriptionObj other = (SpDescriptionObj) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.servicePackageName==null && other.getServicePackageName()==null) || 
             (this.servicePackageName!=null &&
              this.servicePackageName.equals(other.getServicePackageName()))) &&
            ((this.servicePackageCRMId==null && other.getServicePackageCRMId()==null) || 
             (this.servicePackageCRMId!=null &&
              this.servicePackageCRMId.equals(other.getServicePackageCRMId()))) &&
            ((this.COS==null && other.getCOS()==null) || 
             (this.COS!=null &&
              this.COS.equals(other.getCOS()))) &&
            ((this.serviceFlowsUpstream==null && other.getServiceFlowsUpstream()==null) || 
             (this.serviceFlowsUpstream!=null &&
              java.util.Arrays.equals(this.serviceFlowsUpstream, other.getServiceFlowsUpstream()))) &&
            ((this.serviceFlowsDownstream==null && other.getServiceFlowsDownstream()==null) || 
             (this.serviceFlowsDownstream!=null &&
              java.util.Arrays.equals(this.serviceFlowsDownstream, other.getServiceFlowsDownstream()))) &&
            ((this.grupoComandosSNMP==null && other.getGrupoComandosSNMP()==null) || 
             (this.grupoComandosSNMP!=null &&
              java.util.Arrays.equals(this.grupoComandosSNMP, other.getGrupoComandosSNMP()))) &&
            ((this.opcionesAdicionales==null && other.getOpcionesAdicionales()==null) || 
             (this.opcionesAdicionales!=null &&
              java.util.Arrays.equals(this.opcionesAdicionales, other.getOpcionesAdicionales())));
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
        if (getServicePackageName() != null) {
            _hashCode += getServicePackageName().hashCode();
        }
        if (getServicePackageCRMId() != null) {
            _hashCode += getServicePackageCRMId().hashCode();
        }
        if (getCOS() != null) {
            _hashCode += getCOS().hashCode();
        }
        if (getServiceFlowsUpstream() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getServiceFlowsUpstream());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getServiceFlowsUpstream(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getServiceFlowsDownstream() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getServiceFlowsDownstream());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getServiceFlowsDownstream(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGrupoComandosSNMP() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGrupoComandosSNMP());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGrupoComandosSNMP(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOpcionesAdicionales() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOpcionesAdicionales());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOpcionesAdicionales(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SpDescriptionObj.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "spDescriptionObj"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servicePackageName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServicePackageName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servicePackageCRMId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServicePackageCRMId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "COS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceFlowsUpstream");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceFlowsUpstream"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "flowsObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceFlowsDownstream");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceFlowsDownstream"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "flowsObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupoComandosSNMP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GrupoComandosSNMP"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "comandosObj"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opcionesAdicionales");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OpcionesAdicionales"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:IntrawayWSDL", "opcionesObj"));
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
