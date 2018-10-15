/**
 * 
 */
package net.sf.provisioner.core;

import net.sf.provisioner.adapters.SQLAdapter;
import net.sf.provisioner.config.Configuration;

import org.apache.log4j.Logger;



/**
 * .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:27 $
 * @author Gonzalo Espert
 */
public class Producer {

	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
	
	public boolean isRunning = false;
	long timeInterval;
	String configPath;
	int maxConsumers;
	
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
	public Producer(long timeInterval, String configPath, int maxConsumers) {
		
		this.timeInterval = timeInterval;
		this.configPath = configPath;
		this.maxConsumers = maxConsumers;
	}
	
	/**
     * Main loop.
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
	public void Start(Configuration config) throws Exception{
				
		/**
		 * Creates access to the operations queue
		 */
		this.operationQueue = new SQLAdapter(config.db);
		
		try {
			
			this.isRunning = true;
			
			while (isRunning) {
				
				logger.info("Retrieving pending operations...");
				Operation[] operations = this.operationQueue.getPendingOperations(this.maxConsumers);
				
				for (int i = 0; i < this.maxConsumers; i++) {
	
					if (!operations[i].getId().equalsIgnoreCase("0")) {
					
						logger.info("Create consumer to process operation number: " + operations[i].getId());
						Consumer consumer = new Consumer(operations[i], config);
					}
				}
				try {
					Thread.sleep(this.timeInterval);
				}
				catch (Exception e) {
					throw e;
				}
			}
			
		} catch (Exception e) {
		  			
			throw e;
		}
		
		finally {
			
			this.isRunning = false;
		}
	}
	
	/**
     * End of main loop.
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
	public void Stop() {
		this.isRunning = false;
	}
}
