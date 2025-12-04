<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
  <xsl:key name="tipusKey" match="auto" use="tipus"/>
  
  <xsl:output method="html" indent="yes"/>
  
  <xsl:template match="/">
    <html><body>
        <h2>Autótípusok és darabszámuk (csökkenő sorrendben)</h2>
        
        <table border="1">
          <tr><th>Típus</th><th>Darabszám</th></tr>
          
          <xsl:for-each select="autok/auto[generate-id() = generate-id(key('tipusKey', tipus)[1])]">
            <xsl:sort select="count(key('tipusKey', tipus))" data-type="number" order="descending"/>
            <tr>
              <td><xsl:value-of select="tipus"/></td>
              <td><xsl:value-of select="count(key('tipusKey', tipus))"/></td>
            </tr>
          </xsl:for-each>
          
        </table>
        
      </body></html>
  </xsl:template>
  
</xsl:stylesheet>
