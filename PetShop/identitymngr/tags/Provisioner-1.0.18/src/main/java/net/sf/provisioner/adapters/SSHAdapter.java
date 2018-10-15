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
package net.sf.provisioner.adapters;


//import ch.ethz.ssh2.StreamGobbler;

import java.io.*;

import net.sf.provisioner.config.NetworkElement;
import net.sf.provisioner.connectors.SSHConnector;

/**
 * @author Gonzalo
 *
 */
public class SSHAdapter extends Adapter{
	
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
	public SSHAdapter(NetworkElement ne) { this.ne = ne; }
	
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
	public String ExecuteCommand (String command) throws Exception {
		
		StringBuffer result = new StringBuffer();
		
		/* Obtener una conexion SSH */
		SSHConnector connection = new SSHConnector(this.ne);
		connection.Connect();
		
		
		try { 
			
			/* Enviamos el comando */
			//connection.sesion.execCommand(command);
			connection.writer.println(command + "\r\n");

			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {
				throw e;
			}
			
			/* leemos la respuesta del server */
			while (connection.reader.available() > 0) 
				result.append((char) connection.reader.read());
			
			
			logger.info("SSH response:" + result);
		} 
		catch (IOException e)
		{
			throw e;
		}

		connection.Disconnect();
		
		return result.toString();
	}

}
