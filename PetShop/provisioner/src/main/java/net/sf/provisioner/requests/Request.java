/**
 * 
 */
package net.sf.provisioner.requests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.lang.model.util.Elements;
import javax.naming.directory.ModificationItem;

import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.config.NetworkElement;
import net.sf.provisioner.core.Consumer;
import net.sf.provisioner.core.Operation;
import net.sf.provisioner.core.Producer;
import net.sf.provisioner.responses.Response;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;

/**
 * <p>Base class for requests to external services.</p>
 * 
 * <p>A {@link Producer} reads requests from an external queue, and creates 
 * {@link Operation}s from their information.
 * 
 * The operation is passed to a {@link Consumer}, which populates the 
 * operation's set of {@link ConfigRequests} (a single operation may require 
 * multiple requests to multiple end-points.)
 *   
 * The consumer then uses a {@link RequestFactory} to convert each 
 * {@linkplain ConfigRequest} into a {@linkplain Request}, which is 
 * executed, and examine for a successful response.  
 * </p>
 *   
 * @version $Revision: 1.1.2.2 $, $Date: 2007/11/13 22:36:03 $
 * @author Gonzalo Espert
 * @author G. Pearson
 */
public abstract class Request {
	
	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
    
	/* Elemento de red del requerimiento */
	NetworkElement ne = new NetworkElement();
    

	public abstract Response sendRequest() throws Exception;
    
	/**
	 * Convenience method for extending classes.  Takes a set of XML elements 
	 * and finds all "parameter" tags. 
	 * Stores found parameters in a {@linkplain Map}.  Extending classes 
	 * have can modify the results of this method by overriding the 
	 * <code>storeParameter</code> methods.
	 * 
	 * @param opParameters an XML document detailing parameters from an
	 * operation.  Typically, these will come from a request fetched from
	 * the database.
	 * 
	 * @return a {@linkplain Map} of parameter values.
	 */
    protected Map<String, Object> filterParameters(Document opParameters) {
    	Collection<Element> operationElements = filterElements(
    			opParameters.getContent().iterator(), "operation"
    	);
    	    	
    	Map<String, Object> result   = new HashMap<String, Object>();
    	/* TODO: find out if we assume that there will only be one operation 
    	 * per request? */
    	for (Element opElement : operationElements) {
    		Collection<Element> params = filterElements(
    				opElement.getContent().iterator(), "parameter"
    		);
    		
    		for (Element param : params) {
    			storeParameter(
    					result,
    					param.getAttributeValue("name"),
    					param.getAttributeValue("value")
    			);
    		}
    	}    
    	return result;
    }
    
    /**
     * Called by {@link filterParameters} when building a {@linkplain Map}
     * of request parameters.  By default, this simply calls 
     * {@link storeParameter(Map,String,String)} with the element's 
     * <code>name</code> and <code>value</code> attributes.  Extending classes 
     * may choose to store extra information (e.g. LDAPModifyRequest converts 
     * parameters into {@link ModificationItem}s.) 
     *   
     * @param paramStore the {@linkplain Map} that any parameters should be
     * stored in.
     * @param element a parameter element from a {@linkplain Request}'s 
     * operation parameters.
     *            
     */
    protected void storeParameter(Map<String, Object> paramStore, Element element) {
    	storeParameter(
    			paramStore, 
    			element.getAttributeValue("name"), 
    			element.getAttributeValue("value")
    	);
    }
    
    /**
     * The default method for processing request parameters.  Stores the 
     * parameter name and value in <code>paramStore</code>.
     * 
     * @param paramStore the {@linkplain Map} that any parameters should be 
     * stored in.
     * @param name the name of the operation parameter.
     * @param value the value of the named operation parameter.
     */
    protected void storeParameter(Map<String, Object> paramStore, String name, Object value) {
    	List<Object> storedValue = (List<Object>) paramStore.get(name);
    	if (storedValue == null) {
    		// Create a list for this key value and store it in the paramStore.
    		storedValue = new ArrayList<Object>();
    		paramStore.put(name, storedValue);
    	}
    	
		storedValue.add(value); 
    }
    
    /**
     * Filters {@linkplaiun Element}'s by comparing 
     * {@link Element#getName()} with <code>matchingName</code> 
     * (simple equalsIgnoreCase comparison.)         
     * 
     * @param i an iterator for a collection of {@linkplain Elements}.
     * @param matchingName the value that elements are filtered by.
     * @return a collection of elements with names that match <code>matchingName</code>
     */
    protected Collection<Element> filterElements(Iterator<Element> i, String matchingName) {
    	if (matchingName == null) {
    		throw new IllegalArgumentException("matchingName cannot be null.");
    	}
    	
    	List<Element> result = new ArrayList<Element>();
    	while (i.hasNext()) {
    		Element child = i.next();
    		if (matchingName.equalsIgnoreCase(child.getName())) {
    			result.add(child);
    		}
    	}
    	return result;
    }
}
