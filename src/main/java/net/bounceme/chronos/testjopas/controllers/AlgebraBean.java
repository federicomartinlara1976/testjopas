package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.dto.AlgebraDTO;
import net.bounceme.chronos.testjopas.services.utils.Utilidades;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ViewScoped
@Slf4j
public class AlgebraBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "algebraBean";

	/** The app bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	@Autowired
	private Utilidades utilidades;

	@Getter
	@Setter
	private AlgebraDTO algebraDTO;
	
	@Getter
	private BigDecimal[] c;

	@PostConstruct
	public void initialize() {
		try {
			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper()
					.getSessionAttribute("path");

			initializePaths(paths);
		} catch (Exception e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}
	
	@SneakyThrows
	private void initializePaths(TestJopasConstantes.Paths paths) {
		appBean.getCalcService().clearEnvironment();
		appBean.getCalcService().resetPath();
		appBean.getCalcService().addPath(utilidades.getPathFromResource(Paths.FUNCIONES.getPath()));
		appBean.getCalcService().addPath(utilidades.getPathFromResource(paths.getPath()));

		reset();
	}

	public void calcular() {
		try {
			appBean.getCalcService().passVariable("A", algebraDTO.getMatrizCoeficientes());
			appBean.getCalcService().passVariable("b", algebraDTO.getTerminos());
			
			String cmd = "c=solve(A, b)";
			appBean.getCalcService().execute(cmd);
				
			c = appBean.getCalcService().getArray("c");
		} catch (Exception e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}

	public void reset() {
		algebraDTO = new AlgebraDTO();
		c = null;
	}
	
	public void cambiarCoeficientes() {
		Integer n = algebraDTO.getNumeroCoeficientes();
		algebraDTO = new AlgebraDTO(n);
	}
}
