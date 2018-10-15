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
package net.sf.provisioner.connectors;


import java.io.*;

import net.sf.provisioner.config.NetworkElement;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;


/**
 * @author Gonzalo
 *
 */
public class SSHConnector extends Connector{
	
	NetworkElement ne;
	
	Session sesion;
	
	public InputStream reader;
	
	public PrintWriter writer;
	
	Connection conexion;

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
	public SSHConnector(NetworkElement ne) { this.ne = ne; }
	
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
	public void Connect() throws Exception{
		
		boolean isAuthenticated = false;
		
		try
		{
			/* Creamos una instancia de conexion */
			this.conexion = new Connection(this.ne.name);

			/* Nos conectamos */
			this.conexion.connect();

			/* Nos autenticamos */
			logger.info("Iniciando sesion en: " + this.ne.name + " .............");
			
			/* Vemos que tipo de autenticacion exige el server */
			if (this.conexion.isAuthMethodAvailable(this.ne.user, "password")) {
			
			
				isAuthenticated = this.conexion.authenticateWithPassword(this.ne.user, this.ne.password);
			
			} else {
				if (this.conexion.isAuthMethodAvailable(this.ne.user, "publickey")) {
					
					File keyfile = new File("config/private_key");
					isAuthenticated = this.conexion.authenticateWithPublicKey(this.ne.user, keyfile, this.ne.password);
					
				} else {
					
					//String[] metodos = this.conexion.getRemainingAuthMethods(this.ne.user);
				
					throw new IOException("El servidor no soporta autenticacion por el metodo de password ni publickey");
				}
			}
			
			if (isAuthenticated == false)
				throw new IOException("Fallo la autenticacion ssh con usuario : " + this.ne.user + " y password : " + this.ne.password);
			else {
			
				if (this.conexion.isAuthenticationComplete())
					logger.info("Autenticacion exitosa!");
				else
					throw new IOException("El procedimiento de autenticacion no esta completo");
			}
		
			/* Creamos una sesion */
			this.sesion = this.conexion.openSession();
			logger.info("Sesion iniciada exitosamente!");
			
			/* Iniciamos un shell para poder ejecutar mas de un comando en la misma sesion */
			this.sesion.requestDumbPTY();
			this.sesion.startShell();
			
			/* Creamos un stream buffer para leer */
			this.reader = new StreamGobbler(this.sesion.getStdout());
			while (this.reader.available() > 0) 
				this.reader.read(); // limpiamos el buffer

			/* Creamos un stream para escribir */
			this.writer = new PrintWriter(new OutputStreamWriter(this.sesion.getStdin()), true);
		
			/* Si el elemento de red requiere el uso de sudo */
			if (!this.ne.sudoPassword.equalsIgnoreCase("")) {
				
				logger.info("El elemento de red requiere el uso de sudo");
				this.writer.println("su \r\n");
				
				try {
					Thread.sleep(500);
				}
				catch (Exception e) {
					throw e;
				}
				
				/* leemos la respuesta del server */
				StringBuffer result = new StringBuffer();
				while (this.reader.available() > 0) 
					result.append((char) this.reader.read());
				
				/* chequemos si la respuesta contiene el pedido de password del sudo */
				if (result.toString().endsWith(this.ne.sudoPasswordPrompt))
					this.writer.println(this.ne.sudoPassword + "\r\n");
				else
					throw new IOException("No se recibio el prompt para ingresar el password del sudo");
				
				
				try {
					Thread.sleep(500);
				}
				catch (Exception e) {
					throw e;
				}
				
				logger.info("El shell para sudo fue iniciado con exito, ya tenemos prompt de sistema");
				while (this.reader.available() > 0) 
					this.reader.read(); // limpiamos el buffer
					
			}
			
		} catch (IOException e)
		{
			throw e;
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
		
		/* Cerramos la sesion */
		this.sesion.close();

		/* Cerramos la conexion */
		this.conexion.close();
			
	}
	
}
