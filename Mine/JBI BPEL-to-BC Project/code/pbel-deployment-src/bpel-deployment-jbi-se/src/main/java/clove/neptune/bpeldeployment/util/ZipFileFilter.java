/*
 * @(#)ZipFileFilter.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam 
 */
package clove.neptune.bpeldeployment.util;

import java.io.File;
import java.io.FileFilter;

/**
 * Zip File Filter
 *
 * @author Yasir Karam
 * @version $Revision$
 *
 */
public class ZipFileFilter implements FileFilter {
	public boolean accept(File f) {
		return f.isFile() && f.getName().endsWith(".zip");
	}
}
