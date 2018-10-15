/*
 * @(#)BPELDeployer.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.jbi;

import javax.activation.DataHandler;
import javax.jbi.management.DeploymentException;

/**
 * BPEL Deployer
 *
 * @author Yasir Karam
 * @version $Revision$
 */
public interface BPELDeployer {
	/**
	 * Working DIrectory property name.	
	 */
    public static final String WORK_DIR = "clove.neptune.bpeldeployment.workDir"; 
    
    public static final String CONFIG_FILE = "configuration.xml";

    /**
	 * Creates deployment package for sun-bpel-engine JBI service.
	 * @param srcPkg source package
	 * @return created deployment package
	 * @throws DeploymentException on error
	 */
    DataHandler deploymentPackageForSunBPELEngine(DataHandler srcPkg) throws DeploymentException ;

	/**
	 * Creates deployment package for sun-bpel-engine JBI service.
	 * @param srcPkg source package
	 * @return created deployment package
	 * @throws DeploymentException on error
	 */
    DataHandler deploymentPackageForODE(DataHandler srcPkg) throws DeploymentException ;
	
	/**
	 * Releases result source.
	 * @param stream source created by this service.
	 */
	void release(DataHandler stream);
}
