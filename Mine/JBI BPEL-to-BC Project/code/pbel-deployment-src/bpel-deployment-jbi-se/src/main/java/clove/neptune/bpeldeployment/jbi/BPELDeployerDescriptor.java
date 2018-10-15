/*
 * @(#)BPELDeployer.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.jbi;

import javax.jbi.messaging.MessageExchange.Role;
import javax.xml.namespace.QName;

/**
 * Provides the details of the wsdl model for the BPELDeployer service that
 * is provided by the BPELDeployer service engine.
 *
 * @author Yasir Karam
 * @version $Revision$
 */
public class BPELDeployerDescriptor {
    /** 
     * Service description info 
     */
    public final static String SERVICE_NS = "http://clove/neptune/bpeldeployment/service";
    public final static String INTERFACE_NAME = "BPELDeployer";
    public final static String OPERATION_NAME = "echo";
    public final static String OPERATION_INPUT_NAME = "echo";
    public final static String OPERATION_OUTPUT_NAME = "echo";
    public final static String SERVICE_NAME = "BPELDeployerService";
    public final static String ENDPOINT_NAME_PREFIX = "BPELDeployerEP";
    /** 
     * Specific to each concrete wsdl 
     */
    private String endpointName;
    /** 
     * Consumer or Provider role
     */
    private Role role;

    /**
     * Creates a {@code BPELDeployerDescriptor} object.
     * @param role a role
     * @param endpointName an endpoint name
     */
    public BPELDeployerDescriptor(Role role, String endpointName) {
    	super();
        this.role = role;
        this.endpointName = endpointName;
    }
    
    /** 
     * Generates the jbi internal endpoint name 
     */
    public static String generateJBIInternalEndpointName(String endpointNamePrefix) {
        return endpointNamePrefix + "_JBIPort";
    }
    
    /**
     * Returns an interface qualified name.
     * @return an interface qualified name.
     */
    public QName getInterfaceQName() {
        QName interfaceQName =
            new QName(BPELDeployerDescriptor.SERVICE_NS, BPELDeployerDescriptor.INTERFACE_NAME);
        return interfaceQName;
    }

    /** @return operation QName */
    public QName getOperationQName() {
        QName opQName =
            new QName(BPELDeployerDescriptor.SERVICE_NS, BPELDeployerDescriptor.OPERATION_NAME);
        return opQName;
    }

    /**
     * Returns a service qualified name
     * @return a service qualified name
     */
    public QName getServiceQName() {
        QName serviceQName =
            new QName(BPELDeployerDescriptor.SERVICE_NS, BPELDeployerDescriptor.SERVICE_NAME);
        return serviceQName;
    }
    
    /**
     * Returns endpoint name.
     * @return endpoint name.
     */
    public String getEndpointName() {
        return this.endpointName;
    }
    
    /**
     * Returns provider or consumer role.
     * @return provider or consumer role 
     */
    public Role getRole() {
        return this.role;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("Service: ").append(getServiceQName()).append('\n');
    	sb.append("Endpoint: ").append(getEndpointName()).append('\n');
    	sb.append("Interface: ").append(getInterfaceQName());
    	return sb.toString();
    }
}
