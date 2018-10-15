package net.sf.provisioner.config;


/**
 * Esta clase representa una ruta a un elemento de red. Contiene
 * un objeto del tipo NetworkElement y el valor de la clave
 * de busqueda.
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:22 $
 * @author Gonzalo Espert
 */
public class Route {

	/**
	 * Elemento de red para la ruta
	 */ 
	public NetworkElement ne;
	
	/**
	 * Valor de la clave para las busquedas en la tabla
	 */
	public String key;
	
	/**
     * Constructor que recibe un objeto del tipo NeworkElement
     * representando al elemento de red y el dato para encontrar la ruta.
     * 
     * 
     * @param ne
     * 			Datos del elemento de red
     * @param key
     * 			String que contiene el valor de la clave de busqueda
     */
	public Route(NetworkElement ne, String key) {
		
		
		this.key = key;
		this.ne = ne;
		
	}
	
	public String toString() {
		return "key=" + key + ", ne=" + ne;
	}
}
