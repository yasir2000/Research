<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet
  version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
  >
	<xsl:param name="fileName"></xsl:param>
	<xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes"/>

	<xsl:template match="bpel:partnerLinks">
		<xsl:element name="jbi" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:attribute name="xmlns:bp" xmlns="http://www.w3.org/XML/1998/namespace">false</xsl:attribute>
			<xsl:element name="services">
				<xsl:attribute name="binding-component">false</xsl:attribute>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template match="bpel:partnerLink">
		<xsl:choose>
			<xsl:when test="@myRole">
				<xsl:element name="provides" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:attribute name="interface-name">
						<xsl:value-of select="@partnerLinkType"/>
					</xsl:attribute>
					<xsl:attribute name="service-name">
						<xsl:value-of select="@name"/>
					</xsl:attribute>
					<xsl:attribute name="endpoint-name">
						<xsl:value-of select="concat(@myRole, '_myRole')"/>
					</xsl:attribute>
					<xsl:element name="display-name" namespace="http://www.sun.com/jbi/descriptor/service-unit">
						<xsl:value-of select="@name"/>
					</xsl:element>
					<xsl:element name="process-name" namespace="http://www.sun.com/jbi/descriptor/service-unit">
						<xsl:value-of select="@name"/>
					</xsl:element>
					<xsl:element name="file-path" namespace="http://www.sun.com/jbi/descriptor/service-unit">
						<xsl:value-of select="$fileName"/>
					</xsl:element>
				</xsl:element>
			</xsl:when>
			<xsl:when test="@partnerRole">
				<xsl:element name="consumes" namespace="http://java.sun.com/xml/ns/jbi">
					<xsl:attribute name="interface-name">
						<xsl:value-of select="@partnerLinkType"/>
					</xsl:attribute>
					<xsl:attribute name="endpoint-name">
						<xsl:value-of select="concat(@partnerRole, '_partnerRole')"/>
					</xsl:attribute>
					<xsl:element name="display-name" namespace="http://www.sun.com/jbi/descriptor/service-unit">
						<xsl:value-of select="@name"/>
					</xsl:element>
					<xsl:element name="process-name" namespace="http://www.sun.com/jbi/descriptor/service-unit">
						<xsl:value-of select="@name"/>
					</xsl:element>
					<xsl:element name="file-path" namespace="http://www.sun.com/jbi/descriptor/service-unit">
						<xsl:value-of select="$fileName"/>
					</xsl:element>
				</xsl:element>
			</xsl:when>
		</xsl:choose>	
		<xsl:element name="jbi" namespace="http://java.sun.com/xml/ns/jbi">
			<xsl:attribute name="version">
				<xsl:value-of select="'1.0'"/>
			</xsl:attribute>
			<xsl:element name="services">
				<xsl:attribute name="binding-component">false</xsl:attribute>
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
