package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.dto.RaizDTO;
import net.bounceme.chronos.testjopas.util.JsfHelper;

/**
 * The Class SessionBean.
 */
@Component
@Named
@ViewScoped
@Slf4j
public class RaizBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	@Value("${application.paths.funciones}")
	private String pathFunciones;

	/** The appBean bean. */
	@Autowired
	@Getter
	@Setter
	private AppBean appBean;

	/** The sessionBean bean. */
	@Autowired
	@Getter
	@Setter
	private SessionBean sessionBean;
	
	@Getter
	@Setter
	private RaizDTO raizDTO;
	
	@Getter
	@Setter
	private BigDecimal sol;
	
	@Getter
	private Integer iteraciones;
	
	@Getter
	private BigDecimal[] valores;
	
	private String error;

	@PostConstruct
	public void initialize() {
		try {
			appBean.getCalcService().clearEnvironment();
			appBean.getCalcService().resetPath();
			appBean.getCalcService().addPath(pathFunciones);

			reset();
		} catch (Exception e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}

	public void calcular() {
		try {
			appBean.getCalcService().passVariable("puntoInicial", raizDTO.getPuntoInicial());
			appBean.getCalcService().passVariable("tolerancia", raizDTO.getTolerancia());
			appBean.getCalcService().passVariable("iteraciones", raizDTO.getIteraciones());
	
			if ("secante".equals(sessionBean.getOpcion())) {
				appBean.getCalcService().passVariable("primeraAproximacion", raizDTO.getPrimeraAproximacion());
			}
			
			// Ejecuta el comando
			String cmd;
			if ("newton".equals(sessionBean.getOpcion())) {
				cmd = "[x,sol,ni,error]=newtonRaphson(puntoInicial, tolerancia, iteraciones)";
			}
			else {
				cmd = "[x,sol,ni,error]=secante(puntoInicial, primeraAproximacion, tolerancia, iteraciones)";
			}
			
			appBean.getCalcService().execute(cmd);
			
			// Las variables de salida son las que están definidas entre [] en el comando
			error = appBean.getCalcService().getString("error");
			if (StringUtils.isNotBlank(error)) {
				throw new Exception(error);
			}
			else {
				sol = appBean.getCalcService().getScalar("sol");
				iteraciones = appBean.getCalcService().getIntScalar("ni");
				valores = appBean.getCalcService().getArray("x");
			}

		} catch (Exception e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}
	
	public void reset() {
		raizDTO = new RaizDTO();
		
		sol = null;
		valores = new BigDecimal[0];
		error = StringUtils.EMPTY;
	}
}
