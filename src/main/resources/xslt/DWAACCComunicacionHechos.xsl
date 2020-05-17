<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xalan="http://xml.apache.org/xslt">
	<xsl:template match="/">
		<xsl:apply-templates select="DOCUMENTOTYPE" />
	</xsl:template>
	<!-- ================================================================================ 
		INCLUIMOS LA CABECERA, PIE, FIRMA Y DEFINICION DE LA PAGINA 
		 ================================================================================ -->
	<!-- <xsl:include href="DWIncludeCartas.xsl" />
	<xsl:include href="DWIncludeFirma.xsl" /> -->
	<!-- ================================================================================ 
		CUERPO DEL DOCUMENTO 
		 ================================================================================ -->
	<xsl:template match="CUERPO">
		<!-- CABECERA -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>ANEXO II</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>COMUNICACI�N DE HECHOS AL AMPARO DEL PLAN ANUAL DE OBJETIVOS SEPE-ITSS</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>Referencia comunicaci�n:</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>XXXXXXXX</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 1.- DATOS DE IDENTIFICACI�N DEL TRABAJADOR -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>1.- DATOS DE IDENTIFICACI�N DEL TRABAJADOR</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							APELLIDOS Y NOMBRE: [JUAN ESPA�OL ESPA�OL]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							N.I.F. / N.I.E.: [00000000 T]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							DOMICILIO: [CL Nuestra Se�ora de Gracia 2, 3� A - 28044 Madrid]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							OFICINA DE EMPLEO: [Madrid]
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 2.- DATOS DE IDENTIFICACI�N DEL EMPRESARIO -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>2.- DATOS DE IDENTIFICACI�N DEL EMPRESARIO</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							NOMBRE O RAZ�N SOCIAL: [Caf� Espa�ol, S.L.U.]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							DOMICILIO: [CL Ciudad Rodrigo, 1, Madrid]
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 3.- DATOS SOBRE LA PROTECCI�N POR DESEMPLEO -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>3.- DATOS SOBRE LA PROTECCI�N POR DESEMPLEO</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							TIPO DE PRESTACI�N: [Prestaci�n por Desempleo]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							FECHA DE INICIO: [23/08/2018]
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 4.- POSIBLE CONNIVENCIA: An�lisis de la situaci�n legal de desempleo -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>4.- POSIBLE CONNIVENCIA: An�lisis de la situaci�n legal de desempleo</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg> 
							Despido tras la IT, maternidad o denegaci�n de una invalidez.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Cese en varios contratos temporales con una misma empresa y acceso reiterado a prestaciones en periodos de vacaciones.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Indicios de falsedad en los datos declarados en las solicitudes.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Despido individual de un mayor de 60 a�os con indicios suficientes de que accede a la prestaci�n como puente para la pensi�n.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Otras situaciones posibles: investigaci�n de las denuncias de particulares, anulaci�n del alta el mismo d�a que contratan
							cuando el alta coincide con el fin de semana.
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 5.- IRREGULARIDADES EN LA COTIZACI�N, CONTRATACI�N Y ENCUADRAMIENTO -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>5.- IRREGULARIDADES EN LA COTIZACI�N, CONTRATACI�N Y ENCUADRAMIENTO</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Supuestos de cotizaci�n irregular. Falta de alta o periodos de cotizaci�n o incrementos injustificados de bases de cotizaci�n
							en el periodo de c�lculo de la base reguladora.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Relaci�n de parentesco entre empresario y trabajador cuando exist�a connivencia.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Posesi�n de participaciones / o realizaci�n de funciones de direcci�n y gerencia.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Realizaci�n de trabajos de corta duraci�n tras ceses en otra relaci�n laboral sin situaci�n legal de desempleo.
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 6.- INCUMPLIMIENTOS FORMALES -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>6.- INCUMPLIMIENTOS FORMALES</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Trabajadores: irregularidades en la documentaci�n necesaria para el reconocimiento de la prestaci�n
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Empresarios: incumplimientos reiterados del env�o del certificado de empresa de forma telem�tica.
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 7.- INFORME: (DESCRIPCI�N DE LOS HECHOS DETECTADOS, PRUEBAS OBTENIDAS, FECHAS EN QUE SE PRODUCEN Y DETECTAN LOS HECHOS, OTROS DATOS, ETC...) -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>7.- INFORME: (DESCRIPCI�N DE LOS HECHOS DETECTADOS, PRUEBAS OBTENIDAS, FECHAS EN QUE SE PRODUCEN Y DETECTAN LOS HECHOS, OTROS DATOS, ETC...)</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							Al proceder al reconocimiento de la presataci�n por desempleo de [Nombre y apellidos] hemos observado que: 
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							Trabaja en la empresa [Nombre de la empresa] propiedad de su padre. Como tiene poder notarial el mismo 
							firma la carta de despido como empresario y como trabajador. El motivo de despido es que el ayuntamiento
							no concede la licencia de apertura.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							Por todo lo expuesto se solicita actuaci�n inspectora que detecte si existe alguna anomal�a que repercuta
							en la percepci�n de su prestaci�n por desempleo.
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 8.- RELACI�N DE LA DOCUMENTACI�N ADJUNTA -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>8.- RELACI�N DE LA DOCUMENTACI�N ADJUNTA</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">1. Certificado de empresa</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">2. Carta de despido</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">3. Poder notarial</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
	</xsl:template>
</xsl:stylesheet>