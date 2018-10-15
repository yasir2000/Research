/*
 * @(#)Role.java $Revision: 1 $ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2006-2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.xsl.wsdlext;

import javax.wsdl.PortType;

/**
 * Role interface.
 *
 * @author Yasir Karam
 * @version $Revision: 1 $ 
 */
public interface Role extends NamedItem  {
	/**
	 * Returns the port type.
	 * @return the port type.
	 */
	PortType getPortType();
}
