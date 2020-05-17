<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xalan="http://xml.apache.org/xslt">
	<!--
================================================================================
        FIRMA DEL DOCUMENTO
================================================================================
-->
	<xsl:template match="FIRMA">
	<fo:block width="100%"  font-size="8pt">
		<!--lugar y fecha-->
		<fo:block margin-left="8.5cm">
			<fo:inline>
				<xsl:value-of select="../FIRMA/LUGAR"/>, <xsl:value-of select="../FIRMA/DIA"/> de <xsl:value-of select="../FIRMA/MES"/> de <xsl:value-of select="../FIRMA/ANNO"/>
			</fo:inline>
		</fo:block>
		<!--puesto del firmante-->
		<fo:block margin-left="8.5cm"  margin-top="0.01cm">
			<xsl:if test="substring(../FIRMA/URLFIRMA,3,2)!='01' and substring(../FIRMA/URLFIRMA,3,2)!='04'">
				<xsl:if test="substring(../../CABECERA/REMITENTE/REFERENCIA2REMITENTE,5,1)='R'">
						<fo:block font-size="8pt">
								EL DIRECTOR PROVINCIAL
						</fo:block>
								P.S.
				</xsl:if>
			</xsl:if>
			<xsl:value-of select="../FIRMA/CARGO"/> 
		</fo:block>
		<!--firma-->
		<fo:block margin-left="8.8cm" margin-top="0.01cm">
			<fo:external-graphic content-height="35pt" content-width="110pt">
				<xsl:attribute name="src">url("<xsl:value-of select='../../RUTA/RUTA_IMG'/>DWFirmaIRPF<xsl:value-of select='../FIRMA/URLFIRMA'/>.gif")
				</xsl:attribute>
			</fo:external-graphic>
		</fo:block>
		<!--nombre del firmante-->
		<fo:block margin-left=" 8.5cm" margin-top="0.01cm">
			Fdo.: <fo:inline><xsl:value-of select="../FIRMA/FIRMADO"/></fo:inline>
		</fo:block>
	</fo:block>	
	
	</xsl:template>
</xsl:stylesheet>
