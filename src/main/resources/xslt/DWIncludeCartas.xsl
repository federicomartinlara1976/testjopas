<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:param name="codigoCSV" >C.S.V.:DL3Z2DEY5J5A4DPKG67PKAREXQROWWJVQ</xsl:param> 
	<xsl:variable name="lcletters">abcdefghijklmnopqrstuvwxyzáéíóúàèìòù</xsl:variable>
	<xsl:variable name="ucletters">ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙ</xsl:variable>
	<!--
================================================================================
        DEFINICION GENERAL DE LA PAGINA
================================================================================
-->
	<xsl:template match="DOCUMENTOTYPE">
		<fo:root font-size="9pt" letter-spacing="0.01pt">
			<fo:layout-master-set>
				<!-- Para la primera pagina -->
				<fo:simple-page-master master-name="primera" page-height="11.69in" page-width="8.27in" margin-left="1.6cm" margin-right="2cm" margin-bottom="0.2cm" margin-top="1cm">	
					<fo:region-body margin-top="9.36cm" margin-bottom="0.7cm" margin-left="0.4cm"/>
					<fo:region-before extent="9.35cm" region-name="region-before-primera"/>
					<fo:region-after extent="0.5cm"/>
					<fo:region-start extent="0.4cm" region-name="lateral_izq"/>
				</fo:simple-page-master>
				<!-- Para el resto de paginas -->
				<fo:simple-page-master master-name="sucesivas" page-height="11.69in" page-width="8.27in" margin-left="2cm" margin-right="2cm" margin-bottom="0.2cm">		
					<fo:region-body margin-top="1cm" margin-bottom="0.7cm"/>
					<fo:region-before extent="1cm" region-name="region-before-sucesivas"/>
					<fo:region-after extent="0.5cm"/>
				</fo:simple-page-master>
				<!-- Definicion de todas las paginas -->
				<fo:page-sequence-master master-name="paginas">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference master-reference="primera" page-position="first"/>
						<fo:conditional-page-master-reference master-reference="sucesivas" page-position="rest"/>
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="paginas">
				<fo:static-content flow-name="region-before-primera">
					<fo:block>
						<xsl:apply-templates select="CABECERA"/>
					</fo:block>
				</fo:static-content>
				<fo:static-content flow-name="region-before-sucesivas">
					<fo:block/>
				</fo:static-content>
				<!-- Pie de pagina -->
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<xsl:apply-templates select="PIE"/>
					</fo:block>
				</fo:static-content>
				<!-- LATERAL IZQUIERDO: CSV-->
				<fo:static-content flow-name="lateral_izq" width="0.5cm">
					<fo:block-container reference-orientation="90" inline-progression-dimension="15cm" margin-top="11cm" margin-left="0.14cm">
						<fo:block font-size="6pt">
							<fo:inline>
								<xsl:value-of select="$codigoCSV" /> 
								<!--C.S.V.:DL3Z2DEY5J5A4DPKG67PKAREXQROWWJVQ-->
							</fo:inline>
						</fo:block>
					  </fo:block-container>	
				</fo:static-content>
				<!-- Cuerpo del documento -->
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:apply-templates select="CUERPO"/>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<!-- CABECERA DEL DOCUMENTO-->
	<xsl:template match="CABECERA">
		<!-- TABLA CON EL LOGO DE LA CABECERA-->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-column column-width="50%"/>
			<fo:table-column column-width="50%"/>
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block>
							<fo:external-graphic content-width="8.3cm" content-height="2cm">
								<xsl:attribute name="src">url("<xsl:value-of select="../RUTA/RUTA_IMG"/>DWlogos_insti_color.jpg")</xsl:attribute>
							</fo:external-graphic>
						</fo:block>
					</fo:table-cell>
					<fo:table-cell>
						<fo:block text-align="right">
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		<!--DIBUJO CODIGO DE BARRAS-->
		<fo:table width="100%" table-layout="fixed" font-size="8pt" color="black" text-align="center" space-before.optimum="1.4cm">
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-column column-width="3.5cm"/>
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block/>
					</fo:table-cell>
					<fo:table-cell display-align="center" text-align="center">
						<fo:block/>
					</fo:table-cell>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block font-family="Codigo Barras CYT" font-size="24pt" text-align="left" space-before.optimum="0.60cm">
							<xsl:value-of select="../CABECERA/DESTINATARIO/CODIGOBARRAS"/>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		<!--NUMERO CODIGO DE BARRAS-->
		<fo:table width="100%" table-layout="fixed" font-size="8pt" color="black" text-align="center">
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-column column-width="3.5cm"/>
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block>
							<!--Celda vacía-->
						</fo:block>
					</fo:table-cell>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block>
							<!--Celda vacía-->
						</fo:block>
					</fo:table-cell>
					<fo:table-cell display-align="center">
						<fo:block font-size="8pt" text-align="center" space-before.optimum="0.0cm">
							<xsl:value-of select="substring(../CABECERA/DESTINATARIO/CODIGOBARRAS, 2, 23)"/>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block>
							<!--Celda vacía-->
						</fo:block>
					</fo:table-cell>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block>
							<!--Celda vacía-->
						</fo:block>
					</fo:table-cell>
					<fo:table-cell display-align="center">
						<fo:block text-align="center" font-weight="bold" font-style="italic" font-size="9pt">NOTIFICACIÓN</fo:block>
					</fo:table-cell>
				</fo:table-row>	
			</fo:table-body>
		</fo:table>
		<!-- DIRECCION PROVINCIAL-->	
		<fo:table width="100%" table-layout="fixed" font-size="7pt" color="black" text-align="center">
			<fo:table-column column-width="7cm"/>
			<fo:table-column column-width="2.5cm"/>
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block font-weight="bold">DIRECCIÓN PROVINCIAL</fo:block>
						<fo:block space-before.optimum="0.1cm">
							<fo:inline>
								<xsl:value-of select="../CABECERA/REMITENTE/DIRECCIONREMITENTE"/>
							</fo:inline>
						</fo:block>
						<fo:block>
							<fo:inline>
								<xsl:value-of select="../CABECERA/REMITENTE/POBLACIONREMITENTE"/> - 
					                          <xsl:value-of select="../CABECERA/REMITENTE/CODPOSTALREMITENTE"/>
							</fo:inline>
						</fo:block>
						<!-- -->
						<fo:block space-before.optimum="0.2cm" font-weight="bold">
							<fo:inline>
								<xsl:value-of select="../CABECERA/REMITENTE/OFIEMPLEOREMITENTE"/>
									 / <xsl:value-of select="../CABECERA/REMITENTE/REFERENCIA2REMITENTE"/>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
					<fo:table-cell>
						<fo:block>
						</fo:block>
					</fo:table-cell>
					<fo:table-cell>
						<fo:block text-align="left">
							<fo:inline>
								<xsl:value-of select="../CABECERA/DESTINATARIO/NOMBRECOMPLETO"/>
							</fo:inline>
						</fo:block>
						<fo:block text-align="left">
							<fo:inline>
								<xsl:value-of select="translate(../CABECERA/DESTINATARIO/NOMBREVIACOMPLETO,$lcletters,$ucletters)"/>
							</fo:inline>
						</fo:block>
						<fo:block text-align="left">
							<fo:inline>
								<xsl:value-of select="translate(../CABECERA/DESTINATARIO/ESCALERALETRA,$lcletters,$ucletters)"/>
							</fo:inline>
						</fo:block>
						<fo:block text-align="left">
							<fo:inline>
								 <xsl:value-of select="translate(../CABECERA/DESTINATARIO/CODIGOPROVINCIA,$lcletters,$ucletters)"/> 
							</fo:inline>
						</fo:block>
						<fo:block text-align="left">
							<fo:inline>
								<xsl:value-of select="translate(../CABECERA/DESTINATARIO/LOCALIDAD,$lcletters,$ucletters)"/>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		<!-- PREVISUALIZAR-->
		<fo:table width="100%" table-layout="fixed" font-size="8pt" color="black" text-align="center" space-before.optimum="0.8cm">
			<fo:table-column column-width="4.5cm"/>
			<fo:table-column column-width="3.7cm"/>
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block/>
					</fo:table-cell>
					<fo:table-cell display-align="center" text-align="center">
						<fo:block/>
					</fo:table-cell>
					<fo:table-cell display-align="center" text-align="left">
						<fo:block>
							<xsl:if test="//PREVISUALIZAR='true'">
								<fo:inline color="red" font-size="20pt" font-weight="bold">&lt;PREVISUALIZACIÓN&gt;</fo:inline>
							</xsl:if>
							<xsl:if test="//PREVISUALIZAR='false'">
								<fo:inline color="red" font-size="20pt" font-weight="bold">&#160;</fo:inline>
							</xsl:if>
							<fo:block>&#160;</fo:block>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		<fo:block space-before.optimum="1cm" font-weight="bold">
			<xsl:for-each select="../CABECERA/DESTINATARIO">				
				<xsl:choose>
					<xsl:when test="substring(IPF,1,1)='D'">
					DNI  
					</xsl:when>
					<xsl:when test="substring(IPF,1,1)='E'">
					NIE  
					</xsl:when>
					<xsl:otherwise>
					CIF 
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
			<xsl:value-of select="substring(../CABECERA/DESTINATARIO/IPF,2,11)"/>
		</fo:block>
	</xsl:template>
<!--  PIE DEL DOCUMENTO-->
	<xsl:template match="PIE">
		<fo:table table-layout="fixed" width="100%">
			<fo:table-column column-width="5cm"/>
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-column column-width="5cm"/>
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block></fo:block>
					</fo:table-cell>
					<fo:table-cell border="solid">
						<fo:block font-size="8pt" text-align="center">
							Para más información www.sepe.es ó 901119999
						</fo:block>		
					</fo:table-cell>
					<fo:table-cell>
						<fo:block></fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>	
	</xsl:template>
</xsl:stylesheet>
