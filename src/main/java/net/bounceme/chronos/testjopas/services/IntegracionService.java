package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.dto.IntegracionDTO;

@Service
@Slf4j
public class IntegracionService {

	@Value("${application.paths.funciones}")
	private String pathFunciones;
	
	@Value("${application.paths.integracion}")
	private String pathIntegracion;

	@Autowired
	@Qualifier("javaOctaveService")
	private transient CalcService calcService;

	@Getter
	private BigDecimal valor;
	
	@Getter
	private BigDecimal[] valores;
	
	@Getter
	private BigDecimal[] y;

	@PostConstruct
	public void initialize() {
		try {
			calcService.clearEnvironment();
			calcService.resetPath();
			calcService.addPath(pathFunciones);
			calcService.addPath(pathIntegracion);
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
		}
	}

	@SneakyThrows
	public void calcular(IntegracionDTO integracionDTO, String opcion) {

		calcService.passVariable("a", integracionDTO.getA());
		calcService.passVariable("b", integracionDTO.getB());
		calcService.passVariable("tolerancia", integracionDTO.getTolerancia());
		
		String cmd = "[valor,int,error]=integracion(a, b, tolerancia)";
		
		calcService.execute(cmd);
		
		String error = calcService.getString("error");
		if (StringUtils.isNotBlank(error)) {
			throw new Exception(error);
		}
		
		valor = calcService.getScalar("valor");
		valores = calcService.getArray("int");
	}
}
