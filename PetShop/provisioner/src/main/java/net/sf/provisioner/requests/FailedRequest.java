package net.sf.provisioner.requests;

import net.sf.provisioner.responses.Response;

/**
 * A request that performs no operation when {@linkplain sendRequest} is 
 * called, then returns a response object with a custom error message.
 * The message typically details user configuration errors (e.g. no network 
 * element name specified.)
 * 
 * @author  g_pearson
 * @version $Revision: 1 $
 * @created Nov 14, 2007 
 */
public class FailedRequest extends Request {

	private String errorMessage;

	/**
	 * Creates a FailedRequest with a custom error message.
	 * @param the human-readable reason for the request's failure.
	 */
	public FailedRequest(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Returns a failed response containing the error message passed to the
	 * constructor.
	 */
	@Override
	public Response sendRequest() throws Exception {
		Response response    = new Response();
		response.result      =
		response.errorStr    = errorMessage;
		response.retry       = false;
		response.successfull = false;
		return response;
	}
}
