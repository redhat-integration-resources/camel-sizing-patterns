<xsl:stylesheet version="3.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xf="http://www.w3.org/2005/xpath-functions"
xpath-default-namespace="http://www.w3.org/2005/xpath-functions"
exclude-result-prefixes="xf">

	<!-- Helper template to convert JSON into XML
		 as per: https://www.w3.org/TR/xslt-30/#json-to-xml-mapping -->
	<xsl:import href="common/j2x.xsl"/>

	<!-- The actual data mapping -->
	<xsl:template match="map">
      <s1:SubscriberRequest xmlns:s1="http://www.example.org/s1/">
         <Id><xsl:value-of select="/map/string[@key='id']"/></Id>
      </s1:SubscriberRequest>
	</xsl:template>
			
</xsl:stylesheet>