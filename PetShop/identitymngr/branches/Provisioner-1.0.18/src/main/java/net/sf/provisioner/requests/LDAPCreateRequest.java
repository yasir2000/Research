package net.sf.provisioner.requests;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.config.LDAPNetworkElement;
import net.sf.provisioner.responses.Response;
import net.sf.provisioner.utils.LDAPUtils;

import org.jdom.Document;


/**
 * @author g_pearson
 *
 */
public class LDAPCreateRequest extends LDAPRequest {

	public LDAPCreateRequest(ConfigRequest request, Document opParameters) {
		super(request,opParameters);
	}

	@Override
	public Response sendRequest() throws Exception {
		DirContext context = ((LDAPNetworkElement) this.ne).findRootContext();
		
		try {
			if (logger.isTraceEnabled()) {
				logger.trace(
						String.format(
								"sending LDAP create request with objectClass: %s, and params: %s",
								objectClass, params
						)
				);
				logger.trace("baseDN: " + this.getDistinguishedName());
			}

			context.createSubcontext(
					this.getDistinguishedName(),
					LDAPUtils.buildAttrs(
							this.objectClass.toArray(new String[0]), 
							params
					)
			);
			Response response    = new Response();
			response.result      = 
			response.errorStr    = "Created attribute " + getDistinguishedName();
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
