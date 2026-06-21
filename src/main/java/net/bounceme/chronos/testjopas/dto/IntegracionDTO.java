package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class IntegracionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -691155947956632851L;

	@Getter
	@Setter
	private BigDecimal a;
	
	@Getter
	@Setter
	private BigDecimal b;
	
	@Getter
	@Setter
	private BigDecimal tolerancia;
	
	@Getter
	@Setter
	private BigDecimal h;
	
	@Getter
	@Setter
	private Integer iteraciones;

	public IntegracionDTO() {
		a = BigDecimal.ZERO;
		b = BigDecimal.ZERO;
		tolerancia = BigDecimal.ZERO;
		iteraciones = 0;
		h = BigDecimal.ZERO;
	}
}
