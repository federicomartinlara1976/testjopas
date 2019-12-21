package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;
import net.bounceme.chronos.testjopas.dto.PuntoDTO;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ManagedBean(name = InterpolacionBean.NAME)
@ViewScoped
public class InterpolacionBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;
	
	/** The Constant NAME. */
	public static final String NAME = "interpolacionBean";
	
	/** The app bean. */
	@ManagedProperty(value="#{appBean}")
	private AppBean appBean;
	
	/** The app bean. */
	@ManagedProperty(value="#{sessionBean}")
	private SessionBean sessionBean;
	
	private InterpolacionDTO interpolacionDTO;
	
	@PostConstruct
	public void initialize() {
		try {
			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper().getSessionAttribute("path"); 
			
			appBean.getCalcService().clearEnvironment();
			appBean.getCalcService().resetPath();
			appBean.getCalcService().addPath(Paths.funciones.value());
			appBean.getCalcService().addPath(paths.value());
			
			reset();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void calcular() {
	}
	
	public void reset() {
		interpolacionDTO = new InterpolacionDTO();
	}
	
	public void cambiarPuntos() {
		interpolacionDTO.setPuntos(new PuntoDTO[interpolacionDTO.getNumeroPuntos()]);
		
		// inicializa puntos
		for(int i=0;i<interpolacionDTO.getNumeroPuntos();i++) {
			interpolacionDTO.getPuntos()[i] = new PuntoDTO();
		}
	}

	/**
	 * @return the appBean
	 */
	public AppBean getAppBean() {
		return appBean;
	}

	/**
	 * @param appBean the appBean to set
	 */
	public void setAppBean(AppBean appBean) {
		this.appBean = appBean;
	}
	

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}

	/**
	 * @return the interpolacionDTO
	 */
	public InterpolacionDTO getInterpolacionDTO() {
		return interpolacionDTO;
	}

	/**
	 * @param interpolacionDTO the interpolacionDTO to set
	 */
	public void setInterpolacionDTO(InterpolacionDTO interpolacionDTO) {
		this.interpolacionDTO = interpolacionDTO;
	}
}
