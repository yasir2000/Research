package net.sf.provisioner.requests;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.provisioner.adapters.TelnetAdapter;
import net.sf.provisioner.commands.MerakCommand;
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
public class MerakRequest extends Request{
	
	/* Comando del requerimiento */ 
	MerakCommand command = new MerakCommand();
		
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
	public MerakRequest(net.sf.provisioner.config.ConfigRequest request, Document parameters) {
		
		_initRequest(parameters);
		this.ne = request.service.ne;
		this.ne.loginPrompt = "ogin:";
		this.ne.port = "23";
		this.ne.sendInitialCRLF = false;
		
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
	    		      	if (paramName.equalsIgnoreCase("account")) this.command.account = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("u_name")) this.command.user = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("u_password")) this.command.password = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("u_accounttype")) this.command.accountType = child1.getAttributeValue("value");
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
	public Response sendRequest() throws Exception{
		
		Response response = new Response();
		
		String operation = new String();
		
		TelnetAdapter merak = new TelnetAdapter(this.ne);
		
		if (this.command.operation.equalsIgnoreCase("create")) operation = this.ne.create;
	    if (this.command.operation.equalsIgnoreCase("delete")) operation = this.ne.delete;
		
		/* Enviamos a Merak */
		try { 
			
			/* Ejecutamos el comando */
			String respuesta = merak.ExecuteCommand(this.command.bin + " " + 
					                                operation + " account " + 
                                                    this.command.account + " u_name \"" + 
                                                    this.command.user + "\" u_password \"" + 
                                                    this.command.password + "\" u_accounttype " + 
                                                    this.command.accountType);
			
			logger.debug(respuesta);
			
			/* Interpretamos la respuesta */
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
		posibleRespuesta.errorStr = "No se encontro ningun match en el archivo de respuestas, respuesta desconocida";
		posibleRespuesta.successfull = false;
		posibleRespuesta.retry = false;
		return posibleRespuesta;
	}
	
}
