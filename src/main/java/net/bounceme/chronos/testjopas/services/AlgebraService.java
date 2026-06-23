package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.dto.AlgebraDTO;
import net.bounceme.chronos.testjopas.util.FileHelper;

@Service
@Slf4j
public class AlgebraService {

	@Value("${application.paths.algebra}")
	private String pathAlgebra;

	@Autowired
	@Qualifier("javaOctaveService")
	private transient CalcService calcService;

	@Getter
	private BigDecimal[] c;
	
	@Getter
	private BigDecimal[] p;

	@PostConstruct
	public void initialize() {
		try {
			calcService.clearEnvironment();
			calcService.resetPath();
			calcService.addPath(pathAlgebra);
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
		}
	}

	@SneakyThrows
	public void calcular(AlgebraDTO algebraDTO, String opcion) {
		calcService.passVariable("A", algebraDTO.getMatrizCoeficientes());
		calcService.passVariable("b", algebraDTO.getTerminos());
		
		String cmd;
		
		if ("sistemas".equals(opcion)) {
			cmd = "c=solve(A, b)";
		}
		else {
			cmd = "[x, p] = gausspiv(A, b)";
		}
		
		calcService.execute(cmd);
		
		if ("sistemas".equals(opcion)) {
			c = calcService.getArray("c");
		} else {
			c = calcService.getArray("x");
			p = calcService.getArray("p");
		}
	}
	
	public String obtenerCodigo(String opcion) {
		if ("sistemas".equals(opcion)) {
			return FileHelper.leerFichero(pathAlgebra + "/solve.m");
		}
		else {
			return FileHelper.leerFichero(pathAlgebra + "/gausspiv.m");
		}
	}
}
