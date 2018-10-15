/**
 * Esta clase .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, Jan 17, 2007
 * @author Gonzalo Espert 
 */
package net.sf.provisioner.requests;

import java.util.Iterator;
import java.util.List;
import java.util.Enumeration;
import java.util.regex.*;

import net.sf.provisioner.adapters.SSHAdapter;
import net.sf.provisioner.commands.SERCommand;
import net.sf.provisioner.responses.Response;

import org.jdom.Document;
import org.jdom.Element;



/**
 * @author Gonzalo
 *
 */
public class SERRequest extends Request {
	
	/* Request command */
	SERCommand command = new SERCommand();
	
	public SERRequest (net.sf.provisioner.config.ConfigRequest request, Document parameters) {
	
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
	    		      	if (paramName.equalsIgnoreCase("numero_linea")) this.command.line1.number = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("numero_linea_2")) this.command.line2.number = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("contrasenia")) this.command.line1.password = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("contrasenia_linea_2")) this.command.line2.password = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("display")) this.command.line1.display = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("display_linea_2")) this.command.line2.display = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("puerto_sip")) this.command.line1.puertoSIP = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("puerto_sip_linea_2")) this.command.line2.puertoSIP = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("call_waiting")) this.command.line1.callWaiting = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("call_waiting_linea_2")) this.command.line2.callWaiting = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("caller_id")) this.command.line1.callerID = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("caller_id_linea_2")) this.command.line2.callerID = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("conference")) this.command.line1.conference = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("conference_linea_2")) this.command.line2.conference = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("voice_mail")) this.command.line1.voiceMail = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("voice_mail_linea_2")) this.command.line2.voiceMail = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("voice_mail_on_busy")) this.command.line1.voiceMailOnBusy = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("voice_mail_on_busy_linea_2")) this.command.line2.voiceMailOnBusy = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("call_forward")) this.command.line1.callForward = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("call_forward_linea_2")) this.command.line2.callForward = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("bloqueo_solo_sip")) this.command.line1.bloqueoSoloSip = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("bloqueo_solo_sip_linea_2")) this.command.line2.bloqueoSoloSip = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("mac_address")) this.command.macAddress = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("puerto_rtp_minimo")) this.command.puertoRTPminimo = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("puerto_rtp_maximo")) this.command.puertoRTPmaximo = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("tipo_dispositivo")) this.command.tipoDispositivo = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("tipo_bloqueo")) this.command.block = child1.getAttributeValue("value");
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
	public Response sendRequest() throws Exception {
		
		Response response = new Response();
		
		String command = null;
		
		SSHAdapter ser = new SSHAdapter(this.ne);
		
		/* If it is a Create request */
		if (this.command.operation.equalsIgnoreCase("Create")) command = buildCreateCommand();
		/* Delete request */
		else if (this.command.operation.equalsIgnoreCase("Delete")) command = buildDeleteCommand();
		/* Disable supplementary service */
		else if (this.command.operation.equalsIgnoreCase("Disable")) command = buildSuspendCommand();
		/* Enable supplementary service */
		else if (this.command.operation.equalsIgnoreCase("Enable")) command = buildUnsuspendCommand();
		
		/* Send request to SER */
		try { 
			
			/* Execute command */
			String respuesta = ser.ExecuteCommand(command);
			
			logger.debug(respuesta);
			
			/* consume response */
			response = interpretaRespuesta(respuesta);
			
		} catch (Exception e) { throw e; }
		
		return response;
	}
	
	Response interpretaRespuesta (String respuesta) {
		
		Response posibleRespuesta = new Response();
		
		/* Match response with responses configured in xml config file */
		Enumeration respuestas = this.ne.responses.elements();
		while (respuestas.hasMoreElements()) {
			posibleRespuesta = (Response)respuestas.nextElement();
			Pattern pattern = Pattern.compile(posibleRespuesta.result, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(respuesta);
			if (matcher.find() && this.command.operation.equalsIgnoreCase(posibleRespuesta.tipoOperacion))
				return posibleRespuesta;
		}	
		posibleRespuesta.errorStr = "No match found in response configuration file";
		posibleRespuesta.successfull = false;
		posibleRespuesta.retry = false;
		return posibleRespuesta;
	}
	
	String buildDeleteCommand() {
	
		/* Build command string */
		String command = this.command.bin + this.ne.delete;
		command = command + " \"" + this.command.line1.number + "\"";
		
		return command;
	}
	
	String buildSuspendCommand() {
		
		/* Build command string */
		String command = this.command.bin + this.ne.disable;
		command = command + " \"" + this.command.line1.number + "\"";
		command = command + " \"" + this.command.block + "\"";
		
		return command;
	}
	
	String buildUnsuspendCommand() {
		
		/* Build command string */
		String command = this.command.bin + this.ne.enable;
		command = command + " \"" + this.command.line1.number + "\"";
		command = command + " \"" + this.command.block + "\"";
		
		return command;
	}
	
	String buildCreateCommand() {
		
	
		/* Build command string */
		String command = this.command.bin + this.ne.create;
		command = command + " \"" + this.command.line1.number + "\"";
		if (this.command.line1.password != null)
			command = command + " \"" + this.command.line1.password + "\"";
		else
			command = command + " \"\"";
		if (this.command.line1.display != null)
			command = command + " \"" + this.command.line1.display + "\"";
		else
			command = command + " \"\"";
		command = command + " \"" + this.command.line2.number + "\"";
		if (this.command.line2.password != null)
			command = command + " \"" + this.command.line2.password + "\"";
		else
			command = command + " \"\"";
		if (this.command.line2.display != null)
			command = command + " \"" + this.command.line2.display + "\"";
		else
			command = command + " \"\"";
		command = command + " \"" + this.command.macAddress + "\"";
		if (this.command.line1.puertoSIP != null)
			command = command + " \"" + this.command.line1.puertoSIP + "\"";
		else
			command = command + " \"\"";
		if (this.command.line2.puertoSIP != null)
			command = command + " \"" + this.command.line2.puertoSIP + "\"";
		else
			command = command + " \"\"";
		if (this.command.puertoRTPminimo != null)
			command = command + " \"" + this.command.puertoRTPminimo + "\"";
		else
			command = command + " \"\"";
		if (this.command.puertoRTPmaximo != null)
			command = command + " \"" + this.command.puertoRTPmaximo + "\"";
		else
			command = command + " \"\"";
		
		command = command + " \"" + this.command.line1.callWaiting + "\"";
		command = command + " \"" + this.command.line1.callerID + "\"";
		command = command + " \"" + this.command.line1.conference + "\""; 
		command = command + " \"" + this.command.line2.callWaiting + "\"";
		command = command + " \"" + this.command.line2.callerID + "\"";
		command = command + " \"" + this.command.line2.conference + "\"";
		command = command + " \"" + this.command.line1.voiceMail + "\"";
		command = command + " \"" + this.command.line2.voiceMail + "\"";
		command = command + " \"" + this.command.line1.voiceMailOnBusy + "\"";
		command = command + " \"" + this.command.line2.voiceMailOnBusy + "\"";
		command = command + " \"" + this.command.tipoDispositivo + "\"";
		command = command + " \"" + this.command.line1.callForward + "\"";
		command = command + " \"" + this.command.line2.callForward + "\"";
		command = command + " \"" + this.command.line1.bloqueoSoloSip + "\"";
		command = command + " \"" + this.command.line2.bloqueoSoloSip + "\"";
	
		return command;
	}
}
