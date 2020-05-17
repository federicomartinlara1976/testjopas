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
								<xsl:text>COMUNICACIÓN DE HECHOS AL AMPARO DEL PLAN ANUAL DE OBJETIVOS SEPE-ITSS</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>Referencia comunicación:</xsl:text>
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
		
		<!-- 1.- DATOS DE IDENTIFICACIÓN DEL TRABAJADOR -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>1.- DATOS DE IDENTIFICACIÓN DEL TRABAJADOR</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							APELLIDOS Y NOMBRE: [JUAN ESPAÑOL ESPAÑOL]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							N.I.F. / N.I.E.: [00000000 T]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							DOMICILIO: [CL Nuestra Señora de Gracia 2, 3º A - 28044 Madrid]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							OFICINA DE EMPLEO: [Madrid]
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 2.- DATOS DE IDENTIFICACIÓN DEL EMPRESARIO -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>2.- DATOS DE IDENTIFICACIÓN DEL EMPRESARIO</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							NOMBRE O RAZÓN SOCIAL: [Café Español, S.L.U.]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							DOMICILIO: [CL Ciudad Rodrigo, 1, Madrid]
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 3.- DATOS SOBRE LA PROTECCIÓN POR DESEMPLEO -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>3.- DATOS SOBRE LA PROTECCIÓN POR DESEMPLEO</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							TIPO DE PRESTACIÓN: [Prestación por Desempleo]
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							FECHA DE INICIO: [23/08/2018]
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 4.- POSIBLE CONNIVENCIA: Análisis de la situación legal de desempleo -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>4.- POSIBLE CONNIVENCIA: Análisis de la situación legal de desempleo</xsl:text>
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
							Despido tras la IT, maternidad o denegación de una invalidez.
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
							Despido individual de un mayor de 60 años con indicios suficientes de que accede a la prestación como puente para la pensión.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Otras situaciones posibles: investigación de las denuncias de particulares, anulación del alta el mismo día que contratan
							cuando el alta coincide con el fin de semana.
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 5.- IRREGULARIDADES EN LA COTIZACIÓN, CONTRATACIÓN Y ENCUADRAMIENTO -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>5.- IRREGULARIDADES EN LA COTIZACIÓN, CONTRATACIÓN Y ENCUADRAMIENTO</xsl:text>
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
							Supuestos de cotización irregular. Falta de alta o periodos de cotización o incrementos injustificados de bases de cotización
							en el periodo de cálculo de la base reguladora.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Relación de parentesco entre empresario y trabajador cuando existía connivencia.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Posesión de participaciones / o realización de funciones de dirección y gerencia.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Realización de trabajos de corta duración tras ceses en otra relación laboral sin situación legal de desempleo.
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
							Trabajadores: irregularidades en la documentación necesaria para el reconocimiento de la prestación
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<svg width="5" height="5">
            					<rect width="5" height="5" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
        					</svg>
							Empresarios: incumplimientos reiterados del envío del certificado de empresa de forma telemática.
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 7.- INFORME: (DESCRIPCIÓN DE LOS HECHOS DETECTADOS, PRUEBAS OBTENIDAS, FECHAS EN QUE SE PRODUCEN Y DETECTAN LOS HECHOS, OTROS DATOS, ETC...) -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>7.- INFORME: (DESCRIPCIÓN DE LOS HECHOS DETECTADOS, PRUEBAS OBTENIDAS, FECHAS EN QUE SE PRODUCEN Y DETECTAN LOS HECHOS, OTROS DATOS, ETC...)</xsl:text>
							</fo:inline>
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							Al proceder al reconocimiento de la presatación por desempleo de [Nombre y apellidos] hemos observado que: 
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							Trabaja en la empresa [Nombre de la empresa] propiedad de su padre. Como tiene poder notarial el mismo 
							firma la carta de despido como empresario y como trabajador. El motivo de despido es que el ayuntamiento
							no concede la licencia de apertura.
						</fo:block>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							Por todo lo expuesto se solicita actuación inspectora que detecte si existe alguna anomalía que repercuta
							en la percepción de su prestación por desempleo.
						</fo:block>
					</fo:table-cell>
				</fo:table-row>
			</fo:table-body>
		</fo:table>
		
		<!-- 8.- RELACIÓN DE LA DOCUMENTACIÓN ADJUNTA -->
		<fo:table table-layout="fixed" width="100%">
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1pt" padding-bottom="1pt">
							<fo:inline font-size="10pt" font-weight="bold">
								<xsl:text>8.- RELACIÓN DE LA DOCUMENTACIÓN ADJUNTA</xsl:text>
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