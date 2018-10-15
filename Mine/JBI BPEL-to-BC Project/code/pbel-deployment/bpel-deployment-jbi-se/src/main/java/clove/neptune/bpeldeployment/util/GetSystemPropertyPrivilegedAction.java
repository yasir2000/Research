/*
 * @(#)GetSystemPropertyPrivilegedAction.java $Revision: 41 $ ($Date: 2009-03-13 18:39:27 +0200 (Fri, 13 Mar 2009) $)
 * 
 * Author: Yasir Karam
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
 * Get System Property Privileged Action. 
 *
 * @author Yasir Karam
 * @version $Revision: 41 $
 */
public class GetSystemPropertyPrivilegedAction implements PrivilegedAction<String> {
	private final String propertyName;
	/**
	 * Creates a <code>GetSystemPropertyPrivilegedAction</code> object.
	 */
	public GetSystemPropertyPrivilegedAction(final String propertyName) {
		super();
		this.propertyName = propertyName;
	}

	/* (non-Javadoc)
	 * @see java.security.PrivilegedAction#run()
	 */
	public String run() {
		return System.getProperty(propertyName);
	}
}
