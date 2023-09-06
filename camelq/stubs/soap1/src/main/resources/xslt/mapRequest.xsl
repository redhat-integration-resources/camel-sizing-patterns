<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output indent="yes"/>

	<!-- Parameter IN containing SOAP Headers -->
	<xsl:param name="soapHeaders"/>

	<!-- Transformation definition -->
	<xsl:template match="/">

		<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:s1="http://www.example.org/s1/">
<!-- 		   <soapenv:Header>
		      <s2:S2Header>
		         <h1><xsl:value-of select="$soapHeaders//header1"/></h1>
		         <h2><xsl:value-of select="$soapHeaders//header2"/></h2>
		      </s2:S2Header>
		   </soapenv:Header> -->
		   <soapenv:Body>
<!-- 		      <s2:S2Request>
		         <in1><xsl:value-of select="//param1"/></in1>
		         <in2><xsl:value-of select="//param2"/></in2>
		      </s2:S2Request> -->
		      <s1:SubscriberRequest>
		         <Id><xsl:value-of select="concat(//param1,'-mapped')"/></Id>
		         <!-- <param2><xsl:value-of select="//param2"/></param2> -->
		      </s1:SubscriberRequest>

		   </soapenv:Body>
		</soapenv:Envelope>

	</xsl:template>
</xsl:stylesheet>
