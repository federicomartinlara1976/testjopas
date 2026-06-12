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
import net.bounceme.chronos.testjopas.dto.RaizDTO;
import net.bounceme.chronos.testjopas.services.RaizService;
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

	@Autowired
	private RaizService raizService;

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

	@PostConstruct
	public void initialize() {
		reset();
	}

	public void calcular() {
		try {
			raizService.calcular(raizDTO, sessionBean.getOpcion());
			
			sol = raizService.getSolucion();
			iteraciones = raizService.getIteraciones();
			valores = raizService.getValores();
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}
	}
	
	public void reset() {
		raizDTO = new RaizDTO();
		
		sol = null;
		valores = new BigDecimal[0];
	}
}
