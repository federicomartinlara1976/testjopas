package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AlgoritmoDtwDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4085451201013746161L;
	
	private BigDecimal factorDistancia;
	
	private String fichero1;
	
	private String fichero2;

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

	/**
	 * @return the fichero1
	 */
	public String getFichero1() {
		return fichero1;
	}

	/**
	 * @param fichero1 the fichero1 to set
	 */
	public void setFichero1(String fichero1) {
		this.fichero1 = fichero1;
	}

	/**
	 * @return the fichero2
	 */
	public String getFichero2() {
		return fichero2;
	}

	/**
	 * @param fichero2 the fichero2 to set
	 */
	public void setFichero2(String fichero2) {
		this.fichero2 = fichero2;
	}
}
