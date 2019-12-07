package net.bounceme.chronos.testjopas.common;

import net.bounceme.chronos.utils.common.Constantes;

public class TestJopasConstantes extends Constantes {

	/**
	 * The Enum Locales.
	 */
	public enum Paths {
		
		/** The es. */
		raiz("F:/usuarios/frederik/octave/met-num/raiz"), 
		/** The en. */
		interpolacion("F:/usuarios/frederik/octave/met-num/interpolacion"), 
		/** The fr. */
		integracion("F:/usuarios/frederik/octave/met-num/integracion");

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
