package net.sf.provisioner.config;

/**
 * Esta clase representa los criterios a ser utilizados en una eventual
 * busqueda de una ruta.
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:23 $
 * @author Gonzalo Espert
 */
public class RoutingCriteria {
	
	/**
	 * Nombre del archivo xml donde se pueden encontrar las rutas
	 */ 
	public String table;
	
	/**
	 * Dato a ser utilizado para las busquedas en la tabla de rutas
	 */
	public String data;
		
	/**
     * Construnctor que recibe el nombre del archivo de ruteo y
     * el nombre del dato clave que se debe utilizar para realizar 
     * busquedas dentro del archivo.
     * 
     * 
     * @param table
     *        	Nombre del archivo xml donde residen las rutas
     * @param data
     * 			Dato clave para realizar busquedas de rutas
     *                     
     */
	public RoutingCriteria(String table, String data) { this.table = table; this.data = data; }

	public String toString() {
		return "table=" + table + ", data=" + data;
	}
}
