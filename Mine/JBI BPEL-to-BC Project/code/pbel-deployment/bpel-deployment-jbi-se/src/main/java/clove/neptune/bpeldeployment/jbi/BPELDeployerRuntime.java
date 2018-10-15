/*
 * @(#)BPELDeployerRuntime.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam 
 */
package clove.neptune.bpeldeployment.jbi;

import javax.jbi.component.Component;
import javax.jbi.component.ComponentContext;
import javax.jbi.component.ComponentLifeCycle;
import javax.jbi.component.ServiceUnitManager;
import javax.jbi.messaging.MessageExchange;
import javax.jbi.servicedesc.ServiceEndpoint;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;

/**
 * BPEL Deployer Runtime
 *
 * @author Yasir Karam
 * @version $Revision$
 */
public class BPELDeployerRuntime implements Component {
    /** Component LifeCycle implementation */
    private ComponentLifeCycle lifeCycle;
    /** ServiceUnitManager implementation  */
    private ServiceUnitManager suManager;

    /**
     * Returns the {@link javax.jbi.component.ComponentContext}. 
     * @return component context.
     */
    public ComponentContext getComponentContext() {
        ComponentContext ctx = null;
        if (lifeCycle != null && lifeCycle instanceof BPELDeployerLifeCycle) {
            ctx = ((BPELDeployerLifeCycle) lifeCycle).getComponentContext();
        }
        return ctx;
    }

    /**
     * Get the life cycle control interface for this component.
     *
     * @return the life cycle control interface for this component
	 * @see javax.jbi.component.Component#getLifeCycle()
	 */
	public ComponentLifeCycle getLifeCycle() {
		if (lifeCycle == null) {
			lifeCycle = new BPELDeployerLifeCycle();
		}
		return lifeCycle;
	}

    /**
     * Retrieves a DOM representation containing metadata which describes the
     * service provided by this component, through the given endpoint.
     *
     * Default implementation does not support service description.
     *
	 * @see javax.jbi.component.Component#getServiceDescription(javax.jbi.servicedesc.ServiceEndpoint)
	 */
	public Document getServiceDescription(ServiceEndpoint se) {
		return null;
	}

    /**
     * Get the Service Unit manager for this component.
     *
     * @return the {@link ServiceUnitManager} for this component, or {@code null} if there is none.
	 * @see javax.jbi.component.Component#getServiceUnitManager()
	 */
	public ServiceUnitManager getServiceUnitManager() {
		if (suManager == null) {
			suManager = new ServiceUnitManagerImpl(this);
		}
		return suManager;
	}

    /**
     * This method is called by JBI to check if this component, in the role of
     * provider of the service indicated by the given exchange, can actually
     * perform the operation desired.
     *
     * Default implementation has no policy and allows all exchanges with consumer.
     *
	 * @see javax.jbi.component.Component#isExchangeWithConsumerOkay(javax.jbi.servicedesc.ServiceEndpoint, javax.jbi.messaging.MessageExchange)
	 */
	public boolean isExchangeWithConsumerOkay(ServiceEndpoint se, MessageExchange me) {
		return true;
	}

    /**
     * This method is called by JBI to check if this component, in the role of
     * consumer of the service indicated by the given exchange, can actually
     * interact with the provider properly. The provider is described by the
     * given endpoint and the service description supplied by that endpoint.
     *
     * Default implementation has no policy and allows all exchanges with provider.
     *
	 * @see javax.jbi.component.Component#isExchangeWithProviderOkay(javax.jbi.servicedesc.ServiceEndpoint, javax.jbi.messaging.MessageExchange)
	 */
	public boolean isExchangeWithProviderOkay(ServiceEndpoint se, MessageExchange me) {
		return true;
	}

    /**
     * Resolves the given endpoint reference.
     *
     * Default implementation does not have any XML dialect. So can not resolve the
     * endpoint from the document fragment.
     *
	 * @see javax.jbi.component.Component#resolveEndpointReference(org.w3c.dom.DocumentFragment)
	 */
	public ServiceEndpoint resolveEndpointReference(DocumentFragment df) {
		return null;
	}
}
