package net.sf.provisioner.config;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import net.sf.provisioner.utils.PathHelper;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/**
 * Esta clase representa la configuracion de un servicio segun lo
 * indicado por el archivo services.xml y el correspondiente 
 * archivo _routing.xml.
 * Nota: A diferencia de la clase core.Service esta clase es de configuracion
 * y no es un servicio de un request de una operacion.
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:21 $
 * @author Gonzalo Espert
 */
public class ConfigService {

	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());

	/**
	 * Service name
	 */
	public String name;

	/**
	 * Service data to be used to determine the route
	 */
	public String routingData;

	/**
	 * Possible routes for the service
	 */
	public Hashtable routes = new Hashtable();
	
	// Path to configuration files
	String configPath;

	/**
	 * Constructor de un servicio utilizando como entrada el nombre del 
	 * servicio y un objeto del tipo RoutingCriteria que es utilizado
	 * para armar la tabla de rutas del servicio.
	 * 
	 * 
	 * @param name
	 * @param routingCriteria
	 *           
	 */
	public ConfigService(String name, RoutingCriteria routingCriteria, String configPath) { 
		this.name = name;
		this.routingData = routingCriteria.data;
		this.configPath = configPath;

		loadRoutes(routingCriteria);
		logger.trace("Created service: " + this);
	}

	/**
	 * Metodo utilizado para cargar las rutas del servicio utilizando
	 * como entrada un objeto del tipo RoutingCriteria que es quien indica
	 * que archivo _routing.xml utilizar. Se recorre este archivo y se 
	 * configuran los datos del elemento de red de cada ruta.
	 * 
	 * @param routingCriteria
	 *           Indica el nombre de la tabla (archivo _routing.xml) a 
	 *           utilizar y que dato (Zone o Range) utilizar para hacer 
	 *           el fetch. 
	 */
	void loadRoutes(RoutingCriteria routingCriteria) {

		logger.info("Loading routing info for service " + this.name + " ......");
		/*File routingConfig = new File("config/" + routingCriteria.table);
		if (!routingConfig.exists()) {
			String message = "Routing config. file does not exist: " + routingConfig;			
			logger.fatal(message);
			// TODO: shouldn't we throw something after a fatal error?			
			return;
		}		
		logger.trace("processing " + routingConfig);*/
		
		try {
			InputStream routingConfig = PathHelper.pathToStream(configPath + routingCriteria.table);

			Document d = new SAXBuilder().build(routingConfig); 
			List children = d.getContent();  
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element child = (Element) iterator.next();
				getNes(child, 0);
			}
		} catch (Exception e) {
			logger.fatal("Error reading services configuration file " + routingCriteria.table);
			e.printStackTrace();
		}

	}

	/**
	 * Metodo utilizado para obtener el nombre del elemento de red,
	 * tipo y datos de acceso (usuario y clave) de cada una de las rutas
	 * existentes para este servicio. 
	 * Este metodo utiliza como entrada el nodo de rutas del archivo xml.
	 * 
	 * @param current
	 * 			Elemento del nodo del archivo xml
	 * @param i
	 * 			               
	 */
	void getNes(Element current, int i) {

		String neName;

		List children = current.getChildren();
		Iterator iterator = children.iterator();
		while (iterator.hasNext()) {
			Element child = (Element) iterator.next();
			if (child.getName().equalsIgnoreCase("route")) {
				String regExpresion = child.getAttributeValue("reg_exp_key");

				logger.info("Loading routing information based on the regular expresion: (" + regExpresion + ")");

				neName = child.getAttributeValue("ne_name");
				NetworkElement ne = NetworkElementFactory.createElement(neName, configPath);
				this.routes.put(new Integer(i++), new Route(ne, regExpresion));
			}  
			getNes(child, i);
		}   
	}

	public String toString() {
		return "name=" + name + ", routingData=" + routingData + ", routes: " + routes;
	}
}
