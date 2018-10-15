/*
 * @(#)AbstractExtensibilityElement.java $Revision: 1 $ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2006-2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.xsl.wsdlext;

import javax.wsdl.extensions.ExtensibilityElement;
import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

/**
 * Extensibility element implementation.
 *
 * @author Yasir Karam
 * @version $Revision: 1 $ 
 */
abstract class AbstractExtensibilityElement implements ExtensibilityElement {
	private QName elementType;
	private Boolean required;

	/**
	 * Creates a <code>AbstractExtensibilityElement</code> object.
	 */
	public AbstractExtensibilityElement() {
		super();
		elementType = 
			new QName(XMLConstants.W3C_XML_SCHEMA_NS_URI, "complexType");
		required = Boolean.FALSE;
	}

	/* (non-Javadoc)
	 * @see javax.wsdl.extensions.ExtensibilityElement#setElementType(javax.xml.namespace.QName)
	 */
	public void setElementType(QName elementType) {
		this.elementType = elementType;
	}

	/* (non-Javadoc)
	 * @see javax.wsdl.extensions.ExtensibilityElement#getElementType()
	 */
	public QName getElementType() {
		return elementType;
	}

	/* (non-Javadoc)
	 * @see javax.wsdl.extensions.ExtensibilityElement#setRequired(java.lang.Boolean)
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}

	/* (non-Javadoc)
	 * @see javax.wsdl.extensions.ExtensibilityElement#getRequired()
	 */
	public Boolean getRequired() {
		return required;
	}
}
