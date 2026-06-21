package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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
}
