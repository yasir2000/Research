/*
 * @(#)WSDLHelperTest.java $Revision$ ($Date$)
 * 
 * Author: Yasir Karam
 *
 * Copyright (c) 2010 Yasir Karam 
 */
package clove.neptune.bpeldeployment.xsl;

import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clove.neptune.bpeldeployment.xsl.WSDLHelper;

/**
 * WSDLHelperTest
 *
 * @author Yasir Karam
 * @version $Revision$
 *
 */
public class WSDLHelperTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link clove.neptune.bpeldeployment.xsl.xsl.WSDLHelper#getPortType(String, QName, String)}.
	 */
	@Test
	public void testGetPortType() {
		QName ptQName = 
			WSDLHelper.getPortType(
				"build", 
				new QName("http://example.org/bpel/hello.wsdl", "HelloPLT"), "HelloPortTypeRole");
		QName expectedQName = new QName("http://example.org/bpel/hello.wsdl", "HelloPortType");
		Assert.assertEquals(expectedQName, ptQName);
	}

	/**
	 * Test method for {@link clove.neptune.bpeldeployment.xsl.xsl.WSDLHelper#getService(String, QName)}.
	 */
	@Test
	public void testGetService() {
		QName serviceQName = 
			WSDLHelper.getService(
				"build", 
				new QName("http://example.org/bpel/hello.wsdl", "HelloPortType"));
		QName expectedQName = new QName("http://example.org/bpel/hello.wsdl", "HelloService");
		Assert.assertEquals(expectedQName, serviceQName);
	}

	/**
	 * Test method for {@link clove.neptune.bpeldeployment.xsl.xsl.WSDLHelper#getPort(String, QName, QName)}.
	 */
	@Test
	public void testGetPort() {
		String portName = 
			WSDLHelper.getPort(
				"build", 
				new QName("http://example.org/bpel/hello.wsdl", "HelloPortType"),
				new QName("http://example.org/bpel/hello.wsdl", "HelloService")
				);
		String expectedName = "HelloPort";
		Assert.assertEquals(expectedName, portName);
	}
}
