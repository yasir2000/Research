package net.sf.provisioner.requests;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.config.LDAPNetworkElement;
import net.sf.provisioner.responses.Response;

import org.jdom.Document;


/**
 * @author g_pearson
 *
 */
public class LDAPDeleteRequest extends LDAPRequest {

	public LDAPDeleteRequest(ConfigRequest request, Document opParameters) {
		super(request,opParameters);
	}

	@Override
	public Response sendRequest() throws Exception {
		DirContext context = ((LDAPNetworkElement) this.ne).findRootContext();
		
		try {
			if (logger.isTraceEnabled()) {
				logger.trace(
						String.format(
								"sending LDAP delete request for dn '%s'",
								getDistinguishedName()
						)
				);
			}
			context.unbind(getDistinguishedName());
			
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
