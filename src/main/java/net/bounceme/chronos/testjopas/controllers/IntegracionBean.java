package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.dto.IntegracionDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.utils.Utilidades;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ViewScoped
@Slf4j
public class IntegracionBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The appBean bean. */
	@ManagedProperty(value = "#{appBean}")
	private AppBean appBean;

	/** The sessionBean bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	@Autowired
	private Utilidades utilidades;

	@Getter
	@Setter
	private IntegracionDTO integracionDTO;
	
	@Getter
	private BigDecimal valor;
	
	@Getter
	private BigDecimal[] valores;
	
	private String error;

	@PostConstruct
	public void initialize() {
		try {
			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper()
					.getSessionAttribute("path");

			initializePaths(paths);
		} catch (ServiceException e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}
	
	private void initializePaths(TestJopasConstantes.Paths paths) throws ServiceException {
		appBean.getCalcService().clearEnvironment();
		appBean.getCalcService().resetPath();
		appBean.getCalcService().addPath(utilidades.getPathFromResource(Paths.FUNCIONES.getPath()));
		appBean.getCalcService().addPath(utilidades.getPathFromResource(paths.getPath()));

		reset();
	}

	public void calcular() {
		try {
			appBean.getCalcService().passVariable("a", integracionDTO.getA());
			appBean.getCalcService().passVariable("b", integracionDTO.getB());
			appBean.getCalcService().passVariable("tolerancia", integracionDTO.getTolerancia());
			
			String cmd = "[valor,int,error]=integracion(a, b, tolerancia)";
			
			appBean.getCalcService().execute(cmd);
			
			// Las variables de salida son las que están definidas entre [] en el comando
			error = appBean.getCalcService().getString("error");
			if (StringUtils.isNotBlank(error)) {
				throw new Exception(error);
			}
			else {
				valor = appBean.getCalcService().getScalar("valor");
				valores = appBean.getCalcService().getArray("int");
			}

		} catch (Exception e) {
			log.error("ERROR:", e);
			this.addErrorMessage(e);
		}
	}
	
	public void reset() {
		integracionDTO = new IntegracionDTO();
		
		valor = null;
		valores = new BigDecimal[0];
		error = StringUtils.EMPTY;
	}
}
