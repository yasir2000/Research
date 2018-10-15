package net.sf.provisioner.config;

/**
 * Esta clase representa un requerimiento a ser enviado
 * a un elemento de red. Contiene una instancia de la clase core.Service 
 * y un tipo de operacion (Alta, Baja o Modificacion).  
 * Inicialmente se construye con un nombre de servicio y un tipo de 
 * operacion. Posteriormente se completan los datos del servicio cuando 
 * se aplican las reglas.
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:21 $
 * @author Gonzalo Espert
 */
public class ConfigRequest {
	
	/**
	 * Servicio asociado con el request
	 */
	public net.sf.provisioner.core.Service service = new net.sf.provisioner.core.Service();
	
	/**
	 * Tipo de operacion para el requerimiento
	 */
	public String operationType;
	
	/**
     * Constructor que recibe como entrada un objeto del tipo core.Service
     * y un string con el tipo de operacion.
     * 
     * @param service
     * 			Objeto del tipo core.Service que contiene los datos del servicio
     * 			asociado al request
     * @param operationType
     * 			Tipo de operacion que debe ejecutar el request
     *           
     */
	public ConfigRequest(net.sf.provisioner.core.Service service, String operationType) {
		
		this.service = service;
		this.operationType = operationType;
		
	}

	/**
     * Constructor que recibe como entrada un nombre de servicio y un
     * tipo de operacion. El nombre de servicio es asignado al atributo
     * name del objeto service. El resto de la informacion del servicio
     * sera rellenada al momento de aplicar las reglas a la operacion
     * que contenga al request.
     * 
     * 
     * @param serviceName
     * 				Nombre del servicio al cual esta relacionado el 
     * 				request
     * @param operationType
     *              Tipo de operacion que debe ejecutar el request
     *                   
     */
	public ConfigRequest(String serviceName, String operationType) {
		
		this.service.name = serviceName;
		this.operationType = operationType;
		
	}
	
	public String toString() {
		return operationType + ", " + service; 
	}
}
