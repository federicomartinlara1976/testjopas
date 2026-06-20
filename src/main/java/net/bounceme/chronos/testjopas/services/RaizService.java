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
import net.bounceme.chronos.testjopas.util.FileHelper;

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

		passVariables(raizDTO, opcion);

		// Ejecuta el comando
		String cmd = StringUtils.EMPTY; 
		if ("biseccion".equals(opcion)) {
			cmd = "[sol,error,ni]=biseccion(a, b, tolerancia)";
		}
		else if ("secante".equals(opcion)) {
			cmd = "[x,sol,ni,error]=secante(puntoInicial, primeraAproximacion, tolerancia, iteraciones)";
		}
		else {
			cmd = "[x,sol,ni,error]=newtonRaphson(puntoInicial, tolerancia, iteraciones)";
		}

		calcService.execute(cmd);

		passResultado(opcion);
	}

	private void passVariables(RaizDTO raizDTO, String opcion) {
		calcService.passVariable("tolerancia", raizDTO.getTolerancia());
		
		if ("biseccion".equals(opcion)) {
			calcService.passVariable("a", raizDTO.getA());
			calcService.passVariable("b", raizDTO.getB());
		}
		else if ("secante".equals(opcion)) {
			calcService.passVariable("puntoInicial", raizDTO.getPuntoInicial());
			calcService.passVariable("primeraAproximacion", raizDTO.getPrimeraAproximacion());
			calcService.passVariable("iteraciones", raizDTO.getIteraciones());
		}
		else {
			calcService.passVariable("puntoInicial", raizDTO.getPuntoInicial());
			calcService.passVariable("iteraciones", raizDTO.getIteraciones());
		}
	}
	
	@SneakyThrows
	private void passResultado(String opcion) {
		solucion = calcService.getScalar("sol");
		iteraciones = calcService.getIntScalar("ni");
		
		if ("secante".equals(opcion) || "newton".equals(opcion)) {
			String error = calcService.getString("error");
			if (StringUtils.isNotBlank(error)) {
				throw new Exception(error);
			}
			
			valores = calcService.getArray("x");
		}
	}
	
	public String obtenerFuncion() {
		return FileHelper.leerFichero(pathRaiz + "/f.m");
	}
	
	public String obtenerDerivada() {
		return FileHelper.leerFichero(pathRaiz + "/df.m");
	}
}
