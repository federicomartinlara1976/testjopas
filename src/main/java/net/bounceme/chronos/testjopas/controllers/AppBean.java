package net.bounceme.chronos.testjopas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import jakarta.enterprise.context.ApplicationScoped;
import net.bounceme.chronos.testjopas.services.CalcService;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * The Class AppBean.
 */
@ApplicationScoped
public class AppBean extends BaseBean {
	
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -195766865081737425L;
	
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