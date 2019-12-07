package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class SessionBean.
 */
@ManagedBean(name = TestJopasBean.NAME)
@ViewScoped
public class TestJopasBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;
	
	/** The Constant NAME. */
	public static final String NAME = "testjopasBean";
	
	/** The session bean. */
	@ManagedProperty(value="#{appBean}")
	private AppBean appBean;
	
	@PostConstruct
	public void initialize() {
		try {
			appBean.getJopasService().resetPath();
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
}
