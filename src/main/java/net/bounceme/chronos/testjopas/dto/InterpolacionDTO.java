package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class InterpolacionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4783998953563598183L;
	
	private BigDecimal puntoInterpolar;

	public InterpolacionDTO() {
		// TODO Auto-generated constructor stub
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
}
