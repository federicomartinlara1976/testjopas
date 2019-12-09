package net.bounceme.chronos.testjopas.common;

import net.bounceme.chronos.utils.common.Constantes;

public class TestJopasConstantes extends Constantes {

	/**
	 * The Enum Locales.
	 */
	public enum Paths {
		
		raiz("F:/usuarios/frederik/octave/met-num/raiz"), 
		interpolacion("F:/usuarios/frederik/octave/met-num/interpolacion"), 
		integracion("F:/usuarios/frederik/octave/met-num/integracion"),
		funciones("F:/usuarios/frederik/octave/funciones");

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
