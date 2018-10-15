/**
 * IntrawayWSDLLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.provisioner.IntrawayWSDL_pkg;

public class IntrawayWSDLLocator extends org.apache.axis.client.Service implements net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDL {

    public IntrawayWSDLLocator() {
    }


    public IntrawayWSDLLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IntrawayWSDLLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IntrawayWSDLPort
    private java.lang.String IntrawayWSDLPort_address = "http://intraway/IntrawayWS/server.php";

    public java.lang.String getIntrawayWSDLPortAddress() {
        return IntrawayWSDLPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IntrawayWSDLPortWSDDServiceName = "IntrawayWSDLPort";

    public java.lang.String getIntrawayWSDLPortWSDDServiceName() {
        return IntrawayWSDLPortWSDDServiceName;
    }

    public void setIntrawayWSDLPortWSDDServiceName(java.lang.String name) {
        IntrawayWSDLPortWSDDServiceName = name;
    }

    public net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLPortType getIntrawayWSDLPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IntrawayWSDLPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIntrawayWSDLPort(endpoint);
    }

    public net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLPortType getIntrawayWSDLPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLBindingStub _stub = new net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLBindingStub(portAddress, this);
            _stub.setPortName(getIntrawayWSDLPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIntrawayWSDLPortEndpointAddress(java.lang.String address) {
        IntrawayWSDLPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLBindingStub _stub = new net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLBindingStub(new java.net.URL(IntrawayWSDLPort_address), this);
                _stub.setPortName(getIntrawayWSDLPortWSDDServiceName());
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
        if ("IntrawayWSDLPort".equals(inputPortName)) {
            return getIntrawayWSDLPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:IntrawayWSDL", "IntrawayWSDL");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:IntrawayWSDL", "IntrawayWSDLPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IntrawayWSDLPort".equals(portName)) {
            setIntrawayWSDLPortEndpointAddress(address);
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
