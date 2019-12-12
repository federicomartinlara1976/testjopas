package net.bounceme.chronos.testjopas.controllers;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import net.bounceme.chronos.testjopas.services.JoPasService;
import net.bounceme.chronos.utils.jopas.JopasFactory;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class AppBean.
 */
@ManagedBean(name=AppBean.NAME)
@ApplicationScoped
public class AppBean extends BaseBean {
	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -195766865081737425L;

	/** The Constant NAME. */
	public static final String NAME = "appBean";
	
	@Autowired
	private transient JoPasService jopasService;
	
	private JopasFactory jopasFactory;
	
	public AppBean() {
		super();
		jopasFactory = JopasFactory.getInstance();
	}

	/**
	 * @return the jopasService
	 */
	public JoPasService getJopasService() {
		return jopasService;
	}

	/**
	 * @return
	 */
	public JopasFactory getJopasFactory() {
		return jopasFactory;
	}
}