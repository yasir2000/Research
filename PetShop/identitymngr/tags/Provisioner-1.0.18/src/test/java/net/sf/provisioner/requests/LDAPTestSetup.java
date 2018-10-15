package net.sf.provisioner.requests;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.directory.server.configuration.MutableServerStartupConfiguration;
import org.apache.directory.server.core.configuration.MutablePartitionConfiguration;
import org.apache.directory.server.core.configuration.PartitionConfiguration;

public class LDAPTestSetup {
	
	private MutableServerStartupConfiguration configuration = new MutableServerStartupConfiguration();
	
    /**
     * Initialize the server.
     */
    public void setUp() throws Exception
    {
        // Add partition 'sevenSeas'
        MutablePartitionConfiguration pcfg = new MutablePartitionConfiguration();
        //pcfg.setName( "sevenSeas" );
        pcfg.setId("provisioner");
        pcfg.setSuffix( "dc=provisioner,dc=net" );

        // Create some indices
        Set<Object> indexedAttrs = new HashSet<Object>();
        indexedAttrs.add( "objectClass" );
        indexedAttrs.add( "dc" );
        pcfg.setIndexedAttributes( indexedAttrs );

        // Create a first entry associated to the partition
        Attributes attrs = new BasicAttributes( true );

        // First, the objectClass attribute
        attrs = buildAttrs(
        		"domain",
        		"dc",
        		"provisioner"        		
        );

        // Associate this entry to the partition
        pcfg.setContextEntry( attrs );

        // As we can create more than one partition, we must store
        // each created partition in a Set before initialization
        Set<PartitionConfiguration> pcfgs = new HashSet<PartitionConfiguration>();
        pcfgs.add( pcfg );

        configuration.setPartitionConfigurations(pcfgs);

        // Create a working directory
        File workingDirectory = new File( "server-work" );
        configuration.setWorkingDirectory( workingDirectory );

   }
    
    public DirContext createContext( String partition ) throws NamingException
    {
        // Create a environment container
        Hashtable<Object, Object> env = new Hashtable<Object, Object>( 
        		configuration.toJndiEnvironment() 
        );

        // Create a new context pointing to the partition
        env.put( Context.PROVIDER_URL, partition );
        env.put( Context.SECURITY_PRINCIPAL, "uid=admin,ou=system" );
        env.put( Context.SECURITY_CREDENTIALS, "secret" );
        env.put( Context.SECURITY_AUTHENTICATION, "simple" );
        env.put( Context.INITIAL_CONTEXT_FACTORY, 
                    "org.apache.directory.server.jndi.ServerContextFactory" );

        // Let's open a connection on this partition
        InitialContext initialContext = new InitialContext( env );

        // We should be able to read it
        DirContext appRoot = ( DirContext ) initialContext.lookup( "" );
        
        return appRoot;
    }
        
    static BasicAttributes buildAttrs(String className, Map<String, Object> attrs) {
    	return buildAttrs(
    			new String[] { className }, 
    			attrs
    	);
    }
    
    static BasicAttributes buildAttrs(String className, String attrName, Object attrValue) {
    	Map<String, Object> attrs = new HashMap<String, Object>(1);
    	attrs.put(attrName, attrValue);
    	
    	return buildAttrs(className, attrs);
    }
    
	static BasicAttributes buildAttrs(String[] classNames, Map<String, Object> attrs) {
		BasicAttributes result = new BasicAttributes();
		Attribute       classes = new BasicAttribute("objectClass");
		classes.add("top");
		for (String className : classNames) {
			classes.add(className);
		}
		result.put(classes);
		
		for (String key : attrs.keySet()) {
			attrs.put(key, attrs.get(key));
		}
		return result;
	}
	
    
    public static void main(String[] args) throws Exception {
    	LDAPTestSetup ldap = new LDAPTestSetup();
    	ldap.setUp();
    	DirContext context = ldap.createContext("dc=provisioner,dc=net");
    	    	
    	/*context.createSubcontext(
				"dc=yarbles", 
				buildAttrs("domain", "dc", "yarbles")
		);
    	
        LinkedList list = new LinkedList();
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration results = context.search(
        		"", 
        		"(objectClass=domain)", 
        		controls
        );

        while (results.hasMore()) {
           SearchResult searchResult = (SearchResult) results.next();
           Attributes attributes = searchResult.getAttributes();
           NamingEnumeration ne2 = attributes.getAll();
           while (ne2.hasMore()) {
        	   Object attrName = ne2.next();
        	   System.out.println(attrName);        	   
           }
           //Attribute attr = attributes.get("dc");
           //String cn = (String) attr.get();
           //list.add(cn);
        }    	*/
    }
}