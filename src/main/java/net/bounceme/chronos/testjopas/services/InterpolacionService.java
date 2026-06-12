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
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;

@Service
@Slf4j
public class InterpolacionService {

	@Value("${application.paths.funciones}")
	private String pathFunciones;

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
			calcService.addPath(pathFunciones);
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
	public void calcularValor(InterpolacionDTO interpolacionDTO, Integer index) {
		BigDecimal punto = interpolacionDTO.getPuntos()[index].getPunto();
		calcService.passVariable("x", punto);

		String cmd = "y=f(x)";
		calcService.execute(cmd);

		BigDecimal scalarY = calcService.getScalar("y");
		if (scalarY != null) {
			interpolacionDTO.getPuntos()[index].setValor(scalarY);
		}
	}
	
	private BigDecimal[] toArrayPuntos(InterpolacionDTO interpolacionDTO) {
		BigDecimal[] puntos = new BigDecimal[interpolacionDTO.getNumeroPuntos()];
		for (int i=0;i<interpolacionDTO.getNumeroPuntos();i++) {
			puntos[i] = interpolacionDTO.getPuntos()[i].getPunto();
		}
		return puntos;
	}
	
	private BigDecimal[] toArrayValores(InterpolacionDTO interpolacionDTO) {
		BigDecimal[] valores = new BigDecimal[interpolacionDTO.getNumeroPuntos()];
		for (int i=0;i<interpolacionDTO.getNumeroPuntos();i++) {
			valores[i] = interpolacionDTO.getPuntos()[i].getValor();
		}
		return valores;
	}
}
