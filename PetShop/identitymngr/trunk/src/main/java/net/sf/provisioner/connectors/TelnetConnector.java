/**
 * 
 */
package net.sf.provisioner.connectors;

import net.sf.provisioner.config.NetworkElement;

import org.sadun.util.UnixLoginHandler;
import org.sadun.util.UnixLoginHandler.LoginIncorrectException;
import org.sadun.util.TelnetInputStreamConsumer;
import org.sadun.util.OperationTimedoutException;


import java.net.*;
import java.io.*;

/**
 * Esta clase .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:26 $
 * @author Gonzalo Espert
 */
public class TelnetConnector extends Connector {

	public TelnetInputStreamConsumer inputConsumer;
	
	public PrintWriter writer;
	
	private NetworkElement ne;
	
	private UnixLoginHandler endPoint;
	
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
	public TelnetConnector(NetworkElement ne) { this.ne = ne; }
	
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
	public void Connect() throws Exception {

		 try {
			 // Create a socket to the host
			 Socket s = new Socket(this.ne.name, Integer.parseInt(this.ne.port));
			 // Create a UnixLoginHandler object over the socket to login to the host
			 this.endPoint = new UnixLoginHandler(s);
			 this.endPoint.setTimeout(300000);
			 this.endPoint.setLoginPromptSequence(this.ne.loginPrompt);
			 this.endPoint.setLoginIncorrectSequence(this.ne.loginErrorSecuence);
			 this.endPoint.setSendInitialCRLF(this.ne.sendInitialCRLF);
			 // Create a TelnetInputStreamConsumer object over the socket by logging in to the host
			 logger.info("Iniciando sesion en: " + this.ne.name + " .............");
			 this.inputConsumer = this.endPoint.doLogin(this.ne.user,this.ne.password);
			 logger.info("Sesion iniciada exitosamente!");
			 // Create a stream writer
			 this.writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
		 } catch (UnknownHostException e) {
			 Exception ex = new Exception("Host desconocido.");
	    	 throw ex;
	     } catch (IOException e) {
	    	 Exception ex = new Exception("Error de I/O en la secuencia de login.");
	    	 throw ex;
	     } catch (LoginIncorrectException e) {
	    	 Exception ex = new Exception("Login o password incorrectos.");
	    	 throw ex;
	     } catch (OperationTimedoutException e) {
	    	 Exception ex = new Exception("Se produjo un timeout en la secuencia de login.");
	    	 throw ex;
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
	public void Disconnect() {
	
		this.endPoint.doLogout();

	}

}
