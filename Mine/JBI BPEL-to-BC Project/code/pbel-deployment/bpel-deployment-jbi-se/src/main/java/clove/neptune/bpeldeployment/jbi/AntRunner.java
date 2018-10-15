/*
 * @(#)AntRunner.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam
 */
package clove.neptune.bpeldeployment.jbi;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tools.ant.Main;

import clove.neptune.bpeldeployment.util.FilteringClassLoader;

/**
 * AntRunner. 
 *
 * @author Yasir Karam
 * @version $Revision$
 */
public class AntRunner extends Main implements Runnable {
	private boolean allowExit;
	private final String[] arguments;
	private final ClassLoader classLoader;
	private final Logger logger = Logger.getLogger(getClass().getName());
	private final String[] antPrefixes = {
		"org.apache.tools",
		AntRunner.class.getName()
	};

	/**
	 * Creates a <code>AntRunner</code> object.
	 * @param args arguments list for {@code Ant}
	 * @param cl class loader for {@code Ant}
	 */
	public AntRunner(String[] args, ClassLoader cl) {
		super();
		arguments = args;
		classLoader = cl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			ClassLoader cl =
				FilteringClassLoader.newInstance(classLoader, antPrefixes);
			Class antMainClass = Class.forName(AntRunner.class.getName(), true, cl);
			String methodName = allowExit ? "main" : "executeNoExit";
			Method main = antMainClass.getMethod(methodName, String[].class);
			main.invoke(null, (Object) arguments);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error invoking Ant: ", e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.apache.tools.ant.Main#exit(int)
	 */
	@Override
	protected void exit(int exitCode) {
		if (allowExit) {
			super.exit(exitCode);
		}
	}

	/**
	 * Returns the <code>allowExit</code> value.
	 * @return the <code>allowExit</code> value.
	 */
	public boolean isAllowExit() {
		return allowExit;
	}

	/**
	 * Sets the <code>allowExit</code> value.
	 * @param allowExit the allowExit to set.
	 */
	public void setAllowExit(boolean allowExit) {
		this.allowExit = allowExit;
	}

	/**
	 * Executes Ant but does not invoke {@code System#exit(int)}.
	 * @param args arguments
	 */
	public static void executeNoExit(String[] args) {
		AntRunner antRunner = new AntRunner(args, AntRunner.class.getClassLoader());
		antRunner.setAllowExit(false);
		antRunner.startAnt(args, null, null);
	}

	/**
	 * Execute Ant as usual.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		AntRunner antRunner = new AntRunner(args, AntRunner.class.getClassLoader());
		antRunner.setAllowExit(true);
		antRunner.startAnt(args, null, null);
	}
}
