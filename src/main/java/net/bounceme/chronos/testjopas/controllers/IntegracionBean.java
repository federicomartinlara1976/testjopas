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
import net.bounceme.chronos.testjopas.dto.IntegracionDTO;
import net.bounceme.chronos.testjopas.util.JsfHelper;

/**
 * The Class SessionBean.
 */
@Component
@Named
@ViewScoped
@Slf4j
public class IntegracionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;
	
	@Value("${application.paths.funciones}")
	private String pathFunciones;

	/** The appBean bean. */
	@Autowired
	private AppBean appBean;

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
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}
	
	public void reset() {
		integracionDTO = new IntegracionDTO();
		
		valor = null;
		valores = new BigDecimal[0];
		error = StringUtils.EMPTY;
	}
}
