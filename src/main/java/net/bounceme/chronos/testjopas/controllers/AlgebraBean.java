package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

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
import net.bounceme.chronos.testjopas.dto.AlgebraDTO;
import net.bounceme.chronos.testjopas.util.JsfHelper;

/**
 * The Class SessionBean.
 */
@Component
@Named
@ViewScoped
@Slf4j
public class AlgebraBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;
	
	@Value("${application.paths.utilidades}")
	private String pathUtilidades;

	@Autowired
	private AppBean appBean;

	@Getter
	@Setter
	private AlgebraDTO algebraDTO;
	
	@Getter
	private BigDecimal[] c;

	@PostConstruct
	public void initialize() {
		try {
			appBean.getCalcService().clearEnvironment();
			appBean.getCalcService().resetPath();
			appBean.getCalcService().addPath(pathUtilidades);

			reset();
		} catch (Exception e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
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
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
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
