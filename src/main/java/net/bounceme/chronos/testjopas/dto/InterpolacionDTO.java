package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class InterpolacionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4783998953563598183L;
	
	@NotNull
	private BigDecimal puntoInterpolar;
	
	@Min(1)
	@Max(10)
	private Integer numeroPuntos;
	
	private PuntoDTO[] puntos;

	public InterpolacionDTO() {
		puntoInterpolar = BigDecimal.ZERO;
		numeroPuntos = 1;
		puntos = new PuntoDTO[numeroPuntos];
		
		puntos[0] = new PuntoDTO();
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

	/**
	 * @return the puntos
	 */
	public PuntoDTO[] getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(PuntoDTO[] puntos) {
		this.puntos = puntos;
	}
}
