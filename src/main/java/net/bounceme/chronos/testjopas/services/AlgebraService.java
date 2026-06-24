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
		
		passVariables(algebraDTO, opcion);
		
		String cmd;
		if ("sistemas".equals(opcion)) {
			cmd = "x = solve(A, b)";
		}
		else if ("gauss".equals(opcion)) {
			cmd = "[x, p] = gausspiv(A, b)";
		}
		else if ("factorizacionlu".equals(opcion)) {
			cmd = "[L, U, x, error] = factorizacionLU(A, b)";
		}
		else {
			cmd = "[x, iter] = gradconj(A, b, x0)";
		}
		
		calcService.execute(cmd);
		
		passResultado(opcion);
	}
	
	private void passVariables(AlgebraDTO algebraDTO, String opcion) {
		calcService.passVariable("A", algebraDTO.getMatrizCoeficientes());
		calcService.passVariable("b", algebraDTO.getTerminos());
		
		if ("gradconj".equals(opcion)) {
			calcService.passVariable("x0", algebraDTO.getValoresIniciales());
		}
	}
	
	@SneakyThrows
	private void passResultado(String opcion) {
		if ("factorizacionlu".equals(opcion)) {
			String error = calcService.getString("error");
			if (StringUtils.isNotBlank(error)) {
				throw new Exception(error);
			}
		}
			
		c = calcService.getArray("x");
	}
	
	public String obtenerCodigo(String opcion) {
		if ("sistemas".equals(opcion)) {
			return FileHelper.leerFichero(pathAlgebra + "/solve.m");
		}
		else if ("gauss".equals(opcion)) {
			return FileHelper.leerFichero(pathAlgebra + "/gausspiv.m");
		}
		else {
			return FileHelper.leerFichero(pathAlgebra + "/factorizacionLU.m");
		}
	}
}
