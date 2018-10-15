package net.sf.provisioner.requests;

import java.util.Map;

import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.config.LDAPNetworkElement;
import net.sf.provisioner.responses.Response;

import org.jdom.Document;
import org.jdom.Element;


/**
 * Responsible for sending modification requests to external LDAP services.
 * 
 * @see Request
 * 
 * @author g_pearson
 *
 */
public class LDAPModifyRequest extends LDAPRequest {

	public LDAPModifyRequest(ConfigRequest request, Document opParameters) {
		super(request,opParameters);
	}

    /**
     * Changes the behavior of {@link filterParameters}.  Converts  
     * parameters into name/LDAP {@link ModificationItem}s.
     * 
	 * All parameters must have the following attributes: name, value, type 
	 * (with type being one of "ADD", "REPLACE", or "REMOVE".)  An 
	 * {@link IllegalArgumentException} will be thrown if any of these 
	 * parameters are missing. 
	 * 
     * @param paramStore the {@linkplain Map} in which parameters should be
     * stored.
     * @param element a parameter element from a {@linkplain Request}'s 
     * operation parameters.
     * 
     * @throws IllegalArgumentException if a parameter tag is missing name, 
     * value, or type attributes.  Also thrown if type is not ADD, REPLACE, 
     * or REMOVE.
     */
	/* TODO: this allows only one ModificationItem to be stored per
	 * attribute.  Add support for specifying arrays of values.
	*/
    protected void storeParameter(Map<String, Object> paramStore, Element element) {
    	String name      = element.getAttributeValue("name" );
    	String value     = element.getAttributeValue("value");
    	String opTypeStr = element.getAttributeValue("type" );
    	
    	int opType;
    	if ("ADD".equalsIgnoreCase(opTypeStr)) {
    		opType = DirContext.ADD_ATTRIBUTE;
    	} else if ("REPLACE".equalsIgnoreCase(opTypeStr)) {
    		opType = DirContext.REPLACE_ATTRIBUTE;
    	} else if ("REMOVE".equalsIgnoreCase(opTypeStr)) {
    		opType = DirContext.REMOVE_ATTRIBUTE;
    	} else {
    		String message = String.format(
    				"Parameter for LDAP modify does not contain a valid type.  "
    				+ "name=%s, value=%s, type=%s",
    				name, value, opTypeStr
			);
    		throw new IllegalArgumentException(message);    				
    	}
    	
    	ModificationItem item = new ModificationItem(
    			opType,
    			new BasicAttribute(name, value)
    	);
    	storeParameter(paramStore, name, item);
    }
	
	@Override
	public Response sendRequest() throws Exception {
		DirContext context = ((LDAPNetworkElement) this.ne).findRootContext();
		
		try {
			ModificationItem[] items = params
					.values()
					.toArray(new ModificationItem[0])
			;
			
			if (logger.isTraceEnabled()) {
				logger.trace(
						String.format(
								"sending LDAP modify request for dn %s, and params: %s", 
								getDistinguishedName(), params
						)
				);
			}

			context.modifyAttributes(
					getDistinguishedName(),
					items
			);

			Response response    = new Response();
			response.result      = 
			response.errorStr    = "Modified attribute " + getDistinguishedName();
			response.retry       = false;
			response.successfull = true;
			return response;

		} catch (NamingException e) {
			Response response = new Response();
			response.result = "Failed to create attribute.";
			
			Throwable t = e.getRootCause();
			if (t != null) {
				// Save the LDAP server's response, if it's there...
				response.errorStr = t.getMessage();
			} else {
				// ... or save Java's error message.
				response.errorStr = e.getExplanation();
			}
			// TODO: find out which errors should result in a retry. 
			response.retry       = false;
			response.successfull = false;
			return response;
		} finally {
			context.close();			
		}				
	}
}
