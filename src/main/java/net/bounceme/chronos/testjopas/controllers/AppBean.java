package net.bounceme.chronos.testjopas.controllers;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

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
}