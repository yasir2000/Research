/**
 * 
 */
package net.sf.provisioner.adapters;

import org.apache.log4j.Logger;

/**
 * .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:24 $
 * @author Gonzalo Espert
 */
public abstract class Adapter {

	/** Logger for this class and subclasses */
    Logger logger = Logger.getLogger(getClass());
	
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
	public abstract String ExecuteCommand(String command) throws Exception;
	
}
