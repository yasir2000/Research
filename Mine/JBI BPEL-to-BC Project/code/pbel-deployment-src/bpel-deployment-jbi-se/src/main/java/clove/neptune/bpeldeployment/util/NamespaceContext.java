/*
 * @(#)NamespaceContext.java $Revision: 23 $ ($Date: 2007-02-09 19:24:21 +0200$)
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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * NamespaceContext implementation.
 *
 * @author Yasir Karam
 * @version $Revision: 23 $ 
 */
public class NamespaceContext 
	implements javax.xml.namespace.NamespaceContext, Serializable {
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -411773561539276695L;
	private Map<String, String> prefixToNamespaceURI;
	private Map<String, Set<String>> namespaceURIToPrefix;

	/**
	 * Creates a <code>NamespaceContext</code> object.
	 */
	public NamespaceContext() {
		super();
		prefixToNamespaceURI = new HashMap<String, String>();
		namespaceURIToPrefix = new HashMap<String, Set<String>>();
	}

	/**
	 * Creates a <code>NamespaceContext</code> object.
	 * @param namespaces the prefix - namespace map
	 */
	public NamespaceContext(Map<String, String> namespaces) {
		super();
		if (namespaces == null) {
			prefixToNamespaceURI = new HashMap<String, String>();
			namespaceURIToPrefix = new HashMap<String, Set<String>>();
		} else {
			prefixToNamespaceURI = new HashMap<String, String>(namespaces.size());
			namespaceURIToPrefix = new HashMap<String, Set<String>>(namespaces.size());
			Iterator<Map.Entry<String, String>> it = namespaces.entrySet().iterator();
			Map.Entry<String, String> entry; 
			while (it.hasNext()) {
				entry = it.next();
				addNamespace(entry.getKey(), entry.getValue());
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getNamespaceURI(java.lang.String)
	 */
	public String getNamespaceURI(String prefix) {
		return prefixToNamespaceURI.get(prefix);
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getPrefix(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String getPrefix(String namespaceURI) {
		Iterator it = getPrefixes(namespaceURI);
		return it != null ? (String) it.next() : null;
	}

	/* (non-Javadoc)
	 * @see javax.xml.namespace.NamespaceContext#getPrefixes(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Iterator getPrefixes(String namespaceURI) {
		Set<String> prefixes = namespaceURIToPrefix.get(namespaceURI);
		Iterator it = null;
		if (prefixes != null) {
			it = prefixes.iterator();
		}
		return it;
	}

	/**
	 * Adds namespace to context.
	 * @param prefix a prefix
	 * @param namespaceURI a namespace URI
	 */
	public void addNamespace(String prefix, String namespaceURI) {
		prefixToNamespaceURI.put(prefix, namespaceURI);
		Set<String> prefixes;
		if (namespaceURIToPrefix.containsKey(namespaceURI)) {
			prefixes = namespaceURIToPrefix.get(namespaceURI);
		} else {
			prefixes = new TreeSet<String>();
			namespaceURIToPrefix.put(namespaceURI, prefixes);
		}
		prefixes.add(prefix);
	}
}
