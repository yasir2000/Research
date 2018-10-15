/**
 * 
 */
package net.sf.provisioner.connectors;

import org.apache.log4j.Logger;

/**
 * Esta clase .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:26 $
 * @author Gonzalo Espert
 */
public abstract class Connector {
	
	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
    
	protected boolean isConnected = false;
	
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
	public abstract void Connect()throws Exception;
	
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
	public abstract void Disconnect();
	
}
