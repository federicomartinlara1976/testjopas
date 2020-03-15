package net.bounceme.chronos.testjopas.common;

import net.bounceme.chronos.utils.common.Constantes;

public class TestJopasConstantes extends Constantes {

	/**
	 * The Enum Locales.
	 */
	public enum Paths {
		
		raiz("octave/met-num/raiz"), 
		interpolacion("octave/met-num/interpolacion"), 
		integracion("octave/met-num/integracion"),
		algebra("octave/met-num/algebra"),
		algoritmos("octave/met-num/algoritmos/dtw"),
		utilidades("octave/utilidades"),
		funciones("octave/funciones");
		

		/** The path. */
		private String path;

		/**
		 * Instantiates a new locales.
		 *
		 * @param locale the locale
		 */
		private Paths(String path) {
			this.path = path;
		}

		/**
		 * Value.
		 *
		 * @return the locale
		 */
		public String value() {
			return path;
		}
	}

}
