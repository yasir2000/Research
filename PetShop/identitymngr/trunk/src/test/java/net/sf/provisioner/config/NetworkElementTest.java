package net.sf.provisioner.config;

import static org.junit.Assert.assertEquals;

import java.io.File;

import net.sf.provisioner.config.LDAPNetworkElement;
import net.sf.provisioner.utils.PathHelper;

import org.junit.Test;


public class NetworkElementTest {

	@Test
	public void configRead() throws Exception {
		LDAPNetworkElement ne = new LDAPNetworkElement(
				PathHelper.pathToStream("ldap_ne_name.xml")
		);
		assertEquals(ne.type, "ldap_network_element_name");
		assertEquals(ne.name     , "localhost");
		assertEquals(ne.getHost(), "localhost");
		assertEquals(ne.port ,"");
		assertEquals(ne.password, "secret");
		assertEquals(ne.getBaseDN(), "dc=provisioner,dc=net");
		assertEquals(ne.getUserDN(), "uid=root,dc=provisioner,dc=net");
		
		// TODO: test response values. 
	}
	
	
}
