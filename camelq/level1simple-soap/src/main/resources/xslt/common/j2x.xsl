<xsl:stylesheet version="3.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xf="http://www.w3.org/2005/xpath-functions"
exclude-result-prefixes="xf">

	<xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
	<xsl:param name="json"/>

	<!-- JSON INPUT (to XML) 
		 as per: https://www.w3.org/TR/xslt-30/#json-to-xml-mapping -->
	<xsl:template match="data">
		<!-- <debug><xsl:copy-of select="json-to-xml($json)"/></debug> -->
		<xsl:apply-templates select="json-to-xml($json)" />
	</xsl:template>

</xsl:stylesheet>