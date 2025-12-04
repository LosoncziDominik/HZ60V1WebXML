<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  
  <xsl:output method="html" indent="yes"/>
  
  <xsl:template match="/">
    <html><body>
        <h2>30000 Ft-nál drágább autók száma</h2>
        
        <xsl:variable name="db" select="count(autok/auto[ar &gt; 30000])"/>
        <p>Darabszám: <b><xsl:value-of select="$db"/></b></p>
        
      </body></html>
  </xsl:template>
  
</xsl:stylesheet>
