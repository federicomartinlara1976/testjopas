package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AlgoritmoDtwDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4085451201013746161L;
	
	private BigDecimal factorDistancia;

	public AlgoritmoDtwDTO() {
		factorDistancia = BigDecimal.ZERO;
	}

	/**
	 * @return the factorDistancia
	 */
	public BigDecimal getFactorDistancia() {
		return factorDistancia;
	}

	/**
	 * @param factorDistancia the factorDistancia to set
	 */
	public void setFactorDistancia(BigDecimal factorDistancia) {
		this.factorDistancia = factorDistancia;
	}
}
