package net.sf.provisioner.core;

import net.sf.provisioner.config.NetworkElement;

/**
 * Esta clase representa un servicio dentro de un request de 
 * una operacion. Contiene un objeto del tipo NetworkElement 
 * que representa al elemento de red al cual se debe enviar 
 * el request.
 * Nota: A diferencia de config.Service esta calse no es de 
 * configuracion sino que representa a un servicio propiamente 
 * dicho.
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:27 $
 * @author Gonzalo Espert
 */
public class Service {
	
	/**
	 * Service network element
	 */
	public NetworkElement ne = new NetworkElement();
	
	/**
	 * Service name
	 */
	public String name;
	
	/**
     * Empty service constructor.
     *            
     */
	public Service() {}
	
	/**
     * Constructor de un servicio utilizando como entrada el nombre
     * del servicio y el elemento de red al cual debe ser enviado el 
     * request.
     * 
     * 
     * @param name
     * 			nombre del servicio
     * @param ne
     *          elemento de red para el request
     *           
     */
	public Service(String name, NetworkElement ne) { 
		 
		this.name = name;
		this.ne = ne;
		
	}
	
	public String toString() {
		return "name=" + name + ", ne=[" + ne + "]"; 
	}
}
