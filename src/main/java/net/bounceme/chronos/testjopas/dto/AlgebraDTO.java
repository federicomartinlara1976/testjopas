package net.bounceme.chronos.testjopas.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class AlgebraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4085451201013746161L;
	
	@Getter
	@Setter
	private Integer numeroCoeficientes;
	
	@Getter
	@Setter
	private BigDecimal[][] matrizCoeficientes;
	
	@Getter
	@Setter
	private BigDecimal[] terminos;
	
	@Getter
	@Setter
	private BigDecimal[] valoresIniciales;

	public AlgebraDTO() {
		numeroCoeficientes = 2;
		resetVars();
	}

	public void resetVars() {
		matrizCoeficientes = new BigDecimal[numeroCoeficientes][numeroCoeficientes];
		terminos = new BigDecimal[numeroCoeficientes];
		valoresIniciales = new BigDecimal[numeroCoeficientes];
		
		for (int i=0;i<numeroCoeficientes;i++) {
			for (int j=0;j<numeroCoeficientes;j++) {
				matrizCoeficientes[i][j] = BigDecimal.ZERO;
			}
			
			terminos[i] = BigDecimal.ZERO;
			valoresIniciales[i] = BigDecimal.ZERO; 
		}
	}
}
