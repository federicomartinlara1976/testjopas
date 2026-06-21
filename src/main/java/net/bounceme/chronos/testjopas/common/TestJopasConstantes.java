package net.bounceme.chronos.testjopas.common;

import lombok.Getter;

public class TestJopasConstantes {

	/**
	 * The Enum Locales.
	 */
	public enum Paths {
		
		RAIZ("octave/met-num/raiz"), 
		INTERPOLACION("octave/met-num/interpolacion"), 
		INTEGRACION("octave/met-num/integracion"),
		ALGEBRA("octave/met-num/algebra"),
		ALGORITMOS("octave/met-num/algoritmos/dtw"),
		UTILIDADES("octave/utilidades"),
		FUNCIONES("octave/funciones");
		

		@Getter
		private String path;

		private Paths(String path) {
			this.path = path;
		}
	}

}
