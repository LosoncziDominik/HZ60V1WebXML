<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  
    version="1.0"  
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Hallgatók adatai</title>
                <style>
                    table, th, td {
                    border: 1px solid black;
                    border-collapse: collapse;
                    padding: 4px;
                    }
                    th { background-color: #dddddd; }
                </style>
            </head>
            
            <body>
                <h2>Hallgatók adatai - for-each, value-of</h2>
                
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Vezetéknév</th>
                        <th>Keresztnév</th>
                        <th>Becenév</th>
                        <th>Kor</th>
                        <th>Ösztöndíj</th>
                    </tr>
                    
                    <xsl:for-each select="class/student">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="vezeteknev"/></td>
                            <td><xsl:value-of select="keresztnev"/></td>
                            <td><xsl:value-of select="becenev"/></td>
                            <td><xsl:value-of select="kor"/></td>
                            <td><xsl:value-of select="osztondij"/></td>
                        </tr>
                    </xsl:for-each>
                    
                </table>
            </body>
        </html>
    </xsl:template>
    
</xsl:stylesheet>
