/*
 * @(#)WSDLHelper.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam 
 */
package clove.neptune.bpeldeployment.xsl;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.wsdl.Definition;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import clove.neptune.bpeldeployment.xsl.wsdlext.PartnerLinkType;
import clove.neptune.bpeldeployment.xsl.wsdlext.PartnerLinkTypeDeserializer;
import clove.neptune.bpeldeployment.xsl.wsdlext.Role;

/**
 * WSDL Helper
 *
 * @author Yasir Karam
 * @version $Revision$
 *
 */
public class WSDLHelper {
	private static final Map<String, WSDLHelper> instances = 
		new WeakHashMap<String, WSDLHelper>();
	
	private static final Logger logger = Logger.getLogger(WSDLHelper.class.getName());  
	
	private final List<File> wsdlFiles = new LinkedList<File>();
	private final Map<File, Definition> wsdlDefinitions =
		new HashMap<File, Definition>();
	private WSDLReader wsdlReader = null;

	/**
	 * Creates a {@code WSDLHelper} object.
	 * @param pkgDir a package working directory.
	 */
	public WSDLHelper(String pkgDir) {
		super();
		File pkg = new File(pkgDir);
		logger.info("pkg: " + pkg.getAbsolutePath());
		scanDirectory(pkg, new WSDLFileFilter());
	}
	
	private void scanDirectory(File dir, FileFilter filter) {
		if (dir.exists()) {
			if (dir.isDirectory()) {
				wsdlFiles.addAll(Arrays.asList(dir.listFiles(filter)));
				// process subdirectories
				for (File f : dir.listFiles()) {
					if (f.isDirectory()) {
						scanDirectory(f, filter);
					}
				}
			} else if (dir.isFile()) {
				if (filter.accept(dir)) {
					wsdlFiles.add(dir);
				}
			}
		}
	}

	/**
	 * Returns portType qualified name by the partnerLinkType and role name.
	 * @param pkgDir a package directory
	 * @param partnerLinkType a partner link qualified name
	 * @param roleName a role name
	 * @return portType qualified name by the partnerLinkType and role name.
	 */
	@SuppressWarnings("unchecked")
	public static QName getPortType(String pkgDir, QName partnerLinkType, String roleName) {
		WSDLHelper wsdlHelper = WSDLHelper.getInstance(pkgDir);
		try {
			Definition def = wsdlHelper.lookupDefinition(partnerLinkType.getNamespaceURI());
			if (def != null) {
				PartnerLinkType plt;
				for (ExtensibilityElement ee : (List<ExtensibilityElement>) def.getExtensibilityElements()) {
					if (ee instanceof PartnerLinkType) {
						plt = (PartnerLinkType) ee;
						if (partnerLinkType.getLocalPart().equals(plt.getName())) {
							for (Role role : plt.getRoles()) {
								if (roleName.equals(role.getName())) {
									return role.getPortType().getQName();
								}
							}
						}
					}
				}
			}	
		} catch (WSDLException e) {
			logger.log(
					Level.SEVERE, 
					"Error lookup WSDL definition with targetNamespace: " + partnerLinkType.getNamespaceURI(), e);
		}
		return null;
	}
	
	/**
	 * Returns the service qualified name by the portType.
	 * @param pkgDir a package directory
	 * @param portType a port type qualified name
	 * @return the service qualified name by the portType.
	 */
	@SuppressWarnings("unchecked")
	public static QName getService(String pkgDir, QName portType) {
		WSDLHelper wsdlHelper = WSDLHelper.getInstance(pkgDir);
		try {
			Definition def = wsdlHelper.lookupDefinition(portType.getNamespaceURI());
			if (def != null) {
				for (Service service : ((Map<QName, Service>) def.getServices()).values()) {
					for (Port port : ((Map<String, Port>) service.getPorts()).values()) {
						if (port.getBinding() != null 
								&& port.getBinding().getPortType() != null 
								&& portType.equals(port.getBinding().getPortType().getQName())) {
							return service.getQName();
						}
					}
				}
			}	
		} catch (WSDLException e) {
			logger.log(
					Level.SEVERE, 
					"Error lookup WSDL definition with targetNamespace: " + portType.getNamespaceURI(), e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static String getPort(String pkgDir, QName portType, QName service) {
		WSDLHelper wsdlHelper = WSDLHelper.getInstance(pkgDir);
		try {
			Definition def = wsdlHelper.lookupDefinition(portType.getNamespaceURI());
			if (def != null) {
				Service serviceObj = def.getService(service);
				for (Port port : ((Map<String, Port>) serviceObj.getPorts()).values()) {
					if (port.getBinding() != null 
							&& port.getBinding().getPortType() != null 
							&& portType.equals(port.getBinding().getPortType().getQName())) {
						return port.getName();
					}
				}
			}	
		} catch (WSDLException e) {
			logger.log(
					Level.SEVERE, 
					"Error lookup WSDL definition with targetNamespace: " + portType.getNamespaceURI(), e);
		}
		return null;
	}

	/**
	 * Returns the {@code WSDLHelper} instance by the specified package working directory.
	 * @param pkgDir a package working directory.
	 * @return the {@code WSDLHelper} instance by the specified package working directory.
	 */
	protected static WSDLHelper getInstance(String pkgDir) {
		WSDLHelper wsdlHelper = instances.get(pkgDir);
		if (wsdlHelper == null) {
			wsdlHelper = new WSDLHelper(pkgDir);
			instances.put(pkgDir, wsdlHelper);
		}
		return wsdlHelper;
	}
	
	/**
	 * Returns definition by the specified namespace.
	 * @param namespace a definition target namespace
	 * @return definition by the specified namespace.
	 * @throws WSDLException if WSDL error occurs
	 */
	public Definition lookupDefinition(String namespace) throws WSDLException {
		Definition def = null;
		for (File f : wsdlFiles) {
			def = getDefinition(f);
			if (def != null && namespace.equals(def.getTargetNamespace())) {
				break;
			} else {
				def = null;
			}
		}
		return def;
	}
	
	/**
	 * Returns {@link Definition} object by its file.
	 * @param file a WSDL file
	 * @return {@link Definition} object by its file.
	 * @throws WSDLException if WSDL error occurs
	 */
	protected Definition getDefinition(File file) throws WSDLException {
		logger.info("Load wsdl: " + file);
		Definition def = wsdlDefinitions.get(file);
		if (def == null) {
			if (wsdlReader == null) {
				WSDLFactory wsdlFactory;
				wsdlFactory = WSDLFactory.newInstance();
				ExtensionRegistry extensionRegistry = wsdlFactory.newPopulatedExtensionRegistry();
				PartnerLinkTypeDeserializer pltDeserializer = new PartnerLinkTypeDeserializer(this);
				extensionRegistry.registerDeserializer(
						Definition.class, PartnerLinkTypeDeserializer.PARTNER_LINK_TYPE_1_1, pltDeserializer);
				extensionRegistry.registerDeserializer(
						Definition.class, PartnerLinkTypeDeserializer.PARTNER_LINK_TYPE_2_0, pltDeserializer);
				wsdlReader = wsdlFactory.newWSDLReader();
				wsdlReader.setExtensionRegistry(extensionRegistry);
			}
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document doc = db.parse(file); 
				def = wsdlReader.readWSDL(file.getAbsolutePath(), doc);
				if (def != null) {
					// cache def:
					wsdlDefinitions.put(file, def);
				}
			} catch (Exception e) {
				throw new WSDLException(WSDLException.PARSER_ERROR, "Error reading WSDL: " + file, e);
			}
		}
		return def;
	}
}
