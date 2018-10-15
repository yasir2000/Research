/**
 * 
 */
package net.sf.provisioner.core;


import java.util.*;

import net.sf.provisioner.adapters.SQLAdapter;
import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.config.Configuration;
import net.sf.provisioner.requests.*;
import net.sf.provisioner.responses.Response;

import org.apache.log4j.Logger;


/**
 * This class executes all requests pertaining to one operation
 * and all operations that have interdependencies .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:27 $
 * @author Gonzalo Espert
 */
public class Consumer extends Thread {

	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
	

	Operation operation;
	Configuration config;
	
	
	SQLAdapter operationQueue;

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
	public Consumer(Operation operation, Configuration config) { 
		this.operation = operation; 
		this.config = config;
		start();
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
	public void run() {
		
		boolean doContinue = true;
		
		try {
			
			/**
			 * Create access to operations queue
			 */
			this.operationQueue = new SQLAdapter(this.config.db);
			
			/* Apply provisioning rules before sending operation's requests */
			this.operation.applyRules(this.config.rules.elements(), this.config.services);
			
			Response response = new Response();
			
			logger.info("Setting status for the operation (In Process)...");
			this.operationQueue.updateOperationStatus(this.operation.getId(), "PROC");
			
			/* Print operation contents */
			logger.info("Operation type: " + this.operation.getType());
						
			/* Process each of the operation's requests */
			Enumeration requests = this.operation.requests.elements(); 
			
			while (requests.hasMoreElements() && doContinue) {
			
				ConfigRequest configRequest = (ConfigRequest) requests.nextElement();
				Request       request       = RequestFactory.createRequest(
						operation, 
						configRequest
				);
				
				logger.info("Sending request to " + configRequest.service.ne.name + "...");
				logger.info("Target type " + configRequest.service.ne.type + "...");

				response = request.sendRequest();
				/* If one of the operation's requests fails we do not continue */
				doContinue = response.successfull;				
			}
			
			logger.info("Registering request response in operations queue...");
			this.operationQueue.registerResponse(response.errorStr, this.operation.getId());
			
			logger.info("Update operation status in operations queue...");
			if (response.successfull) 
				/* Processed */
				this.operationQueue.updateOperationStatus(this.operation.getId(), "DONE");
			else
				if (response.retry)
					/* "Recoverable error" */
					this.operationQueue.updateOperationStatus(this.operation.getId(), "RETR");
				else
					/* "Irrecoverable error" */
					this.operationQueue.updateOperationStatus(this.operation.getId(), "ERRO");
			
			
		} catch (Exception e) {
			try {
				
				/* "Recoverable error" */
				this.operationQueue.registerResponse(e.toString(), this.operation.getId());
				this.operationQueue.updateOperationStatus(this.operation.getId(), "RETR");
				
			} catch (Exception ex) { ex.printStackTrace();}
			e.printStackTrace();
		}
		
		finally {
			
			/* Force resource release */
			logger.info("Freeing thread resources.......");
			System.gc();
			System.runFinalization();
		
		}
		
		return;
	}
}
