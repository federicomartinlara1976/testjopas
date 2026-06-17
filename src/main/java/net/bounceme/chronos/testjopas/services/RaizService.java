package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.dto.RaizDTO;

@Service
@Slf4j
public class RaizService {

	@Value("${application.paths.raiz}")
	private String pathRaiz;

	@Autowired
	@Qualifier("javaOctaveService")
	private transient CalcService calcService;

	@Getter
	private BigDecimal solucion;

	@Getter
	private Integer iteraciones;

	@Getter
	private BigDecimal[] valores;

	@PostConstruct
	public void initialize() {
		try {
			calcService.clearEnvironment();
			calcService.resetPath();
			calcService.addPath(pathRaiz);
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
		}
	}

	@SneakyThrows
	public void calcular(RaizDTO raizDTO, String opcion) {

		calcService.passVariable("puntoInicial", raizDTO.getPuntoInicial());
		calcService.passVariable("tolerancia", raizDTO.getTolerancia());
		calcService.passVariable("iteraciones", raizDTO.getIteraciones());

		if ("secante".equals(opcion)) {
			calcService.passVariable("primeraAproximacion", raizDTO.getPrimeraAproximacion());
		}

		// Ejecuta el comando
		String cmd = ("newton".equals(opcion)) ? "[x,sol,ni,error]=newtonRaphson(puntoInicial, tolerancia, iteraciones)"
				: "[x,sol,ni,error]=secante(puntoInicial, primeraAproximacion, tolerancia, iteraciones)";

		calcService.execute(cmd);

		// Las variables de salida son las que están definidas entre [] en el comando
		String error = calcService.getString("error");
		if (StringUtils.isNotBlank(error)) {
			throw new Exception(error);
		} else {
			solucion = calcService.getScalar("sol");
			iteraciones = calcService.getIntScalar("ni");
			valores = calcService.getArray("x");
		}
	}
}
