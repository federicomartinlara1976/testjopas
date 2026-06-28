package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;

public interface InterpolacionService {

	BigDecimal getSp();

	BigDecimal[][] getDd();

	BigDecimal[] getY();

	void initialize();

	void calcular(InterpolacionDTO interpolacionDTO, String opcion);

	BigDecimal calcularValor(BigDecimal punto);

	String obtenerFuncion();

	String obtenerCodigo(String opcion);

}