package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PuntoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1355419264401511474L;
	
	private BigDecimal punto;
	
	private BigDecimal valor;

	public PuntoDTO() {
		punto = BigDecimal.ZERO;
		valor = BigDecimal.ZERO;
	}

	/**
	 * @return the punto
	 */
	public BigDecimal getPunto() {
		return punto;
	}

	/**
	 * @param punto the punto to set
	 */
	public void setPunto(BigDecimal punto) {
		this.punto = punto;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
