package net.sf.provisioner.config;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.log4j.Logger;
import org.jdom.Element;


/**
 * Esta clase representa a un elemento de red y sus datos
 * de acceso. Tiene un nombre que puede ser el numero de IP, 
 * un tipo que sirve para determinar que interfaz se debe 
 * utilizar para conectarse al mismo, usuario y password
 * para aquellos elementos de red que requieran iniciar una
 * sesion, clave de autenticacion para aquellos elementos de 
 * red que utilicen este mecanismo de seguridad, y patrones
 * de exito o fracaso para los elementos de red basados en 
 * protocolo Telnet.
 * 
 *             
 * @version $Revision: 1.1.2.2 $, $Date: 2007/11/13 22:36:06 $
 * @author Gordon Pearson
 */
public class LDAPNetworkElement extends NetworkElement {
		
	/** Logger for this class and subclasses */
	Logger logger = Logger.getLogger(getClass());
	
	private String  host;
	private String  baseDN;
	private String  userDN;
	private String  authType;
	
	public LDAPNetworkElement() {
		super();
	}
	
    /**
     * Creates a NetworkElement with its properties populated using 
     * information in an XML file.
     * 
     * @param configFile an XML configuration file (see asterisk.xml and 
     * intraway.xml for examples of the format.) 
     * @throws FileNotFoundException 
     */
    public LDAPNetworkElement(InputStream xmlConfig) throws FileNotFoundException {
    	super(xmlConfig);
    }

	/**
     * Read standard and custom property values from an XML stream.
     * 
     * @param current an XML element.
     */
	void getParameters(Element current) {
		super.getParameters(current);
				
		for (Element child : filterChildParameters(current)) {
	        String paramName = child.getAttributeValue("name");
	        String value     = child.getAttributeValue("value");
	        if (paramName.equals("host")) {
	        	name = value;
	        	host = value;	        	
	        } else if (paramName.equals("userDN")) {
	        	userDN   = value;
	        } else if (paramName.equals("baseDN")) {
	        	baseDN   = value;
	        } else if (paramName.equals("authType")) {
	        	authType = value;
	        }
	    }   
	}

	public String getHost() {
		return host;
	}

	public String getBaseDN() {
		return baseDN;
	}

	public String getUserDN() {
		return userDN;
	}
	
	public String getAuthType() {
		return authType;
	}
	
	public DirContext findRootContext() {
		Hashtable env = new Hashtable();
        env.put(Context.SECURITY_PRINCIPAL     , this.userDN);
        env.put(Context.SECURITY_CREDENTIALS   , this.password);
        env.put(Context.SECURITY_AUTHENTICATION, this.authType);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        
        String url = "";
        if (!this.host.startsWith("ldap://")) {
        	url+= "ldap://";
        }
        url+= this.host;
        if (!url.endsWith(("/"))) {
        	url+= "/";
        }
        
        if (port != null && port.length() > 0) {      
        	try {
        		int portNum = Integer.parseInt(port);
        		url+= ":" + portNum;
        	} catch (NumberFormatException nfe) {
        		throw nfe;
        	}
        }
        url+= this.baseDN;
        
        logger.trace("finding root context with uri: " + url);
        env.put(Context.PROVIDER_URL           , url);
        
        DirContext ctx;
        try {
           ctx = new InitialDirContext(env);
           return ctx;
        } catch (NamingException e) {
        	// TODO: find a more appropriate exception to throw.
           throw new RuntimeException(e);
        }
	}
}
