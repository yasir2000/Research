package net.sf.provisioner.requests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;

/**
 * Abstract extension of a {@link Request} object, augmented to provide
 * common functionality for LDAP requests.
 * 
 * @author g_pearson
 *
 */
/* TODO: Refactor the objectClass field into: LDAPCreateRequest; or
 * a common base class for LDAPCreateRequest, and any new requests that 
 * use it.
 */ 
public abstract class LDAPRequest extends Request {

	protected List<String>        objectClass       = new ArrayList<String>();
	protected String              distinguishedName = null;
	protected Map<String, Object> params            = null;
	
	
	public LDAPRequest(net.sf.provisioner.config.ConfigRequest request, Document opParameters) {
		this.ne = request.service.ne;

		setupRequest(opParameters);
	}

	protected void setupRequest(Document parameters) {
		Map<String, Object> params = filterParameters(parameters);		
		this.params       = params;           
	}

	protected void storeParameter(Map<String, Object> paramStore, String name, Object value) {
		if (name.equalsIgnoreCase("objectClass")) {
			objectClass.add((String) value);
		} else if (name.equalsIgnoreCase("ldap_operation_key")) {
			// Not a request parameter.
		} else if (name.equalsIgnoreCase("dn")) {
			distinguishedName = (String) value;			
		} else {		
			super.storeParameter(paramStore, name, value);
		}		
	}
	
	String getDistinguishedName() {
		return distinguishedName;
	}
	
	Object getParam(String paramName) {
		return params.get(paramName);
	}
}
