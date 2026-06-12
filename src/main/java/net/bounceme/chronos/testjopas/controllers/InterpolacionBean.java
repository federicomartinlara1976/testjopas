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
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;
import net.bounceme.chronos.testjopas.dto.PuntoDTO;
import net.bounceme.chronos.testjopas.services.InterpolacionService;
import net.bounceme.chronos.testjopas.util.JsfHelper;

/**
 * The Class SessionBean.
 */
@Component
@Named
@ViewScoped
@Slf4j
public class InterpolacionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;
	
	@Autowired
	private InterpolacionService interpolacionService;

	/** The app bean. */
	@Autowired
	private SessionBean sessionBean;
	
	@Getter
	@Setter
	private InterpolacionDTO interpolacionDTO;
	
	@Getter
	private BigDecimal sp;
	
	@Getter
	private BigDecimal[][] dd;
	
	@Getter
	private BigDecimal[] y;

	@PostConstruct
	public void initialize() {
		reset();
	}
	
	public void calcular() {
		try {
			interpolacionService.calcular(interpolacionDTO, sessionBean.getOpcion());
			
			sp = interpolacionService.getSp();
			y = interpolacionService.getY();
			dd = interpolacionService.getDd();
		
		} catch (Exception e) {
			log.error("ERROR: {}", e.getMessage());
			JsfHelper.writeMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}
	}

	public void reset() {
		interpolacionDTO = new InterpolacionDTO();
		sp = null;
	}

	public void cambiarPuntos() {
		interpolacionDTO.setPuntos(new PuntoDTO[interpolacionDTO.getNumeroPuntos()]);

		// inicializa puntos
		for (int i = 0; i < interpolacionDTO.getNumeroPuntos(); i++) {
			interpolacionDTO.getPuntos()[i] = new PuntoDTO();
		}
	}
}
