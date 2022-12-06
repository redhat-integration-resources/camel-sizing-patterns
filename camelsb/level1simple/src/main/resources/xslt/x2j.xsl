<xsl:stylesheet version="3.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns="http://www.w3.org/2005/xpath-functions">
<xsl:output method="text" encoding="UTF-8"/>

<xsl:template match="/">
    <!-- CONVERT INPUT TO XML FOR JSON -->
    <xsl:variable name="xml">
        <map>
            <string key="fullName">
                <xsl:value-of select="concat(//Name,' ',//Surname)"/>
            </string>
            <string key="addressLine1">
                <xsl:value-of select="concat(//Number,' ',//Street)"/>
            </string>
            <string key="addressLine2">
                <xsl:value-of select="concat(//City,' ',//PostCode)"/>
            </string>
            <string key="addressLine3">
                <xsl:value-of select="//Country"/>
            </string>
        </map>   
    </xsl:variable>

    <!-- OUTPUT -->
    <xsl:value-of select="xml-to-json($xml)"/>
</xsl:template>

</xsl:stylesheet>