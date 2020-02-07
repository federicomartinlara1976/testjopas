package net.bounceme.chronos.testjopas.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.bounceme.chronos.logger.Log;
import net.bounceme.chronos.logger.LogFactory;
import net.bounceme.chronos.testjopas.exceptions.ServiceException;
import net.bounceme.chronos.testjopas.services.FilesService;
import net.bounceme.chronos.utils.jsf.controller.BaseBean;

/**
 * @author Federico Martín Lara
 *
 */
@ManagedBean(name = UtilidadesBean.NAME)
@ViewScoped
public class UtilidadesBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2350030970399677473L;

	/** The Constant NAME. */
	public static final String NAME = "utilidadesBean";

	/** The logger. */
	private transient Log logger;

	/** The app bean. */
	@ManagedProperty(value = "#{sessionBean}")
	private SessionBean sessionBean;
	
	@Autowired
	private transient FilesService filesService;

	@PostConstruct
	public void initialize() {
		logger = LogFactory.getInstance().getLogger(UtilidadesBean.class, "LOG4J");
	}

	public SessionBean getSessionBean() {
		return sessionBean;
	}

	public void setSessionBean(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
}
