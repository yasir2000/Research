package net.sf.provisioner.IntrawayWSDL;

import net.sf.provisioner.IntrawayWSDL_pkg.InterfaceObjInput;
import net.sf.provisioner.IntrawayWSDL_pkg.InterfaceObjOutput;
import net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLLocator;
import net.sf.provisioner.IntrawayWSDL_pkg.IntrawayWSDLPortType;
import net.sf.provisioner.IntrawayWSDL_pkg.MaintenanceObjInput;
import net.sf.provisioner.IntrawayWSDL_pkg.MaintenanceObjOutput;
import net.sf.provisioner.IntrawayWSDL_pkg.holders.ArrayOfActivityObjOutputHolder;
import net.sf.provisioner.IntrawayWSDL_pkg.holders.DocsisStatusObjOutputHolder;

public class IntrawayWSDLPortTypeProxy implements IntrawayWSDLPortType {
  private String _endpoint = null;
  private IntrawayWSDLPortType intrawayWSDLPortType = null;
  
  public IntrawayWSDLPortTypeProxy() {
    _initIntrawayWSDLPortTypeProxy();
  }
  
  private void _initIntrawayWSDLPortTypeProxy() {
    try {
      intrawayWSDLPortType = (new IntrawayWSDLLocator()).getIntrawayWSDLPort();
      if (intrawayWSDLPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)intrawayWSDLPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)intrawayWSDLPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (intrawayWSDLPortType != null)
      ((javax.xml.rpc.Stub)intrawayWSDLPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public IntrawayWSDLPortType getIntrawayWSDLPortType() {
    if (intrawayWSDLPortType == null)
      _initIntrawayWSDLPortTypeProxy();
    return intrawayWSDLPortType;
  }
  
  /**
   * Este es el metodo put.....
   */
  public InterfaceObjOutput[] put(java.lang.String authKey, InterfaceObjInput[] arrayOfInterfaceObjInput) throws java.rmi.RemoteException{
	  if (intrawayWSDLPortType == null)
	      _initIntrawayWSDLPortTypeProxy();
	    return intrawayWSDLPortType.put(authKey, arrayOfInterfaceObjInput); 
  }

  /**
   * Este es el metodo GetActivity.....
   */
  public void getActivity(java.lang.String authKey, java.lang.String lastIdEntradaCaller, java.lang.String idInterface, javax.xml.rpc.holders.StringHolder idError, javax.xml.rpc.holders.StringHolder errorStr, ArrayOfActivityObjOutputHolder arrayOfActivityObjOutput) throws java.rmi.RemoteException{
	  if (intrawayWSDLPortType == null)
	      _initIntrawayWSDLPortTypeProxy();
	  intrawayWSDLPortType.getActivity(authKey, lastIdEntradaCaller, idInterface, idError, errorStr, arrayOfActivityObjOutput);
  }

  /**
   * Este es el metodo GetDocsisStatus.....
   */
  public void getDocsisStatus(java.lang.String authKey, java.lang.String idEmpresaCRM, java.lang.String idServicio, java.lang.String idVenta, java.lang.String idProducto, java.lang.String xmlEncoding, javax.xml.rpc.holders.StringHolder idError, javax.xml.rpc.holders.StringHolder errorStr, DocsisStatusObjOutputHolder docsisStatusObjOutput) throws java.rmi.RemoteException{
	  if (intrawayWSDLPortType == null)
	      _initIntrawayWSDLPortTypeProxy();
	  intrawayWSDLPortType.getDocsisStatus(authKey, idEmpresaCRM, idServicio, idVenta, idProducto, xmlEncoding, idError, errorStr, docsisStatusObjOutput); 
  }

  /**
   * Este es el metodo Maintenance.....
   */
  public MaintenanceObjOutput[] maintenance(java.lang.String authKey, MaintenanceObjInput[] arrayOfMaintenanceObjInput) throws java.rmi.RemoteException{
	  if (intrawayWSDLPortType == null)
	      _initIntrawayWSDLPortTypeProxy();
	    return intrawayWSDLPortType.maintenance(authKey, arrayOfMaintenanceObjInput);
  }
  
}