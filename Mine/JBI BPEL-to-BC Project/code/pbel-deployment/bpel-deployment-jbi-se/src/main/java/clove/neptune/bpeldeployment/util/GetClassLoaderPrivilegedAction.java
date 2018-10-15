/*
 * @(#)GetClassLoaderPrivilegedAction.java $Revision: 40 $ ($Date: 2009-03-03 14:27:53 +0200 (Tue, 03 Mar 2009) $)
 * 
 * Copyright 2006-2009 Yasir Karam
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

import java.security.PrivilegedAction;

/**
 * Get {@link ClassLoader} privileged action.
 * Returns {@link ClassLoader} object for the specified class.
 * 
 * <ol>
 * This action does following:
 * <li> Invokes {@link Thread#getContextClassLoader()} in the current thread 
 * if <code>prefer context class loader</code> option is specified. </li>
 * <li> If the result is not <code>null</code> then returns it. </li>
 * <li> Else returns the result of {@link Class#getClassLoader()} in the invoker class. </li>
 * </ol>
 *
 * @author Yasir Karam
 * @version $Revision: 40 $
 */
public class GetClassLoaderPrivilegedAction implements PrivilegedAction<ClassLoader> {
	private final Class<?> clazz;
	private final boolean preferContextClassLoader;
	/**
	 * Creates a <code>GetClassLoaderPrivilegedAction</code> object.
	 * @param clazz a class that is loaded by the required class loader.
	 * @param preferContextClassLoader if <code>true</code> then try to get context class loader
	 */
	public GetClassLoaderPrivilegedAction(final Class<?> clazz, final boolean preferContextClassLoader) {
		super();
		this.clazz = clazz;
		this.preferContextClassLoader = preferContextClassLoader; 
	}

	/* (non-Javadoc)
	 * @see java.security.PrivilegedAction#run()
	 */
	public ClassLoader run() {
        ClassLoader classLoader = 
        	preferContextClassLoader ? Thread.currentThread().getContextClassLoader() : null;
        if (classLoader == null) {
        	classLoader = clazz.getClassLoader();
        }
		return classLoader;
	}
}
