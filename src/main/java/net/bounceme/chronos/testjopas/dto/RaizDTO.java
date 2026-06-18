package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class RaizDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3424630592838085778L;
	
	@Getter
	@Setter
	private BigDecimal puntoInicial = BigDecimal.ZERO;
	
	@Getter
	@Setter
	private BigDecimal primeraAproximacion = BigDecimal.ZERO;
	
	@Getter
	@Setter
	private BigDecimal tolerancia = BigDecimal.ZERO;
	
	@Getter
	@Setter
	private Integer iteraciones = 1;
}
