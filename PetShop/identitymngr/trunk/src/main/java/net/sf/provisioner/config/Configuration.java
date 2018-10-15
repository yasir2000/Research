package net.sf.provisioner.config;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.sf.provisioner.utils.PathHelper;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/**
 * Esta clase representa la configuracion del sistema.
 * Contiene las reglas (tipo de operaciones, servicios), 
 * las logicas de ruteo y los datos necesarios para acceder 
 * a la base de datos donde recide la cola de operaciones.
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:22 $
 * @author Gonzalo Espert
 */
public class Configuration {
	
	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
	
	public Vector operationTypes = new Vector();
	
	public Hashtable rules = new Hashtable();
	
	public Hashtable services = new Hashtable();
	
	//for more than one database
	//public Hashtable dbs = new Hashtable();
	public DataBase db = new DataBase();
	
	String rulesFile = "rules.xml";
	
	String servicesFile = "services.xml";
	
	String dbsFile = "database.xml";
	
	String configPath;
	

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
	public Configuration(String configPath) {
	
		this.configPath = configPath;
		
		logger.info("Loading system configuration...........");
		
		setDataBases();
		
		setOperationTypes();
		
		setRules();
		
		setServices();
	}
	
	/**
     * Set operations types from rules.xml.
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
	void setOperationTypes() {
	
		logger.info("Loading operation types...........");
    	try {
		      Document d = new SAXBuilder().build(PathHelper.pathToStream(configPath + this.rulesFile)); 
		      List children = d.getContent();  
			  Iterator iterator = children.iterator();
			  while (iterator.hasNext()) {
				  Element child = (Element) iterator.next();
				  setOperations(child);
			  }
		      
	    } catch (Exception e) {
	    	logger.fatal("Error reading rules configuration file " + configPath + this.rulesFile);
	    	e.printStackTrace();
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
	void setOperations(Element current) {
		
	    List children = current.getChildren();
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("rule"))
	    	  this.operationTypes.addElement(child.getAttributeValue("operation"));
	      setOperations(child);
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
	void setRules() {
		for (int i = 0; i < this.operationTypes.size(); i++) {
			Rule rule = new Rule((String) operationTypes.elementAt(i), configPath + this.rulesFile);
			logger.trace("Created rule " + rule);
			this.rules.put(new Integer(i), rule);
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
	void setServices() {
		
		logger.info("Loading service routing logic configuration");
	    try {
		      Document d = new SAXBuilder().build(PathHelper.pathToStream(configPath + this.servicesFile)); 
		      List children = d.getContent();  
			  Iterator iterator = children.iterator();
			  while (iterator.hasNext()) {
			      Element child = (Element) iterator.next();
			      getCriteria(child, 0);
			  }
	    } catch (Exception e) {
	    	logger.fatal("Error reading services configuration file " + configPath + this.servicesFile);
	    	e.printStackTrace();
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
	void getCriteria(Element current, int i) {
		
	    List children = current.getChildren();
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("service")) {
	    	  logger.info("Creating routing criteria for service: " + child.getAttributeValue("name"));
	    	  RoutingCriteria routingCriteria = new RoutingCriteria(
	    			  child.getAttributeValue("routing_table"),
	    			  child.getAttributeValue("routing_data")
              );
	    	  ConfigService service = new ConfigService(child.getAttributeValue("name"), routingCriteria, configPath);
	    	  logger.trace("Created service " + service);
	    	  this.services.put(new Integer(i++), service);
	      }
	      getCriteria(child, i);
	    }   
	}
	
	/**
     * Set operations types from rules.xml.
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
	void setDataBases() {
	
		logger.info("Loading data base access configuration...........");
    	try {
		      Document d = new SAXBuilder().build(PathHelper.pathToStream(configPath + this.dbsFile)); 
		      List children = d.getContent();  
			  Iterator iterator = children.iterator();
			  while (iterator.hasNext()) {
				  Element child = (Element) iterator.next();
				  setDataBase(child);
			  }
		      
	    } catch (Exception e) {
	    	logger.fatal("Error reading data base access configuration file " + configPath + this.dbsFile);
	    	e.printStackTrace();
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
     * @param current
     *            
     *           
     */
	void setDataBase(Element current) {
		
	    List children = current.getChildren();
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("server")) {
	    	  db.server.name = child.getAttributeValue("name");
	    	  db.server.type = child.getAttributeValue("type");
	      }

	      if (child.getName().equalsIgnoreCase("driver")) {
	    	  db.driver.className = child.getAttributeValue("className");
	    	  db.driver.type = child.getAttributeValue("type");
	      }
	      
	      if (child.getName().equalsIgnoreCase("database")) {
	    	  db.name = child.getAttributeValue("name");
	    	  db.user = child.getAttributeValue("user");
	    	  db.password = child.getAttributeValue("password");
	      }
	      
	      setDataBase(child);
	    }
	}
}
