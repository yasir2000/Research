/**
 * 
 */
package net.sf.provisioner.adapters;

import java.io.IOException;

import net.sf.provisioner.config.NetworkElement;
import net.sf.provisioner.connectors.TelnetConnector;


/**
 * .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:24 $
 * @author Gonzalo Espert
 */
public class TelnetAdapter extends Adapter{
	
	private NetworkElement ne;
	
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
	public TelnetAdapter(NetworkElement ne) { this.ne = ne; }
	
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
	public String ExecuteCommand(String command) throws Exception {
		
		String trash;
		String result = null;
		
		/* Obtener una conexion telnet */
		TelnetConnector connection = new TelnetConnector(this.ne);
		connection.Connect();
		
		/* Vaciamos el stream de entrada */
		try {
			
			trash = connection.inputConsumer.consumeInput(3000);
			logger.info("stdin trash: " + trash);
			
		} catch (IOException e) {
	    	throw e;
	    }
		
		/* Enviamos el comando */
		command = command + "\r\n";
		logger.info("Sending command: \"" + command + "\"");
		// usamos prinln + \r\n porque sino cuando enviamos desde Unix\Linux no funciona
		connection.writer.println(command);

	    /* Recibimos la respuesta */
	    try {
	    		    	
	    	result = connection.inputConsumer.consumeInput(10000);
	    	logger.info("Consumption: " + result);
	    	
	    } catch (IOException e) {
	    	throw e;
	    }
	    
	    connection.Disconnect();
	    
	    return result;
	}
}
