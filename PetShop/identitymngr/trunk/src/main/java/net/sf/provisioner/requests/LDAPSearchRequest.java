package net.sf.provisioner.requests;

import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.config.LDAPNetworkElement;
import net.sf.provisioner.responses.Response;

import org.jdom.Document;


/**
 * @author g_pearson
 *
 */
public class LDAPSearchRequest extends LDAPRequest {

	private int    searchScope = -1;
	private String filter      = null;
	
	
	public LDAPSearchRequest(ConfigRequest request, Document opParameters) {
		super(request,opParameters);
	}

	protected void storeParameter(Map<String, Object> paramStore, String name, String value) {
		if (name.equalsIgnoreCase("searchScope")) {
			if (value.equalsIgnoreCase("object")) {
				searchScope = SearchControls.OBJECT_SCOPE;
			} else if (value.equalsIgnoreCase("onelevel")) {
				searchScope = SearchControls.ONELEVEL_SCOPE;
			} else if (value.equalsIgnoreCase("subtree")) {
				searchScope = SearchControls.SUBTREE_SCOPE;
			} else {
				throw new IllegalArgumentException("Invalid value for searchScope: '" +value + "'");
			}
		} else if (name.equalsIgnoreCase("filter")) {
			filter = value;
		} else {
			super.storeParameter(paramStore, name, value);
		}
	}
	
	@Override
	public Response sendRequest() throws Exception {
		DirContext context = ((LDAPNetworkElement) this.ne).findRootContext();
		
		try {
			if (logger.isTraceEnabled()) {
				logger.trace(
						String.format(
								"sending LDAP search request for dn: '%s', filter: '%s', searchScope: %d",
								getDistinguishedName(), filter, searchScope
						)
				);
			}
			SearchControls controls = new SearchControls();
	        controls.setSearchScope(searchScope);
	        NamingEnumeration results = context.search(
	        		getDistinguishedName(), 
	        		filter, 
	        		controls
	        );

	        StringBuilder buffer = new StringBuilder();
	        while (results.hasMore()) {
	           SearchResult searchResult = (SearchResult) results.next();
	           Attributes attributes = searchResult.getAttributes();
	           NamingEnumeration ne2 = attributes.getAll();
	           while (ne2.hasMore()) {
	        	   Attribute attr = (Attribute) ne2.next();
	        	   buffer.append(attr.getID() + "=" + attributes.get(attr.getID()));
	           }
	           buffer.append("\n");
	        }
	        
			Response response    = new Response();
			response.result      = 
			response.errorStr    = "Deleted attribute " + getDistinguishedName();
			response.retry       = false;
			response.successfull = true;
			return response;

		} catch (NamingException e) {
			Response response = new Response();
			response.result = "Failed to delete attribute.";
			
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
