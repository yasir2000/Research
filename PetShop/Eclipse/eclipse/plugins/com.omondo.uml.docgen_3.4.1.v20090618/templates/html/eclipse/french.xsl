<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:variable name="overview-label" select="'Vue G&#233;n&#233;rale'"/>
<xsl:variable name="package-label" select="'Paquetage'"/>
<xsl:variable name="packages-label" select="'Paquetages'"/>
<xsl:variable name="class-label" select="'Classe'"/>
<xsl:variable name="classes-label" select="'Classes'"/>
<xsl:variable name="diagram-label" select="'Diagramme'"/>
<xsl:variable name="diagrams-label" select="'Diagrammes'"/>
<xsl:variable name="all-label" select="'Tous'"/>
<xsl:variable name="alldiagrams-label" select="'Tous les Diagrammes'"/>
<xsl:variable name="allclasses-label" select="'Toutes les Classes'"/>
<xsl:variable name="interface-label" select="'Interface'"/>
<xsl:variable name="interfaces-label" select="'Interfaces'"/>
<xsl:variable name="exception-label" select="'Exception'"/>
<xsl:variable name="exceptions-label" select="'Exceptions'"/>
<xsl:variable name="error-label" select="'Erreur'"/>
<xsl:variable name="errors-label" select="'Erreurs'"/>
<xsl:variable name="robustnessdiagrams-label" select="'Diagrammes de Robustesse'"/>
<xsl:variable name="usecasediagrams-label" select="'Diagrammes d&#180;&#201;tude de cas'"/>
<xsl:variable name="collaborationdiagrams-label" select="'Diagrammes de Collaboration'"/>
<xsl:variable name="activitydiagrams-label" select="'Diagrammes d&#180;Activit&#233;'"/>
<xsl:variable name="classdiagrams-label" select="'Diagrammes de Classe'"/>
<xsl:variable name="statediagrams-label" select="'Diagrammes d&#180;&#201;tat'"/>
<xsl:variable name="sequencediagrams-label" select="'Diagrammes de S&#233;quence'"/>
<xsl:variable name="objectdiagrams-label" select="'Diagrammes d&#180;Objet'"/>
<xsl:variable name="componentdiagrams-label" select="'Diagrammes de Composant'"/>
<xsl:variable name="deploymentdiagrams-label" select="'Diagrammes de D&#233;ploiement'"/>
<xsl:variable name="robustnessdiagramsummary-label" select="'Sommaire des Diagrammes de Robustesse'"/>
<xsl:variable name="usecasediagramsummary-label" select="'Sommaire des Diagrammes d&#180;&#201;tude de Cas'"/>
<xsl:variable name="collaborationdiagramsummary-label" select="'Sommaire des Diagrammes de Collaboration'"/>
<xsl:variable name="activitydiagramsummary-label" select="'Sommaire des Diagrammes d&#180;Activit&#233;'"/>
<xsl:variable name="classdiagramsummary-label" select="'Sommaire des Diagrammes de Classe'"/>
<xsl:variable name="statediagramsummary-label" select="'Sommaire des Diagrammes d&#180;&#201;tat'"/>
<xsl:variable name="sequencediagramsummary-label" select="'Sommaire des Diagrammes de S&#233;quence'"/>
<xsl:variable name="objectdiagramsummary-label" select="'Sommaire des Diagrammes d&#180;Objet'"/>
<xsl:variable name="componentdiagramsummary-label" select="'Sommaire des Diagrammes de Composant'"/>
<xsl:variable name="deploymentdiagramsummary-label" select="'Sommaire des Diagrammes de D&#233;ploiement'"/>
<xsl:variable name="interfacesummary-label" select="'Sommaire des Interfaces'"/>
<xsl:variable name="classsummary-label" select="'Sommaire des Classes'"/>
<xsl:variable name="exceptionsummary-label" select="'Sommaire des Exceptions'"/>
<xsl:variable name="errorsummary-label" select="'Sommaire des Erreurs'"/>
<xsl:variable name="description-label" select="'Description'"/>
<xsl:variable name="links-label" select="'Liens'"/>
<xsl:variable name="fieldsummary-label" select="'Sommaire des Attributs'"/>
<xsl:variable name="constructorsummary-label" select="'Sommaire des Constructeurs'"/>
<xsl:variable name="methodsummary-label" select="'Sommaire des M&#233;thodes'"/>
<xsl:variable name="fielddetail-label" select="'D&#233;tails des Attributs'"/>
<xsl:variable name="constructordetail-label" select="'D&#233;tails des Constructeurs'"/>
<xsl:variable name="methoddetail-label" select="'D&#233;tails des M&#233;thodes'"/>
<xsl:variable name="usecase-label" select="'&#201;tude de Cas'"/>
<xsl:variable name="precondition-label" select="'Pr&#233;condition'"/>
<xsl:variable name="postcondition-label" select="'Postcondition'"/>
<xsl:variable name="normalflow-label" select="'Flux Normal'"/>
<xsl:variable name="alternativeflow-label" select="'Flux Alternatif'"/>
<xsl:variable name="system-label" select="'Syst&#232;me'"/>

</xsl:stylesheet>