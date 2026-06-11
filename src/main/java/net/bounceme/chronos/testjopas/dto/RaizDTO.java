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
	private BigDecimal puntoInicial;
	
	@Getter
	@Setter
	private BigDecimal primeraAproximacion;
	
	@Getter
	@Setter
	private BigDecimal tolerancia;
	
	@Getter
	@Setter
	private Integer iteraciones;

	public RaizDTO() {
		puntoInicial = BigDecimal.ZERO;
		primeraAproximacion = BigDecimal.ZERO;
		tolerancia = BigDecimal.ZERO;
		iteraciones = 1;
	}
}
