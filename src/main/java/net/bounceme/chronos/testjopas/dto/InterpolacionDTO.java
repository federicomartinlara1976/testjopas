package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class InterpolacionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4783998953563598183L;
	
	private BigDecimal puntoInterpolar;
	
	private Integer numeroPuntos;

	public InterpolacionDTO() {
		puntoInterpolar = BigDecimal.ZERO;
		numeroPuntos = 1;
	}

	/**
	 * @return the puntoInterpolar
	 */
	public BigDecimal getPuntoInterpolar() {
		return puntoInterpolar;
	}

	/**
	 * @param puntoInterpolar the puntoInterpolar to set
	 */
	public void setPuntoInterpolar(BigDecimal puntoInterpolar) {
		this.puntoInterpolar = puntoInterpolar;
	}

	/**
	 * @return the numeroPuntos
	 */
	public Integer getNumeroPuntos() {
		return numeroPuntos;
	}

	/**
	 * @param numeroPuntos the numeroPuntos to set
	 */
	public void setNumeroPuntos(Integer numeroPuntos) {
		this.numeroPuntos = numeroPuntos;
	}
}
