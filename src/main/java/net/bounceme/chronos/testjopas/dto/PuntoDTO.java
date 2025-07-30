package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class PuntoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1355419264401511474L;
	
	@Getter
	@Setter
	private BigDecimal punto = BigDecimal.ZERO;
	
	@Getter
	@Setter
	private BigDecimal valor = BigDecimal.ZERO;
}
