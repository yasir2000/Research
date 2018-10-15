/*
 * @(#)PartnerLinkTypeDeserializer.java $Revision: 1 $ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2006-2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.xsl.wsdlext;

import javax.wsdl.Definition;
import javax.wsdl.PortType;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionDeserializer;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import clove.neptune.bpeldeployment.xsl.WSDLHelper;

/**
 * <em>partnerLinkType</em> element deserializer.
 *
 * @author Yasir Karam
 * @version $Revision: 1 $ 
 */
public class PartnerLinkTypeDeserializer implements ExtensionDeserializer {
	public static final QName PARTNER_LINK_TYPE_2_0 = 
		new QName("http://docs.oasis-open.org/wsbpel/2.0/plnktype", "partnerLinkType");
	public static final QName PARTNER_LINK_TYPE_1_1 = 
		new QName("http://schemas.xmlsoap.org/ws/2003/05/partner-link/", "partnerLinkType");
	
	private final WSDLHelper wsdlHelper;

	/**
	 * Creates a {@code PartnerLinkTypeDeserializer} object.
	 * @param wsdlHelper a {@link WSDLHelper} object
	 */
	public PartnerLinkTypeDeserializer(WSDLHelper wsdlHelper) {
		super();
		this.wsdlHelper = wsdlHelper;
	}

	/* (non-Javadoc)
	 * @see javax.wsdl.extensions.ExtensionDeserializer#unmarshall(java.lang.Class, javax.xml.namespace.QName, org.w3c.dom.Element, javax.wsdl.Definition, javax.wsdl.extensions.ExtensionRegistry)
	 */
	@SuppressWarnings("unchecked")
	public ExtensibilityElement unmarshall(Class parentType, QName elementType,
			Element element, Definition def, ExtensionRegistry extReg)
			throws WSDLException {
		PartnerLinkTypeImpl partnerLinkType = 
			new PartnerLinkTypeImpl(element.getAttribute("name"));
		partnerLinkType.setElementType(elementType);
		setRoles(partnerLinkType, element, def);
		return partnerLinkType;
	}
	
    private void setRoles(PartnerLinkTypeImpl partnerLT, Element element, Definition definition) throws WSDLException {
        NodeList list = element.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            Node node = list.item(i);
            if (node instanceof Element) {
                Element nodeElement = (Element) node;
                if (nodeElement.getLocalName().equals("role")) {
                	if (nodeElement.getNamespaceURI().equals(PARTNER_LINK_TYPE_2_0.getNamespaceURI())) {
                        Role role = 
                        	new RoleImpl(
                        			nodeElement.getAttribute("name"), 
                        			getPortType(definition, nodeElement.getAttribute("portType")));
                        partnerLT.addRole(role);
                	} else if (nodeElement.getNamespaceURI().equals(PARTNER_LINK_TYPE_1_1.getNamespaceURI())) {
                        NodeList nodes = nodeElement.getChildNodes();
                        for (int j = 0; j < nodes.getLength(); ++j) {
                            node = nodes.item(j);
                            if (node instanceof Element) {
                                Element elem = (Element) node;
                                if (elem.getNamespaceURI().equals(PARTNER_LINK_TYPE_1_1.getNamespaceURI())
                                        && elem.getLocalName().equals("portType")) {
                                    Role role = 
                                    	new RoleImpl(
                                    			nodeElement.getAttribute("name"), 
                                    			getPortType(definition, elem.getAttribute("name")));
                                    partnerLT.addRole(role);
                                }
                            }
                        }
                	}	
                }
            }
        }
    }

    /**
     * Extract the right PortType from the definition. 
     * @param definition {@link Definition} object
     * @param name port type name like "buy:BuyerPT"
     * @return a {@link PortType} object
     * @throws WSDLException if the PortType is not found
     */
    private PortType getPortType(Definition definition, String name) throws WSDLException {
        String[] portTypeName = name.split(":");
        portTypeName[0] = definition.getNamespace(portTypeName[0]);
        QName portTypeQName = new QName(portTypeName[0], portTypeName[1]);

        PortType portType = null;
    	if (portTypeQName.getNamespaceURI().equals(definition.getTargetNamespace())) {
        	portType = definition.getPortType(portTypeQName);
    	} else {
    		Definition partnerDef = wsdlHelper.lookupDefinition(portTypeQName.getNamespaceURI());
    		if (partnerDef == null) {
                // no such portType found in wsdl definition
                throw 
                new WSDLException(
                		WSDLException.INVALID_WSDL,
                        "WSDL with " + portTypeQName.getNamespaceURI() + " targetNamespace not found");
    		}
    		portType = partnerDef.getPortType(portTypeQName);
    	}
    	
        if (portType != null) {
            return portType;
        } else {
            // no such portType found in wsdl definition
            throw new WSDLException(WSDLException.INVALID_WSDL, "PortType: " + portTypeQName + " not found");
        }
    }
}
