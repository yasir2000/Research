/**
 * Esta clase .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, 01/08/2007
 * @author Gonzalo Espert 
 */
package net.sf.provisioner.requests;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.provisioner.adapters.TelnetAdapter;
import net.sf.provisioner.commands.GenericCommand;
import net.sf.provisioner.responses.Response;

import org.jdom.Document;
import org.jdom.Element;




/**
 * @author espertgo
 *
 */
public class GenericRequest extends Request {

	// Request command 
	GenericCommand command = new GenericCommand();
	
	/**
	 * 
	 */
	public GenericRequest(net.sf.provisioner.config.ConfigRequest request, Document parameters) {
		
		this.ne = request.service.ne;
		
		_initRequest(parameters);
		
		this.ne.loginPrompt = "ogin:";
		this.ne.port = "23";
		this.ne.sendInitialCRLF = false;
		
		this.command.bin = this.ne.bin;
		this.command.operation = request.operationType;
	}

	/* (non-Javadoc)
	 * @see requests.Request#sendRequest()
	 */
	@Override
	public Response sendRequest() throws Exception {

		Response response = new Response();
		
		String operation = new String();
		
		TelnetAdapter destino = new TelnetAdapter(this.ne);
		
		if (this.command.operation.equalsIgnoreCase("alta")) operation = this.ne.create;
	    if (this.command.operation.equalsIgnoreCase("baja")) operation = this.ne.delete;
		
		/* Send to identity store */
		try { 
			
			String comando = this.command.bin; 
            comando = comando + operation; 
            if (!this.command.parameter1.isEmpty()) {
            	comando = comando + " " + this.command.parameter1;
            	if (!this.command.parameter2.isEmpty()) {
            		comando = comando + " " + this.command.parameter2;
            		if (!this.command.parameter3.isEmpty()) {
            			comando = comando + " " + this.command.parameter3;
            			if (!this.command.parameter4.isEmpty()) {
            				comando = comando + " " + this.command.parameter4;
            				if (!this.command.parameter5.isEmpty()) comando = comando + " " + this.command.parameter5; 
            			}
            		}
            	}
            }
            
			// Execute command
			String respuesta = destino.ExecuteCommand(comando);
			
			logger.debug(respuesta);
			
			// Interpret response
			response = interpretaRespuesta(respuesta);
			
		} catch (Exception e) { throw e; }
		
		return response;
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
		
		String paramName;
		
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
	    		      	if (paramName.equalsIgnoreCase("account")) this.command.account = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("u_name")) this.command.user = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("u_password")) this.command.password = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("u_accounttype")) this.command.accountType = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase(this.ne.parameter1)) this.command.parameter1 = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase(this.ne.parameter2)) this.command.parameter2 = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase(this.ne.parameter3)) this.command.parameter3 = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase(this.ne.parameter4)) this.command.parameter4 = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase(this.ne.parameter5)) this.command.parameter5 = child1.getAttributeValue("value");
	    		  }
	    	  }
	      }
	    }
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
	Response interpretaRespuesta (String respuesta) {
		
		Response posibleRespuesta = new Response();
				
		// Match response with configured possible responses 
		Enumeration respuestas = this.ne.responses.elements();
		while (respuestas.hasMoreElements()) {
			posibleRespuesta = (Response)respuestas.nextElement();
			Pattern pattern = Pattern.compile(posibleRespuesta.result, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(respuesta);
			if (matcher.find() && this.command.operation.equalsIgnoreCase(posibleRespuesta.tipoOperacion))
				return posibleRespuesta;
		}	
		posibleRespuesta.errorStr = "No match in responses configuration file, unknown response";
		posibleRespuesta.successfull = false;
		posibleRespuesta.retry = false;
		return posibleRespuesta;
	}
}
