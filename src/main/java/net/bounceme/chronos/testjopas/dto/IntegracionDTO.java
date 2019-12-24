package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class IntegracionDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -691155947956632851L;

	private BigDecimal a;
	
	private BigDecimal b;
	
	private BigDecimal tolerancia;

	public IntegracionDTO() {
		a = BigDecimal.ZERO;
		b = BigDecimal.ZERO;
		tolerancia = BigDecimal.ZERO;
	}

	/**
	 * @return the a
	 */
	public BigDecimal getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(BigDecimal a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public BigDecimal getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(BigDecimal b) {
		this.b = b;
	}

	/**
	 * @return the tolerancia
	 */
	public BigDecimal getTolerancia() {
		return tolerancia;
	}

	/**
	 * @param tolerancia the tolerancia to set
	 */
	public void setTolerancia(BigDecimal tolerancia) {
		this.tolerancia = tolerancia;
	}
}
