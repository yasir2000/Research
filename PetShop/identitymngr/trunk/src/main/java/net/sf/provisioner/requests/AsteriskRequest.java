/**
 * Esta clase .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, Feb 1, 2007
 * @author Gonzalo Espert 
 */
package net.sf.provisioner.requests;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.provisioner.adapters.SSHAdapter;
import net.sf.provisioner.commands.AsteriskCommand;
import net.sf.provisioner.responses.Response;

import org.jdom.Document;
import org.jdom.Element;


/**
 * @author Gonzalo
 *
 */
public class AsteriskRequest extends Request {

	/* Comando del requerimiento */
	AsteriskCommand command = new AsteriskCommand();
		
	/**
	 * 
	 */
	public AsteriskRequest(net.sf.provisioner.config.ConfigRequest request, Document parameters) {
		
		_initRequest(parameters);
		this.ne = request.service.ne;
		this.command.bin = this.ne.bin;
		this.command.operation = request.operationType;
		
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
	    		      	if (paramName.equalsIgnoreCase("numero_linea")) this.command.line = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("password")) this.command.password = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("nombre_cliente")) this.command.user = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("email")) this.command.email = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("mensaje")) this.command.message = child1.getAttributeValue("value");
	    		  }
	    	  }
	      }
	    }
	}
	
	
	/* (non-Javadoc)
	 * @see requests.Request#sendRequest()
	 */
	@Override
	public Response sendRequest() throws Exception {

		Response response = new Response();
		
		String command = null;
		
		SSHAdapter asterisk = new SSHAdapter(this.ne);
		
		/* Si se trata de una alta */
		if (this.command.operation.equalsIgnoreCase("create")) command = buildCreateCommand();
		/* Si es una baja */
		else if (this.command.operation.equalsIgnoreCase("delete")) command = buildDeleteCommand();
		
		/* Enviar a Asterisk */
		try { 
			
			/* Ejecutamos el comando */
			String respuesta = asterisk.ExecuteCommand(command);
			
			logger.debug(respuesta);
			
			/* Interpretamos la respuesta */
			response = interpretaRespuesta(respuesta);
			
		} catch (Exception e) { throw e; }
		
		return response;
	}

	Response interpretaRespuesta (String respuesta) {
		
		Response posibleRespuesta = new Response();
		
		/* Matcheamos la respuesta con las configuradas en el archivo xml */
		Enumeration respuestas = this.ne.responses.elements();
		while (respuestas.hasMoreElements()) {
			posibleRespuesta = (Response)respuestas.nextElement();
			Pattern pattern = Pattern.compile(posibleRespuesta.result, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(respuesta);
			if (matcher.find() && this.command.operation.equalsIgnoreCase(posibleRespuesta.tipoOperacion))
				return posibleRespuesta;
		}
		posibleRespuesta.errorStr = "No match found in the responses file, unknown response";
		posibleRespuesta.successfull = false;
		posibleRespuesta.retry = false;
		return posibleRespuesta;
	}

	String buildDeleteCommand() {
		
		/* Armamos el string del comando */
		String command = this.command.bin + this.ne.delete;
		command = command + " \"" + this.command.line + "\"";
		command = command + " \"" + this.command.message + "\"";
		
		return command;
	}
	
	String buildCreateCommand() {
		
		/* Armamos el string del comando */
		String command = this.command.bin + this.ne.create;
		command = command + " \"" + this.command.line + "\"";
		command = command + " \"" + this.command.password + "\"";
		command = command + " \"" + this.command.user + "\"";
		command = command + " \"" + this.command.email + "\"";
		
		return command;
	}
}
