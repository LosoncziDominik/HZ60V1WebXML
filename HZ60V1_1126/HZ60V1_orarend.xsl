<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
    version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes" encoding="UTF-8"/>

    <xsl:template match="/">
        <html>
        <head>
            <title>HZ60V1 Órarend</title>
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
            <h2>HZ60V1 Órarend – for-each, value-of</h2>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Típus</th>
                    <th>Tárgy</th>
                    <th>Nap</th>
                    <th>Tól</th>
                    <th>Ig</th>
                    <th>Helyszín</th>
                    <th>Oktató</th>
                    <th>Szak</th>
                </tr>

                <!-- végigmegyünk minden ora elemeken -->
                <xsl:for-each select="LD_orarend/ora">
                    <tr>
                        <td><xsl:value-of select="@id"/></td>
                        <td><xsl:value-of select="@tipus"/></td>
                        <td><xsl:value-of select="targy"/></td>
                        <td><xsl:value-of select="idopont/nap"/></td>
                        <td><xsl:value-of select="idopont/tol"/></td>
                        <td><xsl:value-of select="idopont/ig"/></td>
                        <td><xsl:value-of select="helyszin"/></td>
                        <td><xsl:value-of select="oktato"/></td>
                        <td><xsl:value-of select="szak"/></td>
                    </tr>
                </xsl:for-each>
            </table>
        </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
