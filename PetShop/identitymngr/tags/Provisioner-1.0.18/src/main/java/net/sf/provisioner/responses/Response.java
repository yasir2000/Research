/**
 * 
 */
package net.sf.provisioner.responses;

import org.apache.log4j.Logger;

/**
 * Esta clase es .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, $Date: 2007/11/12 01:57:26 $
 * @author Gonzalo Espert
 */
public class Response {

	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
	
	public boolean retry = false;
	
	public boolean successfull = false;
	
	public String result, tipoOperacion, errorStr = null;
	
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
	public Response() {

	}
	
}
