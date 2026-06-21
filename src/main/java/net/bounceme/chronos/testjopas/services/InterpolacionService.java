package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;
import net.bounceme.chronos.testjopas.dto.PuntoDTO;
import net.bounceme.chronos.testjopas.util.FileHelper;

@Service
@Slf4j
public class InterpolacionService {

	@Value("${application.paths.interpolacion}")
	private String pathInterpolacion;

	@Autowired
	@Qualifier("javaOctaveService")
	private transient CalcService calcService;

	@Getter
	private BigDecimal sp;
	
	@Getter
	private BigDecimal[][] dd;
	
	@Getter
	private BigDecimal[] y;

	@PostConstruct
	public void initialize() {
		try {
			calcService.clearEnvironment();
			calcService.resetPath();
			calcService.addPath(pathInterpolacion);
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
		}
	}

	@SneakyThrows
	public void calcular(InterpolacionDTO interpolacionDTO, String opcion) {

		calcService.passVariable("puntoInterpolar", interpolacionDTO.getPuntoInterpolar());

		BigDecimal[] puntos = toArrayPuntos(interpolacionDTO);
		calcService.passVariable("x", puntos);
		
		String cmd = StringUtils.EMPTY;
		if ("funcion".equals(opcion)) {
			cmd = "[Y,DD,SP]=interpoladorFuncion(puntoInterpolar, x)";
		}
		
		if ("tabla".equals(opcion)) {
			BigDecimal[] valores = toArrayValores(interpolacionDTO);
			calcService.passVariable("y", valores);
			cmd = "[Y,DD,SP]=interpoladorTablaValores(puntoInterpolar, x, y)";
		}
		
		calcService.execute(cmd);
		
		sp = calcService.getScalar("SP");
		y = calcService.getArray("Y");
		dd = calcService.getMatrix("DD");
	}
	
	@SneakyThrows
	public BigDecimal calcularValor(BigDecimal punto) {
		calcService.passVariable("x", punto);

		String cmd = "y=f(x)";
		calcService.execute(cmd);

		return calcService.getScalar("y");
	}
	
	private BigDecimal[] toArrayPuntos(InterpolacionDTO interpolacionDTO) {
		return Arrays.stream(interpolacionDTO.getPuntos())
	            .limit(interpolacionDTO.getNumeroPuntos())
	            .map(PuntoDTO::getPunto)
	            .toArray(BigDecimal[]::new);
	}
	
	private BigDecimal[] toArrayValores(InterpolacionDTO interpolacionDTO) {
		return Arrays.stream(interpolacionDTO.getPuntos())
	            .limit(interpolacionDTO.getNumeroPuntos())
	            .map(PuntoDTO::getValor)
	            .toArray(BigDecimal[]::new);
	}
	
	public String obtenerFuncion() {
		return FileHelper.leerFichero(pathInterpolacion + "/f.m");
	}
	
	public String obtenerCodigo(String opcion) {
		if ("funcion".equals(opcion)) {
			return FileHelper.leerFichero(pathInterpolacion + "/interpoladorFuncion.m");
		}
		else {
			return FileHelper.leerFichero(pathInterpolacion + "/interpoladorTablaValores.m");
		}
	}
}
