package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.bounceme.chronos.testjopas.common.TestJopasConstantes;
import net.bounceme.chronos.testjopas.common.TestJopasConstantes.Paths;
import net.bounceme.chronos.testjopas.dto.InterpolacionDTO;
import net.bounceme.chronos.testjopas.dto.RaizDTO;
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
	
	/** The session bean. */
	@ManagedProperty(value="#{appBean}")
	private AppBean appBean;
	
	private InterpolacionDTO interpolacionDTO;
	
	@PostConstruct
	public void initialize() {
		try {
			TestJopasConstantes.Paths paths = (TestJopasConstantes.Paths) this.getJsfHelper().getSessionAttribute("path"); 
			
			appBean.getJopasService().clearEnvironment();
			appBean.getJopasService().resetPath();
			appBean.getJopasService().addPath(Paths.funciones.value());
			appBean.getJopasService().addPath(paths.value());
			
			interpolacionDTO = new InterpolacionDTO();
		} catch (ServiceException e) {
			e.printStackTrace();
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
