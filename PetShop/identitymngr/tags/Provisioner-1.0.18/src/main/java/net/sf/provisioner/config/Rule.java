package net.sf.provisioner.config;

import java.util.*;

import net.sf.provisioner.utils.PathHelper;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import org.apache.log4j.Logger;

/**
 * Esta clase representa una regla. Contiene tantas instancias de la 
 * clase config.Request como lo indique la configuracion de la regla 
 * en el archivo de configuracion de reglas.
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:22 $
 * @author Gonzalo Espert
 */
public class Rule {

	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
	
	/**
	 * Codigo de operacion de la regla 
	 */
	public String operation;
	
	/**
	 * Titulo de la regla
	 */
	String title;
	
	/**
	 * Tabla de requerimientos de la regla
	 */
	public Hashtable requests = new Hashtable();
	
	/**
     * Constructor de una regla utilizando como entrada un codigo de
     * operacion para saber que operacion leer del archivo de configuracion 
     * de reglas y el nombre de dicho archivo.
     * 
     * 
     * @param operation
     *                 Codigo de la operacion para hacer el fetch en el 
     *                 archivo de configuracion de reglas     
     * @param rulesFile
     * 				Nombre del archivo donde residen las reglas del
     * 				sistema          
     *           
     */
	public Rule(String operation, String rulesFile) {
		
		/**
		 * Setea el codigo de operacion
		 */
		this.operation = operation;
		
		/**
		 * Setea el titulo y carga los requerimientos
		 */
		logger.info("Configuring requests for " + operation + " operation.");
    	try {
		      Document d = new SAXBuilder().build(PathHelper.pathToStream(rulesFile)); 
		      List children = d.getContent();  
			  Iterator iterator = children.iterator();
			  while (iterator.hasNext()) {
				  Element child = (Element) iterator.next();
			      getRuleRequest(child, "", 0);
			  }
	    } catch (Exception e) {
	    	logger.fatal("Error reading rules configuration file " + rulesFile);
	    	e.printStackTrace();
	    }
	}
	
	/**
     * Metodo que carga una por una los requests correspondientes
     * a la regla utilizando como dato de entrada los nodos del 
     * archivo de configuracion de reglas y el codigo de operacion 
     * que corresponde a la regla.
     * 
     *
     * @param current
     * 			Nodos del archivo Rules.xml
     * @param operationID
     * 			Codigo de operacion de la regla
     * @param i
     * 			Indice para cada request
     *                      
     */
	void getRuleRequest(Element current, String operationID, int i) {
		
	    List children = current.getChildren();
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("rule"))
	    	  operationID = child.getAttributeValue("operation");
	      
	      /**
	       * Si se trata del tipo de operacion correcta
	       */
	      if (this.operation.equalsIgnoreCase(operationID)) {
	      
    		  /**
    		   * Setea el titulo de la regla
    		   */
	    	  if (child.getName().equalsIgnoreCase("title")) this.title = child.getText();
    		 
    		  /**
    		   * Carga los requerimientos
    		   */
	    	  if (child.getName().equalsIgnoreCase("request")) {
	    		  
	    		  this.requests.put(new Integer(i++), 
	    				  			new ConfigRequest(child.getAttributeValue("service"), child.getAttributeValue("type")));
	    	  }
	      }
	      getRuleRequest(child, operationID, 0);
	    } // end while  
	}
	
	public String toString() {
		return "operation=" + operation + ", title=" + title +", requests=[" + requests + "]";
	}
}
