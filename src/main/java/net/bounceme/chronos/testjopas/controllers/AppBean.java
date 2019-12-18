package net.bounceme.chronos.testjopas.controllers;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.bounceme.chronos.testjopas.services.CalcService;
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
	@Qualifier("javaOctaveService")
	private transient CalcService calcService;

	/**
	 * @return the jopasService
	 */
	public CalcService getCalcService() {
		return calcService;
	}
}