package net.sf.provisioner.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import net.sf.provisioner.utils.PathHelper;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class NetworkElementFactory {

	private static Logger logger = Logger.getLogger(NetworkElementFactory.class);
	
	
	public static NetworkElement createElement(String networkElementName, String configPath) {
		NetworkElement ne;
		if (networkElementName.equalsIgnoreCase("ldap_ne_name")) {
			ne = new LDAPNetworkElement();
		} else {
			ne = defaultElement();
		}
		ne.setConfigPath(configPath);
		
		InputStream customFileRef = null;
		try {
			customFileRef = PathHelper.pathToStream(networkElementName);
		} catch (IOException ioe) {
			// An exception is acceptable here.  We've no way of knowing
			// if this points to a file, or if the user expects the
			// system to guess the file name.
		}

		if (customFileRef == null) {
			try {
				customFileRef = PathHelper.pathToStream(configPath + networkElementName + ".xml");
			} catch (IOException ioe) {
				logger.fatal("Network element config. could not be read for '" + networkElementName + "'");
				// TODO: decide if returning null is what we want to do here.
				return null;
			}
		}
		
		if (customFileRef == null) {
			logger.fatal("Network element config. could not be found for '" + networkElementName + "'");
			// TODO: decide if returning null is what we want to do here.
			return null;
		}

		return populateNetworkElement(ne, customFileRef);
		/*if (customFileRef.exists()) {
			// The user has specified a path to the config. file.
			return populateNetworkElement(ne, customFileRef);
		} else {
			// The user wants the system to guess where the config. file is.
			return populateNetworkElement(
				ne, 
				new File("config/" + networkElementName + ".xml")
			);
		}*/
	}
	
	public static NetworkElement defaultElement() {
		return new NetworkElement();
	}
	
	private static NetworkElement populateNetworkElement(NetworkElement ne, InputStream xmlConfig) {
		try {
			Document d = new SAXBuilder().build(xmlConfig); 
			List children = d.getContent();  
			Iterator iterator = children.iterator();
			while (iterator.hasNext()) {
				Element child = (Element) iterator.next();
				ne.getParameters(child);
			}
			return ne;
		} catch (Exception e) {
			logger.fatal("Error reading network element configuration file " + xmlConfig);
			// TODO: throw something meaningful here.
			return null;
		}		
	}
}
