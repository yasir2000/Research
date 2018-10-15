/**
 * IntrawayWSDLPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package net.sf.provisioner.IntrawayWSDL_pkg;

public interface IntrawayWSDLPortType extends java.rmi.Remote {

    /**
     * Este es el metodo put.....
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.InterfaceObjOutput[] put(java.lang.String authKey, net.sf.provisioner.IntrawayWSDL_pkg.InterfaceObjInput[] arrayOfInterfaceObjInput) throws java.rmi.RemoteException;

    /**
     * Este es el metodo GetActivity.....
     */
    public void getActivity(java.lang.String authKey, java.lang.String lastIdEntradaCaller, java.lang.String idInterface, javax.xml.rpc.holders.StringHolder idError, javax.xml.rpc.holders.StringHolder errorStr, net.sf.provisioner.IntrawayWSDL_pkg.holders.ArrayOfActivityObjOutputHolder arrayOfActivityObjOutput) throws java.rmi.RemoteException;

    /**
     * Este es el metodo GetDocsisStatus.....
     */
    public void getDocsisStatus(java.lang.String authKey, java.lang.String idEmpresaCRM, java.lang.String idServicio, java.lang.String idVenta, java.lang.String idProducto, java.lang.String xmlEncoding, javax.xml.rpc.holders.StringHolder idError, javax.xml.rpc.holders.StringHolder errorStr, net.sf.provisioner.IntrawayWSDL_pkg.holders.DocsisStatusObjOutputHolder docsisStatusObjOutput) throws java.rmi.RemoteException;

    /**
     * Este es el metodo Maintenance.....
     */
    public net.sf.provisioner.IntrawayWSDL_pkg.MaintenanceObjOutput[] maintenance(java.lang.String authKey, net.sf.provisioner.IntrawayWSDL_pkg.MaintenanceObjInput[] arrayOfMaintenanceObjInput) throws java.rmi.RemoteException;
}
