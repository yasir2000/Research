/**
 * This class represents .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, 08/10/2007
 * @author Gonzalo Espert 
 */
package net.sf.provisioner.requests;


import net.sf.provisioner.commands.SPMLADDCommand;
import net.sf.provisioner.responses.Response;

import org.jdom.Document;
import org.jdom.Element;
import org.openspml.client.SpmlClient;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openspml.message.AddRequest;
import org.openspml.message.AddResponse;


/**
 * @author espertgo
 *
 */
public class SPMLRequest extends Request {

	/* Add Request command */
	SPMLADDCommand command = new SPMLADDCommand();
	
	protected SpmlClient client = new SpmlClient();
	protected AddRequest request = new AddRequest();
	protected HashMap userAttr = new HashMap();
	protected AddResponse response;
	protected final String url = "http://localhost:8080/lighthouse/servlet/rpcrouter2";

	/**
	 * 
	 */
	public SPMLRequest(net.sf.provisioner.config.ConfigRequest request, Document parameters) {

		try {
			logger.info("Creating a SPML request to add a user");
			_initRequest(parameters);
			create();
			logger.info("SPML request generation is complete.");
		} catch (Throwable addUser) {
			// add exception handling
			logger.info(addUser.toString());
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
	void _initRequest(Document parameters) {
		
		String paramName;
		
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
	    			  	paramName = child1.getAttributeValue("name");
	    		      	if (paramName.equalsIgnoreCase("firstName")) this.command.firstName = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("lastName")) this.command.lastName = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("fullName")) this.command.fullName = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("password")) this.command.password = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("email")) this.command.email = child1.getAttributeValue("value");
	    		      	if (paramName.equalsIgnoreCase("identifier")) this.command.identifier = child1.getAttributeValue("value");
	    		  }
	    	  }
	      }
	    }
	}
	
	/**
	 * Create SPML request for add user
	 * 
	 * @exception Exception ex
	 */
	private void create() throws Exception {
		
		this.client.setTrace(true);
		this.client.setUrl(this.url);
		
		this.request.setIdentifier(this.command.identifier);
		this.request.setObjectClass("user");
		// define user attributes
		this.userAttr.put("password", this.command.password);
		this.userAttr.put("email", this.command.email);
		this.userAttr.put("firstname", this.command.firstName);
		this.userAttr.put("lastname", this.command.lastName);
		this.userAttr.put("fullname", this.command.fullName);
		this.request.setAttributes(userAttr);
		
		// generate SPML request to add user
		response = (AddResponse)this.client.request(request);
		this.client.throwErrors(response);
		
	}
	
	/* (non-Javadoc)
	 * @see requests.Request#sendRequest()
	 */
	@Override
	public Response sendRequest() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
