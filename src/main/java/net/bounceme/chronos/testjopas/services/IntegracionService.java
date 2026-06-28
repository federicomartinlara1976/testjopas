package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import net.bounceme.chronos.testjopas.dto.IntegracionDTO;

public interface IntegracionService {

	BigDecimal getValor();

	BigDecimal[] getValores();

	void initialize();

	void calcular(IntegracionDTO integracionDTO, String opcion);

	String obtenerFuncion();

	String obtenerCodigo(String opcion);

}