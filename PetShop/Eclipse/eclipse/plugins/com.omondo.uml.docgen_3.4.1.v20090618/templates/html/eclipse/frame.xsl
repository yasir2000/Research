<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   
<xsl:output method="html"/>

<xsl:variable name="overview-label" select="'Overview'"/>
<xsl:variable name="package-label" select="'Package'"/>
<xsl:variable name="packages-label" select="'Packages'"/>
<xsl:variable name="class-label" select="'Class'"/>
<xsl:variable name="classes-label" select="'Classes'"/>
<xsl:variable name="diagram-label" select="'Diagram'"/>
<xsl:variable name="diagrams-label" select="'Diagrams'"/>
<xsl:variable name="all-label" select="'All'"/>
<xsl:variable name="alldiagrams-label" select="'All Diagrams'"/>
<xsl:variable name="allclasses-label" select="'All Classes'"/>
<xsl:variable name="interface-label" select="'Interface'"/>
<xsl:variable name="interfaces-label" select="'Interfaces'"/>
<xsl:variable name="exception-label" select="'Exception'"/>
<xsl:variable name="exceptions-label" select="'Exceptions'"/>
<xsl:variable name="error-label" select="'Error'"/>
<xsl:variable name="errors-label" select="'Errors'"/>
<xsl:variable name="robustnessdiagrams-label" select="'Robustness Diagrams'"/>
<xsl:variable name="usecasediagrams-label" select="'Usecase Diagrams'"/>
<xsl:variable name="collaborationdiagrams-label" select="'Collaboration Diagrams'"/>
<xsl:variable name="activitydiagrams-label" select="'Activity Diagrams'"/>
<xsl:variable name="classdiagrams-label" select="'Class Diagrams'"/>
<xsl:variable name="statediagrams-label" select="'State Diagrams'"/>
<xsl:variable name="sequencediagrams-label" select="'Sequence Diagrams'"/>
<xsl:variable name="objectdiagrams-label" select="'Object Diagrams'"/>
<xsl:variable name="componentdiagrams-label" select="'Component Diagrams'"/>
<xsl:variable name="deploymentdiagrams-label" select="'Deployment Diagrams'"/>
<xsl:variable name="robustnessdiagramsummary-label" select="'Robustness Diagram Summary'"/>
<xsl:variable name="usecasediagramsummary-label" select="'Usecase Diagram Summary'"/>
<xsl:variable name="collaborationdiagramsummary-label" select="'Collaboration Diagram Summary'"/>
<xsl:variable name="activitydiagramsummary-label" select="'Activity Diagram Summary'"/>
<xsl:variable name="classdiagramsummary-label" select="'Class Diagram Summary'"/>
<xsl:variable name="statediagramsummary-label" select="'State Diagram Summary'"/>
<xsl:variable name="sequencediagramsummary-label" select="'Sequence Diagram Summary'"/>
<xsl:variable name="objectdiagramsummary-label" select="'Object Diagram Summary'"/>
<xsl:variable name="componentdiagramsummary-label" select="'Component Diagram Summary'"/>
<xsl:variable name="deploymentdiagramsummary-label" select="'Deployment Diagram Summary'"/>
<xsl:variable name="interfacesummary-label" select="'Interface Summary'"/>
<xsl:variable name="classsummary-label" select="'Class Summary'"/>
<xsl:variable name="exceptionsummary-label" select="'Exception Summary'"/>
<xsl:variable name="errorsummary-label" select="'Error Summary'"/>
<xsl:variable name="description-label" select="'Description'"/>
<xsl:variable name="links-label" select="'Links'"/>
<xsl:variable name="fieldsummary-label" select="'Field Summary'"/>
<xsl:variable name="constructorsummary-label" select="'Constructor Summary'"/>
<xsl:variable name="methodsummary-label" select="'Method Summary'"/>
<xsl:variable name="fielddetail-label" select="'Field Detail'"/>
<xsl:variable name="constructordetail-label" select="'Constructor Detail'"/>
<xsl:variable name="methoddetail-label" select="'Method Detail'"/>
<xsl:variable name="usecase-label" select="'Usecase'"/>
<xsl:variable name="precondition-label" select="'Precondition'"/>
<xsl:variable name="postcondition-label" select="'Postcondition'"/>
<xsl:variable name="normalflow-label" select="'Normal Flow'"/>
<xsl:variable name="alternativeflow-label" select="'Alternative Flow'"/>
<xsl:variable name="system-label" select="'System'"/>
   
<xsl:param name="base.dir" select="''"/>
<xsl:param name="comment.text" select="''"/>
<xsl:param name="root.file" select="''"/>

<xsl:template match="/">
  <xsl:apply-templates select="*" mode="frameset"/>
  <xsl:apply-templates select="*" mode="packageListFrame"/>
  <xsl:apply-templates select="*" mode="packageFrame"/>
  <xsl:apply-templates select="*" mode="classFrame"/>
  <xsl:apply-templates select="*" mode="diagramFrame"/>
</xsl:template>
   
<!--==============================================-->
<!-- Create frameset container (mode ="frameset") -->
<!--==============================================-->
<xsl:template match="docgen" mode="frameset">
  <xsl:document href="{$root.file}">     
    <html>
     <head>
      <title></title>
     </head>
     <frameset cols="20%, 80%">
       <frameset rows="30%, 70%">
         <frame name="packageListFrame">
            <xsl:attribute name="src"><xsl:text disable-output-escaping="yes">overview-frame.html</xsl:text></xsl:attribute>
         </frame>
         <frame name="packageFrame">
            <xsl:attribute name="src"><xsl:text disable-output-escaping="yes">allclasses-frame.html</xsl:text></xsl:attribute>
         </frame>
       </frameset>
         <frame name="classFrame">
            <xsl:attribute name="src"><xsl:text disable-output-escaping="yes">overview-summary.html</xsl:text></xsl:attribute>
         </frame>
     </frameset>
     <noframes>
       <h2>Frame Alert</h2>
       <p>This document is designed to be viewed using the frames feature. If you see this message, you are using a non-frame-capable web client.</p>
       Link to <a href="overview-summary.html">Non-frame version.</a></noframes>
    </html>
  </xsl:document>
</xsl:template>
   
<!--=========================================================-->
<!-- Create overview-frame.html  (mode = "packageListFrame") -->
<!--=========================================================-->
<xsl:template match="docgen" mode="packageListFrame">
  <xsl:document href="{concat($base.dir,'overview-frame.html')}">
    <html>
     <head>
       <title></title>
     </head>
     <body>
        <xsl:for-each select="info">
          <xsl:for-each select="title">
            <span style="font-size:larger; font-weight:bold">
              <xsl:apply-templates />
            </span>
          </xsl:for-each>
        </xsl:for-each>
        <br /><img src="icons/java_project.gif"/><a target="packageFrame" href="allclasses-frame.html"><xsl:copy-of select="$all-label"/></a><br />
        <br />
        <xsl:for-each select="diagram">
           	 <xsl:if test="position()=1">
 		       <span style="font-size:larger"><xsl:copy-of select="$diagrams-label"/></span>
  		        <br />
             </xsl:if>
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
            <a target="classFrame">
            <xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
	        <xsl:for-each select="title">
    	        <xsl:apply-templates />
	        </xsl:for-each>
            </a>
          <br />
        </xsl:for-each>
          <br />
        <span style="font-size:larger"><xsl:copy-of select="$packages-label"/></span>
        <xsl:for-each select="package">
          <br />
            <img src="icons/package.gif"/>
            <a target="packageFrame">
            <xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">_overview.html</xsl:text></xsl:attribute>
            <xsl:value-of select="@name" />
            </a>
        </xsl:for-each>
     </body>
    </html>
  </xsl:document>
</xsl:template>
   
<!--=========================================================-->
<!-- Create allclasses-frame.html  (mode = "packageFrame")   -->
<!--=========================================================-->
<xsl:template match="docgen" mode="packageFrame">
  <xsl:document href="{concat($base.dir,'allclasses-frame.html')}">
    <html>
     <head>
       <title></title>
     </head>
     <body bgcolor="#FFFFFF" text="#000000">
       <xsl:for-each select="package/diagram">
           	 <xsl:if test="position()=1">
		       <span style="font-size:larger"><xsl:copy-of select="$alldiagrams-label"/></span><br/>
             </xsl:if>
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
       <xsl:sort select="@id"/>
             <a target="classFrame">
                 <xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
               <xsl:for-each select="title">
                 <xsl:apply-templates/>
               </xsl:for-each>
             </a>
         <br />
       </xsl:for-each>
       <br/>
       <span style="font-size:larger"><xsl:copy-of select="$allclasses-label"/></span><br/>
       <xsl:for-each select="package/classifier">
       <xsl:sort select="@name"/>
               <xsl:for-each select="class">
       	         <img src="icons/class_public.gif"/>
                 <a target="classFrame">
                 <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                 <xsl:value-of select="../@name"/>
                 </a>
               </xsl:for-each>
               <xsl:for-each select="interface">
       	         <img src="icons/interface_public.gif"/>
                 <a target="classFrame">
                 <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                 <i><xsl:value-of select="../@name"/></i>
                 </a>
               </xsl:for-each>
               <xsl:for-each select="exception">
       	         <img src="icons/class_public.gif"/>
                 <a target="classFrame">
                 <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                 <xsl:value-of select="../@name"/>
                 </a>
               </xsl:for-each>
               <xsl:for-each select="error">
       	         <img src="icons/class_public.gif"/>
                 <a target="classFrame">
                 <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                 <xsl:value-of select="../@name"/>
                 </a>
               </xsl:for-each>
         <br />
       </xsl:for-each>
     </body>
    </html>
  </xsl:document>

<!--================================================================-->
<!-- Create each %package%_overview.html  (mode = "packageFrame")   -->
<!--================================================================-->
  <xsl:for-each select="package">
    <xsl:document href="{concat($base.dir,concat(@id,'_overview.html'))}">
      <html>
       <head>
         <title></title>
       </head>
       <body bgcolor="#FFFFFF" text="#000000">
         <img src="icons/package.gif"/>
         <a target="classFrame">
           <xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
           <span style="font-size:larger"><xsl:value-of select="@name"/></span><br/>
         </a>
         <br/>
       <xsl:for-each select="diagram">
	       <xsl:sort select="@id"/>
           	 <xsl:if test="position()=1">
		         <br/>
		         <span style="font-size:larger"><xsl:copy-of select="$diagrams-label"/></span><br/>
             </xsl:if>
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
             <a target="classFrame">
                 <xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
               <xsl:for-each select="title">
                 <xsl:apply-templates/>
               </xsl:for-each>
             </a>
         <br />
       </xsl:for-each>
         <br />
         <xsl:for-each select="classifier/interface">
           <xsl:sort select="../@name"/>
           <xsl:if test="count(.) &gt; 0">
           	 <xsl:if test="position()=1">
		         <br/>
		         <span style="font-size:larger"><xsl:copy-of select="$interfaces-label"/></span><br/>
             </xsl:if>
             <img src="icons/interface_public.gif"/>
             <a target="classFrame">
               <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
              <i><xsl:value-of select="../@name"/></i>
             </a>
           <br />
           </xsl:if>
         </xsl:for-each>
         <xsl:for-each select="classifier/class">
           <xsl:sort select="../@name"/>
           <xsl:if test="count(.) &gt; 0">
           	 <xsl:if test="position()=1">
		         <br/>
		         <span style="font-size:larger"><xsl:copy-of select="$classes-label"/></span><br/>
             </xsl:if>
             <img src="icons/class_public.gif"/>
             <a target="classFrame">
               <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
              <xsl:value-of select="../@name"/>
             </a>
           <br />
           </xsl:if>
         </xsl:for-each>
         <xsl:for-each select="classifier/exception">
           <xsl:sort select="../@name"/>
           <xsl:if test="count(.) &gt; 0">
           	 <xsl:if test="position()=1">
		         <br/>
		         <span style="font-size:larger"><xsl:copy-of select="$exceptions-label"/></span><br/>
             </xsl:if>
             <img src="icons/class_public.gif"/>
             <a target="classFrame">
               <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
              <xsl:value-of select="../@name"/>
             </a>
           <br />
           </xsl:if>
         </xsl:for-each>
           
         <xsl:for-each select="classifier/error">
           <xsl:sort select="../@name"/>
           <xsl:if test="count(.) &gt; 0">
           	 <xsl:if test="position()=1">
		         <br/>
           		<span style="font-size:larger"><xsl:copy-of select="$errors-label"/></span><br/>
             </xsl:if>
             <img src="icons/class_public.gif"/>
             <a target="classFrame">
               <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
              <xsl:value-of select="../@name"/>
             </a>
           <br />
           </xsl:if>
         </xsl:for-each>
       </body>
      </html>
    </xsl:document>
  </xsl:for-each>
</xsl:template>

<!--========================================================-->
<!-- Create overview-summary.html  (mode = "classFrame")    -->
<!--========================================================-->
<xsl:template match="docgen" mode="classFrame">
  <xsl:document href="{concat($base.dir,'overview-summary.html')}">
    <html>
     <head>
       <title></title>
     </head>
       <body>
         <table bgcolor="#CCCCFF" border="0" cellpadding="1" cellspacing="3" table-layout="fixed" width="100%">
           <xsl:if test="position()=1">
             <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
           </xsl:if>
           <tr bgColor="#CCCCFF" width="100%">
             <td bgColor="#00008B" width="72" align="center">
               <span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$overview-label"/></span>
             </td>
             <td width="72" align="center"><xsl:copy-of select="$package-label"/></td>
             <td width="72" align="center"><xsl:copy-of select="$class-label"/></td>
             <td width="72" align="center"><xsl:copy-of select="$diagram-label"/></td>
             <td>&#160;</td>
           </tr>
           <xsl:if test="position()=last()">
             <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
           </xsl:if>
         </table>
           <hr />
             <xsl:for-each select="info">
               <xsl:for-each select="title">
                 <center>
                   <span style="font-size:x-large; font-weight:bold">
                     <xsl:apply-templates />
                   </span>
                 </center>
               </xsl:for-each>
               <xsl:for-each select="subtitle">
                 <center>
                   <span style="font-size:larger">
                     <xsl:apply-templates />
                   </span>
                 </center>
               </xsl:for-each>
               <xsl:for-each select="date">
                 <center>
                   <xsl:apply-templates />
                 </center>
               </xsl:for-each>
               <xsl:for-each select="authorgroup">
                 <br />
                 <xsl:for-each select="author">
                   <xsl:for-each select="@name">
                     <span style="font-style:italic">
                       <xsl:value-of select="." />
                     </span>
                   </xsl:for-each>
                   <span style="font-style:italic">&#160;</span>
                     <xsl:for-each select="@email">
                       <span style="font-style:italic">(<a>
                         <xsl:attribute name="href"><xsl:text disable-output-escaping="yes">mailto:</xsl:text><xsl:value-of select="." /></xsl:attribute>
                         <span style="font-style:italic">
                           <xsl:value-of select="." />
                         </span>)</a>
                       </span>
                     </xsl:for-each>
                   </xsl:for-each>
                 </xsl:for-each>
               </xsl:for-each>
               <br />
               <br />
               
               <xsl:for-each select="diagram/usecasediagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                            <font size="+2"><b><xsl:copy-of select="$usecasediagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/usecase_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/robustnessdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                         <font size="+2"><b><xsl:copy-of select="$robustnessdiagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/robustness_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/collaborationdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                          <font size="+2"><b><xsl:copy-of select="$collaborationdiagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/collaboration_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/activitydiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$activitydiagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/activity_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/classdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$classdiagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/class_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/statediagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$statediagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/state_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/sequencediagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$sequencediagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/sequence_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/objectdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$objectdiagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/object_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/componentdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$componentdiagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/component_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/deploymentdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$deploymentdiagrams-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/deployment_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="package">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                         <xsl:for-each select="../info">
                                 <xsl:for-each select="title">
                                     <font size="+2"><b><xsl:apply-templates />&#32;<xsl:copy-of select="$packages-label"/></b></font>
		                         </xsl:for-each>
                         </xsl:for-each>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td width="15%">
                       <img src="icons/package.gif"/><a>
                         <xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                         <span style="font-size:larger">
                           <xsl:value-of select="@name" />
                         </span>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="javadoc">
                       <xsl:for-each select="summary">
                         <xsl:copy-of select="*"/>
                       </xsl:for-each>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
                 </xsl:if>
               </xsl:for-each>
               <br />
               <hr />
               <table bgcolor="#CCCCFF" border="0" cellpadding="1" cellspacing="3" width="100%">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
	    	         <td bgColor="#00008B" width="72" align="center">
    	    	       <span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$overview-label"/></span>
           			  </td>
		             <td width="72" align="center"><xsl:copy-of select="$package-label"/></td>
        		     <td width="72" align="center"><xsl:copy-of select="$class-label"/></td>
		             <td width="72" align="center"><xsl:copy-of select="$diagram-label"/></td>
		             <td>&#160;</td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
               </table>
               <hr />
               <xsl:for-each select="info">
                 <xsl:for-each select="legalnotice">
                   <span style="font-size:x-small">
                     <span style="font-size:x-small">
                       <xsl:apply-templates />
                     </span>
                   </span>
                 </xsl:for-each>
               </xsl:for-each>
               <br />
           </body>
   </html>
  </xsl:document>

<!--=========================================================-->
<!-- Create each %package%.html  (mode = "classFrame")    -->
<!--=========================================================-->
  <xsl:for-each select="package">
    <xsl:document href="{concat($base.dir,concat(@id,'.html'))}">
		<html>
			<head>
       			<xsl:for-each select="diagram/img">
	      	 		<xsl:for-each select="map">
    	   				<link rel="stylesheet" type="text/css" media="screen">
       						<xsl:attribute name="href"><xsl:value-of select="@location"/></xsl:attribute>
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
             <td width="72" align="center">
               <b><a href="overview-summary.html"><xsl:copy-of select="$overview-label"/></a></b>
             </td>
             <td bgColor="#00008B" width="72" align="center">
               <span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$package-label"/></span>
             </td>
             <td width="72" align="center"><xsl:copy-of select="$class-label"/></td>
             <td width="72" align="center"><xsl:copy-of select="$diagram-label"/></td>
             <td>&#160;</td>
           </tr>
           <xsl:if test="position()=last()">
             <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
           </xsl:if>
         </table>
           <hr />
				<span style="font-size:large; font-weight:bold"><img src="icons/package.gif"/><xsl:copy-of select="$package-label"/>&#160;</span>
				<span style="font-size:large; font-weight:bold">
					<xsl:value-of select="@name" />
				</span>
				<br/>
				<br/>
				<xsl:for-each select="javadoc">
					<xsl:for-each select="summary">
						<xsl:copy-of select="*"/>
					</xsl:for-each>
				</xsl:for-each>&#160;
				<hr/>
				<br/>
				<h4>See:</h4>
				<span style="color:#0000FF">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </span>
				<a href="#desc">
					<span style="color:#0000FF; font-weight:bold; text-decoration:underline"><xsl:copy-of select="$description-label"/></span>
				</a>
				<br/>
				<hr/>
				<br/>

               <xsl:for-each select="diagram/usecasediagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                            <font size="+2"><b><xsl:copy-of select="$usecasediagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/usecase_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/robustnessdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                         <font size="+2"><b><xsl:copy-of select="$robustnessdiagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/robustness_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/collaborationdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                          <font size="+2"><b><xsl:copy-of select="$collaborationdiagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/collaboration_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/activitydiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$activitydiagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/activity_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/classdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$classdiagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/class_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/statediagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$statediagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/state_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/sequencediagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$sequencediagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/sequence_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/objectdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$objectdiagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/object_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/componentdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$componentdiagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/component_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>
				
               <xsl:for-each select="diagram/deploymentdiagram">
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <thead>
                     <tr bgColor="#CCCCFF">
                       <td colspan="2">
                           <font size="+2"><b><xsl:copy-of select="$deploymentdiagramsummary-label"/></b></font>
                       </td>
                     </tr>
                   </thead>
                 </xsl:if>
                 <xsl:if test="position()=1">
                   <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
                 </xsl:if>
                 <tr>
                   <td>
                       <img src="icons/deployment_diagram.gif"/>
                       <a>
                         <xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
                             <xsl:for-each select="../title">
                                 <span style="font-size:larger">
                                     <xsl:apply-templates />
                                 </span>
                             </xsl:for-each>
                       </a>
                   </td>
                   <td>
                     <xsl:for-each select="../summary">
                         <xsl:copy-of select="*"/>
                     </xsl:for-each>&#160;
                   </td>
                 </tr>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
                 </xsl:if>
                 <xsl:if test="position()=last()">
                   <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					<br/>
                 </xsl:if>
               </xsl:for-each>

				<xsl:for-each select="classifier/interface">
					<xsl:if test="position()=1">
						<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
					</xsl:if>
					<xsl:if test="position()=1">
						<thead>
							<tr bgColor="#CCCCFF">
								<td colspan="2">
									<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$interfacesummary-label"/></span>
								</td>
							</tr>
						</thead>
					</xsl:if>
					<xsl:if test="position()=1">
						<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
					</xsl:if>
					<tr>
						<td width="15%">
							<span style="color:#0000FF; font-style:italic; font-weight:bold; text-decoration:underline">
							        <img src="icons/interface_public.gif"/>
								<a>
									<xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
									<span style="color:#0000FF; font-style:italic; font-weight:bold; text-decoration:underline">
            	    					<xsl:value-of select="../@name" />
							  		</span>
								</a>
					  		</span>
						</td>
						<td>
							<xsl:for-each select="../javadoc">
					  			<xsl:for-each select="summary">
									<xsl:copy-of select="*"/>
					  			</xsl:for-each>
							</xsl:for-each>&#160;
				  		</td>
					</tr>
					<xsl:if test="position()=last()">
				  		<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
					</xsl:if>
					<xsl:if test="position()=last()">
						  <xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					</xsl:if>
				</xsl:for-each>
			  	<br/>
			  	<br/>
			  	<xsl:for-each select="classifier/class">
					<xsl:if test="position()=1">
			      		<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
			    	</xsl:if>
					<xsl:if test="position()=1">
				  		<thead>
							<tr>
					  			<td bgColor="#CCCCFF" colspan="2">
									<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$classsummary-label"/></span>
					  			</td>
							</tr>
				  		</thead>
					</xsl:if>
					<xsl:if test="position()=1">
				  		<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
			    	</xsl:if>
					<tr>
				  		<td width="15%">
					  		<span style="color:#0000FF; font-style:italic; font-weight:bold; text-decoration:underline">
								<img src="icons/class_public.gif"/>
								<a>
						  			<xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
						  			<span style="color:#0000FF; font-style:italic; font-weight:bold; text-decoration:underline">
			                			<xsl:value-of select="../@name" />
									</span>
								</a>
					  		</span>
				  		</td>
				  		<td>
							<xsl:for-each select="../javadoc">
					  			<xsl:for-each select="summary">
									<xsl:copy-of select="*"/>
					  			</xsl:for-each>
							</xsl:for-each>&#160;
				  		</td>
					</tr>
					<xsl:if test="position()=last()">
				  		<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
					</xsl:if>
					<xsl:if test="position()=last()">
				  		<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					</xsl:if>
			  	</xsl:for-each>
			  	<br/>
		      	<br/>
			  	<xsl:for-each select="classifier/exception">
					<xsl:if test="position()=1">
				    	<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
			    	</xsl:if>
					<xsl:if test="position()=1">
				  		<thead>
							<tr>
					  			<td bgColor="#CCCCFF" colspan="2">
									<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$exceptionsummary-label"/></span>
					  			</td>
							</tr>
				  		</thead>
					</xsl:if>
					<xsl:if test="position()=1">
				  		<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
			    	</xsl:if>
					<tr>
				  		<td>
					  		<span style="color:#0000FF; font-style:italic; font-weight:bold; text-decoration:underline">
								<img src="icons/class_public.gif"/>
								<a>
						  			<xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
						  			<span style="color:#0000FF; font-style:italic; font-weight:bold; text-decoration:underline">
			                			<xsl:value-of select="../@name" />
									</span>
								</a>
					  		</span>
				  		</td>
				  		<td>
							<xsl:for-each select="../javadoc">
					  			<xsl:for-each select="summary">
									<xsl:copy-of select="*"/>
					  			</xsl:for-each>
							</xsl:for-each>&#160;
				  		</td>
					</tr>
					<xsl:if test="position()=last()">
				  		<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
					</xsl:if>
					<xsl:if test="position()=last()">
				  		<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					</xsl:if>
			  	</xsl:for-each>
			  	<br/>
		      	<br/>
			  	<xsl:for-each select="classifier/error">
					<xsl:if test="position()=1">
				    	<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
			    	</xsl:if>
					<xsl:if test="position()=1">
				  		<thead>
							<tr>
					  			<td bgColor="#CCCCFF" colspan="2">
									<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$errorsummary-label"/></span>
					  			</td>
							</tr>
				  		</thead>
					</xsl:if>
					<xsl:if test="position()=1">
				  		<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
			    	</xsl:if>
					<tr>
				  		<td>
					  		<span style="color:#0000FF; font-style:italic; font-weight:bold; text-decoration:underline">
								<img src="icons/class_public.gif"/>
								<a>
						  			<xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
						  			<span style="color:#0000FF; font-style:italic; font-weight:bold; text-decoration:underline">
                						<xsl:value-of select="../@name" />
						  			</span>
								</a>
					  		</span>
				  		</td>
				  		<td>
							<xsl:for-each select="../javadoc">
					  			<xsl:for-each select="summary">
									<xsl:copy-of select="*"/>
					  			</xsl:for-each>
							</xsl:for-each>&#160;
				  		</td>
					</tr>
					<xsl:if test="position()=last()">
				  		<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
					</xsl:if>
					<xsl:if test="position()=last()">
				  		<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
					</xsl:if>
			  	</xsl:for-each>
			  	<br/>
		      	<br/>
				<br/>
				<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$package-label"/>&#32;</span>
				<span style="font-size:large; font-weight:bold">&#160;</span>
			  	<span style="font-size:large; font-weight:bold">
					<a name="desc">
				  		<span style="font-size:large; font-weight:bold">
                			<xsl:value-of select="@name" />
				  		</span>
					</a>
			  	</span>
				<span style="font-size:large; font-weight:bold">&#32;<xsl:copy-of select="$description-label"/></span>
				<br/>
				<br/>
				<xsl:for-each select="javadoc">
			  		<xsl:for-each select="description">
						<xsl:copy-of select="*"/>
			  		</xsl:for-each>
				</xsl:for-each>&#160;<br/>
				<br/>
				<xsl:for-each select="diagram">
					<xsl:for-each select="img">
						<xsl:for-each select="map">
							<div>
								<xsl:attribute name="id"><xsl:value-of select="../../@id" /></xsl:attribute>
								<xsl:for-each select="element">
									<a>
										<xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
										<xsl:attribute name="id"><xsl:value-of select="@id" /></xsl:attribute>
									</a>
								</xsl:for-each>						
							</div>
						</xsl:for-each>						
 			  		</xsl:for-each>
				</xsl:for-each>
				<br/>
				<hr/>&#160;
         <table bgcolor="#CCCCFF" border="0" cellpadding="1" cellspacing="3" table-layout="fixed" width="100%">
           <xsl:if test="position()=1">
             <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
           </xsl:if>
           <tr bgColor="#CCCCFF" width="100%">
             <td width="72" align="center">
               <b><a href="overview-summary.html"><xsl:copy-of select="$overview-label"/></a></b>
             </td>
             <td bgColor="#00008B" width="72" align="center">
               <span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$package-label"/></span>
             </td>
             <td width="72" align="center"><xsl:copy-of select="$class-label"/></td>
             <td width="72" align="center"><xsl:copy-of select="$diagram-label"/></td>
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

<!--====================================================-->
<!-- Create each %class%.html  (mode = "classFrame")    -->
<!--====================================================-->
<xsl:for-each select="classifier">
    <xsl:document href="{concat($base.dir,concat(@id,'.html'))}">
		<html>
			<head>
       			<xsl:for-each select="diagram/img">
	      	 		<xsl:for-each select="map">
    	   				<link rel="stylesheet" type="text/css" media="screen">
       						<xsl:attribute name="href"><xsl:value-of select="@location"/></xsl:attribute>
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
             <td width="72" align="center">
               <b><a href="overview-summary.html"><xsl:copy-of select="$overview-label"/></a></b>
             </td>
             <td width="72" align="center">
               <b><a>
               <xsl:attribute name="href"><xsl:value-of select="@packageid" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
               <xsl:copy-of select="$package-label"/>
               </a></b>
             </td>
             <td bgColor="#00008B" width="72" align="center">
               <span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$class-label"/></span>
             </td>
             <td width="72" align="center">
               <xsl:copy-of select="$diagram-label"/>
             </td>
             <td>&#160;</td>
           </tr>
           <xsl:if test="position()=last()">
             <xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
           </xsl:if>
         </table>
           <hr />
           <h2><font size="-1"><img src="icons/package.gif"/><xsl:value-of select="@packageid" /></font></h2>
           
						<xsl:for-each select="interface">
								<img src="icons/interface_public.gif"/>
								<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$interface-label"/>&#160;</span>
						</xsl:for-each>
						<xsl:for-each select="class">
								<img src="icons/class_public.gif"/>
								<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$class-label"/>&#160;</span>
						</xsl:for-each>
						<xsl:for-each select="exception">
								<img src="icons/class_public.gif"/>
								<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$class-label"/>&#160;</span>
						</xsl:for-each>
						<xsl:for-each select="error">
								<img src="icons/class_public.gif"/>
								<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$class-label"/>&#160;</span>
						</xsl:for-each>
						<span style="font-size:large; font-weight:bold">
							<xsl:value-of select="@name" />
						</span>
						<br/>
						<br/>
							<xsl:for-each select="signature">
								<xsl:value-of select="@visibility"/><xsl:text> </xsl:text>
								<xsl:if test="@abstract=&quot;true&quot;">
									<xsl:text>abstract </xsl:text>
								</xsl:if>
								<xsl:if test="@static=&quot;true&quot;">
									<xsl:text>static </xsl:text>
								</xsl:if>
								<xsl:if test="@final=&quot;true&quot;">
									<xsl:text>final </xsl:text>
								</xsl:if>
								<xsl:if test="@volatile=&quot;true&quot;">
									<xsl:text>volatile </xsl:text>
								</xsl:if>
								<xsl:if test="@native=&quot;true&quot;">
									<xsl:text>native </xsl:text>
								</xsl:if>
								<xsl:if test="@synchronized=&quot;true&quot;">
									<xsl:text>synchronized </xsl:text>
								</xsl:if>
								<xsl:if test="@strictfp=&quot;true&quot;">
									<xsl:text>strictfp </xsl:text>
								</xsl:if>
								<xsl:if test="@synthetic=&quot;true&quot;">
									<xsl:text>synthetic </xsl:text>
								</xsl:if>
								<xsl:if test="@transient=&quot;true&quot;">
									<xsl:text>transient </xsl:text>
								</xsl:if>
							</xsl:for-each>
							<b><xsl:value-of select="@name" /></b>
							<br/>
							<xsl:for-each select="javadoc">
								<xsl:for-each select="description">
									<xsl:copy-of select="*"/>
								</xsl:for-each>
							</xsl:for-each>
							<br/>
							<br/>

							<xsl:for-each select="diagram">
             <h3>
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
             </xsl:for-each>&#160;
								<xsl:for-each select="title">
									<xsl:value-of select="." />
								</xsl:for-each></h3>
								<br/>
									<xsl:for-each select="img">
										<xsl:for-each select="map">
											<div>
												<xsl:attribute name="id"><xsl:value-of select="../../@id" /></xsl:attribute>
												<xsl:for-each select="element">
													<a>
														<xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
														<xsl:attribute name="id"><xsl:value-of select="@id" /></xsl:attribute>
													</a>
												</xsl:for-each>						
											</div>
										</xsl:for-each>						
								</xsl:for-each>
								<br/>
								<xsl:for-each select="description">
									<xsl:copy-of select="*"/>
								</xsl:for-each>
								<br/>
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
								<br/>
							</xsl:for-each>

							<xsl:for-each select="attributegroup">
								<xsl:for-each select="attribute">
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
									</xsl:if>
									<xsl:if test="position()=1">
										<thead>
											<tr>
												<td bgColor="#CCCCFF" colspan="2">
													<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$fieldsummary-label"/></span>
												</td>
											</tr>
										</thead>
									</xsl:if>
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
									</xsl:if>
									<tr>
										<td align="right" valign="top" width="1%">
											<xsl:for-each select="signature">
											<font size="-1"><code>
												<xsl:value-of select="@visibility"/> 
												<xsl:if test="@abstract=&quot;true&quot;">
													<xsl:text> abstract</xsl:text>
												</xsl:if>
												<xsl:if test="@static=&quot;true&quot;">
													<xsl:text> static</xsl:text>
												</xsl:if>
												<xsl:if test="@final=&quot;true&quot;">
													<xsl:text> final</xsl:text>
												</xsl:if>
												<xsl:if test="@volatile=&quot;true&quot;">
													<xsl:text> volatile</xsl:text>
												</xsl:if>
												<xsl:if test="@native=&quot;true&quot;">
													<xsl:text> native</xsl:text>
												</xsl:if>
												<xsl:if test="@synchronized=&quot;true&quot;">
													<xsl:text> synchronized</xsl:text>
												</xsl:if>
												<xsl:if test="@strictfp=&quot;true&quot;">
													<xsl:text> strictfp</xsl:text>
												</xsl:if>
												<xsl:if test="@synthetic=&quot;true&quot;">
													<xsl:text> synthetic</xsl:text>
												</xsl:if>
												<xsl:if test="@transient=&quot;true&quot;">
													<xsl:text> transient</xsl:text>
												</xsl:if>
												<xsl:for-each select="type">
													<xsl:text> </xsl:text>
													<xsl:apply-templates/>
												</xsl:for-each>
											</code></font>
											</xsl:for-each>&#160;
										</td>
										<td>
											<code>
												<a>
													<xsl:attribute name="href"><xsl:text disable-output-escaping="yes">#</xsl:text><xsl:value-of select="@id" /></xsl:attribute>
													<xsl:value-of select="@name"/>
												</a>
											</code>
											<br/>
											<xsl:text disable-output-escaping="yes">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</xsl:text>
											<xsl:for-each select="javadoc">
												<xsl:for-each select="summary">
													<xsl:copy-of select="*"/>
												</xsl:for-each>
											</xsl:for-each>&#160;
										</td>
									</tr>
									<xsl:if test="position()=last()">
										<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
									</xsl:if>
									<xsl:if test="position()=last()">
										<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
									</xsl:if>
								</xsl:for-each>
							</xsl:for-each>
							<br/>
							<br/>
							<xsl:for-each select="constructorgroup">
								<xsl:for-each select="constructor">
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
									</xsl:if>
									<xsl:if test="position()=1">
										<thead>
											<tr>
												<td bgColor="#CCCCFF" colspan="2">
													<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$constructorsummary-label"/></span>
												</td>
											</tr>
										</thead>
									</xsl:if>
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
									</xsl:if>
									<tr>
										<td align="right" valign="top" width="1%">
											<xsl:for-each select="signature">
											<font size="-1"><code>
												<xsl:value-of select="@visibility"/> 
												<xsl:if test="@abstract=&quot;true&quot;">
													<xsl:text> abstract</xsl:text>
												</xsl:if>
												<xsl:if test="@static=&quot;true&quot;">
													<xsl:text> static</xsl:text>
												</xsl:if>
												<xsl:if test="@final=&quot;true&quot;">
													<xsl:text> final</xsl:text>
												</xsl:if>
												<xsl:if test="@volatile=&quot;true&quot;">
													<xsl:text> volatile</xsl:text>
												</xsl:if>
												<xsl:if test="@native=&quot;true&quot;">
													<xsl:text> native</xsl:text>
												</xsl:if>
												<xsl:if test="@synchronized=&quot;true&quot;">
													<xsl:text> synchronized</xsl:text>
												</xsl:if>
												<xsl:if test="@strictfp=&quot;true&quot;">
													<xsl:text> strictfp</xsl:text>
												</xsl:if>
												<xsl:if test="@synthetic=&quot;true&quot;">
													<xsl:text> synthetic</xsl:text>
												</xsl:if>
												<xsl:if test="@transient=&quot;true&quot;">
													<xsl:text> transient</xsl:text>
												</xsl:if>
											</code></font>
											</xsl:for-each>&#160;
										</td>
										<td>
											<code>
												<a>
													<xsl:attribute name="href"><xsl:text disable-output-escaping="yes">#</xsl:text><xsl:value-of select="@id" /></xsl:attribute>
													<xsl:value-of select="@name"/>
												</a>
												<xsl:text>(</xsl:text>
													<xsl:for-each select="signature">
														<xsl:for-each select="parameter">
															<xsl:for-each select="type">
																<xsl:apply-templates/>
															</xsl:for-each>
															<xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="@name" />															
															<xsl:if test="position()!=last()">
																<xsl:text disable-output-escaping="yes">, </xsl:text>
															</xsl:if>
														</xsl:for-each>
													</xsl:for-each>
												<xsl:text>)</xsl:text>
											</code>
											<br/>
											<xsl:text disable-output-escaping="yes">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</xsl:text>
											<xsl:for-each select="javadoc">
												<xsl:for-each select="summary">
													<xsl:copy-of select="*"/>
												</xsl:for-each>
											</xsl:for-each>&#160;
										</td>
									</tr>
									<xsl:if test="position()=last()">
										<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
									</xsl:if>
									<xsl:if test="position()=last()">
										<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
									</xsl:if>
								</xsl:for-each>
							</xsl:for-each>
							<br/>
							<br/>
							<xsl:for-each select="methodgroup">
								<xsl:for-each select="method">
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
									</xsl:if>
									<xsl:if test="position()=1">
										<thead>
											<tr>
												<td bgColor="#CCCCFF" colspan="2">
													<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$methodsummary-label"/></span>
												</td>
											</tr>
										</thead>
									</xsl:if>
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
									</xsl:if>
									<tr>
										<td align="right" valign="top" width="1%">
											<xsl:for-each select="signature">
											<font size="-1"><code>
												<xsl:value-of select="@visibility"/> 
												<xsl:if test="@abstract=&quot;true&quot;">
													<xsl:text> abstract</xsl:text>
												</xsl:if>
												<xsl:if test="@static=&quot;true&quot;">
													<xsl:text> static</xsl:text>
												</xsl:if>
												<xsl:if test="@final=&quot;true&quot;">
													<xsl:text> final</xsl:text>
												</xsl:if>
												<xsl:if test="@volatile=&quot;true&quot;">
													<xsl:text> volatile</xsl:text>
												</xsl:if>
												<xsl:if test="@native=&quot;true&quot;">
													<xsl:text> native</xsl:text>
												</xsl:if>
												<xsl:if test="@synchronized=&quot;true&quot;">
													<xsl:text> synchronized</xsl:text>
												</xsl:if>
												<xsl:if test="@strictfp=&quot;true&quot;">
													<xsl:text> strictfp</xsl:text>
												</xsl:if>
												<xsl:if test="@synthetic=&quot;true&quot;">
													<xsl:text> synthetic</xsl:text>
												</xsl:if>
												<xsl:if test="@transient=&quot;true&quot;">
													<xsl:text> transient</xsl:text>
												</xsl:if>
												<xsl:for-each select="type">
													<xsl:text> </xsl:text>
													<xsl:apply-templates/>
												</xsl:for-each>
											</code></font>
											</xsl:for-each>&#160;
										</td>
										<td>
											<code>
												<a>
													<xsl:attribute name="href"><xsl:text disable-output-escaping="yes">#</xsl:text><xsl:value-of select="@id" /></xsl:attribute>
													<xsl:value-of select="@name"/>
												</a>
												<xsl:text>(</xsl:text>
													<xsl:for-each select="signature">
														<xsl:for-each select="parameter">
															<xsl:for-each select="type">
																<xsl:apply-templates/>
															</xsl:for-each>
															<xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="@name" />															
															<xsl:if test="position()!=last()">
																<xsl:text disable-output-escaping="yes">, </xsl:text>
															</xsl:if>
														</xsl:for-each>
													</xsl:for-each>
												<xsl:text>)</xsl:text>
											</code>
											<br/>
											<xsl:text disable-output-escaping="yes">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</xsl:text>
											<xsl:for-each select="javadoc">
												<xsl:for-each select="summary">
													<xsl:copy-of select="*"/>
												</xsl:for-each>
											</xsl:for-each>&#160;
										</td>
									</tr>
									<xsl:if test="position()=last()">
										<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
									</xsl:if>
									<xsl:if test="position()=last()">
										<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
									</xsl:if>
								</xsl:for-each>
							</xsl:for-each>
							<br/>
							<br/>
							<xsl:for-each select="attributegroup">
								<br/>
								<xsl:for-each select="attribute">
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
											<tr>
												<td bgColor="#CCCCFF">
													<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$fielddetail-label"/></span>
												</td>
											</tr>
										<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
									</xsl:if>
										<a>
											<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
											<span style="font-size:larger">
												<xsl:value-of select="@name"/>
											</span>
										</a>
									<br/>
									<br/>
									<xsl:for-each select="signature">
										<pre>
											<xsl:value-of select="@visibility"/> 
											<xsl:if test="@abstract=&quot;true&quot;">
												<xsl:text> abstract</xsl:text>
											</xsl:if>
											<xsl:if test="@static=&quot;true&quot;">
												<xsl:text> static</xsl:text>
											</xsl:if>
											<xsl:if test="@final=&quot;true&quot;">
												<xsl:text> final</xsl:text>
											</xsl:if>
											<xsl:if test="@volatile=&quot;true&quot;">
												<xsl:text> volatile</xsl:text>
											</xsl:if>
											<xsl:if test="@native=&quot;true&quot;">
												<xsl:text> native</xsl:text>
											</xsl:if>
											<xsl:if test="@synchronized=&quot;true&quot;">
												<xsl:text> synchronized</xsl:text>
											</xsl:if>
											<xsl:if test="@strictfp=&quot;true&quot;">
												<xsl:text> strictfp</xsl:text>
											</xsl:if>
											<xsl:if test="@synthetic=&quot;true&quot;">
												<xsl:text> synthetic</xsl:text>
											</xsl:if>
											<xsl:if test="@transient=&quot;true&quot;">
												<xsl:text> transient</xsl:text>
											</xsl:if>
											<xsl:for-each select="type">
												<xsl:text> </xsl:text>
												<xsl:apply-templates/>
											</xsl:for-each>
											<xsl:text> </xsl:text>
											<b><xsl:value-of select="@name"/></b>
										</pre>
									</xsl:for-each>
									<br/>
									<dl>
									<xsl:for-each select="javadoc">
										<xsl:for-each select="description">
											<xsl:copy-of select="*"/>
										</xsl:for-each>
									</xsl:for-each>
									</dl>
									<br/>
									<hr/>
								</xsl:for-each>
								<br/>
							</xsl:for-each>
							<br/>
							<br/>
							<xsl:for-each select="constructorgroup">
								<br/>
								<xsl:for-each select="constructor">
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
											<tr>
												<td bgColor="#CCCCFF">
													<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$constructordetail-label"/></span>
												</td>
											</tr>
										<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
									</xsl:if>
										<span style="font-size:medium">
											<a>
												<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
												<span style="font-size:medium">
													<xsl:value-of select="@name"/>
												</span>
											</a>
										</span>
									<br/>
									<xsl:for-each select="signature">
										<pre>
											<xsl:value-of select="@visibility"/> 
											<xsl:if test="@abstract=&quot;true&quot;">
												<xsl:text> abstract</xsl:text>
											</xsl:if>
											<xsl:if test="@static=&quot;true&quot;">
												<xsl:text> static</xsl:text>
											</xsl:if>
											<xsl:if test="@final=&quot;true&quot;">
												<xsl:text> final</xsl:text>
											</xsl:if>
											<xsl:if test="@volatile=&quot;true&quot;">
												<xsl:text> volatile</xsl:text>
											</xsl:if>
											<xsl:if test="@native=&quot;true&quot;">
												<xsl:text> native</xsl:text>
											</xsl:if>
											<xsl:if test="@synchronized=&quot;true&quot;">
												<xsl:text> synchronized</xsl:text>
											</xsl:if>
											<xsl:if test="@strictfp=&quot;true&quot;">
												<xsl:text> strictfp</xsl:text>
											</xsl:if>
											<xsl:if test="@synthetic=&quot;true&quot;">
												<xsl:text> synthetic</xsl:text>
											</xsl:if>
											<xsl:if test="@transient=&quot;true&quot;">
												<xsl:text> transient</xsl:text>
											</xsl:if>
											<xsl:text> </xsl:text>
											<b><xsl:value-of select="@name"/></b>
											<xsl:text>(</xsl:text>
												<xsl:for-each select="parameter">
													<xsl:for-each select="type">
														<xsl:apply-templates/>
													</xsl:for-each>
													<xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="@name" />															
													<xsl:if test="position()!=last()">
														<xsl:text disable-output-escaping="yes">, </xsl:text>
													</xsl:if>
												</xsl:for-each>
											<xsl:text>)</xsl:text>
										</pre>
									</xsl:for-each>
									<br/>
									<dl>
									<xsl:for-each select="javadoc">
										<xsl:for-each select="description">
											<xsl:copy-of select="*"/>
										</xsl:for-each>
									</xsl:for-each>
									<br/>
									<xsl:for-each select="diagram">
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
											<h3><xsl:value-of select="." /></h3>
										</xsl:for-each>
										<br/>
										<xsl:for-each select="img">
											<img border="0">
												<xsl:attribute name="src"><xsl:value-of select="@location" /></xsl:attribute>
											</img>
										</xsl:for-each>
										<br/>
										<xsl:for-each select="description">
											<xsl:copy-of select="*"/>
										</xsl:for-each>
										<br/>
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
									<br/>
									</xsl:for-each>
									</dl>
									<hr/>
								</xsl:for-each>
								<br/>
							</xsl:for-each>
							<br/>
							<br/>
							<xsl:for-each select="methodgroup">
								<br/>
								<xsl:for-each select="method">
									<xsl:if test="position()=1">
										<xsl:text disable-output-escaping="yes">&lt;table border="1" cellpadding="3" cellspacing="0" width="100%"&gt;</xsl:text>
											<tr>
												<td bgColor="#CCCCFF">
													<span style="font-size:large; font-weight:bold"><xsl:copy-of select="$methoddetail-label"/></span>
												</td>
											</tr>
										<xsl:text disable-output-escaping="yes">&lt;/table&gt;</xsl:text>
									</xsl:if>
										<a>
											<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
											<span style="font-size:medium">
												<xsl:value-of select="@name"/>
											</span>
										</a>
									<br/>
									<br/>
									<xsl:for-each select="signature">
										<pre>
											<xsl:value-of select="@visibility"/> 
											<xsl:if test="@abstract=&quot;true&quot;">
												<xsl:text> abstract</xsl:text>
											</xsl:if>
											<xsl:if test="@static=&quot;true&quot;">
												<xsl:text> static</xsl:text>
											</xsl:if>
											<xsl:if test="@final=&quot;true&quot;">
												<xsl:text> final</xsl:text>
											</xsl:if>
											<xsl:if test="@volatile=&quot;true&quot;">
												<xsl:text> volatile</xsl:text>
											</xsl:if>
											<xsl:if test="@native=&quot;true&quot;">
												<xsl:text> native</xsl:text>
											</xsl:if>
											<xsl:if test="@synchronized=&quot;true&quot;">
												<xsl:text> synchronized</xsl:text>
											</xsl:if>
											<xsl:if test="@strictfp=&quot;true&quot;">
												<xsl:text> strictfp</xsl:text>
											</xsl:if>
											<xsl:if test="@synthetic=&quot;true&quot;">
												<xsl:text> synthetic</xsl:text>
											</xsl:if>
											<xsl:if test="@transient=&quot;true&quot;">
												<xsl:text> transient</xsl:text>
											</xsl:if>
											<xsl:for-each select="type">
												<xsl:text> </xsl:text>
												<xsl:apply-templates/>												
											</xsl:for-each>
											<xsl:text> </xsl:text>
											<b><xsl:value-of select="@name"/></b>
											<xsl:text>(</xsl:text>
												<xsl:for-each select="parameter">
													<xsl:for-each select="type">
														<xsl:apply-templates/>
													</xsl:for-each>
													<xsl:text disable-output-escaping="yes"> </xsl:text><xsl:value-of select="@name" />															
													<xsl:if test="position()!=last()">
														<xsl:text disable-output-escaping="yes">, </xsl:text>
													</xsl:if>
												</xsl:for-each>
											<xsl:text>)</xsl:text>
										</pre>
									</xsl:for-each>
									<br/>
									<dl>
									<xsl:for-each select="javadoc">
										<xsl:for-each select="description">
											<xsl:copy-of select="*"/>
										</xsl:for-each>
									</xsl:for-each>
									<br/>
									<xsl:for-each select="diagram">
										<xsl:for-each select="img">
											<img border="0">
												<xsl:attribute name="src"><xsl:value-of select="@location" /></xsl:attribute>
											</img>
										</xsl:for-each>
										<br/>
										<xsl:for-each select="description">
											<xsl:copy-of select="*"/>
										</xsl:for-each>
										<br/>
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
										<br/>
									</xsl:for-each>
									</dl>
									<hr/>
								</xsl:for-each>
								<br/>
							</xsl:for-each>
				<br/>
         <table bgcolor="#CCCCFF" border="0" cellpadding="1" cellspacing="3" table-layout="fixed" width="100%">
           <xsl:if test="position()=1">
             <xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
           </xsl:if>
           <tr bgColor="#CCCCFF" width="100%">
             <td width="72" align="center">
               <b><a href="overview-summary.html"><xsl:copy-of select="$overview-label"/></a></b>
             </td>
             <td width="72" align="center">
               <b><a>
               <xsl:attribute name="href"><xsl:value-of select="@packageid" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
               <xsl:copy-of select="$package-label"/>
               </a></b>
             </td>
             <td bgColor="#00008B" width="72" align="center">
               <span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$class-label"/></span>
             </td>
             <td width="72" align="center">
               <xsl:copy-of select="$diagram-label"/>
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
</xsl:for-each>
</xsl:template>

<!--====================================================-->
<!-- Create each %diagram%.html  (mode = "classFrame")  -->
<!--====================================================-->
<xsl:template match="*" mode="diagramFrame">
  <xsl:for-each select="package">
  <xsl:for-each select="diagram">
	<xsl:document href="{concat($base.dir,concat(@id,'.html'))}">
		<html>
			<head>
       <xsl:for-each select="img">
       <xsl:for-each select="map">
       		<link rel="stylesheet" type="text/css" media="screen">
       			<xsl:attribute name="href"><xsl:value-of select="@location"/></xsl:attribute>
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
						<td width="72" align="center">
							<b><a href="overview-summary.html"><xsl:copy-of select="$overview-label"/></a></b>
						</td>
						<td width="72" align="center">
							<b><a>
								<xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
								<xsl:copy-of select="$package-label"/>
							</a></b>
						</td>
						<td width="72" align="center">
							<xsl:copy-of select="$class-label"/>
						</td>
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
					<b><xsl:value-of select="." /></b>
				</xsl:for-each></h1>
				<br/><br/>
				<xsl:for-each select="img">
					<xsl:for-each select="map">
						<div>
							<xsl:attribute name="id"><xsl:value-of select="../../@id" /></xsl:attribute>
							<xsl:for-each select="element">
								<a>
									<xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
									<xsl:attribute name="id"><xsl:value-of select="@id" /></xsl:attribute>
								</a>
							</xsl:for-each>						
						</div>
					</xsl:for-each>						
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
						<td width="72" align="center">
							<b><a href="overview-summary.html"><xsl:copy-of select="$overview-label"/></a></b>
						</td>
						<td width="72" align="center">
							<b><a>
								<xsl:attribute name="href"><xsl:value-of select="../@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
								<xsl:copy-of select="$package-label"/>
							</a></b>
						</td>
						<td width="72" align="center">
							<xsl:copy-of select="$class-label"/>
						</td>
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
</xsl:for-each>
  <xsl:for-each select="diagram">
	<xsl:document href="{concat($base.dir,concat(@id,'.html'))}">
		<html>
			<head/>
			<body>
				<table bgcolor="#CCCCFF" border="0" cellpadding="1" cellspacing="3" table-layout="fixed" width="100%">
					<xsl:if test="position()=1">
						<xsl:text disable-output-escaping="yes">&lt;tbody&gt;</xsl:text>
					</xsl:if>
					<tr bgColor="#CCCCFF" width="100%">
						<td width="72" align="center">
							<b><a href="overview-summary.html"><xsl:copy-of select="$overview-label"/></a></b>
						</td>
						<td width="72" align="center">
							<xsl:copy-of select="$package-label"/>
						</td>
						<td width="72" align="center">
							<xsl:copy-of select="$class-label"/>
						</td>
						<td bgColor="#00008B" width="72" align="center">
							<span style="color:#FFFFFF; font-weight:bold"><xsl:copy-of select="$diagram-label"/></span>
						</td>
						<td>&#160;</td>
					</tr>
					<xsl:if test="position()=last()">
						<xsl:text disable-output-escaping="yes">&lt;/tbody&gt;</xsl:text>
					</xsl:if>
				</table>
				<hr /><h1>

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
						<td width="72" align="center">
							<b><a href="overview-summary.html"><xsl:copy-of select="$overview-label"/></a></b>
						</td>
						<td width="72" align="center">
							<xsl:copy-of select="$package-label"/>
						</td>
						<td width="72" align="center">
							<xsl:copy-of select="$class-label"/>
						</td>
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
		<xsl:attribute name="href"><xsl:value-of select="@id" /><xsl:text disable-output-escaping="yes">.html</xsl:text></xsl:attribute>
		<xsl:apply-templates/>
	</a>
</xsl:template>

</xsl:stylesheet>