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
	private Integer numeroCoeficientes;
	
	@Getter
	@Setter
	private BigDecimal[][] matrizCoeficientes;
	
	@Getter
	@Setter
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
}
