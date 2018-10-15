/*
 * @(#)PartnerLinkType.java $Revision: 1 $ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2006-2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.xsl.wsdlext;

import java.util.List;

import javax.wsdl.extensions.ExtensibilityElement;

/**
 * Partner link type interface.
 *
 * @author Yasir Karam
 * @version $Revision: 1 $ 
 */
public interface PartnerLinkType extends ExtensibilityElement, NamedItem {
	/**
	 * Returns the roles list. List can contain from 1 to 2 roles.
	 * @return the roles list
	 */
	List<Role> getRoles();
}
