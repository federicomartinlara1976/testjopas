package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import net.bounceme.chronos.testjopas.dto.AlgebraDTO;

public interface AlgebraService {

	BigDecimal[] getC();

	BigDecimal[] getP();

	void initialize();

	void calcular(AlgebraDTO algebraDTO, String opcion);

	String obtenerCodigo(String opcion);

}