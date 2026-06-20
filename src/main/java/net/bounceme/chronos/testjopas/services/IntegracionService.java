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

	@Value("${application.paths.integracion}")
	private String pathIntegracion;

	@Autowired
	@Qualifier("javaOctaveService")
	private transient CalcService calcService;

	@Getter
	private BigDecimal valor;

	@Getter
	private BigDecimal[] valores;

	@PostConstruct
	public void initialize() {
		try {
			calcService.clearEnvironment();
			calcService.resetPath();
			calcService.addPath(pathIntegracion);
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
		}
	}

	@SneakyThrows
	public void calcular(IntegracionDTO integracionDTO, String opcion) {

		calcService.passVariable("a", integracionDTO.getA());
		calcService.passVariable("b", integracionDTO.getB());
		calcService.passVariable("iteraciones", integracionDTO.getIteraciones());

		String cmd;
		if ("simpson".equals(opcion)) {
			calcService.passVariable("h", integracionDTO.getH());
			cmd = "sum=simpson(a, b, iteraciones, h)";
		} else {
			calcService.passVariable("tolerancia", integracionDTO.getTolerancia());
			cmd = "[valor, int, error]=integracion(a, b, tolerancia, iteraciones)";
		}

		calcService.execute(cmd);

		if ("integracion".equals(opcion)) {
			String error = calcService.getString("error");
			if (StringUtils.isNotBlank(error)) {
				throw new Exception(error);
			}
		}

		if ("integracion".equals(opcion)) {
			valor = calcService.getScalar("valor");
			valores = calcService.getArray("int");
		} else {
			valor = calcService.getScalar("sum");
		}
	}
	
	public String obtenerFuncion() {
		// TODO - Obtenerla del fichero .m
		return "function y = f(x) \r\n"
				+ "	y = exp(sqrt(x^2+1));\r\n"
				+ "endfunction";
	}
}
