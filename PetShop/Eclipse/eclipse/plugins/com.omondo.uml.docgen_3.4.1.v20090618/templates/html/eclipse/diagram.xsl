<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   
	<xsl:output method="html"/>

	<xsl:include href="english.xsl"/>
   
	<xsl:param name="base.dir" select="''"/>
	<xsl:param name="comment.text" select="''"/>
	<xsl:param name="root.file" select="''"/>

	<xsl:template match="/">
		<xsl:apply-templates select="*" mode="diagram"/>
	</xsl:template>

<!--============================================-->
<!-- Create each %diagram%.html                 -->
<!--============================================-->
	<xsl:template match="*" mode="diagram">
		<xsl:for-each select="diagram">
			<xsl:document href="{$root.file}">
				<html>
					<head>
					    <xsl:for-each select="img">
						    <xsl:for-each select="map">
								<link rel="stylesheet" type="text/css" media="screen">
									<xsl:attribute name="href">
										<xsl:value-of select="@location"/>
									</xsl:attribute>
								</link>
							</xsl:for-each>         
						</xsl:for-each>         
					</head>
					<body>
				<table bgcolor="#CCCCFF" border="0" cellpadding="1" cellspacing="3" table-layout="fixed" width="100%">
					<xsl:if test="position()=1">
						<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
					</xsl:if>
					<tr bgColor="#CCCCFF" width="100%">
						<td bgColor="#00008B" width="72" align="center">
							<span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$diagram-label"/></span>
						</td>
						<td>&#160;</td>
					</tr>
					<xsl:if test="position()=last()">
						<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
					</xsl:if>
				</table>
				<hr />
                <h1> 
						             <xsl:for-each select="classdiagram">
						               <img src="icons/class_diagram.gif"/>
						             </xsl:for-each>
						             <xsl:for-each select="sequencediagram">
               							<img src="icons/sequence_diagram.gif"/>
							     </xsl:for-each>
        						     <xsl:for-each select="usecasediagram">
               							<img src="icons/usecase_diagram.gif"/>
             					    	     </xsl:for-each>
             						     <xsl:for-each select="activitydiagram">
               							<img src="icons/activity_diagram.gif"/>
             						     </xsl:for-each>
             						     <xsl:for-each select="componentdiagram">
                  						<img src="icons/component_diagram.gif"/>
             						     </xsl:for-each>
             						     <xsl:for-each select="collaborationdiagram">
               							<img src="icons/collaboration_diagram.gif"/>
             						     </xsl:for-each>
             						     <xsl:for-each select="deploymentdiagram">
               							<img src="icons/deployment_diagram.gif"/>
             						     </xsl:for-each>
             						     <xsl:for-each select="objectdiagram">
               							<img src="icons/object_diagram.gif"/>
             						     </xsl:for-each>
             						     <xsl:for-each select="statediagram">
               							<img src="icons/state_diagram.gif"/>
             						     </xsl:for-each>
             						     <xsl:for-each select="robustnessdiagram">
               							<img src="icons/robustness_diagram.gif"/>
             						     </xsl:for-each>
				<xsl:for-each select="title">
					<b>&#160;<xsl:value-of select="." /></b>
				</xsl:for-each>
				</h1><br/><br/>
				<xsl:for-each select="img">
					<img border="0">
						<xsl:attribute name="src"><xsl:value-of select="@location" /></xsl:attribute>
					</img><br/>
				</xsl:for-each>
				<br/>
				<xsl:for-each select="description">
					<xsl:copy-of select="*"/>
				</xsl:for-each>
				<br/>
				<xsl:for-each select="usecasediagram">
					<xsl:for-each select="usecase">
						<xsl:for-each select="name">
							<h2><u><b><xsl:copy-of select="$usecase-label"/>&#32;<xsl:apply-templates/></b></u></h2>
						</xsl:for-each>
						<xsl:for-each select="description">
							<xsl:copy-of select="*"/><br/>							
						</xsl:for-each>
						<xsl:for-each select="precondition">
							<b><xsl:copy-of select="$precondition-label"/>&#32;:</b><p><xsl:apply-templates/></p><br/>
						</xsl:for-each>
						<xsl:for-each select="postcondition">
							<b><xsl:copy-of select="$postcondition-label"/>&#32;:</b><p><xsl:apply-templates/></p><br/>
						</xsl:for-each>
						<xsl:for-each select="normalflow">
							<b><xsl:copy-of select="$normalflow-label"/>&#32;:</b><xsl:copy-of select="*"/><br/>							
						</xsl:for-each>
						<xsl:for-each select="alternativeflow">
							<b><xsl:copy-of select="$alternativeflow-label"/>&#32;<xsl:value-of select="position()" /> :</b><xsl:copy-of select="*"/><br/>
						</xsl:for-each>
						<hr/>
					</xsl:for-each>
					<xsl:for-each select="system">
						<xsl:for-each select="name">
							<h2><u><b><xsl:copy-of select="$system-label"/>&#32;<xsl:apply-templates/></b></u></h2>
							<br/>
						</xsl:for-each>
						<xsl:for-each select="usecase">
							<xsl:for-each select="name">
								<h3><b><xsl:copy-of select="$usecase-label"/>&#32;<xsl:apply-templates/></b></h3>
							</xsl:for-each>
							<xsl:for-each select="description">
								<xsl:copy-of select="*"/>
							</xsl:for-each>
							<xsl:for-each select="precondition">
								<b><xsl:copy-of select="$precondition-label"/>&#32;:</b><p><xsl:apply-templates/></p><br/>
							</xsl:for-each>
							<xsl:for-each select="postcondition">
								<b><xsl:copy-of select="$postcondition-label"/>&#32;:</b><p><xsl:apply-templates/></p><br/>
							</xsl:for-each>
							<xsl:for-each select="normalflow">
								<b><xsl:copy-of select="$normalflow-label"/>&#32;:</b><xsl:copy-of select="*"/><br/>
							</xsl:for-each>
							<xsl:for-each select="alternativeflow">
								<b><xsl:copy-of select="$alternativeflow-label"/>&#32;<xsl:value-of select="position()" /> :</b><xsl:copy-of select="*"/><br/>
							</xsl:for-each>
						</xsl:for-each>
						<hr/>
					</xsl:for-each>
				</xsl:for-each>
				<xsl:for-each select="links">									
					<xsl:if test="position()=1">
						<h4><b><xsl:copy-of select="$links-label"/>&#32;:</b></h4>
					</xsl:if>
					<xsl:for-each select="link">
						&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
						<a>
							<xsl:attribute name="href"><xsl:value-of select="@location" />.html</xsl:attribute>
							<xsl:value-of select="@name" />
						</a><br/>
					</xsl:for-each>
				</xsl:for-each>
				<hr/>
				<br/>				
				<table bgcolor="#CCCCFF" border="0" cellpadding="1" cellspacing="3" table-layout="fixed" width="100%">
					<xsl:if test="position()=1">
						<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
					</xsl:if>
					<tr bgColor="#CCCCFF" width="100%">
						<td bgColor="#00008B" width="72" align="center">
							<span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$diagram-label"/></span>
						</td>
						<td>&#160;</td>
					</tr>
					<xsl:if test="position()=last()">
						<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
					</xsl:if>
				</table>
				<hr />
				<br/>
				<br/>
				<center>
					<span style="font-size:xx-small"><xsl:value-of select="$comment.text" /></span>
				</center>
				<br/>
					</body>
				</html>
			</xsl:document>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="reference">
		<a>
			<xsl:attribute name="href"><xsl:value-of select="@type" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
			<xsl:apply-templates/>
		</a>
	</xsl:template>

</xsl:stylesheet>