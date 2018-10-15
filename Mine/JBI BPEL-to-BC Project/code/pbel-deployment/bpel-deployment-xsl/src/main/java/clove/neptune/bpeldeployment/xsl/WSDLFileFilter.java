/*
 * @(#)WSDLFileFilter.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam 
 */
package clove.neptune.bpeldeployment.xsl;

import java.io.File;
import java.io.FileFilter;

/**
 * WSDL File Filter: accepts only file names with ".wsdl" extention.
 *
 * @author Yasir Karam
 * @version $Revision$
 *
 */
public class WSDLFileFilter implements FileFilter {
	/* (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File pathname) {
		return pathname.isFile() && pathname.getName().endsWith(".wsdl");
	}
}
