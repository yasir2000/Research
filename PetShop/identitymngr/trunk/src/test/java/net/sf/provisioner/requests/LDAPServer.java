package net.sf.provisioner.requests;

import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import net.sf.provisioner.utils.LDAPUtils;

import org.apache.directory.server.core.configuration.MutablePartitionConfiguration;
import org.apache.directory.server.unit.AbstractServerTest;

public class LDAPServer extends AbstractServerTest {

	/**
	 * Initialize the server.
	 */
	public void setUp() throws Exception {
		// Add partition 'sevenSeas'
        MutablePartitionConfiguration pcfg = new MutablePartitionConfiguration();
        pcfg.setId( "provisioner" );
        pcfg.setSuffix( "dc=provisioner,dc=net" );
        
        // Create a first entry associated to the partition
        Attributes attrs = LDAPUtils.buildAttrs("domain", "dc", "net");        
        
        // Associate this entry to the partition
        pcfg.setContextEntry( attrs );

        // As we can create more than one partition, we must store
        // each created partition in a Set before initialization
        Set<MutablePartitionConfiguration> pcfgs = new HashSet<MutablePartitionConfiguration>();
        pcfgs.add( pcfg );

        configuration.setPartitionConfigurations( pcfgs );

        super.setUp();
	}

	/**
	 * Empty test just to avoid a warning to be thrown when launching the test
	 */
	public void testEmpty() throws Exception {
		DirContext c2 = rootDSE.createSubcontext(
				"dc=yarbles,dc=provisioner,dc=net", 
				LDAPUtils.buildAttrs("domain", "dc", "yarbles")
		);
		
		rootDSE.createSubcontext(
				"dc=yarb,dc=yarbles,dc=provisioner,dc=net", 
				LDAPUtils.buildAttrs("domain", "dc", "yarb")
		);
		
		SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration results = c2.search(
        		"", 
        		"(objectClass=domain)", 
        		controls
        );

        while (results.hasMore()) {
           SearchResult searchResult = (SearchResult) results.next();
           Attributes attributes = searchResult.getAttributes();
           NamingEnumeration ne2 = attributes.getAll();
           while (ne2.hasMore()) {
        	   Attribute attr = (Attribute) ne2.next();
        	   System.out.print(attr);
           }
           //Attribute attr = attributes.get("dc");
           //String cn = (String) attr.get();
           //list.add(cn);
        }    			
	}

	/**
	 * Shutdown the server.
	 */
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	public static void main(String[] args) throws Exception {
		LDAPServer ldap = null;
		try {
			ldap = new LDAPServer();		
			ldap.setUp();
			ldap.testEmpty();		
		} finally {
			ldap.tearDown();
		}
	}
}
