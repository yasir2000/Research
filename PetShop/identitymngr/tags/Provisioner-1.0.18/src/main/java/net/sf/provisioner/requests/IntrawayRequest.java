/**
 * 
 */
package net.sf.provisioner.requests;


import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.provisioner.IntrawayWSDL.IntrawayWSDLPortTypeProxy;
import net.sf.provisioner.IntrawayWSDL_pkg.InterfaceObjInput;
import net.sf.provisioner.IntrawayWSDL_pkg.InterfaceObjOutput;
import net.sf.provisioner.responses.Response;

import org.jdom.Element;
import org.jdom.Document;


/**
 * Esta clase es .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:25 $
 * @author Gonzalo Espert
 */
public class IntrawayRequest extends Request {

	private InterfaceObjInput request = new InterfaceObjInput();
		
	String operation = new String();
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	public IntrawayRequest(net.sf.provisioner.config.ConfigRequest request, Document parameters) {
    	
		this.operation = request.operationType;
		_initRequest(parameters);
		this.ne = request.service.ne;
		
	}

	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	InterfaceObjInput _initRequestClient(Document parameters, String status) {
		
		String xmlEncoding = "<handleClient>";
		String paramName;
		
		// Static parameters
    	String idVenta = "0", idVentaPadre = "0", idServicio = "0", empresa = "";
    	String idProducto = "0", idCliente = "0", idServicioPadre = "0", idProductoPadre = "0", idPromotor = "0";
    	
    	List children = parameters.getContent();  
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("operation")) {
	    	  List children1 = child.getChildren();
	    	  Iterator iterator1 = children1.iterator();
	    	  while (iterator1.hasNext()) {
	    		  Element child1 = (Element) iterator1.next();
	    		  if (child1.getName().equalsIgnoreCase("parameter")) {
	    			  paramName = child1.getAttributeValue("name");
	    		      	if (paramName.equalsIgnoreCase("nombre_cliente")) xmlEncoding = xmlEncoding + "<Nombre>" + child1.getAttributeValue("value") + "</Nombre>";
	    		      	else if (paramName.equalsIgnoreCase("username")) xmlEncoding = xmlEncoding + "<Username>" + child1.getAttributeValue("value") + "</Username>";
	    		      	else if (paramName.equalsIgnoreCase("contrasena")) xmlEncoding = xmlEncoding + "<Contraseña>" + child1.getAttributeValue("value") + "</Contraseña>";
	    		      	else if (paramName.equalsIgnoreCase("tipo_cliente")) xmlEncoding = xmlEncoding + "<idTipoCliente>" + child1.getAttributeValue("value") + "</idTipoCliente>";
	    		      	else if (paramName.equalsIgnoreCase("numero_cliente")) idCliente = child1.getAttributeValue("value");
	    		      	else if (paramName.equalsIgnoreCase("email_noticias")) xmlEncoding = xmlEncoding + "<emailNoticias>" + child1.getAttributeValue("value") + "</emailNoticias>";
	    		      	else if (paramName.equalsIgnoreCase("empresa")) empresa = child1.getAttributeValue("value");
	    		      	else if (paramName.equalsIgnoreCase("idVenta")) idVenta = child1.getAttributeValue("value");
	    		      	else if (paramName.equalsIgnoreCase("idVentaPadre")) idVentaPadre = child1.getAttributeValue("value");
	    		      	else if (paramName.equalsIgnoreCase("idServicio")) idServicio = child1.getAttributeValue("value");
	    		      	else if (paramName.equalsIgnoreCase("idProducto")) idProducto = child1.getAttributeValue("value");
	    		      	else if (paramName.equalsIgnoreCase("idServicioPadre")) idServicioPadre = child1.getAttributeValue("value");
	    		      	else if (paramName.equalsIgnoreCase("idProductoPadre")) idProductoPadre = child1.getAttributeValue("value");
	    		      	else if (paramName.equalsIgnoreCase("idPromotor")) idPromotor = child1.getAttributeValue("value");
	    		  }
	    	  }
	      }
	    }
    	 
    	xmlEncoding = xmlEncoding + "</handleClient>";
    				  
    	Long timeStamp = new Date().getTime(); 
    	
    	return new InterfaceObjInput(timeStamp.toString(),
    								 "500", // Customer management
    								 status, // 1 Create, 2 Modification
    								 "0",
    								 "",
    								 idCliente,
    								 empresa,
    								 idVenta,
    								 idVentaPadre,
    								 idServicio,
    								 idProducto,
    								 idServicioPadre,
    								 idProductoPadre,
    								 idPromotor,
    								 xmlEncoding);
		
	}
	
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	InterfaceObjInput _initRequestSpace(Document parameters, String status) {
	
		String xmlEncoding  = "<handleClient>";
    	String paramName;
    	
    	// Static parameters
    	String idVenta = "0", idVentaPadre = "0", idServicio = "0", empresa = "";
    	String idProducto = "0", idCliente = "0", idServicioPadre = "0", idProductoPadre = "0", idPromotor = "0";
    	
    	List children = parameters.getContent();  
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("operation")) {
	    	  List children1 = child.getChildren();
	    	  Iterator iterator1 = children1.iterator();
	    	  while (iterator1.hasNext()) {
	    		  Element child1 = (Element) iterator1.next();
	    		  if (child1.getName().equalsIgnoreCase("parameter")) {
	    			  paramName = child1.getAttributeValue("name");
	    		      if (paramName.equalsIgnoreCase("ServicePackageCRMID")) xmlEncoding = xmlEncoding + "<ServicePackageCRMID>" + child1.getAttributeValue("value") + "</ServicePackageCRMID>";
	    		      else if (paramName.equalsIgnoreCase("Hub")) xmlEncoding = xmlEncoding + "<Hub>" + child1.getAttributeValue("value") + "</Hub>";
	    		      else if (paramName.equalsIgnoreCase("Nodo")) xmlEncoding = xmlEncoding + "<Nodo>" + child1.getAttributeValue("value") + "</Nodo>";
	    		      else if (paramName.equalsIgnoreCase("idIspCPECrmId")) xmlEncoding = xmlEncoding + "<idIspCPECrmId>" + child1.getAttributeValue("value") + "</idIspCPECrmId>";
	    		      else if (paramName.equalsIgnoreCase("idIspCmCrmId")) xmlEncoding = xmlEncoding + "<idIspCmCrmId>" + child1.getAttributeValue("value") + "</idIspCmCrmId>";
	    		      else if (paramName.equalsIgnoreCase("idIspMtaCrmId")) xmlEncoding = xmlEncoding + "<idIspMtaCrmId>" + child1.getAttributeValue("value") + "</idIspMtaCrmId>";
	    		      else if (paramName.equalsIgnoreCase("DocsisVersion")) xmlEncoding = xmlEncoding + "<DocsisVersion>" + child1.getAttributeValue("value") + "</DocsisVersion>";
	    		      else if (paramName.equalsIgnoreCase("EnableAccounting")) xmlEncoding = xmlEncoding + "<EnableAccounting>" + child1.getAttributeValue("value") + "</EnableAccounting>";
	    		      else if (paramName.equalsIgnoreCase("ProductName")) xmlEncoding = xmlEncoding + "<ProductName>" + child1.getAttributeValue("value") + "</ProductName>";
	    		      else if (paramName.equalsIgnoreCase("BandPackageCRMId")) xmlEncoding = xmlEncoding + "<BandPackageCRMId>" + child1.getAttributeValue("value") + "</BandPackageCRMId>";
	    		      else if (paramName.equalsIgnoreCase("PrepaidPolicyCrmId")) xmlEncoding = xmlEncoding + "<PrepaidPolicyCrmId>" + child1.getAttributeValue("value") + "</PrepaidPolicyCrmId>";
	    		      else if (paramName.equalsIgnoreCase("numero_cliente")) idCliente = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idIspCRM")) xmlEncoding = xmlEncoding + "<idIspCRM>" + child1.getAttributeValue("value") + "</idIspCRM>";
	    		      else if (paramName.equalsIgnoreCase("BuscarTagCM")) xmlEncoding = xmlEncoding + "<BuscarTagCM>" + child1.getAttributeValue("value") + "</BuscarTagCM>";
	    		      else if (paramName.equalsIgnoreCase("CantidadPCs")) xmlEncoding = xmlEncoding + "<CantCPE>" + child1.getAttributeValue("value") + "</CantCPE>";
	    		      else if (paramName.equalsIgnoreCase("empresa")) empresa = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idVenta")) idVenta = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idVentaPadre")) idVentaPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idServicio")) idServicio = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idProducto")) idProducto = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idServicioPadre")) idServicioPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idProductoPadre")) idProductoPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idPromotor")) idPromotor = child1.getAttributeValue("value");
	    		  }
	    	  }
	      }
	    }
    	 
    	xmlEncoding = xmlEncoding + "</handleClient>";
    				  
    	Long timeStamp = new Date().getTime(); 
    	
    	return new InterfaceObjInput(timeStamp.toString(),
    								 "620", // Internet spaces management
    								 status, // 1 Create, 2 Modification with activation, 0 Delete, 4 Deactivate
    								 "0",
    								 "",
    								 idCliente,
    								 empresa,
    								 idVenta,
    								 idVentaPadre,
    								 idServicio,
    								 idProducto,
    								 idServicioPadre,
    								 idProductoPadre,
    								 idPromotor,
    								 xmlEncoding);
	}
	
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	InterfaceObjInput _initRequestModem(Document parameters, String status) {
		
		String xmlEncoding = "<handleClient>";
		String paramName;
		
		// Static parameters
    	String idVenta = "0", idVentaPadre = "0", idServicio = "0", empresa = "";
    	String idProducto = "0", idCliente = "0", idServicioPadre = "0", idProductoPadre = "0", idPromotor = "0";
    	
    	List children = parameters.getContent();  
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("operation")) {
	    	  List children1 = child.getChildren();
	    	  Iterator iterator1 = children1.iterator();
	    	  while (iterator1.hasNext()) {
	    		  Element child1 = (Element) iterator1.next();
	    		  if (child1.getName().equalsIgnoreCase("parameter")) {
	    			  paramName = child1.getAttributeValue("name");
	    		      if (paramName.equalsIgnoreCase("mac_address")) xmlEncoding = xmlEncoding + "<MacAddress>" + child1.getAttributeValue("value") + "</MacAddress>";
	    		      else if (paramName.equalsIgnoreCase("numero_cliente")) idCliente = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("empresa")) empresa = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idVenta")) idVenta = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idVentaPadre")) idVentaPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idServicio")) idServicio = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idProducto")) idProducto = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idServicioPadre")) idServicioPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idProductoPadre")) idProductoPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idPromotor")) idPromotor = child1.getAttributeValue("value");
	    		  }
	    	  }
	      }
	    }
    	
    	xmlEncoding = xmlEncoding + "</handleClient>";
    				  
    	Long timeStamp = new Date().getTime(); 
    	
    	return new InterfaceObjInput(timeStamp.toString(),
    								 "622", // Modems management
    								 status, // 1 Create, 2 Modification
    								 "0",
    								 "",
    								 idCliente,
    								 empresa,
    								 idVenta,
    								 idVentaPadre,
    								 idServicio,
    								 idProducto,
    								 idServicioPadre,
    								 idProductoPadre,
    								 idPromotor,
    								 xmlEncoding);
	}
	
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	InterfaceObjInput _initRequestPCs(Document parameters, String status) {
		
		String xmlEncoding = "<handleClient>";
		String paramName;
		
		// Static parameters
    	String idVenta = "0", idVentaPadre = "0", idServicio = "0", empresa = "";
    	String idProducto = "0", idCliente = "0", idServicioPadre = "0", idProductoPadre = "0", idPromotor = "0";
    	
    	List children = parameters.getContent();  
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("operation")) {
	    	  List children1 = child.getChildren();
	    	  Iterator iterator1 = children1.iterator();
	    	  while (iterator1.hasNext()) {
	    		  Element child1 = (Element) iterator1.next();
	    		  if (child1.getName().equalsIgnoreCase("parameter")) {
	    			  paramName = child1.getAttributeValue("name");
	    		      if (paramName.equalsIgnoreCase("CantidadPCs")) xmlEncoding = xmlEncoding + "<CantCPE>" + child1.getAttributeValue("value") + "</CantCPE>";
	    		      else if (paramName.equalsIgnoreCase("numero_cliente")) idCliente = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("empresa")) empresa = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idVenta")) idVenta = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idVentaPadre")) idVentaPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idServicio")) idServicio = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idProducto")) idProducto = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idServicioPadre")) idServicioPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idProductoPadre")) idProductoPadre = child1.getAttributeValue("value");
	    		      else if (paramName.equalsIgnoreCase("idPromotor")) idPromotor = child1.getAttributeValue("value");
	    		  }
	    	  }
	      }
	    }
    	
    	xmlEncoding = xmlEncoding + "</handleClient>";
    				  
    	Long timeStamp = new Date().getTime(); 
    	
    	return new InterfaceObjInput(timeStamp.toString(),
    								 "630", // PC management
    								 status, // 1 Create, 2 Modification
    								 "0",
    								 "",
    								 idCliente,
    								 empresa,
    								 idVenta,
    								 idVentaPadre,
    								 idServicio,
    								 idProducto,
    								 idServicioPadre,
    								 idProductoPadre,
    								 idPromotor,
    								 xmlEncoding);
	}
	
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	void _initRequest(Document parameters) {
		
		/* Customer Operations */
		if (this.operation.equalsIgnoreCase("Create Customer")) 
			this.request = _initRequestClient(parameters, "1");
    	else if (this.operation.equalsIgnoreCase("Change Customer")) 
    		this.request = _initRequestClient(parameters, "2");
    	else if (this.operation.equalsIgnoreCase("Delete Customer")) 
    		this.request = _initRequestClient(parameters, "4");

		/* Service Operations */
    	else if (this.operation.equalsIgnoreCase("Assign Service")) 
    		this.request = _initRequestSpace(parameters, "1");
    	else if (this.operation.equalsIgnoreCase("Remove Service")) 
    		this.request = _initRequestSpace(parameters, "0");
    	else if (this.operation.equalsIgnoreCase("Suspend Service")) 
    		this.request = _initRequestSpace(parameters, "4");
    	else if (this.operation.equalsIgnoreCase("Unsuspend Service")) 
    		this.request = _initRequestSpace(parameters, "2");

		/* Modem Operations */
    	else if (this.operation.equalsIgnoreCase("Assign Modem")) 
    		this.request = _initRequestModem(parameters, "2");
    	else if (this.operation.equalsIgnoreCase("Remove Modem")) 
    		this.request = _initRequestModem(parameters, "2");

		/* PCs Operations */
    	else if (this.operation.equalsIgnoreCase("Change PCs")) 
    		this.request = _initRequestPCs(parameters, "2");
    	else if (this.operation.equalsIgnoreCase("Declare PCs")) 
    		this.request = _initRequestPCs(parameters, "1");
    	else if (this.operation.equalsIgnoreCase("Remove PCs")) 
    		this.request = _initRequestPCs(parameters, "0");
    	else
    		/* Unrecognized operation */
    		logger.error("IntrawayRequests._initRequest: operation " + this.operation + " unrecognized.");
	}
	
	/**
     * .
     * <p>
     * 
     * .
     * <p>
     * 
     * 
     * 
     * @param 
     *            
     * @throws 
     *            
     *           
     */
	public Response sendRequest() throws Exception {
		
		/* Input parameters */
		InterfaceObjInput[] arrayOfInterfaceObjInput = new InterfaceObjInput[1];
		arrayOfInterfaceObjInput[0] = this.request;
		
		logger.debug("Sending the following request to intraway: " );
    	logger.debug("idEntradaCaller: " + this.request.getIdEntradaCaller());
    	logger.debug("idInterface: " + this.request.getIdInterface());
    	logger.debug("idEstado: " + this.request.getIdEstado());
    	logger.debug("asyncronic: " + this.request.getAsyncronic());
    	logger.debug("fechaDiferido: " + this.request.getFechaDiferido());
    	logger.debug("idCliente: " + this.request.getIdCliente());
    	logger.debug("idEmpresa: " + this.request.getIdEmpresa());
    	logger.debug("idVenta: " + this.request.getIdVenta());
    	logger.debug("idVentaPadre: " + this.request.getIdVentaPadre());
    	logger.debug("idServicio: " + this.request.getIdServicio());
    	logger.debug("idProducto: " + this.request.getIdProducto());
    	logger.debug("idServicioPadre: " + this.request.getIdServicioPadre());
    	logger.debug("idProductoPadre: " + this.request.getIdProductoPadre());
    	logger.debug("idPromotor: " + this.request.getIdPromotor());
    	logger.debug("xmlencoding: " + this.request.getXmlEncoding());
		
		/* Output parameters */
		InterfaceObjOutput[] interfaceObjOutput;
		Response response = new Response();
		
		/* Create a Proxy instance*/
		IntrawayWSDLPortTypeProxy IntrawayWSDLPortTypeProxyid = new IntrawayWSDLPortTypeProxy();
				
		/* Set the Web Service endpoint */
		IntrawayWSDLPortTypeProxyid.setEndpoint("http://" + this.ne.name + "/IntrawayWS/server.php");
		
		logger.info("Sending request to: " + "http://" + this.ne.name + "/IntrawayWS/server.php");
		
		/* Send to Intraway */
		try{
			
			//logger.debug("Authorisation key: " + this.ne.authKey);
			
			interfaceObjOutput = IntrawayWSDLPortTypeProxyid.put(this.ne.authKey,
																 arrayOfInterfaceObjInput);
			
			logger.info("Command executed at Intraway.........");
			
			if(interfaceObjOutput == null){
			    	
			    	logger.error("Call to put method returned null");
			    	response.successfull = false;
			    	response.retry = false;
			    	
			}else{
			    						
			    	String result = interfaceObjOutput[0].getIdError().toString();
		    		result = result + "-" + interfaceObjOutput[0].getErrorStr().toString();
		    		
		    		logger.debug("Intraway response: " + result);
					
					/* Interpret response */
					response = interpretResponse(result);
		    		
			}

		} catch (Exception e) { throw e; }
		
		return response;
	}
	
	Response interpretResponse (String response) {
		
		Response possibleResponse = new Response();
		
		/* Match response with possible responses configured in xml file */
		Enumeration responses = this.ne.responses.elements();
		while (responses.hasMoreElements()) {
			possibleResponse = (Response)responses.nextElement();
			Pattern pattern = Pattern.compile(possibleResponse.result, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(response);
			if (matcher.find() && this.operation.equalsIgnoreCase(possibleResponse.tipoOperacion))
				return possibleResponse;
		}	
		possibleResponse.errorStr = "No match found on response file, unknown repsponse";
		possibleResponse.successfull = false;
		possibleResponse.retry = false;
		return possibleResponse;
	}
}
