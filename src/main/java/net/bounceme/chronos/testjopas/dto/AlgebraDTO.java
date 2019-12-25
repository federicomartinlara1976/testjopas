package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AlgebraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4085451201013746161L;
	
	private Integer numeroCoeficientes;
	
	private BigDecimal[][] matrizCoeficientes;
	
	private BigDecimal[] terminos;

	public AlgebraDTO() {
		numeroCoeficientes = 2;
		initialize();
	}
	
	public AlgebraDTO(Integer numeroCoeficientes) {
		this.numeroCoeficientes = numeroCoeficientes;
		initialize();
	}

	private void initialize() {
		matrizCoeficientes = new BigDecimal[numeroCoeficientes][numeroCoeficientes];
		terminos = new BigDecimal[numeroCoeficientes];
		
		for (int i=0;i<numeroCoeficientes;i++) {
			for (int j=0;j<numeroCoeficientes;j++) {
				matrizCoeficientes[i][j] = BigDecimal.ZERO;
			}
			
			terminos[i] = BigDecimal.ZERO;
		}
	}

	/**
	 * @return the numeroCoeficientes
	 */
	public Integer getNumeroCoeficientes() {
		return numeroCoeficientes;
	}

	/**
	 * @param numeroCoeficientes the numeroCoeficientes to set
	 */
	public void setNumeroCoeficientes(Integer numeroCoeficientes) {
		this.numeroCoeficientes = numeroCoeficientes;
	}

	/**
	 * @return the matrizCoeficientes
	 */
	public BigDecimal[][] getMatrizCoeficientes() {
		return matrizCoeficientes;
	}

	/**
	 * @param matrizCoeficientes the matrizCoeficientes to set
	 */
	public void setMatrizCoeficientes(BigDecimal[][] matrizCoeficientes) {
		this.matrizCoeficientes = matrizCoeficientes;
	}

	/**
	 * @return the terminos
	 */
	public BigDecimal[] getTerminos() {
		return terminos;
	}

	/**
	 * @param terminos the terminos to set
	 */
	public void setTerminos(BigDecimal[] terminos) {
		this.terminos = terminos;
	}
}
