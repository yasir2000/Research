/*
 * @(#)PartnerLinkTypeImpl.java $Revision: 1 $ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2006-2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.xsl.wsdlext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * PartnerLinkType
 *
 * @author Yasir Karam
 * @version $Revision: 1 $ 
 */
public class PartnerLinkTypeImpl extends AbstractExtensibilityElement 
	implements PartnerLinkType, Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -7714819093344262809L;
	private final String name;
	private final List<Role> roles;

	/**
	 * Creates a <code>PartnerLinkTypeImpl</code> object.
	 * @param name a partner link type name
	 */
	public PartnerLinkTypeImpl(String name) {
		super();
		this.name = name;
		roles = new ArrayList<Role>(2);
	}

	/**
	 * Returns the partner link type name.
	 * @return the partner link type name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the roles list. List can contain from 1 to 2 roles.
	 * @return the roles list
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Adds a role to roles list.
	 * @param role a role to add
	 */
	public void addRole(Role role) {
		roles.add(role);
	}
}
