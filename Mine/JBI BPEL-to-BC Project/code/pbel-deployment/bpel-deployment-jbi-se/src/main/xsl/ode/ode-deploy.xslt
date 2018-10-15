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
		<xsl:element name="dd:deploy" namespace="http://www.apache.org/ode/schemas/dd/2007/03">
			<xsl:element name="dd:process" namespace="http://www.apache.org/ode/schemas/dd/2007/03">
				<xsl:namespace name="bp">
					<xsl:value-of select="/bpel:process/@targetNamespace"/>
				</xsl:namespace>
				<xsl:attribute name="name">
					<xsl:value-of select="concat('bp:', /bpel:process/@name)"/>
				</xsl:attribute>
				<xsl:element name="dd:active" namespace="http://www.apache.org/ode/schemas/dd/2007/03">
					<xsl:text>true</xsl:text>
				</xsl:element>
				<xsl:apply-templates/>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template match="bpel:partnerLink">
		<xsl:choose>
			<xsl:when test="@myRole">
				<xsl:element name="dd:provide" namespace="http://www.apache.org/ode/schemas/dd/2007/03">
					<xsl:call-template name="serviceInfo">
						<xsl:with-param name="roleName" select="@myRole"/>
					</xsl:call-template>
				</xsl:element>
			</xsl:when>
			<xsl:when test="@partnerRole">
				<xsl:element name="dd:invoke" namespace="http://www.apache.org/ode/schemas/dd/2007/03">
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
		<xsl:attribute name="partnerLink">
			<xsl:value-of select="@name"/>
		</xsl:attribute>
		<xsl:element name="dd:service" namespace="http://www.apache.org/ode/schemas/dd/2007/03">
			<xsl:namespace name="ws">
				<xsl:value-of select="namespace-uri-from-QName($portType)"/>
			</xsl:namespace>
			<xsl:attribute name="name">
				<xsl:value-of select="concat('ws:', local-name-from-QName($service))"/>
			</xsl:attribute>
			<xsl:attribute name="port">
				<xsl:value-of select="$port"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
