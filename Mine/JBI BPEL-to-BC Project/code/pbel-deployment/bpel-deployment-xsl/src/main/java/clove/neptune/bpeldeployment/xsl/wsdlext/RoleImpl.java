/*
 * @(#)RoleImpl.java $Revision: 1 $ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2006-2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.xsl.wsdlext;

import java.io.Serializable;

import javax.wsdl.PortType;

/**
 * Role implementation.
 *
 * @author Yasir Karam
 * @version $Revision: 1 $ 
 */
public class RoleImpl implements Role, Serializable {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1528502536865332563L;
	private final String name;
	private final PortType portType;
	
	/**
	 * Creates a <code>RoleImpl</code> object.
	 * @param name a role name
	 * @param portType a port type
	 */
	public RoleImpl(String name, PortType portType) {
		super();
		this.name = name;
		this.portType = portType;
	}

	/**
	 * Returns the role name.
	 * @return the role name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the port type.
	 * @return the port type.
	 */
	public PortType getPortType() {
		return portType;
	}
}
