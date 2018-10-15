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
			<xsl:element name="service-assembly" namespace="http://java.sun.com/xml/ns/jbi">
				<xsl:variable name="appName" select="concat(/bpel:process/@name, 'Process')"/>
				<xsl:element name="identification" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:element name="name" namespace="http://java.sun.com/xml/ns/jbi">
						<xsl:value-of select="$appName"/>
					</xsl:element>
					<xsl:element name="description" namespace="http://java.sun.com/xml/ns/jbi">
						<xsl:value-of select="concat('Represents the Service Assembly of ', $appName)"/>
					</xsl:element>
				</xsl:element>
				<xsl:call-template name="service-unit">
					<xsl:with-param name="appName" select="$appName"/>
					<xsl:with-param name="componentName">sun-bpel-engine</xsl:with-param>
				</xsl:call-template>
				<xsl:call-template name="service-unit">
					<xsl:with-param name="appName" select="$appName"/>
					<xsl:with-param name="componentName">sun-http-binding</xsl:with-param>
				</xsl:call-template>
				<xsl:element name="connections" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:apply-templates/>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template match="bpel:partnerLink">
		<xsl:element name="connection" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:choose>
				<xsl:when test="@myRole">
					<xsl:call-template name="process-provider"/>
				</xsl:when>
				<xsl:when test="@partnerRole">
					<xsl:call-template name="process-consumer"/>
				</xsl:when>
			</xsl:choose>	
		</xsl:element>
	</xsl:template>
	
	<xsl:template match="text()">
		<!-- do nothing -->
	</xsl:template>
	
	<xsl:template name="service-unit">
		<xsl:param name="appName"/>
		<xsl:param name="componentName"/>
		<xsl:element name="service-unit" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:element name="identification" namespace="http://java.sun.com/xml/ns/jbi">
				<xsl:element name="name" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:value-of select="concat($appName, '-', $componentName)"/>
				</xsl:element>
				<xsl:element name="description" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:text>Represents this Service Unit</xsl:text>
				</xsl:element>
			</xsl:element>
			<xsl:element name="target" namespace="http://java.sun.com/xml/ns/jbi">
				<xsl:element name="artifacts-zip" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:value-of select="concat($componentName, '.jar')"/>
				</xsl:element>
				<xsl:element name="component-name" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:value-of select="$componentName"/>
				</xsl:element>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	
	<xsl:template name="process-provider">
		<xsl:variable name="portType" select="wsdl:getPortType($packageDir, resolve-QName(@partnerLinkType, .), @myRole)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:variable name="service" select="wsdl:getService($packageDir, $portType)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:variable name="port" select="wsdl:getPort($packageDir, $portType, $service)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:element name="consumer" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:attribute name="endpoint-name">
				<xsl:value-of select="$port"/>
			</xsl:attribute>
			<xsl:namespace name="ws">
				<xsl:value-of select="namespace-uri-from-QName($portType)"/>
			</xsl:namespace>
			<xsl:attribute name="service-name">
				<xsl:value-of select="concat('ws:', local-name-from-QName($service))"/>
			</xsl:attribute>
		</xsl:element>
		<xsl:element name="provider" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:attribute name="endpoint-name">
				<xsl:value-of select="concat(@myRole, '_myRole')"/>
			</xsl:attribute>
			<xsl:namespace name="bp">
				<xsl:value-of select="/bpel:process/@targetNamespace"/>
			</xsl:namespace>
			<xsl:attribute name="service-name">
				<xsl:value-of select="concat('bp:', @name)"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>

	<xsl:template name="process-consumer">
		<xsl:variable name="portType" select="wsdl:getPortType($packageDir, resolve-QName(@partnerLinkType, .), @partnerRole)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:variable name="service" select="wsdl:getService($packageDir, $portType)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:variable name="port" select="wsdl:getPort($packageDir, $portType, $service)" xmlns:wsdl="clove.neptune.bpeldeployment.xsl.WSDLHelper"/>
		<xsl:element name="consumer" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:attribute name="endpoint-name">
				<xsl:value-of select="concat(@partnerRole, '_partnerRole')"/>
			</xsl:attribute>
			<xsl:namespace name="bp">
				<xsl:value-of select="/bpel:process/@targetNamespace"/>
			</xsl:namespace>
			<xsl:attribute name="service-name">
				<xsl:value-of select="concat('bp:', @name)"/>
			</xsl:attribute>
		</xsl:element>
		<xsl:element name="provider" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:attribute name="endpoint-name">
				<xsl:value-of select="$port"/>
			</xsl:attribute>
			<xsl:namespace name="ws">
				<xsl:value-of select="namespace-uri-from-QName($portType)"/>
			</xsl:namespace>
			<xsl:attribute name="service-name">
				<xsl:value-of select="concat('ws:', local-name-from-QName($service))"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
