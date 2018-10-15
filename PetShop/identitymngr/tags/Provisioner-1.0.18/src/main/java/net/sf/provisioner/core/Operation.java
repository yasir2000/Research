/**
 * 
 */
package net.sf.provisioner.core;

import java.io.StringReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.config.NetworkElement;
import net.sf.provisioner.config.NetworkElementFactory;
import net.sf.provisioner.config.Route;
import net.sf.provisioner.config.Rule;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/**
 * Esta clase .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:27 $
 * @author Gonzalo Espert
 */
public class Operation {

	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
	
	private String id, type;
	
	private Document parameters;
	
	public Hashtable requests = new Hashtable();
	
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
	public Operation() { this.id = "0";}
	
	/**
     * Gets the id value for this operation.
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id value for this operation.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
	
    /**
     * Gets the type value for this operation.
     * 
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type value for this operation.
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
            
    /**
     * Gets the parameters value for this operation.
     * 
     * @return parameters
     */
    public Document getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters value for this operation.
     * 
     * @param parameters
     */
    public void setParameters(String xml_string) throws Exception {
        
    	try {
    		logger.debug("Operation xml string: " + xml_string);
    		this.parameters = new SAXBuilder().build( new StringReader(xml_string));
	    } catch (Exception e) {
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
	public void applyRules(Enumeration rules, Hashtable services) {
    	
    	logger.info("Aplying provisioning rules......");
    	applyProvisioningRules(rules);
    	
    	// Apply routing rationale for each request
    	Enumeration requests = this.requests.elements();
    	while (requests.hasMoreElements()) {
    		
    		ConfigRequest request = (ConfigRequest)requests.nextElement();
    		
    		logger.info("Aplying routing rules for the service " + request.service.name + ".....");
    		/* TODO: find out if all this looping is necessary.  This method 
    		 * loops through every request, then applyRoutingCriteria does the same.
    		 */
    		applyRoutingCriteria(services, request.service.name, request.operationType); 	    
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
	void applyProvisioningRules(Enumeration rules) {
		
		boolean doContinue = true;
		int i = 0;
		
		while (rules.hasMoreElements() && doContinue) {
			
			Rule rule = (Rule)rules.nextElement();
			if (this.type.equalsIgnoreCase(rule.operation)) {
				
				Enumeration requests = rule.requests.elements();
				
				while (requests.hasMoreElements()) {
					
					ConfigRequest request = (ConfigRequest)requests.nextElement();
					//	build requests
					this.requests.put((Integer) i++, request);
						
				}
				//end loop
				doContinue = false;
			}
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
     * @param services
     * @param serviceName
     *                       
     *           
     */
	void applyRoutingCriteria(Hashtable services, String serviceName, String opType) {
		
		Enumeration servicesEnum = services.elements();
		
		// look for the correct operation request
    	for (int i = 0; i < this.requests.size(); i++) {
    		ConfigRequest request = (ConfigRequest)this.requests.get(i);
    		if (request.service.name.equalsIgnoreCase(serviceName) && request.operationType.equalsIgnoreCase(opType)) {
    			
    			// look for service routing criteria in configuration
    			while (servicesEnum.hasMoreElements()) {
    				
    				net.sf.provisioner.config.ConfigService service = (net.sf.provisioner.config.ConfigService)servicesEnum.nextElement();
    				if (service.name.equalsIgnoreCase(serviceName)) {
    					
    					// Apply configuration of routing criteria by replacing 
    					// the composite object
    					this.requests.remove(request);
    					/* Build network element object based on routing criteria */
    					NetworkElement ne = getRoutingData(service.routes.elements(), getKey(service.routingData));
    					Service newService = new Service(serviceName, ne);
    					ConfigRequest newRequest = new ConfigRequest(newService, request.operationType);
    					this.requests.put((Integer) i, newRequest);
    				}	
    			}    			
    		}
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
	NetworkElement getRoutingData(Enumeration routes, String key) {
		if (key == null) throw new IllegalArgumentException("null key passed to Operation.getRoutingData");
		logger.debug("getting routing data for key '" + key + "'");
		
		while (routes.hasMoreElements()) {
			
			Route route = (Route)routes.nextElement();
			Pattern pattern = Pattern.compile(route.key, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(key);
			if (matcher.matches()) return route.ne;
				
		}
		logger.info("Couldn't find route for the request key: " + key);
		return NetworkElementFactory.defaultElement();
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
	String getKey(String criteria) {
		logger.trace("searching for request type using criteria '" + criteria + "'");
		
		List children = parameters.getContent();  
	    Iterator iterator = children.iterator();
	    while (iterator.hasNext()) {
	      Element child = (Element) iterator.next();
	      if (child.getName().equalsIgnoreCase("operation")) {
	    	  List children1 = child.getChildren();
	    	  Iterator iterator1 = children1.iterator();
	    	  while (iterator1.hasNext()) {
	    		  Element child1 = (Element) iterator1.next();
	    		  if (child1.getName().equalsIgnoreCase("parameter")) {
	                  logger.trace("found operation parameter '" + child1.getAttributeValue("name") + "'");
	    		      if (child1.getAttributeValue("name").equalsIgnoreCase(criteria)) {
	    		          String value = child1.getAttributeValue("value");
	    		      	  logger.trace("returning '" + value + "' for criteria '" + criteria + "'");
	    		      	  return value;
	    		      }
	    		  }
	    	  }
	      }
	    }
        logger.info("No key found for criteria '" + criteria + "'");
		return null;
	}
	
	public String toString() {
		return "id=" + id + ", type=" + type + ", requests=[" + requests + "]";
	}
}
