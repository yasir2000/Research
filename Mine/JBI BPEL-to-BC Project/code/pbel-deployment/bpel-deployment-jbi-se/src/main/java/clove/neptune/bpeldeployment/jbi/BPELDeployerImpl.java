/*
 * @(#)BPELDeployerImpl.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam 
 */
package clove.neptune.bpeldeployment.jbi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AccessController;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jbi.management.DeploymentException;

import clove.neptune.bpeldeployment.util.Files;
import clove.neptune.bpeldeployment.util.GetClassLoaderPrivilegedAction;
import clove.neptune.bpeldeployment.util.ZipFileFilter;

/**
 * BPEL Deployer Implementation
 *
 * @author Yasir Karam
 * @version $Revision$
 *
 */
public class BPELDeployerImpl implements BPELDeployer {
	/**
	 * Source package file property name.
	 */
	public static final String SOURCE_FILE = "clove.neptune.bpeldeployment.srcPkg";
	/**
	 * Target package directory property name.
	 */
	public static final String TARGET_DIR = "clove.neptune.bpeldeployment.pkgDir";
	/**
	 * Workspace directory property name.
	 */
	public static final String WORKSPACE_DIR = "clove.neptune.bpeldeployment.workspaceDir";
	private static final String PROPERTY_PATTERN = "-D{0}={1}";
	private final String workspace;
	private Properties properties;
	
	private Map<DataHandler, File> sources = new WeakHashMap<DataHandler, File>();

	/**
	 * Creates a {@code BPELDeployerImpl} object.
	 * @param workspace a workspace directory path.
	 */
	public BPELDeployerImpl(String workspace) {
		super();
		this.workspace = workspace;
	}

	public DataHandler deploymentPackageForODE(DataHandler srcPkg) throws DeploymentException {
		return deploymentPackage(srcPkg, "ode-deployment");
	}

	public DataHandler deploymentPackageForSunBPELEngine(DataHandler srcPkg) throws DeploymentException {
		return deploymentPackage(srcPkg, "sun-bpel-deployment");
	}
	
	/**
	 * Creates deployment package.
	 * @param srcPkg the source package
	 * @param target the target engine ({@code target} for {@code Ant})
	 * @return the required deployment package
	 */
	protected DataHandler deploymentPackage(DataHandler srcPkg, String target) throws DeploymentException {
		DataHandler result = null;
		File workspaceDir = new File(workspace);
		if (properties == null) {
			File f = new File(workspaceDir, CONFIG_FILE);
			properties = new Properties();
			try {
				properties.loadFromXML(new FileInputStream(f));
			} catch (Exception e) {
				throw new DeploymentException("Error reading configuration file: " + f.getAbsolutePath(), e);
			}
		}
		String workDirStr = properties.getProperty(WORK_DIR);
		File workDir = new File(workDirStr);
		try {
			File srcFile = File.createTempFile("src_", ".zip", workDir);
			srcPkg.writeTo(new FileOutputStream(srcFile));
			File destDir = File.createTempFile("dest_", "", workDir);
			if (srcFile.exists()) {
				List<String> args = new LinkedList<String>();
				args.add(MessageFormat.format(PROPERTY_PATTERN, WORKSPACE_DIR, workspaceDir.getAbsolutePath()));
				args.add(MessageFormat.format(PROPERTY_PATTERN, WORK_DIR, workDir.getAbsolutePath()));
				args.add(MessageFormat.format(PROPERTY_PATTERN, SOURCE_FILE, srcFile.getAbsolutePath()));
				args.add(MessageFormat.format(PROPERTY_PATTERN, TARGET_DIR, destDir.getAbsolutePath()));
				args.add("-buildfile");
				File buildFile = new File(workspaceDir, "build.xml");
				args.add(buildFile.getAbsolutePath());
				args.add(target);
				destDir.delete();
				ClassLoader classLoader = 
					AccessController.doPrivileged(new GetClassLoaderPrivilegedAction(getClass(), true));
				AntRunner antRunner = 
					new AntRunner((String[]) args.toArray(new String[args.size()]), classLoader);
				Thread antThread = new Thread(antRunner); 
				antThread.start();
				antThread.join();
				srcFile.delete();
				File[] files = destDir.listFiles(new ZipFileFilter());
				if (files != null && files.length > 0) {
					result = new DataHandler(new FileDataSource(files[0]));
					sources.put(result, destDir);
				}
			}
		} catch (Exception e) {
			throw new DeploymentException("Error occured: ", e);
		}
		return result;
	}

	public void release(DataHandler dh) {
		File dir = sources.get(dh);
		if (dir != null) {
			Files.deleteDir(dir);
		}
	}
}
