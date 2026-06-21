package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.bounceme.chronos.testjopas.dto.IntegracionDTO;
import net.bounceme.chronos.testjopas.services.IntegracionService;
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

	@Autowired
	private IntegracionService integracionService;
	
	@Autowired
	private SessionBean sessionBean;

	@Getter
	@Setter
	private IntegracionDTO integracionDTO;
	
	@Getter
	private BigDecimal valor;
	
	@Getter
	private BigDecimal[] valores;
	
	@Getter
	private String codigo;
	
	@Getter
	private String titulo;
	
	@Getter
	private String icono;
	
	@PostConstruct
	public void initialize() {
		reset();
	}

	public void calcular() {
		try {
			integracionService.calcular(integracionDTO, sessionBean.getOpcion());
			
			valor = integracionService.getValor();
			valores = integracionService.getValores();
		} catch (Exception e) {
			log.error("ERROR:", e);
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error.");
		}
	}
	
	public void reset() {
		integracionDTO = new IntegracionDTO();
		
		valor = null;
		valores = new BigDecimal[0];
	}
	
	public void obtenerFuncion() {
		titulo = "Función";
		icono = "pi pi-chart-line";
		codigo = integracionService.obtenerFuncion();
	}
	
	public void obtenerCodigo() {
		titulo = "Código";
		icono = "pi pi-bars";
		codigo = integracionService.obtenerCodigo(sessionBean.getOpcion());
	}
}
