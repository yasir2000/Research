/*
 * @(#)Files.java $Revision: 31 $ ($Date$)
 * 
 * Copyright 2006-2007 Yasir Karam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package clove.neptune.bpeldeployment.util;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;

/**
 * Recursively selects files from the specified directory that meet
 * the specified filter.
 * @see #Files(File, FileFilter)
 *
 * @author Yasir Karam
 * @version $Revision: 31 $
 */
public class Files {
	private final List<File> files;
	private final File dir;
	private final FileFilter filter;
	
	/**
	 * Creates a <code>Files</code> object.
	 * @param dir a directory
	 * @param filter a filter
	 */
	public Files(File dir, FileFilter filter) {
		super();
		files = new LinkedList<File>();
		this.dir = dir;
		this.filter = filter; 
	}

	/**
	 * Returns the list of the files that meet the specified criteria.
	 * @return the list of the files that meet the specified criteria.
	 * @see #Files(File, FileFilter)
	 */
	public List<File> getList() {
		files.clear();
		populateList(dir);
		return files;
	}

	private void populateList(File d) {
		for (File f : d.listFiles()) {
			if (f.isDirectory()) {
				populateList(f);
			} else if (filter.accept(f)) {
				files.add(f);
			}
		}
	}

	/** 
     * Deletes all files and subdirectories under dir.
     * Returns <code>true</code> if all deletions were successful.
     * If a deletion fails, the method stops attempting to delete 
     * and returns <code>false</code>.
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            boolean success; 
            for (File f: dir.listFiles()) {
                success = 
                	deleteDir(f);
                if (!success) {
                    return false;
                }
            }
        }
    
        // The directory is now empty or this is a file so delete it
        return dir.delete();
    }
}
