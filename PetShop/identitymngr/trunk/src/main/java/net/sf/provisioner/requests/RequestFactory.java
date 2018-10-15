package net.sf.provisioner.requests;

import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.core.Consumer;
import net.sf.provisioner.core.Operation;

import org.apache.log4j.Logger;

/**
 * Factory for {@link Request} objects.
 * 
 * @author g_pearson
 * 
 * @see    Request
 * @see    Consumer
 * @see    Operation
 * @see    ConfigRequest
 */
public class RequestFactory {

	private static Logger logger = Logger.getLogger(RequestFactory.class);
	
	/**
	 * Builds a {@link Request} using the configuration information provided 
	 * by a {@linkplain Operation} and {@linkplain ConfigRequest}.
	 * 
	 * @param op the operation that's about to be transformed into a 
	 * {@linkplain request}. 
	 * @param request the {@linkplain ConfigRequest} that's about to be 
	 * transformed into a {@linkplain Request}.
	 *            
	 * @returns A {@linkplain Request} that's ready to be executed.  If either 
	 * parameter is badly configured or not supported a {@link FailedRequest} will be 
	 * returned.
	 */
	public static Request createRequest(Operation op, ConfigRequest configRequest) {
		Request request = validateRequest(op, configRequest);
		if (request != null) {
			return request;
		}
			
		String neType = configRequest.service.ne.type;
		if ("Intraway".equalsIgnoreCase(neType)) {		
			/* Send to Intraway */
			request = new IntrawayRequest(configRequest, op.getParameters());
			
		} else if ("Merak".equalsIgnoreCase(neType)) {
			/* Send to Merak */
			request = new MerakRequest(configRequest, op.getParameters());
			
		} else if ("SER".equalsIgnoreCase(neType)) {		
			/* Send to SER */
			request = new SERRequest(configRequest, op.getParameters());
			
		} else if ("Asterisk".equalsIgnoreCase(neType)) {
			request = new AsteriskRequest(configRequest, op.getParameters());
			
		} else if ("ldap_network_element_name".equalsIgnoreCase(neType)) {
			if ("LDAPC".equalsIgnoreCase(op.getType())) {
				request = new LDAPCreateRequest(configRequest, op.getParameters());	
			} else if ("LDAPD".equalsIgnoreCase(op.getType())) {
				request = new LDAPDeleteRequest(configRequest, op.getParameters());				
			} else if ("LDAPM".equalsIgnoreCase(op.getType())) {
				request = new LDAPModifyRequest(configRequest, op.getParameters());
			} else {			
				logger.warn("No LDAP support for operation type '" + op.getType() + "'");
			}
		} else if ("Generic".equalsIgnoreCase(neType)) {
			/* Create a Generic request type */			
			request = new GenericRequest(configRequest, op.getParameters());
		}

		if (request == null) {
			/* TODO: decide if we need to be less lenient with bad configurations.
			 * Should this case throw an exception?  
			 */
			String message = "No request found for networkElement type '" + neType + "'";
			logger.warn(message);
			request = new FailedRequest(message);
		}
		
		return request;
	}
	
	/**
	 * Validates the content of <code>op</code> and <code>request</code>. 
	 *  
	 * @param op the operation that's about to be transformed into a 
	 * {@linkplain request}.  
	 * @param request the {@linkplain ConfigRequest} that's about to be 
	 * transformed into a {@linkplain Request}.
	 * 
	 * @returns A {@link FailedRequest} is returned if <code>op</code> or 
	 * <code>request</code> have an invalid configuration. 
	 * configurations. <code>null</null> is returned is both are valid.
	 */
	// TODO: think of a less confusing name?
	private static Request validateRequest(Operation op, ConfigRequest request) {
		if (request.service.ne.name == null) {
			return new FailedRequest(
					String.format(
							  "Network element name is not valid ('%s'). "
							+ "Please review routing configuration.",
							request.service.ne.name
					)
			);						
		}
		
		if (request.service.ne.type == null) {	
			return new FailedRequest(
					String.format(
							  "Network element type is not valid ('%s'). " 
							+ "Please review routing configuration.",
							request.service.ne.type
					)
			);
		}
		return null;
	}
}
