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
	public void calcular(AlgebraDTO algebraDTO) {
		calcService.passVariable("A", algebraDTO.getMatrizCoeficientes());
		calcService.passVariable("b", algebraDTO.getTerminos());
		
		String cmd = "c=solve(A, b)";
		calcService.execute(cmd);
			
		c = calcService.getArray("c");
	}
	
	public String obtenerCodigo() {
		return FileHelper.leerFichero(pathAlgebra + "/solve.m");
	}
}
