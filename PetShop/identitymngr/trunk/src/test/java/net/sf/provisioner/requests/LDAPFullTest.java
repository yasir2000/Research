package net.sf.provisioner.requests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.naming.directory.DirContext;

import net.sf.provisioner.config.ConfigRequest;
import net.sf.provisioner.config.Configuration;
import net.sf.provisioner.config.LDAPNetworkElement;
import net.sf.provisioner.core.Operation;
import net.sf.provisioner.requests.LDAPCreateRequest;
import net.sf.provisioner.requests.LDAPRequest;
import net.sf.provisioner.requests.Request;
import net.sf.provisioner.requests.RequestFactory;
import net.sf.provisioner.responses.Response;
import net.sf.provisioner.utils.PathHelper;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;



public class LDAPFullTest {		 
	
	private static Logger        
	
	logger = Logger.getLogger(LDAPFullTest.class); 
		
	private static Configuration config = null;
	
	@BeforeClass
	public static void setUp() throws Exception {		
		
		String configPath = "src/config/";
		
		PropertyConfigurator.configure(
				PathHelper.pathToURL(configPath + "log4j.properties")				
		);
			
		config = new Configuration(configPath);
		
	}
	
	public static Operation createAddOperation() throws Exception {
		Operation op = new Operation();
		op.setId("LDAPC");
		op.setType("ldapc");
		op.setParameters(
				"<operation>" +
				"<parameter name=\"ldap_operation_key\" value=\"ldap_op_route\" />" +
				"<parameter name=\"dn\"          value=\"dc=testAttribute\" />" +
				"<parameter name=\"objectClass\" value=\"top\"              />" +
				"<parameter name=\"objectClass\" value=\"dcObject\"         />" +
				"<parameter name=\"objectClass\" value=\"organization\"     />" +
				"<parameter name=\"dc\"          value=\"testAttribute\"    />" + 
				"<parameter name=\"o\"           value=\"testAttribute\"    />" +
				"</operation>"
		);
		return op;
	}
	
	public static Operation prepareAddOperation() throws Exception {
		Operation op = createAddOperation();
		op.applyRules(config.rules.elements(), config.services);		
		return op;
	}
	
	public static Operation createDeleteOperation() throws Exception {
		Operation op = new Operation();
		op.setId("LDAPD");
		op.setType("ldapd");
		op.setParameters(
				"<operation>" +
				"<parameter name=\"ldap_operation_key\" value=\"ldap_op_route\" />" +
				"<parameter name=\"dn\"          value=\"dc=testAttribute\" />" +
				"</operation>"
		);
		return op;
	}
	
	public static Operation prepareDeleteOperation() throws Exception {
		Operation op = createDeleteOperation();
		op.applyRules(config.rules.elements(), config.services);		
		return op;
	}
	
	/* Ensures a test operation creates a correct ConfigRequest/NetworkAdapter. */
	@Test	
	public void testOperationSetup() throws Exception {
		/* TODO: Too nebulous.  This should be split into finer-grained tests, 
		 * with less of a reliance on reading XML configs. */
		
		Operation op = prepareAddOperation();

		assertTrue(op.requests.size() == 1);
		
		ConfigRequest configRequest = (ConfigRequest) op.requests.elements().nextElement();
				
		assertTrue(configRequest.service.ne instanceof LDAPNetworkElement);
		LDAPNetworkElement ne = (LDAPNetworkElement) configRequest.service.ne;
			
		assertEquals("Create"         , configRequest.operationType, "Create");
		assertEquals("LDAP_service"   , configRequest.service.name , "LDAP_service");
		assertEquals("localhost"      , ne.getHost());
		assertEquals("dc=provisioner,dc=net", ne.getBaseDN());
		assertEquals("ldap_network_element_name", ne.type);			
	}
	
	@Ignore
	public void testLDAPAddDeleteRequest() throws Exception {
		ConfigRequest  configRequest = (ConfigRequest) prepareAddOperation()
				.requests.elements().nextElement()
		;
		Request        request       = RequestFactory.createRequest(				
				createAddOperation(),
				configRequest
		);
		assertTrue(request instanceof LDAPRequest);
		assertTrue(request instanceof LDAPCreateRequest);
		
		LDAPCreateRequest createRequest = (LDAPCreateRequest) request;
		assertEquals("dc=testAttribute", createRequest.getDistinguishedName());
		assertEquals("testAttribute", createRequest.getParam("dc"));
		
		assertTrue(createRequest.ne instanceof LDAPNetworkElement);
		
		DirContext context = ((LDAPNetworkElement) createRequest.ne).findRootContext();
		
		Response response = createRequest.sendRequest();
		logger.trace("response.errorStr: " + response.errorStr);
				
		assert response.successfull : "Response indicates failure: " + response.errorStr;
		
		Operation deleteOp = prepareDeleteOperation();		
		Request deleteRequest = RequestFactory.createRequest(
				deleteOp,
				(ConfigRequest) deleteOp.requests.elements().nextElement()
		);
		Response deleteResponse = deleteRequest.sendRequest();
		
		assert deleteResponse.successfull : "Delete response indicates failure: " + deleteResponse.errorStr;
	}
}
