/*
 * @(#)FilteringClassLoader.java $Revision: 40 $ ($Date: 2009-03-03 14:27:53 +0200 (Tue, 03 Mar 2009) $)
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Filtering Class Loader. 
 * <p>
 * If the class starts with the one of the specified prefix then
 * this class loader does not delegate the class loading to parent class and
 * the load class itself.
 * <p/>
 *
 * @author Yasir Karam
 * @version $Revision: 40 $
 */
public class FilteringClassLoader extends ClassLoader {
	private final String[] prefixes;

	/**
	 * Creates a <code>FilteringClassLoader</code> object.
	 * @param parent the parent class loader
	 * @param prefixes the package or class names prefixes
	 */
	public FilteringClassLoader(final ClassLoader parent, final String[] prefixes) {
		super(parent);
		this.prefixes = prefixes.clone();
	}

    /**
     * Creates a new instance of FilteringClassLoader using by
     * <code>AccessController.doPrivileged</code> method.
	 * @param parent the parent class loader
	 * @param prefixes the package or class names prefixes
     */
    public static FilteringClassLoader newInstance(final ClassLoader parent, final String[] prefixes) {
		// Need a privileged block to create the class loader
    	FilteringClassLoader classLoader;
		classLoader = 
		    AccessController.doPrivileged(new PrivilegedAction<FilteringClassLoader>() {
				public FilteringClassLoader run() {
				    return new FilteringClassLoader(parent, prefixes);
				}
		    });

		return classLoader;
    }

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#loadClass(java.lang.String)
	 */
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		for (String prefix : prefixes) {
			if (name.startsWith(prefix)) {
				// we need to load those classes in this class loader
				// without delegation.
				return findClass(name);
			}
		}
		return super.loadClass(name);
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
        StringBuilder sb = new StringBuilder(name.length() + 6);
        sb.append(name.replace('.','/')).append(".class");

        InputStream is = getResourceAsStream(sb.toString());
        if (is == null) {
            throw new ClassNotFoundException("Class not found" + sb);
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) >= 0) {
            	baos.write(buf,0,len);
            }
            buf = baos.toByteArray();

			definePackageIfNotDefined(name);
			
            return defineClass(name, buf, 0, buf.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
	}

	private void definePackageIfNotDefined(String className) {
        int index = className.lastIndexOf('.');
        if (index > -1) {
			String packageName = className.substring(0, index);
			if (getPackage(packageName) == null) {
				try {
					getParent().loadClass(className);
				} catch (ClassNotFoundException e) {
				}
			}
        }
	}
}
