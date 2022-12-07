<xsl:stylesheet version="3.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xf="http://www.w3.org/2005/xpath-functions"
exclude-result-prefixes="xf">

	<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
	<xsl:param name="json"/>

	<!-- JSON INPUT (to XML) -->
	<xsl:template match="data">
		<!-- <debug><xsl:copy-of select="json-to-xml($json)"/></debug> -->
		<xsl:apply-templates select="json-to-xml($json)" />
	</xsl:template>

	<!-- The actual data mapping -->
	<xsl:template match="map" xpath-default-namespace="http://www.w3.org/2005/xpath-functions">
		<Subscriber>
			<id><xsl:value-of select="/map/string[@key='id']"/></id>
		</Subscriber>
	</xsl:template>
			
</xsl:stylesheet>