package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class RaizDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3424630592838085778L;
	
	private BigDecimal puntoInicial;
	
	private BigDecimal tolerancia;
	
	private Integer iteraciones;

	public RaizDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the puntoInicial
	 */
	public BigDecimal getPuntoInicial() {
		return puntoInicial;
	}

	/**
	 * @param puntoInicial the puntoInicial to set
	 */
	public void setPuntoInicial(BigDecimal puntoInicial) {
		this.puntoInicial = puntoInicial;
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

	/**
	 * @return the iteraciones
	 */
	public Integer getIteraciones() {
		return iteraciones;
	}

	/**
	 * @param iteraciones the iteraciones to set
	 */
	public void setIteraciones(Integer iteraciones) {
		this.iteraciones = iteraciones;
	}
}
