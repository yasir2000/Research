/*
 * @(#)DeploymentException.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam 
 */
package clove.neptune.bpeldeployment.jbi;

/**
 * Deployment Exception
 *
 * @author Yasir Karam
 * @version $Revision$
 *
 */
public class DeploymentException extends Exception {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -5103248095200301293L;

	/**
	 * Creates a {@code DeploymentException} object.
     * @param message the detail message. 
     * The detail message is saved for later retrieval by the {@link #getMessage()} method.
	 */
	public DeploymentException(String message) {
		super(message);
	}

	/**
	 * Creates a {@code DeploymentException} object.
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
	 */
	public DeploymentException(Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a {@code DeploymentException} object.
     * @param message the detail message. 
     * The detail message is saved for later retrieval by the {@link #getMessage()} method.
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
	 */
	public DeploymentException(String message, Throwable cause) {
		super(message, cause);
	}
}
