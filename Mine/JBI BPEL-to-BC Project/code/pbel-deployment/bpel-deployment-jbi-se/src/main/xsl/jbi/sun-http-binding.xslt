<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
  version="2.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable" 
  xmlns:xsd="http://www.w3.org/2001/XMLSchema"
  xmlns:fn="http://www.w3.org/2005/xpath-functions" 
  >
	<xsl:param name="packageDir"/>
	<xsl:param name="fileName"/>
	<xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes"/>

	<xsl:template match="bpel:partnerLinks">
		<xsl:element name="jbi" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:attribute name="version">
				<xsl:value-of select="'1.0'"/>
			</xsl:attribute>
			<xsl:element name="services" namespace="http://java.sun.com/xml/ns/jbi">
				<xsl:attribute name="binding-component">true</xsl:attribute>
				<xsl:apply-templates/>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template match="bpel:partnerLink">
		<xsl:choose>
			<xsl:when test="@myRole">
				<xsl:element name="consumes" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:call-template name="serviceInfo">
						<xsl:with-param name="roleName" select="@myRole"/>
					</xsl:call-template>
				</xsl:element>
			</xsl:when>
			<xsl:when test="@partnerRole">
				<xsl:element name="provides" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:call-template name="serviceInfo">
						<xsl:with-param name="roleName" select="@partnerRole"/>
					</xsl:call-template>
				</xsl:element>
			</xsl:when>
		</xsl:choose>	
	</xsl:template>
	
	<xsl:template match="text()">
		<!-- do nothing -->
	</xsl:template>
	
	<xsl:template name="serviceInfo">
		<xsl:param name="roleName"/>
		<xsl:variable name="portType" select="wsdl:getPortType($packageDir, resolve-QName(@partnerLinkType, .), $roleName)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:variable name="service" select="wsdl:getService($packageDir, $portType)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:variable name="port" select="wsdl:getPort($packageDir, $portType, $service)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:attribute name="endpoint-name">
			<xsl:value-of select="$port"/>
		</xsl:attribute>
		<xsl:namespace name="ws">
			<xsl:value-of select="namespace-uri-from-QName($portType)"/>
		</xsl:namespace>
		<xsl:attribute name="interface-name">
			<xsl:value-of select="concat('ws:', local-name-from-QName($portType))"/>
		</xsl:attribute>
		<xsl:attribute name="service-name">
			<xsl:value-of select="concat('ws:', local-name-from-QName($service))"/>
		</xsl:attribute>
	</xsl:template>
</xsl:stylesheet>
