<xsl:stylesheet version="3.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xf="http://www.w3.org/2005/xpath-functions"
xmlns:s1="http://www.example.org/s1/"
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

      <s1:SubscriberRequest>
         <Id><xsl:value-of select="/map/string[@key='id']"/></Id>
      </s1:SubscriberRequest>

	</xsl:template>
			
</xsl:stylesheet>