/*
 * @(#)BPELDeployerInstaller.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam 
 */
package clove.neptune.bpeldeployment.jbi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.security.AccessController;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jbi.JBIException;
import javax.jbi.component.Bootstrap;
import javax.jbi.component.InstallationContext;
import javax.management.ObjectName;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import clove.neptune.bpeldeployment.util.Files;
import clove.neptune.bpeldeployment.util.GetClassLoaderPrivilegedAction;
import clove.neptune.bpeldeployment.util.NamespaceContext;

/**
 * BPEL Deployer Installer.
 *
 * @author Yasir Karam
 * @version $Revision$
 */
public class BPELDeployerInstaller implements Bootstrap {
    private static final String PROCESS_DEPLOYER_PROPERTIES = "pd:process-deployer/pd:properties/pd:property";
    private static final String PROCESS_DEPLOYER_RESOURCES = "pd:process-deployer/pd:resources/pd:resource";
	private Logger defaultLogger;
    private Logger bootstrapLogger ;
    private InstallationContext installationContext;
    
    public static final QName PROCESS_DEPLOYER = 
    	new QName("http://clove/neptune/bpeldeployment", "process-deployer", "pd");
    
    
	/* (non-Javadoc)
	 * @see javax.jbi.component.Bootstrap#cleanUp()
	 */
	public void cleanUp() throws JBIException {
	}

    /**
     * Default implementation that does not have extension mbean return null.
	 * @see javax.jbi.component.Bootstrap#getExtensionMBeanName()
	 */
	public ObjectName getExtensionMBeanName() {
		return null;
	}

    /**
     * Returns logger initialized from the installation context or a default logger.
     * @return logger initialized from the installation context or a default logger.
     */
    public Logger getLogger() {
        // try init bootstrap logger
        if (bootstrapLogger == null && installationContext != null) {
            try {
            	bootstrapLogger =
            		installationContext.getContext().getLogger(this.getClass().getName(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // init default logger if required
        if (bootstrapLogger == null && defaultLogger == null) {
        	defaultLogger = Logger.getLogger(this.getClass().getName(), null);
        }
        return (bootstrapLogger != null) ? bootstrapLogger : defaultLogger;
    }

    /** 
	 * Initializes the installation environment for a component.
	 * @see javax.jbi.component.Bootstrap#init(javax.jbi.component.InstallationContext)
	 */
	public void init(InstallationContext installCtx) throws JBIException {
        if (installCtx == null) {
            throw new JBIException("Null Installation Context received in Component Bootstrap initialization");
        }
        // initialize reference to component context
        this.installationContext = installCtx;

        // initialize logger
        getLogger().info(installationContext.getComponentName() + " : Component Installer initialized");
	}

	/* (non-Javadoc)
	 * @see javax.jbi.component.Bootstrap#onInstall()
	 */
	public void onInstall() throws JBIException {
		Map<String, String> props = getConfiguration(installationContext, PROCESS_DEPLOYER_PROPERTIES);
		String workDirStr = props.get(BPELDeployer.WORK_DIR);
		if (workDirStr == null) {
			throw new JBIException("Error installation: " + BPELDeployer.WORK_DIR + " property is to specified.");
		}
		File workDir = new File(workDirStr);
		getLogger().info("Prepare work directory: " + workDir.getAbsolutePath());
		if (!workDir.exists()) {
			workDir.mkdirs();
		} else {
			Files.deleteDir(workDir);
			workDir.mkdir();
		}
		
		File file;
		File workspaceDir = new File(installationContext.getContext().getWorkspaceRoot());
		Properties config = new Properties();
		config.putAll(props);
		file = new File(workspaceDir, BPELDeployer.CONFIG_FILE);
		try {
			config.storeToXML(new FileOutputStream(file), "BPELDeployer configuration", "utf-8");
		} catch (IOException e) {
			throw new JBIException("Error creating file: " + file.getAbsolutePath());
		}
		// create Resources:
		props = getConfiguration(installationContext, PROCESS_DEPLOYER_RESOURCES);
		InputStream is;
		ClassLoader classLoader = 
			AccessController.doPrivileged(new GetClassLoaderPrivilegedAction(getClass(), false));
		
		ReadableByteChannel in = null;
		FileChannel out = null;
		final long bufferSize = 8 * 1024;
		long position;
		long count;
		for (Map.Entry<String, String> entry : props.entrySet()) {
			file = new File(workspaceDir, entry.getKey());
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
				is = classLoader.getResourceAsStream(entry.getValue());
				if (is != null) {
					in = Channels.newChannel(is);
					out = new FileOutputStream(file).getChannel();
					try {
						position = 0;
						while ((count = out.transferFrom(in, position, bufferSize)) > 0) {
							position += count;
						}
						in.close();
						out.close();
					} finally {
						if (in != null) {
							in.close();
						}
						if (out != null) {
							out.close();
						}
					}
				}
			} catch (IOException e) {
				throw new JBIException("Error creating file: " + file.getAbsolutePath());
			}
		}
	}
	
	/**
	 * Returns the configuration properties. 
	 * @param ic an {@link InstallationContext} object
	 * @param xpathExpr an xpath expression, 
	 * use {@code "pd"} as {@link #PROCESS_DEPLOYER} namespace
	 * @see #PROCESS_DEPLOYER  
	 * @return the configuration properties 
	 * @throws JBIException on error
	 */
	protected Map<String, String> getConfiguration(InstallationContext ic, String xpathExpr) throws JBIException {
		Map<String, String> props = new LinkedHashMap<String, String>();
		DocumentFragment doc = ic.getInstallationDescriptorExtension();
		XPath xpath = XPathFactory.newInstance().newXPath();
		NamespaceContext nsCtx = new NamespaceContext();
		nsCtx.addNamespace(PROCESS_DEPLOYER.getPrefix(), PROCESS_DEPLOYER.getNamespaceURI());
		xpath.setNamespaceContext(nsCtx);
		NodeList nodes;
		try {
			int attempt = 0;
			do {
				nodes = (NodeList) xpath.evaluate(xpathExpr, doc, XPathConstants.NODESET);
				if (nodes.getLength() == 0) {
					// probably namespaces is not supported as in sun glassfish:
					++attempt;
					xpathExpr = xpathExpr.replaceAll(PROCESS_DEPLOYER.getPrefix() + ":", "");
				}
			} while (attempt < 2 && nodes.getLength() == 0);
		} catch (XPathExpressionException e) {
			throw new JBIException("Error during evaluating properties: ", e);
		}
		Element propElem;
		for (int i = 0 ; i < nodes.getLength(); ++i) {
			propElem = (Element) nodes.item(i);
			props.put(propElem.getAttribute("name"), propElem.getAttribute("value"));
		}
		return props;
	}

	/* (non-Javadoc)
	 * @see javax.jbi.component.Bootstrap#onUninstall()
	 */
	public void onUninstall() throws JBIException {
		Map<String, String> props = getConfiguration(installationContext, PROCESS_DEPLOYER_PROPERTIES);
		String workDir = props.get(BPELDeployer.WORK_DIR);
		if (workDir != null) {
			File dir = new File(workDir);
			if (dir.exists()) {
				getLogger().info("Delete work directory: " + dir.getAbsolutePath());
				Files.deleteDir(dir);
			}
		}
	}
}
