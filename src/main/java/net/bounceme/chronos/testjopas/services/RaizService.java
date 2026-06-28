package net.bounceme.chronos.testjopas.services;

import java.math.BigDecimal;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import net.bounceme.chronos.testjopas.dto.RaizDTO;

public interface RaizService {

	BigDecimal getSolucion();

	Integer getIteraciones();

	BigDecimal[] getValores();

	void initialize();

	void calcular(RaizDTO raizDTO, String opcion);

	String obtenerFuncion();

	String obtenerDerivada();

	String obtenerCodigo(String opcion);

}