/**
 * Esta clase .
 * <p>
 * 
 * .
 * <p>
 * 
 *             
 * @version $Revision: 1.1.2.1 $, Jan 17, 2007
 * @author Gonzalo Espert 
 */
package net.sf.provisioner.commands;

import net.sf.provisioner.config.Line;

/**
 * @author Gonzalo
 *
 */
public class SERCommand extends Command {
	
	
	public Line line1 = new Line(), line2 = new Line();
	public String macAddress, puertoRTPminimo, puertoRTPmaximo, tipoDispositivo;

	public String operation, block;
	
	public SERCommand() {}
	
}
