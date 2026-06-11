package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import net.bounceme.chronos.testjopas.services.CalcService;
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
	
	@Autowired
	@Qualifier("javaOctaveService")
	private transient CalcService calcService;

	@Autowired
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
			calcService.clearEnvironment();
			calcService.resetPath();
			calcService.addPath(pathFunciones);

			reset();
		} catch (Exception e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}

	public void calcular() {
		try {
			calcService.passVariable("puntoInicial", raizDTO.getPuntoInicial());
			calcService.passVariable("tolerancia", raizDTO.getTolerancia());
			calcService.passVariable("iteraciones", raizDTO.getIteraciones());
	
			if ("secante".equals(sessionBean.getOpcion())) {
				calcService.passVariable("primeraAproximacion", raizDTO.getPrimeraAproximacion());
			}
			
			// Ejecuta el comando
			String cmd;
			if ("newton".equals(sessionBean.getOpcion())) {
				cmd = "[x,sol,ni,error]=newtonRaphson(puntoInicial, tolerancia, iteraciones)";
			}
			else {
				cmd = "[x,sol,ni,error]=secante(puntoInicial, primeraAproximacion, tolerancia, iteraciones)";
			}
			
			calcService.execute(cmd);
			
			// Las variables de salida son las que están definidas entre [] en el comando
			error = calcService.getString("error");
			if (StringUtils.isNotBlank(error)) {
				throw new Exception(error);
			}
			else {
				sol = calcService.getScalar("sol");
				iteraciones = calcService.getIntScalar("ni");
				valores = calcService.getArray("x");
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
